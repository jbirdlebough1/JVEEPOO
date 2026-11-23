with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'class\s+([A-Za-z0-9_]+)\((.*?)\):', text)
print("Classes in server.py:", matches)
m_get = text.find('def do_GET')
print("do_GET found at", m_get)
if m_get != -1:
    print(text[m_get:m_get+1000])
