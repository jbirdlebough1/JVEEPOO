with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("window.onload:", re.findall(r'.{0,30}window\.onload.{0,50}', text))
print("DOMContentLoaded:", re.findall(r'.{0,30}DOMContentLoaded.{0,50}', text))
