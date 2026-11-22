import urllib.request
import json
import urllib.error

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
            print("OK:", data)
    except urllib.error.HTTPError as e:
        err_body = e.read().decode('utf-8')
        print(f"QUERY: '{q}' ERR 400: {err_body}")
    except Exception as e:
        print(f"QUERY: '{q}' ERR: {e}")
