import urllib.request
import json

payload = {
    "meal_name": "Dick's DriveIn 2 cheeze burgers, 3 tatar, fry and large diet",
    "description": "Dick's DriveIn 2 cheeze burgers, 3 tatar, fry and large diet"
}

req = urllib.request.Request(
    "http://localhost:8899/api/meal/analyze",
    data=json.dumps(payload).encode("utf-8"),
    headers={"Content-Type": "application/json"}
)

with urllib.request.urlopen(req) as resp:
    res = json.loads(resp.read().decode("utf-8"))
    print(json.dumps(res, indent=2))
