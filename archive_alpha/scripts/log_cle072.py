import sqlite3
import json

db_path = r"D:\CLEBUGGER\bugs.db"
conn = sqlite3.connect(db_path)
c = conn.cursor()

bug_code = "CLE-072"
bug_number = 72
project_name = "VEEPOO_5010_LCARS"
build_number = "5010-W11-BUILD-99"
title = "Diabetic Goal Setup (Tab 13), On-Demand % Goals Met Audit (Tab 04), Gemini Meal Builder BOM & Common Packages (Tab 09), 100% White Chart Backgrounds, and FITAOS Watch Face Research"
status = "RESOLVED"
component = "LCARS Clinical Telemetry Web Console & Presentation"
affected_files = "w11_htm_app/index.html, index.html, all_slides_data.json, build_presentation.py"
bug_loc = 1250
resolution = (
    "1. Clinical Diabetic Goal Page (Tab 13: #pageGoals): Implemented complete doctor-patient collaborative setup for A1C anchor (8.3%), fasting range (80-130), post-meal max (180), TIR (≥75%), max net carbs (45g), daily step volume (8,000), and GLUT4 walks with local storage persistence.\n"
    "2. On-Demand % of Goals Met Audit (Tab 04: #boxGoalComplianceAudit): Added real-time compliance audit engine with radial progress gauge (85.4% Goals Met, Grade A), 7 clinical domain breakdown bars, and Epic EHR export.\n"
    "3. Gemini Diabetic Meal Builder & Common Packages (Tab 09: #pageDietTrades): Added rapid pull-down selection for recurring meals with unique IDs ([MEAL-01] Mon-Sat Oats & Berries & Turkey Sausage, [MEAL-02] Sunday Eggs, Turkey Sausage & Toast) for zero-keystroke 1-click timeline logging. Added Recipe Bill of Materials (BOM) with itemized department aisles, costs (~$2.85/meal), and local grocery store sourcing engine (Walmart, Kroger, Trader Joe's, Safeway, Whole Foods, Costco, H-E-B, Target).\n"
    "4. 100% Pure White Backgrounds on All Charts: Enforced #ffffff backgrounds across tall multi-lane chart lanes, Lorenz Poincaré scatter plot, step-glucose regression correlator, timeline curves, and all 53 presentation slides.\n"
    "5. 100% Build-to-Presentation Coverage Audit: Verified all 53 slides are fully realized in the functional build.\n"
    "6. FITAOS / Veepoo Watch Face Architecture & Real-Time Glucose Complication Research: Completed reverse-engineering analysis of Veepoo SDK and binary OTA protocol."
)
comments = "Verified across Chrome CDP and rebuilt Preview_Presentation.exe. Synced with local and GitHub remotes."

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
