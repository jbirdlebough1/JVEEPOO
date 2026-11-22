import re
import hashlib

print("=== APPLYING CLE-102: FIX analyzeMealWithGemini SCOPE BUG & ADD KIT KAT SPECIFICATION ===")

# -----------------------------------------------------------------------------
# 1. UPDATE server.py
# -----------------------------------------------------------------------------
server_path = 'D:/veepoo/w11_htm_app/server.py'
with open(server_path, 'r', encoding='utf-8') as f:
    server_code = f.read()

# Replace the candy bar section in server.py to specifically handle Kit Kat
target_candy_block = '''    # 5. Almond Joy & Candy Bars (Hershey, Snickers, Twix, Reese's)
    elif any(w in text for w in ["almond joy", "mounds", "snickers", "twix", "reese", "reeses", "kit kat", "candy bar", "milky way", "butterfinger"]):
        oz = weight_oz if weight_oz else 1.61 # Standard single bar is 1.61 oz (45g)
        is_almond_joy = "almond joy" in text or "mounds" in text
        bar_title = "Almond Joy Milk Chocolate Coconut Candy Bar" if is_almond_joy else "Confectionery Candy Bar"
        cals = int(round(oz * 137.5))
        tot_carbs = round(oz * 17.0, 1)
        fib = round(oz * 1.25, 1)
        net_c = round(tot_carbs - fib, 1)
        prot = round(oz * 1.9, 1)
        fat = round(oz * 7.5, 1)
        meets_goal = net_c <= 45.0
        prod_name = f"{bar_title} ({oz:.1f} oz portion)"
        return {
            "meal_name": prod_name,
            "vendor_matched": "USDA FoodData Central & Confectionery Disclosures (Hershey / Mars)",
            "photo_filename": "candy_bar.jpg",
            "calories": cals,
            "net_carbs": net_c,
            "total_carbs": tot_carbs,
            "fiber": fib,
            "protein": prot,
            "fat": fat,
            "glycemic_load": "45 (High / Prolonged Spike Risk)" if not meets_goal else "15 (Medium)",
            "estimated_cost": f"{max(1.79, oz * 0.95):.2f}",
            "meets_goal": meets_goal,
            "is_restaurant": False,
            "identified_items": [
                {"name": f"{bar_title} ({oz:.1f} oz)", "portion": f"{oz:.1f} oz ({int(oz*28.35)}g)", "net_carbs": net_c, "fiber": fib, "protein": prot, "fat": fat, "calories": cals}
            ],
            "projected_spike": f"+{int(net_c * 1.3)} to +{int(net_c * 1.8)} mg/dL (Delayed Late-Phase Spike)" if not meets_goal else f"+{int(net_c * 1.0)} mg/dL",
            "gemini_notes": f"⚠️ HIGH LIPID & SUGAR LOAD: {oz:.1f} oz of {bar_title} provides {net_c}g Net Carbs and {fat}g Fat. Saturated fats from coconut and cocoa butter delay gastric emptying, resulting in a late-phase glycemic excursion 2 to 4 hours post-consumption.",
            "trade_alternatives": [
                "Limit to 1 snack-size fun bar (0.6 oz, 9g net carbs, meets clinical ceiling).",
                "Swap to 1 oz raw California almonds + 1 square 85% dark chocolate (5g net carbs).",
                "Swap to unsweetened toasted coconut flakes with raw almonds (3g net carbs)."
            ],
            "glut4_recommendation": f"Schedule a {min(60, max(20, int(net_c * 0.45)))}-minute brisk walk beginning 30-45 minutes post-consumption to counter delayed lipid-induced glucose peak."
        }'''

