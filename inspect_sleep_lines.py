with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(17320, 17380):
    print(f"{i+1}: {lines[i]}", end='')
