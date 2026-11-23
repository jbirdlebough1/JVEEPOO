import urllib.request
import json

payload = {"meal_description": "Bubba's tavern Sultan WA chicken fingers and frys honey mustard sauce, 48oz diet"}
data = json.dumps(payload).encode('utf-8')
req = urllib.request.Request('http://127.0.0.1:8899/api/meal/analyze', data=data, headers={'Content-Type': 'application/json'})

try:
    resp = urllib.request.urlopen(req)
    res = json.loads(resp.read().decode('utf-8'))
    print("=== ESTIMATOR OUTPUT ===")
    print(f"Title: {res.get('title')}")
    print(f"Calories: {res.get('calories')}")
    print(f"Net Carbs: {res.get('net_carbs')}")
    print(f"Total Carbs: {res.get('total_carbs')}")
    print(f"Fiber: {res.get('fiber')}")
    print(f"Protein: {res.get('protein')}")
    print(f"Fat: {res.get('fat')}")
    print(f"GL: {res.get('glycemic_load')}")
    print(f"Meets Goal: {res.get('meets_goal')}")
    print(f"Vendor: {res.get('vendor_matched')}")
    print(f"Identified items: {res.get('identified_items')}")
    print(f"Notes: {res.get('gemini_notes')}")
except Exception as e:
    print("Error:", e)
