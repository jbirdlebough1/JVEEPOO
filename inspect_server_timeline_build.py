with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(2050, 2180):
    if i < len(lines):
        print(f'{i+1}: {lines[i]}', end='')
