import sqlite3
import json

db_path = r"D:\CLEBUGGER\bugs.db"
conn = sqlite3.connect(db_path)
c = conn.cursor()

bug_code = "CLE-074"
bug_number = 74
project_name = "VEEPOO_5010_LCARS"
build_number = "5010-W11-BUILD-101"
title = "Scrubbed HBand Label, Added Bounded Scroll Window for Raw Data (Daily/Weekly/Monthly/Yearly), and Telemetry Charging Gaps (Zero for Unrecorded Data)"
status = "RESOLVED"
component = "LCARS Clinical Telemetry Onboarding Toolkit & Presentation"
affected_files = "w11_htm_app/index.html, index.html, all_slides_data.json, build_presentation.py"
bug_loc = 1120
resolution = (
    "1. Scrubbed HBand Label Parity: Fully eliminated user-facing 'Hband', 'H-Band', and '(HBAND)' labels across all application views, navigation headers, capability matrices, FAQs, and all 53 slides in all_slides_data.json, replacing them with standardized 'OEM Mobile App', 'Clinical Spec', or 'Poincaré'.\n"
    "2. Bounded Scroll Viewport for Raw Telemetry Logs (#rawTelemetryScrollContainer):\n"
    "   - Enclosed the raw sensor log table inside a compact 320px scroll window (max-height: 320px; overflow-y: auto) with a sticky header to keep viewport size down.\n"
    "   - Added multi-timeframe navigation buttons: DAILY (24H, 14 hourly entries), WEEKLY (7D, 7 daily audits), MONTHLY (30D, 30 daily audits), and YEARLY (12M, 12 longitudinal entries).\n"
    "3. Telemetry Gaps & Zero for Unrecorded Data (Charging Gap Parity):\n"
    "   - Show only real recorded data; unrecorded or missing time intervals display explicitly as 0 (0 mg/dL, 0 steps, --).\n"
    "   - Preserved real-world off-wrist watch charging gaps (~1.2h, 10:30 to 12:00):\n"
    "     * 8-Lane Synchronized Chart (#svgTallMaster): Glucose, BP, HR, SpO2, and Temp curves break across the gap into discontinuous segments with a spanning '⚡ WATCH CHARGING (0 DATA)' indicator.\n"
    "     * Scrubber HUD: Displays '0 mg/dL (Charging)' and '0 steps • Watch on Charger (~1.2h)' when hovering over the charging slot.\n"
    "     * Bay 4 Activity Bins: Slots 21-23 display 0 steps with amber '⚡' charging indicator.\n"
    "     * 10 Clinical SVG Charts: Curves break discontinuously across charging gaps without artificial interpolation.\n"
    "4. Verified in Headless Chrome CDP, rebuilt all 53 slides and Preview_Presentation.exe."
)
comments = "Verified across Chrome CDP, rebuilt Preview_Presentation.exe, synced with local origin and GitHub remotes."

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
