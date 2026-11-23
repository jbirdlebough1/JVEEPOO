with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

# 1. Update get_recent_meals
old_get_recent = '''def get_recent_meals(limit=100):
    with db_lock:
        conn = sqlite3.connect(DB_PATH)
        cur = conn.cursor()
        cur.execute("""
            SELECT id, timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal
            FROM meal_entries
            ORDER BY timestamp DESC, id DESC LIMIT ?
        """, (limit,))
        rows = cur.fetchall()
        conn.close()
        return [
            {
                "id": r[0], "timestamp": r[1], "meal_name": r[2], "source": r[3],
                "photo_filename": r[4], "calories": r[5], "net_carbs": r[6], "total_carbs": r[7],
                "fiber": r[8], "protein": r[9], "fat": r[10], "glycemic_load": r[11],
                "vendor_matched": r[12], "gemini_notes": r[13], "meets_goal": bool(r[14])
            }
            for r in rows
        ]'''

new_get_recent = '''def get_recent_meals(limit=100):
    with db_lock:
        conn = sqlite3.connect(DB_PATH)
        cur = conn.cursor()
        cur.execute("""
            SELECT id, timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json
            FROM meal_entries
            ORDER BY timestamp DESC, id DESC LIMIT ?
        """, (limit,))
        rows = cur.fetchall()
        conn.close()
        res = []
        for r in rows:
            raw_obj = {}
            if r[15]:
                try:
                    raw_obj = json.loads(r[15])
                except Exception:
                    pass
            res.append({
                "id": r[0], "timestamp": r[1], "meal_name": r[2], "source": r[3],
                "photo_filename": r[4], "calories": r[5], "net_carbs": r[6], "total_carbs": r[7],
                "fiber": r[8], "protein": r[9], "fat": r[10], "glycemic_load": r[11],
                "vendor_matched": r[12], "gemini_notes": r[13], "meets_goal": bool(r[14]),
                "raw_json": r[15],
                "identified_items": raw_obj.get("identified_items", [])
            })
        return res'''

assert old_get_recent in text, "old_get_recent not found"
text = text.replace(old_get_recent, new_get_recent, 1)

# 2. Update do_GET for /api/meal/daily_summary
old_summary_block = '''        elif self.path.startswith("/api/meal/daily_summary"):
            # Generates comprehensive Daily Intake Summary of all consumed
            query_date = datetime.datetime.now().strftime("%Y-%m-%d")
            if "?" in self.path:
                from urllib.parse import parse_qs, urlparse
                params = parse_qs(urlparse(self.path).query)
                if "date" in params:
                    query_date = params["date"][0].strip()

            with db_lock:
                conn = get_db()
                cur = conn.cursor()
                cur.execute("""
                    SELECT id, timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal
                    FROM meal_entries
                    WHERE timestamp LIKE ?
                    ORDER BY timestamp DESC, id DESC
                """, (f"{query_date}%",))
                rows = cur.fetchall()
                conn.close()

                items = [
                    {
                        "id": r[0], "timestamp": r[1], "meal_name": r[2], "source": r[3],
                        "photo_filename": r[4], "calories": r[5], "net_carbs": r[6], "total_carbs": r[7],
                        "fiber": r[8], "protein": r[9], "fat": r[10], "glycemic_load": r[11],
                        "vendor_matched": r[12], "gemini_notes": r[13], "meets_goal": bool(r[14])
                    }
                    for r in rows
                ]

                # Categorize into Breakfast, Lunch, Dinner, Snack
                by_type = {
                    "Breakfast": {"count": 0, "calories": 0, "net_carbs": 0.0, "protein": 0.0, "items": []},
                    "Lunch": {"count": 0, "calories": 0, "net_carbs": 0.0, "protein": 0.0, "items": []},
                    "Dinner": {"count": 0, "calories": 0, "net_carbs": 0.0, "protein": 0.0, "items": []},
                    "Snack": {"count": 0, "calories": 0, "net_carbs": 0.0, "protein": 0.0, "items": []}
                }

                tot_cal = 0
                tot_net = 0.0
                tot_tot = 0.0
                tot_fib = 0.0
                tot_pro = 0.0
                tot_fat = 0.0

                for it in items:
                    c = it["calories"] or 0
                    nc = it["net_carbs"] or 0.0
                    tc = it["total_carbs"] or 0.0
                    fb = it["fiber"] or 0.0
                    pr = it["protein"] or 0.0
                    ft = it["fat"] or 0.0

                    tot_cal += c
                    tot_net += nc
                    tot_tot += tc
                    tot_fib += fb
                    tot_pro += pr
                    tot_fat += ft

                    # Infer meal type from name or notes if not explicit
                    name_lower = (it["meal_name"] + " " + it["source"] + " " + it["gemini_notes"]).lower()
                    if "breakfast" in name_lower or "morning" in name_lower or "omelet" in name_lower or "egg" in name_lower or "coffee" in name_lower:
                        m_type = "Breakfast"
                    elif "lunch" in name_lower or "sandwich" in name_lower or "burger" in name_lower or "wrap" in name_lower or "salad" in name_lower:
                        m_type = "Lunch"
                    elif "dinner" in name_lower or "supper" in name_lower or "steak" in name_lower or "salmon" in name_lower or "taco" in name_lower:
                        m_type = "Dinner"
                    else:
                        m_type = "Snack"

                    by_type[m_type]["count"] += 1
                    by_type[m_type]["calories"] += c
                    by_type[m_type]["net_carbs"] += nc
                    by_type[m_type]["protein"] += pr
                    by_type[m_type]["items"].append(it["meal_name"])'''

