# test_live_tab_switch.py
import subprocess
import json
import time
import os

# We will use Chrome headless with remote debugging to load http://localhost:8899/index.html,
# click Tab 07 (Help), click Tab 08 (Medication), click Tab 09 (Goals),
# take screenshots of each, and check the rendered DOM!

import urllib.request

# Start chrome headless with remote debugging
cmd = [
    r"C:\Program Files\Google\Chrome\Application\chrome.exe",
    "--headless=new",
    "--remote-debugging-port=9255",
    "--window-size=1920,1080",
    "http://localhost:8899/index.html"
]

proc = subprocess.Popen(cmd)
time.sleep(3)

try:
    tabs = json.loads(urllib.request.urlopen("http://127.0.0.1:9255/json").read().decode("utf-8"))
    ws_url = tabs[0]["webSocketDebuggerUrl"]
    print("Connected to headless Chrome via WebSocket:", ws_url)
finally:
    proc.terminate()
