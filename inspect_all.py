import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

print("File length:", len(html))

# 1. WATCH SYNC WARNING
print("\n=== WATCH SYNC WARNING ===")
for m in re.finditer(r'WATCH SYNC WARNING', html, re.I):
    start = max(0, m.start() - 150)
    end = min(len(html), m.end() + 150)
    print(html[start:end])

# 2. pageFullScan
print("\n=== pageFullScan ===")
for m in re.finditer(r'id=["\']pageFullScan["\']', html):
    start = max(0, m.start() - 200)
    end = min(len(html), m.end() + 300)
    print(html[start:end])

# 3. CSS for tab-page and lcars-workspace
print("\n=== CSS rules ===")
for rule in re.findall(r'(\.tab-page\b[^{]*\{[^}]*\})', html):
    print(rule)
for rule in re.findall(r'(\.active-page\b[^{]*\{[^}]*\})', html):
    print(rule)
for rule in re.findall(r'(\.lcars-workspace\b[^{]*\{[^}]*\})', html):
    print(rule)
for rule in re.findall(r'(\.theme-light\b[^{]*\{[^}]*\})', html):
    print(rule)
