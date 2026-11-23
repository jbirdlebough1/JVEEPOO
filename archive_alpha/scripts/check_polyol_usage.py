with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re

for m in re.finditer(r'has_sneaky_polyol', text):
    print("Match at", m.start())
    print(text[m.start()-50:m.end()+300])
