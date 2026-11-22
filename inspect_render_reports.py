with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

for i, line in enumerate(lines):
    if 'function renderReportsView' in line:
        print(f"renderReportsView at line {i+1}:")
        for j in range(i, min(i+90, len(lines))):
            print(f"  {j+1}: {lines[j]}")
        break
