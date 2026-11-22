import sqlite3

con = sqlite3.connect(r'D:\veepoo\w11_htm_app\telemetry.db')
cur = con.cursor()
rows = cur.execute('SELECT id, timestamp, meal_name, calories, net_carbs, protein, fat, meets_goal, source FROM meal_entries ORDER BY id ASC').fetchall()
print(f"Total meal entries: {len(rows)}")
for r in rows:
    print(r)
