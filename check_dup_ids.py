with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re
from collections import Counter

# Find all id="..."
ids = re.findall(r'\bid=[\"\']([^\"\']+)[\"\']', html)
counts = Counter(ids)
dups = {k: v for k, v in counts.items() if v > 1}

print(f"Total IDs: {len(ids)}, Unique IDs: {len(counts)}")
print(f"Duplicate IDs ({len(dups)}):")
for k, v in sorted(dups.items(), key=lambda x: -x[1]):
    print(f"  {k}: {v} times")
