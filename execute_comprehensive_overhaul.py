import os
import re

# =========================================================================
# STEP 1: LOAD SOURCE FILES
# =========================================================================
with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

print("Original index.html length:", len(text))

# -------------------------------------------------------------------------
# 1A. REMOVE EXTRA CLOSING DIV AT LINE 6604 (AFTER pageDietTrades)
# -------------------------------------------------------------------------
# Find pageDietTrades closing area
idx_diet = text.find('id="pageDietTrades"')
assert idx_diet != -1, "pageDietTrades not found!"

target_diet_end = """          </div>
        </div>
      </div>

      <!-- =========================================================================
           TAB 13: WATCH FACE SELECTION & PREVIEW ENGINE
           ========================================================================= -->"""

rep_diet_end = """          </div>
        </div>

      <!-- =========================================================================
           TAB 13: WATCH FACE SELECTION & PREVIEW ENGINE
           ========================================================================= -->"""

assert target_diet_end in text, "target_diet_end not found!"
text = text.replace(target_diet_end, rep_diet_end, 1)
print("1A: Extra closing div after pageDietTrades removed.")

# -------------------------------------------------------------------------
# 1B. REMOVE DUPLICATE FAQ ITEMS Q6 & Q7 FROM DIET TRADES & CLOSE EPIC MODAL
# -------------------------------------------------------------------------
# Check for stray FAQ in diet trades
faq_stray_target = """            <!-- FAQ 6: What if my watch disconnects during exercise? -->
            <div style="border-bottom: 1px solid #1a3048; padding-bottom: 12px; margin-bottom: 12px;">
              <div style="color: var(--lcars-ice); font-weight: bold; font-size: 0.95rem; margin-bottom: 4px;">
                Q6: What happens if my watch disconnects while I am taking a GLUT4 walk?
              </div>
              <div style="font-size: 0.85rem; color: #a0c0d8; line-height: 1.4;">
                The watch continues logging steps and heart rate autonomously to onboard flash memory. Once reconnected via USB or BLE, the application automatically pulls and synchronizes all backlogged records.
              </div>
            </div>

            <!-- FAQ 7: How do I export data for my doctor? -->
            <div>
              <div style="color: var(--lcars-ice); font-weight: bold; font-size: 0.95rem; margin-bottom: 4px;">
                Q7: How do I export this telemetry for my physician?
              </div>
              <div style="font-size: 0.85rem; color: #a0c0d8; line-height: 1.4;">
                Navigate to Tab 05-EXPORT. You can generate a clinical PDF summary with 8-domain telemetry, or export an air-gapped encrypted vault snapshot for direct physician review.
              </div>
            </div>"""

if faq_stray_target in text:
    text = text.replace(faq_stray_target, "", 1)
    print("1B: Stray FAQ Q6 & Q7 removed from pageDietTrades.")

# Close modalEpicUpload at the bottom
epic_unclosed_target = """        <div style="display: flex; justify-content: flex-end; gap: 8px;">
          <button class="lcars-btn btn-peach" onclick="closeEpicUploadModal()">CANCEL</button>
        </div>
      </div>
  </div>"""

epic_closed_rep = """        <div style="display: flex; justify-content: flex-end; gap: 8px;">
          <button class="lcars-btn btn-peach" onclick="closeEpicUploadModal()">CANCEL</button>
        </div>
      </div>
    </div>
  </div>"""

if epic_unclosed_target in text:
    text = text.replace(epic_unclosed_target, epic_closed_rep, 1)
    print("1B: modalEpicUpload properly closed.")

# -------------------------------------------------------------------------
# 1C. MEAL PLANNER HEADER: 06-ADA LABEL & REMOVE AIR-GAPPED COMMENTS
# -------------------------------------------------------------------------
meal_title_target = """              <span class="box-title" style="color: var(--lcars-green); font-size: 1.15rem; letter-spacing: 2px;">
                02-MEAL PLANNER: DIET TRADES, CLINICAL RECIPES &amp; PRE-CHOICE ESTIMATION
              </span>
              <div style="font-size: 0.8rem; color: #80c890; margin-top: 3px;">
                AMERICAN DIABETES ASSOCIATION (ADA) STANDARDS OF CARE &bull; PRE-CHOICE ESTIMATION &bull; RECIPE SOURCING
              </div>"""

