import re

with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    code = f.read()

# 1. Update DEFAULT_POPULAR_MEALS
old_default_popular_meals_re = re.compile(r'DEFAULT_POPULAR_MEALS = \[.*?\]\n\ndef seed_popular_meals_if_empty', re.DOTALL)

new_default_popular_meals = """DEFAULT_POPULAR_MEALS = [
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
            "8 oz unsweetened oat milk provides essential hydration with zero added sugar (<1g net carb).",
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

def seed_popular_meals_if_empty"""

assert old_default_popular_meals_re.search(code) is not None, "Could not find DEFAULT_POPULAR_MEALS in server.py"
code = old_default_popular_meals_re.sub(new_default_popular_meals, code, count=1)

# 2. Update seed_popular_meals_if_empty
old_seed = """            for m in DEFAULT_POPULAR_MEALS:
                cur.execute(\"\"\"
                    INSERT OR REPLACE INTO popular_meals 
                    (id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, updated_at)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                \"\"\", (m["id"], m["title"], m["description"], m["calories"], m["net_carbs"], m["total_carbs"], m["fiber"], m["protein"], m["fat"], m["glycemic_load"], m["tips_json"], m["meets_goal"], m["is_custom"], ts))"""

new_seed = """            for m in DEFAULT_POPULAR_MEALS:
                cur.execute(\"\"\"
                    INSERT OR REPLACE INTO popular_meals 
                    (id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, fluid_oz, occasion, updated_at)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                \"\"\", (m["id"], m["title"], m["description"], m["calories"], m["net_carbs"], m["total_carbs"], m["fiber"], m["protein"], m["fat"], m["glycemic_load"], m["tips_json"], m["meets_goal"], m["is_custom"], m.get("fluid_oz", 8.0), m.get("occasion", "Meal"), ts))"""

assert old_seed in code, "old_seed not found in server.py"
code = code.replace(old_seed, new_seed, 1)

# 3. Update get_popular_meals
old_get_pop = """        cur.execute(\"\"\"
            SELECT id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, updated_at
            FROM popular_meals
            ORDER BY is_custom DESC, updated_at DESC, id ASC
        \"\"\")
        rows = cur.fetchall()
        conn.close()
        result = []
        for r in rows:
            tips = []
            try:
                if r[10]: tips = json.loads(r[10])
            except Exception: pass
            result.append({
                "id": r[0], "title": r[1], "description": r[2], "calories": r[3],
                "net_carbs": r[4], "total_carbs": r[5], "fiber": r[6], "protein": r[7],
                "fat": r[8], "glycemic_load": r[9], "tips": tips, "meets_goal": bool(r[11]),
                "is_custom": bool(r[12]), "updated_at": r[13]
            })"""

new_get_pop = """        cur.execute(\"\"\"
            SELECT id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, updated_at, fluid_oz, occasion
            FROM popular_meals
            ORDER BY is_custom DESC, updated_at DESC, id ASC
        \"\"\")
        rows = cur.fetchall()
        conn.close()
        result = []
        for r in rows:
            tips = []
            try:
                if r[10]: tips = json.loads(r[10])
            except Exception: pass
            result.append({
                "id": r[0], "title": r[1], "description": r[2], "calories": r[3],
                "net_carbs": r[4], "total_carbs": r[5], "fiber": r[6], "protein": r[7],
                "fat": r[8], "glycemic_load": r[9], "tips": tips, "meets_goal": bool(r[11]),
                "is_custom": bool(r[12]), "updated_at": r[13],
                "fluid_oz": r[14] if len(r) > 14 and r[14] is not None else 8.0,
                "occasion": r[15] if len(r) > 15 and r[15] is not None else "Meal"
            })"""

assert old_get_pop in code, "old_get_pop not found in server.py"
code = code.replace(old_get_pop, new_get_pop, 1)

# 4. Update upsert_popular_meal
old_upsert = """    meets = 1 if (payload.get("meets_goal") if "meets_goal" in payload else (net_c <= 45)) else 0
    tips = payload.get("tips") or []
    tips_str = json.dumps(tips) if isinstance(tips, list) else str(tips)
    ts = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute(\"\"\"
            INSERT OR REPLACE INTO popular_meals
            (id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?)
        \"\"\", (meal_id, title, desc, cal, net_c, tot_c, fib, pro, fat, gl, tips_str, meets, ts))"""

