with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# 1. Remove Mobile Cell Gateway Pill (lines ~5669-5678)
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
assert old_cell_pill in html, "old_cell_pill not found"
html = html.replace(old_cell_pill, '')

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
assert old_stage_grid in html, "old_stage_grid not found"
html = html.replace(old_stage_grid, new_stage_grid)

# 3. Update setStageCardState to update stagePct1..6
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
assert old_stage_state_fn in html, "old_stage_state_fn not found"
html = html.replace(old_stage_state_fn, new_stage_state_fn)

# 4. Scrub hex codes from stageLbl in executeScanStage1_Glucose..Stage6_HRV & add pct to setInterval
old_stage1_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 1 / 6: OPTICAL BLOOD GLUCOSE (PPG 0x89 0x01)";'
new_stage1_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 1 / 6: OPTICAL BLOOD GLUCOSE";'
assert old_stage1_exec in html, "old_stage1_exec not found"
html = html.replace(old_stage1_exec, new_stage1_exec)

old_stage1_set = 'setStageCardState(1, \'active\', `${curG} mg/dL`);'
new_stage1_set = 'const gPct = Math.round(((22 - fullScanCountdown) / 22) * 100); setStageCardState(1, \'active\', `${curG} mg/dL`, gPct);'
assert old_stage1_set in html, "old_stage1_set not found"
html = html.replace(old_stage1_set, new_stage1_set)

old_stage2_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 2 / 6: DUAL BODY & SKIN TEMPERATURE (NTC 0x87 0x01)";'
new_stage2_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 2 / 6: DUAL BODY & SKIN TEMPERATURE";'
assert old_stage2_exec in html, "old_stage2_exec not found"
html = html.replace(old_stage2_exec, new_stage2_exec)

old_stage2_set = 'setStageCardState(2, \'active\', `${curF}°F`);'
new_stage2_set = 'const tPct = Math.round(((12 - fullScanCountdown) / 12) * 100); setStageCardState(2, \'active\', `${curF}°F`, tPct);'
assert old_stage2_set in html, "old_stage2_set not found"
html = html.replace(old_stage2_set, new_stage2_set)

old_stage3_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 3 / 6: AMBULATORY BLOOD PRESSURE (PWTT 0x90 0x01)";'
new_stage3_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 3 / 6: AMBULATORY BLOOD PRESSURE";'
assert old_stage3_exec in html, "old_stage3_exec not found"
html = html.replace(old_stage3_exec, new_stage3_exec)

old_stage3_set = 'setStageCardState(3, \'active\', `${s}/${d} mmHg`);'
new_stage3_set = 'const bpPct = Math.round(((20 - fullScanCountdown) / 20) * 100); setStageCardState(3, \'active\', `${s}/${d} mmHg`, bpPct);'
assert old_stage3_set in html, "old_stage3_set not found"
html = html.replace(old_stage3_set, new_stage3_set)

old_stage4_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 4 / 6: BLOOD OXYGEN & HYPOXIA (SpO2 0x80 0x02)";'
new_stage4_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 4 / 6: BLOOD OXYGEN & HYPOXIA (SpO2)";'
assert old_stage4_exec in html, "old_stage4_exec not found"
html = html.replace(old_stage4_exec, new_stage4_exec)

old_stage4_set = 'setStageCardState(4, \'active\', `${curSp}%`);'
new_stage4_set = 'const o2Pct = Math.round(((15 - fullScanCountdown) / 15) * 100); setStageCardState(4, \'active\', `${curSp}%`, o2Pct);'
assert old_stage4_set in html, "old_stage4_set not found"
html = html.replace(old_stage4_set, new_stage4_set)

old_stage5_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 5 / 6: RESTING & ACTIVE HEART RATE (PULSE 0xD0 0x01)";'
new_stage5_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 5 / 6: RESTING & ACTIVE HEART RATE (PULSE)";'
assert old_stage5_exec in html, "old_stage5_exec not found"
html = html.replace(old_stage5_exec, new_stage5_exec)

old_stage5_set = 'setStageCardState(5, \'active\', `${curHr} BPM`);'
new_stage5_set = 'const hrPct = Math.round(((15 - fullScanCountdown) / 15) * 100); setStageCardState(5, \'active\', `${curHr} BPM`, hrPct);'
assert old_stage5_set in html, "old_stage5_set not found"
html = html.replace(old_stage5_set, new_stage5_set)

old_stage6_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 6 / 6: AUTONOMIC HRV & NEURO-STRESS (PPG 0x89 0x07)";'
new_stage6_exec = 'if (stageLbl) stageLbl.textContent = "STAGE 6 / 6: AUTONOMIC HRV & NEURO-STRESS";'
assert old_stage6_exec in html, "old_stage6_exec not found"
html = html.replace(old_stage6_exec, new_stage6_exec)

old_stage6_set = 'setStageCardState(6, \'active\', `${curHrv} ms`);'
new_stage6_set = 'const hrvPct = Math.round(((18 - fullScanCountdown) / 18) * 100); setStageCardState(6, \'active\', `${curHrv} ms`, hrvPct);'
assert old_stage6_set in html, "old_stage6_set not found"
html = html.replace(old_stage6_set, new_stage6_set)

# 5. Fix soundSuccess is not defined & safe invocation
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
assert old_sound_def in html, "old_sound_def not found"
html = html.replace(old_sound_def, new_sound_def)

# Replace all bare soundSuccess(); with if (typeof soundSuccess === 'function') soundSuccess();
# (Notice we don't replace where typeof is already checked)
import re
html = re.sub(r'(?<!typeof soundSuccess === [\'"]function[\'"]\)\s)soundSuccess\(\);', 'if (typeof soundSuccess === "function") soundSuccess();', html)

# 6. Sleep Data Handling in assembleAndParseSleepStream:
# Post sleep record to /api/telemetry and update sleep card
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
assert old_sleep_log in html, "old_sleep_log not found"
html = html.replace(old_sleep_log, new_sleep_log)

# 7. Sleep Data Handling in pollLiveTelemetryChanges:
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
assert old_poll_end in html, "old_poll_end not found"
html = html.replace(old_poll_end, new_poll_end)

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

print("First pass of index.html edits applied successfully!")
