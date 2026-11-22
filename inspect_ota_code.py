with open(r"D:\veepoo\index.html", "r", encoding="utf-8") as f:
    text = f.read()

import re
print("=== OTA / DIAL / CHUNK IN INDEX.HTML ===")
for m in re.finditer(r'([^\n]*(?:OTA|chunk|sendGattCommand|writeWithoutResponse)[^\n]*)', text, re.I):
    print("  ", m.group(1).strip()[:100])
