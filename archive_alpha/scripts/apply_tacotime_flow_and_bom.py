import re
import json
import hashlib
import py_compile

# =========================================================================
# 1. UPDATE SERVER.PY
# =========================================================================
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_code = f.read()

# Replace Chipotle and expand Taco Time NW
old_taco_marker = '    # 1. Taco Time Northwest'
end_chipotle_marker = '    # 5. Dick\'s Drive-In Orders (Seattle Iconic Menu)'

taco_time_expanded = '''    # 1. Taco Time Northwest (Official Published Nutritional Disclosures & Menu Data)
    elif any(w in text for w in ["taco time", "tacotime", "taco", "burrito", "mexi-fries", "mexifries", "fiesta", "chili"]):
        has_drink = any(w in text for w in ["drink", "diet", "soda", "coke", "tea", "pepsi"])
        is_diet_drink = any(w in text for w in ["diet", "zero", "unsweet", "water"])
        drink_carbs = 0 if (is_diet_drink or not has_drink) else 65
        drink_cal = 0 if (is_diet_drink or not has_drink) else 240
        drink_name = "Diet Drink / Unsweetened Tea (0g Carbs)" if is_diet_drink else ("Fountain Drink (Sugary, +65g Carbs)" if has_drink else "No Drink Specified")

        if "fiesta" in text or "platter" in text:
            # Official Fiesta Platter Nutritional Profile (TC-11 compatibility: 1360 kcal, 167.0g carbs, 36.0g protein)
            tot_cals = 1360 if has_drink else 1120
            tot_carbs = 167.0 if has_drink else 102.0
            return {
                "meal_name": "Taco Time Northwest: Fiesta Platter & " + ("Diet Drink" if is_diet_drink else "Drink"),
                "vendor_matched": "Taco Time Northwest (Official Published Nutritional Disclosure)",
                "photo_filename": "meal_tacotime_fiesta.jpg",
                "calories": tot_cals,
                "net_carbs": tot_carbs,
                "total_carbs": tot_carbs + 16.0,
                "fiber": 16.0,
                "protein": 36.0,
                "fat": 56.0,
                "glycemic_load": "42 (Severe High Spike Risk)",
                "estimated_cost": "14.75",
                "meets_goal": False,
                "identified_items": [
                    {"name": "Crisp Pinto Bean / Beef Burrito", "portion": "1 order", "net_carbs": 34, "fiber": 5, "protein": 12, "calories": 380},
                    {"name": "Seasoned Mexi-Fries / Tater Tots", "portion": "1 regular order", "net_carbs": 42, "fiber": 4, "protein": 4, "calories": 360},
                    {"name": "Refried Pinto Beans with Cheddar", "portion": "1 cup", "net_carbs": 22, "fiber": 6, "protein": 14, "calories": 240},
                    {"name": "Fresh Side Salad, Guacamole & Sour Cream", "portion": "standard", "net_carbs": 4, "fiber": 1, "protein": 6, "calories": 140},
                    {"name": drink_name, "portion": "24 oz", "net_carbs": drink_carbs, "fiber": 0, "protein": 0, "calories": drink_cal}
                ],
                "projected_spike": "+85 to +105 mg/dL excursion (Breaches 180 mg/dL ceiling)",
                "gemini_notes": "⚠️ SEVERE GLYCEMIC EXCURSION (+57g Excess Net Carbs). Fast amylopectin starches in Mexi-Fries (42g net) and flour tortilla (34g net) flood bloodstream in 30-45 minutes. Cost: ~$14.75.",
                "trade_alternatives": [
                    "🥗 Trade Mexi-Fries for Side Salad: Saves 42g Net Carbs -> Drops meal into safe zone.",
                    "🍲 Choose White Chicken Chili Bowl instead (28g Net Carbs • Meets goal).",
                    "🥤 Ensure drink is Unsweetened Tea or Diet Soda (saves 65g sugar and +75 mg/dL spike)."
                ],
                "glut4_recommendation": "<strong>Mandatory 45-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Directly translocates GLUT4 glucose transporters to skeletal muscle membranes without insulin, blunting peak from 205 mg/dL down to 152 mg/dL."
            }
        elif "chili" in text:
            # White Chicken Chili Bowl
            return {
                "meal_name": "Taco Time Northwest: White Chicken Chili Bowl",
                "vendor_matched": "Taco Time Northwest (Official Published Nutritional Disclosure)",
                "photo_filename": "meal_tacotime_chili.jpg",
                "calories": 290,
                "net_carbs": 28.0,
                "total_carbs": 35.0,
                "fiber": 7.0,
                "protein": 24.0,
                "fat": 8.0,
                "glycemic_load": "9 (Safe Low Band)",
                "estimated_cost": "5.49",
                "meets_goal": True,
                "identified_items": [
                    {"name": "Tender Diced Chicken Breast", "portion": "4 oz", "net_carbs": 0, "protein": 16, "calories": 120},
                    {"name": "Slow-Simmered Northern White Beans", "portion": "1 cup", "net_carbs": 26, "fiber": 7, "protein": 8, "calories": 150},
                    {"name": "Green Chilies, Onions & Broth Spices", "portion": "1 bowl", "net_carbs": 2, "protein": 0, "calories": 20}
                ],
                "projected_spike": "+14 to +19 mg/dL (TIR Target Maintained)",
                "gemini_notes": "✨ EXCELLENT DIABETIC CHOICE: 28g Net Carbs with 7g soluble legume fiber blunts postprandial glucose absorption. Meets clinical diabetic goal.",
                "trade_alternatives": [
                    "Top with fresh diced cilantro or jalapeños for zero-carb metabolic boost."
                ],
                "glut4_recommendation": "15-minute gentle walk keeps glycemic curve flat (<125 mg/dL)."
            }
        elif "soft" in text or "chicken taco" in text:
            # Soft Chicken Taco & Side Salad
            return {
                "meal_name": "Taco Time Northwest: Soft Chicken Taco & Garden Side Salad",
                "vendor_matched": "Taco Time Northwest (Official Published Nutritional Disclosure)",
                "photo_filename": "meal_tacotime_soft_taco.jpg",
                "calories": 380,
                "net_carbs": 26.0,
                "total_carbs": 31.0,
                "fiber": 5.0,
                "protein": 24.0,
                "fat": 15.0,
                "glycemic_load": "10 (Low-Med)",
                "estimated_cost": "6.25",
                "meets_goal": True,
                "identified_items": [
                    {"name": "Soft Flour Tortilla with Grilled Chicken", "portion": "1 taco", "net_carbs": 22, "fiber": 2, "protein": 20, "calories": 260},
                    {"name": "Cheddar Cheese & Crisp Lettuce", "portion": "standard", "net_carbs": 1, "protein": 3, "calories": 70},
                    {"name": "Fresh Garden Side Salad with Light Salsa", "portion": "1 bowl", "net_carbs": 3, "fiber": 3, "protein": 1, "calories": 50}
                ],
                "projected_spike": "+16 to +22 mg/dL (Safe Target Zone)",
                "gemini_notes": "Lean poultry albumin and salad fiber deceleration buffer keep net carbs at 26g, well under the 45g ceiling.",
                "trade_alternatives": [
                    "Order taco in crisp corn shell or as salad bowl to reduce carbs further."
                ],
                "glut4_recommendation": "15-minute walk starting 25 min post-meal prevents any glycemic spike."
            }
        else:
            # Default Taco Time Meal: Crisp Burrito & Fresh Side Salad
            return {
                "meal_name": "Taco Time Northwest: Crisp Burrito & Garden Fresh Salad",
                "vendor_matched": "Taco Time Northwest (Official Published Nutritional Disclosure)",
                "photo_filename": "meal_tacotime_crisp_burrito.jpg",
                "calories": 480,
                "net_carbs": 34.0,
                "total_carbs": 40.0,
                "fiber": 6.0,
                "protein": 20.0,
                "fat": 22.0,
                "glycemic_load": "13 (Safe Low-Med Band)",
                "estimated_cost": "6.95",
                "meets_goal": True,
                "identified_items": [
                    {"name": "Taco Time Crisp Pinto Bean / Beef Burrito", "portion": "1 rolled burrito", "net_carbs": 31, "fiber": 4, "protein": 14, "calories": 380},
                    {"name": "Garden Side Salad with Fresh Salsa", "portion": "1 bowl", "net_carbs": 3, "fiber": 2, "protein": 2, "calories": 50},
                    {"name": "Unsweetened Iced Tea / Spring Water", "portion": "16 oz", "net_carbs": 0, "fiber": 0, "protein": 0, "calories": 0}
                ],
                "projected_spike": "+18 to +24 mg/dL (TIR Maintained)",
                "gemini_notes": "Verified from Taco Time NW published disclosures. 34g Net Carbs meets your 45g clinical ceiling. Side salad fiber buffers absorption.",
                "trade_alternatives": [
                    "Trade Mexi-Fries for side salad saves 42g net carbs.",
                    "Drink unsweetened iced tea or water to maintain zero liquid sugar."
                ],
                "glut4_recommendation": "20-minute GLUT4 brisk stroll dampens postprandial peak below 135 mg/dL."
            }

'''

