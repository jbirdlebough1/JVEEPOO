import sqlite3

conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("SELECT metric, COUNT(*), MIN(timestamp), MAX(timestamp) FROM vital_records GROUP BY metric")
for r in c.fetchall():
    print("Metric:", r)

print("\nSample records for each metric:")
c.execute("SELECT DISTINCT metric FROM vital_records")
metrics = [r[0] for r in c.fetchall()]
for m in metrics:
    c.execute("SELECT * FROM vital_records WHERE metric = ? ORDER BY timestamp DESC LIMIT 2", (m,))
    print(f"--- {m} ---")
    for r in c.fetchall():
        print("  ", r)
