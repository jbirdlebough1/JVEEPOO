import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# 1. Patch renderDietHistory & deleteMealEntry
start_rd = text.find('async function renderDietHistory() {')
assert start_rd != -1, "renderDietHistory not found"
end_rd = text.find('function clearAllMeals() {', start_rd)
assert end_rd != -1, "clearAllMeals not found"

new_render_diet = '''async function renderDietHistory() {
      const list = document.getElementById('dietHistoryList');
      if (!list) return;

      // 1. Fetch real submitted meals from server SQLite repository
      let meals = [];
      try {
        const resp = await fetch('/api/meal/recent?limit=100');
        if (resp.ok) {
          const data = await resp.json();
          if (data && data.meals && data.meals.length > 0) {
            meals = data.meals;
          }
        }
      } catch (e) {
        console.warn("Could not fetch remote meal history, falling back to local journal:", e);
      }

      // If remote empty, fallback to local mealJournal
      if (meals.length === 0 && typeof mealJournal !== 'undefined' && mealJournal.length > 0) {
        meals = mealJournal.map(m => ({
          id: m.id,
          timestamp: m.ts || new Date().toISOString(),
          meal_name: m.desc || m.type,
          calories: m.cal || 0,
          net_carbs: m.carbs || 0,
          protein: m.protein || 0,
          fat: 0,
          fiber: 0,
          glycemic_load: "Medium",
          vendor_matched: m.context || "User Manual Entry",
          meets_goal: m.carbs <= 45 ? 1 : 0,
          gemini_notes: m.desc || "",
          identified_items: []
        }));
      }

      // GUARANTEE STRICT LATEST-TO-OLDEST SORTING
      meals.sort((a, b) => {
        const tA = new Date(a.timestamp || 0).getTime();
        const tB = new Date(b.timestamp || 0).getTime();
        if (tB !== tA) return tB - tA;
        return (b.id || 0) - (a.id || 0);
      });

      list.innerHTML = '';

      if (meals.length === 0) {
        list.innerHTML = `
          <div style="padding: 24px; text-align: center; color: var(--lcars-blue); font-family: var(--lcars-mono); font-size: 0.95rem; border: 1px dashed #cbd5e1; border-radius: 6px; background: #ffffff;">
            NO MEALS RECORDED IN REPOSITORY • USE THE FORM ABOVE OR MOBILE /cell GATEWAY TO LOG MEALS
          </div>
        `;
        const sC = document.getElementById('sumCarbs'); if (sC && sC.firstChild) sC.firstChild.textContent = "0";
        const sP = document.getElementById('sumProtein'); if (sP && sP.firstChild) sP.firstChild.textContent = "0";
        const sCal = document.getElementById('sumCalories'); if (sCal && sCal.firstChild) sCal.firstChild.textContent = "0";
        return;
      }

      // 2. Compute DAILY INTAKE SUMMARY OF ALL CONSUMED (for TODAY)
      const now = new Date();
      const todayIsoPrefix = `${now.getFullYear()}-${String(now.getMonth() + 1).padStart(2, '0')}-${String(now.getDate()).padStart(2, '0')}`;
      const todayMeals = meals.filter(m => (m.timestamp || '').startsWith(todayIsoPrefix));

      // Occasion grouping for Today's Intake Summary
      const occasions = {
        Breakfast: { count: 0, kcal: 0, netC: 0, items: [], foodComponents: [] },
        Lunch: { count: 0, kcal: 0, netC: 0, items: [], foodComponents: [] },
        Dinner: { count: 0, kcal: 0, netC: 0, items: [], foodComponents: [] },
        Snack: { count: 0, kcal: 0, netC: 0, items: [], foodComponents: [] }
      };

      let dayTotalKcal = 0, dayTotalNetC = 0, dayTotalPro = 0, dayTotalFat = 0, dayTotalFib = 0;
      let dayMissedGoalCount = 0;

      // Use todayMeals if present, otherwise use latest entries for live audit
      const intakeSet = todayMeals.length > 0 ? todayMeals : meals.slice(0, 10);
      intakeSet.forEach(m => {
        const cal = m.calories || m.cal || 0;
        const netC = m.net_carbs !== undefined ? m.net_carbs : (m.carbs || 0);
        const pro = m.protein || 0;
        const fat = m.fat || 0;
        const fib = m.fiber || 0;

        dayTotalKcal += cal;
        dayTotalNetC += netC;
        dayTotalPro += pro;
        dayTotalFat += fat;
        dayTotalFib += fib;

        if (netC > 45) dayMissedGoalCount++;

        // Classify meal occasion: check explicit tags or time of day
        const nameStr = ((m.meal_name || '') + ' ' + (m.source || '') + ' ' + (m.gemini_notes || '')).toLowerCase();
        let occ = 'Snack';
        
        let hour = 12;
        if (m.timestamp && m.timestamp.includes(' ')) {
          const timePart = m.timestamp.split(' ')[1];
          if (timePart) hour = parseInt(timePart.split(':')[0], 10) || 12;
        }

        if (nameStr.includes('[snack]') || nameStr.includes('tea') || nameStr.includes('infusion') || nameStr.includes('snack') || nameStr.includes('shake') || nameStr.includes('cookie')) {
          occ = 'Snack';
        } else if (nameStr.includes('[breakfast]') || nameStr.includes('breakfast') || hour < 11) {
          occ = 'Breakfast';
        } else if (nameStr.includes('[lunch]') || nameStr.includes('lunch') || (hour >= 11 && hour < 16)) {
          occ = 'Lunch';
        } else if (nameStr.includes('[dinner]') || nameStr.includes('dinner') || nameStr.includes('supper') || hour >= 16) {
          occ = 'Dinner';
        }

        occasions[occ].count++;
        occasions[occ].kcal += cal;
        occasions[occ].netC += netC;
        occasions[occ].items.push(m.meal_name || 'Meal');

        // Extract food components
        const comps = m.identified_items || (m.raw_json ? (typeof m.raw_json === 'string' ? JSON.parse(m.raw_json).identified_items : m.raw_json.identified_items) : null);
        if (comps && Array.isArray(comps)) {
          comps.forEach(c => occasions[occ].foodComponents.push(c.name || c.item || 'Food Item'));
        }
      });

      // Update Daily Intake Summary Card Elements
      const lblDate = document.getElementById('lblDailyIntakeDate');
      if (lblDate) {
        const dateOptions = { weekday: 'long', year: 'numeric', month: 'short', day: 'numeric' };
        lblDate.textContent = `${now.toLocaleDateString('en-US', dateOptions).toUpperCase()} • ${intakeSet.length} CONSUMED ITEMS LOGGED`;
      }

      ['Breakfast', 'Lunch', 'Dinner', 'Snack'].forEach(occ => {
        const cEl = document.getElementById(`summaryCount${occ}`);
        const mEl = document.getElementById(`summaryMacro${occ}`);
        const lEl = document.getElementById(`summaryList${occ}`);
        const data = occasions[occ];
        if (cEl) cEl.textContent = `${data.count} ${data.count === 1 ? 'item' : 'items'}`;
        if (mEl) mEl.textContent = `${Math.round(data.kcal)} kcal • ${Math.round(data.netC)}g Net Carbs`;
        if (lEl) {
          const displayFoods = data.foodComponents.length > 0 ? data.foodComponents : data.items;
          lEl.textContent = displayFoods.length > 0 ? displayFoods.join(', ') : 'None recorded';
          lEl.title = displayFoods.join(', ');
        }
      });

      const elDayKcal = document.getElementById('lblDayKcal');
      if (elDayKcal) elDayKcal.textContent = `${Math.round(dayTotalKcal)} / 2,000 kcal`;
      const elDayNet = document.getElementById('lblDayNetCarbs');
      if (elDayNet) elDayNet.textContent = `${Math.round(dayTotalNetC)} / 135 g`;
      const elDayPro = document.getElementById('lblDayProtein');
      if (elDayPro) elDayPro.textContent = `${Math.round(dayTotalPro)} / 75 g`;
      const elDayFat = document.getElementById('lblDayFat');
      if (elDayFat) elDayFat.textContent = `${Math.round(dayTotalFat)} g`;
      const elDayFib = document.getElementById('lblDayFiber');
      if (elDayFib) elDayFib.textContent = `${Math.round(dayTotalFib)} g`;

      const badgeDaily = document.getElementById('badgeDailyIntakeStatus');
      if (badgeDaily) {
        if (dayMissedGoalCount === 0) {
          badgeDaily.textContent = "✅ ALL INTAKES MET DIABETIC GOALS (≤45g NET)";
          badgeDaily.style.background = "#ecfdf5";
          badgeDaily.style.color = "#15803d";
          badgeDaily.style.borderColor = "#10b981";
        } else {
          badgeDaily.textContent = `⚠️ ${dayMissedGoalCount} INTAKE EXCEEDED 45g NET • GLUT4 WALK ADVISORY`;
          badgeDaily.style.background = "#fef2f2";
          badgeDaily.style.color = "#b91c1c";
          badgeDaily.style.borderColor = "#ef4444";
        }
      }

      // Update Top Ribbon Macro Badges to Today's Consumed
      const sC = document.getElementById('sumCarbs'); if (sC && sC.firstChild) sC.firstChild.textContent = Math.round(dayTotalNetC);
      const sP = document.getElementById('sumProtein'); if (sP && sP.firstChild) sP.firstChild.textContent = Math.round(dayTotalPro);
      const sCal = document.getElementById('sumCalories'); if (sCal && sCal.firstChild) sCal.firstChild.textContent = Math.round(dayTotalKcal);

      // 3. Render submitted meals list strictly latest to oldest
      meals.forEach((m, idx) => {
        const netC = m.net_carbs !== undefined ? m.net_carbs : (m.carbs || 0);
        const prot = m.protein || 0;
        const cal = m.calories || m.cal || 0;
        const meetsGoal = (m.meets_goal === 1) || (netC <= 45);

        const badgeHtml = meetsGoal
          ? `<span style="display: inline-block; background: #ecfdf5; color: #15803d; border: 1.5px solid #10b981; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">✅ GOAL MET (≤45g Net)</span>`
          : `<span style="display: inline-block; background: #fef2f2; color: #b91c1c; border: 1.5px solid #ef4444; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">⚠️ EXCEEDS 45g (+${Math.round(netC - 45)}g Net) • GLUT4 WALK NEEDED</span>`;

        const isLatest = (idx === 0);

        // Parse individual component foods
        let foodItems = m.identified_items || [];
        if ((!foodItems || foodItems.length === 0) && m.raw_json) {
          try {
            const rawObj = typeof m.raw_json === 'string' ? JSON.parse(m.raw_json) : m.raw_json;
            if (rawObj && rawObj.identified_items) foodItems = rawObj.identified_items;
          } catch(e) {}
        }

        let foodComponentsHtml = '';
        if (foodItems && foodItems.length > 0) {
          foodComponentsHtml = `
            <div style="margin-top: 8px; background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 6px; padding: 8px 12px;">
              <div style="font-size: 0.70rem; font-weight: 800; color: #64748b; text-transform: uppercase; letter-spacing: 0.05em; margin-bottom: 4px;">
                🥗 CONSUMED FOOD COMPONENTS & PORTIONS:
              </div>
              <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 6px;">
                ${foodItems.map(it => `
                  <div style="font-size: 0.76rem; color: #1e293b; background: #ffffff; border: 1px solid #cbd5e1; border-radius: 4px; padding: 4px 8px; display: flex; justify-content: space-between; align-items: center;">
                    <span><strong>${it.name || it.item || 'Item'}</strong> ${it.portion ? `<span style="color: #64748b; font-size: 0.70rem;">(${it.portion})</span>` : ''}</span>
                    <span style="font-family: var(--lcars-mono); font-weight: 700; color: #0284c7; font-size: 0.72rem;">${it.net_carbs !== undefined ? it.net_carbs + 'g carb' : ''} ${it.calories ? '• ' + it.calories + ' cal' : ''}</span>
                  </div>
                `).join('')}
              </div>
            </div>
          `;
        }

        const item = document.createElement('div');
        item.className = 'diet-item-card';
        item.style.cssText = "background: #ffffff; border: 1.5px solid #e2e8f0; border-left: 5px solid " + (meetsGoal ? "#10b981" : "#ef4444") + "; border-radius: 6px; padding: 10px 14px; box-shadow: 0 1px 3px rgba(0,0,0,0.04);";
        item.innerHTML = `
          <div style="display: flex; justify-content: space-between; align-items: flex-start; gap: 10px; flex-wrap: wrap;">
            <div style="flex: 1; min-width: 220px;">
              <div style="display: flex; align-items: center; gap: 8px; flex-wrap: wrap;">
                ${isLatest ? `<span style="background: var(--lcars-banana); color: #000; font-weight: 900; font-size: 0.70rem; padding: 1px 6px; border-radius: 3px; font-family: var(--lcars-mono);">LATEST</span>` : ''}
                <strong style="color: #0f172a; font-size: 0.98rem; font-weight: 800;">${m.meal_name}</strong>
                <span style="font-family: var(--lcars-mono); font-size: 0.75rem; color: #64748b;">${m.timestamp}</span>
                ${badgeHtml}
              </div>
              <div style="font-size: 0.78rem; color: #475569; margin-top: 3px;">
                <strong>Source:</strong> ${m.vendor_matched || m.source || 'Verified Intake'}
              </div>
              <div style="display: flex; gap: 6px; flex-wrap: wrap; margin-top: 6px;">
                <span class="diet-macro-badge badge-carb" style="font-size: 0.75rem; padding: 2px 8px;">${netC}g NET CARBS</span>
                <span class="diet-macro-badge badge-protein" style="font-size: 0.75rem; padding: 2px 8px;">${prot}g PROTEIN</span>
                <span class="diet-macro-badge badge-cal" style="font-size: 0.75rem; padding: 2px 8px;">${cal} KCAL</span>
                ${m.glycemic_load ? `<span style="background: #f1f5f9; color: #334155; font-size: 0.72rem; padding: 2px 8px; border-radius: 4px; font-weight: 700; border: 1px solid #cbd5e1;">GL: ${m.glycemic_load}</span>` : ''}
              </div>
              ${foodComponentsHtml}
              ${m.gemini_notes ? `<div style="font-size: 0.76rem; color: #475569; margin-top: 6px; background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 4px; padding: 4px 8px; line-height: 1.4;">${m.gemini_notes}</div>` : ''}
            </div>
            <div>
              <button class="lcars-btn btn-orange" style="padding: 3px 8px; font-size: 0.72rem;" onclick="deleteMealEntry(${m.id})">DELETE</button>
            </div>
          </div>
        `;
        list.appendChild(item);
      });
    }

    async function deleteMealEntry(id) {
      soundButtonPress();
      try {
        await fetch('/api/meal/delete?id=' + encodeURIComponent(id), { method: 'POST' });
      } catch (e) {
        console.warn("Could not delete from remote server:", e);
      }
      mealJournal = mealJournal.filter(m => m.id !== id);
      localStorage.setItem('veepoo_meal_journal', JSON.stringify(mealJournal));
      await renderDietHistory();
    }

    '''

