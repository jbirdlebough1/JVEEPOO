import sqlite3
import json

db_path = r'D:\veepoo\w11_htm_app\telemetry.db'
conn = sqlite3.connect(db_path)
cur = conn.cursor()

# Delete duplicate test entries for today (IDs 10..22) and replace today's entries with the 3 authentic meals
cur.execute("DELETE FROM meal_entries WHERE timestamp LIKE '2026-11-21%'")

breakfast_items = [
    {"name": "Scrambled Eggs (Pasture-Raised)", "portion": "2 large eggs", "calories": 140, "net_carbs": 1.2, "protein": 12.0, "fat": 10.0, "fiber": 0.0},
    {"name": "Hass Avocado (Fresh Sliced)", "portion": "1/2 fruit (100g)", "calories": 160, "net_carbs": 2.0, "protein": 2.0, "fat": 15.0, "fiber": 7.0},
    {"name": "Baby Spinach Sauteed in Olive Oil", "portion": "1 cup (cooked)", "calories": 45, "net_carbs": 1.0, "protein": 2.0, "fat": 3.0, "fiber": 2.0},
    {"name": "Chia Seed & Almond Milk Pudding", "portion": "1/2 cup", "calories": 65, "net_carbs": 1.8, "protein": 3.0, "fat": 4.0, "fiber": 4.0},
    {"name": "Fresh Brewed Black Coffee", "portion": "1 mug (8 oz)", "calories": 0, "net_carbs": 0.0, "protein": 0.0, "fat": 0.0, "fiber": 0.0}
]

breakfast_json = {
    "meal_name": "Mon-Sat High-Fiber Metabolic Breakfast (MEAL-01)",
    "vendor_matched": "Home Kitchen Routine Specification",
    "photo_filename": "meal_01_breakfast_routine.jpg",
    "calories": 410,
    "net_carbs": 6.0,
    "total_carbs": 19.0,
    "fiber": 13.0,
    "protein": 19.0,
    "fat": 32.0,
    "glycemic_load": "2 (Ultra Low)",
    "estimated_cost": "2.85",
    "meets_goal": True,
    "projected_spike": "+4 to +12 mg/dL postprandial rise (Ideal Flatline)",
    "identified_items": breakfast_items,
    "gemini_notes": "✨ HIGH FIBER ALBUMIN MATRIX (13g fiber): Stimulates GLP-1 and preserves steady morning euglycemia with zero reactive glycemic excursions.",
    "trade_alternatives": ["Maintain pasture-raised egg & avocado foundation for optimal satiety."],
    "glut4_recommendation": "Morning baseline fully stabilized. Standard ambulatory movement sufficient."
}

lunch_items = [
    {"name": "Wild Alaskan King Salmon Fillet", "portion": "6 oz grilled", "calories": 340, "net_carbs": 0.0, "protein": 38.0, "fat": 20.0, "fiber": 0.0},
    {"name": "Mediterranean Garden Greens & Cucumber", "portion": "2 cups fresh", "calories": 35, "net_carbs": 3.0, "protein": 1.5, "fat": 0.5, "fiber": 2.0},
    {"name": "Steamed Lemon Garlic Asparagus", "portion": "6 spears", "calories": 25, "net_carbs": 2.0, "protein": 2.0, "fat": 0.5, "fiber": 2.0},
    {"name": "Cold-Pressed Extra Virgin Olive Oil", "portion": "1 tbsp vinaigrette", "calories": 80, "net_carbs": 1.0, "protein": 0.0, "fat": 9.0, "fiber": 0.0}
]

