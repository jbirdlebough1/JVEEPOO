with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

for m in re.finditer(r'id=["\']dietHistoryList["\']', html):
    pos = m.start()
    line_no = html.count('\n', 0, pos) + 1
    print(f"Line {line_no}: pos {pos}")
