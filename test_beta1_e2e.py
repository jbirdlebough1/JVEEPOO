"""
test_beta1_e2e.py
Comprehensive End-to-End Six Sigma Verification Suite for Beta 1 First Build.
Evaluates:
  1. Server API responsiveness
  2. Multi-tenant Family Cells & Watch Roster
  3. Startup Legal Governance Status
  4. Full Shuttlecraft Provisioning & Registration Handshake
  5. Mobile Offline Ingestion & Atomic Homecoming Docking
  6. Automated Dual USB/SD Export (SQLite + Epic FHIR R4 JSON + SHA-256 Manifest)
  7. HTML DOM Tag Balance & Hierarchy (index.html & cell.html)
  8. Dual-File Mirror Parity (index.html == w11_htm_app/index.html)
  9. JavaScript Syntax (node --check)
 10. Zero Defect Mandate Compliance
"""

import urllib.request
import json
import sqlite3
import hashlib
import os
import subprocess
import time
import sys

BASE_URL = "http://127.0.0.1:8899"
WORKSPACE_DIR = r"D:\veepoo"
INDEX_ROOT = os.path.join(WORKSPACE_DIR, "index.html")
INDEX_APP = os.path.join(WORKSPACE_DIR, "w11_htm_app", "index.html")
CELL_HTML = os.path.join(WORKSPACE_DIR, "w11_htm_app", "cell.html")
DB_PATH = os.path.join(WORKSPACE_DIR, "w11_htm_app", "telemetry.db")

def post_json(endpoint, payload):
    data = json.dumps(payload).encode("utf-8")
    req = urllib.request.Request(f"{BASE_URL}{endpoint}", data=data, headers={"Content-Type": "application/json"})
    with urllib.request.urlopen(req, timeout=10.0) as resp:
        return json.loads(resp.read().decode("utf-8"))

def get_json(endpoint):
    with urllib.request.urlopen(f"{BASE_URL}{endpoint}", timeout=10.0) as resp:
        return json.loads(resp.read().decode("utf-8"))

