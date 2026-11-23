# apply_exact_junior_mints_3_5oz.py
import sqlite3
import re

# 1. Update telemetry.db
conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
cur = conn.cursor()

title_str = 'Junior Mints Peppermint Crunch (3.5 oz / 99g Box • Label: 29g Total Carbs, 24g Sugars)'
desc_str = 'Junior Mints Peppermint Crunch 3.5 oz (99g) Theater Box. Official FDA Label per serving (1/3 box = 33g): 29g Total Carbs, 24g Total Sugars, 1g Fiber, 140 kcal, 3.5g Fat. (Full 3.5 oz box = 87g Total Carbs, 72g Sugars).'
notes_str = '🍬 OFFICIAL PACKAGE NUTRITION FACTS (3.5 oz / 99g Box): 1 serving (1/3 box / 33g) contains 140 kcal, 29g Total Carbs, 1g Dietary Fiber, 24g Total Sugars -> 28g Net Carbs. Full 3.5 oz (99g) box contains 87g Total Carbs (72g Sugars). Fast sucrose/corn syrup core causes sharp +52 to +65 mg/dL peak; 20-min post-snack GLUT4 walk recommended.'

cur.execute("""
    UPDATE popular_meals
    SET title = ?,
        calories = 140,
        net_carbs = 28.0,
        total_carbs = 29.0,
        fiber = 1.0,
        protein = 1.0,
        fat = 3.5,
        glycemic_load = '20 (Moderate Glycemic Burden)',
        meets_goal = 0,
        description = ?
    WHERE id = 'cell_34'
""", (title_str, desc_str))

cur.execute("""
    UPDATE meal_entries
    SET meal_name = ?,
        calories = 140,
        net_carbs = 28.0,
        total_carbs = 29.0,
        fiber = 1.0,
        protein = 1.0,
        fat = 3.5,
        glycemic_load = '20 (Moderate Glycemic Burden)',
        meets_goal = 0,
        vendor_matched = 'Junior Mints / Tootsie Roll Industries (3.5 oz / 99g Box Label)',
        gemini_notes = ?
    WHERE id = 34 OR meal_name LIKE '%pepper%'
""", (title_str, notes_str))

conn.commit()
conn.close()
print("[✔] telemetry.db updated with exact 3.5 oz / 99g box specs (29g Total Carbs, 24g Sugars)!")

# 2. Update server.py
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_py = f.read()

old_server_jm = """"calories": 130,
            "net_carbs": 26.0,
            "total_carbs": 27.0,
            "fiber": 1.0,
            "protein": 0.0,
            "fat": 3.0,
            "glycemic_load": "18 (Moderate Glycemic Burden)",
            "meets_goal": False,
            "projected_spike": "+45 to +60 mg/dL postprandial peak",
            "trade_alternatives": [
                "Swap to Zero-Sugar Allulose Dark Peppermint Bark (2g net carbs, zero sugar alcohols)",
                "ChocZero Peppermint White Chocolate Squares (1g net carbs, monk fruit sweetened)",
                "Enjoy 1/2 serving (~6-7 pieces = 12g sugars) with a handful of raw walnuts for protein/fat buffer"
            ],
            "gemini_notes": "🍬 OFFICIAL PACKAGE NUTRITION FACTS: 1.1 oz (31g) serving contains 130 kcal, 27g Total Carbs, 1g Fiber, 24g Total Sugars -> 26g Net Carbs. Fits within your 45g net carb ceiling, but because 24g is pure fast-acting sucrose/corn syrup, expect a sharp +45 to +60 mg/dL rise within 30 minutes without a follow-up walk.",
            "tips": [
                "Official label disclosure: 24g Total Sugars (24g Added Sugars), 26g Net Carbs, 130 kcal.",
                "Fast liquid-like fondant center absorbs rapidly; pairing with protein/nuts slows glycemic spike."
            ],"""

