with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

for selector in ['.diet-container', '.diet-form-card', '.lcars-workspace', '.lcars-layout', '.lcars-nav']:
    matches = re.findall(rf'({re.escape(selector)}\b[^{{]*\{{[^}}]*\}})', html)
    print(f"=== {selector} ===")
    for m in matches:
        print(m)
