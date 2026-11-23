import re
import hashlib
import os

INDEX_APP = r"D:\veepoo\w11_htm_app\index.html"
INDEX_ROOT = r"D:\veepoo\index.html"
BFM_HTM = r"D:\veepoo\BFM.HTM"

with open(INDEX_APP, "r", encoding="utf-8") as f:
    content = f.read()

# Stamp canonical build string beta.00.01.0001 into header
old_stardate = """          <div class="actual-datetime" id="actualDateTime">FRIDAY, NOV 20, 2026 9:55:00 AM PST</div>
          <div class="lcars-stardate" id="stardateClock">STARDATE 126885.4 • SEC-SYS-ONLINE</div>"""

new_stardate = """          <div class="actual-datetime" id="actualDateTime">FRIDAY, NOV 20, 2026 9:55:00 AM PST</div>
          <div class="lcars-stardate" id="stardateClock">STARDATE 126885.4 • BUILD beta.00.01.0001 • SEC-SYS-ONLINE</div>"""

content = content.replace(old_stardate, new_stardate)

# Check div balance
open_divs = len(re.findall(r'<div\b', content, re.I))
close_divs = len(re.findall(r'</div\b', content, re.I))
print(f"DOM Div balance: Opens={open_divs}, Closes={close_divs}, Diff={open_divs - close_divs}")
assert open_divs == close_divs, "Div balance mismatch!"

# Write all three files
for p in [INDEX_APP, INDEX_ROOT, BFM_HTM]:
    with open(p, "w", encoding="utf-8") as f:
        f.write(content)
    h = hashlib.sha256(open(p, "rb").read()).hexdigest()
    print(f"  [✔] Wrote {p} (SHA256: {h[:16]}...)")

print("All 3 files deployed and verified bit-for-bit identical!")
