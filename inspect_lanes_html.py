with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.finditer(r'repChartGlucoseTitle', text)
for m in matches:
    start = max(0, m.start() - 100)
    end = min(len(text), m.end() + 500)
    print(text[start:end])
    print("===")
