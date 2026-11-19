import re

# =========================================================================
# 1. UPDATE index.html
# =========================================================================
with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1A. Remove the numbers around the ring (pure GYR ring)
# Find the numbers block:
num_start = html.find('<!-- LARGE, HIGH-CONTRAST THRESHOLD NUMBERS')
if num_start == -1:
    num_start = html.find('<g font-size="11" font-weight="900"')

if num_start != -1:
    num_end = html.find('</g>', num_start)
    if num_end != -1:
        html = html[:num_start] + "<!-- PURE GYR (GREEN-YELLOW-RED) CONTINUOUS RING - NUMBERS REMOVED FOR CLEAN SENIOR VIEWING -->" + html[num_end+4:]
        print("SUCCESS: Removed black numbers from around the glucose ring!")
else:
    print("WARNING: numbers block not found in index.html")

# 1B. Ensure sendCommandBytes ALWAYS pads to exactly 20 bytes
old_send_cmd = """    async function sendCommandBytes(bytes) {
      if (!txCharacteristic || !bluetoothDevice || !bluetoothDevice.gatt || !bluetoothDevice.gatt.connected) {
        const ok = await ensureConnected();
        if (!ok || !txCharacteristic) {
          soundError();
          logLCARS("Error: Could not connect to watch. Ensure watch screen is awake.", "err");
          return;
        }
      }
      try {
        const hex = Array.from(bytes).map(b => b.toString(16).padStart(2, '0')).join(' ');
        logLCARS(`TX [${txCharacteristic.uuid.substring(0, 8)}]: ${hex}`, "tx");
        if (txCharacteristic.properties.write) {
          await txCharacteristic.writeValueWithResponse(bytes);
        } else {
          await txCharacteristic.writeValueWithoutResponse(bytes);
        }
      } catch (err) {
        soundError();
        logLCARS(`TX Error: ${err.message}`, "err");
      }
    }"""

new_send_cmd = """    async function sendCommandBytes(bytes) {
      if (!txCharacteristic || !bluetoothDevice || !bluetoothDevice.gatt || !bluetoothDevice.gatt.connected) {
        const ok = await ensureConnected();
        if (!ok || !txCharacteristic) {
          soundError();
          logLCARS("Error: Could not connect to watch. Ensure watch screen is awake.", "err");
          return;
        }
      }
      // Mandatory: Pad every Veepoo GATT command to exactly 20 bytes
      const payload = new Uint8Array(20);
      for (let i = 0; i < bytes.length && i < 20; i++) {
        payload[i] = bytes[i];
      }
      try {
        const hex = Array.from(payload).map(b => b.toString(16).padStart(2, '0')).join(' ');
        logLCARS(`TX [${txCharacteristic.uuid.substring(0, 8)}]: ${hex}`, "tx");
        if (txCharacteristic.properties.write) {
          await txCharacteristic.writeValueWithResponse(payload);
        } else {
          await txCharacteristic.writeValueWithoutResponse(payload);
        }
      } catch (err) {
        soundError();
        logLCARS(`TX Error: ${err.message}`, "err");
      }
    }"""

if old_send_cmd in html:
    html = html.replace(old_send_cmd, new_send_cmd)
    print("SUCCESS: Updated sendCommandBytes to enforce 20-byte padding!")
else:
    print("WARNING: old_send_cmd not matched")

# 1C. Eliminate Windows 11 BT Pairing Dialog: Check for FITAOS and auto-connect
old_check_known = """async function checkKnownDevice() {
      if (navigator.bluetooth && navigator.bluetooth.getDevices) {
        try {
          const devices = await navigator.bluetooth.getDevices();
          if (devices && devices.length > 0) {
            const savedId = localStorage.getItem('veepoo_saved_id');
            const target = devices.find(d => d.id === savedId) || 
                           devices.find(d => d.name && d.name.toUpperCase().includes('VEE')) ||
                           devices[0];
            if (target) return target;
          }
        } catch (e) {}
      }
      return null;
    }"""

new_check_known = """async function checkKnownDevice() {
      if (navigator.bluetooth && navigator.bluetooth.getDevices) {
        try {
          const devices = await navigator.bluetooth.getDevices();
          if (devices && devices.length > 0) {
            const savedId = localStorage.getItem('veepoo_saved_id');
            // Recognize FITAOS, VEE, or ANY previously paired device
            const target = (savedId ? devices.find(d => d.id === savedId) : null) || 
                           devices.find(d => d.name && (
                             d.name.toUpperCase().includes('FITAOS') || 
                             d.name.toUpperCase().includes('VEE') || 
                             d.name.toUpperCase().includes('FIT')
                           )) ||
                           devices[0];
            if (target) {
              localStorage.setItem('veepoo_saved_id', target.id);
              return target;
            }
          }
        } catch (e) {}
      }
      return null;
    }"""

if old_check_known in html:
    html = html.replace(old_check_known, new_check_known)
    print("SUCCESS: Updated checkKnownDevice to recognize FITAOS without popup!")
else:
    print("WARNING: old_check_known not matched")

# In ensureConnected(), NEVER call pairNewWatchPrompt() if getDevices() has any device
old_ensure = """      if (dev) {
        bluetoothDevice = dev;
        await proceedWithGattConnection();
        return !!txCharacteristic;
      } else {
        await pairNewWatchPrompt();
        return !!txCharacteristic;
      }"""

