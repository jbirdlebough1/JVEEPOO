with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

for term in ['CLINICAL MENTORSHIP', 'AIR-GAPPED PRIVACY SHIELD', 'quick nav', 'quick-nav', 'quickNav', 'quick_nav']:
    for m in re.finditer(re.escape(term), text, re.IGNORECASE):
        print(f"'{term}' found at {m.start()}:")
        print(repr(text[m.start()-100:m.end()+150]))
        print("-" * 50)
