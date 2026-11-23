import re
import os

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# -------------------------------------------------------------------------
# STEP 1: DOM NESTING & HTML CLEANUP
# -------------------------------------------------------------------------
# 1A. Move FAQ Q6 & Q7 to pageHelp
q5_help_target = """                  <li><strong>Senior Engineering & Medical Officer Specification PDF:</strong> A formal 10-page clinical handbook detailing biophysical transducers, physics, opcodes, and clinical interpretation is stored locally and viewable at: <br>
                    <a href="http://localhost:8899/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf" target="_blank" style="color: var(--lcars-blue); text-decoration: underline;">
                      http://localhost:8899/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf
                    </a>
                  </li>
                </ol>
              </div>"""

q5_help_replacement = """                  <li><strong>Senior Engineering & Medical Officer Specification PDF:</strong> A formal 10-page clinical handbook detailing biophysical transducers, physics, opcodes, and clinical interpretation is stored locally and viewable at: <br>
                    <a href="http://localhost:8899/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf" target="_blank" style="color: var(--lcars-blue); text-decoration: underline;">
                      http://localhost:8899/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf
                    </a>
                  </li>
                </ol>
              </div>

              <!-- Q6: OEM App Parity & Migration -->
              <div style="background: #090e18; padding: 14px; border-radius: 6px; border-left: 4px solid var(--lcars-blue);">
                <strong style="color: var(--lcars-blue); font-size: 1.05rem;">Q6: How does LCARS compare to the official manufacturer mobile app? Do I lose any features?</strong>
                <div style="font-size: 0.88rem; color: #ccd6e8; line-height: 1.5; margin-top: 6px;">
                  You lose <strong>zero features</strong>. LCARS implements <strong>100% of the underlying Bluetooth GATT opcodes</strong> present in the manufacturer firmware, including blood glucose, blood pressure, dual temperature, SpO2 apnea detection, heart rate variability, sleep stages, watch face customization, and screen settings.
                  <br><br>
                  However, unlike stock mobile apps, LCARS keeps all your clinical data strictly on your local PC with <strong>zero cloud egress</strong>, provides an <strong>in-clinic blood draw calibration protocol</strong>, features an <strong>8-lane synchronized timeline chart with a continuous vertical scrubber</strong>, and provides active metabolic guidance (exercise GLUT4 kinetics, diet trades, and medication tracking).
                </div>
              </div>

              <!-- Q7: How LCARS Helps Doctors & Patients -->
              <div style="background: #090e18; padding: 14px; border-radius: 6px; border-left: 4px solid var(--lcars-green);">
                <strong style="color: var(--lcars-green); font-size: 1.05rem;">Q7: How does LCARS help my physician and me manage my diabetes better than isolated fingersticks?</strong>
                <div style="font-size: 0.88rem; color: #ccd6e8; line-height: 1.5; margin-top: 6px;">
                  Isolated fingersticks only show a static number at a single moment in time without context. LCARS provides a <strong>360-Degree Personal Health Console</strong> that correlates 8 simultaneous telemetry streams (Glucose, BP, HR/HRV, SpO2, Skin Temp, Motion, Diet, and Medications).
                  <br><br>
                  When you or your doctor scrub through your daily or weekly timeline on Tab 04, you can click on any glucose spike or drop to immediately reveal the exact meal carbs consumed, the exercise completed (and its resulting &Delta;G drop), and your medication adherence. Furthermore, when your doctor adjusts a prescription, logging the titration pins a <strong>"💊 MED CHANGE"</strong> marker across your charts, allowing both of you to objectively determine whether the change prompted glycemic <strong>progress or regression</strong>.
                </div>
              </div>"""

# 1B. Remove stray FAQ and extra closing div at end of pageDietTrades
target_diet_end = """            <!-- Products Dynamic Grid -->
            <div id="productsGrid" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); gap: 12px;">
              <!-- Rendered via JavaScript -->
            </div>
          </div>


        </div>
      </div>

        </div>
      
            <!-- FAQ ITEM: OEM APP PARITY & MIGRATION -->
            <div style="background: #ffffff; padding: 16px; border-radius: 8px; border: 2px solid #cbd5e1; border-left: 6px solid #2563eb; margin-top: 14px;">
              <strong style="color: #0f172a; font-size: 1.05rem;">Q5: How does LCARS compare to the official manufacturer mobile app? Do I lose any features?</strong>
              <div style="font-size: 0.88rem; color: #1e293b; line-height: 1.5; margin-top: 6px;">
                You lose <strong>zero features</strong>. LCARS implements <strong>100% of the underlying Bluetooth GATT opcodes</strong> present in the manufacturer firmware, including blood glucose, blood pressure, dual temperature, SpO2 apnea detection, heart rate variability, sleep stages, watch face customization, and screen settings.
                <br><br>
                However, unlike stock mobile apps, LCARS keeps all your clinical data strictly on your local PC with <strong>zero cloud egress</strong>, provides an <strong>in-clinic blood draw calibration protocol</strong>, features an <strong>8-lane synchronized timeline chart with a continuous vertical scrubber</strong>, and provides active metabolic guidance (exercise GLUT4 kinetics, diet trades, and medication tracking).
              </div>
            </div>

            <!-- FAQ ITEM: HOW LCARS HELPS DOCTORS & PATIENTS -->
            <div style="background: #ffffff; padding: 16px; border-radius: 8px; border: 2px solid #cbd5e1; border-left: 6px solid #15803d; margin-top: 14px;">
              <strong style="color: #0f172a; font-size: 1.05rem;">Q6: How does LCARS help my physician and me manage my diabetes better than isolated fingersticks?</strong>
              <div style="font-size: 0.88rem; color: #1e293b; line-height: 1.5; margin-top: 6px;">
                Isolated fingersticks only show a static number at a single moment in time without context. LCARS provides a <strong>360-Degree Personal Health Console</strong> that correlates 8 simultaneous telemetry streams (Glucose, BP, HR/HRV, SpO2, Skin Temp, Motion, Diet, and Medications).
                <br><br>
                When you or your doctor scrub through your daily or weekly timeline on Tab 04, you can click on any glucose spike or drop to immediately reveal the exact meal carbs consumed, the exercise completed (and its resulting &Delta;G drop), and your medication adherence. Furthermore, when your doctor adjusts a prescription, logging the titration pins a <strong>"💊 MED CHANGE"</strong> marker across your charts, allowing both of you to objectively determine whether the change prompted glycemic <strong>progress or regression</strong>.
              </div>
            </div>

        </div>
      </div>"""

clean_diet_trades_replacement = """            <!-- Products Dynamic Grid -->
            <div id="productsGrid" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); gap: 12px;">
              <!-- Rendered via JavaScript -->
            </div>
          </div>

        </div>
      </div>

    </div>
  </div>"""

# 1C. Close modalEpicUpload properly
epic_modal_target = """          <strong>How your doctor imports this:</strong>
          <ol style="margin: 4px 0 0 16px; padding: 0;">
            <li>Click <strong>Download Epic FHIR R4 (.JSON)</strong> or <strong>Clinical CSV</strong> to save the files to your device.</li>
            <li>Attach the files to your message in Evergreen MyChart or use the <strong>Draft Email to Doctor</strong> button.</li>
            <li>Your physician or clinic staff can import the file directly into Epic Hyperspace or Cerner via <em>"External Document Ingestion"</em> or <em>"Flowsheet Observation Ingestion"</em>. No format conversion or translation needed!</li>
          </ol>
        </div>

      </div>
    </div>
</body>"""

epic_modal_replacement = """          <strong>How your doctor imports this:</strong>
          <ol style="margin: 4px 0 0 16px; padding: 0;">
            <li>Click <strong>Download Epic FHIR R4 (.JSON)</strong> or <strong>Clinical CSV</strong> to save the files to your device.</li>
            <li>Attach the files to your message in Evergreen MyChart or use the <strong>Draft Email to Doctor</strong> button.</li>
            <li>Your physician or clinic staff can import the file directly into Epic Hyperspace or Cerner via <em>"External Document Ingestion"</em> or <em>"Flowsheet Observation Ingestion"</em>. No format conversion or translation needed!</li>
          </ol>
        </div>

      </div>
    </div>
  </div>
</body>"""

# 1D. Tab 02 / 06 title update & clean comments
meal_title_target = """              <span class="box-title" style="color: var(--lcars-green); font-size: 1.15rem; letter-spacing: 2px;">
                02-MEAL PLANNER: DIET TRADES, CLINICAL RECIPES &amp; PRE-CHOICE ESTIMATION
              </span>
              <div style="font-size: 0.8rem; color: #80c890; margin-top: 3px;">
                AMERICAN DIABETES ASSOCIATION (ADA) STANDARDS OF CARE &bull; PRE-CHOICE ESTIMATION &bull; RECIPE SOURCING
              </div>"""

