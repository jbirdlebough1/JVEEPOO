with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re
m = re.search(r'Loading Setup parameters', html)
if m:
    pos = m.start()
    print(html[pos-100:pos+1500])
