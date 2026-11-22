with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(2590, 2640):
    print(f"  {i+1}: {lines[i]}", end='')
