with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'"""', text)]
print("Triple quotes count:", len(matches))
for i, p in enumerate(matches):
    line_num = text[:p].count('\n') + 1
    print(f"  {i+1} at line {line_num}: {repr(text[p:p+30])}")
