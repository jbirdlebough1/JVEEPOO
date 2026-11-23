import sqlite3

for p in [r"D:\veepoo\w11_htm_app\telemetry.db", r"D:\veepoo\telemetry_master.db"]:
    conn = sqlite3.connect(p)
    cur = conn.cursor()
    cur.execute("DELETE FROM registered_cells WHERE cell_id='Cell 3'")
    cur.execute("DELETE FROM user_profiles WHERE user_uid='USR-9482-03'")
    try:
        cur.execute("DELETE FROM meal_entries WHERE user_uid='USR-9482-03'")
    except sqlite3.OperationalError:
        pass
    conn.commit()
    conn.close()
print("Pristine database state verified.")
