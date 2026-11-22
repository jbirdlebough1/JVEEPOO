with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'<select[^>]*id=["\'](?:selPopularMeals|selMealPantry|popularMeals)[^>]*>[\s\S]*?<\/select>', text)
print('Select elements:', matches)

matches2 = re.findall(r'function\s+[A-Za-z0-9_]*(?:Popular|Pantry|Preset)[A-Za-z0-9_]*[^{]*\{', text)
print('Functions:', matches2)
