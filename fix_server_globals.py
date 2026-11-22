def fix_globals():
    path = r"D:\veepoo\w11_htm_app\server.py"
    with open(path, "r", encoding="utf-8") as f:
        code = f.read()

    # Move USB_BACKUP import and config to top
    top_block = '''
# USB Dedup Backup Engine Import & Config
try:
    import usb_backup_engine
    USB_BACKUP_AVAILABLE = True
except ImportError:
    try:
        sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
        import usb_backup_engine
        USB_BACKUP_AVAILABLE = True
    except:
        USB_BACKUP_AVAILABLE = False

backup_config = {
    "schedule_time": "01:00",
    "usb_target_path": None,
    "last_backup_summary": None,
    "last_run_date": None
}
'''
    pos_import = code.find("PORT = 8899")
    if pos_import != -1 and "USB Dedup Backup Engine Import & Config" not in code:
        code = code[:pos_import] + top_block + "\n" + code[pos_import:]
        print("[✔] Injected USB globals at top of server.py")

    with open(path, "w", encoding="utf-8") as f:
        f.write(code)

if __name__ == '__main__':
    fix_globals()
