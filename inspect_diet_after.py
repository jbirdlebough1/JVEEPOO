with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for j in range(13960, min(len(lines), 14030)):
    print(f"{j+1}: {lines[j]}", end='')
