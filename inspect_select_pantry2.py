with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function onSelectPopularMealFromListbox')
print(text[idx+1000:idx+3000])
