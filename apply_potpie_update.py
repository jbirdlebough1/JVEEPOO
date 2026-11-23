import re
import os
import shutil
import sqlite3
import hashlib

print("=== STARTING POT PIE ESTIMATOR & NUTRITION ENGINE UPDATE ===")

# =========================================================================
# 1. UPDATE SERVER.PY
# =========================================================================
server_path = "D:/veepoo/w11_htm_app/server.py"
with open(server_path, "r", encoding="utf-8") as f:
    server_py = f.read()

# A. Insert Section 0D: Pot Pies & Frozen Entrees Module right after Section 0C
target_0c_end = '''                "trade_alternatives": ["Eat hot dog without bun (2g net carbs) or trade for Costco rotisserie chicken."],
                "glut4_recommendation": "<strong>Mandatory 45-Minute Brisk Walk:</strong> Required to blunt severe refined flour excursion."
            }'''

module_0d = '''                "trade_alternatives": ["Eat hot dog without bun (2g net carbs) or trade for Costco rotisserie chicken."],
                "glut4_recommendation": "<strong>Mandatory 45-Minute Brisk Walk:</strong> Required to blunt severe refined flour excursion."
            }

    # =========================================================================
    # 0D. POT PIES & PACKAGED FROZEN ENTREES (BANQUET, MARIE CALLENDER'S, ETC.)
    # =========================================================================
    has_pot_pie = any(w in text for w in ["pot pie", "pot-pie", "potpie", "meat pie", "banquet"])
    if has_pot_pie:
        is_banquet = ("banquet" in text) or ("pot pie" in text) or ("potpie" in text)
        is_marie = any(w in text for w in ["marie callender", "marie callenders", "marie's", "maries"])
        is_chicken = "chicken" in text
        is_turkey = "turkey" in text
        is_beef = "beef" in text or (not is_chicken and not is_turkey)

        m_oz = re.search(r'(\\d+(?:\\.\\d+)?)\\s*(?:oz|ounces?)', text)
        oz_size = float(m_oz.group(1)) if m_oz else (10.0 if is_marie else 7.0)

        if is_marie:
            if oz_size >= 14.0:
                p_cals = 930 if is_beef else 900
                p_net_c = 76.0 if is_beef else 72.0
                p_tot_c = 81.0 if is_beef else 76.0
                p_fib = 5.0 if is_beef else 4.0
                p_pro = 25.0 if is_beef else 24.0
                p_fat = 58.0 if is_beef else 54.0
                p_title = f"Marie Callender's Deep Dish {'Beef' if is_beef else 'Chicken'} Pot Pie ({oz_size:.0f} oz)"
            else:
                p_cals = 630 if is_chicken else 640
                p_net_c = 53.0 if is_chicken else 52.0
                p_tot_c = 57.0 if is_chicken else 56.0
                p_fib = 4.0
                p_pro = 17.0 if is_chicken else 16.0
                p_fat = 38.0
                p_title = f"Marie Callender's {'Chicken' if is_chicken else 'Beef'} Pot Pie ({oz_size:.0f} oz)"
            vendor_src = "Marie Callender's / Conagra Brands (Official Nutrition Disclosure)"
        else:
            # Banquet classic single-serve pot pies (7 oz / 198g) - Conagra Official Package Label
            if is_beef:
                p_cals = 420
                p_tot_c = 35.0
                p_fib = 1.0
                p_net_c = 34.0
                p_pro = 8.0
                p_fat = 27.0
                p_title = "Banquet Beef Pot Pie (7 oz / 198g Single-Serve Pie)"
            elif is_chicken:
                p_cals = 400
                p_tot_c = 36.0
                p_fib = 2.0
                p_net_c = 34.0
                p_pro = 10.0
                p_fat = 24.0
                p_title = "Banquet Chicken Pot Pie (7 oz / 198g Single-Serve Pie)"
            else:
                p_cals = 400
                p_tot_c = 37.0
                p_fib = 2.0
                p_net_c = 35.0
                p_pro = 10.0
                p_fat = 23.0
                p_title = "Banquet Turkey Pot Pie (7 oz / 198g Single-Serve Pie)"
            vendor_src = "Banquet / Conagra Brands (Official Nutrition Label Disclosure)"

        meets_goal = (p_net_c <= 45.0)
        spike = round(p_net_c * 1.6)

        c_items = [
            {
                "name": "Golden Flaky Pastry Crust (Top & Bottom Shell)",
                "portion": f"approx {oz_size * 0.45:.1f} oz",
                "net_carbs": round(p_net_c * 0.7, 1),
                "fiber": round(p_fib * 0.5, 1),
                "protein": round(p_pro * 0.25, 1),
                "fat": round(p_fat * 0.52, 1),
                "calories": round(p_cals * 0.52)
            },
            {
                "name": f"Savory {'Diced Beef' if is_beef else ('Chicken' if is_chicken else 'Turkey')}, Potatoes, Peas & Carrots in Gravy",
                "portion": f"approx {oz_size * 0.55:.1f} oz",
                "net_carbs": round(p_net_c * 0.3, 1),
                "fiber": round(p_fib * 0.5, 1),
                "protein": round(p_pro * 0.75, 1),
                "fat": round(p_fat * 0.48, 1),
                "calories": round(p_cals * 0.48)
            }
        ]

        return {
            "status": "SUCCESS",
            "meal_name": p_title,
            "vendor_matched": vendor_src,
            "photo_filename": "banquet_pot_pie.jpg",
            "calories": p_cals,
            "net_carbs": p_net_c,
            "total_carbs": p_tot_c,
            "fiber": p_fib,
            "protein": p_pro,
            "fat": p_fat,
            "glycemic_load": f"{round(p_net_c * 0.45)} (" + ("Safe Low-Moderate" if meets_goal else "High Spike Risk") + ")",
            "estimated_cost": "1.79",
            "meets_goal": meets_goal,
            "fluid_oz": 0.0,
            "projected_spike": f"+{spike} to +{spike + 12} mg/dL postprandial rise",
            "identified_items": c_items,
            "gemini_notes": f"🥧 {p_title.upper()}: {p_cals} kcal, {p_net_c}g Net Carbs ({p_tot_c}g Total Carbs, {p_fib}g Fiber, {p_pro}g Protein, {p_fat}g Lipids). The golden flaky pastry crust provides {round(p_net_c * 0.7, 1)}g of refined wheat starch, buffered by {p_fat}g of lipids and {p_pro}g protein. " + (f"At {p_net_c}g net carbs, it complies with your 45g diabetic ceiling for a single meal." if meets_goal else f"🚨 Exceeds your 45g ceiling by +{round(p_net_c - 45)}g! High glycemic excursion anticipated."),
            "trade_alternatives": [
                f"HARM REDUCTION: Eat the savory meat and vegetable filling and leave the bottom pastry crust to save 14–18g net carbs (drops meal to {round(p_net_c - 16, 1)}g net carbs!).",
                "Pair with a side garden salad with olive oil vinaigrette to add 4g dietary fiber and slow gastric emptying.",
                "Ensure accompanying beverage is zero-calorie water or brewed herbal tea."
            ],
            "glut4_recommendation": f"<strong>{20 if meets_goal else 35}-Minute Brisk GLUT4 Walk starting 25 min post-meal:</strong> Muscle contraction clears the {p_net_c}g starch load into active leg muscles, blunting projected +{spike} mg/dL glycemic excursion."
        }'''