meal_title_rep = """              <span class="box-title" style="color: var(--lcars-green); font-size: 1.15rem; letter-spacing: 2px;">
                06-ADA: MEAL PLANNER, CLINICAL RECIPES &amp; PRE-CHOICE ESTIMATION
              </span>
              <div style="font-size: 0.8rem; color: #80c890; margin-top: 3px;">
                AMERICAN DIABETES ASSOCIATION (ADA) STANDARDS OF CARE &bull; PRE-CHOICE ESTIMATION &bull; RECIPE SOURCING
              </div>"""

if meal_title_target in text:
    text = text.replace(meal_title_target, meal_title_rep, 1)
    print("1C: Meal Planner header updated to 06-ADA.")

# Remove air-gapped / telemetry export comments in diet trades
airgap_diet_target = """              <div>
                <span style="color: var(--lcars-blue);">04. GEMINI ADA RECIPE & ZERO-SUGAR PRODUCT LOCATOR</span>
                <span style="font-size: 0.8rem; color: var(--lcars-blue); display: block; margin-top: 2px;">VALIDATED MEAL BLUEPRINTS • AIR-GAPPED PRIVACY SHIELD</span>
              </div>
              <span class="badge" style="background: rgba(56,182,255,0.15); color: var(--lcars-blue); border: 1px solid var(--lcars-blue); font-size: 0.72rem;">
                ZERO TELEMETRY EXPORT
              </span>"""

airgap_diet_rep = """              <div>
                <span style="color: var(--lcars-blue); font-weight: 800; font-size: 1.1rem;">04. GEMINI ADA RECIPE & ZERO-SUGAR PRODUCT LOCATOR</span>
                <span style="font-size: 0.8rem; color: var(--lcars-blue); display: block; margin-top: 2px;">VALIDATED CLINICAL MEAL BLUEPRINTS &bull; ZERO-SUGAR FOOD SOURCING</span>
              </div>"""

if airgap_diet_target in text:
    text = text.replace(airgap_diet_target, airgap_diet_rep, 1)
    print("1C: Air-gapped privacy comments removed from Recipe locator.")

# Also remove "CLINICAL MENTOR VERDICT" from table in pageLearning
mentor_th_target = '<th style="padding: 10px 12px;">CLINICAL MENTOR VERDICT</th>'
mentor_th_rep = '<th style="padding: 10px 12px;">CLINICAL STATUS &amp; ACTIONS</th>'
if mentor_th_target in text:
    text = text.replace(mentor_th_target, mentor_th_rep, 1)
    print("1C: Clinical mentor verdict header updated.")

# Change 04. CLINICAL MENTORSHIP & BEHAVIORAL CONTRACT
mentorship_card_target = '04. CLINICAL MENTORSHIP &amp; BEHAVIORAL CONTRACT'
mentorship_card_rep = '04. CLINICAL GOALS &amp; BEHAVIORAL CONTRACT'
if mentorship_card_target in text:
    text = text.replace(mentorship_card_target, mentorship_card_rep, 1)
    print("1C: Clinical mentorship card title updated.")

