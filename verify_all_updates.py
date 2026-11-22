import urllib.request
import json

base_url = "http://localhost:8899"

tests = [
    ("/cell", 200, "text/html"),
    ("/api/telemetry/latest", 200, "application/json"),
    ("/api/telemetry/timeline", 200, "application/json"),
    ("/api/meal/popular", 200, "application/json"),
    ("/api/dial/inventory", 200, "application/json"),
    ("/veepoo_dials_catalog.json", 200, "application/json")
]

print("=== VERIFYING SERVER ENDPOINTS ===")
for path, expected_code, content_type in tests:
    try:
        url = base_url + path
        req = urllib.request.Request(url, headers={"User-Agent": "Mozilla/5.0"})
        with urllib.request.urlopen(req, timeout=5) as resp:
            code = resp.getcode()
            ctype = resp.headers.get("Content-Type", "")
            print(f"[✔] {path:28} -> HTTP {code} ({ctype})")
    except Exception as e:
        print(f"[X] {path:28} -> FAILED: {e}")

# Verify file matching between index.html and w11_htm_app/index.html
with open(r"D:\veepoo\index.html", "rb") as f1:
    b1 = f1.read()
with open(r"D:\veepoo\w11_htm_app\index.html", "rb") as f2:
    b2 = f2.read()

if b1 == b2:
    print("\n[✔] PERFECT DUAL-FILE SYNC: index.html and w11_htm_app/index.html are identical!")
else:
    print(f"\n[!] MISMATCH: index.html ({len(b1)} bytes) vs w11_htm_app/index.html ({len(b2)} bytes)")
