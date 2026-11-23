import sqlite3

conn = sqlite3.connect(r'D:/veepoo/w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("SELECT DISTINCT metric FROM vital_records")
metrics = [r[0] for r in c.fetchall()]
print("Metrics in telemetry.db:", metrics)

for m in metrics:
    c.execute("SELECT timestamp, value1, value2, unit, status, mode FROM vital_records WHERE metric = ? ORDER BY id DESC LIMIT 1", (m,))
    print(f"Latest {m}:", c.fetchone())
conn.close()
