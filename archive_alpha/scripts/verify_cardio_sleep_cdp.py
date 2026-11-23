import asyncio
import aiohttp
import json
import subprocess
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def test_cardio_sleep():
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9255",
        "--disable-gpu",
        "--window-size=1600,1000",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9255/json") as resp:
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

                test_script = """
                (async () => {
                    const results = {};
                    // 1. Switch to tab 02-CARDIO
                    switchTab('cardio');
                    results.cardioActive = document.getElementById('pageCardio').classList.contains('active-page');

                    // 2. Initial state (Last Night default)
                    results.initialSleep = {
                        total: document.getElementById('valSleep').textContent,
                        deep: document.getElementById('valDeepSleep').textContent,
                        light: document.getElementById('valLightSleep').textContent,
                        rem: document.getElementById('valRemSleep').textContent,
                        wake: document.getElementById('valWakeSleep').textContent,
                        subtitle: document.getElementById('lblSleepSubTitle').textContent
                    };

                    // 3. Click PREVIOUS NIGHT button
                    await switchSleepView(1);
                    results.prevNightSleep = {
                        total: document.getElementById('valSleep').textContent,
                        deep: document.getElementById('valDeepSleep').textContent,
                        light: document.getElementById('valLightSleep').textContent,
                        rem: document.getElementById('valRemSleep').textContent,
                        wake: document.getElementById('valWakeSleep').textContent,
                        subtitle: document.getElementById('lblSleepSubTitle').textContent,
                        activeBtn: document.getElementById('btnSleepPrevNight').style.color
                    };

                    // 4. Click LAST NIGHT button to switch back
                    await switchSleepView(0);
                    results.lastNightRestored = {
                        total: document.getElementById('valSleep').textContent,
                        deep: document.getElementById('valDeepSleep').textContent,
                        subtitle: document.getElementById('lblSleepSubTitle').textContent
                    };

                    return results;
                })()
                """
                m_eval = await send("Runtime.evaluate", {"expression": test_script, "awaitPromise": True, "returnByValue": True})
                res_eval = await recv_until(m_eval)
                val = res_eval.get("result", {}).get("result", {}).get("value")
                print("CARDIO SLEEP TEST RESULT:\n", json.dumps(val, indent=2))
                return val
    finally:
        proc.terminate()

if __name__ == '__main__':
    asyncio.run(test_cardio_sleep())
