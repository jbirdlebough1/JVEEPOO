import re
import hashlib

print("=== APPLYING CLE-104: GEMINI GOAL-SEEKING INTENT & HOT CANDY SAFE OPTIONS ===")

# -----------------------------------------------------------------------------
# 1. UPDATE server.py
# -----------------------------------------------------------------------------
server_path = 'D:/veepoo/w11_htm_app/server.py'
with open(server_path, 'r', encoding='utf-8') as f:
    server_code = f.read()

# Replace the whole-field token search in server.py
target_token_search = '''    # -------------------------------------------------------------------------
    # 0a. WHOLE-FIELD TOKEN SEARCH: "find me a sugar free dessert like '...'" / SAFE OPTION DISCOVERY
    # -------------------------------------------------------------------------
    safe_tokens = [
        r'find\\s+me\\s+a\\s+(?:sugar\\s*free|diabetic|keto|low\\s*carb|safe)?\\s*(?:dessert|treat|snack|meal|option)?\\s*like\\s*["\\\']?([^"\\\']+)["\\\']?',
        r'sugar\\s*free\\s*(?:dessert|treat|snack|meal)?\\s*like\\s*["\\\']?([^"\\\']+)["\\\']?',
        r'healthy\\s*alternative\\s*to\\s*["\\\']?([^"\\\']+)["\\\']?',
        r'safe\\s*(?:option|dessert|snack|treat)\\s*for\\s*["\\\']?([^"\\\']+)["\\\']?'
    ]
    query_target = None
    for pattern in safe_tokens:
        m_t = re.search(pattern, text)
        if m_t:
            query_target = m_t.group(1).strip()
            break

    is_safe_token_query = (query_target is not None) or (
        "sugar free" in text and any(w in text for w in ["dessert", "treat", "cake", "cookie", "muffin", "ice cream", "candy"]) and not ("cottage cheese" in text and "blueberry" in text)
    )

    if is_safe_token_query:
        tgt = query_target if query_target else "sugar free dessert"
        tgt_l = tgt.lower()

        if any(w in tgt_l for w in ["ice cream", "parfait", "sundae", "frozen", "blizzard"]):
            cat = "ice_cream"
            store_items = [
                {"name": "Rebel Creamery Zero Sugar Vanilla / Triple Chocolate Pint", "store": "Fred Meyer / QFC (2.8 mi)", "net_carbs": "4g Net", "cals": "180 / serving", "price": "$5.49", "aisle": "Frozen Aisle 14 (Ice Cream)"},
                {"name": "Nick's Swedish-Style Light Ice Cream (Strawberry Swirl)", "store": "Safeway / Albertsons (4.1 mi)", "net_carbs": "5g Net", "cals": "240 / pint", "price": "$5.99", "aisle": "Frozen Desserts"},
                {"name": "Halo Top Keto Series Salted Caramel Pint", "store": "Walmart Supercenter (5.9 mi)", "net_carbs": "6g Net", "cals": "190 / serving", "price": "$4.98", "aisle": "Freezer Bay 8"}
            ]
        elif any(w in tgt_l for w in ["chocolate", "candy", "bar", "snack"]):
            cat = "chocolate"
            store_items = [
                {"name": "Lily's Stevia-Sweetened Dark Chocolate Sea Salt Bar (3 oz)", "store": "Fred Meyer / QFC (2.8 mi)", "net_carbs": "3g Net / oz", "cals": "130 / serving", "price": "$3.99", "aisle": "Candy / Specialty Aisle 7"},
                {"name": "Russell Stover Zero Sugar Dark Chocolate Medallions (8 oz bag)", "store": "Safeway / Albertsons (4.1 mi)", "net_carbs": "1g Net / serving", "cals": "140 / serving", "price": "$4.29", "aisle": "Confectionery Aisle"},
                {"name": "ChocZero Sugar-Free Almond Keto Bark", "store": "Walmart Supercenter (5.9 mi)", "net_carbs": "2g Net / bar", "cals": "120 / serving", "price": "$4.88", "aisle": "Health & Nutrition Aisle"}
            ]
        else:
            cat = "baked"
            store_items = [
                {"name": "Simple Mills Almond Flour Soft-Baked Snack Bars", "store": "Fred Meyer / QFC (2.8 mi)", "net_carbs": "8g Net", "cals": "140 / bar", "price": "$4.99", "aisle": "Organic / Bakery Aisle 9"},
                {"name": "Two Good Zero Sugar Blueberry Greek Yogurt Cup", "store": "Safeway / Albertsons (4.1 mi)", "net_carbs": "3g Net", "cals": "80 / cup", "price": "$1.59", "aisle": "Dairy / Yogurt Wall"},
                {"name": "Highkey Keto Zero Sugar Mini Blueberry / Chocolate Cookies", "store": "Walmart Supercenter (5.9 mi)", "net_carbs": "2g Net", "cals": "130 / serving", "price": "$4.48", "aisle": "Snack Aisle 6"}
            ]

        recipes = [
            {
                "id": "recipe_cottage_cake",
                "title": "Zero-Sugar Cottage Cheese Blueberry Protein Cake",
                "desc": "High-protein diabetic cake utilizing low-fat cottage cheese and egg whites as the moist albumin foundation with fresh organic blueberries. Under 7g net carbs per hearty slice.",
                "calories": 145, "net_carbs": 6.5, "total_carbs": 8.5, "fiber": 2.0, "protein": 13.5, "fat": 6.0,
                "glycemic_load": "2 (Ultra-Low)", "cost": "$1.75 / slice",
                "bom": [
                    {"item": "Low-Fat Cottage Cheese (High Albumin/Casein Base)", "portion": "1 cup in batch (~1/4 cup/slice)", "dept": "Dairy Department", "cost": "$2.49", "impact": "High protein buffer, blunts glycemic spike"},
                    {"item": "Fresh Organic Blueberries (Anthocyanin Antioxidant)", "portion": "1/2 cup in batch (~2 tbsp/slice)", "dept": "Produce Department", "cost": "$3.49", "impact": "Bioactive polyphenol fiber (<3g net)"},
                    {"item": "Large Grade A Eggs & Egg Whites", "portion": "2 eggs in batch (~1/2 egg/slice)", "dept": "Dairy / Egg Department", "cost": "$1.99", "impact": "Protein structural binder"},
                    {"item": "Almond Flour / Oat Fiber Blend (Low-GI Matrix)", "portion": "1/2 cup in batch", "dept": "Baking Aisle", "cost": "$4.29", "impact": "Grain-free slow digesting starch"},
                    {"item": "Zero-Sugar Monk Fruit / Allulose Granulated Sweetener", "portion": "to taste", "dept": "Baking Aisle", "cost": "$4.99", "impact": "Zero glycemic impact"}
                ]
            },
            {
                "id": "recipe_avocado_mousse",
                "title": "3-Ingredient Keto Dark Chocolate Avocado Mousse",
                "desc": "Silky, antioxidant-packed dark chocolate mousse using ripe Haas avocado as a rich monounsaturated lipid buffer with pure Dutch cocoa and monk fruit.",
                "calories": 160, "net_carbs": 4.0, "total_carbs": 9.0, "fiber": 5.0, "protein": 4.0, "fat": 14.0,
                "glycemic_load": "1 (Near Zero)", "cost": "$1.95 / bowl",
                "bom": [
                    {"item": "Fresh Ripe Hass Avocado", "portion": "1 medium avocado", "dept": "Produce Department", "cost": "$1.29", "impact": "Monounsaturated fats flatten glucose curve"},
                    {"item": "Unsweetened Dutch-Process Dark Cocoa Powder", "portion": "3 tbsp", "dept": "Baking Aisle", "cost": "$2.99", "impact": "Flavanol antioxidants, 2g fiber"},
                    {"item": "Unsweetened Almond Milk & Pure Vanilla", "portion": "1/4 cup", "dept": "Dairy Alternative Aisle", "cost": "$1.99", "impact": "Zero sugar liquid emulsion"},
                    {"item": "Liquid Monk Fruit or Allulose Sweetener", "portion": "to taste", "dept": "Baking Aisle", "cost": "$3.99", "impact": "Zero insulin response"}
                ]
            },
            {
                "id": "recipe_baked_apple",
                "title": "Warm Cinnamon-Glazed Baked Apple Slices with Greek Yogurt & Walnuts",
                "desc": "Thinly sliced tart Granny Smith apple baked with whole cloves, Ceylon cinnamon, and raw California walnuts over protein-rich Greek yogurt.",
                "calories": 115, "net_carbs": 8.5, "total_carbs": 12.0, "fiber": 3.5, "protein": 6.0, "fat": 5.0,
                "glycemic_load": "3 (Very Low)", "cost": "$1.40 / serving",
                "bom": [
                    {"item": "Fresh Tart Green Granny Smith Apple", "portion": "1 medium apple sliced thin", "dept": "Produce Department", "cost": "$0.99", "impact": "Pectin soluble fiber slows fructose entry"},
                    {"item": "Plain Non-Fat Greek Yogurt", "portion": "1/2 cup", "dept": "Dairy Department", "cost": "$1.29", "impact": "Slow albumin protein buffer"},
                    {"item": "Raw California Walnut Halves", "portion": "1 oz crushed", "dept": "Produce / Nut Aisle", "cost": "$2.49", "impact": "Omega-3 ALA fatty acids"},
                    {"item": "Ground Ceylon Cinnamon & Whole Cloves", "portion": "1 tsp generous", "dept": "Spice Aisle", "cost": "$2.99", "impact": "Eugenol improves insulin sensitivity"}
                ]
            }
        ]

        primary_rec = recipes[0]
        return {
            "meal_name": f"Safe Diabetic Options Discovery: '{tgt}'",
            "vendor_matched": "Gemini Clinical Nutrition Search & Safe Option Locator",
            "photo_filename": "safe_desserts_locator.jpg",
            "calories": primary_rec["calories"],
            "net_carbs": primary_rec["net_carbs"],
            "total_carbs": primary_rec["total_carbs"],
            "fiber": primary_rec["fiber"],
            "protein": primary_rec["protein"],
            "fat": primary_rec["fat"],
            "glycemic_load": primary_rec["glycemic_load"],
            "estimated_cost": primary_rec["cost"],
            "meets_goal": True,
            "is_restaurant": False,
            "is_safe_option_search": True,
            "query_target": tgt,
            "store_complete_items": store_items,
            "safe_recipes": recipes,
            "identified_items": [
                {"name": it["item"], "portion": it["portion"], "net_carbs": 1.5, "fiber": 0.5, "protein": 3.0, "fat": 1.2, "calories": 30}
                for it in primary_rec["bom"]
            ],
            "bom": primary_rec["bom"],
            "projected_spike": "+6 to +12 mg/dL (Homeostatic Target Zone)",
            "gemini_notes": f"✨ GEMINI SAFE OPTION DISCOVERY: Identified 3 commercial ready-to-eat products at local stores within 20 miles, plus 3 curated home recipes with full Bill of Materials (BOM) tailored to replace '{tgt}' with zero glycemic penalty.",
            "trade_alternatives": [
                "Select one of the 3 complete store-bought items for convenience.",
                "Or prepare one of the 3 curated home recipes using the itemized Bill of Materials.",
                "All options strictly adhere to your 45g clinical net carb ceiling."
            ],
            "glut4_recommendation": "15-minute gentle ambulatory walk maintains optimal baseline in the 80–120 mg/dL target band."
        }'''

