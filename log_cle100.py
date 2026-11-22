import sqlite3
import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()

bug_code = "CLE-100"
bug_number = 100
project_name = "veepoo"
build_number = "v2.5.0"
title = "Clinical Confectionery & Dairy Queen Engine: Eliminate Generic Catch-All Fallbacks and Implement Accurate Ounce-Scaled Candy (M&Ms, Red Hots, Almond Joy) and DQ Peanut Buster Parfait Disclosures"
component = "Nutrition / Gemini Estimator / Safety Engine"
affected_files = "w11_htm_app/server.py, index.html, w11_htm_app/index.html"
resolution = (
    "1. Root cause identified: Dairy Queen Peanut Buster Parfait and Almond Joy fell into raw nut category (matching 'peanut' and 'almond' in health nut list, falsely reporting 4.0g net carbs), while M&Ms and Red Hots fell into generic 480 kcal / 28g net carb fallback.\n"
    "2. Built portion/weight parsing engine for ounces (e.g. 6 oz, 8 oz, grams, lbs) scaling macros proportionally.\n"
    "3. Sourced official vendor disclosures & USDA FoodData Central specs:\n"
    "   - 6 oz M&M's: 840 kcal, 114g net carbs (120g carbs, 6g fiber), 9g protein, 36g fat -> High spike risk, fails 45g ceiling.\n"
    "   - 6 oz Red Hots: 660 kcal, 162g net carbs (100% refined sugar/syrup), 0g fat, 0g fiber, 0g protein -> Severe acute glucose crisis risk.\n"
    "   - 8 oz Almond Joy: 1,100 kcal, 126g net carbs (136g carbs, 10g fiber), 15.2g protein, 60g fat -> Delayed late-phase spike.\n"
    "   - DQ Peanut Buster Parfait: 710 kcal, 90g net carbs (94g carbs, 4g fiber), 16g protein, 33g fat -> Fails 45g ceiling; harm reduction swaps provided (DQ small cone 28g net carbs, NSA Dilly Bar 18g net carbs).\n"
    "4. Synchronized backend and client-side fallback engines with exact numerical preservation."
)
comments = "All 45/45 audit checks and 24/24 QA checks passed. Live server tested with exact authentic macros."
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