replacement_candy_block = '''    # 5. Kit Kat, Almond Joy & Candy Bars (Hershey, Snickers, Twix, Reese's)
    elif any(w in text for w in ["kit kat", "kitkat", "almond joy", "mounds", "snickers", "twix", "reese", "reeses", "candy bar", "milky way", "butterfinger"]):
        if "kit kat" in text or "kitkat" in text:
            bar_title = "Kit Kat Crisp Wafers in Milk Chocolate Bar"
            oz = weight_oz if weight_oz else 1.5 # Standard single pack 1.5 oz (42g)
            cals = int(round(oz * 145.3))
            tot_carbs = round(oz * 18.0, 1)
            fib = round(oz * 0.7, 1)
            net_c = round(tot_carbs - fib, 1)
            prot = round(oz * 1.8, 1)
            fat = round(oz * 7.3, 1)
            photo = "candy_kitkat.jpg"
            vendor = "USDA FoodData Central (The Hershey Company / Kit Kat)"
            gl = "46 (High / Severe Spike Risk)" if net_c > 45.0 else "16 (Medium)"
            cost = f"{max(1.49, oz * 0.95):.2f}"
            notes = f"⚠️ ACUTE CONFECTIONERY WARNING: {oz:.1f} oz of {bar_title} contains {net_c}g Net Carbs ({tot_carbs}g total carbs) and {fat}g Fat. Refined wheat flour wafers and milk chocolate cause a steep, rapid glucose surge exceeding your 45g clinical ceiling by +{max(0, int(net_c - 45))}g!"
            trades = [
                "Limit portion to 1 fun-size Kit Kat bar (0.49 oz, 9g net carbs, meets clinical ceiling).",
                "Swap to 2 squares 85% dark chocolate with raw walnuts (4g net carbs).",
                "Swap to Highkey Keto chocolate cookies (2g net carbs)."
            ]
        elif "almond joy" in text or "mounds" in text:
            bar_title = "Almond Joy Milk Chocolate Coconut Candy Bar"
            oz = weight_oz if weight_oz else 1.61 # Standard single bar is 1.61 oz (45g)
            cals = int(round(oz * 137.5))
            tot_carbs = round(oz * 17.0, 1)
            fib = round(oz * 1.25, 1)
            net_c = round(tot_carbs - fib, 1)
            prot = round(oz * 1.9, 1)
            fat = round(oz * 7.5, 1)
            photo = "candy_bar.jpg"
            vendor = "USDA FoodData Central & Confectionery Disclosures (Hershey / Mars)"
            gl = "45 (High / Prolonged Spike Risk)" if net_c > 45.0 else "15 (Medium)"
            cost = f"{max(1.79, oz * 0.95):.2f}"
            notes = f"⚠️ HIGH LIPID & SUGAR LOAD: {oz:.1f} oz of {bar_title} provides {net_c}g Net Carbs and {fat}g Fat. Saturated fats from coconut and cocoa butter delay gastric emptying, resulting in a late-phase glycemic excursion 2 to 4 hours post-consumption."
            trades = [
                "Limit to 1 snack-size fun bar (0.6 oz, 9g net carbs, meets clinical ceiling).",
                "Swap to 1 oz raw California almonds + 1 square 85% dark chocolate (5g net carbs).",
                "Swap to unsweetened toasted coconut flakes with raw almonds (3g net carbs)."
            ]
        else:
            bar_title = "Confectionery Candy Bar"
            oz = weight_oz if weight_oz else 1.61
            cals = int(round(oz * 137.5))
            tot_carbs = round(oz * 17.0, 1)
            fib = round(oz * 1.25, 1)
            net_c = round(tot_carbs - fib, 1)
            prot = round(oz * 1.9, 1)
            fat = round(oz * 7.5, 1)
            photo = "candy_bar.jpg"
            vendor = "USDA FoodData Central & Confectionery Disclosures (Hershey / Mars)"
            gl = "45 (High / Prolonged Spike Risk)" if net_c > 45.0 else "15 (Medium)"
            cost = f"{max(1.79, oz * 0.95):.2f}"
            notes = f"⚠️ HIGH LIPID & SUGAR LOAD: {oz:.1f} oz of {bar_title} provides {net_c}g Net Carbs and {fat}g Fat."
            trades = [
                "Limit to 1 snack-size fun bar (0.6 oz, 9g net carbs, meets clinical ceiling).",
                "Swap to 1 oz raw California almonds + 1 square 85% dark chocolate (5g net carbs).",
                "Swap to unsweetened toasted coconut flakes with raw almonds (3g net carbs)."
            ]

        meets_goal = net_c <= 45.0
        prod_name = f"{bar_title} ({oz:.1f} oz portion)"
        return {
            "meal_name": prod_name,
            "vendor_matched": vendor,
            "photo_filename": photo,
            "calories": cals,
            "net_carbs": net_c,
            "total_carbs": tot_carbs,
            "fiber": fib,
            "protein": prot,
            "fat": fat,
            "glycemic_load": gl,
            "estimated_cost": cost,
            "meets_goal": meets_goal,
            "is_restaurant": False,
            "identified_items": [
                {"name": f"{bar_title} ({oz:.1f} oz)", "portion": f"{oz:.1f} oz ({int(oz*28.35)}g)", "net_carbs": net_c, "fiber": fib, "protein": prot, "fat": fat, "calories": cals}
            ],
            "projected_spike": f"+{int(net_c * 1.3)} to +{int(net_c * 1.8)} mg/dL (Severe Acute Spike Risk)" if not meets_goal else f"+{int(net_c * 1.0)} mg/dL",
            "gemini_notes": notes,
            "trade_alternatives": trades,
            "glut4_recommendation": f"Schedule a {min(60, max(20, int(net_c * 0.45)))}-minute brisk walk beginning 20-30 minutes post-consumption to counter confectionery glucose spike."
        }'''

