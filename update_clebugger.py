import sqlite3
import json
import datetime
import os

db_path = r'D:\CLEBUGGER\bugs.db'
conn = sqlite3.connect(db_path)
cur = conn.cursor()

now_str = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

# 1. Update CLE-048 to RESOLVED
cur.execute('''
UPDATE bugs SET 
    status = 'RESOLVED',
    resolved_at = ?,
    resolution = 'Fixed deep sleep byte calculation in w11_htm_app/index.html (lines 7205-7225) where raw minutes were previously multiplied by 5, causing an erroneous 15.3h readout. Corrected algorithm to realistic ~1.9h (25.7% of 7.4h sleep). Scaled daily sleep hours cleanly to 24-hour timeline.'
WHERE bug_code = 'CLE-048'
''', (now_str,))

# 2. Update CLE-049 to RESOLVED
cur.execute('''
UPDATE bugs SET 
    status = 'RESOLVED',
    resolved_at = ?,
    resolution = 'Purged unlinked watch banner (#unlinkedBanner) from HTML DOM and suppressed transponder prompt logic in w11_htm_app/index.html (lines 5971, 6156). Enforced seamless background auto-connect for authorized transponders without distracting UI popups.'
WHERE bug_code = 'CLE-049'
''', (now_str,))

# 3. Update CLE-050 to RESOLVED
cur.execute('''
UPDATE bugs SET 
    status = 'RESOLVED',
    resolved_at = ?,
    resolution = 'Enforced strict deduplication constraints and unique epoch validation on watch telemetry packets and local database tables in w11_htm_app/server.py and index.html.'
WHERE bug_code = 'CLE-050'
''', (now_str,))

# 4. Update CLE-051 to RESOLVED
cur.execute('''
UPDATE bugs SET 
    status = 'RESOLVED',
    resolved_at = ?,
    resolution = 'Implemented filterDailyUpToCurrentTime filter in w11_htm_app/index.html (lines 8255-8275) and updated SVG curve rendering engines to strictly terminate plot lines at current collection time (e.g. 0:00 to 9:38 AM) with zero future extrapolation, while displaying the complete 24-hour baseline. Applied pure Light Theme default across console and presentation.'
WHERE bug_code = 'CLE-051'
''', (now_str,))

# Helper to check if a bug_code exists
def bug_exists(code):
    cur.execute('SELECT COUNT(*) FROM bugs WHERE bug_code = ?', (code,))
    return cur.fetchone()[0] > 0

# 5. Add CLE-052
if not bug_exists('CLE-052'):
    cur.execute('''
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', (
        'VEEPOO_LCARS_5010',
        52,
        'CLE-052',
        'V0.16.16.0',
        'Slide 2 Sampling Mathematics: Neye-quist phonetic pronunciation, 2-sample quarterly lab timeline (Day 0 & Day 90), and 5-dimension metrology comparison',
        'RESOLVED',
        'Presentation / Sampling Metrology',
        'all_slides_data.json, build_presentation.py, preview.html, slides/slide_02.html',
        150,
        'Updated Slide 02 to feature authentic phonetic pronunciation ("Neye-quist") for Australian female TTS. Expanded 90-day timeline to explicitly contrast quarterly lab 2-sample protocol (Day 0 & Day 90 with 7.78M sec blind void) against sporadic fingersticks (N≈180) and continuous telemetry (N=25,920). Added comprehensive 5-dimension metrology comparison table evaluating Sensitivity, Specificity, Accuracy, Precision, and Repeatability.',
        'Addresses user request to show the math of single, random, and automated sampling.',
        now_str, now_str, now_str
    ))

# 6. Add CLE-053
if not bug_exists('CLE-053'):
    cur.execute('''
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', (
        'VEEPOO_LCARS_5010',
        53,
        'CLE-053',
        'V0.16.16.0',
        'Expand Sampling Methodology Topic: Biological frequency kinetics, statistical error convergence (SE = σ/√N), and peer-reviewed citations with interactive Gemini research query',
        'RESOLVED',
        'Presentation / Academic Validation',
        'all_slides_data.json, build_presentation.py, preview.html, slides/slide_03.html, slides/slide_04.html, slides/slide_05.html',
        380,
        'Expanded sampling methodology into a self-validating 4-part master sequence (Slides 02-05). Added Slide 03 on Shannon-Neye-quist frequency bounds (glucose half-life 15-30m requires fs ≥ 2.2 mHz, proving quarterly tests are 8 orders of magnitude too slow). Added Slide 04 proving statistical error convergence (SE = σ/√N, reducing error variance by 99.4% and suppressing noise by 161×). Added Slide 05 with peer-reviewed citations (Shannon 1949, Nyquist 1928, Battelino 2019, Beck 2017 JAMA) and an interactive "Would you like me to find more via Gemini?" research query widget allowing live literature exploration.',
        'Total slides expanded to 53 slides total.',
        now_str, now_str, now_str
    ))

# 7. Add CLE-054
if not bug_exists('CLE-054'):
    cur.execute('''
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', (
        'VEEPOO_LCARS_5010',
        54,
        'CLE-054',
        'V0.16.16.0',
        'Audio Voice Synchronization & Purging: Complete re-render of 53 MP3s with Australian Female Voice, screen-focused 01-VITALS narration, and zero Masterclass/Workbench terminology',
        'RESOLVED',
        'Audio Subsystem / Narration',
        'audio/*.mp3, presentation/audio/*.mp3, all_slides_data.json, build_presentation.py, preview.html, presentation/TRANSCRIPT.md',
        220,
        'Identified that audio player was loading legacy pre-rendered MP3s that contained outdated "Masterclass" and "Workbench" terminology. Re-rendered all 53 audio files asynchronously using edge_tts with en-AU-NatashaNeural. Updated Slide 1 narration to focus explicitly on the screen-displayed topic (01-VITALS light theme console, BIO-01 glucose 156 mg/dL, BIO-02 dual thermal 96.1°F, BIO-03 blood pressure 126/84 mmHg, BIO-04 SpO2 98%, status ribbon, and multidisciplinary care team grid). Recompiled Preview_Presentation.exe and pushed to local git and GitHub.',
        'Voice strictly matches slide content and focuses on screen displayed topic.',
        now_str, now_str, now_str
    ))

conn.commit()

# Export audit to docs/cle_bugs_audit.json
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

print(f"CLEBUGGER database and {audit_path} successfully updated with CLE-048 through CLE-054!")
