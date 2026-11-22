import os, re, json

def check_slides():
    p = r"D:\veepoo\all_slides_data.json"
    if not os.path.exists(p): return
    with open(p, "r", encoding="utf-8") as f:
        slides = json.load(f)
    print(f"Checking {len(slides)} slides in all_slides_data.json for HBand...")
    for idx, s in enumerate(slides, 1):
        raw = json.dumps(s)
        matches = list(re.finditer(r'(?i)h[\s-]?band', raw))
        if matches:
            print(f"Slide {idx} ({s.get('slide_id', '')} - {s.get('title', '')}): {len(matches)} matches")
            for m in matches:
                start = max(0, m.start() - 35)
                end = min(len(raw), m.end() + 35)
                print("   ...", raw[start:end].replace('"', ''), "...")

def check_python_files():
    p_ble = r"D:\veepoo\opt3_ble_python\watch_monitor.py"
    if os.path.exists(p_ble):
        with open(p_ble, "r", encoding="utf-8", errors="ignore") as f:
            content = f.read()
        matches = list(re.finditer(r'(?i)h[\s-]?band', content))
        print(f"watch_monitor.py: {len(matches)} matches")

if __name__ == "__main__":
    check_slides()
    check_python_files()
