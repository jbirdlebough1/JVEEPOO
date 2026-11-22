import asyncio
import aiohttp
import subprocess
import json
import base64
import os
import shutil
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

TABS_TO_CAPTURE = [
    {"tab": "fullscan", "file": "lcars_tab01_fullscan.png", "title": "Tab 01 Full Scan"},
    {"tab": "diettrades", "file": "lcars_tab02_meal_planner.png", "title": "Tab 02 Meal Planner 06-ADA"},
    {"tab": "cardio", "file": "lcars_tab03_cardio.png", "title": "Tab 03 Cardio & Vitals"},
    {"tab": "reports", "file": "lcars_tab04_reports.png", "title": "Tab 04 Clinical Reports"},
    {"tab": "export", "file": "lcars_tab05_export.png", "title": "Tab 05 Export & EHR"},
    {"tab": "learning", "file": "lcars_tab06_learning.png", "title": "Tab 06 Clinical Learning"},
    {"tab": "help", "file": "lcars_tab07_help.png", "title": "Tab 07 Help & FAQ"},
    {"tab": "medication", "file": "lcars_tab08_medication.png", "title": "Tab 08 Medication Regimen"},
    {"tab": "goals", "file": "lcars_tab09_goals.png", "title": "Tab 09 Clinical Goals"},
    {"tab": "setup", "file": "lcars_tab10_config.png", "title": "Tab 10 Configuration"},
    {"tab": "operations", "file": "lcars_tab11_operations.png", "title": "Tab 11 Operations & Hardware"},
    {"tab": "log", "file": "lcars_tab12_datalog.png", "title": "Tab 12 Data Log & Packets"},
    {"tab": "watchfaces", "file": "lcars_tab13_watchfaces.png", "title": "Tab 13 Watch Faces & OTA"}
]

async def capture_all():
    print("Launching headless Chrome for fresh presentation screenshot captures...")
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9244",
        "--disable-gpu",
        "--window-size=1600,1100",
        "about:blank"
    ])
    time.sleep(2.0)

    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9244/json") as resp:
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

                print("Navigating to http://localhost:8899/index.html?theme=light...")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(3.0)

                # Ensure telemetry poll is run and full scan hydrated
                await send("Runtime.evaluate", {
                    "expression": """
                        if (typeof pollLiveTelemetryChanges === 'function') pollLiveTelemetryChanges(true);
                        if (typeof initFullScanView === 'function') initFullScanView();
                    """
                })
                await asyncio.sleep(1.5)

                out_dirs = [
                    r"D:\veepoo\presentation\images",
                    r"D:\veepoo\slides\images",
                    r"D:\veepoo\images"
                ]
                for od in out_dirs:
                    os.makedirs(od, exist_ok=True)

                for item in TABS_TO_CAPTURE:
                    tab_id = item["tab"]
                    filename = item["file"]
                    print(f"Switching to {tab_id} ({item['title']})...")

                    await send("Runtime.evaluate", {
                        "expression": f"""
                            switchTab('{tab_id}');
                            window.scrollTo(0, 0);
                        """
                    })
                    await asyncio.sleep(1.0)

                    # For reports tab, ensure renderReportsView() is called and curves render
                    if tab_id == 'reports':
                        await send("Runtime.evaluate", {
                            "expression": """
                                if (typeof renderReportsView === 'function') renderReportsView();
                                if (typeof renderTallSynchronizedChart === 'function') renderTallSynchronizedChart('daily');
                                if (typeof renderLorenzScatterPlot === 'function') renderLorenzScatterPlot();
                                if (typeof renderStepGlucoseCorrelationPlot === 'function') renderStepGlucoseCorrelationPlot();
                            """
                        })
                        await asyncio.sleep(1.5)

                    # For diettrades, ensure 06-ADA meal planner is rendered
                    if tab_id == 'diettrades':
                        await send("Runtime.evaluate", {
                            "expression": """
                                const builder = document.getElementById('txtMealBuilderInput');
                                if (builder && !builder.value) builder.value = "SmartSweets Sour Blast Buddies (0% Sorbitol / Maltitol, 3g Net Carbs)";
                            """
                        })
                        await asyncio.sleep(0.5)

                    res_ss = await send("Page.captureScreenshot", {"format": "png"})
                    img_bytes = base64.b64decode(res_ss["data"])

                    for od in out_dirs:
                        fp = os.path.join(od, filename)
                        with open(fp, "wb") as f:
                            f.write(img_bytes)

                    print(f"  ✓ Captured {filename} ({len(img_bytes):,} bytes)")

                # Also capture specific component crops for key presentation slides
                # 1. 360 Tall Multi-Lane Chart on Reports
                print("Capturing 360 Multi-Lane Scrubber crop...")
                await send("Runtime.evaluate", {
                    "expression": """
                        switchTab('reports');
                        const el = document.getElementById('boxTallScrubberTimeline');
                        if (el) el.scrollIntoView({ behavior: 'instant', block: 'start' });
                    """
                })
                await asyncio.sleep(1.0)
                res_tall = await send("Page.captureScreenshot", {"format": "png"})
                tall_bytes = base64.b64decode(res_tall["data"])
                for od in out_dirs:
                    with open(os.path.join(od, "lcars_chart_tall.png"), "wb") as f:
                        f.write(tall_bytes)
                print("  ✓ Captured lcars_chart_tall.png")

                # 2. Bivariate Regression Plot on Reports
                print("Capturing Bivariate Correlation crop...")
                await send("Runtime.evaluate", {
                    "expression": """
                        switchTab('reports');
                        const el = document.getElementById('boxExerciseMetabolicCorrelation');
                        if (el) el.scrollIntoView({ behavior: 'instant', block: 'center' });
                    """
                })
                await asyncio.sleep(1.0)
                res_biv = await send("Page.captureScreenshot", {"format": "png"})
                biv_bytes = base64.b64decode(res_biv["data"])
                for od in out_dirs:
                    with open(os.path.join(od, "lcars_slide27_habit_correlation.png"), "wb") as f:
                        f.write(biv_bytes)
                print("  ✓ Captured lcars_slide27_habit_correlation.png")

                # 3. Restored Medication Screen Schedule
                print("Capturing Medication Schedule crop...")
                await send("Runtime.evaluate", {
                    "expression": """
                        switchTab('medication');
                        window.scrollTo(0, 0);
                    """
                })
                await asyncio.sleep(1.0)
                res_med = await send("Page.captureScreenshot", {"format": "png"})
                med_bytes = base64.b64decode(res_med["data"])
                for od in out_dirs:
                    with open(os.path.join(od, "lcars_slide37_medication.png"), "wb") as f:
                        f.write(med_bytes)
                print("  ✓ Captured lcars_slide37_medication.png")

                # 4. Restored Clinical Goals Screen
                print("Capturing Clinical Goals crop...")
                await send("Runtime.evaluate", {
                    "expression": """
                        switchTab('goals');
                        window.scrollTo(0, 0);
                    """
                })
                await asyncio.sleep(1.0)
                res_goals = await send("Page.captureScreenshot", {"format": "png"})
                goals_bytes = base64.b64decode(res_goals["data"])
                for od in out_dirs:
                    with open(os.path.join(od, "lcars_slide38_clinical_goals.png"), "wb") as f:
                        f.write(goals_bytes)
                print("  ✓ Captured lcars_slide38_clinical_goals.png")

    finally:
        proc.terminate()
        print("Headless Chrome process terminated.")

if __name__ == '__main__':
    asyncio.run(capture_all())
