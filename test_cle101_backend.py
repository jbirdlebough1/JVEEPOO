import sys
sys.path.insert(0, 'D:/veepoo/w11_htm_app')
from server import analyze_meal_with_gemini_backend

# 1. Token search
t1 = analyze_meal_with_gemini_backend("find me a sugar free dessert like 'blueberry cake'")
print('--- T1: Safe Token Search ---')
print('meal_name:', t1['meal_name'])
print('is_safe_option_search:', t1.get('is_safe_option_search'))
print('store_complete_items count:', len(t1.get('store_complete_items', [])))
for it in t1['store_complete_items']:
    print('  Store item:', it['name'], '|', it['store'], '|', it['net_carbs'])
print('safe_recipes count:', len(t1.get('safe_recipes', [])))
for r in t1['safe_recipes']:
    print('  Recipe:', r['title'], '|', r['net_carbs'], 'g net carbs | BOM items:', len(r['bom']))

# 2. Home-baked materials
t2 = analyze_meal_with_gemini_backend('zero sugar cottage cheese blueberry cake')
print('\n--- T2: Home Baked Materials ---')
print('meal_name:', t2['meal_name'])
print('calories:', t2['calories'], 'net_carbs:', t2['net_carbs'], 'protein:', t2['protein'])
print('meets_goal:', t2['meets_goal'])
print('BOM items:', len(t2.get('bom', [])))

# 3. Confectionery regression
t3 = analyze_meal_with_gemini_backend('M and M candy 6 oz')
print('\n--- T3: Regression Candies ---')
print('meal_name:', t3['meal_name'], 'net_carbs:', t3['net_carbs'], 'meets_goal:', t3['meets_goal'])

# 4. Dairy Queen Parfait regression
t4 = analyze_meal_with_gemini_backend('Dairy Queen Peanut Buster Parfait')
print('\n--- T4: Regression DQ Parfait ---')
print('meal_name:', t4['meal_name'], 'net_carbs:', t4['net_carbs'], 'meets_goal:', t4['meets_goal'])
