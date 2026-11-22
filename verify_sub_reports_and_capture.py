import asyncio
import aiohttp
import subprocess
import json
import time
import base64
import os
import shutil

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def main():
    print("[*] Launching headless Chrome for Sub-Reports verification & screenshot capture...")
    proc = subprocess.Popen([
        chrome_path,
        "--headless=new",
        "--remote-debugging-port=9231",
        "--disable-gpu",
        "--window-size=1280,2400",
        "about:blank"
    ])
    time.sleep(1.5)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get("http://127.0.0.1:9231/json") as resp:
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
                        r = await ws.receive_json()
                        if r.get("id") == mid: return r.get("result", {})

                await send("Page.enable")
                await send("Runtime.enable")

                print("[*] Navigating to http://localhost:8899/index.html?theme=light...")
                await send("Page.navigate", {"url": "http://localhost:8899/index.html?theme=light"})
                await asyncio.sleep(2.5)

                # Switch to Tab 04 (Reports) and evaluate sub-report elements
                print("\n--- Verifying Watch Analysis Sub-Reports Navigation Strip & 6 Cards in Tab 04 ---")
                res_sub = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        switchTab('reports');
                        const strip = document.getElementById('subReportBtnStrip');
                        const stripBtns = strip ? Array.from(strip.querySelectorAll('button')).map(b => b.textContent.trim()) : [];
                        
                        const subCards = [
                            'boxSubMetabolism',
                            'boxSubEcgStress',
                            'boxSubMet',
                            'boxSubBloodComp',
                            'boxSubBodyComp',
                            'boxSubBreath'
                        ];
                        
                        const cardStatus = {};
                        for (const id of subCards) {
                            const el = document.getElementById(id);
                            if (el) {
                                const style = window.getComputedStyle(el);
                                cardStatus[id] = {
                                    found: true,
                                    title: el.querySelector('.chart-title')?.textContent?.trim()?.replace(/\\s+/g, ' '),
                                    bgColor: style.backgroundColor
                                };
                            } else {
                                cardStatus[id] = { found: false };
                            }
                        }
                        
                        // Test jumpToSubReport function
                        let jumpTested = false;
                        if (typeof window.jumpToSubReport === 'function') {
                            window.jumpToSubReport('boxSubMetabolism');
                            jumpTested = true;
                        }
                        
                        return {
                            stripFound: !!strip,
                            buttonCount: stripBtns.length,
                            buttons: stripBtns,
                            cards: cardStatus,
                            jumpFunctionTested: jumpTested
                        };
                    })()""",
                    "returnByValue": True
                })
                val = res_sub.get("result", {}).get("value")
                print("Sub-Reports Audit Result:", json.dumps(val, indent=2))

                # Capture screenshot of the Sub-Reports strip and top 2 cards
                clip_sub = await send("Runtime.evaluate", {
                    "expression": """(() => {
                        const strip = document.getElementById('subReportBtnStrip')?.closest('div[style*="border-top: 4px"]');
                        const meta = document.getElementById('boxSubMetabolism');
                        const ecg = document.getElementById('boxSubEcgStress');
                        if (!strip) return null;
                        
                        const rStrip = strip.getBoundingClientRect();
                        const rEcg = ecg ? ecg.getBoundingClientRect() : rStrip;
                        const totalHeight = (rEcg.bottom - rStrip.top) + 20;
                        
                        return {
                            x: rStrip.left,
                            y: rStrip.top,
                            width: rStrip.width,
                            height: Math.min(totalHeight, 1050),
                            scale: 1
                        };
                    })()""",
                    "returnByValue": True
                })
                clip = clip_sub.get("result", {}).get("value")
                if clip and clip.get("width") > 0:
                    ss = await send("Page.captureScreenshot", {"clip": clip})
                    if ss.get("data"):
                        img_path = "D:/veepoo/images/lcars_slide28_watch_sub_reports.png"
                        with open(img_path, "wb") as f:
                            f.write(base64.b64decode(ss["data"]))
                        print(f"[✔] Captured {img_path}")
                        
                        dest_dirs = [
                            "D:/veepoo/presentation/images",
                            "D:/veepoo/slides/images",
                            "D:/veepoo/presentation/slides/images"
                        ]
                        for d in dest_dirs:
                            os.makedirs(d, exist_ok=True)
                            shutil.copyfile(img_path, os.path.join(d, "lcars_slide28_watch_sub_reports.png"))
                        print("[✔] Replicated lcars_slide28_watch_sub_reports.png to all slide image directories")
                else:
                    print("[!] Failed to get clip rect for Sub-Reports screenshot")

    finally:
        proc.kill()
        print("[*] Chrome closed successfully.")

if __name__ == "__main__":
    asyncio.run(main())
