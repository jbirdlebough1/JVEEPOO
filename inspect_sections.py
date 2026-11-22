with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'<section[^>]*id=["\']([^"\']*)["\']', text)
print("Section IDs:", matches)
for sid in matches:
    if 'goal' in sid.lower() or '11' in sid.lower() or 'report' in sid.lower() or 'fullscan' in sid.lower():
        idx = text.find(f'id="{sid}"')
        print(f"\n--- Section id={sid} ---")
        print(text[idx:idx+500])
