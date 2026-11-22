import asyncio
import aiohttp
import subprocess
import json
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def main():
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9227",
        "--disable-gpu",
        "--window-size=1280,1800",
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
                        if r.get("id") == mid: return r

                await send("Page.enable")
                await send("Runtime.enable")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.0)
                
                r = await send("Runtime.evaluate", {
                    "expression": "document.title",
                    "returnByValue": True
                })
                print("Title evaluate raw response:", json.dumps(r))

                r2 = await send("Runtime.evaluate", {
                    "expression": "typeof switchTab",
                    "returnByValue": True
                })
                print("switchTab raw response:", json.dumps(r2))

                r3 = await send("Runtime.evaluate", {
                    "expression": "(() => { switchTab('goals'); return document.getElementById('pageGoals') !== null; })()",
                    "returnByValue": True
                })
                print("switchTab('goals') raw response:", json.dumps(r3))
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(main())
