# apply_all_updates.py
import re
import json

print("=== 1. Updating server.py ===")
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_code = f.read()

# 1a. Update upsert_popular_meal to deduplicate by normalized title
old_upsert = """def upsert_popular_meal(payload):
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
        \"\"\", (meal_id, title, desc, cal, net_c, tot_c, fib, pro, fat, gl, tips_str, meets, fluid_oz, occasion, ts))
        conn.commit()
        conn.close()
    return {"id": meal_id, "title": title, "net_carbs": net_c, "calories": cal, "meets_goal": bool(meets)}"""

new_upsert = """def upsert_popular_meal(payload):
    title = (payload.get("title") or payload.get("meal_name") or "Custom Saved Meal").strip()
    norm_title = title.lower()
    desc = payload.get("description") or payload.get("desc") or ""
    cal = int(payload.get("calories") or 0)
    net_c = float(payload.get("net_carbs") or payload.get("netCarbs") or 0)
    tot_c = float(payload.get("total_carbs") or payload.get("totalCarbs") or net_c)
    fib = float(payload.get("fiber") or 0)
    pro = float(payload.get("protein") or 0)
    fat = float(payload.get("fat") or 0)
    gl = str(payload.get("glycemic_load") or payload.get("gl") or "Moderate")
    meets = 1 if (payload.get("meets_goal") if "meets_goal" in payload else (net_c <= 45)) else 0
    fluid_oz = float(payload.get("fluid_oz") or payload.get("fluidOz") or 8.0)
    occasion = str(payload.get("occasion") or "Meal")
    tips = payload.get("tips") or []
    tips_str = json.dumps(tips) if isinstance(tips, list) else str(tips)
    ts = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        # Deduplication check: if a meal with the same title already exists, reuse its ID to prevent pantry duplicates!
        cur.execute("SELECT id FROM popular_meals WHERE LOWER(TRIM(title)) = ?", (norm_title,))
        existing_row = cur.fetchone()
        if existing_row:
            meal_id = existing_row[0]
        else:
            meal_id = str(payload.get("id") or ("custom_" + str(int(time.time())))).strip().lower()

        cur.execute(\"\"\"
            INSERT OR REPLACE INTO popular_meals
            (id, title, description, calories, net_carbs, total_carbs, fiber, protein, fat, glycemic_load, tips_json, meets_goal, is_custom, fluid_oz, occasion, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?, ?, ?)
        \"\"\", (meal_id, title, desc, cal, net_c, tot_c, fib, pro, fat, gl, tips_str, meets, fluid_oz, occasion, ts))
        conn.commit()
        conn.close()
    return {"id": meal_id, "title": title, "net_carbs": net_c, "calories": cal, "meets_goal": bool(meets)}"""

assert old_upsert in server_code, "old_upsert not found in server.py"
server_code = server_code.replace(old_upsert, new_upsert)

# 1b. In analyze_meal_with_gemini_backend: add Nutrition Facts label parser and candy/peppermint crunch handler
old_kitkat = """    # Kit Kat wafer bar handler
    if "kit kat" in text or "kitkat" in text:"""

