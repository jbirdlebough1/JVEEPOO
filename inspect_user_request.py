with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

print("--- SKIP TO NEXT SEARCH ---")
for m in re.finditer(r'skip\s*to\s*next', text, re.IGNORECASE):
    s = max(0, m.start() - 100)
    e = min(len(text), m.end() + 150)
    print(text[s:e])
    print('---')

print("--- RUN ANOTHER SCAN SEARCH ---")
for m in re.finditer(r'run\s+another|another\s+scan|new\s+scan|start\s+new', text, re.IGNORECASE):
    s = max(0, m.start() - 100)
    e = min(len(text), m.end() + 150)
    print(text[s:e])
    print('---')

print("--- NAV TABS ---")
m_nav = text.find('<nav class="lcars-sidebar">')
if m_nav != -1:
    print(text[m_nav:m_nav+1500])
