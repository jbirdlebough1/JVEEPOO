import re

# 1. Inspect server.py to prepare patch for /api/goals/compliance-report and /api/meal/recent limit
with open(r"D:\veepoo\w11_htm_app\server.py", "r", encoding="utf-8") as f:
    server_text = f.read()

endpoint_code = '''
# ---------------------------------------------------------------------------
# GOALS COMPLIANCE & ACTIVITY AUDIT ENGINE (CLE-080)
# ---------------------------------------------------------------------------
def get_goals_compliance_report(timeframe="month"):
    now = datetime.datetime.now()
    if timeframe == "week":
        start_dt = now - datetime.timedelta(days=7)
    elif timeframe == "month":
        start_dt = now - datetime.timedelta(days=30)
    elif timeframe == "qtr":
        start_dt = now - datetime.timedelta(days=90)
    elif timeframe == "ytd":
        start_dt = datetime.datetime(now.year, 1, 1, 0, 0, 0)
    else:
        start_dt = now - datetime.timedelta(days=30)

    start_iso = start_dt.strftime("%Y-%m-%d %H:%M:%S")

    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute("""
            SELECT id, timestamp, meal_name, calories, net_carbs, total_carbs, meets_goal, gemini_notes, vendor_matched
            FROM meal_entries
            WHERE timestamp >= ?
            ORDER BY timestamp DESC
        """, (start_iso,))
        meals = cur.fetchall()

        events = []
        met_count = 0
        missed_no_act_count = 0
        mitigated_count = 0

        for m in meals:
            m_id = m[0]
            ts_str = m[1]
            meal_name = m[2]
            cal = m[3]
            net_c = m[4] if m[4] is not None else 0.0
            tot_c = m[5] if m[5] is not None else 0.0
            meets_flag = bool(m[6]) if m[6] is not None else False
            notes = m[7] or ""
            vendor = m[8] or ""

            # Evaluate against 45g net carb ceiling
            is_carb_compliant = (net_c <= 45.0) or meets_flag

            if is_carb_compliant:
                status = "MET"
                activity_status = "✅ NOMINAL (Within 45g Net Carb Ceiling)"
                met_count += 1
                act_steps = 0
            else:
                # Check for follow-up GLUT4 walk / steps in vital_records within 2.5 hours post-meal
                try:
                    clean_ts = ts_str.replace("T", " ")
                    meal_dt = datetime.datetime.strptime(clean_ts[:19], "%Y-%m-%d %H:%M:%S")
                    window_end = meal_dt + datetime.timedelta(hours=2, minutes=30)
                    cur.execute("""
                        SELECT MAX(value1) FROM vital_records
                        WHERE metric = 'PEDOMETER' AND timestamp >= ? AND timestamp <= ?
                    """, (meal_dt.isoformat(), window_end.isoformat()))
                    step_row = cur.fetchone()
                    act_steps = step_row[0] if (step_row and step_row[0]) else 0
                except Exception:
                    act_steps = 0

                if act_steps >= 1500:
                    status = "MITIGATED"
                    activity_status = f"⚡ GLUT4 WALK LOGGED ({int(act_steps):,} steps blunted peak)"
                    mitigated_count += 1
                else:
                    status = "MISSED"
                    activity_status = "❌ NO ACTIVITY DETECTED (Sedentary Postprandial Excursion)"
                    missed_no_act_count += 1

            events.append({
                "id": m_id,
                "timestamp": ts_str,
                "meal_name": meal_name,
                "vendor": vendor,
                "calories": cal,
                "net_carbs": net_c,
                "total_carbs": tot_c,
                "target_carbs": 45,
                "status": status,
                "activity_status": activity_status,
                "notes": notes
            })

        conn.close()

    total = len(events)
    compliance_pct = round(((met_count + mitigated_count) / total) * 100, 1) if total > 0 else 100.0

    return {
        "status": "OK",
        "timeframe": timeframe,
        "summary": {
            "total_events": total,
            "met_count": met_count,
            "missed_no_activity_count": missed_no_act_count,
            "mitigated_count": mitigated_count,
            "compliance_pct": compliance_pct
        },
        "events": events
    }
'''

print("Checking presence of get_goals_compliance_report...")
if "def get_goals_compliance_report" not in server_text:
    idx_spot = server_text.find("def get_recent_meals")
    server_text = server_text[:idx_spot] + endpoint_code + "\n" + server_text[idx_spot:]
    print("  [✔] Injected get_goals_compliance_report into server.py")

# Update get_recent_meals default limit to 100
server_text = server_text.replace("def get_recent_meals(limit=10):", "def get_recent_meals(limit=100):")
server_text = server_text.replace("elif self.path == \"/api/meal/recent\":\n            meals = get_recent_meals(15)", 
"""elif self.path.startswith("/api/meal/recent"):
            meals = get_recent_meals(100)
            self.send_json_response({"status": "OK", "meals": meals})
        elif self.path.startswith("/api/goals/compliance-report"):
            # Parse query params: timeframe=week|month|qtr|ytd
            tf = "month"
            if "?" in self.path:
                params = urllib.parse.parse_qs(self.path.split("?", 1)[1])
                tf = params.get("timeframe", ["month"])[0]
            report = get_goals_compliance_report(tf)
            self.send_json_response(report)""")

with open(r"D:\veepoo\w11_htm_app\server.py", "w", encoding="utf-8") as f:
    f.write(server_text)

print("[✔] Successfully updated server.py with Goals Report endpoint and expanded meal recent limit")
