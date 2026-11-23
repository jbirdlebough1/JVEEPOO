with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.finditer(r'boxSynchronizedTallChart', text)
for m in matches:
    start = max(0, m.start() - 20)
    end = min(len(text), m.end() + 100)
    print(text[start:end])
    print("---")
