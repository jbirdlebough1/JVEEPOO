with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('boxHybridChronoStudio')
print(text[idx-1000:idx])