if "has_pot_pie = any(w in text" not in server_py:
    server_py = server_py.replace(target_0c_end, module_0d, 1)
    print("[server.py] Added Section 0D Pot Pies Module")

# B. Update Section 0B Compound Parser to support pot pies paired with beverages
old_has_food = 'has_hotdog_or_food = any(w in text for w in ["hot dog", "hotdog", "frank", "costco", "costo", "burger", "pizza", "sandwich", "chicken bake"])'
new_has_food = 'has_hotdog_or_food = any(w in text for w in ["hot dog", "hotdog", "frank", "costco", "costo", "burger", "pizza", "sandwich", "chicken bake", "pot pie", "potpie", "banquet"])'

if old_has_food in server_py:
    server_py = server_py.replace(old_has_food, new_has_food, 1)
    print("[server.py] Updated Section 0B has_hotdog_or_food with pot pies")

# C. Update Section 0B food_item resolution
old_food_resolve = '''        food_item = None
        if "costco" in text or "costo" in text or "hot dog" in text or "hotdog" in text or "footlong" in text:
            food_item = {
                "name": "Costco Food Court 1/4 lb / Foot-Long All-Beef Hot Dog & Bun",
                "portion": "1 complete hot dog & bun (approx 8 oz)",
                "net_carbs": 44.0,
                "total_carbs": 46.0,
                "fiber": 2.0,
                "protein": 24.0,
                "fat": 33.0,
                "calories": 570
            }'''

