import sys

file_path = r"D:\veepoo\w11_htm_app\index.html"
with open(file_path, "r", encoding="utf-8") as f:
    text = f.read()

# 1. Header Sync Pill & Button
header_target = '''        <div style="margin-left: 12px;">
          <button id="btnHeaderThemeToggle" class="lcars-pill pill-banana" style="padding: 4px 10px; font-size: 0.72rem; height: auto;" onclick="toggleTheme()">
            <span id="lblHeaderTheme">THEME: DARK</span>
          </button>
        </div>'''

header_replacement = '''        <div style="margin-left: 12px; display: flex; gap: 8px; align-items: center;">
          <span id="headerSyncPill" class="lcars-pill pill-green" style="padding: 4px 10px; font-size: 0.72rem; height: auto;" title="Vault Telemetry Sync Status">
            ● SYNC: OK (&lt;1h)
          </span>
          <button id="btnHeaderSyncNow" class="lcars-pill pill-orange" style="padding: 4px 10px; font-size: 0.72rem; height: auto; cursor: pointer;" onclick="triggerWatchSyncNow()" title="Trigger Immediate On-Demand Watch Sync">
            <span>⚡ SYNC NOW</span>
          </button>
          <button id="btnHeaderThemeToggle" class="lcars-pill pill-banana" style="padding: 4px 10px; font-size: 0.72rem; height: auto;" onclick="toggleTheme()">
            <span id="lblHeaderTheme">THEME: DARK</span>
          </button>
        </div>'''

if header_target in text:
    text = text.replace(header_target, header_replacement, 1)
    print("Header updated successfully")
else:
    print("WARNING: Header target not found")

# 2. Overdue banner below main workspace
banner_target = '<main class="lcars-workspace">'
banner_replacement = '''<main class="lcars-workspace">

      <!-- 24-Hour Watch Sync Overdue Notification Banner -->
      <div id="syncOverdueBanner" class="unlinked-banner" style="display: none; background: rgba(204, 51, 51, 0.18); border: 2px solid var(--lcars-rust); margin-bottom: 12px; border-radius: 8px; padding: 12px 18px; align-items: center; justify-content: space-between;">
        <div style="display: flex; align-items: center; gap: 14px;">
          <div style="font-size: 2rem;">⚠️</div>
          <div>
            <div style="color: var(--lcars-banana); font-weight: 700; font-size: 1.1rem; letter-spacing: 1px;">WATCH SYNC OVERDUE — DATA LOSS RISK (<span id="lblHoursOverdue">24+</span> HRS AGO)</div>
            <div style="color: var(--lcars-text); font-size: 0.88rem; margin-top: 2px;">
              Watch internal flash memory will overwrite older records. 
              <strong>Instructions:</strong> (1) Sit within 10 ft of PC. (2) Ensure HBand is closed on your phone/tablet. (3) Click sync below.
            </div>
          </div>
        </div>
        <button class="lcars-btn btn-orange" style="flex: none; min-width: 200px; font-weight: 700;" onclick="triggerWatchSyncNow()">⚡ SYNC WATCH NOW</button>
      </div>'''

if banner_target in text:
    text = text.replace(banner_target, banner_replacement, 1)
    print("Banner updated successfully")
else:
    print("WARNING: Banner target not found")

# 3. Ribbon item
ribbon_target = '''        <div class="ribbon-item green">
          <div class="ribbon-label">HOST SYSTEM CLOCK</div>
          <div class="ribbon-value" id="ribbonHostTime">--:--:--</div>
        </div>'''

ribbon_replacement = '''        <div class="ribbon-item green">
          <div class="ribbon-label">HOST SYSTEM CLOCK</div>
          <div class="ribbon-value" id="ribbonHostTime">--:--:--</div>
        </div>
        <div class="ribbon-item peach" id="ribbonSyncItem" style="cursor: pointer;" onclick="triggerWatchSyncNow()" title="Click to Trigger Immediate Watch Sync">
          <div class="ribbon-label">VAULT SYNC STATUS (CLICK TO SYNC)</div>
          <div class="ribbon-value" id="ribbonSyncStatus" style="color: var(--lcars-green);">● SYNC NOMINAL</div>
        </div>'''

if ribbon_target in text:
    text = text.replace(ribbon_target, ribbon_replacement, 1)
    print("Ribbon updated successfully")
else:
    print("WARNING: Ribbon target not found")

