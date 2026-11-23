import sqlite3
conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("PRAGMA table_info(vital_records)")
print('vital_records columns:', [col[1] for col in c.fetchall()])

c.execute("SELECT * FROM vital_records WHERE metric = 'SLEEP' ORDER BY timestamp DESC LIMIT 10")
rows = c.fetchall()
print(f'Sleep rows in vital_records: {len(rows)}')
for r in rows:
    print(r)
