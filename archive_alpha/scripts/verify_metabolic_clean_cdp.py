import asyncio
import aiohttp
import json
import subprocess
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def test_metabolic():
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9266",
        "--disable-gpu",
        "--window-size=1600,1000",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9266/json") as resp:
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
                    switchTab('metabolic');
                    await renderDietHistory();

                    const results = {
                        dateStr: document.getElementById('lblDailyIntakeDate') ? document.getElementById('lblDailyIntakeDate').textContent : null,
                        breakfast: {
                            count: document.getElementById('summaryCountBreakfast')?.textContent,
                            macro: document.getElementById('summaryMacroBreakfast')?.textContent,
                            list: document.getElementById('summaryListBreakfast')?.textContent
                        },
                        lunch: {
                            count: document.getElementById('summaryCountLunch')?.textContent,
                            macro: document.getElementById('summaryMacroLunch')?.textContent,
                            list: document.getElementById('summaryListLunch')?.textContent
                        },
                        dinner: {
                            count: document.getElementById('summaryCountDinner')?.textContent,
                            macro: document.getElementById('summaryMacroDinner')?.textContent,
                            list: document.getElementById('summaryListDinner')?.textContent
                        },
                        snack: {
                            count: document.getElementById('summaryCountSnack')?.textContent,
                            macro: document.getElementById('summaryMacroSnack')?.textContent,
                            list: document.getElementById('summaryListSnack')?.textContent
                        },
                        totals: {
                            kcal: document.getElementById('lblDayKcal')?.textContent,
                            netCarbs: document.getElementById('lblDayNetCarbs')?.textContent,
                            protein: document.getElementById('lblDayProtein')?.textContent,
                            fat: document.getElementById('lblDayFat')?.textContent,
                            fiber: document.getElementById('lblDayFiber')?.textContent,
                            badge: document.getElementById('badgeDailyIntakeStatus')?.textContent
                        },
                        mealCards: Array.from(document.querySelectorAll('#dietLog .meal-card, #dietLog .diet-card')).map(card => {
                            const title = card.querySelector('strong, .meal-title, h4')?.textContent?.trim();
                            const time = card.querySelector('.meal-time, .text-muted, span')?.textContent?.trim();
                            return { title, time };
                        }).slice(0, 5)
                    };

                    return results;
                })()
                """
                m_eval = await send("Runtime.evaluate", {"expression": test_script, "awaitPromise": True, "returnByValue": True})
                res_eval = await recv_until(m_eval)
                val = res_eval.get("result", {}).get("result", {}).get("value")
                print("METABOLIC LIVE VERIFICATION:\n", json.dumps(val, indent=2))
                return val
    finally:
        proc.terminate()

if __name__ == '__main__':
    asyncio.run(test_metabolic())
