with open(r'D:\veepoo\opt3_ble_python\watch_monitor.py', 'r', encoding='utf-8', errors='ignore') as f:
    for i, line in enumerate(f):
        if i < 70:
            print(f"{i+1}: {line.rstrip()}")
