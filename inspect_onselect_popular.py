with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx1 = text.find('function onSelectPopularMealFromListbox(')
print('=== onSelectPopularMealFromListbox ===')
print(text[idx1:idx1+1000])

idx2 = text.find('function loadAndAssessPopularMeal(')
print('\n=== loadAndAssessPopularMeal ===')
print(text[idx2:idx2+1000])
