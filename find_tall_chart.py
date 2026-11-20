with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("Tall chart IDs:", re.findall(r'id=[\'"][^\'"]*tall[^\'"]*[\'"]', text, re.IGNORECASE))
