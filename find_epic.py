with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.finditer(r'.{0,30}EPIC.{0,40}', text, re.IGNORECASE)
for m in matches:
    print(m.group(0))
