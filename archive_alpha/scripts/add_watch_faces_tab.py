import re

file_path = r"D:\veepoo\w11_htm_app\index.html"
with open(file_path, "r", encoding="utf-8") as f:
    text = f.read()

# 1. Add Sidebar Nav Button for Tab 10
nav_target = '''      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span>09-ADA TRADES</span>
      </button>'''

nav_replacement = '''      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span>09-ADA TRADES</span>
      </button>

      <button id="navTabWatchFaces" class="lcars-pill pill-lilac" onclick="switchTab('watchfaces')">
        <span>10-WATCH FACES</span>
      </button>'''

if nav_target in text:
    text = text.replace(nav_target, nav_replacement, 1)
    print("Sidebar navigation button for Tab 10 added")
else:
    print("WARNING: nav_target not found")

# 2. Add Tab 10 HTML Content right before </main>
tab_content = '''
      <!-- =====================================================================
           TAB 10: WATCH FACES & DISPLAY CUSTOMIZATION
           Hardware Dial Matrix (0xC7), Screen Timeout (0xB4), Brightness (0xB1)
           ===================================================================== -->
      <div id="pageWatchFaces" class="tab-page">
        <div style="display: flex; flex-direction: column; gap: 14px;">

          <!-- Top Status & Active Face Header -->
          <div class="card-box" style="border-left: 6px solid var(--lcars-lilac);">
            <div class="box-header" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;">
              <div>
                <span class="box-title" style="color: var(--lcars-lilac);">VEEPOO 5010 WATCH FACE &amp; DISPLAY HARDWARE CONTROLLER</span>
                <div style="font-size: 0.85rem; color: var(--lcars-text); margin-top: 2px;">
                  GATT Protocol Opcode <strong>0xC7</strong> (Screen Style) • Opcode <strong>0xB4</strong> (Screen Timeout) • Opcode <strong>0xB1</strong> (Backlight Brightness)
                </div>
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-pill pill-lilac" style="font-size: 0.8rem; padding: 6px 14px; height: auto; cursor: pointer;" onclick="queryCurrentWatchFace()">
                  <span>🔍 IDENTIFY ACTIVE FACE FROM WATCH</span>
                </button>
              </div>
            </div>

            <div class="box-content" style="padding: 12px 16px;">
              <div style="display: flex; align-items: center; justify-content: space-between; flex-wrap: wrap; gap: 12px; background: rgba(204, 153, 204, 0.1); border: 1px solid var(--lcars-lilac); border-radius: 6px; padding: 12px 18px;">
                <div>
                  <div style="font-size: 0.75rem; color: var(--lcars-gray); text-transform: uppercase;">ACTIVE HARDWARE DIAL ON WATCH</div>
                  <div id="lblCurrentActiveFace" style="font-size: 1.4rem; font-weight: bold; color: var(--lcars-banana);">STYLE 0: BIO-METABOLIC LCARS CONSOLE</div>
                  <div style="font-size: 0.82rem; color: var(--lcars-text); margin-top: 2px;">
                    Dial Source: <strong style="color: var(--lcars-green);" id="lblActiveFaceSource">FIRMWARE ROM DEFAULT (0x00)</strong> • Display Slot: <span id="lblActiveFaceSlot">Slot 0</span>
                  </div>
                </div>
                <div id="badgeFaceStatus" style="background: var(--lcars-green); color: var(--lcars-black); font-weight: bold; padding: 4px 12px; border-radius: 4px; font-size: 0.85rem;">
                  CONFIRMED ACTIVE
                </div>
              </div>
            </div>
          </div>

          <!-- Section 1: Built-In Watch Face Gallery -->
          <div class="card-box">
            <div class="box-header">
              <span class="box-title" style="color: var(--lcars-banana);">BUILT-IN HARDWARE WATCH FACE GALLERY (OPCODE 0xC7)</span>
            </div>
            <div class="box-content" style="padding: 16px;">
              <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 16px;" id="watchFaceGalleryGrid">

                <!-- Face 0: Bio-Metabolic LCARS Console -->
                <div class="watchface-card" id="cardFace0" style="background: rgba(12, 14, 20, 0.95); border: 2px solid var(--lcars-banana); border-radius: 10px; padding: 14px; display: flex; flex-direction: column; justify-content: space-between;">
                  <div>
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                      <strong style="color: var(--lcars-banana); font-size: 1.05rem;">STYLE 0: BIO-LCARS DASHBOARD</strong>
                      <span class="lcars-pill pill-banana" style="font-size: 0.7rem; padding: 2px 8px; height: auto;">RECOMMENDED</span>
                    </div>
                    <!-- Mock Preview Frame -->
                    <div style="width: 100%; height: 160px; background: #050608; border: 2px solid #222; border-radius: 8px; padding: 10px; display: flex; flex-direction: column; justify-content: space-between; box-shadow: inset 0 0 10px rgba(0,0,0,0.8);">
                      <div style="display: flex; justify-content: space-between; font-size: 0.75rem; color: var(--lcars-orange);">
                        <span>LCARS 5010</span>
                        <span>🔋 85%</span>
                      </div>
                      <div style="text-align: center;">
                        <div style="font-size: 2.2rem; font-weight: 700; color: #fff; letter-spacing: 2px; font-family: var(--lcars-mono);">12:45</div>
                        <div style="font-size: 0.75rem; color: var(--lcars-banana);">WED NOV 18</div>
                      </div>
                      <div style="display: flex; justify-content: space-between; font-size: 0.72rem; color: var(--lcars-green); border-top: 1px solid #1a2332; padding-top: 4px;">
                        <span>🩸 124 mg/dL</span>
                        <span>❤️ 72 BPM</span>
                        <span>🏃 8,420</span>
                      </div>
                    </div>
                    <div style="font-size: 0.8rem; color: var(--lcars-text); margin-top: 10px;">
                      Full clinical layout showing continuous optical glucose, heart rate, pedometer, and battery gauge in high-contrast Starfleet palette.
                    </div>
                  </div>
                  <button class="lcars-btn btn-banana" style="margin-top: 12px; width: 100%; font-weight: bold;" onclick="applyWatchFace(0, 'STYLE 0: BIO-LCARS DASHBOARD')">
                    APPLY THIS WATCH FACE
                  </button>
                </div>

                <!-- Face 1: Minimalist Senior High-Contrast Digital -->
                <div class="watchface-card" id="cardFace1" style="background: rgba(12, 14, 20, 0.95); border: 1px solid var(--lcars-border); border-radius: 10px; padding: 14px; display: flex; flex-direction: column; justify-content: space-between;">
                  <div>
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                      <strong style="color: var(--lcars-peach); font-size: 1.05rem;">STYLE 1: SENIOR HIGH-CONTRAST</strong>
                      <span class="lcars-pill pill-peach" style="font-size: 0.7rem; padding: 2px 8px; height: auto;">LARGE TYPE</span>
                    </div>
                    <!-- Mock Preview Frame -->
                    <div style="width: 100%; height: 160px; background: #000; border: 2px solid #333; border-radius: 8px; padding: 10px; display: flex; flex-direction: column; justify-content: center; align-items: center; box-shadow: inset 0 0 10px rgba(0,0,0,0.8);">
                      <div style="font-size: 0.85rem; color: var(--lcars-peach); margin-bottom: 2px;">WEDNESDAY</div>
                      <div style="font-size: 3.2rem; font-weight: 900; color: #fff; line-height: 1; font-family: var(--lcars-mono);">12:45</div>
                      <div style="font-size: 1.1rem; color: var(--lcars-green); font-weight: bold; margin-top: 4px;">🩸 124 mg/dL</div>
                    </div>
                    <div style="font-size: 0.8rem; color: var(--lcars-text); margin-top: 10px;">
                      Extra-large bold numerals engineered for senior readability and quick visual glance without reading glasses.
                    </div>
                  </div>
                  <button class="lcars-btn btn-peach" style="margin-top: 12px; width: 100%; font-weight: bold;" onclick="applyWatchFace(1, 'STYLE 1: SENIOR HIGH-CONTRAST')">
                    APPLY THIS WATCH FACE
                  </button>
                </div>

                <!-- Face 2: Classic Medical Chronograph -->
                <div class="watchface-card" id="cardFace2" style="background: rgba(12, 14, 20, 0.95); border: 1px solid var(--lcars-border); border-radius: 10px; padding: 14px; display: flex; flex-direction: column; justify-content: space-between;">
                  <div>
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                      <strong style="color: var(--lcars-blue); font-size: 1.05rem;">STYLE 2: CLINICAL CHRONOGRAPH</strong>
                      <span class="lcars-pill pill-blue" style="font-size: 0.7rem; padding: 2px 8px; height: auto;">ANALOG</span>
                    </div>
                    <!-- Mock Preview Frame -->
                    <div style="width: 100%; height: 160px; background: #070a10; border: 2px solid #1a2332; border-radius: 8px; padding: 10px; display: flex; flex-direction: column; justify-content: center; align-items: center; position: relative;">
                      <svg width="120" height="120" viewBox="0 0 120 120">
                        <circle cx="60" cy="60" r="54" fill="none" stroke="#6699cc" stroke-width="2" />
                        <!-- Hour ticks -->
                        <line x1="60" y1="8" x2="60" y2="16" stroke="#ffcc66" stroke-width="3" />
                        <line x1="112" y1="60" x2="104" y2="60" stroke="#99ccff" stroke-width="2" />
                        <line x1="60" y1="112" x2="60" y2="104" stroke="#99ccff" stroke-width="2" />
                        <line x1="8" y1="60" x2="16" y2="60" stroke="#99ccff" stroke-width="2" />
                        <!-- Hands -->
                        <line x1="60" y1="60" x2="60" y2="28" stroke="#ffaa55" stroke-width="4" stroke-linecap="round" />
                        <line x1="60" y1="60" x2="88" y2="44" stroke="#ffffff" stroke-width="2" stroke-linecap="round" />
                        <circle cx="60" cy="60" r="4" fill="#ff9900" />
                        <text x="60" y="80" text-anchor="middle" fill="#55ff77" font-size="9" font-weight="bold">124 mg/dL</text>
                      </svg>
                    </div>
                    <div style="font-size: 0.8rem; color: var(--lcars-text); margin-top: 10px;">
                      Sophisticated analog dial layout with clinical sub-dial for blood glucose and physical activity progress ring.
                    </div>
                  </div>
                  <button class="lcars-btn btn-blue" style="margin-top: 12px; width: 100%; font-weight: bold;" onclick="applyWatchFace(2, 'STYLE 2: CLINICAL CHRONOGRAPH')">
                    APPLY THIS WATCH FACE
                  </button>
                </div>

                <!-- Face 3: High-Aerobic Cardio Chronometer -->
                <div class="watchface-card" id="cardFace3" style="background: rgba(12, 14, 20, 0.95); border: 1px solid var(--lcars-border); border-radius: 10px; padding: 14px; display: flex; flex-direction: column; justify-content: space-between;">
                  <div>
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                      <strong style="color: var(--lcars-green); font-size: 1.05rem;">STYLE 3: SPORT &amp; METs CHRONO</strong>
                      <span class="lcars-pill pill-green" style="font-size: 0.7rem; padding: 2px 8px; height: auto;">AEROBIC</span>
                    </div>
                    <!-- Mock Preview Frame -->
                    <div style="width: 100%; height: 160px; background: #04080e; border: 2px solid #1a2332; border-radius: 8px; padding: 10px; display: flex; flex-direction: column; justify-content: space-between;">
                      <div style="display: flex; justify-content: space-between; font-size: 0.72rem; color: var(--lcars-banana);">
                        <span>ZONE 2 (FAT BURN)</span>
                        <span>3.8 METs</span>
                      </div>
                      <div style="display: flex; align-items: baseline; justify-content: center; gap: 8px;">
                        <span style="font-size: 2.4rem; font-weight: bold; color: var(--lcars-green); font-family: var(--lcars-mono);">128</span>
                        <span style="font-size: 0.9rem; color: var(--lcars-gray);">BPM</span>
                      </div>
                      <!-- 5-Zone Mini Bar -->
                      <div style="display: flex; height: 8px; border-radius: 4px; overflow: hidden; background: #222;">
                        <div style="width: 20%; background: #6699cc;"></div>
                        <div style="width: 35%; background: #55ff77;"></div>
                        <div style="width: 25%; background: #ffaa55;"></div>
                        <div style="width: 15%; background: #ff9900;"></div>
                        <div style="width: 5%; background: #cc3333;"></div>
                      </div>
                      <div style="display: flex; justify-content: space-between; font-size: 0.72rem; color: #fff;">
                        <span>PACE: 15:40 /mi</span>
                        <span>428 KCAL</span>
                      </div>
                    </div>
                    <div style="font-size: 0.8rem; color: var(--lcars-text); margin-top: 10px;">
                      Dedicated exercise workout dial with live cardiac effort zone bar, real-time pulse, and active calorie burn.
                    </div>
                  </div>
                  <button class="lcars-btn btn-green" style="margin-top: 12px; width: 100%; font-weight: bold;" onclick="applyWatchFace(3, 'STYLE 3: SPORT & METs CHRONO')">
                    APPLY THIS WATCH FACE
                  </button>
                </div>

              </div>
            </div>
          </div>

          <!-- Section 2: Display Hardware Backlight & Timeout Controls -->
          <div class="card-box">
            <div class="box-header">
              <span class="box-title" style="color: var(--lcars-orange);">DISPLAY TIMEOUT &amp; BACKLIGHT HARDWARE CONTROLS (OPCODES 0xB4 &amp; 0xB1)</span>
            </div>
            <div class="box-content" style="padding: 16px;">
              <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 16px;">

                <!-- Screen Timeout Setting (0xB4) -->
                <div style="background: rgba(255, 153, 0, 0.06); border: 1px solid var(--lcars-orange); border-radius: 8px; padding: 14px;">
                  <strong style="color: var(--lcars-orange); font-size: 1rem;">SCREEN WAKE TIMEOUT (OPCODE 0xB4)</strong>
                  <div style="font-size: 0.82rem; color: var(--lcars-text); margin-top: 4px; margin-bottom: 12px;">
                    Controls how long the watch display remains illuminated before entering low-power sleep.
                  </div>
                  <div style="display: flex; flex-wrap: wrap; gap: 8px;">
                    <button id="btnTimeout5" class="lcars-btn btn-orange" style="flex: 1; min-width: 65px;" onclick="applyScreenTimeout(5)">5 SEC</button>
                    <button id="btnTimeout10" class="lcars-btn btn-orange" style="flex: 1; min-width: 65px;" onclick="applyScreenTimeout(10)">10 SEC</button>
                    <button id="btnTimeout15" class="lcars-btn btn-orange" style="flex: 1; min-width: 65px;" onclick="applyScreenTimeout(15)">15 SEC</button>
                    <button id="btnTimeout30" class="lcars-btn btn-orange" style="flex: 1; min-width: 65px;" onclick="applyScreenTimeout(30)">30 SEC</button>
                  </div>
                  <div style="font-size: 0.78rem; color: var(--lcars-gray); margin-top: 8px;" id="lblTimeoutStatus">
                    Current Watch Setting: 10 Seconds (Nominal Battery Balance)
                  </div>
                </div>

                <!-- Backlight Brightness Level (0xB1) -->
                <div style="background: rgba(153, 204, 255, 0.06); border: 1px solid var(--lcars-blue); border-radius: 8px; padding: 14px;">
                  <strong style="color: var(--lcars-blue); font-size: 1rem;">BACKLIGHT BRIGHTNESS &amp; NIGHT DIMMING (OPCODE 0xB1)</strong>
                  <div style="font-size: 0.82rem; color: var(--lcars-text); margin-top: 4px; margin-bottom: 12px;">
                    Sets the optical brightness nits and activates automatic nighttime eye-comfort dimming.
                  </div>
                  <div style="display: flex; flex-wrap: wrap; gap: 8px;">
                    <button class="lcars-btn btn-blue" style="flex: 1; min-width: 60px;" onclick="applyScreenBrightness(1)">LVL 1 (20%)</button>
                    <button class="lcars-btn btn-blue" style="flex: 1; min-width: 60px;" onclick="applyScreenBrightness(2)">LVL 2 (40%)</button>
                    <button class="lcars-btn btn-blue" style="flex: 1; min-width: 60px;" onclick="applyScreenBrightness(3)">LVL 3 (60%)</button>
                    <button class="lcars-btn btn-blue" style="flex: 1; min-width: 60px;" onclick="applyScreenBrightness(4)">LVL 4 (80%)</button>
                    <button class="lcars-btn btn-blue" style="flex: 1; min-width: 60px;" onclick="applyScreenBrightness(5)">LVL 5 (100%)</button>
                  </div>
                  <div style="font-size: 0.78rem; color: var(--lcars-gray); margin-top: 8px;">
                    Night Dimming Window: <strong>22:00 to 07:00</strong> automatically drops to Level 1.
                  </div>
                </div>

              </div>
            </div>
          </div>

          <!-- Section 3: Custom Dial Image Architecture -->
          <div class="card-box">
            <div class="box-header">
              <span class="box-title" style="color: var(--lcars-peach);">CUSTOM DIAL IMAGE SPECIFICATION (MODEL 5010 HARDWARE)</span>
            </div>
            <div class="box-content" style="padding: 14px 18px; font-size: 0.85rem; line-height: 1.5; color: var(--lcars-text);">
              <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(260px, 1fr)); gap: 14px;">
                <div>
                  <strong style="color: var(--lcars-banana);">Hardware Display Specifications:</strong>
                  <ul style="margin: 4px 0 0 16px; padding: 0;">
                    <li>Resolution: <strong>240 × 280 pixels</strong> (IPS High-Color Panel)</li>
                    <li>Color Depth: <strong>16-bit RGB565 Little-Endian</strong> (65,536 colors)</li>
                    <li>Custom Slot: <code>uiFromType = 0x02 (CUSTOM)</code></li>
                    <li>Transmission Mode: 20-byte BLE MTU packet chunker via characteristic <code>0xF0080003</code></li>
                  </ul>
                </div>
                <div>
                  <strong style="color: var(--lcars-banana);">Medical Safety Guardrails:</strong>
                  <ul style="margin: 4px 0 0 16px; padding: 0;">
                    <li>Mandatory High Contrast: Time and glucose digits must maintain at least 4.5:1 contrast against background image.</li>
                    <li>Zero Clinical Clutter: Critical alarms (e.g. Hypoglycemia &lt;70 mg/dL, Tachycardia &gt;120 BPM) override custom dial art.</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
'''

