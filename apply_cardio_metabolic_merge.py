import re
import json
import hashlib
import py_compile

# =========================================================================
# STEP 1: FIX FULL SCAN CONTRAST (CLINICAL MULTI-VECTOR SYNTHESIS & EVALUATION)
# =========================================================================
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# 1. Update the synthesis card container styling in index.html
old_synth_html = '''            <!-- Automated Physician & Patient Clinical Interpretation -->
            <div style="background: #06101c; border: 1px solid #1a2f4a; border-radius: 6px; padding: 14px; margin-top: 12px;">
              <strong style="color: var(--lcars-green); font-size: 0.95rem; font-family: var(--lcars-mono);">
                CLINICAL MULTI-VECTOR SYNTHESIS &amp; EVALUATION
              </strong>
              <div id="fullScanClinicalSynthesis" style="font-size: 0.86rem; color: #d0e4f2; line-height: 1.5; margin-top: 6px;">
                <!-- Populated by JavaScript -->
              </div>
            </div>'''

new_synth_html = '''            <!-- Automated Physician & Patient Clinical Interpretation -->
            <div class="fullscan-synthesis-box" style="background: #f0f9ff; border: 1.5px solid #0284c7; border-left: 6px solid #0369a1; border-radius: 6px; padding: 14px; margin-top: 12px;">
              <strong style="color: #0369a1; font-size: 0.95rem; font-family: var(--lcars-mono); display: block; margin-bottom: 6px;">
                CLINICAL MULTI-VECTOR SYNTHESIS &amp; EVALUATION
              </strong>
              <div id="fullScanClinicalSynthesis" style="font-size: 0.88rem; color: #0c4a6e; font-weight: 500; line-height: 1.55;">
                <!-- Populated by JavaScript -->
              </div>
            </div>'''

assert old_synth_html in html, "old_synth_html not found"
html = html.replace(old_synth_html, new_synth_html)
print("[+] Updated Full Scan Clinical Multi-Vector Synthesis card to high-contrast dark blue (#0369a1 / #0c4a6e)")

# 2. Update the JS synthesis text color in initFullScanView and propagateFullScanResults
old_js_syn_dormant = '''        if (syn) {
          syn.innerHTML = `
            <div style="color: var(--lcars-banana); padding: 8px 0; font-size: 0.95rem; line-height: 1.5;">
              <strong>SMARTWATCH HARDWARE SYNCHRONIZED (DORMANT BUFFER / STANDBY):</strong>'''

new_js_syn_dormant = '''        if (syn) {
          syn.innerHTML = `
            <div style="color: #0c4a6e; padding: 4px 0; font-size: 0.90rem; line-height: 1.55;">
              <strong style="color: #0369a1;">SMARTWATCH HARDWARE SYNCHRONIZED (DORMANT BUFFER / STANDBY):</strong>'''

if old_js_syn_dormant in html:
    html = html.replace(old_js_syn_dormant, new_js_syn_dormant)

old_js_syn_active = '''      // Clinical Synthesis Text
      const syn = document.getElementById('fullScanClinicalSynthesis');
      if (syn) {
        syn.innerHTML = `
          Full systematic biometric acquisition completed across all 6 physiological parameters.
          Optical blood glucose reading is <strong>${r.glucose} mg/dL</strong>, which resides safely within your clinical <strong>60–200 mg/dL Time-In-Range</strong> window.
          Hemodynamics indicate normotensive blood pressure (<strong>${r.bp.sys}/${r.bp.dia} mmHg</strong>, pulse pressure ${r.bp.sys - r.bp.dia} mmHg) paired with a resting pulse of <strong>${r.hr} BPM</strong>.
          Arterial oxygen saturation is optimal at <strong>${r.spo2}%</strong> with euvolemic thermoregulation (<strong>${r.temp.bodyF.toFixed(1)}°F</strong>).
          Autonomic HRV analysis demonstrates balanced vagal nerve tone (<strong>${r.hrv.hrv} ms</strong> SDNN, stress index ${r.hrv.stress}/100), reflecting low sympathetic strain.
        `;
      }'''

