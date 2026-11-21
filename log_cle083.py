import sqlite3
import datetime

conn = sqlite3.connect(r"D:\CLEBUGGER\bugs.db")
cursor = conn.cursor()

# Check existing CLE-083
cursor.execute("SELECT bug_code FROM bugs WHERE bug_code = 'CLE-083'")
existing = cursor.fetchone()

if not existing:
    now = datetime.datetime.now().isoformat()
    cursor.execute("""
        INSERT INTO bugs (
            project_name, bug_number, bug_code, build_number, title, status, component, affected_files, bug_loc, resolution, comments, created_at, updated_at, resolved_at
        ) VALUES (
            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        )
    """, (
        "Veepoo LCARS Health System",
        83,
        "CLE-083",
        "BUILD-2026.11.21.06",
        "Hardware-Adaptive Watch Connect Telemetry Harvest (H8 / VEE / 5010), Full Watch Data Set Download on Connect, Multi-Console SQLite Hydration, and Vault Sync Complete State",
        "RESOLVED",
        "Bluetooth GATT / Watch Connect Sync / Hardware Adapter",
        "index.html, w11_htm_app/index.html, w11_htm_app/server.py",
        "index.html:7845, index.html:7865, index.html:8020, index.html:16670, index.html:19230, server.py:1635",
        "1. Added hardware-adaptive characteristic discovery in setupGattChannels() to bind command TX and telemetry RX for older Veepoo models (H8 / VEE) using alternative UUIDs (0000fea2, 0000fee8, 0000ae01, 6e400002). 2. Built executeFullWatchConnectSync() pipeline executing full multi-vector data harvest immediately on connect (Auth 0xA1, Battery 0x03, 32-bit Pedometer 0x89/0xA8/0x07, 24h Health Dump 0xA7, Glucose Calibration Baseline 0x87, Sleep Day 0 & Day 1 0xE0). 3. Wired multi-console SQLite hydration on connect. 4. Updated vault sync status to display '⏳ DOWNLOADING FULL WATCH DATA SET...' during harvest, and only state '● SYNC COMPLETE (100% CURRENT)' after all data is downloaded and deduplicated.",
        "Verified with test_accumulated_qa.py and run_full_audit_test_suite.py. Byte-synchronized across index.html files.",
        now,
        now,
        now
    ))
    conn.commit()
    print("Inserted CLE-083 successfully!")
else:
    print("CLE-083 already exists.")

conn.close()
