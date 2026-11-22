import urllib.request
import json

queries = [
    "M and M's 6oz",
    "m and M candy 6 oz",
    "Red Hots 6oz",
    "Almond Joy bar 8oz",
    "Dairy Queen Peanut Buster Parfait"
]

for q in queries:
    req = urllib.request.Request(
        'http://127.0.0.1:8899/api/meal/analyze',
        data=json.dumps({"meal_name": q, "description": q, "analyze_only": True, "commit": False}).encode('utf-8'),
        headers={'Content-Type': 'application/json'}
    )
    try:
        with urllib.request.urlopen(req) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            a = data.get('analysis', {})
            print(f"QUERY: '{q}'")
            print(f"  Matched: {a.get('vendor_matched')}")
            print(f"  Calories: {a.get('calories')}, Net Carbs: {a.get('net_carbs')}, Total Carbs: {a.get('total_carbs')}, Fiber: {a.get('fiber')}, Protein: {a.get('protein')}, Meets Goal: {a.get('meets_goal')}")
            print(f"  Notes: {a.get('gemini_notes')[:80]}...")
            print()
    except Exception as e:
        print(f"QUERY: '{q}' ERR: {e}")