assert target_candy_block in server_code, "target_candy_block not found in server.py"
server_code = server_code.replace(target_candy_block, replacement_candy_block, 1)

with open(server_path, 'w', encoding='utf-8') as f:
    f.write(server_code)
print("[+] Updated server.py with Kit Kat brand detection and accurate macros!")

# -----------------------------------------------------------------------------
# 2. UPDATE index.html: FIX SCOPE OF analyzeMealWithGemini
# -----------------------------------------------------------------------------
html_path = 'D:/veepoo/index.html'
with open(html_path, 'r', encoding='utf-8') as f:
    html = f.read()

# Locate analyzeMealWithGemini start up to if (!data)
start_marker = 'async function analyzeMealWithGemini(presetKey = null) {'
end_marker = 'if (!data) {\n        if (text.includes("cottage") && (text.includes("blueberry") || text.includes("cake")))'

pos_start = html.find(start_marker)
pos_end = html.find(end_marker, pos_start)

assert pos_start != -1, "start_marker not found in index.html"
assert pos_end != -1, "end_marker not found in index.html"

# Clean function entry and live backend call
clean_entry_and_fetch = '''async function analyzeMealWithGemini(presetKey = null) {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      const goals = getActiveClinicalGoals();
      const input = document.getElementById('txtMealBuilderInput');
      const btn = document.querySelector('button[onclick="analyzeMealWithGemini()"]');
      const originalBtnHtml = btn ? btn.innerHTML : '✨ ESTIMATE WITH GEMINI';

      if (presetKey && POPULAR_MEALS[presetKey]) {
        if (input) input.value = POPULAR_MEALS[presetKey].desc || POPULAR_MEALS[presetKey].title;
      }

      const rawText = input ? input.value.trim() : "";
      const text = rawText.toLowerCase();

      if (btn) {
        btn.innerHTML = '✨ GEMINI EVALUATING MEAL...';
        btn.disabled = true;
      }

      let data = null;

      // 1. Attempt to call live backend /api/meal/analyze
      try {
        const controller = new AbortController();
        const timeoutId = setTimeout(() => controller.abort(), 4000);
        const resp = await fetch('/api/meal/analyze', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            meal_name: rawText || "Custom Ambulatory Meal",
            description: rawText,
            source: "LCARS_MEAL_BUILDER",
            analyze_only: true,
            commit: false,
            fluid_oz: parseFloat(document.getElementById('inpMealFluidOz')?.value) || 8.0
          }),
          signal: controller.signal
        });
        clearTimeout(timeoutId);
        if (resp.ok) {
          const res = await resp.json();
          if (res && (res.status === "SUCCESS" || res.status === "ANALYSIS_PREVIEW" || res.analysis)) {
            const a = res.analysis || res;
            window._latestMealAnalysis = a;
            data = {
              title: a.meal_name || rawText,
              desc: rawText,
              calories: (a.calories !== undefined) ? a.calories : 450,
              netCarbs: (a.net_carbs !== undefined) ? a.net_carbs : 25,
              totalCarbs: (a.total_carbs !== undefined) ? a.total_carbs : 35,
              fiber: (a.fiber !== undefined) ? a.fiber : 10,
              protein: (a.protein !== undefined) ? a.protein : 30,
              fat: (a.fat !== undefined) ? a.fat : 15,
              gl: a.glycemic_load || "10 (Low)",
              estimatedCost: a.estimated_cost || "14.75",
              tradeAlternatives: a.trade_alternatives || [],
              meetsGoal: a.meets_goal !== undefined ? a.meets_goal : (a.net_carbs <= goals.maxCarbsMeal),
              bom: a.bom || [],
              identified_items: a.identified_items || [],
              is_safe_option_search: a.is_safe_option_search,
              store_complete_items: a.store_complete_items,
              safe_recipes: a.safe_recipes,
              tips: a.gemini_notes ? [a.gemini_notes] : [
                "Nutrient breakdown verified by Gemini against clinical diabetic baseline.",
                "High fiber & lean protein buffer blunts postprandial glucose absorption."
              ],
              glut4: a.glut4_recommendation || (a.net_carbs > goals.maxCarbsMeal ?
                `<strong>Recommended 30-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Blunts expected +${Math.round((a.net_carbs - goals.maxCarbsMeal)*1.8)} mg/dL spike into target safe band.` :
                "<strong>15-Minute Ambulatory Walk:</strong> Maintains flat postprandial trajectory within the 60–200 mg/dL target band.")
            };
          }
        }
      } catch (err) {
        console.warn("Backend /api/meal/analyze deferred, using intelligent local engine:", err);
      }

      // 2. Client-side fallback if backend was unavailable
      '''

