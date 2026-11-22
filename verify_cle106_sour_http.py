import urllib.request
import json

payload = {
    'meal_name': 'sour candy that meets my goals',
    'description': 'sour candy that meets my goals',
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
    a = data.get('analysis', {})
    print('Meal Name:', a.get('meal_name'))
    print('is_safe_option_search:', a.get('is_safe_option_search'))
    print('Calories:', a.get('calories'), 'Net Carbs:', a.get('net_carbs'), 'Protein:', a.get('protein'))
    print('\n--- Store items ---')
    for s in a.get('store_complete_items', []):
        print(f"  • {s['name']} ({s['store']}) | Net Carbs: {s['net_carbs']}")
    print('\n--- Safe recipes ---')
    for r in a.get('safe_recipes', []):
        print(f"  • {r['title']} ({r['net_carbs']}g net carbs)")
    print('\nNotes:', a.get('gemini_notes'))
