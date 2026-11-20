with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'.{0,30}onTallScrubberMoved.{0,40}', text)
for m in matches:
    print(m)