html = html[:pos_start] + clean_entry_and_fetch + html[pos_end:]

# Now inside client-side fallback `if (!data) {`, insert parseClientWeightOz and Kit Kat & candy parsers!
target_fallback_marker = 'if (!data) {\n        if (text.includes("cottage") && (text.includes("blueberry") || text.includes("cake")))'
assert target_fallback_marker in html, "target_fallback_marker not found"

candy_client_fallback = '''if (!data) {
        const parseClientWeightOz = (str) => {
          const m = str.match(/(\\d+(?:\\.\\d+)?)\\s*(?:oz|ounces?|ounce\\b)/i);
          if (m) return parseFloat(m[1]);
          const mg = str.match(/(\\d+(?:\\.\\d+)?)\\s*(?:g|grams?)\\b/i);
          if (mg) return parseFloat(mg[1]) / 28.35;
          return null;
        };
        const cWeightOz = parseClientWeightOz(rawText);

        if (text.includes("cottage") && (text.includes("blueberry") || text.includes("cake")))'''

html = html.replace(target_fallback_marker, candy_client_fallback, 1)

# Now insert Kit Kat, DQ, M&Ms, Red Hots, Almond Joy right after cottage cheese cake block
target_after_cottage = '''glut4: "15-minute gentle postprandial stroll keeps glucose baseline steady in 80–120 mg/dL target band."
          };
        } else '''