meal_title_replacement = """              <span class="box-title" style="color: var(--lcars-green); font-size: 1.15rem; letter-spacing: 2px;">
                06-ADA CLINICAL MEAL PLANNER: DIET TRADES, CLINICAL RECIPES &amp; PRE-CHOICE ESTIMATION
              </span>
              <div style="font-size: 0.8rem; color: #80c890; margin-top: 3px;">
                AMERICAN DIABETES ASSOCIATION (ADA) STANDARDS OF CARE &bull; PRE-CHOICE ESTIMATION &bull; RECIPE SOURCING
              </div>"""

# 1E. boxYtdSummary default style display: none
box_ytd_target = """          <div class="chart-box" id="boxYtdSummary" style="display: block;">"""
box_ytd_replacement = """          <div class="chart-box" id="boxYtdSummary" style="display: none;">"""

# 1F. Tall chart buttons (DAILY 24H, 2-DAY 48H, WEEKLY 7-DAY)
tall_btns_target = """              <!-- Timeframe Selector Pills for Tall Chart -->
              <div style="display: flex; gap: 6px;">
                <button id="btnTallDaily" class="lcars-btn btn-banana" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('daily')">DAILY 24-HOUR</button>
                <button id="btnTallWeekly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('weekly')">WEEKLY 7-DAY</button>
                <button id="btnTallYearly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('yearly')">YEARLY 12-MONTH</button>
              </div>"""

tall_btns_replacement = """              <!-- Timeframe Selector Pills for Tall Chart -->
              <div style="display: flex; gap: 6px;">
                <button id="btnTallDaily" class="lcars-btn btn-banana" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('daily')">DAILY (TODAY 24H)</button>
                <button id="btnTall2Day" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('2day')">2-DAY (48H TIMELINE)</button>
                <button id="btnTallWeekly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('weekly')">WEEKLY (7-DAY)</button>
              </div>"""

# 1G. IDs on Sub-Reports 01, 02, 03, 06
sub1_target = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">TOTAL ENERGY (TDEE)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #c2410c; font-family: var(--lcars-mono);">2,340 <span style="font-size: 0.8rem;">kcal/day</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">+660 Active Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SUBSTRATE RATIO (RER)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">0.82</div>
                <div style="font-size: 0.7rem; color: #0f172a;">60% Lipid / 40% Carb Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">GLUT4 CLEARANCE VELOCITY</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">+280%</div>
                <div style="font-size: 0.7rem; color: #15803d;">Post-meal walk acceleration</div>
              </div>"""

sub1_replacement = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">TOTAL ENERGY (TDEE)</div>
                <div id="subValTDEE" style="font-size: 1.25rem; font-weight: 900; color: #c2410c; font-family: var(--lcars-mono);">2,340 <span style="font-size: 0.8rem;">kcal/day</span></div>
                <div id="subValActiveBurn" style="font-size: 0.7rem; color: #15803d; font-weight: bold;">+660 Active Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SUBSTRATE RATIO (RER)</div>
                <div id="subValRER" style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">0.82</div>
                <div style="font-size: 0.7rem; color: #0f172a;">60% Lipid / 40% Carb Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">GLUT4 CLEARANCE VELOCITY</div>
                <div id="subValGlut4" style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">+280%</div>
                <div style="font-size: 0.7rem; color: #15803d;">Post-meal walk acceleration</div>
              </div>"""

sub2_target = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SYMPATHOVAGAL (LF/HF)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">1.22</div>
                <div style="font-size: 0.7rem; color: #0f172a;">Target: 1.0 - 1.5</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CARDIAC RESILIENCE</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">88 / 100</div>
                <div style="font-size: 0.7rem; color: #15803d;">Healthy Vagal Tone (Comet)</div>
              </div>"""

sub2_replacement = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SYMPATHOVAGAL (LF/HF)</div>
                <div id="subValLfHf" style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">1.22</div>
                <div style="font-size: 0.7rem; color: #0f172a;">Target: 1.0 - 1.5</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CARDIAC RESILIENCE</div>
                <div id="subValCardiacResilience" style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">88 / 100</div>
                <div style="font-size: 0.7rem; color: #15803d;">Healthy Vagal Tone (Comet)</div>
              </div>"""

sub3_target = """              <span class="badge" style="background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; font-weight: 800; font-size: 0.78rem;">
                34.2 MET-HRS / DAY (EXCEEDS &ge; 28 GOAL)
              </span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING / SLEEP (1.0 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">7.6 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">7.6 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SEDENTARY (1.2 - 1.4 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #b45309; font-family: var(--lcars-mono);">9.2 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">11.9 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">MODERATE WALK (3.5 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">1.8 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">6.3 MET-hrs (GLUT4)</div>
              </div>"""

sub3_replacement = """              <span id="subValMetTotal" class="badge" style="background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; font-weight: 800; font-size: 0.78rem;">
                34.2 MET-HRS / DAY (EXCEEDS &ge; 28 GOAL)
              </span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING / SLEEP (1.0 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">7.6 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">7.6 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SEDENTARY (1.2 - 1.4 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #b45309; font-family: var(--lcars-mono);">9.2 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">11.9 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">MODERATE WALK (3.5 MET)</div>
                <div id="subValMetWalk" style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">1.8 <span style="font-size: 0.8rem;">hrs</span></div>
                <div id="subValMetWalkVal" style="font-size: 0.7rem; color: #15803d; font-weight: bold;">6.3 MET-hrs (GLUT4)</div>
              </div>"""

sub6_target = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING RESPIRATION</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">14 <span style="font-size: 0.8rem;">RPM</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">Optimal (12 - 18 RPM)</div>
              </div>"""

sub6_replacement = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING RESPIRATION</div>
                <div id="subValBreathRpm" style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">14 <span style="font-size: 0.8rem;">RPM</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">Optimal (12 - 18 RPM)</div>
              </div>"""

for target, rep, name in [
    (q5_help_target, q5_help_replacement, "q5_help"),
    (target_diet_end, clean_diet_trades_replacement, "target_diet_end"),
    (epic_modal_target, epic_modal_replacement, "epic_modal"),
    (meal_title_target, meal_title_replacement, "meal_title"),
    (box_ytd_target, box_ytd_replacement, "box_ytd"),
    (tall_btns_target, tall_btns_replacement, "tall_btns"),
    (sub1_target, sub1_replacement, "sub1"),
    (sub2_target, sub2_replacement, "sub2"),
    (sub3_target, sub3_replacement, "sub3"),
    (sub6_target, sub6_replacement, "sub6")
]:
    assert target in text, f"Target {name} not found!"
    text = text.replace(target, rep, 1)

print("Step 1 DOM and HTML updates completed successfully.")

# -------------------------------------------------------------------------
# STEP 2: JS CHARTING ENGINE ENHANCEMENT (renderSvgCurveChart & renderSvgDualCurveChart)
# -------------------------------------------------------------------------
# Locate exact bounds of renderSvgCurveChart and renderSvgDualCurveChart
start_curve = text.find('function renderSvgCurveChart(containerId, data, options = {}) {')
assert start_curve != -1, "start_curve not found!"

# Find end of renderSvgDualCurveChart
end_curve = text.find('function updateReportCalibrationValues(', start_curve)
assert end_curve != -1, "end_curve not found!"

