with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    py = f.read()

import re

print("=== Sleep in server.py ===")
for m in re.finditer(r'sleep', py, re.I):
    pos = m.start()
    print(py[pos-40:pos+100].replace('\n', ' '))

print("\n=== Sleep queries in server.py ===")
for m in re.finditer(r'SELECT.*sleep', py, re.I):
    pos = m.start()
    print(py[pos:pos+200].replace('\n', ' '))
