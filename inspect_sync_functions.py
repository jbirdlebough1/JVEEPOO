with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# find executeFullWatchSync
m = re.search(r'async function executeFullWatchSync\b', text)
if m:
    idx = m.start()
    print('--- executeFullWatchSync ---')
    print(text[idx:idx+2500])

# find execute15MinWatchDataDump
m2 = re.search(r'async function execute15MinWatchDataDump\b', text)
if m2:
    idx2 = m2.start()
    print('\n--- execute15MinWatchDataDump ---')
    print(text[idx2:idx2+1500])
