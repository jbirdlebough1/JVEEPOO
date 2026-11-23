import sqlite3
conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("SELECT id, metric, value1, value2, unit, status, mode, timestamp FROM telemetry WHERE metric = 'SLEEP' ORDER BY timestamp DESC LIMIT 10")
rows = c.fetchall()
print(f'Sleep rows count: {len(rows)}')
for r in rows:
    print(r)
