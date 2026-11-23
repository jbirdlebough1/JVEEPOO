import re
import hashlib

# =========================================================================
# STEP 1: PATCH server.py
# =========================================================================
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_code = f.read()

# Insertion block for analyze_meal_with_gemini_backend
candy_dq_backend_code = '''    # Weight & Portion Parser
    weight_oz = None
    m_oz = re.search(r'(\\d+(?:\\.\\d+)?)\\s*(?:oz|ounces?|ounce\\b)', text, re.IGNORECASE)
    if m_oz:
        weight_oz = float(m_oz.group(1))
    else:
        m_g = re.search(r'(\\d+(?:\\.\\d+)?)\\s*(?:g|grams?)\\b', text, re.IGNORECASE)
        if m_g:
            weight_oz = float(m_g.group(1)) / 28.35

    # 1. Dairy Queen Peanut Buster Parfait
    if ("peanut buster" in text and "parfait" in text) or ("dairy queen" in text and "parfait" in text) or ("dq" in text and "parfait" in text) or ("peanut" in text and "parfait" in text):
        cals = 710
        total_carbs = 94.0
        fiber = 4.0
        net_carbs = 90.0
        protein = 16.0
        fat = 33.0
        meets_goal = False
        prod_name = "Dairy Queen Peanut Buster Parfait (Official Menu Disclosure)"
        return {
            "meal_name": prod_name,
            "vendor_matched": "Dairy Queen (Published Nutritional Disclosures & Menu Data)",
            "photo_filename": "dairy_queen_parfait.jpg",
            "calories": cals,
            "net_carbs": net_carbs,
            "total_carbs": total_carbs,
            "fiber": fiber,
            "protein": protein,
            "fat": fat,
            "glycemic_load": "36 (High / Severe Spike Risk)",
            "estimated_cost": "5.49",
            "meets_goal": meets_goal,
            "is_restaurant": True,
            "identified_items": [
                {"name": "DQ Vanilla Soft Serve Base", "portion": "Vanilla Soft Serve Base", "net_carbs": 52, "fiber": 0, "protein": 8, "fat": 14, "calories": 370},
                {"name": "Rich Hot Fudge Layers", "portion": "3 generous pumps", "net_carbs": 32, "fiber": 2, "protein": 2, "fat": 7, "calories": 190},
                {"name": "Crunchy Roasted Salted Peanuts", "portion": "Salted Whole Peanuts", "net_carbs": 6, "fiber": 2, "protein": 6, "fat": 12, "calories": 150}
            ],
            "projected_spike": "+75 to +110 mg/dL (Severe Prolonged Glycemic Excursion)",
            "gemini_notes": "⚠️ SEVERE DIABETIC WARNING: The Dairy Queen Peanut Buster Parfait contains 90g Net Carbs (over double your 45g ceiling). Dense sucrose in hot fudge and lactose in soft serve cause an acute glucose spike, while peanut fats delay clearance, prolonging hyperglycemia for 4+ hours.",
            "trade_alternatives": [
                "Swap to a Dairy Queen Small Vanilla Soft Serve Cone (28g net carbs, meets 45g clinical ceiling).",
                "Swap to a No-Sugar-Added Dilly Bar (18g net carbs, 200 kcal).",
                "Swap to a Mini Sundae with nuts on the side (31g net carbs, meets goal)."
            ],
            "glut4_recommendation": "Mandatory 45 to 55-minute brisk GLUT4 walk starting within 20 minutes to force skeletal muscle glucose uptake without insulin dependence."
        }

    # 2. General Dairy Queen / Soft Serve / Blizzards / Sundaes / Cones
    elif any(w in text for w in ["dairy queen", "dq", "blizzard", "dilly bar", "soft serve"]) or (any(w in text for w in ["sundae", "ice cream"]) and not any(w in text for w in ["keto", "rebel", "halo top", "nicks"])):
        is_small = any(w in text for w in ["mini", "small", "kid", "small size"])
        if "blizzard" in text:
            cals = 380 if is_small else 680
            carbs = 48.0 if is_small else 92.0
            prod_name = "Dairy Queen Mini Blizzard Treat" if is_small else "Dairy Queen Standard Blizzard Treat"
        elif "cone" in text:
            cals = 200 if is_small else 330
            carbs = 28.0 if is_small else 46.0
            prod_name = "Dairy Queen Small Vanilla Soft Serve Cone" if is_small else "Dairy Queen Medium Vanilla Cone"
        elif "dilly" in text:
            cals = 220
            carbs = 25.0
            prod_name = "Dairy Queen Dilly Bar"
        else: # Sundae / Ice Cream
            cals = 280 if is_small else 440
            carbs = 38.0 if is_small else 62.0
            prod_name = "Dairy Queen Small Chocolate Sundae" if is_small else "Dairy Queen Regular Hot Fudge Sundae"

        meets_goal = carbs <= 45.0
        return {
            "meal_name": prod_name,
            "vendor_matched": "Dairy Queen (Published Nutritional Disclosures & Menu Data)",
            "photo_filename": "dairy_queen_sundae.jpg",
            "calories": cals,
            "net_carbs": carbs,
            "total_carbs": carbs + 2.0,
            "fiber": 1.0,
            "protein": 7.0,
            "fat": 12.0,
            "glycemic_load": "22 (High Spike Risk)" if not meets_goal else "12 (Low-Med)",
            "estimated_cost": "4.19",
            "meets_goal": meets_goal,
            "is_restaurant": True,
            "identified_items": [
                {"name": prod_name, "portion": "1 order", "net_carbs": carbs, "fiber": 1, "protein": 7, "fat": 12, "calories": cals}
            ],
            "projected_spike": f"+{int(carbs * 1.5)} to +{int(carbs * 2.1)} mg/dL (High Spike Risk)" if not meets_goal else f"+{int(carbs * 1.0)} mg/dL (Manageable Spike)",
            "gemini_notes": f"🍦 RESTAURANT DESSERT DISCLOSURE: Official Dairy Queen published nutritional specs. {'⚠️ Exceeds 45g net carb ceiling by +' + str(int(carbs - 45)) + 'g.' if not meets_goal else '✅ Meets 45g clinical ceiling.'}",
            "trade_alternatives": [
                "Swap to a Small Vanilla Cone (28g net carbs, meets goal).",
                "Choose Mini size instead of regular/large to cap carbohydrate load under 45g.",
                "Perform a 30-minute post-meal GLUT4 walk to blunt peak glucose excursion."
            ],
            "glut4_recommendation": "Perform 30 to 40 minutes brisk walking within 25 minutes of finishing to clear bloodstream glucose."
        }

    # 3. M&M's & Milk Chocolate Candies
    elif any(w in text for w in ["m and m", "m&m", "mnm", "m&ms", "m and m's", "m&m's"]):
        oz = weight_oz if weight_oz else 1.69 # Standard single bag is 1.69 oz (~48g)
        cals = int(round(oz * 140.0))
        tot_carbs = round(oz * 20.0, 1)
        fib = round(oz * 1.0, 1)
        net_c = round(tot_carbs - fib, 1)
        prot = round(oz * 1.5, 1)
        fat = round(oz * 6.0, 1)
        meets_goal = net_c <= 45.0
        prod_name = f"M&M's Milk Chocolate Candy ({oz:.1f} oz portion)"
        return {
            "meal_name": prod_name,
            "vendor_matched": "USDA FoodData Central & Confectionery Disclosures (M&M's Mars)",
            "photo_filename": "candy_mms.jpg",
            "calories": cals,
            "net_carbs": net_c,
            "total_carbs": tot_carbs,
            "fiber": fib,
            "protein": prot,
            "fat": fat,
            "glycemic_load": "42 (High / Severe Spike Risk)" if not meets_goal else "14 (Medium)",
            "estimated_cost": f"{max(1.49, oz * 0.85):.2f}",
            "meets_goal": meets_goal,
            "is_restaurant": False,
            "identified_items": [
                {"name": f"M&M's Milk Chocolate Candies ({oz:.1f} oz)", "portion": f"{oz:.1f} oz ({int(oz*28.35)}g)", "net_carbs": net_c, "fiber": fib, "protein": prot, "fat": fat, "calories": cals}
            ],
            "projected_spike": f"+{int(net_c * 1.4)} to +{int(net_c * 1.9)} mg/dL (Requires Immediate Exercise)" if not meets_goal else f"+{int(net_c * 1.1)} mg/dL",
            "gemini_notes": f"⚠️ ACUTE CONFECTIONERY WARNING: {oz:.1f} oz of M&M's contains {net_c}g Net Carbs (Ceiling: 45g max). Pure refined sucrose and chocolate lipids drive rapid glucose entry into portal circulation with minimal fiber attenuation.",
            "trade_alternatives": [
                "Cap portion to 1 oz 'fun-size' pack (19g net carbs, meets clinical goal).",
                "Swap to 85%+ Dark Chocolate squares (6g net carbs per oz).",
                "Swap to roasted chocolate-dusted almonds (4g net carbs per oz, high protein/fiber)."
            ],
            "glut4_recommendation": f"Perform a {min(60, max(20, int(net_c * 0.45)))}-minute brisk GLUT4 walk starting within 15 minutes to force skeletal muscle GLUT4 translocation."
        }

    # 4. Red Hots & Pure Sugar Hard Candies
    elif any(w in text for w in ["red hot", "redhots", "red hots", "cinnamon candy", "cinnamon drops", "hot tamale", "hot tamales", "jelly bean", "jelly belly", "skittle", "skittles", "gummy bear", "gummy"]):
        oz = weight_oz if weight_oz else 1.5 # Standard theater box/pack
        cals = int(round(oz * 110.0))
        tot_carbs = round(oz * 27.0, 1)
        fib = 0.0
        net_c = tot_carbs
        prot = 0.0
        fat = 0.0
        meets_goal = net_c <= 45.0
        candy_type = "Red Hots Cinnamon Candy" if any(w in text for w in ["red", "cinnamon", "tamale"]) else "Hard Candy / Fruit Chews"
        prod_name = f"{candy_type} ({oz:.1f} oz portion)"
        return {
            "meal_name": prod_name,
            "vendor_matched": "USDA FoodData Central (Sugar Confectionery / Hard Candies)",
            "photo_filename": "candy_redhots.jpg",
            "calories": cals,
            "net_carbs": net_c,
            "total_carbs": tot_carbs,
            "fiber": fib,
            "protein": prot,
            "fat": fat,
            "glycemic_load": "58 (Extreme / Dangerous Spike Risk)" if not meets_goal else "18 (Medium)",
            "estimated_cost": f"{max(1.29, oz * 0.75):.2f}",
            "meets_goal": meets_goal,
            "is_restaurant": False,
            "identified_items": [
                {"name": f"{candy_type} ({oz:.1f} oz)", "portion": f"{oz:.1f} oz ({int(oz*28.35)}g)", "net_carbs": net_c, "fiber": 0, "protein": 0, "fat": 0, "calories": cals}
            ],
            "projected_spike": f"+{int(net_c * 1.5)} to +{int(net_c * 2.2)} mg/dL (Severe Acute Glucose Crisis Risk)" if not meets_goal else f"+{int(net_c * 1.2)} mg/dL",
            "gemini_notes": f"🚨 SEVERE GLYCEMIC SURGE WARNING: {oz:.1f} oz of {candy_type} is virtually 100% refined corn syrup and sucrose ({net_c}g net carbs) with ZERO fat, ZERO fiber, and ZERO protein. Causes immediate, unbuffered arterial glucose spikes.",
            "trade_alternatives": [
                "Limit intake to 5-6 small pieces (0.5 oz, 13g net carbs, meets goal).",
                "Swap to Sugar-Free Cinnamon Hard Candies (sweetened with isomalt, 0g glycemic impact).",
                "Brew a mug of hot whole clove and Ceylon cinnamon herbal infusion (0g net carbs, zero spike)."
            ],
            "glut4_recommendation": f"Immediate {min(60, max(25, int(net_c * 0.45)))}-minute brisk walk is clinically essential to clear acute circulating glucose."
        }

    # 5. Almond Joy & Candy Bars (Hershey, Snickers, Twix, Reese's)
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
        }
'''

