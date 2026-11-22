with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()
for i in range(85, 115):
    print(f'{i+1}: {repr(lines[i])}')
