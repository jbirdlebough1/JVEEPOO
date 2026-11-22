with open('D:/veepoo/index.html', 'rb') as f1, open('D:/veepoo/w11_htm_app/index.html', 'rb') as f2:
    b1 = f1.read()
    b2 = f2.read()

print('index.html size:', len(b1))
print('w11_htm_app/index.html size:', len(b2))
print('Exact match?', b1 == b2)

if b1 != b2:
    with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
        html2 = f.read()
    print('soundSuccess in w11_htm_app/index.html:', 'soundSuccess' in html2)
    pos2 = html2.find('soundSuccess')
    print('pos:', pos2)
