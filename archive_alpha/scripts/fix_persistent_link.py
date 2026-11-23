import re

# =========================================================================
# 1. UPDATE SERVER.PY (Eliminate Bluetooth Radio Contention with Chrome)
# =========================================================================
server_path = r"D:\veepoo\w11_htm_app\server.py"
with open(server_path, "r", encoding="utf-8") as f:
    server_text = f.read()

# Add browser_activity_timestamp tracking
browser_flag_code = '''last_browser_activity = 0

def record_browser_activity():
    global last_browser_activity
    last_browser_activity = time.time()

def is_browser_active():
    # If browser sent a log or telemetry in the last 45 seconds, it is actively managing BLE
    return (time.time() - last_browser_activity) < 45.0
'''

if "def is_browser_active():" not in server_text:
    server_text = server_text.replace("sync_trigger_event = threading.Event()", "sync_trigger_event = threading.Event()\n" + browser_flag_code, 1)

# In do_POST of /api/log and /api/telemetry, record browser activity
server_text = server_text.replace('if self.path == "/api/log":', 'if self.path == "/api/log":\n            record_browser_activity()')
server_text = server_text.replace('elif self.path == "/api/telemetry":', 'elif self.path == "/api/telemetry":\n            record_browser_activity()')

# In run_ble_auto_sync_loop, if browser is active, do not connect via Bleak to avoid kicking Chrome off!
loop_check_target = '''            if target_device:
                ble_state["last_seen"] = datetime.datetime.now().isoformat()'''

loop_check_replacement = '''            if target_device:
                ble_state["last_seen"] = datetime.datetime.now().isoformat()
                
                # Check if Chrome Web Bluetooth is already active
                if is_browser_active():
                    print("[*] Browser Web Bluetooth is active in Chrome. Background daemon standing by (preventing link drops).")
                    ble_state["status"] = "BROWSER_LINK_ACTIVE"
                    sync_trigger_event.wait(timeout=25.0)
                    sync_trigger_event.clear()
                    continue'''

if loop_check_target in server_text and "is_browser_active():" not in server_text:
    server_text = server_text.replace(loop_check_target, loop_check_replacement, 1)

with open(server_path, "w", encoding="utf-8") as f:
    f.write(server_text)
print("server.py updated to prevent radio contention!")


# =========================================================================
# 2. UPDATE INDEX.HTML (Autonomous Background Reconnect Loop & Zero Re-Pair)
# =========================================================================
htm_path = r"D:\veepoo\w11_htm_app\index.html"
with open(htm_path, "r", encoding="utf-8") as f:
    htm_text = f.read()

# Replace onDisconnected() and add autoReconnectWatch()
old_onDisconnected = '''    function onDisconnected() {
      soundError();
      logLCARS("Subspace link severed: Watch entered sleep mode.", "err");
      document.getElementById("ribbonComm").textContent = "CLICK TO LINK";
      document.getElementById("ribbonComm").style.color = "var(--lcars-orange)";
    }'''

new_onDisconnected = '''    let isAutoReconnecting = false;
    let autoReconnectTimer = null;

    function onDisconnected() {
      logLCARS("Watch link temporarily severed (sleep or range). Auto-reconnecting in background...", "sys");
      document.getElementById("ribbonComm").textContent = "AUTO-RECONNECTING...";
      document.getElementById("ribbonComm").style.color = "var(--lcars-banana)";
      startAutoReconnectLoop();
    }

    async function startAutoReconnectLoop() {
      if (isAutoReconnecting) return;
      isAutoReconnecting = true;
      let attempt = 0;

      while (!gattServer || !gattServer.connected) {
        attempt++;
        try {
          const known = await checkKnownDevice();
          if (known) {
            bluetoothDevice = known;
            bluetoothDevice.removeEventListener('gattserverdisconnected', onDisconnected);
            bluetoothDevice.addEventListener('gattserverdisconnected', onDisconnected);
            
            gattServer = await bluetoothDevice.gatt.connect();
            if (gattServer.connected) {
              logLCARS(`[+] Watch link automatically restored (Attempt ${attempt})!`, "rx");
              await setupGattChannels();
              document.getElementById("ribbonComm").textContent = "LINKED & ONLINE";
              document.getElementById("ribbonComm").style.color = "var(--lcars-green)";
              isAutoReconnecting = false;
              return;
            }
          }
        } catch (err) {
          // Silent retry every 2.5 seconds
        }
        await new Promise(r => setTimeout(r, 2500));
      }
      isAutoReconnecting = false;
    }'''

if old_onDisconnected in htm_text:
    htm_text = htm_text.replace(old_onDisconnected, new_onDisconnected, 1)
    print("onDisconnected() upgraded to autonomous reconnect loop!")
else:
    print("WARNING: old_onDisconnected not found")

# Suppress unlinked banner forever if watch was ever previously paired
attempt_target = '''      } else {
        // Only if brand new setup
        logLCARS("No preconfigured transponder found. Ready for first-time pairing.", "sys");
        document.getElementById('unlinkedBanner').style.display = 'flex';
        document.getElementById('btnPairNew').style.display = 'flex';
        document.getElementById("ribbonComm").textContent = "AWAITING PAIR";
      }'''

attempt_replacement = '''      } else {
        const savedId = localStorage.getItem('veepoo_saved_id');
        if (savedId) {
          // Never ask to pair again! Keep searching silently in background
          logLCARS("Saved watch transponder recognized. Auto-connecting in background...", "sys");
          document.getElementById('unlinkedBanner').style.display = 'none';
          document.getElementById('btnPairNew').style.display = 'none';
          document.getElementById("ribbonComm").textContent = "AUTO-CONNECTING...";
          document.getElementById("ribbonComm").style.color = "var(--lcars-banana)";
          startAutoReconnectLoop();
        } else {
          // Brand new first-time setup
          logLCARS("No preconfigured transponder found. Ready for first-time pairing.", "sys");
          document.getElementById('unlinkedBanner').style.display = 'flex';
          document.getElementById('btnPairNew').style.display = 'flex';
          document.getElementById("ribbonComm").textContent = "AWAITING PAIR";
        }
      }'''

if attempt_target in htm_text:
    htm_text = htm_text.replace(attempt_target, attempt_replacement, 1)
    print("unlinkedBanner suppressed for returning paired watches!")
else:
    print("WARNING: attempt_target not found")

# Add periodic BLE keep-alive ping (keeps GATT alive so watch doesn't sleep)
keepalive_target = "    // Poll vault sync status every 15 seconds"
keepalive_code = '''    // BLE Keep-Alive Ping (prevents watch from entering deep sleep disconnect)
    setInterval(() => {
      if (txCharacteristic && gattServer && gattServer.connected) {
        // Lightweight battery query keep-alive ping
        const ping = new Uint8Array(20);
        ping[0] = 0xA0;
        try {
          if (txCharacteristic.writeValueWithoutResponse) {
            txCharacteristic.writeValueWithoutResponse(ping);
          }
        } catch (e) {}
      } else if (!isAutoReconnecting) {
        // If not connected, keep attempting background reconnect
        startAutoReconnectLoop();
      }
    }, 12000);

    // Poll vault sync status every 15 seconds'''

if keepalive_target in htm_text:
    htm_text = htm_text.replace(keepalive_target, keepalive_code, 1)
    print("GATT keep-alive ping installed!")
else:
    print("WARNING: keepalive_target not found")

with open(htm_path, "w", encoding="utf-8") as f:
    f.write(htm_text)
print("index.html successfully updated with persistent link recovery!")
