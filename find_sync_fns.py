with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'executeFullWatchConnectSync|execute15MinWatchDataDump|triggerWatchSyncNow', text)]
for m in matches:
    start = max(0, text.rfind('\n', 0, m - 50))
    end = min(len(text), text.find('\n', m + 800))
    print("--- MATCH ---")
    print(text[start:end])
