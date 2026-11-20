import sqlite3
import json

db_path = r"D:\CLEBUGGER\bugs.db"
conn = sqlite3.connect(db_path)
c = conn.cursor()

bug_code = "CLE-076"
bug_number = 76
project_name = "VEEPOO_5010_LCARS"
build_number = "5010-W11-BUILD-103"
title = "Popular Meals Full CRUD, Longitudinal Doctor-Patient Explorer (Day, Week, Month, Qtr eA1C, Year), Dick's Custom Feast Engine & McDonald's Scrub"
status = "RESOLVED"
component = "LCARS Clinical Telemetry Web Console & Backend"
affected_files = "w11_htm_app/server.py, w11_htm_app/index.html, index.html, w11_htm_app/mobile_upload.html, mobile_upload.html"
bug_loc = 680
resolution = (
    "1. Scrubbed McDonald's from presets, buttons, pull-downs, and backend scrapers due to zero clinically safe diabetic options (<45g net carbs without trans fats, dextrose coatings, and high-glycemic buns). Injected clinical rejection advisory.\n"
    "2. Built Popular Meals Library with full CRUD (View, Edit, Delete, Create) backed by SQLite `popular_meals` table and localStorage synchronization.\n"
    "3. Gemini Pre-Choice Nutrition Coach accurately assesses user requests like 'Dick's DriveIn 2 cheeze burgers, 3 tatar, fry and large diet' = 95g Net Carbs, 1,260 kcal, 35g Protein, 78g Fat, High Glycemic Load 38, itemized BOM, and mandatory 35-45 min GLUT4 walk prescription.\n"
    "4. Empowered user choices upon Gemini calculation: '⭐ ADD TO POPULAR MEALS' (1-click reuse) and '🚀 SUBMIT ACTUAL MEAL & TRACK METRICS' (logs to 8-lane telemetry stack & switches to correlation view).\n"
    "5. Implemented Doctor & Patient Longitudinal Meal-to-Metric Correlation Engine bridging daily 24-hr postprandial curve, 7-day weekly recurrence, 30-day glycemic stability (CV% 17.8%), 90-day quarterly eA1C lab window (7.4% -> 5.6% remission), and 365-day annual durability with doctor mentoring notes.\n"
    "6. Rebuilt presentation slides, synchronized mobile portal, and recompiled Preview_Presentation.exe."
)
comments = "Verified via headless Chrome CDP with zero syntax errors. Synced with local and GitHub remotes."

c.execute("""
    INSERT OR REPLACE INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)
""", (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments))

conn.commit()

c.execute("SELECT id, project_name, bug_code, title, status, component, affected_files, resolution, comments FROM bugs ORDER BY id ASC")
all_bugs = [
    {
        "id": b[0], "project_name": b[1], "bug_code": b[2], "title": b[3], "status": b[4],
        "component": b[5], "affected_files": b[6], "resolution": b[7], "comments": b[8]
    }
    for b in c.fetchall()
]
conn.close()

with open(r"D:\veepoo\docs\cle_bugs_audit.json", "w", encoding="utf-8") as f:
    json.dump(all_bugs, f, indent=2)
print(f"[✔] Successfully logged {bug_code} and exported {len(all_bugs)} bugs to D:\\veepoo\\docs\\cle_bugs_audit.json")
