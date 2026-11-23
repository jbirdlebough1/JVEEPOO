with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

for m in re.finditer(r'repChartSleep|repSleep|valSleep', html):
    pos = m.start()
    print(html[pos-40:pos+120].replace('\n', ' '))
    print("---")
