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
    print("[*] Launching headless Chrome for Tab 09 Coach verification...")
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9233",
        "--disable-gpu",
        "--window-size=1280,2200",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9233/json") as resp:
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

                res = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('diettrades');
                        const oldNotifier = document.getElementById('smartGlucoseNotificationCard');
                        const coachBadge = document.querySelector('span.badge')?.textContent || "";
                        const titleEl = document.querySelector('#pageDietTrades .setup-section-title span');
                        const mainTitle = titleEl ? titleEl.textContent.trim() : "";
                        
                        // Test Preset
                        selectMealBuilderPreset('chipotle');
                        const netCarbs = document.getElementById('chipNetCarbs')?.textContent;
                        const statusBadge = document.getElementById('badgeGeminiGoalStatus')?.textContent;

                        return {
                            oldNotifierCardPresent: !!oldNotifier,
                            mainTitle: mainTitle,
                            coachBadgeText: coachBadge,
                            chipotleNetCarbs: netCarbs,
                            statusBadgeText: statusBadge
                        };
                    })()""",
                    "returnByValue": True
                })
                val = res.get("result", {}).get("value")
                print("Tab 09 Audit Result:", json.dumps(val, indent=2))

                # Capture clean screenshot of Tab 09 Meal Builder with Gemini Coach
                clip_coach = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('txtMealBuilderInput')?.closest('.setup-section');
                        if (!el) return null;
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: rect.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_coach.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        p = "D:/veepoo/images/lcars_tab09_gemini_meal_builder.png"
                        with open(p, "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print(f"[✔] Captured updated {p}")
                        
                        dest_dirs = [
                            "D:/veepoo/presentation/images",
                            "D:/veepoo/slides/images",
                            "D:/veepoo/presentation/slides/images"
                        ]
                        for d in dest_dirs:
                            os.makedirs(d, exist_ok=True)
                            shutil.copyfile(p, os.path.join(d, "lcars_tab09_gemini_meal_builder.png"))
                        print("[✔] Replicated lcars_tab09_gemini_meal_builder.png across all slide directories")

    finally:
        proc.kill()
        print("[*] Chrome closed successfully.")

if __name__ == "__main__":
    asyncio.run(main())
