with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re
matches = [m.start() for m in re.finditer(r'dietHistoryList', html)]
print('dietHistoryList matches:', len(matches))
for pos in matches:
    print('---')
    print(html[pos-100:pos+200])
