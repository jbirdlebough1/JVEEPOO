import os
import json
import urllib.request
import ssl
import concurrent.futures
import struct

print("[*] Starting Shape 4 (240x280) Watch Face Downloader & Analyzer...")

DEST_DIR = r"D:\veepoo\stock_dials\shape4_240x280"
os.makedirs(DEST_DIR, exist_ok=True)

with open(r"D:\veepoo\w11_htm_app\veepoo_dials_catalog.json", "r", encoding="utf-8") as f:
    cat = json.load(f)

shape4_dials = [d for d in cat if d.get("dialShape") == 4]
print(f"[*] Found {len(shape4_dials)} official watch faces for 240x280 screen.")

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

def download_dial(dial):
    name = dial["name"]
    bin_url = dial["fileUrl"]
    prev_url = dial.get("previewUrl", "")
    
    bin_path = os.path.join(DEST_DIR, f"{name}.bin")
    prev_path = os.path.join(DEST_DIR, f"{name}_preview.png")
    
    status = {"name": name, "bin_ok": False, "prev_ok": False, "bin_size": 0, "prev_size": 0}
    
    # Download BIN
    if not os.path.exists(bin_path) or os.path.getsize(bin_path) == 0:
        try:
            req = urllib.request.Request(bin_url, headers={"User-Agent": "Mozilla/5.0"})
            with urllib.request.urlopen(req, context=ctx, timeout=12) as resp:
                data = resp.read()
                with open(bin_path, "wb") as f:
                    f.write(data)
                status["bin_ok"] = True
                status["bin_size"] = len(data)
        except Exception as e:
            status["bin_err"] = str(e)
    else:
        status["bin_ok"] = True
        status["bin_size"] = os.path.getsize(bin_path)

    # Download Preview PNG
    if prev_url and (not os.path.exists(prev_path) or os.path.getsize(prev_path) == 0):
        try:
            req = urllib.request.Request(prev_url, headers={"User-Agent": "Mozilla/5.0"})
            with urllib.request.urlopen(req, context=ctx, timeout=12) as resp:
                data = resp.read()
                with open(prev_path, "wb") as f:
                    f.write(data)
                status["prev_ok"] = True
                status["prev_size"] = len(data)
        except Exception as e:
            status["prev_err"] = str(e)
    else:
        status["prev_ok"] = os.path.exists(prev_path)
        status["prev_size"] = os.path.getsize(prev_path) if os.path.exists(prev_path) else 0

    return status

print(f"[*] Launching concurrent download with 10 worker threads...")
results = []
with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
    futures = {executor.submit(download_dial, d): d for d in shape4_dials}
    for future in concurrent.futures.as_completed(futures):
        res = future.result()
        results.append(res)
        if len(results) % 15 == 0 or len(results) == len(shape4_dials):
            print(f"[*] Progress: {len(results)} / {len(shape4_dials)} downloaded.")

success_bins = sum(1 for r in results if r["bin_ok"])
success_prevs = sum(1 for r in results if r["prev_ok"])
print(f"[✔] Download Complete: {success_bins}/{len(shape4_dials)} binaries, {success_prevs}/{len(shape4_dials)} previews.")

# Save inventory summary
with open(os.path.join(DEST_DIR, "inventory.json"), "w", encoding="utf-8") as f:
    json.dump(results, f, indent=2)

print("[*] Now running deep binary inspection across downloaded watch faces...")
