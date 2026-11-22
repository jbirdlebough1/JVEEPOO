import sqlite3

conn = sqlite3.connect('w11_htm_app/telemetry.db')
cur = conn.cursor()

cur.execute("SELECT name FROM sqlite_master WHERE type='table'")
tables = cur.fetchall()
print('Tables:', tables)

for t in tables:
    t_name = t[0]
    cur.execute(f"SELECT COUNT(*) FROM {t_name}")
    count = cur.fetchone()[0]
    print(f'Table {t_name}: {count} rows')

cur.execute("SELECT DISTINCT metric, COUNT(*) FROM vital_records GROUP BY metric")
print('Vital records by metric:', cur.fetchall())

cur.execute("SELECT * FROM vital_records ORDER BY timestamp DESC LIMIT 5")
print('Latest 5 vital records:', cur.fetchall())

cur.execute("SELECT * FROM meal_entries ORDER BY timestamp DESC LIMIT 5")
print('Latest 5 meal entries:', cur.fetchall())

try:
    cur.execute("SELECT * FROM watch_config")
    print('Watch config table:', cur.fetchall())
except Exception as e:
    print('Watch config error:', e)

try:
    cur.execute("SELECT * FROM user_settings")
    print('User settings:', cur.fetchall())
except Exception as e:
    print('User settings error:', e)

conn.close()