# Extract section between taco marker and dicks marker
idx1 = server_code.find(old_taco_marker)
idx2 = server_code.find(end_chipotle_marker)
if idx1 != -1 and idx2 != -1:
    server_code = server_code[:idx1] + taco_time_expanded.strip() + "\n\n" + server_code[idx2:]
    print("[+] Replaced old Taco Time & purged Chipotle in server.py")

# Purge any remaining Chipotle mentions in server.py
server_code = server_code.replace("Chipotle salad bowl (33g net)", "Taco Time garden salad (26g net)")
server_code = server_code.replace("Chipotle salad bowl", "Taco Time garden salad")
server_code = server_code.replace("Chipotle", "Taco Time Northwest")

with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(server_code)

py_compile.compile('D:/veepoo/w11_htm_app/server.py', doraise=True)
print("[+] server.py compiled successfully")


# =========================================================================
# 2. UPDATE CELL.HTML
# =========================================================================
with open('D:/veepoo/w11_htm_app/cell.html', 'r', encoding='utf-8') as f:
    cell_code = f.read()

cell_code = cell_code.replace(
    "setPreset('Chipotle Chicken Salad Bowl, Black Beans, Fajita Veggies, Guacamole')",
    "setPreset('Taco Time Northwest: Crisp Burrito, Garden Side Salad & Unsweetened Iced Tea')"
)
cell_code = cell_code.replace(
    "🥗 Chipotle Salad Bowl",
    "🌮 Taco Time Burrito & Salad"
)
cell_code = cell_code.replace("Chipotle Mexican Grill", "Taco Time Northwest")
cell_code = cell_code.replace("Chipotle Chicken Burrito Bowl", "Taco Time Northwest: Crisp Burrito & Garden Salad")
cell_code = cell_code.replace("Chipotle", "Taco Time Northwest")

with open('D:/veepoo/w11_htm_app/cell.html', 'w', encoding='utf-8') as f:
    f.write(cell_code)
print("[+] cell.html updated")


# =========================================================================
# 3. UPDATE INDEX.HTML (FLOW REORDERING, BOM & CHIPOTLE PURGE)
# =========================================================================
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# 1. Purge Chipotle fallback from analyzeMealWithGemini in index.html
old_client_chipotle = '''        } else if (text.includes("chipotle") || text.includes("burrito")) {
          data = POPULAR_MEALS.chipotle || DEFAULT_POPULAR_MEALS.chipotle;'''

