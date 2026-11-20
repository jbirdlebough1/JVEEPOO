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
    24,
    'CLE-024',
    'v1.0.24',
    'Relabel Tab 03 to METABOLIC and eliminate low-contrast dim pink letters on pink/red cards by standardizing high-visibility LCARS blue text and borders',
    'RESOLVED',
    'UI Ergonomics & Contrast / Tab 03 METABOLIC',
    'w11_htm_app/index.html',
    95,
    'Relabeled sidebar navigation Tab 03 from 03-360° HEALTH to 03-METABOLIC. Replaced all dim pink titles (#ff6b6b), dim pink descriptions (#ffd0d0), and bold accents (#ff9999) on all cards, warning banners, and intake modules with crisp, high-visibility LCARS blue (#38b6ff) and ice blue (#cce6ff). Updated .stat-card-sub and comprehensive CSS contrast rules for all pink/lilac/red-tinted backgrounds.',
    'Validated syntax with node --check (0 errors) and verified live HTTP 200 on http://localhost:8899/.',
    now,
    now,
    now
))

conn.commit()
print(f"Recorded CLE-024 successfully. Row ID: {cursor.lastrowid}")
conn.close()
