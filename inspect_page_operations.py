with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageOperations"')
idx2 = text.find('id="pageLog"')
print(text[idx:idx+2500])
