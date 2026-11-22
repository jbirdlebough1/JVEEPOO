with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'CREATE TABLE', text)]
for p in matches:
    print(text[p:p+300])
    print("---")
