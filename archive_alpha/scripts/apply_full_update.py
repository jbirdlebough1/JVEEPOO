with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# 1. Remove Mobile Cell Gateway Pill (if still present)
old_cell_pill = '''            <!-- CLEAN MOBILE WI-FI ACCESS PILL -->
            <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-left: 5px solid #0284c7; border-radius: 6px; padding: 10px 14px; margin-bottom: 14px; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <strong style="color: #0284c7; font-size: 0.90rem;">📱 DIRECT MOBILE FOOD INTAKE GATEWAY (WI-FI):</strong>
                <span style="color: #475569; font-size: 0.82rem; margin-left: 6px;">Connect any smartphone on your local Wi-Fi to <code style="background: #e2e8f0; color: #0284c7; padding: 2px 6px; border-radius: 4px; font-weight: 800; font-size: 0.85rem;">http://192.168.12.207:8899/cell</code> to take multi-photos &amp; log meals.</span>
              </div>
              <a href="http://localhost:8899/cell" target="_blank" class="lcars-btn btn-blue" style="font-size: 0.78rem; padding: 4px 12px; text-decoration: none; font-weight: bold;">
                🔗 OPEN /CELL INTERFACE
              </a>
            </div>'''
if old_cell_pill in html:
    html = html.replace(old_cell_pill, '')
    print("[✔] Removed mobile cell gateway pill from LCARS")

# 2. Update Stage Cards 1..6: Replace hex codes with STAGE 01..06 and add colored stagePct1..6
old_stage_grid = '''            <!-- 6 Sequenced Stage Status Cards -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(150px, 1fr)); gap: 8px; margin-top: 12px;" id="gridFullScanStages">
              <!-- Stage 1 -->
              <div id="stageCard1" class="stat-card" style="border-left-color: var(--lcars-banana); padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 1 • 0x89 0x01</div>
                <strong style="color: var(--lcars-banana); font-size: 0.88rem; display: block; margin-top: 2px;">OPTICAL GLUCOSE</strong>
                <div id="stageStatus1" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 2 -->
              <div id="stageCard2" class="stat-card" style="border-left-color: var(--lcars-blue); padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 2 • 0x87 0x01</div>
                <strong style="color: var(--lcars-blue); font-size: 0.88rem; display: block; margin-top: 2px;">BODY TEMPERATURE</strong>
                <div id="stageStatus2" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 3 -->
              <div id="stageCard3" class="stat-card" style="border-left-color: #ff9944; padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 3 • 0x90 0x01</div>
                <strong style="color: #ff9944; font-size: 0.88rem; display: block; margin-top: 2px;">BLOOD PRESSURE</strong>
                <div id="stageStatus3" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 4 -->
              <div id="stageCard4" class="stat-card" style="border-left-color: var(--lcars-green); padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 4 • 0x80 0x02</div>
                <strong style="color: var(--lcars-green); font-size: 0.88rem; display: block; margin-top: 2px;">BLOOD OXYGEN (SpO2)</strong>
                <div id="stageStatus4" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 5 -->
              <div id="stageCard5" class="stat-card" style="border-left-color: #ff5566; padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 5 • 0xD0 0x01</div>
                <strong style="color: #ff5566; font-size: 0.88rem; display: block; margin-top: 2px;">HEART RATE (PULSE)</strong>
                <div id="stageStatus5" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 6 -->
              <div id="stageCard6" class="stat-card" style="border-left-color: var(--lcars-peach); padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 6 • 0x89 0x07</div>
                <strong style="color: var(--lcars-peach); font-size: 0.88rem; display: block; margin-top: 2px;">HRV &amp; NEURO-STRESS</strong>
                <div id="stageStatus6" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
            </div>'''

new_stage_grid = '''            <!-- 6 Sequenced Stage Status Cards (Zero Hex Codes • Live Colored % Complete) -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(150px, 1fr)); gap: 8px; margin-top: 12px;" id="gridFullScanStages">
              <!-- Stage 1 -->
              <div id="stageCard1" class="stat-card" style="border-left-color: var(--lcars-banana); padding: 10px; opacity: 0.6;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span style="font-size: 0.72rem; color: var(--lcars-gray); font-weight: bold; letter-spacing: 0.05em;">STAGE 01</span>
                  <span id="stagePct1" style="font-size: 0.90rem; font-weight: 900; color: var(--lcars-gray); font-family: var(--lcars-mono);">0%</span>
                </div>
                <strong style="color: var(--lcars-banana); font-size: 0.88rem; display: block; margin-top: 2px;">OPTICAL GLUCOSE</strong>
                <div id="stageStatus1" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 2 -->
              <div id="stageCard2" class="stat-card" style="border-left-color: var(--lcars-blue); padding: 10px; opacity: 0.6;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span style="font-size: 0.72rem; color: var(--lcars-gray); font-weight: bold; letter-spacing: 0.05em;">STAGE 02</span>
                  <span id="stagePct2" style="font-size: 0.90rem; font-weight: 900; color: var(--lcars-gray); font-family: var(--lcars-mono);">0%</span>
                </div>
                <strong style="color: var(--lcars-blue); font-size: 0.88rem; display: block; margin-top: 2px;">BODY TEMPERATURE</strong>
                <div id="stageStatus2" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 3 -->
              <div id="stageCard3" class="stat-card" style="border-left-color: #ff9944; padding: 10px; opacity: 0.6;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span style="font-size: 0.72rem; color: var(--lcars-gray); font-weight: bold; letter-spacing: 0.05em;">STAGE 03</span>
                  <span id="stagePct3" style="font-size: 0.90rem; font-weight: 900; color: var(--lcars-gray); font-family: var(--lcars-mono);">0%</span>
                </div>
                <strong style="color: #ff9944; font-size: 0.88rem; display: block; margin-top: 2px;">BLOOD PRESSURE</strong>
                <div id="stageStatus3" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 4 -->
              <div id="stageCard4" class="stat-card" style="border-left-color: var(--lcars-green); padding: 10px; opacity: 0.6;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span style="font-size: 0.72rem; color: var(--lcars-gray); font-weight: bold; letter-spacing: 0.05em;">STAGE 04</span>
                  <span id="stagePct4" style="font-size: 0.90rem; font-weight: 900; color: var(--lcars-gray); font-family: var(--lcars-mono);">0%</span>
                </div>
                <strong style="color: var(--lcars-green); font-size: 0.88rem; display: block; margin-top: 2px;">BLOOD OXYGEN (SpO2)</strong>
                <div id="stageStatus4" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 5 -->
              <div id="stageCard5" class="stat-card" style="border-left-color: #ff5566; padding: 10px; opacity: 0.6;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span style="font-size: 0.72rem; color: var(--lcars-gray); font-weight: bold; letter-spacing: 0.05em;">STAGE 05</span>
                  <span id="stagePct5" style="font-size: 0.90rem; font-weight: 900; color: var(--lcars-gray); font-family: var(--lcars-mono);">0%</span>
                </div>
                <strong style="color: #ff5566; font-size: 0.88rem; display: block; margin-top: 2px;">HEART RATE (PULSE)</strong>
                <div id="stageStatus5" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 6 -->
              <div id="stageCard6" class="stat-card" style="border-left-color: var(--lcars-peach); padding: 10px; opacity: 0.6;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span style="font-size: 0.72rem; color: var(--lcars-gray); font-weight: bold; letter-spacing: 0.05em;">STAGE 06</span>
                  <span id="stagePct6" style="font-size: 0.90rem; font-weight: 900; color: var(--lcars-gray); font-family: var(--lcars-mono);">0%</span>
                </div>
                <strong style="color: var(--lcars-peach); font-size: 0.88rem; display: block; margin-top: 2px;">HRV &amp; NEURO-STRESS</strong>
                <div id="stageStatus6" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
            </div>'''
