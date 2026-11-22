with open(r"D:\veepoo\opt2_decompiled\vpbluetooth\sources\com\inuker\bluetooth\library\jieli\dial\JLWatchFaceManager.java", "r", encoding="utf-8", errors="ignore") as f:
    text = f.read()

import re
print("Methods in JLWatchFaceManager:")
for m in re.finditer(r'public\s+(?:void|boolean|int|String|FatFile)\s+([A-Za-z0-9_]+)\s*\([^)]*\)', text):
    print("  ", m.group(0)[:80])
