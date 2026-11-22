with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

for m in re.finditer(r'quick', text, re.IGNORECASE):
    print(repr(text[m.start()-40:m.end()+60]))
