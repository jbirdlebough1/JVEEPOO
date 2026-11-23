import sys, sqlite3, datetime
conn = sqlite3.connect('w11_htm_app/telemetry.db')
cur = conn.cursor()
cur.execute("SELECT timestamp, meal_name, net_carbs, calories FROM meal_entries")
for r in cur.fetchall():
    print(r)
conn.close()
