with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('summaryListBreakfast')
print(text[idx-200:idx+2500])