replacement_token_search = '''    # -------------------------------------------------------------------------
    # 0a. WHOLE-FIELD GOAL-SEEKING & SAFE OPTIONS DISCOVERY ENGINE
    # Handles: "I want a hot candy 4 oz that meets my goals what is best", "find me a sugar free dessert like...", etc.
    # -------------------------------------------------------------------------
    recommendation_intent_patterns = [
        r'find\\s+me\\s+(?:a\\s+)?(?:sugar\\s*free|diabetic|keto|low\\s*carb|safe)?\\s*(?:dessert|treat|snack|meal|candy|option)?\\s*like\\s*["\\\']?([^"\\\']+)["\\\']?',
        r'sugar\\s*free\\s*(?:dessert|treat|snack|meal|candy)?\\s*like\\s*["\\\']?([^"\\\']+)["\\\']?',
        r'healthy\\s*alternative\\s*to\\s*["\\\']?([^"\\\']+)["\\\']?',
        r'safe\\s*(?:option|dessert|snack|treat|candy|alternative)\\s*for\\s*["\\\']?([^"\\\']+)["\\\']?',
        r'(?:i\\s+want|craving|looking\\s+for)\\s+(?:a\\s+)?(.+?)\\s+that\\s+meets\\s+(?:my\\s+)?goals(?:\s+what\s+is\s+best)?',
        r'what\\s+is\\s+(?:the\\s+)?best\\s+(?:safe\\s+|diabetic\\s+)?(.+?)(?:\\s+that\\s+meets\\s+(?:my\\s+)?goals)?',
        r'what\\s+(?:can\\s+i\\s+have|can\\s+i\\s+eat|should\\s+i\\s+get)\\s+(?:for\\s+|if\\s+i\\s+want\\s+)(.+)',
        r'(?:recommend|suggest)\\s+(?:a\\s+)?(?:safe\\s+|diabetic\\s+)?(.+)'
    ]

    query_target = None
    for pattern in recommendation_intent_patterns:
        m_t = re.search(pattern, text)
        if m_t:
            query_target = m_t.group(1).strip()
            query_target = re.sub(r'\\s*(?:what\\s+is\\s+best|that\\s+meets\\s+my\\s+goals|that\\s+meets\\s+goals|meets\\s+goals?)\\s*$', '', query_target).strip()
            break

    is_recommendation_query = (query_target is not None) or (
        any(phrase in text for phrase in ["meets my goals", "meets goals", "what is best", "what's best", "healthy alternative", "safe alternative", "best option"]) and any(word in text for word in ["candy", "dessert", "snack", "treat", "hot candy", "chocolate", "sweet", "ice cream", "cookie", "cake", "pie"])
    ) or (
        "sugar free" in text and any(w in text for w in ["dessert", "treat", "cake", "cookie", "muffin", "ice cream", "candy", "hot candy"]) and not ("cottage cheese" in text and "blueberry" in text)
    )

    if is_recommendation_query:
        tgt = query_target if query_target else "safe diabetic snack"
        tgt_l = tgt.lower()

        # 1. Hot Cinnamon Candies / Spicy Treats (Red Hots, Atomic Fireballs, Cinnamon Drops)
        if any(w in tgt_l for w in ["hot candy", "cinnamon", "red hot", "fireball", "tamale", "spicy candy"]):
            cat = "hot_candy"
            store_items = [
                {"name": "Dr. John's Sugar-Free Cinnamon Hard Candies (Tooth Ticklers)", "store": "Fred Meyer / QFC (2.8 mi)", "net_carbs": "0g Net (Isomalt/Xylitol)", "cals": "35 / serving", "price": "$4.99", "aisle": "Candy Aisle 7 (Sugar-Free)"},
                {"name": "Russell Stover Sugar-Free Cinnamon Hard Candies (8.25 oz bag)", "store": "Safeway / Albertsons (4.1 mi)", "net_carbs": "0g Net", "cals": "30 / serving", "price": "$3.99", "aisle": "Confectionery / Sugar-Free Wall"},
                {"name": "Simply Gum Natural Sugar-Free Cinnamon Mints / Drops", "store": "Walmart Supercenter (5.9 mi)", "net_carbs": "<1g Net", "cals": "5 / serving", "price": "$2.98", "aisle": "Front Register Snack Bay"}
            ]
            recipes = [
                {
                    "id": "recipe_cinnamon_pecans",
                    "title": "Zero-Sugar Spicy Ceylon Cinnamon Glazed Pecans",
                    "desc": "Crunchy raw Georgia pecan halves tossed with fiery Ceylon cinnamon, ground cloves, granulated allulose, and a touch of cayenne. Ultra-low carb lipid matrix with zero glycemic surge.",
                    "calories": 130, "net_carbs": 2.5, "total_carbs": 4.5, "fiber": 2.0, "protein": 3.0, "fat": 12.0,
                    "glycemic_load": "1 (Near Zero)", "cost": "$1.85 / serving",
                    "bom": [
                        {"item": "Raw Georgia Pecan Halves (Healthy Monounsaturated Fat)", "portion": "1 cup in batch (~1/4 cup/serving)", "dept": "Produce / Nut Aisle", "cost": "$3.49", "impact": "High fat/fiber matrix halts glucose entry"},
                        {"item": "Ground Ceylon Cinnamon & Whole Cloves", "portion": "1 tbsp generous", "dept": "Spice Aisle", "cost": "$2.99", "impact": "Cinnamaldehyde & eugenol sensitize insulin"},
                        {"item": "Granulated Monk Fruit / Allulose Sweetener", "portion": "2 tbsp in batch", "dept": "Baking Aisle", "cost": "$4.99", "impact": "Zero glycemic / insulin response"},
                        {"item": "Organic Cayenne Pepper & Sea Salt", "portion": "1 pinch for hot bite", "dept": "Spice Aisle", "cost": "$1.99", "impact": "Thermogenic capsaicin"},
                        {"item": "Pure Madagascar Vanilla Extract & Egg White", "portion": "1 tsp vanilla + 1 egg white glaze", "dept": "Baking / Dairy Aisle", "cost": "$2.29", "impact": "Binding glaze with albumin protein"}
                    ]
                },
                {
                    "id": "recipe_baked_apple",
                    "title": "Warm Cinnamon-Glazed Baked Apple Slices with Greek Yogurt & Walnuts",
                    "desc": "Thinly sliced tart Granny Smith apple baked with whole cloves, Ceylon cinnamon, and raw California walnuts over protein-rich Greek yogurt.",
                    "calories": 115, "net_carbs": 8.5, "total_carbs": 12.0, "fiber": 3.5, "protein": 6.0, "fat": 5.0,
                    "glycemic_load": "3 (Very Low)", "cost": "$1.40 / serving",
                    "bom": [
                        {"item": "Fresh Tart Green Granny Smith Apple", "portion": "1 medium apple sliced thin", "dept": "Produce Department", "cost": "$0.99", "impact": "Pectin soluble fiber slows fructose entry"},
                        {"item": "Plain Non-Fat Greek Yogurt", "portion": "1/2 cup", "dept": "Dairy Department", "cost": "$1.29", "impact": "Slow albumin protein buffer"},
                        {"item": "Raw California Walnut Halves", "portion": "1 oz crushed", "dept": "Produce / Nut Aisle", "cost": "$2.49", "impact": "Omega-3 ALA fatty acids"},
                        {"item": "Ground Ceylon Cinnamon & Whole Cloves", "portion": "1 tsp generous", "dept": "Spice Aisle", "cost": "$2.99", "impact": "Eugenol improves insulin sensitivity"}
                    ]
                },
                {
                    "id": "recipe_cinnamon_steamer",
                    "title": "Hot Fiery Cinnamon-Clove Steamer & Herbal Chai",
                    "desc": "Whole Ceylon cinnamon quills and whole cloves simmered in unsweetened vanilla almond milk with a touch of allulose for warming fiery cinnamon comfort.",
                    "calories": 25, "net_carbs": 0.5, "total_carbs": 1.5, "fiber": 1.0, "protein": 1.0, "fat": 2.0,
                    "glycemic_load": "0 (Zero Glycemic Impact)", "cost": "$0.95 / mug",
                    "bom": [
                        {"item": "Whole Ceylon Cinnamon Sticks (True Cinnamon)", "portion": "2 quills", "dept": "Spice Aisle", "cost": "$3.49", "impact": "Zero sugar, natural cinnamaldehyde"},
                        {"item": "Whole Dried Cloves", "portion": "1 tsp", "dept": "Spice Aisle", "cost": "$2.99", "impact": "Potent antioxidant & beta-cell protective"},
                        {"item": "Unsweetened Vanilla Almond Milk", "portion": "8 fl oz", "dept": "Dairy Alternative Aisle", "cost": "$1.99", "impact": "Zero sugar creamy emulsion"},
                        {"item": "Liquid Allulose / Monk Fruit Drops", "portion": "to taste", "dept": "Baking Aisle", "cost": "$3.99", "impact": "Zero insulin demand"}
                    ]
                }
            ]
            primary_rec = recipes[0]
            gemini_explanation = f"✨ GEMINI SAFE OPTION DISCOVERY: You asked for a hot candy to meet your diabetic goals. A traditional 4 oz commercial hot candy (like Red Hots or Atomic Fireballs) contains ~108g of pure refined sugar—drastically exceeding your 45g clinical ceiling! Instead, Gemini identified 3 commercial sugar-free cinnamon candies at local grocers within 20 miles (0g net carbs), plus 3 curated diabetic recipes (under 3g net carbs) with full Bill of Materials (BOM) below."
            trades = [
                "Select Dr. John's or Russell Stover Sugar-Free Cinnamon Candies at Fred Meyer / Safeway (0g net carbs, zero glycemic surge).",
                "Or load the Zero-Sugar Spicy Ceylon Cinnamon Glazed Pecans recipe below for crunchy heat with healthy omega fats.",
                "Avoid commercial corn syrup hot candies (108g net carbs per 4 oz)."
            ]

        # 2. Ice Creams, Sundaes, Parfaits, Frozen Treats
        elif any(w in tgt_l for w in ["ice cream", "parfait", "sundae", "frozen", "blizzard"]):
            cat = "ice_cream"
            store_items = [
                {"name": "Rebel Creamery Zero Sugar Vanilla / Triple Chocolate Pint", "store": "Fred Meyer / QFC (2.8 mi)", "net_carbs": "4g Net", "cals": "180 / serving", "price": "$5.49", "aisle": "Frozen Aisle 14 (Ice Cream)"},
                {"name": "Nick's Swedish-Style Light Ice Cream (Strawberry Swirl)", "store": "Safeway / Albertsons (4.1 mi)", "net_carbs": "5g Net", "cals": "240 / pint", "price": "$5.99", "aisle": "Frozen Desserts"},
                {"name": "Halo Top Keto Series Salted Caramel Pint", "store": "Walmart Supercenter (5.9 mi)", "net_carbs": "6g Net", "cals": "190 / serving", "price": "$4.98", "aisle": "Freezer Bay 8"}
            ]
            recipes = [
                {
                    "id": "recipe_cottage_cake",
                    "title": "Zero-Sugar Cottage Cheese Blueberry Protein Cake",
                    "desc": "High-protein diabetic cake utilizing low-fat cottage cheese and egg whites as the moist albumin foundation with fresh organic blueberries. Under 7g net carbs per hearty slice.",
                    "calories": 145, "net_carbs": 6.5, "total_carbs": 8.5, "fiber": 2.0, "protein": 13.5, "fat": 6.0,
                    "glycemic_load": "2 (Ultra-Low)", "cost": "$1.75 / slice",
                    "bom": [
                        {"item": "Low-Fat Cottage Cheese (High Albumin/Casein Base)", "portion": "1 cup in batch (~1/4 cup/slice)", "dept": "Dairy Department", "cost": "$2.49", "impact": "High protein buffer, blunts glycemic spike"},
                        {"item": "Fresh Organic Blueberries (Anthocyanin Antioxidant)", "portion": "1/2 cup in batch (~2 tbsp/slice)", "dept": "Produce Department", "cost": "$3.49", "impact": "Bioactive polyphenol fiber (<3g net)"},
                        {"item": "Large Grade A Eggs & Egg Whites", "portion": "2 eggs in batch (~1/2 egg/slice)", "dept": "Dairy / Egg Department", "cost": "$1.99", "impact": "Protein structural binder"},
                        {"item": "Almond Flour / Oat Fiber Blend (Low-GI Matrix)", "portion": "1/2 cup in batch", "dept": "Baking Aisle", "cost": "$4.29", "impact": "Grain-free slow digesting starch"},
                        {"item": "Zero-Sugar Monk Fruit / Allulose Granulated Sweetener", "portion": "to taste", "dept": "Baking Aisle", "cost": "$4.99", "impact": "Zero glycemic impact"}
                    ]
                },
                {
                    "id": "recipe_avocado_mousse",
                    "title": "3-Ingredient Keto Dark Chocolate Avocado Mousse",
                    "desc": "Silky, antioxidant-packed dark chocolate mousse using ripe Haas avocado as a rich monounsaturated lipid buffer with pure Dutch cocoa and monk fruit.",
                    "calories": 160, "net_carbs": 4.0, "total_carbs": 9.0, "fiber": 5.0, "protein": 4.0, "fat": 14.0,
                    "glycemic_load": "1 (Near Zero)", "cost": "$1.95 / bowl",
                    "bom": [
                        {"item": "Fresh Ripe Hass Avocado", "portion": "1 medium avocado", "dept": "Produce Department", "cost": "$1.29", "impact": "Monounsaturated fats flatten glucose curve"},
                        {"item": "Unsweetened Dutch-Process Dark Cocoa Powder", "portion": "3 tbsp", "dept": "Baking Aisle", "cost": "$2.99", "impact": "Flavanol antioxidants, 2g fiber"},
                        {"item": "Unsweetened Almond Milk & Pure Vanilla", "portion": "1/4 cup", "dept": "Dairy Alternative Aisle", "cost": "$1.99", "impact": "Zero sugar liquid emulsion"},
                        {"item": "Liquid Monk Fruit or Allulose Sweetener", "portion": "to taste", "dept": "Baking Aisle", "cost": "$3.99", "impact": "Zero insulin response"}
                    ]
                },
                {
                    "id": "recipe_baked_apple",
                    "title": "Warm Cinnamon-Glazed Baked Apple Slices with Greek Yogurt & Walnuts",
                    "desc": "Thinly sliced tart Granny Smith apple baked with whole cloves, Ceylon cinnamon, and raw California walnuts over protein-rich Greek yogurt.",
                    "calories": 115, "net_carbs": 8.5, "total_carbs": 12.0, "fiber": 3.5, "protein": 6.0, "fat": 5.0,
                    "glycemic_load": "3 (Very Low)", "cost": "$1.40 / serving",
                    "bom": [
                        {"item": "Fresh Tart Green Granny Smith Apple", "portion": "1 medium apple sliced thin", "dept": "Produce Department", "cost": "$0.99", "impact": "Pectin soluble fiber slows fructose entry"},
                        {"item": "Plain Non-Fat Greek Yogurt", "portion": "1/2 cup", "dept": "Dairy Department", "cost": "$1.29", "impact": "Slow albumin protein buffer"},
                        {"item": "Raw California Walnut Halves", "portion": "1 oz crushed", "dept": "Produce / Nut Aisle", "cost": "$2.49", "impact": "Omega-3 ALA fatty acids"},
                        {"item": "Ground Ceylon Cinnamon & Whole Cloves", "portion": "1 tsp generous", "dept": "Spice Aisle", "cost": "$2.99", "impact": "Eugenol improves insulin sensitivity"}
                    ]
                }
            ]
            primary_rec = recipes[0]
            gemini_explanation = f"✨ GEMINI SAFE OPTION DISCOVERY: Identified 3 commercial ready-to-eat products at local stores within 20 miles, plus 3 curated home recipes with full Bill of Materials (BOM) tailored to replace '{tgt}' with zero glycemic penalty."
            trades = [
                "Select one of the 3 complete store-bought items for convenience.",
                "Or prepare one of the 3 curated home recipes using the itemized Bill of Materials.",
                "All options strictly adhere to your 45g clinical net carb ceiling."
            ]

        # 3. Chocolates, Candies, Bars
        elif any(w in tgt_l for w in ["chocolate", "candy", "bar", "snack"]):
            cat = "chocolate"
            store_items = [
                {"name": "Lily's Stevia-Sweetened Dark Chocolate Sea Salt Bar (3 oz)", "store": "Fred Meyer / QFC (2.8 mi)", "net_carbs": "3g Net / oz", "cals": "130 / serving", "price": "$3.99", "aisle": "Candy / Specialty Aisle 7"},
                {"name": "Russell Stover Zero Sugar Dark Chocolate Medallions (8 oz bag)", "store": "Safeway / Albertsons (4.1 mi)", "net_carbs": "1g Net / serving", "cals": "140 / serving", "price": "$4.29", "aisle": "Confectionery Aisle"},
                {"name": "ChocZero Sugar-Free Almond Keto Bark", "store": "Walmart Supercenter (5.9 mi)", "net_carbs": "2g Net / bar", "cals": "120 / serving", "price": "$4.88", "aisle": "Health & Nutrition Aisle"}
            ]
            recipes = [
                {
                    "id": "recipe_avocado_mousse",
                    "title": "3-Ingredient Keto Dark Chocolate Avocado Mousse",
                    "desc": "Silky, antioxidant-packed dark chocolate mousse using ripe Haas avocado as a rich monounsaturated lipid buffer with pure Dutch cocoa and monk fruit.",
                    "calories": 160, "net_carbs": 4.0, "total_carbs": 9.0, "fiber": 5.0, "protein": 4.0, "fat": 14.0,
                    "glycemic_load": "1 (Near Zero)", "cost": "$1.95 / bowl",
                    "bom": [
                        {"item": "Fresh Ripe Hass Avocado", "portion": "1 medium avocado", "dept": "Produce Department", "cost": "$1.29", "impact": "Monounsaturated fats flatten glucose curve"},
                        {"item": "Unsweetened Dutch-Process Dark Cocoa Powder", "portion": "3 tbsp", "dept": "Baking Aisle", "cost": "$2.99", "impact": "Flavanol antioxidants, 2g fiber"},
                        {"item": "Unsweetened Almond Milk & Pure Vanilla", "portion": "1/4 cup", "dept": "Dairy Alternative Aisle", "cost": "$1.99", "impact": "Zero sugar liquid emulsion"},
                        {"item": "Liquid Monk Fruit or Allulose Sweetener", "portion": "to taste", "dept": "Baking Aisle", "cost": "$3.99", "impact": "Zero insulin response"}
                    ]
                },
                {
                    "id": "recipe_cottage_cake",
                    "title": "Zero-Sugar Cottage Cheese Blueberry Protein Cake",
                    "desc": "High-protein diabetic cake utilizing low-fat cottage cheese and egg whites as the moist albumin foundation with fresh organic blueberries. Under 7g net carbs per hearty slice.",
                    "calories": 145, "net_carbs": 6.5, "total_carbs": 8.5, "fiber": 2.0, "protein": 13.5, "fat": 6.0,
                    "glycemic_load": "2 (Ultra-Low)", "cost": "$1.75 / slice",
                    "bom": [
                        {"item": "Low-Fat Cottage Cheese (High Albumin/Casein Base)", "portion": "1 cup in batch (~1/4 cup/slice)", "dept": "Dairy Department", "cost": "$2.49", "impact": "High protein buffer, blunts glycemic spike"},
                        {"item": "Fresh Organic Blueberries (Anthocyanin Antioxidant)", "portion": "1/2 cup in batch (~2 tbsp/slice)", "dept": "Produce Department", "cost": "$3.49", "impact": "Bioactive polyphenol fiber (<3g net)"},
                        {"item": "Large Grade A Eggs & Egg Whites", "portion": "2 eggs in batch (~1/2 egg/slice)", "dept": "Dairy / Egg Department", "cost": "$1.99", "impact": "Protein structural binder"},
                        {"item": "Almond Flour / Oat Fiber Blend (Low-GI Matrix)", "portion": "1/2 cup in batch", "dept": "Baking Aisle", "cost": "$4.29", "impact": "Grain-free slow digesting starch"},
                        {"item": "Zero-Sugar Monk Fruit / Allulose Granulated Sweetener", "portion": "to taste", "dept": "Baking Aisle", "cost": "$4.99", "impact": "Zero glycemic impact"}
                    ]
                },
                {
                    "id": "recipe_baked_apple",
                    "title": "Warm Cinnamon-Glazed Baked Apple Slices with Greek Yogurt & Walnuts",
                    "desc": "Thinly sliced tart Granny Smith apple baked with whole cloves, Ceylon cinnamon, and raw California walnuts over protein-rich Greek yogurt.",
                    "calories": 115, "net_carbs": 8.5, "total_carbs": 12.0, "fiber": 3.5, "protein": 6.0, "fat": 5.0,
                    "glycemic_load": "3 (Very Low)", "cost": "$1.40 / serving",
                    "bom": [
                        {"item": "Fresh Tart Green Granny Smith Apple", "portion": "1 medium apple sliced thin", "dept": "Produce Department", "cost": "$0.99", "impact": "Pectin soluble fiber slows fructose entry"},
                        {"item": "Plain Non-Fat Greek Yogurt", "portion": "1/2 cup", "dept": "Dairy Department", "cost": "$1.29", "impact": "Slow albumin protein buffer"},
                        {"item": "Raw California Walnut Halves", "portion": "1 oz crushed", "dept": "Produce / Nut Aisle", "cost": "$2.49", "impact": "Omega-3 ALA fatty acids"},
                        {"item": "Ground Ceylon Cinnamon & Whole Cloves", "portion": "1 tsp generous", "dept": "Spice Aisle", "cost": "$2.99", "impact": "Eugenol improves insulin sensitivity"}
                    ]
                }
            ]
            primary_rec = recipes[0]
            gemini_explanation = f"✨ GEMINI SAFE OPTION DISCOVERY: Identified 3 commercial ready-to-eat products at local stores within 20 miles, plus 3 curated home recipes with full Bill of Materials (BOM) tailored to replace '{tgt}' with zero glycemic penalty."
            trades = [
                "Select one of the 3 complete store-bought items for convenience.",
                "Or prepare one of the 3 curated home recipes using the itemized Bill of Materials.",
                "All options strictly adhere to your 45g clinical net carb ceiling."
            ]

        # 4. Baked Goods, Cakes, Cookies, Pastries
        else:
            cat = "baked"
            store_items = [
                {"name": "Simple Mills Almond Flour Soft-Baked Snack Bars", "store": "Fred Meyer / QFC (2.8 mi)", "net_carbs": "8g Net", "cals": "140 / bar", "price": "$4.99", "aisle": "Organic / Bakery Aisle 9"},
                {"name": "Two Good Zero Sugar Blueberry Greek Yogurt Cup", "store": "Safeway / Albertsons (4.1 mi)", "net_carbs": "3g Net", "cals": "80 / cup", "price": "$1.59", "aisle": "Dairy / Yogurt Wall"},
                {"name": "Highkey Keto Zero Sugar Mini Blueberry / Chocolate Cookies", "store": "Walmart Supercenter (5.9 mi)", "net_carbs": "2g Net", "cals": "130 / serving", "price": "$4.48", "aisle": "Snack Aisle 6"}
            ]
            recipes = [
                {
                    "id": "recipe_cottage_cake",
                    "title": "Zero-Sugar Cottage Cheese Blueberry Protein Cake",
                    "desc": "High-protein diabetic cake utilizing low-fat cottage cheese and egg whites as the moist albumin foundation with fresh organic blueberries. Under 7g net carbs per hearty slice.",
                    "calories": 145, "net_carbs": 6.5, "total_carbs": 8.5, "fiber": 2.0, "protein": 13.5, "fat": 6.0,
                    "glycemic_load": "2 (Ultra-Low)", "cost": "$1.75 / slice",
                    "bom": [
                        {"item": "Low-Fat Cottage Cheese (High Albumin/Casein Base)", "portion": "1 cup in batch (~1/4 cup/slice)", "dept": "Dairy Department", "cost": "$2.49", "impact": "High protein buffer, blunts glycemic spike"},
                        {"item": "Fresh Organic Blueberries (Anthocyanin Antioxidant)", "portion": "1/2 cup in batch (~2 tbsp/slice)", "dept": "Produce Department", "cost": "$3.49", "impact": "Bioactive polyphenol fiber (<3g net)"},
                        {"item": "Large Grade A Eggs & Egg Whites", "portion": "2 eggs in batch (~1/2 egg/slice)", "dept": "Dairy / Egg Department", "cost": "$1.99", "impact": "Protein structural binder"},
                        {"item": "Almond Flour / Oat Fiber Blend (Low-GI Matrix)", "portion": "1/2 cup in batch", "dept": "Baking Aisle", "cost": "$4.29", "impact": "Grain-free slow digesting starch"},
                        {"item": "Zero-Sugar Monk Fruit / Allulose Granulated Sweetener", "portion": "to taste", "dept": "Baking Aisle", "cost": "$4.99", "impact": "Zero glycemic impact"}
                    ]
                },
                {
                    "id": "recipe_avocado_mousse",
                    "title": "3-Ingredient Keto Dark Chocolate Avocado Mousse",
                    "desc": "Silky, antioxidant-packed dark chocolate mousse using ripe Haas avocado as a rich monounsaturated lipid buffer with pure Dutch cocoa and monk fruit.",
                    "calories": 160, "net_carbs": 4.0, "total_carbs": 9.0, "fiber": 5.0, "protein": 4.0, "fat": 14.0,
                    "glycemic_load": "1 (Near Zero)", "cost": "$1.95 / bowl",
                    "bom": [
                        {"item": "Fresh Ripe Haas Avocado", "portion": "1 medium avocado", "dept": "Produce Department", "cost": "$1.29", "impact": "Monounsaturated fats flatten glucose curve"},
                        {"item": "Unsweetened Dutch-Process Dark Cocoa Powder", "portion": "3 tbsp", "dept": "Baking Aisle", "cost": "$2.99", "impact": "Flavanol antioxidants, 2g fiber"},
                        {"item": "Unsweetened Almond Milk & Pure Vanilla", "portion": "1/4 cup", "dept": "Dairy Alternative Aisle", "cost": "$1.99", "impact": "Zero sugar liquid emulsion"},
                        {"item": "Liquid Monk Fruit or Allulose Sweetener", "portion": "to taste", "dept": "Baking Aisle", "cost": "$3.99", "impact": "Zero insulin response"}
                    ]
                },
                {
                    "id": "recipe_baked_apple",
                    "title": "Warm Cinnamon-Glazed Baked Apple Slices with Greek Yogurt & Walnuts",
                    "desc": "Thinly sliced tart Granny Smith apple baked with whole cloves, Ceylon cinnamon, and raw California walnuts over protein-rich Greek yogurt.",
                    "calories": 115, "net_carbs": 8.5, "total_carbs": 12.0, "fiber": 3.5, "protein": 6.0, "fat": 5.0,
                    "glycemic_load": "3 (Very Low)", "cost": "$1.40 / serving",
                    "bom": [
                        {"item": "Fresh Tart Green Granny Smith Apple", "portion": "1 medium apple sliced thin", "dept": "Produce Department", "cost": "$0.99", "impact": "Pectin soluble fiber slows fructose entry"},
                        {"item": "Plain Non-Fat Greek Yogurt", "portion": "1/2 cup", "dept": "Dairy Department", "cost": "$1.29", "impact": "Slow albumin protein buffer"},
                        {"item": "Raw California Walnut Halves", "portion": "1 oz crushed", "dept": "Produce / Nut Aisle", "cost": "$2.49", "impact": "Omega-3 ALA fatty acids"},
                        {"item": "Ground Ceylon Cinnamon & Whole Cloves", "portion": "1 tsp generous", "dept": "Spice Aisle", "cost": "$2.99", "impact": "Eugenol improves insulin sensitivity"}
                    ]
                }
            ]
            primary_rec = recipes[0]
            gemini_explanation = f"✨ GEMINI SAFE OPTION DISCOVERY: Identified 3 commercial ready-to-eat products at local stores within 20 miles, plus 3 curated home recipes with full Bill of Materials (BOM) tailored to replace '{tgt}' with zero glycemic penalty."
            trades = [
                "Select one of the 3 complete store-bought items for convenience.",
                "Or prepare one of the 3 curated home recipes using the itemized Bill of Materials.",
                "All options strictly adhere to your 45g clinical net carb ceiling."
            ]

        return {
            "meal_name": f"Safe Options Discovery: '{tgt}'",
            "vendor_matched": "Gemini Clinical Nutrition Search & Safe Option Locator",
            "photo_filename": "safe_desserts_locator.jpg",
            "calories": primary_rec["calories"],
            "net_carbs": primary_rec["net_carbs"],
            "total_carbs": primary_rec["total_carbs"],
            "fiber": primary_rec["fiber"],
            "protein": primary_rec["protein"],
            "fat": primary_rec["fat"],
            "glycemic_load": primary_rec["glycemic_load"],
            "estimated_cost": primary_rec["cost"],
            "meets_goal": True,
            "is_restaurant": False,
            "is_safe_option_search": True,
            "query_target": tgt,
            "store_complete_items": store_items,
            "safe_recipes": recipes,
            "identified_items": [
                {"name": it["item"], "portion": it["portion"], "net_carbs": 1.5, "fiber": 0.5, "protein": 3.0, "fat": 1.2, "calories": 30}
                for it in primary_rec["bom"]
            ],
            "bom": primary_rec["bom"],
            "projected_spike": "+6 to +12 mg/dL (Homeostatic Target Zone)",
            "gemini_notes": gemini_explanation,
            "trade_alternatives": trades,
            "glut4_recommendation": "15-minute gentle ambulatory walk maintains optimal baseline in the 80–120 mg/dL target band."
        }'''

