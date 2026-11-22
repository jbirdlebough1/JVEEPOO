import os
import re

print("=== SEARCHING DECOMPILED / SDK FOR DIAL TRANSFER / FLASHING ===")
# Search for classes handling dial sending in opt2_decompiled
sdk_dir = r"D:\veepoo\opt2_decompiled"
matches = []

for root, dirs, files in os.walk(sdk_dir):
    for f in files:
        if f.endswith(".java") and any(k in f.lower() for k in ["dial", "screenstyle", "customui", "watchface"]):
            matches.append(os.path.join(root, f))

print(f"Found {len(matches)} dial java files. Sample of 15:")
for m in matches[:15]:
    print("  ", m.replace(sdk_dir, ""))

# Search for opcode 0xC7 or dial transfer opcodes in vpprotocol
with open(r"D:\veepoo\index.html", "r", encoding="utf-8") as f:
    text = f.read()

print("\n=== OPCODES FOR DIAL IN INDEX.HTML ===")
for m in re.finditer(r'([^\n]*(?:0xC7|0x61|0x62|0x63|0xC8|0x23|applyDial|flashDial)[^\n]*)', text):
    print("  ", m.group(1).strip()[:100])
