import sqlite3
import datetime

conn = sqlite3.connect(r"D:\CLEBUGGER\bugs.db")
cur = conn.cursor()

cur.execute("SELECT MAX(bug_number) FROM bugs")
row = cur.fetchone()
max_num = row[0] if row and row[0] else 79
next_num = max_num + 1
bug_code = f"CLE-{next_num:03d}"

title = "Submitted Meals Scrollable Window (Latest 5 + Full History), and Clinical Goals Compliance & Activity Follow-up Audit Report (Week/Month/Qtr/YTD with Met/Missed Filters)"
component = "Tab 06 Diet Trades, Tab 07 Reports Console & Telemetry Server"
affected_files = "index.html, w11_htm_app/index.html, w11_htm_app/server.py"
bug_loc = 415
resolution = """1. Submitted Meals Window (#dietHistoryList): Styled container with max-height: 520px and overflow-y: auto to show the latest ~5 meals directly in view while providing a smooth vertical scrollbar to browse all historical meals to date. Upgraded renderDietHistory() to fetch all committed records from /api/meal/recent?limit=100 in SQLite (bridging mobile /cell submissions and LCARS builder) with high-contrast compliance badges, macros, and GLUT4 walk recommendations.
2. Clinical Goals Compliance & Activity Audit Engine (/api/goals/compliance-report): Built dedicated server endpoint supporting week, month, qtr, and ytd timeframes. Evaluates every nutritional intake event against the 45g net carb diabetic ceiling. Integrates correlated vital_records pedometer telemetry to detect whether high-carb excursions were followed up with a 1500+ step GLUT4 walk or left unmitigated (sedentary). Returns compliance rates, KPI totals, and event breakdowns.
3. Goals Report Console (Tab 07 boxGoalsComplianceReport): Added dedicated, high-contrast report card featuring:
   - Timeframe Selectors: WEEK (7D), MONTH (30D), QTR (90D), YTD (ANNUAL)
   - Filter Tabs: ALL (N), MET (N), MISSED • NO ACTIVITY (N)
   - KPI Summary Tiles: TOTAL EVALUATED, GOALS MET, MISSED • NO ACTIVITY, SAVED VIA GLUT4 WALK, and COMPLIANCE SCORE (%)
   - Scrollable Table Viewer: 400px scrollable view showing timestamp, meal name, net carbs vs target, follow-up activity status, and status badge."""

comments = "Verified across all timeframes (week, month, qtr, ytd) and verified 100% dual-file parity between index.html and w11_htm_app/index.html."

now_str = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

cur.execute("""
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
""", ("LCARS_VEEPOO", next_num, bug_code, "2.4.1", title, "RESOLVED", component, affected_files, bug_loc, resolution, comments, now_str, now_str, now_str))

conn.commit()
print(f"[✔] Successfully recorded {bug_code} in D:\\CLEBUGGER\\bugs.db!")

cur.execute("SELECT id, bug_code, title, status FROM bugs WHERE bug_code = ?", (bug_code,))
print("Inserted record:", cur.fetchone())
conn.close()
