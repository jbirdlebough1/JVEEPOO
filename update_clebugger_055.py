import sqlite3
import json
import datetime
import os

db_path = r'D:\CLEBUGGER\bugs.db'
conn = sqlite3.connect(db_path)
cur = conn.cursor()

now_str = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

cur.execute('SELECT COUNT(*) FROM bugs WHERE bug_code = "CLE-055"')
if cur.fetchone()[0] == 0:
    cur.execute('''
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', (
        'VEEPOO_LCARS_5010',
        55,
        'CLE-055',
        'V0.16.16.0',
        'Purge Artificial "Slide #" Numbering Comments from Voice Narration Across Deck',
        'RESOLVED',
        'Audio Subsystem / Narration Script',
        'all_slides_data.json, audio/*.mp3, presentation/audio/*.mp3, build_presentation.py, preview.html, TRANSCRIPT.md',
        190,
        'Audited all 53 slides and identified 29 narrations containing artificial "Slide #" announcements (e.g. "Slide 7 establishes...", "In Slide 8...", "Slide thirty-one focuses on..."), which caused numbering desynchronization and distracted from the clinical flow. Completely replaced all 29 instances with seamless, screen-focused physiological transitions (e.g. "Our clinical safe harbor standardizes on...", "Displayed on screen is...", "Next, we examine..."). Re-rendered all 29 MP3 audio files with Australian female voice (en-AU-NatashaNeural) and regenerated presentation player and W11 executable.',
        'Zero "Slide #" comments remain in the entire voice narration suite.',
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

print(f"CLEBUGGER and {audit_path} updated with CLE-055!")
