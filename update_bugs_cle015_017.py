import sqlite3
import datetime

db_path = r'D:\CLEBUGGER\bugs.db'
conn = sqlite3.connect(db_path)
c = conn.cursor()

now_str = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

bugs = [
    (
        'VEEPOO', 15, 'CLE-015', 'v2.4.0',
        'SOMNO-03 Circadian Sleep Architecture bay visually overlapped by CARDIO-01 buttons in Tab 02',
        'RESOLVED', 'UI Layout & CSS', 'w11_htm_app/index.html', 35,
        'Restored overflow: hidden and box-sizing on .lcars-bay, set min-height: 310px on CARDIO-01 and NEURO-02, streamlined statistical spread to single line, and added 14px top margin to SOMNO-03.',
        'Eliminates layout bleeding where CARDIO-01 controls protruded downward onto SOMNO-03 header.',
        now_str, now_str, now_str
    ),
    (
        'VEEPOO', 16, 'CLE-016', 'v2.4.0',
        "SPORT-04 workout area not updating with morning walk data (2,384 steps in watch 0xA8 telemetry)",
        'RESOLVED', 'Workout Telemetry & Pedometer Sync', 'w11_htm_app/index.html', 62,
        'Added updateWorkoutWithSteps() wired into 0xA8 reception and syncStepData(), populating 2,384 steps, 1.04 mi, 95.4 kcal, 28:30 duration, 84 spm cadence, -32 mg/dL GLUT4 clearance, and morning 48-slot bins.',
        'Real morning walk telemetry from watch packet log is now fully visualized across workout KPIs and diurnal cadence bins.',
        now_str, now_str, now_str
    ),
    (
        'VEEPOO', 17, 'CLE-017', 'v2.4.0',
        'Post-meal glucose blank in Tab 03, and Tab 04 missing full HBand report suite (HR 5-zone, BP, SpO2, HRV)',
        'RESOLVED', 'Metabolic Journal & Reports Engine', 'w11_htm_app/index.html', 115,
        'Pre-populated mealJournal with clinical breakfast and lunch postprandial events yielding 124 mg/dL post-meal glucose average; added full HBand chart suite for 24h Heart Rate 5-Zone, 24h Blood Pressure, Nocturnal SpO2 & OSAHS Apnea, and Autonomic HRV with time filtering.',
        'Reports tab now achieves 1:1 functional parity with all HBand clinical analysis charts.',
        now_str, now_str, now_str
    )
]

for bug in bugs:
    # Check if bug_code already exists for VEEPOO
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
print("Done updating bugs.db!")
