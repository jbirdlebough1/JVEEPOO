print("[*] Implementing fixes for CLE-003 and CLE-004...")

with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1. FIX CLE-004: True Link Status Watchdog
watchdog_code = """
    // =========================================================================
    // TRUE CONNECTION STATE WATCHDOG (CLE-004 FIX)
    // Only displays "LINKED & ONLINE" (Green) when GATT is TRULY connected & TX ready
    // =========================================================================
    function updateConnectionStateUI() {
      const isTrulyConnected = !!(bluetoothDevice && bluetoothDevice.gatt && bluetoothDevice.gatt.connected && txCharacteristic);
      const elComm = document.getElementById("ribbonComm");
      if (!elComm) return;

      if (isTrulyConnected) {
        if (elComm.textContent !== "LINKED & ONLINE") {
          elComm.textContent = "LINKED & ONLINE";
          elComm.style.color = "var(--lcars-green)";
        }
      } else if (isAutoReconnecting) {
        if (elComm.textContent !== "AUTO-RECONNECTING...") {
          elComm.textContent = "AUTO-RECONNECTING...";
          elComm.style.color = "var(--lcars-banana)";
        }
      } else {
        if (elComm.textContent !== "STANDBY (OFFLINE)") {
          elComm.textContent = "STANDBY (OFFLINE)";
          elComm.style.color = "var(--lcars-orange)";
        }
      }
    }
    setInterval(updateConnectionStateUI, 1000);
"""

if "function updateConnectionStateUI" not in html:
    pos_on_disc = html.find("function onDisconnected()")
    if pos_on_disc != -1:
        html = html[:pos_on_disc] + watchdog_code + "\n    " + html[pos_on_disc:]
        print("[+] Added updateConnectionStateUI watchdog (CLE-004 fix)!")

# Update onDisconnected to trigger watchdog immediately
old_on_disc = """    function onDisconnected() {
      logLCARS("Watch link temporarily severed (sleep or range). Auto-reconnecting in background...", "sys");
      document.getElementById("ribbonComm").textContent = "AUTO-RECONNECTING...";
      document.getElementById("ribbonComm").style.color = "var(--lcars-banana)";
      startAutoReconnectLoop();
    }"""

new_on_disc = """    function onDisconnected() {
      txCharacteristic = null;
      logLCARS("Watch link severed (screen asleep or out of range). Silently reconnecting in background...", "sys");
      updateConnectionStateUI();
      startAutoReconnectLoop();
    }"""

if old_on_disc in html:
    html = html.replace(old_on_disc, new_on_disc)
    print("[+] Updated onDisconnected to nullify txCharacteristic and update UI!")


# 2. FIX CLE-003: Suppress pairNewWatchPrompt in ensureConnected (Zero-click auto-connect)
old_ensure = """      if (dev) {
        bluetoothDevice = dev;
        await proceedWithGattConnection();
        return !!txCharacteristic;
      } else {
        // Try getting any known device without triggering popup
        if (navigator.bluetooth && navigator.bluetooth.getDevices) {
          try {
            const devs = await navigator.bluetooth.getDevices();
            if (devs && devs.length > 0) {
              bluetoothDevice = devs[0];
              localStorage.setItem('veepoo_saved_id', devs[0].id);
              await proceedWithGattConnection();
              return !!txCharacteristic;
            }
          } catch(e) {}
        }
        await pairNewWatchPrompt();
        return !!txCharacteristic;
      }"""

new_ensure = """      if (dev) {
        bluetoothDevice = dev;
        await proceedWithGattConnection();
        return !!txCharacteristic;
      } else {
        // Zero-click: Silently try any authorized device from Chrome permissions
        if (navigator.bluetooth && navigator.bluetooth.getDevices) {
          try {
            const devs = await navigator.bluetooth.getDevices();
            if (devs && devs.length > 0) {
              bluetoothDevice = devs[0];
              localStorage.setItem('veepoo_saved_id', devs[0].id);
              await proceedWithGattConnection();
              return !!txCharacteristic;
            }
          } catch(e) {}
        }
        // NEVER trigger pairing popup automatically! Log standby and let user initiate manually
        logLCARS("[*] Watch sleeping or out of range. Background auto-link active (no popup).", "sys");
        updateConnectionStateUI();
        return false;
      }"""

if old_ensure in html:
    html = html.replace(old_ensure, new_ensure)
    print("[+] Suppressed automatic pairing popup in ensureConnected (CLE-003 fix)!")
else:
    print("[!] Warning: old_ensure not matched")

# Update proceedWithGattConnection so it only sets LINKED & ONLINE when setupGattChannels actually succeeds
old_proceed_conn = """      logLCARS("[+] GATT Subspace connection established!", "rx");
      document.getElementById("ribbonComm").textContent = "LINKED & ONLINE";
      document.getElementById("ribbonComm").style.color = "var(--lcars-green)";
      document.getElementById("ribbonDevice").textContent = `${bluetoothDevice.name || "VEE"}`;

      await setupGattChannels();
      populateSetupFromDevice();"""

new_proceed_conn = """      logLCARS("[+] GATT Subspace connection established!", "rx");
      document.getElementById("ribbonDevice").textContent = `${bluetoothDevice.name || "VEE"}`;

      await setupGattChannels();
      updateConnectionStateUI();
      if (txCharacteristic) {
        logLCARS("[★] Watch GATT channels ready and verified online!", "rx");
        populateSetupFromDevice();
      } else {
        logLCARS("[!] GATT connected but command channel not ready. Retrying...", "err");
      }"""

if old_proceed_conn in html:
    html = html.replace(old_proceed_conn, new_proceed_conn)
    print("[+] Updated proceedWithGattConnection to verify channels before setting online status!")
else:
    print("[!] Warning: old_proceed_conn not matched")

with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[✔] CLE-003 and CLE-004 fixes successfully applied to index.html!")
