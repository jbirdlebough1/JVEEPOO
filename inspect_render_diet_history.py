with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function renderDietHistory()')
print(text[idx:idx+2500])
