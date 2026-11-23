with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

pos = html.find("window.addEventListener('load'")
while pos != -1:
    print(f"window.addEventListener('load') at {pos}:")
    print(html[pos:pos+600])
    print("="*40)
    pos = html.find("window.addEventListener('load'", pos + 30)
