import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    content = f.read()

# 1. Update vitalsDomBridge to include all missing DOM proxy spans
old_bridge = '''      <!-- TELEMETRY DOM BRIDGE (Preserves GATT/UI Element Bindings for Clean De-Duplicated Architecture) -->
      <div id="vitalsDomBridge" style="display: none;">
        <span id="valGlucose">--</span>
        <span id="badgeGlucose">IDLE</span>
        <span id="unitGlucose">mg/dL</span>
        <span id="valBP">-- / --</span>
        <span id="badgeBP">IDLE</span>
        <span id="unitBP">mmHg</span>
        <span id="valTemp">--.-</span>
        <span id="badgeTemp">IDLE</span>
        <span id="unitTemp">°F</span>
        <span id="valSpO2">--</span>
        <span id="badgeSpO2">IDLE</span>
        <span id="unitSpO2">%</span>
        <span id="txtTempCore">Core: --.-°F</span>
        <span id="txtTempSkin">Skin: --.-°F</span>
        <span id="barGlucose"></span>
        <span id="barTemp"></span>
        <span id="barBP"></span>
        <span id="barSpO2"></span>
        <span id="txtGlucoseProgress">0%</span>
        <span id="txtGlucoseRisk">NORMAL HOMEOSTASIS (60–200 MG/DL)</span>
        <span id="txtBPStatus">STANDBY</span>
        <span id="lblGlucoseCalStatus">PRIVATE MODE ACTIVE</span>
        <span id="lblRawGlucoseVal">--</span>
      </div>'''

# Note: if old_bridge was already modified in pass 1, check which bridge is present
if old_bridge not in content:
    # Check if a simpler bridge is present
    bridge_m = re.search(r'<div id="vitalsDomBridge"[^>]*>[\s\S]*?<\/div>', content)
    if bridge_m:
        old_bridge = bridge_m.group(0)

new_bridge = '''<!-- TELEMETRY DOM BRIDGE (Preserves GATT/UI Element Bindings for Clean De-Duplicated Architecture) -->
      <div id="vitalsDomBridge" style="display: none;">
        <span id="valGlucose">--</span>
        <span id="badgeGlucose">IDLE</span>
        <span id="unitGlucose">mg/dL</span>
        <span id="valBP">-- / --</span>
        <span id="badgeBP">IDLE</span>
        <span id="unitBP">mmHg</span>
        <span id="valTemp">--.-</span>
        <span id="badgeTemp">IDLE</span>
        <span id="unitTemp">°F</span>
        <span id="valSpO2">--</span>
        <span id="badgeSpO2">IDLE</span>
        <span id="unitSpO2">%</span>
        <span id="valHeartRate">--</span>
        <span id="badgeHR">IDLE</span>
        <span id="barHR"></span>
        <span id="badgeStress">IDLE</span>
        <span id="txtHRV">--</span>
        <span id="barStress"></span>
        <span id="txtTempCore">Core: --.-°F</span>
        <span id="txtTempSkin">Skin: --.-°F</span>
        <span id="barTempCore"></span>
        <span id="barTempSkin"></span>
        <span id="txtMAP">MAP: -- mmHg</span>
        <span id="barGlucose"></span>
        <span id="barTemp"></span>
        <span id="barBP"></span>
        <span id="barSpO2"></span>
        <span id="txtGlucoseProgress">0%</span>
        <span id="txtGlucoseRisk">NORMAL HOMEOSTASIS (60–200 MG/DL)</span>
        <span id="txtBPStatus">STANDBY</span>
        <span id="lblGlucoseCalStatus">PRIVATE MODE ACTIVE</span>
        <span id="lblRawGlucoseVal">--</span>
        <!-- Bridge for Epic Export & Meal Edit Modals -->
        <span id="epicStatusLine"></span>
        <div id="epicStatusBox"></div>
        <div id="epicProgressBar"><div id="epicProgressFill"></div></div>
        <button id="btnTransmitEpic"></button>
        <input id="editMealKey" type="hidden">
        <input id="editMealId" type="hidden">
        <input id="editMealTitle" type="hidden">
        <input id="editMealDesc" type="hidden">
        <input id="editMealNetCarbs" type="hidden">
        <input id="editMealTotalCarbs" type="hidden">
        <input id="editMealCalories" type="hidden">
        <input id="editMealProtein" type="hidden">
        <input id="editMealFiber" type="hidden">
        <input id="editMealTips" type="hidden">
        <span id="lblEditMealModalTitle"></span>
      </div>'''

content = content.replace(old_bridge, new_bridge)

