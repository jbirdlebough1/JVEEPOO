import os
import re

def update_html(filepath):
    print(f"[*] Processing {filepath}...")
    with open(filepath, "r", encoding="utf-8") as f:
        html = f.read()

    # 1. Update Preset Buttons: Replace McDonald's with Dick's Combo
    old_btn = r'''<button class="lcars-btn btn-orange" style="font-size: 0.76rem; padding: 4px 10px;" onclick="selectMealBuilderPreset\('mcdonalds'\)">\s*🍔 McDonald's Quarter Pounder Meal\s*</button>'''
    new_btn = '''<button class="lcars-btn btn-orange" style="font-size: 0.76rem; padding: 4px 10px;" onclick="selectMealBuilderPreset('dicks_combo')">
                  🍔 Dick's 2 Cheeseburgers, 3 Tartar, Fries & Diet (95g net)
                </button>'''
    html = re.sub(old_btn, new_btn, html)

    # 2. Update Common Meal Packages Header & Toolbar
    old_toolbar = r'''<button class="lcars-btn btn-peach" style="font-size: 0.78rem; padding: 4px 10px;" onclick="saveCurrentAsNewPackage\(\)">\s*💾 SAVE AS NEW MEAL ID\s*</button>'''
    new_toolbar = '''<button class="lcars-btn btn-banana" style="font-size: 0.78rem; padding: 4px 12px; font-weight: bold;" onclick="togglePopularMealsManager()">
                    ⚙️ POPULAR MEALS MANAGER (EDIT / DELETE)
                  </button>
                  <button class="lcars-btn btn-peach" style="font-size: 0.78rem; padding: 4px 10px;" onclick="promptCreateNewPopularMeal()">
                    ➕ NEW POPULAR MEAL
                  </button>'''
    html = re.sub(old_toolbar, new_toolbar, html)

    # 3. Replace McDonald's option in selCommonMealPackage
    old_mcd_opt = r'''<option value="mcdonalds">\[MEAL-06\] McDonald's Quarter Pounder Meal \(Standard vs\. Low-Carb Bunless\)</option>'''
    new_opt = '''<option value="dicks_combo">[MEAL-06] Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Hand-Cut Fries & Large Diet (95g Net Carbs)</option>'''
    html = re.sub(old_mcd_opt, new_opt, html)

    # 4. Inject Popular Meals Manager Card & Edit Modal if not already present
    popular_manager_ui = '''
            <!-- =========================================================================
                 ⭐ POPULAR & SAVED MEALS MANAGER (FULL CRUD: VIEW, EDIT, DELETE, ADD)
                 ========================================================================= -->
            <div id="cardPopularMealsManager" style="background: #ffffff; border: 2px solid #0284c7; border-left: 6px solid #f59e0b; border-radius: 8px; padding: 14px 16px; margin-bottom: 16px; display: none;">
              <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 12px; flex-wrap: wrap; gap: 8px;">
                <div>
                  <strong style="color: #0369a1; font-size: 0.98rem; font-family: var(--lcars-mono);">
                    ⭐ POPULAR &amp; SAVED MEALS LIBRARY &bull; CLINICAL EDIT &amp; DELETE MANAGER
                  </strong>
                  <div style="font-size: 0.76rem; color: #475569;">
                    Customize recurring meal packages, tune nutrient macros, edit ingredients, or delete meals. Doctor and patient collaborate here to build therapeutic routines.
                  </div>
                </div>
                <div style="display: flex; gap: 6px;">
                  <button class="lcars-btn btn-green" style="font-size: 0.76rem; padding: 4px 12px; font-weight: bold;" onclick="promptCreateNewPopularMeal()">
                    ➕ ADD NEW POPULAR MEAL
                  </button>
                  <button class="lcars-btn btn-blue" style="font-size: 0.76rem; padding: 4px 10px;" onclick="syncPopularMealsWithServer()">
                    🔄 SYNC WITH DB
                  </button>
                  <button class="lcars-btn btn-peach" style="font-size: 0.76rem; padding: 4px 10px;" onclick="togglePopularMealsManager()">
                    ✖ CLOSE MANAGER
                  </button>
                </div>
              </div>

              <!-- Popular Meals Table -->
              <div style="overflow-x: auto; max-height: 380px; overflow-y: auto; border: 1px solid #cbd5e1; border-radius: 6px;">
                <table style="width: 100%; border-collapse: collapse; font-size: 0.78rem; font-family: var(--lcars-mono); text-align: left;">
                  <thead style="background: #f1f5f9; position: sticky; top: 0; z-index: 2;">
                    <tr style="border-bottom: 2px solid #cbd5e1;">
                      <th style="padding: 8px; color: #0f172a;">ID</th>
                      <th style="padding: 8px; color: #0f172a;">POPULAR MEAL SPECIFICATION &amp; INGREDIENTS</th>
                      <th style="padding: 8px; color: #0f172a; text-align: center;">NET CARBS</th>
                      <th style="padding: 8px; color: #0f172a; text-align: center;">CALORIES</th>
                      <th style="padding: 8px; color: #0f172a; text-align: center;">PROTEIN</th>
                      <th style="padding: 8px; color: #0f172a; text-align: center;">STATUS</th>
                      <th style="padding: 8px; color: #0f172a; text-align: center; min-width: 180px;">CLINICAL ACTIONS</th>
                    </tr>
                  </thead>
                  <tbody id="popularMealsTableBody">
                    <!-- Populated dynamically by renderPopularMealsManager() -->
                  </tbody>
                </table>
              </div>
            </div>

            <!-- Modal: Edit Popular Meal -->
            <div id="modalEditPopularMeal" style="display: none; position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; background: rgba(15,23,42,0.7); z-index: 9999; justify-content: center; align-items: center;">
              <div style="background: #ffffff; border: 2.5px solid #0284c7; border-radius: 10px; width: 92%; max-width: 650px; padding: 20px; box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.4);">
                <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 14px;">
                  <strong style="color: #0369a1; font-size: 1.05rem; font-family: var(--lcars-mono);" id="lblEditMealModalTitle">
                    ✏️ EDIT POPULAR MEAL SPECIFICATION
                  </strong>
                  <button onclick="closeEditPopularMealModal()" style="background: none; border: none; font-size: 1.2rem; cursor: pointer; color: #64748b;">✕</button>
                </div>
                
                <form id="formEditPopularMeal" onsubmit="saveEditedPopularMeal(event)">
                  <input type="hidden" id="editMealKey">
                  
                  <div style="display: grid; grid-template-columns: 1fr 2fr; gap: 10px; margin-bottom: 10px;">
                    <div>
                      <label style="font-size: 0.74rem; font-weight: bold; color: #475569; display: block; margin-bottom: 3px;">PACKAGE ID:</label>
                      <input type="text" id="editMealId" style="width: 100%; padding: 6px 8px; border: 1px solid #cbd5e1; border-radius: 4px; font-family: var(--lcars-mono); font-size: 0.82rem;" required>
                    </div>
                    <div>
                      <label style="font-size: 0.74rem; font-weight: bold; color: #475569; display: block; margin-bottom: 3px;">MEAL TITLE / NAME:</label>
                      <input type="text" id="editMealTitle" style="width: 100%; padding: 6px 8px; border: 1px solid #cbd5e1; border-radius: 4px; font-size: 0.85rem;" required>
                    </div>
                  </div>

                  <div style="margin-bottom: 10px;">
                    <label style="font-size: 0.74rem; font-weight: bold; color: #475569; display: block; margin-bottom: 3px;">DESCRIPTION &amp; INGREDIENTS:</label>
                    <textarea id="editMealDesc" rows="3" style="width: 100%; padding: 6px 8px; border: 1px solid #cbd5e1; border-radius: 4px; font-size: 0.8rem;" required></textarea>
                  </div>

                  <div style="display: grid; grid-template-columns: repeat(5, 1fr); gap: 8px; margin-bottom: 12px;">
                    <div>
                      <label style="font-size: 0.72rem; font-weight: bold; color: #475569; display: block; margin-bottom: 3px;">NET CARBS (g):</label>
                      <input type="number" step="0.1" id="editMealNetCarbs" style="width: 100%; padding: 6px; border: 1px solid #cbd5e1; border-radius: 4px; font-weight: bold; color: #15803d;" required>
                    </div>
                    <div>
                      <label style="font-size: 0.72rem; font-weight: bold; color: #475569; display: block; margin-bottom: 3px;">TOTAL CARBS (g):</label>
                      <input type="number" step="0.1" id="editMealTotalCarbs" style="width: 100%; padding: 6px; border: 1px solid #cbd5e1; border-radius: 4px;">
                    </div>
                    <div>
                      <label style="font-size: 0.72rem; font-weight: bold; color: #475569; display: block; margin-bottom: 3px;">CALORIES (kcal):</label>
                      <input type="number" id="editMealCalories" style="width: 100%; padding: 6px; border: 1px solid #cbd5e1; border-radius: 4px;" required>
                    </div>
                    <div>
                      <label style="font-size: 0.72rem; font-weight: bold; color: #475569; display: block; margin-bottom: 3px;">PROTEIN (g):</label>
                      <input type="number" step="0.1" id="editMealProtein" style="width: 100%; padding: 6px; border: 1px solid #cbd5e1; border-radius: 4px;">
                    </div>
                    <div>
                      <label style="font-size: 0.72rem; font-weight: bold; color: #475569; display: block; margin-bottom: 3px;">FIBER (g):</label>
                      <input type="number" step="0.1" id="editMealFiber" style="width: 100%; padding: 6px; border: 1px solid #cbd5e1; border-radius: 4px;">
                    </div>
                  </div>

                  <div style="margin-bottom: 14px;">
                    <label style="font-size: 0.74rem; font-weight: bold; color: #475569; display: block; margin-bottom: 3px;">DOCTOR &amp; COACH GUIDANCE / GLUT4 WALK RECOMMENDATION:</label>
                    <input type="text" id="editMealTips" style="width: 100%; padding: 6px 8px; border: 1px solid #cbd5e1; border-radius: 4px; font-size: 0.8rem;" placeholder="e.g. 15-min GLUT4 walk keeps post-meal curve < 130 mg/dL.">
                  </div>

                  <div style="display: flex; justify-content: flex-end; gap: 8px;">
                    <button type="button" class="lcars-btn btn-peach" onclick="closeEditPopularMealModal()" style="font-size: 0.8rem; padding: 6px 14px;">
                      CANCEL
                    </button>
                    <button type="submit" class="lcars-btn btn-green" style="font-size: 0.85rem; padding: 6px 18px; font-weight: bold;">
                      💾 SAVE POPULAR MEAL
                    </button>
                  </div>
                </form>
              </div>
            </div>
'''
    if 'id="cardPopularMealsManager"' not in html:
        html = html.replace('<!-- User Meal Request Input Box -->', popular_manager_ui + '\n            <!-- User Meal Request Input Box -->')

    # 5. Update Action Toolbar below Gemini Analysis Result
    old_action_bar = r'''<button class="lcars-btn btn-green" style="font-size: 0\.85rem; padding: 6px 18px; font-weight: 900;" onclick="logMealBuilderToTimeline\(\)">\s*➕ COMMIT TO MEAL HISTORY &amp; 8-LANE TELEMETRY TIMELINE\s*</button>'''
    new_action_bar = '''<button class="lcars-btn btn-banana" style="font-size: 0.85rem; padding: 6px 14px; font-weight: bold;" onclick="addCurrentAnalysisToPopularMeals()">
                    ⭐ ADD TO POPULAR MEALS
                  </button>
                  <button class="lcars-btn btn-green" style="font-size: 0.85rem; padding: 6px 18px; font-weight: 900;" onclick="submitActualMealAndMonitorMetrics()">
                    🚀 SUBMIT ACTUAL MEAL &amp; MONITOR METRICS
                  </button>'''
    html = re.sub(old_action_bar, new_action_bar, html)

    # 6. Inject Doctor-Patient Longitudinal Telemetry Explorer right below Gemini Analysis Result
    longitudinal_explorer_ui = '''
            <!-- =========================================================================
                 🩺 DOCTOR-PATIENT LONGITUDINAL MEAL METABOLIC EXPLORER
                 ========================================================================= -->
            <div id="cardDoctorPatientLongitudinal" style="background: #ffffff; border: 2px solid #0284c7; border-left: 6px solid #10b981; border-radius: 8px; padding: 16px; margin-top: 18px; margin-bottom: 22px;">
              <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 10px; margin-bottom: 12px; flex-wrap: wrap; gap: 8px;">
                <div>
                  <div style="display: flex; align-items: center; gap: 8px;">
                    <span style="font-size: 1.4rem;">🩺</span>
                    <div>
                      <strong style="color: #065f46; font-size: 1.05rem; font-family: var(--lcars-mono);">
                        DOCTOR &amp; PATIENT LONGITUDINAL MEAL-TO-METRIC EXPLORER
                      </strong>
                      <div style="font-size: 0.78rem; color: #475569;">
                        Pre-Choice Coaching (Gemini) + 1-Click Submission &bull; Track What Meal Has What Effect Across Day, Week, Month, Quarter (eA1C), &amp; Year
                      </div>
                    </div>
                  </div>
                </div>
                
                <!-- Timeframe Exploration Strip -->
                <div style="display: flex; gap: 6px; flex-wrap: wrap;">
                  <button class="lcars-btn btn-green active" id="btnLongTfDay" style="font-size: 0.78rem; padding: 5px 12px; font-weight: bold;" onclick="setLongitudinalTimeframe('daily')">
                    📅 24-HR (DAILY)
                  </button>
                  <button class="lcars-btn btn-blue" id="btnLongTfWeek" style="font-size: 0.78rem; padding: 5px 12px; font-weight: bold;" onclick="setLongitudinalTimeframe('weekly')">
                    📅 7-DAY (WEEKLY)
                  </button>
                  <button class="lcars-btn btn-banana" id="btnLongTfMonth" style="font-size: 0.78rem; padding: 5px 12px; font-weight: bold;" onclick="setLongitudinalTimeframe('monthly')">
                    📅 30-DAY (MONTHLY)
                  </button>
                  <button class="lcars-btn btn-peach" id="btnLongTfQuarter" style="font-size: 0.78rem; padding: 5px 12px; font-weight: bold;" onclick="setLongitudinalTimeframe('quarterly')">
                    📅 90-DAY (QUARTERLY eA1C)
                  </button>
                  <button class="lcars-btn btn-lilac" id="btnLongTfYear" style="font-size: 0.78rem; padding: 5px 12px; font-weight: bold;" onclick="setLongitudinalTimeframe('yearly')">
                    📅 365-DAY (YEARLY)
                  </button>
                </div>
              </div>

              <!-- Dynamic Longitudinal Viewport -->
              <div id="longitudinalTelemetryViewport">
                <!-- Rendered dynamically by renderLongitudinalView() -->
              </div>

              <!-- Doctor Mentoring & Gemini Coaching Strategy Card -->
              <div id="longitudinalDoctorNotesCard" style="margin-top: 14px; background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 12px 14px;">
                <!-- Populated dynamically -->
              </div>
            </div>
'''
    if 'id="cardDoctorPatientLongitudinal"' not in html:
        html = html.replace('<!-- Component 0B: Impulse Food Helper & Cost Analysis', longitudinal_explorer_ui + '\n<!-- Component 0B: Impulse Food Helper & Cost Analysis')

    with open(filepath, "w", encoding="utf-8") as f:
        f.write(html)
    print(f"[✔] Successfully updated HTML layout in {filepath}")

update_html(r"D:\veepoo\w11_htm_app\index.html")
update_html(r"D:\veepoo\index.html")
