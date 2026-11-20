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
    25,
    'CLE-025',
    'v1.0.25',
    'Eliminate pink backgrounds across all cards and minimize pitch-black backgrounds for high-contrast senior-friendly ergonomics',
    'RESOLVED',
    'UI Ergonomics / Accessibility',
    'w11_htm_app/index.html',
    110,
    'Removed all pink backgrounds (rgba(230,57,70,0.15) and rgba(204,153,255)) from all clinical notice cards, stat cards, and intake containers. Replaced with solid slate navy (#162238 / #182538 / #1a283e) and high-contrast amber borders (#f59e0b) with bold white and gold text. Minimized pitch black backgrounds across the body and bays to reduce glare and visual fatigue for older users.',
    'Validated syntax with node --check (0 errors) and verified live HTTP 200 on http://localhost:8899/.',
    now,
    now,
    now
))

conn.commit()
print(f"Recorded CLE-025 successfully. Row ID: {cursor.lastrowid}")
conn.close()
