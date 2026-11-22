with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageOperations"')
idx2 = text.find('class="tab-page', idx+20)
print('Length of pageOperations:', idx2 - idx)
print(text[idx:idx+2000])