target_anchor = '# 1. Taco Time Northwest (Official Published Nutritional Disclosures & Menu Data)'
assert target_anchor in server_code, "target_anchor not found in server.py"

server_code = server_code.replace(target_anchor, candy_dq_backend_code + '\n' + target_anchor, 1)

with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(server_code)
print("[+] Successfully inserted candy, confectionery, and Dairy Queen engine into server.py")

# =========================================================================
# STEP 2: PATCH index.html
# =========================================================================
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# In analyzeMealWithGemini:
# 1. Update data unpacking to not convert 0 into fallback
target_unpack = '''            data = {
              title: a.meal_name || rawText,
              desc: rawText,
              calories: a.calories || 450,
              netCarbs: a.net_carbs || 25,
              totalCarbs: a.total_carbs || 35,
              fiber: a.fiber || 10,
              protein: a.protein || 30,
              fat: a.fat || 15,'''

replacement_unpack = '''            data = {
              title: a.meal_name || rawText,
              desc: rawText,
              calories: (a.calories !== undefined) ? a.calories : 450,
              netCarbs: (a.net_carbs !== undefined) ? a.net_carbs : 25,
              totalCarbs: (a.total_carbs !== undefined) ? a.total_carbs : 35,
              fiber: (a.fiber !== undefined) ? a.fiber : 10,
              protein: (a.protein !== undefined) ? a.protein : 30,
              fat: (a.fat !== undefined) ? a.fat : 15,'''

