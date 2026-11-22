with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

for m in re.finditer(r'function toggleTheme\b', html):
    pos = m.start()
    print("toggleTheme function:")
    print(html[pos:pos+500])

for m in re.finditer(r'localStorage\.getItem\([\'"]theme[\'"]\)', html):
    pos = m.start()
    print("localStorage theme:")
    print(html[pos-50:pos+300])
