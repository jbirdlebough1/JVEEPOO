with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'(?:btnTall|btnReport|timeframe|Timeframe)', text)]
for m in matches[:25]:
    line_no = text[:m].count('\n') + 1
    lines_arr = text.splitlines()
    print(f'Line {line_no}: {lines_arr[line_no-1].strip()[:100]}')