new_summary_block = '''        elif self.path.startswith("/api/meal/daily_summary"):
            # Generates comprehensive Daily Intake Summary of all consumed
            query_date = datetime.datetime.now().strftime("%Y-%m-%d")
            if "?" in self.path:
                from urllib.parse import parse_qs, urlparse
                params = parse_qs(urlparse(self.path).query)
                if "date" in params:
                    query_date = params["date"][0].strip()

            with db_lock:
                conn = get_db()
                cur = conn.cursor()
                cur.execute("""
                    SELECT id, timestamp, meal_name, source, photo_filename, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, vendor_matched, gemini_notes, meets_goal, raw_json
                    FROM meal_entries
                    WHERE timestamp LIKE ?
                    ORDER BY timestamp DESC, id DESC
                """, (f"{query_date}%",))
                rows = cur.fetchall()
                conn.close()

                items = []
                for r in rows:
                    raw_obj = {}
                    if r[15]:
                        try:
                            raw_obj = json.loads(r[15])
                        except Exception:
                            pass
                    items.append({
                        "id": r[0], "timestamp": r[1], "meal_name": r[2], "source": r[3],
                        "photo_filename": r[4], "calories": r[5], "net_carbs": r[6], "total_carbs": r[7],
                        "fiber": r[8], "protein": r[9], "fat": r[10], "glycemic_load": r[11],
                        "vendor_matched": r[12], "gemini_notes": r[13], "meets_goal": bool(r[14]),
                        "raw_json": r[15],
                        "identified_items": raw_obj.get("identified_items", [])
                    })

                # Categorize into Breakfast, Lunch, Dinner, Snack
                by_type = {
                    "Breakfast": {"count": 0, "calories": 0, "net_carbs": 0.0, "protein": 0.0, "items": [], "food_components": []},
                    "Lunch": {"count": 0, "calories": 0, "net_carbs": 0.0, "protein": 0.0, "items": [], "food_components": []},
                    "Dinner": {"count": 0, "calories": 0, "net_carbs": 0.0, "protein": 0.0, "items": [], "food_components": []},
                    "Snack": {"count": 0, "calories": 0, "net_carbs": 0.0, "protein": 0.0, "items": [], "food_components": []}
                }

                tot_cal = 0
                tot_net = 0.0
                tot_tot = 0.0
                tot_fib = 0.0
                tot_pro = 0.0
                tot_fat = 0.0

                for it in items:
                    c = it["calories"] or 0
                    nc = it["net_carbs"] or 0.0
                    tc = it["total_carbs"] or 0.0
                    fb = it["fiber"] or 0.0
                    pr = it["protein"] or 0.0
                    ft = it["fat"] or 0.0

                    tot_cal += c
                    tot_net += nc
                    tot_tot += tc
                    tot_fib += fb
                    tot_pro += pr
                    tot_fat += ft

                    # Infer meal type from name or notes if not explicit
                    name_lower = (it["meal_name"] + " " + it["source"] + " " + it["gemini_notes"]).lower()
                    if "breakfast" in name_lower or "morning" in name_lower or "omelet" in name_lower or "egg" in name_lower or "coffee" in name_lower:
                        m_type = "Breakfast"
                    elif "lunch" in name_lower or "sandwich" in name_lower or "burger" in name_lower or "wrap" in name_lower or "salad" in name_lower:
                        m_type = "Lunch"
                    elif "dinner" in name_lower or "supper" in name_lower or "steak" in name_lower or "salmon" in name_lower or "taco" in name_lower:
                        m_type = "Dinner"
                    else:
                        m_type = "Snack"

                    by_type[m_type]["count"] += 1
                    by_type[m_type]["calories"] += c
                    by_type[m_type]["net_carbs"] += nc
                    by_type[m_type]["protein"] += pr
                    by_type[m_type]["items"].append(it["meal_name"])
                    for comp in it.get("identified_items", []):
                        by_type[m_type]["food_components"].append(comp)'''

assert old_summary_block in text, "old_summary_block not found"
text = text.replace(old_summary_block, new_summary_block, 1)

with open(r'D:\veepoo\w11_htm_app\server.py', 'w', encoding='utf-8') as f:
    f.write(text)

print("server.py updated successfully!")
