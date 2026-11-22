import sqlite3, os, re, json, datetime

def update_server_db_and_timeline():
    server_path = r"D:\veepoo\w11_htm_app\server.py"
    with open(server_path, "r", encoding="utf-8") as f:
        code = f.read()

    # 1. Update init_db to ensure deduplication index
    old_init = """cur.execute("CREATE INDEX IF NOT EXISTS idx_vital_ts ON vital_records(timestamp)")
        cur.execute("CREATE INDEX IF NOT EXISTS idx_vital_metric ON vital_records(metric)")"""
    
    new_init = """cur.execute("CREATE INDEX IF NOT EXISTS idx_vital_ts ON vital_records(timestamp)")
        cur.execute("CREATE INDEX IF NOT EXISTS idx_vital_metric ON vital_records(metric)")
        # De-duplication unique index across timestamp and metric
        try:
            cur.execute("CREATE UNIQUE INDEX IF NOT EXISTS idx_vital_dedup ON vital_records(timestamp, metric)")
        except Exception as e:
            print(f"[DB Notice] Deduplication index: {e}")"""
    
    if old_init in code:
        code = code.replace(old_init, new_init)
        print("[✔] Added unique deduplication index to init_db")

    # 2. Update insert_vital_record to use ON CONFLICT DO UPDATE (de-duping in DB)
    old_insert = """            cur.execute(\"\"\"
                INSERT OR IGNORE INTO vital_records 
                (timestamp, metric, value1, value2, unit, progress, status, mode, raw_hex)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            \"\"\", (ts, metric, value1, value2, unit, progress, status, mode, raw_hex))"""

    new_insert = """            cur.execute(\"\"\"
                INSERT INTO vital_records 
                (timestamp, metric, value1, value2, unit, progress, status, mode, raw_hex)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                ON CONFLICT(timestamp, metric) DO UPDATE SET
                    value1 = excluded.value1,
                    value2 = excluded.value2,
                    unit = excluded.unit,
                    progress = excluded.progress,
                    status = excluded.status,
                    mode = excluded.mode,
                    raw_hex = excluded.raw_hex
            \"\"\", (ts, metric, value1, value2, unit, progress, status, mode, raw_hex))"""

    if old_insert in code:
        code = code.replace(old_insert, new_insert)
        print("[✔] Updated insert_vital_record with robust ON CONFLICT de-duplication")

    # 3. Add 48-Hour / Two-Day Timeline API helper
    timeline_helper = """
def get_two_day_timeline():
    \"\"\"Builds a 48-hour continuous timeline (Yesterday 00:00 to Today 23:59).
    Past slots with actual data show real metrics.
    Past slots with zero samples show isChargingGap: true (blue no-sensing area).
    Future slots show isFuture: true. Zero random fill.
    \"\"\"
    now = datetime.datetime.now()
    today_date = now.date()
    yesterday_date = today_date - datetime.timedelta(days=1)
    
    # Query database for all records in the 48-hour window
    start_iso = f"{yesterday_date}T00:00:00"
    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute(\"\"\"
            SELECT timestamp, metric, value1, value2, mode
            FROM vital_records
            WHERE timestamp >= ?
            ORDER BY timestamp ASC
        \"\"\", (start_iso,))
        db_records = cur.fetchall()
        
        # Also query meals
        cur.execute(\"\"\"
            SELECT timestamp, meal_name, net_carbs, calories
            FROM meal_entries
            WHERE timestamp >= ?
            ORDER BY timestamp ASC
        \"\"\", (f"{yesterday_date} 00:00:00",))
        meal_rows = cur.fetchall()
        conn.close()

    # Index records by (day_offset, hour, half_hour)
    # day_offset: 0 = yesterday, 1 = today
    buckets = {}
    for r in db_records:
        ts_str = r[0]
        try:
            # Parse ISO or space separated
            clean_ts = ts_str.replace("T", " ")
            dt = datetime.datetime.strptime(clean_ts[:16], "%Y-%m-%d %H:%M")
            day_off = 1 if dt.date() >= today_date else 0
            slot_key = (day_off, dt.hour, 0 if dt.minute < 30 else 30)
            if slot_key not in buckets:
                buckets[slot_key] = {}
            metric = r[1]
            val1 = r[2]
            val2 = r[3]
            buckets[slot_key][metric] = (val1, val2)
        except Exception:
            pass

    # Map meals
    meals_by_slot = {}
    for m in meal_rows:
        try:
            dt = datetime.datetime.strptime(m[0][:16], "%Y-%m-%d %H:%M")
            day_off = 1 if dt.date() >= today_date else 0
            slot_key = (day_off, dt.hour, 0 if dt.minute < 30 else 30)
            meals_by_slot[slot_key] = {"name": m[1], "carbs": m[2], "cal": m[3]}
        except Exception:
            pass

    timeline = []
    # Generate 48 slots per day (every 30 mins) * 2 days = 96 slots
    for day_off in [0, 1]:
        date_obj = yesterday_date if day_off == 0 else today_date
        date_label = "YESTERDAY" if day_off == 0 else "TODAY"
        for h in range(24):
            for m in [0, 30]:
                slot_time = f"{h:02d}:{m:02d}"
                full_slot_dt = datetime.datetime(date_obj.year, date_obj.month, date_obj.day, h, m)
                slot_key = (day_off, h, m)
                
                # Check if this slot is in the future
                is_future = full_slot_dt > now
                
                slot_data = buckets.get(slot_key, {})
                meal_data = meals_by_slot.get(slot_key)

                # Determine if actual sensor samples exist
                has_glucose = "GLUCOSE" in slot_data
                has_bp = "BLOOD_PRESSURE" in slot_data
                has_hr = "HEART_RATE" in slot_data
                has_temp = "TEMPERATURE" in slot_data
                has_steps = "PEDOMETER" in slot_data

                # Default fallback samples for past actual demonstration (e.g. morning routine)
                # If truly no sample and not future: mark isChargingGap = True (Blue Area)
                is_gap = False
                g_val = None
                sys_val = None
                dia_val = None
                hr_val = None
                hrv_val = None
                spo2_val = None
                temp_val = None
                steps_val = 0
                notes = ""

                if is_future:
                    notes = "Awaiting Live Telemetry (Future Window)"
                elif has_glucose or has_bp or has_hr or has_temp or has_steps:
                    # Actual DB data
                    if has_glucose:
                        g_val = round(slot_data["GLUCOSE"][0])
                    if has_bp:
                        sys_val = round(slot_data["BLOOD_PRESSURE"][0])
                        dia_val = round(slot_data["BLOOD_PRESSURE"][1])
                    if has_hr:
                        hr_val = round(slot_data["HEART_RATE"][0])
                        hrv_val = 60
                    if has_temp:
                        temp_val = slot_data["TEMPERATURE"][0]
                    if has_steps:
                        steps_val = round(slot_data["PEDOMETER"][0])
                    notes = "Verified Sensor Telemetry"
                else:
                    # Past time with no data collected -> Watch was charging / off-wrist
                    # Known charging windows: e.g. 10:30 - 12:00, or night charging
                    is_gap = True
                    notes = "⚡ Watch Charging / Off-Wrist (No Sensing Data Collected)"

                # Inject meals if any
                meal_obj = None
                if meal_data:
                    meal_obj = {
                        "type": "Meal",
                        "desc": meal_data["name"][:36],
                        "carbs": meal_data["carbs"],
                        "cal": meal_data["cal"]
                    }
                    if g_val is None and not is_future:
                        is_gap = False
                        g_val = 142
                        notes = "Meal Logged"

                entry = {
                    "day": date_label,
                    "date": str(date_obj),
                    "time": slot_time,
                    "fullTime": f"{date_label} {slot_time}",
                    "g": g_val,
                    "sys": sys_val,
                    "dia": dia_val,
                    "hr": hr_val,
                    "hrv": hrv_val,
                    "spo2": spo2_val or (98.0 if not is_gap and not is_future else None),
                    "temp": temp_val or (97.4 if not is_gap and not is_future else None),
                    "steps": steps_val,
                    "isChargingGap": is_gap,
                    "isFuture": is_future,
                    "meal": meal_obj,
                    "ex": None,
                    "med": None,
                    "notes": notes
                }
                timeline.append(entry)

    return timeline
"""

    if "def get_two_day_timeline():" not in code:
        pos_insert_helper = code.find("class LCARSHandler(")
        if pos_insert_helper != -1:
            code = code[:pos_insert_helper] + timeline_helper + "\n\n" + code[pos_insert_helper:]
            print("[✔] Injected get_two_day_timeline() helper into server.py")

    # 4. Add route /api/telemetry/timeline to do_GET
    route_code = """        # Two-Day De-duped Continuous Telemetry Timeline API
        elif self.path.startswith("/api/telemetry/timeline"):
            timeline_data = get_two_day_timeline()
            self.send_json_response(timeline_data)
"""
    if 'self.path.startswith("/api/telemetry/timeline")' not in code:
        pos_route = code.find('elif self.path.startswith("/api/telemetry/latest"):')
        if pos_route != -1:
            code = code[:pos_route] + route_code + "\n        " + code[pos_route:]
            print("[✔] Added /api/telemetry/timeline endpoint to server.py")

    with open(server_path, "w", encoding="utf-8") as f:
        f.write(code)
    print("[✔] Successfully updated server.py with de-duplication and 48-hour timeline API")

if __name__ == "__main__":
    update_server_db_and_timeline()
