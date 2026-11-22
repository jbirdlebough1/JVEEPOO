with open(r'D:\veepoo\w11_htm_app\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('function initUnifiedGlucoseRing')
print(text[pos:pos+1600])
