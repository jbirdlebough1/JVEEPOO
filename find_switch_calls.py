with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if "switchTab(" in l:
        print(f"{i+1}: {l.strip()}")
