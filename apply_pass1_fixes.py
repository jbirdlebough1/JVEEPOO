import re
import os

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# -------------------------------------------------------------------------
# 1. FIX DOM NESTING & TAB PAGES
# -------------------------------------------------------------------------
# Move FAQ Q6 & Q7 to pageHelp
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

# Remove stray FAQ and extra closing div at end of pageDietTrades
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

# Close modalEpicUpload properly
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

# -------------------------------------------------------------------------
# 2. TAB 02 / 06 MEAL PLANNER TITLE & SCRUB AIR-GAPPED COMMENTS
# -------------------------------------------------------------------------
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

# -------------------------------------------------------------------------
# 3. FIX BOXYTDSUMMARY DEFAULT DISPLAY (HIDE ON DAILY, SHOW ON YEARLY)
# -------------------------------------------------------------------------
box_ytd_target = """          <div class="chart-box" id="boxYtdSummary" style="display: block;">"""
box_ytd_replacement = """          <div class="chart-box" id="boxYtdSummary" style="display: none;">"""

# -------------------------------------------------------------------------
# 4. FIX TALL CHART BUTTONS
# -------------------------------------------------------------------------
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

assert q5_help_target in text, "q5_help_target not found!"
assert target_diet_end in text, "target_diet_end not found!"
assert epic_modal_target in text, "epic_modal_target not found!"
assert meal_title_target in text, "meal_title_target not found!"
assert box_ytd_target in text, "box_ytd_target not found!"
assert tall_btns_target in text, "tall_btns_target not found!"

text = text.replace(q5_help_target, q5_help_replacement, 1)
text = text.replace(target_diet_end, clean_diet_trades_replacement, 1)
text = text.replace(epic_modal_target, epic_modal_replacement, 1)
text = text.replace(meal_title_target, meal_title_replacement, 1)
text = text.replace(box_ytd_target, box_ytd_replacement, 1)
text = text.replace(tall_btns_target, tall_btns_replacement, 1)

print("Pass 1 substitutions successful!")

with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

print("Saved index.html and w11_htm_app/index.html")
