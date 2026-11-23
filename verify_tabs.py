# verify_tabs.py
import re
from bs4 import BeautifulSoup

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# Let's find all tab page divs and their line numbers
lines = html.splitlines()
tab_pages = []
for i, line in enumerate(lines, 1):
    m = re.search(r'<div\s+id=[\'"](page[^\'"]+)[\'"]', line)
    if m:
        tab_pages.append((i, m.group(1), line.strip()[:100]))

print("Tab page divs found:")
for ln, pid, txt in tab_pages:
    print(f"L{ln:5}: id={pid:25} | {txt}")

# Check switchTab function definition in html
switch_tab_matches = [i for i, line in enumerate(lines, 1) if 'function switchTab(' in line]
print(f"\nswitchTab found at lines: {switch_tab_matches}")
if switch_tab_matches:
    start = switch_tab_matches[0]
    print("\n--- switchTab implementation ---")
    for j in range(start-1, min(start+60, len(lines))):
        print(f"L{j+1:5}: {lines[j]}")
