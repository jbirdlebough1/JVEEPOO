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
    89,
    'CLE-089',
    'V0.16.16.0',
    'Full Scan Pipeline Stage 2 Hang Fix, Universal Null-Safe Sensor Handlers, Dynamic Progress Percentages, and Clean Telemetry Bridge',
    'RESOLVED',
    'Full Scan Sequence / Temperature Acquisition / DOM Bridge',
    'index.html, w11_htm_app/index.html',
    180,
    'Resolved uncaught TypeError in startTempDetection caused by missing barTempCore proxy span; added full null-safe accessors across all 6 sensor drivers; wrapped hardware start/stop calls in try/catch to eliminate hangs; added barTempCore, barTempSkin, txtMAP, and modal proxies to vitalsDomBridge; cleaned up redundant SCANNING (Scanning) label and restored dynamic stage percentage tickers.',
    'Verified via headless Chrome CDP: 0 errors during full 6-stage sequential scan, instant transition across Stage 1 through Stage 6, 45/45 audit tests passed, 24/24 QA tests passed.',
    now,
    now,
    now
))

conn.commit()
print("CLE-089 logged successfully!")
