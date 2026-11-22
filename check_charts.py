with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

pos = html.find('if (isDaily) {', 400000)
if pos != -1:
    print(html[pos:pos+4000])