new_kitkat_and_candy = """    # 00. Explicit Nutrition Facts Label / QR Code Nutrition Link / URL Parameter Parser
    m_carbs = re.search(r'(?:total\s+carbs?|carbohydrates?|net\s+carbs?|carbs?)\s*[:=]?\s*(\d+(?:\.\d+)?)\s*g?', text)
    m_cals = re.search(r'(?:calories?|energy|kcal)\s*[:=]?\s*(\d+)', text)
    m_fib = re.search(r'(?:dietary\s+fiber|fiber|fibre)\s*[:=]?\s*(\d+(?:\.\d+)?)\s*g?', text)
    m_pro = re.search(r'(?:protein)\s*[:=]?\s*(\d+(?:\.\d+)?)\s*g?', text)
    m_fat = re.search(r'(?:total\s+fat|fat)\s*[:=]?\s*(\d+(?:\.\d+)?)\s*g?', text)
    m_sugar = re.search(r'(?:total\s+sugars?|added\s+sugars?|sugars?)\s*[:=]?\s*(\d+(?:\.\d+)?)\s*g?', text)

    # If explicit nutrition numbers were detected from OCR label, QR URL, or user entry:
    if m_carbs and (m_cals or m_fib or m_sugar):
        tot_c = float(m_carbs.group(1))
        fib = float(m_fib.group(1)) if m_fib else 0.0
        net_c = max(0.0, round(tot_c - fib, 1))
        cals = int(m_cals.group(1)) if m_cals else round(tot_c * 4 + 100)
        pro = float(m_pro.group(1)) if m_pro else 4.0
        fat = float(m_fat.group(1)) if m_fat else 8.0
        meets = (net_c <= 45.0) and (not m_sugar or float(m_sugar.group(1)) <= 15.0)
        gl_val = round(net_c * 0.75)
        spike = round(net_c * 1.8)
        title_disp = meal_name.strip() if meal_name.strip() else "Verified Nutrition Facts Item"
        return {
            "status": "SUCCESS",
            "meal_name": title_disp,
            "vendor_matched": "Nutrition Facts Disclosure & QR Code Ingestion",
            "photo_filename": "meal_nutrition_label.jpg",
            "calories": cals,
            "net_carbs": net_c,
            "total_carbs": tot_c,
            "fiber": fib,
            "protein": pro,
            "fat": fat,
            "glycemic_load": f"{gl_val} (" + ("Safe Low-Med" if meets else "HIGH SPIKE HAZARD") + ")",
            "meets_goal": meets,
            "projected_spike": f"+{spike} to +{spike+20} mg/dL postprandial rise",
            "gemini_notes": f"📋 NUTRITION FACTS VERIFIED: Total Carbs {tot_c}g, Fiber {fib}g -> {net_c}g Net Carbs. " + ("Meets 45g clinical ceiling." if meets else f"🚨 EXCEEDS 45g CLINICAL CEILING by +{round(net_c - 45)}g! Postprandial glucose spike anticipated."),
            "tips": [
                f"Accurately extracted from nutrition facts disclosure: {net_c}g Net Carbs.",
                "Ensure accompanying drink is unsweetened/water to avoid liquid sugar penalty."
            ],
            "glut4_recommendation": f"<strong>{max(15, min(60, round(net_c * 0.75)))}-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Directly clears excess circulating glucose."
        }

    # 01. High-Sugar Candy, Peppermint Crunch & Confectionery Handler
    if any(w in text for w in ["peppermint", "peppermint crunch", "candy", "candies", "confection", "confectionery", "fudge", "caramel", "toffee", "peppermint bark", "marshmallow", "snickers", "skittles", "jelly bean", "gumdrop", "lollipop", "taffy", "brittle"]):
        oz = 2.0 if ("2" in text or "2 oz" in text) else 1.5
        cals = round(270 * (oz / 1.5))
        carbs = round(39.0 * (oz / 1.5), 1)
        fiber = 0.0
        net_c = carbs
        fat = round(12.0 * (oz / 1.5), 1)
        pro = round(1.5 * (oz / 1.5), 1)
        gl = 45
        spike = round(net_c * 2.1)
        return {
            "status": "SUCCESS",
            "meal_name": meal_name if meal_name else f"Christmas Peppermint Crunch Candy ({oz:.1f} oz)",
            "vendor_matched": "Holiday Confectionery Nutritional Disclosure",
            "photo_filename": "meal_candy_peppermint.jpg",
            "calories": cals,
            "net_carbs": net_c,
            "total_carbs": carbs,
            "fiber": fiber,
            "protein": pro,
            "fat": fat,
            "glycemic_load": f"{gl} (SEVERE HIGH SPIKE HAZARD)",
            "meets_goal": False,
            "projected_spike": f"+{spike} to +{spike+25} mg/dL acute spike",
            "trade_alternatives": [
                "Swap to Zero-Sugar Allulose Peppermint Bark (2g net carbs, monk fruit/allulose sweetened — zero spike)",
                "ChocZero Sugar-Free White Chocolate Peppermint Squares (1g net carbs, zero sugar alcohols)",
                "Stevia-sweetened peppermint herbal infusion tea with splash of heavy cream (0g carbs)"
            ],
            "gemini_notes": f"🚨 SEVERE CONFECTIONERY SPIKE: {oz:.1f} oz Peppermint Crunch contains {net_c}g pure sucrose and refined corn syrup carbs with ZERO fiber. Rapid gastric absorption will induce an acute glycemic spike of +{spike} mg/dL, severely violating the 45g diabetic ceiling. Strictly recommend harm-reduction trade or immediate GLUT4 walk.",
            "tips": [
                f"Pure sucrose and corn syrup rapidly hydrolyze into blood glucose within 20 minutes ({net_c}g net carbs).",
                "Zero fiber and low protein provide zero buffer against rapid hyperglycemic excursions.",
                "Mandatory GLUT4 muscle contraction required if consumed."
            ],
            "glut4_recommendation": f"<strong>MANDATORY 45-Minute Brisk GLUT4 Walk starting 15 min post-consumption:</strong> Required to blunt projected +{spike} mg/dL glycemic excursion."
        }

    # Kit Kat wafer bar handler
    if "kit kat" in text or "kitkat" in text:"""

