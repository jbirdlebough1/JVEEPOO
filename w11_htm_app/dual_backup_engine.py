"""
dual_backup_engine.py
Automated Daily Dual USB/SD Export Engine
Exports:
  1. Telemetry SQLite Database Snapshot (.sqlite)
  2. Epic Systems FHIR R4 JSON Bundle (.json)
  3. Cryptographic Verification Manifest (.sha256)
"""

import os
import sys
import shutil
import datetime
import hashlib
import sqlite3

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import fhir_export_engine

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
DEFAULT_EXPORT_DIR = os.path.join(BASE_DIR, "backups")

def perform_dual_backup(target_dir=None, db_path=None):
    if not target_dir:
        target_dir = DEFAULT_EXPORT_DIR
    if not db_path:
        db_path = os.path.join(BASE_DIR, "telemetry.db")

    os.makedirs(target_dir, exist_ok=True)
    today_str = datetime.datetime.now().strftime("%Y-%m-%d")

    # 1. SQLite Database Snapshot
    sqlite_filename = f"telemetry_export_{today_str}.sqlite"
    sqlite_filepath = os.path.join(target_dir, sqlite_filename)
    
    # Use SQLite VACUUM INTO for online transaction-safe snapshot
    if os.path.exists(sqlite_filepath):
        try:
            os.remove(sqlite_filepath)
        except:
            pass

    try:
        conn = sqlite3.connect(db_path)
        conn.execute(f"VACUUM INTO '{sqlite_filepath}'")
        conn.close()
    except Exception as e:
        # Fallback to copy if VACUUM INTO fails
        shutil.copy2(db_path, sqlite_filepath)

    sqlite_sha256 = hashlib.sha256(open(sqlite_filepath, "rb").read()).hexdigest()
    sqlite_size = os.path.getsize(sqlite_filepath)

    # 2. Epic Systems FHIR R4 JSON Export
    fhir_res = fhir_export_engine.generate_fhir_bundle(
        db_path=db_path,
        output_dir=target_dir,
        mrn="PATIENT-MRN-948201",
        user_uid="USR-9482-01"
    )

    # 3. SHA-256 Verification Manifest
    manifest_filename = f"telemetry_export_{today_str}.sha256"
    manifest_filepath = os.path.join(target_dir, manifest_filename)

    manifest_lines = [
        f"{sqlite_sha256}  {sqlite_filename}",
        f"{fhir_res['sha256']}  {fhir_res['filename']}"
    ]
    with open(manifest_filepath, "w", encoding="utf-8") as f:
        f.write("\n".join(manifest_lines) + "\n")

    summary = {
        "status": "DUAL_BACKUP_SUCCESS",
        "date": today_str,
        "timestamp": datetime.datetime.utcnow().isoformat() + "Z",
        "export_directory": target_dir,
        "sqlite_export": {
            "filename": sqlite_filename,
            "filepath": sqlite_filepath,
            "size_bytes": sqlite_size,
            "sha256": sqlite_sha256
        },
        "fhir_export": fhir_res,
        "manifest": {
            "filename": manifest_filename,
            "filepath": manifest_filepath
        }
    }
    return summary

if __name__ == "__main__":
    res = perform_dual_backup()
    print("Dual Backup Result:")
    print(f"  SQLite: {res['sqlite_export']['filename']} ({res['sqlite_export']['size_bytes']} bytes)")
    print(f"  FHIR:   {res['fhir_export']['filename']} ({res['fhir_export']['entry_count']} entries)")
    print(f"  Manifest: {res['manifest']['filename']}")
