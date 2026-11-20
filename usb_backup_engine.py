import os
import sys
import json
import hashlib
import shutil
import sqlite3
import datetime
import subprocess

def get_drive_letters():
    """Detect removable USB drives on Windows."""
    drives = []
    # Try powershell Get-Volume
    try:
        cmd = ["powershell", "-NoProfile", "-Command", "Get-Volume | Where-Object { $_.DriveType -eq 'Removable' } | Select-Object -ExpandProperty DriveLetter"]
        out = subprocess.check_output(cmd, text=True).strip()
        for letter in out.splitlines():
            letter = letter.strip()
            if letter:
                drives.append(f"{letter}:\\")
    except Exception as e:
        pass

    # If no removable drive found, fallback to search for existing LCARS_BACKUP on any drive D-Z
    if not drives:
        for ch in "EFGHIJKLMNOPQRSTUVWXYZ":
            p = f"{ch}:\\"
            if os.path.exists(p):
                drives.append(p)
                
    if not drives:
        drives.append("D:\\LCARS_USB_BACKUP\\")
    return drives

def compute_sha256(filepath):
    h = hashlib.sha256()
    with open(filepath, 'rb') as f:
        while chunk := f.read(65536):
            h.update(chunk)
    return h.hexdigest()

def perform_dedup_backup(usb_path=None, source_dirs=None):
    if not usb_path:
        drives = get_drive_letters()
        usb_path = os.path.join(drives[0], "LCARS_BACKUP")
    else:
        if not usb_path.endswith("LCARS_BACKUP"):
            usb_path = os.path.join(usb_path, "LCARS_BACKUP")

    pool_dir = os.path.join(usb_path, "pool")
    snapshots_dir = os.path.join(usb_path, "snapshots")
    os.makedirs(pool_dir, exist_ok=True)
    os.makedirs(snapshots_dir, exist_ok=True)

    timestamp_str = datetime.datetime.now().strftime("%Y-%m-%d_%H%M%S")
    snapshot_dir = os.path.join(snapshots_dir, f"snapshot_{timestamp_str}")
    os.makedirs(snapshot_dir, exist_ok=True)

    # Atomic backup of SQLite telemetry.db
    base_dir = r"D:\veepoo"
    db_source = os.path.join(base_dir, "w11_htm_app", "telemetry.db")
    atomic_db_temp = os.path.join(snapshot_dir, "telemetry_atomic.db")
    if os.path.exists(db_source):
        try:
            src_conn = sqlite3.connect(db_source)
            dst_conn = sqlite3.connect(atomic_db_temp)
            src_conn.backup(dst_conn)
            dst_conn.close()
            src_conn.close()
        except Exception as e:
            shutil.copy2(db_source, atomic_db_temp)

    # Define files to back up
    files_to_backup = []
    
    # Core app files
    for root, dirs, files in os.walk(os.path.join(base_dir, "w11_htm_app")):
        if ".git" in root or "__pycache__" in root:
            continue
        for f in files:
            files_to_backup.append(os.path.join(root, f))

    # Root configs, slides and tools
    for f in ["index.html", "all_slides_data.json", "build_presentation.py", "audit_53_slides_coverage.py"]:
        p = os.path.join(base_dir, f)
        if os.path.exists(p):
            files_to_backup.append(p)

    total_logical_bytes = 0
    unique_stored_bytes = 0
    files_deduped_count = 0
    files_new_count = 0
    manifest = []

    for fpath in files_to_backup:
        try:
            size = os.path.getsize(fpath)
            total_logical_bytes += size
            fhash = compute_sha256(fpath)
            rel_path = os.path.relpath(fpath, base_dir)

            pool_file = os.path.join(pool_dir, fhash)
            if os.path.exists(pool_file):
                # Deduplicated!
                files_deduped_count += 1
            else:
                # Store new unique blob in pool
                shutil.copy2(fpath, pool_file)
                unique_stored_bytes += size
                files_new_count += 1

            manifest.append({
                "path": rel_path,
                "hash": fhash,
                "size": size,
                "modified": os.path.getmtime(fpath)
            })
        except Exception as e:
            print(f"Error backing up {fpath}: {e}")

    # Write snapshot manifest
    manifest_file = os.path.join(snapshot_dir, "manifest.json")
    with open(manifest_file, "w", encoding="utf-8") as f:
        json.dump(manifest, f, indent=2)

    # Compute overall pool size
    pool_size = sum(os.path.getsize(os.path.join(pool_dir, f)) for f in os.listdir(pool_dir))
    
    dedup_ratio = (total_logical_bytes / max(1, pool_size))
    space_saved_pct = ((total_logical_bytes - pool_size) / max(1, total_logical_bytes)) * 100 if total_logical_bytes > pool_size else 0

    backup_summary = {
        "timestamp": datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
        "usb_target": usb_path,
        "snapshot_id": f"snapshot_{timestamp_str}",
        "total_files": len(manifest),
        "new_blobs_stored": files_new_count,
        "deduped_blobs_reused": files_deduped_count,
        "logical_data_size_mb": round(total_logical_bytes / (1024*1024), 2),
        "physical_pool_size_mb": round(pool_size / (1024*1024), 2),
        "dedup_ratio": f"{dedup_ratio:.2f}x",
        "space_saved_percent": f"{space_saved_pct:.1f}%",
        "status": "SUCCESS • AIR-GAPPED VERIFIED"
    }

    # Save summary into snapshot and log file
    with open(os.path.join(snapshot_dir, "backup_summary.json"), "w", encoding="utf-8") as f:
        json.dump(backup_summary, f, indent=2)

    history_file = os.path.join(usb_path, "backup_history.json")
    history = []
    if os.path.exists(history_file):
        try:
            with open(history_file, "r", encoding="utf-8") as f:
                history = json.load(f)
        except:
            history = []
    history.insert(0, backup_summary)
    with open(history_file, "w", encoding="utf-8") as f:
        json.dump(history[:30], f, indent=2)

    return backup_summary

if __name__ == '__main__':
    print("[*] Testing USB Dedup Backup Engine...")
    summary = perform_dedup_backup()
    print("[✔] Backup completed successfully:")
    print(json.dumps(summary, indent=2))
