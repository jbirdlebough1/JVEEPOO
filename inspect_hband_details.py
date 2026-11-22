import re

path = r"D:\veepoo\w11_htm_app\index.html"
with open(path, "r", encoding="utf-8") as f:
    lines = f.readlines()

print(f"Total lines: {len(lines)}")
for idx, line in enumerate(lines, 1):
    m = re.search(r'(?i)h[\s-]?band', line)
    if m:
        # Check if in comment, script, css or html
        stripped = line.strip()
        print(f"Line {idx:5d}: {stripped}")
