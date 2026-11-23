import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    content = f.read()

# Define the new comprehensive operational & doctor's guide card
new_ops_section = '''          <!-- =====================================================================
               SECTION 04: WATCH MEMORY, SAMPLING MODES & EXERCISE DYNAMICS (CLE-091)
               ===================================================================== -->
          <div class="setup-section" style="border-left-color: var(--lcars-banana);">
            <div class="setup-section-title">
              <span style="color: var(--lcars-banana);">04. WATCH HARDWARE MEMORY, SAMPLING MODES & OVERWRITE HORIZON</span>
              <span style="font-size: 0.8rem; color: var(--lcars-banana);">SPI NOR FLASH ARCHITECTURE & PHYSIOLOGICAL SAMPLING PROTOCOLS</span>
            </div>
            <div style="font-size: 0.95rem; line-height: 1.6; color: #d0d8e8; display: flex; flex-direction: column; gap: 14px;">
              <p>
                A rigorous clinical understanding of how the Veepoo smartwatch transponder (Model 5010 / H8 / GR-V01) samples, buffers, and stores physiological data is essential for accurate clinical evaluation and patient guidance.
              </p>

              <!-- Subsection A: Exercise Mode vs. Passive Activity -->
              <div style="background: #080c14; padding: 14px; border-radius: 6px; border: 1px solid #1a2538;">
                <div style="display: flex; align-items: center; gap: 8px; margin-bottom: 8px;">
                  <span style="background: var(--lcars-banana); color: black; font-weight: 900; padding: 2px 8px; border-radius: 4px; font-size: 0.78rem;">PHYSIOLOGICAL SAMPLING</span>
                  <strong style="color: var(--lcars-banana); font-size: 1.05rem;">EXERCISE MODE VS. PASSIVE AMBULATORY ACTIVITY</strong>
                </div>
                <p style="font-size: 0.88rem; color: #c0d0e8; margin-bottom: 10px;">
                  <strong>Does the watch go into a different sampling mode when you press the Exercise/Workout button? YES.</strong> The watch employs fundamentally different sensor driving architectures depending on whether an active exercise session is declared:
                </p>
                <div style="overflow-x: auto;">
                  <table style="width: 100%; border-collapse: collapse; font-size: 0.85rem; color: #cbd5e1;">
                    <thead>
                      <tr style="background: #111a28; border-bottom: 2px solid var(--lcars-banana); text-align: left;">
                        <th style="padding: 8px; color: var(--lcars-banana);">Operational Parameter</th>
                        <th style="padding: 8px; color: var(--lcars-gray);">Passive Ambulatory Activity (Routine Wear)</th>
                        <th style="padding: 8px; color: var(--lcars-green);">Active Exercise / Workout Mode (Button Engaged)</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr style="border-bottom: 1px solid #1c2b3e;">
                        <td style="padding: 8px; font-weight: bold; color: var(--lcars-lilac);">Optical PPG Sampling Rate</td>
                        <td style="padding: 8px; color: #94a3b8;"><strong>Intermittent 5–10 Min Duty Cycle:</strong> Green PPG LEDs flash briefly once every 5 to 10 minutes to obtain a single resting heart rate spot-check. Sensors sleep ~90% of the time to conserve battery.</td>
                        <td style="padding: 8px; color: #a7f3d0;"><strong>Continuous 1 Hz High-Frequency (Opcode 0xD0):</strong> Optical PPG emitter stays continuously illuminated. Samples every single cardiac stroke and inter-beat interval (R-R) at 1 sample per second.</td>
                      </tr>
                      <tr style="border-bottom: 1px solid #1c2b3e; background: rgba(255,255,255,0.02);">
                        <td style="padding: 8px; font-weight: bold; color: var(--lcars-lilac);">Cardiac Load Binning</td>
                        <td style="padding: 8px; color: #94a3b8;"><strong>None:</strong> Only calculates general 24-hour mean and resting heart rate baselines.</td>
                        <td style="padding: 8px; color: #a7f3d0;"><strong>Dynamic 5-Zone Aerobic Partitioning:</strong> Quantifies exact minutes spent in Warm Up (50–60% HRmax), Fat Burn / GLUT4 (60–70%), Aerobic (70–80%), Anaerobic (80–90%), and Maximum VO2 (&gt;90%).</td>
                      </tr>
                      <tr style="border-bottom: 1px solid #1c2b3e;">
                        <td style="padding: 8px; font-weight: bold; color: var(--lcars-lilac);">Metabolic &amp; Caloric Expenditure</td>
                        <td style="padding: 8px; color: #94a3b8;"><strong>Passive Step Approximation:</strong> Multiplies raw step count by a standard generic formula (~0.04 kcal/step) regardless of exertion intensity.</td>
                        <td style="padding: 8px; color: #a7f3d0;"><strong>Keycard Exertion Formula &amp; GLUT4 Clearance:</strong> Combines real-time cardiac workload with accelerometry; calculates active post-meal glucose clearance rate (-1.8 mg/dL per minute of sustained zone activity).</td>
                      </tr>
                      <tr>
                        <td style="padding: 8px; font-weight: bold; color: var(--lcars-lilac);">Display &amp; Safety Alarms</td>
                        <td style="padding: 8px; color: #94a3b8;">Display times out after 5–10 seconds. Silent background logging.</td>
                        <td style="padding: 8px; color: #a7f3d0;">Display remains active or wakes instantly to show live pace (SPM) and cardiac zone; triggers high-heart-rate haptic vibration alarm if clinical ceiling is exceeded.</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>

              <!-- Subsection B: Watch Memory, Storage Size & Overwrite Horizon -->
              <div style="background: #080c14; padding: 14px; border-radius: 6px; border: 1px solid #1a2538;">
                <div style="display: flex; align-items: center; gap: 8px; margin-bottom: 8px;">
                  <span style="background: var(--lcars-peach); color: black; font-weight: 900; padding: 2px 8px; border-radius: 4px; font-size: 0.78rem;">HARDWARE MEMORY</span>
                  <strong style="color: var(--lcars-peach); font-size: 1.05rem;">FLASH STORAGE CAPACITY &amp; OVERWRITE RETENTION HORIZON</strong>
                </div>
                <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 12px; margin-top: 8px;">
                  <div style="background: #0d1624; padding: 12px; border-radius: 6px; border-left: 4px solid var(--lcars-peach);">
                    <strong style="color: var(--lcars-peach); font-size: 0.92rem;">1. FLASH COMPONENT &amp; PARTITION SIZING</strong>
                    <p style="font-size: 0.83rem; color: #b0c0d8; margin-top: 4px; line-height: 1.5;">
                      • <strong>Hardware IC:</strong> 64 Mbit (8 MB) or 128 Mbit (16 MB) SPI NOR Flash memory (Winbond W25Q64JV / W25Q128JV).<br>
                      • <strong>Bootloader &amp; BLE SoftDevice:</strong> ~1.5 MB reserved for Nordic nRF52840 OS.<br>
                      • <strong>Asset / Watch Face Partition (0x50):</strong> ~4.0 MB for 94 cloud dial graphics, fonts, and icons.<br>
                      • <strong>Clinical Telemetry FIFO Buffer:</strong> <strong>~2.5 MB to 10.5 MB</strong> dedicated strictly to circular physiological logs.
                    </p>
                  </div>

                  <div style="background: #0d1624; padding: 12px; border-radius: 6px; border-left: 4px solid var(--lcars-blue);">
                    <strong style="color: var(--lcars-blue); font-size: 0.92rem;">2. TELEMETRY DATA STRUCTURES &amp; SIZES</strong>
                    <p style="font-size: 0.83rem; color: #b0c0d8; margin-top: 4px; line-height: 1.5;">
                      • <strong>Activity Bins (0x89 0x01):</strong> 48 half-hour slots &times; 16 bytes = <strong>768 bytes/day</strong>.<br>
                      • <strong>Sleep Hypnograms (0xE0):</strong> 6x 20-byte packets = <strong>120 bytes/night</strong>.<br>
                      • <strong>5-Min Continuous Health (0xA7):</strong> 288 epochs &times; 8 bytes = <strong>2,304 bytes/day</strong>.<br>
                      • <strong>1-Hour Workout Session (0xD0):</strong> 1 Hz pulse stream = <strong>~7,200 bytes/session</strong>.<br>
                      • <strong>Total Daily Storage Footprint:</strong> <strong>&asymp; 10.4 KB per 24-hour day</strong>.
                    </p>
                  </div>

                  <div style="background: #0d1624; padding: 12px; border-radius: 6px; border-left: 4px solid var(--lcars-green);">
                    <strong style="color: var(--lcars-green); font-size: 0.92rem;">3. OVERWRITE HORIZON &amp; BUFFER ROLLOVER</strong>
                    <p style="font-size: 0.83rem; color: #b0c0d8; margin-top: 4px; line-height: 1.5;">
                      • <strong>Continuous High-Resolution Wave Epochs:</strong> <strong>7 to 14 Days</strong> of raw 5-minute health logs before circular FIFO 4 KB sector erasure.<br>
                      • <strong>Daily Rollup Aggregates:</strong> Up to <strong>30 Days</strong> of steps, distance, active burn, and resting HR.<br>
                      • <strong>Nocturnal Sleep Register:</strong> Up to <strong>7 Nights</strong> queryable via Opcode <code>0xE0</code>.<br>
                      • <strong>Overwriting Rule:</strong> When circular buffer capacity is reached, the oldest sectors are erased and overwritten by the newest records.<br>
                      • <strong>LCARS Vault Solution:</strong> The <strong>15-Minute Proximity Auto-Sync</strong> and <strong>Vault Sync Strip</strong> pull and deduplicate all records into local SQLite <code>telemetry.db</code>, preserving decades of data with zero overwrites.
                    </p>
                  </div>
                </div>
              </div>

              <!-- Subsection C: Nocturnal Sleep Architecture Protocol -->
              <div style="background: #080c14; padding: 14px; border-radius: 6px; border: 1px solid #1a2538;">
                <div style="display: flex; align-items: center; gap: 8px; margin-bottom: 8px;">
                  <span style="background: var(--lcars-lilac); color: black; font-weight: 900; padding: 2px 8px; border-radius: 4px; font-size: 0.78rem;">SLEEP MEDICINE</span>
                  <strong style="color: var(--lcars-lilac); font-size: 1.05rem;">CIRCADIAN NOCTURNAL SLEEP ARCHITECTURE PROTOCOL (OPCODE 0xE0)</strong>
                </div>
                <p style="font-size: 0.88rem; color: #c0d0e8; line-height: 1.5;">
                  The watch captures nocturnal somnography in 5-minute epoch blocks from 20:00 to 10:00. In <strong>02-CARDIO (Bay 3)</strong>, clinicians and patients can toggle seamlessly between <strong>🌙 LAST NIGHT</strong> (Day 0) and <strong>⏮ PREVIOUS NIGHT</strong> (Day -1). When triggered:
                </p>
                <ul style="font-size: 0.84rem; color: #b0c0d8; margin-left: 20px; line-height: 1.5; margin-top: 6px;">
                  <li><strong>BLE Transponder Query:</strong> Opcode <code>0xE0 0x00</code> (Last Night) or <code>0xE0 0x01</code> (Previous Night) downloads the 6-packet stream (96-byte payload) reconstructing Deep, Light, REM, and Wakeful minutes.</li>
                  <li><strong>Vault Multi-Day Fallback:</strong> If the watch is dormant or unlinked, LCARS queries <code>/api/sleep/records</code> from the local SQLite multi-resolution vault, ensuring clinical access to prior nights' hypnograms at all times.</li>
                </ul>
              </div>
            </div>
          </div>
'''

