import re

def patch_app(filepath):
    print(f"[*] Patching {filepath}...")
    with open(filepath, "r", encoding="utf-8") as f:
        text = f.read()

    # 1. Update #dietHistoryList styling for latest 5 meals + vertical scrollbar
    old_list_tag = '<div class="diet-history-list" id="dietHistoryList"></div>'
    new_list_tag = '<div class="diet-history-list" id="dietHistoryList" style="max-height: 520px; overflow-y: auto; scrollbar-width: thin; padding-right: 8px; display: flex; flex-direction: column; gap: 10px; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px; background: #f8fafc;"></div>'
    if old_list_tag in text:
        text = text.replace(old_list_tag, new_list_tag)
        print("  [✔] Updated #dietHistoryList container style (scrollable, shows latest 5)")

    # 2. Update renderDietHistory to fetch from /api/meal/recent
    old_render_fn_idx = text.find('function renderDietHistory()')
    next_fn_idx = text.find('\n    function ', old_render_fn_idx + 30)
    old_render_fn = text[old_render_fn_idx:next_fn_idx]

    new_render_fn = """async function renderDietHistory() {
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

      let totalC = 0, totalP = 0, totalKcal = 0;

      meals.forEach(m => {
        const netC = m.net_carbs !== undefined ? m.net_carbs : (m.carbs || 0);
        const prot = m.protein || 0;
        const cal = m.calories || m.cal || 0;
        const meetsGoal = (m.meets_goal === 1) || (netC <= 45);

        totalC += netC;
        totalP += prot;
        totalKcal += cal;

        const badgeHtml = meetsGoal
          ? `<span style="display: inline-block; background: #ecfdf5; color: #15803d; border: 1.5px solid #10b981; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">✅ GOAL MET (≤45g Net)</span>`
          : `<span style="display: inline-block; background: #fef2f2; color: #b91c1c; border: 1.5px solid #ef4444; border-radius: 4px; padding: 2px 8px; font-size: 0.72rem; font-weight: 800; font-family: var(--lcars-mono);">⚠️ EXCEEDS 45g (+${Math.round(netC - 45)}g Net) • GLUT4 WALK NEEDED</span>`;

        const item = document.createElement('div');
        item.className = 'diet-item-card';
        item.style.cssText = "background: #ffffff; border: 1.5px solid #e2e8f0; border-left: 5px solid " + (meetsGoal ? "#10b981" : "#ef4444") + "; border-radius: 6px; padding: 10px 14px; box-shadow: 0 1px 3px rgba(0,0,0,0.04);";
        item.innerHTML = `
          <div style="display: flex; justify-content: space-between; align-items: flex-start; gap: 10px; flex-wrap: wrap;">
            <div style="flex: 1; min-width: 220px;">
              <div style="display: flex; align-items: baseline; gap: 8px; flex-wrap: wrap;">
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

      const sC = document.getElementById('sumCarbs'); if (sC && sC.firstChild) sC.firstChild.textContent = Math.round(totalC);
      const sP = document.getElementById('sumProtein'); if (sP && sP.firstChild) sP.firstChild.textContent = Math.round(totalP);
      const sCal = document.getElementById('sumCalories'); if (sCal && sCal.firstChild) sCal.firstChild.textContent = Math.round(totalKcal);
    }"""

    text = text[:old_render_fn_idx] + new_render_fn + text[next_fn_idx:]
    print("  [✔] Upgraded renderDietHistory to load remote SQLite meals with live badges and metrics")

    # 3. Add Goals Report card in Tab 07 Reports
    goals_report_html = """
          <!-- =========================================================================
               CLINICAL GOALS & ACTIVITY FOLLOW-UP AUDIT REPORT (CLE-080)
               ========================================================================= -->
          <div class="card-box" id="boxGoalsComplianceReport" style="background: #ffffff; border: 1.5px solid #cbd5e1; border-top: 4px solid #16a34a; border-radius: 8px; padding: 14px 18px; margin-bottom: 22px; box-shadow: 0 2px 8px rgba(0,0,0,0.06);">
            
            <!-- Header Row with Title and Timeframe Toggles -->
            <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px; border-bottom: 2px solid #e2e8f0; padding-bottom: 10px; margin-bottom: 12px;">
              <div>
                <strong style="color: #15803d; font-size: 1.05rem; font-family: var(--lcars-mono); letter-spacing: 0.05em; display: flex; align-items: center; gap: 8px;">
                  <span>🎯 CLINICAL GOALS &amp; ACTIVITY FOLLOW-UP AUDIT REPORT</span>
                </strong>
                <span style="font-size: 0.78rem; color: #64748b; display: block; margin-top: 2px;">
                  Tracks Compliance Rate &bull; Identifies Glycemic/Carb Breaches Without Follow-up GLUT4 Activity &bull; Selectable Timeframes &amp; Filters
                </span>
              </div>

              <!-- Timeframe Selectors -->
              <div style="display: flex; gap: 6px; align-items: center; flex-wrap: wrap;">
                <span style="font-size: 0.72rem; font-weight: 800; color: #475569; text-transform: uppercase;">TIMEFRAME:</span>
                <button id="btnGoalsTfWeek" class="lcars-btn" style="font-size: 0.75rem; padding: 5px 12px; font-weight: 800; background: #f1f5f9; color: #334155; border: 1px solid #cbd5e1; border-radius: 4px; cursor: pointer;" onclick="switchGoalsTimeframe('week')">
                  WEEK (7D)
                </button>
                <button id="btnGoalsTfMonth" class="lcars-btn" style="font-size: 0.75rem; padding: 5px 12px; font-weight: 800; background: #16a34a; color: #ffffff; border: 1px solid #15803d; border-radius: 4px; cursor: pointer;" onclick="switchGoalsTimeframe('month')">
                  MONTH (30D)
                </button>
                <button id="btnGoalsTfQtr" class="lcars-btn" style="font-size: 0.75rem; padding: 5px 12px; font-weight: 800; background: #f1f5f9; color: #334155; border: 1px solid #cbd5e1; border-radius: 4px; cursor: pointer;" onclick="switchGoalsTimeframe('qtr')">
                  QTR (90D)
                </button>
                <button id="btnGoalsTfYtd" class="lcars-btn" style="font-size: 0.75rem; padding: 5px 12px; font-weight: 800; background: #f1f5f9; color: #334155; border: 1px solid #cbd5e1; border-radius: 4px; cursor: pointer;" onclick="switchGoalsTimeframe('ytd')">
                  YTD (ANNUAL)
                </button>
              </div>
            </div>

            <!-- KPI Summary Stat Badges -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(170px, 1fr)); gap: 10px; margin-bottom: 14px;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px; text-align: center;">
                <div style="font-size: 0.72rem; font-weight: 800; color: #64748b; text-transform: uppercase;">TOTAL EVALUATED</div>
                <div id="goalsKpiTotal" style="font-size: 1.5rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono); margin-top: 2px;">--</div>
                <div style="font-size: 0.7rem; color: #64748b;">Meal &amp; Biometric Events</div>
              </div>

              <div style="background: #ecfdf5; border: 1.5px solid #10b981; border-radius: 6px; padding: 10px; text-align: center;">
                <div style="font-size: 0.72rem; font-weight: 800; color: #15803d; text-transform: uppercase;">GOALS MET</div>
                <div id="goalsKpiMet" style="font-size: 1.5rem; font-weight: 900; color: #16a34a; font-family: var(--lcars-mono); margin-top: 2px;">--</div>
                <div id="goalsKpiMetPct" style="font-size: 0.7rem; color: #15803d; font-weight: bold;">--% compliance</div>
              </div>

              <div style="background: #fef2f2; border: 1.5px solid #ef4444; border-radius: 6px; padding: 10px; text-align: center;">
                <div style="font-size: 0.72rem; font-weight: 800; color: #b91c1c; text-transform: uppercase;">MISSED • NO ACTIVITY</div>
                <div id="goalsKpiMissed" style="font-size: 1.5rem; font-weight: 900; color: #dc2626; font-family: var(--lcars-mono); margin-top: 2px;">--</div>
                <div style="font-size: 0.7rem; color: #b91c1c; font-weight: bold;">Unmitigated Spikes</div>
              </div>

              <div style="background: #fffbeb; border: 1.5px solid #f59e0b; border-radius: 6px; padding: 10px; text-align: center;">
                <div style="font-size: 0.72rem; font-weight: 800; color: #b45309; text-transform: uppercase;">SAVED VIA GLUT4 WALK</div>
                <div id="goalsKpiMitigated" style="font-size: 1.5rem; font-weight: 900; color: #d97706; font-family: var(--lcars-mono); margin-top: 2px;">--</div>
                <div style="font-size: 0.7rem; color: #b45309; font-weight: bold;">Exercise Blunted Excursion</div>
              </div>

              <div style="background: #f0f9ff; border: 1.5px solid #0284c7; border-radius: 6px; padding: 10px; text-align: center;">
                <div style="font-size: 0.72rem; font-weight: 800; color: #0369a1; text-transform: uppercase;">COMPLIANCE SCORE</div>
                <div id="goalsKpiScore" style="font-size: 1.5rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono); margin-top: 2px;">--%</div>
                <div style="font-size: 0.7rem; color: #0369a1; font-weight: bold;">Clinical Rating</div>
              </div>
            </div>

            <!-- Filter Controls Strip -->
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px; flex-wrap: wrap; gap: 8px; background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 6px; padding: 6px 12px;">
              <div style="display: flex; gap: 6px; align-items: center;">
                <span style="font-size: 0.72rem; font-weight: 800; color: #475569;">FILTER VIEW:</span>
                <button id="btnGoalsFilterAll" class="lcars-pill pill-blue" style="font-size: 0.72rem; padding: 3px 10px; height: auto; cursor: pointer;" onclick="setGoalsFilter('all')">
                  <span id="lblFilterAll">ALL (--)</span>
                </button>
                <button id="btnGoalsFilterMet" class="lcars-pill pill-green" style="font-size: 0.72rem; padding: 3px 10px; height: auto; cursor: pointer; opacity: 0.65;" onclick="setGoalsFilter('met')">
                  <span id="lblFilterMet">✅ MET (--)</span>
                </button>
                <button id="btnGoalsFilterMissed" class="lcars-pill pill-red" style="font-size: 0.72rem; padding: 3px 10px; height: auto; cursor: pointer; opacity: 0.65;" onclick="setGoalsFilter('missed')">
                  <span id="lblFilterMissed">❌ MISSED • NO ACTIVITY (--)</span>
                </button>
              </div>
              <div style="font-size: 0.72rem; color: #64748b; font-family: var(--lcars-mono);">
                Target: &le; 45g Net Carbs / Meal &bull; 2,000 Daily Steps
              </div>
            </div>

            <!-- Scrollable Viewer Table Container -->
            <div id="goalsReportTableContainer" style="max-height: 400px; overflow-y: auto; scrollbar-width: thin; border: 1px solid #cbd5e1; border-radius: 6px; background: #ffffff;">
              <table style="width: 100%; border-collapse: collapse; font-size: 0.8rem; font-family: var(--lcars-mono); text-align: left;">
                <thead style="background: #f1f5f9; position: sticky; top: 0; z-index: 10; border-bottom: 1.5px solid #cbd5e1;">
                  <tr>
                    <th style="padding: 8px 12px; color: #334155;">TIMESTAMP</th>
                    <th style="padding: 8px 12px; color: #334155;">EVENT &amp; NUTRITION</th>
                    <th style="padding: 8px 12px; color: #334155;">TARGET</th>
                    <th style="padding: 8px 12px; color: #334155;">FOLLOW-UP ACTIVITY</th>
                    <th style="padding: 8px 12px; color: #334155; text-align: right;">STATUS</th>
                  </tr>
                </thead>
                <tbody id="goalsReportTableBody">
                  <tr>
                    <td colspan="5" style="padding: 24px; text-align: center; color: #64748b;">Loading goals compliance telemetry...</td>
                  </tr>
                </tbody>
              </table>
            </div>

          </div>
"""
    target_nav_strip = '<!-- =========================================================================\n               CLINICAL BIOMETRIC WATCH ANALYSIS SUB-REPORTS NAVIGATION STRIP'
    if target_nav_strip in text and "boxGoalsComplianceReport" not in text:
        text = text.replace(target_nav_strip, goals_report_html + "\n          " + target_nav_strip)
        print("  [✔] Injected Goals Compliance & Activity Audit Report into Tab 07 Reports")

    # 4. Add JavaScript Controller Functions for Goals Report
    goals_js = """
    // =========================================================================
    // GOALS COMPLIANCE & ACTIVITY FOLLOW-UP AUDIT CONTROLLER (CLE-080)
    // =========================================================================
    let currentGoalsTimeframe = 'month';
    let currentGoalsFilter = 'all';
    let cachedGoalsReportData = null;

    async function loadGoalsComplianceReport(timeframe = null) {
      if (timeframe) currentGoalsTimeframe = timeframe;
      try {
        const resp = await fetch(`/api/goals/compliance-report?timeframe=${currentGoalsTimeframe}`);
        if (!resp.ok) return;
        cachedGoalsReportData = await resp.json();
        renderGoalsComplianceUI();
      } catch (e) {
        console.error("Could not load goals compliance report:", e);
      }
    }

    function switchGoalsTimeframe(tf) {
      soundButtonPress();
      currentGoalsTimeframe = tf;
      ['Week', 'Month', 'Qtr', 'Ytd'].forEach(t => {
        const btn = document.getElementById(`btnGoalsTf${t}`);
        if (btn) {
          if (t.toLowerCase() === tf) {
            btn.style.background = '#16a34a';
            btn.style.color = '#ffffff';
            btn.style.borderColor = '#15803d';
          } else {
            btn.style.background = '#f1f5f9';
            btn.style.color = '#334155';
            btn.style.borderColor = '#cbd5e1';
          }
        }
      });
      loadGoalsComplianceReport(tf);
    }

    function setGoalsFilter(filt) {
      soundButtonPress();
      currentGoalsFilter = filt;
      const bAll = document.getElementById('btnGoalsFilterAll');
      const bMet = document.getElementById('btnGoalsFilterMet');
      const bMissed = document.getElementById('btnGoalsFilterMissed');

      if (bAll) bAll.style.opacity = filt === 'all' ? '1' : '0.65';
      if (bMet) bMet.style.opacity = filt === 'met' ? '1' : '0.65';
      if (bMissed) bMissed.style.opacity = filt === 'missed' ? '1' : '0.65';

      renderGoalsComplianceUI();
    }

    function renderGoalsComplianceUI() {
      if (!cachedGoalsReportData || !cachedGoalsReportData.summary) return;
      const s = cachedGoalsReportData.summary;
      const events = cachedGoalsReportData.events || [];

      // Update KPI Counter Tiles
      const elTot = document.getElementById('goalsKpiTotal'); if (elTot) elTot.textContent = s.total_events;
      const elMet = document.getElementById('goalsKpiMet'); if (elMet) elMet.textContent = s.met_count;
      const elMetPct = document.getElementById('goalsKpiMetPct');
      if (elMetPct && s.total_events > 0) {
        elMetPct.textContent = `${Math.round((s.met_count / s.total_events) * 100)}% compliant`;
      }
      const elMissed = document.getElementById('goalsKpiMissed'); if (elMissed) elMissed.textContent = s.missed_no_activity_count;
      const elMit = document.getElementById('goalsKpiMitigated'); if (elMit) elMit.textContent = s.mitigated_count;
      const elScore = document.getElementById('goalsKpiScore'); if (elScore) elScore.textContent = `${s.compliance_pct}%`;

      // Update Filter Button Labels
      const lblAll = document.getElementById('lblFilterAll'); if (lblAll) lblAll.textContent = `ALL (${s.total_events})`;
      const lblMet = document.getElementById('lblFilterMet'); if (lblMet) lblMet.textContent = `✅ MET (${s.met_count})`;
      const lblMissed = document.getElementById('lblFilterMissed'); if (lblMissed) lblMissed.textContent = `❌ MISSED • NO ACTIVITY (${s.missed_no_activity_count})`;

      // Filter events
      let filtered = events;
      if (currentGoalsFilter === 'met') {
        filtered = events.filter(e => e.status === 'MET');
      } else if (currentGoalsFilter === 'missed') {
        filtered = events.filter(e => e.status === 'MISSED');
      }

      const tbody = document.getElementById('goalsReportTableBody');
      if (!tbody) return;

      if (filtered.length === 0) {
        tbody.innerHTML = `
          <tr>
            <td colspan="5" style="padding: 24px; text-align: center; color: #64748b;">
              No goal events match the selected timeframe and filter.
            </td>
          </tr>
        `;
        return;
      }

      tbody.innerHTML = filtered.map(item => {
        let statusBadge = '';
        let rowBg = '#ffffff';

        if (item.status === 'MET') {
          statusBadge = `<span style="background: #ecfdf5; color: #15803d; border: 1.5px solid #10b981; border-radius: 4px; padding: 3px 8px; font-weight: 800; font-size: 0.72rem;">✅ MET</span>`;
        } else if (item.status === 'MITIGATED') {
          statusBadge = `<span style="background: #fffbeb; color: #b45309; border: 1.5px solid #f59e0b; border-radius: 4px; padding: 3px 8px; font-weight: 800; font-size: 0.72rem;">⚡ MITIGATED</span>`;
          rowBg = '#fffdfa';
        } else {
          statusBadge = `<span style="background: #fef2f2; color: #b91c1c; border: 1.5px solid #ef4444; border-radius: 4px; padding: 3px 8px; font-weight: 800; font-size: 0.72rem;">❌ MISSED</span>`;
          rowBg = '#fffafa';
        }

        return `
          <tr style="background: ${rowBg}; border-bottom: 1px solid #e2e8f0;">
            <td style="padding: 10px 12px; color: #475569; white-space: nowrap; font-size: 0.76rem;">${item.timestamp}</td>
            <td style="padding: 10px 12px;">
              <strong style="color: #0f172a; font-size: 0.86rem; font-family: var(--lcars-font);">${item.meal_name}</strong>
              <div style="font-size: 0.72rem; color: #64748b; margin-top: 2px;">
                ${item.net_carbs}g Net Carbs &bull; ${item.calories} kcal &bull; Source: ${item.vendor || 'Nutritional Log'}
              </div>
            </td>
            <td style="padding: 10px 12px; color: #334155; white-space: nowrap;">
              &le; ${item.target_carbs}g Net
            </td>
            <td style="padding: 10px 12px; font-size: 0.76rem;">
              ${item.activity_status}
            </td>
            <td style="padding: 10px 12px; text-align: right; white-space: nowrap;">
              ${statusBadge}
            </td>
          </tr>
        `;
      }).join('');
    }
"""
    if "function loadGoalsComplianceReport" not in text:
        target_render_reports = "function renderReportsView() {"
        text = text.replace(target_render_reports, goals_js + "\n    " + target_render_reports)
        print("  [✔] Injected loadGoalsComplianceReport JS controller")

    # Wire loadGoalsComplianceReport into switchTab('reports') and DOMContentLoaded
    if "loadGoalsComplianceReport();" not in text:
        target_switch_reports = "case 'reports':"
        if target_switch_reports in text:
            text = text.replace(target_switch_reports, target_switch_reports + "\n          loadGoalsComplianceReport();")
            print("  [✔] Wired loadGoalsComplianceReport into switchTab('reports')")

    with open(filepath, "w", encoding="utf-8") as f:
        f.write(text)
    print(f"[✔] Successfully patched {filepath}\n")

patch_app(r"D:\veepoo\index.html")
patch_app(r"D:\veepoo\w11_htm_app\index.html")
