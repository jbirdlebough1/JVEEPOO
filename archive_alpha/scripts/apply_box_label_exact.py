# apply_box_label_exact.py
import sqlite3
import re
import os

# 1. Update telemetry.db
conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
cur = conn.cursor()

title_str = 'Junior Mints Peppermint Crunch (Label: 120 kcal, 26g Carbs, 24g Sugars per srv • 3.5x Box: 420 kcal, 91g Carbs)'
desc_str = 'Junior Mints Peppermint Crunch 3.5 oz (99g) Box. Official Label: 120 kcal, 26g Total Carbs, 24g Total Sugars, 0g Fiber, 2g Fat, 0g Protein per serving (11 pieces). Full Box (3.5x servings): 420 kcal, 91g Total Carbs, 84g Sugars, 7g Fat.'
notes_str = '🍬 EXACT PACKAGE NUTRITION FACTS (Junior Mints Peppermint Crunch): Per serving (11 pieces): 120 kcal, 26g Total Carbs, 0g Fiber, 24g Sugars, 2g Fat, 0g Protein -> 26g Net Carbs. If full 3.5x package consumed: 420 kcal, 91g Net Carbs (84g Sugars). Fast fondant core induces acute +50 to +90 mg/dL glucose spike; mandatory GLUT4 walking required.'

cur.execute("""
    UPDATE popular_meals
    SET title = ?,
        calories = 120,
        net_carbs = 26.0,
        total_carbs = 26.0,
        fiber = 0.0,
        protein = 0.0,
        fat = 2.0,
        glycemic_load = '20 (Moderate-High Glycemic Burden)',
        meets_goal = 0,
        description = ?
    WHERE id = 'cell_34'
""", (title_str, desc_str))

cur.execute("""
    UPDATE meal_entries
    SET meal_name = ?,
        calories = 120,
        net_carbs = 26.0,
        total_carbs = 26.0,
        fiber = 0.0,
        protein = 0.0,
        fat = 2.0,
        glycemic_load = '20 (Moderate-High Glycemic Burden)',
        meets_goal = 0,
        vendor_matched = 'Junior Mints / Tootsie Roll Industries (Verified Physical Box Label)',
        gemini_notes = ?
    WHERE id = 34 OR meal_name LIKE '%pepper%'
""", (title_str, notes_str))

conn.commit()
conn.close()
print("[✔] 1. telemetry.db updated with 120 cal / 26g carbs / 0g fiber / 2g fat / 0g protein per srv!")

# 2. Update server.py
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_py = f.read()

# Replace the Junior Mints block in server.py
old_server_pattern = r'# 01\. Junior Mints Peppermint Crunch[\s\S]*?# 01\. High-Sugar Candy'

new_server_jm = """# 01. Junior Mints Peppermint Crunch (Verified Physical Box Nutrition Facts)
    if ("junior mint" in text or "junior mints" in text) or ("peppermint crunch" in text and ("junior" in text or "mint" in text or "box" in text or "120" in text or "26" in text or "crunch" in text)):
        is_whole_box = any(w in text for w in ["box", "whole", "entire", "3.5", "finish", "package", "all", "bag"])
        servings = 3.5 if is_whole_box else 1.0
        cals = round(120 * servings)
        carbs = round(26.0 * servings, 1)
        sugars = round(24.0 * servings, 1)
        fat = round(2.0 * servings, 1)
        pro = 0.0
        fib = 0.0
        net_c = carbs
        spike = round(net_c * 1.9)
        portion_label = "Full 3.5 oz (99g) Box (3.5 Servings Finished)" if is_whole_box else "1 Serving (11 pieces / 28g)"
        return {
            "status": "SUCCESS",
            "meal_name": f"Junior Mints Peppermint Crunch ({portion_label})",
            "vendor_matched": "Junior Mints / Tootsie Roll Industries (Official Box Label)",
            "photo_filename": "meal_junior_mints.jpg",
            "calories": cals,
            "net_carbs": net_c,
            "total_carbs": carbs,
            "fiber": fib,
            "protein": pro,
            "fat": fat,
            "glycemic_load": "20 (Moderate-High Glycemic Burden)" if servings == 1.0 else "70 (Severe Hyperglycemic Risk)",
            "meets_goal": False,
            "projected_spike": f"+{spike} to +{spike + 15} mg/dL postprandial peak",
            "trade_alternatives": [
                "Swap to Zero-Sugar Allulose Dark Peppermint Bark (2g net carbs, zero sugar alcohols)",
                "ChocZero Peppermint White Chocolate Squares (1g net carbs, monk fruit sweetened)",
                "If eating Junior Mints, limit portion to 1/2 serving (5-6 pieces = 12g carbs) paired with raw walnuts"
            ],
            "gemini_notes": f"🍬 VERIFIED PHYSICAL BOX LABEL: Per serving (11 pieces): 120 kcal, 26g Total Carbs, 0g Fiber, 24g Total Sugars, 2g Fat, 0g Protein -> 26g Net Carbs. Evaluated portion ({portion_label}): {cals} kcal, {net_c}g Net Carbs ({sugars}g Sugars). Fast fondant sucrose core induces rapid postprandial glucose spike of +{spike} mg/dL.",
            "tips": [
                f"Verified Box Label: 120 cal, 26g Total Carbs, 24g Sugars, 0g Fiber, 2g Fat, 0g Protein per serving.",
                f"Evaluated portion: {portion_label} = {net_c}g Net Carbs ({sugars}g pure fast sugars).",
                "Mandatory GLUT4 walking required to prevent rapid hyperglycemic spike."
            ],
            "glut4_recommendation": f"<strong>{max(20, min(60, round(net_c * 0.65)))}-Minute Brisk GLUT4 Walk starting 15 min post-consumption:</strong> Directly blunts projected +{spike} mg/dL spike."
        }

    # 01. High-Sugar Candy"""

