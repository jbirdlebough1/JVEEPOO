import urllib.request
import json

base_url = "http://localhost:8899"

tests = [
    ("/cell", 200, "text/html"),
    ("/api/meal/recent?limit=100", 200, "application/json"),
    ("/api/goals/compliance-report?timeframe=week", 200, "application/json"),
    ("/api/goals/compliance-report?timeframe=month", 200, "application/json"),
    ("/api/goals/compliance-report?timeframe=qtr", 200, "application/json"),
    ("/api/goals/compliance-report?timeframe=ytd", 200, "application/json"),
    ("/api/telemetry/latest", 200, "application/json"),
    ("/api/telemetry/timeline", 200, "application/json")
]

print("=== VERIFYING SERVER ENDPOINTS ===")
all_passed = True
for path, expected_code, content_type in tests:
    try:
        url = base_url + path
        req = urllib.request.Request(url, headers={"User-Agent": "Mozilla/5.0"})
        with urllib.request.urlopen(req, timeout=5) as resp:
            code = resp.getcode()
            ctype = resp.headers.get("Content-Type", "")
            data = resp.read()
            print(f"[✔] {path:44} -> HTTP {code} ({len(data)} bytes)")
    except Exception as e:
        print(f"[X] {path:44} -> FAILED: {e}")
        all_passed = False

# Verify Dual-File Sync
with open(r"D:\veepoo\index.html", "rb") as f1:
    b1 = f1.read()
with open(r"D:\veepoo\w11_htm_app\index.html", "rb") as f2:
    b2 = f2.read()

if b1 == b2:
    print(f"\n[✔] PERFECT DUAL-FILE SYNC: Both files are {len(b1):,} bytes identical!")
else:
    print(f"\n[!] MISMATCH: index.html ({len(b1)} bytes) vs w11_htm_app/index.html ({len(b2)} bytes)")
    all_passed = False

if all_passed:
    print("\n[✔] ALL VERIFICATION CHECKS PASSED SUCCESSFULLY!")
