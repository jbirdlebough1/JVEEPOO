with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

for i, line in enumerate(lines):
    if '<!-- Diet Lane Badges -->' in line or 'p.meal' in line or 'lane.id === "diet"' in line:
        print(f"L{i+1}: {line}")
        for j in range(max(0, i-5), min(i+45, len(lines))):
            print(f"  {j+1}: {lines[j]}")
        break