new_ensure = """      if (dev) {
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

if old_ensure in html:
    html = html.replace(old_ensure, new_ensure)
    print("SUCCESS: Updated ensureConnected to suppress popup!")
else:
    print("WARNING: old_ensure not matched")

# 1D. Add Slot Overwrite Toolbar in Tab 10
old_top_toolbar = """              <div style="display: flex; gap: 8px; flex-wrap: wrap;">
                <button class="lcars-btn btn-banana" style="font-size: 0.82rem; padding: 6px 14px; font-weight: bold; cursor: pointer;" onclick="applyWatchFace(0, 'STYLE 0: CLEAN DEFAULT ROM FACE')">
                  ⚡ RESTORE CLEAN ORIGINAL WATCH FACE (STYLE 0)
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.8rem; padding: 6px 12px; height: auto; cursor: pointer;" onclick="applyWatchFace(1, 'STYLE 1: SPORT ANALOG')">
                  <span>STYLE 1</span>
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.8rem; padding: 6px 12px; height: auto; cursor: pointer;" onclick="applyWatchFace(2, 'STYLE 2: BIG DIGITAL')">
                  <span>STYLE 2</span>
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.8rem; padding: 6px 12px; height: auto; cursor: pointer;" onclick="applyWatchFace(3, 'STYLE 3: CHRONOMETER')">
                  <span>STYLE 3</span>
                </button>
                <button class="lcars-pill pill-lilac" style="font-size: 0.8rem; padding: 6px 12px; height: auto; cursor: pointer;" onclick="queryCurrentWatchFace()">
                  <span>🔍 AUDIT ACTIVE FACE</span>
                </button>
              </div>"""

new_top_toolbar = """              <div style="display: flex; gap: 6px; flex-wrap: wrap; align-items: center;">
                <span style="font-size: 0.75rem; color: var(--lcars-banana); font-weight: bold; text-transform: uppercase;">SELECT / OVERWRITE SLOT:</span>
                <button class="lcars-btn btn-banana" style="font-size: 0.78rem; padding: 5px 12px; font-weight: bold; cursor: pointer;" onclick="applyWatchFace(0, 'SLOT 0: CLEAN DEFAULT ROM')">
                  ⚡ SLOT 0 (DEFAULT)
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(1, 'SLOT 1: SPORT ANALOG')">
                  <span>SLOT 1</span>
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(2, 'SLOT 2: BIG DIGITAL')">
                  <span>SLOT 2</span>
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(3, 'SLOT 3: CHRONOMETER')">
                  <span>SLOT 3</span>
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(4, 'SLOT 4: HEALTH MATRIX')">
                  <span>SLOT 4</span>
                </button>
                <button class="lcars-pill pill-orange" style="font-size: 0.78rem; padding: 5px 12px; height: auto; cursor: pointer;" onclick="overwriteDynamicWatchSlot(1)" title="Activate Dynamic Custom Slot on Watch">
                  <span>⚡ OVERWRITE CUSTOM SLOT</span>
                </button>
                <button class="lcars-pill pill-lilac" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="queryCurrentWatchFace()">
                  <span>🔍 AUDIT SLOTS</span>
                </button>
              </div>"""

if old_top_toolbar in html:
    html = html.replace(old_top_toolbar, new_top_toolbar)
    print("SUCCESS: Added Slot Selection & Overwrite Toolbar!")
else:
    print("WARNING: old_top_toolbar not matched")

# Add overwriteDynamicWatchSlot function
if 'function overwriteDynamicWatchSlot' not in html:
    overwrite_fn = """
    // Directly commands watch to dynamic custom slot (with exact 20-byte packet)
    async function overwriteDynamicWatchSlot(slotNum) {
      soundButtonPress();
      logLCARS(`[*] Directing watch to Overwrite Slot ${slotNum || 1} (Opcode 0xC7)...`, "tx");
      // Opcode 0xC7: sub-cmd 0x01, slotIndex = 1, uiFromType = 2 (Custom Slot)
      const pkt = new Uint8Array(20);
      pkt[0] = 0xC7;
      pkt[1] = 0x01;
      pkt[2] = (slotNum || 1) & 0xFF;
      pkt[3] = 0x02; // Custom Dynamic Slot
      await sendCommandBytes(pkt);
      document.getElementById('lblCurrentActiveFace').textContent = `CUSTOM OVERWRITE SLOT ${slotNum || 1}`;
      document.getElementById('lblActiveFaceSource').textContent = "SPI FLASH CUSTOM REGISTER (0x02)";
      logLCARS(`[+] Overwrite Slot ${slotNum || 1} selected on watch!`, "rx");
      
      setTimeout(() => {
        setWatch12HourFormat();
      }, 350);
    }
"""
    pos_scr = html.find('async function applyWatchFace')
    if pos_scr != -1:
        html = html[:pos_scr] + overwrite_fn + "\n    " + html[pos_scr:]
        print("SUCCESS: Added overwriteDynamicWatchSlot to index.html")

with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

# =========================================================================
# 2. UPDATE server.py to recognize FITAOS in background daemon
# =========================================================================
with open(r"D:\veepoo\w11_htm_app\server.py", "r", encoding="utf-8") as f:
    srv = f.read()

old_srv_match = """                d_name = (d.name or "").lower()
                d_address = (d.address or "").upper()
                if TARGET_NAME in d_name or d_address == TARGET_MAC.upper():"""

new_srv_match = """                d_name = (d.name or "").lower()
                d_address = (d.address or "").upper()
                # Recognize FITAOS, VEE, or MAC address
                if any(k in d_name for k in ["vee", "fitaos", "fit"]) or d_address == TARGET_MAC.upper():"""

if old_srv_match in srv:
    srv = srv.replace(old_srv_match, new_srv_match)
    print("SUCCESS: server.py updated to recognize FITAOS!")

with open(r"D:\veepoo\w11_htm_app\server.py", "w", encoding="utf-8") as f:
    f.write(srv)

print("ALL UPDATES COMPLETE!")
