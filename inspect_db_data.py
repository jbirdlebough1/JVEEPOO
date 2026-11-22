import sqlite3
import json

db_path = r"D:\veepoo\w11_htm_app\telemetry.db"
conn = sqlite3.connect(db_path)
cur = conn.cursor()

print("=== ALL MEAL ENTRIES IN DB ===")
cur.execute("SELECT id, timestamp, meal_name, source, calories, net_carbs, protein, fat, fiber, meets_goal, gemini_notes FROM meal_entries ORDER BY id DESC")
rows = cur.fetchall()
print(f"Total meal rows in db: {len(rows)}")
for r in rows:
    print(r)

print("\n=== LATEST VITAL RECORDS PER METRIC ===")
cur.execute("""
    SELECT v1.metric, v1.timestamp, v1.value1, v1.value2, v1.unit, v1.status, v1.mode
    FROM vital_records v1
    INNER JOIN (
        SELECT metric, MAX(id) as max_id FROM vital_records GROUP BY metric
    ) v2 ON v1.id = v2.max_id
""")
vitals = cur.fetchall()
for v in vitals:
    print(v)

conn.close()
