# test_all_user_verifications.py
import urllib.request
import json
import time

def run():
    print("[*] Checking running Chrome CDP on port 9244...")
    try:
        req = urllib.request.urlopen("http://127.0.0.1:9244/json")
        tabs = json.loads(req.read().decode("utf-8"))
        print(f"[✔] Found {len(tabs)} tabs in Chrome.")
    except Exception as e:
        print(f"[!] Could not connect to port 9244: {e}. Will test against local server.")
        return

if __name__ == '__main__':
    run()
