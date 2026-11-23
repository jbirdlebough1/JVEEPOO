import sqlite3
from datetime import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()

now = datetime.now().strftime('%Y-%m-%d %H:%M:%S')

# 1. Log CLE-102: Scope Fix & Kit Kat Support
c.execute("SELECT id FROM bugs WHERE bug_code = 'CLE-102'")
if not c.fetchone():
    c.execute("""
        INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
        VALUES ('veepoo', 102, 'CLE-102', 'v1.4.1',
                'Fix analyzeMealWithGemini Scope ReferenceError & Add Kit Kat Confectionery Specification',
                'RESOLVED', 'Gemini Estimator / Meal Planner', 'w11_htm_app/server.py, index.html, w11_htm_app/index.html', 145,
                'Resolved ReferenceError where parseClientWeightOz(rawText) was evaluated before rawText and text were declared at top of analyzeMealWithGemini. Restored clean function entry and moved client fallback into if (!data) block. Sourced USDA FoodData Central for Kit Kat Crisp Wafers: 6.0 oz portion accurately computed at 872 kcal, 103.8g net carbs, 43.8g fat, acute spike warnings, and harm-reduction fun-size/keto trades.',
                'Verified zero div tag imbalance, dual-file bit-for-bit parity, 45/45 comprehensive audit checks passed, and 24/24 accumulated QA tests passed.',
                ?, ?, ?)
    """, (now, now, now))
    print("[+] Inserted CLE-102")
else:
    print("[=] CLE-102 already present")

# 2. Log CLE-103: Header Alignment & Remove Redundant Quick Nav
c.execute("SELECT id FROM bugs WHERE bug_code = 'CLE-103'")
if not c.fetchone():
    c.execute("""
        INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
        VALUES ('veepoo', 103, 'CLE-103', 'v1.4.1',
                'Clean Meal Planner Tab Header to 02-MEAL PLANNER & Remove Redundant Quick Nav Ribbon',
                'RESOLVED', 'Meal Planner UI', 'index.html, w11_htm_app/index.html', 40,
                'Updated stale 06-ADA header title to 02-MEAL PLANNER: DIET TRADES, CLINICAL RECIPES & PRE-CHOICE ESTIMATION. Removed clinical mentoring and air-gapped privacy badge container on right. Removed redundant QUICK NAV workflow ribbon above the interactive clinical decision flowchart.',
                'Div tag balance delta=0, exact dual-file bit-for-bit parity maintained, 45/45 audit and 24/24 QA tests passed.',
                ?, ?, ?)
    """, (now, now, now))
    print("[+] Inserted CLE-103")
else:
    print("[=] CLE-103 already present")

conn.commit()
conn.close()
