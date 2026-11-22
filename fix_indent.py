def fix_indent():
    path = r"D:\veepoo\w11_htm_app\server.py"
    with open(path, "r", encoding="utf-8") as f:
        code = f.read()

    bad = '''                self.wfile.write(content)
                return

                elif self.path == "/api/backup/status":
            drives = usb_backup_engine.get_drive_letters() if USB_BACKUP_AVAILABLE else []
            self.send_json_response({
                "status": "OK",
                "schedule_time": backup_config.get("schedule_time", "01:00"),
                "detected_drives": drives,
                "current_target": backup_config.get("usb_target_path") or (drives[0] if drives else "D:\\\\LCARS_USB_BACKUP\\\\"),
                "last_backup_summary": backup_config.get("last_backup_summary")
            })

        elif self.path == "/api/meal/recent":'''

    good = '''                self.wfile.write(content)
                return

        elif self.path == "/api/backup/status":
            drives = usb_backup_engine.get_drive_letters() if USB_BACKUP_AVAILABLE else []
            self.send_json_response({
                "status": "OK",
                "schedule_time": backup_config.get("schedule_time", "01:00"),
                "detected_drives": drives,
                "current_target": backup_config.get("usb_target_path") or (drives[0] if drives else "D:\\\\LCARS_USB_BACKUP\\\\"),
                "last_backup_summary": backup_config.get("last_backup_summary")
            })

        elif self.path == "/api/meal/recent":'''

    if bad in code:
        code = code.replace(bad, good)
        print("[✔] Fixed indentation")
    else:
        print("[!] Not matched")

    with open(path, "w", encoding="utf-8") as f:
        f.write(code)

if __name__ == '__main__':
    fix_indent()
