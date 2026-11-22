import sqlite3, datetime

conn = sqlite3.connect('w11_htm_app/telemetry.db')
cur = conn.cursor()

cur.execute("""
    SELECT timestamp, metric, value1, value2 FROM vital_records
    WHERE metric IN ('GLUCOSE', 'PEDOMETER')
    ORDER BY timestamp ASC
""")
rows = cur.fetchall()

daily_data = {}
for r in rows:
    ts_str = r[0]
    try:
        if "Z" in ts_str or "+" in ts_str:
            dt = datetime.datetime.fromisoformat(ts_str.replace("Z", "+00:00")).astimezone().replace(tzinfo=None)
        else:
            clean_ts = ts_str.replace("T", " ")
            dt = datetime.datetime.strptime(clean_ts[:16], "%Y-%m-%d %H:%M")
        d_str = dt.strftime("%Y-%m-%d")
        if d_str not in daily_data:
            daily_data[d_str] = {"glucose": [], "steps": 0}
        if r[1] == 'GLUCOSE':
            daily_data[d_str]["glucose"].append(r[2])
        elif r[1] == 'PEDOMETER':
            daily_data[d_str]["steps"] = max(daily_data[d_str]["steps"], int(r[2]))
    except:
        pass

print('Real daily steps & glucose pairs:')
for d, v in sorted(daily_data.items()):
    avg_g = round(sum(v["glucose"]) / len(v["glucose"]), 1) if v["glucose"] else None
    print(f'Date {d}: Steps={v["steps"]}, Mean Glucose={avg_g}, Samples={len(v["glucose"])}')

conn.close()
