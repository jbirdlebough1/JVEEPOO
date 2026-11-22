with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

import re

stack = []
for i in range(5711, 6606):
    line = lines[i]
    # find tokens in line
    tokens = re.finditer(r'(<div[^>]*>|</div>)', line)
    for tok in tokens:
        tag = tok.group(0)
        if tag.startswith('<div'):
            # grab class or id if any
            m_id = re.search(r'id=[\'"]([^\'"]+)[\'"]', tag)
            m_cl = re.search(r'class=[\'"]([^\'"]+)[\'"]', tag)
            desc = (m_id.group(1) if m_id else '') + (' ' + m_cl.group(1) if m_cl else '')
            stack.append((i+1, tag[:40], desc))
        else:
            if stack:
                stack.pop()
            else:
                print(f"EXTRA </div> at line {i+1}!")

print(f"Stack size at end of pageDietTrades: {len(stack)}")
for item in stack:
    print("Unclosed div:", item)
