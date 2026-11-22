import re

with open("index.html", "r", encoding="utf-8") as f:
    lines = f.readlines()

for i, line in enumerate(lines):
    if "DOMContentLoaded" in line or "window.onload" in line or "addEventListener('load'" in line:
        print(f"Line {i+1}: {line.strip()}")
        # print next 25 lines
        for j in range(i+1, min(i+35, len(lines))):
            print(f"  {j+1}: {lines[j].rstrip()}")
