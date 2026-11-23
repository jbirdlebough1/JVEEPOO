import sqlite3
import json

db_path = r"D:\CLEBUGGER\bugs.db"
conn = sqlite3.connect(db_path)
c = conn.cursor()

bug_code = "CLE-075"
bug_number = 75
project_name = "VEEPOO_5010_LCARS"
build_number = "5010-W11-BUILD-102"
title = "Removed Broken '00. Smart Notifier' Card and Replaced 'Copilot' with 'Coach' Across App and Presentation"
status = "RESOLVED"
component = "LCARS Clinical Telemetry Onboarding Toolkit & Presentation"
affected_files = "w11_htm_app/index.html, index.html, audit_53_slides_coverage.py, docs/presentation_build_coverage_audit.json, build_presentation.py"
bug_loc = 1040
resolution = (
    "1. Removed Broken '00. Smart Notifier' Card (Tab 09):\n"
    "   - Eliminated the broken legacy card ('00. SMART GLUCOSE-DRIVEN SNACK & MEAL NOTIFIER') where a full-width .lcars-pill button squished text into a 25px vertical column and triggered a static dummy text dump.\n"
    "   - Placed the fully functional '🥗 INTERACTIVE DIABETIC MEAL BUILDER & GEMINI NUTRITION COACH' front-and-center at the top of Tab 09 with Common Packages (MEAL-01 / MEAL-02), Recipe BOM, Local Store Search, and Wi-Fi cell photo upload gateway.\n"
    "2. Trademark Protection Parity ('Copilot' -> 'Coach'):\n"
    "   - Completely eliminated all occurrences of 'Copilot', 'COPILOT', and 'copilot' across the app codebase, scripts, and documentation.\n"
    "   - Replaced with Google-aligned clinical term 'Coach' / 'COACH' (e.g. 'GEMINI NUTRITION COACH', 'GEMINI COACH ACTIVE').\n"
    "3. Verified via Headless Chrome CDP, rebuilt all 53 slides and Preview_Presentation.exe, synced with local origin and GitHub remotes."
)
comments = "Verified in Headless Chrome CDP, rebuilt Preview_Presentation.exe, synced with local origin and GitHub remotes."

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
