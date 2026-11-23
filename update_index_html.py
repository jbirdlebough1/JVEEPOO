# update_index_html.py
import re
import hashlib

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# 1. Fix the DOM tree at the end of pageDietTrades
bad_chunk = html[html.find('<!-- Products Dynamic Grid -->'):html.find('<!-- =====================================================================\n           TAB 13: WATCH')]

replacement_chunk = """<!-- Products Dynamic Grid -->
            <div id="productsGrid" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); gap: 12px;">
              <!-- Rendered via JavaScript -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

    """

assert len(bad_chunk) > 50, "bad_chunk not located properly"
html = html.replace(bad_chunk, replacement_chunk)
print("[✔] 1. DOM tree fix applied to pageDietTrades!")

# 2. Update BOM Table header: replace EST. COST with METABOLIC IMPACT (NET CARBS & GL)
old_bom_th = '<th style="padding: 6px 8px; color: #0f172a; text-align: right;">EST. COST</th>'
new_bom_th = '<th style="padding: 6px 8px; color: #0f172a; text-align: right;">METABOLIC IMPACT (NET CARBS &amp; GL)</th>'
assert old_bom_th in html, "old_bom_th not found"
html = html.replace(old_bom_th, new_bom_th)
print("[✔] 2. BOM Table header replaced with METABOLIC IMPACT!")

# 3. Replace restaurant order card cost with Metabolic Impact
old_rest_cost = '<span style="font-size: 0.78rem; font-weight: 900; color: #b45309; background: #ffedd5; padding: 2px 8px; border-radius: 4px;" id="lblRestOrderCost">\n                        ~$7.50\n                      </span>'
new_rest_cost = '<span style="font-size: 0.78rem; font-weight: 900; color: #b45309; background: #ffedd5; padding: 2px 8px; border-radius: 4px;" id="lblRestOrderCost">\n                        METABOLIC IMPACT: GL 18-35 (SPIKE HAZARD)\n                      </span>'
if old_rest_cost in html:
    html = html.replace(old_rest_cost, new_rest_cost)
else:
    html = re.sub(r'<span[^>]*id="lblRestOrderCost"[^>]*>[\s\S]*?</span>', new_rest_cost, html)
print("[✔] 3. Restaurant order card updated to Metabolic Impact!")

# 4. Replace grocer basket prices ($7.85 Basket, etc.)
html = re.sub(r'<span id="bomStore1Basket"[^>]*>.*?</span>', '<span id="bomStore1Basket" style="font-size: 0.82rem; font-weight: 900; color: #15803d;">ADA Compliant Basket</span>', html)
html = re.sub(r'<span id="bomStore2Basket"[^>]*>.*?</span>', '<span id="bomStore2Basket" style="font-size: 0.82rem; font-weight: 900; color: #15803d;">ADA Compliant Basket</span>', html)
html = re.sub(r'<span id="bomStore3Basket"[^>]*>.*?</span>', '<span id="bomStore3Basket" style="font-size: 0.82rem; font-weight: 900; color: #15803d;">ADA Compliant Basket</span>', html)
print("[✔] 4. Grocer basket prices replaced with ADA Compliant Basket!")

# 5. In analyzeMealWithGemini: replace EST. COST with METABOLIC BURDEN
old_el_cost = 'if (elCost) elCost.textContent = `EST. COST: ~$${data.estimatedCost || "14.75"}`;'
new_el_cost = 'if (elCost) elCost.textContent = `METABOLIC BURDEN: ${data.netCarbs}g Net Carbs • Glycemic Load ${data.gl} • Spike +${Math.round(data.netCarbs * 1.8)} mg/dL`;'
assert old_el_cost in html, "old_el_cost not found"
html = html.replace(old_el_cost, new_el_cost)

old_cost_el2 = 'if (costEl) costEl.textContent = `~$${data.estimatedCost || "7.50"}`;'
new_cost_el2 = 'if (costEl) costEl.textContent = `GLYCEMIC LOAD: ${data.gl || "Moderate"} (${data.netCarbs || 25}g Net Carbs)`;'
assert old_cost_el2 in html, "old_cost_el2 not found"
html = html.replace(old_cost_el2, new_cost_el2)
print("[✔] 5. elCost and costEl replaced with Metabolic Burden!")

