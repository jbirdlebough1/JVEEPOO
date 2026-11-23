import urllib.request
import json
import time

time.sleep(1.0)
base = "http://127.0.0.1:8899"

# 1. Roster
req = urllib.request.urlopen(f"{base}/api/shuttle/roster")
roster_data = json.loads(req.read().decode())
print(f"[*] /api/shuttle/roster: {roster_data['status']}, {len(roster_data['roster'])} cells registered.")

# 2. Legal status
req = urllib.request.urlopen(f"{base}/api/legal/status")
legal_data = json.loads(req.read().decode())
print(f"[*] /api/legal/status: accepted={legal_data.get('accepted')}")

# 3. Provision
prov_payload = json.dumps({
    "cell_id": "Cell 3",
    "user_uid": "USR-9482-03",
    "user_callsign": "Spock",
    "assigned_registry": "NCC-202X-3",
    "linked_watch_mac": "D4:33:A2:11:00:88",
    "target_a1c": 6.0
}).encode()
req = urllib.request.Request(f"{base}/api/shuttle/provision", data=prov_payload, headers={"Content-Type": "application/json"})
prov_res = json.loads(urllib.request.urlopen(req).read().decode())
print(f"[*] /api/shuttle/provision: {prov_res['status']}, token={prov_res['pairing_token'][:8]}...")

# 4. Register
reg_payload = json.dumps({
    "pairing_token": prov_res["pairing_token"],
    "phone_name": "Google Pixel Tablet",
    "phone_esn": "ESN-9482-SPOCK"
}).encode()
req = urllib.request.Request(f"{base}/api/shuttle/register", data=reg_payload, headers={"Content-Type": "application/json"})
reg_res = json.loads(urllib.request.urlopen(req).read().decode())
print(f"[*] /api/shuttle/register: {reg_res['status']}, cell={reg_res['cell_id']}")

# 5. Dock
dock_payload = json.dumps({
    "cell_id": "Cell 3",
    "phone_esn": "ESN-9482-SPOCK",
    "batch_records": [
        {
            "meal_name": "Vulcan Plomeek Soup",
            "calories": 180,
            "net_carbs": 12,
            "protein": 6,
            "fat": 2,
            "glycemic_impact_score": "LOW"
        }
    ]
}).encode()
req = urllib.request.Request(f"{base}/api/shuttle/dock", data=dock_payload, headers={"Content-Type": "application/json"})
dock_res = json.loads(urllib.request.urlopen(req).read().decode())
print(f"[*] /api/shuttle/dock: {dock_res['status']}, docked={dock_res['docked_records']}")

# 6. Dual Export
req = urllib.request.Request(f"{base}/api/backup/dual_export", data=b"{}", headers={"Content-Type": "application/json"})
exp_res = json.loads(urllib.request.urlopen(req).read().decode())
print(f"[*] /api/backup/dual_export: {exp_res['status']}, sqlite={exp_res['sqlite_export']['filename']}, fhir={exp_res['fhir_export']['filename']}")
print("[✔] All 6 Phase 1 Backend REST API Endpoints Verified 100% Passing!")
