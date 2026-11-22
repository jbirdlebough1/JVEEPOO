with open("index.html", "r", encoding="utf-8") as f:
    lines = f.readlines()

import re
stack = []
for i in range(5414, 6048):
    line = lines[i]
    line_num = i + 1
    tags = re.findall(r'(<div[^>]*>|<!--.*?-->|</div>)', line)
    for tag in tags:
        if tag.startswith('<!--'):
            continue
        if tag.startswith('</'):
            if stack:
                stack.pop()
        else:
            stack.append((tag, line_num))

print("Unclosed divs at line 6048:")
for s in stack:
    print(f"Line {s[1]}: {s[0][:70]}")
