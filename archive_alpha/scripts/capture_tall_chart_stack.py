import asyncio
import aiohttp
import subprocess
import json
import base64
import os
import shutil
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def capture_tall():
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9223",
        "--disable-gpu",
        "--window-size=1280,3500",
        "about:blank"
    ])
    
    time.sleep(1.5)
    
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9223/json") as resp:
                targets = await resp.json()
                ws_url = targets[0]["webSocketDebuggerUrl"]
                
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
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.0)
                
                # Switch to Tab 04 and make sure charts are rendered
                res = await send("Runtime.evaluate", {
                    "expression": """
                        switchTab('reports');
                        const elTop = document.getElementById('boxChartGlucose');
                        const elBot = document.getElementById('boxChartHRV');
                        const rTop = elTop.getBoundingClientRect();
                        const rBot = elBot.getBoundingClientRect();
                        JSON.stringify({
                            x: rTop.x,
                            y: rTop.y + window.scrollY,
                            width: rTop.width,
                            height: (rBot.bottom + window.scrollY) - (rTop.top + window.scrollY)
                        });
                    """
                })
                rect = json.loads(res["result"]["value"])
                print("Measured tall chart stack rect:", rect)
                
                # Capture clip screenshot
                clip = {
                    "x": max(0, rect["x"]),
                    "y": max(0, rect["y"]),
                    "width": rect["width"],
                    "height": rect["height"],
                    "scale": 1.0
                }
                res_shot = await send("Page.captureScreenshot", {
                    "format": "png",
                    "clip": clip,
                    "captureBeyondViewport": True
                })
                img_data = base64.b64decode(res_shot["data"])
                out_path = r"D:\veepoo\images\lcars_tall_chart_scrolled.png"
                with open(out_path, "wb") as f:
                    f.write(img_data)
                    
                print(f"Captured tall chart cropped stack: {len(img_data)} bytes! Height: {rect['height']}px")
                
                # Copy to presentation folders
                for d in [r"D:\veepoo\presentation\images", r"D:\veepoo\slides\images", r"D:\veepoo\presentation\slides\images"]:
                    os.makedirs(d, exist_ok=True)
                    shutil.copy2(out_path, os.path.join(d, "lcars_tall_chart_scrolled.png"))
                    shutil.copy2(out_path, os.path.join(d, "lcars_slide27_stacked_lanes.png"))
                    shutil.copy2(out_path, os.path.join(d, "lcars_slide26_tall_chart_header.png"))
                    
                print("Copied to all slide directories successfully!")
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(capture_tall())
