with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

for m in re.finditer(r'soundSuccess', html):
    pos = m.start()
    line_no = html.count('\n', 0, pos) + 1
    snippet = html[max(0, pos-40):min(len(html), pos+60)].replace('\n', ' ')
    print(f"Line {line_no}: {snippet}")