# 6. Deduplicate Meal Pantry in syncPopularMealsWithServer() and initPopularMeals()
old_sync_popular = """            // Synchronize with #listboxPopularMeals in Meal Pantry
            const listbox = document.getElementById('listboxPopularMeals');
            if (listbox) {
              listbox.innerHTML = '';
              data.popular_meals.forEach(m => {
                const opt = document.createElement('option');
                opt.value = m.id;
                const statusIcon = (m.net_carbs <= 45) ? '✅' : '⚠️';
                opt.textContent = `${statusIcon} ${m.title} (${m.net_carbs}g Net Carbs • ${m.calories} kcal)`;
                listbox.appendChild(opt);
              });
            }"""

new_sync_popular = """            // Synchronize with #listboxPopularMeals in Meal Pantry (DEDUPLICATED BY TITLE)
            const listbox = document.getElementById('listboxPopularMeals');
            if (listbox) {
              listbox.innerHTML = '';
              const seenTitles = new Set();
              data.popular_meals.forEach(m => {
                const normTitle = (m.title || '').trim().toLowerCase();
                if (seenTitles.has(normTitle)) return; // Skip duplicates!
                seenTitles.add(normTitle);

                const opt = document.createElement('option');
                opt.value = m.id;
                const statusIcon = (m.net_carbs <= 45) ? '✅' : '⚠️';
                opt.textContent = `${statusIcon} ${m.title} (${m.net_carbs}g Net Carbs • ${m.calories} kcal)`;
                listbox.appendChild(opt);
              });
            }"""

assert old_sync_popular in html, "old_sync_popular not found"
html = html.replace(old_sync_popular, new_sync_popular)

# Also deduplicate selCommonMealPackage
old_pull_down = """      Object.keys(POPULAR_MEALS).forEach(key => {
        const m = POPULAR_MEALS[key];
        const opt = document.createElement('option');
        opt.value = key;
        const statusIcon = m.netCarbs <= 45 ? '✅' : '⚠️';
        opt.textContent = `[${m.id || key.toUpperCase()}] ${m.title} (${m.netCarbs}g Net Carbs • ${m.calories} kcal) ${statusIcon}`;
        sel.appendChild(opt);
      });"""

new_pull_down = """      const seenDropdownTitles = new Set();
      Object.keys(POPULAR_MEALS).forEach(key => {
        const m = POPULAR_MEALS[key];
        if (!m || !m.title) return;
        const normTitle = m.title.trim().toLowerCase();
        if (seenDropdownTitles.has(normTitle)) return; // Deduplicate!
        seenDropdownTitles.add(normTitle);

        const opt = document.createElement('option');
        opt.value = key;
        const statusIcon = m.netCarbs <= 45 ? '✅' : '⚠️';
        opt.textContent = `[${m.id || key.toUpperCase()}] ${m.title} (${m.netCarbs}g Net Carbs • ${m.calories} kcal) ${statusIcon}`;
        sel.appendChild(opt);
      });"""

assert old_pull_down in html, "old_pull_down not found"
html = html.replace(old_pull_down, new_pull_down)
print("[✔] 6. Meal Pantry listbox and dropdown deduplicated by normalized title!")

# 7. Add Peppermint Crunch & Candy and OCR label parser to client-side analyzeMealWithGemini fallback
old_cottage = """        if (text.includes("cottage") && (text.includes("blueberry") || text.includes("cake"))) {"""

