with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

print("=== 1. SEARCH FOR SUBMITTED MEALS IN INDEX.HTML ===")
for m in re.finditer(r'([^\n]*(?:submitted\s*meal|dietHistory|meal_entries|renderDietHistory)[^\n]*)', text, re.I):
    print("  ", m.group(1).strip()[:120])

print("\n=== 2. SEARCH FOR RENDER DIET HISTORY ===")
idx = text.find('function renderDietHistory')
if idx != -1:
    print(text[idx:idx+2000])

print("\n=== 3. SEARCH FOR REPORTS SUB-STRIP OR TAB 07 SECTIONS ===")
idx_rep = text.find('id="tab-reports"')
if idx_rep == -1:
    idx_rep = text.find('id="reports"')
if idx_rep != -1:
    print(text[idx_rep:idx_rep+2000])
