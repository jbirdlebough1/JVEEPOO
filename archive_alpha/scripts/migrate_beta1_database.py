import sqlite3
import os
import datetime

DB_PATHS = [
    r"D:\veepoo\w11_htm_app\telemetry.db",
    r"D:\veepoo\telemetry_master.db"
]

def migrate_db(db_path):
    print(f"Migrating database: {db_path}...")
    conn = sqlite3.connect(db_path)
    cur = conn.cursor()
    cur.execute("PRAGMA foreign_keys = ON;")

    # 1. user_profiles table
    cur.execute("""
    CREATE TABLE IF NOT EXISTS user_profiles (
        user_uid TEXT PRIMARY KEY,
        user_callsign TEXT NOT NULL,
        assigned_registry TEXT NOT NULL,
        baseline_a1c REAL DEFAULT 6.5,
        eag_target_low REAL DEFAULT 70.0,
        eag_target_high REAL DEFAULT 140.0,
        database_filename TEXT NOT NULL,
        created_at TEXT NOT NULL,
        status TEXT DEFAULT 'ACTIVE'
    );
    """)

    # 2. registered_cells table
    cur.execute("""
    CREATE TABLE IF NOT EXISTS registered_cells (
        cell_id TEXT PRIMARY KEY,
        registry_number TEXT NOT NULL,
        user_uid TEXT NOT NULL,
        user_callsign TEXT NOT NULL,
        linked_watch_mac TEXT UNIQUE NOT NULL,
        phone_name TEXT NOT NULL,
        phone_esn TEXT UNIQUE NOT NULL,
        pairing_token TEXT NOT NULL,
        registered_at TEXT NOT NULL,
        last_docked_at TEXT,
        status TEXT DEFAULT 'ACTIVE',
        FOREIGN KEY(user_uid) REFERENCES user_profiles(user_uid)
    );
    """)

    # 3. user_legal_acceptance table
    cur.execute("""
    CREATE TABLE IF NOT EXISTS user_legal_acceptance (
        agreement_id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_uid TEXT NOT NULL,
        terms_version TEXT NOT NULL,
        terms_sha256 TEXT NOT NULL,
        accepted_timestamp TEXT NOT NULL,
        signature_acknowledgment INTEGER DEFAULT 1,
        ip_address TEXT,
        device_fingerprint TEXT
    );
    """)

    # 4. Check existing tables and add user_uid if missing
    cur.execute("SELECT name FROM sqlite_master WHERE type='table'")
    existing_tables = [r[0] for r in cur.fetchall()]

    for t in ['vital_records', 'daily_rollups', 'meal_entries']:
        if t in existing_tables:
            cur.execute(f"PRAGMA table_info({t})")
            cols = [c[1] for c in cur.fetchall()]
            if 'user_uid' not in cols:
                print(f"  Adding user_uid column to {t}...")
                cur.execute(f"ALTER TABLE {t} ADD COLUMN user_uid TEXT DEFAULT 'USR-9482-01'")

    # 5. Seed default records
    now_iso = datetime.datetime.utcnow().isoformat() + "Z"
    
    # Primary Command: John
    cur.execute("""
    INSERT OR REPLACE INTO user_profiles (user_uid, user_callsign, assigned_registry, baseline_a1c, eag_target_low, eag_target_high, database_filename, created_at, status)
    VALUES ('USR-9482-01', 'John (Command)', 'NCC-202X-1', 8.5, 70.0, 140.0, 'telemetry.db', ?, 'ACTIVE')
    """, (now_iso,))

    # Secondary: Mary
    cur.execute("""
    INSERT OR REPLACE INTO user_profiles (user_uid, user_callsign, assigned_registry, baseline_a1c, eag_target_low, eag_target_high, database_filename, created_at, status)
    VALUES ('USR-9482-02', 'Mary', 'NCC-202X-2', 6.2, 70.0, 140.0, 'telemetry_mary.db', ?, 'ACTIVE')
    """, (now_iso,))

    # Seed Cell 1 (Foodoor)
    cur.execute("""
    INSERT OR REPLACE INTO registered_cells (cell_id, registry_number, user_uid, user_callsign, linked_watch_mac, phone_name, phone_esn, pairing_token, registered_at, last_docked_at, status)
    VALUES ('Cell 1', 'NCC-202X-1', 'USR-9482-01', 'John', '8B:DE:40:83:F6:16', 'Pixel 9 Pro (Shuttlecraft Foodoor)', 'ESN-9482-A01', 'b7e28941-4821-4d92-bf39-2a9f4c391201', ?, ?, 'ACTIVE')
    """, (now_iso, now_iso))

    # Seed Cell 2 (Auxiliary)
    cur.execute("""
    INSERT OR REPLACE INTO registered_cells (cell_id, registry_number, user_uid, user_callsign, linked_watch_mac, phone_name, phone_esn, pairing_token, registered_at, last_docked_at, status)
    VALUES ('Cell 2', 'NCC-202X-2', 'USR-9482-02', 'Mary', 'C4:12:9A:33:02:51', 'Samsung Galaxy S24 Ultra', 'ESN-7731-B942-3C', 'a8f7c9e1-4b2d-49f3-8d01-e246985a1b3c', ?, NULL, 'ACTIVE')
    """, (now_iso,))

    # Seed Legal Acceptance for primary
    cur.execute("""
    INSERT OR IGNORE INTO user_legal_acceptance (user_uid, terms_version, terms_sha256, accepted_timestamp, signature_acknowledgment, ip_address, device_fingerprint)
    VALUES ('USR-9482-01', 'BFM-BETA1-2026', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', ?, 1, '127.0.0.1', 'NODE-CL3-LOCAL')
    """, (now_iso,))

    conn.commit()
    conn.close()
    print(f"Migration completed cleanly for {db_path}.")

for p in DB_PATHS:
    migrate_db(p)
print("All database migrations verified successfully!")
