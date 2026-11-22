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
        "--remote-debugging-port=9229",
        "--disable-gpu",
        "--window-size=1280,900",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9229/json") as resp:
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
                
                # Check BOTH without theme param and with theme=light
                for url in ["http://localhost:8899/", "http://localhost:8899/?theme=light"]:
                    print("Testing URL:", url)
                    m = await send("Page.navigate", {"url": url})
                    await recv_until(m)
                    await asyncio.sleep(2.0)
                    
                    eval_css = """
                    (() => {
                        const body = document.body;
                        const pfs = document.getElementById('pageFullScan');
                        const card = pfs ? pfs.querySelector('.diet-form-card') : null;
                        const title = pfs ? pfs.querySelector('.setup-section-title span') : null;
                        const btn = pfs ? pfs.querySelector('#btnStartFullScan') : null;
                        
                        return {
                            bodyBg: window.getComputedStyle(body).backgroundColor,
                            bodyColor: window.getComputedStyle(body).color,
                            pfsDisplay: pfs ? window.getComputedStyle(pfs).display : 'null',
                            pfsHeight: pfs ? pfs.offsetHeight : 0,
                            cardBg: card ? window.getComputedStyle(card).backgroundColor : 'null',
                            cardColor: card ? window.getComputedStyle(card).color : 'null',
                            cardBorder: card ? window.getComputedStyle(card).borderLeftColor : 'null',
                            titleColor: title ? window.getComputedStyle(title).color : 'null',
                            titleText: title ? title.textContent : 'null',
                            btnDisplay: btn ? window.getComputedStyle(btn).display : 'null',
                            btnBg: btn ? window.getComputedStyle(btn).backgroundColor : 'null'
                        };
                    })()
                    """
                    m_eval = await send("Runtime.evaluate", {"expression": eval_css, "returnByValue": True})
                    res = await recv_until(m_eval)
                    print(json.dumps(res.get("result", {}).get("result", {}).get("value"), indent=2))
                    
                    # Also take screenshot!
                    m_ss = await send("Page.captureScreenshot", {"format": "png"})
                    res_ss = await recv_until(m_ss)
                    import base64
                    png_data = base64.b64decode(res_ss["result"]["data"])
                    filename = "screenshot_light.png" if "theme=light" in url else "screenshot_default.png"
                    with open(f"D:/veepoo/{filename}", "wb") as f_img:
                        f_img.write(png_data)
                    print(f"Saved {filename}, size: {len(png_data)}")
    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(main())
