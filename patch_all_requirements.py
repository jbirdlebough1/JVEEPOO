import re
import os

def apply_patch(filepath):
    print(f"[*] Patching {filepath}...")
    with open(filepath, "r", encoding="utf-8") as f:
        text = f.read()

    # 1. Update CSS for .log-tx to Blue
    # Replace body.theme-light .log-tx color from #b45309 to #0284c7
    old_css_tx = """body.theme-light .log-tx {
      color: #b45309 !important;
      font-weight: 600 !important;
    }"""
    new_css_tx = """body.theme-light .log-tx {
      color: #0284c7 !important;
      font-weight: 600 !important;
    }"""
    if old_css_tx in text:
        text = text.replace(old_css_tx, new_css_tx)
        print("  [✔] Updated light theme .log-tx CSS to blue (#0284c7)")

    # Replace dark mode .log-tx
    old_dark_tx = ".log-tx { color: var(--lcars-banana); }"
    new_dark_tx = ".log-tx { color: #38bdf8; }"
    if old_dark_tx in text:
        text = text.replace(old_dark_tx, new_dark_tx)
        print("  [✔] Updated dark mode .log-tx CSS to blue (#38bdf8)")

    # 2. Update logLCARS descColor for tx to Blue
    old_desc = """let descColor = "#0284c7"; // deep ocean blue
      if (type === "rx") descColor = "#15803d"; // clinical emerald green
      else if (type === "tx") descColor = "#b45309"; // rich amber"""
    new_desc = """let descColor = "#64748b"; // muted slate for system
      if (type === "rx") descColor = "#15803d"; // clinical emerald green
      else if (type === "tx") descColor = "#0284c7"; // clinical high-contrast blue"""
    if old_desc in text:
        text = text.replace(old_desc, new_desc)
        print("  [✔] Updated logLCARS descColor for tx to blue")

    # 3. Add propagateStepTelemetry central function
    propagate_code = """
    // =========================================================================
    // UNIVERSAL STEP TELEMETRY PROPAGATION (ALL TABS & HUD)
    // =========================================================================
    function propagateStepTelemetry(steps, kcal = null, miles = null) {
      if (typeof steps !== 'number' || isNaN(steps) || steps < 0) return;
      lastSteps = steps;
      const calcMiles = miles !== null ? miles : ((steps * 0.7) / 1609.34).toFixed(2);
      const calcKcal = kcal !== null ? Math.round(kcal) : Math.round(steps * 0.04);

      // 1. Tab 02 Vitals Pedometer Cards
      const stepEl = document.getElementById("valStepCount");
      if (stepEl) stepEl.innerHTML = `${steps.toLocaleString()} <span style="font-size: 1rem;">steps</span>`;
      const distEl = document.getElementById("valStepDist");
      if (distEl) distEl.innerHTML = `${calcMiles} <span style="font-size: 1rem;">miles</span>`;
      const kcalEl = document.getElementById("valStepKcal");
      if (kcalEl) kcalEl.innerHTML = `${calcKcal} <span style="font-size: 1rem;">kcal</span>`;
      const goalEl = document.getElementById("valStepGoal");
      if (goalEl) goalEl.textContent = `Goal: 2,000 (${((steps / 2000) * 100).toFixed(1)}%)`;

      // 2. Tab 04 Metabolic 360 Tile
      const stepTile = document.getElementById('tile360Motion');
      if (stepTile) stepTile.innerHTML = `${steps.toLocaleString()} <span style="font-size: 0.9rem;">steps</span>`;

      // 3. Tab 07 Reports Console Average Steps Card & Chart Title
      const elRepSteps = document.getElementById('repAvgSteps');
      if (elRepSteps) elRepSteps.innerHTML = `${steps.toLocaleString()} <span style="font-size: 1rem;">steps</span>`;
      const eT = document.getElementById('repChartExerciseTitle');
      if (eT && currentReportTimeframe === 'daily') {
        eT.textContent = `TODAY HOURLY STEP ACTIVITY (${steps.toLocaleString()} TOTAL STEPS)`;
      }

      // 4. Tab 11 Clinical Goals (Pillar 4 Activity)
      const elActiveSteps = document.getElementById('lblActiveDailySteps');
      if (elActiveSteps) elActiveSteps.textContent = `Current Active: ${steps.toLocaleString()} steps`;
      const elGoalSteps = document.getElementById('lblGoalSteps');
      if (elGoalSteps) elGoalSteps.textContent = `${steps.toLocaleString()} steps`;

      // 5. Tab 13 Operations / Sport Bay & Physical Chronometer Pod
      const chronoPod = document.getElementById('chronoPodSteps');
      if (chronoPod) chronoPod.textContent = steps.toLocaleString();
      const lblTestSteps = document.getElementById('lblTestSteps');
      if (lblTestSteps) lblTestSteps.textContent = `${steps.toLocaleString()} steps`;

      // 6. Real-Time Workout Area
      updateWorkoutWithSteps(steps);
    }
"""
    if "function propagateStepTelemetry(" not in text:
        # Insert before handleIncomingTelemetryPacket
        target_fn = "function handleIncomingTelemetryPacket("
        if target_fn in text:
            text = text.replace(target_fn, propagate_code + "\n    " + target_fn)
            print("  [✔] Injected propagateStepTelemetry function")

    # 4. Wire propagateStepTelemetry into handleIncomingTelemetryPacket 0xA8
    old_a8 = """const steps = (bytes[1] << 24) | (bytes[2] << 16) | (bytes[3] << 8) | bytes[4];
        lastSteps = steps;
        const miles = ((steps * 0.7) / 1609.34).toFixed(2);
        const km = ((steps * 0.7) / 1000).toFixed(2);
        const kcal = Math.round(steps * 0.04);
        const stepEl = document.getElementById("valStepCount");
        if (stepEl) stepEl.innerHTML = `${steps.toLocaleString()} <span style="font-size: 1rem;">steps</span>`;
        const distEl = document.getElementById("valStepDist");
        if (distEl) distEl.innerHTML = `${miles} <span style="font-size: 1rem;">miles</span>`;
        const kcalEl = document.getElementById("valStepKcal");
        if (kcalEl) kcalEl.innerHTML = `${kcal} <span style="font-size: 1rem;">kcal</span>`;
        const goalEl = document.getElementById("valStepGoal");
        if (goalEl) goalEl.textContent = `Goal: 2,000 (${((steps / 2000) * 100).toFixed(1)}%)`;
        logLCARS(`[👟 PEDOMETER] Real watch step data: ${steps.toLocaleString()} steps | ${miles} mi (${km} km) | ${kcal} kcal`, "rx");

        // Wire directly into SPORT-04 Workout Area and 48-Slot Chart
        updateWorkoutWithSteps(steps);"""

    new_a8 = """const steps = (bytes[1] << 24) | (bytes[2] << 16) | (bytes[3] << 8) | bytes[4];
        const miles = ((steps * 0.7) / 1609.34).toFixed(2);
        const km = ((steps * 0.7) / 1000).toFixed(2);
        const kcal = Math.round(steps * 0.04);
        logLCARS(`[👟 PEDOMETER] Real watch step data: ${steps.toLocaleString()} steps | ${miles} mi (${km} km) | ${kcal} kcal`, "rx");
        propagateStepTelemetry(steps, kcal, miles);"""

    if old_a8 in text:
        text = text.replace(old_a8, new_a8)
        print("  [✔] Wired propagateStepTelemetry into 0xA8 handler")

    # 5. Wire propagateStepTelemetry into pollLiveTelemetryChanges
    old_poll_pedometer = """if (vitals.PEDOMETER && vitals.PEDOMETER.value1) {
            lastSteps = Math.round(vitals.PEDOMETER.value1);
            const elSteps = document.getElementById('valStepCount');
            if (elSteps) elSteps.innerHTML = `${lastSteps.toLocaleString()} <span style="font-size: 1rem;">steps</span>`;
            const elRepSteps = document.getElementById('repAvgSteps');
            if (elRepSteps) elRepSteps.innerHTML = `${lastSteps.toLocaleString()} <span style="font-size: 1rem;">steps</span>`;
          }"""
    new_poll_pedometer = """if (vitals.PEDOMETER && vitals.PEDOMETER.value1 !== undefined) {
            const stepsVal = Math.round(vitals.PEDOMETER.value1);
            propagateStepTelemetry(stepsVal);
          }"""
    if old_poll_pedometer in text:
        text = text.replace(old_poll_pedometer, new_poll_pedometer)
        print("  [✔] Wired propagateStepTelemetry into pollLiveTelemetryChanges")

    # 6. Clean up initializeVitalsAndCardioBaselines - remove bogus numbers
    old_init_vitals = """function initializeVitalsAndCardioBaselines() {
      // Ensure Vitals BP is never left as a blank placeholder
      const elBp = document.getElementById('valBP');
      if (elBp && (elBp.textContent.trim() === '-- / --' || elBp.textContent.trim() === '')) {
        elBp.textContent = "118/76";
        const badgeBp = document.getElementById('badgeBP');
        if (badgeBp) {
          badgeBp.textContent = "NORMOTENSIVE (RESTING)";
          badgeBp.className = "state-badge nominal";
        }
      }

      // Ensure Cardio HRV is never left as a blank placeholder
      const elHrv = document.getElementById('txtHRV');
      if (elHrv && (elHrv.textContent.includes('--') || elHrv.textContent.trim() === '')) {
        elHrv.textContent = "64 ms (BALANCED)";
        const elStress = document.getElementById('valStress');
        if (elStress) elStress.textContent = "28";
        const badgeStress = document.getElementById('badgeStress');
        if (badgeStress) {
          badgeStress.textContent = "BALANCED / LOW STRESS";
          badgeStress.className = "state-badge nominal";
        }
      }

      // Ensure tiles & HUD have initial values
      const tileBp = document.getElementById('tile360Bp');
      if (tileBp && (tileBp.innerHTML.includes('--') || tileBp.innerHTML.trim() === '')) {
        tileBp.innerHTML = `118/76 <span style="font-size: 0.9rem;">mmHg</span>`;
      }
      const tileHrHrv = document.getElementById('tile360HrHrv');
      if (tileHrHrv && (tileHrHrv.innerHTML.includes('--') || tileHrHrv.innerHTML.trim() === '')) {
        tileHrHrv.innerHTML = `72 BPM <span style="font-size: 0.85rem;">(64ms HRV)</span>`;
      }
      const hudBp = document.getElementById('hudBp');
      if (hudBp && (hudBp.textContent.includes('--') || hudBp.textContent.trim() === '')) {
        hudBp.textContent = "118/76 mmHg";
      }
      const hudHrHrv = document.getElementById('hudHrHrv');
      if (hudHrHrv && (hudHrHrv.textContent.includes('--') || hudHrHrv.textContent.trim() === '')) {
        hudHrHrv.textContent = "72 BPM / 64 ms";
      }
    }"""

    new_init_vitals = """function initializeVitalsAndCardioBaselines() {
      // Clinical honesty: display placeholder if no real data recorded yet
      const elBp = document.getElementById('valBP');
      if (elBp && !elBp.textContent.trim()) {
        elBp.textContent = "-- / --";
      }
      const elHrv = document.getElementById('txtHRV');
      if (elHrv && !elHrv.textContent.trim()) {
        elHrv.textContent = "-- ms";
      }
      const elStress = document.getElementById('valStress');
      if (elStress && !elStress.textContent.trim()) {
        elStress.textContent = "--";
      }
      const tileBp = document.getElementById('tile360Bp');
      if (tileBp && !tileBp.innerHTML.trim()) {
        tileBp.innerHTML = `--/-- <span style="font-size: 0.8rem; color: #94a3b8;">(NO DATA)</span>`;
      }
      const tileHrHrv = document.getElementById('tile360HrHrv');
      if (tileHrHrv && !tileHrHrv.innerHTML.trim()) {
        tileHrHrv.innerHTML = `-- BPM / -- ms <span style="font-size: 0.8rem; color: #94a3b8;">(NO DATA)</span>`;
      }
    }"""

    if old_init_vitals in text:
        text = text.replace(old_init_vitals, new_init_vitals)
        print("  [✔] Cleaned initializeVitalsAndCardioBaselines (removed fake 118/76, 64ms, 28 stress)")

    # 7. Clean up render360HealthView - remove bogus fallbacks
    old_360 = """function render360HealthView() {
      // 1. Update 360° Real-Time Biometric Console Tiles
      const gTile = document.getElementById('tile360Glucose');
      const curG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : 160;
      if (gTile) gTile.innerHTML = `${curG} <span style="font-size: 0.9rem;">mg/dL</span>`;

      const curBp = (typeof lastSystolic !== 'undefined' && lastSystolic > 0) ? `${lastSystolic}/${lastDiastolic}` : "118/76";
      const bpTile = document.getElementById('tile360Bp');
      if (bpTile) bpTile.innerHTML = `${curBp} <span style="font-size: 0.9rem;">mmHg</span>`;

      const curSpo2 = (typeof lastSpO2 !== 'undefined' && lastSpO2 > 0) ? `${lastSpO2}%` : "98%";
      const spTile = document.getElementById('tile360Spo2');
      if (spTile) spTile.textContent = curSpo2;

      const curHr = (typeof lastHR !== 'undefined' && lastHR > 0) ? lastHR : 72;
      const curHrv = (typeof lastHRV !== 'undefined' && lastHRV > 0) ? lastHRV : 62;
      const hrTile = document.getElementById('tile360HrHrv');
      if (hrTile) hrTile.innerHTML = `${curHr} <span style="font-size: 0.9rem;">BPM</span> / ${curHrv} <span style="font-size: 0.9rem;">ms</span>`;

      const curTemp = (typeof lastBodyF !== 'undefined' && lastBodyF > 0) ? `${lastBodyF.toFixed(1)}°F` : "97.3°F";
      const tempTile = document.getElementById('tile360Temp');
      if (tempTile) tempTile.textContent = curTemp;

      const curSteps = (typeof lastSteps !== 'undefined' && lastSteps > 0) ? lastSteps.toLocaleString() : "2,384";
      const stepTile = document.getElementById('tile360Motion');
      if (stepTile) stepTile.innerHTML = `${curSteps} <span style="font-size: 0.9rem;">steps</span>`;

      // 2. Render Exercise & Diet Histories
      renderExerciseHistory();
      renderDietHistory();

      // 3. Generate Personal Health Insights
      generatePersonalHealthInsights();
    }"""

    new_360 = """function render360HealthView() {
      // 1. Update 360° Real-Time Biometric Console Tiles (Clinical Zero Synthetic Interpolation)
      const gTile = document.getElementById('tile360Glucose');
      if (gTile) {
        if (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) {
          gTile.innerHTML = `${lastGlucoseMg} <span style="font-size: 0.9rem;">mg/dL</span>`;
        } else {
          gTile.innerHTML = `-- <span style="font-size: 0.8rem; color: #94a3b8;">(NO DATA)</span>`;
        }
      }

      const bpTile = document.getElementById('tile360Bp');
      if (bpTile) {
        if (typeof lastSystolic !== 'undefined' && lastSystolic > 0) {
          bpTile.innerHTML = `${lastSystolic}/${lastDiastolic} <span style="font-size: 0.9rem;">mmHg</span>`;
        } else {
          bpTile.innerHTML = `--/-- <span style="font-size: 0.8rem; color: #94a3b8;">(NO DATA)</span>`;
        }
      }

      const spTile = document.getElementById('tile360Spo2');
      if (spTile) {
        if (typeof lastSpO2 !== 'undefined' && lastSpO2 > 0) {
          spTile.textContent = `${lastSpO2}%`;
        } else {
          spTile.textContent = `--% (NO DATA)`;
        }
      }

      const hrTile = document.getElementById('tile360HrHrv');
      if (hrTile) {
        if (typeof lastHR !== 'undefined' && lastHR > 0) {
          const hrvStr = (typeof lastHRV !== 'undefined' && lastHRV > 0) ? `${lastHRV} ms` : `-- ms`;
          hrTile.innerHTML = `${lastHR} <span style="font-size: 0.9rem;">BPM</span> / ${hrvStr}`;
        } else {
          hrTile.innerHTML = `-- BPM / -- ms <span style="font-size: 0.8rem; color: #94a3b8;">(NO DATA)</span>`;
        }
      }

      const tempTile = document.getElementById('tile360Temp');
      if (tempTile) {
        if (typeof lastBodyF !== 'undefined' && lastBodyF > 0) {
          tempTile.textContent = `${lastBodyF.toFixed(1)}°F`;
        } else {
          tempTile.textContent = `--.-°F (NO DATA)`;
        }
      }

      const stepTile = document.getElementById('tile360Motion');
      if (stepTile) {
        if (typeof lastSteps !== 'undefined' && lastSteps > 0) {
          stepTile.innerHTML = `${lastSteps.toLocaleString()} <span style="font-size: 0.9rem;">steps</span>`;
        } else {
          stepTile.innerHTML = `0 <span style="font-size: 0.8rem; color: #94a3b8;">steps (NO DATA)</span>`;
        }
      }

      // 2. Render Exercise & Diet Histories
      renderExerciseHistory();
      renderDietHistory();

      // 3. Generate Personal Health Insights
      generatePersonalHealthInsights();
    }"""

    if old_360 in text:
        text = text.replace(old_360, new_360)
        print("  [✔] Cleaned render360HealthView (removed fake fallbacks)")

    # 8. Clean up assembleAndParseSleepStream - remove fake 7.4h fallback
    old_sleep_fallback = """      } else {
        // Fallback to clinical sleep architecture baseline (Last night's recorded restorative sleep: 7.4h)
        const elSleep = document.getElementById("valSleep");
        if (elSleep && (elSleep.textContent === "--.-" || !elSleep.textContent)) elSleep.textContent = "7.4";
        const elDeep = document.getElementById("valDeepSleep");
        if (elDeep && (elDeep.textContent.includes("--") || !elDeep.textContent)) elDeep.textContent = "1.9h (25.7%)";
        const elLight = document.getElementById("valLightSleep");
        if (elLight && (elLight.textContent.includes("--") || !elLight.textContent)) elLight.textContent = "4.1h (55.4%)";
        const elRem = document.getElementById("valRemSleep");
        if (elRem && (elRem.textContent.includes("--") || !elRem.textContent)) elRem.textContent = "1.4h (18.9%)";
        const elWake = document.getElementById("valWakeSleep");
        if (elWake && (elWake.textContent.includes("--") || !elWake.textContent)) elWake.textContent = "12 min";
        logLCARS("[🌙 SOMNO-03] Maintained last night clinical sleep baseline (7.4h total, 1.9h deep, 4.1h light, 1.4h REM, 12 min wake).", "sys");
      }"""

    new_sleep_fallback = """      } else {
        // Zero synthetic data: honestly display no sleep recorded if watch was off-wrist or not worn
        const elSleep = document.getElementById("valSleep");
        if (elSleep) elSleep.textContent = "--.-";
        const elDeep = document.getElementById("valDeepSleep");
        if (elDeep) elDeep.textContent = "-- (NO DATA)";
        const elLight = document.getElementById("valLightSleep");
        if (elLight) elLight.textContent = "-- (NO DATA)";
        const elRem = document.getElementById("valRemSleep");
        if (elRem) elRem.textContent = "-- (NO DATA)";
        const elWake = document.getElementById("valWakeSleep");
        if (elWake) elWake.textContent = "-- min";
        const sT = document.getElementById('repChartSleepTitle');
        if (sT) sT.textContent = "NO NOCTURNAL SLEEP RECORDED (WATCH OFF-WRIST OR ON CHARGING DOCK)";
        logLCARS("[🌙 SOMNO-03] No nocturnal sleep architecture found on watch for queried date. Displaying NO DATA.", "sys");
      }"""

    if old_sleep_fallback in text:
        text = text.replace(old_sleep_fallback, new_sleep_fallback)
        print("  [✔] Cleaned assembleAndParseSleepStream (removed fake 7.4h sleep fallback)")

    # 9. Clean updateWorkoutWithSteps
    old_workout = "if (!steps || steps <= 0) steps = 2384;"
    new_workout = "if (!steps || steps < 0) steps = 0;"
    if old_workout in text:
        text = text.replace(old_workout, new_workout)
        print("  [✔] Cleaned updateWorkoutWithSteps (removed fake 2384 fallback)")

    # 10. Update renderSvgCurveChart empty data state
    old_curve_start = """function renderSvgCurveChart(containerId, data, options = {}) {
      const container = document.getElementById(containerId);
      if (!container) return;"""
    new_curve_start = """function renderSvgCurveChart(containerId, data, options = {}) {
      const container = document.getElementById(containerId);
      if (!container) return;

      const validPoints = (data || []).filter(d => d.val !== null && d.val !== undefined && !d.isGap && d.val > 0);
      if (!data || data.length === 0 || validPoints.length === 0) {
        container.innerHTML = `
          <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100%; min-height: 180px; color: #64748b; font-family: var(--lcars-mono); text-align: center; border: 1.5px dashed #cbd5e1; border-radius: 6px; background: rgba(248, 250, 252, 0.85); padding: 16px;">
            <div style="font-size: 1rem; font-weight: 800; color: #0284c7; letter-spacing: 0.05em; margin-bottom: 4px;">
              ⚡ NO SENSOR TELEMETRY RECORDED FOR THIS TIMEFRAME
            </div>
            <div style="font-size: 0.78rem; color: #64748b;">
              Connect watch or sync sensor data to generate clinical curve. Zero synthetic interpolation.
            </div>
          </div>`;
        return;
      }"""
    if old_curve_start in text and "const validPoints" not in text:
        text = text.replace(old_curve_start, new_curve_start)
        print("  [✔] Added clean NO DATA empty state to renderSvgCurveChart")

    # 11. Update renderSvgDualCurveChart empty data state
    old_dual_start = """function renderSvgDualCurveChart(containerId, data, options = {}) {
      const container = document.getElementById(containerId);
      if (!container) return;"""
    new_dual_start = """function renderSvgDualCurveChart(containerId, data, options = {}) {
      const container = document.getElementById(containerId);
      if (!container) return;

      const validDual = (data || []).filter(d => (d.core !== null && d.core !== undefined && d.core > 0) || (d.skin !== null && d.skin !== undefined && d.skin > 0));
      if (!data || data.length === 0 || validDual.length === 0) {
        container.innerHTML = `
          <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100%; min-height: 180px; color: #64748b; font-family: var(--lcars-mono); text-align: center; border: 1.5px dashed #cbd5e1; border-radius: 6px; background: rgba(248, 250, 252, 0.85); padding: 16px;">
            <div style="font-size: 1rem; font-weight: 800; color: #0284c7; letter-spacing: 0.05em; margin-bottom: 4px;">
              ⚡ NO SENSOR TELEMETRY RECORDED FOR THIS TIMEFRAME
            </div>
            <div style="font-size: 0.78rem; color: #64748b;">
              Connect watch or sync sensor data to generate dual clinical curve. Zero synthetic interpolation.
            </div>
          </div>`;
        return;
      }"""
    if old_dual_start in text and "const validDual" not in text:
        text = text.replace(old_dual_start, new_dual_start)
        print("  [✔] Added clean NO DATA empty state to renderSvgDualCurveChart")

    # 12. Update DOMContentLoaded for immediate startup dump and refresh
    old_dom_loaded = """    // Call initPopularMeals and baseline setup on window load
    window.addEventListener('DOMContentLoaded', () => {
      initializeVitalsAndCardioBaselines();
      initPopularMeals();
      switchTab('fullscan');
    });"""

    new_dom_loaded = """    // Immediate startup data harvest, SQLite hydration and baseline setup on window load
    window.addEventListener('DOMContentLoaded', async () => {
      initializeVitalsAndCardioBaselines();
      initPopularMeals();
      switchTab('fullscan');

      // 1. Immediately hydrate telemetry from SQLite database into LCARS without waiting
      try {
        await fetchTelemetryLatest();
        await fetchTelemetryTimeline();
        renderReportsView();
        render360HealthView();
      } catch (e) {
        console.error("Initial telemetry hydration error:", e);
      }

      // 2. Automated startup watch link check & full telemetry dump (3-Click / Zero-Click automation)
      setTimeout(async () => {
        if (navigator.bluetooth && navigator.bluetooth.getDevices) {
          try {
            const devs = await navigator.bluetooth.getDevices();
            if (devs && devs.length > 0) {
              bluetoothDevice = devs[0];
              logLCARS("[*] Watch recognized in browser registry. Initiating automatic startup telemetry dump...", "sys");
              const ok = await proceedWithGattConnection();
              if (ok) {
                await execute15MinWatchDataDump();
              }
            }
          } catch(e) {
            console.log("Startup BLE auto-connect check:", e);
          }
        }
      }, 1200);
    });"""

    if old_dom_loaded in text:
        text = text.replace(old_dom_loaded, new_dom_loaded)
        print("  [✔] Updated DOMContentLoaded with immediate startup dump and hydration")

    with open(filepath, "w", encoding="utf-8") as f:
        f.write(text)
    print(f"[✔] Successfully patched {filepath}\n")

apply_patch(r"D:\veepoo\index.html")
apply_patch(r"D:\veepoo\w11_htm_app\index.html")
