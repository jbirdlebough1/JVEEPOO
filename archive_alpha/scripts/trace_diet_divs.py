with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

# Track which divs are opened and closed from line 5712 (pageDietTrades)
import re

open_divs = []
for i in range(5711, 6610):
    line = lines[i]
    clean_line = re.sub(r'<!--.*?-->', '', line)
    tokens = re.finditer(r'(<div\b[^>]*>|</div>)', clean_line)
    for tok in tokens:
        tag = tok.group(0)
        if tag.startswith('<div'):
            m_id = re.search(r'id=["\']([^"\']+)["\']', tag)
            desc = m_id.group(1) if m_id else tag[:35]
            open_divs.append((i+1, desc))
            print(f"L{i+1}: OPEN  {desc} (total open: {len(open_divs)})")
        else:
            if open_divs:
                closed = open_divs.pop()
                print(f"L{i+1}: CLOSE {closed[1]} (remaining open: {len(open_divs)})")
            else:
                print(f"L{i+1}: EXTRA CLOSE </div> (open is EMPTY!)")
