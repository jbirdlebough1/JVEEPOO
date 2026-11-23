import sqlite3
import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
cur = conn.cursor()

now = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

# CLE-095
cur.execute("""
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
""", (
    "VEEPOO",
    95,
    "CLE-095",
    "2.0.95",
    "Clinical Workflow Sequence Tab Swap (01-FULL SCAN, 02-CARDIO, 03-METABOLIC, 04-MEAL PLANNER, 05-REPORTS, 06-EXPORT) and Cross-Tab Reference Alignment",
    "RESOLVED",
    "LCARS Sidebar Navigation & Workflow Layout",
    "index.html, w11_htm_app/index.html",
    45,
    "Swapped Meal Planner with Export tab to establish natural clinical usage flow (Transponder link -> Full Scan -> Cardio -> Metabolic -> Meal Planner -> Reports -> Export). Updated all cross-tab references (8-Lane Chart Tab 05, Meal Planner Tab 04, Export Tab 06). Maintained exact div tag balance (1725/1725, 0 delta) and byte parity.",
    "User requested: 'lets move swap meal planner with export tab, so we should see full scan, cardio, metatbolic, meal planner, reports, export. that makes the flow more natural. User starts by lining, then full scan, cardio, metabolic, the meal planner and reports the six highest usage tabs'.",
    now,
    now,
    now
))

# CLE-096
cur.execute("""
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
""", (
    "VEEPOO",
    96,
    "CLE-096",
    "2.0.96",
    "Gemini Pre-Choice 'What Will This Choice Cost Me' Estimation Isolation, Distinct Add to Meal Pantry as Meal/Snack Reference Flow, Unconsumed Shake Purge, and Dinner Goals Adherence",
    "RESOLVED",
    "Gemini Nutrition Coach, Meal Pantry & Meal Log Backend",
    "index.html, w11_htm_app/index.html, w11_htm_app/server.py, telemetry.db",
    120,
    "1) Isolated /api/meal/analyze by setting analyze_only=True by default with commit=False, preventing unconsumed meals from writing to meal_entries. 2) Provided explicit '⭐ ADD TO PANTRY AS MEAL' and '🍵 ADD TO PANTRY AS SNACK' buttons in Flowchart Step 3 and Action Bar to build a reference library of known foods and metabolic costs without logging as eaten today. 3) Purged unconsumed shake (Row 30 and cell_30) from SQLite database. 4) Updated user's actual dinner (4 oz chicken breast, lettuce and baby tomato salad, 2 2oz cornbread, 12 oz ocean spray zero sugar tropical punch) to 490 kcal, 33g net carbs, and meets_goal=1. Goals compliance API reaches 100.0% (6/6 Met, 0 Missed).",
    "User requests: 'your mixing gemini what will this choice cost me with actual consumed meal in the meal journal/log. you should have offered to add to the Meal Pantry as a meal or snack. This build them a refence of know things they like and waht the cost its metabolically.' and 'Now my dinner 4 oz chicken breast, lettuce and baby tomato salad and 2 2oz corn bread should meet goals. you added the shake which was not consumed.'",
    now,
    now,
    now
))

conn.commit()
conn.close()
print("[+] Successfully logged CLE-095 and CLE-096 in D:/CLEBUGGER/bugs.db")
