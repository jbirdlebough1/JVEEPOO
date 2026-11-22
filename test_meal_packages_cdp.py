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
    print("[*] Launching headless Chrome for MEAL-01 & MEAL-02 and BOM testing...")
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9231",
        "--disable-gpu",
        "--window-size=1280,2400",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9231/json") as resp:
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
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.5)

                # 1. Switch to Diet Trades tab (Tab 09) and select MEAL-01
                print("\n--- 1. Testing MEAL-01 (Mon-Sat Oatmeal & Berries & Turkey Sausage) ---")
                res_m1 = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('diettrades');
                        onCommonMealPackageSelected('meal01');
                        const status = document.getElementById('badgeGeminiGoalStatus')?.textContent;
                        const netCarbs = document.getElementById('chipNetCarbs')?.textContent;
                        const cal = document.getElementById('chipCalories')?.textContent;
                        const rows = document.getElementById('tbodyMealBOM')?.querySelectorAll('tr').length;
                        return { status, netCarbs, cal, bomRows: rows };
                    })()""",
                    "returnByValue": True
                })
                print("MEAL-01 Evaluation:", res_m1.get("result", {}).get("value"))

                # Capture MEAL-01 Screenshot with BOM
                clip_m1 = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('selCommonMealPackage')?.closest('.setup-section');
                        if (!el) return null;
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: rect.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_m1.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        with open("D:/veepoo/images/lcars_tab09_gemini_meal_builder.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        with open("D:/veepoo/images/lcars_tab09_meal01_bom.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print("[✔] Saved images/lcars_tab09_gemini_meal_builder.png & lcars_tab09_meal01_bom.png")

                # 2. Select MEAL-02 (Sunday Scramble & Toast)
                print("\n--- 2. Testing MEAL-02 (Sunday Eggs, Turkey Sausage & Toast) ---")
                res_m2 = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        onCommonMealPackageSelected('meal02');
                        const status = document.getElementById('badgeGeminiGoalStatus')?.textContent;
                        const netCarbs = document.getElementById('chipNetCarbs')?.textContent;
                        const cal = document.getElementById('chipCalories')?.textContent;
                        const rows = document.getElementById('tbodyMealBOM')?.querySelectorAll('tr').length;
                        return { status, netCarbs, cal, bomRows: rows };
                    })()""",
                    "returnByValue": True
                })
                print("MEAL-02 Evaluation:", res_m2.get("result", {}).get("value"))

                clip_m2 = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('cardMealBOM');
                        if (!el) return null;
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: rect.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_m2.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        with open("D:/veepoo/images/lcars_tab09_meal02_bom_table.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print("[✔] Saved images/lcars_tab09_meal02_bom_table.png")

                # Replicate screenshots
                for name in ["lcars_tab09_gemini_meal_builder.png", "lcars_tab09_meal01_bom.png", "lcars_tab09_meal02_bom_table.png"]:
                    for d in ["D:/veepoo/presentation/images", "D:/veepoo/slides/images", "D:/veepoo/presentation/slides/images"]:
                        shutil.copyfile(f"D:/veepoo/images/{name}", os.path.join(d, name))
                print("[✔] Replicated MEAL-01 & MEAL-02 BOM images across folders")
    finally:
        proc.kill()
        print("[*] Chrome closed.")

if __name__ == "__main__":
    asyncio.run(main())