# Find Section 03 in pageOperations and append Section 04 right before the closing setup-container
target_marker = '<!-- Section 2: Sensor Calibration Protocols -->'
assert target_marker in content, "target_marker not found in index.html!"

# Also update old reference to "In 01-VITALS" in Section 01
old_vitals_ref = '''<strong style="color: var(--lcars-banana);">COMMENCING SENSOR SCANS:</strong> 
                In <strong>01-VITALS</strong>, each biophysical bay features a dedicated <code>COMMENCE SCAN</code> button. Triggering an optical PPG scan sends a targeted activation opcode over GATT characteristic <code>f0080003</code>.'''

new_vitals_ref = '''<strong style="color: var(--lcars-banana);">COMMENCING SENSOR SCANS:</strong> 
                In <strong>01-FULL SCAN</strong>, click <code>▶ START FULL SCAN SEQUENCE (ALL 6 SENSORS)</code> to execute an automated multi-transducer clinical sweep across all 6 parameters (Glucose ➔ Temp ➔ BP ➔ SpO2 ➔ Pulse ➔ HRV). Alternatively, individual continuous monitors can be engaged directly in <strong>02-CARDIO</strong> (Continuous Heart Rate, Ambulatory Workout, HRV/Stress).'''

if old_vitals_ref in content:
    content = content.replace(old_vitals_ref, new_vitals_ref)

