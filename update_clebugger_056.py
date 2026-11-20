import sqlite3
import json
import datetime
import os

db_path = r'D:\CLEBUGGER\bugs.db'
conn = sqlite3.connect(db_path)
cur = conn.cursor()

now_str = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

cur.execute('SELECT COUNT(*) FROM bugs WHERE bug_code = "CLE-056"')
if cur.fetchone()[0] == 0:
    cur.execute('''
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', (
        'VEEPOO_LCARS_5010',
        56,
        'CLE-056',
        'V0.16.16.0',
        'Core Biosensor Array Visual Diagrams & Physiological Ray-Tracing (Slides 14-18)',
        'RESOLVED',
        'Presentation / Hardware Biosensing Architecture',
        'all_slides_data.json, audio/slide_14-18.mp3, build_presentation.py, preview.html, slides/slide_14-18.html, TRANSCRIPT.md',
        340,
        'Embedded rich SVG vector diagrams into the right panel of Slides 14 through 18 to visually explain the physical biosensors: (1) Slide 14: Multi-wavelength (525nm/940nm) optical ray-tracing through epidermis, capillary bed, and Beer-Lambert glucose absorption; (2) Slide 15: Dual-thermistor heat-flux vector architecture and 24h circadian thermal curve with 4:30 AM nadir (96.0°F); (3) Slide 16: PWTT dual-channel electro-optical timing (ECG R-peak to wrist PPG arrival) and Moens-Korteweg arterial pressure wave; (4) Slide 17: Dual-wavelength (660nm red vs 940nm IR) hemoglobin absorption spectra and sleep apnea 86% desaturation waveform; (5) Slide 18: Beat-to-beat R-R tachogram and Poincaré Lorenz scatter geometry (SD1 vagal tone vs SD2 sympathetic load). Re-rendered synchronized Australian female audio tracks matching diagrams directly.',
        'Directly addresses user request for sensor pictures and diagrams starting at Slide 14+.',
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

print(f"CLEBUGGER and {audit_path} updated with CLE-056!")
