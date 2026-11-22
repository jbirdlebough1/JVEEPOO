import urllib.request
import json

req = urllib.request.Request('http://127.0.0.1:8899/api/meals/popular')
with urllib.request.urlopen(req) as resp:
    data = json.loads(resp.read().decode())
    print("Popular meals count:", len(data.get('popular_meals', [])))
    for m in data.get('popular_meals', []):
        print(f"  • {m['id']}: {m['title']} -- {m.get('fluid_oz')} oz -- {m.get('occasion')} -- {m['net_carbs']}g net carbs -- {m['calories']} kcal")
