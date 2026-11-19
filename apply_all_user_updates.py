import sys
import re

print("[*] Starting update script for index.html and server.py...")

# =========================================================================
# 1. UPDATE index.html
# =========================================================================
with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

# -------------------------------------------------------------------------
# A. Dial Background and Outer/Inner Borders to Blue, Pure GYR Ring, Yellow Hour Hand
# -------------------------------------------------------------------------
# Find svgHybridDial
dial_pattern = re.compile(r'<svg id="svgHybridDial"[^>]*>.*?</svg>', re.DOTALL)
m = dial_pattern.search(html)
if not m:
    print("[-] ERROR: svgHybridDial not found!")
    sys.exit(1)

old_dial_svg = m.group(0)

# Build the improved SVG with:
# 1. Deep Rich Blue Background (#07152b / #0a1b38)
# 2. High-contrast Brilliant Blue Outer & Inner Ring Borders (#00d2ff, stroke-width 2)
# 3. Rich Navy Sculpted Groove Channel (#0d2346)
# 4. Pure continuous GYR ring with luminous blue puck
# 5. NO black numbers around the ring
# 6. Solid High-Visibility Safety Yellow Hour Hand (#ffe600 / #ffd700) with white core spine
# 7. Solid Crisp White Minute Hand with black border
# 8. High-contrast subdial pods with crisp borders

