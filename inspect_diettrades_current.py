with open(r'D:\veepoo\w11_htm_app\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos1 = text.find('<div id="pageDietTrades"')
pos2 = text.find('<!-- Component 0B: Impulse Food Helper', pos1)
print(text[pos1:pos2])
