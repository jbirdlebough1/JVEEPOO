import sqlite3
import datetime

con = sqlite3.connect(r'D:\CLEBUGGER\bugs.db')
cur = con.cursor()

now = datetime.datetime.now().isoformat()
cur.execute("""
    INSERT OR REPLACE INTO bugs 
    (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
""", (
    "Veepoo LCARS 5010",
    87,
    "CLE-087",
    "v0.16.17.0",
    "Unconditional Screen Field Hydration Across All Consoles, Purge of Synthetic Test Duplicates, True Daily Intake Summary with Actual Food Components, and Latest-to-Oldest Meal Log Display",
    "RESOLVED",
    "Telemetry Hydration, Daily Intake Summary, Meal Entries & UI Consoles",
    "index.html, w11_htm_app/index.html, w11_htm_app/server.py, telemetry.db",
    "pollLiveTelemetryChanges(), renderDietHistory(), switchTab(), server.py /api/meal/daily_summary, /api/meal/recent, /api/meal/delete",
    "1. Removed restrictive dbVer gating from pollLiveTelemetryChanges(force) and synchronized all global variables and DOM fields across Tab 01, 02, 03, 04, and 07. 2. Purged 14 duplicate test injections from meal_entries in telemetry.db. 3. Added individual food components breakdown and correct occasion categorization to Daily Intake Summary and Meal Log. 4. Added /api/meal/delete to server.py. 5. Balanced all page DOM tags to 0 diff.",
    "Verified with 45/45 passed checks in run_full_audit_test_suite.py and 24/24 in test_accumulated_qa.py. Dual-file byte-for-byte exact equality maintained.",
    now,
    now,
    now
))

con.commit()
print("Logged CLE-087 in CLEBUGGER database!")
con.close()
