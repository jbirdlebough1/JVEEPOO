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
        "--remote-debugging-port=9233",
        "--disable-gpu",
        "--window-size=1280,1200",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9233/json") as resp:
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
                await asyncio.sleep(2.5)

                test_script = """
                (async () => {
                    const results = {};

                    // 1. Full Scan tab check
                    const pfs = document.getElementById('pageFullScan');
                    results.fullScan = {
                        active: pfs && pfs.classList.contains('active-page'),
                        stage1_text: document.getElementById('stageStatus1') ? document.getElementById('stageStatus1').innerText.trim() : null,
                        stage1_pct: document.getElementById('stagePct1') ? document.getElementById('stagePct1').innerText.trim() : null,
                        repGlucose: document.getElementById('repValGlucose') ? document.getElementById('repValGlucose').innerText.trim() : null,
                        repTemp: document.getElementById('repValTemp') ? document.getElementById('repValTemp').innerText.trim() : null,
                        repBp: document.getElementById('repValBp') ? document.getElementById('repValBp').innerText.trim() : null,
                        repSpo2: document.getElementById('repValSpo2') ? document.getElementById('repValSpo2').innerText.trim() : null,
                        repHr: document.getElementById('repValHr') ? document.getElementById('repValHr').innerText.trim() : null,
                        repHrv: document.getElementById('repValHrv') ? document.getElementById('repValHrv').innerText.trim() : null,
                        hasSkipBtn: document.getElementById('btnSkipFullScan') !== null,
                        hasRunAnotherBtn: document.body.innerText.includes('RUN ANOTHER FULL SCAN')
                    };

                    // 2. Vitals screen removal verification
                    results.vitalsRemoval = {
                        hasNavTabVitals: document.getElementById('navTabVitals') !== null,
                        hasPageVitals: document.getElementById('pageVitals') !== null,
                        hasVitalsBridge: document.getElementById('vitalsDomBridge') !== null,
                        bridgeGlucose: document.getElementById('valGlucose') ? document.getElementById('valGlucose').innerText.trim() : null
                    };

                    // 3. Cardio tab check (now 02-CARDIO)
                    switchTab('cardio');
                    await new Promise(r => setTimeout(r, 200));
                    results.cardio = {
                        active: document.getElementById('pageCardio') && document.getElementById('pageCardio').classList.contains('active-page'),
                        heartRate: document.querySelector('#pageCardio .valHeartRate') ? document.querySelector('#pageCardio .valHeartRate').innerText.trim() : null
                    };

                    // 4. Metabolic Tab check (Daily Intake Summary & Meal Log)
                    switchTab('metabolic');
                    await new Promise(r => setTimeout(r, 400));
                    results.metabolic = {
                        dailyDate: document.getElementById('lblDailyIntakeDate') ? document.getElementById('lblDailyIntakeDate').innerText.trim() : null,
                        summaryBreakfast: document.getElementById('summaryMacroBreakfast') ? document.getElementById('summaryMacroBreakfast').innerText.trim() : null,
                        summaryLunch: document.getElementById('summaryMacroLunch') ? document.getElementById('summaryMacroLunch').innerText.trim() : null,
                        summarySnack: document.getElementById('summaryMacroSnack') ? document.getElementById('summaryMacroSnack').innerText.trim() : null,
                        dayKcal: document.getElementById('lblDayKcal') ? document.getElementById('lblDayKcal').innerText.trim() : null,
                        dayNetCarbs: document.getElementById('lblDayNetCarbs') ? document.getElementById('lblDayNetCarbs').innerText.trim() : null,
                        dailyBadge: document.getElementById('badgeDailyIntakeStatus') ? document.getElementById('badgeDailyIntakeStatus').innerText.trim() : null,
                        mealLogCardCount: document.querySelectorAll('#dietHistoryList .diet-item-card').length,
                        firstMealTitle: document.querySelector('#dietHistoryList .diet-item-card strong') ? document.querySelector('#dietHistoryList .diet-item-card strong').innerText.trim() : null
                    };

                    return results;
                })()
                """
                m_eval = await send("Runtime.evaluate", {"expression": test_script, "awaitPromise": True, "returnByValue": True})
                res = await recv_until(m_eval)
                val = res.get("result", {}).get("result", {}).get("value")
                print("LIVE VERIFICATION RESULT:\n", json.dumps(val, indent=2))

    finally:
        proc.kill()

if __name__ == "__main__":
    asyncio.run(main())
