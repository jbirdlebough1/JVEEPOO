with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('let lightHours = (')
print(text[idx:idx+2500])
