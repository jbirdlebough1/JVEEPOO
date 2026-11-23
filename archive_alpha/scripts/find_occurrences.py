with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

for pattern in [r'clinical\s+mentor\w*', r'air[- ]*gap\w*']:
    print(f"=== Pattern: {pattern} ===")
    for m in re.finditer(pattern, text, re.IGNORECASE):
        # find preceding page id
        before = text[:m.start()]
        page_m = list(re.finditer(r'id="(page\w+)"', before))
        page_name = page_m[-1].group(1) if page_m else "UNKNOWN"
        line_no = before.count('\n') + 1
        snippet = text[max(0, m.start()-60):min(len(text), m.end()+60)].replace('\n', ' ')
        print(f"L{line_no} [{page_name}]: {snippet}")
