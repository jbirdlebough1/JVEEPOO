with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

for m in re.finditer(r'<div[^>]*id="(page\w+)"', text):
    print(f"{m.group(1)} at {m.start()}")
