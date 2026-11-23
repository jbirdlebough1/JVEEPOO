lines = open(r"D:\veepoo\w11_htm_app\server.py", encoding="utf-8").readlines()
for i, l in enumerate(lines):
    if 'def send_json_response' in l or 'def do_POST' in l:
        print(f"Line {i+1}: {l.strip()[:100]}")
