# find_all_sec05.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [(i, l.strip()[:100]) for i, l in enumerate(text.splitlines(), 1) if 'SPECIALIZED CARDIOMETABOLIC' in l.upper()]
print("Occurrences of SPECIALIZED CARDIOMETABOLIC in index.html:")
for ln, txt in matches:
    print(f"  L{ln:5}: {txt}")
