import urllib.request
import json

payload = {
    'meal_name': 'kit kat bar 6 oz',
    'description': 'kit kat bar 6 oz',
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
    print('Vendor:', a.get('vendor_matched'))
    print('Calories:', a.get('calories'), 'Net Carbs:', a.get('net_carbs'), 'Meets Goal:', a.get('meets_goal'))
    print('Gemini Notes:', a.get('gemini_notes'))
    print('Trades:', len(a.get('trade_alternatives', [])))