if old_stage_grid in html:
    html = html.replace(old_stage_grid, new_stage_grid)
    print("[✔] Stage grid updated with human-readable STAGE 01..06 and stagePct elements")

# 3. Update setStageCardState
old_stage_state_fn = '''    function setStageCardState(stageNum, state, message) {
      const card = document.getElementById('stageCard' + stageNum);
      const status = document.getElementById('stageStatus' + stageNum);
      if (!card || !status) return;

      if (state === 'active') {
        card.style.opacity = '1';
        card.style.boxShadow = '0 0 12px rgba(255, 187, 0, 0.4)';
        card.style.borderColor = 'var(--lcars-banana)';
        status.innerHTML = `<span style="color: var(--lcars-banana); font-weight: bold;">● SCANNING (${message || 'In Progress'})</span>`;
      } else if (state === 'done') {
        card.style.opacity = '1';
        card.style.boxShadow = 'none';
        card.style.borderColor = 'var(--lcars-green)';
        status.innerHTML = `<span style="color: var(--lcars-green); font-weight: bold;">✓ ${message || 'ACQUIRED'}</span>`;
      } else if (state === 'skipped') {
        card.style.opacity = '0.7';
        card.style.boxShadow = 'none';
        status.innerHTML = `<span style="color: var(--lcars-blue);">⏭ SKIPPED</span>`;
      } else {
        // idle
        card.style.opacity = '0.5';
        card.style.boxShadow = 'none';
        card.style.borderColor = '';
        status.innerHTML = `<span style="color: var(--lcars-gray);">IDLE</span>`;
      }
    }'''

new_stage_state_fn = '''    function setStageCardState(stageNum, state, message, pct) {
      const card = document.getElementById('stageCard' + stageNum);
      const status = document.getElementById('stageStatus' + stageNum);
      const pctEl = document.getElementById('stagePct' + stageNum);
      if (!card || !status) return;

      if (state === 'active') {
        card.style.opacity = '1';
        card.style.boxShadow = '0 0 12px rgba(255, 187, 0, 0.4)';
        card.style.borderColor = 'var(--lcars-banana)';
        status.innerHTML = `<span style="color: var(--lcars-banana); font-weight: bold;">● SCANNING (${message || 'In Progress'})</span>`;
        if (pctEl) {
          const p = (pct !== undefined) ? pct : 50;
          pctEl.textContent = `${p}%`;
          pctEl.style.color = 'var(--lcars-banana)';
        }
      } else if (state === 'done') {
        card.style.opacity = '1';
        card.style.boxShadow = 'none';
        card.style.borderColor = 'var(--lcars-green)';
        status.innerHTML = `<span style="color: var(--lcars-green); font-weight: bold;">✓ ${message || 'ACQUIRED'}</span>`;
        if (pctEl) {
          pctEl.textContent = '100%';
          pctEl.style.color = 'var(--lcars-green)';
        }
      } else if (state === 'skipped') {
        card.style.opacity = '0.7';
        card.style.boxShadow = 'none';
        status.innerHTML = `<span style="color: var(--lcars-blue);">⏭ SKIPPED</span>`;
        if (pctEl) {
          pctEl.textContent = '--%';
          pctEl.style.color = 'var(--lcars-blue)';
        }
      } else {
        // idle
        card.style.opacity = '0.5';
        card.style.boxShadow = 'none';
        card.style.borderColor = '';
        status.innerHTML = `<span style="color: var(--lcars-gray);">IDLE</span>`;
        if (pctEl) {
          pctEl.textContent = '0%';
          pctEl.style.color = 'var(--lcars-gray)';
        }
      }
    }'''
if old_stage_state_fn in html:
    html = html.replace(old_stage_state_fn, new_stage_state_fn)
    print("[✔] setStageCardState updated to manage colored percentage")

