with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageOperations"')
idx_next = text.find('id="pageLog"', idx)
print('Length of pageOperations:', idx_next - idx)
print('End snippet of pageOperations:')
print(text[idx_next-600:idx_next])
