import re

html_path = 'D:/veepoo/w11_htm_app/index.html'
with open(html_path, 'r', encoding='utf-8') as f:
    content = f.read()

# -----------------------------------------------------------------------------
# 1. Update Navigation Pill: 03-METABOLIC -> 03-360° HEALTH
# -----------------------------------------------------------------------------
old_nav_metabolic = '''      <button id="navTabMetabolic" class="lcars-pill pill-peach" onclick="switchTab('metabolic')">
        <span>03-METABOLIC</span>
      </button>'''

new_nav_metabolic = '''      <button id="navTabMetabolic" class="lcars-pill pill-peach" onclick="switchTab('metabolic')">
        <span>03-360° HEALTH</span>
      </button>'''

assert old_nav_metabolic in content, "old_nav_metabolic not found"
content = content.replace(old_nav_metabolic, new_nav_metabolic, 1)

# -----------------------------------------------------------------------------
# 2. Add Clinical Warning to Bay 1 (BIO-01 Glycemic Matrix)
# -----------------------------------------------------------------------------
old_bay1_badge = '''              <!-- Active Glycemic Calibration Status Ribbon -->
              <div id="bayGlucoseCalBadge" style="margin: 8px 0; padding: 6px 10px; background: rgba(255, 187, 0, 0.12); border: 1px solid var(--lcars-banana); border-left: 4px solid var(--lcars-banana); border-radius: 4px; display: flex; justify-content: space-between; align-items: center; font-family: var(--lcars-mono); font-size: 0.75rem;">
                <span><strong style="color: var(--lcars-banana);">CALIBRATION:</strong> <span id="lblGlucoseCalStatus" style="color: var(--lcars-text);">PRIVATE MODE ACTIVE (CALIBRATED)</span></span>
                <span style="color: var(--lcars-gray);">RAW PPG: <span id="lblRawGlucoseVal" style="color: var(--lcars-banana); font-weight: bold;">--</span> mg/dL</span>
              </div>'''

new_bay1_badge = '''              <!-- Active Glycemic Calibration Status Ribbon -->
              <div id="bayGlucoseCalBadge" style="margin: 8px 0; padding: 6px 10px; background: rgba(255, 187, 0, 0.12); border: 1px solid var(--lcars-banana); border-left: 4px solid var(--lcars-banana); border-radius: 4px; display: flex; justify-content: space-between; align-items: center; font-family: var(--lcars-mono); font-size: 0.75rem;">
                <span><strong style="color: var(--lcars-banana);">CALIBRATION:</strong> <span id="lblGlucoseCalStatus" style="color: var(--lcars-text);">PRIVATE MODE ACTIVE (CALIBRATED)</span></span>
                <span style="color: var(--lcars-gray);">RAW PPG: <span id="lblRawGlucoseVal" style="color: var(--lcars-banana); font-weight: bold;">--</span> mg/dL</span>
              </div>

              <!-- Critical T1D Contraindication Banner -->
              <div style="margin: 6px 0; padding: 6px 10px; background: rgba(255, 68, 68, 0.12); border: 1px solid #ff4444; border-left: 4px solid #ff4444; border-radius: 4px; font-size: 0.73rem; color: #ffaaaa; font-family: var(--lcars-mono); line-height: 1.35;">
                <strong style="color: #ff6666;">⚠️ CLINICAL WARNING: NOT FOR TYPE 1 DIABETICS / INSULIN DOSING.</strong> Designed strictly for Type 2 lifestyle management, dietary feedback, and metabolic fitness. Never calculate insulin doses with optical PPG.
              </div>'''

assert old_bay1_badge in content, "old_bay1_badge not found"
content = content.replace(old_bay1_badge, new_bay1_badge, 1)

# -----------------------------------------------------------------------------
# 3. Add Clinical Warning to Setup Section 02
# -----------------------------------------------------------------------------
old_setup_sec2_title = '''          <!-- SECTION 02: HEMODYNAMIC GLUCOSE CALIBRATION MATRIX -->
          <div class="setup-section" style="border-left-color: var(--lcars-orange);">
            <div class="setup-section-title">
              <span style="color: var(--lcars-orange);">02. HEMODYNAMIC GLUCOSE CALIBRATION MATRIX</span>
              <span style="font-size: 0.8rem; color: var(--lcars-orange);">CMD 0x89 / OPTICAL PPG</span>
            </div>'''

new_setup_sec2_title = '''          <!-- SECTION 02: HEMODYNAMIC GLUCOSE CALIBRATION MATRIX -->
          <div class="setup-section" style="border-left-color: var(--lcars-orange);">
            <div class="setup-section-title">
              <span style="color: var(--lcars-orange);">02. HEMODYNAMIC GLUCOSE CALIBRATION MATRIX</span>
              <span style="font-size: 0.8rem; color: var(--lcars-orange);">CMD 0x89 / OPTICAL PPG</span>
            </div>
            <!-- Clinical Warning: Not for Type 1 Diabetics -->
            <div style="margin-bottom: 12px; padding: 8px 12px; background: rgba(255, 68, 68, 0.12); border: 1px solid #ff4444; border-left: 4px solid #ff4444; border-radius: 4px; font-size: 0.78rem; color: #ffcccc; font-family: var(--lcars-mono); line-height: 1.4;">
              <strong style="color: #ff6666;">⚠️ CLINICAL WARNING: NOT FOR TYPE 1 DIABETICS OR INSULIN TITRATION.</strong> Optical wrist telemetry estimates microvascular trends and is contraindicated for calculating rapid-acting or basal insulin dosages in insulin-dependent patients.
            </div>'''

assert old_setup_sec2_title in content, "old_setup_sec2_title not found"
content = content.replace(old_setup_sec2_title, new_setup_sec2_title, 1)

print("Step 1-3 applied successfully!")
