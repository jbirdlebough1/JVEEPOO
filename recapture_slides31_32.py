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
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9230",
        "--disable-gpu",
        "--window-size=1280,2200",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9230/json") as resp:
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

                # Switch to reports
                await send("Runtime.evaluate", {"expression": "switchTab('reports')"})
                await asyncio.sleep(1.0)

                # 1. Slide 31: Scrubber HUD
                clip_hud = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('tallHudRibbon');
                        if (!el) return null;
                        const rect = el.getBoundingClientRect();
                        return { x: rect.left - 10, y: rect.top - 50, width: rect.width + 20, height: rect.height + 70, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_hud.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        with open("D:/veepoo/images/lcars_slide31_scrubber_hud.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print("[✔] Re-captured images/lcars_slide31_scrubber_hud.png")

                # 2. Slide 32: Point Card Modal over 8-lane chart (selecting 158 -> 188)
                await send("Runtime.evaluate", {
                    "expression": """(() => {
                        openTimelinePointCard(5);
                    })()"""
                })
                await asyncio.sleep(0.5)

                clip_modal = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const box = document.getElementById('boxSynchronizedTallChart');
                        if (!box) return null;
                        const rect = box.getBoundingClientRect();
                        return { x: rect.left, y: rect.top, width: rect.width, height: 1250, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_modal.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        with open("D:/veepoo/images/lcars_slide32_point_card_modal.png", "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print("[✔] Re-captured images/lcars_slide32_point_card_modal.png")

                # Replicate across folders
                for name in ["lcars_slide31_scrubber_hud.png", "lcars_slide32_point_card_modal.png"]:
                    for d in ["D:/veepoo/presentation/images", "D:/veepoo/slides/images", "D:/veepoo/presentation/slides/images"]:
                        shutil.copyfile(f"D:/veepoo/images/{name}", os.path.join(d, name))
                print("[✔] Replicated slide 31 & 32 images")
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(main())
