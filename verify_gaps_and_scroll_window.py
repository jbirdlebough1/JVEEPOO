import asyncio
import aiohttp
import subprocess
import json
import time
import base64
import os
import shutil

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def main():
    print("[*] Launching headless Chrome for Gaps & Scroll Window verification...")
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9232",
        "--disable-gpu",
        "--window-size=1280,2400",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9232/json") as resp:
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
                        r = await ws.receive_json()
                        if r.get("id") == mid: return r.get("result", {})

                await send("Page.enable")
                await send("Runtime.enable")

                print("[*] Navigating to http://localhost:8899/index.html?theme=light...")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.5)

                # =============================================================
                # TEST 1: RAW DATA SCROLL WINDOW & TIMEFRAMES
                # =============================================================
                print("\n--- Test 1: Raw Data Scroll Window & Timeframes ---")
                res_raw = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('reports');
                        const container = document.getElementById('rawTelemetryScrollContainer');
                        const cStyle = container ? window.getComputedStyle(container) : null;
                        
                        // Daily test
                        setRawDataTimeframe('daily');
                        const tbody = document.getElementById('telemetryTableBody');
                        const dailyCount = tbody ? tbody.querySelectorAll('tr').length : 0;
                        const dailyText = tbody ? tbody.textContent : "";
                        const hasChargingGap = dailyText.includes('WATCH CHARGING') && dailyText.includes('0 (Charging)');

                        // Weekly test
                        setRawDataTimeframe('weekly');
                        const weeklyCount = tbody ? tbody.querySelectorAll('tr').length : 0;

                        // Monthly test (30 days)
                        setRawDataTimeframe('monthly');
                        const monthlyCount = tbody ? tbody.querySelectorAll('tr').length : 0;
                        const monthlyBoxHeight = container ? container.getBoundingClientRect().height : 0;

                        // Yearly test
                        setRawDataTimeframe('yearly');
                        const yearlyCount = tbody ? tbody.querySelectorAll('tr').length : 0;

                        // Return to daily for presentation
                        setRawDataTimeframe('daily');

                        return {
                            scrollContainerFound: !!container,
                            maxHeight: cStyle ? cStyle.maxHeight : null,
                            overflowY: cStyle ? cStyle.overflowY : null,
                            dailyRowCount: dailyCount,
                            hasChargingGap: hasChargingGap,
                            weeklyRowCount: weeklyCount,
                            monthlyRowCount: monthlyCount,
                            monthlyBoxHeight: Math.round(monthlyBoxHeight),
                            yearlyRowCount: yearlyCount
                        };
                    })()""",
                    "returnByValue": True
                })
                print("Raw Data Scroll Window Audit:", json.dumps(res_raw.get("result", {}).get("value"), indent=2))

                # Capture screenshot of the Raw Data Bounded Scroll Window
                clip_raw = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('boxRawTelemetryLog');
                        if (!el) return null;
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: rect.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_raw.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        p = "D:/veepoo/images/lcars_slide28_raw_scroll_window.png"
                        with open(p, "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print(f"[✔] Captured {p}")

                # =============================================================
                # TEST 2: 8-LANE MASTER CHART CHARGING GAP & DISCONTINUOUS PATHS
                # =============================================================
                print("\n--- Test 2: 8-Lane Master Chart Charging Gap ---")
                res_tall = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        setTallChartTimeframe('daily');
                        const svg = document.getElementById('svgTallMaster');
                        const svgText = svg ? svg.textContent : "";
                        const hasGapBanner = svgText.includes("WATCH CHARGING (0 DATA)");
                        
                        // Test scrubber over gap (slot index 6 of 14 is 11:00 AM ~ 46%)
                        onTallScrubberMoved(46);
                        const badge = document.getElementById('tallScrubberTimeBadge')?.textContent;
                        const hudG = document.getElementById('hudGlucose')?.textContent;
                        const hudSteps = document.getElementById('hudExercise')?.textContent;

                        return {
                            hasGapBanner,
                            scrubberBadge: badge,
                            hudGlucose: hudG,
                            hudSteps: hudSteps
                        };
                    })()""",
                    "returnByValue": True
                })
                print("8-Lane Chart Gap Audit:", json.dumps(res_tall.get("result", {}).get("value"), indent=2))

                # Capture screenshot of 8-lane chart showing charging gap
                clip_tall = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('boxSynchronizedTallChart');
                        if (!el) return null;
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: rect.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_tall.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        for out_name in ["lcars_slide29_tall_chart_stack.png", "lcars_slide30_stacked_lanes.png"]:
                            p = f"D:/veepoo/images/{out_name}"
                            with open(p, "wb") as f:
                                f.write(base64.b64decode(ss["data"]))
                        print(f"[✔] Captured updated lcars_slide29_tall_chart_stack.png with charging gaps")

                # =============================================================
                # TEST 3: BAY 4 ACTIVITY BINS CHARGING WINDOW
                # =============================================================
                print("\n--- Test 3: Bay 4 Activity Bins Charging Window ---")
                res_bay4 = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('cardio');
                        const g = document.getElementById('hband48BarsGroup');
                        const text = g ? g.innerHTML : "";
                        const hasChargingLightning = text.includes("⚡");
                        const totalSteps = document.getElementById('lblActiveDailySteps')?.textContent;
                        return {
                            hasChargingSlotSymbol: hasChargingLightning,
                            totalDailyStepsText: totalSteps
                        };
                    })()""",
                    "returnByValue": True
                })
                print("Bay 4 Activity Bins Audit:", json.dumps(res_bay4.get("result", {}).get("value"), indent=2))

                # Replicate updated images across all presentation folders
                img_names = [
                    "lcars_slide28_raw_scroll_window.png",
                    "lcars_slide29_tall_chart_stack.png",
                    "lcars_slide30_stacked_lanes.png"
                ]
                dest_dirs = [
                    "D:/veepoo/presentation/images",
                    "D:/veepoo/slides/images",
                    "D:/veepoo/presentation/slides/images"
                ]
                for d in dest_dirs:
                    os.makedirs(d, exist_ok=True)
                    for im in img_names:
                        src = f"D:/veepoo/images/{im}"
                        if os.path.exists(src):
                            shutil.copyfile(src, os.path.join(d, im))
                print("[✔] Replicated all screenshots across presentation folders")

    finally:
        proc.kill()
        print("[*] Chrome closed successfully.")

if __name__ == "__main__":
    asyncio.run(main())
