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
    "--remote-debugging-port=9228",
    "--disable-gpu",
    "--window-size=1440,3600",
    "about:blank"
])
time.sleep(2.0)

async def capture_all():
    try:
        async with aiohttp.ClientSession() as s:
            async with s.get("http://127.0.0.1:9228/json") as r:
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

                await send("Runtime.evaluate", {"expression": "switchTab('reports');"})
                await asyncio.sleep(0.8)

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

                # Slide 27: Habit correlation tall
                r27 = await send("Runtime.evaluate", {"expression": """
                    (() => {
                        const topEl = document.querySelector('#pageReports .reports-container');
                        const botEl = document.getElementById('boxExerciseMetabolicCorrelation');
                        const r1 = topEl.getBoundingClientRect();
                        const r2 = botEl.getBoundingClientRect();
                        return JSON.stringify({
                            x: Math.round(r1.x),
                            y: Math.round(r1.y + window.scrollY),
                            width: Math.round(r1.width),
                            height: Math.round((r2.bottom + window.scrollY) - (r1.y + window.scrollY))
                        });
                    })()
                """, "returnByValue": True})
                rect27 = json.loads(r27["result"]["result"]["value"])
                await shot_clip(rect27, "lcars_slide27_habit_correlation.png")

                # Slide 29: Tall chart stack
                r29 = await send("Runtime.evaluate", {"expression": """
                    (() => {
                        setTallChartTimeframe('daily');
                        const box = document.getElementById('boxSynchronizedTallChart');
                        const r = box.getBoundingClientRect();
                        return JSON.stringify({
                            x: Math.round(r.x),
                            y: Math.round(r.y + window.scrollY),
                            width: Math.round(r.width),
                            height: Math.round(r.height)
                        });
                    })()
                """, "returnByValue": True})
                rect29 = json.loads(r29["result"]["result"]["value"])
                await shot_clip(rect29, "lcars_slide29_tall_chart_stack.png")
                await shot_clip(rect29, "lcars_slide30_stacked_lanes.png")
                await shot_clip(rect29, "lcars_slide26_tall_chart_header.png")
                await shot_clip(rect29, "lcars_slide27_stacked_lanes.png")

                # Slide 31: Scrubber moved to 55%
                await send("Runtime.evaluate", {"expression": """
                    (() => {
                        setTallChartTimeframe('daily');
                        onTallScrubberMoved(55);
                    })()
                """})
                await asyncio.sleep(0.4)
                await shot_clip(rect29, "lcars_slide31_scrubber_hud.png")
                await shot_clip(rect29, "lcars_slide28_scrubber_hud.png")

                # Slide 32: Point card modal
                await send("Runtime.evaluate", {"expression": """
                    (() => {
                        setTallChartTimeframe('daily');
                        openTimelinePointCard(7);
                    })()
                """})
                await asyncio.sleep(0.5)
                await shot_clip(rect29, "lcars_slide32_point_card_modal.png")
                await shot_clip(rect29, "lcars_slide29_point_cards.png")
                await send("Runtime.evaluate", {"expression": "closeTimelinePointCard();"})

                # Slide 33: Yearly timeframe with MED CHANGE milestone
                await send("Runtime.evaluate", {"expression": """
                    (() => {
                        setTallChartTimeframe('yearly');
                        onTallScrubberMoved(80);
                    })()
                """})
                await asyncio.sleep(0.5)
                r33 = await send("Runtime.evaluate", {"expression": """
                    (() => {
                        const box = document.getElementById('boxSynchronizedTallChart');
                        const r = box.getBoundingClientRect();
                        return JSON.stringify({
                            x: Math.round(r.x),
                            y: Math.round(r.y + window.scrollY),
                            width: Math.round(r.width),
                            height: Math.round(r.height)
                        });
                    })()
                """, "returnByValue": True})
                rect33 = json.loads(r33["result"]["result"]["value"])
                await shot_clip(rect33, "lcars_slide33_med_change_milestone.png")
                await shot_clip(rect33, "lcars_slide30_med_milestones.png")

                print("ALL SLIDE CAPTURES COMPLETED SUCCESSFULLY!")
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(capture_all())
