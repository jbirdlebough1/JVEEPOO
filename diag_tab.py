import asyncio
import aiohttp
import json
import subprocess
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def test_live():
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9288",
        "--disable-gpu",
        "--window-size=1600,1100",
        "about:blank"
    ])
    time.sleep(2.0)

    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9288/json") as resp:
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
                        res = await ws.receive_json()
                        if res.get("id") == mid:
                            return res

                await send("Page.enable")
                await send("Runtime.enable")

                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.5)

                r = await send("Runtime.evaluate", {
                    "expression": "document.getElementById('navTabWatchFaces') ? document.getElementById('navTabWatchFaces').outerHTML : 'NOT FOUND'"
                })
                print("raw eval response:", json.dumps(r, indent=2))

    finally:
        proc.terminate()

if __name__ == '__main__':
    asyncio.run(test_live())
