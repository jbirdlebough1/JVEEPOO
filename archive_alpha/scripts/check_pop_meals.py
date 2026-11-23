with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = list(re.finditer(r'POPULAR_MEALS\s*=', text))
print("POPULAR_MEALS assignments:", len(matches))
for m in matches:
    print(repr(text[m.start()-50:m.end()+150]))
