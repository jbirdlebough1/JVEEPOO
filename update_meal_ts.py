# update_meal_ts.py
import sqlite3

conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
cur = conn.cursor()

cur.execute("UPDATE meal_entries SET timestamp = '2026-11-22 11:36:00' WHERE id = 34")
conn.commit()

cur.execute("SELECT id, timestamp, meal_name, net_carbs FROM meal_entries WHERE id = 34")
print("Verified meal_entries #34:", cur.fetchall())

cur.execute("SELECT timestamp, metric, value1, unit FROM vital_records WHERE metric = 'glucose' ORDER BY timestamp DESC LIMIT 8")
rows = cur.fetchall()
print("\nMost recent watch glucose readings:")
for r in rows:
    print(" ", r)

conn.close()
