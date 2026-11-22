with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'daily_summary|meal/recent', text)]
for m in matches:
    print(text[m-50:m+400])
    print('='*50)
