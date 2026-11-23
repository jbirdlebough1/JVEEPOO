import sqlite3
import json

db_path = r"D:\CLEBUGGER\bugs.db"
conn = sqlite3.connect(db_path)
c = conn.cursor()

bug_code = "CLE-073"
bug_number = 73
project_name = "VEEPOO_5010_LCARS"
build_number = "5010-W11-BUILD-100"
title = "Automated USB Deduplicating Backup (01:00 AM), Mobile Cell Phone Photo Gateway with Gemini Estimation, Watch Biometric Sub-Reports Navigation Strip & 6 Clinical Modules, and White Background Parity"
status = "RESOLVED"
component = "LCARS Clinical Telemetry Onboarding Toolkit & Presentation"
affected_files = "w11_htm_app/index.html, index.html, w11_htm_app/server.py, w11_htm_app/mobile_upload.html, usb_backup_engine.py, all_slides_data.json, build_presentation.py"
bug_loc = 1420
resolution = (
    "1. Daily Automated Removable USB Backup with Block-Level Deduplication (Tab 05 & server.py & usb_backup_engine.py):\n"
    "   - Built production SHA-256 content-addressable storage pool (LCARS_BACKUP/pool/ and snapshots/) targeting removable USB drives.\n"
    "   - Uses atomic SQLite online backup API to prevent database corruption during live streaming.\n"
    "   - Configurable in Tab 05 (#pageSetup) with drive detection, deduplication ratio stats (100% dedup for unchanged blocks), manual trigger, and background 01:00 AM daemon scheduler.\n"
    "2. Mobile Cell Phone Photo Sync Gateway (mobile_upload.html & server.py & Tab 09 banner):\n"
    "   - Created mobile smartphone camera portal for snapping dining-out meal photos.\n"
    "   - When phone connects to home Wi-Fi and PC server is active, photos sync to /api/meal/upload where Gemini estimates net carbs, fiber, protein, and calories and inserts into telemetry.db meal_entries table.\n"
    "3. Clinical Watch Biometric Analysis Sub-Reports Navigation Strip & 6 Modules (Tab 04 #pageReports):\n"
    "   - Injected Sub-Reports Navigation Strip (#subReportBtnStrip) with 9 one-click smooth-scroll buttons.\n"
    "   - Injected 6 dedicated clinical biometric modules with pure white backgrounds (#ffffff):\n"
    "     * 01. Metabolism & GLUT4 Muscular Glucose Clearance (#boxSubMetabolism)\n"
    "     * 02. ECG Stress, Intervals & Autonomic Balance (#boxSubEcgStress)\n"
    "     * 03. MET (Metabolic Equivalent of Task) Energy Expenditure (#boxSubMet)\n"
    "     * 04. Blood Composition, Viscosity & Lipid Tonometry (#boxSubBloodComp)\n"
    "     * 05. Body Composition & Bioelectrical Impedance BIA (#boxSubBodyComp)\n"
    "     * 06. Breath, Respiration Rate & Nocturnal Mechanics 14 RPM (#boxSubBreath)\n"
    "4. 100% Pure White Background Parity & Presentation Rebuild:\n"
    "   - Verified rgb(255, 255, 255) backgrounds across all sub-reports via Chrome CDP.\n"
    "   - Rebuilt all 53 slides, preview.html, and Preview_Presentation.exe Windows 11 executable launcher.\n"
    "   - Verified 100% build-to-presentation feature parity."
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
