import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Only analyze HTML portion before <script>
script_idx = text.find('<script>')
html_text = text[:script_idx]
lines = html_text.splitlines()

stack = [] # (line_num, line_str)

for line_idx, line in enumerate(lines):
    line_num = line_idx + 1
    # Find all <div ...> and </div>
    # Careful not to match inside comments or strings
    # remove HTML comments first
    clean_line = re.sub(r'<!--.*?-->', '', line)
    
    tokens = re.finditer(r'(<div\b[^>]*>|</div>)', clean_line)
    for tok in tokens:
        tag = tok.group(0)
        if tag.startswith('<div'):
            # get id or class
            m_id = re.search(r'id=["\']([^"\']+)["\']', tag)
            desc = m_id.group(1) if m_id else tag[:30]
            stack.append((line_num, desc))
        else:
            if not stack:
                print(f"EXTRA </div> at line {line_num}: {line.strip()}")
            else:
                stack.pop()

print(f"\nRemaining unclosed divs in HTML: {len(stack)}")
for item in stack:
    print("  Unclosed div:", item)
