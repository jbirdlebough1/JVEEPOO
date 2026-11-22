import asyncio
import aiohttp
import base64
import json
import subprocess
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def main():
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9235",
        "--disable-gpu",
        "--window-size=1600,1000",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9235/json") as resp:
                tabs = await resp.json()
                ws_url = tabs[0]["webSocketDebuggerUrl"]

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

                m_nav = await send("Page.navigate", {"url": "http://localhost:8899/"})
                await recv_until(m_nav)
                await asyncio.sleep(2.5)

                # Evaluate state of pageFullScan
                eval_script = """
                (() => {
                    const pfs = document.getElementById('pageFullScan');
                    return {
                        pageFullScan_display: pfs ? window.getComputedStyle(pfs).display : 'missing',
                        pageFullScan_offsetHeight: pfs ? pfs.offsetHeight : 0,
                        workspace_offsetHeight: document.querySelector('.lcars-workspace') ? document.querySelector('.lcars-workspace').offsetHeight : 0,
                        active_pages: Array.from(document.querySelectorAll('.tab-page.active-page')).map(e => e.id),
                        active_tabs: Array.from(document.querySelectorAll('.lcars-sidebar .lcars-pill.active')).map(e => e.textContent.trim()),
                        has02Vitals: Array.from(document.querySelectorAll('.lcars-sidebar .lcars-pill')).some(e => e.textContent.includes('02-VITALS')),
                        sidebar_pills: Array.from(document.querySelectorAll('.lcars-sidebar .lcars-pill .tab-label')).map(e => e.textContent.trim())
                    };
                })()
                """
                m_eval = await send("Runtime.evaluate", {"expression": eval_script, "returnByValue": True})
                res_eval = await recv_until(m_eval)
                val = res_eval.get("result", {}).get("result", {}).get("value")
                print("EVAL RESULT:\n", json.dumps(val, indent=2))

                # Capture screenshot
                m_ss = await send("Page.captureScreenshot", {"format": "png"})
                res_ss = await recv_until(m_ss)
                png_b64 = res_ss["result"]["data"]
                with open("D:/veepoo/current_live_render.png", "wb") as f:
                    f.write(base64.b64decode(png_b64))
                print("Captured D:/veepoo/current_live_render.png")
    finally:
        proc.terminate()

if __name__ == '__main__':
    asyncio.run(main())
