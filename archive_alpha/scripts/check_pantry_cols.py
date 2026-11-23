import sqlite3
conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("PRAGMA table_info(popular_meals)")
print([col[1] for col in c.fetchall()])
c.execute("SELECT * FROM popular_meals")
for r in c.fetchall():
    print(r)
