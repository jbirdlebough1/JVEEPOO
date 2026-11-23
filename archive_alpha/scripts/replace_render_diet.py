with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('async function renderDietHistory() {')
end_pos = html.find('function deleteMealEntry', pos)
assert pos != -1 and end_pos != -1, "renderDietHistory block not found"

new_fn = '''async function renderDietHistory() {
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
          gemini_notes: m.desc || ""
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
        Breakfast: { count: 0, kcal: 0, netC: 0, items: [] },
        Lunch: { count: 0, kcal: 0, netC: 0, items: [] },
        Dinner: { count: 0, kcal: 0, netC: 0, items: [] },
        Snack: { count: 0, kcal: 0, netC: 0, items: [] }
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

        // Classify meal occasion
        const nameStr = ((m.meal_name || '') + ' ' + (m.source || '') + ' ' + (m.gemini_notes || '')).toLowerCase();
        let occ = 'Snack';
        if (nameStr.includes('breakfast') || nameStr.includes('morning') || nameStr.includes('egg') || nameStr.includes('omelet') || nameStr.includes('coffee')) {
          occ = 'Breakfast';
        } else if (nameStr.includes('lunch') || nameStr.includes('sandwich') || nameStr.includes('burger') || nameStr.includes('salad') || nameStr.includes('wrap')) {
          occ = 'Lunch';
        } else if (nameStr.includes('dinner') || nameStr.includes('supper') || nameStr.includes('steak') || nameStr.includes('salmon') || nameStr.includes('taco')) {
          occ = 'Dinner';
        }

        occasions[occ].count++;
        occasions[occ].kcal += cal;
        occasions[occ].netC += netC;
        occasions[occ].items.push(m.meal_name || 'Meal');
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
          lEl.textContent = data.items.length > 0 ? data.items.join(', ') : 'None recorded';
          lEl.title = data.items.join(', ');
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

      // 3. Render submitted meals list strictly latest to oldest (shows latest 5 in viewport, scrollable vertically)
      meals.forEach((m, idx) => {
        const netC = m.net_carbs !== undefined ? m.net_carbs : (m.carbs || 0);
        const prot = m.protein || 0;
        const cal = m.calories || m.cal || 0;
        const meetsGoal = (m.meets_goal === 1) || (netC <= 45);

        const badgeHtml = meetsGoal
          ? `<span style="display: inline-block; background: #ecfdf5; color: #15803d; border: 1.5px solid #10b981; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">✅ GOAL MET (≤45g Net)</span>`
          : `<span style="display: inline-block; background: #fef2f2; color: #b91c1c; border: 1.5px solid #ef4444; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">⚠️ EXCEEDS 45g (+${Math.round(netC - 45)}g Net) • GLUT4 WALK NEEDED</span>`;

        const isLatest = (idx === 0);
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
    '''

html = html[:pos] + new_fn + html[end_pos:]

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

print("[✔] Updated renderDietHistory in both index.html and w11_htm_app/index.html!")
