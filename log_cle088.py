import sqlite3
import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()

now = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

c.execute('''
INSERT INTO bugs (
    project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at
) VALUES (
    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
)
''', (
    'VEEPOO',
    88,
    'CLE-088',
    'V0.16.16.0',
    'Elimination of Duplicate Vitals Screen, Removal of Redundant Full Scan Controls (Skip & Run Another), Sequential Sidebar Renumbering (01-14), and Telemetry DOM Bridge',
    'RESOLVED',
    'LCARS UI / Full Scan / Vitals Consolidation',
    'index.html, w11_htm_app/index.html',
    520,
    'De-duplicated 02-VITALS tab and replaced with invisible vitalsDomBridge to preserve legacy GATT DOM element bindings; purged skip-to-next and run-another-scan buttons; re-indexed sidebar pills 01 through 14; guarded all sound function calls.',
    'Verified via CDP live render: pageFullScan flex display 745px height, 02-vitals removed, 0 syntax errors, 1660/1660 balanced div tags.',
    now,
    now,
    now
))

conn.commit()
print("CLE-088 logged successfully!")
