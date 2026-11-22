with open('index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(16116, 16396):
    line = lines[i]
    if any(k in line for k in ['banana', '1b120c', '0d1420', '330b0b', 'stroke=', 'fill=', 'text x=']):
        print(f'{i+1}: {line.strip()[:100]}')
