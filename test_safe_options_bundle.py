import json

def get_safe_options_bundle(target_name):
    t = (target_name or "dessert").lower()

    if any(w in t for w in ["ice cream", "parfait", "sundae", "frozen", "blizzard"]):
        cat = "ice_cream"
        store_items = [
            {"name": "Rebel Creamery Zero Sugar Vanilla / Triple Chocolate Pint", "store": "Fred Meyer / QFC (2.8 mi)", "net_carbs": "4g Net", "cals": "180 / serving", "price": "$5.49", "aisle": "Frozen Aisle 14 (Ice Cream)"},
            {"name": "Nick's Swedish-Style Light Ice Cream (Strawberry Swirl)", "store": "Safeway / Albertsons (4.1 mi)", "net_carbs": "5g Net", "cals": "240 / pint", "price": "$5.99", "aisle": "Frozen Desserts"},
            {"name": "Halo Top Keto Series Salted Caramel Pint", "store": "Walmart Supercenter (5.9 mi)", "net_carbs": "6g Net", "cals": "190 / serving", "price": "$4.98", "aisle": "Freezer Bay 8"}
        ]
    elif any(w in t for w in ["chocolate", "candy", "bar", "snack"]):
        cat = "chocolate"
        store_items = [
            {"name": "Lily's Stevia-Sweetened Dark Chocolate Sea Salt Bar (3 oz)", "store": "Fred Meyer / QFC (2.8 mi)", "net_carbs": "3g Net / oz", "cals": "130 / serving", "price": "$3.99", "aisle": "Candy / Specialty Aisle 7"},
            {"name": "Russell Stover Zero Sugar Dark Chocolate Medallions (8 oz bag)", "store": "Safeway / Albertsons (4.1 mi)", "net_carbs": "1g Net / serving", "cals": "140 / serving", "price": "$4.29", "aisle": "Confectionery Aisle"},
            {"name": "ChocZero Sugar-Free Almond Keto Bark", "store": "Walmart Supercenter (5.9 mi)", "net_carbs": "2g Net / bar", "cals": "120 / serving", "price": "$4.88", "aisle": "Health & Nutrition Aisle"}
        ]
    else: # Default baked goods / cake / berry desserts
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

    return {
        "target": target_name,
        "category": cat,
        "store_items": store_items,
        "recipes": recipes
    }

print(json.dumps(get_safe_options_bundle("blueberry cake"), indent=2))