text = text[:start_rd] + new_render_diet + text[end_rd:]

# 2. Patch pollLiveTelemetryChanges
start_poll = text.find('async function pollLiveTelemetryChanges() {')
assert start_poll != -1, "pollLiveTelemetryChanges not found"
end_poll = text.find('// Run live telemetry poll every 2.5 seconds', start_poll)
assert end_poll != -1, "end of pollLiveTelemetryChanges not found"

new_poll = '''async function pollLiveTelemetryChanges(force = false) {
      try {
        const resp = await fetch('/api/telemetry/latest');
        if (!resp.ok) return;
        const data = await resp.json();
        const vitals = data.vitals || {};
        window._latestTelemetry = vitals;
        const dbVer = data.db_version || 0;

        // Check if new records arrived OR forced update
        if (force || dbVer !== lastKnownDbVersion || !lastKnownDbVersion) {
          lastKnownDbVersion = dbVer;

          // Always synchronize global vitals variables
          if (vitals.GLUCOSE && vitals.GLUCOSE.value1 > 0) {
            lastGlucoseMg = Math.round(vitals.GLUCOSE.value1);
          }
          if (vitals.BLOOD_PRESSURE && vitals.BLOOD_PRESSURE.value1 > 0) {
            lastSystolic = Math.round(vitals.BLOOD_PRESSURE.value1);
            lastDiastolic = Math.round(vitals.BLOOD_PRESSURE.value2);
          }
          if (vitals.HEART_RATE && vitals.HEART_RATE.value1 > 0) {
            lastHR = Math.round(vitals.HEART_RATE.value1);
          }
          const spo2Obj = vitals.BLOOD_OXYGEN || vitals.SPO2;
          if (spo2Obj && spo2Obj.value1 > 0) {
            lastSpO2 = Math.round(spo2Obj.value1);
          }
          const hrvObj = vitals.HRV_STRESS || vitals.HRV;
          if (hrvObj && hrvObj.value1 > 0) {
            lastHRV = Math.round(hrvObj.value1);
            lastStress = Math.round(hrvObj.value2);
          }
          if (vitals.TEMPERATURE && vitals.TEMPERATURE.value1 > 0) {
            lastBodyF = parseFloat(vitals.TEMPERATURE.value1.toFixed(1));
            lastSkinF = parseFloat((vitals.TEMPERATURE.value2 || (lastBodyF - 2.4)).toFixed(1));
          }
          if (vitals.PEDOMETER) {
            lastSteps = Math.round(vitals.PEDOMETER.value1 || 0);
            lastKcal = Math.round(vitals.PEDOMETER.value2 || 0);
          }
          if (vitals.SLEEP) {
            lastSleepTotalHours = parseFloat(vitals.SLEEP.value1 || 0);
            lastDeepSleepHours = parseFloat(vitals.SLEEP.value2 || 0);
          }

          // 1. Update Glucose across ALL tabs
          if (vitals.GLUCOSE && vitals.GLUCOSE.value1 > 0) {
            const mgdl = Math.round(vitals.GLUCOSE.value1);
            const ea1c = vitals.GLUCOSE.value2 || ((mgdl + 46.7) / 28.7).toFixed(1);
            
            const elG = document.getElementById('valGlucose');
            if (elG) elG.textContent = `${mgdl}`;
            const badgeG = document.getElementById('badgeGlucose');
            if (badgeG) {
              badgeG.textContent = (mgdl >= 70 && mgdl <= 140) ? "NORMAL / EUGLYCEMIC" : (mgdl <= 180 ? "TARGET TIR" : "ELEVATED POSTPRANDIAL");
              badgeG.className = (mgdl >= 70 && mgdl <= 180) ? "state-badge nominal" : "state-badge warning";
            }
            const elRepG = document.getElementById('repAvgGlucose');
            if (elRepG) elRepG.innerHTML = `${mgdl} <span style="font-size: 1rem;">mg/dL</span>`;
            const elRepA = document.getElementById('repAvgA1C');
            if (elRepA) elRepA.textContent = `${ea1c}%`;
            const tileG = document.getElementById('tile360Glucose');
            if (tileG) tileG.innerHTML = `${mgdl} <span style="font-size: 0.9rem;">mg/dL</span>`;
            const hudG = document.getElementById('hudGlucose');
            if (hudG) hudG.textContent = `${mgdl} mg/dL`;
            
            if (typeof refreshGlucoseRecommendation === 'function') {
              refreshGlucoseRecommendation();
            }
          }

          // 2. Update Blood Pressure across ALL tabs
          if (vitals.BLOOD_PRESSURE && vitals.BLOOD_PRESSURE.value1 > 0) {
            const sys = Math.round(vitals.BLOOD_PRESSURE.value1);
            const dia = Math.round(vitals.BLOOD_PRESSURE.value2);
            const elBp = document.getElementById('valBP');
            if (elBp) elBp.textContent = `${sys}/${dia}`;
            const badgeBp = document.getElementById('badgeBP');
            if (badgeBp) {
              badgeBp.textContent = (sys < 125 && dia < 82) ? "NORMOTENSIVE" : "PRE-HYPERTENSIVE";
              badgeBp.className = (sys < 130 && dia < 85) ? "state-badge nominal" : "state-badge warning";
            }
            const tileBp = document.getElementById('tile360Bp');
            if (tileBp) tileBp.innerHTML = `${sys}/${dia} <span style="font-size: 0.9rem;">mmHg</span>`;
            const hudBp = document.getElementById('hudBp');
            if (hudBp) hudBp.textContent = `${sys}/${dia} mmHg`;
          }

          // 3. Update Heart Rate across Core Vitals & Cardio Bay
          if (vitals.HEART_RATE && vitals.HEART_RATE.value1 > 0) {
            const bpm = Math.round(vitals.HEART_RATE.value1);
            document.querySelectorAll('#valHeartRate, .valHeartRate').forEach(el => el.textContent = `${bpm}`);
            const badgeHr = document.getElementById('badgeHR');
            if (badgeHr) {
              badgeHr.textContent = bpm < 60 ? "BRADYCARDIA" : bpm > 100 ? "TACHYCARDIA" : "RESTING NORMAL";
              badgeHr.className = "state-badge nominal";
            }
          }

          // 4. Update Autonomic HRV & Stress
          if (hrvObj && hrvObj.value1 > 0) {
            const hrvVal = Math.round(hrvObj.value1);
            const stressVal = Math.round(hrvObj.value2);
            const elHrv = document.getElementById('txtHRV');
            if (elHrv) elHrv.textContent = `${hrvVal} ms (${stressVal < 35 ? 'BALANCED' : 'ELEVATED'})`;
            const elStress = document.getElementById('valStress');
            if (elStress) elStress.textContent = `${stressVal}`;
            const badgeStress = document.getElementById('badgeStress');
            if (badgeStress) {
              badgeStress.textContent = stressVal < 35 ? "BALANCED / LOW STRESS" : "ELEVATED SYMPATHETIC";
            }
            const tileHrHrv = document.getElementById('tile360HrHrv');
            if (tileHrHrv) tileHrHrv.innerHTML = `${lastHR || 72} BPM <span style="font-size: 0.85rem;">(${hrvVal}ms HRV)</span>`;
            const hudH = document.getElementById('hudHrHrv');
            if (hudH) hudH.textContent = `${lastHR || 72} BPM / ${hrvVal} ms`;
          }

          // 5. Update Blood Oxygen / SpO2
          if (spo2Obj && spo2Obj.value1 > 0) {
            const sp = Math.round(spo2Obj.value1);
            const elSp = document.getElementById('valSpO2');
            if (elSp) elSp.textContent = `${sp}`;
            const badgeSpo2 = document.getElementById('badgeSpO2');
            if (badgeSpo2) {
              badgeSpo2.textContent = sp >= 95 ? "EUMOXIC (NORMAL)" : "BORDERLINE HYPOXIA";
              badgeSpo2.className = sp >= 95 ? "state-badge nominal" : "state-badge warning";
            }
            const tileSpo2 = document.getElementById('tile360Spo2');
            if (tileSpo2) tileSpo2.innerHTML = `${sp}% <span style="font-size: 0.9rem;">SpO2</span>`;
            const hudSpo2 = document.getElementById('hudSpo2');
            if (hudSpo2) hudSpo2.textContent = `${sp}% SpO2`;
          }

          // 6. Update Temperature
          if (vitals.TEMPERATURE && vitals.TEMPERATURE.value1 > 0) {
            const bodyF = vitals.TEMPERATURE.value1.toFixed(1);
            const skinF = (vitals.TEMPERATURE.value2 || (vitals.TEMPERATURE.value1 - 2.4)).toFixed(1);
            const elT = document.getElementById('valTemp');
            if (elT) elT.textContent = `${bodyF}`;
            const badgeTemp = document.getElementById('badgeTemp');
            if (badgeTemp) {
              badgeTemp.textContent = (vitals.TEMPERATURE.value1 >= 97.0 && vitals.TEMPERATURE.value1 <= 99.2) ? "AFEBRILE (NOMINAL)" : "FEBRILE ELEVATION";
              badgeTemp.className = "state-badge nominal";
            }
            const elCore = document.getElementById('txtTempCore');
            if (elCore) elCore.textContent = `Core: ${bodyF}°F`;
            const elSkin = document.getElementById('txtTempSkin');
            if (elSkin) elSkin.textContent = `Skin: ${skinF}°F`;
            const tileT = document.getElementById('tile360Temp');
            if (tileT) tileT.innerHTML = `${bodyF}°F <span style="font-size: 0.85rem;">(${skinF}°F skin)</span>`;
            const hudTemp = document.getElementById('hudTemp');
            if (hudTemp) hudTemp.textContent = `${bodyF}°F`;
          }

          // 7. Update Pedometer & Steps (Universal Step Propagation)
          if (vitals.PEDOMETER) {
            const steps = Math.round(vitals.PEDOMETER.value1 || 0);
            const kcal = Math.round(vitals.PEDOMETER.value2 || 0);
            const miles = (steps * 0.000473).toFixed(2);
            const elStep = document.getElementById('valStepCount');
            if (elStep) elStep.innerHTML = `${steps.toLocaleString()} <span style="font-size: 1rem;">steps</span>`;
            const elKcal = document.getElementById('valStepKcal');
            if (elKcal) elKcal.innerHTML = `${kcal} <span style="font-size: 1rem;">kcal</span>`;
            const elDist = document.getElementById('valStepDist');
            if (elDist) elDist.innerHTML = `${miles} <span style="font-size: 1rem;">miles</span>`;
            const elGoal = document.getElementById('valStepGoal');
            if (elGoal) elGoal.textContent = `Goal: 2,000 (${Math.round((steps / 2000) * 100)}% Met)`;
            const tileMotion = document.getElementById('tile360Motion');
            if (tileMotion) tileMotion.innerHTML = `${steps.toLocaleString()} <span style="font-size: 0.9rem;">steps</span>`;
            const lblSteps = document.getElementById('lblActiveDailySteps');
            if (lblSteps) {
              const pct = Math.min(100, Math.round((steps / 8000) * 100));
              lblSteps.textContent = `TOTAL: ${steps.toLocaleString()} / 8,000 STEPS (${pct}%)`;
            }
          }

          // 8. Update Sleep Telemetry
          if (vitals.SLEEP) {
            const totH = parseFloat(vitals.SLEEP.value1);
            const deepH = parseFloat(vitals.SLEEP.value2);
            if (!isNaN(totH) && totH > 0) {
              const elSleep = document.getElementById("valSleep");
              if (elSleep) elSleep.textContent = totH.toFixed(1);
              const elDeep = document.getElementById("valDeepSleep");
              if (elDeep && !isNaN(deepH) && deepH > 0) {
                const pct = Math.round((deepH / totH) * 100);
                elDeep.textContent = `${deepH.toFixed(1)}h (${pct}%)`;
              }
              const sT = document.getElementById('repChartSleepTitle');
              if (sT) sT.textContent = `LAST NIGHT NOCTURNAL SLEEP HYPNOGRAM (${totH.toFixed(1)} HOURS TOTAL)`;
              const tileSleep = document.getElementById('tile360Sleep');
              if (tileSleep) tileSleep.innerHTML = `${totH.toFixed(1)}h <span style="font-size: 0.85rem;">(${deepH.toFixed(1)}h deep)</span>`;
            }
          }

          // 9. Synchronize Subordinate Consoles & Tabs
          if (typeof render360HealthView === 'function') render360HealthView();
          if (typeof renderDietHistory === 'function') renderDietHistory();
          if (typeof loadGoalsComplianceReport === 'function') loadGoalsComplianceReport();
          if (!isFullScanRunning && typeof initFullScanView === 'function') initFullScanView();

          const pageRep = document.getElementById('pageReports');
          if (pageRep && pageRep.classList.contains('active-page') && typeof renderReportsView === 'function') {
            renderReportsView();
          }
        }
      } catch (e) {
        console.warn("pollLiveTelemetryChanges error:", e);
      }
    }
'''

