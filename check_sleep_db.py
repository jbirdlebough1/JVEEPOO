with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    py = f.read()

import re

for m in re.finditer(r"'SLEEP'|\"SLEEP\"|sleep_records", py):
    pos = m.start()
    print(py[pos-50:pos+150].replace('\n', ' '))
    print("---")