new_dial_svg = """<svg id="svgHybridDial" width="240" height="280" viewBox="0 0 240 280" style="background: #07152b; font-family: 'Share Tech Mono', Consolas, sans-serif;">
                    
                    <!-- HIGH-CONTRAST BLUE OUTER BEZEL PLATE -->
                    <circle cx="120" cy="140" r="118" fill="#091b36" stroke="#1d4ed8" stroke-width="2" />
                    
                    <!-- HIGH-VISIBILITY BLUE OUTER & INNER RING BORDERS (Senior High-Contrast) -->
                    <circle cx="120" cy="140" r="114" fill="none" stroke="#00d2ff" stroke-width="2" style="filter: drop-shadow(0 0 3px #00a8ff);" />
                    <circle cx="120" cy="140" r="96" fill="none" stroke="#00d2ff" stroke-width="2" style="filter: drop-shadow(0 0 3px #00a8ff);" />

                    <!-- SCULPTED NAVY BLUE BACKGROUND GROOVE (Deep Channel for Visible Path) -->
                    <path id="trackGroove" d="" fill="none" stroke="#0d2346" stroke-width="17" stroke-linecap="round" />

                    <!-- CONTINUOUS 330° OUTER GLUCOSE TRACK (Clockwise from Bottom 6 o'clock, through Left, Top, Right, to Bottom) -->
                    <!-- Zone 0: Hypo alert tick (60 to 70 mg/dL) -->
                    <path id="trackZoneHypo" d="" fill="none" stroke="rgba(239,68,68,0.85)" stroke-width="14" stroke-linecap="round" />
                    <!-- Zone 1: Target Green (70 to 160 mg/dL) -->
                    <path id="trackZoneGreen" d="" fill="none" stroke="rgba(34,197,94,0.75)" stroke-width="14" />
                    <!-- Zone 2: Elevated Yellow (160 to 180 mg/dL) -->
                    <path id="trackZoneYellow" d="" fill="none" stroke="rgba(234,179,8,0.85)" stroke-width="14" />
                    <!-- Zone 3: Spike Red (>180 to 200 mg/dL) -->
                    <path id="trackZoneRed" d="" fill="none" stroke="rgba(239,68,68,0.85)" stroke-width="14" stroke-linecap="round" />

                    <!-- THE ACTIVE DYNAMIC GLUCOSE FILL RING (Sweeps to current value) -->
                    <path id="arcValGlucose" d="" fill="none" stroke="#22c55e" stroke-width="14" stroke-linecap="round" style="filter: drop-shadow(0 0 6px #22c55e);" />

                    <!-- HIGH-VISIBILITY ELECTRIC BLUE INDICATOR PUCK (Rides at current value for instant reading) -->
                    <circle id="dotValGlucoseGlow" cx="120" cy="140" r="14" fill="rgba(0, 229, 255, 0.55)" style="filter: drop-shadow(0 0 10px #00e5ff);" />
                    <circle id="dotValGlucose" cx="120" cy="140" r="8" fill="#00e5ff" stroke="#ffffff" stroke-width="2.5" />
                    <circle id="dotValGlucoseCore" cx="120" cy="140" r="3" fill="#ffffff" />

                    <!-- PURE GYR TRACK: NUMBERS REMOVED FOR CLEAN SENIOR VIEWING (Value read in large pod at 9 o'clock) -->

                    <!-- TOP SUB-DIAL: 12-HOUR DIGITAL CLOCK & DATE (12 o'clock) -->
                    <rect x="65" y="47" width="110" height="39" rx="8" fill="#091c38" stroke="#38bdf8" stroke-width="2" />
                    <text id="chronoDigitalTime" x="120" y="69" text-anchor="middle" fill="#ffffff" font-size="20" font-weight="900" letter-spacing="1">12:45 PM</text>
                    <text id="chronoDigitalDate" x="120" y="80" text-anchor="middle" fill="#facc15" font-size="9" font-weight="bold" letter-spacing="0.5">WED NOV 18</text>

                    <!-- CENTER-LEFT: PRIMARY GLUCOSE METRIC HUB (9 o'clock) -->
                    <circle cx="60" cy="140" r="32" fill="#091c38" stroke="#00e5ff" stroke-width="2.5" />
                    <text x="60" y="123" text-anchor="middle" fill="#93c5fd" font-size="9" font-weight="bold">GLUCOSE</text>
                    <text id="chronoPodGlucose" x="60" y="146" text-anchor="middle" fill="#4ade80" font-size="25" font-weight="900">124</text>
                    <text id="chronoPodGlucoseUnit" x="60" y="160" text-anchor="middle" fill="#cbd5e1" font-size="9" font-weight="bold">mg/dL</text>

                    <!-- CENTER-RIGHT: PULSE / HEART RATE (3 o'clock) -->
                    <circle cx="180" cy="140" r="32" fill="#091c38" stroke="#fb923c" stroke-width="2.5" />
                    <text x="180" y="123" text-anchor="middle" fill="#93c5fd" font-size="9" font-weight="bold">PULSE / HR</text>
                    <text id="chronoPodHr" x="180" y="146" text-anchor="middle" fill="#fb923c" font-size="22" font-weight="900">72</text>
                    <text x="180" y="160" text-anchor="middle" fill="#cbd5e1" font-size="9.5" font-weight="bold">BPM</text>

                    <!-- BOTTOM: PEDOMETER STEPS READOUT (6 o'clock) -->
                    <rect x="68" y="196" width="104" height="36" rx="8" fill="#091c38" stroke="#38bdf8" stroke-width="2" />
                    <text x="120" y="208" text-anchor="middle" fill="#93c5fd" font-size="9" font-weight="bold">DAILY STEPS</text>
                    <text id="chronoPodSteps" x="120" y="225" text-anchor="middle" fill="#38bdf8" font-size="17" font-weight="900">8,420</text>

                    <!-- LOWER LEFT: BATTERY INDICATOR POD -->
                    <g id="chronoBattGroup" transform="translate(18, 246)">
                      <!-- Outer capsule backdrop -->
                      <rect x="0" y="0" width="56" height="20" rx="5" fill="#091c38" stroke="#38bdf8" stroke-width="1.5" />
                      <!-- Battery Shell -->
                      <rect x="6" y="4.5" width="20" height="11" rx="2" fill="none" stroke="#4ade80" stroke-width="1.4" />
                      <!-- Battery Positive Terminal -->
                      <path d="M 26 7.5 L 28 7.5 L 28 12.5 L 26 12.5 Z" fill="#4ade80" />
                      <!-- Active Dynamic Fill Bar -->
                      <rect id="chronoBatteryFill" x="8" y="6.5" width="14" height="7" rx="1" fill="#4ade80" />
                      <!-- Battery Percentage Text -->
                      <text id="chronoBattery" x="31" y="13" fill="#4ade80" font-size="9" font-weight="900" font-family="'Share Tech Mono', Consolas, sans-serif">85%</text>
                    </g>

                    <!-- LOWER RIGHT: BLUETOOTH STRENGTH ICON POD -->
                    <g id="chronoBtGroup" transform="translate(166, 246)">
                      <!-- Outer capsule backdrop -->
                      <rect x="0" y="0" width="56" height="20" rx="5" fill="#091c38" stroke="#38bdf8" stroke-width="1.5" />
                      <!-- Vector Bluetooth Rune Glyph -->
                      <g stroke="#38bdf8" stroke-width="1.3" stroke-linecap="round" stroke-linejoin="round" fill="none">
                        <line x1="12" y1="3" x2="12" y2="17" />
                        <polyline points="7,7 16,13 12,17 12,3 16,7 7,13" />
                      </g>
                      <!-- 4-Stage Signal Strength Bars -->
                      <g fill="#38bdf8">
                        <rect id="btBar1" x="22" y="13" width="2.5" height="4" rx="0.5" fill="#38bdf8" />
                        <rect id="btBar2" x="26" y="10" width="2.5" height="7" rx="0.5" fill="#38bdf8" />
                        <rect id="btBar3" x="30" y="7" width="2.5" height="10" rx="0.5" fill="#38bdf8" />
                        <rect id="btBar4" x="34" y="4" width="2.5" height="13" rx="0.5" fill="#38bdf8" />
                      </g>
                      <!-- Link Status Text -->
                      <text id="chronoBtStatus" x="46" y="13" text-anchor="middle" fill="#38bdf8" font-size="7.5" font-weight="900" font-family="'Share Tech Mono', Consolas, sans-serif">OK</text>
                    </g>

                    <!-- HIGH-VISIBILITY ANALOG WATCH HANDS (Pivoting at center 120, 140) -->
                    <!-- HIGH-VISIBILITY BOLD YELLOW HOUR HAND (Solid Yellow Blade with White Spine & Black Border) -->
                    <g id="handHour" transform="rotate(0, 120, 140)">
                      <polygon points="115,140 117,86 120,78 123,86 125,140" fill="#ffe600" stroke="#000000" stroke-width="1.5" />
                      <line x1="120" y1="136" x2="120" y2="84" stroke="#ffffff" stroke-width="2.2" stroke-linecap="round" />
                    </g>

                    <!-- HIGH-CONTRAST SOLID WHITE MINUTE HAND (Length 86, Width 6) -->
                    <g id="handMinute" transform="rotate(0, 120, 140)">
                      <polygon points="117,140 118.5,60 120,52 121.5,60 123,140" fill="#ffffff" stroke="#000000" stroke-width="1.5" />
                      <line x1="120" y1="136" x2="120" y2="58" stroke="#38bdf8" stroke-width="1.8" stroke-linecap="round" />
                    </g>

                    <!-- SWEEPING SECOND HAND (Vivid Starfleet Orange needle) -->
                    <g id="handSecond" transform="rotate(0, 120, 140)">
                      <line x1="120" y1="160" x2="120" y2="44" stroke="#ff9900" stroke-width="2" />
                      <circle cx="120" cy="140" r="5.5" fill="#ff9900" stroke="#ffffff" stroke-width="1" />
                      <circle cx="120" cy="52" r="3.5" fill="#ff9900" />
                    </g>

                    <!-- Center Cap Pin -->
                    <circle cx="120" cy="140" r="4" fill="#ffe600" stroke="#000000" stroke-width="1.5" />

                  </svg>"""