text = text[:start_poll] + new_poll + text[end_poll:]

# 3. Patch switchTab to force-refresh pollLiveTelemetryChanges(true) on tab switch
old_switch_end = '''} else if (tabId === 'fullscan') {
        document.getElementById('navTabFullScan').classList.add('active');
        document.getElementById('pageFullScan').classList.add('active-page');
        initFullScanView();
      }'''

new_switch_end = '''} else if (tabId === 'fullscan') {
        document.getElementById('navTabFullScan').classList.add('active');
        document.getElementById('pageFullScan').classList.add('active-page');
        initFullScanView();
      }
      // Guarantee instant live telemetry refresh across tab navigation
      if (typeof pollLiveTelemetryChanges === 'function') {
        pollLiveTelemetryChanges(true);
      }'''

assert old_switch_end in text, "old_switch_end not found"
text = text.replace(old_switch_end, new_switch_end, 1)

# 4. Patch executeFullWatchConnectSync to force-refresh pollLiveTelemetryChanges(true)
old_sync_step9 = '''// Step 9: Re-hydrate ALL UI Consoles, Fields, Charts, and Metrics
        await fetchTelemetryLatest();
        await fetchTelemetryTimeline();
        if (typeof initFullScanView === 'function') initFullScanView();
        if (typeof renderReportsView === 'function') renderReportsView();'''

new_sync_step9 = '''// Step 9: Re-hydrate ALL UI Consoles, Fields, Charts, and Metrics
        await pollLiveTelemetryChanges(true);
        await fetchTelemetryTimeline();
        if (typeof initFullScanView === 'function') initFullScanView();
        if (typeof render360HealthView === 'function') render360HealthView();
        if (typeof renderReportsView === 'function') renderReportsView();
        if (typeof renderDietHistory === 'function') renderDietHistory();'''

assert old_sync_step9 in text, "old_sync_step9 not found"
text = text.replace(old_sync_step9, new_sync_step9, 1)

# Save to both index.html and w11_htm_app/index.html
with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

print("Both index.html files patched and synchronized!")
