import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

script_idx = text.find('<script>')
html_text = text[:script_idx]
lines = html_text.splitlines()

stack = []

for line_idx, line in enumerate(lines):
    line_num = line_idx + 1
    clean_line = re.sub(r'<!--.*?-->', '', line)
    
    tokens = re.finditer(r'(<div\b[^>]*>|</div>)', clean_line)
    for tok in tokens:
        tag = tok.group(0)
        if tag.startswith('<div'):
            m_id = re.search(r'id=["\']([^"\']+)["\']', tag)
            desc = m_id.group(1) if m_id else tag[:30]
            stack.append((line_num, desc))
        else:
            if not stack:
                print(f"Stack UNDERFLOW (extra </div>) at line {line_num}: {line.strip()}")
            else:
                stack.pop()
        
        # When stack size is 1, it means only lcars-frame is open!
        # If stack size becomes 0, lcars-frame was just closed!
        if len(stack) == 0 and line_num < 7685:
            print(f"lcars-frame premature closure at line {line_num}: {line.strip()}")
