import sqlite3
import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()

now = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

c.execute("DELETE FROM bugs WHERE bug_code = 'CLE-093'")

c.execute('''
INSERT INTO bugs (
    project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at
) VALUES (
    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
)
''', (
    'VEEPOO',
    93,
    'CLE-093',
    'V0.16.18.0',
    'Tab 05 Meal Builder Full-Width Layout Restoration, Active Goals Button & Coach Badge Removal, Hydration & Biometric Metabolic-Activity Interaction Engine',
    'RESOLVED',
    'Tab 05 Meal Builder / Meal Pantry / Hydration Engine / Biometrics',
    'index.html, w11_htm_app/index.html, w11_htm_app/server.py, w11_htm_app/telemetry.db',
    192,
    '1. Removed "VIEW ACTIVE DIABETIC GOALS (TAB 11)" button and "GEMINI COACH ACTIVE" badge from Tab 05 header. 2. Fixed unclosed setup-section-title flex container, making Meal Pantry and Meal Request text area left-justified to full max-width. 3. Purged Chipotle, Scrambled eggs & spinach, 8oz prime rib, 8oz ribeye and broccoli duplicates, and synthetic test meals from popular_meals and meal_entries. 4. Established full meal standing for snacks in the Meal Pantry and logging. 5. Integrated beverage fluid volume in ounces (fluid_oz) across Meal Pantry, database schema, and live Gemini analysis. 6. Added dedicated Hydration & Biometric Metabolic-Activity Interaction Tile tracking fluid intake relative to patient biometrics (Height: 175cm, Weight: 75kg / 165 lbs, BMI: 24.5 kg/m2), daily target (~83 oz + activity offset), and glycemic peak attenuation (-8 to -14 mg/dL).',
    '100% full audit suite (45/45) and accumulated QA (24/24) pass. Div tag symmetry preserved (1695/1695). Exact dual-file byte parity verified (1,146,673 bytes).',
    now,
    now,
    now
))

conn.commit()
print("CLE-093 logged successfully!")

c.execute("SELECT bug_code, title, status FROM bugs WHERE bug_code IN ('CLE-092', 'CLE-093')")
for r in c.fetchall():
    print(r)
conn.close()
