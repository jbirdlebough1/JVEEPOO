with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("=== /api/meal routes in server.py ===")
for m in re.finditer(r'([^\n]*/api/meal[^\n]*)', text):
    print("  ", m.group(1).strip()[:100])
