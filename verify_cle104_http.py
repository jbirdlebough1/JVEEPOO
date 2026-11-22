import urllib.request
import json

payload = {
    'meal_name': 'I want a hot candy 4 oz that meets my goals what is best',
    'description': 'I want a hot candy 4 oz that meets my goals what is best',
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
    a = data.get('analysis', {})
    print('Meal Name:', a.get('meal_name'))
    print('is_safe_option_search:', a.get('is_safe_option_search'))
    print('Calories:', a.get('calories'), 'Net Carbs:', a.get('net_carbs'), 'Meets Goal:', a.get('meets_goal'))
    print('Store Items count:', len(a.get('store_complete_items', [])))
    for s in a.get('store_complete_items', []):
        print('  Store:', s['name'], '|', s['store'], '|', s['net_carbs'])
    print('Safe Recipes count:', len(a.get('safe_recipes', [])))
    for r in a.get('safe_recipes', []):
        print('  Recipe:', r['title'], '|', r['net_carbs'], 'g net carbs')
    print('Notes:', a.get('gemini_notes'))
