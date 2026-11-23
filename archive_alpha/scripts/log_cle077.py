import sqlite3, datetime, json

db_path = r"D:\CLEBUGGER\bugs.db"
conn = sqlite3.connect(db_path)
cur = conn.cursor()

cur.execute("SELECT MAX(bug_number) FROM bugs WHERE project_name='VEEPOO'")
row = cur.fetchone()
next_num = (row[0] or 76) + 1
bug_code = f"CLE-{next_num:03d}"
now_iso = datetime.datetime.now().isoformat()

title = "Epic Send Removal, Watch Settings Correlator Positioning, 48h Two-Day Deduplication & Blue Charging Gaps"
resolution = ("1) Scrubbed all fake Epic Send and random doctor ('Dr. Sarah Vance') references across app and presentation; "
              "replaced with authentic patient-controlled air-gapped local clinical export (RFC-4180 CSV / PDF). "
              "2) Updated Step-Glucose Correlator to dynamically anchor step guides to Watch Settings (cfgStepGoal) and display live watch position marker. "
              "3) Implemented SQLite UNIQUE deduplication on (timestamp, metric) in vital_records. "
              "4) Added 48-hour continuous two-day scrollable timeline (/api/telemetry/timeline) with actual collected data on left, "
              "transparent blue charging areas (isChargingGap) for uncollected/charging periods, zero random fill, and future window separation.")

cur.execute("""
    INSERT INTO bugs (
        project_name, bug_number, bug_code, build_number, title, status,
        component, affected_files, bug_loc, resolution, comments,
        created_at, updated_at, resolved_at
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
""", (
    "VEEPOO", next_num, bug_code, 5010, title, "RESOLVED",
    "Telemetry Pipeline & Reports", "w11_htm_app/server.py, index.html, all_slides_data.json",
    "server.py: get_two_day_timeline, vital_records; index.html: renderStepGlucoseCorrelator, tallChart",
    resolution, "Verified with SQLite timeline query and slide regeneration.",
    now_iso, now_iso, now_iso
))
conn.commit()

# Export audit json
cur.execute("SELECT bug_code, title, status, component, resolution FROM bugs WHERE project_name='VEEPOO' ORDER BY bug_number ASC")
all_bugs = [
    {"code": r[0], "title": r[1], "status": r[2], "component": r[3], "resolution": r[4]}
    for r in cur.fetchall()
]
with open(r"D:\veepoo\docs\cle_bugs_audit.json", "w", encoding="utf-8") as f:
    json.dump({"total_resolved": len(all_bugs), "bugs": all_bugs}, f, indent=2)

print(f"[✔] Logged {bug_code}: {title} (Total resolved bugs: {len(all_bugs)})")
conn.close()