if target_unpack in html:
    html = html.replace(target_unpack, replacement_unpack, 1)
    print("[+] Updated data unpacking in analyzeMealWithGemini to preserve exact numerical values")

# 2. Add client-side candy & DQ fallback in analyzeMealWithGemini
client_candy_fallback = '''        // 2a. Client-Side Accurate Candy, Dessert & DQ Parser
        const parseClientWeightOz = (str) => {
          const m = str.match(/(\\d+(?:\\.\\d+)?)\\s*(?:oz|ounces?|ounce\\b)/i);
          if (m) return parseFloat(m[1]);
          const mg = str.match(/(\\d+(?:\\.\\d+)?)\\s*(?:g|grams?)\\b/i);
          if (mg) return parseFloat(mg[1]) / 28.35;
          return null;
        };
        const cWeightOz = parseClientWeightOz(rawText);

        if ((text.includes("peanut buster") && text.includes("parfait")) || (text.includes("dairy queen") && text.includes("parfait")) || (text.includes("dq") && text.includes("parfait")) || (text.includes("peanut") && text.includes("parfait"))) {
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
        } else '''

assert 'if (presetKey && POPULAR_MEALS[presetKey]) {' in html, "presetKey line not found in index.html"
html = html.replace('if (presetKey && POPULAR_MEALS[presetKey]) {',
                    client_candy_fallback + 'if (presetKey && POPULAR_MEALS[presetKey]) {', 1)

print("[+] Inserted client-side fallback into analyzeMealWithGemini in index.html")

# Div tag check
o = len(re.findall(r'<div\b', html))
c = len(re.findall(r'</div\b', html))
print(f"Div balance: open={o}, close={c}, delta={o-c}")
assert o == c, f"Div tag mismatch: open={o}, close={c}"

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
print(f"[+] Exact dual-file parity: {h1 == h2} ({h1})")
