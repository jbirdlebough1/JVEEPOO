with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# Find patterns like document.getElementById("...").property
pattern = r'document\.getElementById\([\'\"]([^\'\"]+)[\'\"]\)\.([A-Za-z0-9_]+)'
matches = re.finditer(pattern, text)

missing = []
for m in matches:
    elem_id = m.group(1)
    prop = m.group(2)
    # check if elem_id exists in HTML
    if f'id="{elem_id}"' not in text and f"id='{elem_id}'" not in text:
        line = text[:m.start()].count('\n') + 1
        missing.append((line, elem_id, prop, m.group(0)))

print(f'Total unguarded calls to missing element IDs: {len(missing)}')
for line, elem_id, prop, code in missing[25:]:
    print(f'  L{line}: ID "{elem_id}" -> {code}')
