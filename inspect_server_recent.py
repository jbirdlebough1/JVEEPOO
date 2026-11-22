with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
m = re.search(r'def get_recent_meals|/api/meal/recent', text)
if m:
    start = m.start()
    print(text[start:start+1500])