new_candy_and_label_client = """        // Explicit Nutrition Label Regex Parser on Client
        const mCarbs = text.match(/(?:total\\s+carbs?|carbohydrates?|net\\s+carbs?|carbs?)\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)\\s*g?/i);
        const mCals = text.match(/(?:calories?|energy|kcal)\\s*[:=]?\\s*(\\d+)/i);
        const mFib = text.match(/(?:dietary\\s+fiber|fiber|fibre)\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)\\s*g?/i);
        const mSugar = text.match(/(?:total\\s+sugars?|added\\s+sugars?|sugars?)\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)\\s*g?/i);

        if (mCarbs && (mCals || mFib || mSugar)) {
          const totC = parseFloat(mCarbs[1]);
          const fib = mFib ? parseFloat(mFib[1]) : 0;
          const netC = Math.max(0, Math.round((totC - fib) * 10) / 10);
          const cals = mCals ? parseInt(mCals[1], 10) : Math.round(totC * 4 + 100);
          const meets = (netC <= goals.maxCarbsMeal) && (!mSugar || parseFloat(mSugar[1]) <= 15.0);
          const spike = Math.round(netC * 1.8);
          data = {
            title: rawText || "Nutrition Facts Item",
            desc: rawText,
            calories: cals,
            netCarbs: netC,
            totalCarbs: totC,
            fiber: fib,
            protein: 4,
            fat: 8,
            gl: `${Math.round(netC * 0.75)} (` + (meets ? "Safe Low-Med" : "HIGH SPIKE HAZARD") + ")",
            meetsGoal: meets,
            tips: [
              `Extracted from Nutrition Facts label: ${totC}g Carbs, ${fib}g Fiber -> ${netC}g Net Carbs.`,
              meets ? `Meets your ${goals.maxCarbsMeal}g clinical ceiling.` : `🚨 EXCEEDS CEILING BY +${Math.round(netC - goals.maxCarbsMeal)}g! Acute glycemic spike anticipated.`
            ],
            glut4: `<strong>${Math.max(15, Math.min(60, Math.round(netC * 0.75)))}-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Required to blunt postprandial glucose spike.`
          };
        } else if (/(?:peppermint|crunch|candy|candies|confection|fudge|caramel|toffee|marshmallow|snickers|skittles|lollipop)/i.test(text)) {
          const is2oz = text.includes("2") || text.includes("2 oz");
          const netC = is2oz ? 52.0 : 39.0;
          const cals = is2oz ? 360 : 270;
          const spike = Math.round(netC * 2.1);
          data = {
            title: rawText ? (rawText.charAt(0).toUpperCase() + rawText.slice(1)) : "Christmas Peppermint Crunch Candy",
            desc: rawText,
            calories: cals,
            netCarbs: netC,
            totalCarbs: netC,
            fiber: 0,
            protein: 2,
            fat: 16,
            gl: "45 (Severe High Spike Risk)",
            meetsGoal: false,
            tips: [
              `🚨 SEVERE CONFECTIONERY HAZARD: Contains ${netC}g pure sucrose/corn syrup carbs with 0g fiber.`,
              `Expected glycemic excursion: +${spike} mg/dL above baseline, exceeding ${goals.maxCarbsMeal}g clinical ceiling.`
            ],
            glut4: `<strong>MANDATORY 45-Minute Brisk GLUT4 Walk starting 15 min post-consumption:</strong> Required to blunt projected +${spike} mg/dL spike.`
          };
        } else if (text.includes("cottage") && (text.includes("blueberry") || text.includes("cake"))) {"""

assert old_cottage in html, "old_cottage not found"
html = html.replace(old_cottage, new_candy_and_label_client)
print("[✔] 7. Candy/peppermint and Nutrition Facts extraction added to client fallback!")

# 8. Update BOM table items: replace monetary costs with metabolic cost / glycemic contribution
old_bom_row1 = '{ item: "Low-Fat Cottage Cheese (High Albumin/Casein Base)", portion: "1 cup in batch (~1/4 cup/slice)", dept: "Dairy Department", cost: "$2.49", impact: "High protein buffer, blunts glycemic spike" },'
new_bom_row1 = '{ item: "Low-Fat Cottage Cheese (High Albumin/Casein Base)", portion: "1 cup in batch (~1/4 cup/slice)", dept: "Dairy Department", cost: "0g Net Carbs (0 GL)", impact: "High protein buffer, blunts glycemic spike" },'
if old_bom_row1 in html:
    html = html.replace(old_bom_row1, new_bom_row1)

old_bom_row2 = '{ item: "Fresh Organic Blueberries (Anthocyanin Antioxidant)", portion: "1/2 cup in batch (~2 tbsp/slice)", dept: "Produce Department", cost: "$3.49", impact: "Bioactive polyphenol fiber (<3g net)" },'
new_bom_row2 = '{ item: "Fresh Organic Blueberries (Anthocyanin Antioxidant)", portion: "1/2 cup in batch (~2 tbsp/slice)", dept: "Produce Department", cost: "<3g Net Carbs (Low GL)", impact: "Bioactive polyphenol fiber (<3g net)" },'
if old_bom_row2 in html:
    html = html.replace(old_bom_row2, new_bom_row2)

# Write to D:/veepoo/index.html and w11_htm_app/index.html
with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
print(f"[✔] Dual-file parity verified! SHA256: {h1}")
assert h1 == h2