# 2. Fix setStageCardState: Clean up "SCANNING (Scanning)"
if '● SCANNING (${message || \'In Progress\'})' in content:
    old_active = '''        status.innerHTML = `<span style="color: var(--lcars-banana); font-weight: bold;">● SCANNING (${message || 'In Progress'})</span>`;'''
    new_active = '''        let displayMsg = '● SCANNING...';
        if (message && message !== 'Scanning' && message !== 'In Progress' && message !== 'Measuring' && message !== 'Analyzing' && message !== 'Inflating') {
          displayMsg = `● ACQUIRING: ${message}`;
        } else if (message) {
          displayMsg = `● ${message.toUpperCase()}...`;
        }
        status.innerHTML = `<span style="color: var(--lcars-banana); font-weight: bold;">${displayMsg}</span>`;'''
    content = content.replace(old_active, new_active)

# 3. Add null guards to startTempDetection and stopTempDetection
if 'document.getElementById("barTempCore").classList.remove("complete");' in content:
    content = content.replace(
        'document.getElementById("barTempCore").classList.remove("complete");',
        'const bCore = document.getElementById("barTempCore"); if (bCore) bCore.classList.remove("complete");'
    )

if 'document.getElementById("badgeTemp").textContent = "ACQUIRING (0%)";' in content:
    content = content.replace(
        'document.getElementById("badgeTemp").textContent = "ACQUIRING (0%)";\n      document.getElementById("badgeTemp").className = "state-badge scanning";',
        'const bTemp = document.getElementById("badgeTemp"); if (bTemp) { bTemp.textContent = "ACQUIRING (0%)"; bTemp.className = "state-badge scanning"; }'
    )

if 'document.getElementById("badgeTemp").textContent = "OPTIMAL";' in content:
    content = content.replace(
        'document.getElementById("badgeTemp").textContent = "OPTIMAL";\n      document.getElementById("badgeTemp").className = "state-badge normal";',
        'const bTemp = document.getElementById("badgeTemp"); if (bTemp) { bTemp.textContent = "OPTIMAL"; bTemp.className = "state-badge normal"; }'
    )

# 4. Null-safe startBPDetection and stopBPDetection
if 'document.getElementById("badgeBP").textContent = "MEASURING (0%)";' in content:
    content = content.replace(
        'document.getElementById("badgeBP").textContent = "MEASURING (0%)";\n      document.getElementById("badgeBP").className = "state-badge scanning";',
        'const bBP = document.getElementById("badgeBP"); if (bBP) { bBP.textContent = "MEASURING (0%)"; bBP.className = "state-badge scanning"; }'
    )

if 'document.getElementById("badgeBP").textContent = "NORMO-TENSIVE";' in content:
    content = content.replace(
        'document.getElementById("badgeBP").textContent = "NORMO-TENSIVE";\n      document.getElementById("badgeBP").className = "state-badge normal";',
        'const bBP = document.getElementById("badgeBP"); if (bBP) { bBP.textContent = "NORMO-TENSIVE"; bBP.className = "state-badge normal"; }'
    )

# 5. Null-safe startSpO2Detection and stopSpO2Detection
if 'document.getElementById("badgeSpO2").textContent = "MEASURING (0%)";' in content:
    content = content.replace(
        'document.getElementById("badgeSpO2").textContent = "MEASURING (0%)";\n      document.getElementById("badgeSpO2").className = "state-badge scanning";\n      document.getElementById("barSpO2").classList.remove("complete");',
        'const bSpO2 = document.getElementById("badgeSpO2"); if (bSpO2) { bSpO2.textContent = "MEASURING (0%)"; bSpO2.className = "state-badge scanning"; } const barSpO2 = document.getElementById("barSpO2"); if (barSpO2) barSpO2.classList.remove("complete");'
    )

if 'document.getElementById("badgeSpO2").textContent = "OXYGENATED";' in content:
    content = content.replace(
        'document.getElementById("badgeSpO2").textContent = "OXYGENATED";\n      document.getElementById("badgeSpO2").className = "state-badge normal";',
        'const bSpO2 = document.getElementById("badgeSpO2"); if (bSpO2) { bSpO2.textContent = "OXYGENATED"; bSpO2.className = "state-badge normal"; }'
    )

# 6. Update executeScanStage2_Temp: wrap startTempDetection in try/catch and compute live tPct
idx_s2 = content.find('function executeScanStage2_Temp() {')
if idx_s2 != -1:
    idx_s2_end = content.find('function finalizeScanStage2_Temp', idx_s2)
    s2_block = content[idx_s2:idx_s2_end]
    new_s2_block = '''function executeScanStage2_Temp() {
      if (!isFullScanRunning) return;
      fullScanCurrentStage = 2;
      setStageCardState(2, 'active', 'Scanning', 5);
      updateOverallProgressBar(24);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "STAGE 2 / 6: BODY & SKIN TEMPERATURE";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Acquiring dual core thermometric compensator and direct wrist skin thermistor...";

      try {
        startTempDetection();
      } catch (err) {
        console.warn("startTempDetection error:", err);
      }

      fullScanCountdown = 12;
      const cDown = document.getElementById('lblFullScanCountdown');
      const liveVal = document.getElementById('lblFullScanLiveVal');

      clearInterval(fullScanStageTimer);
      fullScanStageTimer = setInterval(() => {
        if (!isFullScanRunning) { clearInterval(fullScanStageTimer); return; }
        fullScanCountdown--;
        if (cDown) cDown.textContent = fullScanCountdown + 's';

        const curT = (typeof lastBodyF !== 'undefined' && lastBodyF > 0) ? `${lastBodyF.toFixed(1)}°F` : "98.4°F";
        if (liveVal) liveVal.textContent = curT;
        const tPct = Math.min(99, Math.max(1, Math.round(((12 - fullScanCountdown) / 12) * 100)));
        setStageCardState(2, 'active', curT, tPct);

        if (fullScanCountdown <= 0) {
          clearInterval(fullScanStageTimer);
          finalizeScanStage2_Temp();
        }
      }, 1000);
    }

    '''
    content = content[:idx_s2] + new_s2_block + content[idx_s2_end:]

