import re

with open("index.html", "r", encoding="utf-8") as f:
    html = f.read()

for match in re.finditer(r'(\.theme-light[^{]*\{[^}]*\})', html):
    print(match.group(1))

idx = html.find('function toggleTheme')
if idx != -1:
    print("\n--- toggleTheme ---")
    print(html[idx:idx+800])
