with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if '<body' in l:
        for j in range(i, min(len(lines), i+40)):
            print(f"{j+1}: {lines[j]}", end='')
        break
