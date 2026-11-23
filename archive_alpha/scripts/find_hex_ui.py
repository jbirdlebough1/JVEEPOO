import json
import re

with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    text = f.read()

# Look for visible text containing HEX or [0x..] or similar
lines = text.split("\n")
for i, line in enumerate(lines):
    if any(k in line.upper() for k in ["RAW HEX", "HEX PAYLOAD", "HEX DUMP", "0X", "RAW_HEX"]):
        if not any(ign in line for ign in ["color:", "#", "Hexokinase", "Math.", "toString(16)"]):
            print(f"Line {i+1}: {line.strip()[:100]}")
