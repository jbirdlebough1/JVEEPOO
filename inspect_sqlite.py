import sqlite3

conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
c = conn.cursor()

c.execute("SELECT name FROM sqlite_master WHERE type='table'")
tables = c.fetchall()
print("Tables:", tables)

for t in tables:
    tname = t[0]
    c.execute(f"SELECT COUNT(*) FROM {tname}")
    cnt = c.fetchone()[0]
    print(f"\nTable {tname}: {cnt} rows")
    c.execute(f"SELECT * FROM {tname} ORDER BY rowid DESC LIMIT 5")
    rows = c.fetchall()
    for r in rows:
        print("  ", r)

if 'vitals' in [t[0] for t in tables]:
    print("\nDistinct metrics in vitals:")
    c.execute("SELECT metric, COUNT(*), MIN(timestamp), MAX(timestamp) FROM vitals GROUP BY metric")
    for r in c.fetchall():
        print("  Metric:", r)
