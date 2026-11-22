with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re
matches = [m.start() for m in re.finditer(r'veepoo_user_config', html)]
pos = matches[2]
print(html[pos-200:pos+1200])
