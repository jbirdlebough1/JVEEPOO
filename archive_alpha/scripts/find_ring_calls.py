with open(r'D:\veepoo\w11_htm_app\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'initUnifiedGlucoseRing' in l:
        print(f"Line {i+1}: {l.strip()}")
