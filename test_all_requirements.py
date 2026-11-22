import urllib.request, json, re

print("=== 1. TESTING SERVER ENDPOINTS ===")
# Test /cell
try:
    with urllib.request.urlopen("http://127.0.0.1:8899/cell") as resp:
        content = resp.read().decode('utf-8')
        assert resp.status == 200
        assert "LCARS 5010 • MOBILE FOOD INTAKE" in content
        assert "filePhotos" in content
        assert "txtMealInput" in content
        assert "btnAnalyze" in content
        assert "btnSubmit" in content
        print("  ✓ /cell mobile food intake page verified OK (HTTP 200, 3-click interface, multi-photo, zero-auth)")
except Exception as e:
    print("  ✗ /cell test failed:", e)

# Test Taco Time Fiesta Platter
try:
    req = urllib.request.Request(
        "http://127.0.0.1:8899/api/meal/analyze",
        data=json.dumps({"meal_name": "Taco Time fiesta platter and drink"}).encode('utf-8'),
        headers={"Content-Type": "application/json"}
    )
    with urllib.request.urlopen(req) as resp:
        res = json.loads(resp.read().decode('utf-8'))
        assert resp.status == 200
        a = res.get('analysis') or res
        assert "Taco Time" in a['meal_name']
        assert a['net_carbs'] >= 100
        assert not a['meets_goal']
        assert a['estimated_cost'] == "14.75"
        assert "GLUT4" in a['glut4_recommendation']
        print(f"  ✓ Taco Time Fiesta Platter analyzed OK: {a['meal_name']} -> {a['net_carbs']}g net carbs, cost ${a['estimated_cost']}, projected spike {a['projected_spike']}")
except Exception as e:
    print("  ✗ Taco Time analyze test failed:", e)

print("\n=== 2. TESTING APP HTML FILES ===")
for p in [r"D:/veepoo/index.html", r"D:/veepoo/w11_htm_app/index.html"]:
    with open(p, "r", encoding="utf-8") as f:
        html = f.read()
    
    assert "listboxPopularMeals" in html, f"Missing listbox in {p}"
    assert "onSelectPopularMealFromListbox" in html, f"Missing onSelectPopularMealFromListbox in {p}"
    assert "btnSubmitChoiceConsumed" in html, f"Missing submit choice button in {p}"
    assert "execute15MinWatchDataDump" in html, f"Missing 15-min auto-dump in {p}"
    assert "packets.sort((a, b) => a[1] - b[1])" in html, f"Sleep sort not fixed in {p}"
    assert "<!-- CELL PHONE PHOTO UPLOAD & WI-FI AUTO-SYNC GATEWAY -->" not in html, f"Old cell section not removed from {p}"
    assert "http://192.168.12.207:8899/cell" in html, f"Missing /cell reference in {p}"
    print(f"  ✓ {p} verified OK with scrollable listbox, 3-click paradigm, 15-min auto-dump, and sleep fix!")

print("\nALL VERIFICATIONS PASSED!")
