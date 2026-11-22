with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

for i in range(3310, 3440):
    print(f"{i+1:5}: {lines[i]}")
