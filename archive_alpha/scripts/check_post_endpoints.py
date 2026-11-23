with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    py = f.read()

import re

pos = py.find('def do_POST')
if pos != -1:
    print(py[pos:pos+2500])
