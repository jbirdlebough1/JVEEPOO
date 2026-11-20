import asyncio
import aiohttp
import subprocess
import json
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def check():
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9227",
        "--disable-gpu",
        "--window-size=1280,3000",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9227/json") as resp:
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
                
                while True:
                    event = await ws.receive_json()
                    if event.get("method") == "Page.loadEventFired":
                        break
                        
                await asyncio.sleep(1.0)
                res = await send("Runtime.evaluate", {
                    "expression": """
                        try {
                            switchTab('reports');
                            const topEl = document.getElementById('boxChartGlucose');
                            const botEl = document.getElementById('boxChartHRV');
                            const rTop = topEl.getBoundingClientRect();
                            const rBot = botEl.getBoundingClientRect();
                            JSON.stringify({
                                x: Math.round(rTop.x),
                                y: Math.round(rTop.top + window.scrollY),
                                width: Math.round(rTop.width),
                                height: Math.round((rBot.bottom + window.scrollY) - (rTop.top + window.scrollY))
                            });
                        } catch (err) {
                            'ERROR: ' + err.message + ' ' + err.stack;
                        }
                    """,
                    "returnByValue": True
                })
                print("Evaluate result:", res)
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(check())
