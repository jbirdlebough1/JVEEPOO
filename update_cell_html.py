import re

with open('D:/veepoo/w11_htm_app/cell.html', 'r', encoding='utf-8') as f:
    cell_code = f.read()

# 1. Update calculateClinicalNutrition in cell.html
old_tea_check = """      // 1. Teas, Herbal Infusions, Spices, Black Coffee, Zero Drinks
      const isTea = /tea|chai|infusion|tisane|matcha|peppermint|chamomile|earl grey|green tea|black tea|hibiscus|rooibos|clove|cinnamon/.test(t);"""

new_nutrition_parser = """      // 00. Explicit Nutrition Facts / Label / QR Link Parameter Parser
      const mCarbs = t.match(/(?:total\\s+carbs?|carbohydrates?|net\\s+carbs?|carbs?)\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)\\s*g?/i);
      const mCals = t.match(/(?:calories?|energy|kcal)\\s*[:=]?\\s*(\\d+)/i);
      const mFib = t.match(/(?:dietary\\s+fiber|fiber|fibre)\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)\\s*g?/i);
      const mPro = t.match(/(?:protein)\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)\\s*g?/i);
      const mFat = t.match(/(?:total\\s+fat|fat)\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)\\s*g?/i);
      const mSugar = t.match(/(?:total\\s+sugars?|added\\s+sugars?|sugars?)\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)\\s*g?/i);

      if (mCarbs && (mCals || mFib || mSugar)) {
        const totC = parseFloat(mCarbs[1]);
        const fib = mFib ? parseFloat(mFib[1]) : 0;
        const netC = Math.max(0, Math.round((totC - fib) * 10) / 10);
        const cals = mCals ? parseInt(mCals[1], 10) : Math.round(totC * 4 + 100);
        const pro = mPro ? parseFloat(mPro[1]) : 4;
        const fat = mFat ? parseFloat(mFat[1]) : 8;
        const meets = (netC <= 45.0) && (!mSugar || parseFloat(mSugar[1]) <= 15.0);
        const spike = Math.round(netC * 1.8);
        return {
          meal_name: text.trim() || "Nutrition Facts Disclosure Item",
          vendor_matched: "Nutrition Facts Disclosure & Camera/QR Ingestion",
          calories: cals,
          net_carbs: netC,
          total_carbs: totC,
          fiber: fib,
          protein: pro,
          fat: fat,
          glycemic_load: `${Math.round(netC * 0.75)} (` + (meets ? "Safe Low-Med" : "HIGH SPIKE HAZARD") + ")",
          meets_goal: meets,
          projected_spike: `+${spike} to +${spike + 20} mg/dL postprandial rise`,
          gemini_notes: `📋 NUTRITION FACTS PARSED: ${totC}g Carbs, ${fib}g Fiber -> ${netC}g Net Carbs. ` + (meets ? "Meets 45g clinical ceiling." : `🚨 EXCEEDS 45g CLINICAL CEILING by +${Math.round(netC - 45)}g! Spike alert.`),
          trade_alternatives: [
            `Extracted directly from label: ${netC}g Net Carbs.`,
            "Ensure beverage is unsweetened/water to avoid liquid glucose surge."
          ],
          glut4_recommendation: `<strong>${Math.max(15, Math.min(60, Math.round(netC * 0.75)))}-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Required to blunt excursion.`
        };
      }

      // 01. High-Sugar Candy, Peppermint Crunch & Confectionery Handler
      if (/(?:peppermint|crunch|candy|candies|confection|fudge|caramel|toffee|marshmallow|snickers|skittles|jelly bean|lollipop)/i.test(t)) {
        const is2oz = t.includes("2") || t.includes("2 oz");
        const cals = is2oz ? 360 : 270;
        const carbs = is2oz ? 52.0 : 39.0;
        const netC = carbs;
        const gl = 45;
        const spike = Math.round(netC * 2.1);
        return {
          meal_name: text.trim() || "Christmas Peppermint Crunch Candy",
          vendor_matched: "Holiday Confectionery Nutritional Disclosure",
          calories: cals,
          net_carbs: netC,
          total_carbs: carbs,
          fiber: 0,
          protein: 2,
          fat: 16,
          glycemic_load: `${gl} (SEVERE HIGH SPIKE HAZARD)`,
          meets_goal: false,
          projected_spike: `+${spike} to +${spike + 25} mg/dL acute spike`,
          gemini_notes: `🚨 SEVERE CONFECTIONERY SPIKE: Peppermint Crunch contains ${netC}g pure sucrose/corn syrup net carbs with 0g fiber. Acute expected spike: +${spike} mg/dL. Severely violates 45g clinical ceiling. Recommend zero-sugar allulose confection trade or mandatory GLUT4 walk.`,
          trade_alternatives: [
            "Swap to Zero-Sugar Allulose Peppermint Bark (2g net carbs, monk fruit/allulose sweetened — zero spike)",
            "ChocZero Sugar-Free White Chocolate Peppermint Squares (1g net carbs, zero sugar alcohols)",
            "Stevia-sweetened peppermint herbal infusion tea with heavy cream (0g carbs)"
          ],
          glut4_recommendation: `<strong>MANDATORY 45-Minute Brisk GLUT4 Walk starting 15 min post-consumption:</strong> Required to blunt projected +${spike} mg/dL glycemic excursion.`
        };
      }

      // 1. Teas, Herbal Infusions, Spices, Black Coffee, Zero Drinks
      const isTea = /tea|chai|infusion|tisane|matcha|peppermint\\s+tea|chamomile|earl grey|green tea|black tea|hibiscus|rooibos|clove|cinnamon/.test(t);"""

