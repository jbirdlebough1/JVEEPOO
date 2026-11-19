import sqlite3
from datetime import datetime

conn = sqlite3.connect(r'D:\CLEBUGGER\bugs.db')
cursor = conn.cursor()

now = datetime.now().isoformat()
cursor.execute('''
    INSERT INTO bugs (
        project_name, bug_number, bug_code, build_number, title,
        status, component, affected_files, bug_loc, resolution, comments,
        created_at, updated_at, resolved_at
    ) VALUES (
        ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?,
        ?, ?, ?
    )
''', (
    'veepoo',
    23,
    'CLE-023',
    'v1.0.23',
    'Split active medication regimen into two discrete daily intake times (Morning suite + Dinner Metformin split) with individual edit/delete modal controls',
    'RESOLVED',
    '11-MEDICATION / Pharmacological Adherence',
    'w11_htm_app/index.html',
    140,
    'Restructured 01. Active Pharmacological Regimen to render two intake schedules (Intake 1 Morning with Breakfast, Intake 2 Dinner with Dinner Metformin 1,000 mg split dose). Provided take-all batch adherence buttons per intake slot, individual [EDIT], [DELETE], [TITRATE], and [TAKEN] buttons per medication, an LCARS modal (#modalEditMedication) for granular medication management, and normalization to preserve existing localStorage records.',
    'Verified via node --check with zero errors. Verified live HTTP 200 on http://localhost:8899/ with no-cache headers.',
    now,
    now,
    now
))

conn.commit()
print(f"Recorded CLE-023 successfully. Row ID: {cursor.lastrowid}")
conn.close()
