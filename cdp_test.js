// cdp_test.js
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
  const chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
  const chrome = spawn(chromePath, [
    '--headless=new',
    '--remote-debugging-port=9258',
    '--window-size=1600,1000',
    '--disable-gpu',
    'http://localhost:8899/index.html'
  ]);

  await new Promise(r => setTimeout(r, 2500));

  try {
    const list = await getJson('http://127.0.0.1:9258/json');
    const pageTab = list.find(t => t.type === 'page');
    console.log('Target tab found:', pageTab.id, pageTab.title);

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

    await send('Page.enable');
    await send('Runtime.enable');

    // Wait 1.5s for page script init
    await new Promise(r => setTimeout(r, 1500));

    // Helper to evaluate JS in page
    async function evalCode(expression) {
      const res = await send('Runtime.evaluate', { expression, returnByValue: true, awaitPromise: true });
      return res.result.value;
    }

    // Helper to capture screenshot
    async function saveScreenshot(filename) {
      const { data } = await send('Page.captureScreenshot', { format: 'png' });
      fs.writeFileSync(filename, Buffer.from(data, 'base64'));
      console.log(`[✔] Saved screenshot: ${filename}`);
    }

    // 1. Initial State
    let initialActive = await evalCode(`document.querySelector('.tab-page.active-page')?.id`);
    console.log(`1. Initial active page: ${initialActive}`);

    // 2. Click Tab 07 (Help & FAQ)
    await evalCode(`switchTab('help')`);
    await new Promise(r => setTimeout(r, 300));
    let helpActive = await evalCode(`document.querySelector('.tab-page.active-page')?.id`);
    console.log(`2. Click Help -> active page: ${helpActive}`);

    // 3. Click Tab 08 (Medication)
    await evalCode(`switchTab('medication')`);
    await new Promise(r => setTimeout(r, 500));
    let medActive = await evalCode(`document.querySelector('.tab-page.active-page')?.id`);
    let isSec05InMed = await evalCode(`(() => {
      const sec05 = Array.from(document.querySelectorAll('*')).find(e => e.textContent && e.textContent.includes('SECTION 05: SPECIALIZED CARDIOMETABOLIC PROCEDURES'));
      if (!sec05) return false;
      return sec05.offsetParent !== null; // True if visible
    })()`);
    let medRegimenVisible = await evalCode(`(() => {
      const reg = Array.from(document.querySelectorAll('*')).find(e => e.textContent && e.textContent.includes('01. ACTIVE PHARMACOLOGICAL REGIMEN'));
      return reg && reg.offsetParent !== null;
    })()`);
    console.log(`3. Click Medication -> active page: ${medActive}`);
    console.log(`   - Is Section 05 visible on screen? ${isSec05InMed}`);
    console.log(`   - Is Medication Regimen visible on screen? ${medRegimenVisible}`);
    await saveScreenshot('D:/veepoo/screenshot_tab08_medication.png');

    // 4. Click Tab 09 (Clinical Goals)
    await evalCode(`switchTab('goals')`);
    await new Promise(r => setTimeout(r, 500));
    let goalsActive = await evalCode(`document.querySelector('.tab-page.active-page')?.id`);
    let isSec05InGoals = await evalCode(`(() => {
      const sec05 = Array.from(document.querySelectorAll('*')).find(e => e.textContent && e.textContent.includes('SECTION 05: SPECIALIZED CARDIOMETABOLIC PROCEDURES'));
      return sec05 && sec05.offsetParent !== null;
    })()`);
    let goalsCardVisible = await evalCode(`(() => {
      const g = Array.from(document.querySelectorAll('*')).find(e => e.textContent && e.textContent.includes('CLINICAL DIABETIC GOALS & PERSONALIZED AMBULATORY CARE PROTOCOL'));
      return g && g.offsetParent !== null;
    })()`);
    console.log(`4. Click Clinical Goals -> active page: ${goalsActive}`);
    console.log(`   - Is Section 05 visible on screen? ${isSec05InGoals}`);
    console.log(`   - Is Clinical Goals Protocol visible on screen? ${goalsCardVisible}`);
    await saveScreenshot('D:/veepoo/screenshot_tab09_goals.png');

    // 5. Test Draft Email modal
    await evalCode(`switchTab('export')`);
    await new Promise(r => setTimeout(r, 300));
    await evalCode(`openDraftEmailToDoctor()`);
    await new Promise(r => setTimeout(r, 300));
    let emailModalDisplay = await evalCode(`document.getElementById('modalDraftEmailToDoctor')?.style.display`);
    console.log(`5. Click Draft Email -> modal display: ${emailModalDisplay}`);
    await saveScreenshot('D:/veepoo/screenshot_email_modal.png');
    await evalCode(`closeDraftEmailModal()`);

    // 6. Test Watch Probe & Audit %
    await evalCode(`switchTab('watchfaces')`);
    await new Promise(r => setTimeout(r, 300));
    let probeInitial = await evalCode(`document.getElementById('lblProbePct')?.textContent`);
    console.log(`6. Watch page -> initial probe pct: ${probeInitial}`);
    await evalCode(`runWatchCapabilityAudit()`);
    // Wait for the staged probe animation (4 * 250ms = 1000ms + margin)
    await new Promise(r => setTimeout(r, 1500));
    let probeFinal = await evalCode(`document.getElementById('lblProbePct')?.textContent`);
    let badgeText = await evalCode(`document.getElementById('badgeAuditStatus')?.textContent`);
    console.log(`   - Probe pct after audit: ${probeFinal}`);
    console.log(`   - Audit badge text: ${badgeText}`);

    // Check Part 4 15 functions matrix
    let matrix15Count = await evalCode(`document.querySelectorAll('#tblWatchHealthFunctions tbody tr').length`);
    console.log(`   - Part 4 Health Dial Functions rows count: ${matrix15Count}`);

    ws.close();
  } finally {
    chrome.kill();
  }
}

run().catch(err => {
  console.error("Test failed:", err);
  process.exit(1);
});
