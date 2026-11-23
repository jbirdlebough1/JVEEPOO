# find_unclosed_div.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

import re

stack = []
for i, l in enumerate(lines, 1):
    tokens = re.finditer(r'(<div\b[^>]*>|</div>)', l, re.I)
    for m in tokens:
        tag = m.group(1)
        if tag.startswith('</'):
            if stack:
                stack.pop()
            else:
                print(f"L{i}: Extra close </div>")
        else:
            stack.append((i, tag[:40]))

print(f"\nTotal unclosed divs at EOF: {len(stack)}")
for ln, txt in stack:
    print(f"  L{ln:5}: {txt}")
