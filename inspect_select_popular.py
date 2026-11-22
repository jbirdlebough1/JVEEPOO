with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function selectPopularMeal(')
if idx != -1:
    print(text[idx:idx+1500])
else:
    print('selectPopularMeal not found')
