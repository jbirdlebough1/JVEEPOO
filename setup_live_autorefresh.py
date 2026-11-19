import re

# 1. Update server.py: Include change version and total_records in /api/telemetry/latest
server_path = r"D:\veepoo\w11_htm_app\server.py"
with open(server_path, "r", encoding="utf-8") as f:
    stext = f.read()

target_snapshot = '''    def get_latest_telemetry_snapshot(self):
        snapshot = {}'''

replacement_snapshot = '''    def get_latest_telemetry_snapshot(self):
        snapshot = {
            "db_version": ble_state.get("records_synced", 0) + ble_state.get("total_records", 0),
            "last_sync": ble_state.get("last_sync"),
            "status": ble_state.get("status", "STANDBY"),
            "active_connection": ble_state.get("active_connection", False),
            "vitals": {}
        }'''

if target_snapshot in stext:
    stext = stext.replace(target_snapshot, replacement_snapshot, 1)
    stext = stext.replace('snapshot[m] = dict(row)', 'snapshot["vitals"][m] = dict(row)')
    with open(server_path, "w", encoding="utf-8") as f:
        f.write(stext)
    print("server.py snapshot API updated")
else:
    print("Snapshot target not found in server.py")


# 2. Update index.html: Add pollLiveTelemetryChanges()
htm_path = r"D:\veepoo\w11_htm_app\index.html"
with open(htm_path, "r", encoding="utf-8") as f:
    htext = f.read()

poll_js = '''
    // =========================================================================
    // AUTONOMOUS LIVE TELEMETRY SYNC & AUTO-REFRESH (Zero Manual Refresh)
    // =========================================================================
    let lastKnownDbVersion = -1;
    let lastKnownGlucoseTs = "";

    async function pollLiveTelemetryChanges() {
      try {
        const resp = await fetch('/api/telemetry/latest');
        if (!resp.ok) return;
        const data = await resp.json();
        const vitals = data.vitals || {};
        const dbVer = data.db_version || 0;

        // Check if new records arrived
        if (dbVer !== lastKnownDbVersion) {
          lastKnownDbVersion = dbVer;

          // 1. Update Glucose if present
          if (vitals.GLUCOSE && vitals.GLUCOSE.timestamp !== lastKnownGlucoseTs) {
            lastKnownGlucoseTs = vitals.GLUCOSE.timestamp;
            const mgdl = Math.round(vitals.GLUCOSE.value1);
            const ea1c = vitals.GLUCOSE.value2;
            
            const elG = document.getElementById('dispGlucose');
            if (elG) elG.textContent = `${mgdl} mg/dL`;
            const elA = document.getElementById('dispA1C');
            if (elA) elA.textContent = `${ea1c}%`;
            const elRepG = document.getElementById('repAvgGlucose');
            if (elRepG) elRepG.innerHTML = `${mgdl} <span style="font-size: 1rem;">mg/dL</span>`;
            const elRepA = document.getElementById('repAvgA1C');
            if (elRepA) elRepA.textContent = `${ea1c}%`;
            
            // Check diet notifications
            if (typeof refreshGlucoseRecommendation === 'function') {
              refreshGlucoseRecommendation();
            }
          }

          // 2. Update Blood Pressure
          if (vitals.BLOOD_PRESSURE) {
            const sys = Math.round(vitals.BLOOD_PRESSURE.value1);
            const dia = Math.round(vitals.BLOOD_PRESSURE.value2);
            const elBp = document.getElementById('dispBP');
            if (elBp && sys > 0) elBp.textContent = `${sys}/${dia} mmHg`;
          }

          // 3. Update Heart Rate
          if (vitals.HEART_RATE) {
            const bpm = Math.round(vitals.HEART_RATE.value1);
            const elHr = document.getElementById('dispHr');
            if (elHr && bpm > 0) elHr.textContent = `${bpm} BPM`;
          }

          // 4. Update Temperature
          if (vitals.TEMPERATURE) {
            const bodyF = vitals.TEMPERATURE.value1.toFixed(1);
            const skinF = vitals.TEMPERATURE.value2.toFixed(1);
            const elT = document.getElementById('dispTemp');
            if (elT) elT.textContent = `${bodyF}°F`;
            const elS = document.getElementById('dispSkinTemp');
            if (elS) elS.textContent = `${skinF}°F`;
          }

          // 5. Update Pedometer & Steps
          if (vitals.PEDOMETER) {
            const steps = Math.round(vitals.PEDOMETER.value1);
            const kcal = Math.round(vitals.PEDOMETER.value2);
            const elStep = document.getElementById('dispSteps');
            if (elStep) elStep.textContent = `${steps.toLocaleString()} STEPS`;
            const elKcal = document.getElementById('dispCalories');
            if (elKcal) elKcal.textContent = `${kcal} KCAL`;
          }

          // 6. Update Battery
          if (vitals.BATTERY) {
            const pct = Math.round(vitals.BATTERY.value1);
            const elBat = document.getElementById('dispBattery');
            if (elBat) elBat.textContent = `${pct}%`;
          }

          // Refresh active reports tab view if currently open
          const pageRep = document.getElementById('pageReports');
          if (pageRep && pageRep.classList.contains('active-page') && typeof renderReportsView === 'function') {
            renderReportsView();
          }
        }
      } catch (e) {}
    }

    // Run live telemetry poll every 2.5 seconds
    setInterval(pollLiveTelemetryChanges, 2500);
    pollLiveTelemetryChanges();
'''

target_insert = '// Poll vault sync status every 15 seconds'
if target_insert in htext and "pollLiveTelemetryChanges" not in htext:
    htext = htext.replace(target_insert, poll_js + '\n    ' + target_insert, 1)
    with open(htm_path, "w", encoding="utf-8") as f:
        f.write(htext)
    print("index.html auto-refresh polling inserted successfully")
else:
    print("Target not found or already inserted")
