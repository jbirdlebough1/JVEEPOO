with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

# Check executeFullWatchConnectSync
pos_sync = html.find('executeFullWatchConnectSync')
pos_sound = html.find('function soundSuccess')
print('executeFullWatchConnectSync at:', pos_sync)
print('function soundSuccess at:', pos_sound)

# Look at lines around executeFullWatchConnectSync
if pos_sync != -1:
    print("\n--- executeFullWatchConnectSync excerpt ---")
    start = max(0, pos_sync - 200)
    end = min(len(html), pos_sync + 1500)
    print(html[start:end])

# Also check where soundSuccess is called in executeFullWatchConnectSync:
m = re.search(r'executeFullWatchConnectSync[\s\S]*?(?=function\b|\Z)', html)
if m:
    block = m.group(0)[:3000]
    print("\n--- soundSuccess in executeFullWatchConnectSync ---")
    for sm in re.finditer(r'soundSuccess', block):
        s = max(0, sm.start() - 50)
        e = min(len(block), sm.end() + 50)
        print(block[s:e])
