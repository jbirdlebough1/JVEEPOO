with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function onSelectPopularMeal')
if idx == -1:
    idx = text.find('listboxPopularMeals')
print(text[idx:idx+2000])
