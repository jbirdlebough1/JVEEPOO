import sqlite3, datetime

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
    except Exception as e:
        pass

print(f'Total populated slot buckets: {len(buckets)}')
for k in sorted(buckets.keys()):
    day_lbl = "YESTERDAY" if k[0] == 0 else "TODAY"
    time_str = f"{k[1]:02d}:{k[2]:02d}"
    metrics_present = list(buckets[k].keys())
    g_val = buckets[k].get("GLUCOSE", (None,))[0]
    steps = buckets[k].get("PEDOMETER", (None,))[0]
    print(f'{day_lbl} {time_str}: metrics={metrics_present} | g={g_val} | steps={steps}')

conn.close()
