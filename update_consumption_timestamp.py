# update_consumption_timestamp.py
import sqlite3

conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
cur = conn.cursor()

# Find the peppermint meal entry
cur.execute("SELECT id, timestamp, meal_name, net_carbs FROM meal_entries WHERE meal_name LIKE '%Junior%' OR meal_name LIKE '%pepper%'")
rows = cur.fetchall()
print("Current meal entries:", rows)

# Update the timestamp to exactly 2026-11-22 11:36:00
cur.execute("""
    UPDATE meal_entries
    SET timestamp = '2026-11-22 11:36:00'
    WHERE meal_name LIKE '%Junior%' OR meal_name LIKE '%pepper%' OR id = 34
""")

# Check if there is an associated diet marker in vital_records
cur.execute("""
    UPDATE vital_records
    SET timestamp = '2026-11-22 11:36:00'
    WHERE record_type = 'diet' AND (notes LIKE '%pepper%' OR notes LIKE '%Junior%')
""")

conn.commit()

# Verify
cur.execute("SELECT id, timestamp, meal_name, net_carbs FROM meal_entries WHERE id = 34")
print("Updated meal entry:", cur.fetchall())

# Check latest glucose readings from watch
cur.execute("""
    SELECT timestamp, value1 FROM vital_records 
    WHERE record_type = 'glucose' AND timestamp >= '2026-11-22 11:00:00'
    ORDER BY timestamp DESC LIMIT 5
""")
print("Latest glucose readings from watch:", cur.fetchall())

conn.close()
