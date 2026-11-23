with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("URLSearchParams:", re.findall(r'.{0,30}URLSearchParams.{0,50}', text))
