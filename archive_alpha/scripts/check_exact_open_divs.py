import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Let's check open divs right before productsGrid
lines = text.splitlines()
open_divs = []
for i in range(5711, 6576):
    line = lines[i]
    clean_line = re.sub(r'<!--.*?-->', '', line)
    tokens = re.finditer(r'(<div\b[^>]*>|</div>)', clean_line)
    for tok in tokens:
        tag = tok.group(0)
        if tag.startswith('<div'):
            m_id = re.search(r'id=["\']([^"\']+)["\']', tag)
            desc = m_id.group(1) if m_id else tag[:35]
            open_divs.append((i+1, desc))
        else:
            if open_divs:
                open_divs.pop()

print("Open divs right after line 6575 (closing products section):")
for item in open_divs:
    print(" ", item)
