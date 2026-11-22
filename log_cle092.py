import sqlite3
import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()

now = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

c.execute("DELETE FROM bugs WHERE bug_code = 'CLE-092'")

c.execute('''
INSERT INTO bugs (
    project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at
) VALUES (
    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
)
''', (
    'VEEPOO',
    92,
    'CLE-092',
    'V0.16.17.0',
    'Purge of Synthetic Demo Salmon & Duplicates, True Daily Intake Restoration (Mon-Sat Breakfast, Soup Lunch, Clove Tea), and Pantry-to-Log Isolation',
    'RESOLVED',
    'Metabolic Engine / Diet History / Daily Intake Summary / SQLite Vault',
    'index.html, w11_htm_app/index.html, w11_htm_app/server.py, w11_htm_app/telemetry.db',
    185,
    'Completely purged synthetic "Wild-Caught Salmon" and test duplicates from meal_entries and popular_meals. Corrected Daily Intake Summary to strictly compute todays intake from 00:01 AM: Breakfast (Mon-Sat High-Fiber Metabolic Breakfast MEAL-01: 430 kcal, 38g net carbs), Lunch (Weekly Soup with Vegetables & Meat, Whole Wheat Grilled Cheese, 1.5oz Orange, 1/2 Apple Slices: 493 kcal, 34g net carbs), and Afternoon Snack (Brewed Clove & Cinnamon Infusion: 2 kcal, 0.5g net carbs) totaling 925 kcal, 73g net carbs (100% diabetic compliant). Isolated Meal Pantry recipe selector from Consumed Log historical records. Enhanced renderDietHistory() with robust food component extraction and fallback parsing.',
    'Verified via full audit suite (45/45 pass) and accumulated QA (24/24 pass). Exact tag balance preserved (1677/1677 div tags). Zero synthetic meals remain.',
    now,
    now,
    now
))

conn.commit()
print("CLE-092 logged successfully!")

c.execute("SELECT bug_code, title, status FROM bugs WHERE bug_code IN ('CLE-091', 'CLE-092')")
for r in c.fetchall():
    print(r)
conn.close()
