with open("index.html", "r", encoding="utf-8") as f:
    lines = f.readlines()

start = None
for i, line in enumerate(lines):
    if "function initFullScanView" in line:
        start = i
        break

if start is not None:
    for i in range(start, start + 120):
        print(f"{i+1}: {lines[i]}", end="")