# 4. Scrub hex codes from stageLbl in executeScanStage1_Glucose..Stage6_HRV
stage_labels = [
    ('STAGE 1 / 6: OPTICAL BLOOD GLUCOSE (PPG 0x89 0x01)', 'STAGE 1 / 6: OPTICAL BLOOD GLUCOSE'),
    ('STAGE 2 / 6: BODY & SKIN TEMPERATURE (0x87 0x01)', 'STAGE 2 / 6: BODY & SKIN TEMPERATURE'),
    ('STAGE 3 / 6: AMBULATORY BLOOD PRESSURE (0x90 0x01)', 'STAGE 3 / 6: AMBULATORY BLOOD PRESSURE'),
    ('STAGE 4 / 6: BLOOD OXYGENATION SpO2 (0x80 0x02)', 'STAGE 4 / 6: BLOOD OXYGENATION (SpO2)'),
    ('STAGE 5 / 6: DYNAMIC HEART RATE (PULSE 0xD0 0x01)', 'STAGE 5 / 6: DYNAMIC HEART RATE (PULSE)'),
    ('STAGE 6 / 6: AUTONOMIC HRV & NEURO-STRESS (0x89 0x07)', 'STAGE 6 / 6: AUTONOMIC HRV & NEURO-STRESS')
]
for old_lbl, new_lbl in stage_labels:
    if old_lbl in html:
        html = html.replace(old_lbl, new_lbl)
        print(f"[✔] Scrubbed hex code: {old_lbl} -> {new_lbl}")

# 5. Add pct to the 6 stage countdown intervals
old_s1 = "setStageCardState(1, 'active', `${curG} mg/dL`);"
new_s1 = "const gPct = Math.min(99, Math.max(1, Math.round(((22 - fullScanCountdown) / 22) * 100))); setStageCardState(1, 'active', `${curG} mg/dL`, gPct);"
if old_s1 in html: html = html.replace(old_s1, new_s1)

old_s2 = "setStageCardState(2, 'active', `${curF}°F`);"
new_s2 = "const tPct = Math.min(99, Math.max(1, Math.round(((12 - fullScanCountdown) / 12) * 100))); setStageCardState(2, 'active', `${curF}°F`, tPct);"
if old_s2 in html: html = html.replace(old_s2, new_s2)

old_s3 = "setStageCardState(3, 'active', `${s}/${d} mmHg`);"
new_s3 = "const bpPct = Math.min(99, Math.max(1, Math.round(((20 - fullScanCountdown) / 20) * 100))); setStageCardState(3, 'active', `${s}/${d} mmHg`, bpPct);"
if old_s3 in html: html = html.replace(old_s3, new_s3)

old_s4 = "setStageCardState(4, 'active', `${curSp}%`);"
new_s4 = "const o2Pct = Math.min(99, Math.max(1, Math.round(((15 - fullScanCountdown) / 15) * 100))); setStageCardState(4, 'active', `${curSp}%`, o2Pct);"
if old_s4 in html: html = html.replace(old_s4, new_s4)

old_s5 = "setStageCardState(5, 'active', `${curHr} BPM`);"
new_s5 = "const hrPct = Math.min(99, Math.max(1, Math.round(((15 - fullScanCountdown) / 15) * 100))); setStageCardState(5, 'active', `${curHr} BPM`, hrPct);"
if old_s5 in html: html = html.replace(old_s5, new_s5)

old_s6 = "setStageCardState(6, 'active', `${curHrv} ms`);"
new_s6 = "const hrvPct = Math.min(99, Math.max(1, Math.round(((18 - fullScanCountdown) / 18) * 100))); setStageCardState(6, 'active', `${curHrv} ms`, hrvPct);"
if old_s6 in html: html = html.replace(old_s6, new_s6)
print("[✔] Stage timer intervals updated with live percentage computation")

# 6. Global safe soundSuccess definition
old_sound_def = '''    function soundSuccess() {
      if (new URLSearchParams(window.location.search).get('headless')) return;
      playBeep(880, 'sine', 0.08, 0.08);
      setTimeout(() => playBeep(1760, 'sine', 0.08, 0.10), 60);
    }'''

new_sound_def = '''    function soundSuccess() {
      if (typeof window !== 'undefined' && new URLSearchParams(window.location.search).get('headless')) return;
      if (typeof playBeep === 'function') {
        playBeep(880, 'sine', 0.08, 0.08);
        setTimeout(() => { if (typeof playBeep === 'function') playBeep(1760, 'sine', 0.08, 0.10); }, 60);
      }
    }
    window.soundSuccess = soundSuccess;'''
if old_sound_def in html:
    html = html.replace(old_sound_def, new_sound_def)
    print("[✔] soundSuccess definition secured globally")

import re
html = re.sub(r'(?<!typeof soundSuccess === [\'"]function[\'"]\)\s)soundSuccess\(\);', 'if (typeof soundSuccess === "function") soundSuccess();', html)
print("[✔] Bare soundSuccess() calls safely guarded")

# 7. Sleep Data Handling in assembleAndParseSleepStream:
old_sleep_log = '''        logLCARS(`[🌙 SOMNO-03] Sleep Telemetry Loaded: ${totalHours} hrs total (Deep: ${deepHours}h / ${deepPct}%, Light: ${lightHours}h / ${lightPct}%, REM: ${remHours}h / ${remPct}%, Wake: ${iWake})`, "rx");
        if (typeof soundSuccess === "function") soundSuccess();'''

new_sleep_log = '''        logLCARS(`[🌙 SOMNO-03] Sleep Telemetry Loaded: ${totalHours} hrs total (Deep: ${deepHours}h / ${deepPct}%, Light: ${lightHours}h / ${lightPct}%, REM: ${remHours}h / ${remPct}%, Wake: ${iWake})`, "rx");
        if (typeof soundSuccess === "function") soundSuccess();

        // Persist sleep telemetry record to SQLite via /api/telemetry
        try {
          fetch('/api/telemetry', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
              metric: 'SLEEP',
              value1: parseFloat(totalHours),
              value2: parseFloat(deepHours),
              unit: 'hours',
              progress: 100,
              status: 'OK',
              mode: `Deep: ${deepPct}%, Light: ${lightPct}%, REM: ${remPct}%`,
              timestamp: new Date().toISOString()
            })
          }).catch(e => console.warn("Sleep telemetry POST deferred:", e));
        } catch (e) {}'''
if old_sleep_log in html:
    html = html.replace(old_sleep_log, new_sleep_log)
    print("[✔] assembleAndParseSleepStream updated to persist SLEEP telemetry to SQLite")

# 8. Sleep Data Handling in pollLiveTelemetryChanges:
old_poll_end = '''          // Hydrate Full Scan View if not currently scanning
          if (!isFullScanRunning && typeof initFullScanView === 'function') {
            initFullScanView();
          }'''

