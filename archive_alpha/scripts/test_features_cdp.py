import urllib.request
import json
import base64

def cdp_eval(expr):
    url = "http://localhost:8899/json"
    req = urllib.request.Request(url)
    with urllib.request.urlopen(req) as resp:
        targets = json.loads(resp.read().decode('utf-8'))
    
    ws_url = None
    for t in targets:
        if "8899" in t.get("url", ""):
            ws_url = t.get("webSocketDebuggerUrl")
            break
    if not ws_url and len(targets) > 0:
        ws_url = targets[0].get("webSocketDebuggerUrl")
        
    print("Connecting to ws_url:", ws_url)
    # Use websocket client or simple CDP HTTP command if available
    # Or test by fetching page HTML and parsing
    return ws_url

if __name__ == '__main__':
    cdp_eval("1+1")
