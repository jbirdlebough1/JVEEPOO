with open('presentation/preview.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(3040, 3180):
    if i < len(lines):
        print(f'{i+1}: {lines[i]}', end='')
