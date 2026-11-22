import sqlite3, os

db_path = 'D:/veepoo/w11_htm_app/telemetry.db'
if not os.path.exists(db_path):
    print("telemetry.db not found at", db_path)
    # search for .db files
    for r, d, f in os.walk('D:/veepoo'):
        for file in f:
            if file.endswith('.db'):
                print("Found db:", os.path.join(r, file))

if os.path.exists(db_path):
    conn = sqlite3.connect(db_path)
    cur = conn.cursor()
    cur.execute("SELECT name FROM sqlite_master WHERE type='table'")
    tables = cur.fetchall()
    print("Tables:", tables)

    cur.execute("SELECT DISTINCT metric FROM vital_records")
    metrics = cur.fetchall()
    print("Distinct metrics in vital_records:", metrics)

    cur.execute("SELECT COUNT(*), metric FROM vital_records GROUP BY metric")
    counts = cur.fetchall()
    print("Counts per metric:", counts)

    # Check if there are any records with metric containing SLEEP
    cur.execute("SELECT * FROM vital_records WHERE metric LIKE '%SLEEP%' LIMIT 5")
    sleep_rows = cur.fetchall()
    print("Sleep rows:", sleep_rows)

    conn.close()