assert old_kitkat in server_code, "old_kitkat not found in server.py"
server_code = server_code.replace(old_kitkat, new_kitkat_and_candy)

# 1c. In /api/meal/upload: respect client-supplied nutrition facts if present
old_upload_analysis = """                # Analyze meal with Gemini multimodal logic
                analysis = analyze_meal_with_gemini_backend(meal_name, notes, photo_data)"""

new_upload_analysis = """                # Check if client already supplied validated clinical nutrition facts (e.g. from /cell or barcode/QR)
                client_net_carbs = payload.get("net_carbs")
                client_calories = payload.get("calories")
                if client_net_carbs is not None and client_calories is not None:
                    net_c = float(client_net_carbs)
                    tot_c = float(payload.get("total_carbs", net_c))
                    fib = float(payload.get("fiber", 0.0))
                    cals = int(client_calories)
                    pro = float(payload.get("protein", 0.0))
                    fat = float(payload.get("fat", 0.0))
                    meets = bool(payload.get("meets_goal", net_c <= 45.0))
                    analysis = {
                        "status": "SUCCESS",
                        "meal_name": meal_name,
                        "description": desc,
                        "calories": cals,
                        "net_carbs": net_c,
                        "total_carbs": tot_c,
                        "fiber": fib,
                        "protein": pro,
                        "fat": fat,
                        "glycemic_load": payload.get("glycemic_load") or (f"{round(net_c * 0.6)} (" + ("Safe Low" if meets else "High Spike Risk") + ")"),
                        "meets_goal": meets,
                        "vendor_matched": payload.get("vendor_matched", "Mobile QR / Verified Ingestion"),
                        "gemini_notes": payload.get("gemini_notes") or f"Direct clinical ingestion: {net_c}g Net Carbs ({cals} kcal). " + ("Meets 45g ceiling." if meets else "⚠️ Exceeds 45g ceiling."),
                        "tips": payload.get("tips") or [f"Ingested from mobile device: {net_c}g Net Carbs."],
                        "trade_alternatives": payload.get("trade_alternatives") or [],
                        "glut4_recommendation": payload.get("glut4_recommendation") or (f"<strong>{max(15, min(60, round(net_c*0.75)))}-Min GLUT4 Walk:</strong> Clears circulating glucose.")
                    }
                else:
                    # Analyze meal with Gemini multimodal logic
                    analysis = analyze_meal_with_gemini_backend(meal_name, notes, photo_data)"""

assert old_upload_analysis in server_code, "old_upload_analysis not found in server.py"
server_code = server_code.replace(old_upload_analysis, new_upload_analysis)

# 1d. Remove ~$ / Estimated cost from general fallback
old_fallback_cost = 'Estimated cost: ~${est_cost}.'
new_fallback_cost = 'Metabolic Burden: {est_carbs}g Net Carbs (Glycemic Load: {round(est_carbs * 0.4)}).'
if old_fallback_cost in server_code:
    server_code = server_code.replace(old_fallback_cost, new_fallback_cost)

with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(server_code)
print("[✔] server.py updated successfully!")
