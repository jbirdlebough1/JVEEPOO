import os, re, json

def update_index_files():
    files = [r"D:\veepoo\w11_htm_app\index.html", r"D:\veepoo\index.html"]
    
    for fpath in files:
        with open(fpath, "r", encoding="utf-8") as fp:
            html = fp.read()

        # -------------------------------------------------------------
        # 1. REORDER SIDEBAR TABS (1 to 13)
        # 01-FULL SCAN (active), 02-VITALS, 03-CARDIO, 04-METABOLIC, 05-REPORTS,
        # 06-ADA TRADES, 07-HELP & FAQ, 08-MEDICATION, 09-CLINICAL GOALS,
        # 10-CONFIG, 11-OPERATIONS, 12-DATA LOG, 13-WATCH FACES
        # -------------------------------------------------------------
        new_sidebar = """    <nav class="lcars-sidebar">
      <button id="navTabFullScan" class="lcars-pill pill-blue active" onclick="switchTab('fullscan')">
        <span class="tab-label">01-FULL SCAN</span>
      </button>
      <button id="navTabVitals" class="lcars-pill pill-banana" onclick="switchTab('vitals')">
        <span class="tab-label">02-VITALS</span>
      </button>
      <button id="navTabCardio" class="lcars-pill pill-orange" onclick="switchTab('cardio')">
        <span class="tab-label">03-CARDIO</span>
      </button>
      <button id="navTabMetabolic" class="lcars-pill pill-peach" onclick="switchTab('metabolic')">
        <span class="tab-label">04-METABOLIC</span>
      </button>
      <button id="navTabReports" class="lcars-pill pill-blue" onclick="switchTab('reports')">
        <span class="tab-label">05-REPORTS</span>
      </button>
      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">06-ADA TRADES</span>
      </button>
      <button id="navTabHelp" class="lcars-pill pill-banana" onclick="switchTab('help')">
        <span class="tab-label">07-HELP &amp; FAQ</span>
      </button>
      <button id="navTabMedication" class="lcars-pill pill-orange" onclick="switchTab('medication')">
        <span class="tab-label">08-MEDICATION</span>
      </button>
      <button id="navTabGoals" class="lcars-pill pill-green" onclick="switchTab('goals')">
        <span class="tab-label">09-CLINICAL GOALS</span>
      </button>
      <button id="navTabSetup" class="lcars-pill pill-lilac" onclick="switchTab('setup')">
        <span class="tab-label">10-CONFIG</span>
      </button>
      <button id="navTabOperations" class="lcars-pill pill-peach" onclick="switchTab('operations')">
        <span class="tab-label">11-OPERATIONS</span>
      </button>
      <button id="navTabLog" class="lcars-pill pill-ice" onclick="switchTab('log')">
        <span class="tab-label">12-DATA LOG</span>
      </button>
      <button id="navTabWatchFaces" class="lcars-pill pill-lilac" onclick="switchTab('watchfaces')">
        <span class="tab-label">13-WATCH FACES</span>
      </button>
    </nav>"""

        pos_sidebar_start = html.find('<nav class="lcars-sidebar">')
        pos_sidebar_end = html.find('</nav>', pos_sidebar_start) + len('</nav>')
        if pos_sidebar_start != -1 and pos_sidebar_end != -1:
            html = html[:pos_sidebar_start] + new_sidebar + html[pos_sidebar_end:]
            print(f"[✔] Reordered sidebar tabs in {fpath}")

        # Set default active page to pageFullScan
        html = re.sub(r'id="pageVitals"\s+class="tab-page\s+active-page"', 'id="pageVitals" class="tab-page"', html)
        html = re.sub(r'id="pageFullScan"\s+class="tab-page"', 'id="pageFullScan" class="tab-page active-page"', html)

        # -------------------------------------------------------------
        # 2. UPDATE STEP GUIDE & CURRENT POSITION IN CORRELATOR
        # -------------------------------------------------------------
        # Replace renderStepGlucoseCorrelator and related functions
        old_correlator_target = "function renderStepGlucoseCorrelator() {"
        pos_corr_func = html.find(old_correlator_target)
        if pos_corr_func != -1:
            # Find end of renderStepGlucoseCorrelator
            pos_corr_end = html.find("function onStepTargetSliderChanged", pos_corr_func)
            if pos_corr_end != -1:
                new_correlator_js = """function getLiveWatchSteps() {
      const valEl = document.getElementById('valSteps');
      if (valEl) {
        const parsed = parseInt(valEl.textContent.replace(/[^0-9]/g, ''), 10);
        if (!isNaN(parsed) && parsed > 0) return parsed;
      }
      return 2450; // Clinical active daytime sample
    }

    function getWatchStepSettingGoal() {
      const cfgEl = document.getElementById('cfgStepGoal');
      if (cfgEl) {
        const parsed = parseInt(cfgEl.value, 10);
        if (!isNaN(parsed) && parsed > 0) return parsed;
      }
      return 2000; // Watch default step goal
    }

    function snapCorrelatorToWatchCurrent() {
      const curSteps = getLiveWatchSteps();
      currentStepSimulationTarget = curSteps;
      const slider = document.getElementById('sliderStepTarget');
      if (slider) slider.value = curSteps;
      onStepTargetSliderChanged(curSteps);
      if (typeof soundButtonPress === 'function') soundButtonPress();
    }

    function snapCorrelatorToWatchSettingGoal() {
      const goalSteps = getWatchStepSettingGoal();
      currentStepSimulationTarget = goalSteps;
      const slider = document.getElementById('sliderStepTarget');
      if (slider) slider.value = goalSteps;
      onStepTargetSliderChanged(goalSteps);
      if (typeof soundButtonPress === 'function') soundButtonPress();
    }

    function renderStepGlucoseCorrelator() {
      initStepGlucoseCorrelatorData();
      const svg = document.getElementById('svgStepGlucoseCorrelator');
      if (!svg) return;

      const padLeft = 52;
      const padRight = 20;
      const padTop = 20;
      const padBottom = 45;
      const plotW = 500 - padLeft - padRight;
      const plotH = 340 - padTop - padBottom;

      const minX = 0;
      const maxX = 14000;
      const minY = 80;
      const maxY = 180;

      function mapX(s) {
        return padLeft + ((s - minX) / (maxX - minX)) * plotW;
      }
      function mapY(g) {
        return padTop + plotH - ((g - minY) / (maxY - minY)) * plotH;
      }

      // Read Watch Settings and Live Telemetry
      const watchStepGoal = getWatchStepSettingGoal();
      const liveWatchSteps = getLiveWatchSteps();
      const curGlucoseEl = document.getElementById('valGlucose');
      const liveGlucose = curGlucoseEl ? parseInt(curGlucoseEl.textContent, 10) || 106 : 106;

      let svgHtml = '';

      // Dynamic Guide Zones scaled to Watch Settings Step Goal
      const xSedStart = mapX(0);
      const xSedEnd = mapX(Math.min(3000, watchStepGoal * 0.75));
      svgHtml += `<rect x="${xSedStart}" y="${padTop}" width="${xSedEnd - xSedStart}" height="${plotH}" fill="#fee2e2" fill-opacity="0.45" />`;
      svgHtml += `<text x="${(xSedStart + xSedEnd)/2}" y="${padTop + 14}" font-size="8" font-family="monospace" fill="#b91c1c" font-weight="bold" text-anchor="middle">PRE-GOAL ZONE (&lt;${Math.round(watchStepGoal * 0.75 / 1000 * 10)/10}K)</text>`;

      const xModEnd = mapX(watchStepGoal);
      svgHtml += `<rect x="${xSedEnd}" y="${padTop}" width="${xModEnd - xSedEnd}" height="${plotH}" fill="#fef3c7" fill-opacity="0.35" />`;
      svgHtml += `<text x="${(xSedEnd + xModEnd)/2}" y="${padTop + 14}" font-size="8" font-family="monospace" fill="#b45309" font-weight="bold" text-anchor="middle">APPROACHING GOAL</text>`;

      const xOptEnd = mapX(14000);
      svgHtml += `<rect x="${xModEnd}" y="${padTop}" width="${xOptEnd - xModEnd}" height="${plotH}" fill="#dcfce7" fill-opacity="0.45" />`;
      svgHtml += `<text x="${(xModEnd + xOptEnd)/2}" y="${padTop + 14}" font-size="8" font-family="monospace" fill="#15803d" font-weight="bold" text-anchor="middle">WATCH GOAL &amp; GLUT4 ZONE (≥${(watchStepGoal/1000).toFixed(1)}K)</text>`;

      // Clinical Homeostasis Line (120 mg/dL)
      const yTarget = mapY(120);
      svgHtml += `<line x1="${padLeft}" y1="${yTarget}" x2="${padLeft + plotW}" y2="${yTarget}" stroke="#15803d" stroke-dasharray="4 4" stroke-width="1.2" opacity="0.8" />`;
      svgHtml += `<text x="${padLeft + plotW - 6}" y="${yTarget - 4}" font-size="8" font-family="monospace" fill="#15803d" font-weight="bold" text-anchor="end">CLINICAL HOMEOSTASIS: 120 MG/DL</text>`;

      // Grid Lines
      for (let s = 0; s <= 14000; s += 2000) {
        const gx = mapX(s);
        svgHtml += `<line x1="${gx.toFixed(1)}" y1="${padTop}" x2="${gx.toFixed(1)}" y2="${padTop + plotH}" stroke="#e2e8f0" stroke-width="1" />`;
        const label = s === 0 ? '0' : `${s / 1000}k`;
        svgHtml += `<text x="${gx.toFixed(1)}" y="${padTop + plotH + 16}" font-size="9" font-family="monospace" fill="#64748b" text-anchor="middle">${label}</text>`;
      }

      for (let g = 80; g <= 180; g += 20) {
        const gy = mapY(g);
        svgHtml += `<line x1="${padLeft}" y1="${gy.toFixed(1)}" x2="${padLeft + plotW}" y2="${gy.toFixed(1)}" stroke="#e2e8f0" stroke-width="1" />`;
        svgHtml += `<text x="${padLeft - 8}" y="${gy.toFixed(1) + 3}" font-size="9" font-family="monospace" fill="#64748b" text-anchor="end">${g}</text>`;
      }

      svgHtml += `<rect x="${padLeft}" y="${padTop}" width="${plotW}" height="${plotH}" fill="none" stroke="#cbd5e1" stroke-width="1.5" />`;

      // Confidence Band & Regression Line
      const x0 = mapX(500);
      const y0Top = mapY(163.5 - 0.0052 * 500 + 7.5);
      const y0Bot = mapY(163.5 - 0.0052 * 500 - 7.5);
      const x1 = mapX(13500);
      const y1Top = mapY(163.5 - 0.0052 * 13500 + 8.5);
      const y1Bot = mapY(163.5 - 0.0052 * 13500 - 8.5);

      const ribbonPoints = `${x0.toFixed(1)},${y0Top.toFixed(1)} ${x1.toFixed(1)},${y1Top.toFixed(1)} ${x1.toFixed(1)},${y1Bot.toFixed(1)} ${x0.toFixed(1)},${y0Bot.toFixed(1)}`;
      svgHtml += `<polygon points="${ribbonPoints}" fill="#bfdbfe" fill-opacity="0.45" />`;

      const yLine0 = mapY(163.5 - 0.0052 * 500);
      const yLine1 = mapY(163.5 - 0.0052 * 13500);
      svgHtml += `<line x1="${x0.toFixed(1)}" y1="${yLine0.toFixed(1)}" x2="${x1.toFixed(1)}" y2="${yLine1.toFixed(1)}" stroke="#2563eb" stroke-width="2.5" />`;
      svgHtml += `<text x="${x1.toFixed(1) - 6}" y="${yLine1 - 6}" font-size="8.5" font-family="monospace" fill="#1d4ed8" font-weight="bold" text-anchor="end">SLOPE: -5.2 MG/DL / 1,000 STEPS (r = -0.79)</text>`;

      // Historical Scatter Points
      correlatorPoints.forEach((p) => {
        const cx = mapX(p.steps);
        const cy = mapY(p.glucose);
        let fillCol = '#0284c7';
        if (p.steps >= watchStepGoal && p.glucose <= 125) fillCol = '#16a34a';
        else if (p.steps < (watchStepGoal * 0.75) || p.glucose >= 150) fillCol = '#dc2626';

        svgHtml += `<circle cx="${cx.toFixed(1)}" cy="${cy.toFixed(1)}" r="3" fill="${fillCol}" stroke="#ffffff" stroke-width="0.8" opacity="0.82">
          <title>Day ${p.day}: ${p.steps.toLocaleString()} steps • Mean Glucose: ${p.glucose} mg/dL • TIR: ${p.tir}%</title>
        </circle>`;
      });

      // 1. WATCH SETTINGS STEP GUIDE LINE (Anchored to cfgStepGoal)
      const xGoal = mapX(watchStepGoal);
      svgHtml += `<line x1="${xGoal.toFixed(1)}" y1="${padTop}" x2="${xGoal.toFixed(1)}" y2="${padTop + plotH}" stroke="#0284c7" stroke-width="2" stroke-dasharray="6 3" />`;
      svgHtml += `<rect x="${xGoal - 75}" y="${padTop + 24}" width="150" height="15" rx="3" fill="#0284c7" fill-opacity="0.9" />`;
      svgHtml += `<text x="${xGoal}" y="${padTop + 35}" font-size="8" font-family="monospace" fill="#ffffff" font-weight="bold" text-anchor="middle">WATCH GOAL: ${watchStepGoal.toLocaleString()} STEPS</text>`;

      // 2. WHERE USER IS CURRENTLY IN CORRELATOR (Live Watch Steps Indicator)
      const xCurrent = mapX(liveWatchSteps);
      const yCurrentOnCurve = mapY(163.5 - 0.0052 * liveWatchSteps);
      const pctCurrentOfGoal = Math.round((liveWatchSteps / watchStepGoal) * 100);

      svgHtml += `<line x1="${xCurrent.toFixed(1)}" y1="${padTop}" x2="${xCurrent.toFixed(1)}" y2="${padTop + plotH}" stroke="#16a34a" stroke-width="2.5" />`;
      // Outer glow circle
      svgHtml += `<circle cx="${xCurrent.toFixed(1)}" cy="${yCurrentOnCurve.toFixed(1)}" r="9" fill="#16a34a" fill-opacity="0.25">
        <animate attributeName="r" values="7;12;7" dur="2s" repeatCount="indefinite"/>
        <animate attributeName="opacity" values="0.4;0.1;0.4" dur="2s" repeatCount="indefinite"/>
      </circle>`;
      // Inner solid point
      svgHtml += `<circle cx="${xCurrent.toFixed(1)}" cy="${yCurrentOnCurve.toFixed(1)}" r="6" fill="#16a34a" stroke="#ffffff" stroke-width="2" />`;
      
      // Floating Callout Badge for Current Position
      const badgeY = Math.max(padTop + 45, yCurrentOnCurve - 25);
      const badgeX = Math.min(padLeft + plotW - 90, Math.max(padLeft + 90, xCurrent));
      svgHtml += `<rect x="${badgeX - 85}" y="${badgeY - 14}" width="170" height="26" rx="4" fill="#0f172a" stroke="#16a34a" stroke-width="1.5" />`;
      svgHtml += `<text x="${badgeX}" y="${badgeY - 2}" font-size="8" font-family="monospace" fill="#4ade80" font-weight="900" text-anchor="middle">📍 CURRENT WATCH: ${liveWatchSteps.toLocaleString()} STEPS</text>`;
      svgHtml += `<text x="${badgeX}" y="${badgeY + 8}" font-size="7.5" font-family="monospace" fill="#ffffff" font-weight="bold" text-anchor="middle">${pctCurrentOfGoal}% OF WATCH GOAL • EST: ${Math.round(163.5 - 0.0052 * liveWatchSteps)} mg/dL</text>`;

      // Interactive Simulator Dot (if different from current)
      if (Math.abs(currentStepSimulationTarget - liveWatchSteps) > 50) {
        const simX = mapX(currentStepSimulationTarget);
        const simExpG = Math.max(85, Math.min(175, 163.5 - 0.0052 * currentStepSimulationTarget));
        const simY = mapY(simExpG);

        svgHtml += `<line x1="${simX.toFixed(1)}" y1="${padTop}" x2="${simX.toFixed(1)}" y2="${padTop + plotH}" stroke="#f59e0b" stroke-width="1.5" stroke-dasharray="3 3" />`;
        svgHtml += `<circle cx="${simX.toFixed(1)}" cy="${simY.toFixed(1)}" r="5" fill="#f59e0b" stroke="#ffffff" stroke-width="1.5" />`;
      }

      svg.innerHTML = svgHtml;

      // Also update daily steps label in timeline
      const dailyStepsLbl = document.getElementById('lblActiveDailySteps');
      if (dailyStepsLbl) {
        dailyStepsLbl.textContent = `TOTAL: ${liveWatchSteps.toLocaleString()} / ${watchStepGoal.toLocaleString()} STEPS (${pctCurrentOfGoal}%)`;
      }
    }

    """
                html = html[:pos_corr_func] + new_correlator_js + html[pos_corr_end:]
                print(f"[✔] Injected Watch Setting step guide and current position marker into {fpath}")

        # Update correlator slider controls HTML to add snap buttons
        old_slider_row = """<label style="font-size: 0.78rem; font-weight: bold; color: #0f172a; white-space: nowrap;">🎯 SIMULATE STEP TARGET:</label>"""
        new_slider_row = """<label style="font-size: 0.78rem; font-weight: bold; color: #0f172a; white-space: nowrap;">🎯 STEP SIMULATOR &amp; GUIDE:</label>
                  <button type="button" class="lcars-btn btn-green" style="font-size: 0.70rem; padding: 2px 8px;" onclick="snapCorrelatorToWatchCurrent()" title="Snap simulator to current live watch step count">
                    📍 CURRENT WATCH (LIVE)
                  </button>
                  <button type="button" class="lcars-btn btn-blue" style="font-size: 0.70rem; padding: 2px 8px;" onclick="snapCorrelatorToWatchSettingGoal()" title="Snap simulator to Watch Settings goal">
                    ⚙️ WATCH GOAL GUIDE
                  </button>"""
        if old_slider_row in html:
            html = html.replace(old_slider_row, new_slider_row)
            print(f"[✔] Added Quick-Snap buttons (Current Watch & Watch Goal Guide) to Correlator in {fpath}")

        # Bind cfgStepGoal input event to re-render correlator
        listener_bind = """
      const cfgStepGoalEl = document.getElementById('cfgStepGoal');
      if (cfgStepGoalEl) {
        cfgStepGoalEl.addEventListener('input', () => { if (typeof renderStepGlucoseCorrelator === 'function') renderStepGlucoseCorrelator(); });
        cfgStepGoalEl.addEventListener('change', () => { if (typeof renderStepGlucoseCorrelator === 'function') renderStepGlucoseCorrelator(); });
      }
"""
        if "cfgStepGoalEl.addEventListener" not in html and "function initApp()" in html:
            html = html.replace("function initApp() {", "function initApp() {" + listener_bind)
            print(f"[✔] Bound cfgStepGoal listener in initApp() in {fpath}")

        # -------------------------------------------------------------
        # 3. SCRUB HEX CODES FROM STAGE CARDS & BUTTONS
        # -------------------------------------------------------------
        replacements = [
            ("(0x89 0x01)", "Optical PPG Sensor"),
            ("(0x87 0x01)", "Bio-Impedance Sensor"),
            ("(0x90 0x01)", "Skin & Core Thermometry"),
            ("(0xE0)", "Battery Diagnostics"),
            ("(0xB8)", "Sleep Architecture"),
            ("(0xA1)", "Metabolic Waveform"),
            ("(0x03)", "Lead Calibration"),
            ("(0xC7)", "Watch Face Controller"),
            ("(0x93)", "Motion G-Sensor"),
            ("(0xB4)", "Screen Timeout"),
            ("(0x80)", "Firmware Verification"),
            ("0xF0080003", "Live Waveform Stream"),
            ("0xF0080002", "Continuous Stream"),
            ("(0x20)", "Health Packets"),
            ("(0x61)", "Profile Targets"),
            ("(0x71)", "Time Synchronization"),
            ("0x9B4E...A1F0", "SHA-256 Air-Gapped Hash")
        ]
        for old_txt, new_txt in replacements:
            html = html.replace(old_txt, new_txt)

        # -------------------------------------------------------------
        # 4. FIX WATCH FACE SIMULATOR SO IT ALWAYS LOADS
        # -------------------------------------------------------------
        # Make sure queryCurrentWatchFace is async and doesn't fail if disconnected
        watch_query_pattern = r'function queryCurrentWatchFace\(\)\s*\{[^}]*\}'
        new_watch_query = """async function queryCurrentWatchFace() {
      soundButtonPress();
      logLCARS("[*] Checking Watch Face complication slots and physical dial display...", "tx");
      try {
        if (typeof isConnected !== 'undefined' && isConnected) {
          const pkt = [0xC7, 0x02, 0x00, 0x00];
          await sendGattCommandBytes(pkt, "Read Screen Style");
        } else {
          logLCARS("[i] Watch face dial running in high-fidelity autonomous simulation mode.", "sys");
        }
      } catch(e) {
        console.warn("Watch face query:", e);
      }
      // Ensure dial is fully rendered and ticking
      if (typeof initUnifiedGlucoseRing === 'function') initUnifiedGlucoseRing();
      if (typeof updateChronoDial === 'function') updateChronoDial(106, getLiveWatchSteps(), 68);
      if (typeof tickChronoClock === 'function') tickChronoClock();
    }"""
        html = re.sub(watch_query_pattern, new_watch_query, html, count=1)

        with open(fpath, "w", encoding="utf-8") as fp:
            fp.write(html)
        print(f"[✔] Successfully processed and saved {fpath}")

if __name__ == "__main__":
    update_index_files()