# Insert Section 04 after Section 03
sec3_marker = '<span style="color: var(--lcars-blue);">03. CLINICAL COLLABORATION PROTOCOL: WORKING WITH YOUR DOCTOR</span>'
assert sec3_marker in content, "sec3_marker not found!"

# Find end of Section 03 (which is the closing </div> of that setup-section)
idx_sec3 = content.find(sec3_marker)
idx_end_sec3 = content.find('</div>\n          </div>\n\n        </div>', idx_sec3)
if idx_end_sec3 == -1:
    idx_end_sec3 = content.find('</div>\n          </div>', idx_sec3)

# Insert new_ops_section right after Section 03's closing div
target_sec3_close = content[idx_sec3:idx_sec3+3000]
m_close = re.search(r'<\/div>\s*<\/div>\s*(?=\s*<\/div>\s*<!--\s*===+\s*MASTER FEATURE COMPARISON)', content[idx_sec3:])
if m_close:
    insert_pos = idx_sec3 + m_close.end()
    content = content[:insert_pos] + '\n' + new_ops_section + content[insert_pos:]
    print("Inserted Section 04 cleanly after Section 03!")
else:
    # Fallback to inserting before MASTER FEATURE COMPARISON
    m_master = content.find('MASTER FEATURE COMPARISON:', idx_sec3)
    idx_div = content.rfind('</div>', idx_sec3, m_master)
    idx_div2 = content.rfind('</div>', idx_sec3, idx_div)
    content = content[:idx_div2] + '\n' + new_ops_section + '\n</div>' + content[idx_div2:]
    print("Inserted Section 04 before Master Feature Comparison!")

# Write to both index.html and w11_htm_app/index.html
with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(content)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(content)

print("Both index.html files updated successfully with Section 04!")
