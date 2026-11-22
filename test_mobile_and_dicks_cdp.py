import asyncio
import aiohttp
import subprocess
import json
import time
import base64
import os
import shutil
import tempfile

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def main():
    print("[*] Launching headless Chrome for Mobile Wallet, Q-Tag & Dick's Drive-In verification...", flush=True)
    temp_dir = tempfile.mkdtemp()
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9234",
        f"--user-data-dir={temp_dir}",
        "--disable-gpu",
        "--window-size=1280,2200",
        "about:blank"
    ])
    try:
        # Wait for Chrome CDP endpoint
        ws_url = None
        async with aiohttp.ClientSession() as session:
            for _ in range(20):
                await asyncio.sleep(0.5)
                try:
                    async with session.get("http://127.0.0.1:9234/json") as resp:
                        targets = await resp.json()
                        page_targets = [t for t in targets if t.get("type") == "page"]
                        if page_targets:
                            ws_url = page_targets[0]["webSocketDebuggerUrl"]
                            print(f"[✔] Connected to Chrome page target: {ws_url}", flush=True)
                            break
                except Exception as ex:
                    pass
            
            if not ws_url:
                raise RuntimeError("Could not obtain Chrome webSocketDebuggerUrl")

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
                        r = await ws.receive_json()
                        if r.get("id") == mid:
                            if "error" in r:
                                print(f"[!] Error in {method}: {r['error']}", flush=True)
                            return r.get("result", {})

                await send("Page.enable")
                await send("Runtime.enable")

                # =============================================================
                # TEST 1: MOBILE CELL UPLOADER (Wallet Auth + Dick's Drive-In)
                # =============================================================
                print("\n--- TEST 1: Mobile Phone Portal (http://192.168.12.207:8899/mobile_upload.html) ---")
                await send("Page.navigate", {"url": "http://192.168.12.207:8899/mobile_upload.html"})
                await asyncio.sleep(2.0)

                res_mobile = await send("Runtime.evaluate", {
                    "expression": """(async () => {
                        // Simulate saving to phone wallet & logging in
                        localStorage.setItem('lcars_cell_wallet_auth', 'true');
                        localStorage.setItem('lcars_cell_username', 'Patient 5010 (Wallet Connected)');
                        showUploaderScreen();

                        // Test Dick's Drive-In input
                        const notes = document.getElementById('txtMobileMealNotes');
                        notes.value = "Dick's Drive-In Deluxe Burger (Seattle 1954)";

                        // Trigger Gemini Upload
                        await uploadMealToGemini();
                        await new Promise(r => setTimeout(r, 600));

                        const desc = document.getElementById('lblMealDesc')?.textContent;
                        const netCarbs = document.getElementById('valNetCarbs')?.textContent;
                        const compliance = document.getElementById('lblCompliance')?.textContent;

                        return {
                            uploaderVisible: document.getElementById('sectionMealUploader')?.style.display !== 'none',
                            loginHidden: document.getElementById('sectionWalletLogin')?.style.display === 'none',
                            analyzedDesc: desc,
                            netCarbs: netCarbs,
                            complianceText: compliance
                        };
                    })()""",
                    "awaitPromise": True,
                    "returnByValue": True
                })
                print("Mobile Portal Audit:", json.dumps(res_mobile.get("result", {}).get("value"), indent=2))

                # Capture Mobile Upload Screen
                clip_mobile = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.body;
                        return { x: 0, y: 0, width: 480, height: 860, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip_m = clip_mobile.get("result", {}).get("value")
                if clip_m:
                    ss = await send("Page.captureScreenshot", {"clip": clip_m})
                    if ss.get("data"):
                        p = "D:/veepoo/images/lcars_mobile_phone_dicks_uploader.png"
                        with open(p, "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print(f"[✔] Captured {p}")

                # =============================================================
                # TEST 2: DESKTOP LCARS TAB 09 Q-TAG GATEWAY & DICKS PRESET
                # =============================================================
                print("\n--- TEST 2: Desktop Tab 09 Google Lens Q-Tag Gateway ---")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.0)

                res_desktop = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('diettrades');
                        const qrImg = document.getElementById('imgMobileWifiQr');
                        const urlInput = document.getElementById('txtMobileWifiUrl');
                        
                        // Select Dick's Drive-In Preset in Desktop Meal Builder
                        selectMealBuilderPreset('dicks');
                        const badgeStatus = document.getElementById('badgeGeminiGoalStatus')?.textContent;
                        const carbsChip = document.getElementById('chipNetCarbs')?.textContent;
                        const storesTable = document.getElementById('tableMealBOM')?.textContent;

                        return {
                            qrImgFound: !!qrImg && qrImg.src.includes('mobile_wifi_qr.svg'),
                            wifiUrlValue: urlInput ? urlInput.value : null,
                            dicksBadgeStatus: badgeStatus,
                            dicksNetCarbs: carbsChip,
                            hasSeattleStores: storesTable.includes('Broadway') && storesTable.includes('Wallingford')
                        };
                    })()""",
                    "returnByValue": True
                })
                print("Desktop Tab 09 Audit:", json.dumps(res_desktop.get("result", {}).get("value"), indent=2))

                # Capture Desktop Tab 09 Gateway screenshot
                clip_gw = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const el = document.getElementById('txtMobileWifiUrl')?.closest('div[style*="border-top: 4px"]');
                        if (!el) return null;
                        const r = el.getBoundingClientRect();
                        return { x: r.left, y: r.top, width: r.width, height: r.height, scale: 1 };
                    })()""",
                    "returnByValue": True
                })
                clip_g = clip_gw.get("result", {}).get("value")
                if clip_g and clip_g.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip_g})
                    if ss.get("data"):
                        p = "D:/veepoo/images/lcars_tab09_mobile_wifi_qtag_gateway.png"
                        with open(p, "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print(f"[✔] Captured {p}")

                # Replicate new images across presentation directories
                dest_dirs = [
                    "D:/veepoo/presentation/images",
                    "D:/veepoo/slides/images",
                    "D:/veepoo/presentation/slides/images"
                ]
                for d in dest_dirs:
                    os.makedirs(d, exist_ok=True)
                    for im in ["lcars_mobile_phone_dicks_uploader.png", "lcars_tab09_mobile_wifi_qtag_gateway.png"]:
                        src = f"D:/veepoo/images/{im}"
                        if os.path.exists(src):
                            shutil.copyfile(src, os.path.join(d, im))
                print("[✔] Replicated screenshots across all presentation folders")

    finally:
        proc.kill()
        try:
            shutil.rmtree(temp_dir, ignore_errors=True)
        except Exception:
            pass
        print("[*] Chrome closed successfully.", flush=True)

if __name__ == "__main__":
    asyncio.run(main())
