with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    s = f.read()

import re
matches = re.findall(r'/api/config[^\s\"]*', s)
print('Config routes:', matches)
