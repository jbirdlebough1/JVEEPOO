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
        "--remote-debugging-port=9230",
        "--disable-gpu",
        "--window-size=1280,1000",
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
                    return mid

                async def recv_until(mid):
                    while True:
                        r = await ws.receive_json()
                        if r.get("id") == mid:
                            return r

                m1 = await send("Page.enable")
                await recv_until(m1)
                m2 = await send("Runtime.enable")
                await recv_until(m2)
                
                m = await send("Page.navigate", {"url": "http://localhost:8899/"})
                await recv_until(m)
                await asyncio.sleep(2.0)
                
                # Check with applyTheme('light')
                eval_script = """
                (() => {
                    applyTheme('light');
                    const pfs = document.getElementById('pageFullScan');
                    const nodes = pfs ? Array.from(pfs.querySelectorAll('*')).slice(0, 30) : [];
                    return {
                        bodyClasses: document.body.className,
                        bodyBg: window.getComputedStyle(document.body).backgroundColor,
                        pfsDisplay: window.getComputedStyle(pfs).display,
                        pfsHeight: pfs.offsetHeight,
                        pfsWidth: pfs.offsetWidth,
                        elements: nodes.map(n => ({
                            tag: n.tagName,
                            id: n.id,
                            classes: n.className,
                            color: window.getComputedStyle(n).color,
                            bg: window.getComputedStyle(n).backgroundColor,
                            height: n.offsetHeight,
                            width: n.offsetWidth
                        }))
                    };
                })()
                """
                m_eval = await send("Runtime.evaluate", {"expression": eval_script, "returnByValue": True})
                res = await recv_until(m_eval)
                print(json.dumps(res.get("result", {}).get("result", {}).get("value"), indent=2))

                # Screenshot
                m_ss = await send("Page.captureScreenshot", {"format": "png"})
                res_ss = await recv_until(m_ss)
                import base64
                png_data = base64.b64decode(res_ss["result"]["data"])
                with open("D:/veepoo/screenshot_light_mode.png", "wb") as f_img:
                    f_img.write(png_data)
                print("Saved screenshot_light_mode.png, size:", len(png_data))
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(main())
