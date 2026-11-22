with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

for i in range(3320, 3540):
    line = lines[i]
    if '<span' in line or '<div style="font-size: 1.25rem' in line or 'badge' in line:
        print(f"{i+1:5}: {line.strip()[:100]}")
