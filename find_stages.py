with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

for m in re.finditer(r'fullScanCurrentStage\b', html):
    pos = m.start()
    print(html[pos-50:pos+200])
    print("---")
