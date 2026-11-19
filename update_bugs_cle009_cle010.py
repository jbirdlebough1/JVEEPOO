import sqlite3
import datetime

db_path = r'D:\CLEBUGGER\bugs.db'
conn = sqlite3.connect(db_path)
c = conn.cursor()

# 1. Update CLE-009 to RESOLVED
c.execute("""
UPDATE bugs 
SET status = 'RESOLVED',
    resolution = 'Identified smartwatch firmware lacks BLE flash readback/dump opcode. Loaded and verified 94 official Shape 4 stock dial binaries in stock_dials/shape4_240x280 with verified CRC-16. Implemented live 0xC7/0x50 hardware slot auditor, Slot 2 switch controller, and Slot 3 overwrite experiment via /api/dial/style2 and 0xC7/0x50 protocol.',
    updated_at = CURRENT_TIMESTAMP,
    resolved_at = CURRENT_TIMESTAMP
WHERE project_name = 'VEEPOO' AND bug_code = 'CLE-009'
""")

# 2. Insert or replace CLE-010 (Measuring keep still lockout)
c.execute("""
INSERT OR REPLACE INTO bugs (
    project_name, bug_number, bug_code, build_number, title, status, component,
    affected_files, bug_loc, resolution, comments, updated_at, resolved_at
) VALUES (
    'VEEPOO', 10, 'CLE-010', 'v1.0.5',
    'Watch locked in \"Measuring keep still\" due to unhalted optical PPG SpO2/HRV scans',
    'RESOLVED', 'SENSOR_AUTOHALT', 'w11_htm_app/index.html', 45,
    'Added automatic sensor halt dispatch (0x80 0x02 0x02 for SpO2, 0x81 0x02 for HRV) upon measurement completion. Added proactive sensor stop dispatch before executing watch face transitions to prevent display lockouts.',
    'Continuous background SpO2 streaming (80 01 00 61 00 64) kept watch hardware in active measuring state, causing screen to display Measuring keep still and rejecting screen style commands.',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
)
""")

conn.commit()

# Print current bugs
c.execute("SELECT bug_code, status, title FROM bugs WHERE project_name = 'VEEPOO' ORDER BY bug_number")
for row in c.fetchall():
    print(row)

conn.close()
print("bugs.db updated successfully.")
