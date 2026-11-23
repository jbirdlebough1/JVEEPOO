import urllib.request
import json

req = urllib.request.Request('http://127.0.0.1:8899/api/meal/recent?limit=10')
with urllib.request.urlopen(req) as resp:
    data = json.loads(resp.read().decode())
    print("Recent meals:")
    for m in data.get('meals', []):
        if '2026-11-21' in m.get('timestamp', ''):
            print(f"  • {m['id']}: {m['timestamp']} -- {m['meal_name']} -- {m.get('fluid_oz')} oz -- {m['net_carbs']}g net carbs -- {m['calories']} kcal")
