import urllib.request
import json

def test_api():
    print("[*] Testing /api/backup/status...")
    req = urllib.request.Request("http://localhost:8899/api/backup/status")
    with urllib.request.urlopen(req) as resp:
        data = json.loads(resp.read().decode('utf-8'))
        print("Backup Status Response:", json.dumps(data, indent=2))

    print("\n[*] Testing /api/backup/trigger...")
    req = urllib.request.Request(
        "http://localhost:8899/api/backup/trigger",
        data=b"{}",
        headers={"Content-Type": "application/json"}
    )
    with urllib.request.urlopen(req) as resp:
        data = json.loads(resp.read().decode('utf-8'))
        print("Backup Trigger Response:", json.dumps(data, indent=2))

    print("\n[*] Testing /api/meal/upload (MEAL-01 cell upload)...")
    payload = json.dumps({
        "meal_name": "Mon-Sat Oatmeal & Berries with 3 Turkey Sausage",
        "source": "MOBILE_CELL_PHOTO_UPLOAD",
        "notes": "meal01 flax seed and cinnamon with oat milk"
    }).encode('utf-8')
    req = urllib.request.Request(
        "http://localhost:8899/api/meal/upload",
        data=payload,
        headers={"Content-Type": "application/json"}
    )
    with urllib.request.urlopen(req) as resp:
        data = json.loads(resp.read().decode('utf-8'))
        print("Meal Upload Response:", json.dumps(data, indent=2))

    print("\n[*] Testing /mobile_upload.html HTTP fetch...")
    req = urllib.request.Request("http://localhost:8899/mobile_upload.html")
    with urllib.request.urlopen(req) as resp:
        html = resp.read().decode('utf-8')
        print(f"mobile_upload.html fetched OK, length = {len(html)} bytes.")

if __name__ == '__main__':
    test_api()
