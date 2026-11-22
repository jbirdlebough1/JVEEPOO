import sqlite3
import json

conn = sqlite3.connect(r"D:\CLEBUGGER\bugs.db")
cursor = conn.cursor()
cursor.execute("SELECT bug_code, title, component, affected_files, bug_loc, resolution FROM bugs ORDER BY id ASC")
rows = cursor.fetchall()

print(f"Total bugs in CLEBUGGER: {len(rows)}")
relevant = []
for b_code, title, comp, aff_f, b_loc, resol in rows:
    full_text = f"{b_code} {title} {comp} {aff_f} {b_loc} {resol}".lower()
    if any(k in full_text for k in ["h8", "vee", "5010", "connect", "sync", "dump", "vault", "sleep", "pedometer", "rx", "gatt", "packet", "characteristic"]):
        relevant.append({
            "code": b_code,
            "title": title,
            "comp": comp,
            "loc": b_loc,
            "resolution": resol
        })

print(f"Found {len(relevant)} relevant bugs relating to watch connection, sync, and models.")
with open(r"D:\veepoo\relevant_bugs.json", "w", encoding="utf-8") as f:
    json.dump(relevant, f, indent=2)

for b in relevant:
    print(f"[{b['code']}] {b['title'][:70]}")

conn.close()
