import sqlite3
import urllib.request
import json
import time

conn = sqlite3.connect('w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("INSERT INTO meal_entries (timestamp, meal_name, calories, net_carbs) VALUES ('2026-11-21 17:40:00', 'Dummy Test Meal', 100, 10)")
dummy_id = c.lastrowid
conn.commit()
conn.close()
print(f"Inserted dummy id={dummy_id}")

time.sleep(0.5)

req = urllib.request.Request(f'http://127.0.0.1:8899/api/meal/delete?id={dummy_id}', data=b'', method='POST')
with urllib.request.urlopen(req) as resp:
    res = json.loads(resp.read().decode())
    print("POST delete response:", res)

conn = sqlite3.connect('w11_htm_app/telemetry.db')
c = conn.cursor()
c.execute("SELECT id FROM meal_entries WHERE id = ?", (dummy_id,))
row = c.fetchone()
conn.close()
assert row is None, "Row was not deleted!"
print("Verified: Row successfully deleted from SQLite via POST /api/meal/delete!")
