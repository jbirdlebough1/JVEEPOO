# check_operations_sections.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

print("Inside #pageOperations (lines 4875 - 5455):")
for l in range(4875, 5455):
    line = lines[l-1]
    if 'SECTION' in line.upper() or '01.' in line or '02.' in line or '03.' in line or '04.' in line or '05.' in line:
        if any(tag in line for tag in ['<div', '<h', '<span', '<strong']):
            print(f"L{l:5}: {line.strip()[:100]}")
