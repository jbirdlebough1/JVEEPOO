lines = open(r"D:\veepoo\w11_htm_app\server.py", encoding="utf-8").readlines()
for i, l in enumerate(lines):
    if 'TARGET_MAC' in l or 'INSERT INTO vital_records' in l:
        print(f"Line {i+1}: {l.strip()[:100]}")
