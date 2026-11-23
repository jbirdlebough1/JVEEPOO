with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'fetch\([\'\"][^\'\"]+[\'\"]\)', text)
print("All fetch endpoints in index.html:")
for m in sorted(list(set(matches))):
    print("  ", m)