new_poll_end = '''          // 8. Update Sleep Telemetry if present in latest vitals
          if (vitals.SLEEP) {
            const totH = parseFloat(vitals.SLEEP.value1);
            const deepH = parseFloat(vitals.SLEEP.value2);
            if (!isNaN(totH) && totH > 0) {
              const elSleep = document.getElementById("valSleep");
              if (elSleep) elSleep.textContent = totH.toFixed(1);
              const elDeep = document.getElementById("valDeepSleep");
              if (elDeep && !isNaN(deepH) && deepH > 0) {
                const pct = Math.round((deepH / totH) * 100);
                elDeep.textContent = `${deepH.toFixed(1)}h (${pct}%)`;
              }
              const sT = document.getElementById('repChartSleepTitle');
              if (sT) sT.textContent = `LAST NIGHT NOCTURNAL SLEEP HYPNOGRAM (${totH.toFixed(1)} HOURS TOTAL)`;
            }
          }

          // Hydrate Full Scan View if not currently scanning
          if (!isFullScanRunning && typeof initFullScanView === 'function') {
            initFullScanView();
          }'''
if old_poll_end in html:
    html = html.replace(old_poll_end, new_poll_end)
    print("[✔] pollLiveTelemetryChanges updated to ingest and display SLEEP telemetry")

# 9. Add DAILY INTAKE SUMMARY OF ALL CONSUMED component to Tab 04 Metabolic
old_metabolic_macro_ribbon = '''            <!-- Macro Summary Ribbon -->
            <div class="diet-ribbon-summary" style="margin-top: 4px;">
              <div class="stat-card">
                <span class="stat-card-title">TOTAL CARBS</span>
                <span class="stat-card-val" id="sumCarbs">0 <span style="font-size: 1rem;">g</span></span>
                <span class="stat-card-sub">Daily Macro Intake</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-blue);">
                <span class="stat-card-title">TOTAL PROTEIN</span>
                <span class="stat-card-val" style="color: var(--lcars-blue);" id="sumProtein">0 <span style="font-size: 1rem;">g</span></span>
                <span class="stat-card-sub">Daily Macro Intake</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-orange);">
                <span class="stat-card-title">TOTAL CALORIES</span>
                <span class="stat-card-val" style="color: var(--lcars-orange);" id="sumCalories">0 <span style="font-size: 1rem;">kcal</span></span>
                <span class="stat-card-sub">Nutritional Intake</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-green);">
                <span class="stat-card-title">POST-MEAL GLUCOSE AVG</span>
                <span class="stat-card-val" style="color: var(--lcars-green);" id="sumAvgPostGlucose">124 <span style="font-size: 1rem;">mg/dL</span></span>
                <span class="stat-card-sub">Postprandial Glycemic Index</span>
              </div>
            </div>'''

