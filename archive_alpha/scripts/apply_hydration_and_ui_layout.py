import re
import hashlib

def patch_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        html = f.read()

    # 1. Update Tab 05 Header & remove active goals button + gemini coach active
    old_header = """          <div class="setup-section" style="border-left-color: #16a34a; margin-bottom: 22px; background: #ffffff; border: 1.5px solid #cbd5e1; border-left: 6px solid #16a34a; border-radius: 8px; padding: 18px;">
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
                  VIEW ACTIVE DIABETIC GOALS (TAB 11)
                </button>
              </div>"""

    new_header = """          <div class="setup-section" style="border-left-color: #16a34a; margin-bottom: 22px; background: #ffffff; border: 1.5px solid #cbd5e1; border-left: 6px solid #16a34a; border-radius: 8px; padding: 18px;">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 14px;">
              <div>
                <span style="color: #15803d; font-size: 1.2rem; font-weight: 900; letter-spacing: 0.05em;">
                  🥗 INTERACTIVE DIABETIC MEAL BUILDER &amp; GEMINI NUTRITION COACH
                </span>
                <span style="font-size: 0.82rem; color: #475569; display: block; margin-top: 2px;">
                  ENTER YOUR MEAL REQUEST &bull; GEMINI PREDICTS GLYCEMIC RESPONSE &amp; ENSURES ADHERENCE TO CLINICAL GOALS
                </span>
              </div>
            </div>"""

    assert old_header in html, f"old_header not found in {filepath}"
    html = html.replace(old_header, new_header, 1)

    # 2. Update listbox options: remove Chipotle, Steak, Salmon, Scrambled eggs/spinach, add beverage in oz
    old_listbox = """              <select id="listboxPopularMeals" size="5" style="width: 100%; border: 1.5px solid #0284c7; border-radius: 6px; padding: 6px 8px; font-size: 0.88rem; font-family: inherit; color: #0f172a; background: #ffffff; cursor: pointer; box-shadow: inset 0 1px 3px rgba(0,0,0,0.06);" onchange="onSelectPopularMealFromListbox(this.value)">
                <option value="tacotime_fiesta">🌮 Taco Time Northwest: Fiesta Platter &amp; Drink (102g Net Carbs • $14.75)</option>
                <option value="dicks_combo">🍔 Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Fries &amp; Diet (95g Net Carbs • $12.40)</option>
                <option value="dicks_deluxe">🍔 Dick's Drive-In: Single Deluxe Burger (28g Net Carbs • Goal Met • $5.30)</option>
                <option value="chipotle">🥗 Chipotle Mexican Grill: Chicken Salad Bowl &amp; Guacamole (33g Net Carbs • Goal Met • $11.85)</option>
                <option value="meal01">🥣 Mon-Sat High-Fiber Metabolic Breakfast (Oats, Berries, Turkey Sausage) (38g Net Carbs • Goal Met • $2.85)</option>
                <option value="meal02">🍳 Sunday Protein &amp; Sprouted Toast Breakfast (Eggs, Sausage, Toast) (17g Net Carbs • Goal Met • $3.20)</option>
                <option value="salmon">🍣 Wild Pacific Salmon, Roasted Asparagus &amp; Half Sweet Potato (24g Net Carbs • Goal Met • $14.50)</option>
                <option value="steak">🥩 Grilled Sirloin Steak, Garlic Broccoli &amp; Side Salad (8g Net Carbs • Goal Met • $16.00)</option>
                <option value="pizza">🍕 2 Slices Thin-Crust Veggie Pizza &amp; Unsweetened Iced Tea (48g Net Carbs • $9.50)</option>
              </select>"""

    new_listbox = """              <select id="listboxPopularMeals" size="5" style="width: 100%; border: 1.5px solid #0284c7; border-radius: 6px; padding: 6px 8px; font-size: 0.88rem; font-family: inherit; color: #0f172a; background: #ffffff; cursor: pointer; box-shadow: inset 0 1px 3px rgba(0,0,0,0.06);" onchange="onSelectPopularMealFromListbox(this.value)">
                <option value="meal01">🥣 Mon-Sat High-Fiber Metabolic Breakfast (MEAL-01) (38g Net Carbs • 8 oz Beverage • 430 kcal)</option>
                <option value="meal02">🍳 Sunday Protein &amp; Sprouted Toast Breakfast (MEAL-02) (17g Net Carbs • 8 oz Beverage • 385 kcal)</option>
                <option value="soup_grilled_cheese">🍲 Weekly Vegetable &amp; Meat Soup with Whole Wheat Grilled Cheese, Mini Orange &amp; Apple Slices (34g Net Carbs • 16 oz Beverage • 493 kcal)</option>
                <option value="dicks">🍔 Dick's Drive-In Deluxe Burger (Seattle 1954) (28g Net Carbs • 12 oz Beverage • 420 kcal)</option>
                <option value="dicks_combo">🍔 Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Fries &amp; Large Diet (95g Net Carbs • 24 oz Beverage • 1260 kcal)</option>
                <option value="snack_clove_tea">🍵 [Snack] Brewed Clove &amp; Ceylon Cinnamon Herbal Infusion (0.5g Net Carbs • 16 oz Beverage • 2 kcal)</option>
                <option value="snack_apple_walnut">🍏 [Snack] Crisp Green Apple Slices &amp; Raw California Walnuts (14g Net Carbs • 12 oz Beverage • 240 kcal)</option>
              </select>"""

    assert old_listbox in html, f"old_listbox not found in {filepath}"
    html = html.replace(old_listbox, new_listbox, 1)

    # 3. Update Meal Request Input Box: left-justified max-width + beverage volume in oz
    old_input_box = """            <!-- User Meal Request Input Box -->
            <div style="display: flex; gap: 10px; margin-bottom: 14px;">
              <textarea id="txtMealBuilderInput" rows="2" style="flex: 1; padding: 8px 12px; border: 1.5px solid #cbd5e1; border-radius: 6px; font-size: 0.88rem; font-family: sans-serif; color: #0f172a; background: #fff;" placeholder="Describe your planned meal or restaurant order (e.g., 'Chipotle salad bowl with chicken, black beans, fajita peppers, salsa, guacamole, and sour cream')">Chipotle burrito bowl with brown rice, black beans, grilled chicken, fajita veggies, fresh tomato salsa, cheese, lettuce, and guacamole.</textarea>
              <button class="lcars-btn btn-green" style="font-size: 0.9rem; padding: 8px 18px; font-weight: 900; white-space: nowrap;" onclick="analyzeMealWithGemini()">
                ✨ ANALYZE WITH GEMINI
              </button>
            </div>"""

    new_input_box = """            <!-- User Meal Request Input Box & Hydration Specification (Left-Justified Max Width) -->
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

    assert old_input_box in html, f"old_input_box not found in {filepath}"
    html = html.replace(old_input_box, new_input_box, 1)

    # 4. Insert Hydration Tile into geminiMealAnalysisResult
    old_chips_end = """                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                  <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">GLYCEMIC LOAD</div>
                  <div style="font-size: 1.15rem; font-weight: 900; color: #15803d;" id="chipGlycemicLoad">14 (MED)</div>
                </div>
              </div>"""

    new_chips_end = """                <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 6px 10px; text-align: center;">
                  <div style="font-size: 0.7rem; color: #64748b; font-weight: bold;">GLYCEMIC LOAD</div>
                  <div style="font-size: 1.15rem; font-weight: 900; color: #0284c7;" id="chipGlycemicLoad">14 (MED)</div>
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
                    BEVERAGE: 8.0 FL OZ (237 mL)
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
                    <div style="font-size: 0.92rem; font-weight: 800; color: #15803d;" id="lblHydrationMetabolicImpact">-8 to -14 mg/dL Peak Attenuation</div>
                  </div>
                </div>
                <div style="font-size: 0.76rem; color: #334155; line-height: 1.4;" id="descHydrationKinetics">
                  <strong>Physiological Mechanism:</strong> Adequate hydration expands intravascular plasma volume, blunting postprandial glucose concentration peaks and preventing hemoconcentration artifacts in optical biosensor readings. Supports renal glucosuric excretion above 180 mg/dL threshold.
                </div>
              </div>"""

    assert old_chips_end in html, f"old_chips_end not found in {filepath}"
    html = html.replace(old_chips_end, new_chips_end, 1)

    # 5. Update DEFAULT_POPULAR_MEALS in JavaScript
    old_default_js_re = re.compile(r'const DEFAULT_POPULAR_MEALS = \{.*?\n    \};\n\n    // Backward compatibility alias for presets', re.DOTALL)
    
    new_default_js = """const DEFAULT_POPULAR_MEALS = {
      meal01: {
        id: "MEAL-01",
        title: "Mon-Sat High-Fiber Metabolic Breakfast",
        desc: "1/2 cup fresh berries, 3 all-natural turkey sausage links, 1/2 banana sliced, 1/2 cup steel cut oatmeal prepared with Ceylon cinnamon and 1 tbsp ground flax seed, unsweetened oat milk.",
        calories: 430,
        netCarbs: 38,
        totalCarbs: 48,
        fiber: 10,
        protein: 24,
        fat: 14,
        gl: "12 (MED-LOW)",
        fluidOz: 8.0,
        occasion: "Breakfast",
        meetsGoal: true,
        tips: [
          "Steel cut oats and ground flaxseed form a viscous matrix slowing carbohydrate digestion.",
          "8 oz unsweetened oat milk provides essential hydration with zero added sugar (<1g net carb).",
          "15-minute GLUT4 post-meal stroll keeps glucose peak < 122 mg/dL."
        ],
        glut4: "<strong>15-Minute Post-Meal Stroll:</strong> Light walking starting 25 min post-meal completely dampens the predicted 38 mg/dL curve down to < 14 mg/dL.",
        glut4Peak: "PREDICTED PEAK WITH WALK: 122 MG/DL (STEADY COMPLIANCE)",
        bom: [
          { item: "Organic Steel Cut Oats (Bob's Red Mill / Quaker)", portion: "1/2 cup dry (40g)", dept: "Aisle 4: Hot Breakfast Grains", impact: "High Beta-Glucan Soluble Fiber", cost: "$0.42" },
          { item: "Fresh Organic Mixed Berries (Blueberries & Blackberries)", portion: "1/2 cup (75g)", dept: "Produce: Fresh Berry Cooler", impact: "Low GI Polyphenols & Antioxidants", cost: "$0.85" },
          { item: "All-Natural Turkey Sausage Links (Jennie-O / Applegate)", portion: "3 cooked links (60g)", dept: "Meat: Poultry & Sausage Case", impact: "Lean Protein / Albumin Satiety", cost: "$0.95" },
          { item: "Fresh Banana (Yellow with green tips)", portion: "1/2 medium banana", dept: "Produce: Fresh Fruit Stand", impact: "Resistant Starch & Potassium", cost: "$0.18" },
          { item: "Organic Ground Flaxseed Meal (Spectrum / Bob's)", portion: "1 tbsp (10g)", dept: "Natural Foods / Baking Aisle", impact: "Omega-3 ALA & Lignan Mucilage", cost: "$0.15" },
          { item: "Pure Organic Ceylon Cinnamon Powder", portion: "1/2 tsp (1.5g)", dept: "Aisle 7: Spices & Seasonings", impact: "Cinnamtannin Insulin Mimetic", cost: "$0.08" },
          { item: "Unsweetened Plain Oat Milk (Planet Oat / Califia)", portion: "1/2 cup (120ml)", dept: "Dairy / Plant-Based Beverages", impact: "Zero Added Sugar (<1g Net Carb)", cost: "$0.22" }
        ],
        totalBOMCost: "$2.85 per serving ($17.10 for 6-day package)"
      },
      meal02: {
        id: "MEAL-02",
        title: "Sunday Protein & Sprouted Toast Breakfast",
        desc: "2 large eggs scrambled in butter/olive oil, 3 turkey sausage links, 1 slice 100% whole wheat sprouted bread, 1/2 cup organic berries, fresh black coffee.",
        calories: 385,
        netCarbs: 17,
        totalCarbs: 22,
        fiber: 5,
        protein: 29,
        fat: 19,
        gl: "5 (VERY LOW)",
        fluidOz: 8.0,
        occasion: "Breakfast",
        meetsGoal: true,
        tips: [
          "High albumin protein-to-carb ratio (1.7:1) stimulates GLP-1 without beta-cell strain.",
          "8 oz black coffee / water delivers zero glycemic impact hydration."
        ],
        glut4: "<strong>Relaxed 15-Minute Sunday Stroll:</strong> Keeps digestive motility active and supports Sunday circadian relaxation.",
        glut4Peak: "PREDICTED PEAK: 116 MG/DL (EXCELLENT CONTROL)",
        bom: [
          { item: "Pasture-Raised Grade A Large Eggs (Vital Farms / Pete & Gerry's)", portion: "2 whole eggs", dept: "Dairy: Egg Case", impact: "Choline & Complete Amino Acids", cost: "$0.70" },
          { item: "All-Natural Turkey Sausage Links (Jennie-O / Butterball)", portion: "3 cooked links (60g)", dept: "Meat: Poultry & Breakfast Meats", impact: "Lean Protein / Low Saturated Fat", cost: "$0.95" },
          { item: "100% Whole Wheat Sprouted Bread (Dave's Killer / Ezekiel)", portion: "1 slice toasted (34g)", dept: "Bakery / Whole Grain Bread", impact: "Sprouted Grain Low Glycemic Index", cost: "$0.32" },
          { item: "Fresh Organic Mixed Berries (Blueberries & Raspberries)", portion: "1/2 cup (75g)", dept: "Produce: Organic Fruit Case", impact: "Anthocyanins & Fiber", cost: "$0.85" },
          { item: "Grass-Fed Pure Butter or Extra Virgin Olive Oil", portion: "1 tsp (5g)", dept: "Dairy / Butter Case", impact: "Healthy Lipids & Satiety Buffer", cost: "$0.12" }
        ],
        totalBOMCost: "$2.94 per serving"
      },
      soup_grilled_cheese: {
        id: "MEAL-06",
        title: "Weekly Vegetable & Meat Soup with Whole Wheat Grilled Cheese, Mini Orange & Apple Slices",
        desc: "Weekly vegetable & meat soup, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), 1/2 apple slices, spring water.",
        calories: 493,
        netCarbs: 34,
        totalCarbs: 43.2,
        fiber: 9.2,
        protein: 30.6,
        fat: 22.2,
        gl: "8 (LOW)",
        fluidOz: 16.0,
        occasion: "Lunch",
        meetsGoal: true,
        tips: [
          "Vegetable fiber and beef broth proteins blunt postprandial glycemic excursions.",
          "16 oz hydration (broth + spring water) expands intravascular plasma volume to reduce peak glucose concentration.",
          "Small fruit portioning (1.5oz orange + 1/2 apple) stays strictly within 45g net carb ceiling."
        ],
        glut4: "15-minute post-meal GLUT4 walk promotes peripheral glucose uptake.",
        glut4Peak: "PREDICTED PEAK: 126 MG/DL (OPTIMAL GLYCEMIC REGULATION)"
      },
      dicks: {
        id: "MEAL-04",
        title: "Dick's Drive-In Deluxe Burger (Seattle Iconic 1954)",
        desc: "Dick's Deluxe 100% real beef double patties, melted cheese, crisp lettuce, pickle relish and mayo on a toasted bun, iced water.",
        calories: 420,
        netCarbs: 28,
        totalCarbs: 30,
        fiber: 2,
        protein: 20,
        fat: 26,
        gl: "12 (MED-LOW)",
        fluidOz: 12.0,
        occasion: "Lunch/Dinner",
        meetsGoal: true,
        tips: [
          "28g Net Carbs is well below your 45g ceiling!",
          "Beef albumin and healthy lipid buffer slows carb absorption.",
          "12 oz filtered iced water provides essential hydration with zero glycemic impact."
        ],
        glut4: "15-minute post-meal walk keeps peak < 160 mg/dL.",
        glut4Peak: "PREDICTED PEAK WITH WALK: 128 MG/DL (SAFE TARGET BAND)"
      },
      dicks_combo: {
        id: "MEAL-05",
        title: "Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Fries & Large Diet",
        desc: "Dick's Drive-In order: 2 cheeseburgers, 3 house tartar sauces, 1 regular hand-cut fry, and 1 large diet drink.",
        calories: 1260,
        netCarbs: 95,
        totalCarbs: 101,
        fiber: 6,
        protein: 35,
        fat: 78,
        gl: "38 (HIGH SPIKE)",
        fluidOz: 24.0,
        occasion: "Dinner",
        meetsGoal: false,
        tips: [
          "<strong>Carb Breakdown:</strong> 50g net carbs from 2 cheeseburgers + 44g from hand-cut fries + 1g from 3 tartar sauces + 0g from diet drink = 95g Net Carbs.",
          "<strong>Anticipated Excursion:</strong> Glucose spike of +75 to +90 mg/dL without kinetic intervention.",
          "24 oz diet beverage delivers zero glycemic hydration."
        ],
        glut4: "<strong>Mandatory 35-45 Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Translocates muscle GLUT4 transporters to cell membrane without insulin, forcing glucose directly into working skeletal muscle.",
        glut4Peak: "PREDICTED PEAK WITHOUT WALK: 185-195 MG/DL | WITH 40-MIN WALK: 148 MG/DL (BLUNTED)"
      },
      snack_clove_tea: {
        id: "SNACK-01",
        title: "[Snack] Brewed Clove & Ceylon Cinnamon Herbal Infusion",
        desc: "16 oz hot brewed whole clove & Ceylon cinnamon herbal infusion. Zero sugar, anti-inflammatory, polyphenol rich.",
        calories: 2,
        netCarbs: 0.5,
        totalCarbs: 0.5,
        fiber: 0.0,
        protein: 0.0,
        fat: 0.0,
        gl: "0 (ZERO)",
        fluidOz: 16.0,
        occasion: "Snack",
        meetsGoal: true,
        tips: [
          "16 oz herbal hydration stimulates renal clearance and decreases plasma viscosity.",
          "Eugenol and cinnamtannin B1 improve peripheral insulin receptor sensitivity.",
          "Full snack meal status with zero caloric or glycemic load."
        ],
        glut4: "No postprandial GLUT4 walk required. Glycemic trajectory will remain completely flat in target safe zone.",
        glut4Peak: "PREDICTED PEAK: FLATLINE (< 2 MG/DL EXCURSION)"
      },
      snack_apple_walnut: {
        id: "SNACK-02",
        title: "[Snack] Crisp Green Apple Slices & Raw California Walnuts",
        desc: "1/2 crisp green apple sliced with 1 oz raw California walnut halves and 12 oz chilled sparkling water.",
        calories: 240,
        netCarbs: 14.0,
        totalCarbs: 18.0,
        fiber: 4.0,
        protein: 5.0,
        fat: 18.0,
        gl: "4 (LOW)",
        fluidOz: 12.0,
        occasion: "Snack",
        meetsGoal: true,
        tips: [
          "Omega-3 fatty acids in walnuts slow gastric emptying of fructose.",
          "12 oz sparkling water supports optimal metabolic hydration.",
          "Ideal afternoon bridge meal maintaining steady 90-110 mg/dL glucose baseline."
        ],
        glut4: "15-minute relaxed stroll promotes healthy digestive circulation.",
        glut4Peak: "PREDICTED PEAK: 106 MG/DL (FLAT BASELINE)"
      }
    };

    // Backward compatibility alias for presets"""

    assert old_default_js_re.search(html) is not None, f"old_default_js_re not found in {filepath}"
    html = old_default_js_re.sub(new_default_js, html, count=1)

    # 6. Update onSelectPopularMealFromListbox
    old_onselect = """    function onSelectPopularMealFromListbox(mealKey) {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      const listbox = document.getElementById('listboxPopularMeals');
      const selectedOption = listbox.options[listbox.selectedIndex];
      const mealText = selectedOption ? selectedOption.text : mealKey;

      const input = document.getElementById('txtMealBuilderInput');
      if (input) {
        if (mealKey === 'tacotime_fiesta') {
          input.value = "Taco Time Northwest: Fiesta Platter and Drink";
        } else if (mealKey === 'dicks_combo') {
          input.value = "Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Fries & Large Diet";
        } else if (mealKey === 'dicks_deluxe') {
          input.value = "Dick's Drive-In: Single Deluxe Burger";
        } else if (mealKey === 'chipotle') {
          input.value = "Chipotle Burrito Bowl with chicken, black beans, brown rice, fajita veggies, guacamole";
        } else if (mealKey === 'soup_grilled_cheese') {
          input.value = "Weekly vegetable and meat soup, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), 1/2 apple slices";
        } else if (mealKey === 'steak') {
          input.value = "Grilled Sirloin Steak, garlic broccoli, side salad";
        } else if (mealKey === 'meal01') {
          input.value = "Mon-Sat High-Fiber Metabolic Breakfast (Oats, Berries, Turkey Sausage)";
        } else if (mealKey === 'meal02') {
          input.value = "Sunday Protein & Sprouted Toast Breakfast (Eggs, Sausage, Toast)";
        } else if (mealKey === 'pizza') {
          input.value = "2 Slices Thin-Crust Veggie Pizza & Unsweetened Iced Tea";
        } else {
          input.value = mealText;
        }
      }

      // Automatically trigger Gemini analysis into usual place
      analyzeMealWithGemini(mealKey);
    }"""

    new_onselect = """    function onSelectPopularMealFromListbox(mealKey) {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      const listbox = document.getElementById('listboxPopularMeals');
      const selectedOption = listbox ? listbox.options[listbox.selectedIndex] : null;
      const mealText = selectedOption ? selectedOption.text : mealKey;

      const input = document.getElementById('txtMealBuilderInput');
      const fluidInp = document.getElementById('inpMealFluidOz');
      const m = (typeof POPULAR_MEALS !== 'undefined' && POPULAR_MEALS[mealKey]) ? POPULAR_MEALS[mealKey] : (DEFAULT_POPULAR_MEALS[mealKey] || null);

      if (fluidInp && m && m.fluidOz) {
        fluidInp.value = m.fluidOz;
      }

      if (input) {
        if (m && m.desc) {
          input.value = m.desc;
        } else if (mealKey === 'dicks_combo') {
          input.value = "Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Fries & Large Diet";
        } else if (mealKey === 'dicks') {
          input.value = "Dick's Drive-In: Single Deluxe Burger";
        } else if (mealKey === 'soup_grilled_cheese') {
          input.value = "Weekly vegetable and meat soup, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), 1/2 apple slices";
        } else if (mealKey === 'meal01') {
          input.value = "Mon-Sat High-Fiber Metabolic Breakfast: steel cut oats with cinnamon and flax seed, fresh berries, turkey sausage links, unsweetened oat milk.";
        } else if (mealKey === 'meal02') {
          input.value = "Sunday Protein & Sprouted Toast Breakfast: 2 scrambled eggs, 3 turkey sausage links, 1 slice sprouted toast, 1/2 cup fresh berries, fresh coffee.";
        } else if (mealKey === 'snack_clove_tea') {
          input.value = "16 oz hot brewed whole clove & Ceylon cinnamon herbal infusion. Zero sugar, polyphenol rich.";
        } else if (mealKey === 'snack_apple_walnut') {
          input.value = "1/2 crisp green apple sliced with 1 oz raw California walnut halves and 12 oz chilled sparkling water.";
        } else {
          input.value = mealText.split('(')[0].trim();
        }
      }

      // Automatically trigger Gemini analysis
      analyzeMealWithGemini(mealKey);
    }"""

    assert old_onselect in html, f"old_onselect not found in {filepath}"
    html = html.replace(old_onselect, new_onselect, 1)

    # 7. Update analyzeMealWithGemini to handle fluidOz and update hydration tile
    old_call_body = """            meal_name: rawText || "Custom Ambulatory Meal",
            description: rawText,
            source: "LCARS_MEAL_BUILDER"
          }),"""

    new_call_body = """            meal_name: rawText || "Custom Ambulatory Meal",
            description: rawText,
            source: "LCARS_MEAL_BUILDER",
            fluid_oz: parseFloat(document.getElementById('inpMealFluidOz')?.value) || 8.0
          }),"""

    assert old_call_body in html, f"old_call_body not found in {filepath}"
    html = html.replace(old_call_body, new_call_body, 1)

    old_update_dom = """      const elGL = document.getElementById('chipGlycemicLoad');
      if (elGL) elGL.textContent = data.gl || "12 (MED)";"""

    new_update_dom = """      const elGL = document.getElementById('chipGlycemicLoad');
      if (elGL) elGL.textContent = data.gl || "12 (MED)";

      // Hydration & Biometric Metabolic-Activity Interaction Update
      const userFluidOz = parseFloat(document.getElementById('inpMealFluidOz')?.value) || (data.fluidOz || 8.0);
      data.fluidOz = userFluidOz;
      const elHydVol = document.getElementById('lblHydrationVolumeChip');
      if (elHydVol) elHydVol.textContent = `BEVERAGE: ${userFluidOz.toFixed(1)} FL OZ (${Math.round(userFluidOz * 29.57)} mL)`;

      const userH = (typeof getInt === 'function' ? getInt('cfgHeight', 175) : 175);
      const userW = (typeof getInt === 'function' ? getInt('cfgWeight', 75) : 75);
      const userWeightLbs = Math.round(userW * 2.20462);
      const heightM = userH / 100.0;
      const bmi = (userW / (heightM * heightM)).toFixed(1);

      const elBio = document.getElementById('lblPatientBiometrics');
      if (elBio) elBio.textContent = `Height: ${userH}cm | ${userW}kg (${userWeightLbs} lbs) | BMI: ${bmi} kg/m²`;

      const baselineHyd = Math.round(userWeightLbs * 0.5); // ~83 oz for 165 lbs
      const elHydTgt = document.getElementById('lblDailyHydrationTarget');
      if (elHydTgt) elHydTgt.textContent = `Baseline: ~${baselineHyd} oz + Activity Offset`;

      const elHydImp = document.getElementById('lblHydrationMetabolicImpact');
      if (elHydImp) {
        if (userFluidOz >= 12) {
          elHydImp.textContent = "-10 to -16 mg/dL Peak Attenuation (Optimal)";
          elHydImp.style.color = "#15803d";
        } else if (userFluidOz >= 8) {
          elHydImp.textContent = "-6 to -10 mg/dL Peak Attenuation (Good)";
          elHydImp.style.color = "#0284c7";
        } else {
          elHydImp.textContent = "Minimal Attenuation (<4 oz fluid)";
          elHydImp.style.color = "#d97706";
        }
      }"""

    assert old_update_dom in html, f"old_update_dom not found in {filepath}"
    html = html.replace(old_update_dom, new_update_dom, 1)

    # 8. Update submitActualMealAndMonitorMetrics to pass fluid_oz
    old_submit_call = """          body: JSON.stringify({
            meal_name: currentAnalyzedMealData?.title || mealDesc,
            description: mealDesc,
            source: "DESKTOP_LCARS_CONSOLE"
          })"""

    new_submit_call = """          body: JSON.stringify({
            meal_name: currentAnalyzedMealData?.title || mealDesc,
            description: mealDesc,
            source: "DESKTOP_LCARS_CONSOLE",
            fluid_oz: currentAnalyzedMealData?.fluidOz || parseFloat(document.getElementById('inpMealFluidOz')?.value) || 8.0
          })"""

    assert old_submit_call in html, f"old_submit_call not found in {filepath}"
    html = html.replace(old_submit_call, new_submit_call, 1)

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
