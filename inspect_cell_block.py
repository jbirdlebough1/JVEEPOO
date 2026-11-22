with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = 328409
print(text[pos-200:pos+1500])
