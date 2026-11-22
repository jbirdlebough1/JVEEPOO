with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
m = re.search(r'async function executeFullWatchConnectSync\s*\(', text)
if m:
    start = m.start()
    print(text[start:start+3500])
