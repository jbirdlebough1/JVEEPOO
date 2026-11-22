with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re
matches = re.finditer(r'veepoo_config', html)
for m in matches:
    pos = m.start()
    print('Match at', pos, ':\n', html[pos-50:pos+300])
