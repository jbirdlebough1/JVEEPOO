import asyncio
import aiohttp
import subprocess
import json
import base64
import os
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def test_preview_and_charts():
    print("Testing preview.html and live charts rendering via CDP...")
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9255",
        "--disable-gpu",
        "--window-size=1600,1050",
        "about:blank"
    ])
    time.sleep(2.0)

    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9255/json") as resp:
                targets = await resp.json()
                ws_url = targets[0]["webSocketDebuggerUrl"]

            async with session.ws_connect(ws_url) as ws:
                msg_id = 1
                async def send(method, params=None):
                    nonlocal msg_id
                    mid = msg_id
                    msg_id += 1
                    payload = {"id": mid, "method": method}
                    if params: payload["params"] = params
                    await ws.send_json(payload)
                    while True:
                        res = await ws.receive_json()
                        if res.get("id") == mid:
                            return res.get("result", {})

                await send("Page.enable")
                await send("Runtime.enable")

                # 1. Test live application charts at http://localhost:8899/index.html?theme=light
                print("1. Checking live application charts on Reports tab...")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.5)

                chart_audit_script = """
                (() => {
                    switchTab('reports');
                    if (typeof renderReportsView === 'function') renderReportsView();
                    if (typeof renderTallSynchronizedChart === 'function') renderTallSynchronizedChart('daily');
                    
                    const charts = [
                        'tallChartSvgContainer', 'svgStepGlucoseCorrelator', 'curveTimelineGlucose',
                        'curveTimelineA1C', 'curveTimelineTemp', 'curveTimelineHeartRate',
                        'curveTimelineBP', 'curveTimelineSpO2', 'curveTimelineHRV',
                        'svgLorenzScatter', 'curveTimelineSleep', 'curveTimelineExercise'
                    ];

                    const results = {};
                    charts.forEach(id => {
                        const el = document.getElementById(id);
                        if (!el) {
                            results[id] = 'MISSING_CONTAINER';
                        } else {
                            const svg = el.querySelector('svg');
                            const polyline = el.querySelector('polyline');
                            const path = el.querySelector('path');
                            const circles = el.querySelectorAll('circle');
                            const texts = el.querySelectorAll('text');
                            results[id] = {
                                hasSvg: !!svg,
                                numCircles: circles.length,
                                numTexts: texts.length,
                                hasPolylineOrPath: !!(polyline || path),
                                contentSnippet: svg ? svg.innerHTML.substring(0, 100) : 'none'
                            };
                        }
                    });

                    // Check Tall Chart
                    const tallEl = document.querySelector('#tallChartSvgContainer svg') || document.getElementById('tallSynchronizedSvg');
                    const tallResult = {
                        hasTallSvg: !!tallEl,
                        hasMealTags: !!(tallEl && (tallEl.innerHTML.includes('🥗') || tallEl.innerHTML.includes('MEAL') || tallEl.innerHTML.includes('SNACK'))),
                        hasCorrelations: !!(tallEl && (tallEl.innerHTML.includes('line') || tallEl.innerHTML.includes('polyline'))),
                        hasMetformin: !!(tallEl && tallEl.innerHTML.includes('METFORMIN'))
                    };

                    // Check Sub-Reports 01-06 values
                    const subReportValues = {
                        tdee: document.getElementById('subValTDEE')?.textContent,
                        activeBurn: document.getElementById('subValActiveBurn')?.textContent,
                        glut4: document.getElementById('subValGlut4')?.textContent,
                        rer: document.getElementById('subValRER')?.textContent,
                        avnn: document.getElementById('subValAVNN')?.textContent,
                        sdnn: document.getElementById('subValSDNN')?.textContent,
                        rmssd: document.getElementById('subValRMSSD')?.textContent,
                        lfhf: document.getElementById('subValLFHF')?.textContent,
                        met: document.getElementById('subValMET')?.textContent,
                        respRpm: document.getElementById('subValRespRPM')?.textContent
                    };

                    return { charts: results, tall: tallResult, subReports: subReportValues };
                })()
                """

                res_charts = await send("Runtime.evaluate", {"expression": chart_audit_script, "returnByValue": True})
                chart_data = res_charts.get("result", {}).get("value", {})
                print("\nLIVE CHART AUDIT RESULTS:")
                if isinstance(chart_data, dict):
                    charts_dict = chart_data.get("charts", {})
                    if isinstance(charts_dict, dict):
                        for cid, cinfo in charts_dict.items():
                            if isinstance(cinfo, dict):
                                print(f"  {cid}: hasSvg={cinfo.get('hasSvg')}, circles={cinfo.get('numCircles')}, texts={cinfo.get('numTexts')}")
                            else:
                                print(f"  {cid}: {cinfo}")
                    else:
                        print("  charts is not dict:", charts_dict)
                else:
                    print("  chart_data is not dict:", chart_data)

                print("\nTALL MULTI-LANE 360 CHART:")
                print(" ", json.dumps(chart_data.get("tall", {}), indent=2))

                print("\nSUB-REPORTS 01-06 LIVE METRIC AUDIT:")
                print(" ", json.dumps(chart_data.get("subReports", {}), indent=2))

                # 2. Test preview.html presentation player
                print("\n2. Checking presentation preview.html player and scroll sync...")
                await send("Page.navigate", {"url": "http://localhost:8899/presentation/preview.html"})
                await asyncio.sleep(2.5)

                preview_audit_script = """
                (() => {
                    const iframe = document.getElementById('slideIframe');
                    const totalSlides = typeof slides !== 'undefined' ? slides.length : 0;
                    const audio = document.getElementById('slideAudio');
                    
                    // Simulate step focus sync on slide 1 at norm = 0.2, 0.5, 0.9
                    let scrollPositions = [];
                    if (typeof syncSlideContent === 'function') {
                        syncSlideContent(0.1);
                        scrollPositions.push({ norm: 0.1, targetLeft: targetScrollLeft });
                        syncSlideContent(0.5);
                        scrollPositions.push({ norm: 0.5, targetLeft: targetScrollLeft });
                        syncSlideContent(0.9);
                        scrollPositions.push({ norm: 0.9, targetLeft: targetScrollLeft });
                    }

                    return {
                        totalSlides: totalSlides,
                        hasIframe: !!iframe,
                        hasAudio: !!audio,
                        scrollPositions: scrollPositions,
                        firstSlideTitle: (slides && slides[0]) ? slides[0].title : null
                    };
                })()
                """

                res_prev = await send("Runtime.evaluate", {"expression": preview_audit_script, "returnByValue": True})
                prev_data = res_prev.get("result", {}).get("value", {})
                print("\nPREVIEW PLAYER AUDIT:")
                print(" ", json.dumps(prev_data, indent=2))

    finally:
        proc.terminate()
        print("\nHeadless Chrome process terminated.")

if __name__ == '__main__':
    asyncio.run(test_preview_and_charts())