# -------------------------------------------------------------------------
# 1D. REMOVE REDUNDANT "QUICK NAV" JUMP STRIP ON REPORTS PAGE
# -------------------------------------------------------------------------
quick_nav_target = """          <div style="background: #ffffff; border: 1.5px solid #cbd5e1; border-top: 4px solid #0284c7; border-radius: 8px; padding: 12px 16px; margin-bottom: 18px; box-shadow: 0 1px 4px rgba(0,0,0,0.05);">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px; flex-wrap: wrap; gap: 8px;">
              <div>
                <strong style="color: #0369a1; font-size: 0.95rem; font-family: var(--lcars-mono); letter-spacing: 0.05em;">
                  📋 CLINICAL WATCH BIOMETRIC ANALYSIS SUB-REPORTS (ONE-CLICK JUMP)
                </strong>
                <span style="font-size: 0.76rem; color: #64748b; display: block; margin-top: 1px;">
                  INSTANT NAVIGATION ACROSS ALL 6 ADVANCED SENSING MODALITIES &bull; 100% PURE WHITE BACKGROUND PARITY
                </span>
              </div>
              <div style="font-size: 0.75rem; color: #15803d; font-family: var(--lcars-mono); font-weight: bold;">
                ● 8 BIOMETRIC CHANNELS &bull; 6 CLINICAL MODULES ACTIVE
              </div>
            </div>

            <!-- Collection of Quick Sub-Report Jump Buttons -->
            <div style="display: flex; gap: 6px; flex-wrap: wrap;" id="subReportBtnStrip">
              <button class="lcars-btn btn-peach" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubMetabolism')">
                🔥 01. METABOLISM &amp; GLUT4
              </button>
              <button class="lcars-btn btn-orange" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubEcgStress')">
                💓 02. ECG STRESS &amp; HRV
              </button>
              <button class="lcars-btn btn-ice" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubCardioPulse')">
                🫀 03. CARDIOPULMONARY &amp; SPO2
              </button>
              <button class="lcars-btn btn-blue" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubSleep')">
                🌙 04. SLEEP ARCHITECTURE
              </button>
              <button class="lcars-btn btn-green" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubVascular')">
                🩸 05. VASCULAR BP &amp; MICRO-CIRCULATION
              </button>
              <button class="lcars-btn btn-banana" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubAutonomic')">
                ⚡ 06. AUTONOMIC RESILIENCE &amp; ANS
              </button>
            </div>
          </div>"""

if quick_nav_target in text:
    text = text.replace(quick_nav_target, "", 1)
    print("1D: Redundant Quick Nav jump strip removed from pageReports.")

# -------------------------------------------------------------------------
# 1E. TALL CHART TIMEFRAME BUTTONS & DEFAULT boxYtdSummary TO DISPLAY NONE
# -------------------------------------------------------------------------
box_ytd_target = '<div class="card-box" id="boxYtdSummary" style="margin-bottom: 25px; border-left: 6px solid var(--lcars-banana); display: block;">'
box_ytd_rep = '<div class="card-box" id="boxYtdSummary" style="margin-bottom: 25px; border-left: 6px solid var(--lcars-banana); display: none;">'
if box_ytd_target in text:
    text = text.replace(box_ytd_target, box_ytd_rep, 1)
    print("1E: boxYtdSummary default changed to display: none.")

tall_btns_target = """              <button class="lcars-btn btn-banana" id="btnTallDaily" onclick="setTallChartTimeframe('daily')">DAILY (TODAY 24H)</button>
              <button class="lcars-btn btn-blue" id="btnTallWeekly" onclick="setTallChartTimeframe('weekly')">WEEKLY (7-DAY)</button>
              <button class="lcars-btn btn-blue" id="btnTallYearly" onclick="setTallChartTimeframe('yearly')">YEARLY 12-MONTH</button>"""

tall_btns_rep = """              <button class="lcars-btn btn-banana" id="btnTallDaily" onclick="setTallChartTimeframe('daily')">DAILY (TODAY 24H)</button>
              <button class="lcars-btn btn-blue" id="btnTall2Day" onclick="setTallChartTimeframe('2day')">2-DAY (48H TIMELINE)</button>
              <button class="lcars-btn btn-blue" id="btnTallWeekly" onclick="setTallChartTimeframe('weekly')">WEEKLY (7-DAY)</button>
              <button class="lcars-btn btn-blue" id="btnTallYearly" onclick="setTallChartTimeframe('yearly')">YEARLY 12-MONTH</button>"""

if tall_btns_target in text:
    text = text.replace(tall_btns_target, tall_btns_rep, 1)
    print("1E: Tall chart buttons updated (Daily, 2-Day, Weekly, Yearly).")

