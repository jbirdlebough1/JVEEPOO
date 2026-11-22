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
        "--remote-debugging-port=9228",
        "--disable-gpu",
        "--window-size=1280,1800",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9228/json") as resp:
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
                    return mid

                await send("Page.enable")
                await send("Runtime.enable")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                
                # Listen to incoming messages for 3 seconds
                start = time.time()
                while time.time() - start < 3.0:
                    try:
                        msg = await asyncio.wait_for(ws.receive_json(), timeout=1.0)
                        if msg.get("method") == "Runtime.exceptionThrown":
                            print("\n[!] Exception thrown:", json.dumps(msg["params"]["exceptionDetails"], indent=2))
                    except asyncio.TimeoutError:
                        break
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(main())
