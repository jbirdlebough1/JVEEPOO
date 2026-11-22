with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

for m in re.finditer(r'\bswitchTab\([\'"][^\'"]+[\'"]\)', html):
    start = max(0, m.start() - 60)
    end = min(len(html), m.end() + 60)
    print(f"At {m.start()}: {html[start:end]}")