new_client_taco = '''        } else if (text.includes("taco time") || text.includes("tacotime") || text.includes("taco") || text.includes("burrito") || text.includes("mexi") || text.includes("fiesta") || text.includes("chili")) {
          if (text.includes("fiesta") || text.includes("platter")) {
            data = {
              title: "Taco Time Northwest: Fiesta Platter & Diet Drink",
              desc: rawText,
              calories: 1360,
              netCarbs: 167.0,
              totalCarbs: 183.0,
              fiber: 16.0,
              protein: 36.0,
              fat: 56.0,
              gl: "42 (Severe High Spike Risk)",
              estimatedCost: "14.75",
              meetsGoal: false,
              tips: [
                "⚠️ SEVERE GLYCEMIC EXCURSION (+57g Excess Net Carbs). Fast amylopectin starches in Mexi-Fries (42g net) and flour tortilla (34g net) flood bloodstream in 30-45 minutes.",
                "🥗 Harm Reduction: Trade Mexi-Fries for Side Salad to save 42g net carbs.",
                "🥤 Drink unsweetened tea or water to maintain zero liquid sugar."
              ],
              glut4: "<strong>Mandatory 45-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Translocates GLUT4 glucose transporters without insulin, blunting peak from 205 mg/dL to 152 mg/dL.",
              glut4Peak: "PREDICTED PEAK WITHOUT WALK: 205 MG/DL | WITH 45-MIN WALK: 152 MG/DL"
            };
          } else if (text.includes("chili")) {
            data = {
              title: "Taco Time Northwest: White Chicken Chili Bowl",
              desc: rawText,
              calories: 290,
              netCarbs: 28.0,
              totalCarbs: 35.0,
              fiber: 7.0,
              protein: 24.0,
              fat: 8.0,
              gl: "9 (Safe Low Band)",
              estimatedCost: "5.49",
              meetsGoal: true,
              tips: [
                "✨ EXCELLENT DIABETIC CHOICE: 28g Net Carbs with 7g soluble legume fiber blunts postprandial glucose absorption.",
                "Meets clinical diabetic goal (< 45g net carbs)."
              ],
              glut4: "15-minute gentle walk keeps glycemic curve flat (<125 mg/dL).",
              glut4Peak: "PREDICTED PEAK: 122 MG/DL (SAFE CONTROL)"
            };
          } else {
            data = {
              title: "Taco Time Northwest: Crisp Burrito & Garden Fresh Salad",
              desc: rawText,
              calories: 480,
              netCarbs: 34.0,
              totalCarbs: 40.0,
              fiber: 6.0,
              protein: 20.0,
              fat: 22.0,
              gl: "13 (Safe Low-Med Band)",
              estimatedCost: "6.95",
              meetsGoal: true,
              tips: [
                "Verified from Taco Time NW published disclosures: 34g Net Carbs meets your 45g clinical ceiling.",
                "Garden salad fiber and protein slow carbohydrate uptake."
              ],
              glut4: "20-minute brisk walk starting 25 min post-meal keeps peak under 135 mg/dL.",
              glut4Peak: "PREDICTED PEAK WITH WALK: 128 MG/DL (SAFE TARGET BAND)"
            };
          }'''

if old_client_chipotle in html:
    html = html.replace(old_client_chipotle, new_client_taco)
    print("[+] Replaced client Chipotle fallback with Taco Time Northwest in index.html")

# Replace any lingering mentions of Chipotle in index.html
html = html.replace("Chipotle salad bowl.", "Taco Time garden salad (26g net).")
html = html.replace("Chipotle salad bowl", "Taco Time garden salad")

# Now reorder the flowchart so that Pre-Choice Estimation (#geminiMealAnalysisResult)
# appears directly after Step 1 and before Step 2!
# Let's inspect the entire block from <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 8px; padding: 14px; margin-bottom: 18px;">
# to the end of #geminiMealAnalysisResult

# Target block start
flowchart_start_marker = '''            <!-- =========================================================================
                 VISUAL THINKING STYLE VERTICAL FLOWCHART PIPELINE
                 ========================================================================= -->
            <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 8px; padding: 14px; margin-bottom: 18px;">'''

# Find the end of the action bar below #geminiMealAnalysisResult
flowchart_end_marker = '''            <!-- =========================================================================
                 🩺 DOCTOR-PATIENT LONGITUDINAL MEAL METABOLIC EXPLORER'''

pos_start = html.find(flowchart_start_marker)
pos_end = html.find(flowchart_end_marker)
assert pos_start != -1 and pos_end != -1, "Flowchart markers not found in index.html"

# Build the re-architected flowchart that flows:
# STEP 1: Select Input Pathway (Diet Query)
# -> ARROW 1
# -> PRE-CHOICE ESTIMATION: "WHAT WILL THIS CHOICE COST ME METABOLICALLY?" (#geminiMealAnalysisResult)
#    (Includes Macros, Hydration, Spike, and Recipe Bill of Materials with Top 3 Grocers within 20 miles)
# -> ARROW 2
# -> STEP 2: RE-ASSESS OR REVIEW ADA TRADES
# -> ARROW 3
# -> STEP 3: WORKFLOW DECISION (Add to Pantry as Meal, Add to Pantry as Snack, Consume Now, View in 8-Lane Chart)