new_js_syn_active = '''      // Clinical Synthesis Text
      const syn = document.getElementById('fullScanClinicalSynthesis');
      if (syn) {
        syn.innerHTML = `
          <div style="color: #0c4a6e; line-height: 1.55;">
            Full systematic biometric acquisition completed across all 6 physiological parameters.<br>
            Optical blood glucose reading is <strong style="color: #0369a1;">${r.glucose} mg/dL</strong>, which resides safely within your clinical <strong style="color: #15803d;">60–200 mg/dL Time-In-Range</strong> window.<br>
            Hemodynamics indicate normotensive blood pressure (<strong style="color: #0369a1;">${r.bp.sys}/${r.bp.dia} mmHg</strong>, pulse pressure ${r.bp.sys - r.bp.dia} mmHg) paired with a resting pulse of <strong style="color: #0369a1;">${r.hr} BPM</strong>.<br>
            Arterial oxygen saturation is optimal at <strong style="color: #15803d;">${r.spo2}%</strong> with euvolemic thermoregulation (<strong style="color: #0369a1;">${r.temp.bodyF.toFixed(1)}°F</strong>).<br>
            Autonomic HRV analysis demonstrates balanced vagal nerve tone (<strong style="color: #0369a1;">${r.hrv.hrv} ms</strong> SDNN, stress index ${r.hrv.stress}/100), reflecting low sympathetic strain.
          </div>
        `;
      }'''

if old_js_syn_active in html:
    html = html.replace(old_js_syn_active, new_js_syn_active)
    print("[+] Updated JS synthesis text to high-contrast dark blue (#0c4a6e / #0369a1)")


