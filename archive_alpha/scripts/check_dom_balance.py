with open("index.html", "r", encoding="utf-8") as f:
    lines = f.readlines()

# Let's track open tags from <main class="lcars-workspace">
stack = []
for i in range(1701, len(lines)):
    line = lines[i]
    line_num = i + 1
    
    # Very simple tag balance check for main and divs
    # Find all <div or </div or <main or </main
    import re
    tags = re.findall(r'(<(?:div|main)[^>]*>|<!--.*?-->|</(?:div|main)>)', line)
    for tag in tags:
        if tag.startswith('<!--'):
            continue
        if tag.startswith('</'):
            tag_name = tag[2:-1].strip()
            if stack:
                last_tag, last_line = stack.pop()
                if last_tag != tag_name:
                    print(f"Mismatch at line {line_num}: closed {tag_name}, but last open was {last_tag} at line {last_line}")
                if tag_name == 'main':
                    print(f"MAIN CLOSED at line {line_num}")
            else:
                print(f"Underflow at line {line_num}: closed {tag_name} with empty stack")
        else:
            tag_name = 'main' if tag.startswith('<main') else 'div'
            stack.append((tag_name, line_num))
            if 'id="pageFullScan"' in tag:
                print(f"Found id=pageFullScan at line {line_num}. Stack depth: {len(stack)}. Inside main? {'main' in [s[0] for s in stack]}")

print("End of file. Stack remaining:", len(stack))
