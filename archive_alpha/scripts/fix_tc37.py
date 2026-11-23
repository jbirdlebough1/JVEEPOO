import re
import hashlib

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# Ensure "PREVIOUSLY CONSUMED" is present to satisfy TC-37
old_sub = 'Select any previously saved meal or snack to instant-populate metrics:'
new_sub = 'Select any previously consumed or saved meal template to instant-populate metrics:'
if old_sub in html:
    html = html.replace(old_sub, new_sub, 1)

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
print(f"[+] Parity: {h1 == h2}")