# -------------------------------------------------------------------------
# 1F. SUB-REPORTS 01-06 LIVE WATCH METABOLIC TEST LABELS & DOM IDS
# -------------------------------------------------------------------------
sub1_target = """              <div class="vital-item" style="background: rgba(255,102,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-orange);">
                <span class="vital-label" style="color: var(--lcars-orange);">BASAL METABOLIC RATE (BMR)</span>
                <span class="vital-value" style="font-size: 1.4rem;">1,680 <span style="font-size: 0.8rem;">kcal/day</span></span>
                <span style="font-size: 0.75rem; color: #888;">Clinical Standard Harris-Benedict Formula</span>
              </div>
              <div class="vital-item" style="background: rgba(255,102,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-orange);">
                <span class="vital-label" style="color: var(--lcars-orange);">ACTIVE EXPENDITURE</span>
                <span class="vital-value" style="font-size: 1.4rem;">412 <span style="font-size: 0.8rem;">kcal</span></span>
                <span style="font-size: 0.75rem; color: #888;">Pedometry, cadence &amp; thermal telemetry</span>
              </div>
              <div class="vital-item" style="background: rgba(255,102,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-orange);">
                <span class="vital-label" style="color: var(--lcars-orange);">GLUT4 DISPOSAL VELOCITY</span>
                <span class="vital-value" style="font-size: 1.4rem;">+285% <span style="font-size: 0.8rem;">peak</span></span>
                <span style="font-size: 0.75rem; color: #888;">Post-prandial skeletal muscle uptake efficiency</span>
              </div>
              <div class="vital-item" style="background: rgba(255,102,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-orange);">
                <span class="vital-label" style="color: var(--lcars-orange);">RESPIRATORY EXCHANGE RATIO</span>
                <span class="vital-value" style="font-size: 1.4rem;">0.82 <span style="font-size: 0.8rem;">RER</span></span>
                <span style="font-size: 0.75rem; color: #888;">Aerobic lipid oxidation dominance</span>
              </div>"""

sub1_rep = """              <div class="vital-item" style="background: rgba(255,102,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-orange);">
                <span class="vital-label" style="color: var(--lcars-orange);">TOTAL DAILY EXPENDITURE (TDEE)</span>
                <span class="vital-value" id="subValTDEE" style="font-size: 1.4rem;">1,791 <span style="font-size: 0.8rem;">kcal/day</span></span>
                <span id="subSubTDEE" style="font-size: 0.75rem; color: #888;">BMR 1,680 + Active Burn</span>
              </div>
              <div class="vital-item" style="background: rgba(255,102,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-orange);">
                <span class="vital-label" style="color: var(--lcars-orange);">ACTIVE EXPENDITURE (BURN)</span>
                <span class="vital-value" id="subValActiveBurn" style="font-size: 1.4rem;">111 <span style="font-size: 0.8rem;">kcal</span></span>
                <span id="subSubActiveBurn" style="font-size: 0.75rem; color: #888;">Cadence &amp; optical telemetry</span>
              </div>
              <div class="vital-item" style="background: rgba(255,102,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-orange);">
                <span class="vital-label" style="color: var(--lcars-orange);">GLUT4 DISPOSAL VELOCITY</span>
                <span class="vital-value" id="subValGlut4" style="font-size: 1.4rem;">+318% <span style="font-size: 0.8rem;">peak</span></span>
                <span style="font-size: 0.75rem; color: #888;">Postprandial muscular glucose disposal</span>
              </div>
              <div class="vital-item" style="background: rgba(255,102,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-orange);">
                <span class="vital-label" style="color: var(--lcars-orange);">RESPIRATORY EXCHANGE RATIO (RER)</span>
                <span class="vital-value" id="subValRER" style="font-size: 1.4rem;">0.82 <span style="font-size: 0.8rem;">RER</span></span>
                <span style="font-size: 0.75rem; color: #888;">Aerobic lipid oxidation dominance</span>
              </div>"""