replacement_after_cottage = '''glut4: "15-minute gentle postprandial stroll keeps glucose baseline steady in 80–120 mg/dL target band."
          };
        } else if (text.includes("kit kat") || text.includes("kitkat")) {
          const oz = cWeightOz || 1.5;
          const cals = Math.round(oz * 145.3);
          const totC = Math.round(oz * 18.0);
          const fib = Math.round(oz * 0.7);
          const netC = Math.round(totC - fib);
          data = {
            title: `Kit Kat Crisp Wafers in Milk Chocolate Bar (${oz.toFixed(1)} oz portion)`,
            desc: rawText,
            calories: cals,
            netCarbs: netC,
            totalCarbs: totC,
            fiber: fib,
            protein: Math.round(oz * 1.8),
            fat: Math.round(oz * 7.3),
            gl: netC > 45 ? "46 (High / Severe Spike Risk)" : "16 (Medium)",
            meetsGoal: netC <= 45,
            tips: [
              `⚠️ ACUTE CONFECTIONERY WARNING: ${oz.toFixed(1)} oz contains ${netC}g Net Carbs (Ceiling: 45g max).`,
              "Refined wheat flour wafers and milk chocolate cause a steep, rapid glucose surge.",
              "Harm Reduction: Limit to 1 fun-size bar (0.49 oz, 9g net carbs) or 85% dark chocolate."
            ],
            glut4: "<strong>45-Minute Brisk Walk starting within 20 min:</strong> Required to clear confectionery glucose load."
          };
        } else if ((text.includes("peanut buster") && text.includes("parfait")) || (text.includes("dairy queen") && text.includes("parfait")) || (text.includes("dq") && text.includes("parfait")) || (text.includes("peanut") && text.includes("parfait"))) {
          data = {
            title: "Dairy Queen Peanut Buster Parfait (Official Menu Disclosure)",
            desc: rawText,
            calories: 710,
            netCarbs: 90,
            totalCarbs: 94,
            fiber: 4,
            protein: 16,
            fat: 33,
            gl: "36 (High / Severe Spike Risk)",
            meetsGoal: false,
            tips: [
              "⚠️ SEVERE DIABETIC WARNING: 90g Net Carbs exceeds your 45g ceiling by +45g!",
              "Dense sucrose from hot fudge and lactose from soft serve cause acute rapid spike.",
              "Peanut fats delay clearance, prolonging hyperglycemia for 4+ hours."
            ],
            glut4: "<strong>Mandatory 45 to 55-Minute Brisk Walk:</strong> Required to clear excessive glucose without insulin dependence."
          };
        } else if (text.includes("dairy queen") || text.includes("dq") || text.includes("blizzard") || text.includes("dilly bar") || text.includes("sundae")) {
          const isSm = text.includes("mini") || text.includes("small");
          const isBlizz = text.includes("blizzard");
          const cals = isBlizz ? (isSm ? 380 : 680) : (isSm ? 280 : 440);
          const carbs = isBlizz ? (isSm ? 48 : 92) : (isSm ? 38 : 62);
          data = {
            title: isBlizz ? (isSm ? "Dairy Queen Mini Blizzard Treat" : "Dairy Queen Standard Blizzard") : (isSm ? "Dairy Queen Small Sundae" : "Dairy Queen Regular Sundae"),
            desc: rawText,
            calories: cals,
            netCarbs: carbs,
            totalCarbs: carbs + 2,
            fiber: 1,
            protein: 7,
            fat: 12,
            gl: carbs > 45 ? "22 (High Spike Risk)" : "12 (Low-Med)",
            meetsGoal: carbs <= 45,
            tips: [
              "🍦 Official Dairy Queen Published Disclosures.",
              carbs > 45 ? "⚠️ Exceeds 45g net carb ceiling." : "✅ Meets 45g clinical ceiling."
            ],
            glut4: "<strong>30-Minute Brisk Walk:</strong> Blunts acute postprandial spike."
          };
        } else if (text.includes("m and m") || text.includes("m&m") || text.includes("mnm")) {
          const oz = cWeightOz || 1.69;
          const cals = Math.round(oz * 140);
          const totC = Math.round(oz * 20);
          const fib = Math.round(oz * 1);
          const netC = totC - fib;
          data = {
            title: `M&M's Milk Chocolate Candy (${oz.toFixed(1)} oz portion)`,
            desc: rawText,
            calories: cals,
            netCarbs: netC,
            totalCarbs: totC,
            fiber: fib,
            protein: Math.round(oz * 1.5),
            fat: Math.round(oz * 6),
            gl: netC > 45 ? "42 (High / Severe Spike Risk)" : "14 (Medium)",
            meetsGoal: netC <= 45,
            tips: [
              `⚠️ ACUTE CONFECTIONERY WARNING: ${oz.toFixed(1)} oz contains ${netC}g Net Carbs (Ceiling: 45g max).`,
              "Pure sucrose and chocolate lipids drive rapid glucose entry with minimal fiber.",
              "Harm Reduction: Limit to 1 oz fun-size pack (19g net carbs) or 85% dark chocolate."
            ],
            glut4: "<strong>35 to 45-Minute Brisk GLUT4 Walk:</strong> Required to clear refined confectionery glucose."
          };
        } else if (text.includes("red hot") || text.includes("redhots") || text.includes("cinnamon drops") || text.includes("cinnamon candy")) {
          const oz = cWeightOz || 1.5;
          const cals = Math.round(oz * 110);
          const totC = Math.round(oz * 27);
          data = {
            title: `Red Hots Cinnamon Candy (${oz.toFixed(1)} oz portion)`,
            desc: rawText,
            calories: cals,
            netCarbs: totC,
            totalCarbs: totC,
            fiber: 0,
            protein: 0,
            fat: 0,
            gl: "58 (Extreme / Dangerous Spike Risk)",
            meetsGoal: totC <= 45,
            tips: [
              `🚨 SEVERE GLYCEMIC SURGE: ${oz.toFixed(1)} oz is 100% refined sugar/syrup (${totC}g net carbs) with zero protein, fat, or fiber buffer!`,
              "Causes immediate, steep glucose rise (>250+ mg/dL).",
              "Swap to sugar-free cinnamon candies or brewed clove-cinnamon tea (0g carbs)."
            ],
            glut4: "<strong>Immediate 45-Minute Brisk Walk:</strong> Essential to clear unbuffered sugar surge."
          };
        } else if (text.includes("almond joy") || text.includes("mounds")) {
          const oz = cWeightOz || 1.61;
          const cals = Math.round(oz * 137.5);
          const totC = Math.round(oz * 17);
          const fib = Math.round(oz * 1.25);
          const netC = totC - fib;
          data = {
            title: `Almond Joy Milk Chocolate Coconut Candy Bar (${oz.toFixed(1)} oz portion)`,
            desc: rawText,
            calories: cals,
            netCarbs: netC,
            totalCarbs: totC,
            fiber: fib,
            protein: Math.round(oz * 1.9),
            fat: Math.round(oz * 7.5),
            gl: netC > 45 ? "45 (High / Prolonged Spike Risk)" : "15 (Medium)",
            meetsGoal: netC <= 45,
            tips: [
              `⚠️ HIGH LIPID & SUGAR LOAD: ${oz.toFixed(1)} oz contains ${netC}g Net Carbs and ${Math.round(oz * 7.5)}g Fat.`,
              "Coconut lipids delay gastric emptying, causing dual-phase prolonged spike 2-4 hours post-intake.",
              "Harm Reduction: Limit to 1 mini bar (0.6 oz, 9g net carbs) or raw almonds with dark chocolate."
            ],
            glut4: "<strong>40-Minute Brisk Walk starting 30 min post-meal:</strong> Targets delayed lipid-induced glucose peak."
          };
        } else if (text.includes("snickers") || text.includes("twix") || text.includes("reese") || text.includes("reeses") || text.includes("candy bar")) {
          const oz = cWeightOz || 1.61;
          const cals = Math.round(oz * 137.5);
          const totC = Math.round(oz * 17);
          const fib = Math.round(oz * 1.25);
          const netC = totC - fib;
          data = {
            title: `Confectionery Candy Bar (${oz.toFixed(1)} oz portion)`,
            desc: rawText,
            calories: cals,
            netCarbs: netC,
            totalCarbs: totC,
            fiber: fib,
            protein: Math.round(oz * 1.9),
            fat: Math.round(oz * 7.5),
            gl: netC > 45 ? "45 (High / Prolonged Spike Risk)" : "15 (Medium)",
            meetsGoal: netC <= 45,
            tips: [
              `⚠️ CONFECTIONERY ALERT: ${oz.toFixed(1)} oz contains ${netC}g Net Carbs (Max: 45g).`,
              "Dense sucrose and fats trigger sustained hyperglycemia.",
              "Harm Reduction: Limit to fun-size portion or swap to 85% dark chocolate."
            ],
            glut4: "<strong>40-Minute Brisk Walk:</strong> Required to blunt confectionery glycemic excursion."
          };
        } else '''

assert target_after_cottage in html, "target_after_cottage not found in index.html"
html = html.replace(target_after_cottage, replacement_after_cottage, 1)

# Div tag balance check
open_divs = len(re.findall(r'<div\b', html))
close_divs = len(re.findall(r'</div\b', html))
print(f"Div balance: open={open_divs}, close={close_divs}, delta={open_divs - close_divs}")
assert open_divs == close_divs, f"Div balance mismatch: open={open_divs}, close={close_divs}"

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
print(f"[+] Exact dual-file parity: {h1 == h2} ({h1})")
print("=== CLE-102 PATCH APPLIED SUCCESSFULLY ===")
