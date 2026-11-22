import asyncio
import aiohttp
import json
import subprocess
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def test_live():
    print("Launching test headless Chrome...")
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
                            return res.get("result", {}).get("result", {})

                await send("Page.enable")
                await send("Runtime.enable")

                print("Navigating to http://localhost:8899/index.html?theme=light...")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.5)

                # Check tab button label
                res = await send("Runtime.evaluate", {
                    "expression": "const el = document.getElementById('navTabWatchFaces'); el ? el.textContent.trim() : ''",
                    "returnByValue": True
                })
                tab_text = res.get("value", "")
                print(f"Tab button text: '{tab_text}'")
                assert "WATCH" in tab_text, f"Expected WATCH in tab text, got {tab_text}"

                # Switch to watch tab
                await send("Runtime.evaluate", {
                    "expression": "switchTab('watchfaces');"
                })
                await asyncio.sleep(1.0)

                # Verify analyzer elements
                eval_script = """
                (() => {
                    const model = document.getElementById('analyzerWatchModel');
                    const chans = document.getElementById('analyzerDataChannelsGrid');
                    const cmds = document.getElementById('analyzerCommandTableBody');
                    const p0 = document.getElementById('analyzerB8Page0Grid');
                    const p1 = document.getElementById('analyzerB8Page1Grid');
                    const term = document.getElementById('analyzerTerminalOutput');

                    return {
                        hasModel: !!model && model.textContent.length > 0,
                        modelText: model ? model.textContent : '',
                        chanCount: chans ? chans.children.length : 0,
                        cmdCount: cmds ? cmds.children.length : 0,
                        p0Count: p0 ? p0.children.length : 0,
                        p1Count: p1 ? p1.children.length : 0,
                        termLen: term ? term.textContent.length : 0
                    };
                })()
                """
                res = await send("Runtime.evaluate", {
                    "expression": eval_script,
                    "returnByValue": True
                })
                data = res.get("value", {})
                print("Analyzer Elements Audit:", json.dumps(data, indent=2))
                assert data["chanCount"] == 12, f"Expected 12 data channels, got {data['chanCount']}"
                assert data["cmdCount"] == 22, f"Expected 22 commands, got {data['cmdCount']}"
                assert data["p0Count"] == 8, f"Expected 8 Page 0 bitfields, got {data['p0Count']}"
                assert data["p1Count"] == 8, f"Expected 8 Page 1 bitfields, got {data['p1Count']}"

                # Test running the audit
                print("Testing runWatchCapabilityAudit()...")
                await send("Runtime.evaluate", {
                    "expression": "runWatchCapabilityAudit();"
                })
                await asyncio.sleep(0.8)

                res = await send("Runtime.evaluate", {
                    "expression": "document.getElementById('analyzerTerminalOutput').textContent",
                    "returnByValue": True
                })
                term_text = res.get("value", "")
                print("Terminal Output after audit:\n", term_text)
                assert "0xB8 PAGE 0 MASK" in term_text, "Expected 0xB8 PAGE 0 MASK in terminal"
                assert "0xB8 PAGE 1 MASK" in term_text, "Expected 0xB8 PAGE 1 MASK in terminal"
                assert "12/12 SENSOR CHANNELS VERIFIED" in term_text, "Expected verified text in terminal"

                print("✓ LIVE WATCH ANALYZER CDP VERIFICATION 100% PASSED!")

    finally:
        proc.terminate()

if __name__ == '__main__':
    asyncio.run(test_live())
