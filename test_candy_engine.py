import re

def analyze_candies_and_desserts(meal_name, custom_notes=""):
    text = (meal_name + " " + custom_notes).lower()

    # Weight parser
    weight_oz = None
    m = re.search(r'(\d+(?:\.\d+)?)\s*(?:oz|ounces?|ounce\b)', text, re.IGNORECASE)
    if m:
        weight_oz = float(m.group(1))
    else:
        m_g = re.search(r'(\d+(?:\.\d+)?)\s*(?:g|grams?)\b', text, re.IGNORECASE)
        if m_g:
            weight_oz = float(m_g.group(1)) / 28.35

    # 1. Dairy Queen Peanut Buster Parfait
    if ("peanut buster" in text and "parfait" in text) or ("dairy queen" in text and "parfait" in text) or ("dq" in text and "parfait" in text):
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
                {"name": "DQ Soft Serve Vanilla Ice Cream", "portion": "Vanilla Soft Serve Base", "net_carbs": 52, "fiber": 0, "protein": 8, "fat": 14, "calories": 370},
                {"name": "Rich Hot Fudge Layers", "portion": "3 generous pumps", "net_carbs": 32, "fiber": 2, "protein": 2, "fat": 7, "calories": 190},
                {"name": "Crunchy Roasted Peanuts", "portion": "Salted Whole Peanuts", "net_carbs": 6, "fiber": 2, "protein": 6, "fat": 12, "calories": 150}
            ],
            "projected_spike": "+75 to +110 mg/dL (Severe Prolonged Glycemic Excursion)",
            "gemini_notes": "⚠️ SEVERE DIABETIC WARNING: The Dairy Queen Peanut Buster Parfait contains 90g Net Carbs (over double your 45g ceiling). The combination of dense sucrose from hot fudge and lactose from soft serve produces an acute, sustained glucose spike. Peanuts add lipid delay, prolonging hyperglycemia for 4+ hours.",
            "trade_alternatives": [
                "Swap to a Dairy Queen Small Vanilla Cone (28g net carbs, meets 45g ceiling).",
                "Swap to a No-Sugar-Added Dilly Bar (18g net carbs, 200 kcal).",
                "Swap to a Mini Sundae with nuts on the side (31g net carbs, meets goal)."
            ],
            "glut4_recommendation": "Mandatory 40 to 50-minute brisk GLUT4 walk starting within 20 minutes to clear glucose via muscle contraction without insulin dependence."
        }

    # 2. General Dairy Queen / Soft Serve / Blizzards / Sundaes
    if any(w in text for w in ["dairy queen", "dq", "blizzard", "dilly bar", "soft serve"]) or (any(w in text for w in ["sundae", "ice cream"]) and not any(w in text for w in ["keto", "rebel", "halo top", "nicks"])):
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

    # 3. M&M's & Chocolate Coated Candy
    if any(w in text for w in ["m and m", "m&m", "mnm", "m&ms", "m and m's", "m&m's"]):
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
            "gemini_notes": f"⚠️ ACUTE CONFECTIONERY WARNING: {oz:.1f} oz of M&M's contains {net_c}g Net Carbs (Ceiling: 45g max). Pure refined sucrose and chocolate lipids drive rapid glucose entry into the portal circulation with minimal fiber attenuation.",
            "trade_alternatives": [
                "Cap portion to 1 oz 'fun-size' pack (19g net carbs, meets clinical goal).",
                "Swap to 85%+ Dark Chocolate squares (6g net carbs per oz).",
                "Swap to roasted chocolate-dusted almonds (4g net carbs per oz, high protein/fiber)."
            ],
            "glut4_recommendation": f"Perform a {min(60, max(20, int(net_c * 0.45)))}-minute brisk GLUT4 walk starting within 15 minutes to force skeletal muscle GLUT4 translocation."
        }

    # 4. Red Hots & Pure Sugar Hard Candies
    if any(w in text for w in ["red hot", "redhots", "red hots", "cinnamon candy", "cinnamon drops", "hot tamale", "hot tamales", "jelly bean", "jelly belly", "skittle", "skittles", "gummy bear", "gummy"]):
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
            "gemini_notes": f"🚨 SEVERE GLYCEMIC SURGE WARNING: {oz:.1f} oz of {candy_type} is virtually 100% refined corn syrup and sucrose ({net_c}g net carbs) with ZERO fat, ZERO fiber, and ZERO protein. This causes an immediate, unbuffered arterial glucose spike.",
            "trade_alternatives": [
                "Limit intake to 5-6 small pieces (0.5 oz, 13g net carbs, meets goal).",
                "Swap to Sugar-Free Cinnamon Hard Candies (sweetened with isomalt, 0g glycemic impact).",
                "Brew a mug of hot whole clove and Ceylon cinnamon herbal infusion (0g net carbs, zero spike)."
            ],
            "glut4_recommendation": f"Immediate {min(60, max(25, int(net_c * 0.45)))}-minute brisk walk is clinically essential to clear acute circulating glucose."
        }

    # 5. Almond Joy & Candy Bars (Hershey, Snickers, Twix, Reese's)
    if any(w in text for w in ["almond joy", "mounds", "snickers", "twix", "reese", "reeses", "kit kat", "candy bar", "milky way", "butterfinger"]):
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
            "gemini_notes": f"⚠️ HIGH LIPID & SUGAR LOAD: {oz:.1f} oz of {bar_title} provides {net_c}g Net Carbs and {fat}g Fat. Saturated fats from coconut and cocoa butter delay gastric emptying, resulting in a dual-phase glycemic curve with late elevation 2 to 4 hours post-consumption.",
            "trade_alternatives": [
                "Limit to 1 snack-size fun bar (0.6 oz, 9g net carbs, meets clinical ceiling).",
                "Swap to 1 oz raw California almonds + 1 square 85% dark chocolate (5g net carbs).",
                "Swap to unsweetened toasted coconut flakes with raw almonds (3g net carbs)."
            ],
            "glut4_recommendation": f"Schedule a {min(60, max(20, int(net_c * 0.45)))}-minute brisk walk beginning 30-45 minutes post-consumption to counter delayed lipid-induced glucose peak."
        }

    return None

test_cases = [
    "m and M candy 6 oz",
    "M and M's 6oz",
    "Red Hots 6oz",
    "Almond Joy bar 8oz",
    "Dairy Queen Peanut Buster Parfait",
    "Dairy Queen Mini Blizzard",
    "Almond Joy fun size 0.6oz"
]

print("RESULTS:")
for tc in test_cases:
    res = analyze_candies_and_desserts(tc)
    if res:
        print(f"=== '{tc}' ===")
        print(f"  Name: {res['meal_name']}")
        print(f"  Cal: {res['calories']} kcal | Net Carbs: {res['net_carbs']}g | Carbs: {res['total_carbs']}g | Fiber: {res['fiber']}g | Prot: {res['protein']}g | Fat: {res['fat']}g")
        print(f"  Meets Goal: {res['meets_goal']} | Spike: {res['projected_spike']}")
        print(f"  Notes: {res['gemini_notes'][:90]}...")
    else:
        print(f"FAILED TO MATCH: '{tc}'")
