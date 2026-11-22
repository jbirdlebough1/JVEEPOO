with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
pos = text.find('function switchTab')
if pos != -1:
    print(text[pos:pos+1500])
else:
    print("switchTab not found")
