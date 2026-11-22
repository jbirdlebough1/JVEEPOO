with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function renderReportsView')
next_fn = text.find('function renderUnifiedLongitudinalTallChart')
print(text[idx:next_fn])
