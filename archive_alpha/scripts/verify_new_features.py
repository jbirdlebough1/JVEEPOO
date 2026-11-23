import asyncio
import aiohttp
import subprocess
import json
import time
import base64
import os

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def main():
    print("[*] Launching headless Chrome for feature verification...")
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9226",
        "--disable-gpu",
        "--window-size=1280,1800",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9226/json") as resp:
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
                        if r.get("id") == mid: 
                            if "error" in r:
                                print(f"[!] Error in {method}: {r['error']}")
                            return r.get("result", {})

                await send("Page.enable")
                await send("Runtime.enable")

                print("[*] Navigating to http://localhost:8899/index.html?theme=light...")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.5)

                # 1. Check Tab 13: Clinical Goals
                print("\n--- 1. Testing Tab 13: Clinical Goals ---")
                res = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('goals');
                        const p = document.getElementById('pageGoals');
                        const a1c = document.getElementById('goalTargetA1C')?.value;
                        const carb = document.getElementById('goalMaxCarbsMeal')?.value;
                        const steps = document.getElementById('goalStepTarget')?.value;
                        return {
                            active: p?.classList.contains('active-page'),
                            a1c: a1c,
                            maxCarbs: carb,
                            stepTarget: steps
                        };
                    })()""",
                    "returnByValue": True
                })
                print("Goals Page Evaluation:", res.get("result", {}).get("value"))

                # Capture Goals Page Screenshot
                clip_goals = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('pageGoals');
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: Math.min(rect.height, 950), scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_goals.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        with open("D:/veepoo/images/lcars_tab13_clinical_goals.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print("[✔] Captured images/lcars_tab13_clinical_goals.png")

                # 2. Check Tab 09: Diabetic Meal Builder
                print("\n--- 2. Testing Tab 09: Diabetic Meal Builder & Gemini Nutrition Coach ---")
                res_meal = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('diettrades');
                        selectMealBuilderPreset('chipotle');
                        const status = document.getElementById('badgeGeminiGoalStatus')?.textContent;
                        const netCarbs = document.getElementById('chipNetCarbs')?.textContent;
                        const cal = document.getElementById('chipCalories')?.textContent;
                        return { status, netCarbs, cal };
                    })()""",
                    "returnByValue": True
                })
                print("Meal Builder Evaluation:", res_meal.get("result", {}).get("value"))

                # Capture Meal Builder Screenshot
                clip_meal = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('txtMealBuilderInput')?.closest('.setup-section');
                        if (!el) return null;
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: rect.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_meal.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        with open("D:/veepoo/images/lcars_tab09_gemini_meal_builder.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print("[✔] Captured images/lcars_tab09_gemini_meal_builder.png")

                # 3. Check Tab 04: Reports & Goal Compliance Audit & White Chart Backgrounds
                print("\n--- 3. Testing Tab 04: Reports & Goal Compliance Audit ---")
                res_audit = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('reports');
                        runGoalComplianceAudit();
                        const score = document.getElementById('lblAuditCompositePercent')?.textContent;
                        const grade = document.getElementById('badgeAuditGrade')?.textContent;
                        const tallChart = document.getElementById('svgTallMaster');
                        const rects = tallChart ? Array.from(tallChart.querySelectorAll('rect')) : [];
                        const laneFills = rects.slice(0, 8).map(r => r.getAttribute('fill'));
                        return {
                            score,
                            grade,
                            tallChartPresent: !!tallChart,
                            laneFills: laneFills
                        };
                    })()""",
                    "returnByValue": True
                })
                print("Reports & Audit Evaluation:", res_audit.get("result", {}).get("value"))

                # Capture Goal Compliance Audit Box Screenshot
                clip_audit = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('boxGoalComplianceAudit');
                        if (!el) return null;
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: rect.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_audit.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        with open("D:/veepoo/images/lcars_slide28_goal_adherence_audit.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print("[✔] Captured images/lcars_slide28_goal_adherence_audit.png")

                # Capture Tall Chart 8-Lane Stack with pure white lane backgrounds!
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
                        with open("D:/veepoo/images/lcars_slide29_tall_chart_stack.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        with open("D:/veepoo/images/lcars_slide30_stacked_lanes.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print("[✔] Re-captured images/lcars_slide29_tall_chart_stack.png and lcars_slide30_stacked_lanes.png (White Backgrounds)")

    finally:
        proc.kill()
        print("[*] Chrome closed.")

if __name__ == "__main__":
    asyncio.run(main())
