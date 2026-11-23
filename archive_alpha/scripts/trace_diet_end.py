# trace_diet_end.py
import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

stack = []
for ln in range(5909, 6806):
    line = lines[ln - 1]
    tokens = re.finditer(r'(<div[^>]*>|</div>)', line, re.I)
    for m in tokens:
        tag = m.group(1)
        if tag.startswith('</'):
            if stack:
                stack.pop()
            else:
                print(f"L{ln}: EXTRA CLOSE </div>")
        else:
            stack.append((ln, tag[:40]))
    if ln >= 6765:
        print(f"L{ln:5}: depth={len(stack)} | {line.strip()[:60]}")
