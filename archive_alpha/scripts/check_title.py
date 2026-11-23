with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

m = re.search(r'<title>([^<]+)</title>', html)
if m:
    print("Title in index.html:", m.group(1))

with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    html2 = f.read()
m2 = re.search(r'<title>([^<]+)</title>', html2)
if m2:
    print("Title in w11_htm_app/index.html:", m2.group(1))
