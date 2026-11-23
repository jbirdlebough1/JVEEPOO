with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    py = f.read()

import re

for m in re.finditer(r'INSERT.*popular_meals', py, re.I):
    pos = m.start()
    print(py[pos-50:pos+300])
    print("="*40)
