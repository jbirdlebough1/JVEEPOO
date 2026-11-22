with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'soundSuccess', text)]
print("Total soundSuccess references:", len(matches))
for m in matches:
    start = max(0, text.rfind('\n', 0, m - 100))
    end = min(len(text), text.find('\n', m + 150))
    print("--- REFERENCE ---")
    print(text[start:end])
