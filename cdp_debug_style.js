// cdp_debug_style.js
const http = require('http');

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
  const list = await getJson('http://127.0.0.1:9258/json').catch(() => null);
  if (!list) {
    console.log("No running Chrome on 9258, starting fresh...");
    const { spawn } = require('child_process');
    const chrome = spawn("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", [
      '--headless=new',
      '--remote-debugging-port=9258',
      '--window-size=1600,1000',
      '--disable-gpu',
      'http://localhost:8899/index.html'
    ]);
    await new Promise(r => setTimeout(r, 2000));
  }

  const list2 = await getJson('http://127.0.0.1:9258/json');
  const pageTab = list2.find(t => t.type === 'page');
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

  // Switch to Medication
  await evalCode(`switchTab('medication')`);
  const medDisplay = await evalCode(`window.getComputedStyle(document.getElementById('pageMedication')).display`);
  const opsDisplay = await evalCode(`window.getComputedStyle(document.getElementById('pageOperations')).display`);
  const helpDisplay = await evalCode(`window.getComputedStyle(document.getElementById('pageHelp')).display`);

  console.log(`pageMedication display: ${medDisplay}`);
  console.log(`pageOperations display: ${opsDisplay}`);
  console.log(`pageHelp display: ${helpDisplay}`);

  // Switch to Clinical Goals
  await evalCode(`switchTab('goals')`);
  const goalsDisplay = await evalCode(`window.getComputedStyle(document.getElementById('pageGoals')).display`);
  const medDisplay2 = await evalCode(`window.getComputedStyle(document.getElementById('pageMedication')).display`);
  console.log(`pageGoals display: ${goalsDisplay}`);
  console.log(`pageMedication display after switch: ${medDisplay2}`);

  ws.close();
  process.exit(0);
}
run();