new_metabolic_macro_ribbon = '''            <!-- Macro Summary Ribbon (Today's Daily Totals) -->
            <div class="diet-ribbon-summary" style="margin-top: 4px;">
              <div class="stat-card">
                <span class="stat-card-title">TODAY'S NET CARBS</span>
                <span class="stat-card-val" id="sumCarbs">0 <span style="font-size: 1rem;">g</span></span>
                <span class="stat-card-sub">Diabetic Budget: ≤135g/day</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-blue);">
                <span class="stat-card-title">TODAY'S PROTEIN</span>
                <span class="stat-card-val" style="color: var(--lcars-blue);" id="sumProtein">0 <span style="font-size: 1rem;">g</span></span>
                <span class="stat-card-sub">RDA Target: 75g/day</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-orange);">
                <span class="stat-card-title">TODAY'S CALORIES</span>
                <span class="stat-card-val" style="color: var(--lcars-orange);" id="sumCalories">0 <span style="font-size: 1rem;">kcal</span></span>
                <span class="stat-card-sub">Budget: 2,000 kcal/day</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-green);">
                <span class="stat-card-title">POST-MEAL GLUCOSE AVG</span>
                <span class="stat-card-val" style="color: var(--lcars-green);" id="sumAvgPostGlucose">124 <span style="font-size: 1rem;">mg/dL</span></span>
                <span class="stat-card-sub">Postprandial Glycemic Index</span>
              </div>
            </div>

            <!-- =========================================================================
                 DAILY INTAKE SUMMARY OF ALL CONSUMED (TODAY'S MACRONUTRIENTS & OCCASIONS)
                 ========================================================================= -->
            <div id="cardDailyIntakeSummary" style="background: #ffffff; border: 1.5px solid #cbd5e1; border-left: 6px solid #16a34a; border-radius: 8px; padding: 14px; margin-top: 10px; margin-bottom: 12px; box-shadow: 0 1px 4px rgba(0,0,0,0.06);">
              <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 10px; flex-wrap: wrap; gap: 8px;">
                <div>
                  <span style="font-size: 1.05rem; font-weight: 900; color: #15803d; letter-spacing: 0.05em; font-family: var(--lcars-mono);">
                    📊 DAILY INTAKE SUMMARY OF ALL CONSUMED
                  </span>
                  <span id="lblDailyIntakeDate" style="display: block; font-size: 0.78rem; color: #475569; font-weight: bold; margin-top: 2px;">
                    TODAY: ALL CONSUMED MEALS &amp; SNACKS LOGGED
                  </span>
                </div>
                <div style="display: flex; gap: 6px; align-items: center;">
                  <span id="badgeDailyIntakeStatus" style="background: #ecfdf5; color: #15803d; border: 1.5px solid #10b981; border-radius: 4px; padding: 3px 10px; font-size: 0.75rem; font-weight: 800; font-family: var(--lcars-mono);">
                    ✅ DIABETIC COMPLIANT
                  </span>
                  <button class="lcars-btn btn-blue" style="font-size: 0.72rem; padding: 3px 10px;" onclick="renderDietHistory()">
                    🔄 REFRESH INTAKE
                  </button>
                </div>
              </div>

              <!-- 4-Quadrant Consumed Occasions Grid -->
              <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(210px, 1fr)); gap: 10px; margin-bottom: 12px;">
                <!-- Breakfast Bay -->
                <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-left: 4px solid #f59e0b; border-radius: 6px; padding: 10px;">
                  <div style="display: flex; justify-content: space-between; align-items: center;">
                    <strong style="color: #b45309; font-size: 0.85rem;">🌅 BREAKFAST</strong>
                    <span id="summaryCountBreakfast" style="font-size: 0.75rem; font-weight: 800; color: #64748b;">0 items</span>
                  </div>
                  <div id="summaryMacroBreakfast" style="font-size: 0.82rem; font-weight: bold; color: #0f172a; margin-top: 4px;">0 kcal &bull; 0g Net Carbs</div>
                  <div id="summaryListBreakfast" style="font-size: 0.75rem; color: #475569; margin-top: 4px; max-height: 48px; overflow-y: auto;">None recorded</div>
                </div>

                <!-- Lunch Bay -->
                <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-left: 4px solid #0284c7; border-radius: 6px; padding: 10px;">
                  <div style="display: flex; justify-content: space-between; align-items: center;">
                    <strong style="color: #0369a1; font-size: 0.85rem;">🥪 LUNCH</strong>
                    <span id="summaryCountLunch" style="font-size: 0.75rem; font-weight: 800; color: #64748b;">0 items</span>
                  </div>
                  <div id="summaryMacroLunch" style="font-size: 0.82rem; font-weight: bold; color: #0f172a; margin-top: 4px;">0 kcal &bull; 0g Net Carbs</div>
                  <div id="summaryListLunch" style="font-size: 0.75rem; color: #475569; margin-top: 4px; max-height: 48px; overflow-y: auto;">None recorded</div>
                </div>

                <!-- Dinner Bay -->
                <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-left: 4px solid #7c3aed; border-radius: 6px; padding: 10px;">
                  <div style="display: flex; justify-content: space-between; align-items: center;">
                    <strong style="color: #6d28d9; font-size: 0.85rem;">🍽️ DINNER</strong>
                    <span id="summaryCountDinner" style="font-size: 0.75rem; font-weight: 800; color: #64748b;">0 items</span>
                  </div>
                  <div id="summaryMacroDinner" style="font-size: 0.82rem; font-weight: bold; color: #0f172a; margin-top: 4px;">0 kcal &bull; 0g Net Carbs</div>
                  <div id="summaryListDinner" style="font-size: 0.75rem; color: #475569; margin-top: 4px; max-height: 48px; overflow-y: auto;">None recorded</div>
                </div>

                <!-- Snacks & Refreshments Bay (Includes all cell uploads) -->
                <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-left: 4px solid #10b981; border-radius: 6px; padding: 10px;">
                  <div style="display: flex; justify-content: space-between; align-items: center;">
                    <strong style="color: #047857; font-size: 0.85rem;">🍎 SNACKS &amp; REFRESHMENTS</strong>
                    <span id="summaryCountSnack" style="font-size: 0.75rem; font-weight: 800; color: #64748b;">0 items</span>
                  </div>
                  <div id="summaryMacroSnack" style="font-size: 0.82rem; font-weight: bold; color: #0f172a; margin-top: 4px;">0 kcal &bull; 0g Net Carbs</div>
                  <div id="summaryListSnack" style="font-size: 0.75rem; color: #475569; margin-top: 4px; max-height: 48px; overflow-y: auto;">None recorded</div>
                </div>
              </div>

              <!-- Daily Nutritional Totals vs Goals Bar -->
              <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 8px; background: #f1f5f9; border-radius: 6px; padding: 8px 12px; font-family: var(--lcars-mono); font-size: 0.78rem;">
                <div><span style="color: #64748b;">CALORIES:</span> <strong id="lblDayKcal" style="color: #ea580c;">0 / 2,000 kcal</strong></div>
                <div><span style="color: #64748b;">NET CARBS:</span> <strong id="lblDayNetCarbs" style="color: #15803d;">0 / 135 g</strong></div>
                <div><span style="color: #64748b;">TOTAL PROTEIN:</span> <strong id="lblDayProtein" style="color: #0284c7;">0 / 75 g</strong></div>
                <div><span style="color: #64748b;">TOTAL FAT:</span> <strong id="lblDayFat" style="color: #7c3aed;">0 g</strong></div>
                <div><span style="color: #64748b;">TOTAL FIBER:</span> <strong id="lblDayFiber" style="color: #059669;">0 g</strong></div>
              </div>
            </div>'''
if old_metabolic_macro_ribbon in html:
    html = html.replace(old_metabolic_macro_ribbon, new_metabolic_macro_ribbon)
    print("[✔] Added Daily Intake Summary of All Consumed card to Tab 04 Metabolic")