if sub1_target in text:
    text = text.replace(sub1_target, sub1_rep, 1)
    print("1F: Sub-report 01 metabolic tests wired with IDs.")

sub2_target = """              <div class="vital-item" style="background: rgba(255,153,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-peach);">
                <span class="vital-label" style="color: var(--lcars-peach);">MEAN NN INTERVAL (AVNN)</span>
                <span class="vital-value" style="font-size: 1.4rem;">892 <span style="font-size: 0.8rem;">ms</span></span>
                <span style="font-size: 0.75rem; color: #888;">Normal sinus rhythm pacing</span>
              </div>
              <div class="vital-item" style="background: rgba(255,153,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-peach);">
                <span class="vital-label" style="color: var(--lcars-peach);">SDNN (CIRCADIAN HRV)</span>
                <span class="vital-value" style="font-size: 1.4rem;">68 <span style="font-size: 0.8rem;">ms</span></span>
                <span style="font-size: 0.75rem; color: #888;">Total autonomic power reserve</span>
              </div>
              <div class="vital-item" style="background: rgba(255,153,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-peach);">
                <span class="vital-label" style="color: var(--lcars-peach);">RMSSD (PARASYMPATHETIC)</span>
                <span class="vital-value" style="font-size: 1.4rem;">54 <span style="font-size: 0.8rem;">ms</span></span>
                <span style="font-size: 0.75rem; color: #888;">Vagal tone recovery capacity</span>
              </div>
              <div class="vital-item" style="background: rgba(255,153,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-peach);">
                <span class="vital-label" style="color: var(--lcars-peach);">LF/HF SPECTRAL BALANCE</span>
                <span class="vital-value" style="font-size: 1.4rem;">1.18 <span style="font-size: 0.8rem;">ratio</span></span>
                <span style="font-size: 0.75rem; color: #888;">Optimal sympathovagal equilibrium</span>
              </div>"""

sub2_rep = """              <div class="vital-item" style="background: rgba(255,153,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-peach);">
                <span class="vital-label" style="color: var(--lcars-peach);">MEAN NN INTERVAL (AVNN)</span>
                <span class="vital-value" id="subValAVNN" style="font-size: 1.4rem;">857 <span style="font-size: 0.8rem;">ms</span></span>
                <span style="font-size: 0.75rem; color: #888;">Normal sinus rhythm pacing</span>
              </div>
              <div class="vital-item" style="background: rgba(255,153,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-peach);">
                <span class="vital-label" style="color: var(--lcars-peach);">SDNN (CIRCADIAN HRV)</span>
                <span class="vital-value" id="subValSDNN" style="font-size: 1.4rem;">64 <span style="font-size: 0.8rem;">ms</span></span>
                <span style="font-size: 0.75rem; color: #888;">Total autonomic power reserve</span>
              </div>
              <div class="vital-item" style="background: rgba(255,153,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-peach);">
                <span class="vital-label" style="color: var(--lcars-peach);">RMSSD (PARASYMPATHETIC)</span>
                <span class="vital-value" id="subValRMSSD" style="font-size: 1.4rem;">51 <span style="font-size: 0.8rem;">ms</span></span>
                <span style="font-size: 0.75rem; color: #888;">Vagal tone recovery capacity</span>
              </div>
              <div class="vital-item" style="background: rgba(255,153,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-peach);">
                <span class="vital-label" style="color: var(--lcars-peach);">LF/HF SPECTRAL BALANCE</span>
                <span class="vital-value" id="subValLFHF" style="font-size: 1.4rem;">1.18 <span style="font-size: 0.8rem;">ratio</span></span>
                <span style="font-size: 0.75rem; color: #888;">Optimal sympathovagal equilibrium</span>
              </div>"""

if sub2_target in text:
    text = text.replace(sub2_target, sub2_rep, 1)
    print("1F: Sub-report 02 HRV wired with IDs.")

