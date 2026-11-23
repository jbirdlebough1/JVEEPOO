import re

with open(r'D:\veepoo\w11_htm_app\index.html', encoding='utf-8') as f:
    lines = f.readlines()

stack = []
extra_closes = []

for idx, line in enumerate(lines, 1):
    tokens = re.findall(r'<div[^>]*>|</div>', line)
    for t in tokens:
        if t == '</div>':
            if stack:
                stack.pop()
            else:
                extra_closes.append((idx, line.strip()))
        else:
            m = re.search(r'(id|class)=["\']([^"\']+)["\']', t)
            label = f'{m.group(1)}="{m.group(2)}"' if m else t[:30]
            stack.append((idx, label))

print(f"Extra </div> tags: {len(extra_closes)}")
for item in extra_closes:
    print(f"  Extra </div> at line {item[0]}: {item[1]}")

print(f"\nUnclosed divs: {len(stack)}")
for item in stack:
    print(f"  Unclosed from line {item[0]}: {item[1]}")
