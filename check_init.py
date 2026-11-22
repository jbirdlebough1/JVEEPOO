with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

# Search switchTab
pos = html.find('function switchTab')
if pos != -1:
    print("=== switchTab ===")
    print(html[pos:pos+2500])

# Search DOMContentLoaded or onload
print("=== DOMContentLoaded / onload ===")
for m in re.finditer(r'(?:DOMContentLoaded|window\.onload|window\.addEventListener\([\'"]load[\'"])', html):
    pos = m.start()
    print(html[pos-50:pos+300])
    print("---")
