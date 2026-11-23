import sqlite3
import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()

bug_code = "CLE-099"
bug_number = 99
project_name = "veepoo"
build_number = "v2.5.0"
title = "Directed Workflow Optimization: Swap Meal Planner to 02-MEAL PLANNER and Metabolic to 03-METABOLIC, and Eliminate Duplication between Reusable Templates and Chronological Consumed Journal"
component = "UI / Directed Workflow / Nutrition / Metabolic"
affected_files = "index.html, w11_htm_app/index.html"
resolution = (
    "1. Optimized directed linear workflow sequence: swapped tabs so 02-MEAL PLANNER immediately precedes 03-METABOLIC (Scan Baseline -> Plan/Consume Food -> Monitor Real-Time Hemodynamic/Glucose Response -> View Reports -> Export).\n"
    "2. Eliminated conceptual duplication between Meal Pantry and Consumed Log: clarified Tab 02 Meal Pantry as 'Saved Meal & Snack Templates (Reusable Favorites)' and Tab 03 Section 03 as 'Actual Consumed Meals & Glucose Scrubber (Date & Time Timeline)'.\n"
    "3. Added direct transition button in Meal Planner ('👉 VIEW METABOLIC RESPONSE (TAB 03)') and updated Full Scan link to Tab 02."
)
comments = "All 45/45 audit checks and 24/24 QA checks passed. 0-delta div tag balance and bit-for-bit parity preserved."
status = "RESOLVED"
now_str = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

c.execute("SELECT id FROM bugs WHERE bug_code = ?", (bug_code,))
existing = c.fetchone()
if existing:
    c.execute("""
    UPDATE bugs
    SET title = ?, status = ?, component = ?, affected_files = ?, resolution = ?, comments = ?, updated_at = ?, resolved_at = ?
    WHERE bug_code = ?
    """, (title, status, component, affected_files, resolution, comments, now_str, now_str, bug_code))
else:
    c.execute("""
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """, (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, resolution, comments, now_str, now_str, now_str))

conn.commit()
print(f"[+] Successfully logged {bug_code} in D:/CLEBUGGER/bugs.db")
c.execute("SELECT bug_code, title, status FROM bugs WHERE bug_code = ?", (bug_code,))
print("Verification:", c.fetchone())
conn.close()
