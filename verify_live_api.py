import urllib.request
import json

payload = {
    'meal_name': "find me a sugar free dessert like 'blueberry cake'",
    'analyze_only': True,
    'commit': False
}
req = urllib.request.Request(
    'http://127.0.0.1:8899/api/meal/analyze',
    data=json.dumps(payload).encode('utf-8'),
    headers={'Content-Type': 'application/json'}
)
with urllib.request.urlopen(req) as resp:
    data = json.loads(resp.read().decode('utf-8'))
    print('HTTP Status:', resp.status)
    print('API Status:', data.get('status'))
    analysis = data.get('analysis', {})
    print('Meal Name:', analysis.get('meal_name'))
    print('is_safe_option_search:', analysis.get('is_safe_option_search'))
    print('Store Items count:', len(analysis.get('store_complete_items', [])))
    print('Safe Recipes count:', len(analysis.get('safe_recipes', [])))
    print('BOM ingredients:', len(analysis.get('bom', [])))
