print("[*] Applying Radio Yield & Chrome Priority updates...")

# 1. Update server.py
with open(r"D:\veepoo\w11_htm_app\server.py", "r", encoding="utf-8") as f:
    srv = f.read()

# Add /api/radio_yield
old_srv_post = """        elif self.path == "/api/browser_active":
            record_browser_activity()
            self.send_json_response({"status": "BROWSER_ACTIVE_CONFIRMED"})"""

new_srv_post = """        elif self.path == "/api/browser_active":
            record_browser_activity()
            self.send_json_response({"status": "BROWSER_ACTIVE_CONFIRMED"})

        elif self.path == "/api/radio_yield":
            # Force background scanner to immediately yield BLE adapter to browser
            record_browser_activity()
            ble_state["status"] = "CHROME_LINK_ACTIVE"
            ble_state["contention_warning"] = False
            self.send_json_response({"status": "RADIO_YIELDED_TO_BROWSER"})"""

if old_srv_post in srv:
    srv = srv.replace(old_srv_post, new_srv_post)
    print("[+] Added /api/radio_yield to server.py!")

# Increase browser activity window to 60s
old_active_win = "return (time.time() - last_browser_activity) < 45.0"
new_active_win = "return (time.time() - last_browser_activity) < 60.0"
if old_active_win in srv:
    srv = srv.replace(old_active_win, new_active_win)
    print("[+] Increased browser activity priority window to 60s!")

with open(r"D:\veepoo\w11_htm_app\server.py", "w", encoding="utf-8") as f:
    f.write(srv)


# 2. Update index.html
with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

# Make pingBrowserActiveToDaemon ping whenever the tab is open, not just when connected
old_ping_fn = """    function pingBrowserActiveToDaemon() {
      if (bluetoothDevice && bluetoothDevice.gatt && bluetoothDevice.gatt.connected) {
        fetch('/api/browser_active', { method: 'POST' }).catch(() => {});
      }
    }
    setInterval(pingBrowserActiveToDaemon, 4000);"""

new_ping_fn = """    function pingBrowserActiveToDaemon() {
      // Keep Python daemon from stealing the radio whenever this web page is open
      fetch('/api/browser_active', { method: 'POST' }).catch(() => {});
    }
    setInterval(pingBrowserActiveToDaemon, 3000);
    pingBrowserActiveToDaemon(); // Ping immediately on page load"""

if old_ping_fn in html:
    html = html.replace(old_ping_fn, new_ping_fn)
    print("[+] Updated pingBrowserActiveToDaemon to ping unconditionally!")
else:
    print("[!] Warning: old_ping_fn did not match")

# In proceedWithGattConnection: yield radio before connect
old_proceed = """    async function proceedWithGattConnection() {
      document.getElementById("ribbonDevice").textContent = `${bluetoothDevice.name || "VEE"}`;
      document.getElementById("ribbonComm").textContent = "LINKING...";
      bluetoothDevice.addEventListener('gattserverdisconnected', onDisconnected);"""

new_proceed = """    async function proceedWithGattConnection() {
      // 1. Force Python daemon to release radio before Chrome opens GATT link
      try {
        await fetch('/api/radio_yield', { method: 'POST' });
        await new Promise(r => setTimeout(r, 200));
      } catch (e) {}

      document.getElementById("ribbonDevice").textContent = `${bluetoothDevice.name || "VEE"}`;
      document.getElementById("ribbonComm").textContent = "LINKING...";
      bluetoothDevice.addEventListener('gattserverdisconnected', onDisconnected);"""

if old_proceed in html:
    html = html.replace(old_proceed, new_proceed)
    print("[+] Added radio yield request to proceedWithGattConnection!")
else:
    print("[!] Warning: old_proceed did not match")

with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[✔] Updates successfully written!")
