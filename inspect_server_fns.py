with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
m = re.search(r'def get_recent_meals', text)
if m:
    start = m.start()
    print("--- get_recent_meals ---")
    print(text[start:start+1200])

m2 = re.search(r'elif self\.path\.startswith\(\"/api/meal/daily_summary\"\):', text)
if m2:
    start = m2.start()
    print("--- daily_summary route ---")
    print(text[start:start+2500])
