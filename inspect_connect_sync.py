with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
m = re.search(r'async function executeFullWatchConnectSync\b', text)
if m:
    idx = m.start()
    print('--- executeFullWatchConnectSync ---')
    print(text[idx:idx+2500])
else:
    print('Not found')
