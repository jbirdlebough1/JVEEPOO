import sqlite3

conn = sqlite3.connect('w11_htm_app/telemetry.db')
c = conn.cursor()

c.execute("SELECT id, timestamp, meal_name, calories, net_carbs FROM meal_entries WHERE meal_name LIKE '%dick%' OR meal_name LIKE '%Dick%'")
rows = c.fetchall()
print("Found Dick's entries in meal_entries:")
for r in rows:
    print(r)

c.execute("DELETE FROM meal_entries WHERE meal_name LIKE '%dick%' OR meal_name LIKE '%Dick%'")
conn.commit()
print("Deleted rows count:", c.rowcount)

print("\nRemaining meal_entries:")
c.execute("SELECT id, timestamp, meal_name, fluid_oz, net_carbs, calories FROM meal_entries ORDER BY id DESC")
for r in c.fetchall():
    print(r)

conn.close()
