import urllib.request, json

resp = urllib.request.urlopen("http://127.0.0.1:8899/api/telemetry/latest")
data = json.loads(resp.read().decode())
print("db_version:", data.get("db_version"))
print("keys in vitals:", list(data.get("vitals", {}).keys()))
