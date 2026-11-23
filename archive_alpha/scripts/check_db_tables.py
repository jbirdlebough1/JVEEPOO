import sqlite3
conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("SELECT name FROM sqlite_master WHERE type='table'")
print('Tables in telemetry.db:', c.fetchall())
