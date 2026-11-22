with open(r'D:\veepoo\w11_htm_app\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(16520, min(len(lines), 16580)):
    print(f"{i+1}: {lines[i].rstrip()}")
