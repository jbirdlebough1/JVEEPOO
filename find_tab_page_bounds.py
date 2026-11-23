# find_tab_page_bounds.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

import re

for i, l in enumerate(lines, 1):
    m = re.search(r'id=[\'"](page[^\'"]+)[\'"]', l)
    if m:
        print(f"OPEN L{i:5}: {m.group(1)}")
    if '</main>' in l:
        print(f"CLOSE </main> L{i:5}")
    if '</body' in l:
        print(f"CLOSE </body> L{i:5}")
