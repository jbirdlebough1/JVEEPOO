import sqlite3
import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()

now = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

c.execute("DELETE FROM bugs WHERE bug_code = 'CLE-094'")

c.execute('''
INSERT INTO bugs (
    project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at
) VALUES (
    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
)
''', (
    'VEEPOO',
    94,
    'CLE-094',
    'V0.16.19.0',
    'POST /api/meal/delete Endpoint Restoration, Purge of Stale Dick Meal, Re-labeling to 05-MEAL PLANNER, and Visual Thinking Style Vertical Flowchart & Top Ribbon Pipeline',
    'RESOLVED',
    'Tab 05 Meal Planner / Visual Flowchart / Server Delete API / SQLite Vault',
    'index.html, w11_htm_app/index.html, w11_htm_app/server.py, w11_htm_app/telemetry.db',
    215,
    '1. Fixed delete button in chronological journal by implementing POST /api/meal/delete route in server.py (supporting both query params and JSON payload) and adding fallback in deleteMealEntry(). 2. Completely purged stale Dick meal from SQLite meal_entries. 3. Re-labeled Tab 05 sidebar from "05-ADA TRADES" to "05-MEAL PLANNER". 4. Added top LCARS quick-nav ribbon across Tab 05 (New Meal, Meal Pantry, Gemini Estimate, ADA Trades, Consume & Log). 5. Implemented interactive Visual Thinking Style Vertical Flowchart pipeline: Step 1 Input Selection (Pantry vs New Meal with fluid oz) -> Step 2 Gemini Clinical Estimation & Nutrition Audit (with Tweak/Re-Assess and Review ADA Trades options) -> Step 3 Action Decision (Submit to Meal Pantry template vs Consume Now to Diary).',
    '100% pass across all 45 audit and 24 QA tests. Div tag symmetry preserved (1725/1725). Exact dual-file byte parity verified (1,161,173 bytes).',
    now,
    now,
    now
))

conn.commit()
print("CLE-094 logged successfully!")

c.execute("SELECT bug_code, title, status FROM bugs WHERE bug_code IN ('CLE-092', 'CLE-093', 'CLE-094')")
for r in c.fetchall():
    print(r)
conn.close()
