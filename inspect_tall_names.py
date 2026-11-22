with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'renderTall[A-Za-z0-9_]*', text)]
for p in matches:
    print(text[p:p+60])
