// inspect_tabs_complete.js
const { spawn } = require('child_process');
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
  const chrome = spawn("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", [
    '--headless=new',
    '--remote-debugging-port=9260',
    '--window-size=1600,1000',
    '--disable-gpu',
    'http://localhost:8899/index.html'
  ]);

  await new Promise(r => setTimeout(r, 2000));

  try {
    const list = await getJson('http://127.0.0.1:9260/json');
    const pageTab = list.find(t => t.type === 'page');
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

    await evalCode(`switchTab('medication')`);
    const infoMed = await evalCode(`(() => {
      const el = document.getElementById('pageMedication');
      if (!el) return { error: 'pageMedication not found' };
      const rect = el.getBoundingClientRect();
      const style = window.getComputedStyle(el);
      const parent = el.parentElement;
      return {
        id: el.id,
        className: el.className,
        rect: { top: rect.top, left: rect.left, width: rect.width, height: rect.height },
        style: { display: style.display, visibility: style.visibility, opacity: style.opacity },
        parentTag: parent ? parent.tagName : null,
        parentId: parent ? parent.id : null,
        parentClass: parent ? parent.className : null,
        parentDisplay: parent ? window.getComputedStyle(parent).display : null,
        childrenCount: el.children.length,
        child0Tag: el.children[0]?.tagName,
        child0Rect: el.children[0]?.getBoundingClientRect()
      };
    })()`);
    console.log("pageMedication info:", JSON.stringify(infoMed, null, 2));

    await evalCode(`switchTab('goals')`);
    const infoGoals = await evalCode(`(() => {
      const el = document.getElementById('pageGoals');
      if (!el) return { error: 'pageGoals not found' };
      const rect = el.getBoundingClientRect();
      return {
        id: el.id,
        rect: { top: rect.top, left: rect.left, width: rect.width, height: rect.height },
        childrenCount: el.children.length,
        child0Tag: el.children[0]?.tagName,
        child0Rect: el.children[0]?.getBoundingClientRect()
      };
    })()`);
    console.log("pageGoals info:", JSON.stringify(infoGoals, null, 2));

    ws.close();
  } finally {
    chrome.kill();
  }
}
run();
