import sqlite3
conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("SELECT id, timestamp, value1, value2, mode FROM vital_records WHERE metric = 'SLEEP' ORDER BY timestamp DESC")
rows = c.fetchall()
print('All sleep records in DB:')
for r in rows:
    print(r)