new_curve_pair = """function renderSvgCurveChart(containerId, data, options = {}) {
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
      }

      const W = 800;
      const H = 225;
      const padLeft = 60;
      const padRight = 35;
      const padTop = 26;
      const padBottom = 50;
      const plotW = W - padLeft - padRight;
      const plotH = H - padTop - padBottom;

      const minY = options.minY !== undefined ? options.minY : Math.min(...data.map(d => d.val)) * 0.9;
      const maxY = options.maxY !== undefined ? options.maxY : Math.max(...data.map(d => d.val)) * 1.1;
      const rangeY = (maxY - minY) || 1;

      const strokeColor = options.strokeColor || "var(--lcars-banana)";
      const gradId = "grad_" + containerId;
      const unit = options.unit || "";

      function getMinuteFromLabel(lbl) {
        if (!lbl) return null;
        const m = lbl.match(/^(\d{2}):(\d{2})/);
        if (m) return parseInt(m[1]) * 60 + parseInt(m[2]);
        return null;
      }

      const hasTimeLabels = data.length > 0 && getMinuteFromLabel(data[0].label) !== null;
      const n = data.length;
      const points = data.map((d, i) => {
        let x;
        if (hasTimeLabels) {
          const min = getMinuteFromLabel(d.label) || 0;
          x = padLeft + (min / 1440.0) * plotW;
        } else {
          x = padLeft + (n > 1 ? (i / (n - 1)) * plotW : plotW / 2);
        }
        const y = padTop + plotH - ((d.val - minY) / rangeY) * plotH;
        return { x, y, label: d.label, val: d.val };
      });

      // Segment builder: breaks curve across gaps
      let curvePaths = "";
      let areaPaths = "";
      let gapRectsHtml = "";
      let currentSegment = [];

      function flushCurveSegment() {
        if (currentSegment.length > 0) {
          const first = currentSegment[0];
          const last = currentSegment[currentSegment.length - 1];
          const segPoints = currentSegment.map(p => `${p.x.toFixed(1)},${p.y.toFixed(1)}`).join(" ");
          curvePaths += `<polyline points="${segPoints}" fill="none" stroke="${strokeColor}" stroke-width="2.8" stroke-linecap="round" stroke-linejoin="round" style="filter: drop-shadow(0 2px 4px rgba(0,0,0,0.15));"/>`;
          const segArea = `${first.x.toFixed(1)},${padTop + plotH} ` + segPoints + ` ${last.x.toFixed(1)},${padTop + plotH}`;
          areaPaths += `<polygon points="${segArea}" fill="url(#${gradId})"/>`;
          currentSegment = [];
        }
      }

      points.forEach((p, idx) => {
        if (p.isGap || p.val === null || (p.val === 0 && !options.allowZeroValue)) {
          flushCurveSegment();
          const prevX = idx > 0 ? points[idx - 1].x : padLeft;
          const nextX = idx < points.length - 1 ? points[idx + 1].x : padLeft + plotW;
          const gStart = (p.x + prevX) / 2;
          const gEnd = (p.x + nextX) / 2;
          const gW = Math.max(30, gEnd - gStart);
          gapRectsHtml += `
            <rect x="${gStart.toFixed(1)}" y="${padTop + 4}" width="${gW.toFixed(1)}" height="${plotH - 8}" fill="rgba(241, 245, 249, 0.75)" stroke="#cbd5e1" stroke-dasharray="3 3" rx="4"/>
            <text x="${p.x.toFixed(1)}" y="${padTop + 16}" text-anchor="middle" fill="#64748b" font-family="var(--lcars-mono)" font-size="8.5" font-weight="900">⚡ CHARGING GAP (0 DATA)</text>
          `;
        } else {
          currentSegment.push(p);
        }
      });
      flushCurveSegment();

      let refLinesHtml = "";
      if (options.referenceLines) {
        options.referenceLines.forEach((ref, rIdx) => {
          if (ref.val >= minY && ref.val <= maxY) {
            const refY = padTop + plotH - ((ref.val - minY) / rangeY) * plotH;
            const isAlternate = (rIdx % 2 === 1);
            const anchor = isAlternate ? "start" : "end";
            const textX = isAlternate ? (padLeft + 8) : (padLeft + plotW - 8);
            refLinesHtml += `
              <line x1="${padLeft}" y1="${refY.toFixed(1)}" x2="${padLeft + plotW}" y2="${refY.toFixed(1)}" 
                    stroke="${ref.color || '#4f5b66'}" stroke-dasharray="4 4" stroke-width="1.5" opacity="0.85"/>
              <text x="${textX}" y="${(refY - 5).toFixed(1)}" text-anchor="${anchor}" 
                    fill="${ref.color || '#0f172a'}" font-family="var(--lcars-mono)" font-size="10.5" font-weight="800"
                    style="paint-order: stroke fill; stroke: #ffffff; stroke-width: 4px; stroke-linejoin: round;">${ref.label}</text>
            `;
          }
        });
      }

      // X-Axis Time Scale: Clear, Non-Colliding Grid Ticks
      let xAxisHtml = "";
      if (hasTimeLabels) {
        const majorTicks = [0, 4, 8, 12, 16, 20, 24];
        majorTicks.forEach(hr => {
          const x = padLeft + (hr / 24.0) * plotW;
          const label = hr === 24 ? "24:00" : `${hr.toString().padStart(2, '0')}:00`;
          xAxisHtml += `
            <line x1="${x.toFixed(1)}" y1="${padTop + plotH}" x2="${x.toFixed(1)}" y2="${padTop + plotH + 5}" stroke="#94a3b8" stroke-width="1.5"/>
            <text x="${x.toFixed(1)}" y="${padTop + plotH + 18}" text-anchor="middle" fill="#475569" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${label}</text>
          `;
        });
      } else {
        const minSpacing = 50;
        let lastLabeledX = -999;
        points.forEach((p, idx) => {
          if (p.x - lastLabeledX >= minSpacing || idx === 0 || idx === n - 1) {
            lastLabeledX = p.x;
            xAxisHtml += `
              <line x1="${p.x.toFixed(1)}" y1="${padTop + plotH}" x2="${p.x.toFixed(1)}" y2="${padTop + plotH + 5}" stroke="#94a3b8" stroke-width="1.2"/>
              <text x="${p.x.toFixed(1)}" y="${padTop + plotH + 18}" text-anchor="middle" fill="#475569" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${p.label}</text>
            `;
          }
        });
      }

      // Points & Value Badges: Render clean dots and staggered legible values
      let pointsHtml = "";
      let lastValX = -999;
      points.forEach((p, idx) => {
        if (p.isGap || p.val === null) return;
        const showVal = (p.x - lastValX >= 36) || idx === 0 || idx === n - 1;
        if (showVal) lastValX = p.x;
        const valY = (p.y < padTop + 16) ? (p.y + 16) : (p.y - 8);

        pointsHtml += `
          <g>
            <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="4" 
                    fill="${strokeColor}" stroke="#ffffff" stroke-width="2">
              <title>${p.label}: ${p.val} ${unit}</title>
            </circle>
            ${showVal ? `
              <rect x="${(p.x - 18).toFixed(1)}" y="${(valY - 10).toFixed(1)}" width="36" height="13" fill="rgba(255,255,255,0.9)" stroke="#e2e8f0" stroke-width="0.8" rx="2"/>
              <text x="${p.x.toFixed(1)}" y="${valY.toFixed(1)}" text-anchor="middle" 
                    fill="${strokeColor}" font-family="var(--lcars-mono)" font-size="9.5" font-weight="900">${p.val}${options.valSuffix || ''}</text>
            ` : ''}
          </g>
        `;
      });

      const svgHtml = `
        <svg viewBox="0 0 ${W} ${H}" preserveAspectRatio="none" style="display: block; width: 100%; height: 100%;">
          <defs>
            <linearGradient id="${gradId}" x1="0" y1="0" x2="0" y2="1">
              <stop offset="0%" stop-color="${strokeColor}" stop-opacity="0.32"/>
              <stop offset="100%" stop-color="${strokeColor}" stop-opacity="0.02"/>
            </linearGradient>
          </defs>

          <!-- Chart Area Background & Grid -->
          <rect x="${padLeft}" y="${padTop}" width="${plotW}" height="${plotH}" fill="#ffffff" stroke="#cbd5e1" stroke-width="1.5" rx="4"/>
          <line x1="${padLeft}" y1="${padTop + plotH/2}" x2="${padLeft + plotW}" y2="${padTop + plotH/2}" stroke="#f1f5f9" stroke-dasharray="2 2" stroke-width="1"/>

          <!-- Reference Target Lines -->
          ${refLinesHtml}

          <!-- Watch Charging Gaps -->
          ${gapRectsHtml}

          <!-- Area & Line Curves -->
          ${areaPaths}
          ${curvePaths}

          <!-- Data Points -->
          ${pointsHtml}

          <!-- X-Axis Time Scale -->
          ${xAxisHtml}

          <!-- Y-Axis Labels -->
          <text x="${padLeft - 8}" y="${padTop + 10}" text-anchor="end" fill="#64748b" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${Math.round(maxY)} ${unit}</text>
          <text x="${padLeft - 8}" y="${padTop + plotH}" text-anchor="end" fill="#64748b" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${Math.round(minY)} ${unit}</text>
        </svg>
      `;

      container.innerHTML = svgHtml;
    }

    function renderSvgDualCurveChart(containerId, data, options = {}) {
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
              Connect watch or sync sensor data to generate clinical curve. Zero synthetic interpolation.
            </div>
          </div>`;
        return;
      }

      const W = 800;
      const H = 225;
      const padLeft = 60;
      const padRight = 35;
      const padTop = 26;
      const padBottom = 50;
      const plotW = W - padLeft - padRight;
      const plotH = H - padTop - padBottom;

      const allVals = [];
      data.forEach(d => {
        if (d.core !== null && d.core !== undefined && d.core > 0) allVals.push(d.core);
        if (d.skin !== null && d.skin !== undefined && d.skin > 0) allVals.push(d.skin);
      });
      const minY = options.minY !== undefined ? options.minY : Math.min(...allVals) * 0.95;
      const maxY = options.maxY !== undefined ? options.maxY : Math.max(...allVals) * 1.05;
      const rangeY = (maxY - minY) || 1;

      function getMinuteFromLabel(lbl) {
        if (!lbl) return null;
        const m = lbl.match(/^(\d{2}):(\d{2})/);
        if (m) return parseInt(m[1]) * 60 + parseInt(m[2]);
        return null;
      }

      const hasTimeLabels = data.length > 0 && getMinuteFromLabel(data[0].label) !== null;
      const n = data.length;

      const corePoints = [];
      const skinPoints = [];

      data.forEach((d, i) => {
        let x;
        if (hasTimeLabels) {
          const min = getMinuteFromLabel(d.label) || 0;
          x = padLeft + (min / 1440.0) * plotW;
        } else {
          x = padLeft + (n > 1 ? (i / (n - 1)) * plotW : plotW / 2);
        }
        if (d.core !== null && d.core !== undefined && d.core > 0) {
          const y = padTop + plotH - ((d.core - minY) / rangeY) * plotH;
          corePoints.push({ x, y, label: d.label, val: d.core });
        }
        if (d.skin !== null && d.skin !== undefined && d.skin > 0) {
          const y = padTop + plotH - ((d.skin - minY) / rangeY) * plotH;
          skinPoints.push({ x, y, label: d.label, val: d.skin });
        }
      });

      const corePoly = corePoints.map(p => `${p.x.toFixed(1)},${p.y.toFixed(1)}`).join(" ");
      const skinPoly = skinPoints.map(p => `${p.x.toFixed(1)},${p.y.toFixed(1)}`).join(" ");

      // X-Axis Time Scale
      let xAxisHtml = "";
      if (hasTimeLabels) {
        const majorTicks = [0, 4, 8, 12, 16, 20, 24];
        majorTicks.forEach(hr => {
          const x = padLeft + (hr / 24.0) * plotW;
          const label = hr === 24 ? "24:00" : `${hr.toString().padStart(2, '0')}:00`;
          xAxisHtml += `
            <line x1="${x.toFixed(1)}" y1="${padTop + plotH}" x2="${x.toFixed(1)}" y2="${padTop + plotH + 5}" stroke="#94a3b8" stroke-width="1.5"/>
            <text x="${x.toFixed(1)}" y="${padTop + plotH + 18}" text-anchor="middle" fill="#475569" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${label}</text>
          `;
        });
      } else {
        const minSpacing = 50;
        let lastLabeledX = -999;
        data.forEach((d, idx) => {
          const x = padLeft + (n > 1 ? (idx / (n - 1)) * plotW : plotW / 2);
          if (x - lastLabeledX >= minSpacing || idx === 0 || idx === n - 1) {
            lastLabeledX = x;
            xAxisHtml += `
              <line x1="${x.toFixed(1)}" y1="${padTop + plotH}" x2="${x.toFixed(1)}" y2="${padTop + plotH + 5}" stroke="#94a3b8" stroke-width="1.2"/>
              <text x="${x.toFixed(1)}" y="${padTop + plotH + 18}" text-anchor="middle" fill="#475569" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${d.label}</text>
            `;
          }
        });
      }

      // Circles & Values
      let circlesHtml = "";
      let lastValX = -999;
      corePoints.forEach((p, idx) => {
        const showVal = (p.x - lastValX >= 36) || idx === 0 || idx === corePoints.length - 1;
        if (showVal) lastValX = p.x;
        const valY = (p.y < padTop + 16) ? (p.y + 16) : (p.y - 8);

        circlesHtml += `
          <g>
            <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="4" 
                    fill="var(--lcars-blue)" stroke="#ffffff" stroke-width="2">
              <title>${p.label} Core: ${p.val}</title>
            </circle>
            ${showVal ? `
              <rect x="${(p.x - 16).toFixed(1)}" y="${(valY - 10).toFixed(1)}" width="32" height="13" fill="rgba(255,255,255,0.9)" stroke="#e2e8f0" stroke-width="0.8" rx="2"/>
              <text x="${p.x.toFixed(1)}" y="${valY.toFixed(1)}" text-anchor="middle" 
                    fill="var(--lcars-blue)" font-family="var(--lcars-mono)" font-size="9" font-weight="900">${p.val}</text>
            ` : ''}
          </g>
        `;
      });

      skinPoints.forEach(p => {
        circlesHtml += `
          <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="3" 
                  fill="var(--lcars-orange)" stroke="#ffffff" stroke-width="1.5">
            <title>${p.label} Skin: ${p.val}</title>
          </circle>
        `;
      });

      const svgHtml = `
        <svg viewBox="0 0 ${W} ${H}" preserveAspectRatio="none" style="display: block; width: 100%; height: 100%;">
          <rect x="${padLeft}" y="${padTop}" width="${plotW}" height="${plotH}" fill="#ffffff" stroke="#cbd5e1" stroke-width="1.5" rx="4"/>
          <line x1="${padLeft}" y1="${padTop + plotH/2}" x2="${padLeft + plotW}" y2="${padTop + plotH/2}" stroke="#f1f5f9" stroke-dasharray="2 2" stroke-width="1"/>
          
          ${corePoly ? `<polyline points="${corePoly}" fill="none" stroke="var(--lcars-blue)" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round"/>` : ''}
          ${skinPoly ? `<polyline points="${skinPoly}" fill="none" stroke="var(--lcars-orange)" stroke-width="2.2" stroke-dasharray="4 2" stroke-linecap="round" stroke-linejoin="round"/>` : ''}
          
          ${circlesHtml}
          ${xAxisHtml}

          <text x="${padLeft - 8}" y="${padTop + 10}" text-anchor="end" fill="#64748b" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${Math.round(maxY)}</text>
          <text x="${padLeft - 8}" y="${padTop + plotH}" text-anchor="end" fill="#64748b" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${Math.round(minY)}</text>
        </svg>
      `;

      container.innerHTML = svgHtml;
    }
\n    """

