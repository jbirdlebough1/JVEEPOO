def move_backup_funcs():
    path = r"D:\veepoo\w11_htm_app\server.py"
    with open(path, "r", encoding="utf-8") as f:
        code = f.read()

    # Find backup_config block at top
    pos_top = code.find("backup_config = {")
    end_top = code.find("}", pos_top) + 1

    funcs_code = '''
def load_backup_config():
    cfg_file = os.path.join(BASE_DIR, "backup_config.json")
    if os.path.exists(cfg_file):
        try:
            with open(cfg_file, "r", encoding="utf-8") as f:
                backup_config.update(json.load(f))
        except:
            pass

def save_backup_config():
    cfg_file = os.path.join(BASE_DIR, "backup_config.json")
    try:
        with open(cfg_file, "w", encoding="utf-8") as f:
            json.dump(backup_config, f, indent=2)
    except:
        pass

def background_backup_scheduler_worker():
    print(f"[*] USB Backup Background Scheduler started. Target time: {backup_config['schedule_time']} Daily.")
    while True:
        try:
            now = datetime.datetime.now()
            current_hm = now.strftime("%H:%M")
            current_ymd = now.strftime("%Y-%m-%d")
            if current_hm == backup_config["schedule_time"] and backup_config.get("last_run_date") != current_ymd:
                print(f"[⏰] 01:00 AM SCHEDULED BACKUP TRIGGERED! Executing automated USB dedup backup...")
                if USB_BACKUP_AVAILABLE:
                    summary = usb_backup_engine.perform_dedup_backup(backup_config.get("usb_target_path"))
                    backup_config["last_backup_summary"] = summary
                    backup_config["last_run_date"] = current_ymd
                    save_backup_config()
                    print(f"[✔] 01:00 AM Automated Backup Succeeded! Dedup ratio: {summary.get('dedup_ratio')}")
        except Exception as e:
            print(f"[!] Error in backup scheduler loop: {e}")
        time.sleep(30)
'''

    # Insert right after backup_config definition
    code = code[:end_top] + funcs_code + code[end_top:]
    
    # Remove old duplicate definition if present further down
    # (it won't hurt, but let's compile test)
    with open(path, "w", encoding="utf-8") as f:
        f.write(code)
    print("[✔] Moved backup functions to top of server.py")

if __name__ == '__main__':
    move_backup_funcs()
