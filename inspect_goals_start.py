with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'id="pageGoals"' in l:
        for j in range(i, i+15):
            print(f"{j+1}: {lines[j]}", end='')
        break