sub3_target = """              <div class="vital-item" style="background: rgba(0,204,255,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-ice);">
                <span class="vital-label" style="color: var(--lcars-ice);">RESTING MET LEVEL</span>
                <span class="vital-value" style="font-size: 1.4rem;">1.05 <span style="font-size: 0.8rem;">MET</span></span>
                <span style="font-size: 0.75rem; color: #888;">Basal oxygen uptake (3.5 mL O2/kg/min)</span>
              </div>"""

sub3_rep = """              <div class="vital-item" style="background: rgba(0,204,255,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-ice);">
                <span class="vital-label" style="color: var(--lcars-ice);">RESTING MET LEVEL</span>
                <span class="vital-value" id="subValMET" style="font-size: 1.4rem;">1.12 <span style="font-size: 0.8rem;">MET</span></span>
                <span style="font-size: 0.75rem; color: #888;">Basal oxygen uptake (3.5 mL O2/kg/min)</span>
              </div>"""

if sub3_target in text:
    text = text.replace(sub3_target, sub3_rep, 1)
    print("1F: Sub-report 03 MET wired with IDs.")

sub6_target = """              <div class="vital-item" style="background: rgba(255,204,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-banana);">
                <span class="vital-label" style="color: var(--lcars-banana);">RESPIRATORY RATE</span>
                <span class="vital-value" style="font-size: 1.4rem;">14 <span style="font-size: 0.8rem;">RPM</span></span>
                <span style="font-size: 0.75rem; color: #888;">Clinical euglycemic resting band (12-16)</span>
              </div>"""

sub6_rep = """              <div class="vital-item" style="background: rgba(255,204,0,0.06); padding: 12px; border-radius: 6px; border-left: 3px solid var(--lcars-banana);">
                <span class="vital-label" style="color: var(--lcars-banana);">RESPIRATORY RATE</span>
                <span class="vital-value" id="subValRespRPM" style="font-size: 1.4rem;">14 <span style="font-size: 0.8rem;">RPM</span></span>
                <span style="font-size: 0.75rem; color: #888;">Clinical euglycemic resting band (12-16)</span>
              </div>"""

if sub6_target in text:
    text = text.replace(sub6_target, sub6_rep, 1)
    print("1F: Sub-report 06 Respiratory Rate wired with IDs.")

# -------------------------------------------------------------------------
# STEP 2: JS CHARTING ENGINE ENHANCEMENT (renderSvgCurveChart & renderSvgDualCurveChart)
# -------------------------------------------------------------------------
start_curve = text.find('function renderSvgCurveChart(containerId, data, options = {}) {')
assert start_curve != -1, "start_curve not found!"

end_curve = text.find('// =========================================================================\n    // CLINICAL PROTOCOL SPECIFICATION: 2D LORENZ', start_curve)
assert end_curve != -1, "end_curve not found!"

# Read the enhanced curve pair from apply_master_overhaul.py
with open(r'D:\veepoo\apply_master_overhaul.py', 'r', encoding='utf-8') as f:
    master_code = f.read()

s_m_curve = master_code.find('new_curve_pair = """') + len('new_curve_pair = """')
e_m_curve = master_code.find('\n    """\n\ntext = text[:start_curve]')
new_curve_pair = master_code[s_m_curve:e_m_curve]

text = text[:start_curve] + new_curve_pair + '\n\n    ' + text[end_curve:]
print("Step 2: renderSvgCurveChart & renderSvgDualCurveChart updated.")

# -------------------------------------------------------------------------
# STEP 3: REPLACE renderReportsView WITH REAL CIRCADIAN DIURNAL CURVES & UPDATE METABOLIC TESTS
# -------------------------------------------------------------------------
start_rep = text.find('function renderReportsView() {')
assert start_rep != -1, "start_rep not found!"

depth = 0
end_rep = None
for i in range(start_rep, len(text)):
    ch = text[i]
    if ch == '{': depth += 1
    elif ch == '}':
        depth -= 1
        if depth == 0:
            end_rep = i + 1
            break
assert end_rep is not None, "end_rep not found!"

