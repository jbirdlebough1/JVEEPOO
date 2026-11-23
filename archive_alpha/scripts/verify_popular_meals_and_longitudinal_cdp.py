import asyncio
import aiohttp
import subprocess
import json
import time
import base64
import os
import shutil
import tempfile

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def main():
    print("[*] Launching headless Chrome for Popular Meals & Doctor-Patient Longitudinal Telemetry...", flush=True)
    temp_dir = tempfile.mkdtemp()
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9235",
        f"--user-data-dir={temp_dir}",
        "--disable-gpu",
        "--window-size=1400,2400",
        "about:blank"
    ])
    try:
        ws_url = None
        async with aiohttp.ClientSession() as session:
            for _ in range(25):
                await asyncio.sleep(0.4)
                try:
                    async with session.get("http://127.0.0.1:9235/json") as resp:
                        targets = await resp.json()
                        pages = [t for t in targets if t.get("type") == "page"]
                        if pages:
                            ws_url = pages[0]["webSocketDebuggerUrl"]
                            print(f"[✔] Connected to Chrome page: {ws_url}", flush=True)
                            break
                except Exception:
                    pass

            if not ws_url:
                raise RuntimeError("Could not connect to Chrome CDP endpoint")

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
                                print(f"[!] CDP Error in {method}: {r['error']}", flush=True)
                            return r.get("result", {})

                await send("Page.enable")
                await send("Runtime.enable")

                # Navigate to Desktop LCARS App
                print("\n--- Navigating to http://localhost:8899/index.html?theme=light ---")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.5)

                # =============================================================
                # TEST 1: VERIFY TAB 09 POPULAR MEALS & ZERO MCDONALDS
                # =============================================================
                print("\n--- TEST 1: Tab 09 Popular Meals & McDonald's Scrub ---")
                res_init = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('diettrades');
                        const keys = Object.keys(POPULAR_MEALS);
                        const hasMcDonalds = keys.some(k => k.toLowerCase().includes('mcdonald') || POPULAR_MEALS[k].title.toLowerCase().includes('mcdonald'));
                        const selOptions = Array.from(document.getElementById('selCommonMealPackage')?.options || []).map(o => o.text);
                        const selHasMcDonalds = selOptions.some(t => t.toLowerCase().includes('mcdonald'));

                        return {
                            popularMealsCount: keys.length,
                            mealKeys: keys,
                            hasMcDonaldsInLibrary: hasMcDonalds,
                            hasMcDonaldsInSelect: selHasMcDonalds,
                            hasDicksCombo: keys.includes('dicks_combo')
                        };
                    })()""",
                    "returnByValue": True
                })
                print("Init Audit:", json.dumps(res_init.get("result", {}).get("value"), indent=2))

                # =============================================================
                # TEST 2: GEMINI ASSESS USER REQUEST "Dick's DriveIn 2 cheeze burgers, 3 tatar, fry and large diet"
                # =============================================================
                print("\n--- TEST 2: Gemini Pre-Choice Assessment of Dick's Feast ---")
                res_dicks = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const input = document.getElementById('txtMealBuilderInput');
                        input.value = "Dick's DriveIn 2 cheeze burgers, 3 tatar, fry and large diet";
                        analyzeMealWithGemini();

                        const badge = document.getElementById('badgeGeminiGoalStatus')?.textContent;
                        const netCarbs = document.getElementById('chipNetCarbs')?.textContent;
                        const cal = document.getElementById('chipCalories')?.textContent;
                        const pro = document.getElementById('chipProtein')?.textContent;
                        const fat = document.getElementById('chipFat')?.textContent;
                        const title = document.getElementById('lblGeminiMealTitle')?.textContent;

                        return {
                            title: title,
                            badge: badge,
                            netCarbs: netCarbs,
                            calories: cal,
                            protein: pro,
                            fat: fat,
                            currentAnalyzedNetCarbs: currentAnalyzedMealData?.netCarbs,
                            currentAnalyzedMeetsGoal: currentAnalyzedMealData?.meetsGoal
                        };
                    })()""",
                    "returnByValue": True
                })
                print("Dick's Assessment Audit:", json.dumps(res_dicks.get("result", {}).get("value"), indent=2))

                # =============================================================
                # TEST 3: CHOICE 1 - ADD TO POPULAR MEALS & TOGGLE MANAGER
                # =============================================================
                print("\n--- TEST 3: Choice 1 - Add to Popular Meals & Manager View ---")
                res_mgr = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        // Toggle manager open
                        togglePopularMealsManager();
                        const cardVisible = document.getElementById('cardPopularMealsManager')?.style.display !== 'none';
                        const tableRows = document.getElementById('popularMealsTableBody')?.rows.length;

                        // Test editing a popular meal
                        openEditPopularMeal('dicks_combo');
                        const modalVisible = document.getElementById('modalEditPopularMeal')?.style.display === 'flex';
                        const modalTitle = document.getElementById('editMealTitle')?.value;

                        // Close modal for screenshot
                        closeEditPopularMealModal();

                        return {
                            managerCardVisible: cardVisible,
                            tableRowsCount: tableRows,
                            editModalOpened: modalVisible,
                            editModalTitlePreloaded: modalTitle
                        };
                    })()""",
                    "returnByValue": True
                })
                print("Manager & Edit Audit:", json.dumps(res_mgr.get("result", {}).get("value"), indent=2))

                # Capture Popular Meals Manager Screenshot
                clip_mgr = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('cardPopularMealsManager');
                        if (!el) return null;
                        const r = el.getBoundingClientRect();
                        return { x: r.left, y: r.top, width: r.width, height: r.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip_m = clip_mgr.get("result", {}).get("value")
                if clip_m and clip_m.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip_m})
                    if ss.get("data"):
                        p = "D:/veepoo/images/lcars_popular_meals_manager.png"
                        with open(p, "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print(f"[✔] Captured {p}")

                # =============================================================
                # TEST 4: CHOICE 2 - SUBMIT ACTUAL MEAL & TRACK LONGITUDINAL METRICS
                # =============================================================
                print("\n--- TEST 4: Choice 2 - Submit Actual Meal & Doctor-Patient Longitudinal Explorer ---")
                res_long = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        // Switch through timeframes: daily, weekly, monthly, quarterly (eA1C), yearly
                        setLongitudinalTimeframe('quarterly');
                        const qtrText = document.getElementById('longitudinalTelemetryViewport')?.textContent;
                        const notesText = document.getElementById('longitudinalDoctorNotesCard')?.textContent;

                        setLongitudinalTimeframe('daily');
                        const daySvgFound = !!document.querySelector('#longitudinalTelemetryViewport svg');

                        return {
                            hasQuarterlyReport: qtrText.includes('90-DAY QUARTERLY eA1C') && qtrText.includes('5.6%'),
                            hasDoctorMentoringNotes: notesText.includes('DOCTOR MENTORING NOTE'),
                            hasDailyPostprandialSvg: daySvgFound
                        };
                    })()""",
                    "returnByValue": True
                })
                print("Longitudinal Explorer Audit:", json.dumps(res_long.get("result", {}).get("value"), indent=2))

                # Capture Doctor-Patient Longitudinal Telemetry Screenshot
                clip_long = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        setLongitudinalTimeframe('daily');
                        const el = document.getElementById('cardDoctorPatientLongitudinal');
                        if (!el) return null;
                        const r = el.getBoundingClientRect();
                        return { x: r.left, y: r.top, width: r.width, height: r.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip_l = clip_long.get("result", {}).get("value")
                if clip_l and clip_l.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip_l})
                    if ss.get("data"):
                        p = "D:/veepoo/images/lcars_doctor_patient_longitudinal_explorer.png"
                        with open(p, "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print(f"[✔] Captured {p}")

                # Also capture Quarterly eA1C View
                clip_qtr = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        setLongitudinalTimeframe('quarterly');
                        const el = document.getElementById('cardDoctorPatientLongitudinal');
                        if (!el) return null;
                        const r = el.getBoundingClientRect();
                        return { x: r.left, y: r.top, width: r.width, height: r.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip_q = clip_qtr.get("result", {}).get("value")
                if clip_q and clip_q.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip_q})
                    if ss.get("data"):
                        p = "D:/veepoo/images/lcars_doctor_patient_quarterly_ea1c.png"
                        with open(p, "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print(f"[✔] Captured {p}")

                # Replicate new images across presentation directories
                dest_dirs = [
                    "D:/veepoo/presentation/images",
                    "D:/veepoo/slides/images",
                    "D:/veepoo/presentation/slides/images"
                ]
                for d in dest_dirs:
                    os.makedirs(d, exist_ok=True)
                    for im in [
                        "lcars_popular_meals_manager.png",
                        "lcars_doctor_patient_longitudinal_explorer.png",
                        "lcars_doctor_patient_quarterly_ea1c.png"
                    ]:
                        src = f"D:/veepoo/images/{im}"
                        if os.path.exists(src):
                            shutil.copyfile(src, os.path.join(d, im))
                print("[✔] Replicated screenshots across all presentation directories")

    finally:
        proc.kill()
        try:
            shutil.rmtree(temp_dir, ignore_errors=True)
        except Exception:
            pass
        print("[*] Chrome closed successfully.", flush=True)

if __name__ == "__main__":
    asyncio.run(main())