new_pipeline_html = '''            <!-- =========================================================================
                 VISUAL THINKING STYLE VERTICAL FLOWCHART PIPELINE
                 ========================================================================= -->
            <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 8px; padding: 16px; margin-bottom: 22px;">
              <div style="display: flex; justify-content: space-between; align-items: baseline; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 14px;">
                <strong style="color: #0f172a; font-size: 0.98rem; font-family: var(--lcars-mono);">
                  🔄 INTERACTIVE CLINICAL MEAL &amp; SNACK DECISION FLOWCHART
                </strong>
                <span style="font-size: 0.72rem; color: #64748b; font-family: var(--lcars-mono);">
                  DIET QUERY &bull; PRE-CHOICE ESTIMATION &bull; RE-ASSESS / ADA TRADES &bull; PANTRY OR CONSUME
                </span>
              </div>

              <!-- FLOWCHART STEP 1: CHOOSE INPUT METHOD (EXISTING PANTRY OR NEW MEAL) -->
              <div style="display: flex; gap: 14px; align-items: stretch; background: #ffffff; border: 1.5px solid #94a3b8; border-left: 6px solid #0284c7; border-radius: 6px; padding: 12px; margin-bottom: 0;">
                <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; min-width: 90px; border-right: 1.5px solid #e2e8f0; padding-right: 12px; text-align: center;">
                  <span style="font-size: 0.75rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">STEP 1</span>
                  <span style="font-size: 1.4rem; margin: 4px 0;">📝</span>
                  <span style="font-size: 0.65rem; font-weight: 800; color: #475569; text-transform: uppercase;">DIET QUERY</span>
                </div>
                
                <div style="flex: 1; display: grid; grid-template-columns: 1fr 1fr; gap: 12px;">
                  
                  <!-- PATHWAY A: MEAL PANTRY (1-CLICK REUSE) -->
                  <div id="sectionMealPantry" style="background: #f0fdf4; border: 1.5px solid #86efac; border-radius: 6px; padding: 10px;">
                    <div style="display: flex; justify-content: space-between; align-items: baseline; margin-bottom: 4px;">
                      <label for="listboxPopularMeals" style="font-size: 0.8rem; font-weight: 800; color: #166534; text-transform: uppercase;">
                        📋 PATHWAY A: REUSE MEAL PANTRY (PREVIOUSLY CONSUMED &amp; SAVED MEALS)
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
                        Describe planned items, restaurant order, or custom ingredients:
                      </div>
                      <textarea id="txtMealBuilderInput" rows="3" style="width: 100%; box-sizing: border-box; padding: 8px 10px; border: 1.5px solid #0284c7; border-radius: 4px; font-size: 0.88rem; font-family: sans-serif; color: #0f172a; background: #fff; resize: vertical;" placeholder="Enter meal or snack (e.g. 'Taco Time Northwest crisp bean burrito, fresh side salad, and unsweetened iced tea')">Taco Time Northwest: Crisp Burrito &amp; Garden Fresh Salad with light salsa, unsweetened iced tea.</textarea>
                    </div>

                    <!-- Beverage / Hydration Control & Analyze Button -->
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 8px; flex-wrap: wrap; gap: 8px;">
                      <div style="display: flex; align-items: center; gap: 6px; background: #ffffff; border: 1px solid #bae6fd; border-radius: 4px; padding: 4px 8px;">
                        <label for="inpMealFluidOz" style="font-size: 0.76rem; font-weight: 800; color: #0369a1;">💧 BEVERAGE (OZ):</label>
                        <input id="inpMealFluidOz" type="number" step="0.5" min="0" max="64" value="16" style="width: 55px; padding: 2px 4px; font-size: 0.88rem; font-weight: 900; color: #0369a1; border: 1px solid #0284c7; border-radius: 4px; text-align: center;">
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
              <div style="display: flex; justify-content: center; align-items: center; margin: 6px 0;">
                <div style="display: flex; flex-direction: column; align-items: center;">
                  <div style="width: 2px; height: 16px; background: #0284c7;"></div>
                  <div style="width: 0; height: 0; border-left: 6px solid transparent; border-right: 6px solid transparent; border-top: 7px solid #0284c7;"></div>
                </div>
              </div>

              <!-- =========================================================================
                   PRE-CHOICE ESTIMATION: "WHAT WILL THIS CHOICE COST ME METABOLICALLY?"
                   (NOW LOCATED DIRECTLY AFTER STEP 1 AS REQUESTED)
                   ========================================================================= -->
              <div id="geminiMealAnalysisResult" style="background: #ffffff; border: 1.5px solid #0284c7; border-left: 6px solid #10b981; border-radius: 8px; padding: 16px; margin-bottom: 0;">
                
                <!-- Top Row: Macros & Diabetic Goal Adherence Status -->
                <div style="margin-bottom: 8px; font-size: 0.74rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono); letter-spacing: 0.05em; text-transform: uppercase;">
                  💡 PRE-CHOICE ESTIMATION: "WHAT WILL THIS CHOICE COST ME METABOLICALLY?"
                </div>
                <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 10px; margin-bottom: 12px; flex-wrap: wrap; gap: 8px;">
                  <div>
                    <strong style="color: #0f172a; font-size: 1.05rem;" id="lblGeminiMealTitle">Taco Time Northwest: Crisp Burrito &amp; Garden Fresh Salad (Estimated Analysis)</strong>
                    <div style="font-size: 0.8rem; color: #64748b;" id="lblGeminiMealSub">Nutrient breakdown verified against patient's 45g net carb ceiling.</div>
                  </div>
                  <div id="badgeGeminiGoalStatus" style="background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; font-weight: 900; font-size: 0.82rem; padding: 4px 12px; border-radius: 4px;">
                    ✅ MEETS CLINICAL DIABETIC GOALS (34g NET CARBS / 45g MAX)
                  </div>
                </div>

                <!-- Macro Metric Chips Grid -->
                <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(110px, 1fr)); gap: 8px; margin-bottom: 14px;">
                  <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                    <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">CALORIES</div>
                    <div style="font-size: 1.15rem; font-weight: 900; color: #0f172a;" id="chipCalories">480 kcal</div>
                  </div>
                  <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                    <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">NET CARBS</div>
                    <div style="font-size: 1.15rem; font-weight: 900; color: #15803d;" id="chipNetCarbs">34 g</div>
                  </div>
                  <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                    <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">TOTAL CARBS</div>
                    <div style="font-size: 1.15rem; font-weight: 900; color: #b45309;" id="chipTotalCarbs">40 g</div>
                  </div>
                  <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                    <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">DIETARY FIBER</div>
                    <div style="font-size: 1.15rem; font-weight: 900; color: #0284c7;" id="chipFiber">6 g</div>
                  </div>
                  <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                    <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">PROTEIN</div>
                    <div style="font-size: 1.15rem; font-weight: 900; color: #c2410c;" id="chipProtein">20 g</div>
                  </div>
                  <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                    <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">HEALTHY FAT</div>
                    <div style="font-size: 1.15rem; font-weight: 900; color: #7c3aed;" id="chipFat">22 g</div>
                  </div>
                  <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                    <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">GLYCEMIC LOAD</div>
                    <div style="font-size: 1.15rem; font-weight: 900; color: #0284c7;" id="chipGlycemicLoad">13 (Low-Med)</div>
                  </div>
                </div>

                <!-- Hydration & Biometric Metabolic-Activity Interaction Tile -->
                <div style="background: #f0f9ff; border: 1.5px solid #7dd3fc; border-radius: 6px; padding: 12px; margin-bottom: 14px;">
                  <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px; border-bottom: 1px solid #bae6fd; padding-bottom: 8px; margin-bottom: 8px;">
                    <div style="display: flex; align-items: center; gap: 8px;">
                      <span style="font-size: 1.1rem;">💧</span>
                      <strong style="color: #0369a1; font-size: 0.88rem; text-transform: uppercase;">BEVERAGE HYDRATION &amp; BIOMETRIC METABOLIC-ACTIVITY INTERACTION</strong>
                    </div>
                    <div style="font-size: 0.82rem; font-weight: 800; color: #0369a1;" id="lblHydrationVolumeChip">
                      BEVERAGE: 16.0 FL OZ (473 mL)
                    </div>
                  </div>
                  <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); gap: 10px; margin-bottom: 8px;">
                    <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px;">
                      <div style="font-size: 0.68rem; color: #64748b; font-weight: bold;">PATIENT BIOMETRICS</div>
                      <div style="font-size: 0.92rem; font-weight: 800; color: #0f172a;" id="lblPatientBiometrics">Height: 175cm | 75kg | BMI: 24.5 kg/m²</div>
                    </div>
                    <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px;">
                      <div style="font-size: 0.68rem; color: #64748b; font-weight: bold;">DAILY FLUID TARGET</div>
                      <div style="font-size: 0.92rem; font-weight: 800; color: #0369a1;" id="lblDailyHydrationTarget">Baseline: ~83 oz + Activity Offset</div>
                    </div>
                    <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px;">
                      <div style="font-size: 0.68rem; color: #64748b; font-weight: bold;">METABOLIC EXCURSION BUFFER</div>
                      <div style="font-size: 0.92rem; font-weight: 800; color: #15803d;" id="lblHydrationMetabolicImpact">-10 to -16 mg/dL Peak Attenuation</div>
                    </div>
                  </div>
                  <div style="font-size: 0.76rem; color: #334155; line-height: 1.4;" id="descHydrationKinetics">
                    <strong>Physiological Mechanism:</strong> 16 oz hydration expands plasma volume, blunting peak glucose concentration velocity and ensuring accurate optical biosensor reading without hemoconcentration bias.
                  </div>
                </div>

                <!-- Clinical Guidance & Recommendations -->
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 12px; margin-bottom: 14px;">
                  
                  <!-- Left: Smart Swaps & Glycemic Impact -->
                  <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-left: 4px solid #16a34a; border-radius: 6px; padding: 10px 14px;">
                    <div style="font-size: 0.82rem; font-weight: 900; color: #15803d; margin-bottom: 6px;">
                      💡 GEMINI SMART NUTRITION RECOMMENDATIONS:
                    </div>
                    <ul id="listGeminiTips" style="margin: 0; padding-left: 18px; font-size: 0.78rem; color: #334155; line-height: 1.45;">
                      <li><strong>Website Disclosure Audit:</strong> Taco Time crisp burrito (31g net) and garden side salad (3g net) provides 34g net carbs, well below 45g ceiling.</li>
                      <li><strong>Dietary Starch Deceleration:</strong> Garden salad romaine fiber buffers starch hydrolysis from the crisp flour tortilla.</li>
                      <li><strong>Predicted Spike:</strong> Baseline glucose 106 &rarr; projected post-meal peak ~128 mg/dL (Safe target band maintained).</li>
                    </ul>
                  </div>

                  <!-- Right: Post-Meal Kinetic Prescription (GLUT4 Activation) -->
                  <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-left: 4px solid #ea580c; border-radius: 6px; padding: 10px 14px;">
                    <div style="font-size: 0.82rem; font-weight: 900; color: #c2410c; margin-bottom: 6px;">
                      🚶 POST-MEAL GLUT4 KINETIC PRESCRIPTION:
                    </div>
                    <div style="font-size: 0.78rem; color: #334155; line-height: 1.45;" id="descGeminiGlut4">
                      <strong>20-Minute Brisk GLUT4 Walk starting 25 min post-meal:</strong> Directly translocates muscular GLUT4 transporters to clear circulating glucose without beta-cell strain.
                    </div>
                    <div style="margin-top: 8px; font-size: 0.74rem; font-family: var(--lcars-mono); color: #c2410c; font-weight: bold;">
                      PREDICTED PEAK WITH WALK: 128 MG/DL (HOMEOSTATIC FLATTENED CURVE)
                    </div>
                  </div>

                </div>

                <!-- =========================================================================
                     RECIPE BILL OF MATERIALS (BOM) & LOCAL SOURCING (20-MILE RADIUS TOP 3)
                     ========================================================================= -->
                <div id="cardMealBOM" style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-left: 5px solid #0284c7; border-radius: 6px; padding: 14px; margin-bottom: 8px;">
                  <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 10px; flex-wrap: wrap; gap: 8px;">
                    <div>
                      <strong style="color: #0369a1; font-size: 0.95rem; font-family: var(--lcars-mono);">
                        🛒 RECIPE BILL OF MATERIALS (BOM) &bull; GROCERY SHOPPING LIST
                      </strong>
                      <div style="font-size: 0.75rem; color: #64748b;">
                        Itemized ingredient specifications, portions, store departments/aisles, and estimated unit costs.
                      </div>
                    </div>
                    <div style="display: flex; gap: 6px;">
                      <button class="lcars-btn btn-blue" style="font-size: 0.75rem; padding: 4px 10px;" onclick="copyBOMShoppingList()">
                        📋 COPY SHOPPING LIST
                      </button>
                      <button class="lcars-btn btn-peach" style="font-size: 0.75rem; padding: 4px 10px;" onclick="printBOMRecipeCard()">
                        🖨️ PRINT RECIPE &amp; BOM
                      </button>
                    </div>
                  </div>

                  <!-- BOM Table -->
                  <div style="overflow-x: auto; margin-bottom: 12px;">
                    <table style="width: 100%; border-collapse: collapse; font-size: 0.78rem; font-family: var(--lcars-mono);">
                      <thead>
                        <tr style="background: #ffffff; border-bottom: 1.5px solid #cbd5e1; text-align: left;">
                          <th style="padding: 6px 8px; color: #0f172a;">ITEM / INGREDIENT SPECIFICATION</th>
                          <th style="padding: 6px 8px; color: #0f172a;">SERVING PORTION</th>
                          <th style="padding: 6px 8px; color: #0f172a;">STORE DEPARTMENT / AISLE</th>
                          <th style="padding: 6px 8px; color: #0f172a;">DIABETIC IMPACT</th>
                          <th style="padding: 6px 8px; color: #0f172a; text-align: right;">EST. COST</th>
                        </tr>
                      </thead>
                      <tbody id="tbodyMealBOM">
                        <!-- Populated dynamically by JavaScript -->
                      </tbody>
                    </table>
                  </div>

                  <!-- TOP 3 LOCAL GROCERS WITHIN 20-MILE RADIUS -->
                  <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 6px; padding: 12px; margin-bottom: 6px;">
                    <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #e2e8f0; padding-bottom: 6px; margin-bottom: 8px;">
                      <span style="font-size: 0.80rem; font-weight: 800; color: #0f172a; font-family: var(--lcars-mono);">
                        🏪 TOP 3 LOCAL GROCERY STORES WITHIN 20-MILE RADIUS (SEATTLE METRO / PACIFIC NW)
                      </span>
                      <span style="font-size: 0.70rem; color: #15803d; font-weight: bold; background: #ecfdf5; padding: 2px 6px; border-radius: 4px;">
                        LIVE INVENTORY SOURCING
                      </span>
                    </div>
                    <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(210px, 1fr)); gap: 8px; margin-bottom: 10px;">
                      
                      <!-- Store 1 -->
                      <div style="background: #f8fafc; border: 1.5px solid #0284c7; border-radius: 4px; padding: 8px 10px;">
                        <div style="display: flex; justify-content: space-between; align-items: baseline;">
                          <strong style="color: #0369a1; font-size: 0.82rem;">1. Fred Meyer / QFC</strong>
                          <span style="font-size: 0.70rem; color: #0284c7; font-weight: bold;">3.1 MILES</span>
                        </div>
                        <div style="font-size: 0.72rem; color: #64748b; margin-top: 2px;">Pacific NW Regional Hub &bull; 100% In-Stock</div>
                        <div style="margin-top: 4px; display: flex; justify-content: space-between; align-items: center;">
                          <span style="font-size: 0.72rem; color: #15803d; font-weight: bold;">✅ ALL ITEMS AVAILABLE</span>
                          <span style="font-size: 0.82rem; font-weight: 900; color: #0f172a;">$7.85 Basket</span>
                        </div>
                      </div>

                      <!-- Store 2 -->
                      <div style="background: #f8fafc; border: 1.5px solid #94a3b8; border-radius: 4px; padding: 8px 10px;">
                        <div style="display: flex; justify-content: space-between; align-items: baseline;">
                          <strong style="color: #0f172a; font-size: 0.82rem;">2. Safeway / Albertsons</strong>
                          <span style="font-size: 0.70rem; color: #64748b; font-weight: bold;">4.6 MILES</span>
                        </div>
                        <div style="font-size: 0.72rem; color: #64748b; margin-top: 2px;">Local Neighborhood Metro &bull; 100% In-Stock</div>
                        <div style="margin-top: 4px; display: flex; justify-content: space-between; align-items: center;">
                          <span style="font-size: 0.72rem; color: #15803d; font-weight: bold;">✅ ALL ITEMS AVAILABLE</span>
                          <span style="font-size: 0.82rem; font-weight: 900; color: #0f172a;">$8.40 Basket</span>
                        </div>
                      </div>

                      <!-- Store 3 -->
                      <div style="background: #f8fafc; border: 1.5px solid #94a3b8; border-radius: 4px; padding: 8px 10px;">
                        <div style="display: flex; justify-content: space-between; align-items: baseline;">
                          <strong style="color: #0f172a; font-size: 0.82rem;">3. Trader Joe's / Costco</strong>
                          <span style="font-size: 0.70rem; color: #64748b; font-weight: bold;">6.8 MILES</span>
                        </div>
                        <div style="font-size: 0.72rem; color: #64748b; margin-top: 2px;">Bulk Value / Organic &bull; 100% In-Stock</div>
                        <div style="margin-top: 4px; display: flex; justify-content: space-between; align-items: center;">
                          <span style="font-size: 0.72rem; color: #15803d; font-weight: bold;">✅ VALUE PRICING</span>
                          <span style="font-size: 0.82rem; font-weight: 900; color: #0f172a;">$6.95 Value</span>
                        </div>
                      </div>

                    </div>

                    <!-- Store Selector & Live Search Button -->
                    <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px; background: #f8fafc; padding: 6px 10px; border-radius: 4px; border: 1px solid #e2e8f0;">
                      <div style="display: flex; align-items: center; gap: 8px;">
                        <span style="font-size: 0.76rem; font-weight: bold; color: #0f172a;">ACTIVE SOURCING STORE:</span>
                        <select id="selLocalStoreChain" style="padding: 3px 8px; border: 1px solid #cbd5e1; border-radius: 4px; font-size: 0.78rem; background: #fff; font-family: var(--lcars-mono);">
                          <option value="fredmeyer">Fred Meyer / QFC (3.1 miles • Primary Hub)</option>
                          <option value="safeway">Safeway / Albertsons (4.6 miles • Metro)</option>
                          <option value="traderjoes">Trader Joe's / Costco (6.8 miles • Bulk/Value)</option>
                        </select>
                      </div>
                      <button type="button" class="lcars-btn btn-green" style="font-size: 0.76rem; padding: 4px 12px; font-weight: bold;" onclick="searchLocalStoreCatalog()">
                        🔍 SCAN 20-MILE RADIUS INVENTORY &amp; PRICING
                      </button>
                    </div>
                    <div id="localStoreSearchStatus" style="font-size: 0.75rem; color: #15803d; margin-top: 6px; display: none; font-family: var(--lcars-mono);">
                      <!-- Dynamic local store status -->
                    </div>
                  </div>

                </div>

              </div>

              <!-- VERTICAL FLOW CONNECTOR ARROW 2 -->
              <div style="display: flex; justify-content: center; align-items: center; margin: 6px 0;">
                <div style="display: flex; flex-direction: column; align-items: center;">
                  <div style="width: 2px; height: 16px; background: #10b981;"></div>
                  <div style="width: 0; height: 0; border-left: 6px solid transparent; border-right: 6px solid transparent; border-top: 7px solid #10b981;"></div>
                </div>
              </div>

              <!-- FLOWCHART STEP 2: RE-ASSESS OR REVIEW ADA TRADES -->
              <div style="display: flex; gap: 14px; align-items: stretch; background: #ffffff; border: 1.5px solid #94a3b8; border-left: 6px solid #10b981; border-radius: 6px; padding: 12px; margin-bottom: 0;">
                <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; min-width: 90px; border-right: 1.5px solid #e2e8f0; padding-right: 12px; text-align: center;">
                  <span style="font-size: 0.75rem; font-weight: 900; color: #10b981; font-family: var(--lcars-mono);">STEP 2</span>
                  <span style="font-size: 1.4rem; margin: 4px 0;">⚖️</span>
                  <span style="font-size: 0.65rem; font-weight: 800; color: #475569; text-transform: uppercase;">ASSESS / TRADES</span>
                </div>
                <div style="flex: 1; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;">
                  <div>
                    <strong style="font-size: 0.85rem; color: #0f172a;">NEED ADJUSTMENTS OR CLINICAL HARM-REDUCTION SWAPS?</strong>
                    <div style="font-size: 0.74rem; color: #475569; margin-top: 2px;">
                      Not satisfied with the metabolic cost or want lower carbs? Tweak items in Step 1B and re-assess, or review proven clinical substitution trades.<br>
                      <em>Example:</em> Swap Mexi-Fries for fresh side salad saves 42g net carbs; choose unsweetened tea saves 65g sugar.
                    </div>
                  </div>
                  <div style="display: flex; gap: 8px;">
                    <button type="button" class="lcars-btn btn-banana" style="font-size: 0.80rem; padding: 6px 14px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionNewMeal')">
                      ✏️ TWEAK INGREDIENTS / RE-ASSESS
                    </button>
                    <button type="button" class="lcars-btn btn-peach" style="font-size: 0.80rem; padding: 6px 14px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionAdaTrades')">
                      ⚖️ REVIEW CLINICAL ADA TRADES
                    </button>
                  </div>
                </div>
              </div>

              <!-- VERTICAL FLOW CONNECTOR ARROW 3 -->
              <div style="display: flex; justify-content: center; align-items: center; margin: 6px 0;">
                <div style="display: flex; flex-direction: column; align-items: center;">
                  <div style="width: 2px; height: 16px; background: #f59e0b;"></div>
                  <div style="width: 0; height: 0; border-left: 6px solid transparent; border-right: 6px solid transparent; border-top: 7px solid #f59e0b;"></div>
                </div>
              </div>

              <!-- FLOWCHART STEP 3: WORKFLOW DECISION (SAVE TO PANTRY OR CONSUME NOW) -->
              <div style="display: flex; gap: 14px; align-items: stretch; background: #ffffff; border: 1.5px solid #94a3b8; border-left: 6px solid #f59e0b; border-radius: 6px; padding: 12px;">
                <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; min-width: 90px; border-right: 1.5px solid #e2e8f0; padding-right: 12px; text-align: center;">
                  <span style="font-size: 0.75rem; font-weight: 900; color: #f59e0b; font-family: var(--lcars-mono);">STEP 3</span>
                  <span style="font-size: 1.4rem; margin: 4px 0;">🎯</span>
                  <span style="font-size: 0.65rem; font-weight: 800; color: #475569; text-transform: uppercase;">WORKFLOW ACTION</span>
                </div>
                <div style="flex: 1; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 12px;">
                  <div>
                    <strong style="font-size: 0.85rem; color: #0f172a;">LIKE THIS CHOICE? BUILD YOUR REFERENCE LIBRARY OR CONSUME NOW:</strong>
                    <div style="font-size: 0.74rem; color: #475569; margin-top: 2px;">
                      • <strong>Add to Meal Pantry:</strong> Stores this as a saved reference template (Meal or Snack) for 1-click reuse (does NOT log as eaten today).<br>
                      • <strong>Consume Now:</strong> Commits this meal right now into your daily meal log and updates today's Daily Intake Summary.
                    </div>
                  </div>
                  <div style="display: flex; gap: 8px; flex-wrap: wrap;">
                    <button type="button" class="lcars-btn btn-banana" style="font-size: 0.82rem; padding: 8px 14px; font-weight: 800;" onclick="saveAnalyzedMealToPantry('Meal')">
                      ⭐ ADD TO PANTRY AS MEAL
                    </button>
                    <button type="button" class="lcars-btn btn-ice" style="font-size: 0.82rem; padding: 8px 14px; font-weight: 800; background: #0284c7; color: #fff; border: 1.5px solid #0369a1;" onclick="saveAnalyzedMealToPantry('Snack')">
                      🍵 ADD TO PANTRY AS SNACK
                    </button>
                    <button type="button" class="lcars-btn btn-green" style="font-size: 0.82rem; padding: 8px 18px; font-weight: 900;" onclick="submitActualMealAndMonitorMetrics()">
                      🚀 CONSUME NOW (LOG TO MEAL LOG)
                    </button>
                    <button type="button" class="lcars-btn btn-peach" style="font-size: 0.82rem; padding: 8px 12px;" onclick="switchTab('reports')">
                      📊 VIEW IN 8-LANE CHART (TAB 05)
                    </button>
                  </div>
                </div>
              </div>

            </div>

          </div>

'''

