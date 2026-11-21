import sqlite3
import datetime

conn = sqlite3.connect(r"D:\CLEBUGGER\bugs.db")
c = conn.cursor()

c.execute("SELECT MAX(bug_number) FROM bugs WHERE project_name='VEEPOO'")
max_num = c.fetchone()[0] or 84
new_num = max_num + 1
bug_code = f"CLE-{new_num:03d}"
now = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

title = "Voice Dictation & MediaRecorder Mobile Intake, Meal Pantry 1-Click Refresh, Full Scan Unrestricted Startup Hydration, and Unified Vault Sync Strip"
resolution = "1. Integrated SpeechRecognition Web API & MediaRecorder into /cell (cell.html) providing hands-free voice transcription for complex meal descriptions, attached audio memos, and multi-photo queueing with automatic home Wi-Fi PC sync to /api/meal/upload. 2. Enhanced Meal Pantry (#listboxPopularMeals) with prominent clinical labeling and auto-refresh of diet history (#dietHistoryList) and goals compliance report upon meal submission and live db polling. 3. Removed restrictive 24-hour age check in initFullScanView() so that full scan console loads and displays latest recorded biometric audit on startup from SQLite telemetry with timestamp, preventing blank page. 4. Eliminated scattered redundant sync buttons across UI, consolidating all sync actions into the Vault Sync Strip (#ribbonSyncItem / #ribbonSyncStatus) capturing pedometer, calories, vitals, sleep, and workout telemetry. 5. Maintained 100% byte parity across root and app index.html."

c.execute("""
    INSERT INTO bugs (project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at)
    VALUES (?, ?, ?, ?, ?, 'RESOLVED', ?, ?, ?, ?, ?, ?, ?, ?)
""", (
    "VEEPOO", new_num, bug_code, "v0.16.16.0", title,
    "Voice Input, Meal Pantry, Full Scan Startup, Unified Vault Sync",
    "cell.html, index.html, w11_htm_app/index.html, server.py",
    180, resolution, "Verified via run_full_audit_test_suite.py and test_accumulated_qa.py",
    now, now, now
))
conn.commit()
print(f"Logged {bug_code} successfully into CLEBUGGER!")

c.execute("SELECT bug_code, title, status FROM bugs WHERE bug_code=?", (bug_code,))
print("Verified in DB:", c.fetchone())
conn.close()
