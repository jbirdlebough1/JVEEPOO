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

                async def recv_until(mid):
                    while True:
                        r = await ws.receive_json()
                        if r.get("method") == "Runtime.consoleAPICalled":
                            args = [a.get("value") or a.get("description") for a in r.get("params", {}).get("args", [])]
                            print("[CONSOLE]", r.get("params", {}).get("type"), args)
                        elif r.get("method") == "Runtime.exceptionThrown":
                            print("[EXCEPTION]", r.get("params", {}).get("exceptionDetails", {}).get("text"), r.get("params", {}).get("exceptionDetails", {}).get("exception"))
                        elif r.get("id") == mid:
                            return r

                m1 = await send("Page.enable")
                await recv_until(m1)
                m2 = await send("Runtime.enable")
                await recv_until(m2)
                
                m3 = await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await recv_until(m3)
                
                # Wait for initial load
                await asyncio.sleep(2.5)

                # Check pageFullScan display and bounds
                eval_script = """
                (() => {
                    const pfs = document.getElementById('pageFullScan');
                    if (!pfs) return { error: 'pageFullScan not found' };
                    const rect = pfs.getBoundingClientRect();
                    const comp = window.getComputedStyle(pfs);
                    return {
                        id: pfs.id,
                        className: pfs.className,
                        display: comp.display,
                        visibility: comp.visibility,
                        opacity: comp.opacity,
                        rect: { top: rect.top, left: rect.left, width: rect.width, height: rect.height },
                        parent: pfs.parentElement ? { tag: pfs.parentElement.tagName, className: pfs.parentElement.className, height: pfs.parentElement.offsetHeight } : null,
                        childrenCount: pfs.children.length,
                        innerHTMLSnippet: pfs.innerHTML.substring(0, 300)
                    };
                })()
                """
                m4 = await send("Runtime.evaluate", {"expression": eval_script, "returnByValue": True})
                res = await recv_until(m4)
                print("pageFullScan state:", json.dumps(res.get("result", {}).get("result", {}).get("value"), indent=2))

                # Check all tabs active state
                eval_tabs = """
                (() => {
                    return Array.from(document.querySelectorAll('.tab-page')).map(p => ({
                        id: p.id,
                        classes: p.className,
                        display: window.getComputedStyle(p).display,
                        height: p.offsetHeight
                    }));
                })()
                """
                m5 = await send("Runtime.evaluate", {"expression": eval_tabs, "returnByValue": True})
                res_tabs = await recv_until(m5)
                print("Tabs state:", json.dumps(res_tabs.get("result", {}).get("result", {}).get("value"), indent=2))

    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(main())