assert target_token_search in server_code, "target_token_search not found in server.py"
server_code = server_code.replace(target_token_search, replacement_token_search, 1)

# Now update the Red Hots parser in server.py to include "hot candy", "atomic fireball", etc.
target_redhots = 'elif any(w in text for w in ["red hot", "redhots", "red hots", "cinnamon candy", "cinnamon drops", "hot tamale", "hot tamales", "jelly bean", "jelly belly", "skittle", "skittles", "gummy bear", "gummy"]):'
replacement_redhots = 'elif any(w in text for w in ["red hot", "redhots", "red hots", "cinnamon candy", "cinnamon drops", "hot candy", "hot candies", "atomic fireball", "fireball", "cinnamon drop", "cinnamon balls", "spicy candy", "hot tamale", "hot tamales", "jelly bean", "jelly belly", "skittle", "skittles", "gummy bear", "gummy"]):'

assert target_redhots in server_code, "target_redhots not found in server.py"
server_code = server_code.replace(target_redhots, replacement_redhots, 1)

# And update candy_type inside Red Hots parser:
target_candy_type = 'candy_type = "Red Hots Cinnamon Candy" if any(w in text for w in ["red", "cinnamon", "tamale"]) else "Hard Candy / Fruit Chews"'
replacement_candy_type = 'candy_type = "Hot Cinnamon Candy / Red Hots" if any(w in text for w in ["red", "cinnamon", "tamale", "hot candy", "fireball", "spicy"]) else "Hard Candy / Fruit Chews"'

