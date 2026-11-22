with open(r'D:\veepoo\w11_htm_app\index.html', encoding='utf-8') as f:
    for i, line in enumerate(f):
        if 'id="page' in line:
            print(f'Line {i+1}: {line.strip()[:60]}')