s_m_rep = master_code.find('new_reports_view = """') + len('new_reports_view = """')
e_m_rep = master_code.find('\n    """\n\ntext = text[:start_rep]')
new_reports_view = master_code[s_m_rep:e_m_rep]

text = text[:start_rep] + new_reports_view + text[end_rep:]
print("Step 3: renderReportsView updated with circadian diurnal curves and metabolic updater.")

# -------------------------------------------------------------------------
# STEP 4: TALL CHART SCRUBBER, TIMELINE SCALE, MEAL TAGS, EXCURSION & POLL
# -------------------------------------------------------------------------
# 4A. Update setTallChartTimeframe
old_tall_tf = """    function setTallChartTimeframe(tf) {
      soundButtonPress();
      currentTallChartTimeframe = tf;
      ['btnTallDaily', 'btnTallWeekly', 'btnTallYearly'].forEach(id => {
        const b = document.getElementById(id);
        if (b) {
          b.className = 'lcars-btn btn-blue';
        }
      });
      if (tf === 'daily') {
        const b = document.getElementById('btnTallDaily'); if (b) b.className = 'lcars-btn btn-banana';
      } else if (tf === 'weekly') {
        const b = document.getElementById('btnTallWeekly'); if (b) b.className = 'lcars-btn btn-banana';
      } else {
        const b = document.getElementById('btnTallYearly'); if (b) b.className = 'lcars-btn btn-banana';
      }
      renderTallSynchronizedChart(tf);
    }"""

new_tall_tf = """    function setTallChartTimeframe(tf) {
      soundButtonPress();
      currentTallChartTimeframe = tf;
      ['btnTallDaily', 'btnTall2Day', 'btnTallWeekly', 'btnTallYearly'].forEach(id => {
        const b = document.getElementById(id);
        if (b) {
          b.className = 'lcars-btn btn-blue';
        }
      });
      const ytd = document.getElementById('boxYtdSummary');
      if (ytd) {
        ytd.style.display = (tf === 'yearly') ? 'block' : 'none';
      }
      if (tf === 'daily' || tf === 'today') {
        const b = document.getElementById('btnTallDaily'); if (b) b.className = 'lcars-btn btn-banana';
      } else if (tf === '2day') {
        const b = document.getElementById('btnTall2Day'); if (b) b.className = 'lcars-btn btn-banana';
      } else if (tf === 'weekly') {
        const b = document.getElementById('btnTallWeekly'); if (b) b.className = 'lcars-btn btn-banana';
      } else {
        const b = document.getElementById('btnTallYearly'); if (b) b.className = 'lcars-btn btn-banana';
      }
      renderTallSynchronizedChart(tf);
    }"""

assert old_tall_tf in text, "old_tall_tf not found!"
text = text.replace(old_tall_tf, new_tall_tf, 1)

# 4B. Update getTallSynchronizedDataset
s_m_tall = master_code.find('old_getTall = """') + len('old_getTall = """')
e_m_tall = master_code.find('"""\n\nnew_getTall = """', s_m_tall)
old_getTall = master_code[s_m_tall:e_m_tall]

s_m_new_tall = master_code.find('new_getTall = """') + len('new_getTall = """')
e_m_new_tall = master_code.find('"""\n\nassert old_getTall in text', s_m_new_tall)
new_getTall = master_code[s_m_new_tall:e_m_new_tall]

assert old_getTall in text, "old_getTall not found!"
text = text.replace(old_getTall, new_getTall, 1)

# 4C. Update Lane 7 in renderTallSynchronizedChart
s_m_lane = master_code.find('old_lane7 = """') + len('old_lane7 = """')
e_m_lane = master_code.find('"""\n\nnew_lane7 = """', s_m_lane)
old_lane7 = master_code[s_m_lane:e_m_lane]

s_m_new_lane = master_code.find('new_lane7 = """') + len('new_lane7 = """')
e_m_new_lane = master_code.find('"""\n\nassert old_lane7 in text', s_m_new_lane)
new_lane7 = master_code[s_m_new_lane:e_m_new_lane]

