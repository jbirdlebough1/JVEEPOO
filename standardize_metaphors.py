import re

html_path = 'D:/veepoo/w11_htm_app/index.html'
with open(html_path, 'r', encoding='utf-8') as f:
    content = f.read()

# -----------------------------------------------------------------------------
# 1. Bay 1 (BIO-01): Clean 4-button control: SCAN, CANCEL, CALIBRATE, READ CAL
# -----------------------------------------------------------------------------
target_bay1_controls = '''              <div class="bay-controls" style="display: flex; flex-wrap: wrap; gap: 6px;">
                <button id="btnStartGlucose" class="lcars-btn btn-banana" onclick="startGlucoseDetection()">COMMENCE SCAN</button>
                <button id="btnStopGlucose" class="lcars-btn btn-orange" onclick="stopGlucoseDetection()">HALT</button>
                <button id="btnClinicCalModal" class="lcars-btn btn-green" onclick="openClinicCalModal()">IN-CLINIC DRAW CAL</button>
                <button id="btnApply83Watch" class="lcars-btn btn-banana" onclick="applyTemporary83Calibration()">SYNC 8.3% TO WATCH</button>
                <button id="btnToggleCalModel" class="lcars-btn btn-peach" onclick="toggleCalModel()">CAL: 8.3% ANCHOR</button>
                <button id="btnReadCal" class="lcars-btn btn-blue" onclick="readWatchGlucoseCalibration()">READ CAL</button>
                <button id="btnCalibrate" class="lcars-btn btn-ice" onclick="calibrateGlucosePrompt()">MANUAL CAL</button>
                <button class="lcars-btn btn-lilac" onclick="toggleGlucoseUnit()">UNIT</button>
              </div>'''

replacement_bay1_controls = '''              <div class="bay-controls" style="display: flex; flex-wrap: wrap; gap: 8px;">
                <button id="btnStartGlucose" class="lcars-btn btn-banana" onclick="startGlucoseDetection()">SCAN</button>
                <button id="btnStopGlucose" class="lcars-btn btn-orange" onclick="stopGlucoseDetection()">CANCEL</button>
                <button id="btnCalibrate" class="lcars-btn btn-ice" onclick="calibrateGlucosePrompt()">CALIBRATE</button>
                <button id="btnReadCal" class="lcars-btn btn-blue" onclick="readWatchGlucoseCalibration()">READ CAL</button>
              </div>'''

assert target_bay1_controls in content, "target_bay1_controls not found"
content = content.replace(target_bay1_controls, replacement_bay1_controls, 1)

# Clean up Bay 1 cal badge and label
content = content.replace(
    '<span id="lblGlucoseCalStatus" style="color: var(--lcars-text);">8.3% HbA1c TEMPORARY (+41.5 mg/dL)</span>',
    '<span id="lblGlucoseCalStatus" style="color: var(--lcars-text);">PRIVATE MODE ACTIVE (CALIBRATED)</span>'
)

# -----------------------------------------------------------------------------
# 2. Bay 2 (BIO-02): SCAN TEMP, CANCEL (Unit moved to config)
# -----------------------------------------------------------------------------
target_bay2_controls = '''              <div class="bay-controls">
                <button id="btnStartTemp" class="lcars-btn btn-blue" onclick="startTempDetection()">ACQUIRE TEMP</button>
                <button id="btnStopTemp" class="lcars-btn btn-orange" onclick="stopTempDetection()">HALT</button>
                <button class="lcars-btn btn-lilac" onclick="toggleTempUnit()">UNIT TOGGLE</button>
              </div>'''

replacement_bay2_controls = '''              <div class="bay-controls" style="display: flex; gap: 8px;">
                <button id="btnStartTemp" class="lcars-btn btn-blue" onclick="startTempDetection()">SCAN TEMP</button>
                <button id="btnStopTemp" class="lcars-btn btn-orange" onclick="stopTempDetection()">CANCEL</button>
              </div>'''

