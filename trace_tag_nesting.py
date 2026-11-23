# trace_tag_nesting.py
import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

# We trace depth of div and main from line 1700 to line 8300
tag_stack = []

for line_no in range(1730, 8210):
    line = lines[line_no - 1]
    # find tags
    tokens = re.findall(r'(<(?:div|main)[^>]*>|</(?:div|main)>)', line, re.I)
    for t in tokens:
        if t.startswith('</'):
            tag_name = t[2:-1].lower()
            if tag_stack:
                last_tag, last_line, last_txt = tag_stack.pop()
                if last_tag != tag_name:
                    print(f"[MISMATCH at L{line_no}] Closed </{tag_name}> but expected </{last_tag}> (opened at L{last_line}: {last_txt})")
            else:
                print(f"[EXTRA CLOSE at L{line_no}] </{tag_name}> with empty stack!")
        else:
            tag_name = re.match(r'<([a-zA-Z0-9]+)', t).group(1).lower()
            tag_stack.append((tag_name, line_no, t[:40]))

print(f"\nRemaining unclosed tags at L8210: {len(tag_stack)}")
for t, l, txt in tag_stack[-15:]:
    print(f"  L{l:5}: <{t}> {txt}")