new_food_resolve = '''        food_item = None
        if "costco" in text or "costo" in text or "hot dog" in text or "hotdog" in text or "footlong" in text:
            food_item = {
                "name": "Costco Food Court 1/4 lb / Foot-Long All-Beef Hot Dog & Bun",
                "portion": "1 complete hot dog & bun (approx 8 oz)",
                "net_carbs": 44.0,
                "total_carbs": 46.0,
                "fiber": 2.0,
                "protein": 24.0,
                "fat": 33.0,
                "calories": 570
            }
        elif "pot pie" in text or "potpie" in text or "banquet" in text:
            food_item = {
                "name": "Banquet Beef Pot Pie (7 oz / 198g Single-Serve Pie)",
                "portion": "1 complete pie (7 oz / 198g)",
                "net_carbs": 34.0,
                "total_carbs": 35.0,
                "fiber": 1.0,
                "protein": 8.0,
                "fat": 27.0,
                "calories": 420
            }'''

if old_food_resolve in server_py:
    server_py = server_py.replace(old_food_resolve, new_food_resolve, 1)
    print("[server.py] Updated Section 0B food_item resolution for pot pies")

# D. Add Banquet Beef Pot Pie to DEFAULT_POPULAR_MEALS
banquet_pantry_entry = '''    {
        "id": "banquet_beef_pot_pie_7oz",
        "title": "Banquet Beef Pot Pie (7 oz / 198g Single-Serve Pie)",
        "description": "7 oz classic flaky crust pot pie with tender diced beef, potatoes, peas and carrots in rich brown gravy. 420 kcal, 34g net carbs.",
        "calories": 420,
        "net_carbs": 34.0,
        "total_carbs": 35.0,
        "fiber": 1.0,
        "protein": 8.0,
        "fat": 27.0,
        "glycemic_load": "14 (Moderate)",
        "meets_goal": 1,
        "is_custom": 0,
        "fluid_oz": 0.0,
        "occasion": "Lunch/Dinner",
        "tips_json": json.dumps([
            "420 kcal and 34g Net Carbs complies with 45g diabetic ceiling.",
            "Flaky crust contains 24g refined starch; leave bottom crust to save ~16g net carbs.",
            "Pair with 16 oz water or unsweetened herbal tea for zero-glycemic hydration."
        ])
    },
'''

if '"id": "banquet_beef_pot_pie_7oz"' not in server_py:
    target_dicks = 'DEFAULT_POPULAR_MEALS = [\n'
    server_py = server_py.replace(target_dicks, target_dicks + banquet_pantry_entry, 1)
    print("[server.py] Added Banquet Beef Pot Pie 7oz to DEFAULT_POPULAR_MEALS")

with open(server_path, "w", encoding="utf-8") as f:
    f.write(server_py)
print("[server.py] Saved w11_htm_app/server.py")

# E. Update SQLite telemetry.db popular_meals table
db_path = "D:/veepoo/w11_htm_app/telemetry.db"
conn = sqlite3.connect(db_path)
cur = conn.cursor()
cur.execute("""
    INSERT OR REPLACE INTO popular_meals 
    (id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, fluid_oz, occasion, updated_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, datetime('now'))
""", (
    "banquet_beef_pot_pie_7oz",
    "Banquet Beef Pot Pie (7 oz / 198g Single-Serve Pie)",
    "7 oz classic flaky crust pot pie with tender diced beef, potatoes, peas and carrots in rich brown gravy. 420 kcal, 34g net carbs.",
    420, 34.0, 35.0, 1.0, 8.0, 27.0, "14 (Moderate)",
    '["420 kcal and 34g Net Carbs complies with 45g diabetic ceiling.", "Flaky crust contains 24g refined starch; leave bottom crust to save ~16g net carbs.", "Pair with 16 oz water or unsweetened herbal tea for zero-glycemic hydration."]',
    1, 0, 0.0, "Lunch/Dinner"
))
conn.commit()
conn.close()
print("[telemetry.db] Inserted Banquet Beef Pot Pie 7oz into popular_meals")