assert target_bay2_controls in content, "target_bay2_controls not found"
content = content.replace(target_bay2_controls, replacement_bay2_controls, 1)

# -----------------------------------------------------------------------------
# 3. Bay 3 (BIO-03): TAKE BP, CANCEL
# -----------------------------------------------------------------------------
target_bay3_controls = '''              <div class="bay-controls">
                <button id="btnStartBP" class="lcars-btn btn-orange" onclick="startBPDetection()">MEASURE BP</button>
                <button id="btnStopBP" class="lcars-btn btn-lilac" onclick="stopBPDetection()">HALT</button>
              </div>'''

replacement_bay3_controls = '''              <div class="bay-controls" style="display: flex; gap: 8px;">
                <button id="btnStartBP" class="lcars-btn btn-orange" onclick="startBPDetection()">TAKE BP</button>
                <button id="btnStopBP" class="lcars-btn btn-lilac" onclick="stopBPDetection()">CANCEL</button>
              </div>'''

assert target_bay3_controls in content, "target_bay3_controls not found"
content = content.replace(target_bay3_controls, replacement_bay3_controls, 1)

# -----------------------------------------------------------------------------
# 4. Bay 4 (BIO-04): SCAN O2, CANCEL
# -----------------------------------------------------------------------------
target_bay4_controls = '''              <div class="bay-controls">
                <button id="btnStartSpO2" class="lcars-btn btn-green" onclick="startSpO2Detection()">MEASURE SpO2</button>
                <button id="btnStopSpO2" class="lcars-btn btn-orange" onclick="stopSpO2Detection()">HALT</button>
              </div>'''

replacement_bay4_controls = '''              <div class="bay-controls" style="display: flex; gap: 8px;">
                <button id="btnStartSpO2" class="lcars-btn btn-green" onclick="startSpO2Detection()">SCAN O2</button>
                <button id="btnStopSpO2" class="lcars-btn btn-orange" onclick="stopSpO2Detection()">CANCEL</button>
              </div>'''

assert target_bay4_controls in content, "target_bay4_controls not found"
content = content.replace(target_bay4_controls, replacement_bay4_controls, 1)

# -----------------------------------------------------------------------------
# 5. Tab 02: CARDIO-01 (SCAN PULSE, CANCEL) and NEURO-02 (SCAN HRV, CANCEL)
# -----------------------------------------------------------------------------
target_cardio_controls = '''              <div class="bay-controls" style="display: flex; flex-wrap: wrap; gap: 8px; margin-top: auto; padding-top: 8px; padding-bottom: 4px;">
                <button id="btnStartHR" class="lcars-btn btn-red" style="min-height: 36px;" onclick="startHRDetection()">ACQUIRE PULSE</button>
                <button id="btnStopHR" class="lcars-btn btn-orange" style="min-height: 36px;" onclick="stopHRDetection()">HALT</button>
              </div>'''

replacement_cardio_controls = '''              <div class="bay-controls" style="display: flex; flex-wrap: wrap; gap: 8px; margin-top: auto; padding-top: 8px; padding-bottom: 4px;">
                <button id="btnStartHR" class="lcars-btn btn-red" style="min-height: 36px;" onclick="startHRDetection()">SCAN PULSE</button>
                <button id="btnStopHR" class="lcars-btn btn-orange" style="min-height: 36px;" onclick="stopHRDetection()">CANCEL</button>
              </div>'''

assert target_cardio_controls in content, "target_cardio_controls not found"
content = content.replace(target_cardio_controls, replacement_cardio_controls, 1)

target_hrv_controls = '''              <div class="bay-controls" style="display: flex; flex-wrap: wrap; gap: 8px; margin-top: auto; padding-top: 12px; padding-bottom: 6px;">
                <button id="btnStartHRV" class="lcars-btn btn-lilac" style="min-height: 38px;" onclick="startHRVDetection()">ACQUIRE HRV</button>
                <button id="btnStopHRV" class="lcars-btn btn-orange" style="min-height: 38px;" onclick="stopHRVDetection()">HALT</button>
              </div>'''

