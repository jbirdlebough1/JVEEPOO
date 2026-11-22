import sqlite3, json
conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("SELECT id, meal_key, title, calories, net_carbs, total_carbs, protein, fiber, fat, notes FROM popular_meals")
for r in c.fetchall():
    print(r)
