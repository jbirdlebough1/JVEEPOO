import asyncio
import json
import urllib.request
import websockets

async def test_tab05():
    # 1. Get CDP targets
    req = urllib.request.Request('http://127.0.0.1:9222/json')
    try:
        with urllib.request.urlopen(req, timeout=3) as resp:
            targets = json.loads(resp.read().decode())
    except Exception as e:
        print("Could not connect to Chrome debugging port 9222:", e)
        return

    # Find the target with port 8899
    target = None
    for t in targets:
        if '8899' in t.get('url', ''):
            target = t
            break
    if not target:
        target = targets[0]

    ws_url = target.get('webSocketDebuggerUrl')
    print("Connecting to CDP target:", target.get('url'))

    async with websockets.connect(ws_url, max_size=20*1024*1024) as ws:
        msg_id = 1
        
        async def send(method, params=None):
            nonlocal msg_id
            m = {"id": msg_id, "method": method, "params": params or {}}
            msg_id += 1
            await ws.send(json.dumps(m))
            while True:
                resp = json.loads(await ws.recv())
                if resp.get('id') == m['id']:
                    return resp

        # Reload page to get fresh HTML
        await send("Page.reload")
        await asyncio.sleep(2.0)

        # Switch to Tab 05 (ADA Trades)
        eval_resp = await send("Runtime.evaluate", {
            "expression": """(() => {
                switchTab('diettrades');
                const btnActiveGoals = Array.from(document.querySelectorAll('button')).find(b => b.textContent.includes('VIEW ACTIVE DIABETIC GOALS'));
                const badgeCoach = Array.from(document.querySelectorAll('.badge, span')).find(b => b.textContent.includes('GEMINI COACH ACTIVE'));
                const inputArea = document.getElementById('txtMealBuilderInput');
                const fluidInp = document.getElementById('inpMealFluidOz');
                const listbox = document.getElementById('listboxPopularMeals');
                const options = listbox ? Array.from(listbox.options).map(o => o.text) : [];
                
                // Select MEAL-01 and trigger analyze
                if (listbox) {
                    listbox.value = 'meal01';
                    onSelectPopularMealFromListbox('meal01');
                }
                
                return {
                    hasActiveGoalsBtn: !!btnActiveGoals,
                    hasCoachBadge: !!badgeCoach,
                    inputWidth: inputArea ? inputArea.offsetWidth : 0,
                    inputParentWidth: inputArea ? inputArea.parentElement.offsetWidth : 0,
                    fluidOzValue: fluidInp ? fluidInp.value : null,
                    options: options
                };
            })()""",
            "returnByValue": True
        })

        res = eval_resp.get('result', {}).get('result', {}).get('value', {})
        print("Tab 05 Verification State:")
        print("Active Goals Button Present:", res.get('hasActiveGoalsBtn'))
        print("Gemini Coach Badge Present:", res.get('hasCoachBadge'))
        print("Input Area Width:", res.get('inputWidth'), "of Parent:", res.get('inputParentWidth'))
        print("Fluid Oz Value:", res.get('fluidOzValue'))
        print("Meal Pantry Options Count:", len(res.get('options', [])))
        for opt in res.get('options', []):
            print("  •", opt)

        # Wait 1.5s for Gemini analysis to render
        await asyncio.sleep(1.5)

        # Verify Hydration Tile in Gemini Analysis
        eval_resp2 = await send("Runtime.evaluate", {
            "expression": """(() => {
                const hydChip = document.getElementById('lblHydrationVolumeChip')?.textContent || '';
                const bioChip = document.getElementById('lblPatientBiometrics')?.textContent || '';
                const tgtChip = document.getElementById('lblDailyHydrationTarget')?.textContent || '';
                const impactChip = document.getElementById('lblHydrationMetabolicImpact')?.textContent || '';
                return { hydChip, bioChip, tgtChip, impactChip };
            })()""",
            "returnByValue": True
        })
        res2 = eval_resp2.get('result', {}).get('result', {}).get('value', {})
        print("\nGemini Hydration Analysis State:")
        print("  Hydration Chip:", res2.get('hydChip'))
        print("  Biometrics Chip:", res2.get('bioChip'))
        print("  Target Chip:", res2.get('tgtChip'))
        print("  Impact Chip:", res2.get('impactChip'))

        # Take screenshot of Tab 05
        shot = await send("Page.captureScreenshot", {"format": "png"})
        import base64
        img_data = base64.b64decode(shot['result']['data'])
        with open('tab05_verified_render.png', 'wb') as f:
            f.write(img_data)
        print("\nSaved tab05_verified_render.png successfully!")

asyncio.run(test_tab05())
