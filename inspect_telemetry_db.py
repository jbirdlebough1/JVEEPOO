import sqlite3

conn = sqlite3.connect(r"D:\veepoo\w11_htm_app\telemetry.db")
c = conn.cursor()
c.execute("SELECT name FROM sqlite_master WHERE type='table'")
tables = [r[0] for r in c.fetchall()]
print("Tables:", tables)
for t in tables:
    c.execute(f"SELECT count(*) FROM {t}")
    print(f"{t}: {c.fetchone()[0]} rows")

c.execute("SELECT DISTINCT metric, count(*) FROM vital_records GROUP BY metric")
print("Metrics in vital_records:", c.fetchall())

c.execute("SELECT timestamp, metric, value1, value2 FROM vital_records ORDER BY id DESC LIMIT 10")
print("Latest 10 vital_records:")
for r in c.fetchall():
    print(" ", r)
