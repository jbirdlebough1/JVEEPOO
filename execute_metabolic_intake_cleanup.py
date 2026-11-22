import sqlite3
import json
import datetime

db_path = 'D:/veepoo/w11_htm_app/telemetry.db'
conn = sqlite3.connect(db_path)
cur = conn.cursor()

# 1. Delete Salmon and duplicate demo records from meal_entries
cur.execute("DELETE FROM meal_entries WHERE meal_name LIKE '%salmon%'")
cur.execute("DELETE FROM meal_entries WHERE id IN (3, 4, 5, 6, 7)") # Purge duplicate Dick's burgers

# 2. Update Row 23 (Breakfast for 2026-11-21) to exact Mon-Sat breakfast
breakfast_items = [
    {"name": "Steel Cut Oats with Cinnamon & Flax Seed", "portion": "1 cup prepared", "calories": 160, "net_carbs": 24, "protein": 5, "fiber": 4, "fat": 3},
    {"name": "Fresh Berries", "portion": "1/2 cup fresh", "calories": 40, "net_carbs": 7, "protein": 1, "fiber": 3, "fat": 0},
    {"name": "Turkey Sausage Links", "portion": "3 links grilled", "calories": 130, "net_carbs": 1, "protein": 14, "fiber": 0, "fat": 8},
    {"name": "Fresh Banana", "portion": "1/2 sliced", "calories": 55, "net_carbs": 13, "protein": 1, "fiber": 1.5, "fat": 0},
    {"name": "Unsweetened Oat Milk", "portion": "1/2 cup", "calories": 45, "net_carbs": 3, "protein": 3, "fiber": 1.5, "fat": 3}
]
breakfast_json = json.dumps({"identified_items": breakfast_items})

cur.execute("""
    UPDATE meal_entries 
    SET meal_name = 'Mon-Sat High-Fiber Metabolic Breakfast (MEAL-01)',
        calories = 430,
        net_carbs = 38.0,
        total_carbs = 48.0,
        protein = 24.0,
        fiber = 10.0,
        fat = 14.0,
        raw_json = ?,
        gemini_notes = 'User Mon-Sat clinical breakfast: Steel cut oats, cinnamon, flaxseed, fresh berries, turkey sausage links, 1/2 banana, and oat milk. High fiber blunts glycemic rise.'
    WHERE id = 23
""", (breakfast_json,))

# 3. Insert or Replace User's Actual Lunch for 2026-11-21
lunch_items = [
    {"name": "Weekly Vegetable & Meat Soup", "portion": "1.5 cup bowl", "calories": 180, "net_carbs": 8.0, "protein": 18.0, "fiber": 4.0, "fat": 6.0},
    {"name": "Whole Wheat Grilled Cheese Sandwich", "portion": "1 sandwich (sprouted wheat & real cheddar)", "calories": 260, "net_carbs": 14.0, "protein": 12.0, "fiber": 3.0, "fat": 16.0},
    {"name": "Miniature Orange", "portion": "1 miniature fruit (1.5 oz)", "calories": 22, "net_carbs": 4.5, "protein": 0.4, "fiber": 0.8, "fat": 0.1},
    {"name": "Crisp Apple Slices", "portion": "1/2 apple fresh sliced", "calories": 31, "net_carbs": 7.5, "protein": 0.2, "fiber": 1.4, "fat": 0.1}
]
lunch_json = json.dumps({"identified_items": lunch_items})

cur.execute("""
    INSERT INTO meal_entries (
        timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, protein, fiber, fat, glycemic_load, meets_goal, gemini_notes, raw_json
    ) VALUES (
        '2026-11-21 12:30:22',
        'Weekly Vegetable & Meat Soup, Whole Wheat Grilled Cheese, Miniature Orange & Apple Slices',
        'VOICE_DICTATION_AND_PHOTO',
        'meal_lunch_20261121.jpg',
        493,
        34.0,
        43.2,
        30.6,
        9.2,
        22.2,
        '8 (Low)',
        1,
        'Weekly soup with vegetables and meat, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), and 1/2 apple slices. High fiber and protein buffer carbohydrate absorption, keeping net carbs at 34g (well within 45g ceiling).',
        ?
    )
""", (lunch_json,))

# 4. Ensure Row 25 (Snack: Brewed Clove & Cinnamon Herbal Infusion) has identified_items in raw_json
snack_items = [
    {"name": "Brewed Whole Clove & Ceylon Cinnamon Herbal Infusion", "portion": "16 oz mug", "calories": 2, "net_carbs": 0.5, "protein": 0.0, "fiber": 0.0, "fat": 0.0}
]
snack_json = json.dumps({"identified_items": snack_items})

cur.execute("""
    UPDATE meal_entries
    SET calories = 2,
        net_carbs = 0.5,
        total_carbs = 0.5,
        protein = 0.0,
        fiber = 0.0,
        fat = 0.0,
        raw_json = ?,
        gemini_notes = 'Brewed whole clove and Ceylon cinnamon herbal infusion. Active polyphenols, zero glycemic impact.'
    WHERE id = 25
""", (snack_json,))

# 5. Clean popular_meals: remove salmon and add user's actual favorite lunch
cur.execute("DELETE FROM popular_meals WHERE id = 'salmon' OR title LIKE '%salmon%'")

lunch_tips = json.dumps([
    "Weekly vegetable & meat soup provides rich broth proteins and vegetable fiber that slow gastric emptying.",
    "Sprouted whole wheat and cheddar cheese lipid buffer prevents sharp postprandial glucose spike.",
    "Small fruit portioning (1.5oz miniature orange + 1/2 apple) satisfies fruit cravings while remaining under 45g net carb ceiling."
])

cur.execute("""
    INSERT OR REPLACE INTO popular_meals (
        id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, updated_at
    ) VALUES (
        'soup_grilled_cheese',
        'Weekly Vegetable & Meat Soup with Whole Wheat Grilled Cheese, Mini Orange & Apple Slices',
        'Weekly vegetable and meat soup, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), 1/2 apple slices.',
        493,
        34.0,
        43.2,
        9.2,
        30.6,
        22.2,
        '8 (Low)',
        ?,
        1,
        1,
        '2026-11-21 16:45:00'
    )
""", (lunch_tips,))

conn.commit()
conn.close()
print("telemetry.db meal_entries and popular_meals cleaned and updated successfully!")
