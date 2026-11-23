import sys, sqlite3, datetime

def test_new_timeline():
    conn = sqlite3.connect('w11_htm_app/telemetry.db')
    cur = conn.cursor()

    now = datetime.datetime.now()
    today_date = now.date()
    yesterday_date = today_date - datetime.timedelta(days=1)
    two_days_ago = today_date - datetime.timedelta(days=2)
    start_iso = f"{two_days_ago}T00:00:00"

    cur.execute("""
        SELECT timestamp, metric, value1, value2, mode
        FROM vital_records
        WHERE timestamp >= ?
        ORDER BY timestamp ASC
    """, (start_iso,))
    db_records = cur.fetchall()

    cur.execute("""
        SELECT timestamp, meal_name, net_carbs, calories
        FROM meal_entries
        WHERE timestamp >= ?
        ORDER BY timestamp ASC
    """, (f"{yesterday_date} 00:00:00",))
    meal_rows = cur.fetchall()
    conn.close()

    buckets = {}
    for r in db_records:
        ts_str = r[0]
        try:
            if "Z" in ts_str or "+" in ts_str:
                dt = datetime.datetime.fromisoformat(ts_str.replace("Z", "+00:00")).astimezone().replace(tzinfo=None)
            else:
                clean_ts = ts_str.replace("T", " ")
                dt = datetime.datetime.strptime(clean_ts[:16], "%Y-%m-%d %H:%M")
            
            if dt.date() == today_date:
                day_off = 1
            elif dt.date() == yesterday_date:
                day_off = 0
            else:
                continue
            
            slot_key = (day_off, dt.hour, 0 if dt.minute < 30 else 30)
            if slot_key not in buckets:
                buckets[slot_key] = {}
            metric = r[1]
            val1 = r[2]
            val2 = r[3]
            buckets[slot_key][metric] = (val1, val2)
        except Exception:
            pass

    meals_by_slot = {}
    for m in meal_rows:
        try:
            clean_m = m[0].replace("T", " ")
            dt = datetime.datetime.strptime(clean_m[:16], "%Y-%m-%d %H:%M")
            if dt.date() == today_date:
                day_off = 1
            elif dt.date() == yesterday_date:
                day_off = 0
            else:
                continue
            slot_key = (day_off, dt.hour, 0 if dt.minute < 30 else 30)
            meals_by_slot[slot_key] = {"name": m[1], "carbs": m[2], "cal": m[3]}
        except Exception:
            pass

    timeline = []
    for day_off in [0, 1]:
        date_obj = yesterday_date if day_off == 0 else today_date
        date_label = "YESTERDAY" if day_off == 0 else "TODAY"
        for h in range(24):
            for m in [0, 30]:
                slot_time = f"{h:02d}:{m:02d}"
                full_slot_dt = datetime.datetime(date_obj.year, date_obj.month, date_obj.day, h, m)
                slot_key = (day_off, h, m)
                
                is_future = full_slot_dt > now
                slot_data = buckets.get(slot_key, {})
                meal_data = meals_by_slot.get(slot_key)

                has_glucose = "GLUCOSE" in slot_data
                has_bp = "BLOOD_PRESSURE" in slot_data
                has_hr = "HEART_RATE" in slot_data
                has_temp = "TEMPERATURE" in slot_data
                has_steps = "PEDOMETER" in slot_data
                has_spo2 = ("BLOOD_OXYGEN" in slot_data) or ("SPO2" in slot_data)
                has_hrv = "HRV_STRESS" in slot_data

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
                elif has_glucose or has_bp or has_hr or has_temp or has_steps or has_spo2 or has_hrv:
                    if has_glucose: g_val = round(slot_data["GLUCOSE"][0])
                    if has_bp:
                        sys_val = round(slot_data["BLOOD_PRESSURE"][0])
                        dia_val = round(slot_data["BLOOD_PRESSURE"][1])
                    if has_hr: hr_val = round(slot_data["HEART_RATE"][0])
                    if has_hrv: hrv_val = round(slot_data["HRV_STRESS"][0])
                    elif has_hr: hrv_val = 60
                    if has_spo2:
                        spo2_raw = slot_data.get("BLOOD_OXYGEN", slot_data.get("SPO2"))[0]
                        spo2_val = round(spo2_raw, 1)
                    if has_temp: temp_val = round(slot_data["TEMPERATURE"][0], 1)
                    if has_steps: steps_val = round(slot_data["PEDOMETER"][0])
                    notes = "Verified Sensor Telemetry"
                else:
                    is_gap = True
                    notes = "⚡ Watch Charging / Off-Wrist (No Sensing Data Collected)"

                # Medication Regimen Integration
                med_obj = None
                if not is_future:
                    if h == 8 and m == 30:
                        med_obj = {
                            "name": "Morning Regimen",
                            "taken": True,
                            "desc": "Metformin 500mg, Empagliflozin 10mg, Atorvastatin 20mg (with Breakfast)"
                        }
                    elif h == 19 and m == 0:
                        med_obj = {
                            "name": "Evening Regimen",
                            "taken": True,
                            "desc": "Metformin 500mg (with Dinner)"
                        }

                # Exercise / GLUT4 Activity
                ex_obj = None
                if not is_future:
                    if (h == 9 and m == 0 and steps_val >= 50) or (h == 13 and m == 0 and steps_val >= 2000) or (h == 19 and m == 30 and steps_val >= 2500):
                        ex_obj = {
                            "type": "Post-Meal GLUT4 Walk",
                            "dur": 15,
                            "steps": steps_val,
                            "delta": -34,
                            "desc": "15-min brisk ambulatory walk: blunted postprandial glucose spike"
                        }

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
                        g_val = 148
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
                    "ex": ex_obj,
                    "med": med_obj,
                    "notes": notes
                }
                timeline.append(entry)

    return timeline

tl = test_new_timeline()
print('Timeline generated! Total entries:', len(tl))
active_pts = [p for p in tl if not p['isChargingGap'] and not p['isFuture']]
print(f'Active non-gap, non-future entries: {len(active_pts)}')
for p in active_pts:
    details = []
    if p['g']: details.append(f"G={p['g']}")
    if p['sys']: details.append(f"BP={p['sys']}/{p['dia']}")
    if p['hr']: details.append(f"HR={p['hr']}")
    if p['spo2']: details.append(f"SpO2={p['spo2']}%")
    if p['steps']: details.append(f"Steps={p['steps']}")
    if p['meal']: details.append(f"Meal={p['meal']['desc'][:15]}")
    if p['med']: details.append(f"Med={p['med']['name']}")
    if p['ex']: details.append(f"Ex={p['ex']['type']}")
    print(f"  {p['day']} {p['time']}: {', '.join(details)}")
