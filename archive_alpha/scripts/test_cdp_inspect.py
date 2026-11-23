import urllib.request
import json
import base64
import time

def run():
    # 1. Get CDP websocket url
    try:
        req = urllib.request.urlopen("http://localhost:9222/json")
        pages = json.loads(req.read().decode('utf-8'))
        print(f"Found {len(pages)} CDP targets:")
        for p in pages:
            print(" -", p.get('title'), p.get('url'), p.get('webSocketDebuggerUrl'))
    except Exception as e:
        print("CDP error:", e)

run()
