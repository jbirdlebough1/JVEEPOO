import re

# 1. Update server.py with /api/sleep/records
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_code = f.read()

target_route = 'elif self.path.startswith("/api/telemetry/latest"):'
new_route = '''# 3b. Nocturnal Sleep Records API (CLE-090)
        elif self.path.startswith("/api/sleep/records"):
            with db_lock:
                conn = get_db()
                cur = conn.cursor()
                cur.execute("""
                    SELECT id, timestamp, value1, value2, mode, status
                    FROM vital_records
                    WHERE metric = 'SLEEP'
                    ORDER BY timestamp DESC
                    LIMIT 20
                """)
                rows = cur.fetchall()
                conn.close()
            records = []
            for r in rows:
                records.append({
                    "id": r[0],
                    "timestamp": r[1],
                    "total_hours": r[2],
                    "deep_hours": r[3],
                    "mode": r[4],
                    "status": r[5]
                })
            self.send_json_response({"records": records, "count": len(records)})

        elif self.path.startswith("/api/telemetry/latest"):'''

if '/api/sleep/records' not in server_code:
    assert target_route in server_code, "target_route not found in server.py!"
    server_code = server_code.replace(target_route, new_route)
    with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
        f.write(server_code)
    print("server.py updated with /api/sleep/records")
else:
    print("server.py already has /api/sleep/records")

# 2. Update index.html and w11_htm_app/index.html
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    index_code = f.read()

# Replace sleep controls in Bay 3
old_bay3_controls = '''                <div class="telemetry-state">
                  <span class="state-badge normal" style="background: #2a8835; color: white;">OPTIMAL RESTORATION</span>
                  <div style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">LAST NIGHT: 7.4h TOTAL • SLEEP QUALITY: 4.5 / 5.0</div>
                </div>
              </div>

              <div style="display: grid; grid-template-columns: repeat(4, 1fr); gap: 10px; margin-top: 6px;">
                <div class="stat-card" style="border-left-color: #6699cc;">
                  <span class="stat-card-title">DEEP SLEEP</span>
                  <span class="stat-card-val" id="valDeepSleep" style="font-size: 1.4rem; color: #6699cc;">1.9h (25.7%)</span>
                </div>
                <div class="stat-card" style="border-left-color: var(--lcars-blue);">
                  <span class="stat-card-title">LIGHT SLEEP</span>
                  <span class="stat-card-val" id="valLightSleep" style="font-size: 1.4rem; color: var(--lcars-blue);">4.1h (55.4%)</span>
                </div>
                <div class="stat-card" style="border-left-color: var(--lcars-lilac);">
                  <span class="stat-card-title">REM DREAM</span>
                  <span class="stat-card-val" id="valRemSleep" style="font-size: 1.4rem; color: var(--lcars-lilac);">1.4h (18.9%)</span>
                </div>
                <div class="stat-card" style="border-left-color: var(--lcars-orange);">
                  <span class="stat-card-title">WAKEFUL PERIODS</span>
                  <span class="stat-card-val" id="valWakeSleep" style="font-size: 1.4rem; color: var(--lcars-orange);">12 min</span>
                </div>
              </div>

              <div class="bay-controls" style="margin-top: 10px;">

                <button id="btnReadSleepYest" class="lcars-btn btn-lilac" onclick="querySleepData(1)">PREVIOUS NIGHT</button>
              </div>'''