# 10. Update renderDietHistory to strictly sort latest to oldest & calculate Daily Intake Summary
old_render_diet_history = '''    async function renderDietHistory() {
      const list = document.getElementById('dietHistoryList');
      if (!list) return;

      // 1. Fetch real submitted meals from server SQLite repository
      let meals = [];
      try {
        const resp = await fetch('/api/meal/recent?limit=100');
        if (resp.ok) {
          const data = await resp.json();
          if (data && data.meals && data.meals.length > 0) {
            meals = data.meals;
          }
        }
      } catch (e) {
        console.warn("Could not fetch remote meal history, falling back to local journal:", e);
      }

      // If remote empty, fallback to local mealJournal
      if (meals.length === 0 && typeof mealJournal !== 'undefined' && mealJournal.length > 0) {
        meals = mealJournal.map(m => ({
          id: m.id,
          timestamp: m.ts || new Date().toISOString(),
          meal_name: m.desc || m.type,
          calories: m.cal || 0,
          net_carbs: m.carbs || 0,
          protein: m.protein || 0,
          fat: 0,
          fiber: 0,
          glycemic_load: "Medium",
          vendor_matched: m.context || "User Manual Entry",
          meets_goal: m.carbs <= 45 ? 1 : 0,
          gemini_notes: m.desc || ""
        }));
      }

      list.innerHTML = '';

      if (meals.length === 0) {
        list.innerHTML = `
          <div style="padding: 24px; text-align: center; color: var(--lcars-blue); font-family: var(--lcars-mono); font-size: 0.95rem; border: 1px dashed #cbd5e1; border-radius: 6px; background: #ffffff;">
            NO MEALS RECORDED IN REPOSITORY • USE THE FORM ABOVE OR MOBILE /cell GATEWAY TO LOG MEALS
          </div>
        `;
        const sC = document.getElementById('sumCarbs'); if (sC && sC.firstChild) sC.firstChild.textContent = "0";
        const sP = document.getElementById('sumProtein'); if (sP && sP.firstChild) sP.firstChild.textContent = "0";
        const sCal = document.getElementById('sumCalories'); if (sCal && sCal.firstChild) sCal.firstChild.textContent = "0";
        return;
      }

      let totalC = 0, totalP = 0, totalKcal = 0;

      meals.forEach(m => {
        const netC = m.net_carbs !== undefined ? m.net_carbs : (m.carbs || 0);
        const prot = m.protein || 0;
        const cal = m.calories || m.cal || 0;
        const meetsGoal = (m.meets_goal === 1) || (netC <= 45);

        totalC += netC;
        totalP += prot;
        totalKcal += cal;

        const badgeHtml = meetsGoal
          ? `<span style="display: inline-block; background: #ecfdf5; color: #15803d; border: 1.5px solid #10b981; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">✅ GOAL MET (≤45g Net)</span>`
          : `<span style="display: inline-block; background: #fef2f2; color: #b91c1c; border: 1.5px solid #ef4444; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">⚠️ EXCEEDS 45g (+${Math.round(netC - 45)}g Net) • GLUT4 WALK NEEDED</span>`;

        const item = document.createElement('div');
        item.className = 'diet-item-card';
        item.style.cssText = "background: #ffffff; border: 1.5px solid #e2e8f0; border-left: 5px solid " + (meetsGoal ? "#10b981" : "#ef4444") + "; border-radius: 6px; padding: 10px 14px; box-shadow: 0 1px 3px rgba(0,0,0,0.04);";
        item.innerHTML = `
          <div style="display: flex; justify-content: space-between; align-items: flex-start; gap: 10px; flex-wrap: wrap;">
            <div>
              <div style="display: flex; align-items: center; gap: 8px;">
                <strong style="color: #0f172a; font-size: 0.95rem;">${m.meal_name || "Custom Meal"}</strong>
                ${badgeHtml}
              </div>
              <div style="font-size: 0.78rem; color: #64748b; margin-top: 2px;">
                ${m.timestamp ? m.timestamp.replace('T', ' ').substring(0, 19) : 'Just Now'} &bull; Source: <span style="font-weight: 600; color: #0284c7;">${m.source || "Manual Entry"}</span>
              </div>
            </div>
            <div style="display: flex; gap: 12px; font-family: var(--lcars-mono); font-size: 0.85rem; background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 4px; padding: 4px 10px;">
              <div><span style="color: #64748b;">CAL:</span> <strong style="color: #ea580c;">${cal}</strong></div>
              <div><span style="color: #64748b;">NET CARBS:</span> <strong style="color: ${netC <= 45 ? '#15803d' : '#dc2626'};">${netC}g</strong></div>
              <div><span style="color: #64748b;">PROTEIN:</span> <strong style="color: #0284c7;">${prot}g</strong></div>
            </div>
          </div>
          ${m.gemini_notes ? `<div style="font-size: 0.78rem; color: #334155; margin-top: 6px; padding: 4px 8px; background: #f1f5f9; border-radius: 4px; font-style: italic;">💡 ${m.gemini_notes}</div>` : ''}
        `;
        list.appendChild(item);
      });

      const sC = document.getElementById('sumCarbs'); if (sC && sC.firstChild) sC.firstChild.textContent = Math.round(totalC);
      const sP = document.getElementById('sumProtein'); if (sP && sP.firstChild) sP.firstChild.textContent = Math.round(totalP);
      const sCal = document.getElementById('sumCalories'); if (sCal && sCal.firstChild) sCal.firstChild.textContent = Math.round(totalKcal);
    }'''

