import urllib.request
import json

queries = [
    ("hot dog foot long Costo, tea", "Compound Meal: Costco Hot Dog + Saved Tea"),
    ("tea black+green bag, three cloves, cinnimon, nutmeg, 12oz water", "Custom Tea: Black+Green + Cloves + Cinnamon + Nutmeg + 12oz Water"),
    ("Recipe: 2 cups almond flour, 1 cup Greek yogurt, 4 eggs, 1 cup blueberries, 1/2 cup allulose. Batch makes 8 servings", "Recipe Materials (BOM) & Diabetic Portion Sizing"),
    ("Diet Coke 24oz", "Vendor Beverage: Diet Coke 24oz Hydration Tracking")
]

print("=== TESTING LIVE ESTIMATOR ENDPOINTS ON PORT 8899 ===")

for q, desc in queries:
    payload = json.dumps({"meal_name": q, "analyze_only": True}).encode("utf-8")
    req = urllib.request.Request(
        "http://localhost:8899/api/meal/analyze",
        data=payload,
        headers={"Content-Type": "application/json"}
    )
    try:
        with urllib.request.urlopen(req) as resp:
            data = json.loads(resp.read().decode("utf-8"))
            analysis = data.get("analysis", data)
            print(f"\n[{desc}] Query: '{q}'")
            print(f"  Status: {data.get('status')} | Vendor: {analysis.get('vendor_matched')}")
            print(f"  Meal: {analysis.get('meal_name')}")
            print(f"  Calories: {analysis.get('calories')} kcal | Net Carbs: {analysis.get('net_carbs')}g | Fiber: {analysis.get('fiber')}g")
            print(f"  Protein: {analysis.get('protein')}g | Fat: {analysis.get('fat')}g | Fluid: {analysis.get('fluid_oz')} oz")
            print(f"  Meets Goal: {analysis.get('meets_goal')} | Spike: {analysis.get('projected_spike')}")
            print(f"  Identified Items Count: {len(analysis.get('identified_items', []))}")
            for item in analysis.get('identified_items', []):
                print(f"    - {item.get('name')} ({item.get('portion')}): {item.get('calories')} kcal, {item.get('net_carbs')}g net carbs")
            print(f"  Gemini Clinical Note: {analysis.get('gemini_notes')[:120]}...")
    except Exception as e:
        print(f"  ERROR for '{q}': {e}")