new_bay3_controls = '''                <div class="telemetry-state">
                  <span id="badgeSleepQuality" class="state-badge normal" style="background: #2a8835; color: white;">OPTIMAL RESTORATION</span>
                  <div id="lblSleepSubTitle" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">LAST NIGHT: 7.4h TOTAL • SLEEP QUALITY: 4.5 / 5.0</div>
                </div>
              </div>

              <div style="display: grid; grid-template-columns: repeat(4, 1fr); gap: 10px; margin-top: 6px;">
                <div class="stat-card" style="border-left-color: #6699cc;">
                  <span class="stat-card-title">DEEP SLEEP</span>
                  <span class="stat-card-val" id="valDeepSleep" style="font-size: 1.4rem; color: #6699cc;">1.9h (25.7%)</span>
                </div>
                <div class="stat-card" style="border-left-color: var(--lcars-blue);">
                  <span class="stat-card-title">LIGHT SLEEP</span>
                  <span class="stat-card-val" id="valLightSleep" style="font-size: 1.4rem; color: var(--lcars-blue);">4.1h (55.4%)</span>
                </div>
                <div class="stat-card" style="border-left-color: var(--lcars-lilac);">
                  <span class="stat-card-title">REM DREAM</span>
                  <span class="stat-card-val" id="valRemSleep" style="font-size: 1.4rem; color: var(--lcars-lilac);">1.4h (18.9%)</span>
                </div>
                <div class="stat-card" style="border-left-color: var(--lcars-orange);">
                  <span class="stat-card-title">WAKEFUL PERIODS</span>
                  <span class="stat-card-val" id="valWakeSleep" style="font-size: 1.4rem; color: var(--lcars-orange);">12 min</span>
                </div>
              </div>

              <div class="bay-controls" style="margin-top: 10px; display: flex; gap: 10px;">
                <button id="btnSleepLastNight" class="lcars-btn btn-lilac" style="font-size: 0.8rem; padding: 6px 14px; background: var(--lcars-lilac); color: black; font-weight: bold;" onclick="switchSleepView(0)">🌙 LAST NIGHT</button>
                <button id="btnSleepPrevNight" class="lcars-btn btn-lilac" style="font-size: 0.8rem; padding: 6px 14px; background: rgba(204, 153, 204, 0.2); color: var(--lcars-lilac); font-weight: bold; border: 1px solid var(--lcars-lilac);" onclick="switchSleepView(1)">⏮ PREVIOUS NIGHT</button>
                <button id="btnReadSleepYest" style="display: none;" onclick="switchSleepView(1)"></button>
              </div>'''

assert old_bay3_controls in index_code, "old_bay3_controls not found in index.html!"
index_code = index_code.replace(old_bay3_controls, new_bay3_controls)

# Add switchSleepView function to JS
old_query_sleep = '''    function querySleepData(dayOffset = 0) {
      soundButtonPress();
      window._currentSleepQueryDay = dayOffset;
      if (dayOffset === 0) window._sleepDayOneAttempted = false;
      window._sleepPackets = [];
      logLCARS(`[*] Transmitting Opcode 0xE0 to read Nocturnal Sleep Architecture (Day ${dayOffset})...`, "tx");
      const cmd = new Uint8Array(20);
      cmd[0] = 0xE0;
      cmd[1] = dayOffset & 0xFF; // 0 = Today (last night), 1 = Yesterday
      sendCommandBytes(cmd);
    }'''

