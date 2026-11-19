import os
import glob
import struct
import json

BIN_DIR = r"D:\veepoo\stock_dials\shape4_240x280"
bin_files = glob.glob(os.path.join(BIN_DIR, "*.bin"))

print(f"[*] Analyzing {len(bin_files)} downloaded stock dial binaries...")

analysis = []
all_magics = set()
sizes = []

for bf in bin_files:
    fname = os.path.basename(bf)
    name = fname.replace(".bin", "")
    with open(bf, "rb") as f:
        data = f.read()
    
    size = len(data)
    sizes.append(size)
    magic = data[:6]
    all_magics.add(magic)
    
    # Check Protobuf tag presence
    # 0x08 (field 1), 0x10 (field 2), 0x18 (field 3), 0x20 (field 4), 0x28 (field 5)
    has_proto = False
    if len(data) > 64 and data[0x28:0x2c] in [b'\x0a\x05\x08\xfe', b'\x0a\x06\x08\xfe'] or b'\x08\xfe\x01' in data[:256]:
        has_proto = True

    # Search for strings/labels in the binary
    text_matches = []
    for s in [b"STEP", b"HEART", b"PULSE", b"BPM", b"KCAL", b"CAL", b"BAT", b"KM", b"GLUCOSE", b"BP", b"SPO2"]:
        if s in data:
            text_matches.append(s.decode('latin1'))

    # Check for image data starting offsets
    # Typically background starts after header padding
    img_offset = -1
    for i in range(256, 4096, 4):
        if data[i:i+4] != b'\x00\x00\x00\x00' and data[i:i+4] != b'\xff\xff\xff\xff':
            img_offset = i
            break

    analysis.append({
        "name": name,
        "size": size,
        "magic": magic.decode('latin1', errors='ignore'),
        "has_proto": has_proto,
        "text_matches": text_matches,
        "img_offset": hex(img_offset) if img_offset != -1 else "none"
    })

print(f"[✔] Unique Magics: {all_magics}")
print(f"[✔] Average size: {sum(sizes)//len(sizes)} bytes (Min: {min(sizes)}, Max: {max(sizes)})")

# Check if ANY stock dial has GLUCOSE
glucose_dials = [a["name"] for a in analysis if "GLUCOSE" in a["text_matches"]]
print(f"[!] Stock dials containing 'GLUCOSE': {glucose_dials}")

# Save detailed report
with open(os.path.join(BIN_DIR, "analysis_report.json"), "w", encoding="utf-8") as f:
    json.dump({
        "total_analyzed": len(analysis),
        "magics": [m.decode('latin1', errors='ignore') for m in all_magics],
        "size_stats": {
            "avg": sum(sizes)//len(sizes),
            "min": min(sizes),
            "max": max(sizes)
        },
        "sample_entries": analysis[:15]
    }, f, indent=2)

print("[✔] Analysis complete. Saved to analysis_report.json.")
