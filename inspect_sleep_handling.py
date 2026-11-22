with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_py = f.read()

import re
print("=== SLEEP IN SERVER.PY ===")
for m in re.finditer(r'([^\n]*sleep[^\n]*)', server_py, re.I):
    print("  ", m.group(1).strip()[:100])

print("\n=== E0 IN SERVER.PY ===")
for m in re.finditer(r'([^\n]*0xE0[^\n]*)', server_py, re.I):
    print("  ", m.group(1).strip()[:100])

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    index_html = f.read()

print("\n=== E0 IN INDEX.HTML ===")
for m in re.finditer(r'([^\n]*0xE0[^\n]*)', index_html, re.I):
    print("  ", m.group(1).strip()[:100])
