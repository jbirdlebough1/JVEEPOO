with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

for m in re.finditer(r'function\s+(executeScanStage[A-Za-z0-9_]*|finalizeScanStage[A-Za-z0-9_]*|finalize[A-Za-z0-9_]*Report[A-Za-z0-9_]*)\b', text):
    print(m.group(0))
