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
    91,
    'CLE-091',
    'V0.16.16.0',
    'Clinical Operations & Doctor Guide Update: Exercise vs. Passive Sampling Modes, SPI Flash Memory Architecture, Capacity, and Overwrite Retention Horizon',
    'RESOLVED',
    'Clinical Operations / Doctor Guide / Hardware Memory Spec',
    'index.html, w11_htm_app/index.html',
    210,
    'Embedded Section 04 into 12-OPERATIONS documenting: 1) Exercise mode 1Hz continuous PPG vs passive 5-10 min duty cycling; 2) SPI NOR Flash memory architecture (64Mbit/128Mbit Winbond IC, 2.5-10.5MB circular buffer, 10.4 KB/day footprint); 3) FIFO overwrite horizon (7-14 days raw epochs, 30 days daily aggregates); 4) Updated 01-FULL SCAN cross-references.',
    'Verified via full audit suite (45/45 pass) and accumulated QA (24/24 pass). Exact tag balance preserved (1677/1677 div tags).',
    now,
    now,
    now
))

conn.commit()
print("CLE-091 logged successfully!")
