# update_jm_exact_box.py
import re
import hashlib

# 1. Update index.html
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    index_html = f.read()

target_index = """        } else if (/(?:junior\\s*mints?|peppermint\\s+crunch)/i.test(text)) {
          data = {
            title: "Junior Mints Peppermint Crunch (Official FDA Package Label)",
            desc: rawText,
            calories: 140,
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
            ],
            glut4: "<strong>20-Minute Brisk GLUT4 Walk starting 20 min post-snack:</strong> Blunts anticipated +50 mg/dL spike back into baseline."
          };"""

replacement_index = """        } else if (/(?:junior\\s*mints?|peppermint\\s+crunch)/i.test(text)) {
          const isWholeBox = /(?:box|whole|entire|3\\.5|finish|package|all)/i.test(text) || !/(?:serving|11\\s*pieces)/i.test(text);
          const servings = isWholeBox ? 3.5 : 1.0;
          const sCals = 120;
          const sCarbs = 26.0;
          const sSugars = 24.0;
          const sFat = 2.0;
          const totCals = Math.round(sCals * servings);
          const totCarbs = Math.round(sCarbs * servings * 10) / 10;
          const totSugars = Math.round(sSugars * servings * 10) / 10;
          const totFat = Math.round(sFat * servings * 10) / 10;
          const spike = Math.round(totCarbs * 1.9);
          const portionLabel = isWholeBox ? "Full 3.5 oz Box (3.5x Servings Finished)" : "1 Serving (11 pieces / ~28g)";
          data = {
            title: `Junior Mints Peppermint Crunch (${portionLabel})`,
            desc: rawText,
            calories: totCals,
            netCarbs: totCarbs,
            totalCarbs: totCarbs,
            fiber: 0.0,
            protein: 0.0,
            fat: totFat,
            gl: isWholeBox ? "70 (Severe Hyperglycemic Risk)" : "20 (Moderate-High Glycemic Burden)",
            meetsGoal: false,
            tips: [
              `🍬 EXACT PACKAGE LABEL: 1 Serving (11 pieces): 120 kcal, 26g Total Carbs, 24g Sugars, 0g Fiber, 2g Fat, 0g Protein.`,
              `Evaluated: ${portionLabel} = ${totCals} kcal, ${totCarbs}g Net Carbs (${totSugars}g Sugars, ${totFat}g Fat). Anticipated peak: +${spike} mg/dL.`
            ],
            glut4: `<strong>${Math.max(25, Math.min(60, Math.round(totCarbs * 0.65)))}-Minute Brisk GLUT4 Walk starting 15 min post-snack:</strong> Required to blunt +${spike} mg/dL spike.`
          };"""

