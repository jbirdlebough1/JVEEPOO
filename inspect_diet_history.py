with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
m = re.search(r'function renderDietHistory\s*\(', text)
if m:
    start = m.start()
    print("--- renderDietHistory ---")
    print(text[start:start+4000])
