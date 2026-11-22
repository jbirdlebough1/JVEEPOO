import sqlite3

conn = sqlite3.connect('w11_htm_app/telemetry.db')
cur = conn.cursor()
cur.execute("SELECT MIN(timestamp), MAX(timestamp), COUNT(*) FROM vital_records")
print('vital_records timestamp range:', cur.fetchall())

cur.execute("SELECT DISTINCT date(timestamp) FROM vital_records ORDER BY date(timestamp) DESC")
print('vital_records dates:', cur.fetchall())

cur.execute("SELECT MIN(timestamp), MAX(timestamp), COUNT(*) FROM meal_entries")
print('meal_entries timestamp range:', cur.fetchall())

cur.execute("SELECT timestamp, metric, value1, value2 FROM vital_records WHERE metric='GLUCOSE' ORDER BY timestamp DESC LIMIT 10")
print('Recent glucose records:', cur.fetchall())

cur.execute("SELECT timestamp, metric, value1, value2 FROM vital_records WHERE metric='PEDOMETER' ORDER BY timestamp DESC LIMIT 10")
print('Recent pedometer records:', cur.fetchall())

conn.close()
