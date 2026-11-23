import urllib.request
import json

queries = [
    "Banquet beef pot pie 7oz",
    "Banquet beef pot pie",
    "Banquet beef pot pie 7oz, tea"
]

print("=== TESTING LIVE POT PIE ESTIMATOR ON PORT 8899 ===")

for q in queries:
    payload = json.dumps({"meal_name": q, "analyze_only": True}).encode("utf-8")
    req = urllib.request.Request(
        "http://localhost:8899/api/meal/analyze",
        data=payload,
        headers={"Content-Type": "application/json"}
    )
    with urllib.request.urlopen(req) as resp:
        res = json.loads(resp.read().decode("utf-8"))
        a = res.get("analysis", res)
        print(f"\n[QUERY: '{q}']")
        print(f"  Meal Name:   {a.get('meal_name')}")
        print(f"  Vendor:      {a.get('vendor_matched')}")
        print(f"  Calories:    {a.get('calories')} kcal (Target: 420 kcal standalone / 422 combo)")
        print(f"  Net Carbs:   {a.get('net_carbs')}g | Total Carbs: {a.get('total_carbs')}g | Fiber: {a.get('fiber')}g")
        print(f"  Protein:     {a.get('protein')}g | Fat: {a.get('fat')}g | Fluid: {a.get('fluid_oz')} oz")
        print(f"  Meets Goal:  {a.get('meets_goal')} | Spike: {a.get('projected_spike')}")
        print(f"  Components ({len(a.get('identified_items', []))} items):")
        for item in a.get('identified_items', []):
            print(f"    - {item.get('name')} ({item.get('portion')}): {item.get('calories')} kcal, {item.get('net_carbs')}g net carbs")
        print(f"  Gemini Note: {a.get('gemini_notes')[:140]}...")