# =========================================================================
# STEP 2: RESTAURANT MEALS VS HOME RECIPES BOM LOGIC
# =========================================================================
# In renderBOMTable, detect if the meal is from a restaurant / commercial prepared eatery
# (e.g. Dairy Queen, Taco Time, Dick's, fast food, restaurant, sundae, etc.)
# If it is a restaurant, hide the raw grocery BOM and instead display the Restaurant Sourcing Card!
old_render_bom = '''    function renderBOMTable(mealKey) {
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

new_render_bom = '''    function isRestaurantOrPreparedMeal(data) {
      if (!data) return false;
      const title = (data.title || data.meal_name || "").toLowerCase();
      const desc = (data.desc || data.description || "").toLowerCase();
      const vendor = (data.vendor_matched || "").toLowerCase();
      const keywords = [
        "dairy queen", "dq", "sundae", "blizzard", "ice cream",
        "taco time", "tacotime", "dick's", "dicks", "mcdonald", "burger king",
        "wendy", "starbucks", "restaurant", "drive-in", "drivein", "fast food",
        "takeout", "cafe", "diner", "pizzeria", "bistro", "eatery"
      ];
      return keywords.some(k => title.includes(k) || desc.includes(k) || vendor.includes(k));
    }

    function renderBOMTable(mealKey) {
      let data = POPULAR_MEALS[mealKey] || DEFAULT_POPULAR_MEALS[mealKey] || currentAnalyzedMealData;
      const tbody = document.getElementById('tbodyMealBOM');
      const cardBOM = document.getElementById('cardMealBOM');
      const cardRest = document.getElementById('cardRestaurantOrderGuide');

      const isRest = isRestaurantOrPreparedMeal(data);
      if (cardRest) cardRest.style.display = isRest ? 'block' : 'none';
      if (cardBOM) {
        // If restaurant, show restaurant disclosure instead of grocery shopping list
        const bomTableContainer = document.getElementById('containerBOMTableWrapper');
        const groceryStoreList = document.getElementById('containerTop3Grocers');
        if (bomTableContainer) bomTableContainer.style.display = isRest ? 'none' : 'block';
        if (groceryStoreList) groceryStoreList.style.display = isRest ? 'none' : 'block';
      }

      if (isRest && cardRest) {
        const titleEl = document.getElementById('lblRestOrderTitle');
        if (titleEl) titleEl.textContent = data.title || "Commercial Restaurant Order";
        const vendorEl = document.getElementById('lblRestOrderVendor');
        if (vendorEl) vendorEl.textContent = data.vendor_matched || "Published Commercial Restaurant Disclosure";
        const costEl = document.getElementById('lblRestOrderCost');
        if (costEl) costEl.textContent = `~$${data.estimatedCost || "7.50"}`;
        const tipsEl = document.getElementById('listRestOrderTactics');
        if (tipsEl) {
          tipsEl.innerHTML = `
            <li><strong>Commercial Prepared Food:</strong> Sourced directly from local restaurant within 20 miles. No grocery shopping or home recipe assembly required.</li>
            <li><strong>Diabetic Harm-Reduction Tip:</strong> If craving a treat (e.g., Dairy Queen sundae), choose small or mini size (-18g net carbs), skip extra fudge/candy toppings, and schedule a 25-minute post-meal GLUT4 stroll.</li>
            <li><strong>Hydration Pairing:</strong> Pair with 16 oz iced water or unsweetened tea to prevent dehydration and rapid glycemic spiking.</li>
          `;
        }
        return;
      }

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

assert old_render_bom in html, "old_render_bom not found in index.html"
html = html.replace(old_render_bom, new_render_bom)
print("[+] Added isRestaurantOrPreparedMeal and conditional BOM / Restaurant Disclosure routing")

# 3. Add the cardRestaurantOrderGuide element inside cardMealBOM in index.html
old_card_bom_head = '''                  <!-- BOM Table -->
                  <div style="overflow-x: auto; margin-bottom: 12px;">'''

new_card_bom_head = '''                  <!-- Restaurant Direct Sourcing Card (Shown when restaurant/prepared food is evaluated) -->
                  <div id="cardRestaurantOrderGuide" style="display: none; background: #ffffff; border: 1.5px solid #f59e0b; border-left: 5px solid #d97706; border-radius: 6px; padding: 12px; margin-bottom: 12px;">
                    <div style="display: flex; justify-content: space-between; align-items: baseline; border-bottom: 1px solid #fed7aa; padding-bottom: 6px; margin-bottom: 8px;">
                      <div>
                        <strong style="color: #b45309; font-size: 0.88rem; font-family: var(--lcars-mono);" id="lblRestOrderTitle">
                          🏪 COMMERCIAL RESTAURANT / PREPARED FOOD ORDER
                        </strong>
                        <div style="font-size: 0.72rem; color: #78350f;" id="lblRestOrderVendor">
                          Direct Restaurant Sourcing &bull; Official Nutritional Disclosure
                        </div>
                      </div>
                      <span style="font-size: 0.78rem; font-weight: 900; color: #b45309; background: #ffedd5; padding: 2px 8px; border-radius: 4px;" id="lblRestOrderCost">
                        ~$7.50
                      </span>
                    </div>
                    <ul id="listRestOrderTactics" style="margin: 0; padding-left: 18px; font-size: 0.76rem; color: #451a03; line-height: 1.45;">
                      <!-- Populated dynamically -->
                    </ul>
                  </div>

                  <!-- BOM Table (Shown only for home recipes & grocery meal prep) -->
                  <div id="containerBOMTableWrapper" style="overflow-x: auto; margin-bottom: 12px;">'''

assert old_card_bom_head in html, "old_card_bom_head not found in index.html"
html = html.replace(old_card_bom_head, new_card_bom_head)

# Also wrap the top 3 grocers container with id="containerTop3Grocers"
old_grocers_div = '''                  <!-- TOP 3 LOCAL GROCERS WITHIN 20-MILE RADIUS -->
                  <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 6px; padding: 12px; margin-bottom: 6px;">'''

new_grocers_div = '''                  <!-- TOP 3 LOCAL GROCERS WITHIN 20-MILE RADIUS -->
                  <div id="containerTop3Grocers" style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 6px; padding: 12px; margin-bottom: 6px;">'''

assert old_grocers_div in html, "old_grocers_div not found in index.html"
html = html.replace(old_grocers_div, new_grocers_div)
print("[+] Added Restaurant Ordering Guide and conditional grocery BOM display")


# =========================================================================
# STEP 3: UNIFY CARDIO & METABOLIC INTO 02-METABOLIC
# =========================================================================
# User: "I am thinking merging Cardio and Metabolic into one tab Metabolic as all the data is realted."
# Let's inspect how to unify Cardio and Metabolic:
# 1. Update the sidebar navigation rail:
#    01-FULL SCAN
#    02-METABOLIC (Cardio & Metabolic Unified)
#    03-MEAL PLANNER
#    04-REPORTS
#    05-EXPORT
#    06-LEARNING
#    ...
# 2. Keep switchTab('cardio') functional by having switchTab('cardio') route to switchTab('metabolic')
#    and smooth-scroll to the Cardiac section!
old_sidebar_buttons = '''      <button id="navTabFullScan" class="lcars-pill pill-blue active" onclick="switchTab('fullscan')">
        <span class="tab-label">01-FULL SCAN</span>
      </button>
      <button id="navTabCardio" class="lcars-pill pill-orange" onclick="switchTab('cardio')">
        <span class="tab-label">02-CARDIO</span>
      </button>
      <button id="navTabMetabolic" class="lcars-pill pill-peach" onclick="switchTab('metabolic')">
        <span class="tab-label">03-METABOLIC</span>
      </button>
      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">04-MEAL PLANNER</span>
      </button>
      <button id="navTabReports" class="lcars-pill pill-blue" onclick="switchTab('reports')">
        <span class="tab-label">05-REPORTS</span>
      </button>
      <button id="navTabExport" class="lcars-pill pill-lilac" onclick="switchTab('export')" style="border-left: 5px solid #38bdf8;">
        <span class="tab-label">06-EXPORT</span>
      </button>
      <button id="navTabLearning" class="lcars-pill pill-ice" onclick="switchTab('learning')" style="border-left: 5px solid #0284c7;">
        <span class="tab-label">07-LEARNING</span>
      </button>
      <button id="navTabHelp" class="lcars-pill pill-banana" onclick="switchTab('help')">
        <span class="tab-label">08-HELP &amp; FAQ</span>
      </button>
      <button id="navTabMedication" class="lcars-pill pill-orange" onclick="switchTab('medication')">
        <span class="tab-label">09-MEDICATION</span>
      </button>
      <button id="navTabGoals" class="lcars-pill pill-green" onclick="switchTab('goals')">
        <span class="tab-label">10-CLINICAL GOALS</span>
      </button>
      <button id="navTabSetup" class="lcars-pill pill-lilac" onclick="switchTab('setup')">
        <span class="tab-label">11-CONFIG</span>
      </button>
      <button id="navTabOperations" class="lcars-pill pill-peach" onclick="switchTab('operations')">
        <span class="tab-label">12-OPERATIONS</span>
      </button>
      <button id="navTabLog" class="lcars-pill pill-blue" onclick="switchTab('log')">
        <span class="tab-label">13-DATA LOG</span>
      </button>
      <button id="navTabWatchFaces" class="lcars-pill pill-banana" onclick="switchTab('watchfaces')">
        <span class="tab-label">14-WATCH FACES</span>
      </button>'''

new_sidebar_buttons = '''      <button id="navTabFullScan" class="lcars-pill pill-blue active" onclick="switchTab('fullscan')">
        <span class="tab-label">01-FULL SCAN</span>
      </button>
      <button id="navTabMetabolic" class="lcars-pill pill-peach" onclick="switchTab('metabolic')">
        <span class="tab-label">02-METABOLIC</span>
      </button>
      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">03-MEAL PLANNER</span>
      </button>
      <button id="navTabReports" class="lcars-pill pill-blue" onclick="switchTab('reports')">
        <span class="tab-label">04-REPORTS</span>
      </button>
      <button id="navTabExport" class="lcars-pill pill-lilac" onclick="switchTab('export')" style="border-left: 5px solid #38bdf8;">
        <span class="tab-label">05-EXPORT</span>
      </button>
      <button id="navTabLearning" class="lcars-pill pill-ice" onclick="switchTab('learning')" style="border-left: 5px solid #0284c7;">
        <span class="tab-label">06-LEARNING</span>
      </button>
      <button id="navTabHelp" class="lcars-pill pill-banana" onclick="switchTab('help')">
        <span class="tab-label">07-HELP &amp; FAQ</span>
      </button>
      <button id="navTabMedication" class="lcars-pill pill-orange" onclick="switchTab('medication')">
        <span class="tab-label">08-MEDICATION</span>
      </button>
      <button id="navTabGoals" class="lcars-pill pill-green" onclick="switchTab('goals')">
        <span class="tab-label">09-CLINICAL GOALS</span>
      </button>
      <button id="navTabSetup" class="lcars-pill pill-lilac" onclick="switchTab('setup')">
        <span class="tab-label">10-CONFIG</span>
      </button>
      <button id="navTabOperations" class="lcars-pill pill-peach" onclick="switchTab('operations')">
        <span class="tab-label">11-OPERATIONS</span>
      </button>
      <button id="navTabLog" class="lcars-pill pill-blue" onclick="switchTab('log')">
        <span class="tab-label">12-DATA LOG</span>
      </button>
      <button id="navTabWatchFaces" class="lcars-pill pill-banana" onclick="switchTab('watchfaces')">
        <span class="tab-label">13-WATCH FACES</span>
      </button>
      <!-- Backward-compatible hidden button for test suites & direct triggers -->
      <button id="navTabCardio" style="display: none;" onclick="switchTab('cardio')"></button>'''

assert old_sidebar_buttons in html, "old_sidebar_buttons not found in index.html"
html = html.replace(old_sidebar_buttons, new_sidebar_buttons)
print("[+] Updated sidebar navigation to unified 02-METABOLIC sequence")

# 4. In switchTab, route 'cardio' to 'metabolic' seamlessly
old_switch_cardio = '''      if (tabId === 'vitals') {
        switchTab('fullscan');
        return;
      } else if (tabId === 'cardio') {
        document.getElementById('navTabCardio').classList.add('active');
        document.getElementById('pageCardio').classList.add('active-page');
      } else if (tabId === 'metabolic') {'''

new_switch_cardio = '''      if (tabId === 'vitals') {
        switchTab('fullscan');
        return;
      } else if (tabId === 'cardio') {
        // Cardio is now unified inside Tab 02: METABOLIC
        switchTab('metabolic');
        const cardioSec = document.getElementById('secCardioAutonomicBay');
        if (cardioSec) cardioSec.scrollIntoView({ behavior: 'smooth' });
        return;
      } else if (tabId === 'metabolic') {'''

assert old_switch_cardio in html, "old_switch_cardio not found in index.html"
html = html.replace(old_switch_cardio, new_switch_cardio)

# 5. Move the content of pageCardio directly inside pageMetabolic as a dedicated section:
#    secCardioAutonomicBay (Cardiac, Autonomic HRV/Stress, Sleep Architecture, and Workout/GLUT4 Engine)
#    and retain an empty/bridge pageCardio with id="pageCardio" for DOM compatibility
cardio_start_tag = '<div id="pageCardio" class="tab-page">'
metabolic_start_tag = '<div id="pageMetabolic" class="tab-page">'

p_c_start = html.find(cardio_start_tag)
p_m_start = html.find(metabolic_start_tag)
assert p_c_start != -1 and p_m_start != -1, "Cardio or Metabolic start tag not found"

# Extract the inner HTML of pageCardio
p_c_end = p_m_start
cardio_block = html[p_c_start:p_c_end]
# Remove outer <div id="pageCardio" class="tab-page"> and trailing </div>
cardio_inner = cardio_block[len(cardio_start_tag):]
last_close = cardio_inner.rfind('</div>')
cardio_inner = cardio_inner[:last_close].strip()

# Now create the unified section to inject into pageMetabolic
unified_cardio_section = f'''
        <!-- =========================================================================
             UNIFIED CARDIO-METABOLIC INTEGRATION BAY (HEART RATE, HRV, SLEEP & WORKOUT)
             ========================================================================= -->
        <div id="secCardioAutonomicBay" style="margin-top: 18px; border-top: 2px solid #cbd5e1; padding-top: 16px;">
          <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 12px; flex-wrap: wrap; gap: 8px;">
            <div>
              <span style="color: #0369a1; font-size: 1.15rem; font-weight: 900; font-family: var(--lcars-mono);">
                ❤️ CARDIAC, AUTONOMIC TONE &amp; KINETIC EXERCISE INTEGRATION
              </span>
              <span style="font-size: 0.8rem; color: #64748b; display: block; margin-top: 2px;">
                HEART RATE &bull; AUTONOMIC HRV &amp; STRESS &bull; NOCTURNAL SLEEP RECOVERY &bull; GLUT4 WORKOUT TRACKING
              </span>
            </div>
          </div>
          {cardio_inner}
        </div>
'''

# New pageCardio bridge (maintains backward compatibility with zero visual duplication)
new_page_cardio_bridge = '''      <!-- TAB 2 DOM BRIDGE: Cardio is now integrated directly into 02-METABOLIC -->
      <div id="pageCardio" class="tab-page" style="display: none;"></div>\n\n      '''

# Inject unified_cardio_section right before the closing </div> of pageMetabolic
# Let's find where pageMetabolic ends
page_diet_trades_marker = '      <!-- =====================================================================\n           TAB 05: MEAL PLANNER & GEMINI NUTRITION COACH'
if page_diet_trades_marker not in html:
    page_diet_trades_marker = '<div id="pageDietTrades"'

p_dt_start = html.find(page_diet_trades_marker)
assert p_dt_start != -1, "pageDietTrades marker not found"

# Find the </div> before p_dt_start that closes pageMetabolic
p_m_close = html.rfind('</div>', p_m_start, p_dt_start)
assert p_m_close != -1, "pageMetabolic close div not found"

# Build updated html
html = (
    html[:p_c_start] +
    new_page_cardio_bridge +
    html[p_m_start:p_m_close] +
    unified_cardio_section +
    html[p_m_close:]
)
print("[+] Successfully unified Cardio and Metabolic into a single cohesive 02-METABOLIC console!")

# Update button references pointing to Tab 05 / Tab 04 to reflect the new sequence:
# 01-FULL SCAN
# 02-METABOLIC (Cardio & Metabolic unified)
# 03-MEAL PLANNER
# 04-REPORTS
# 05-EXPORT
# 06-LEARNING
html = html.replace("VIEW IN 8-LANE CHART (TAB 05)", "VIEW IN 8-LANE CHART (TAB 04)")
html = html.replace("VIEW 360° SYNCHRONIZED TALL CHART (TAB 05)", "VIEW 360° SYNCHRONIZED TALL CHART (TAB 04)")
html = html.replace("OPEN 360° SYNCHRONIZED TALL CHART (TAB 05)", "OPEN 360° SYNCHRONIZED TALL CHART (TAB 04)")
html = html.replace("VIEW IN TALL CHART (TAB 05)", "VIEW IN TALL CHART (TAB 04)")
html = html.replace("📊 AUDIT % GOALS MET IN REPORTS (TAB 05)", "📊 AUDIT % GOALS MET IN REPORTS (TAB 04)")
html = html.replace("📊 AUDIT % GOALS MET (TAB 05)", "📊 AUDIT % GOALS MET (TAB 04)")
html = html.replace("SINGLE TALL SYNCHRONIZED MULTI-MEASUREMENT CHART (TAB 05).", "SINGLE TALL SYNCHRONIZED MULTI-MEASUREMENT CHART (TAB 04).")
html = html.replace("timeline on Tab 05", "timeline on Tab 04")
html = html.replace("Tab 06 Export", "Tab 05 Export")
html = html.replace("[ EXPORT CLINICAL CSV ] in Tab 06", "[ EXPORT CLINICAL CSV ] in Tab 05")
html = html.replace("OPEN MEAL PLANNER &amp; COACH (TAB 04)", "OPEN MEAL PLANNER &amp; COACH (TAB 03)")

# Check Div balance
o = html.count('<div')
c = html.count('</div')
print(f"Div tag check: open={o}, close={c}, delta={o-c}")
assert o == c, f"Div mismatch: open={o}, close={c}"

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
assert h1 == h2, "Hashes do not match!"
print(f"[+] Exact dual-file byte parity verified with SHA256: {h1}")