main_close = '</main>'
if main_close in text:
    text = text.replace(main_close, tab_content + '\n    </main>', 1)
    print("Tab 10 HTML Content inserted successfully")
else:
    print("WARNING: </main> not found")

# 3. Add Tab 10 to switchTab() function
switch_target = "      } else if (tabId === 'help') {"
switch_replacement = """      } else if (tabId === 'watchfaces') {
        document.getElementById('navTabWatchFaces').classList.add('active');
        document.getElementById('pageWatchFaces').classList.add('active-page');
        queryCurrentWatchFace();
      } else if (tabId === 'help') {"""

if switch_target in text:
    text = text.replace(switch_target, switch_replacement, 1)
    print("switchTab() updated for Tab 10")
else:
    print("WARNING: switch_target not found")

# 4. Add JavaScript Functions for Watch Faces and Screen Controls
js_code = '''
    // =========================================================================
    // TAB 10: WATCH FACE & DISPLAY CONTROLLER (0xC7, 0xB4, 0xB1)
    // =========================================================================
    let currentActiveFaceIndex = 0;

    async function queryCurrentWatchFace() {
      soundButtonPress();
      logLCARS("[*] Querying Watch Face Style Matrix (Opcode 0xC7)...", "tx");
      // Opcode 0xC7, sub-cmd 0x02 = Read Screen Style
      const pkt = [0xC7, 0x02, 0x00, 0x00];
      await sendGattCommandBytes(pkt, "Read Screen Style (0xC7)");
      
      // Also query screen timeout (0xB4)
      setTimeout(async () => {
        await sendGattCommandBytes([0xB4, 0x02], "Read Screen Timeout (0xB4)");
      }, 300);
    }

    async function applyWatchFace(styleIndex, styleName) {
      soundButtonPress();
      logLCARS(`[*] Transmitting Watch Face Change -> ${styleName} (Style ${styleIndex})...`, "tx");
      // Opcode 0xC7, sub-cmd 0x01 = Set Screen Style, styleIndex, uiFromType=0 (Default Firmware)
      const pkt = [0xC7, 0x01, styleIndex & 0xFF, 0x00];
      await sendGattCommandBytes(pkt, `Set Watch Face Style ${styleIndex} (0xC7)`);

      // Update UI state
      currentActiveFaceIndex = styleIndex;
      document.getElementById('lblCurrentActiveFace').textContent = styleName.toUpperCase();
      document.getElementById('lblActiveFaceSlot').textContent = `Slot ${styleIndex}`;
      
      // Highlight active card
      document.querySelectorAll('.watchface-card').forEach((card, idx) => {
        if (idx === styleIndex) {
          card.style.border = "2px solid var(--lcars-banana)";
        } else {
          card.style.border = "1px solid var(--lcars-border)";
        }
      });
      logLCARS(`[+] Watch Face Switched to Style ${styleIndex}!`, "rx");
    }

    async function applyScreenTimeout(seconds) {
      soundButtonPress();
      logLCARS(`[*] Transmitting Screen Timeout -> ${seconds} Seconds (Opcode 0xB4)...`, "tx");
      // Opcode 0xB4, sub-cmd 0x01, seconds
      const pkt = [0xB4, 0x01, seconds & 0xFF];
      await sendGattCommandBytes(pkt, `Set Screen Timeout ${seconds}s (0xB4)`);
      document.getElementById('lblTimeoutStatus').textContent = `Current Watch Setting: ${seconds} Seconds (Configured)`;
      
      // Update button active states
      [5, 10, 15, 30].forEach(s => {
        const btn = document.getElementById(`btnTimeout${s}`);
        if (btn) {
          if (s === seconds) btn.style.boxShadow = "0 0 10px var(--lcars-orange)";
          else btn.style.boxShadow = "none";
        }
      });
      logLCARS(`[+] Display Timeout set to ${seconds} seconds.`, "rx");
    }

    async function applyScreenBrightness(level) {
      soundButtonPress();
      logLCARS(`[*] Transmitting Display Brightness -> Level ${level} / 5 (Opcode 0xB1)...`, "tx");
      // Opcode 0xB1: startHour, startMin, endHour, endMin, level, otherLevel, auto
      const pkt = [0xB1, 0x01, 22, 0, 7, 0, level & 0xFF, 1, 1];
      await sendGattCommandBytes(pkt, `Set Screen Brightness Lvl ${level} (0xB1)`);
      logLCARS(`[+] Display Brightness updated: Level ${level} (Day) / Level 1 (Night Dim).`, "rx");
    }

    async function sendGattCommandBytes(cmdArray, label) {
      if (!txCharacteristic) {
        logLCARS(`Notice: GATT Channel offline. Watch will receive command on next sync.`, "sys");
        return;
      }
      const payload = new Uint8Array(20);
      for (let i = 0; i < cmdArray.length; i++) {
        payload[i] = cmdArray[i];
      }
      try {
        if (txCharacteristic.writeValueWithoutResponse) {
          await txCharacteristic.writeValueWithoutResponse(payload);
        } else {
          await txCharacteristic.writeValue(payload);
        }
        logLCARS(`TX [${label}]: ${Array.from(payload).map(b => b.toString(16).padStart(2,'0')).join(' ')}`, "tx");
      } catch (err) {
        logLCARS(`Command Error (${label}): ${err.message}`, "err");
      }
    }
'''

# Insert JS before </script>
script_close = '</script>'
if script_close in text:
    text = text.replace(script_close, js_code + '\n  </script>', 1)
    print("JavaScript functions for Tab 10 inserted successfully")
else:
    print("WARNING: </script> not found")

with open(file_path, "w", encoding="utf-8") as f:
    f.write(text)
print("index.html successfully updated with Tab 10!")
