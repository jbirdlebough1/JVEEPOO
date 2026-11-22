with open("index.html", "r", encoding="utf-8") as f:
    lines = f.readlines()

import re
stack = []
for i in range(5414, 6100):
    line = lines[i]
    line_num = i + 1
    tags = re.findall(r'(<div[^>]*>|<!--.*?-->|</div>)', line)
    for tag in tags:
        if tag.startswith('<!--'):
            continue
        if tag.startswith('</'):
            if stack:
                popped = stack.pop()
                if not stack:
                    print(f"pageDietTrades closed at line {line_num}!")
        else:
            stack.append((tag, line_num))
            if 'id="pageDietTrades"' in tag:
                print(f"pageDietTrades started at line {line_num}")

print("At line 6100, remaining unclosed divs inside pageDietTrades:", len(stack))
for s in stack:
    print("  Line", s[1], s[0][:60])
