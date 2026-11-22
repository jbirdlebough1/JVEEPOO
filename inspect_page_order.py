with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re
matches = re.finditer(r'id="(page[A-Za-z0-9]+)"', html)
print('Pages in order of appearance in index.html:')
for m in matches:
    print(f'  pos={m.start()}: {m.group(1)}')
