def fix_routes():
    path = r"D:\veepoo\w11_htm_app\server.py"
    with open(path, "r", encoding="utf-8") as f:
        code = f.read()

    # Define clean do_GET addition
    get_routes = '''        elif self.path == "/api/backup/status":
            drives = usb_backup_engine.get_drive_letters() if USB_BACKUP_AVAILABLE else []
            self.send_json_response({
                "status": "OK",
                "schedule_time": backup_config.get("schedule_time", "01:00"),
                "detected_drives": drives,
                "current_target": backup_config.get("usb_target_path") or (drives[0] if drives else "D:\\\\LCARS_USB_BACKUP\\\\"),
                "last_backup_summary": backup_config.get("last_backup_summary")
            })

        elif self.path == "/api/meal/recent":
            meals = get_recent_meals(15)
            self.send_json_response({"status": "OK", "meals": meals})
'''

    # Remove bad snippet from do_POST
    bad_post = '''    def do_POST(self):
        # 1. Trigger On-Demand Immediate Sync
        
        elif self.path == "/api/backup/status":
            drives = usb_backup_engine.get_drive_letters() if USB_BACKUP_AVAILABLE else []
            self.send_json_response({
                "status": "OK",
                "schedule_time": backup_config.get("schedule_time", "01:00"),
                "detected_drives": drives,
                "current_target": backup_config.get("usb_target_path") or (drives[0] if drives else "D:\\\\LCARS_USB_BACKUP\\\\"),
                "last_backup_summary": backup_config.get("last_backup_summary")
            })

        elif self.path == "/api/meal/recent":
            meals = get_recent_meals(15)
            self.send_json_response({"status": "OK", "meals": meals})
        if self.path == "/api/sync/trigger":'''

    good_post = '''    def do_POST(self):
        # 1. Trigger On-Demand Immediate Sync
        if self.path == "/api/sync/trigger":'''

    if bad_post in code:
        code = code.replace(bad_post, good_post)
        print("[✔] Cleaned up bad post block")

    # Now add get_routes before "else:\n            # Default static file server"
    pos_else = code.find("else:\n            # Default static file server")
    if pos_else != -1:
        code = code[:pos_else] + get_routes + "        " + code[pos_else:]
        print("[✔] Added get_routes into do_GET")

    with open(path, "w", encoding="utf-8") as f:
        f.write(code)

if __name__ == '__main__':
    fix_routes()
