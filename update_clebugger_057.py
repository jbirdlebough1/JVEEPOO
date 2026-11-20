import sqlite3
import json
import datetime
import os

db_path = r'D:\CLEBUGGER\bugs.db'
conn = sqlite3.connect(db_path)
cur = conn.cursor()

now_str = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

cur.execute('SELECT COUNT(*) FROM bugs WHERE bug_code = "CLE-057"')
if cur.fetchone()[0] == 0:
    cur.execute('''
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', (
        'VEEPOO_LCARS_5010',
        57,
        'CLE-057',
        'V0.16.16.0',
        'Slide 25 Visual Alignment & Physiological Nadir Architecture: 24h Glucose Curve and Dawn vs Somogyi Analysis',
        'RESOLVED',
        'Presentation / Metabolic Telemetry',
        'all_slides_data.json, audio/slide_25.mp3, build_presentation.py, preview.html, slides/slide_25.html, TRANSCRIPT.md',
        185,
        'Corrected Slide 25 screenshot from erroneous Cardio console to authentic 24-hour continuous glucose curve (images/lcars_chart_glucose.png). Designed and embedded comprehensive right-panel SVG trajectory diagram and clinical educational callout defining the physiological Nadir (lowest metabolic trough, 03:00-04:30 AM). Detailed the vital clinical distinction between true Dawn Phenomenon (normal nadir + morning cortisol surge) and Somogyi rebound (nocturnal crash <60 mg/dL + emergency adrenaline dump), showing why single morning fingersticks risk lethal overtreatment. Re-rendered slide_25.mp3 with Australian female voice.',
        'Directly addresses user instruction to fix Slide 25 screenshot and add in-depth Nadir explanation.',
        now_str, now_str, now_str
    ))

conn.commit()

# Export audit
cur.execute('SELECT bug_code, title, status, build_number, component, affected_files, resolution, created_at, resolved_at FROM bugs ORDER BY bug_number ASC')
all_bugs = []
for row in cur.fetchall():
    all_bugs.append({
        'bug_code': row[0],
        'title': row[1],
        'status': row[2],
        'build_number': row[3],
        'component': row[4],
        'affected_files': row[5],
        'resolution': row[6],
        'created_at': row[7],
        'resolved_at': row[8]
    })

conn.close()

docs_dir = r'D:\veepoo\docs'
os.makedirs(docs_dir, exist_ok=True)
audit_path = os.path.join(docs_dir, 'cle_bugs_audit.json')
with open(audit_path, 'w', encoding='utf-8') as f:
    json.dump(all_bugs, f, indent=2)

print(f"CLEBUGGER and {audit_path} updated with CLE-057!")
