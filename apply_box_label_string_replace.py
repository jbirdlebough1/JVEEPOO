# apply_box_label_string_replace.py

# 1. Update cell.html
with open('D:/veepoo/w11_htm_app/cell.html', 'r', encoding='utf-8') as f:
    cell_html = f.read()

target_cell_old = """      // 01a. Junior Mints Peppermint Crunch (Official Package Label)
      if (/(?:junior\\s*mints?|peppermint\\s+crunch)/i.test(t)) {
        return {
          meal_name: "Junior Mints Peppermint Crunch (Official FDA Package Label)",
          vendor_matched: "Junior Mints / Tootsie Roll Industries Official Label",
          calories: 130,
          net_carbs: 26.0,
          total_carbs: 27.0,
          fiber: 1.0,
          protein: 0,
          fat: 3.0,
          glycemic_load: "18 (Moderate Glycemic Burden)",
          meets_goal: false,
          projected_spike: "+45 to +60 mg/dL postprandial rise",
          gemini_notes: "🍬 OFFICIAL PACKAGE NUTRITION FACTS: 1.1 oz (31g) serving contains 130 kcal, 27g Carbs, 1g Fiber, 24g Total Sugars -> 26g Net Carbs. Fits under 45g ceiling; 20-min post-snack walk recommended to blunt 24g sucrose spike.",
          trade_alternatives: [
            "Swap to Zero-Sugar Allulose Dark Peppermint Bark (2g net carbs)",
            "ChocZero Peppermint White Chocolate Squares (1g net carbs)"
          ],
          glut4_recommendation: "<strong>20-Minute Brisk GLUT4 Walk starting 20 min post-snack:</strong> Blunts expected +50 mg/dL spike."
        };
      }"""

target_cell_new = """      // 01a. Junior Mints Peppermint Crunch (Verified Physical Box Nutrition Facts)
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
          gemini_notes: `🍬 VERIFIED BOX LABEL: Per serving (11 pieces): 120 kcal, 26g Total Carbs, 0g Fiber, 24g Sugars, 2g Fat, 0g Protein. Portion (${portionLabel}): ${cals} kcal, ${netC}g Net Carbs (${sugars}g Sugars). Fast sucrose core induces +${spike} mg/dL spike.`,
          trade_alternatives: [
            "Swap to Zero-Sugar Allulose Dark Peppermint Bark (2g net carbs)",
            "ChocZero Peppermint White Chocolate Squares (1g net carbs)"
          ],
          glut4_recommendation: `<strong>${Math.max(20, Math.min(60, Math.round(netC * 0.65)))}-Minute Brisk GLUT4 Walk starting 15 min post-consumption:</strong> Required to blunt +${spike} mg/dL spike.`
        };
      }"""

assert target_cell_old in cell_html, "target_cell_old not found in cell.html"
cell_html = cell_html.replace(target_cell_old, target_cell_new)
with open('D:/veepoo/w11_htm_app/cell.html', 'w', encoding='utf-8') as f:
    f.write(cell_html)
print("[✔] cell.html updated via string replace!")

# 2. Update index.html
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    index_html = f.read()

target_index_old = """        } else if (/(?:junior\\s*mints?|peppermint\\s+crunch)/i.test(text)) {
          data = {
            title: "Junior Mints Peppermint Crunch (Official FDA Package Label)",
            desc: rawText,
            calories: 130,
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
            ],
            glut4: "<strong>20-Minute Brisk GLUT4 Walk starting 20 min post-snack:</strong> Blunts anticipated +50 mg/dL spike back into baseline."
          };"""

target_index_new = """        } else if (/(?:junior\\s*mints?|peppermint\\s+crunch)/i.test(text)) {
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
          };"""

assert target_index_old in index_html, "target_index_old not found in index.html"
index_html = index_html.replace(target_index_old, target_index_new)

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(index_html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(index_html)
print("[✔] index.html and w11_htm_app/index.html updated via string replace!")
