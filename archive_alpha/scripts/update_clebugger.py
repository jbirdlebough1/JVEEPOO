import sqlite3
import datetime

conn = sqlite3.connect(r"D:\CLEBUGGER\bugs.db")
cursor = conn.cursor()

# Insert CLE-082 if not present
cursor.execute("SELECT bug_code FROM bugs WHERE bug_code = 'CLE-082'")
existing = cursor.fetchone()

if not existing:
    now = datetime.datetime.now().isoformat()
    cursor.execute("""
        INSERT INTO bugs (
            project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at
        ) VALUES (
            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        )
    """, (
        "Veepoo LCARS Health System",
        82,
        "CLE-082",
        "BUILD-2026.11.21.05",
        "Full Scan Page Telemetry Hydration, Watch Sync BLE Proximity Trigger, Mobile Meal Grouping & Offline Staging Queue, Zero-Calorie Tea AI Accuracy, and Accumulated QA Regression Test Suite",
        "RESOLVED",
        "Full Scan / Sync Watch / Nutrition AI / Mobile Cell Portal",
        "index.html, w11_htm_app/index.html, w11_htm_app/server.py, w11_htm_app/cell.html, test_accumulated_qa.py",
        "index.html:7007, index.html:12230, index.html:15005, index.html:16604, server.py:440, server.py:1614, cell.html:300",
        "1. Unhid #cardFullScanReport and hydrated 6 stage cards and report tiles with SQLite telemetry on page load and live poll. 2. Bound triggerWatchSyncNow() to execute direct BLE telemetry harvest and multi-console UI refresh. 3. Fixed tea nutritional hallucination with exact zero-calorie rule (<10 kcal, 0.5g net carbs, 0g protein, 0g fat). 4. Created mobile meal occasion grouping (Breakfast, Lunch, Dinner, Snack/Tea) with offline staging queue (localStorage) and auto/1-click PC Wi-Fi sync. 5. Built accumulated automated QA test suite verifying 24/24 tests 100% green with zero regression.",
        "Verified with test_accumulated_qa.py across 24 tests with 100% pass rate. Both index.html files byte-synchronized.",
        now,
        now,
        now
    ))
    conn.commit()
    print("Inserted CLE-082 successfully!")
else:
    print("CLE-082 already exists.")

conn.close()
