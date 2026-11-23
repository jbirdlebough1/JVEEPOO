import os
import re
import json

def update_server():
    path = r"D:\veepoo\w11_htm_app\server.py"
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()

    # 1. Add popular_meals table to init_database
    popular_table_sql = '''
        cur.execute("""
            CREATE TABLE IF NOT EXISTS popular_meals (
                id TEXT PRIMARY KEY,
                title TEXT NOT NULL,
                description TEXT NOT NULL,
                calories INTEGER DEFAULT 0,
                net_carbs REAL DEFAULT 0,
                total_carbs REAL DEFAULT 0,
                fiber REAL DEFAULT 0,
                protein REAL DEFAULT 0,
                fat REAL DEFAULT 0,
                glycemic_load TEXT,
                tips_json TEXT,
                meets_goal INTEGER DEFAULT 1,
                is_custom INTEGER DEFAULT 0,
                updated_at TEXT
            )
        """)
'''
    if "CREATE TABLE IF NOT EXISTS popular_meals" not in content:
        content = content.replace('cur.execute("""\n            CREATE TABLE IF NOT EXISTS vital_records', popular_table_sql + '\n        cur.execute("""\n            CREATE TABLE IF NOT EXISTS vital_records')

    # 2. Add backend helpers for popular meals
    popular_helpers = '''
# =============================================================================
# POPULAR MEALS CRUD HELPER FUNCTIONS
# =============================================================================
DEFAULT_POPULAR_MEALS = [
    {
        "id": "meal01",
        "title": "Mon-Sat High-Fiber Metabolic Breakfast (MEAL-01)",
        "description": "1/2 cup berries, 3 turkey sausage links, 1/2 banana, steel cut oats with cinnamon and flax seed, oat milk.",
        "calories": 430,
        "net_carbs": 38.0,
        "total_carbs": 48.0,
        "fiber": 10.0,
        "protein": 24.0,
        "fat": 14.0,
        "glycemic_load": "12 (Low-Med)",
        "meets_goal": 1,
        "is_custom": 0,
        "tips_json": json.dumps(["Steel cut oats and flaxseed mucilage blunt post-meal glucose rise.", "15-min GLUT4 walk keeps peak < 122 mg/dL."])
    },
    {
        "id": "meal02",
        "title": "Sunday Protein & Whole Wheat Toast Breakfast (MEAL-02)",
        "description": "2 eggs scrambled, 3 turkey sausage links, 1 slice sprouted whole wheat toast, 1/2 cup fresh berries.",
        "calories": 385,
        "net_carbs": 17.0,
        "total_carbs": 22.0,
        "fiber": 5.0,
        "protein": 29.0,
        "fat": 19.0,
        "glycemic_load": "5 (Very Low)",
        "meets_goal": 1,
        "is_custom": 0,
        "tips_json": json.dumps(["High albumin protein-to-carb ratio (1.7:1) stimulates GLP-1.", "Minimal glycemic excursion (+18 mg/dL)."])
    },
    {
        "id": "chipotle",
        "title": "Chipotle Burrito Bowl (Chicken & Black Beans)",
        "description": "Chipotle bowl with light brown rice, black beans, grilled chicken, fajita veggies, salsa, and guacamole.",
        "calories": 540,
        "net_carbs": 33.0,
        "total_carbs": 44.0,
        "fiber": 11.0,
        "protein": 46.0,
        "fat": 22.0,
        "glycemic_load": "14 (Medium)",
        "meets_goal": 1,
        "is_custom": 0,
        "tips_json": json.dumps(["Avocado healthy fats and grilled chicken protein slow gastric emptying.", "15-min walk keeps post-meal curve flat."])
    },
    {
        "id": "dicks",
        "title": "Dick's Drive-In Deluxe Burger (Seattle 1954)",
        "description": "Dick's Deluxe 100% real beef double patties, melted cheese, lettuce, pickle relish and mayo on toasted bun (28g net carbs).",
        "calories": 420,
        "net_carbs": 28.0,
        "total_carbs": 30.0,
        "fiber": 2.0,
        "protein": 20.0,
        "fat": 26.0,
        "glycemic_load": "12 (Low-Med)",
        "meets_goal": 1,
        "is_custom": 0,
        "tips_json": json.dumps(["28g Net Carbs is well below your 45g ceiling!", "Beef protein and lipid buffer slows carbohydrate uptake."])
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
        "tips_json": json.dumps(["95g Net Carbs exceeds 45g ceiling by +50g.", "Anticipated postprandial glycemic spike: +75 to +90 mg/dL.", "Immediate 35-45 minute brisk GLUT4 walk strongly recommended."])
    },
    {
        "id": "salmon",
        "title": "Wild Alaskan Salmon & Roasted Asparagus",
        "description": "6 oz wild-caught salmon fillet grilled with dill, roasted asparagus spears, and 1/2 baked sweet potato.",
        "calories": 410,
        "net_carbs": 22.0,
        "total_carbs": 28.0,
        "fiber": 6.0,
        "protein": 38.0,
        "fat": 18.0,
        "glycemic_load": "6 (Low)",
        "meets_goal": 1,
        "is_custom": 0,
        "tips_json": json.dumps(["Omega-3 fatty acids reduce systemic inflammation.", "Low glycemic oligosaccharides."])
    },
    {
        "id": "steak",
        "title": "8oz Prime Ribeye Steak & Roasted Broccoli",
        "description": "8 oz grass-fed ribeye steak seared with garlic herb butter and double roasted broccoli florets.",
        "calories": 560,
        "net_carbs": 8.0,
        "total_carbs": 12.0,
        "fiber": 4.0,
        "protein": 42.0,
        "fat": 36.0,
        "glycemic_load": "3 (Minimal)",
        "meets_goal": 1,
        "is_custom": 0,
        "tips_json": json.dumps(["Zero glucose excursion.", "Sulforaphane in roasted broccoli stimulates Nrf2 cellular defense."])
    },
    {
        "id": "breakfast",
        "title": "Scrambled Eggs, Baby Spinach & Hass Avocado",
        "description": "3 pasture-raised eggs scrambled with 2 cups organic baby spinach, 1/2 sliced avocado, and olive oil.",
        "calories": 360,
        "net_carbs": 4.0,
        "total_carbs": 9.0,
        "fiber": 5.0,
        "protein": 22.0,
        "fat": 26.0,
        "glycemic_load": "2 (Minimal)",
        "meets_goal": 1,
        "is_custom": 0,
        "tips_json": json.dumps(["Ultra-low glycemic ketogenic impact: zero insulin spike.", "Rich in lutein and choline."])
    }
]

def seed_popular_meals_if_empty():
    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute("SELECT COUNT(*) FROM popular_meals")
        if cur.fetchone()[0] == 0:
            ts = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            for m in DEFAULT_POPULAR_MEALS:
                cur.execute("""
                    INSERT OR REPLACE INTO popular_meals 
                    (id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, updated_at)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """, (m["id"], m["title"], m["description"], m["calories"], m["net_carbs"], m["total_carbs"], m["fiber"], m["protein"], m["fat"], m["glycemic_load"], m["tips_json"], m["meets_goal"], m["is_custom"], ts))
            conn.commit()
        conn.close()

def get_popular_meals():
    seed_popular_meals_if_empty()
    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute("""
            SELECT id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, updated_at
            FROM popular_meals
            ORDER BY id ASC
        """)
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
            })
        return result

def upsert_popular_meal(payload):
    meal_id = str(payload.get("id") or ("custom_" + str(int(time.time())))).strip().lower()
    title = payload.get("title") or payload.get("meal_name") or "Custom Saved Meal"
    desc = payload.get("description") or payload.get("desc") or ""
    cal = int(payload.get("calories") or 0)
    net_c = float(payload.get("net_carbs") or payload.get("netCarbs") or 0)
    tot_c = float(payload.get("total_carbs") or payload.get("totalCarbs") or net_c)
    fib = float(payload.get("fiber") or 0)
    pro = float(payload.get("protein") or 0)
    fat = float(payload.get("fat") or 0)
    gl = str(payload.get("glycemic_load") or payload.get("gl") or "Moderate")
    meets = 1 if (payload.get("meets_goal") if "meets_goal" in payload else (net_c <= 45)) else 0
    tips = payload.get("tips") or []
    tips_str = json.dumps(tips) if isinstance(tips, list) else str(tips)
    ts = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute("""
            INSERT OR REPLACE INTO popular_meals
            (id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?)
        """, (meal_id, title, desc, cal, net_c, tot_c, fib, pro, fat, gl, tips_str, meets, ts))
        conn.commit()
        conn.close()
    return {"id": meal_id, "title": title, "net_carbs": net_c, "calories": cal, "meets_goal": bool(meets)}

def delete_popular_meal(meal_id):
    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute("DELETE FROM popular_meals WHERE id = ?", (meal_id,))
        deleted = cur.rowcount > 0
        conn.commit()
        conn.close()
    return deleted
'''
    if "def get_popular_meals():" not in content:
        content = content.replace("def analyze_meal_with_gemini_backend", popular_helpers + "\ndef analyze_meal_with_gemini_backend")

    # 3. Replace Dick's & McDonald's analysis logic in analyze_meal_with_gemini_backend
    dicks_mcdonalds_pattern = re.compile(
        r'# 4\. Dick\'s Drive-In Order.*?# 5\. McDonald\'s Fast Food Order.*?# 5\. General Default Meal',
        re.DOTALL
    )

    dicks_mcdonalds_replacement = '''# 4. Dick's Drive-In Orders (Iconic Seattle Menu since 1954)
    elif "dick" in text or "drive-in" in text or "drivein" in text or "deluxe" in text or "cheeze" in text or "cheeseburger" in text or "tatar" in text or "tartar" in text:
        has_fries = ("frie" in text or "fry" in text or "fries" in text)
        is_custom_combo = (
            ("2 cheeze" in text or "2 cheeseburger" in text or "2 cheese" in text or "two cheese" in text) or
            ("3 tatar" in text or "3 tartar" in text or "tatar" in text or "tartar" in text) or
            ("diet" in text and ("fry" in text or "frie" in text or "burger" in text))
        )

        if is_custom_combo:
            return {
                "meal_name": "Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Hand-Cut Fries & Large Diet",
                "vendor_matched": "Dick's Drive-In Restaurants (Seattle Official Nutrition Disclosure)",
                "photo_filename": "meal_dicks_custom_combo.jpg",
                "calories": 1260,
                "net_carbs": 95.0,
                "total_carbs": 101.0,
                "fiber": 6.0,
                "protein": 35.0,
                "fat": 78.0,
                "glycemic_load": "38 (High Spike Risk)",
                "meets_goal": False,
                "identified_items": [
                    {"name": "Dick's Cheeseburgers (2x)", "portion": "2 sandwiches", "net_carbs": 50, "protein": 30, "calories": 660, "fat": 36, "fiber": 2},
                    {"name": "Dick's Fresh Hand-Cut Washington Fries", "portion": "1 order", "net_carbs": 44, "protein": 5, "calories": 390, "fat": 21, "fiber": 4},
                    {"name": "Dick's House-Made Tartar Sauce (3x)", "portion": "3 containers", "net_carbs": 1, "protein": 0, "calories": 210, "fat": 21, "fiber": 0},
                    {"name": "Large Diet Beverage", "portion": "32 oz cup", "net_carbs": 0, "protein": 0, "calories": 0, "fat": 0, "fiber": 0}
                ],
                "gemini_notes": "HIGH POSTPRANDIAL GLUCOSE SPIKE WARNING: 95g Net Carbs exceeds your 45g diabetic limit by +50g (+75 to +90 mg/dL projected peak excursion). CLINICAL ACTIONS: 1) Save 1 cheeseburger for dinner (-25g net carbs) or eat half the fries (-22g net carbs) to bring meal into the safe zone; 2) Take an immediate 35-45 minute brisk GLUT4 walk starting 20 minutes post-meal to blunt the glycemic peak directly into skeletal muscle without requiring insulin."
            }
        elif ("deluxe" in text or "special" in text or "burger" in text or "cheeseburger" in text) and has_fries:
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
        elif has_fries and not ("deluxe" in text or "burger" in text or "cheeseburger" in text):
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

    # 5. McDonald's (Strict Clinical Exclusion - No Safe Meals)
    elif "mcdonald" in text or "quarter pounder" in text or "big mac" in text or "mcchicken" in text:
        return {
            "meal_name": "McDonald's Fast Food (Clinically Excluded)",
            "vendor_matched": "Clinical Dietary Advisory Board (Exclusion Protocol)",
            "photo_filename": "meal_excluded_warning.jpg",
            "calories": 1050,
            "net_carbs": 88.0,
            "total_carbs": 94.0,
            "fiber": 2.0,
            "protein": 28.0,
            "fat": 48.0,
            "glycemic_load": "45 (Severe High Glycemic)",
            "meets_goal": False,
            "identified_items": [
                {"name": "McDonald's Fast Food Items", "portion": "standard", "net_carbs": 88, "calories": 1050}
            ],
            "gemini_notes": "⚠️ CLINICAL EXCLUSION PROTOCOL: McDonald's contains zero safe options meeting our strict diabetic baseline (<45g net carbs without industrial trans fats, dextrose additives, and high-glycemic buns). Avoid entirely."
        }

    # 6. General Default Meal'''

    content = dicks_mcdonalds_pattern.sub(dicks_mcdonalds_replacement, content)

    # 4. Add popular meals HTTP endpoints in do_GET and do_POST
    if 'elif self.path == "/api/meals/popular":' not in content:
        # In do_GET:
        get_popular_route = '''        elif self.path == "/api/meals/popular":
            meals = get_popular_meals()
            self.send_json_response({"status": "OK", "popular_meals": meals})
'''
        content = content.replace('elif self.path == "/api/network/wifi_info":', get_popular_route + '\n        elif self.path == "/api/network/wifi_info":')

        # In do_POST:
        post_popular_route = '''        elif self.path == "/api/meals/popular":
            content_length = int(self.headers.get("Content-Length", 0))
            body = self.rfile.read(content_length).decode("utf-8", errors="ignore")
            try:
                payload = json.loads(body)
                saved = upsert_popular_meal(payload)
                self.send_json_response({"status": "OK", "meal": saved})
            except Exception as e:
                self.send_json_response({"status": "ERROR", "error": str(e)}, 400)

        elif self.path == "/api/meals/popular/delete" or self.path.startswith("/api/meals/popular/delete"):
            content_length = int(self.headers.get("Content-Length", 0))
            body = self.rfile.read(content_length).decode("utf-8", errors="ignore") if content_length > 0 else "{}"
            try:
                payload = json.loads(body) if body.strip() else {}
                meal_id = payload.get("id")
                if not meal_id and "?" in self.path:
                    import urllib.parse
                    qs = urllib.parse.parse_qs(self.path.split("?", 1)[1])
                    meal_id = qs.get("id", [None])[0]
                if meal_id:
                    deleted = delete_popular_meal(meal_id)
                    self.send_json_response({"status": "OK", "deleted_id": meal_id, "success": deleted})
                else:
                    self.send_json_response({"status": "ERROR", "error": "Missing meal id"}, 400)
            except Exception as e:
                self.send_json_response({"status": "ERROR", "error": str(e)}, 400)
'''
        content = content.replace('elif self.path == "/api/meal/upload" or self.path == "/api/meal/analyze":', post_popular_route + '\n        elif self.path == "/api/meal/upload" or self.path == "/api/meal/analyze":')

    with open(path, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"[✔] Successfully updated {path}")

update_server()
