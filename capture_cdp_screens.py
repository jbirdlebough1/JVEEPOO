import asyncio
import aiohttp
import subprocess
import json
import base64
import os
import shutil
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def capture_cdp():
    # 1. Start Chrome with remote debugging
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9222",
        "--disable-gpu",
        "--window-size=1280,1024",
        "about:blank"
    ])
    
    time.sleep(1.5)
    
    try:
        async with aiohttp.ClientSession() as session:
            # Get pages
            async with session.get("http://127.0.0.1:9222/json") as resp:
                targets = await resp.json()
                ws_url = targets[0]["webSocketDebuggerUrl"]
                print("Connected to Chrome CDP WebSocket:", ws_url)
                
            async with session.ws_connect(ws_url) as ws:
                msg_id = 1
                
                async def send(method, params=None):
                    nonlocal msg_id
                    mid = msg_id
                    msg_id += 1
                    payload = {"id": mid, "method": method}
                    if params:
                        payload["params"] = params
                    await ws.send_json(payload)
                    while True:
                        res = await ws.receive_json()
                        if res.get("id") == mid:
                            return res.get("result", {})
                
                await send("Page.enable")
                await send("Runtime.enable")
                
                # Navigate to index.html
                print("Navigating to LCARS Console...")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.0)
                
                # --- CAPTURE 1: Slide 27 Habit Correlation ---
                print("Switching to Tab 04 and scrolling to boxExerciseMetabolicCorrelation...")
                await send("Runtime.evaluate", {
                    "expression": """
                        switchTab('reports');
                        const el = document.getElementById('boxExerciseMetabolicCorrelation');
                        if (el) el.scrollIntoView({ behavior: 'instant', block: 'center' });
                    """
                })
                await asyncio.sleep(1.0)
                
                res = await send("Page.captureScreenshot", {"format": "png"})
                img_data = base64.b64decode(res["data"])
                out_path27 = r"D:\veepoo\images\lcars_slide27_habit_correlation.png"
                with open(out_path27, "wb") as f:
                    f.write(img_data)
                print(f"Captured Slide 27 screenshot: {len(img_data)} bytes")
                
                # --- CAPTURE 2: Slide 28 Epic Upload Button & Modal ---
                print("Opening Epic Upload Modal on Tab 04...")
                await send("Runtime.evaluate", {
                    "expression": """
                        switchTab('reports');
                        window.scrollTo(0, 0);
                        if (typeof openEpicUploadModal === 'function') openEpicUploadModal();
                    """
                })
                await asyncio.sleep(1.0)
                
                res = await send("Page.captureScreenshot", {"format": "png"})
                img_data = base64.b64decode(res["data"])
                out_path28 = r"D:\veepoo\images\lcars_slide28_epic_gateway.png"
                with open(out_path28, "wb") as f:
                    f.write(img_data)
                print(f"Captured Slide 28 screenshot: {len(img_data)} bytes")
                
                # Copy to all presentation directories
                target_dirs = [
                    r"D:\veepoo\presentation\images",
                    r"D:\veepoo\slides\images",
                    r"D:\veepoo\presentation\slides\images"
                ]
                for td in target_dirs:
                    os.makedirs(td, exist_ok=True)
                    shutil.copy2(out_path27, os.path.join(td, "lcars_slide27_habit_correlation.png"))
                    shutil.copy2(out_path28, os.path.join(td, "lcars_slide28_epic_gateway.png"))
                    
                print("All screenshots replicated to all subdirectories successfully!")
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(capture_cdp())