replacement_hrv_controls = '''              <div class="bay-controls" style="display: flex; flex-wrap: wrap; gap: 8px; margin-top: auto; padding-top: 12px; padding-bottom: 6px;">
                <button id="btnStartHRV" class="lcars-btn btn-lilac" style="min-height: 38px;" onclick="startHRVDetection()">SCAN HRV</button>
                <button id="btnStopHRV" class="lcars-btn btn-orange" style="min-height: 38px;" onclick="stopHRVDetection()">CANCEL</button>
              </div>'''

assert target_hrv_controls in content, "target_hrv_controls not found"
content = content.replace(target_hrv_controls, replacement_hrv_controls, 1)

# -----------------------------------------------------------------------------
# 6. Tab 05 (05-SETUP): In-Clinic Draw & Unit Configuration Centered
# -----------------------------------------------------------------------------
target_setup_sec2 = '''            <div class="setup-grid">
              <div class="setup-field">
                <label class="setup-label">PERSONAL REFERENCE BASELINE (MG/DL)</label>
                <input type="number" id="cfgGlucoseBaseline" class="setup-input" value="160" min="40" max="400">
              </div>
              <div class="setup-field">
                <label class="setup-label">MEASUREMENT CALIBRATION MODEL</label>
                <select id="cfgGlucoseMode" class="setup-select" onchange="if(this.value==='83') applyTemporary83Calibration(); else if(this.value==='clinic') openClinicCalModal();">
                  <option value="83">TEMPORARY CLINICAL CALIBRATION (8.3% HbA1c • 191.5 MG/DL eAG)</option>
                  <option value="clinic">IN-CLINIC VENOUS BLOOD DRAW CALIBRATION (&le;5% ERROR)</option>
                  <option value="1">PRIVATE PERSONALIZED PPG CALIBRATION (140 MG/DL BASELINE)</option>
                  <option value="0">PUBLIC FACTORY GENERIC BASELINE (106 MG/DL UNCALIBRATED)</option>
                </select>
              </div>
              <div class="setup-field" style="grid-column: span 2; display: flex; gap: 8px; flex-wrap: wrap;">
                <button type="button" class="lcars-btn btn-banana" style="flex: 1;" onclick="applyTemporary83Calibration()">APPLY 8.3% HbA1c CAL (192 MG/DL TO WATCH)</button>
                <button type="button" class="lcars-btn btn-green" style="flex: 1;" onclick="openClinicCalModal()">IN-CLINIC BLOOD DRAW PROTOCOL (&le;5%)</button>
              </div>'''

replacement_setup_sec2 = '''            <div class="setup-grid">
              <div class="setup-field">
                <label class="setup-label">PERSONAL REFERENCE BASELINE (MG/DL)</label>
                <input type="number" id="cfgGlucoseBaseline" class="setup-input" value="160" min="40" max="400">
              </div>
              <div class="setup-field">
                <label class="setup-label">OPERATING MODE</label>
                <div style="background: rgba(85, 255, 119, 0.1); border: 1px solid var(--lcars-green); border-radius: 4px; padding: 7px 10px; font-family: var(--lcars-mono); font-size: 0.85rem; color: var(--lcars-green); font-weight: bold;">
                  ● STANDARDIZED: PRIVATE CALIBRATED MODE ONLY
                </div>
              </div>
              <div class="setup-field" style="grid-column: span 2; display: flex; gap: 10px; flex-wrap: wrap;">
                <button type="button" class="lcars-btn btn-green" style="flex: 1; min-height: 40px; font-weight: bold;" onclick="openClinicCalModal()">🩸 IN-CLINIC VENOUS BLOOD DRAW CALIBRATION (&le;5% ERROR)</button>
                <button type="button" class="lcars-btn btn-banana" style="flex: 1; min-height: 40px;" onclick="transmitGlucoseCalibration()">PROGRAM BASELINE TO WATCH EEPROM (0x89 0x02)</button>
              </div>'''

