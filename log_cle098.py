import sqlite3
import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()

bug_code = "CLE-098"
bug_number = 98
project_name = "veepoo"
build_number = "v2.5.0"
title = "Full Scan Multi-Vector Contrast Fix, Restaurant Meal BOM Bypass (Dairy Queen/Fast Food to Restaurant Disclosure), Unified Cardio-Metabolic Console (02-METABOLIC), and Residential Zip Code Grocery Sourcing (40+ Miles from Seattle in Config & Meal Planner)"
component = "UI / Nutrition Coach / Config / Cardio-Metabolic"
affected_files = "index.html, w11_htm_app/index.html, w11_htm_app/server.py"
resolution = (
    "1. Restored Full Scan Clinical Multi-Vector Synthesis & Evaluation text styling to high-contrast dark blue (#0369a1 header, #0c4a6e body, #f0f9ff background) for WCAG AAA daylight readability.\n"
    "2. Implemented isRestaurantOrPreparedMeal(data) conditional BOM bypass: fast food and restaurant orders (Dairy Queen, Taco Time, Dick's, etc.) bypass grocery aisles (Fred Meyer/Walmart) and display the Restaurant Ordering & Nutritional Disclosure Guide; home recipes retain the itemized BOM and 20-mile local grocers.\n"
    "3. Unified Cardio and Metabolic into single 02-METABOLIC console bay (#secCardioAutonomicBay) with smooth backward compatibility.\n"
    "4. Added Residential Zip Code configuration to Tab 10-CONFIG (Section 01) and Tab 03-MEAL PLANNER with live 2-way sync and dynamic store resolution tailored for users living 40+ miles from Seattle (e.g. North Sound 982xx, South Sound 983xx/984xx/985xx, Foothills 980xx)."
)
comments = "All 45/45 audit checks and 24/24 QA checks passed. Bit-for-bit dual file parity preserved."
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