new_upsert = """    meets = 1 if (payload.get("meets_goal") if "meets_goal" in payload else (net_c <= 45)) else 0
    fluid_oz = float(payload.get("fluid_oz") or payload.get("fluidOz") or 8.0)
    occasion = str(payload.get("occasion") or "Meal")
    tips = payload.get("tips") or []
    tips_str = json.dumps(tips) if isinstance(tips, list) else str(tips)
    ts = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute(\"\"\"
            INSERT OR REPLACE INTO popular_meals
            (id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, fluid_oz, occasion, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?, ?, ?)
        \"\"\", (meal_id, title, desc, cal, net_c, tot_c, fib, pro, fat, gl, tips_str, meets, fluid_oz, occasion, ts))"""

assert old_upsert in code, "old_upsert not found in server.py"
code = code.replace(old_upsert, new_upsert, 1)

# 5. Update insert_meal_entry and get_recent_meals
old_insert_sig = 'def insert_meal_entry(meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json=""):'
new_insert_sig = 'def insert_meal_entry(meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json="", fluid_oz=8.0):'
assert old_insert_sig in code, "old_insert_sig not found"
code = code.replace(old_insert_sig, new_insert_sig, 1)

old_insert_sql = """        cur.execute(\"\"\"
            INSERT INTO meal_entries (timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        \"\"\", (ts, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json))"""

new_insert_sql = """        cur.execute(\"\"\"
            INSERT INTO meal_entries (timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json, fluid_oz)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        \"\"\", (ts, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json, fluid_oz))"""

assert old_insert_sql in code, "old_insert_sql not found"
code = code.replace(old_insert_sql, new_insert_sql, 1)

# Update get_recent_meals
old_recent_sql = """        cur.execute(\"\"\"
            SELECT id, timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json
            FROM meal_entries
            ORDER BY timestamp DESC, id DESC LIMIT ?
        \"\"\", (limit,))"""

new_recent_sql = """        cur.execute(\"\"\"
            SELECT id, timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json, fluid_oz
            FROM meal_entries
            ORDER BY timestamp DESC, id DESC LIMIT ?
        \"\"\", (limit,))"""

assert old_recent_sql in code, "old_recent_sql not found"
code = code.replace(old_recent_sql, new_recent_sql, 1)

old_recent_append = """            res.append({
                "id": r[0], "timestamp": r[1], "meal_name": r[2], "source": r[3],
                "photo_filename": r[4], "calories": r[5], "net_carbs": r[6], "total_carbs": r[7],
                "fiber": r[8], "protein": r[9], "fat": r[10], "glycemic_load": r[11],
                "vendor_matched": r[12], "gemini_notes": r[13], "meets_goal": bool(r[14]),
                "raw_json": r[15],
                "identified_items": raw_obj.get("identified_items", [])
            })"""

new_recent_append = """            res.append({
                "id": r[0], "timestamp": r[1], "meal_name": r[2], "source": r[3],
                "photo_filename": r[4], "calories": r[5], "net_carbs": r[6], "total_carbs": r[7],
                "fiber": r[8], "protein": r[9], "fat": r[10], "glycemic_load": r[11],
                "vendor_matched": r[12], "gemini_notes": r[13], "meets_goal": bool(r[14]),
                "raw_json": r[15],
                "identified_items": raw_obj.get("identified_items", []),
                "fluid_oz": r[16] if len(r) > 16 and r[16] is not None else 8.0
            })"""

assert old_recent_append in code, "old_recent_append not found"
code = code.replace(old_recent_append, new_recent_append, 1)

# In do_POST for /api/meal/record:
idx_rec = code.find('if not analyze_only:')
idx_rec_end = code.find('raw_json=json.dumps(analysis)', idx_rec)
idx_paren = code.find(')', idx_rec_end)

old_rec_block = code[idx_rec:idx_paren+1]
new_rec_block = """if not analyze_only:
                    fluid_oz = float(payload.get("fluid_oz") or payload.get("fluidOz") or 8.0)
                    meal_id, ts = insert_meal_entry(
                        meal_name=analysis["meal_name"],
                        source=source,
                        photo_filename=analysis.get("photo_filename", "meal_cell_photo.jpg"),
                        calories=analysis["calories"],
                        net_carbs=analysis["net_carbs"],
                        total_carbs=analysis["total_carbs"],
                        fiber=analysis["fiber"],
                        protein=analysis["protein"],
                        fat=analysis["fat"],
                        glycemic_load=analysis["glycemic_load"],
                        vendor_matched=analysis.get("vendor_matched", "Home Recipe"),
                        gemini_notes=analysis["gemini_notes"],
                        meets_goal=1 if analysis["meets_goal"] else 0,
                        raw_json=json.dumps(analysis),
                        fluid_oz=fluid_oz
                    )"""

code = code.replace(old_rec_block, new_rec_block, 1)

with open('w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(code)

print("w11_htm_app/server.py patched successfully!")
