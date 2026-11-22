with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
pos = text.find('class="lcars-sidebar')
if pos != -1:
    print(text[pos:pos+2000])
else:
    print("lcars-sidebar not found in HTML body")
    # search for sidebar in body
    body_pos = text.find('<body')
    print("Body start:", text[body_pos:body_pos+1500])
