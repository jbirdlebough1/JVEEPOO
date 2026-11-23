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
    print("[*] Launching headless Chrome for full feature verification & screenshot capture...")
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9229",
        "--disable-gpu",
        "--window-size=1280,2200",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9229/json") as resp:
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
                # TEST 1: TAB 13 CLINICAL GOALS
                # =============================================================
                print("\n--- TEST 1: Tab 13 Clinical Goals ---")
                res_goals = await send("Runtime.evaluate", {
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
                print("Goals Page Evaluation:", res_goals.get("result", {}).get("value"))

                clip_goals = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('pageGoals');
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: Math.min(rect.height, 920), scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_goals.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        with open("D:/veepoo/images/lcars_tab13_clinical_goals.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print("[✔] Saved images/lcars_tab13_clinical_goals.png")

                # =============================================================
                # TEST 2: TAB 09 DIABETIC MEAL BUILDER & GEMINI COACH
                # =============================================================
                print("\n--- TEST 2: Tab 09 Diabetic Meal Builder ---")
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
                        print("[✔] Saved images/lcars_tab09_gemini_meal_builder.png")

                # =============================================================
                # TEST 3: TAB 04 REPORTS & ON-DEMAND GOAL COMPLIANCE AUDIT
                # =============================================================
                print("\n--- TEST 3: Tab 04 Reports & Goal Compliance Audit ---")
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
                        print("[✔] Saved images/lcars_slide28_goal_adherence_audit.png")

                # =============================================================
                # TEST 4: TALL CHART 8-LANE STACK WITH WHITE BACKGROUNDS
                # =============================================================
                print("\n--- TEST 4: Tall Chart 8-Lane Stack ---")
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
                            with open(f"D:/veepoo/images/{out_name}", "wb") as f:
                                f.write(base64.b64decode(ss["data"]))
                        print("[✔] Saved images/lcars_slide29_tall_chart_stack.png and lcars_slide30_stacked_lanes.png (White Backgrounds)")

                # Replicate new images across all presentation image folders
                img_names = [
                    "lcars_tab13_clinical_goals.png",
                    "lcars_tab09_gemini_meal_builder.png",
                    "lcars_slide28_goal_adherence_audit.png",
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
