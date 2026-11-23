import sqlite3
import json
import datetime

db_path = r'D:\veepoo\w11_htm_app\telemetry.db'
conn = sqlite3.connect(db_path)
cur = conn.cursor()

# 1. Inspect existing meal_entries
rows = cur.execute('SELECT id, timestamp, meal_name FROM meal_entries').fetchall()
print(f"Current entries in meal_entries: {len(rows)}")
for r in rows:
    print(r)

conn.close()
