def patch_analyze_details(file_path):
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()

    # 1. Ensure data object from server gets estimated_cost and trade_alternatives
    old_data_mapping = """data = {
              title: a.meal_name || rawText,
              desc: rawText,
              calories: a.calories || 450,
              netCarbs: a.net_carbs || 25,
              totalCarbs: a.total_carbs || 35,
              fiber: a.fiber || 10,
              protein: a.protein || 30,
              fat: a.fat || 15,
              gl: a.glycemic_load || "10 (Low)",
              meetsGoal: a.meets_goal !== undefined ? a.meets_goal : (a.net_carbs <= goals.maxCarbsMeal),
              tips: a.gemini_notes ? [a.gemini_notes] : [
                "Nutrient breakdown verified by Gemini against clinical diabetic baseline.",
                "High fiber & lean protein buffer blunts postprandial glucose absorption."
              ],
              glut4: a.glut4_recommendation || (a.net_carbs > goals.maxCarbsMeal ?
                `<strong>Recommended 30-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Blunts expected +${Math.round((a.net_carbs - goals.maxCarbsMeal)*1.8)} mg/dL spike into target safe band.` :
                "<strong>15-Minute Ambulatory Walk:</strong> Maintains flat postprandial trajectory within the 60–200 mg/dL target band.")
            };"""

    new_data_mapping = """data = {
              title: a.meal_name || rawText,
              desc: rawText,
              calories: a.calories || 450,
              netCarbs: a.net_carbs || 25,
              totalCarbs: a.total_carbs || 35,
              fiber: a.fiber || 10,
              protein: a.protein || 30,
              fat: a.fat || 15,
              gl: a.glycemic_load || "10 (Low)",
              estimatedCost: a.estimated_cost || "14.75",
              tradeAlternatives: a.trade_alternatives || [],
              meetsGoal: a.meets_goal !== undefined ? a.meets_goal : (a.net_carbs <= goals.maxCarbsMeal),
              tips: a.gemini_notes ? [a.gemini_notes] : [
                "Nutrient breakdown verified by Gemini against clinical diabetic baseline.",
                "High fiber & lean protein buffer blunts postprandial glucose absorption."
              ],
              glut4: a.glut4_recommendation || (a.net_carbs > goals.maxCarbsMeal ?
                `<strong>Recommended 30-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Blunts expected +${Math.round((a.net_carbs - goals.maxCarbsMeal)*1.8)} mg/dL spike into target safe band.` :
                "<strong>15-Minute Ambulatory Walk:</strong> Maintains flat postprandial trajectory within the 60–200 mg/dL target band.")
            };"""

    if old_data_mapping in content:
        content = content.replace(old_data_mapping, new_data_mapping)

    # 2. Update the DOM elements in analyzeMealWithGemini
    target_dom_end = """const descGlut4 = document.getElementById('descGeminiGlut4');
      if (descGlut4 && data.glut4) {
        descGlut4.innerHTML = data.glut4;
      }"""

    replacement_dom_end = """const descGlut4 = document.getElementById('descGeminiGlut4');
      if (descGlut4 && data.glut4) {
        descGlut4.innerHTML = data.glut4;
      }

      // Pre-Check Metabolic Impact & Associated Costs
      const elCost = document.getElementById('lblGeminiCost');
      if (elCost) elCost.textContent = `EST. COST: ~$${data.estimatedCost || "14.75"}`;

      const elSpike = document.getElementById('lblGeminiSpike');
      if (elSpike) {
        const spike = Math.round(data.netCarbs * 0.95);
        elSpike.innerHTML = `&bull; <strong>Projected Spike:</strong> +${spike} to +${spike+20} mg/dL postprandial excursion (${data.netCarbs > goals.maxCarbsMeal ? "⚠️ TIR Risk: High — Exceeds 180 mg/dL ceiling" : "✅ TIR Target Maintained"}).`;
      }

      const elBioCost = document.getElementById('lblGeminiBioCost');
      if (elBioCost) {
        elBioCost.innerHTML = `&bull; <strong>Metabolic Impact:</strong> ${data.netCarbs > goals.maxCarbsMeal ? "Elevated beta-cell insulin demand & postprandial glycemic volatility." : "Minimal glycemic impact; preserves pancreatic beta-cell reserve."}`;
      }

      // Pre-Choice Harm Reduction Alternatives
      const containerTrades = document.getElementById('containerGeminiTradeOptions');
      if (containerTrades) {
        if (data.tradeAlternatives && data.tradeAlternatives.length > 0) {
          containerTrades.innerHTML = data.tradeAlternatives.map(trade => `
            <button type="button" class="lcars-btn" style="background: #f1f5f9; color: #0f172a; border: 1px solid #94a3b8; font-size: 0.80rem; padding: 6px 12px; margin-bottom: 4px; width: 100%; text-align: left;" onclick="applyPreChoiceSwap('${trade.replace(/'/g, "\\'")}')">
              💡 ${trade}
            </button>
          `).join("");
        } else if (data.netCarbs > goals.maxCarbsMeal) {
          containerTrades.innerHTML = `
            <button type="button" class="lcars-btn" style="background: #f1f5f9; color: #0f172a; border: 1px solid #94a3b8; font-size: 0.80rem; padding: 6px 12px; margin-bottom: 4px; width: 100%; text-align: left;" onclick="applyPreChoiceSwap('Reduce portion by half (-${Math.round(data.netCarbs * 0.5)}g Net Carbs)')">
              🍽️ <strong>Split Portion:</strong> Eat half now, save remainder -> Brings meal within target goal.
            </button>
            <button type="button" class="lcars-btn" style="background: #f1f5f9; color: #0f172a; border: 1px solid #94a3b8; font-size: 0.80rem; padding: 6px 12px; margin-bottom: 4px; width: 100%; text-align: left;" onclick="applyPreChoiceSwap('Swap side for green salad or roasted vegetables')">
              🥗 <strong>Fibrous Vegetable Swap:</strong> Adds fiber buffer and reduces rapid starch absorption.
            </button>
          `;
        }
      }"""

    if target_dom_end in content:
        content = content.replace(target_dom_end, replacement_dom_end)

    with open(file_path, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"Patched analyze details in {file_path}")

patch_analyze_details(r"D:/veepoo/index.html")
patch_analyze_details(r"D:/veepoo/w11_htm_app/index.html")
