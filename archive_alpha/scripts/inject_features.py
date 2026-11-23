import os
import re

def main():
    path = r'D:\veepoo\w11_htm_app\index.html'
    with open(path, 'r', encoding='utf-8') as f:
        html = f.read()

    print(f"Loaded {path}, size = {len(html)} chars")

    # 1. Add navTabGoals to sidebar
    nav_button = '''
      <button id="navTabGoals" class="lcars-pill pill-green" onclick="switchTab('goals')">
        <span>13-CLINICAL GOALS</span>
      </button>
'''
    if 'id="navTabGoals"' not in html:
        pos = html.find('id="navTabFullScan"')
        btn_end = html.find('</button>', pos) + len('</button>')
        html = html[:btn_end] + nav_button + html[btn_end:]
        print("[✔] Added navTabGoals button to sidebar")
    else:
        print("[*] navTabGoals already present")

    # 2. Add switchTab('goals') support
    switch_marker = "else if (tabId === 'fullscan') {"
    goals_tab_js = '''      } else if (tabId === 'goals') {
        document.getElementById('navTabGoals').classList.add('active');
        document.getElementById('pageGoals').classList.add('active-page');
        if (typeof loadClinicalGoalsUI === 'function') loadClinicalGoalsUI();
'''
    if "tabId === 'goals'" not in html:
        pos = html.find(switch_marker)
        if pos != -1:
            html = html[:pos] + goals_tab_js + html[pos:]
            print("[✔] Added switchTab('goals') support")
        else:
            print("[!] Could not find switchTab fullscan marker")
    else:
        print("[*] switchTab('goals') already present")

    # 3. Add pageGoals right after pageFullScan
    page_goals_html = '''
    <!-- =====================================================================
         TAB 13: CLINICAL DIABETIC GOALS & PERSONALIZED AMBULATORY CARE PROTOCOL
         ===================================================================== -->
    <div id="pageGoals" class="tab-page">
      <div class="diet-container">
        
        <!-- Header Banner -->
        <div class="card-box" style="margin-bottom: 20px; border-left: 6px solid var(--lcars-green); background: #ffffff;">
          <div class="box-header" style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 12px; margin-bottom: 16px;">
            <div>
              <span class="box-title" style="color: #15803d; font-size: 1.25rem; font-weight: 900; letter-spacing: 1px;">
                13-CLINICAL DIABETIC GOALS &amp; PERSONALIZED AMBULATORY CARE PROTOCOL
              </span>
              <div style="font-size: 0.85rem; color: #475569; margin-top: 3px;">
                COLLABORATIVE PHYSICIAN &amp; PATIENT TARGET SETUP • CLOSED-LOOP MULTI-LANE TELEMETRY AUDITING
              </div>
            </div>
            <div style="display: flex; gap: 8px; align-items: center;">
              <span class="badge" style="background: #ecfdf5; color: #047857; border: 1.5px solid #10b981; font-weight: 800; font-size: 0.78rem;">
                🔒 CLINICAL PROTOCOL ACTIVE
              </span>
              <button class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 14px;" onclick="switchTab('reports')">
                📊 AUDIT % GOALS MET IN REPORTS (TAB 04)
              </button>
            </div>
          </div>

          <!-- Doctor & Patient Identification Ribbon -->
          <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 12px; background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 12px 16px; margin-bottom: 18px;">
            <div>
              <span style="font-size: 0.72rem; color: #64748b; font-weight: bold; text-transform: uppercase;">PATIENT IDENTIFICATION</span>
              <div style="font-size: 0.95rem; font-weight: bold; color: #0f172a;">JOHN DOE (MRN: 984210)</div>
              <div style="font-size: 0.78rem; color: #475569;">DOB: 1960-04-12 • Type 2 Diabetes Mellitus</div>
            </div>
            <div>
              <span style="font-size: 0.72rem; color: #64748b; font-weight: bold; text-transform: uppercase;">PRESCRIBING PHYSICIAN</span>
              <div style="font-size: 0.95rem; font-weight: bold; color: #0f172a;">DR. SARAH VANCE, MD</div>
              <div style="font-size: 0.78rem; color: #475569;">Endocrinology &bull; Lic #CA-89241 &bull; St. Jude Telemetry</div>
            </div>
            <div>
              <span style="font-size: 0.72rem; color: #64748b; font-weight: bold; text-transform: uppercase;">PROTOCOL STATUS &amp; LOCK DATE</span>
              <div style="font-size: 0.95rem; font-weight: bold; color: #15803d;">CALIBRATED &bull; LOCKED 2026-11-18</div>
              <div style="font-size: 0.78rem; color: #475569;">Air-Gapped Telemetry Verification Complete</div>
            </div>
          </div>

          <!-- Clinical Preset Selector Pills -->
          <div style="margin-bottom: 20px;">
            <div style="font-size: 0.82rem; font-weight: 800; color: #0f172a; margin-bottom: 8px;">
              CHOOSE CLINICAL TARGET ARCHETYPE PRESET:
            </div>
            <div style="display: flex; gap: 8px; flex-wrap: wrap;">
              <button id="btnPresetAda" class="lcars-btn btn-blue" style="font-size: 0.82rem; padding: 6px 14px;" onclick="applyClinicalGoalPreset('ada')">
                ADA Standard Tier (eA1C &lt; 7.0% &bull; Fasting 80-130 &bull; TIR &ge; 70%)
              </button>
              <button id="btnPresetAnchor" class="lcars-btn btn-green" style="font-size: 0.82rem; padding: 6px 14px; border: 2px solid #15803d; font-weight: 900;" onclick="applyClinicalGoalPreset('anchor')">
                ★ Personalized Anchor Tier (eA1C 8.0-8.3% &bull; Hypo-Protection &bull; GLUT4 Walks)
              </button>
              <button id="btnPresetTight" class="lcars-btn btn-orange" style="font-size: 0.82rem; padding: 6px 14px;" onclick="applyClinicalGoalPreset('tight')">
                Strict Optimization Tier (eA1C &lt; 6.5% &bull; Fasting 70-110 &bull; TIR &ge; 80%)
              </button>
            </div>
          </div>

          <!-- Form Grid of Clinical Targets -->
          <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap: 16px;">
            
            <!-- Category 1: Glycemic Targets -->
            <div style="background: #ffffff; border: 1.5px solid #cbd5e1; border-top: 5px solid #d97706; border-radius: 8px; padding: 16px;">
              <div style="font-size: 0.95rem; font-weight: 900; color: #b45309; margin-bottom: 12px; display: flex; justify-content: space-between; align-items: center;">
                <span>🩸 METABOLIC &amp; GLUCOSE TARGETS</span>
                <span style="font-size: 0.75rem; background: #fef3c7; color: #92400e; padding: 2px 8px; border-radius: 4px;">PRIMARY OUTCOME</span>
              </div>
              <div style="display: flex; flex-direction: column; gap: 12px;">
                <div>
                  <label style="display: flex; justify-content: space-between; font-size: 0.82rem; font-weight: bold; color: #1e293b;">
                    <span>Target eA1C Anchor (%):</span>
                    <span id="lblGoalA1C" style="color: #b45309; font-weight: 900;">8.3%</span>
                  </label>
                  <input type="range" id="goalTargetA1C" min="5.5" max="9.5" step="0.1" value="8.3" oninput="document.getElementById('lblGoalA1C').textContent=this.value+'%'" style="width: 100%; accent-color: #d97706; cursor: pointer;">
                  <div style="font-size: 0.72rem; color: #64748b;">Clinically anchored to prevent hypoglycemic unawareness while stabilizing excursions.</div>
                </div>
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 8px;">
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Fasting Range (mg/dL):</label>
                    <input type="text" id="goalFastingRange" value="80 - 130" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">2-Hr Post-Meal Ceiling:</label>
                    <input type="number" id="goalPostMealMax" value="180" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                </div>
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 8px;">
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Time-In-Range (70-180):</label>
                    <input type="text" id="goalTirTarget" value="≥ 75%" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Hypoglycemia Alarm Floor:</label>
                    <input type="number" id="goalHypoFloor" value="70" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                </div>
              </div>
            </div>

            <!-- Category 2: Dietary & Carbohydrate Boundaries -->
            <div style="background: #ffffff; border: 1.5px solid #cbd5e1; border-top: 5px solid #16a34a; border-radius: 8px; padding: 16px;">
              <div style="font-size: 0.95rem; font-weight: 900; color: #15803d; margin-bottom: 12px; display: flex; justify-content: space-between; align-items: center;">
                <span>🥗 DIETARY &amp; CARBOHYDRATE LIMITS</span>
                <span style="font-size: 0.75rem; background: #dcfce7; color: #15803d; padding: 2px 8px; border-radius: 4px;">ADA NUTRITION</span>
              </div>
              <div style="display: flex; flex-direction: column; gap: 12px;">
                <div>
                  <label style="display: flex; justify-content: space-between; font-size: 0.82rem; font-weight: bold; color: #1e293b;">
                    <span>Max Net Carbs per Meal (g):</span>
                    <span id="lblGoalMaxCarbsMeal" style="color: #15803d; font-weight: 900;">45 g</span>
                  </label>
                  <input type="range" id="goalMaxCarbsMeal" min="15" max="90" step="5" value="45" oninput="document.getElementById('lblGoalMaxCarbsMeal').textContent=this.value+' g'" style="width: 100%; accent-color: #16a34a; cursor: pointer;">
                  <div style="font-size: 0.72rem; color: #64748b;">Triggers Gemini meal warnings if request exceeds limit.</div>
                </div>
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 8px;">
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Daily Net Carbs (g/day):</label>
                    <input type="number" id="goalDailyNetCarbs" value="130" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Min Dietary Fiber (g/day):</label>
                    <input type="number" id="goalMinFiber" value="30" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                </div>
                <div style="font-size: 0.75rem; color: #334155; line-height: 1.4; background: #f0fdf4; padding: 6px 10px; border-radius: 4px; border: 1px solid #bbf7d0;">
                  <strong>Doctor's Dietary Guidance:</strong> Emphasize fibrous legumes, avocado, wild salmon, and cruciferous vegetables. Maintain consistent carbohydrate spacing across 3 meals.
                </div>
              </div>
            </div>

            <!-- Category 3: Kinetic Steps & GLUT4 Activation -->
            <div style="background: #ffffff; border: 1.5px solid #cbd5e1; border-top: 5px solid #ea580c; border-radius: 8px; padding: 16px;">
              <div style="font-size: 0.95rem; font-weight: 900; color: #c2410c; margin-bottom: 12px; display: flex; justify-content: space-between; align-items: center;">
                <span>🚶 PHYSICAL ACTIVITY &amp; GLUT4 WALKS</span>
                <span style="font-size: 0.75rem; background: #ffedd5; color: #c2410c; padding: 2px 8px; border-radius: 4px;">NON-INSULIN CLEARANCE</span>
              </div>
              <div style="display: flex; flex-direction: column; gap: 12px;">
                <div>
                  <label style="display: flex; justify-content: space-between; font-size: 0.82rem; font-weight: bold; color: #1e293b;">
                    <span>Daily Step Volume Target:</span>
                    <span id="lblGoalSteps" style="color: #c2410c; font-weight: 900;">8,000 steps</span>
                  </label>
                  <input type="range" id="goalStepTarget" min="3000" max="15000" step="500" value="8000" oninput="document.getElementById('lblGoalSteps').textContent=parseInt(this.value).toLocaleString()+' steps'" style="width: 100%; accent-color: #ea580c; cursor: pointer;">
                  <div style="font-size: 0.72rem; color: #64748b;">Correlator proves -5.2 mg/dL drop in mean glucose per 1,000 additional steps.</div>
                </div>
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 8px;">
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Post-Meal Walk (min):</label>
                    <input type="number" id="goalGlut4Duration" value="15" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Timing After Meal:</label>
                    <input type="text" id="goalGlut4Window" value="20 - 45 min" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                </div>
                <div style="font-size: 0.75rem; color: #334155; line-height: 1.4; background: #fff7ed; padding: 6px 10px; border-radius: 4px; border: 1px solid #fed7aa;">
                  <strong>Mechanism:</strong> Muscle contraction translocates GLUT4 glucose transporters independently of insulin, blunting peak excursions by 28–42 mg/dL.
                </div>
              </div>
            </div>

            <!-- Category 4: Nocturnal SpO2, Sleep & Cardiovascular -->
            <div style="background: #ffffff; border: 1.5px solid #cbd5e1; border-top: 5px solid #2563eb; border-radius: 8px; padding: 16px;">
              <div style="font-size: 0.95rem; font-weight: 900; color: #1d4ed8; margin-bottom: 12px; display: flex; justify-content: space-between; align-items: center;">
                <span>🌙 NOCTURNAL RESTORATION &amp; CARDIO</span>
                <span style="font-size: 0.75rem; background: #eff6ff; color: #1d4ed8; padding: 2px 8px; border-radius: 4px;">AUTONOMIC RECOVERY</span>
              </div>
              <div style="display: flex; flex-direction: column; gap: 12px;">
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 8px;">
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Blood Pressure Ceiling:</label>
                    <input type="text" id="goalBpCeiling" value="130 / 80" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Resting Heart Rate (BPM):</label>
                    <input type="text" id="goalHrCeiling" value="60 - 80" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                </div>
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 8px;">
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Nocturnal SpO2 Nadir (%):</label>
                    <input type="text" id="goalSpo2Nadir" value="≥ 92%" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                  <div>
                    <label style="font-size: 0.78rem; font-weight: bold; color: #1e293b; display: block;">Target Sleep Duration:</label>
                    <input type="text" id="goalSleepDuration" value="≥ 7.0 hrs" style="width: 100%; padding: 6px 8px; border: 1.5px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #fff;">
                  </div>
                </div>
                <div style="font-size: 0.75rem; color: #334155; line-height: 1.4; background: #f8fafc; padding: 6px 10px; border-radius: 4px; border: 1px solid #e2e8f0;">
                  <strong>OSAHS Prevention:</strong> Screening nocturnal dips &lt; 90% protects against hypoxia-induced hepatic glycogenolysis and elevated dawn phenomenon fasting spikes.
                </div>
              </div>
            </div>

          </div>

          <!-- Doctor Attestation & Notes -->
          <div style="margin-top: 18px; background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 14px;">
            <div style="font-size: 0.85rem; font-weight: bold; color: #0f172a; margin-bottom: 6px;">
              PHYSICIAN CLINICAL RATIONALE &amp; AMBULATORY ORDERS:
            </div>
            <textarea id="goalPhysicianNotes" rows="2" style="width: 100%; padding: 8px 10px; border: 1px solid #cbd5e1; border-radius: 4px; font-size: 0.82rem; font-family: sans-serif; color: #1e293b; background: #fff; line-height: 1.4;">Patient John Doe: Focus on blunting post-lunch and post-dinner excursions using 15-minute GLUT4 walks. Aiming for calibrated 8.3% eA1C anchor to minimize hypoglycemia risk while steadily reducing glycemic variability.</textarea>
            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 10px; flex-wrap: wrap; gap: 8px;">
              <div style="font-size: 0.78rem; color: #64748b; font-family: var(--lcars-mono);">
                SIGNATURE: Dr. Sarah Vance, MD (CA-89241) &bull; VERIFIED AIR-GAPPED HASH: 0x9B4E...A1F0
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-btn btn-green" style="font-size: 0.82rem; padding: 6px 16px; font-weight: bold;" onclick="saveClinicalGoals()">
                  💾 SAVE &amp; LOCK CLINICAL GOALS
                </button>
                <button class="lcars-btn btn-blue" style="font-size: 0.82rem; padding: 6px 16px;" onclick="switchTab('reports')">
                  📊 AUDIT % GOALS MET (TAB 04)
                </button>
              </div>
            </div>
          </div>

        </div>

      </div>
    </div>
'''
    if 'id="pageGoals"' not in html:
        pos = html.find('</div>\n\n    </main>')
        if pos == -1:
            pos = html.find('</div>\r\n\r\n    </main>')
        if pos == -1:
            pos = html.find('</main>')
        html = html[:pos] + page_goals_html + '\n' + html[pos:]
        print("[✔] Injected pageGoals (Tab 13) into workspace")
    else:
        print("[*] pageGoals already present")

    with open(path, 'w', encoding='utf-8') as f:
        f.write(html)
    print(f"[✔] Successfully saved updates to {path}")

if __name__ == '__main__':
    main()