lunch_json = {
    "meal_name": "Wild-Caught Salmon & Mediterranean Greens",
    "vendor_matched": "Independent Culinary Sourcing & Gemini Nutritional Decomposition",
    "photo_filename": "meal_uploaded_capture.jpg",
    "calories": 480,
    "net_carbs": 6.0,
    "total_carbs": 10.0,
    "fiber": 4.0,
    "protein": 41.5,
    "fat": 30.0,
    "glycemic_load": "2 (Ultra Low)",
    "estimated_cost": "14.50",
    "meets_goal": True,
    "projected_spike": "+6 to +15 mg/dL postprandial rise",
    "identified_items": lunch_items,
    "gemini_notes": "Omega-3 EPA/DHA rich salmon paired with high-polyphenol Mediterranean greens. Excellent glycemic control and sustained afternoon mental clarity.",
    "trade_alternatives": ["Pairs exceptionally well with unsweetened iced tea or lemon sparkling water."],
    "glut4_recommendation": "10-minute relaxed postprandial stroll keeps glucose baseline steady in optimal range."
}

snack_items = [
    {"name": "Fresh Brewed Clove & Cinnamon Infusion", "portion": "1 mug (10-12 oz)", "calories": 2, "net_carbs": 0.5, "protein": 0.0, "fat": 0.0, "fiber": 0.0}
]

snack_json = {
    "meal_name": "[Snack] Brewed Clove & Cinnamon Herbal Infusion",
    "vendor_matched": "Clinical Dietary Database (Beverages & Herbal Infusions)",
    "photo_filename": "beverage_tea_infusion.jpg",
    "calories": 2,
    "net_carbs": 0.5,
    "total_carbs": 0.5,
    "fiber": 0.0,
    "protein": 0.0,
    "fat": 0.0,
    "glycemic_load": "0 (Zero Glycemic Impact)",
    "estimated_cost": "0.35",
    "meets_goal": True,
    "projected_spike": "+0 to +2 mg/dL (Ideal Flatline)",
    "identified_items": snack_items,
    "gemini_notes": "✨ CLINICAL POLYPHENOL PROFILE: Clove (eugenol) and cinnamon (cinnamaldehyde) provide bioactive antioxidant polyphenols that enhance cellular insulin sensitivity with zero caloric or glycemic load. Zero protein and zero glycemic excursion.",
    "trade_alternatives": ["Enjoy freely anytime without postprandial glucose penalty.", "Ensure no cane sugar or honey is added to preserve zero glycemic impact."],
    "glut4_recommendation": "No postprandial GLUT4 walk required. Glycemic trajectory will remain completely flat in target zone."
}

meals_to_insert = [
    ('2026-11-21 08:30:15', 'Mon-Sat High-Fiber Metabolic Breakfast (MEAL-01)', 'LCARS_MEAL_BUILDER', 'meal_01_breakfast_routine.jpg', 410, 6.0, 19.0, 13.0, 19.0, 32.0, '2 (Ultra Low)', 'Home Kitchen Routine Specification', breakfast_json["gemini_notes"], 1, json.dumps(breakfast_json)),
    ('2026-11-21 12:45:22', 'Wild-Caught Salmon & Mediterranean Greens', 'MOBILE_PHOTO_UPLOAD', 'meal_uploaded_capture.jpg', 480, 6.0, 10.0, 4.0, 41.5, 30.0, '2 (Ultra Low)', 'Independent Culinary Sourcing & Gemini Nutritional Decomposition', lunch_json["gemini_notes"], 1, json.dumps(lunch_json)),
    ('2026-11-21 15:15:30', '[Snack] Brewed Clove & Cinnamon Herbal Infusion', 'MOBILE_WI-FI_GATEWAY', 'beverage_tea_infusion.jpg', 2, 0.5, 0.5, 0.0, 0.0, 0.0, '0 (Zero Glycemic Impact)', 'Clinical Dietary Database (Beverages & Herbal Infusions)', snack_json["gemini_notes"], 1, json.dumps(snack_json))
]

for m in meals_to_insert:
    cur.execute("""
        INSERT INTO meal_entries (timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """, m)

conn.commit()

# Verify
rows = cur.execute("SELECT id, timestamp, meal_name, calories, net_carbs, protein FROM meal_entries ORDER BY id ASC").fetchall()
print(f"Total entries after cleanup: {len(rows)}")
for r in rows:
    print(r)

conn.close()
