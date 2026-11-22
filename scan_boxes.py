with open('index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(2760, 3250):
    line = lines[i]
    if 'chart-box' in line or 'svg' in line or 'canvas' in line or 'TIMELINE' in line or 'REGIMEN' in line or 'id=' in line:
        print(f'{i+1}: {line.strip()[:100]}')
