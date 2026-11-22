import sqlite3
import json

conn = sqlite3.connect(r"D:\CLEBUGGER\bugs.db")
cursor = conn.cursor()
cursor.execute("SELECT bug_code, title, component, affected_files, bug_loc, resolution, status FROM bugs ORDER BY id ASC")
rows = cursor.fetchall()

dump = []
for r in rows:
    dump.append({
        "bug_code": r[0],
        "title": r[1],
        "component": r[2],
        "affected_files": r[3],
        "bug_loc": r[4],
        "resolution": r[5],
        "status": r[6]
    })

with open(r"D:\veepoo\clebugger_dump.json", "w", encoding="utf-8") as f:
    json.dump(dump, f, indent=2)

print(f"Dumped {len(dump)} records to D:\\veepoo\\clebugger_dump.json")
conn.close()
