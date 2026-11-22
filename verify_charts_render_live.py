import asyncio
import aiohttp
import subprocess
import json
import time

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

async def check():
    proc = subprocess.Popen([
        chrome_path,
        '--headless=new', '--remote-debugging-port=9266', '--disable-gpu',
        'about:blank'
    ])
    time.sleep(2)
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get('http://127.0.0.1:9266/json') as resp:
                ws_url = (await resp.json())[0]['webSocketDebuggerUrl']
            async with session.ws_connect(ws_url) as ws:
                mid = 1
                async def send(m, p=None):
                    nonlocal mid
                    mid += 1
                    await ws.send_json({'id': mid, 'method': m, 'params': p or {}})
                    while True:
                        r = await ws.receive_json()
                        if r.get('id') == mid: return r.get('result', {})

                await send('Page.enable')
                await send('Runtime.enable')
                await send('Page.navigate', {'url': 'http://localhost:8899/index.html?theme=light'})
                await asyncio.sleep(2.5)

                res = await send('Runtime.evaluate', {
                    'expression': '''
                    (() => {
                        switchTab('reports');
                        if (typeof renderReportsView === 'function') renderReportsView();
                        const ids = [
                            'curveTimelineGlucose', 'curveTimelineA1C', 'curveTimelineTemp',
                            'curveTimelineHeartRate', 'curveTimelineBP', 'curveTimelineSpO2',
                            'curveTimelineHRV', 'curveTimelineSleep', 'curveTimelineExercise'
                        ];
                        return ids.map(id => {
                            const el = document.getElementById(id);
                            if (!el) return { id, exists: false };
                            const svg = el.querySelector('svg');
                            const circles = el.querySelectorAll('circle');
                            const polyline = el.querySelector('polyline');
                            const path = el.querySelector('path');
                            return {
                                id,
                                exists: true,
                                hasSvg: !!svg,
                                numCircles: circles.length,
                                hasPolylineOrPath: !!(polyline || path),
                                innerLen: el.innerHTML.length
                            };
                        });
                    })()
                    ''',
                    'returnByValue': True
                })
                print(json.dumps(res.get('result', {}).get('value', []), indent=2))
    finally:
        proc.terminate()

asyncio.run(check())