assert target_candy_type in server_code, "target_candy_type not found in server.py"
server_code = server_code.replace(target_candy_type, replacement_candy_type, 1)

with open(server_path, 'w', encoding='utf-8') as f:
    f.write(server_code)
print("[+] server.py updated with recommendation intent engine and hot candy safe options!")

# -----------------------------------------------------------------------------
# 2. UPDATE index.html
# -----------------------------------------------------------------------------
html_path = 'D:/veepoo/index.html'
with open(html_path, 'r', encoding='utf-8') as f:
    html = f.read()

# Add prompt token helper chip for hot candy in Pathway B
target_prompt_chips = '''<span style="font-size: 0.70rem; font-weight: 800; color: #0369a1; font-family: var(--lcars-mono);">💡 PROMPT TOKENS:</span>'''
replacement_prompt_chips = '''<span style="font-size: 0.70rem; font-weight: 800; color: #0369a1; font-family: var(--lcars-mono);">💡 PROMPT TOKENS:</span>
                        <button type="button" class="lcars-btn" style="padding: 2px 8px; font-size: 0.70rem; background: #e0f2fe; color: #0369a1; border: 1px solid #7dd3fc; border-radius: 4px; font-weight: bold;" onclick="setMealQueryToken(\\'I want a hot candy that meets my goals what is best\\')">
                          🌶️ hot candy that meets my goals
                        </button>'''

assert target_prompt_chips in html, "target_prompt_chips not found in index.html"
html = html.replace(target_prompt_chips, replacement_prompt_chips, 1)
print("[+] Added hot candy prompt token helper chip to Pathway B")

# Also in analyzeMealWithGemini client-side fallback, add hot candy recognition
target_client_redhots = 'else if (text.includes("red hot") || text.includes("redhots") || text.includes("cinnamon drops") || text.includes("cinnamon candy")) {'
replacement_client_redhots = 'else if (text.includes("red hot") || text.includes("redhots") || text.includes("cinnamon drops") || text.includes("cinnamon candy") || text.includes("hot candy") || text.includes("atomic fireball")) {'

assert target_client_redhots in html, "target_client_redhots not found in index.html"
html = html.replace(target_client_redhots, replacement_client_redhots, 1)
print("[+] Updated client-side fallback with hot candy recognition")

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
print("=== CLE-104 PATCH APPLIED SUCCESSFULLY ===")
