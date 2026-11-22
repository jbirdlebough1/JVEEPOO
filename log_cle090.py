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
    90,
    'CLE-090',
    'V0.16.16.0',
    'Cardio Previous Night Sleep Toggle Restoration, Nocturnal Vault Synchronizer, /api/sleep/records API, and Exercise Sampling Mode Clarification',
    'RESOLVED',
    'Cardio Console / Sleep Bay / Server Sleep API',
    'index.html, w11_htm_app/index.html, w11_htm_app/server.py',
    145,
    'Added dual-night selector buttons (Last Night / Previous Night) to Cardio Bay 3; created /api/sleep/records backend route in server.py; connected switchSleepView to query historical sleep records from SQLite and transmit Opcode 0xE0; updated subtitle dynamically to confirm day and total sleep hours; documented high-frequency 1Hz continuous PPG vs passive ambient exercise sampling dynamics.',
    'Verified via CDP live interaction: both Last Night and Previous Night toggle cleanly, load deep/light/REM/wake metrics, update dynamic subtitle with zero errors.',
    now,
    now,
    now
))

conn.commit()
print("CLE-090 logged successfully!")
