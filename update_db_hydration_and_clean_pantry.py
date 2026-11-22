import sqlite3
import json

db_path = 'w11_htm_app/telemetry.db'
conn = sqlite3.connect(db_path)
cur = conn.cursor()

# 1. Add fluid_oz column to popular_meals and meal_entries if not present
cur.execute("PRAGMA table_info(popular_meals)")
pop_cols = [c[1] for c in cur.fetchall()]
if 'fluid_oz' not in pop_cols:
    cur.execute("ALTER TABLE popular_meals ADD COLUMN fluid_oz REAL DEFAULT 8.0")
    print("Added fluid_oz column to popular_meals")

if 'occasion' not in pop_cols:
    cur.execute("ALTER TABLE popular_meals ADD COLUMN occasion TEXT DEFAULT 'Meal'")
    print("Added occasion column to popular_meals")

cur.execute("PRAGMA table_info(meal_entries)")
meal_cols = [c[1] for c in cur.fetchall()]
if 'fluid_oz' not in meal_cols:
    cur.execute("ALTER TABLE meal_entries ADD COLUMN fluid_oz REAL DEFAULT 8.0")
    print("Added fluid_oz column to meal_entries")

# 2. Purge unwanted demo meals from popular_meals:
# user requested: "remove Chipoltle scrambled eggs and spinach, 8oz prime rib, 8oz ribeye and brocollie (2 entreis a dup)"
# Also remove salmon and McDonalds
cur.execute("DELETE FROM popular_meals WHERE id LIKE '%chipotle%' OR title LIKE '%chipotle%'")
cur.execute("DELETE FROM popular_meals WHERE id LIKE '%steak%' OR id LIKE '%ribeye%' OR title LIKE '%ribeye%' OR title LIKE '%prime rib%' OR description LIKE '%ribeye%'")
cur.execute("DELETE FROM popular_meals WHERE id = 'pkg-09' OR id = 'cell_29'")
cur.execute("DELETE FROM popular_meals WHERE id = 'breakfast' OR title LIKE '%scrambled eggs%spinach%'")
cur.execute("DELETE FROM popular_meals WHERE title LIKE '%salmon%' OR id LIKE '%salmon%'")
cur.execute("DELETE FROM popular_meals WHERE id = 'cell_27' OR id = 'cell_28'")
cur.execute("DELETE FROM popular_meals WHERE title LIKE '%mcdonald%'")