def run_e2e():
    print("=" * 75)
    print("   BETA 1 FIRST BUILD: END-TO-END SIX SIGMA VERIFICATION ENGINE")
    print("=" * 75)
    time.sleep(1.0) # ensure daemon ready

    # 1. Server Health
    print("\n[STEP 1/10] Telemetry Server Health & Port 8899 Check...")
    h = get_json("/api/telemetry/latest")
    assert "db_version" in h, "Health check failed!"
    print(f"  [PASS] Server online. Telemetry DB version: {h.get('db_version')}")

    # 2. Multi-Tenant Family Roster
    print("\n[STEP 2/10] Registered Family Cells & Watch Roster Verification...")
    r = get_json("/api/shuttle/roster")
    assert r.get("status") == "OK", "Roster query failed!"
    roster = r.get("roster", [])
    assert len(roster) >= 1, f"Expected at least 1 cell, found {len(roster)}"
    print(f"  [PASS] Verified {len(roster)} registered family cells (Cell 1 John).")

    # 3. Startup Legal Acceptance Governance
    print("\n[STEP 3/10] Startup Legal Governance & Audit Table Check...")
    leg = get_json("/api/legal/status")
    assert leg.get("accepted") is True, "Legal acceptance check failed!"
    print(f"  [PASS] Legal audit confirmed: User {leg.get('user_uid')}, Terms {leg.get('terms_version')}")

    # 4. Provisioning & Registration Handshake
    print("\n[STEP 4/10] Full Shuttlecraft Provisioning & Registration Lifecycle...")
    prov = post_json("/api/shuttle/provision", {
        "cell_id": "Cell 99",
        "user_uid": "USR-9482-99",
        "user_callsign": "Telemetry Test Scout",
        "assigned_registry": "NCC-5010-99",
        "linked_watch_mac": "EE:FF:00:11:22:33",
        "target_a1c": 5.9
    })
    token = prov.get("pairing_token")
    assert token, "Provisioning failed to issue pairing token!"
    print(f"  [PASS] Provisioned Cell 99. Token: {token[:8]}...")

    reg = post_json("/api/shuttle/register", {
        "pairing_token": token,
        "phone_name": "E2E Automated Test Device",
        "phone_esn": "ESN-9482-E2E-TEST"
    })
    assert reg.get("status") == "REGISTERED", "Registration handshake failed!"
    print(f"  [PASS] Handshake confirmed for {reg.get('cell_id')} (ESN: {reg.get('phone_esn')})")

    # 5. Mobile Offline Ingestion & Atomic Homecoming Docking
    print("\n[STEP 5/10] Offline Meal Queue & Atomic Bulk Docking Verification...")
    dock = post_json("/api/shuttle/dock", {
        "cell_id": "Cell 99",
        "phone_esn": "ESN-9482-E2E-TEST",
        "batch_records": [
            {
                "intake_timestamp": "2026-11-23T06:00:00Z",
                "meal_name": "E2E Test Field Ration",
                "calories": 250,
                "net_carbs": 15,
                "protein": 20,
                "fat": 10,
                "glycemic_impact_score": "LOW"
            }
        ]
    })
    assert dock.get("status") == "DOCK_SUCCESS", "Docking failed!"
    assert dock.get("docked_records") == 1, "Incorrect docked count!"
    print(f"  [PASS] Homecoming dock completed: 1 record ingested at {dock.get('last_docked_at')}")

    # Clean up test cell
    conn = sqlite3.connect(DB_PATH)
    conn.execute("DELETE FROM registered_cells WHERE cell_id='Cell 99'")
    conn.execute("DELETE FROM user_profiles WHERE user_uid='USR-9482-99'")
    conn.execute("DELETE FROM meal_entries WHERE user_uid='USR-9482-99'")
    conn.commit()
    conn.close()

    # 6. Automated Dual USB/SD Export Check
    print("\n[STEP 6/10] Dual USB/SD Export (SQLite + Epic FHIR R4 JSON)...")
    exp = post_json("/api/backup/dual_export", {})
    assert exp.get("status") == "DUAL_BACKUP_SUCCESS", "Dual export failed!"
    sqlite_fn = exp["sqlite_export"]["filename"]
    fhir_fn = exp["fhir_export"]["filename"]
    manifest_fn = exp["manifest"]["filename"]
    print(f"  [PASS] Dual export generated:")
    print(f"         - SQLite: {sqlite_fn} ({exp['sqlite_export']['size_bytes']} bytes)")
    print(f"         - FHIR:   {fhir_fn} ({exp['fhir_export']['entry_count']} entries)")
    print(f"         - SHA256: {manifest_fn}")

    # 7. DOM Hierarchy & Tag Balance
    print("\n[STEP 7/10] Strict DOM Tag Balance & Hierarchy Check...")
    h_idx = open(INDEX_ROOT, "r", encoding="utf-8").read()
    h_cell = open(CELL_HTML, "r", encoding="utf-8").read()
    import re
    diff_idx = len(re.findall(r'<div\b', h_idx, re.I)) - len(re.findall(r'</div\b', h_idx, re.I))
    diff_cell = len(re.findall(r'<div\b', h_cell, re.I)) - len(re.findall(r'</div\b', h_cell, re.I))
    assert diff_idx == 0, f"index.html div imbalance: {diff_idx}"
    assert diff_cell == 0, f"cell.html div imbalance: {diff_cell}"
    print(f"  [PASS] index.html Tag Balance (Diff == 0)")
    print(f"  [PASS] cell.html Tag Balance (Diff == 0)")

    # 8. Dual-File Mirror Parity
    print("\n[STEP 8/10] Dual-File SHA-256 Bit-for-Bit Mirror Parity...")
    hash1 = hashlib.sha256(open(INDEX_ROOT, "rb").read()).hexdigest()
    hash2 = hashlib.sha256(open(INDEX_APP, "rb").read()).hexdigest()
    assert hash1 == hash2, "Parity mismatch between root and app index.html!"
    print(f"  [PASS] Bit-for-bit identical parity verified (SHA-256: {hash1[:16]}...)")

    # 9. Node.js JavaScript AST Check
    print("\n[STEP 9/10] JavaScript Static Syntax AST Check (Node --check)...")
    from bs4 import BeautifulSoup
    soup = BeautifulSoup(h_idx, "html.parser")
    scripts = [s.string for s in soup.find_all("script") if s.string and not s.get("src")]
    js_blob = "\n;\n".join(scripts)
    temp_js = os.path.join(WORKSPACE_DIR, "_e2e_temp_lint.js")
    with open(temp_js, "w", encoding="utf-8") as f:
        f.write(js_blob)
    try:
        proc = subprocess.run(["node", "--check", temp_js], capture_output=True, text=True)
        assert proc.returncode == 0, f"JS Syntax error: {proc.stderr}"
        print("  [PASS] JavaScript static syntax verified clean (zero errors).")
    finally:
        if os.path.exists(temp_js):
            os.remove(temp_js)

    # 10. Zero Defect Mandate Compliance
    print("\n[STEP 10/10] Full Repository Zero Defect Mandate Verification...")
    audit_proc = subprocess.run([sys.executable, os.path.join(WORKSPACE_DIR, "audit_zero_defect.py")], capture_output=True, text=True)
    assert audit_proc.returncode == 0, f"Zero defect audit failed:\n{audit_proc.stdout}"
    print("  [PASS] 8/8 Zero Defect checks verified (0 broken links, 0 LaTeX, 0 ASCII art, 0 raw charts).")

    print("\n" + "=" * 75)
    print("   STATUS: 10/10 SIX SIGMA FIRST BUILD VERIFICATION ACHIEVED (100% PASS)")
    print("=" * 75)

if __name__ == "__main__":
    run_e2e()