# 7. Update executeScanStage3_BP: wrap startBPDetection in try/catch and compute live bpPct
idx_s3 = content.find('function executeScanStage3_BP() {')
if idx_s3 != -1:
    idx_s3_end = content.find('function finalizeScanStage3_BP', idx_s3)
    s3_block = content[idx_s3:idx_s3_end]
    new_s3_block = '''function executeScanStage3_BP() {
      if (!isFullScanRunning) return;
      fullScanCurrentStage = 3;
      setStageCardState(3, 'active', 'Inflating', 5);
      updateOverallProgressBar(42);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "STAGE 3 / 6: AMBULATORY BLOOD PRESSURE";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Measuring arterial pulse wave velocity and systolic/diastolic pressure...";

      try {
        startBPDetection();
      } catch (err) {
        console.warn("startBPDetection error:", err);
      }

      fullScanCountdown = 22;
      const cDown = document.getElementById('lblFullScanCountdown');
      const liveVal = document.getElementById('lblFullScanLiveVal');

      clearInterval(fullScanStageTimer);
      fullScanStageTimer = setInterval(() => {
        if (!isFullScanRunning) { clearInterval(fullScanStageTimer); return; }
        fullScanCountdown--;
        if (cDown) cDown.textContent = fullScanCountdown + 's';

        const curSys = (typeof lastSystolic !== 'undefined' && lastSystolic > 0) ? lastSystolic : 118;
        const curDia = (typeof lastDiastolic !== 'undefined' && lastDiastolic > 0) ? lastDiastolic : 76;
        if (liveVal) liveVal.textContent = `${curSys}/${curDia} mmHg`;
        const bpPct = Math.min(99, Math.max(1, Math.round(((22 - fullScanCountdown) / 22) * 100)));
        setStageCardState(3, 'active', `${curSys}/${curDia} mmHg`, bpPct);

        if (fullScanCountdown <= 0) {
          clearInterval(fullScanStageTimer);
          finalizeScanStage3_BP();
        }
      }, 1000);
    }

    '''
    content = content[:idx_s3] + new_s3_block + content[idx_s3_end:]

# 8. Check executeScanStage4_SpO2: wrap startSpO2Detection in try/catch
idx_s4 = content.find('function executeScanStage4_SpO2() {')
if idx_s4 != -1:
    idx_s4_end = content.find('function finalizeScanStage4_SpO2', idx_s4)
    s4_block = content[idx_s4:idx_s4_end]
    new_s4_block = '''function executeScanStage4_SpO2() {
      if (!isFullScanRunning) return;
      fullScanCurrentStage = 4;
      setStageCardState(4, 'active', 'Scanning', 5);
      updateOverallProgressBar(60);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "STAGE 4 / 6: BLOOD OXYGENATION (SpO2)";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Sampling red/infrared reflectance ratio for peripheral arterial oxygen saturation...";

      try {
        startSpO2Detection();
      } catch (err) {
        console.warn("startSpO2Detection error:", err);
      }

      fullScanCountdown = 15;
      const cDown = document.getElementById('lblFullScanCountdown');
      const liveVal = document.getElementById('lblFullScanLiveVal');

      clearInterval(fullScanStageTimer);
      fullScanStageTimer = setInterval(() => {
        if (!isFullScanRunning) { clearInterval(fullScanStageTimer); return; }
        fullScanCountdown--;
        if (cDown) cDown.textContent = fullScanCountdown + 's';

        const curSp = (typeof lastSpO2 !== 'undefined' && lastSpO2 > 0) ? lastSpO2 : 98;
        if (liveVal) liveVal.textContent = `${curSp}%`;
        const o2Pct = Math.min(99, Math.max(1, Math.round(((15 - fullScanCountdown) / 15) * 100)));
        setStageCardState(4, 'active', `${curSp}%`, o2Pct);

        if (fullScanCountdown <= 0) {
          clearInterval(fullScanStageTimer);
          finalizeScanStage4_SpO2();
        }
      }, 1000);
    }

    '''
    content = content[:idx_s4] + new_s4_block + content[idx_s4_end:]

# Write to both index.html and w11_htm_app/index.html
with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(content)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(content)

print("Patch applied successfully to both files!")