# 3. Insert or update the clean, curated meals and snacks with beverage in oz
curated_meals = [
    {
        "id": "meal01",
        "title": "Mon-Sat High-Fiber Metabolic Breakfast (MEAL-01)",
        "description": "1/2 cup fresh berries, 3 turkey sausage links, 1/2 banana, steel cut oats with cinnamon and flax seed, unsweetened oat milk.",
        "calories": 430,
        "net_carbs": 38.0,
        "total_carbs": 48.0,
        "fiber": 10.0,
        "protein": 24.0,
        "fat": 14.0,
        "glycemic_load": "12 (Low-Med)",
        "meets_goal": 1,
        "is_custom": 0,
        "fluid_oz": 8.0,
        "occasion": "Breakfast",
        "tips_json": json.dumps([
            "Steel cut oats and flaxseed mucilage blunt post-meal glucose rise.",
            "8 oz unsweetened oat milk provides hydration with zero added sugar (<1g net carbs).",
            "15-min GLUT4 walk keeps peak < 122 mg/dL."
        ])
    },
    {
        "id": "meal02",
        "title": "Sunday Protein & Sprouted Toast Breakfast (MEAL-02)",
        "description": "2 eggs scrambled, 3 turkey sausage links, 1 slice sprouted whole wheat toast, 1/2 cup fresh berries, fresh brewed black coffee.",
        "calories": 385,
        "net_carbs": 17.0,
        "total_carbs": 22.0,
        "fiber": 5.0,
        "protein": 29.0,
        "fat": 19.0,
        "glycemic_load": "5 (Very Low)",
        "meets_goal": 1,
        "is_custom": 0,
        "fluid_oz": 8.0,
        "occasion": "Breakfast",
        "tips_json": json.dumps([
            "High albumin protein-to-carb ratio (1.7:1) stimulates GLP-1 satiety.",
            "8 oz black coffee / water delivers zero glycemic impact hydration."
        ])
    },
    {
        "id": "soup_grilled_cheese",
        "title": "Weekly Vegetable & Meat Soup with Whole Wheat Grilled Cheese, Mini Orange & Apple Slices",
        "description": "Weekly vegetable & meat soup, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), 1/2 apple slices, spring water.",
        "calories": 493,
        "net_carbs": 34.0,
        "total_carbs": 43.2,
        "fiber": 9.2,
        "protein": 30.6,
        "fat": 22.2,
        "glycemic_load": "8 (Low)",
        "meets_goal": 1,
        "is_custom": 0,
        "fluid_oz": 16.0,
        "occasion": "Lunch",
        "tips_json": json.dumps([
            "Vegetable fiber and beef broth proteins blunt postprandial glycemic excursions.",
            "16 oz hydration (broth + spring water) expands intravascular plasma volume to reduce peak glucose concentration.",
            "Small fruit portioning (1.5oz orange + 1/2 apple) stays strictly within 45g net carb ceiling."
        ])
    },
    {
        "id": "dicks",
        "title": "Dick's Drive-In Deluxe Burger (Seattle 1954)",
        "description": "Dick's Deluxe 100% real beef double patties, melted cheese, lettuce, pickle relish and mayo on toasted bun (28g net carbs), iced water.",
        "calories": 420,
        "net_carbs": 28.0,
        "total_carbs": 30.0,
        "fiber": 2.0,
        "protein": 20.0,
        "fat": 26.0,
        "glycemic_load": "12 (Low-Med)",
        "meets_goal": 1,
        "is_custom": 0,
        "fluid_oz": 12.0,
        "occasion": "Lunch/Dinner",
        "tips_json": json.dumps([
            "28g Net Carbs is well below your 45g ceiling!",
            "Beef protein and lipid buffer slow carbohydrate uptake.",
            "12 oz filtered iced water provides essential hydration with zero glycemic impact."
        ])
    },
    {
        "id": "dicks_combo",
        "title": "Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Hand-Cut Fries & Large Diet",
        "description": "2 Dick's cheeseburgers, 3 house tartar sauces, 1 regular hand-cut fry, and 1 large diet drink (95g net carbs - GLUT4 walk required).",
        "calories": 1260,
        "net_carbs": 95.0,
        "total_carbs": 101.0,
        "fiber": 6.0,
        "protein": 35.0,
        "fat": 78.0,
        "glycemic_load": "38 (High Spike Risk)",
        "meets_goal": 0,
        "is_custom": 0,
        "fluid_oz": 24.0,
        "occasion": "Dinner",
        "tips_json": json.dumps([
            "95g Net Carbs exceeds 45g ceiling by +50g.",
            "24 oz diet beverage delivers zero glycemic hydration.",
            "Immediate 35-45 minute brisk GLUT4 walk strongly recommended."
        ])
    },
    {
        "id": "snack_clove_tea",
        "title": "[Snack] Brewed Clove & Ceylon Cinnamon Herbal Infusion",
        "description": "16 oz hot brewed whole clove & Ceylon cinnamon herbal infusion. Zero sugar, anti-inflammatory, polyphenol rich.",
        "calories": 2,
        "net_carbs": 0.5,
        "total_carbs": 0.5,
        "fiber": 0.0,
        "protein": 0.0,
        "fat": 0.0,
        "glycemic_load": "0 (Zero Glycemic Impact)",
        "meets_goal": 1,
        "is_custom": 0,
        "fluid_oz": 16.0,
        "occasion": "Snack",
        "tips_json": json.dumps([
            "16 oz herbal hydration stimulates renal clearance and decreases plasma viscosity.",
            "Eugenol and cinnamtannin B1 improve peripheral insulin receptor sensitivity.",
            "Full snack meal status with zero caloric or glycemic load."
        ])
    },
    {
        "id": "snack_apple_walnut",
        "title": "[Snack] Crisp Green Apple Slices & Raw California Walnuts",
        "description": "1/2 crisp green apple sliced with 1 oz raw California walnut halves and 12 oz chilled sparkling water.",
        "calories": 240,
        "net_carbs": 14.0,
        "total_carbs": 18.0,
        "fiber": 4.0,
        "protein": 5.0,
        "fat": 18.0,
        "glycemic_load": "4 (Very Low)",
        "meets_goal": 1,
        "is_custom": 0,
        "fluid_oz": 12.0,
        "occasion": "Snack",
        "tips_json": json.dumps([
            "Omega-3 fatty acids in walnuts slow gastric emptying of fructose.",
            "12 oz sparkling water supports optimal metabolic hydration.",
            "Ideal afternoon bridge meal maintaining steady 90-110 mg/dL glucose baseline."
        ])
    }
]

for m in curated_meals:
    cur.execute("""
        INSERT OR REPLACE INTO popular_meals
        (id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, fluid_oz, occasion, updated_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, datetime('now'))
    """, (
        m["id"], m["title"], m["description"], m["calories"], m["net_carbs"], m["total_carbs"],
        m["fiber"], m["protein"], m["fat"], m["glycemic_load"], m["tips_json"], m["meets_goal"],
        m["is_custom"], m["fluid_oz"], m["occasion"]
    ))

conn.commit()

print("Current popular_meals in DB:")
cur.execute("SELECT id, title, fluid_oz, occasion, net_carbs, calories FROM popular_meals")
for r in cur.fetchall():
    print(r)

# Update today's consumed meal entries with fluid_oz
cur.execute("UPDATE meal_entries SET fluid_oz = 8.0 WHERE id = 23") # Breakfast
cur.execute("UPDATE meal_entries SET fluid_oz = 16.0 WHERE id = 26") # Lunch (soup + water)
cur.execute("UPDATE meal_entries SET fluid_oz = 16.0 WHERE id = 25") # Snack (clove tea)
conn.commit()

print("\nToday's meal_entries in DB:")
cur.execute("SELECT id, timestamp, meal_name, fluid_oz, net_carbs, calories FROM meal_entries WHERE timestamp LIKE '2026-11-21%'")
for r in cur.fetchall():
    print(r)

conn.close()
print("Database hydration and meal pantry cleanup completed successfully!")
