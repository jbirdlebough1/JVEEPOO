import sqlite3
import datetime

conn = sqlite3.connect(r"D:\CLEBUGGER\bugs.db")
cur = conn.cursor()

cur.execute("SELECT MAX(bug_number) FROM bugs")
row = cur.fetchone()
max_num = row[0] if row and row[0] else 78
next_num = max_num + 1
bug_code = f"CLE-{next_num:03d}"

title = "Universal Step Telemetry Propagation, Data Log TX Blue Theme Parity, Zero-Synthetic Empty Data States, Sleep Architecture Off-Wrist Guard, Startup Immediate BLE Dump, and FITAOS Watch Face Compatibility Architecture"
component = "LCARS Reports & Telemetry Consoles, Data Log Metrology, BLE Sync & Watch Faces"
affected_files = "index.html, w11_htm_app/index.html, w11_htm_app/server.py"
bug_loc = 320
resolution = """1. Data Log TX Color: Updated .log-tx in CSS to high-contrast clinical blue (#0284c7 in light theme, #38bdf8 in dark theme) and set descColor = #0284c7 in logLCARS().
2. Universal Step Propagation: Created central propagateStepTelemetry(steps, kcal, miles) updating all step telemetry targets across all tabs: Tab 02 Vitals, Tab 04 Metabolic 360, Tab 07 Reports (repAvgSteps), Tab 11 Clinical Goals (lblActiveDailySteps, lblGoalSteps), and Tab 13 Operations/Sport Bay. Wired to 0xA8, 0x89, pollLiveTelemetryChanges(), and initial startup SQLite load.
3. Scrubbed Bogus Demo Data & Added Clean Empty States: Eliminated fake 118/76, 64 ms, 28 stress in initializeVitalsAndCardioBaselines(); eliminated fake 160 mg/dL, 118/76, 98%, 72 BPM, 97.3°F, 2,384 steps in render360HealthView(); eliminated synthetic 7.4h fallback in assembleAndParseSleepStream(); updated renderSvgCurveChart and renderSvgDualCurveChart with clean, honest LCARS empty state banners: 'NO SENSOR TELEMETRY RECORDED FOR THIS TIMEFRAME'.
4. Last Night Sleep Telemetry Fix: Replaced synthetic 7.4h fallback with honest --.- and empty hypnogram state when watch was off-wrist or on charging dock; correctly parses live/archived 0xE0 packets when present.
5. Immediate Startup Telemetry Dump & Hydration: In DOMContentLoaded, immediately fetches /api/telemetry/latest and /api/telemetry/timeline to populate all 15 tabs without waiting; automatically reconnects previously authorized Web Bluetooth watch and executes the full sequential GATT harvest (0x03, 0x89, 0xA7, 0x87, 0xE0, 0xA8) with SQLite deduplication (zero-click automation).
6. FITAOS Watch Face Compatibility & Build Specification: Documented the proprietary VEEPOO 6-byte magic container, 240x280 RGB565 bitmap payload structure, and Veepoo CRC validation; integrated local vault of 94 official downloaded stock faces and defined the template injection workflow for custom designs."""

comments = "Verified across Chrome DevTools, local server, and BLE protocol handlers. Zero synthetic interpolation enforced across all 15 tabs."

now_str = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

cur.execute("""
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
""", ("LCARS_VEEPOO", next_num, bug_code, "2.4.0", title, "RESOLVED", component, affected_files, bug_loc, resolution, comments, now_str, now_str, now_str))

conn.commit()
print(f"[✔] Successfully recorded {bug_code} in D:\\CLEBUGGER\\bugs.db!")

cur.execute("SELECT id, bug_code, title, status FROM bugs WHERE bug_code = ?", (bug_code,))
print("Inserted record:", cur.fetchone())
conn.close()
