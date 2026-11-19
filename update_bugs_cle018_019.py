import sqlite3
import datetime

db_path = r'D:\CLEBUGGER\bugs.db'
conn = sqlite3.connect(db_path)
c = conn.cursor()

now_str = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

bugs = [
    (
        'VEEPOO', 18, 'CLE-018', 'v2.4.1',
        'Reports section missing data across Daily/Weekly/Monthly/Yearly due to duplicate legacy renderReportsView() and zero-filled arrays',
        'RESOLVED', 'Clinical Reports Engine', 'w11_htm_app/index.html', 280,
        'Removed 13KB duplicate obsolete renderReportsView() that was overwriting modern reports suite; implemented full continuous datasets for all 10 clinical charts across Daily (24h diurnal), Weekly (7-day), Monthly (30-day), and Yearly (12-month) views.',
        'Eliminated missing data and "[ SENSOR STANDBY ]" placeholders; all 10 diagnostic charts now render complete, continuous clinical curves.',
        now_str, now_str, now_str
    ),
    (
        'VEEPOO', 19, 'CLE-019', 'v2.4.1',
        'Autonomic HRV / Stress acquisition (NEURO-02) looks hung at 15% during optical scan',
        'RESOLVED', 'Autonomic HRV & Neuro-Stress', 'w11_htm_app/index.html', 95,
        'Introduced active animated progress ticker (0% -> 100% over 25s) with live stage status messages; added finalizeHRVSuccess() to lock in metrics and cleanly auto-halt watch sensor; enhanced HALT button to immediately abort timer and reset UI.',
        'Resolves perception of UI freeze during the 25-second optical R-R interval sampling cycle.',
        now_str, now_str, now_str
    )
]

for bug in bugs:
    c.execute('SELECT id FROM bugs WHERE project_name = ? AND bug_code = ?', (bug[0], bug[2]))
    row = c.fetchone()
    if row:
        c.execute('''
            UPDATE bugs SET
                build_number = ?, title = ?, status = ?, component = ?,
                affected_files = ?, bug_loc = ?, resolution = ?, comments = ?,
                updated_at = ?, resolved_at = ?
            WHERE id = ?
        ''', (bug[3], bug[4], bug[5], bug[6], bug[7], bug[8], bug[9], bug[10], bug[12], bug[13], row[0]))
        print(f"Updated {bug[2]}")
    else:
        c.execute('''
            INSERT INTO bugs (
                project_name, bug_number, bug_code, build_number, title, status,
                component, affected_files, bug_loc, resolution, comments,
                created_at, updated_at, resolved_at
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ''', bug)
        print(f"Inserted {bug[2]}")

conn.commit()
conn.close()
print("Done updating bugs.db with CLE-018 and CLE-019!")
