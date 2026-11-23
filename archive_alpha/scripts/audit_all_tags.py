import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

stack = [] # (tag_name, line_num, line_str)

errors = []

for line_idx, line in enumerate(lines):
    line_num = line_idx + 1
    # find all tags (open or close)
    # ignore void tags: <br>, <hr>, <img>, <input>, <meta>, <link>
    # Note: <script>, <style> should be handled or avoided
    tokens = re.finditer(r'<(/)?([a-zA-Z0-9\-]+)([^>]*)>', line)
    for tok in tokens:
        is_close = bool(tok.group(1))
        tag = tok.group(2).lower()
        rest = tok.group(3)
        
        # Check self-closing
        if rest.strip().endswith('/'):
            continue
        if tag in ['br', 'hr', 'img', 'input', 'meta', 'link', 'col', 'base']:
            continue
        if tag in ['script', 'style']:
            # we know scripts and styles might have </script>
            continue
            
        if not is_close:
            stack.append((tag, line_num, line.strip()[:60]))
        else:
            if not stack:
                errors.append(f"Line {line_num}: extra closing </{tag}> with empty stack!")
            else:
                top_tag, top_line, top_str = stack[-1]
                if top_tag == tag:
                    stack.pop()
                else:
                    errors.append(f"Line {line_num}: mismatched </{tag}>, expected </{top_tag}> from L{top_line} ({top_str})")

print(f"Total unclosed tags in stack: {len(stack)}")
for item in stack[-15:]:
    print("  Unclosed:", item)

print(f"\nTotal mismatch/errors: {len(errors)}")
for e in errors[:20]:
    print(" ", e)