new_render_diet_history = '''    async function renderDietHistory() {
      const list = document.getElementById('dietHistoryList');
      if (!list) return;

      // 1. Fetch real submitted meals from server SQLite repository
      let meals = [];
      try {
        const resp = await fetch('/api/meal/recent?limit=100');
        if (resp.ok) {
          const data = await resp.json();
          if (data && data.meals && data.meals.length > 0) {
            meals = data.meals;
          }
        }
      } catch (e) {
        console.warn("Could not fetch remote meal history, falling back to local journal:", e);
      }

      // If remote empty, fallback to local mealJournal
      if (meals.length === 0 && typeof mealJournal !== 'undefined' && mealJournal.length > 0) {
        meals = mealJournal.map(m => ({
          id: m.id,
          timestamp: m.ts || new Date().toISOString(),
          meal_name: m.desc || m.type,
          calories: m.cal || 0,
          net_carbs: m.carbs || 0,
          protein: m.protein || 0,
          fat: 0,
          fiber: 0,
          glycemic_load: "Medium",
          vendor_matched: m.context || "User Manual Entry",
          meets_goal: m.carbs <= 45 ? 1 : 0,
          gemini_notes: m.desc || ""
        }));
      }

      // GUARANTEE STRICT LATEST-TO-OLDEST SORTING
      meals.sort((a, b) => {
        const tA = new Date(a.timestamp || 0).getTime();
        const tB = new Date(b.timestamp || 0).getTime();
        if (tB !== tA) return tB - tA;
        return (b.id || 0) - (a.id || 0);
      });

      list.innerHTML = '';

      if (meals.length === 0) {
        list.innerHTML = `
          <div style="padding: 24px; text-align: center; color: var(--lcars-blue); font-family: var(--lcars-mono); font-size: 0.95rem; border: 1px dashed #cbd5e1; border-radius: 6px; background: #ffffff;">
            NO MEALS RECORDED IN REPOSITORY • USE THE FORM ABOVE OR MOBILE /cell GATEWAY TO LOG MEALS
          </div>
        `;
        const sC = document.getElementById('sumCarbs'); if (sC && sC.firstChild) sC.firstChild.textContent = "0";
        const sP = document.getElementById('sumProtein'); if (sP && sP.firstChild) sP.firstChild.textContent = "0";
        const sCal = document.getElementById('sumCalories'); if (sCal && sCal.firstChild) sCal.firstChild.textContent = "0";
        return;
      }

      // 2. Compute DAILY INTAKE SUMMARY OF ALL CONSUMED (for TODAY)
      const now = new Date();
      const todayIsoPrefix = `${now.getFullYear()}-${String(now.getMonth() + 1).padStart(2, '0')}-${String(now.getDate()).padStart(2, '0')}`;
      const todayMeals = meals.filter(m => (m.timestamp || '').startsWith(todayIsoPrefix));

      // Occasion grouping for Today's Intake Summary
      const occasions = {
        Breakfast: { count: 0, kcal: 0, netC: 0, items: [] },
        Lunch: { count: 0, kcal: 0, netC: 0, items: [] },
        Dinner: { count: 0, kcal: 0, netC: 0, items: [] },
        Snack: { count: 0, kcal: 0, netC: 0, items: [] }
      };

      let dayTotalKcal = 0, dayTotalNetC = 0, dayTotalPro = 0, dayTotalFat = 0, dayTotalFib = 0;
      let dayMissedGoalCount = 0;

      // If todayMeals has entries, compute from todayMeals; otherwise show latest day or active session
      const intakeSet = todayMeals.length > 0 ? todayMeals : meals.slice(0, 10);
      intakeSet.forEach(m => {
        const cal = m.calories || m.cal || 0;
        const netC = m.net_carbs !== undefined ? m.net_carbs : (m.carbs || 0);
        const pro = m.protein || 0;
        const fat = m.fat || 0;
        const fib = m.fiber || 0;

        dayTotalKcal += cal;
        dayTotalNetC += netC;
        dayTotalPro += pro;
        dayTotalFat += fat;
        dayTotalFib += fib;

        if (netC > 45) dayMissedGoalCount++;

        // Classify meal occasion
        const nameStr = ((m.meal_name || '') + ' ' + (m.source || '') + ' ' + (m.gemini_notes || '')).toLowerCase();
        let occ = 'Snack';
        if (nameStr.includes('breakfast') || nameStr.includes('morning') || nameStr.includes('egg') || nameStr.includes('omelet') || nameStr.includes('coffee')) {
          occ = 'Breakfast';
        } else if (nameStr.includes('lunch') || nameStr.includes('sandwich') || nameStr.includes('burger') || nameStr.includes('salad') || nameStr.includes('wrap')) {
          occ = 'Lunch';
        } else if (nameStr.includes('dinner') || nameStr.includes('supper') || nameStr.includes('steak') || nameStr.includes('salmon') || nameStr.includes('taco')) {
          occ = 'Dinner';
        }

        occasions[occ].count++;
        occasions[occ].kcal += cal;
        occasions[occ].netC += netC;
        occasions[occ].items.push(m.meal_name || 'Meal');
      });

      // Update Daily Intake Summary Card Elements
      const lblDate = document.getElementById('lblDailyIntakeDate');
      if (lblDate) {
        const dateOptions = { weekday: 'long', year: 'numeric', month: 'short', day: 'numeric' };
        lblDate.textContent = `${now.toLocaleDateString('en-US', dateOptions).toUpperCase()} • ${intakeSet.length} CONSUMED ITEMS LOGGED`;
      }

      ['Breakfast', 'Lunch', 'Dinner', 'Snack'].forEach(occ => {
        const cEl = document.getElementById(`summaryCount${occ}`);
        const mEl = document.getElementById(`summaryMacro${occ}`);
        const lEl = document.getElementById(`summaryList${occ}`);
        const data = occasions[occ];
        if (cEl) cEl.textContent = `${data.count} ${data.count === 1 ? 'item' : 'items'}`;
        if (mEl) mEl.textContent = `${Math.round(data.kcal)} kcal • ${Math.round(data.netC)}g Net Carbs`;
        if (lEl) {
          lEl.textContent = data.items.length > 0 ? data.items.join(', ') : 'None recorded';
          lEl.title = data.items.join(', ');
        }
      });

      const elDayKcal = document.getElementById('lblDayKcal');
      if (elDayKcal) elDayKcal.textContent = `${Math.round(dayTotalKcal)} / 2,000 kcal`;
      const elDayNet = document.getElementById('lblDayNetCarbs');
      if (elDayNet) elDayNet.textContent = `${Math.round(dayTotalNetC)} / 135 g`;
      const elDayPro = document.getElementById('lblDayProtein');
      if (elDayPro) elDayPro.textContent = `${Math.round(dayTotalPro)} / 75 g`;
      const elDayFat = document.getElementById('lblDayFat');
      if (elDayFat) elDayFat.textContent = `${Math.round(dayTotalFat)} g`;
      const elDayFib = document.getElementById('lblDayFiber');
      if (elDayFib) elDayFib.textContent = `${Math.round(dayTotalFib)} g`;

      const badgeDaily = document.getElementById('badgeDailyIntakeStatus');
      if (badgeDaily) {
        if (dayMissedGoalCount === 0) {
          badgeDaily.textContent = "✅ ALL INTAKES MET DIABETIC GOALS (≤45g NET)";
          badgeDaily.style.background = "#ecfdf5";
          badgeDaily.style.color = "#15803d";
          badgeDaily.style.borderColor = "#10b981";
        } else {
          badgeDaily.textContent = `⚠️ ${dayMissedGoalCount} INTAKE EXCEEDED 45g NET • GLUT4 WALK ADVISORY`;
          badgeDaily.style.background = "#fef2f2";
          badgeDaily.style.color = "#b91c1c";
          badgeDaily.style.borderColor = "#ef4444";
        }
      }

      // Update Top Ribbon Macro Badges to Today's Consumed
      const sC = document.getElementById('sumCarbs'); if (sC && sC.firstChild) sC.firstChild.textContent = Math.round(dayTotalNetC);
      const sP = document.getElementById('sumProtein'); if (sP && sP.firstChild) sP.firstChild.textContent = Math.round(dayTotalPro);
      const sCal = document.getElementById('sumCalories'); if (sCal && sCal.firstChild) sCal.firstChild.textContent = Math.round(dayTotalKcal);

      // 3. Render submitted meals list strictly latest to oldest (bounded viewport shows latest 5, vertically scrollable)
      meals.forEach((m, idx) => {
        const netC = m.net_carbs !== undefined ? m.net_carbs : (m.carbs || 0);
        const prot = m.protein || 0;
        const cal = m.calories || m.cal || 0;
        const meetsGoal = (m.meets_goal === 1) || (netC <= 45);

        const badgeHtml = meetsGoal
          ? `<span style="display: inline-block; background: #ecfdf5; color: #15803d; border: 1.5px solid #10b981; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">✅ GOAL MET (≤45g Net)</span>`
          : `<span style="display: inline-block; background: #fef2f2; color: #b91c1c; border: 1.5px solid #ef4444; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">⚠️ EXCEEDS 45g (+${Math.round(netC - 45)}g Net) • GLUT4 WALK NEEDED</span>`;

        const isLatest = (idx === 0);
        const item = document.createElement('div');
        item.className = 'diet-item-card';
        item.style.cssText = "background: #ffffff; border: 1.5px solid #e2e8f0; border-left: 5px solid " + (meetsGoal ? "#10b981" : "#ef4444") + "; border-radius: 6px; padding: 10px 14px; box-shadow: 0 1px 3px rgba(0,0,0,0.04);";
        item.innerHTML = `
          <div style="display: flex; justify-content: space-between; align-items: flex-start; gap: 10px; flex-wrap: wrap;">
            <div>
              <div style="display: flex; align-items: center; gap: 8px;">
                ${isLatest ? `<span style="background: var(--lcars-banana); color: #000; font-weight: 900; font-size: 0.70rem; padding: 1px 6px; border-radius: 3px; font-family: var(--lcars-mono);">LATEST</span>` : ''}
                <strong style="color: #0f172a; font-size: 0.95rem;">${m.meal_name || "Custom Meal"}</strong>
                ${badgeHtml}
              </div>
              <div style="font-size: 0.78rem; color: #64748b; margin-top: 2px;">
                ${m.timestamp ? m.timestamp.replace('T', ' ').substring(0, 19) : 'Just Now'} &bull; Source: <span style="font-weight: 600; color: #0284c7;">${m.source || "Manual Entry"}</span>
              </div>
            </div>
            <div style="display: flex; gap: 12px; font-family: var(--lcars-mono); font-size: 0.85rem; background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 4px; padding: 4px 10px;">
              <div><span style="color: #64748b;">CAL:</span> <strong style="color: #ea580c;">${cal}</strong></div>
              <div><span style="color: #64748b;">NET CARBS:</span> <strong style="color: ${netC <= 45 ? '#15803d' : '#dc2626'};">${netC}g</strong></div>
              <div><span style="color: #64748b;">PROTEIN:</span> <strong style="color: #0284c7;">${prot}g</strong></div>
            </div>
          </div>
          ${m.gemini_notes ? `<div style="font-size: 0.78rem; color: #334155; margin-top: 6px; padding: 4px 8px; background: #f1f5f9; border-radius: 4px; font-style: italic;">💡 ${m.gemini_notes}</div>` : ''}
        `;
        list.appendChild(item);
      });
    }'''
