with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for j in range(7520, min(len(lines), 7570)):
    print(f"{j+1}: {lines[j]}", end='')