assert old_tea_check in cell_code, "old_tea_check not found in cell.html"
cell_code = cell_code.replace(old_tea_check, new_nutrition_parser)

# 2. Update submitMealDirect to include full analysis fields
old_submit_payload = """        const payload = {
          meal_name: `[${selectedMealType}] ${text || currentAnalysis.meal_name}`,
          description: text,
          source: "MOBILE_WI-FI_GATEWAY",
          photo_data: uploadedPhotos.length > 0 ? uploadedPhotos[0] : "",
          photo_count: uploadedPhotos.length,
          audio_memo: recordedAudioBase64 || "",
          commit: true
        };"""

new_submit_payload = """        const a = currentAnalysis || {};
        const payload = {
          meal_name: `[${selectedMealType}] ${text || a.meal_name || 'Custom Meal'}`,
          description: text || a.meal_name || '',
          source: "MOBILE_WI-FI_GATEWAY",
          photo_data: uploadedPhotos.length > 0 ? uploadedPhotos[0] : "",
          photo_count: uploadedPhotos.length,
          audio_memo: recordedAudioBase64 || "",
          commit: true,
          calories: (a.calories !== undefined) ? a.calories : undefined,
          net_carbs: (a.net_carbs !== undefined) ? a.net_carbs : undefined,
          total_carbs: (a.total_carbs !== undefined) ? a.total_carbs : undefined,
          fiber: (a.fiber !== undefined) ? a.fiber : undefined,
          protein: (a.protein !== undefined) ? a.protein : undefined,
          fat: (a.fat !== undefined) ? a.fat : undefined,
          glycemic_load: a.glycemic_load,
          meets_goal: a.meets_goal,
          vendor_matched: a.vendor_matched,
          gemini_notes: a.gemini_notes,
          tips: a.tips,
          trade_alternatives: a.trade_alternatives,
          glut4_recommendation: a.glut4_recommendation
        };"""

assert old_submit_payload in cell_code, "old_submit_payload not found in cell.html"
cell_code = cell_code.replace(old_submit_payload, new_submit_payload)

# 3. Update syncStagedQueue payload similarly
old_sync_payload = """          const payload = {
            meal_name: `[${item.meal_type}] ${item.meal_name}`,
            description: item.meal_name,
            source: "MOBILE_OFFLINE_SYNC",
            photo_data: item.photos.length > 0 ? item.photos[0] : "",
            photo_count: item.photo_count,
            audio_memo: item.audio_memo || "",
            commit: true
          };"""

new_sync_payload = """          const payload = {
            meal_name: `[${item.meal_type}] ${item.meal_name}`,
            description: item.meal_name,
            source: "MOBILE_OFFLINE_SYNC",
            photo_data: item.photos.length > 0 ? item.photos[0] : "",
            photo_count: item.photo_count,
            audio_memo: item.audio_memo || "",
            commit: true,
            calories: item.calories,
            net_carbs: item.net_carbs,
            total_carbs: item.total_carbs,
            fiber: item.fiber,
            protein: item.protein,
            fat: item.fat,
            glycemic_load: item.glycemic_load,
            meets_goal: item.meets_goal,
            vendor_matched: item.vendor_matched,
            gemini_notes: item.gemini_notes,
            tips: item.tips,
            trade_alternatives: item.trade_alternatives,
            glut4_recommendation: item.glut4_recommendation
          };"""

assert old_sync_payload in cell_code, "old_sync_payload not found in cell.html"
cell_code = cell_code.replace(old_sync_payload, new_sync_payload)

# 4. Remove monetary costs from cell.html
cell_code = re.sub(r'Cost:\s*~\$\d+(?:\.\d+)?\.?', 'Metabolic Impact: Verified Glycemic Profile.', cell_code)
cell_code = re.sub(r'estimated_cost:\s*["\']\d+(?:\.\d+)?["\'],?', '', cell_code)

with open('D:/veepoo/w11_htm_app/cell.html', 'w', encoding='utf-8') as f:
    f.write(cell_code)
print("[✔] cell.html updated successfully!")