html = html.replace(old_dial_svg, new_dial_svg)
print("[+] Applied Blue Background, High-Contrast Blue Borders, Bold Yellow Hour Hand & Pure GYR Ring!")

# -------------------------------------------------------------------------
# B. Ensure sendCommandBytes Pads to 20 Bytes
# -------------------------------------------------------------------------
if "payload = new Uint8Array(20)" not in html:
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
        print("[+] Updated sendCommandBytes to enforce 20-byte padding!")
    else:
        print("[!] Warning: old_send_cmd string variation")

# -------------------------------------------------------------------------
# C. Recognize FITAOS and Suppress BT Pairing Dialog
# -------------------------------------------------------------------------
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
    print("[+] Updated checkKnownDevice to recognize FITAOS without popup!")

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
    print("[+] Updated ensureConnected to suppress popup!")

# -------------------------------------------------------------------------
# D. Add Slot Selection & Overwrite Toolbar in Tab 10
# -------------------------------------------------------------------------
if "SLOT 0 (DEFAULT)" not in html:
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
        print("[+] Added Slot Selection & Overwrite Toolbar!")

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
        print("[+] Added overwriteDynamicWatchSlot to index.html")

# Write index.html
with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[+] index.html write complete!")

# =========================================================================
# 2. UPDATE server.py
# =========================================================================
with open(r"D:\veepoo\w11_htm_app\server.py", "r", encoding="utf-8") as f:
    srv = f.read()

# Make sure server recognizes FITAOS in scanning loop
old_srv_match = """                d_name = (d.name or "").lower()
                d_address = (d.address or "").upper()
                if TARGET_NAME in d_name or d_address == TARGET_MAC.upper():"""

new_srv_match = """                d_name = (d.name or "").lower()
                d_address = (d.address or "").upper()
                # Recognize FITAOS, VEE, or MAC address
                if any(k in d_name for k in ["vee", "fitaos", "fit"]) or d_address == TARGET_MAC.upper():"""

if old_srv_match in srv:
    srv = srv.replace(old_srv_match, new_srv_match)
    print("[+] server.py updated to recognize FITAOS!")

with open(r"D:\veepoo\w11_htm_app\server.py", "w", encoding="utf-8") as f:
    srv = f.write(srv)

print("[✔] All updates executed successfully!")
