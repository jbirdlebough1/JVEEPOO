// cdp_full_verification.js
const { spawn } = require('child_process');
const http = require('http');
const fs = require('fs');

async function getJson(url) {
  return new Promise((resolve, reject) => {
    http.get(url, res => {
      let data = '';
      res.on('data', chunk => data += chunk);
      res.on('end', () => resolve(JSON.parse(data)));
    }).on('error', reject);
  });
}

async function run() {
  console.log("[*] Starting Headless Chrome for full verification...");
  const chrome = spawn("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", [
    '--headless=new',
    '--remote-debugging-port=9265',
    '--window-size=1600,1050',
    '--disable-gpu',
    'http://localhost:8899/index.html'
  ]);

  await new Promise(r => setTimeout(r, 2500));

  try {
    const list = await getJson('http://127.0.0.1:9265/json');
    const pageTab = list.find(t => t.type === 'page');
    console.log('[✔] Target page connected:', pageTab.title);

    const ws = new WebSocket(pageTab.webSocketDebuggerUrl);
    let msgId = 1;
    const callbacks = new Map();
    ws.onmessage = (event) => {
      const resp = JSON.parse(event.data);
      if (resp.id && callbacks.has(resp.id)) {
        const { resolve, reject } = callbacks.get(resp.id);
        callbacks.delete(resp.id);
        if (resp.error) reject(resp.error);
        else resolve(resp.result);
      }
    };
    await new Promise(r => ws.onopen = r);
    function send(method, params = {}) {
      return new Promise((resolve, reject) => {
        const id = msgId++;
        callbacks.set(id, { resolve, reject });
        ws.send(JSON.stringify({ id, method, params }));
      });
    }

    async function evalCode(expression) {
      const res = await send('Runtime.evaluate', { expression, returnByValue: true, awaitPromise: true });
      return res.result.value;
    }

    async function saveScreenshot(filename) {
      const { data } = await send('Page.captureScreenshot', { format: 'png' });
      fs.writeFileSync(filename, Buffer.from(data, 'base64'));
      console.log(`[✔] Saved screenshot: ${filename}`);
    }

    await send('Page.enable');
    await send('Runtime.enable');
    await new Promise(r => setTimeout(r, 2000));

    // TEST 1: Tab 08 - Medication (Verify visible on screen, top < 200px, Section 05 NOT visible)
    console.log("\n--- TEST 1: TAB 08 - MEDICATION ---");
    await evalCode(`switchTab('medication')`);
    await new Promise(r => setTimeout(r, 600));
    const medInfo = await evalCode(`(() => {
      const el = document.getElementById('pageMedication');
      const rect = el.getBoundingClientRect();
      const style = window.getComputedStyle(el);
      const sec05 = Array.from(document.querySelectorAll('*')).find(e => e.textContent && e.textContent.includes('SECTION 05: SPECIALIZED CARDIOMETABOLIC PROCEDURES'));
      return {
        id: el.id,
        className: el.className,
        rect: { top: rect.top, left: rect.left, width: rect.width, height: rect.height },
        display: style.display,
        sec05Visible: sec05 ? (sec05.offsetParent !== null && window.getComputedStyle(sec05).display !== 'none') : false
      };
    })()`);
    console.log("Medication Info:", JSON.stringify(medInfo, null, 2));
    await saveScreenshot('D:/veepoo/screenshot_tab08_medication_fixed.png');

    // TEST 2: Tab 09 - Clinical Goals (Verify visible on screen, top < 200px, Section 05 NOT visible)
    console.log("\n--- TEST 2: TAB 09 - CLINICAL GOALS ---");
    await evalCode(`switchTab('goals')`);
    await new Promise(r => setTimeout(r, 600));
    const goalsInfo = await evalCode(`(() => {
      const el = document.getElementById('pageGoals');
      const rect = el.getBoundingClientRect();
      const sec05 = Array.from(document.querySelectorAll('*')).find(e => e.textContent && e.textContent.includes('SECTION 05: SPECIALIZED CARDIOMETABOLIC PROCEDURES'));
      return {
        id: el.id,
        rect: { top: rect.top, left: rect.left, width: rect.width, height: rect.height },
        sec05Visible: sec05 ? (sec05.offsetParent !== null && window.getComputedStyle(sec05).display !== 'none') : false
      };
    })()`);
    console.log("Goals Info:", JSON.stringify(goalsInfo, null, 2));
    await saveScreenshot('D:/veepoo/screenshot_tab09_goals_fixed.png');

    // TEST 3: Tab 11 - Operations (Verify Section 05 IS visible exclusively here)
    console.log("\n--- TEST 3: TAB 11 - OPERATIONS (SECTION 05) ---");
    await evalCode(`switchTab('operations')`);
    await new Promise(r => setTimeout(r, 600));
    const opsInfo = await evalCode(`(() => {
      const sec05 = Array.from(document.querySelectorAll('*')).find(e => e.textContent && e.textContent.includes('SECTION 05: SPECIALIZED CARDIOMETABOLIC PROCEDURES'));
      return {
        sec05Present: !!sec05,
        sec05Visible: sec05 ? (sec05.offsetParent !== null) : false
      };
    })()`);
    console.log("Operations Info:", JSON.stringify(opsInfo, null, 2));

    // TEST 4: Meal Pantry Deduplication
    console.log("\n--- TEST 4: MEAL PANTRY DEDUPLICATION ---");
    await evalCode(`switchTab('diettrades')`);
    await new Promise(r => setTimeout(r, 800));
    const pantryDeduplication = await evalCode(`(() => {
      const listbox = document.getElementById('listboxPopularMeals');
      const options = Array.from(listbox ? listbox.options : []).map(o => o.textContent);
      const titles = options.map(t => t.replace(/^[✅⚠️]\s*/, '').replace(/\s*\([^)]*\)$/, '').trim().toLowerCase());
      const duplicates = titles.filter((item, index) => titles.indexOf(item) !== index);
      return {
        totalOptions: options.length,
        uniqueTitles: new Set(titles).size,
        duplicateCount: duplicates.length,
        duplicateTitles: duplicates,
        optionsSample: options.slice(0, 8)
      };
    })()`);
    console.log("Pantry Deduplication Info:", JSON.stringify(pantryDeduplication, null, 2));

    // TEST 5: Pathway B Pre-Choice Estimation with Peppermint Crunch Candy
    console.log("\n--- TEST 5: PATHWAY B - PEPPERMINT CRUNCH CANDY & METABOLIC BURDEN ---");
    const candyEstimate = await evalCode(`(async () => {
      const input = document.getElementById('txtMealBuilderInput');
      if (input) input.value = "Christmas candy pepper mint crunch 2 oz";
      await analyzeMealWithGemini();
      const title = document.getElementById('lblGeminiMealTitle')?.textContent;
      const status = document.getElementById('badgeGeminiGoalStatus')?.textContent;
      const metrics = document.getElementById('valGeminiCarbs')?.textContent;
      const spike = document.getElementById('valGeminiSpike')?.textContent;
      const costEl = document.getElementById('lblRestOrderCost')?.textContent;
      return { title, status, metrics, spike, costText: costEl };
    })()`);
    console.log("Candy Estimate Result:", JSON.stringify(candyEstimate, null, 2));
    await saveScreenshot('D:/veepoo/screenshot_tab02_candy_estimate.png');

    // TEST 6: Check no tilda$ or monetary cost in BOM table
    console.log("\n--- TEST 6: METABOLIC IMPACT HEADER & BOM TABLE ---");
    const bomCheck = await evalCode(`(() => {
      const ths = Array.from(document.querySelectorAll('#containerBOMTableWrapper th')).map(t => t.textContent.trim());
      const hasEstCost = ths.some(t => t === 'EST. COST');
      const hasMetabolicImpact = ths.some(t => t.includes('METABOLIC IMPACT'));
      return { ths, hasEstCost, hasMetabolicImpact };
    })()`);
    console.log("BOM Check:", JSON.stringify(bomCheck, null, 2));

    ws.close();
  } finally {
    chrome.kill();
  }
}

run().catch(err => {
  console.error("Test failed:", err);
  process.exit(1);
});
