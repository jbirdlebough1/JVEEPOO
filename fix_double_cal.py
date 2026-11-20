html_path = 'D:/veepoo/w11_htm_app/index.html'
with open(html_path, 'r', encoding='utf-8') as f:
    content = f.read()

# 1. Remove the bogus 0x89 0x03 transmission in saveSetupToDevice()
target_bogus_alarm = '''      // 2b. Transmit 60 - 200 mg/dL Glucose Alarm Range to Watch (0x89 0x03)
      setTimeout(() => {
        const lowMg = cfg.glucoseAlarmLow || 60;
        const highMg = cfg.glucoseAlarm || 200;
        const lowRaw = Math.round((lowMg / 18.0182) * 100);
        const highRaw = Math.round((highMg / 18.0182) * 100);
        const gAlarmCmd = new Uint8Array(20);
        gAlarmCmd[0] = 0x89;
        gAlarmCmd[1] = 0x03; // Alarm setting opcode
        gAlarmCmd[2] = 0x01; // Enable
        gAlarmCmd[3] = lowRaw & 0xFF;
        gAlarmCmd[4] = (lowRaw >> 8) & 0xFF;
        gAlarmCmd[5] = highRaw & 0xFF;
        gAlarmCmd[6] = (highRaw >> 8) & 0xFF;
        sendCommandBytes(gAlarmCmd);
        logLCARS(`[*] Transmitted Glucose Safety Window: ${lowMg} – ${highMg} mg/dL (Opcode 0x89 0x03)...`, 'tx');
      }, 380);'''

assert target_bogus_alarm in content, "target_bogus_alarm not found"
content = content.replace(target_bogus_alarm, '''      // Note: 60 - 200 mg/dL is a software clinical Time-in-Range (TIR) safety window.
      // (Opcode 0x89 0x03 in Veepoo SDK is multi-meal calibration, not an alarm limit).''', 1)

# 2. Fix Double-Calibration in onTelemetryReceived
# In Private Mode (glucoseDetectMode === 1), the watch firmware ALREADY scales using register 0x89 0x02.
# Adding +41.5 on top resulted in 185 + 41.5 = 227 mg/dL!
target_telemetry = '''        // Apply Active Clinical Calibration Model
        let displayMg = rawMg;
        if (rawMg > 0) {
          if (glucoseCalMode === 'temp_8_3_a1c') {
            displayMg = Math.round(rawMg + 41.5);
          } else if (glucoseCalMode === 'custom_clinic' && typeof customClinicOffsetMg === 'number') {
            displayMg = Math.round(rawMg + customClinicOffsetMg);
          }
        }
        lastGlucoseMg = displayMg;
        lastGlucoseMmol = displayMg / 18.0182;'''

replacement_telemetry = '''        // Apply Active Clinical Calibration Model (Prevents Double-Calibration)
        // When watch is in Private Mode (1), watch hardware ALREADY scales using register 0x89 0x02!
        let displayMg = rawMg;
        const isWatchPrivateMode = (glucoseDetectMode === 1);
        
        if (rawMg > 0) {
          if (isWatchPrivateMode) {
            // Watch hardware EEPROM calibration is active: display directly matches watch screen
            displayMg = rawMg;
          } else {
            // Watch is in Public / Generic mode: apply software offset
            if (glucoseCalMode === 'temp_8_3_a1c') {
              displayMg = Math.round(rawMg + 41.5);
            } else if (glucoseCalMode === 'custom_clinic' && typeof customClinicOffsetMg === 'number') {
              displayMg = Math.round(rawMg + customClinicOffsetMg);
            }
          }
        }
        lastGlucoseMg = displayMg;
        lastGlucoseMmol = displayMg / 18.0182;'''

assert target_telemetry in content, "target_telemetry not found"
content = content.replace(target_telemetry, replacement_telemetry, 1)

# 3. Update default baseline to 160 mg/dL
content = content.replace('value="192"', 'value="160"')
content = content.replace("cfgGlucoseBaseline.value = 192", "cfgGlucoseBaseline.value = 160")
content = content.replace("transmitGlucoseCalibration(192)", "transmitGlucoseCalibration(160)")
content = content.replace("value = 192", "value = 160")

# Write updated file
with open(html_path, 'w', encoding='utf-8') as f:
    f.write(content)

print("SUCCESS: Double-calibration removed, 0x89 0x03 removed, baseline updated to 160!")
