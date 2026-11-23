with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("Tab switch functions:", re.findall(r'function (?:switch|show|select|nav)\w*\([^)]*\)', text, re.IGNORECASE))
