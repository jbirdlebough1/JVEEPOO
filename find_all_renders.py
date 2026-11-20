with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'function render\w+\([^)]*\)', text)
for m in set(matches):
    print(m)
