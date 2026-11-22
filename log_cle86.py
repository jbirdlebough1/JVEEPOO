import sqlite3
import datetime

cle_db = r"D:\CLEBUGGER\bugs.db"
conn = sqlite3.connect(cle_db)
cur = conn.cursor()

bug_code = "CLE-086"
title = "Daily Intake Summary of All Consumed, Stage Hex Code Scrub, Colored % Complete, Sleep Data Ingestion, Meal Pantry Auto-Upsert, and Removal of Cell Pill"
resolution = (
    "1. Implemented Daily Intake Summary of All Consumed (#cardDailyIntakeSummary) in Tab 04 Metabolic with 4-bay occasion breakdown "
    "(Breakfast, Lunch, Dinner, Snack) and macro totals vs daily targets, accompanied by /api/meal/daily_summary endpoint in server.py. "
    "2. Scrubbed all hex opcodes (0x89 0x01, etc.) from Full Scan measurement cards (#stageCard1..6), replacing with human-readable STAGE 01..06. "
    "3. Restored colored % complete numbers (#stagePct1..6) displaying live progress during scan and 100% on completion. "
    "4. Restored sleep data telemetry: added SLEEP to server snapshot metrics, assembled multi-packet stream posted to SQLite, and hydrated in DOM. "
    "5. Scrubbed obsolete mobile food intake gateway pill from LCARS desktop console. "
    "6. Cell uploads dynamically upsert into Meal Pantry (popular_meals) and submitted meals strictly sort latest-to-oldest. "
    "7. Fixed soundSuccess ReferenceError with global safe assignment. "
    "8. Verified 0 DOM unclosed tags, 0 JS syntax errors, and 100% pass across 45 test cases."
)
status = "RESOLVED"
now_str = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

cur.execute("""
    INSERT INTO bugs 
    (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at) 
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
""", (
    "Veepoo LCARS Health System",
    86,
    bug_code,
    "v0.16.16.0",
    title,
    status,
    "Daily Intake Summary / Full Scan Stage Cards / Sleep Telemetry / Meal Pantry",
    "index.html, w11_htm_app/index.html, server.py, run_full_audit_test_suite.py",
    "index.html:1788, index.html:5670, index.html:9200, index.html:115000, server.py:1200",
    resolution,
    "Verified via run_full_audit_test_suite.py (45/45 passed, 100%) and test_accumulated_qa.py (24/24 passed, 100%)",
    now_str,
    now_str,
    now_str
))

conn.commit()
conn.close()
print(f"[✔] Successfully logged {bug_code} in CLEBUGGER database!")
