with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

for fn in ['estimateCustomMealDiet', 'analyzePlannedMeal', 'setMealQueryToken', 'selectSafeOption', 'commitSelectedPlanToPantry']:
    idx = text.find(fn)
    print(f"{fn}: {idx}")