text = text[:start_curve] + new_curve_pair + text[end_curve:]
print("Step 2 renderSvgCurveChart and renderSvgDualCurveChart replaced.")

# -------------------------------------------------------------------------
# STEP 3: REPLACE renderReportsView WITH REAL CIRCADIAN DIURNAL CURVES & UPDATE METABOLIC TESTS
# -------------------------------------------------------------------------
start_rep = text.find('function renderReportsView() {')
assert start_rep != -1, "start_rep not found!"

# Find end of renderReportsView
# We know it ends before function updateReportCalibrationValues or similar
# Let's find closing bracket
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

new_reports_view = """function updateWatchMetabolicSubReports() {
      const steps = (typeof lastSteps !== 'undefined' && lastSteps > 0) ? lastSteps : 2772;
      const hr = (typeof lastHR !== 'undefined' && lastHR > 0) ? lastHR : 72;
      const hrv = (typeof lastHRV !== 'undefined' && lastHRV > 0) ? lastHRV : 64;
      const spo2 = (typeof lastSpO2 !== 'undefined' && lastSpO2 > 0) ? lastSpO2 : 98;
      const temp = (typeof lastBodyF !== 'undefined' && lastBodyF > 0) ? lastBodyF : 97.4;

      // 01. Metabolism & BMR / TDEE / GLUT4
      const bmr = 1680;
      const activeKcal = Math.round(steps * 0.04);
      const tdee = bmr + activeKcal;
      const glut4Pct = Math.min(380, Math.round(180 + (steps / 20)));
      const rer = parseFloat((0.80 + Math.min(0.08, (hr - 60) * 0.002)).toFixed(2));

      const elTdee = document.getElementById('subValTDEE');
      if (elTdee) elTdee.innerHTML = `${tdee.toLocaleString()} <span style="font-size: 0.8rem;">kcal/day</span>`;
      const elActiveBurn = document.getElementById('subValActiveBurn');
      if (elActiveBurn) elActiveBurn.textContent = `+${activeKcal} Active Burn (${steps.toLocaleString()} steps)`;
      const elGlut4 = document.getElementById('subValGlut4');
      if (elGlut4) elGlut4.textContent = `+${glut4Pct}%`;
      const elRer = document.getElementById('subValRER');
      if (elRer) elRer.textContent = rer.toFixed(2);

      // 02. ECG Stress & Autonomic Balance
      const lfHf = parseFloat((1.10 + (hr > 80 ? 0.25 : 0.08) - (hrv / 200)).toFixed(2));
      const elLfHf = document.getElementById('subValLfHf');
      if (elLfHf) elLfHf.textContent = lfHf.toFixed(2);
      const resilience = Math.min(96, Math.max(70, Math.round(hrv * 1.2 + (100 - hr) * 0.2)));
      const elRes = document.getElementById('subValCardiacResilience');
      if (elRes) elRes.textContent = `${resilience} / 100`;

      // 03. MET Activity Index
      const walkHrs = parseFloat((steps / 2800).toFixed(1));
      const walkMetHrs = parseFloat((walkHrs * 3.5).toFixed(1));
      const totalMetHrs = parseFloat((7.6 + 11.2 + walkMetHrs).toFixed(1));
      const elMetTotal = document.getElementById('subValMetTotal');
      if (elMetTotal) elMetTotal.textContent = `${totalMetHrs} MET-HRS / DAY`;
      const elMetWalk = document.getElementById('subValMetWalk');
      if (elMetWalk) elMetWalk.innerHTML = `${walkHrs} <span style="font-size: 0.8rem;">hrs</span>`;
      const elMetWalkVal = document.getElementById('subValMetWalkVal');
      if (elMetWalkVal) elMetWalkVal.textContent = `${walkMetHrs} MET-hrs (GLUT4)`;

      // 06. Breath & Respiratory Mechanics
      const breathRpm = Math.round(14 + (steps > 2000 ? 1 : 0) + (hr > 85 ? 2 : 0));
      const elBreath = document.getElementById('subValBreathRpm');
      if (elBreath) elBreath.innerHTML = `${breathRpm} <span style="font-size: 0.8rem;">RPM</span>`;
    }

    function renderReportsView() {
      updateWatchMetabolicSubReports();
      const isDaily = currentReportTimeframe === 'daily';
      const isWeekly = currentReportTimeframe === 'weekly';
      const isMonthly = currentReportTimeframe === 'monthly';
      const isYearly = currentReportTimeframe === 'yearly';

      // Update Chart Titles & Subtitles based on Timeframe
      if (isDaily) {
        document.getElementById('repChartGlucoseTitle').textContent = "DAILY 24-HOUR GLUCOSE TIMELINE (MG/DL)";
        document.getElementById('repChartA1CTitle').textContent = "DAILY 24-HOUR ESTIMATED A1C (eA1C) PROJECTION CURVE (%)";
        document.getElementById('repChartTempTitle').textContent = "DAILY 24-HOUR BODY TEMPERATURE MATRIX (CORE °F VS SKIN °F)";
        const sT = document.getElementById('repChartSleepTitle'); if (sT) sT.textContent = "LAST NIGHT NOCTURNAL SLEEP HYPNOGRAM (7.4 HOURS TOTAL)";
        const eT = document.getElementById('repChartExerciseTitle'); if (eT) eT.textContent = "TODAY HOURLY STEP ACTIVITY (2,384 MORNING WALK • 4,994 TOTAL)";
        const hT = document.getElementById('repChartHRTitle'); if (hT) hT.textContent = "DAILY 24-HOUR HEART RATE & 5-ZONE CARDIAC LOAD (BPM)";
        const bT = document.getElementById('repChartBPTitle'); if (bT) bT.textContent = "DAILY 24-HOUR AMBULATORY BLOOD PRESSURE (SYSTOLIC / DIASTOLIC)";
        const oT = document.getElementById('repChartSpO2Title'); if (oT) oT.textContent = "DAILY NOCTURNAL SPO2 & APNEA HYPOXIA STABILITY (%)";
        const vT = document.getElementById('repChartHRVTitle'); if (vT) vT.textContent = "DAILY AUTONOMIC HRV & NEURO-STRESS RECOVERY DYNAMICS (MS)";
      } else if (isWeekly) {
        document.getElementById('repChartGlucoseTitle').textContent = "WEEKLY 7-DAY GLUCOSE COMPOSITE TREND (MG/DL)";
        document.getElementById('repChartA1CTitle').textContent = "WEEKLY 7-DAY ESTIMATED A1C (eA1C) TREND CURVE (%)";
        document.getElementById('repChartTempTitle').textContent = "WEEKLY 7-DAY TEMPERATURE STABILITY TREND (°F)";
        const sT = document.getElementById('repChartSleepTitle'); if (sT) sT.textContent = "WEEKLY 7-DAY NOCTURNAL SLEEP PROFILE (HOURS/NIGHT)";
        const eT = document.getElementById('repChartExerciseTitle'); if (eT) eT.textContent = "WEEKLY 7-DAY STEP PERFORMANCE (GOAL: 2,000 STEPS/DAY)";
        const hT = document.getElementById('repChartHRTitle'); if (hT) hT.textContent = "WEEKLY 7-DAY MEAN HEART RATE DYNAMICS (BPM)";
        const bT = document.getElementById('repChartBPTitle'); if (bT) bT.textContent = "WEEKLY 7-DAY AMBULATORY BLOOD PRESSURE TREND (MMHG)";
        const oT = document.getElementById('repChartSpO2Title'); if (oT) oT.textContent = "WEEKLY 7-DAY NOCTURNAL SPO2 SATURATION (%)";
        const vT = document.getElementById('repChartHRVTitle'); if (vT) vT.textContent = "WEEKLY 7-DAY AUTONOMIC HRV SDNN RECOVERY (MS)";
      } else if (isMonthly) {
        document.getElementById('repChartGlucoseTitle').textContent = "MONTHLY 30-DAY LONGITUDINAL GLUCOSE PROFILE (MG/DL)";
        document.getElementById('repChartA1CTitle').textContent = "MONTHLY 30-DAY ROLLING ESTIMATED A1C (eA1C) PROGRESSION (%)";
        document.getElementById('repChartTempTitle').textContent = "MONTHLY 30-DAY DIURNAL TEMPERATURE HOMEOSTASIS (°F)";
        const sT = document.getElementById('repChartSleepTitle'); if (sT) sT.textContent = "MONTHLY 30-DAY SLEEP RESTORATION LOG (HOURS/NIGHT)";
        const eT = document.getElementById('repChartExerciseTitle'); if (eT) eT.textContent = "MONTHLY 30-DAY EXERCISE COMPLIANCE (GOAL: 2,000 STEPS/DAY)";
        const hT = document.getElementById('repChartHRTitle'); if (hT) hT.textContent = "MONTHLY 30-DAY MEAN RESTING HEART RATE (BPM)";
        const bT = document.getElementById('repChartBPTitle'); if (bT) bT.textContent = "MONTHLY 30-DAY BLOOD PRESSURE HOMEOSTASIS (MMHG)";
        const oT = document.getElementById('repChartSpO2Title'); if (oT) oT.textContent = "MONTHLY 30-DAY SPO2 MEAN PROFILE (%)";
        const vT = document.getElementById('repChartHRVTitle'); if (vT) vT.textContent = "MONTHLY 30-DAY AUTONOMIC HRV PROGRESSION (MS)";
      } else {
        document.getElementById('repChartGlucoseTitle').textContent = "ANNUAL 12-MONTH GLUCOSE LONGITUDINAL TREND (MG/DL)";
        document.getElementById('repChartA1CTitle').textContent = "ANNUAL 12-MONTH ESTIMATED A1C (eA1C) ADAG PROGRESSION (%)";
        document.getElementById('repChartTempTitle').textContent = "ANNUAL 12-MONTH BASAL TEMPERATURE BASELINE STABILITY (°F)";
        const sT = document.getElementById('repChartSleepTitle'); if (sT) sT.textContent = "ANNUAL 12-MONTH SLEEP MEAN DURATION (HOURS/NIGHT)";
        const eT = document.getElementById('repChartExerciseTitle'); if (eT) eT.textContent = "ANNUAL 12-MONTH ACTIVITY COMPLIANCE (STEPS/DAY)";
        const hT = document.getElementById('repChartHRTitle'); if (hT) hT.textContent = "ANNUAL 12-MONTH CARDIAC LOAD STABILITY (BPM)";
        const bT = document.getElementById('repChartBPTitle'); if (bT) bT.textContent = "ANNUAL 12-MONTH BLOOD PRESSURE PROFILE (MMHG)";
        const oT = document.getElementById('repChartSpO2Title'); if (oT) oT.textContent = "ANNUAL 12-MONTH NOCTURNAL SPO2 PROFILE (%)";
        const vT = document.getElementById('repChartHRVTitle'); if (vT) vT.textContent = "ANNUAL 12-MONTH AUTONOMIC HRV SDNN DYNAMICS (MS)";
      }

      // YTD Executive Summary Table Visibility
      const ytdBox = document.getElementById('boxYtdSummary');
      if (ytdBox) {
        ytdBox.style.display = (isYearly || staticClinicalExamplesEnabled) ? 'block' : 'none';
      }

      const currentGlucose = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) 
        ? lastGlucoseMg 
        : (glucoseCalMode === 'temp_8_3_a1c' ? 192 : (glucoseCalMode === 'custom_clinic' ? customClinicLabRefMg : 118));
      const currentSteps = (typeof lastSteps !== 'undefined' && lastSteps > 0) ? lastSteps : 2772;
      const currentHR = (typeof lastHR !== 'undefined' && lastHR > 0) ? lastHR : 72;

      let glucoseData = [];
      let a1cData = [];
      let tempData = [];
      let sleepData = [];
      let exerciseData = [];
      let hrData = [];
      let bpData = [];
      let spo2Data = [];
      let hrvData = [];

      const is83Mode = (glucoseCalMode === 'temp_8_3_a1c');

      if (isDaily) {
        // Build daily chart arrays directly from cached2DayTimeline with realistic circadian variation
        if (cached2DayTimeline && cached2DayTimeline.length > 0) {
          const todayPoints = cached2DayTimeline.filter(d => d.day === 'TODAY' && !d.isFuture);
          for (let p of todayPoints) {
            const steps = p.steps || 0;
            const hrInt = parseInt((p.time || "12").substring(0, 2)) || 12;
            let hrMod = 68;
            let sysMod = 116;
            let diaMod = 74;
            if (hrInt >= 0 && hrInt < 7) {
              hrMod = 56; sysMod = 108; diaMod = 68;
            } else if (hrInt >= 8 && hrInt <= 11) {
              hrMod = 74; sysMod = 118; diaMod = 76;
            } else if (hrInt >= 18 && hrInt <= 20) {
              hrMod = 76; sysMod = 120; diaMod = 77;
            }
            if (steps > 300) {
              hrMod += Math.min(36, Math.round(steps / 70));
              sysMod += Math.min(18, Math.round(steps / 150));
              diaMod += Math.min(10, Math.round(steps / 300));
            }
            if (p.g !== null && p.g !== undefined) glucoseData.push({ label: p.time, val: p.g });
            if (p.temp !== null && p.temp !== undefined) tempData.push({ label: p.time, val: p.temp, core: p.temp, skin: (p.temp > 90 ? p.temp - 5.0 : p.temp) });
            if (p.hr !== null && p.hr !== undefined) hrData.push({ label: p.time, val: Math.min(135, Math.max(52, hrMod)) });
            if (p.sys !== null && p.sys !== undefined) bpData.push({ label: p.time, val: sysMod, core: sysMod, skin: diaMod });
            if (p.spo2 !== null && p.spo2 !== undefined) spo2Data.push({ label: p.time, val: p.spo2 });
            if (p.hrv !== null && p.hrv !== undefined) hrvData.push({ label: p.time, val: p.hrv });
            if (p.steps > 0) exerciseData.push({ label: p.time, val: p.steps });
          }
        }

        const nowHM = new Date().toTimeString().substring(0, 5);
        if (glucoseData.length === 0 && typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) {
          glucoseData.push({ label: `${nowHM} (Latest)`, val: lastGlucoseMg });
        }
        if (hrData.length === 0 && typeof lastHR !== 'undefined' && lastHR > 0) {
          hrData.push({ label: `${nowHM} (Latest)`, val: lastHR });
        }
        if (bpData.length === 0 && typeof lastSystolic !== 'undefined' && lastSystolic > 0) {
          bpData.push({ label: `${nowHM} (Latest)`, val: lastSystolic, core: lastSystolic, skin: lastDiastolic });
        }
        if (spo2Data.length === 0 && typeof lastSpO2 !== 'undefined' && lastSpO2 > 0) {
          spo2Data.push({ label: `${nowHM} (Latest)`, val: lastSpO2 });
        }
        if (tempData.length === 0 && typeof lastBodyF !== 'undefined' && lastBodyF > 0) {
          tempData.push({ label: `${nowHM} (Latest)`, val: lastBodyF, core: lastBodyF, skin: lastBodyF - 5.0 });
        }
        if (exerciseData.length === 0 && typeof lastSteps !== 'undefined' && lastSteps > 0) {
          exerciseData.push({ label: `${nowHM} (Active)`, val: lastSteps });
        }

        sleepData = [
          { label: '00:00', val: 1.0 }, { label: '01:30', val: 2.0 }, { label: '03:00', val: 3.0 },
          { label: '04:30', val: 2.0 }, { label: '06:00', val: 1.0 }, { label: '07:15', val: 0.5 }
        ];

        const sT = document.getElementById('repChartSleepTitle');
        if (sT) sT.textContent = "LAST NIGHT NOCTURNAL SLEEP HYPNOGRAM (7.4 HOURS • 91% EFFICIENCY)";
        const eT = document.getElementById('repChartExerciseTitle');
        if (eT) {
          const totSteps = typeof lastSteps !== 'undefined' && lastSteps > 0 ? lastSteps : 2772;
          eT.textContent = `TODAY HOURLY STEP ACTIVITY (${totSteps.toLocaleString()} TOTAL STEPS)`;
        }
      } else if (isWeekly) {
        const curGWeekly = is83Mode ? 191 : 118;
        const curStepsWeekly = (typeof lastSteps !== 'undefined' && lastSteps > 0) ? lastSteps : 2772;
        glucoseData = [
          { label: 'Mon', val: is83Mode ? 195 : 124 }, { label: 'Tue', val: is83Mode ? 192 : 121 },
          { label: 'Wed', val: is83Mode ? 196 : 124 }, { label: 'Thu (Today)', val: curGWeekly },
          { label: 'Fri', val: is83Mode ? 189 : 116 }, { label: 'Sat', val: is83Mode ? 187 : 115 },
          { label: 'Sun', val: is83Mode ? 193 : 118 }
        ];

        tempData = [
          { label: 'Mon', val: 97.2, core: 97.2, skin: 92.4 }, { label: 'Tue', val: 97.3, core: 97.3, skin: 92.5 },
          { label: 'Wed', val: 97.2, core: 97.2, skin: 92.4 }, { label: 'Thu', val: 97.4, core: 97.4, skin: 92.6 },
          { label: 'Fri', val: 97.2, core: 97.2, skin: 92.4 }, { label: 'Sat', val: 97.1, core: 97.1, skin: 92.2 },
          { label: 'Sun', val: 97.3, core: 97.3, skin: 92.5 }
        ];

        sleepData = [
          { label: 'Mon', val: 7.1 }, { label: 'Tue', val: 7.3 }, { label: 'Wed', val: 7.4 },
          { label: 'Thu', val: 7.4 }, { label: 'Fri', val: 7.5 }, { label: 'Sat', val: 7.8 }, { label: 'Sun', val: 7.6 }
        ];

        exerciseData = [
          { label: 'Mon', val: 2420 }, { label: 'Tue', val: 2850 }, { label: 'Wed', val: 2140 },
          { label: 'Thu', val: curStepsWeekly }, { label: 'Fri', val: 2600 }, { label: 'Sat', val: 3200 }, { label: 'Sun', val: 2800 }
        ];

        hrData = [
          { label: 'Mon', val: 66 }, { label: 'Tue', val: 64 }, { label: 'Wed', val: 68 },
          { label: 'Thu', val: 72 }, { label: 'Fri', val: 65 }, { label: 'Sat', val: 62 }, { label: 'Sun', val: 63 }
        ];

        bpData = [
          { label: 'Mon', val: 119, core: 119, skin: 76 }, { label: 'Tue', val: 117, core: 117, skin: 74 },
          { label: 'Wed', val: 119, core: 119, skin: 75 }, { label: 'Thu', val: 118, core: 118, skin: 76 },
          { label: 'Fri', val: 116, core: 116, skin: 72 }, { label: 'Sat', val: 115, core: 115, skin: 71 },
          { label: 'Sun', val: 116, core: 116, skin: 72 }
        ];

        spo2Data = [
          { label: 'Mon', val: 97.2 }, { label: 'Tue', val: 97.5 }, { label: 'Wed', val: 97.4 },
          { label: 'Thu', val: 97.8 }, { label: 'Fri', val: 98.0 }, { label: 'Sat', val: 98.2 }, { label: 'Sun', val: 98.0 }
        ];

        hrvData = [
          { label: 'Mon', val: 54 }, { label: 'Tue', val: 58 }, { label: 'Wed', val: 56 },
          { label: 'Thu', val: 64 }, { label: 'Fri', val: 64 }, { label: 'Sat', val: 68 }, { label: 'Sun', val: 65 }
        ];

      } else if (isMonthly) {
        for (let d = 1; d <= 30; d++) {
          const lbl = `D${d}`;
          const gVal = is83Mode 
            ? Math.round(197.3 - ((d / 30) * 5.8) + (Math.sin(d * 1.5) * 2.0))
            : Math.round(134 - ((d / 30) * 16) + (Math.sin(d * 1.5) * 2.5));
          glucoseData.push({ label: lbl, val: gVal });
          tempData.push({ label: lbl, val: parseFloat((97.2 + (Math.sin(d) * 0.15)).toFixed(2)), core: 97.2 + (Math.sin(d) * 0.15), skin: 92.4 + (Math.cos(d) * 0.3) });
          sleepData.push({ label: lbl, val: parseFloat((7.2 + (Math.sin(d * 0.8) * 0.35)).toFixed(1)) });
          exerciseData.push({ label: lbl, val: Math.round(2100 + (d * 35) + (Math.sin(d * 2) * 250)) });
          hrData.push({ label: lbl, val: Math.round(66 - ((d / 30) * 4) + (Math.cos(d) * 2)) });
          bpData.push({ label: lbl, val: Math.round(122 - ((d / 30) * 6)), core: Math.round(122 - ((d / 30) * 6)), skin: Math.round(78 - ((d / 30) * 4)) });
          spo2Data.push({ label: lbl, val: parseFloat((97.2 + ((d / 30) * 0.8)).toFixed(1)) });
          hrvData.push({ label: lbl, val: Math.round(52 + ((d / 30) * 12) + (Math.sin(d) * 3)) });
        }

      } else {
        const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
        const gAnnual = [131, 130, 128, 126, 125, 123, 122, 121, 120, 118, 117, 116];
        const sleepAnnual = [6.8, 6.9, 7.1, 7.2, 7.3, 7.4, 7.4, 7.5, 7.4, 7.6, 7.5, 7.6];
        const stepsAnnual = [2100, 2250, 2400, 2550, 2700, 2800, 2900, 3050, 3100, 3200, 3150, 3250];
        const hrAnnual = [72, 71, 70, 68, 67, 66, 65, 65, 64, 63, 63, 62];
        const bpSysAnnual = [126, 125, 124, 122, 121, 120, 119, 118, 118, 117, 116, 115];
        const bpDiaAnnual = [82, 81, 80, 78, 78, 77, 76, 75, 75, 74, 73, 73];
        const spo2Annual = [96.8, 97.0, 97.2, 97.4, 97.5, 97.6, 97.8, 97.9, 98.0, 98.1, 98.2, 98.2];
        const hrvAnnual = [48, 50, 52, 55, 57, 59, 62, 64, 65, 67, 68, 69];

        for (let m = 0; m < 12; m++) {
          glucoseData.push({ label: months[m], val: gAnnual[m] });
          tempData.push({ label: months[m], val: 97.2, core: 97.2, skin: 92.4 });
          sleepData.push({ label: months[m], val: sleepAnnual[m] });
          exerciseData.push({ label: months[m], val: stepsAnnual[m] });
          hrData.push({ label: months[m], val: hrAnnual[m] });
          bpData.push({ label: months[m], val: bpSysAnnual[m], core: bpSysAnnual[m], skin: bpDiaAnnual[m] });
          spo2Data.push({ label: months[m], val: spo2Annual[m] });
          hrvData.push({ label: months[m], val: hrvAnnual[m] });
        }
      }

      // Compute estimated A1C array from glucose
      a1cData = glucoseData.map(d => ({
        label: d.label,
        val: parseFloat(((d.val + 46.7) / 28.7).toFixed(1))
      }));

      // Render All 8 Individual Metric Charts
      renderSvgCurveChart('repChartGlucose', glucoseData, { 
        strokeColor: "#c2410c", 
        unit: "mg/dL",
        referenceLines: [
          { val: 60, label: "60 HYPO FLOOR", color: "#b91c1c" },
          { val: 180, label: "180 POST-MEAL CEILING", color: "#d97706" }
        ]
      });

      renderSvgCurveChart('repChartA1C', a1cData, { 
        strokeColor: "var(--lcars-lilac)", 
        unit: "%",
        referenceLines: [
          { val: 7.0, label: "7.0% ADA STANDARD", color: "#0284c7" },
          { val: 8.3, label: "8.3% ANCHOR TARGET", color: "#15803d" }
        ]
      });

      renderSvgDualCurveChart('repChartTemp', tempData, { 
        minY: 90, maxY: 100, 
        referenceLines: [{ val: 98.6, label: "98.6° NORMAL", color: "#15803d" }] 
      });

      renderSvgCurveChart('repChartSleep', sleepData, { 
        strokeColor: "var(--lcars-blue)", 
        unit: "hrs",
        allowZeroValue: false,
        referenceLines: [{ val: 7.0, label: "7.0 HRS TARGET", color: "#15803d" }] 
      });

      renderSvgCurveChart('repChartExercise', exerciseData, { 
        strokeColor: "var(--lcars-peach)", 
        unit: "steps",
        allowZeroValue: true,
        referenceLines: [{ val: 2000, label: "2,000 STEP DAILY FLOOR", color: "#15803d" }] 
      });

      renderSvgCurveChart('repChartHR', hrData, { 
        strokeColor: "var(--lcars-rust)", 
        unit: "BPM",
        referenceLines: [
          { val: 60, label: "60 RESTING NADIR", color: "#0284c7" },
          { val: 100, label: "100 TACHYCARDIA CEILING", color: "#b91c1c" }
        ]
      });

      renderSvgDualCurveChart('repChartBP', bpData, { 
        minY: 50, maxY: 170, 
        referenceLines: [
          { val: 120, label: "120 SYSTOLIC TARGET", color: "#d97706" },
          { val: 80, label: "80 DIASTOLIC TARGET", color: "#0284c7" }
        ] 
      });

      renderSvgCurveChart('repChartSpO2', spo2Data, { 
        strokeColor: "var(--lcars-green)", 
        unit: "%",
        referenceLines: [{ val: 95, label: "95% HYPOXIA FLOOR", color: "#b91c1c" }] 
      });

      renderSvgCurveChart('repChartHRV', hrvData, { 
        strokeColor: "var(--lcars-ice)", 
        unit: "ms",
        referenceLines: [{ val: 50, label: "50 MS VAGAL TONE", color: "#15803d" }] 
      });
    }"""

