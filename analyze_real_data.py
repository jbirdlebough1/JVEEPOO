import sqlite3, datetime

conn = sqlite3.connect('w11_htm_app/telemetry.db')
cur = conn.cursor()

# Check date distribution of records with timezone consideration
cur.execute("""
    SELECT 
        DATE(datetime(timestamp)) as dt_utc,
        metric,
        COUNT(*),
        ROUND(AVG(value1), 1),
        ROUND(MIN(value1), 1),
        ROUND(MAX(value1), 1)
    FROM vital_records
    GROUP BY dt_utc, metric
    ORDER BY dt_utc DESC, metric ASC
""")
rows = cur.fetchall()
print('Daily metric summary (UTC dates):')
for r in rows:
    print(r)

cur.execute("SELECT id, timestamp, meal_name, calories, net_carbs, protein, fat FROM meal_entries ORDER BY timestamp DESC")
print('\nMeal entries:')
for m in cur.fetchall():
    print(m)

conn.close()
