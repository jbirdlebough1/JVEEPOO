with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
for m in re.finditer(r'\.lcars-frame[^{]*\{[^}]*\}', text):
    print(m.group(0))