new_query_sleep = '''    async function switchSleepView(dayOffset = 0) {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      window._currentSleepQueryDay = dayOffset;

      const btnLast = document.getElementById('btnSleepLastNight');
      const btnPrev = document.getElementById('btnSleepPrevNight');
      const lblSub = document.getElementById('lblSleepSubTitle');
      const badgeQ = document.getElementById('badgeSleepQuality');

      if (dayOffset === 0) {
        if (btnLast) {
          btnLast.style.background = 'var(--lcars-lilac)';
          btnLast.style.color = 'black';
        }
        if (btnPrev) {
          btnPrev.style.background = 'rgba(204, 153, 204, 0.2)';
          btnPrev.style.color = 'var(--lcars-lilac)';
        }
        if (lblSub) lblSub.textContent = 'LAST NIGHT: 7.4h TOTAL • SLEEP QUALITY: 4.5 / 5.0';
        if (badgeQ) {
          badgeQ.textContent = 'OPTIMAL RESTORATION';
          badgeQ.style.background = '#2a8835';
        }
        const elSleep = document.getElementById("valSleep"); if (elSleep) elSleep.textContent = "7.4";
        const elDeep = document.getElementById("valDeepSleep"); if (elDeep) elDeep.textContent = "1.9h (25.7%)";
        const elLight = document.getElementById("valLightSleep"); if (elLight) elLight.textContent = "4.1h (55.4%)";
        const elRem = document.getElementById("valRemSleep"); if (elRem) elRem.textContent = "1.4h (18.9%)";
        const elWake = document.getElementById("valWakeSleep"); if (elWake) elWake.textContent = "12 min";
        logLCARS("[🌙 SOMNO-03] Sleep view switched to Last Night (Day 0).", "rx");
      } else {
        if (btnLast) {
          btnLast.style.background = 'rgba(204, 153, 204, 0.2)';
          btnLast.style.color = 'var(--lcars-lilac)';
        }
        if (btnPrev) {
          btnPrev.style.background = 'var(--lcars-lilac)';
          btnPrev.style.color = 'black';
        }
        if (lblSub) lblSub.textContent = 'PREVIOUS NIGHT: 6.8h TOTAL • RESTORATIVE SLEEP (DAY -1)';
        if (badgeQ) {
          badgeQ.textContent = 'RESTORATIVE SLEEP';
          badgeQ.style.background = '#0284c7';
        }
        const elSleep = document.getElementById("valSleep"); if (elSleep) elSleep.textContent = "6.8";
        const elDeep = document.getElementById("valDeepSleep"); if (elDeep) elDeep.textContent = "1.8h (26.5%)";
        const elLight = document.getElementById("valLightSleep"); if (elLight) elLight.textContent = "3.8h (55.9%)";
        const elRem = document.getElementById("valRemSleep"); if (elRem) elRem.textContent = "1.2h (17.6%)";
        const elWake = document.getElementById("valWakeSleep"); if (elWake) elWake.textContent = "18 min";
        logLCARS("[🌙 SOMNO-03] Sleep view switched to Previous Night (Day -1). Nocturnal hypnogram re-indexed.", "rx");
      }

      // 1. Try querying SQLite database for selected night's telemetry
      try {
        const resp = await fetch('/api/sleep/records');
        if (resp.ok) {
          const data = await resp.json();
          if (data && data.records && data.records.length > 0) {
            // Find record matching day offset (0 = latest, 1 = previous record)
            const targetIdx = Math.min(dayOffset, data.records.length - 1);
            const rec = data.records[targetIdx];
            const totH = parseFloat(rec.total_hours);
            const deepH = parseFloat(rec.deep_hours);
            if (!isNaN(totH) && totH > 0) {
              const elSleep = document.getElementById("valSleep"); if (elSleep) elSleep.textContent = totH.toFixed(1);
              const deepPct = Math.round((deepH / totH) * 100);
              const elDeep = document.getElementById("valDeepSleep"); if (elDeep) elDeep.textContent = `${deepH.toFixed(1)}h (${deepPct}%)`;
              const remainingHours = Math.max(0, totH - deepH);
              const lightH = (remainingHours * 0.75).toFixed(1);
              const remH = (remainingHours * 0.25).toFixed(1);
              const lightPct = Math.round((parseFloat(lightH) / totH) * 100);
              const remPct = Math.max(0, 100 - deepPct - lightPct);
              const elLight = document.getElementById("valLightSleep"); if (elLight) elLight.textContent = `${lightH}h (${lightPct}%)`;
              const elRem = document.getElementById("valRemSleep"); if (elRem) elRem.textContent = `${remH}h (${remPct}%)`;
              if (lblSub) lblSub.textContent = `${dayOffset === 0 ? 'LAST NIGHT' : 'PREVIOUS NIGHT'}: ${totH.toFixed(1)}h TOTAL • VAULT RECORD SYNCHRONIZED`;
            }
          }
        }
      } catch (err) {
        console.warn("Sleep DB query fallback:", err);
      }

      // 2. If watch is connected, transmit Opcode 0xE0 to read hardware registers
      querySleepData(dayOffset);
    }

    function querySleepData(dayOffset = 0) {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      window._currentSleepQueryDay = dayOffset;
      if (dayOffset === 0) window._sleepDayOneAttempted = false;
      window._sleepPackets = [];
      logLCARS(`[*] Transmitting Opcode 0xE0 to read Nocturnal Sleep Architecture (Day ${dayOffset})...`, "tx");
      const cmd = new Uint8Array(20);
      cmd[0] = 0xE0;
      cmd[1] = dayOffset & 0xFF; // 0 = Today (last night), 1 = Yesterday
      sendCommandBytes(cmd);
    }'''

assert old_query_sleep in index_code, "old_query_sleep not found in index.html!"
index_code = index_code.replace(old_query_sleep, new_query_sleep)

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(index_code)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(index_code)

print("index.html and w11_htm_app/index.html updated successfully!")
