import sqlite3
import json
from datetime import datetime

db_path = r'D:\CLEBUGGER\bugs.db'
audit_json_path = r'D:\veepoo\docs\cle_bugs_audit.json'

conn = sqlite3.connect(db_path)
cursor = conn.cursor()

now_iso = datetime.now().isoformat()

bugs = [
    (
        "CLE",
        62,
        "CLE-062",
        "v2.8.5",
        "Slides 35, 37, 38, 40, 41: Mathematical Consensus & Detailed Clinical Discussions",
        "RESOLVED",
        "Presentation / Clinical Metrology",
        "all_slides_data.json, slides/slide_35.html, slides/slide_37.html, slides/slide_38.html, slides/slide_40.html, slides/slide_41.html",
        "Added Nathan et al. ADAG consensus formula (eA1C = (MeanG + 46.7)/28.7) and sensitivity derivative to Slide 35; added Bramwell-Hill PWTT tonometry and nocturnal dipping stratification to Slide 37; added dual-wavelength 660/940nm ratio-of-ratios and apnea-cortisol glycemic surge to Slide 38; added SDNN, RMSSD, and Poincaré SD1/SD2 geometry to Slide 40; added polysomnographic multi-sensor hypnogram staging to Slide 41.",
        now_iso,
        now_iso,
        now_iso
    ),
    (
        "CLE",
        63,
        "CLE-063",
        "v2.8.5",
        "Tab 11 Medication Deck Alignment & Progressive Viewport Scrolling (Slides 43, 44, 45)",
        "RESOLVED",
        "Presentation & Tab 11 Medication",
        "all_slides_data.json, images/lcars_slide43_medication_tall.png, images/lcars_slide44_medication_schedules.png, images/lcars_slide45_titration_screen.png, audio/slide_43.mp3, audio/slide_44.mp3, audio/slide_45.mp3",
        "Captured 1144x3277 tall crop of Tab 11 for Slide 43 with monotonic top-to-bottom scroll progression; captured 1106x1921 focused crop for Slide 44 with targeted scrolling to Morning Suite and Evening Split cards; captured 1106x477 titration logger console for Slide 45. Re-rendered audio with El-CARS phonetic pronunciation.",
        now_iso,
        now_iso,
        now_iso
    ),
    (
        "CLE",
        64,
        "CLE-064",
        "v2.8.5",
        "Tab 12 Sequential Full Scan 6-Stage Pipeline Flow Chart & Visual Animation (Slide 46)",
        "RESOLVED",
        "Presentation & Tab 12 Full Scan",
        "all_slides_data.json, images/lcars_slide46_fullscan_active.png, audio/slide_46.mp3, slides/slide_46.html",
        "Captured active 6-stage full scan sequence screenshot; built a comprehensive SVG flowchart in the right pane detailing the 120-second non-interfering diagnostic sequence (Glucose -> Temp -> BP -> SpO2 -> Pulse -> HRV -> Complete Audit Report). Re-rendered slide_46.mp3.",
        now_iso,
        now_iso,
        now_iso
    ),
    (
        "CLE",
        65,
        "CLE-065",
        "v2.8.5",
        "Multidisciplinary Spoke-and-Wheel Collaboration Diagram, Doctor Consultation Scroll & Math (Slides 49, 51, 53)",
        "RESOLVED",
        "Presentation & Open Source / Collaboration",
        "all_slides_data.json, images/lcars_slide51_doctor_consultation_view.png, audio/slide_51.mp3, audio/slide_53.mp3, slides/slide_49.html, slides/slide_51.html, slides/slide_53.html",
        "Added in-clinic lab venous calibration offset and GLUT4 kinetics to Slide 49; captured 1129x5706 tall doctor consultation review view for Slide 51 with smooth scroll to YTD summary and Epic push; embedded full multidisciplinary spoke-and-wheel collaboration diagram below resources on Slide 53 (Patient in center, 4 specialist doctors, 5010 watch, consolidated information sharing); standardized El-CARS pronunciation across all audio tracks.",
        now_iso,
        now_iso,
        now_iso
    )
]

for bug in bugs:
    cursor.execute("""
        INSERT OR REPLACE INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, resolution, created_at, updated_at, resolved_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """, bug)

conn.commit()

# Export to audit JSON
cursor.execute("SELECT bug_code, title, status, build_number, component, affected_files, resolution, created_at, resolved_at FROM bugs ORDER BY bug_number ASC")
rows = cursor.fetchall()
keys = ["bug_code", "title", "status", "build_number", "component", "affected_files", "resolution", "created_at", "resolved_at"]
audit_data = [dict(zip(keys, r)) for r in rows]

with open(audit_json_path, 'w', encoding='utf-8') as f:
    json.dump(audit_data, f, indent=2, ensure_ascii=False)

conn.close()
print(f"Logged CLE-062, CLE-063, CLE-064, CLE-065 and exported {len(audit_data)} bugs to {audit_json_path}!")
