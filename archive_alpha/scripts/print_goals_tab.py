with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

for i in range(7453, 7550):
    print(f"{i+1:5}: {lines[i]}")