server_py = re.sub(old_server_pattern, new_server_jm, server_py)
with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(server_py)
print("[✔] 2. server.py updated with verified box label!")

# 3. Update cell.html
with open('D:/veepoo/w11_htm_app/cell.html', 'r', encoding='utf-8') as f:
    cell_html = f.read()

old_cell_pattern = r'// 01a\. Junior Mints Peppermint Crunch[\s\S]*?// 01\. High-Sugar Candy'

new_cell_jm = """// 01a. Junior Mints Peppermint Crunch (Verified Physical Box Nutrition Facts)
      if (/(?:junior\\s*mints?|peppermint\\s+crunch)/i.test(t)) {
        const isWholeBox = /(?:box|whole|entire|3\\.5|finish|package|all)/i.test(t);
        const servings = isWholeBox ? 3.5 : 1.0;
        const cals = Math.round(120 * servings);
        const carbs = Math.round(26.0 * servings * 10) / 10;
        const sugars = Math.round(24.0 * servings * 10) / 10;
        const fat = Math.round(2.0 * servings * 10) / 10;
        const netC = carbs;
        const spike = Math.round(netC * 1.9);
        const portionLabel = isWholeBox ? "Full 3.5 oz Box (3.5 Servings Finished)" : "1 Serving (11 pieces / 28g)";
        return {
          meal_name: `Junior Mints Peppermint Crunch (${portionLabel})`,
          vendor_matched: "Junior Mints Official Box Label",
          calories: cals,
          net_carbs: netC,
          total_carbs: carbs,
          fiber: 0.0,
          protein: 0.0,
          fat: fat,
          glycemic_load: isWholeBox ? "70 (Severe Hyperglycemic Risk)" : "20 (Moderate-High Glycemic Burden)",
          meets_goal: false,
          projected_spike: `+${spike} to +${spike + 15} mg/dL postprandial rise`,
          gemini_notes: `🍬 VERIFIED BOX LABEL: Per serving: 120 kcal, 26g Total Carbs, 0g Fiber, 24g Sugars, 2g Fat, 0g Protein. Portion (${portionLabel}): ${cals} kcal, ${netC}g Net Carbs (${sugars}g Sugars). Fast sucrose core induces +${spike} mg/dL spike.`,
          trade_alternatives: [
            "Swap to Zero-Sugar Allulose Dark Peppermint Bark (2g net carbs)",
            "ChocZero Peppermint White Chocolate Squares (1g net carbs)"
          ],
          glut4_recommendation: `<strong>${Math.max(20, Math.min(60, Math.round(netC * 0.65)))}-Minute Brisk GLUT4 Walk starting 15 min post-consumption:</strong> Required to blunt +${spike} mg/dL spike.`
        };
      }

      // 01. High-Sugar Candy"""

cell_html = re.sub(old_cell_pattern, new_cell_jm, cell_html)
with open('D:/veepoo/w11_htm_app/cell.html', 'w', encoding='utf-8') as f:
    f.write(cell_html)
print("[✔] 3. cell.html updated with verified box label!")

# 4. Update index.html and w11_htm_app/index.html
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    index_html = f.read()

old_index_pattern = r'\} else if \(/\(?:\?:junior\\s\*mints\?\|peppermint\\s\+crunch\)/i\.test\(text\)\) \{[\s\S]*?\} else if \(/\(?:\?:peppermint\|crunch'

new_index_jm = """} else if (/(?:junior\\s*mints?|peppermint\\s+crunch)/i.test(text)) {
          const isWholeBox = /(?:box|whole|entire|3\\.5|finish|package|all)/i.test(text);
          const servings = isWholeBox ? 3.5 : 1.0;
          const cals = Math.round(120 * servings);
          const carbs = Math.round(26.0 * servings * 10) / 10;
          const sugars = Math.round(24.0 * servings * 10) / 10;
          const fat = Math.round(2.0 * servings * 10) / 10;
          const netC = carbs;
          const spike = Math.round(netC * 1.9);
          const portionLabel = isWholeBox ? "Full 3.5 oz Box (3.5 Servings Finished)" : "1 Serving (11 pieces / 28g)";
          data = {
            title: `Junior Mints Peppermint Crunch (${portionLabel})`,
            desc: rawText,
            calories: cals,
            netCarbs: netC,
            totalCarbs: carbs,
            fiber: 0.0,
            protein: 0.0,
            fat: fat,
            gl: isWholeBox ? "70 (Severe Hyperglycemic Risk)" : "20 (Moderate-High Glycemic Burden)",
            meetsGoal: false,
            tips: [
              `🍬 VERIFIED PHYSICAL BOX LABEL: 120 kcal, 26g Total Carbs, 0g Fiber, 24g Total Sugars, 2g Fat, 0g Protein per serving (11 pieces).`,
              `Evaluated: ${portionLabel} = ${cals} kcal, ${netC}g Net Carbs (${sugars}g fast sugars). Anticipated peak: +${spike} mg/dL within 30 min.`
            ],
            glut4: `<strong>${Math.max(20, Math.min(60, Math.round(netC * 0.65)))}-Minute Brisk GLUT4 Walk starting 15 min post-snack:</strong> Required to blunt +${spike} mg/dL spike.`
          };
        } else if (/(?:peppermint|crunch"""

index_html = re.sub(old_index_pattern, new_index_jm, index_html)

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(index_html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(index_html)
print("[✔] 4. index.html and w11_htm_app/index.html updated with verified box label!")
