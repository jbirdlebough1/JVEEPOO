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
        58,
        "CLE-058",
        "v2.8.4",
        "Slide 26 Visual Alignment & SOMNO-03 Hypnogram: Delta Sleep vs Cortisol Suppression",
        "RESOLVED",
        "Presentation & Telemetry / Sleep Hypnogram",
        "all_slides_data.json, audio/slide_26.mp3, images/lcars_chart_sleep.png",
        "Replaced incorrect screenshot on Slide 26 with authentic SOMNO-03 sleep hypnogram (lcars_chart_sleep.png). Configured right panel with SVG sleep architecture diagram contrasting Deep Delta sleep cortisol suppression against fragmented hypoxia and morning fasting surges. Re-rendered slide_26.mp3 with Australian Female Voice.",
        now_iso,
        now_iso,
        now_iso
    ),
    (
        "CLE",
        59,
        "CLE-059",
        "v2.8.4",
        "Slide 27 Habit Correlation Scrolled View & Slide 28 Epic EHR Integration",
        "RESOLVED",
        "Presentation & Tab 04 Reports / Epic Bridge",
        "w11_htm_app/index.html, index.html, all_slides_data.json, audio/slide_27.mp3, audio/slide_28.mp3, images/lcars_slide27_habit_correlation.png, images/lcars_slide28_epic_gateway.png",
        "Restored accidentally truncated 230KB script section in index.html. Captured tall crop lcars_slide27_habit_correlation.png (1129x1641) enabling smooth viewport scrolling to reveal #boxExerciseMetabolicCorrelation (r = -0.79 inverse correlation, -42 mg/dL post-meal drop). Updated Slide 28 with lcars_slide28_epic_gateway.png highlighting toolbar 'PUSH TO DOCTOR\\'S EPIC' button and HL7 FHIR R4 clinical rationale. Re-rendered audio for Slides 27 and 28.",
        now_iso,
        now_iso,
        now_iso
    ),
    (
        "CLE",
        60,
        "CLE-060",
        "v2.8.4",
        "Slides 29 & 30: 360° Synchronized Tall Chart, 8 Stacked Lanes & Pinned Med/Food Markers",
        "RESOLVED",
        "Presentation & Tab 04 Reports / Tall Chart",
        "all_slides_data.json, audio/slide_29.mp3, audio/slide_30.mp3, images/lcars_slide29_tall_chart_stack.png",
        "Captured 1076x1232 tall cropped screenshot of the 360° synchronized tall chart. Configured Slide 29 and Slide 30 with monotonic scroll progression synchronized to Australian Female Voice. Explicitly discussed pinned '🥗 FOOD / MEAL' carb markers and '💊 MED CHANGE' milestones on Slide 29, and conducted a thorough top-to-bottom clinical audit of all 8 stacked lanes on Slide 30. Re-rendered slide_29.mp3 and slide_30.mp3.",
        now_iso,
        now_iso,
        now_iso
    ),
    (
        "CLE",
        61,
        "CLE-061",
        "v2.8.4",
        "Slides 31, 32 & 33: Vertical Scrubber HUD, Click-to-Pop Point Cards & Pinned Med Milestones",
        "RESOLVED",
        "Presentation & Tab 04 Reports / Interactive Audits",
        "all_slides_data.json, audio/slide_31.mp3, audio/slide_32.mp3, audio/slide_33.mp3, images/lcars_slide31_scrubber_hud.png, images/lcars_slide32_point_card_modal.png, images/lcars_slide33_med_change_milestone.png",
        "Captured tall cropped screenshots for Slide 31 (vertical scrubber line cross-secting all 8 lanes at 13:30 with HUD ribbon), Slide 32 (Click-to-Pop Point Card modal in context displaying 188 mg/dL, BP, SpO2, and GLUT4 drop), and Slide 33 (focused on the red dashed line and '💊 MED CHANGE' milestone marker with 30-day pre/post -24 mg/dL therapeutic proof). Re-rendered slide_31.mp3, slide_32.mp3, and slide_33.mp3 with Australian Female Voice.",
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
print(f"Logged CLE-058, CLE-059, CLE-060, CLE-061 and exported {len(audit_data)} bugs to {audit_json_path}!")
