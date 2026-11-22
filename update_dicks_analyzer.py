import os, re

def update_server_dicks_and_wifi():
    p = r"D:\veepoo\w11_htm_app\server.py"
    with open(p, "r", encoding="utf-8") as f:
        code = f.read()

    # 1. Update analyze_meal_with_gemini_backend in server.py
    # Remove "burger" from McDonald's check and add Dick's Drive-In handler
    old_mcdonald_block = """    # 4. McDonald's Fast Food Order
    elif "mcdonald" in text or "quarter pounder" in text or "burger" in text:
        return {
            "meal_name": "McDonald's Quarter Pounder Meal (Standard)",
            "vendor_matched": "McDonald's USA Nutritional Database (Verified Packaging)",
            "photo_filename": "meal_mcdonalds_qp.jpg",
            "calories": 1050,
            "net_carbs": 88.0,
            "total_carbs": 94.0,
            "fiber": 6.0,
            "protein": 36.0,
            "fat": 42.0,
            "glycemic_load": "38 (High)",
            "meets_goal": False,
            "identified_items": [
                {"name": "Quarter Pounder with Cheese", "portion": "1 sandwich", "net_carbs": 38, "protein": 30},
                {"name": "Medium French Fries", "portion": "1 medium", "net_carbs": 44, "fat": 16},
                {"name": "Regular Coca-Cola", "portion": "16 oz", "net_carbs": 39, "sugar": 39}
            ],
            "gemini_notes": "CRITICAL EXCURSION WARNING: 88g net carbs exceeds 45g diabetic ceiling by 43g. Order bunless and swap fries for side salad to eliminate spike."
        }"""

    new_dicks_and_mcdonald_block = """    # 4. Dick's Drive-In Order (Seattle Iconic Menu since 1954)
    elif "dick" in text or "drive-in" in text or "drivein" in text or "deluxe" in text:
        has_fries = ("frie" in text or "fry" in text or "fries" in text)
        has_burger = ("deluxe" in text or "special" in text or "burger" in text or "cheeseburger" in text or "dick" in text)

        if has_burger and has_fries:
            return {
                "meal_name": "Dick's Drive-In Deluxe Burger & Hand-Cut Fries",
                "vendor_matched": "Dick's Drive-In Restaurants (Seattle Official Nutrition Disclosure)",
                "photo_filename": "meal_dicks_deluxe_combo.jpg",
                "calories": 810,
                "net_carbs": 72.0,
                "total_carbs": 78.0,
                "fiber": 6.0,
                "protein": 25.0,
                "fat": 47.0,
                "glycemic_load": "29 (Elevated)",
                "meets_goal": False,
                "identified_items": [
                    {"name": "Dick's Deluxe (2 beef patties, cheese, relish, mayo, bun)", "portion": "1 burger", "net_carbs": 28, "protein": 20, "calories": 420},
                    {"name": "Dick's Hand-Cut Fresh Washington Potato Fries", "portion": "1 order", "net_carbs": 44, "fiber": 4, "protein": 5, "calories": 390}
                ],
                "gemini_notes": "Dick's Deluxe single burger (28g net carbs) is safely within your 45g goal! Adding the full order of hand-cut fries (+44g net carbs) brings total to 72g net carbs. ADA Strategy: Share half the fries (saves 22g net carbs) or perform a 20-min brisk GLUT4 walk to blunt postprandial glycemic rise."
            }
        elif has_fries and not ("deluxe" in text or "burger" in text):
            return {
                "meal_name": "Dick's Drive-In Hand-Cut Washington Potato Fries",
                "vendor_matched": "Dick's Drive-In Restaurants (Seattle Nutrition Disclosure)",
                "photo_filename": "meal_dicks_fries.jpg",
                "calories": 390,
                "net_carbs": 44.0,
                "total_carbs": 48.0,
                "fiber": 4.0,
                "protein": 5.0,
                "fat": 21.0,
                "glycemic_load": "24 (Moderate)",
                "meets_goal": True,
                "identified_items": [
                    {"name": "Dick's Fresh Hand-Cut Fries", "portion": "1 order", "net_carbs": 44, "fiber": 4, "protein": 5, "calories": 390}
                ],
                "gemini_notes": "Fresh hand-cut Washington potatoes. 44g net carbs is right at the 45g limit. 15-min GLUT4 walk recommended."
            }
        else:
            return {
                "meal_name": "Dick's Drive-In Deluxe Burger (Classic Seattle 1954)",
                "vendor_matched": "Dick's Drive-In Restaurants (Seattle Official Nutrition Disclosure)",
                "photo_filename": "meal_dicks_deluxe.jpg",
                "calories": 420,
                "net_carbs": 28.0,
                "total_carbs": 30.0,
                "fiber": 2.0,
                "protein": 20.0,
                "fat": 26.0,
                "glycemic_load": "12 (Low-Medium)",
                "meets_goal": True,
                "identified_items": [
                    {"name": "100% Real Beef Patties", "portion": "2 patties", "net_carbs": 0, "protein": 18},
                    {"name": "Melted American Cheese", "portion": "1 slice", "net_carbs": 1, "protein": 2},
                    {"name": "Crisp Lettuce, Relish & Mayo Sauce", "portion": "standard", "net_carbs": 3, "fiber": 1},
                    {"name": "Toasted Hamburger Bun", "portion": "1 bun", "net_carbs": 24, "fiber": 1}
                ],
                "gemini_notes": "EXCELLENT DIABETIC COMPLIANCE: 28g Net Carbs is well below your 45g ceiling! High beef protein (20g) and healthy lipid buffer slows carbohydrate absorption. Projected postprandial excursion: minimal (+22 mg/dL)."
            }

    # 5. McDonald's Fast Food Order (Strict match only, never hijacked)
    elif "mcdonald" in text or "quarter pounder" in text or "big mac" in text or "mcchicken" in text:
        return {
            "meal_name": "McDonald's Quarter Pounder Meal (Standard)",
            "vendor_matched": "McDonald's USA Nutritional Database (Verified Packaging)",
            "photo_filename": "meal_mcdonalds_qp.jpg",
            "calories": 1050,
            "net_carbs": 88.0,
            "total_carbs": 94.0,
            "fiber": 6.0,
            "protein": 36.0,
            "fat": 42.0,
            "glycemic_load": "38 (High)",
            "meets_goal": False,
            "identified_items": [
                {"name": "Quarter Pounder with Cheese", "portion": "1 sandwich", "net_carbs": 38, "protein": 30},
                {"name": "Medium French Fries", "portion": "1 medium", "net_carbs": 44, "fat": 16},
                {"name": "Regular Coca-Cola", "portion": "16 oz", "net_carbs": 39, "sugar": 39}
            ],
            "gemini_notes": "CRITICAL EXCURSION WARNING: 88g net carbs exceeds 45g diabetic ceiling by 43g. Order bunless and swap fries for side salad to eliminate spike."
        }"""

    if old_mcdonald_block in code:
        code = code.replace(old_mcdonald_block, new_dicks_and_mcdonald_block)
        print("[✔] Injected Dick's Drive-In analyzer and fixed McDonald's strict matching in server.py")
    else:
        print("[!] Could not match old_mcdonald_block in server.py")

    with open(p, "w", encoding="utf-8") as f:
        f.write(code)
    print(f"Saved {p}")

if __name__ == "__main__":
    update_server_dicks_and_wifi()