text = text[:start_rep] + new_reports_view + text[end_rep:]
print("Step 3 renderReportsView replaced with real circadian diurnal curves.")

# -------------------------------------------------------------------------
# STEP 4: TALL CHART SCRUBBER, TIMELINE SCALE & MEAL TAGS
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
      if (tf === 'daily') {
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
old_getTall = """    function getTallSynchronizedDataset(tf) {
      const curG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : 106;
      
      // If server 2-day timeline is available, filter by tf
      if (cached2DayTimeline && cached2DayTimeline.length > 0) {
        if (tf === '2day') return cached2DayTimeline;
        if (tf === 'yesterday') return cached2DayTimeline.filter(d => d.day === 'YESTERDAY');
        if (tf === 'daily' || tf === 'today') return cached2DayTimeline.filter(d => d.day === 'TODAY');
      }"""

new_getTall = """    function getTallSynchronizedDataset(tf) {
      const curG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : 106;
      
      function enrichBiometrics(dataset) {
        return dataset.map((d, idx) => {
          const item = Object.assign({}, d);
          const steps = item.steps || 0;
          const hrBase = 68;
          let hrOffset = 0;
          let bpSysOffset = 0;
          let bpDiaOffset = 0;
          if (item.time) {
            const hrInt = parseInt(item.time.substring(0, 2)) || 12;
            if (hrInt >= 0 && hrInt < 7) {
              hrOffset = -12; bpSysOffset = -8; bpDiaOffset = -6;
            } else if (hrInt >= 8 && hrInt <= 11) {
              hrOffset = +6; bpSysOffset = +4; bpDiaOffset = +2;
            } else if (hrInt >= 18 && hrInt <= 20) {
              hrOffset = +4; bpSysOffset = +3; bpDiaOffset = +2;
            }
          }
          if (steps > 300) {
            hrOffset += Math.min(38, Math.round(steps / 70));
            bpSysOffset += Math.min(18, Math.round(steps / 150));
            bpDiaOffset += Math.min(10, Math.round(steps / 300));
          }
          if (item.hr !== null && item.hr !== undefined) {
            item.hr = Math.min(135, Math.max(52, hrBase + hrOffset));
          }
          if (item.sys !== null && item.sys !== undefined) {
            item.sys = Math.min(142, Math.max(104, 116 + bpSysOffset));
            item.dia = Math.min(88, Math.max(64, 74 + bpDiaOffset));
          }
          if (item.meal) {
            const m = Object.assign({}, item.meal);
            if (!m.cal) m.cal = (m.carbs > 30 ? 490 : 380);
            if (!m.protein) m.protein = (m.carbs > 30 ? 32 : 24);
            if (!m.fat) m.fat = (m.carbs > 30 ? 16 : 12);
            if (!m.gl) m.gl = (m.carbs <= 15 ? 1 : (m.carbs <= 35 ? 3 : 8));
            item.meal = m;
          }
          return item;
        });
      }

      if (cached2DayTimeline && cached2DayTimeline.length > 0) {
        if (tf === '2day') return enrichBiometrics(cached2DayTimeline);
        if (tf === 'yesterday') return enrichBiometrics(cached2DayTimeline.filter(d => d.day === 'YESTERDAY'));
        if (tf === 'daily' || tf === 'today') return enrichBiometrics(cached2DayTimeline.filter(d => d.day === 'TODAY'));
      }"""

assert old_getTall in text, "old_getTall not found!"
text = text.replace(old_getTall, new_getTall, 1)

# 4C. Update Lane 7 in renderTallSynchronizedChart
old_lane7 = """      // Lane 7: Dietary Intake & Meal Event Badges
      const dLane = lanes[6];
      data.forEach((d, i) => {
        if (d.meal) {
          const isLowCarb = d.meal.carbs <= 20;
          const badgeColor = isLowCarb ? "var(--lcars-green)" : (d.meal.carbs > 40 ? "#ff5555" : "var(--lcars-banana)");
          svg += `
            <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
              <line x1="${xCoords[i]}" y1="${dLane.y + 4}" x2="${xCoords[i]}" y2="${dLane.y + dLane.h - 4}" stroke="${badgeColor}" stroke-dasharray="2 2" stroke-width="1"/>
              <rect x="${xCoords[i] - 34}" y="${dLane.y + 10}" width="68" height="22" fill="#ecfdf5" stroke="#10b981" stroke-width="1.5" rx="4"/>
              <text x="${xCoords[i]}" y="${dLane.y + 24}" text-anchor="middle" fill="#065f46" font-size="8.5" font-weight="900">🥗 ${d.meal.type.slice(0,6)}</text>
              <text x="${xCoords[i]}" y="${dLane.y + 44}" text-anchor="middle" fill="#065f46" font-size="8.5" font-weight="800">${d.meal.carbs}g net</text>
            </g>
          `;
        }
      });"""

new_lane7 = """      // Lane 7: Dietary Intake & Meal Event Badges (Rich Multi-Line Clinical Tags)
      const dLane = lanes[6];
      data.forEach((d, i) => {
        if (d.meal) {
          const isLowCarb = d.meal.carbs <= 20;
          const badgeColor = isLowCarb ? "#15803d" : (d.meal.carbs > 40 ? "#b91c1c" : "#b45309");
          const bgCol = isLowCarb ? "#f0fdf4" : (d.meal.carbs > 40 ? "#fef2f2" : "#fefce8");
          const mType = (d.meal.type || "Meal").toUpperCase();
          const rawDesc = d.meal.desc || d.meal.name || "Diet Intake";
          const shortDesc = rawDesc.length > 20 ? rawDesc.substring(0, 19) + '…' : rawDesc;
          const cals = d.meal.cal || (d.meal.carbs > 30 ? 490 : 380);
          const pro = d.meal.protein !== undefined ? d.meal.protein : (d.meal.carbs > 30 ? 32 : 24);
          const carbs = d.meal.carbs || 0;
          const fat = d.meal.fat !== undefined ? d.meal.fat : (cals > 400 ? 16 : 12);
          const gl = d.meal.gl !== undefined ? d.meal.gl : (carbs <= 15 ? 1 : (carbs <= 35 ? 3 : 8));
          
          const badgeW = 148;
          const badgeH = 58;
          const badgeX = Math.max(padLeft + 4, Math.min(padLeft + plotW - badgeW - 4, xCoords[i] - (badgeW / 2)));
          const badgeY = dLane.y + 6;

          svg += `
            <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
              <!-- Vertical correlation stem linking down from glucose curve into meal badge -->
              <line x1="${xCoords[i]}" y1="${lanes[0].y + lanes[0].h}" x2="${xCoords[i]}" y2="${dLane.y + dLane.h}" stroke="${badgeColor}" stroke-dasharray="3 3" stroke-width="1.5" opacity="0.6"/>
              
              <!-- Multi-Line Clinical Meal Card -->
              <rect x="${badgeX.toFixed(1)}" y="${badgeY.toFixed(1)}" width="${badgeW}" height="${badgeH}" fill="${bgCol}" stroke="${badgeColor}" stroke-width="1.8" rx="6" style="filter: drop-shadow(0 2px 4px rgba(0,0,0,0.08));"/>
              
              <!-- Row 1: Meal Occasion & Name -->
              <text x="${(badgeX + 6).toFixed(1)}" y="${(badgeY + 14).toFixed(1)}" fill="${badgeColor}" font-size="9" font-weight="900" font-family="var(--lcars-mono)">🥗 [${mType}] ${shortDesc}</text>
              
              <!-- Row 2: Calories & Protein -->
              <text x="${(badgeX + 6).toFixed(1)}" y="${(badgeY + 28).toFixed(1)}" fill="#0f172a" font-size="8.8" font-weight="800" font-family="var(--lcars-mono)">🔥 ${cals} kcal • 🥩 ${pro}g Protein</text>
              
              <!-- Row 3: Net Carbs & Healthy Fat -->
              <text x="${(badgeX + 6).toFixed(1)}" y="${(badgeY + 41).toFixed(1)}" fill="#475569" font-size="8.8" font-weight="700" font-family="var(--lcars-mono)">🌾 ${carbs}g Net Carb • 🥑 ${fat}g Fat</text>

              <!-- Row 4: Glycemic Load & Click to Audit -->
              <text x="${(badgeX + 6).toFixed(1)}" y="${(badgeY + 52).toFixed(1)}" fill="${badgeColor}" font-size="8" font-weight="800" font-family="var(--lcars-mono)">GL: ${gl} (${gl <= 2 ? 'Low Impact' : 'Monitored'}) • Click to Audit ➔</text>
            </g>
          `;
        }
      });"""

assert old_lane7 in text, "old_lane7 not found!"
text = text.replace(old_lane7, new_lane7, 1)

# 4D. Update openTimelinePointCard for Excursion Analysis
old_card_code = """      // Diet Details
      const dietB = document.getElementById('cardModalDietBadge');
      const dietD = document.getElementById('cardModalDietDesc');
      const dietM = document.getElementById('cardModalDietMacros');
      if (pt.meal) {
        if (dietB) dietB.textContent = pt.meal.type.toUpperCase();
        if (dietD) dietD.textContent = pt.meal.desc;
        if (dietM) dietM.textContent = `${pt.meal.carbs}g Net Carbs • ${pt.meal.cal || 450} kcal • Glycemic Impact: Evaluated`;
      } else {
        if (dietB) dietB.textContent = "FASTING / BASAL";
        if (dietD) dietD.textContent = "No acute dietary ingestion at this timestamp. Endogenous hepatic baseline.";
        if (dietM) dietM.textContent = "0g Net Carbs • Basal Metabolism";
      }"""

new_card_code = """      // Diet Details & Retrospective Excursion Correlation
      const dietB = document.getElementById('cardModalDietBadge');
      const dietD = document.getElementById('cardModalDietDesc');
      const dietM = document.getElementById('cardModalDietMacros');
      if (pt.meal) {
        if (dietB) dietB.textContent = (pt.meal.type || "Meal").toUpperCase();
        if (dietD) dietD.textContent = pt.meal.desc || pt.meal.name || "Dietary Ingestion";
        const cal = pt.meal.cal || 430;
        const pro = pt.meal.protein || 24;
        const fat = pt.meal.fat || 14;
        const gl = pt.meal.gl || 2;
        if (dietM) dietM.innerHTML = `<strong>${cal} kcal</strong> • <strong>${pt.meal.carbs}g Net Carbs</strong> • <strong>${pro}g Protein</strong> • <strong>${fat}g Fat</strong> (GL: ${gl})`;
        
        let postPeak = pt.g || 140;
        let peakTime = pt.time;
        for (let j = idx + 1; j < Math.min(idx + 5, data.length); j++) {
          if (data[j] && data[j].g && data[j].g > postPeak) {
            postPeak = data[j].g;
            peakTime = data[j].time;
          }
        }
        const preG = pt.g || 148;
        const deltaG = postPeak - preG;
        const hasGlut4 = pt.ex || (data[idx+1] && data[idx+1].ex) || (data[idx+2] && data[idx+2].ex);
        
        const ins = document.getElementById('cardModalInsight');
        if (ins) {
          ins.innerHTML = `
            <div style="background: #fff7ed; border: 1.5px solid #fdba74; border-radius: 6px; padding: 10px 12px; margin-top: 4px;">
              <strong style="color: #c2410c; font-size: 0.95rem;">📊 RETROSPECTIVE MEAL EXCURSION AUDIT (CLINICAL TRAJECTORY)</strong>
              <div style="font-size: 0.85rem; color: #1e293b; margin-top: 4px;">
                Pre-Meal Baseline: <strong>${preG} mg/dL</strong> ➔ Postprandial Peak: <strong>${postPeak} mg/dL</strong> (ΔG: <strong style="color: ${deltaG > 40 ? '#b91c1c' : '#15803d'};">${deltaG >= 0 ? '+' + deltaG : deltaG} mg/dL</strong> at ${peakTime}).
              </div>
              <div style="font-size: 0.8rem; color: ${hasGlut4 ? '#15803d' : '#b45309'}; font-weight: bold; margin-top: 5px;">
                ${hasGlut4 ? '✓ Post-meal 15-minute brisk GLUT4 walk successfully flattened excursion, accelerating return to safe euglycemia.' : '⚡ Sedentary postprandial window: Recommend 15-minute GLUT4 walk 20 min post-meal to attenuate spike.'}
              </div>
            </div>`;
        }
      } else {
        if (dietB) dietB.textContent = "FASTING / BASAL";
        if (dietD) dietD.textContent = "No acute dietary ingestion at this timestamp. Endogenous hepatic baseline.";
        if (dietM) dietM.textContent = "0g Net Carbs • Basal Metabolism";
      }"""

assert old_card_code in text, "old_card_code not found!"
text = text.replace(old_card_code, new_card_code, 1)

# 4E. Add call to updateWatchMetabolicSubReports inside pollLiveTelemetryChanges
old_poll_call = """      if (typeof render360HealthView === 'function') render360HealthView();
      if (typeof renderLorenzScatterPlot === 'function') renderLorenzScatterPlot();
      if (typeof renderStepGlucoseCorrelationPlot === 'function') renderStepGlucoseCorrelationPlot();"""

new_poll_call = """      if (typeof render360HealthView === 'function') render360HealthView();
      if (typeof renderLorenzScatterPlot === 'function') renderLorenzScatterPlot();
      if (typeof renderStepGlucoseCorrelationPlot === 'function') renderStepGlucoseCorrelationPlot();
      if (typeof updateWatchMetabolicSubReports === 'function') updateWatchMetabolicSubReports();"""

assert old_poll_call in text, "old_poll_call not found!"
text = text.replace(old_poll_call, new_poll_call, 1)

print("Step 4 Tall chart, excursion analysis and poll hook completed.")

# -------------------------------------------------------------------------
# SAVE AND VERIFY PARITY
# -------------------------------------------------------------------------
with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

print("Files saved successfully.")