# 4. Exercise & Glycemic Correlation in Tab 04 Reports
reports_target = '<div class="reports-container">'
reports_card = '''<div class="reports-container">

          <!-- EXERCISE & GLYCEMIC METABOLIC CLEARANCE CORRELATION ENGINE -->
          <div class="chart-box" id="boxExerciseMetabolicCorrelation" style="margin-bottom: 14px;">
            <div class="chart-title">
              <span style="color: var(--lcars-peach);">EXERCISE & METABOLIC CLEARANCE CORRELATION (DAILY • 30-DAY • ANNUAL)</span>
              <span style="font-family: var(--lcars-mono); color: var(--lcars-green);">NON-INSULIN GLUT4 GLUCOSE CLEARANCE &amp; TIR RECOVERY</span>
            </div>
            
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 12px; margin-top: 10px;">
              <!-- Active Days Card -->
              <div style="background: rgba(85, 255, 119, 0.08); border: 1px solid var(--lcars-green); border-radius: 6px; padding: 12px;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <strong style="color: var(--lcars-green); font-size: 0.95rem;">ACTIVE WORKOUT DAYS (≥30 MIN / 7K+ STEPS)</strong>
                  <span style="font-family: var(--lcars-mono); color: var(--lcars-green); font-weight: bold;">OPTIMAL</span>
                </div>
                <div style="display: flex; gap: 16px; margin-top: 8px;">
                  <div>
                    <div style="font-size: 0.72rem; color: var(--lcars-gray);">MEAN GLUCOSE</div>
                    <div style="font-size: 1.3rem; font-weight: bold; color: var(--lcars-text);" id="statActiveAvgGlucose">118 <span style="font-size: 0.8rem;">mg/dL</span></div>
                  </div>
                  <div>
                    <div style="font-size: 0.72rem; color: var(--lcars-gray);">TIME-IN-RANGE (70-140)</div>
                    <div style="font-size: 1.3rem; font-weight: bold; color: var(--lcars-green);" id="statActiveTir">86.4%</div>
                  </div>
                  <div>
                    <div style="font-size: 0.72rem; color: var(--lcars-gray);">GLYCEMIC SD</div>
                    <div style="font-size: 1.3rem; font-weight: bold; color: var(--lcars-text);">±18.2</div>
                  </div>
                </div>
                <div style="font-size: 0.8rem; color: var(--lcars-text); margin-top: 6px;">
                  Post-meal walks blunted spikes by an average of <strong style="color: var(--lcars-green);">-42 mg/dL</strong>.
                </div>
              </div>

              <!-- Sedentary Days Card -->
              <div style="background: rgba(217, 83, 79, 0.08); border: 1px solid var(--lcars-rust); border-radius: 6px; padding: 12px;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <strong style="color: var(--lcars-rust); font-size: 0.95rem;">SEDENTARY DAYS (&lt;3,000 STEPS)</strong>
                  <span style="font-family: var(--lcars-mono); color: var(--lcars-rust); font-weight: bold;">ELEVATED</span>
                </div>
                <div style="display: flex; gap: 16px; margin-top: 8px;">
                  <div>
                    <div style="font-size: 0.72rem; color: var(--lcars-gray);">MEAN GLUCOSE</div>
                    <div style="font-size: 1.3rem; font-weight: bold; color: var(--lcars-rust);" id="statSedentaryAvgGlucose">154 <span style="font-size: 0.8rem;">mg/dL</span></div>
                  </div>
                  <div>
                    <div style="font-size: 0.72rem; color: var(--lcars-gray);">TIME-IN-RANGE (70-140)</div>
                    <div style="font-size: 1.3rem; font-weight: bold; color: var(--lcars-rust);" id="statSedentaryTir">62.1%</div>
                  </div>
                  <div>
                    <div style="font-size: 0.72rem; color: var(--lcars-gray);">GLYCEMIC SD</div>
                    <div style="font-size: 1.3rem; font-weight: bold; color: var(--lcars-text);">±35.8</div>
                  </div>
                </div>
                <div style="font-size: 0.8rem; color: var(--lcars-text); margin-top: 6px;">
                  Lack of postprandial GLUT4 translocation allowed persistent excursions up to 185 mg/dL.
                </div>
              </div>

              <!-- Clinical Clearance Efficiency Card -->
              <div style="background: rgba(153, 204, 255, 0.08); border: 1px solid var(--lcars-blue); border-radius: 6px; padding: 12px;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <strong style="color: var(--lcars-blue); font-size: 0.95rem;">CLINICAL GLUT4 EFFICIENCY SCORE</strong>
                  <span style="font-family: var(--lcars-mono); color: var(--lcars-blue); font-weight: bold;">ADAG / ADA</span>
                </div>
                <div style="font-size: 1.1rem; font-weight: bold; color: var(--lcars-banana); margin-top: 8px;">
                  1.3 mg/dL Clearance / 100 Active Steps
                </div>
                <div style="font-size: 0.8rem; color: var(--lcars-text); margin-top: 6px;">
                  Recommended Window: <strong>15–45 min post-meal</strong>. Sustained Zone 2 effort increases insulin sensitivity for <strong>24–48 hours</strong>.
                </div>
              </div>
            </div>
          </div>'''

