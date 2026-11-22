with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('CIRCADIAN SLEEP ARCHITECTURE')
idx2 = text.find('BAY 4:', idx)
print(text[idx-200:idx2])
