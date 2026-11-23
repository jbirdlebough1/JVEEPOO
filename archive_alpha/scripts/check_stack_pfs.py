with open("index.html", "r", encoding="utf-8") as f:
    lines = f.readlines()

import re
stack = []
for i in range(1701, 6905):
    line = lines[i]
    line_num = i + 1
    
    tags = re.findall(r'(<(?:div|main)[^>]*>|<!--.*?-->|</(?:div|main)>)', line)
    for tag in tags:
        if tag.startswith('<!--'):
            continue
        if tag.startswith('</'):
            if stack:
                stack.pop()
        else:
            tag_name = 'main' if tag.startswith('<main') else 'div'
            stack.append((tag, line_num))
            if 'id="pageFullScan"' in tag:
                print(f"Stack at line {line_num} (pageFullScan):")
                for s in stack:
                    print("  ", s[1], s[0][:80])