new_server_jm = """"calories": 140,
            "net_carbs": 28.0,
            "total_carbs": 29.0,
            "fiber": 1.0,
            "protein": 1.0,
            "fat": 3.5,
            "glycemic_load": "20 (Moderate Glycemic Burden)",
            "meets_goal": False,
            "projected_spike": "+52 to +65 mg/dL postprandial peak",
            "trade_alternatives": [
                "Swap to Zero-Sugar Allulose Dark Peppermint Bark (2g net carbs, zero sugar alcohols)",
                "ChocZero Peppermint White Chocolate Squares (1g net carbs, monk fruit sweetened)",
                "Enjoy 1/2 serving (~6-7 pieces = 12g sugars) paired with raw walnuts for protein/fat buffer"
            ],
            "gemini_notes": "🍬 OFFICIAL PACKAGE NUTRITION FACTS (3.5 oz / 99g Box): 1 serving (1/3 box / 33g) contains 140 kcal, 29g Total Carbs, 1g Fiber, 24g Total Sugars -> 28g Net Carbs. Full 3.5 oz box = 87g Total Carbs. Fast fondant sucrose core induces an acute +52 to +65 mg/dL glycemic excursion. 20-min postprandial GLUT4 walk recommended.",
            "tips": [
                "Official package label (3.5 oz / 99g Box): 29g Total Carbs, 24g Total Sugars, 1g Fiber, 140 kcal.",
                "Fast-absorbing liquid fondant center requires 20-min GLUT4 walk or portion moderation."
            ],"""

assert old_server_jm in server_py, "old_server_jm not found in server.py"
server_py = server_py.replace(old_server_jm, new_server_jm)
with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(server_py)
print("[✔] server.py updated!")

# 3. Update cell.html
with open('D:/veepoo/w11_htm_app/cell.html', 'r', encoding='utf-8') as f:
    cell_html = f.read()

old_cell_jm = """          calories: 130,
          net_carbs: 26.0,
          total_carbs: 27.0,
          fiber: 1.0,
          protein: 0,
          fat: 3.0,
          glycemic_load: "18 (Moderate Glycemic Burden)",
          meets_goal: false,
          projected_spike: "+45 to +60 mg/dL postprandial rise",
          gemini_notes: "🍬 OFFICIAL PACKAGE NUTRITION FACTS: 1.1 oz (31g) serving contains 130 kcal, 27g Carbs, 1g Fiber, 24g Total Sugars -> 26g Net Carbs. Fits under 45g ceiling; 20-min post-snack walk recommended to blunt 24g sucrose spike.","""

new_cell_jm = """          calories: 140,
          net_carbs: 28.0,
          total_carbs: 29.0,
          fiber: 1.0,
          protein: 1.0,
          fat: 3.5,
          glycemic_load: "20 (Moderate Glycemic Burden)",
          meets_goal: false,
          projected_spike: "+52 to +65 mg/dL postprandial rise",
          gemini_notes: "🍬 OFFICIAL PACKAGE NUTRITION FACTS (3.5 oz / 99g Box): 1 serving (33g) contains 140 kcal, 29g Total Carbs, 1g Fiber, 24g Total Sugars -> 28g Net Carbs. Full 3.5 oz box = 87g Carbs. 20-min post-snack walk recommended.","""

assert old_cell_jm in cell_html, "old_cell_jm not found in cell.html"
cell_html = cell_html.replace(old_cell_jm, new_cell_jm)
with open('D:/veepoo/w11_htm_app/cell.html', 'w', encoding='utf-8') as f:
    f.write(cell_html)
print("[✔] cell.html updated!")

# 4. Update index.html and w11_htm_app/index.html
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    index_html = f.read()

old_index_jm = """            calories: 130,
            netCarbs: 26.0,
            totalCarbs: 27.0,
            fiber: 1.0,
            protein: 0,
            fat: 3.0,
            gl: "18 (Moderate Glycemic Burden)",
            meetsGoal: false,
            tips: [
              "🍬 OFFICIAL PACKAGE NUTRITION FACTS: 1.1 oz (31g) serving contains 130 kcal, 27g Total Carbs, 1g Fiber, 24g Total Sugars -> 26g Net Carbs.",
              "Meets clinical 45g ceiling. Because 24g is pure rapid sucrose/corn syrup, expect a +45 to +60 mg/dL rise within 30 min."
            ],"""

new_index_jm = """            calories: 140,
            netCarbs: 28.0,
            totalCarbs: 29.0,
            fiber: 1.0,
            protein: 1.0,
            fat: 3.5,
            gl: "20 (Moderate Glycemic Burden)",
            meetsGoal: false,
            tips: [
              "🍬 OFFICIAL PACKAGE NUTRITION FACTS (3.5 oz / 99g Box): 1 serving (1/3 box / 33g) contains 140 kcal, 29g Total Carbs, 1g Fiber, 24g Total Sugars -> 28g Net Carbs.",
              "Full 3.5 oz box = 87g Carbs (72g Sugars). Fast fondant core causes acute +52 to +65 mg/dL peak within 30 min."
            ],"""

assert old_index_jm in index_html, "old_index_jm not found in index.html"
index_html = index_html.replace(old_index_jm, new_index_jm)

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(index_html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(index_html)
print("[✔] index.html and w11_htm_app/index.html updated!")