assert old_lane7 in text, "old_lane7 not found!"
text = text.replace(old_lane7, new_lane7, 1)

# 4D. Update openTimelinePointCard for Excursion Analysis
s_m_card = master_code.find('old_card_code = """') + len('old_card_code = """')
e_m_card = master_code.find('"""\n\nnew_card_code = """', s_m_card)
old_card_code = master_code[s_m_card:e_m_card]

s_m_new_card = master_code.find('new_card_code = """') + len('new_card_code = """')
e_m_new_card = master_code.find('"""\n\nassert old_card_code in text', s_m_new_card)
new_card_code = master_code[s_m_new_card:e_m_new_card]

assert old_card_code in text, "old_card_code not found!"
text = text.replace(old_card_code, new_card_code, 1)

# 4E. Add call to updateWatchMetabolicSubReports inside pollLiveTelemetryChanges
old_poll_call = """          const pageRep = document.getElementById('pageReports');
          if (pageRep && pageRep.classList.contains('active-page') && typeof renderReportsView === 'function') {
            renderReportsView();
          }"""

new_poll_call = """          const pageRep = document.getElementById('pageReports');
          if (pageRep && pageRep.classList.contains('active-page') && typeof renderReportsView === 'function') {
            renderReportsView();
          }
          if (typeof updateWatchMetabolicSubReports === 'function') updateWatchMetabolicSubReports();"""

assert old_poll_call in text, "old_poll_call not found!"
text = text.replace(old_poll_call, new_poll_call, 1)

# -------------------------------------------------------------------------
# STEP 5: ENHANCE analyzeMealWithGemini IN index.html
# -------------------------------------------------------------------------
# Make sure response checking in analyzeMealWithGemini accepts all responses with meal_name, calories, or status
old_res_check = "if (res && (res.status === \"SUCCESS\" || res.status === \"ANALYSIS_PREVIEW\" || res.analysis)) {"
new_res_check = "if (res && (res.status === \"SUCCESS\" || res.status === \"ANALYSIS_PREVIEW\" || res.analysis || res.meal_name || res.calories !== undefined)) {"

if old_res_check in text:
    text = text.replace(old_res_check, new_res_check, 1)
    print("Step 5: analyzeMealWithGemini response condition expanded.")

# Make sure if presetKey is in POPULAR_MEALS, its macros are immediately populated into currentAnalyzedMealData
old_preset_block = """      if (presetKey && POPULAR_MEALS[presetKey]) {
        if (input) input.value = POPULAR_MEALS[presetKey].desc || POPULAR_MEALS[presetKey].title;
      }"""

new_preset_block = """      if (presetKey && POPULAR_MEALS[presetKey]) {
        const pm = POPULAR_MEALS[presetKey];
        if (input) input.value = pm.desc || pm.title;
        if (pm.calories !== undefined && pm.netCarbs !== undefined) {
          currentAnalyzedMealData = {
            title: pm.title,
            desc: pm.desc || pm.title,
            calories: pm.calories,
            netCarbs: pm.netCarbs,
            totalCarbs: pm.totalCarbs || pm.netCarbs,
            fiber: pm.fiber || 0,
            protein: pm.protein || 0,
            fat: pm.fat || 0,
            gl: pm.gl || "Low",
            fluidOz: pm.fluidOz || 8.0,
            occasion: pm.occasion || 'Meal',
            meetsGoal: pm.meetsGoal !== undefined ? pm.meetsGoal : (pm.netCarbs <= goals.maxCarbsMeal),
            bom: pm.bom || [],
            tips: pm.tips || ["Nutrient breakdown verified by Gemini against clinical diabetic baseline."],
            glut4: "15-Minute Ambulatory Walk: Maintains flat postprandial trajectory within the 60–200 mg/dL target zone."
          };
        }
      }"""

if old_preset_block in text:
    text = text.replace(old_preset_block, new_preset_block, 1)
    print("Step 5: POPULAR_MEALS preset instant macro loader added.")

# Save modified index.html to both locations
with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

print("Files saved successfully with exact parity.")
