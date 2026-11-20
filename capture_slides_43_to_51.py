import asyncio
import aiohttp
import subprocess
import json
import base64
import os
import shutil
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"
proc = subprocess.Popen([
    chrome_path,
    "--headless=new",
    "--remote-debugging-port=9230",
    "--disable-gpu",
    "--window-size=1440,3600",
    "about:blank"
])
time.sleep(2.0)

async def capture_all():
    try:
        async with aiohttp.ClientSession() as s:
            async with s.get("http://127.0.0.1:9230/json") as r:
                targets = await r.json()
                ws_url = targets[0]["webSocketDebuggerUrl"]
            async with s.ws_connect(ws_url) as ws:
                mid = 1
                async def send(m, p=None):
                    nonlocal mid
                    mid += 1
                    await ws.send_json({"id": mid, "method": m, "params": p or {}})
                    while True:
                        res = await ws.receive_json()
                        if res.get("id") == mid:
                            return res
                await send("Page.enable")
                await send("Runtime.enable")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.5)

                async def shot_clip(rect, filename):
                    clip = {
                        "x": max(0, rect["x"]),
                        "y": max(0, rect["y"]),
                        "width": rect["width"],
                        "height": rect["height"],
                        "scale": 1.0
                    }
                    res = await send("Page.captureScreenshot", {"format": "png", "clip": clip, "captureBeyondViewport": True})
                    data = base64.b64decode(res["result"]["data"])
                    p = os.path.join(r"D:\veepoo\images", filename)
                    with open(p, "wb") as f:
                        f.write(data)
                    print(f"Captured {filename}: {len(data)} bytes ({rect['width']}x{rect['height']})")
                    for d in [r"D:\veepoo\presentation\images", r"D:\veepoo\slides\images", r"D:\veepoo\presentation\slides\images"]:
                        os.makedirs(d, exist_ok=True)
                        shutil.copy2(p, os.path.join(d, filename))

                # 1. Slide 43: Tab 11 Medication Full Tall Page (Component 1 & 2)
                await send("Runtime.evaluate", {"expression": "switchTab('medication');"})
                await asyncio.sleep(0.5)
                r43 = await send("Runtime.evaluate", {"expression": """
                    (() => {
                        const page = document.getElementById('pageMedication');
                        const r = page.getBoundingClientRect();
                        return JSON.stringify({
                            x: Math.round(r.x),
                            y: Math.round(r.y + window.scrollY),
                            width: Math.round(r.width),
                            height: Math.round(r.height)
                        });
                    })()
                """, "returnByValue": True})
                rect43 = json.loads(r43["result"]["result"]["value"])
                await shot_clip(rect43, "lcars_slide43_medication_tall.png")

                # 2. Slide 44: Tab 11 Component 1 Focused (Morning & Evening Regimen Cards)
                r44 = await send("Runtime.evaluate", {"expression": """
                    (() => {
                        const page = document.getElementById('pageMedication');
                        const card = page.querySelectorAll('.diet-form-card')[0];
                        const r = card.getBoundingClientRect();
                        return JSON.stringify({
                            x: Math.round(r.x),
                            y: Math.round(r.y + window.scrollY),
                            width: Math.round(r.width),
                            height: Math.round(r.height)
                        });
                    })()
                """, "returnByValue": True})
                rect44 = json.loads(r44["result"]["result"]["value"])
                await shot_clip(rect44, "lcars_slide44_medication_schedules.png")

                # 3. Slide 45: Tab 11 Component 2 Focused (Titration & Med Change Logger)
                r45 = await send("Runtime.evaluate", {"expression": """
                    (() => {
                        const page = document.getElementById('pageMedication');
                        const card = page.querySelectorAll('.diet-form-card')[1];
                        const r = card.getBoundingClientRect();
                        return JSON.stringify({
                            x: Math.round(r.x),
                            y: Math.round(r.y + window.scrollY),
                            width: Math.round(r.width),
                            height: Math.round(r.height + 150)
                        });
                    })()
                """, "returnByValue": True})
                rect45 = json.loads(r45["result"]["result"]["value"])
                await shot_clip(rect45, "lcars_slide45_titration_screen.png")

                # 4. Slide 46: Tab 12 Full Scan Active Pipeline
                await send("Runtime.evaluate", {"expression": """
                    switchTab('fullscan');
                    if (typeof startFullScanSequence === 'function') startFullScanSequence();
                """})
                await asyncio.sleep(0.8)
                r46 = await send("Runtime.evaluate", {"expression": """
                    (() => {
                        const page = document.getElementById('pageFullScan');
                        const card = page.querySelector('.diet-form-card');
                        const r = card.getBoundingClientRect();
                        return JSON.stringify({
                            x: Math.round(r.x),
                            y: Math.round(r.y + window.scrollY),
                            width: Math.round(r.width),
                            height: Math.round(r.height + 80)
                        });
                    })()
                """, "returnByValue": True})
                rect46 = json.loads(r46["result"]["result"]["value"])
                await shot_clip(rect46, "lcars_slide46_fullscan_active.png")

                # 5. Slide 51: Doctor Review Consultation View (Reports with YTD / Month / Daily and Epic upload)
                await send("Runtime.evaluate", {"expression": """
                    switchTab('reports');
                    const ytd = document.getElementById('boxYtdSummary');
                    if (ytd) ytd.style.display = 'block';
                """})
                await asyncio.sleep(0.5)
                r51 = await send("Runtime.evaluate", {"expression": """
                    (() => {
                        const topEl = document.querySelector('#pageReports .reports-container');
                        const ytd = document.getElementById('boxYtdSummary');
                        const r1 = topEl.getBoundingClientRect();
                        const r2 = ytd.getBoundingClientRect();
                        return JSON.stringify({
                            x: Math.round(r1.x),
                            y: Math.round(r1.y + window.scrollY),
                            width: Math.round(r1.width),
                            height: Math.round((r2.bottom + window.scrollY) - (r1.y + window.scrollY))
                        });
                    })()
                """, "returnByValue": True})
                rect51 = json.loads(r51["result"]["result"]["value"])
                await shot_clip(rect51, "lcars_slide51_doctor_consultation_view.png")

                print("ALL REQUIRED SCREENSHOTS CAPTURED SUCCESSFULLY!")
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(capture_all())
