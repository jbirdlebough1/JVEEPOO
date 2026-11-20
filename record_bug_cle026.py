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
    26,
    'CLE-026',
    'v1.0.26',
    'Build 40-slide presentation suite with Aussie voice narration, W11 launcher, update physician specification, and embed H-Band parity matrix into user docs',
    'RESOLVED',
    'Documentation & Presentation / Operations / H-Band Parity',
    'w11_htm_app/index.html, docs/generate_cmo_spec.py, presentation/*',
    340,
    'Built 40-slide presentation suite in D:\\veepoo\\presentation with auto-advancing Windows 11 player (preview.html), compiled native launcher (Preview_Presentation.exe), Australian voice narration engine, and complete TRANSCRIPT.md. Updated physician clinical specification (generate_cmo_spec.py, cmo_spec.html, and PDF) with 360-degree telemetry details, GLUT4 exercise kinetics, ADA trades, and H-Band comparison matrix. Embedded H-Band comparison matrix and FAQs into Tab 07 and Tab 08 of web application.',
    'Verified with Chrome headless PDF compilation (returncode 0), csc.exe compiler (returncode 0), and node --check on index.html (0 errors).',
    now,
    now,
    now
))

conn.commit()
print(f"Recorded CLE-026 successfully. Row ID: {cursor.lastrowid}")
conn.close()
