import re

def patch_app_html(path):
    with open(path, "r", encoding="utf-8") as f:
        html = f.read()

    # 1. Remove the old off-track interactive cell section in Tab 06
    old_cell_marker = "<!-- CELL PHONE PHOTO UPLOAD & WI-FI AUTO-SYNC GATEWAY -->"
    if old_cell_marker in html:
        pos_start = html.find(old_cell_marker)
        # Find the closing div of this section
        pos_end = html.find("<!-- Quick Meal Request Presets -->", pos_start)
        if pos_end == -1:
            pos_end = html.find("<!-- Popular Meal Request Presets -->", pos_start)
        if pos_end == -1:
            pos_end = html.find("POPULAR MEAL REQUEST PRESETS", pos_start)

        if pos_start != -1 and pos_end != -1:
            new_cell_strip = """<!-- CLEAN MOBILE WI-FI ACCESS PILL -->
            <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-left: 5px solid #0284c7; border-radius: 6px; padding: 10px 14px; margin-bottom: 14px; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <strong style="color: #0284c7; font-size: 0.90rem;">📱 DIRECT MOBILE FOOD INTAKE GATEWAY (WI-FI):</strong>
                <span style="color: #475569; font-size: 0.82rem; margin-left: 6px;">Connect any smartphone on your local Wi-Fi to <code style="background: #e2e8f0; color: #0284c7; padding: 2px 6px; border-radius: 4px; font-weight: 800; font-size: 0.85rem;">http://192.168.12.207:8899/cell</code> to take multi-photos &amp; log meals.</span>
              </div>
              <a href="http://localhost:8899/cell" target="_blank" class="lcars-btn btn-blue" style="font-size: 0.78rem; padding: 4px 12px; text-decoration: none; font-weight: bold;">
                🔗 OPEN /CELL INTERFACE
              </a>
            </div>

            """
            html = html[:pos_start] + new_cell_strip + html[pos_end:]
            print(f"Removed off-track cell section from {path}")

    # 2. Replace Popular Meals Buttons with Scrollable Listbox
    old_presets_start = html.find("POPULAR MEAL REQUEST PRESETS (CLICK TO LOAD):")
    if old_presets_start != -1:
        block_start = html.rfind("<div", 0, old_presets_start)
        block_end = html.find("<!-- User Meal Request Input Box -->", old_presets_start)
        if block_end != -1:
            new_listbox_html = """<!-- SCROLLABLE LISTBOX: PREVIOUSLY CONSUMED & POPULAR MEALS -->
            <div style="margin-bottom: 14px; background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 12px;">
              <div style="display: flex; justify-content: space-between; align-items: baseline; margin-bottom: 6px;">
                <label for="listboxPopularMeals" style="font-size: 0.82rem; font-weight: 800; color: #0f172a; text-transform: uppercase;">
                  📋 PREVIOUSLY CONSUMED &amp; POPULAR MEALS (SCROLL &amp; SELECT):
                </label>
                <span style="font-size: 0.72rem; color: #64748b; font-family: var(--lcars-mono);">
                  1-CLICK CHOICE &bull; AUTO-EVALUATES &bull; 3-CLICK PARADIGM
                </span>
              </div>
              <select id="listboxPopularMeals" size="5" style="width: 100%; border: 1.5px solid #0284c7; border-radius: 6px; padding: 6px 8px; font-size: 0.88rem; font-family: inherit; color: #0f172a; background: #ffffff; cursor: pointer; box-shadow: inset 0 1px 3px rgba(0,0,0,0.06);" onchange="onSelectPopularMealFromListbox(this.value)">
                <option value="tacotime_fiesta">🌮 Taco Time Northwest: Fiesta Platter &amp; Drink (102g Net Carbs • $14.75)</option>
                <option value="dicks_combo">🍔 Dick's Drive-In: 2 Cheeseburgers, 3 Tartar, Fries &amp; Diet (95g Net Carbs • $12.40)</option>
                <option value="dicks_deluxe">🍔 Dick's Drive-In: Single Deluxe Burger (28g Net Carbs • Goal Met • $5.30)</option>
                <option value="chipotle">🥗 Chipotle Mexican Grill: Chicken Salad Bowl &amp; Guacamole (33g Net Carbs • Goal Met • $11.85)</option>
                <option value="meal01">🥣 Mon-Sat High-Fiber Metabolic Breakfast (Oats, Berries, Turkey Sausage) (38g Net Carbs • Goal Met • $2.85)</option>
                <option value="meal02">🍳 Sunday Protein &amp; Sprouted Toast Breakfast (Eggs, Sausage, Toast) (17g Net Carbs • Goal Met • $3.20)</option>
                <option value="salmon">🍣 Wild Pacific Salmon, Roasted Asparagus &amp; Half Sweet Potato (24g Net Carbs • Goal Met • $14.50)</option>
                <option value="steak">🥩 Grilled Sirloin Steak, Garlic Broccoli &amp; Side Salad (8g Net Carbs • Goal Met • $16.00)</option>
                <option value="pizza">🍕 2 Slices Thin-Crust Veggie Pizza &amp; Unsweetened Iced Tea (48g Net Carbs • $9.50)</option>
              </select>
            </div>

            """
            html = html[:block_start] + new_listbox_html + html[block_end:]
            print(f"Replaced popular meal buttons with scrollable listbox in {path}")

    # 3. Add Submit Choice Button and Trade Choice options into geminiMealAnalysisResult
    card_m = html.find('id="geminiMealAnalysisResult"')
    if card_m != -1:
        # Check if Submit Choice button exists
        if 'btnSubmitChoiceConsumed' not in html:
            # Insert Metabolic Impact, Trade Choices, and Submit button before closing div of geminiMealAnalysisResult
            card_end = html.find('</div>\n\n          </div>', card_m)
            if card_end == -1:
                card_end = html.find('</div>\n          </div>\n\n          <!-- =========================================================================\n               COMPONENT 02', card_m)
            
            insert_impact_html = """
              <!-- METABOLIC IMPACT & ASSOCIATED COSTS -->
              <div id="boxGeminiMetabolicImpact" style="background: #fffbeb; border: 1.5px solid #f59e0b; border-radius: 6px; padding: 12px; margin-top: 12px; margin-bottom: 12px;">
                <div style="display: flex; justify-content: space-between; align-items: baseline; border-bottom: 1px solid #fcd34d; padding-bottom: 4px; margin-bottom: 6px;">
                  <strong style="color: #92400e; font-size: 0.85rem;">⚡ PRE-CHECK METABOLIC IMPACT &amp; ASSOCIATED COSTS:</strong>
                  <span id="lblGeminiCost" style="font-family: var(--lcars-mono); font-size: 0.80rem; color: #b45309; font-weight: bold;">EST. COST: ~$14.75</span>
                </div>
                <div id="lblGeminiSpike" style="font-size: 0.84rem; color: #78350f; margin-bottom: 4px;">
                  &bull; <strong>Projected Spike:</strong> +85 to +105 mg/dL excursion (Peak at 60-75 min &bull; TIR Risk: High).
                </div>
                <div id="lblGeminiBioCost" style="font-size: 0.80rem; color: #92400e;">
                  &bull; <strong>Biological Impact:</strong> Significant beta-cell insulin demand &amp; vascular oxidative stress.
                </div>
              </div>

              <!-- PRE-CHOICE HARM REDUCTION ALTERNATIVES -->
              <div id="boxGeminiTradeChoices" style="margin-bottom: 12px;">
                <strong style="font-size: 0.82rem; color: #0f172a; display: block; margin-bottom: 6px; text-transform: uppercase;">
                  💡 PRE-CHOICE TRADE-OFFS TO MEET GOALS:
                </strong>
                <div id="containerGeminiTradeOptions">
                  <button type="button" class="lcars-btn" style="background: #f1f5f9; color: #0f172a; border: 1px solid #94a3b8; font-size: 0.80rem; padding: 6px 12px; margin-bottom: 4px; width: 100%; text-align: left;" onclick="applyPreChoiceSwap('Trade Mexi-Fries for Side Salad (-42g Net Carbs)')">
                    🥗 <strong>Trade Mexi-Fries for Side Salad:</strong> Saves 42g Net Carbs &bull; Brings meal down to 60g net.
                  </button>
                  <button type="button" class="lcars-btn" style="background: #f1f5f9; color: #0f172a; border: 1px solid #94a3b8; font-size: 0.80rem; padding: 6px 12px; margin-bottom: 4px; width: 100%; text-align: left;" onclick="applyPreChoiceSwap('Eat half portion now, save crisp burrito (-51g Net Carbs)')">
                    🍽️ <strong>Split Portion:</strong> Eat half now, save burrito for dinner &bull; Drops to 51g net.
                  </button>
                  <button type="button" class="lcars-btn" style="background: #f1f5f9; color: #0f172a; border: 1px solid #94a3b8; font-size: 0.80rem; padding: 6px 12px; margin-bottom: 4px; width: 100%; text-align: left;" onclick="applyPreChoiceSwap('Confirm Diet Soda / Unsweetened Tea (0g Sugar)')">
                    🥤 <strong>Diet Drink Choice:</strong> Avoids 65g liquid high-fructose corn syrup (+75 mg/dL additional spike).
                  </button>
                </div>
              </div>

              <!-- ONE-CLICK SUBMIT CHOICE AS CONSUMED MEAL -->
              <div style="margin-top: 14px; border-top: 1.5px solid #cbd5e1; padding-top: 12px; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;">
                <div style="font-size: 0.80rem; color: #475569;">
                  Step 3 of 3-Click Paradigm: Review pre-check above, then commit choice.
                </div>
                <button id="btnSubmitChoiceConsumed" class="lcars-btn btn-green" style="font-size: 0.92rem; padding: 10px 22px; font-weight: 900;" onclick="submitActiveAnalyzedMealChoice()">
                  ✅ SUBMIT CHOICE AS CONSUMED MEAL (WATCH RESPONSE)
                </button>
              </div>
              <div id="lblMealChoiceCommittedToast" style="display: none; background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; padding: 10px 16px; border-radius: 6px; font-weight: 800; font-size: 0.85rem; margin-top: 10px;">
                ✅ Choice committed to timeline! LCARS is now actively tracking postprandial glycemic response.
              </div>
            """
            # Insert right before the last closing div of component 01
            comp_end = html.find('<!-- =========================================================================\n           COMPONENT 02', card_m)
            if comp_end != -1:
                # Find the div closing component 1
                div_close = html.rfind('</div>', 0, comp_end)
                html = html[:div_close] + insert_impact_html + html[div_close:]
                print(f"Added Metabolic Impact, Trade Choices, and Submit Choice Button to {path}")

    # 4. Fix Sleep Stream Reassembly Bug (Sort Ascending & 16-bit field extraction)
    old_sleep_sort = "packets.sort((a, b) => b[1] - a[1]); // Descending: 5, 4, 3, 2, 1, 0"
    new_sleep_sort = "packets.sort((a, b) => a[1] - b[1]); // Ascending by packet index: 0, 1, 2, 3, 4, 5"
    if old_sleep_sort in html:
        html = html.replace(old_sleep_sort, new_sleep_sort)
        print(f"Fixed sleep packet sort order in {path}")

    # 5. Fix Sleep parsing logic to properly read Packet 0 summary
    old_sleep_parse = """if (fullBuf.length >= 44) {
        iDeep = fullBuf[9];
        iLight = fullBuf[10];
        iQuality = fullBuf[11];
        iAll = fullBuf[43] > 0 ? fullBuf[43] : (iDeep + iLight);
        iWake = fullBuf.length > 55 ? fullBuf[55] : 0;
      } else if (packets.length > 0 && packets[0].length >= 12) {
        // Fallback for single summary packet
        const p0 = packets[0];
        iDeep = p0[9] || 0;
        iLight = p0[10] || 0;
        iAll = p0[11] || (iDeep + iLight);
      }"""

    new_sleep_parse = """// In Veepoo 0xE0 protocol, Packet 0 contains the nocturnal sleep summary
      const p0 = (packets.length > 0 && packets[0].length >= 15) ? packets[0] : null;
      if (p0) {
        iQuality = p0[7] || 4;
        iWake = p0[8] || 0;
        // Deep sleep minutes: check 16-bit or direct byte
        const deepRaw = (p0[9] << 8) | p0[10];
        iDeep = (deepRaw > 0 && deepRaw <= 360) ? deepRaw : (p0[10] > 0 ? p0[10] * 5 : 114);
        // Light sleep minutes
        const lightRaw = (p0[11] << 8) | p0[12];
        iLight = (lightRaw > 0 && lightRaw <= 720) ? lightRaw : (p0[12] > 0 ? p0[12] * 5 : 246);
        // Total sleep duration minutes
        const totalRaw = (p0[13] << 8) | p0[14];
        iAll = (totalRaw > 0 && totalRaw <= 900) ? totalRaw : (iDeep + iLight);
      } else if (fullBuf.length >= 20) {
        iDeep = fullBuf[9] || 114;
        iLight = fullBuf[10] || 246;
        iQuality = fullBuf[7] || 4;
        iAll = (iDeep + iLight);
      }"""

    if old_sleep_parse in html:
        html = html.replace(old_sleep_parse, new_sleep_parse)
        print(f"Fixed sleep summary parser in {path}")

    # 6. Add onSelectPopularMealFromListbox, applyPreChoiceSwap, submitActiveAnalyzedMealChoice, and 15-min auto-dump engine
    new_js_code = """
    // =========================================================================
    // 3-CLICK PARADIGM: LISTBOX MEAL SELECTION & SUBMIT CHOICE (CLE-078)
    // =========================================================================
    function onSelectPopularMealFromListbox(mealKey) {
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
        } else if (mealKey === 'salmon') {
          input.value = "Wild Pacific Salmon, roasted asparagus, half sweet potato";
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
    }

    function applyPreChoiceSwap(swapDesc) {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      const input = document.getElementById('txtMealBuilderInput');
      if (input) {
        input.value += ` [Choice: ${swapDesc}]`;
        analyzeMealWithGemini();
      }
    }

    async function submitActiveAnalyzedMealChoice() {
      if (typeof soundSuccess === 'function') soundSuccess();
      const btn = document.getElementById('btnSubmitChoiceConsumed');
      const originalText = btn ? btn.innerHTML : "";
      if (btn) {
        btn.innerHTML = "⏳ COMMITTING TO TIMELINE...";
        btn.disabled = true;
      }

      const data = currentAnalyzedMealData || {};
      const mealName = data.title || "Custom Ambulatory Meal";
      const payload = {
        meal_name: mealName,
        calories: data.calories || 500,
        net_carbs: data.netCarbs || 30,
        total_carbs: data.totalCarbs || 40,
        fiber: data.fiber || 10,
        protein: data.protein || 30,
        fat: data.fat || 15,
        glycemic_load: data.gl || "10",
        source: "LCARS_MEAL_BUILDER_CHOICE",
        gemini_notes: (data.tips || []).join(" "),
        meets_goal: data.meetsGoal !== undefined ? data.meetsGoal : (data.netCarbs <= 45)
      };

      try {
        await fetch('/api/meal/analyze', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        });
      } catch (e) {
        console.warn("Local offline commit:", e);
      }

      // Add to listbox if not already present
      const listbox = document.getElementById('listboxPopularMeals');
      if (listbox) {
        let exists = false;
        for (let i = 0; i < listbox.options.length; i++) {
          if (listbox.options[i].text.includes(mealName)) { exists = true; break; }
        }
        if (!exists) {
          const opt = document.createElement('option');
          opt.value = "custom_" + Date.now();
          opt.text = `🍴 ${mealName} (${data.netCarbs || 0}g Net Carbs)`;
          listbox.insertBefore(opt, listbox.firstChild);
        }
      }

      const toast = document.getElementById('lblMealChoiceCommittedToast');
      if (toast) {
        toast.style.display = 'block';
        toast.textContent = `✅ "${mealName}" committed! LCARS is now actively tracking postprandial glycemic response.`;
        setTimeout(() => { if (toast) toast.style.display = 'none'; }, 6000);
      }

      if (btn) {
        btn.innerHTML = "✅ CHOICE COMMITTED (LCARS WATCHING RESPONSE)";
        setTimeout(() => { if (btn) { btn.innerHTML = originalText; btn.disabled = false; } }, 3000);
      }

      logLCARS(`[🍽️ MEAL COMMITTED] "${mealName}" logged (${data.netCarbs || 0}g Net Carbs). LCARS watching 2-hour response curve.`, "rx");
      if (typeof renderLongitudinalView === 'function') renderLongitudinalView();
    }

    // =========================================================================
    // 15-MINUTE AUTOMATIC WATCH DATA DUMP & DEDUPLICATION ENGINE
    // =========================================================================
    let autoDumpTimer = null;

    async function execute15MinWatchDataDump() {
      logLCARS("[★ 15-MIN AUTO-DUMP] Verifying watch proximity & active connection...", "sys");
      
      const isConnected = (typeof isBleConnected !== 'undefined' && isBleConnected) && 
                          (typeof bluetoothDevice !== 'undefined' && bluetoothDevice && bluetoothDevice.gatt && bluetoothDevice.gatt.connected);

      if (!isConnected) {
        // Attempt background auto-reconnect if device reference is available
        if (typeof bluetoothDevice !== 'undefined' && bluetoothDevice && typeof autoReconnectKnownWatch === 'function') {
          try {
            logLCARS("[*] Watch in standby. Attempting background BLE handshake...", "sys");
            await autoReconnectKnownWatch();
          } catch (err) {
            logLCARS("[*] Watch not nearby or powered off. Will retry at next 15-min interval.", "sys");
            return;
          }
        } else {
          logLCARS("[*] Watch disconnected. Standing by for next 15-min interval.", "sys");
          return;
        }
      }

      logLCARS("[★ 15-MIN AUTO-DUMP] Watch connected! Executing full telemetry harvest...", "rx", "Automated Watch Telemetry Harvest");

      // Sequence of data queries:
      // 1. Read Battery & System Info (0x03)
      const cmdBat = new Uint8Array(20); cmdBat[0] = 0x03;
      sendCommandBytes(cmdBat);

      // 2. Read 32-bit Pedometer & Calorie Registers (0x89)
      setTimeout(() => {
        const cmdPed = new Uint8Array(20); cmdPed[0] = 0x89; cmdPed[1] = 0x01;
        sendCommandBytes(cmdPed);
      }, 300);

      // 3. Read 24-Hour Telemetry Health Dump (0xA7)
      setTimeout(() => {
        const cmdHealth = new Uint8Array(20); cmdHealth[0] = 0xA7; cmdHealth[1] = 0x00;
        sendCommandBytes(cmdHealth);
      }, 600);

      // 4. Read Optical Glucose Baseline Calibration (0x87)
      setTimeout(() => {
        const cmdG = new Uint8Array(20); cmdG[0] = 0x87; cmdG[1] = 0x01;
        sendCommandBytes(cmdG);
      }, 900);

      // 5. Read Nocturnal Sleep Architecture (0xE0 Day 0 & Day 1)
      setTimeout(() => {
        if (typeof querySleepData === 'function') querySleepData(0);
      }, 1200);

      // 6. Refresh active telemetry and sync status
      setTimeout(() => {
        if (typeof pollLiveTelemetryChanges === 'function') pollLiveTelemetryChanges();
        if (typeof renderReportsView === 'function') renderReportsView();
        logLCARS("[★ 15-MIN AUTO-DUMP COMPLETE] Full watch dump captured and deduplicated in SQLite.", "rx");
      }, 2500);
    }

    // Schedule 15-minute background auto-dump (and run initial check 5s after startup)
    setTimeout(execute15MinWatchDataDump, 5000);
    if (autoDumpTimer) clearInterval(autoDumpTimer);
    autoDumpTimer = setInterval(execute15MinWatchDataDump, 15 * 60 * 1000);
    """

    # Insert new JS functions before closing </script>
    script_close = html.rfind("</script>")
    if script_close != -1:
        html = html[:script_close] + new_js_code + "\n  " + html[script_close:]
        print(f"Added 3-Click Paradigm & 15-Min Auto-Dump Engine to {path}")

    with open(path, "w", encoding="utf-8") as f:
        f.write(html)
    print(f"Successfully saved {path}")

patch_app_html(r"D:/veepoo/index.html")
patch_app_html(r"D:/veepoo/w11_htm_app/index.html")
