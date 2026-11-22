with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('if (header === 0xE0 && bytes.length >= 4)')
print(text[idx-50:idx+2500])
