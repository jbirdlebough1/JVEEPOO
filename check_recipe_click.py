with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

for term in ['safe_recipes', 'store_complete_items', 'recipe_sour_gummies', 'selectSafeRecipe', 'populateRecipeSelection']:
    matches = list(re.finditer(re.escape(term), text))
    print(f"{term}: {len(matches)} matches")
    for m in matches[:3]:
        print(repr(text[m.start()-40:m.end()+60]))
