with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_py = f.read()

# 1. Update metrics in get_latest_telemetry_snapshot to include "SLEEP"
old_metrics = 'metrics = ["GLUCOSE", "BLOOD_PRESSURE", "HEART_RATE", "SPO2", "BLOOD_OXYGEN", "TEMPERATURE", "PEDOMETER", "BATTERY", "HRV_STRESS", "HRV"]'
new_metrics = 'metrics = ["GLUCOSE", "BLOOD_PRESSURE", "HEART_RATE", "SPO2", "BLOOD_OXYGEN", "TEMPERATURE", "PEDOMETER", "BATTERY", "HRV_STRESS", "HRV", "SLEEP"]'
assert old_metrics in server_py, "old_metrics not found in server.py"
server_py = server_py.replace(old_metrics, new_metrics)

# 2. In get_recent_meals, ensure ORDER BY timestamp DESC, id DESC
old_order = 'ORDER BY id DESC LIMIT ?'
new_order = 'ORDER BY timestamp DESC, id DESC LIMIT ?'
assert old_order in server_py, "old_order not found in server.py"
server_py = server_py.replace(old_order, new_order)

# 3. In get_popular_meals, ensure custom/recent meals appear first
old_pop_order = 'ORDER BY id ASC'
new_pop_order = 'ORDER BY is_custom DESC, updated_at DESC, id ASC'
assert old_pop_order in server_py, "old_pop_order not found in server.py"
server_py = server_py.replace(old_pop_order, new_pop_order)

# 4. In /api/meal/upload, upsert cell-uploaded meal/snack into popular_meals
old_upload_block = '''                    meal_id, ts = insert_meal_entry(
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
                        raw_json=json.dumps(analysis)
                    )
                    analysis["meal_id"] = meal_id
                    analysis["timestamp"] = ts
                    analysis["status"] = "COMMITTED_TO_DB"'''

new_upload_block = '''                    meal_id, ts = insert_meal_entry(
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
                        raw_json=json.dumps(analysis)
                    )
                    analysis["meal_id"] = meal_id
                    analysis["timestamp"] = ts
                    analysis["status"] = "COMMITTED_TO_DB"

                    # Automatically upsert into popular_meals (Meal Pantry) so all cell-uploaded meals & snacks appear in Meal Pantry
                    try:
                        upsert_popular_meal({
                            "id": f"cell_{meal_id}",
                            "title": analysis["meal_name"],
                            "description": analysis.get("gemini_notes", desc),
                            "calories": analysis.get("calories", 0),
                            "net_carbs": analysis.get("net_carbs", 0),
                            "total_carbs": analysis.get("total_carbs", 0),
                            "fiber": analysis.get("fiber", 0),
                            "protein": analysis.get("protein", 0),
                            "fat": analysis.get("fat", 0),
                            "glycemic_load": analysis.get("glycemic_load", "LOW"),
                            "tips": [analysis.get("gemini_notes", "")],
                            "meets_goal": 1 if analysis.get("meets_goal", True) else 0,
                            "is_custom": 1
                        })
                        print(f"[✔] Upserted into Meal Pantry (popular_meals): cell_{meal_id} - '{analysis['meal_name']}'")
                    except Exception as e_pop:
                        print(f"[!] Warning: failed to upsert cell meal to popular_meals: {e_pop}")'''

assert old_upload_block in server_py, "old_upload_block not found in server.py"
server_py = server_py.replace(old_upload_block, new_upload_block)

# 5. Add /api/meal/daily_summary endpoint
old_post_check = 'elif self.path == "/api/meals/popular":'
new_post_check = '''elif self.path.startswith("/api/meal/daily_summary"):
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
                    by_type[m_type]["items"].append(it["meal_name"])

                daily_summary = {
                    "date": query_date,
                    "total_consumed_count": len(items),
                    "total_calories": tot_cal,
                    "total_net_carbs": round(tot_net, 1),
                    "total_carbs": round(tot_tot, 1),
                    "total_fiber": round(tot_fib, 1),
                    "total_protein": round(tot_pro, 1),
                    "total_fat": round(tot_fat, 1),
                    "calories_goal": 2000,
                    "net_carbs_goal": 135.0,
                    "protein_goal": 75.0,
                    "by_type": by_type,
                    "items": items
                }
                self.send_json_response(daily_summary)

        elif self.path == "/api/meals/popular":'''

assert old_post_check in server_py, "old_post_check not found in server.py"
server_py = server_py.replace(old_post_check, new_post_check)

with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(server_py)

print("server.py updated successfully!")
