with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
nav_buttons = re.findall(r'<button[^>]*class="[^"]*nav-btn[^"]*"[^>]*>.*?</button>', text, re.DOTALL)
print(f"Total nav buttons: {len(nav_buttons)}")
for b in nav_buttons:
    print("  ", b.strip().replace('\n', ' '))

pages = re.findall(r'<div[^>]*id="(page[A-Za-z0-9]+)"', text)
print("Pages found:", pages)