assert target_setup_sec2 in content, "target_setup_sec2 not found"
content = content.replace(target_setup_sec2, replacement_setup_sec2, 1)

# -----------------------------------------------------------------------------
# 7. JavaScript: Standardize on Private Mode Only (0x89 0x01 0x01 0x01)
# -----------------------------------------------------------------------------
target_start_glucose = '''    function startGlucoseDetection() {
      soundButtonPress();
      const isPrivate = (glucoseDetectMode === 1);
      logLCARS(`[*] Commencing optical PPG blood glucose scan [${isPrivate ? 'PRIVATE CALIBRATED MODE (0x89 0x01 0x01 0x01)' : 'PUBLIC GENERIC MODE (0x89 0x01 0x01 0x00)'}]...`, "tx");
      const cmd = new Uint8Array(20);
      cmd[0] = 0x89;
      cmd[1] = 0x01;
      cmd[2] = 0x01; // Start
      cmd[3] = isPrivate ? 0x01 : 0x00; // 0x01 = PRIVATE MODE (140 mg/dL), 0x00 = PUBLIC MODE (106 mg/dL)
      sendCommandBytes(cmd);'''

replacement_start_glucose = '''    function startGlucoseDetection() {
      soundButtonPress();
      // Standardized on Private Mode ONLY (0x89 0x01 0x01 0x01)
      glucoseDetectMode = 1;
      logLCARS("[*] Commencing optical PPG blood glucose scan [STANDARDIZED PRIVATE MODE (0x89 0x01 0x01 0x01)]...", "tx");
      const cmd = new Uint8Array(20);
      cmd[0] = 0x89;
      cmd[1] = 0x01;
      cmd[2] = 0x01; // Start
      cmd[3] = 0x01; // Always Private Mode (0x01)
      sendCommandBytes(cmd);'''

assert target_start_glucose in content, "target_start_glucose not found"
content = content.replace(target_start_glucose, replacement_start_glucose, 1)

target_stop_glucose = '''    function stopGlucoseDetection() {
      soundButtonPress();
      const isPrivate = (glucoseDetectMode === 1);
      logLCARS("[*] Halting optical glucose scan...", "tx");
      const cmd = new Uint8Array(20);
      cmd[0] = 0x89;
      cmd[1] = 0x01;
      cmd[2] = 0x02; // Stop
      cmd[3] = isPrivate ? 0x01 : 0x00; // vp_bv (0x01) or vp_bu (0x00)
      sendCommandBytes(cmd);'''

replacement_stop_glucose = '''    function stopGlucoseDetection() {
      soundButtonPress();
      logLCARS("[*] Canceling optical glucose scan...", "tx");
      const cmd = new Uint8Array(20);
      cmd[0] = 0x89;
      cmd[1] = 0x01;
      cmd[2] = 0x02; // Stop
      cmd[3] = 0x01; // Private Mode
      sendCommandBytes(cmd);'''

assert target_stop_glucose in content, "target_stop_glucose not found"
content = content.replace(target_stop_glucose, replacement_stop_glucose, 1)

# In-clinic draw modal instructions update:
content = content.replace(
    'open the <strong>[IN-CLINIC DRAW CAL]</strong> modal in <strong>01-VITALS</strong>',
    'open the <strong>[IN-CLINIC BLOOD DRAW CALIBRATION]</strong> tool in <strong>05-CONFIG / SETUP</strong>'
)

# Write updated file
with open(html_path, 'w', encoding='utf-8') as f:
    f.write(content)

print("SUCCESS: Standardized on Private Mode only and unified W11 action verbs across all bays!")
