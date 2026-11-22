import asyncio
import aiohttp
import json
import subprocess
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def test_full_scan():
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9244",
        "--disable-gpu",
        "--window-size=1600,1000",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9244/json") as resp:
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
                await asyncio.sleep(2.0)

                # Test initiating full scan sequence and fast-forwarding timers to verify no exceptions
                test_script = """
                (async () => {
                    const errors = [];
                    window.addEventListener('error', e => errors.push(e.message));

                    // Check initial stage states
                    const initialStages = [1,2,3,4,5,6].map(i => ({
                        stage: i,
                        pct: document.getElementById('stagePct' + i).textContent,
                        status: document.getElementById('stageStatus' + i).textContent
                    }));

                    // Trigger Stage 1 -> Stage 2 transition directly to test executeScanStage2_Temp
                    startFullScanSequence();
                    const s1Active = isFullScanRunning && fullScanCurrentStage === 1;

                    // Fast finalize stage 1 and enter stage 2
                    finalizeScanStage1_Glucose(true);
                    executeScanStage2_Temp();
                    const s2Active = isFullScanRunning && fullScanCurrentStage === 2;
                    const s2Pct = document.getElementById('stagePct2').textContent;
                    const s2Status = document.getElementById('stageStatus2').textContent;

                    // Fast finalize stage 2 and enter stage 3
                    finalizeScanStage2_Temp(true);
                    executeScanStage3_BP();
                    const s3Active = isFullScanRunning && fullScanCurrentStage === 3;
                    const s3Pct = document.getElementById('stagePct3').textContent;
                    const s3Status = document.getElementById('stageStatus3').textContent;

                    // Fast finalize stage 3 and enter stage 4
                    finalizeScanStage3_BP(true);
                    executeScanStage4_SpO2();
                    const s4Active = isFullScanRunning && fullScanCurrentStage === 4;

                    // Fast finalize stage 4 and enter stage 5
                    finalizeScanStage4_SpO2(true);
                    executeScanStage5_Pulse();
                    const s5Active = isFullScanRunning && fullScanCurrentStage === 5;

                    // Fast finalize stage 5 and enter stage 6
                    finalizeScanStage5_Pulse(true);
                    executeScanStage6_HRV();
                    const s6Active = isFullScanRunning && fullScanCurrentStage === 6;

                    // Finalize stage 6 and complete full scan
                    finalizeScanStage6_HRV();

                    const reportVisible = document.getElementById('cardFullScanReport').style.display !== 'none';
                    const repGlucose = document.getElementById('repValGlucose').textContent;
                    const repTemp = document.getElementById('repValTemp').textContent;
                    const repBp = document.getElementById('repValBp').textContent;
                    const repSpo2 = document.getElementById('repValSpo2').textContent;
                    const repHr = document.getElementById('repValHr').textContent;
                    const repHrv = document.getElementById('repValHrv').textContent;

                    return {
                        errors,
                        initialStages,
                        s1Active,
                        s2Active,
                        s2Pct,
                        s2Status,
                        s3Active,
                        s3Pct,
                        s3Status,
                        s4Active,
                        s5Active,
                        s6Active,
                        reportVisible,
                        repGlucose,
                        repTemp,
                        repBp,
                        repSpo2,
                        repHr,
                        repHrv
                    };
                })()
                """
                m_eval = await send("Runtime.evaluate", {"expression": test_script, "awaitPromise": True, "returnByValue": True})
                res_eval = await recv_until(m_eval)
                val = res_eval.get("result", {}).get("result", {}).get("value")
                print("PIPELINE EXECUTION RESULT:\n", json.dumps(val, indent=2))
                return val
    finally:
        proc.terminate()

if __name__ == '__main__':
    asyncio.run(test_full_scan())
