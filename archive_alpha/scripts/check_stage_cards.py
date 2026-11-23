with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(1780, 1835):
    if i < len(lines):
        print(f"{i+1}: {lines[i]}", end='')
