import re
import hashlib

def patch_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        html = f.read()

    # 1. Update Sidebar Nav Label from 05-ADA TRADES to 05-MEAL PLANNER
    old_nav = """<button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">05-ADA TRADES</span>
      </button>"""
    new_nav = """<button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">05-MEAL PLANNER</span>
      </button>"""
    assert old_nav in html, f"old_nav not found in {filepath}"
    html = html.replace(old_nav, new_nav, 1)

    # 2. Update deleteMealEntry function in JS
    old_delete_fn = """    async function deleteMealEntry(id) {
      soundButtonPress();
      try {
        await fetch('/api/meal/delete?id=' + encodeURIComponent(id), { method: 'POST' });
      } catch (e) {
        console.warn("Could not delete from remote server:", e);
      }
      mealJournal = mealJournal.filter(m => m.id !== id);
      localStorage.setItem('veepoo_meal_journal', JSON.stringify(mealJournal));
      await renderDietHistory();
    }"""

    new_delete_fn = """    async function deleteMealEntry(id) {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      try {
        const resp = await fetch('/api/meal/delete?id=' + encodeURIComponent(id), {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ id: id })
        });
        if (!resp.ok) {
          await fetch('/api/meal/delete?id=' + encodeURIComponent(id));
        }
      } catch (e) {
        console.warn("Could not delete from remote server:", e);
      }
      mealJournal = mealJournal.filter(m => m.id !== id && m.id != id);
      try {
        localStorage.setItem('veepoo_meal_journal', JSON.stringify(mealJournal));
      } catch (e) {}
      await renderDietHistory();
      if (typeof loadGoalsComplianceReport === 'function') {
        loadGoalsComplianceReport();
      }
      logLCARS(`[🗑️ MEAL DELETED] Meal entry #${id} purged from SQLite vault.`, "rx");
    }"""

    assert old_delete_fn in html, f"old_delete_fn not found in {filepath}"
    html = html.replace(old_delete_fn, new_delete_fn, 1)

    # 3. Add scrollToWorkflowSection and saveAnalyzedMealToPantry helper functions
    old_helper_spot = """    function onSelectPopularMealFromListbox(mealKey) {"""
    new_helpers = """    function scrollToWorkflowSection(sectionId) {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      const el = document.getElementById(sectionId);
      if (el) {
        el.scrollIntoView({ behavior: 'smooth', block: 'start' });
        el.style.boxShadow = '0 0 16px rgba(2, 132, 199, 0.6)';
        setTimeout(() => { if (el) el.style.boxShadow = 'none'; }, 2200);
      }
    }

    async function saveAnalyzedMealToPantry() {
      if (typeof soundSuccess === 'function') soundSuccess();
      const data = currentAnalyzedMealData;
      if (!data) {
        alert("Please analyze a meal or snack first before saving to Meal Pantry.");
        return;
      }
      const title = data.title || "Custom Saved Meal";
      const fluidOz = data.fluidOz || parseFloat(document.getElementById('inpMealFluidOz')?.value) || 8.0;
      const isSnack = (data.occasion === 'Snack') || title.toLowerCase().includes('snack') || (data.calories <= 250 && data.netCarbs <= 15);
      const occasion = isSnack ? 'Snack' : 'Meal';
      const mealId = "custom_" + Date.now();

      const payload = {
        id: mealId,
        title: title,
        description: data.desc || title,
        calories: data.calories || 0,
        net_carbs: data.netCarbs || 0,
        total_carbs: data.totalCarbs || data.netCarbs || 0,
        fiber: data.fiber || 0,
        protein: data.protein || 0,
        fat: data.fat || 0,
        glycemic_load: data.gl || "Low",
        fluid_oz: fluidOz,
        occasion: occasion,
        meets_goal: data.meetsGoal ? 1 : 0,
        tips: data.tips || []
      };

      try {
        await fetch('/api/meals/popular', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        });
      } catch (err) {
        console.warn("Could not save to server pantry:", err);
      }

      // Add to local popular meals & listbox
      POPULAR_MEALS[mealId] = {
        id: mealId.toUpperCase(),
        title: title,
        desc: data.desc || title,
        calories: data.calories || 0,
        netCarbs: data.netCarbs || 0,
        totalCarbs: data.totalCarbs || 0,
        fiber: data.fiber || 0,
        protein: data.protein || 0,
        fat: data.fat || 0,
        gl: data.gl || "Low",
        fluidOz: fluidOz,
        occasion: occasion,
        meetsGoal: data.meetsGoal,
        tips: data.tips || []
      };
      savePopularMealsToStorage();

      const listbox = document.getElementById('listboxPopularMeals');
      if (listbox) {
        const opt = document.createElement('option');
        opt.value = mealId;
        opt.textContent = `⭐ ${title} (${data.netCarbs}g Net Carbs • ${fluidOz} oz • ${data.calories} kcal)`;
        listbox.insertBefore(opt, listbox.firstChild);
        listbox.value = mealId;
      }

      // Show toast
      const toast = document.getElementById('lblPantrySavedToast');
      if (toast) {
        toast.style.display = 'block';
        toast.textContent = `✅ "${title}" successfully saved to Meal Pantry! Ready for 1-click reuse.`;
        setTimeout(() => { if (toast) toast.style.display = 'none'; }, 5000);
      }
      logLCARS(`[⭐ PANTRY SAVED] "${title}" added to Meal Pantry as reusable ${occasion}.`, "rx");
    }

    function onSelectPopularMealFromListbox(mealKey) {"""

    assert old_helper_spot in html, f"old_helper_spot not found in {filepath}"
    html = html.replace(old_helper_spot, new_helpers, 1)

    # 4. Redesign Tab 05 Component 01 with Top Ribbon & Vertical Flowchart
    old_comp01_start = """          <!-- =========================================================================
               COMPONENT 01: INTERACTIVE DIABETIC MEAL BUILDER & GEMINI NUTRITION COACH
               ========================================================================= -->
          <div class="setup-section" style="border-left-color: #16a34a; margin-bottom: 22px; background: #ffffff; border: 1.5px solid #cbd5e1; border-left: 6px solid #16a34a; border-radius: 8px; padding: 18px;">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 14px;">
              <div>
                <span style="color: #15803d; font-size: 1.2rem; font-weight: 900; letter-spacing: 0.05em;">
                  🥗 INTERACTIVE DIABETIC MEAL BUILDER &amp; GEMINI NUTRITION COACH
                </span>
                <span style="font-size: 0.82rem; color: #475569; display: block; margin-top: 2px;">
                  ENTER YOUR MEAL REQUEST &bull; GEMINI PREDICTS GLYCEMIC RESPONSE &amp; ENSURES ADHERENCE TO CLINICAL GOALS
                </span>
              </div>
            </div>



            <!-- Quick Meal Request Presets -->
            <!-- SCROLLABLE LISTBOX: PREVIOUSLY CONSUMED & POPULAR MEALS -->
            <div style="margin-bottom: 14px; background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 12px;">
              <div style="display: flex; justify-content: space-between; align-items: baseline; margin-bottom: 6px;">
                <label for="listboxPopularMeals" style="font-size: 0.82rem; font-weight: 800; color: #0f172a; text-transform: uppercase;">
                  📋 MEAL PANTRY (PREVIOUSLY CONSUMED &amp; SAVED MEALS — SCROLL &amp; SELECT):
                </label>
                <span style="font-size: 0.72rem; color: #64748b; font-family: var(--lcars-mono);">
                  MEAL PANTRY &bull; 1-CLICK SELECT &bull; GEMINI EVALUATION &bull; SUBMIT TO DB
                </span>
              </div>
              <select id="listboxPopularMeals" size="5" style="width: 100%; border: 1.5px solid #0284c7; border-radius: 6px; padding: 6px 8px; font-size: 0.88rem; font-family: inherit; color: #0f172a; background: #ffffff; cursor: pointer; box-shadow: inset 0 1px 3px rgba(0,0,0,0.06);" onchange="onSelectPopularMealFromListbox(this.value)">
                <option value="meal01">🥣 Mon-Sat High-Fiber Metabolic Breakfast (MEAL-01) (38g Net Carbs • 8 oz Beverage • 430 kcal)</option>
                <option value="meal02">🍳 Sunday Protein &amp; Sprouted Toast Breakfast (MEAL-02) (17g Net Carbs • 8 oz Beverage • 385 kcal)</option>
                <option value="soup_grilled_cheese">🍲 Weekly Vegetable &amp; Meat Soup with Whole Wheat Grilled Cheese, Mini Orange &amp; Apple Slices (34g Net Carbs • 16 oz Beverage • 493 kcal)</option>
                <option value="dicks">🍔 Dick's Drive-In Deluxe Burger (Seattle 1954) (28g Net Carbs • 12 oz Beverage • 420 kcal)</option>
                <option value="dicks_combo">🍔 Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Fries &amp; Large Diet (95g Net Carbs • 24 oz Beverage • 1260 kcal)</option>
                <option value="snack_clove_tea">🍵 [Snack] Brewed Clove &amp; Ceylon Cinnamon Herbal Infusion (0.5g Net Carbs • 16 oz Beverage • 2 kcal)</option>
                <option value="snack_apple_walnut">🍏 [Snack] Crisp Green Apple Slices &amp; Raw California Walnuts (14g Net Carbs • 12 oz Beverage • 240 kcal)</option>
              </select>
            </div>

            <!-- User Meal Request Input Box & Hydration Specification (Left-Justified Max Width) -->
            <div style="margin-bottom: 16px; background: #ffffff; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 12px;">
              <div style="display: flex; justify-content: space-between; align-items: baseline; margin-bottom: 6px;">
                <label for="txtMealBuilderInput" style="font-size: 0.82rem; font-weight: 800; color: #0f172a; text-transform: uppercase;">
                  🍽️ MEAL OR SNACK DESCRIPTION (MAX-WIDTH LEFT-JUSTIFIED INPUT):
                </label>
                <span style="font-size: 0.72rem; color: #64748b; font-family: var(--lcars-mono);">
                  SNACKS ARE MEALS &bull; GEMINI NUTRITION COACH &bull; GLYCEMIC &amp; HYDRATION KINETICS
                </span>
              </div>
              <textarea id="txtMealBuilderInput" rows="2" style="width: 100%; box-sizing: border-box; padding: 10px 14px; border: 1.5px solid #0284c7; border-radius: 6px; font-size: 0.95rem; font-family: sans-serif; color: #0f172a; background: #fff; resize: vertical; margin-bottom: 10px;" placeholder="Describe your planned meal, snack, or restaurant order (e.g., 'Weekly soup with meat and vegetables, whole wheat grilled cheese, miniature orange and apple slices')">Mon-Sat High-Fiber Metabolic Breakfast: steel cut oats with cinnamon and flax seed, fresh berries, turkey sausage links, unsweetened oat milk.</textarea>
              
              <!-- Beverage / Hydration Volume Bar & Analyze Button -->
              <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;">
                <div style="display: flex; align-items: center; gap: 8px; background: #f0f9ff; border: 1px solid #bae6fd; border-radius: 6px; padding: 6px 12px;">
                  <label for="inpMealFluidOz" style="font-size: 0.82rem; font-weight: 800; color: #0369a1; text-transform: uppercase;">
                    💧 BEVERAGE VOLUME (HYDRATION):
                  </label>
                  <input id="inpMealFluidOz" type="number" step="0.5" min="0" max="64" value="8" style="width: 65px; padding: 4px 8px; font-size: 0.95rem; font-weight: 900; color: #0369a1; border: 1.5px solid #0284c7; border-radius: 4px; text-align: center;">
                  <span style="font-size: 0.82rem; font-weight: 800; color: #0369a1;">FL OZ</span>
                  <span style="font-size: 0.72rem; color: #64748b;">(Tracks metabolic hydration, plasma osmolality &amp; glucose buffering)</span>
                </div>

                <div>
                  <button class="lcars-btn btn-green" style="font-size: 0.95rem; padding: 8px 24px; font-weight: 900;" onclick="analyzeMealWithGemini()">
                    ✨ ANALYZE WITH GEMINI
                  </button>
                </div>
              </div>
            </div>"""

    new_comp01_start = """          <!-- =========================================================================
               COMPONENT 01: MEAL & SNACK PLANNER & GEMINI NUTRITION COACH
               ========================================================================= -->
          <div class="setup-section" style="border-left-color: #16a34a; margin-bottom: 22px; background: #ffffff; border: 1.5px solid #cbd5e1; border-left: 6px solid #16a34a; border-radius: 8px; padding: 18px;">
            
            <!-- HEADER -->
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 12px;">
              <div>
                <span style="color: #15803d; font-size: 1.25rem; font-weight: 900; letter-spacing: 0.05em;">
                  🥗 MEAL &amp; SNACK PLANNER &bull; GEMINI PRE-CHOICE NUTRITION COACH
                </span>
                <span style="font-size: 0.82rem; color: #475569; display: block; margin-top: 2px;">
                  PLAN MEAL/SNACK &bull; REUSE PANTRY &bull; GEMINI ESTIMATION &bull; CLINICAL TRADES &bull; CONSUME &amp; COMMIT
                </span>
              </div>
            </div>

            <!-- TOP LCARS WORKFLOW RIBBON (MINIMUM KEY & MOUSE STROKES) -->
            <div style="display: flex; gap: 8px; margin-bottom: 16px; flex-wrap: wrap; background: #0f172a; padding: 8px 12px; border-radius: 6px; border: 1.5px solid #334155; align-items: center;">
              <span style="font-size: 0.74rem; font-weight: 900; color: #38bdf8; font-family: var(--lcars-mono); margin-right: 4px;">QUICK NAV:</span>
              <button type="button" class="lcars-btn btn-blue" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionMealPantry')">
                📋 1. MEAL PANTRY (REUSE)
              </button>
              <button type="button" class="lcars-btn btn-green" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionNewMeal')">
                ➕ 2. NEW MEAL / SNACK
              </button>
              <button type="button" class="lcars-btn btn-banana" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('geminiMealAnalysisResult')">
                ✨ 3. GEMINI ESTIMATION
              </button>
              <button type="button" class="lcars-btn btn-peach" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionAdaTrades')">
                ⚖️ 4. ADA TRADES
              </button>
              <button type="button" class="lcars-btn" style="background: #10b981; color: #ffffff; border: 1.5px solid #059669; font-size: 0.78rem; padding: 5px 14px; font-weight: 900;" onclick="submitActualMealAndMonitorMetrics()">
                🚀 5. CONSUME &amp; LOG
              </button>
            </div>

            <!-- TOAST CONFIRMATIONS -->
            <div id="lblPantrySavedToast" style="display: none; background: #fef9c3; border: 1.5px solid #eab308; color: #854d0e; padding: 8px 14px; border-radius: 6px; font-size: 0.85rem; font-weight: 800; margin-bottom: 12px; text-align: center;"></div>
            <div id="lblMealChoiceCommittedToast" style="display: none; background: #dcfce7; border: 1.5px solid #16a34a; color: #15803d; padding: 8px 14px; border-radius: 6px; font-size: 0.85rem; font-weight: 800; margin-bottom: 12px; text-align: center;"></div>

            <!-- =========================================================================
                 VISUAL THINKING STYLE VERTICAL FLOWCHART PIPELINE
                 ========================================================================= -->
            <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 8px; padding: 14px; margin-bottom: 18px;">
              <div style="display: flex; justify-content: space-between; align-items: baseline; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 12px;">
                <strong style="color: #0f172a; font-size: 0.95rem; font-family: var(--lcars-mono);">
                  🔄 INTERACTIVE MEAL &amp; SNACK DECISION FLOWCHART
                </strong>
                <span style="font-size: 0.72rem; color: #64748b; font-family: var(--lcars-mono);">
                  MINIMUM KEY &amp; MOUSE STROKES &bull; 1-CLICK SELECT &bull; ESTIMATE &bull; ASSESS / MODIFY &bull; SAVE OR CONSUME
                </span>
              </div>

              <!-- FLOWCHART STEP 1: CHOOSE INPUT METHOD (EXISTING PANTRY OR NEW MEAL) -->
              <div style="display: flex; gap: 14px; align-items: stretch; background: #ffffff; border: 1.5px solid #94a3b8; border-left: 6px solid #0284c7; border-radius: 6px; padding: 12px; margin-bottom: 0;">
                <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; min-width: 90px; border-right: 1.5px solid #e2e8f0; padding-right: 12px; text-align: center;">
                  <span style="font-size: 0.75rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">STEP 1</span>
                  <span style="font-size: 1.4rem; margin: 4px 0;">📝</span>
                  <span style="font-size: 0.65rem; font-weight: 800; color: #475569; text-transform: uppercase;">SELECT INPUT PATHWAY</span>
                </div>
                
                <div style="flex: 1; display: grid; grid-template-columns: 1fr 1fr; gap: 12px;">
                  
                  <!-- PATHWAY A: MEAL PANTRY (1-CLICK REUSE) -->
                  <div id="sectionMealPantry" style="background: #f0fdf4; border: 1.5px solid #86efac; border-radius: 6px; padding: 10px;">
                    <div style="display: flex; justify-content: space-between; align-items: baseline; margin-bottom: 4px;">
                      <label for="listboxPopularMeals" style="font-size: 0.8rem; font-weight: 800; color: #166534; text-transform: uppercase;">
                        📋 PATHWAY A: REUSE MEAL PANTRY
                      </label>
                      <span style="font-size: 0.68rem; color: #15803d; font-weight: 800; background: #dcfce7; padding: 2px 6px; border-radius: 4px;">
                        1-CLICK SELECT
                      </span>
                    </div>
                    <div style="font-size: 0.72rem; color: #4b5563; margin-bottom: 6px;">
                      Select any previously saved meal or snack to instant-populate metrics:
                    </div>
                    <select id="listboxPopularMeals" size="5" style="width: 100%; border: 1.5px solid #0284c7; border-radius: 4px; padding: 6px 8px; font-size: 0.85rem; font-family: inherit; color: #0f172a; background: #ffffff; cursor: pointer; box-shadow: inset 0 1px 3px rgba(0,0,0,0.06);" onchange="onSelectPopularMealFromListbox(this.value)">
                      <option value="meal01">🥣 Mon-Sat High-Fiber Metabolic Breakfast (MEAL-01) (38g Net Carbs • 8 oz Beverage • 430 kcal)</option>
                      <option value="meal02">🍳 Sunday Protein &amp; Sprouted Toast Breakfast (MEAL-02) (17g Net Carbs • 8 oz Beverage • 385 kcal)</option>
                      <option value="soup_grilled_cheese">🍲 Weekly Vegetable &amp; Meat Soup with Whole Wheat Grilled Cheese, Mini Orange &amp; Apple Slices (34g Net Carbs • 16 oz Beverage • 493 kcal)</option>
                      <option value="dicks">🍔 Dick's Drive-In Deluxe Burger (Seattle 1954) (28g Net Carbs • 12 oz Beverage • 420 kcal)</option>
                      <option value="dicks_combo">🍔 Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Fries &amp; Large Diet (95g Net Carbs • 24 oz Beverage • 1260 kcal)</option>
                      <option value="snack_clove_tea">🍵 [Snack] Brewed Clove &amp; Ceylon Cinnamon Herbal Infusion (0.5g Net Carbs • 16 oz Beverage • 2 kcal)</option>
                      <option value="snack_apple_walnut">🍏 [Snack] Crisp Green Apple Slices &amp; Raw California Walnuts (14g Net Carbs • 12 oz Beverage • 240 kcal)</option>
                    </select>
                  </div>

                  <!-- PATHWAY B: PLAN NEW MEAL OR SNACK -->
                  <div id="sectionNewMeal" style="background: #f0f9ff; border: 1.5px solid #7dd3fc; border-radius: 6px; padding: 10px; display: flex; flex-direction: column; justify-content: space-between;">
                    <div>
                      <div style="display: flex; justify-content: space-between; align-items: baseline; margin-bottom: 4px;">
                        <label for="txtMealBuilderInput" style="font-size: 0.8rem; font-weight: 800; color: #0369a1; text-transform: uppercase;">
                          ➕ PATHWAY B: PLAN NEW MEAL OR SNACK
                        </label>
                        <span style="font-size: 0.68rem; color: #0284c7; font-weight: 800; background: #e0f2fe; padding: 2px 6px; border-radius: 4px;">
                          NATURAL LANGUAGE
                        </span>
                      </div>
                      <div style="font-size: 0.72rem; color: #4b5563; margin-bottom: 6px;">
                        Describe planned items, ingredients, portions, or custom recipes:
                      </div>
                      <textarea id="txtMealBuilderInput" rows="3" style="width: 100%; box-sizing: border-box; padding: 8px 10px; border: 1.5px solid #0284c7; border-radius: 4px; font-size: 0.88rem; font-family: sans-serif; color: #0f172a; background: #fff; resize: vertical;" placeholder="Enter meal or snack (e.g., 'Weekly soup with meat and vegetables, whole wheat grilled cheese, miniature orange and apple slices')">Mon-Sat High-Fiber Metabolic Breakfast: steel cut oats with cinnamon and flax seed, fresh berries, turkey sausage links, unsweetened oat milk.</textarea>
                    </div>

                    <!-- Beverage / Hydration Control & Analyze Button -->
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 8px; flex-wrap: wrap; gap: 8px;">
                      <div style="display: flex; align-items: center; gap: 6px; background: #ffffff; border: 1px solid #bae6fd; border-radius: 4px; padding: 4px 8px;">
                        <label for="inpMealFluidOz" style="font-size: 0.76rem; font-weight: 800; color: #0369a1;">💧 BEVERAGE (OZ):</label>
                        <input id="inpMealFluidOz" type="number" step="0.5" min="0" max="64" value="8" style="width: 55px; padding: 2px 4px; font-size: 0.88rem; font-weight: 900; color: #0369a1; border: 1px solid #0284c7; border-radius: 4px; text-align: center;">
                        <span style="font-size: 0.74rem; font-weight: bold; color: #0369a1;">FL OZ</span>
                      </div>
                      <button type="button" class="lcars-btn btn-green" style="font-size: 0.82rem; padding: 6px 16px; font-weight: 900;" onclick="analyzeMealWithGemini()">
                        ✨ ESTIMATE WITH GEMINI
                      </button>
                    </div>
                  </div>

                </div>
              </div>

              <!-- VERTICAL FLOW CONNECTOR ARROW 1 -->
              <div style="display: flex; justify-content: center; align-items: center; margin: 4px 0;">
                <div style="display: flex; flex-direction: column; align-items: center;">
                  <div style="width: 2px; height: 16px; background: #0284c7;"></div>
                  <div style="width: 0; height: 0; border-left: 6px solid transparent; border-right: 6px solid transparent; border-top: 7px solid #0284c7;"></div>
                </div>
              </div>

              <!-- FLOWCHART STEP 2: GEMINI CLINICAL ESTIMATION & FEEDBACK LOOP -->
              <div style="display: flex; gap: 14px; align-items: stretch; background: #ffffff; border: 1.5px solid #94a3b8; border-left: 6px solid #10b981; border-radius: 6px; padding: 12px; margin-bottom: 0;">
                <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; min-width: 90px; border-right: 1.5px solid #e2e8f0; padding-right: 12px; text-align: center;">
                  <span style="font-size: 0.75rem; font-weight: 900; color: #10b981; font-family: var(--lcars-mono);">STEP 2</span>
                  <span style="font-size: 1.4rem; margin: 4px 0;">📊</span>
                  <span style="font-size: 0.65rem; font-weight: 800; color: #475569; text-transform: uppercase;">CLINICAL ESTIMATION</span>
                </div>
                <div style="flex: 1; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;">
                  <div>
                    <strong style="font-size: 0.85rem; color: #0f172a;">REAL-TIME MACROS, HYDRATION &amp; 45G CEILING AUDIT</strong>
                    <div style="font-size: 0.74rem; color: #475569; margin-top: 2px;">
                      Gemini estimates calories, net carbs, glycemic load, fluid balance, and predicts postprandial glycemic spike.<br>
                      <em>Not satisfied?</em> Simply modify items in Step 1B and hit <strong>Re-Assess</strong>. <em>Need lower carbs?</em> Review <strong>ADA Trades</strong>.
                    </div>
                  </div>
                  <div style="display: flex; gap: 6px;">
                    <button type="button" class="lcars-btn btn-banana" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionNewMeal')">
                      ✏️ TWEAK &amp; RE-ASSESS
                    </button>
                    <button type="button" class="lcars-btn btn-peach" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionAdaTrades')">
                      ⚖️ REVIEW ADA TRADES
                    </button>
                  </div>
                </div>
              </div>

              <!-- VERTICAL FLOW CONNECTOR ARROW 2 -->
              <div style="display: flex; justify-content: center; align-items: center; margin: 4px 0;">
                <div style="display: flex; flex-direction: column; align-items: center;">
                  <div style="width: 2px; height: 16px; background: #10b981;"></div>
                  <div style="width: 0; height: 0; border-left: 6px solid transparent; border-right: 6px solid transparent; border-top: 7px solid #10b981;"></div>
                </div>
              </div>

              <!-- FLOWCHART STEP 3: WORKFLOW COMPLETION (SAVE TO PANTRY OR CONSUME NOW) -->
              <div style="display: flex; gap: 14px; align-items: stretch; background: #ffffff; border: 1.5px solid #94a3b8; border-left: 6px solid #f59e0b; border-radius: 6px; padding: 12px;">
                <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; min-width: 90px; border-right: 1.5px solid #e2e8f0; padding-right: 12px; text-align: center;">
                  <span style="font-size: 0.75rem; font-weight: 900; color: #f59e0b; font-family: var(--lcars-mono);">STEP 3</span>
                  <span style="font-size: 1.4rem; margin: 4px 0;">🎯</span>
                  <span style="font-size: 0.65rem; font-weight: 800; color: #475569; text-transform: uppercase;">WORKFLOW DECISION</span>
                </div>
                <div style="flex: 1; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 12px;">
                  <div>
                    <strong style="font-size: 0.85rem; color: #0f172a;">LIKE THE ESTIMATED MEAL OR SNACK? CHOOSE ACTION:</strong>
                    <div style="font-size: 0.74rem; color: #475569; margin-top: 2px;">
                      • <strong>Save to Meal Pantry:</strong> Stores this as a saved template in your pantry for future 1-click reuse (does NOT log as eaten today).<br>
                      • <strong>Consume Now:</strong> Commits this meal as eaten right now into your daily meal log and updates today's Daily Intake Summary.
                    </div>
                  </div>
                  <div style="display: flex; gap: 10px;">
                    <button type="button" class="lcars-btn btn-banana" style="font-size: 0.85rem; padding: 8px 16px; font-weight: 800;" onclick="saveAnalyzedMealToPantry()">
                      ⭐ SUBMIT TO MEAL PANTRY (NEW MEAL/SNACK)
                    </button>
                    <button type="button" class="lcars-btn btn-green" style="font-size: 0.85rem; padding: 8px 20px; font-weight: 900;" onclick="submitActualMealAndMonitorMetrics()">
                      🚀 CONSUME NOW (LOG TO MEAL LOG)
                    </button>
                  </div>
                </div>
              </div>

            </div>"""

    assert old_comp01_start in html, f"old_comp01_start not found in {filepath}"
    html = html.replace(old_comp01_start, new_comp01_start, 1)

    # 5. Label the ADA Trades section with id="sectionAdaTrades"
    old_trades_spot = """              <!-- Clinical Guidance & Recommendations -->
              <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 12px; margin-bottom: 14px;">"""
    
    new_trades_spot = """              <!-- Clinical Guidance & Recommendations -->
              <div id="sectionAdaTrades" style="display: grid; grid-template-columns: 1fr 1fr; gap: 12px; margin-bottom: 14px;">"""

    assert old_trades_spot in html, f"old_trades_spot not found in {filepath}"
    html = html.replace(old_trades_spot, new_trades_spot, 1)

    # 6. Update action buttons at bottom of Gemini card to clearly distinguish Save to Pantry vs Consume Now
    old_bottom_buttons = """                <div style="display: flex; gap: 8px;">
                  <button class="lcars-btn btn-banana" style="font-size: 0.85rem; padding: 6px 14px; font-weight: bold;" onclick="addCurrentAnalysisToPopularMeals()">
                    ⭐ ADD TO POPULAR MEALS
                  </button>
                  <button class="lcars-btn btn-green" style="font-size: 0.85rem; padding: 6px 18px; font-weight: 900;" onclick="submitActualMealAndMonitorMetrics()">
                    🚀 SUBMIT ACTUAL MEAL &amp; MONITOR METRICS
                  </button>
                  <button class="lcars-btn btn-peach" style="font-size: 0.85rem; padding: 6px 14px;" onclick="switchTab('reports')">
                    📊 VIEW IN 8-LANE CHART (TAB 04)
                  </button>
                </div>"""

    new_bottom_buttons = """                <div style="display: flex; gap: 8px;">
                  <button class="lcars-btn btn-banana" style="font-size: 0.85rem; padding: 6px 14px; font-weight: 800;" onclick="saveAnalyzedMealToPantry()">
                    ⭐ SUBMIT TO MEAL PANTRY (NEW MEAL/SNACK)
                  </button>
                  <button class="lcars-btn btn-green" style="font-size: 0.85rem; padding: 6px 18px; font-weight: 900;" onclick="submitActualMealAndMonitorMetrics()">
                    🚀 CONSUME NOW (LOG TO MEAL LOG)
                  </button>
                  <button class="lcars-btn btn-peach" style="font-size: 0.85rem; padding: 6px 14px;" onclick="switchTab('reports')">
                    📊 VIEW IN 8-LANE CHART (TAB 04)
                  </button>
                </div>"""

    assert old_bottom_buttons in html, f"old_bottom_buttons not found in {filepath}"
    html = html.replace(old_bottom_buttons, new_bottom_buttons, 1)

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(html)
    print(f"Patched {filepath} successfully!")

patch_file('index.html')
patch_file('w11_htm_app/index.html')

# Verify SHA256 parity
with open('index.html', 'rb') as f:
    h1 = hashlib.sha256(f.read()).hexdigest()
with open('w11_htm_app/index.html', 'rb') as f:
    h2 = hashlib.sha256(f.read()).hexdigest()

print(f"index.html SHA:          {h1}")
print(f"w11_htm_app/index.html:  {h2}")
assert h1 == h2, "Dual file SHA256 mismatch!"
print("Exact dual-file byte parity verified!")