# =========================================================================
# 2. UPDATE INDEX.HTML
# =========================================================================
index_path = "D:/veepoo/index.html"
with open(index_path, "r", encoding="utf-8") as f:
    html = f.read()

old_tokens = '''                        <button type="button" class="lcars-btn" style="padding: 2px 8px; font-size: 0.70rem; background: #eff6ff; color: #1e40af; border: 1px solid #bfdbfe; border-radius: 4px; font-weight: bold;" onclick="setMealQueryToken('Recipe: 2 cups almond flour, 1 cup Greek yogurt, 4 eggs, 1 cup blueberries, 1/2 cup allulose. Batch makes 8 servings')">
                          👩‍🍳 Recipe Materials &amp; Diabetic Portion Sizing
                        </button>'''

new_tokens = '''                        <button type="button" class="lcars-btn" style="padding: 2px 8px; font-size: 0.70rem; background: #eff6ff; color: #1e40af; border: 1px solid #bfdbfe; border-radius: 4px; font-weight: bold;" onclick="setMealQueryToken('Recipe: 2 cups almond flour, 1 cup Greek yogurt, 4 eggs, 1 cup blueberries, 1/2 cup allulose. Batch makes 8 servings')">
                          👩‍🍳 Recipe Materials &amp; Diabetic Portion Sizing
                        </button>
                        <button type="button" class="lcars-btn" style="padding: 2px 8px; font-size: 0.70rem; background: #eff6ff; color: #1e40af; border: 1px solid #bfdbfe; border-radius: 4px; font-weight: bold;" onclick="setMealQueryToken('Banquet beef pot pie 7oz')">
                          🥧 Banquet Beef Pot Pie (7oz / 420 kcal)
                        </button>'''

if old_tokens in html and "Banquet beef pot pie 7oz" not in html:
    html = html.replace(old_tokens, new_tokens, 1)
    print("[index.html] Added Banquet Beef Pot Pie prompt token")

with open(index_path, "w", encoding="utf-8") as f:
    f.write(html)

shutil.copy2(index_path, "D:/veepoo/w11_htm_app/index.html")
print("Mirrored index.html to w11_htm_app/index.html")

# =========================================================================
# 3. UPDATE CELL.HTML
# =========================================================================
cell_path = "D:/veepoo/w11_htm_app/cell.html"
with open(cell_path, "r", encoding="utf-8") as f:
    cell_html = f.read()

old_cell_btn = '<button type="button" onclick="setPreset(\'Recipe: 2 cups almond flour, 1 cup Greek yogurt, 4 eggs, 1 cup blueberries, 1/2 cup allulose. Batch makes 8 servings\', \'Snack\')"'
new_cell_btn = '<button type="button" onclick="setPreset(\'Banquet beef pot pie 7oz\', \'Lunch\')" style="padding: 4px 8px; font-size: 0.72rem; background: #eff6ff; color: #1e40af; border: 1px solid #bfdbfe; border-radius: 4px; cursor: pointer; font-weight: bold;">🥧 Banquet Beef Pot Pie 7oz</button>\n      <button type="button" onclick="setPreset(\'Recipe: 2 cups almond flour, 1 cup Greek yogurt, 4 eggs, 1 cup blueberries, 1/2 cup allulose. Batch makes 8 servings\', \'Snack\')"'

if old_cell_btn in cell_html and "Banquet beef pot pie 7oz" not in cell_html:
    cell_html = cell_html.replace(old_cell_btn, new_cell_btn, 1)
    print("[cell.html] Added Banquet Beef Pot Pie preset to cell.html")

with open(cell_path, "w", encoding="utf-8") as f:
    f.write(cell_html)

sha1 = hashlib.sha256(open("D:/veepoo/index.html", "rb").read()).hexdigest()
sha2 = hashlib.sha256(open("D:/veepoo/w11_htm_app/index.html", "rb").read()).hexdigest()
assert sha1 == sha2, f"SHA mismatch! {sha1} != {sha2}"
print(f"Parity verified: {sha1}")
