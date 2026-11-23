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
    a = data.get('analysis', {})
    print('Meal Name:', a.get('meal_name'))
    print('is_safe_option_search:', a.get('is_safe_option_search'))
    print('\n--- Store Complete Items (Screened for Zero Sorbitol/Maltitol) ---')
    for s in a.get('store_complete_items', []):
        print(f"  • {s['name']} ({s['store']}) | Net Carbs: {s['net_carbs']} | Sweetener: {s.get('sweetener')}")
    print('\n--- Curated Recipes ---')
    for r in a.get('safe_recipes', []):
        print(f"  • {r['title']} ({r['net_carbs']}g net carbs)")
    print('\n--- Primary Recipe BOM (Sweetening Spec) ---')
    for b in a.get('bom', []):
        if 'Sweetener' in b['item'] or 'Allulose' in b['item']:
            print(f"  • Item: {b['item']} | Impact: {b['impact']}")
