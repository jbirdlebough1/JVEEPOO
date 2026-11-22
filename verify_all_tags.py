with open("index.html", "r", encoding="utf-8") as f:
    lines = f.readlines()

import re
stack = []
for i, line in enumerate(lines):
    line_num = i + 1
    tags = re.findall(r'(<(?:div|main)[^>]*>|<!--.*?-->|</(?:div|main)>)', line)
    for tag in tags:
        if tag.startswith('<!--'):
            continue
        if tag.startswith('</'):
            tag_name = tag[2:-1].strip()
            if stack:
                last_tag, last_line, last_src = stack.pop()
                if last_tag != tag_name:
                    print(f"Mismatch at line {line_num}: closed </{tag_name}>, but opened <{last_tag}> at line {last_line}")
            else:
                print(f"Extra closing </{tag_name}> at line {line_num}")
        else:
            tag_name = 'main' if tag.startswith('<main') else 'div'
            stack.append((tag_name, line_num, tag))

print("Unclosed elements at EOF:")
for s in stack:
    print(f"  Line {s[1]}: <{s[0]}> {s[2][:60]}")