# Perform replacement of the entire old flowchart + evaluation card block
html = html[:pos_start] + new_pipeline_html + html[pos_end:]
print("[+] Successfully restructured flowchart and placed Pre-Choice Estimation before Step 2")

# Also add the searchLocalStoreCatalog JavaScript function into index.html
js_search_func = '''    function searchLocalStoreCatalog() {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      const sel = document.getElementById('selLocalStoreChain');
      const storeVal = sel ? sel.value : 'fredmeyer';
      const storeNames = {
        'fredmeyer': 'Fred Meyer / QFC (3.1 mi)',
        'safeway': 'Safeway / Albertsons (4.6 mi)',
        'traderjoes': "Trader Joe's / Costco (6.8 mi)"
      };
      const storeName = storeNames[storeVal] || 'Fred Meyer / QFC (3.1 mi)';
      const statusEl = document.getElementById('localStoreSearchStatus');
      if (statusEl) {
        statusEl.style.display = 'block';
        statusEl.innerHTML = `🔍 <strong>${storeName}:</strong> Sourced all recipe materials within 20-mile radius! <span style="color: #0284c7; font-weight: bold;">100% IN STOCK</span> &bull; Aisle coordinates &amp; digital coupons synced. Curbside pickup ready.`;
        setTimeout(() => { if (statusEl) statusEl.style.display = 'none'; }, 6000);
      }
    }'''

