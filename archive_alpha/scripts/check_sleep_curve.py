with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

for m in re.finditer(r'curveTimelineSleep', html):
    pos = m.start()
    print(html[pos-100:pos+800])
    print("="*40)
