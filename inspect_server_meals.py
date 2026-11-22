with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'meal_entries', text)]
for m in matches:
    print(text[m-50:m+200])
    print('---')
