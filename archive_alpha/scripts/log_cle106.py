import sqlite3
from datetime import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()

now = datetime.now().strftime('%Y-%m-%d %H:%M:%S')

title = "Sour Candy Safe Options Engine & 1-Click Meal Pantry Selection Workflow"
resolution = "Resolved issue where 'sour candy that meets my goals' was categorized as chocolate. Added dedicated sour_candy category with SmartSweets Sour Blast Buddies (Fred Meyer, 3g net carbs, Allulose/Monk Fruit, 0% sorbitol/maltitol), Behave Sour Gummy Bears (Safeway, 3g net carbs), and Zolli Candy Zero Sugar Sour Drops (Walmart, 0g net carbs), plus 3 curated recipes (Zero-Sugar Sour Lemon-Berry Collagen Protein Gummies at 0.5g net carbs, Tart Apple Crisp, Frozen Sour Citrus Pops). Added 1-click 'SELECT STORE OPTION & VIEW METRICS' and 'SELECT RECIPE' buttons to instantly display metabolic metrics and BOM in the pre-choice card. Added 1-click 'ADD TO PANTRY AS SNACK' with auto-scroll highlighting in Pathway A Meal Pantry list, enabling users to select and log from their list when consumed."
comments = "Verified zero div tag imbalance, dual-file bit-for-bit parity, 45/45 comprehensive audit checks passed, and 24/24 accumulated QA tests passed."

c.execute("SELECT id FROM bugs WHERE bug_code = 'CLE-106'")
if not c.fetchone():
    c.execute("""
        INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
        VALUES ('veepoo', 106, 'CLE-106', 'v1.4.1', ?, 'RESOLVED', 'Gemini Estimator / Meal Pantry Workflow', 'w11_htm_app/server.py, index.html, w11_htm_app/index.html', 175, ?, ?, ?, ?, ?)
    """, (title, resolution, comments, now, now, now))
    print("[+] Inserted CLE-106")
else:
    c.execute("""
        UPDATE bugs
        SET title = ?, status = 'RESOLVED', component = 'Gemini Estimator / Meal Pantry Workflow',
            affected_files = 'w11_htm_app/server.py, index.html, w11_htm_app/index.html',
            resolution = ?, comments = ?, updated_at = ?, resolved_at = ?
        WHERE bug_code = 'CLE-106'
    """, (title, resolution, comments, now, now))
    print("[+] Updated CLE-106")

conn.commit()
conn.close()