assert target_index in index_html, "target_index not found in index.html"
index_html = index_html.replace(target_index, replacement_index)

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(index_html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(index_html)
print("[✔] index.html and w11_htm_app/index.html updated with exact 120 cal / 26g carbs / 0g fiber / 2g fat / 0g protein specs!")

# 2. Update cell.html
with open('D:/veepoo/w11_htm_app/cell.html', 'r', encoding='utf-8') as f:
    cell_html = f.read()

target_cell = """      // 01a. Junior Mints Peppermint Crunch (Official Package Label)
      if (/(?:junior\\s*mints?|peppermint\\s+crunch)/i.test(t)) {
        return {
          meal_name: "Junior Mints Peppermint Crunch (Official FDA Package Label)",
          vendor_matched: "Junior Mints / Tootsie Roll Industries Official Label",
          calories: 140,
          net_carbs: 28.0,
          total_carbs: 29.0,
          fiber: 1.0,
          protein: 1.0,
          fat: 3.5,
          glycemic_load: "20 (Moderate Glycemic Burden)",
          meets_goal: false,
          projected_spike: "+52 to +65 mg/dL postprandial rise",
          gemini_notes: "🍬 OFFICIAL PACKAGE NUTRITION FACTS (3.5 oz / 99g Box): 1 serving (33g) contains 140 kcal, 29g Total Carbs, 1g Fiber, 24g Total Sugars -> 28g Net Carbs. Full 3.5 oz box = 87g Carbs. 20-min post-snack walk recommended.",
          trade_alternatives: [
            "Swap to Zero-Sugar Allulose Dark Peppermint Bark (2g net carbs)",
            "ChocZero Peppermint White Chocolate Squares (1g net carbs)"
          ],
          glut4_recommendation: "<strong>20-Minute Brisk GLUT4 Walk starting 20 min post-snack:</strong> Blunts expected +50 mg/dL spike."
        };
      }"""

replacement_cell = """      // 01a. Junior Mints Peppermint Crunch (Verified Physical Box Nutrition Facts)
      if (/(?:junior\\s*mints?|peppermint\\s+crunch)/i.test(t)) {
        const isWholeBox = /(?:box|whole|entire|3\\.5|finish|package|all)/i.test(t) || !/(?:serving|11\\s*pieces)/i.test(t);
        const servings = isWholeBox ? 3.5 : 1.0;
        const cals = Math.round(120 * servings);
        const carbs = Math.round(26.0 * servings * 10) / 10;
        const sugars = Math.round(24.0 * servings * 10) / 10;
        const fat = Math.round(2.0 * servings * 10) / 10;
        const spike = Math.round(carbs * 1.9);
        const portionLabel = isWholeBox ? "Full 3.5 oz Box (3.5x Servings Finished)" : "1 Serving (11 pieces / ~28g)";
        return {
          meal_name: `Junior Mints Peppermint Crunch (${portionLabel})`,
          vendor_matched: "Junior Mints Official Box Label",
          calories: cals,
          net_carbs: carbs,
          total_carbs: carbs,
          fiber: 0.0,
          protein: 0.0,
          fat: fat,
          glycemic_load: isWholeBox ? "70 (Severe Hyperglycemic Risk)" : "20 (Moderate-High Glycemic Burden)",
          meets_goal: false,
          projected_spike: `+${spike} to +${spike + 15} mg/dL postprandial rise`,
          gemini_notes: `🍬 EXACT BOX LABEL: 1 Serving (11 pieces): 120 kcal, 26g Carbs, 24g Sugars, 0g Fiber, 2g Fat, 0g Protein. Evaluated: ${portionLabel} = ${cals} kcal, ${carbs}g Net Carbs (${sugars}g Sugars, ${fat}g Fat). Projected peak: +${spike} mg/dL.`,
          trade_alternatives: [
            "Swap to Zero-Sugar Allulose Dark Peppermint Bark (2g net carbs)",
            "ChocZero Peppermint White Chocolate Squares (1g net carbs)"
          ],
          glut4_recommendation: `<strong>${Math.max(25, Math.min(60, Math.round(carbs * 0.65)))}-Minute Brisk GLUT4 Walk starting 15 min post-consumption:</strong> Required to blunt +${spike} mg/dL spike.`
        };
      }"""

assert target_cell in cell_html, "target_cell not found in cell.html"
cell_html = cell_html.replace(target_cell, replacement_cell)

with open('D:/veepoo/w11_htm_app/cell.html', 'w', encoding='utf-8') as f:
    f.write(cell_html)
print("[✔] cell.html updated!")

# 3. Update server.py
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_py = f.read()

target_server = """    # 01. Junior Mints Peppermint Crunch (Official Tootsie Roll Industries FDA Label)
    if ("junior mint" in text or "junior mints" in text) or ("peppermint crunch" in text and ("junior" in text or "mint" in text or "24" in text or "crunch" in text)):
        return {
            "status": "SUCCESS",
            "meal_name": "Junior Mints Peppermint Crunch (Official FDA Package Label)",
            "vendor_matched": "Junior Mints / Tootsie Roll Industries Official Nutrition Facts",
            "photo_filename": "meal_junior_mints.jpg",
            "calories": 140,
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
            ],
            "glut4_recommendation": "<strong>20-Minute Brisk GLUT4 Walk starting 20 min post-consumption:</strong> Readily blunts projected +50 mg/dL glycemic excursion back into baseline."
        }"""

replacement_server = """    # 01. Junior Mints Peppermint Crunch (Verified Physical Box Nutrition Facts)
    if ("junior mint" in text or "junior mints" in text) or ("peppermint crunch" in text and ("junior" in text or "mint" in text or "box" in text or "120" in text or "26" in text or "crunch" in text)):
        is_whole_box = any(w in text for w in ["box", "whole", "entire", "3.5", "finish", "package", "all", "bag"]) or not any(w in text for w in ["serving", "11 pieces"])
        servings = 3.5 if is_whole_box else 1.0
        cals = round(120 * servings)
        carbs = round(26.0 * servings, 1)
        sugars = round(24.0 * servings, 1)
        fat = round(2.0 * servings, 1)
        pro = 0.0
        fib = 0.0
        net_c = carbs
        spike = round(net_c * 1.9)
        portion_label = "Full 3.5 oz (99g) Box (3.5x Servings Finished)" if is_whole_box else "1 Serving (11 pieces / ~28g)"
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
            "glycemic_load": "70 (Severe Hyperglycemic Risk)" if is_whole_box else "20 (Moderate-High Glycemic Burden)",
            "meets_goal": False,
            "projected_spike": f"+{spike} to +{spike + 15} mg/dL postprandial peak",
            "trade_alternatives": [
                "Swap to Zero-Sugar Allulose Dark Peppermint Bark (2g net carbs, zero sugar alcohols)",
                "ChocZero Peppermint White Chocolate Squares (1g net carbs, monk fruit sweetened)",
                "If eating Junior Mints, limit portion to 1/2 serving (5-6 pieces = 12g carbs) paired with raw walnuts"
            ],
            "gemini_notes": f"🍬 EXACT PHYSICAL BOX LABEL: 1 Serving (11 pieces): 120 kcal, 26g Total Carbs, 0g Fiber, 24g Total Sugars, 2g Fat, 0g Protein. Evaluated portion ({portion_label}): {cals} kcal, {net_c}g Net Carbs ({sugars}g Sugars, {fat}g Fat). Fast sucrose core induces rapid postprandial glucose spike of +{spike} mg/dL.",
            "tips": [
                "Official Box Label: 120 cal, 26g Total Carbs, 24g Sugars, 0g Fiber, 2g Fat, 0g Protein per serving (11 pieces).",
                f"Evaluated portion: {portion_label} = {cals} kcal, {net_c}g Net Carbs ({sugars}g pure fast sugars).",
                "Mandatory GLUT4 walking required to prevent rapid hyperglycemic spike."
            ],
            "glut4_recommendation": f"<strong>{max(25, min(60, round(net_c * 0.65)))}-Minute Brisk GLUT4 Walk starting 15 min post-consumption:</strong> Directly blunts projected +{spike} mg/dL spike."
        }"""

assert target_server in server_py, "target_server not found in server.py"
server_py = server_py.replace(target_server, replacement_server)

with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(server_py)
print("[✔] server.py updated!")
