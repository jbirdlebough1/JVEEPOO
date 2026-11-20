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
        "--remote-debugging-port=9226",
        "--disable-gpu",
        "--window-size=1280,2000",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9226/json") as resp:
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
                    "expression": "document.title",
                    "returnByValue": True
                })
                print("document.title res:", res)
                
                res2 = await send("Runtime.evaluate", {
                    "expression": "Boolean(document.getElementById('boxChartGlucose'))",
                    "returnByValue": True
                })
                print("boxChartGlucose exists:", res2)
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(check())