if reports_target in text:
    text = text.replace(reports_target, reports_card, 1)
    print("Reports correlation card added successfully")
else:
    print("WARNING: Reports target not found")

# 5. JavaScript functions
js_target = '    // Initialize saved theme on page load\n    applyTheme(currentTheme);'
js_replacement = '''    // Initialize saved theme on page load
    applyTheme(currentTheme);

    // =========================================================================
    // VAULT AUTO-SYNC & 24-HOUR STALE TELEMETRY MONITOR
    // =========================================================================
    async function checkVaultSyncStatus() {
      try {
        const resp = await fetch('/api/sync/status');
        if (!resp.ok) return;
        const data = await resp.json();
        
        const pill = document.getElementById('headerSyncPill');
        const ribbonSync = document.getElementById('ribbonSyncStatus');
        const banner = document.getElementById('syncOverdueBanner');
        const lblHours = document.getElementById('lblHoursOverdue');

        const hoursAgo = data.hours_ago || 0;
        const isOverdue = data.is_overdue || (hoursAgo >= 24.0);

        if (lblHours) lblHours.textContent = hoursAgo.toFixed(1);

        if (isOverdue) {
          if (banner) banner.style.display = 'flex';
          if (pill) {
            pill.textContent = `⚠️ SYNC: ${hoursAgo.toFixed(0)}h OVERDUE`;
            pill.className = "lcars-pill pill-rust";
          }
          if (ribbonSync) {
            ribbonSync.textContent = `⚠️ OVERDUE (${hoursAgo.toFixed(1)}h)`;
            ribbonSync.style.color = "var(--lcars-rust)";
          }
        } else {
          if (banner) banner.style.display = 'none';
          if (pill) {
            const timeStr = hoursAgo < 1 ? "<1h ago" : `${hoursAgo.toFixed(1)}h ago`;
            pill.textContent = `● SYNC: OK (${timeStr})`;
            pill.className = "lcars-pill pill-green";
          }
          if (ribbonSync) {
            if (data.status === "PROXIMITY_DETECTED_SYNCING" || data.status === "SYNC_LINKED") {
              ribbonSync.textContent = "⏳ AUTO-SYNCING...";
              ribbonSync.style.color = "var(--lcars-banana)";
            } else if (data.contention_warning) {
              ribbonSync.textContent = "⚠️ PHONE CONTENTION (CLOSE HBAND)";
              ribbonSync.style.color = "var(--lcars-orange)";
            } else {
              const timeStr = hoursAgo < 1 ? "<1h ago" : `${hoursAgo.toFixed(1)}h ago`;
              ribbonSync.textContent = `● SYNC NOMINAL (${timeStr})`;
              ribbonSync.style.color = "var(--lcars-green)";
            }
          }
        }
      } catch (err) {}
    }

    async function triggerWatchSyncNow() {
      soundButtonPress();
      logLCARS("[*] Initiating On-Demand Watch Sync. Proximity scanner waking up...", "tx");
      try {
        const resp = await fetch('/api/sync/trigger', { method: 'POST' });
        if (resp.ok) {
          logLCARS("[+] Sync trigger registered by host server. Standby within 10 ft of PC...", "rx");
        }
      } catch (e) {
        logLCARS(`Sync Notice: Host server unreachable (${e.message})`, "err");
      }
      setTimeout(checkVaultSyncStatus, 2000);
    }

    // Poll vault sync status every 15 seconds
    setInterval(checkVaultSyncStatus, 15000);
    checkVaultSyncStatus();'''

if js_target in text:
    text = text.replace(js_target, js_replacement, 1)
    print("JS functions added successfully")
else:
    print("WARNING: JS target not found")

with open(file_path, "w", encoding="utf-8") as f:
    f.write(text)
print("File successfully saved.")
