with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()
for l in [8225, 8226, 8227, 8228, 8234, 8238, 8372, 8806, 8807, 8809, 8834]:
    print(f'L{l}: {lines[l-1]}')
