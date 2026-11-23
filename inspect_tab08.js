// inspect_tab08.js
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
  const list = await getJson('http://127.0.0.1:9258/json');
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

  const info = await evalCode(`(() => {
    const el = document.getElementById('pageMedication');
    if (!el) return { error: 'pageMedication not found' };
    const rect = el.getBoundingClientRect();
    const style = window.getComputedStyle(el);
    const parent = el.parentElement;
    const parentRect = parent ? parent.getBoundingClientRect() : null;
    return {
      id: el.id,
      className: el.className,
      rect: { top: rect.top, left: rect.left, width: rect.width, height: rect.height },
      style: { display: style.display, visibility: style.visibility, opacity: style.opacity, color: style.color, background: style.background },
      parentTag: parent ? parent.tagName : null,
      parentId: parent ? parent.id : null,
      parentClass: parent ? parent.className : null,
      parentRect: parentRect ? { top: parentRect.top, left: parentRect.left, width: parentRect.width, height: parentRect.height } : null,
      childrenCount: el.children.length,
      innerHTMLHead: el.innerHTML.slice(0, 300)
    };
  })()`);

  console.log("pageMedication DOM info:", JSON.stringify(info, null, 2));
  ws.close();
}
run();