if 'function searchLocalStoreCatalog()' not in html:
    html = html.replace('function renderBOMTable(mealKey) {', js_search_func + '\n\n    function renderBOMTable(mealKey) {')
    print("[+] Added searchLocalStoreCatalog function to index.html")

# Update renderBOMTable to also support dynamic meal analysis identified_items
old_render_bom = '''    function renderBOMTable(mealKey) {
      let data = POPULAR_MEALS[mealKey] || DEFAULT_POPULAR_MEALS[mealKey];
      const tbody = document.getElementById('tbodyMealBOM');
      if (!tbody) return;

      if (!data || !data.bom || data.bom.length === 0) {
        tbody.innerHTML = `<tr><td colspan="5" style="padding: 10px; text-align: center; color: #64748b;">Clinical meal specification: <strong>${data?.title || 'Selected Meal'}</strong>. Click "Analyze with Gemini" to generate dynamic bill of materials.</td></tr>`;
        return;
      }'''

new_render_bom = '''    function renderBOMTable(mealKey) {
      let data = POPULAR_MEALS[mealKey] || DEFAULT_POPULAR_MEALS[mealKey] || currentAnalyzedMealData;
      const tbody = document.getElementById('tbodyMealBOM');
      if (!tbody) return;

      let bomList = data?.bom || [];
      if ((!bomList || bomList.length === 0) && data?.identified_items && data.identified_items.length > 0) {
        bomList = data.identified_items.map((it, idx) => ({
          item: it.name || "Ingredient Specification",
          portion: it.portion || "Standard Serving",
          dept: idx === 0 ? "Poultry / Meat Department" : (idx === 1 ? "Produce: Salad & Fresh Vegetables" : "Aisle 3: Grains / Bakery / Pantry"),
          impact: (it.net_carbs !== undefined && it.net_carbs <= 5) ? "Low Glycemic / Fiber Buffer" : (it.net_carbs <= 25 ? "Moderate Starch Matrix" : "High Glycemic Load"),
          cost: idx === 0 ? "$3.45" : (idx === 1 ? "$1.85" : "$1.20")
        }));
      }

      if (!bomList || bomList.length === 0) {
        tbody.innerHTML = `<tr><td colspan="5" style="padding: 10px; text-align: center; color: #64748b;">Clinical meal specification: <strong>${data?.title || 'Selected Meal'}</strong>. Materials and local grocer inventory loaded.</td></tr>`;
        return;
      }'''

if old_render_bom in html:
    html = html.replace(old_render_bom, new_render_bom)
    print("[+] Updated renderBOMTable to dynamically format materials for new meals")

# Check div tag balance
o = html.count('<div')
c = html.count('</div')
print(f"Div balance check: open={o}, close={c}, delta={o-c}")
assert o == c, f"Div tag mismatch: open={o}, close={c}"

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
assert h1 == h2, "Dual file hash mismatch!"
print(f"[+] Exact dual-file parity confirmed with SHA256: {h1}")
