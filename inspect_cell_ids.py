with open('D:/veepoo/w11_htm_app/cell.html', 'r', encoding='utf-8') as f:
    cell_html = f.read()

import re
print("cell.html IDs:", re.findall(r'id=["\']([^"\']*)["\']', cell_html))
