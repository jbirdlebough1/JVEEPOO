import os

def main():
    path = r'D:\veepoo\w11_htm_app\index.html'
    with open(path, 'r', encoding='utf-8') as f:
        html = f.read()

    print(f"Loaded {path}, size = {len(html)} chars")

    # 1. Add boxGoalComplianceAudit to pageReports right above boxExerciseMetabolicCorrelation
    goal_compliance_html = '''
          <!-- =========================================================================
               CLINICAL GOAL COMPLIANCE & ON-DEMAND ADHERENCE AUDIT (% OF GOALS MET)
               ========================================================================= -->
          <div class="chart-box" id="boxGoalComplianceAudit" style="margin-bottom: 18px; border-left: 6px solid #16a34a; background: #ffffff;">
            <div class="chart-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;">
              <div>
                <span style="color: #15803d; font-size: 1.15rem; font-weight: 900; letter-spacing: 0.05em;">
                  CLINICAL GOAL ADHERENCE AUDIT • ON-DEMAND TELEMETRY ANALYSIS
                </span>
                <span style="font-family: var(--lcars-mono); color: #475569; display: block; font-size: 0.82rem; margin-top: 2px;">
                  MEASURING 90-DAY AMBULATORY TELEMETRY BUFFER AGAINST PHYSICIAN-PRESCRIBED GOALS (TAB 13)
                </span>
              </div>
              <div style="display: flex; gap: 6px; flex-wrap: wrap;">
                <button class="lcars-btn btn-green" style="font-size: 0.8rem; padding: 4px 12px; font-weight: bold;" onclick="runGoalComplianceAudit()">
                  🔄 RE-CALCULATE % GOALS MET ON-DEMAND
                </button>
                <button class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="switchTab('goals')">
                  ⚙️ CONFIGURE CLINICAL GOALS (TAB 13)
                </button>
                <button class="lcars-btn btn-orange" style="font-size: 0.8rem; padding: 4px 12px;" onclick="exportGoalAuditToEpic()">
                  📤 PUSH GOAL AUDIT TO DOCTOR'S EPIC
                </button>
              </div>
            </div>

            <!-- Composite Score Display & Domain Breakdown -->
            <div style="display: grid; grid-template-columns: 280px 1fr; gap: 16px; margin-top: 14px; align-items: center;">
              
              <!-- Circular Progress Gauge Card -->
              <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 8px; padding: 16px; text-align: center; display: flex; flex-direction: column; align-items: center; justify-content: center;">
                <div style="position: relative; width: 140px; height: 140px;">
                  <svg viewBox="0 0 100 100" style="width: 100%; height: 100%; transform: rotate(-90deg);">
                    <circle cx="50" cy="50" r="42" fill="none" stroke="#e2e8f0" stroke-width="10"/>
                    <circle id="circleGoalAuditProgress" cx="50" cy="50" r="42" fill="none" stroke="#16a34a" stroke-width="10" stroke-dasharray="264" stroke-dashoffset="38" stroke-linecap="round"/>
                  </svg>
                  <div style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; display: flex; flex-direction: column; align-items: center; justify-content: center;">
                    <span id="lblAuditCompositePercent" style="font-size: 1.6rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">85.4%</span>
                    <span style="font-size: 0.68rem; font-weight: bold; color: #64748b; text-transform: uppercase;">GOALS MET</span>
                  </div>
                </div>
                <div id="badgeAuditGrade" style="background: #dcfce7; color: #15803d; border: 1px solid #86efac; font-weight: 900; font-size: 0.8rem; padding: 3px 12px; border-radius: 20px; margin-top: 10px;">
                  GRADE A &bull; EXCELLENT ADHERENCE
                </div>
                <div id="lblAuditVectorsSatisfied" style="font-size: 0.75rem; color: #475569; margin-top: 6px;">
                  6 of 7 Clinical Target Vectors Met
                </div>
              </div>

              <!-- 7 Clinical Target Domain Breakdown Bars -->
              <div style="display: flex; flex-direction: column; gap: 8px;">
                
                <!-- Vector 1: Time-In-Range -->
                <div style="background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 8px 12px;">
                  <div style="display: flex; justify-content: space-between; font-size: 0.8rem; margin-bottom: 4px;">
                    <strong style="color: #0f172a;">1. Glucose Time-In-Range (70 - 180 mg/dL):</strong>
                    <span style="font-family: var(--lcars-mono); font-weight: bold; color: #15803d;">Actual 78.4% vs Goal &ge; 75% &bull; <span style="background: #dcfce7; padding: 1px 6px; border-radius: 3px;">100% MET (PASS)</span></span>
                  </div>
                  <div style="height: 6px; background: #e2e8f0; border-radius: 3px; overflow: hidden;">
                    <div style="width: 100%; height: 100%; background: #16a34a;"></div>
                  </div>
                </div>

                <!-- Vector 2: Post-Meal Excursions -->
                <div style="background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 8px 12px;">
                  <div style="display: flex; justify-content: space-between; font-size: 0.8rem; margin-bottom: 4px;">
                    <strong style="color: #0f172a;">2. Post-Prandial Peak Blunting (&lt; 180 mg/dL):</strong>
                    <span style="font-family: var(--lcars-mono); font-weight: bold; color: #15803d;">85.7% Compliant Meals vs Goal &ge; 80% &bull; <span style="background: #dcfce7; padding: 1px 6px; border-radius: 3px;">100% MET (PASS)</span></span>
                  </div>
                  <div style="height: 6px; background: #e2e8f0; border-radius: 3px; overflow: hidden;">
                    <div style="width: 100%; height: 100%; background: #16a34a;"></div>
                  </div>
                </div>

                <!-- Vector 3: Dietary Net Carbs -->
                <div style="background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 8px 12px;">
                  <div style="display: flex; justify-content: space-between; font-size: 0.8rem; margin-bottom: 4px;">
                    <strong style="color: #0f172a;">3. Dietary Net Carbs (&le; 45g/meal):</strong>
                    <span style="font-family: var(--lcars-mono); font-weight: bold; color: #15803d;">88.2% Compliant vs Goal &ge; 85% &bull; <span style="background: #dcfce7; padding: 1px 6px; border-radius: 3px;">100% MET (PASS)</span></span>
                  </div>
                  <div style="height: 6px; background: #e2e8f0; border-radius: 3px; overflow: hidden;">
                    <div style="width: 100%; height: 100%; background: #16a34a;"></div>
                  </div>
                </div>

                <!-- Vector 4: Daily Step Volume -->
                <div style="background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 8px 12px;">
                  <div style="display: flex; justify-content: space-between; font-size: 0.8rem; margin-bottom: 4px;">
                    <strong style="color: #0f172a;">4. Daily Step Volume (&ge; 8,000 steps):</strong>
                    <span style="font-family: var(--lcars-mono); font-weight: bold; color: #15803d;">82.2% Compliant Days vs Goal &ge; 80% &bull; <span style="background: #dcfce7; padding: 1px 6px; border-radius: 3px;">100% MET (PASS)</span></span>
                  </div>
                  <div style="height: 6px; background: #e2e8f0; border-radius: 3px; overflow: hidden;">
                    <div style="width: 100%; height: 100%; background: #16a34a;"></div>
                  </div>
                </div>

                <!-- Vector 5: Post-Meal GLUT4 Walk -->
                <div style="background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 8px 12px;">
                  <div style="display: flex; justify-content: space-between; font-size: 0.8rem; margin-bottom: 4px;">
                    <strong style="color: #0f172a;">5. Post-Meal GLUT4 Kinetic Walks (&ge; 15 min):</strong>
                    <span style="font-family: var(--lcars-mono); font-weight: bold; color: #d97706;">75.0% Walks Completed vs Goal &ge; 80% &bull; <span style="background: #fef3c7; color: #92400e; padding: 1px 6px; border-radius: 3px;">93.8% MET (ATTENTION)</span></span>
                  </div>
                  <div style="height: 6px; background: #e2e8f0; border-radius: 3px; overflow: hidden;">
                    <div style="width: 93.8%; height: 100%; background: #f59e0b;"></div>
                  </div>
                </div>

                <!-- Vector 6: Nocturnal SpO2 Stability -->
                <div style="background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 8px 12px;">
                  <div style="display: flex; justify-content: space-between; font-size: 0.8rem; margin-bottom: 4px;">
                    <strong style="color: #0f172a;">6. Nocturnal SpO2 Stability (&ge; 92% Nadir):</strong>
                    <span style="font-family: var(--lcars-mono); font-weight: bold; color: #15803d;">96.2% Compliant Nights vs Goal &ge; 95% &bull; <span style="background: #dcfce7; padding: 1px 6px; border-radius: 3px;">100% MET (PASS)</span></span>
                  </div>
                  <div style="height: 6px; background: #e2e8f0; border-radius: 3px; overflow: hidden;">
                    <div style="width: 100%; height: 100%; background: #16a34a;"></div>
                  </div>
                </div>

                <!-- Vector 7: Sleep Duration -->
                <div style="background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 8px 12px;">
                  <div style="display: flex; justify-content: space-between; font-size: 0.8rem; margin-bottom: 4px;">
                    <strong style="color: #0f172a;">7. Sleep Duration (&ge; 7.0 Hours Restorative):</strong>
                    <span style="font-family: var(--lcars-mono); font-weight: bold; color: #15803d;">83.3% Compliant Nights vs Goal &ge; 80% &bull; <span style="background: #dcfce7; padding: 1px 6px; border-radius: 3px;">100% MET (PASS)</span></span>
                  </div>
                  <div style="height: 6px; background: #e2e8f0; border-radius: 3px; overflow: hidden;">
                    <div style="width: 100%; height: 100%; background: #16a34a;"></div>
                  </div>
                </div>

              </div>
            </div>

            <!-- Audit Footer / Clinical Attestation -->
            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 12px; padding-top: 10px; border-top: 1px solid #cbd5e1; font-size: 0.75rem; color: #64748b; font-family: var(--lcars-mono); flex-wrap: wrap; gap: 8px;">
              <div>EVALUATED ACROSS: 7,840 AMBULATORY GLUCOSE PTS &bull; 90 SLEEP HYPNOGRAMS &bull; 180 MEAL EVENTS &bull; 82 EXERCISE SESSIONS</div>
              <div style="color: #15803d; font-weight: bold;">AIR-GAPPED COMPLIANCE STATUS: CLINICALLY COMPLIANT (DR. SARAH VANCE PROTOCOL)</div>
            </div>
          </div>
'''
    if 'id="boxGoalComplianceAudit"' not in html:
        pos = html.find('id="boxExerciseMetabolicCorrelation"')
        if pos != -1:
            # find preceding <div class="chart-box"
            prev_div = html.rfind('<div class="chart-box"', 0, pos)
            html = html[:prev_div] + goal_compliance_html + '\n' + html[prev_div:]
            print("[✔] Injected boxGoalComplianceAudit into pageReports")
        else:
            print("[!] Could not find boxExerciseMetabolicCorrelation")
    else:
        print("[*] boxGoalComplianceAudit already present")

    # 2. Add Diabetic Meal Builder & Gemini Nutrition Coach to pageDietTrades
    meal_builder_html = '''
          <!-- =========================================================================
               COMPONENT 01: INTERACTIVE DIABETIC MEAL BUILDER & GEMINI NUTRITION COACH
               ========================================================================= -->
          <div class="setup-section" style="border-left-color: #16a34a; margin-bottom: 22px; background: #ffffff; border: 1.5px solid #cbd5e1; border-left: 6px solid #16a34a; border-radius: 8px; padding: 18px;">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px; margin-bottom: 12px;">
              <div>
                <span style="color: #15803d; font-size: 1.2rem; font-weight: 900; letter-spacing: 0.05em;">
                  🥗 INTERACTIVE DIABETIC MEAL BUILDER &amp; GEMINI NUTRITION COACH
                </span>
                <span style="font-size: 0.82rem; color: #475569; display: block; margin-top: 2px;">
                  ENTER YOUR MEAL REQUEST &bull; GEMINI PREDICTS GLYCEMIC RESPONSE &amp; ENSURES ADHERENCE TO CLINICAL GOALS
                </span>
              </div>
              <div style="display: flex; gap: 8px; align-items: center;">
                <span class="badge" style="background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; font-weight: 800; font-size: 0.78rem;">
                  ✨ GEMINI COACH ACTIVE
                </span>
                <button class="lcars-btn btn-blue" style="font-size: 0.78rem; padding: 4px 12px;" onclick="switchTab('goals')">
                  VIEW ACTIVE DIABETIC GOALS (TAB 13)
                </button>
              </div>
            </div>

            <!-- Quick Meal Request Presets -->
            <div style="margin-bottom: 12px;">
              <span style="font-size: 0.78rem; font-weight: bold; color: #0f172a; display: block; margin-bottom: 6px;">
                POPULAR MEAL REQUEST PRESETS (CLICK TO LOAD):
              </span>
              <div style="display: flex; gap: 6px; flex-wrap: wrap;">
                <button class="lcars-btn btn-peach" style="font-size: 0.76rem; padding: 4px 10px;" onclick="selectMealBuilderPreset('chipotle')">
                  🥗 Chipotle Burrito Bowl
                </button>
                <button class="lcars-btn btn-orange" style="font-size: 0.76rem; padding: 4px 10px;" onclick="selectMealBuilderPreset('mcdonalds')">
                  🍔 McDonald's Quarter Pounder Meal
                </button>
                <button class="lcars-btn btn-green" style="font-size: 0.76rem; padding: 4px 10px;" onclick="selectMealBuilderPreset('salmon')">
                  🍣 Grilled Salmon, Asparagus &amp; Half Sweet Potato
                </button>
                <button class="lcars-btn btn-banana" style="font-size: 0.76rem; padding: 4px 10px;" onclick="selectMealBuilderPreset('pizza')">
                  🍕 2 Slices Veggie Pizza + Iced Tea
                </button>
                <button class="lcars-btn btn-blue" style="font-size: 0.76rem; padding: 4px 10px;" onclick="selectMealBuilderPreset('breakfast')">
                  🍳 Scrambled Eggs, Spinach &amp; Avocado
                </button>
                <button class="lcars-btn btn-lilac" style="font-size: 0.76rem; padding: 4px 10px;" onclick="selectMealBuilderPreset('steak')">
                  🥩 Ribeye Steak, Roasted Broccoli &amp; Herb Butter
                </button>
              </div>
            </div>

            <!-- User Meal Request Input Box -->
            <div style="display: flex; gap: 10px; margin-bottom: 14px;">
              <textarea id="txtMealBuilderInput" rows="2" style="flex: 1; padding: 8px 12px; border: 1.5px solid #cbd5e1; border-radius: 6px; font-size: 0.88rem; font-family: sans-serif; color: #0f172a; background: #fff;" placeholder="Describe your planned meal or restaurant order (e.g., 'Chipotle salad bowl with chicken, black beans, fajita peppers, salsa, guacamole, and sour cream')">Chipotle burrito bowl with brown rice, black beans, grilled chicken, fajita veggies, fresh tomato salsa, cheese, lettuce, and guacamole.</textarea>
              <button class="lcars-btn btn-green" style="font-size: 0.9rem; padding: 8px 18px; font-weight: 900; white-space: nowrap;" onclick="analyzeMealWithGemini()">
                ✨ ANALYZE WITH GEMINI
              </button>
            </div>

            <!-- Gemini Interactive Evaluation Card -->
            <div id="geminiMealAnalysisResult" style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 8px; padding: 16px;">
              
              <!-- Top Row: Macros & Diabetic Goal Adherence Status -->
              <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 10px; margin-bottom: 12px; flex-wrap: wrap; gap: 8px;">
                <div>
                  <strong style="color: #0f172a; font-size: 1.05rem;" id="lblGeminiMealTitle">Chipotle Burrito Bowl (Estimated Analysis)</strong>
                  <div style="font-size: 0.8rem; color: #64748b;" id="lblGeminiMealSub">Nutrient breakdown verified against patient's 45g net carb ceiling.</div>
                </div>
                <div id="badgeGeminiGoalStatus" style="background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; font-weight: 900; font-size: 0.82rem; padding: 4px 12px; border-radius: 4px;">
                  ✅ MEETS CLINICAL DIABETIC GOALS (33g NET CARBS / 45g MAX)
                </div>
              </div>

              <!-- Macro Metric Chips Grid -->
              <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(110px, 1fr)); gap: 8px; margin-bottom: 14px;">
                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                  <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">CALORIES</div>
                  <div style="font-size: 1.15rem; font-weight: 900; color: #0f172a;" id="chipCalories">540 kcal</div>
                </div>
                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                  <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">NET CARBS</div>
                  <div style="font-size: 1.15rem; font-weight: 900; color: #15803d;" id="chipNetCarbs">33 g</div>
                </div>
                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                  <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">TOTAL CARBS</div>
                  <div style="font-size: 1.15rem; font-weight: 900; color: #b45309;" id="chipTotalCarbs">44 g</div>
                </div>
                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                  <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">DIETARY FIBER</div>
                  <div style="font-size: 1.15rem; font-weight: 900; color: #0284c7;" id="chipFiber">11 g</div>
                </div>
                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                  <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">PROTEIN</div>
                  <div style="font-size: 1.15rem; font-weight: 900; color: #c2410c;" id="chipProtein">46 g</div>
                </div>
                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                  <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">HEALTHY FAT</div>
                  <div style="font-size: 1.15rem; font-weight: 900; color: #7c3aed;" id="chipFat">22 g</div>
                </div>
                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                  <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">GLYCEMIC LOAD</div>
                  <div style="font-size: 1.15rem; font-weight: 900; color: #15803d;" id="chipGlycemicLoad">14 (MED)</div>
                </div>
              </div>

              <!-- Clinical Guidance & Recommendations -->
              <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 12px; margin-bottom: 14px;">
                
                <!-- Left: Smart Swaps & Glycemic Impact -->
                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-left: 4px solid #16a34a; border-radius: 6px; padding: 10px 14px;">
                  <div style="font-size: 0.82rem; font-weight: 900; color: #15803d; margin-bottom: 6px;">
                    💡 GEMINI SMART NUTRITION RECOMMENDATIONS:
                  </div>
                  <ul id="listGeminiTips" style="margin: 0; padding-left: 18px; font-size: 0.78rem; color: #334155; line-height: 1.45;">
                    <li><strong>Smart Substitution:</strong> Request light brown rice or swap for extra romaine greens to cut net carbs by another 18g.</li>
                    <li><strong>Fat/Protein Buffering:</strong> Avocado monounsaturated fats and grilled chicken protein slow gastric emptying, preventing sharp spike.</li>
                    <li><strong>Estimated Glycemic Rise:</strong> Baseline glucose 106 &rarr; projected post-meal peak ~142 mg/dL (Safe under 180 mg/dL ceiling).</li>
                  </ul>
                </div>

                <!-- Right: Post-Meal Kinetic Prescription (GLUT4 Activation) -->
                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-left: 4px solid #ea580c; border-radius: 6px; padding: 10px 14px;">
                  <div style="font-size: 0.82rem; font-weight: 900; color: #c2410c; margin-bottom: 6px;">
                    🚶 POST-MEAL GLUT4 KINETIC PRESCRIPTION:
                  </div>
                  <div style="font-size: 0.78rem; color: #334155; line-height: 1.45;" id="descGeminiGlut4">
                    <strong>15-Minute Brisk Walk starting 25 min post-meal:</strong> Translocates muscle GLUT4 transporters without insulin, blunting peak excursion from +36 mg/dL down to &lt; +12 mg/dL.
                  </div>
                  <div style="margin-top: 8px; font-size: 0.74rem; font-family: var(--lcars-mono); color: #c2410c; font-weight: bold;">
                    PREDICTED PEAK WITH WALK: 118 MG/DL (HOMEOSTATIC FLATTENED CURVE)
                  </div>
                </div>

              </div>

              <!-- Action Toolbar: Log to Timeline -->
              <div style="display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #e2e8f0; padding-top: 10px; flex-wrap: wrap; gap: 8px;">
                <div style="font-size: 0.76rem; color: #64748b; font-family: var(--lcars-mono);">
                  SYNC STATUS: READY TO COMMIT TO TELEMETRY LANE 7 (DIETARY INTAKE) &amp; SCRUBBER
                </div>
                <div style="display: flex; gap: 8px;">
                  <button class="lcars-btn btn-green" style="font-size: 0.85rem; padding: 6px 18px; font-weight: 900;" onclick="logMealBuilderToTimeline()">
                    ➕ COMMIT TO MEAL HISTORY &amp; 8-LANE TELEMETRY TIMELINE
                  </button>
                  <button class="lcars-btn btn-peach" style="font-size: 0.85rem; padding: 6px 14px;" onclick="switchTab('reports')">
                    📊 VIEW IN 8-LANE CHART (TAB 04)
                  </button>
                </div>
              </div>

            </div>

          </div>
'''
    if 'id="txtMealBuilderInput"' not in html:
        pos = html.find('id="pageDietTrades"')
        if pos != -1:
            comp0_pos = html.find('<!-- Component 0B:', pos)
            if comp0_pos == -1:
                comp0_pos = html.find('<!-- Component 0:', pos)
            if comp0_pos != -1:
                html = html[:comp0_pos] + meal_builder_html + '\n' + html[comp0_pos:]
                print("[✔] Injected Diabetic Meal Builder & Gemini Nutrition Coach into pageDietTrades")
            else:
                print("[!] Could not find Component 0 marker in pageDietTrades")
        else:
            print("[!] Could not find pageDietTrades")
    else:
        print("[*] Diabetic Meal Builder already present")

    with open(path, 'w', encoding='utf-8') as f:
        f.write(html)
    print(f"[✔] Successfully saved part 2 updates to {path}")

if __name__ == '__main__':
    main()
