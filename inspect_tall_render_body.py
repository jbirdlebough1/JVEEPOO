with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

for i in range(16230, min(16450, len(lines))):
    print(f"{i+1:5}: {lines[i]}")