if old_render_diet_history in html:
    html = html.replace(old_render_diet_history, new_render_diet_history)
    print("[✔] renderDietHistory updated to calculate Daily Intake Summary and sort latest-to-oldest")

# 11. Update syncPopularMealsWithServer to populate #listboxPopularMeals as well as selCommonMealPackage
old_sync_pop = '''    async function syncPopularMealsWithServer() {
      try {
        const resp = await fetch('/api/meals/popular');
        if (resp.ok) {
          const data = await resp.json();
          if (data && data.popular_meals && Array.isArray(data.popular_meals)) {
            data.popular_meals.forEach(m => {
              if (m.id) {
                POPULAR_MEALS[m.id] = {
                  id: m.id.toUpperCase(),
                  title: m.title,
                  desc: m.description,
                  calories: m.calories,
                  netCarbs: m.net_carbs,
                  totalCarbs: m.total_carbs,
                  fiber: m.fiber,
                  protein: m.protein,
                  fat: m.fat,
                  gl: m.glycemic_load,
                  meetsGoal: m.meets_goal,
                  tips: m.tips || []
                };
              }
            });
            savePopularMealsToStorage();
          }
        }
      } catch (e) {}
    }'''

new_sync_pop = '''    async function syncPopularMealsWithServer() {
      try {
        const resp = await fetch('/api/meals/popular');
        if (resp.ok) {
          const data = await resp.json();
          if (data && data.popular_meals && Array.isArray(data.popular_meals)) {
            data.popular_meals.forEach(m => {
              if (m.id) {
                POPULAR_MEALS[m.id] = {
                  id: m.id.toUpperCase(),
                  title: m.title,
                  desc: m.description,
                  calories: m.calories,
                  netCarbs: m.net_carbs,
                  totalCarbs: m.total_carbs,
                  fiber: m.fiber,
                  protein: m.protein,
                  fat: m.fat,
                  gl: m.glycemic_load,
                  meetsGoal: m.meets_goal,
                  tips: m.tips || []
                };
              }
            });
            savePopularMealsToStorage();

            // Synchronize with #listboxPopularMeals in Meal Pantry
            const listbox = document.getElementById('listboxPopularMeals');
            if (listbox) {
              listbox.innerHTML = '';
              data.popular_meals.forEach(m => {
                const opt = document.createElement('option');
                opt.value = m.id;
                const statusIcon = (m.net_carbs <= 45) ? '✅' : '⚠️';
                opt.textContent = `${statusIcon} ${m.title} (${m.net_carbs}g Net Carbs • ${m.calories} kcal)`;
                listbox.appendChild(opt);
              });
            }
          }
        }
      } catch (e) {}
    }'''
if old_sync_pop in html:
    html = html.replace(old_sync_pop, new_sync_pop)
    print("[✔] syncPopularMealsWithServer updated to refresh #listboxPopularMeals")

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

print("Both index.html and w11_htm_app/index.html updated and synchronized successfully!")
