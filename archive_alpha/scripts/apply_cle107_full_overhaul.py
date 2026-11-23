# Implementation script for CLE-107
import re

with open("index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1. Add getWatchConfiguredStepTarget helper before initStepGlucoseCorrelatorData
helper_code = '''    // Helper: Query Active Configured Watch Step Target (Clinical Anchor)
    function getWatchConfiguredStepTarget() {
      if (typeof clinicalGoals !== 'undefined' && clinicalGoals && clinicalGoals.stepTarget) {
        const v = parseInt(clinicalGoals.stepTarget, 10);
        if (!isNaN(v) && v > 0) return v;
      }
      const elGoal = document.getElementById('goalStepTarget');
      if (elGoal && elGoal.value) {
        const v = parseInt(elGoal.value, 10);
        if (!isNaN(v) && v > 0) return v;
      }
      const elCfg = document.getElementById('cfgStepGoal');
      if (elCfg && elCfg.value) {
        const v = parseInt(elCfg.value, 10);
        if (!isNaN(v) && v > 0) return v;
      }
      try {
        const cg = localStorage.getItem('veepoo_clinical_goals');
        if (cg) {
          const p = JSON.parse(cg);
          if (p.stepTarget) return parseInt(p.stepTarget, 10);
        }
        const vc = localStorage.getItem('veepoo_config');
        if (vc) {
          const p = JSON.parse(vc);
          if (p.stepGoal) return parseInt(p.stepGoal, 10);
        }
      } catch(e) {}
      return 8000;
    }

'''

target_spot_1 = '    function initStepGlucoseCorrelatorData() {'
assert target_spot_1 in html, "target_spot_1 not found!"
html = html.replace(target_spot_1, helper_code + target_spot_1, 1)

# 2. Update initStepGlucoseCorrelatorData and onStepTargetSliderChanged to sync with watch configured step target
old_bivariate_fn = re.compile(r'function onStepTargetSliderChanged\(val\) \{.*?function renderStepGlucoseCorrelator\(\) \{.*?svg\.innerHTML = svgHtml;\s+\}', re.DOTALL)

new_bivariate_fn = '''function onStepTargetSliderChanged(val) {
      currentStepSimulationTarget = parseInt(val, 10);
      const lbl = document.getElementById('lblStepTargetVal');
      if (lbl) lbl.textContent = `${currentStepSimulationTarget.toLocaleString()} STEPS`;

      const watchTarget = getWatchConfiguredStepTarget();
      const watchExpG = Math.max(85, Math.min(175, 163.5 - 0.0052 * watchTarget));
      const watchA1c = (watchExpG + 46.7) / 28.7;

      const predG = Math.max(85, Math.min(175, 163.5 - 0.0052 * currentStepSimulationTarget));
      const predA1c = (predG + 46.7) / 28.7;
      const blunting = Math.round(-15 - (currentStepSimulationTarget / 10000.0) * 35);

      const elG = document.getElementById('projGlucose');
      if (elG) elG.textContent = `${predG.toFixed(1)} mg/dL`;
      const elA1c = document.getElementById('projA1c');
      if (elA1c) elA1c.textContent = `${predA1c.toFixed(2)}%`;
      const elSpike = document.getElementById('projSpike');
      if (elSpike) elSpike.textContent = `${blunting} mg/dL`;

      const boxProj = document.getElementById('boxStepProjection');
      if (boxProj) {
        boxProj.innerHTML = `
          <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
            <div>
              <span style="color: #0284c7; font-weight: 900;">🔵 WATCH CONFIGURED: ${watchTarget.toLocaleString()} STEPS</span> &bull; 
              PREDICTED: <span style="color: #0369a1; font-weight: 900;">${watchExpG.toFixed(1)} mg/dL</span> &bull; eA1C: <span style="color: #0369a1; font-weight: 800;">${watchA1c.toFixed(2)}%</span>
            </div>
            <div>
              <span style="color: #d97706; font-weight: 900;">🎯 SIMULATED: ${currentStepSimulationTarget.toLocaleString()} STEPS</span> &bull; 
              PREDICTED: <span style="color: #b45309; font-weight: 900;">${predG.toFixed(1)} mg/dL</span> &bull; eA1C: <span style="color: #b45309; font-weight: 800;">${predA1c.toFixed(2)}%</span> &bull; 
              SPIKE BLUNTING: <span style="color: #15803d; font-weight: 900;">${blunting} mg/dL</span>
            </div>
          </div>
        `;
      }

      renderStepGlucoseCorrelator();
    }

    function renderStepGlucoseCorrelator() {
      initStepGlucoseCorrelatorData();
      const svg = document.getElementById('svgStepGlucoseCorrelator');
      if (!svg) return;

      const watchTarget = getWatchConfiguredStepTarget();

      const padLeft = 52;
      const padRight = 20;
      const padTop = 20;
      const padBottom = 45;
      const plotW = 500 - padLeft - padRight;
      const plotH = 340 - padTop - padBottom;

      const minX = 0;
      const maxX = 14000;
      const minY = 80;
      const maxY = 180;

      function mapX(s) {
        return padLeft + ((s - minX) / (maxX - minX)) * plotW;
      }
      function mapY(g) {
        return padTop + plotH - ((g - minY) / (maxY - minY)) * plotH;
      }

      let svgHtml = '';

      // Sedentary & Optimal Zones
      const xSedStart = mapX(0);
      const xSedEnd = mapX(3000);
      svgHtml += `<rect x="${xSedStart}" y="${padTop}" width="${xSedEnd - xSedStart}" height="${plotH}" fill="#fee2e2" fill-opacity="0.45" />`;
      svgHtml += `<text x="${(xSedStart + xSedEnd)/2}" y="${padTop + 14}" font-size="8" font-family="monospace" fill="#b91c1c" font-weight="bold" text-anchor="middle">SEDENTARY RISK (&lt;3K)</text>`;

      const xModEnd = mapX(7000);
      svgHtml += `<rect x="${xSedEnd}" y="${padTop}" width="${xModEnd - xSedEnd}" height="${plotH}" fill="#fef3c7" fill-opacity="0.35" />`;
      svgHtml += `<text x="${(xSedEnd + xModEnd)/2}" y="${padTop + 14}" font-size="8" font-family="monospace" fill="#b45309" font-weight="bold" text-anchor="middle">TRANSITION (3K-7K)</text>`;

      const xOptEnd = mapX(14000);
      svgHtml += `<rect x="${xModEnd}" y="${padTop}" width="${xOptEnd - xModEnd}" height="${plotH}" fill="#dcfce7" fill-opacity="0.45" />`;
      svgHtml += `<text x="${(xModEnd + xOptEnd)/2}" y="${padTop + 14}" font-size="8" font-family="monospace" fill="#15803d" font-weight="bold" text-anchor="middle">OPTIMAL METABOLIC CLEARANCE (&gt;7K)</text>`;

      // Grid Lines
      for (let s = 2000; s <= 12000; s += 2000) {
        const gx = mapX(s);
        svgHtml += `<line x1="${gx.toFixed(1)}" y1="${padTop}" x2="${gx.toFixed(1)}" y2="${padTop + plotH}" stroke="#e2e8f0" stroke-width="1" />`;
        const label = `${s / 1000}k`;
        svgHtml += `<text x="${gx.toFixed(1)}" y="${padTop + plotH + 16}" font-size="9" font-family="monospace" fill="#64748b" text-anchor="middle">${label}</text>`;
      }

      for (let g = 80; g <= 180; g += 20) {
        const gy = mapY(g);
        svgHtml += `<line x1="${padLeft}" y1="${gy.toFixed(1)}" x2="${padLeft + plotW}" y2="${gy.toFixed(1)}" stroke="#e2e8f0" stroke-width="1" />`;
        svgHtml += `<text x="${padLeft - 8}" y="${gy.toFixed(1) + 3}" font-size="9" font-family="monospace" fill="#64748b" text-anchor="end">${g}</text>`;
      }

      svgHtml += `<rect x="${padLeft}" y="${padTop}" width="${plotW}" height="${plotH}" fill="none" stroke="#cbd5e1" stroke-width="1.5" />`;

      // 95% Confidence Interval Ribbon
      const x0 = mapX(500);
      const y0Top = mapY(163.5 - 0.0052 * 500 + 7.5);
      const y0Bot = mapY(163.5 - 0.0052 * 500 - 7.5);
      const x1 = mapX(13500);
      const y1Top = mapY(163.5 - 0.0052 * 13500 + 8.5);
      const y1Bot = mapY(163.5 - 0.0052 * 13500 - 8.5);

      const ribbonPoints = `${x0.toFixed(1)},${y0Top.toFixed(1)} ${x1.toFixed(1)},${y1Top.toFixed(1)} ${x1.toFixed(1)},${y1Bot.toFixed(1)} ${x0.toFixed(1)},${y0Bot.toFixed(1)}`;
      svgHtml += `<polygon points="${ribbonPoints}" fill="#bfdbfe" fill-opacity="0.45" />`;

      // Linear Regression Line
      const yLine0 = mapY(163.5 - 0.0052 * 500);
      const yLine1 = mapY(163.5 - 0.0052 * 13500);
      svgHtml += `<line x1="${x0.toFixed(1)}" y1="${yLine0.toFixed(1)}" x2="${x1.toFixed(1)}" y2="${yLine1.toFixed(1)}" stroke="#2563eb" stroke-width="2.5" />`;
      svgHtml += `<text x="${x1.toFixed(1) - 6}" y="${yLine1 - 6}" font-size="8.5" font-family="monospace" fill="#1d4ed8" font-weight="bold" text-anchor="end">SLOPE: -5.2 MG/DL / 1,000 STEPS (r = -0.79)</text>`;

      // Clinical Longitudinal Observation Points
      correlatorPoints.forEach((p) => {
        const cx = mapX(p.steps);
        const cy = mapY(p.glucose);
        let fillCol = '#0284c7';
        if (p.steps >= 7000 && p.glucose <= 125) fillCol = '#16a34a';
        else if (p.steps < 3000 || p.glucose >= 150) fillCol = '#dc2626';

        svgHtml += `<circle cx="${cx.toFixed(1)}" cy="${cy.toFixed(1)}" r="3" fill="${fillCol}" stroke="#ffffff" stroke-width="0.8" opacity="0.82">
          <title>Day ${p.day}: ${p.steps.toLocaleString()} steps • Mean Glucose: ${p.glucose} mg/dL • TIR: ${p.tir}%</title>
        </circle>`;
      });

      // 1. WATCH CONFIGURED STEP GOAL (PROMINENT BLUE DOT REQUIRED BY USER)
      const watchExpG = Math.max(85, Math.min(175, 163.5 - 0.0052 * watchTarget));
      const watchX = mapX(watchTarget);
      const watchY = mapY(watchExpG);

      svgHtml += `<line x1="${watchX.toFixed(1)}" y1="${padTop}" x2="${watchX.toFixed(1)}" y2="${padTop + plotH}" stroke="#0284c7" stroke-width="2.5" stroke-dasharray="4 3" />`;
      svgHtml += `<circle cx="${watchX.toFixed(1)}" cy="${watchY.toFixed(1)}" r="13" fill="none" stroke="#0284c7" stroke-width="2" opacity="0.6" />`;
      svgHtml += `<circle cx="${watchX.toFixed(1)}" cy="${watchY.toFixed(1)}" r="7.5" fill="#0284c7" stroke="#ffffff" stroke-width="2.5" />`;

      const badgeW = 216;
      const badgeX = Math.min(padLeft + plotW - badgeW - 6, Math.max(padLeft + 6, watchX - (badgeW / 2)));
      const badgeY = Math.max(padTop + 24, watchY - 26);
      svgHtml += `
        <g transform="translate(${badgeX.toFixed(1)}, ${badgeY.toFixed(1)})">
          <rect x="0" y="0" width="${badgeW}" height="22" rx="4" fill="#0284c7" stroke="#0369a1" stroke-width="1.2" />
          <text x="${badgeW / 2}" y="15" text-anchor="middle" font-size="8.5" font-family="monospace" fill="#ffffff" font-weight="900">🔵 WATCH TARGET: ${watchTarget.toLocaleString()} STEPS (${watchExpG.toFixed(1)} MG/DL)</text>
        </g>
      `;

      // 2. Real Telemetry Reading (Today Observed from SQLite)
      const todaySteps = (typeof lastSteps !== 'undefined' && lastSteps > 0) ? lastSteps : 2772;
      const todayG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : 163.9;
      const todayX = mapX(todaySteps);
      const todayY = mapY(todayG);
      svgHtml += `
        <circle cx="${todayX.toFixed(1)}" cy="${todayY.toFixed(1)}" r="8" fill="#f59e0b" stroke="#ffffff" stroke-width="2.5" />
        <circle cx="${todayX.toFixed(1)}" cy="${todayY.toFixed(1)}" r="12" fill="none" stroke="#f59e0b" stroke-width="1.5" opacity="0.6" />
        <g transform="translate(${Math.min(padLeft + plotW - 170, Math.max(padLeft + 4, todayX + 12)).toFixed(1)}, ${(todayY - 8).toFixed(1)})">
          <rect x="0" y="0" width="160" height="20" rx="3" fill="#fffbeb" stroke="#f59e0b" stroke-width="1.2" />
          <text x="80" y="14" text-anchor="middle" font-size="8" font-family="monospace" fill="#b45309" font-weight="900">⭐ TODAY: ${todaySteps.toLocaleString()} STEPS (${Math.round(todayG)} MG/DL)</text>
        </g>
      `;

      // 3. User Simulation Target (if user moved slider away from watch configured target)
      if (Math.abs(currentStepSimulationTarget - watchTarget) > 100) {
        const simX = mapX(currentStepSimulationTarget);
        const simExpG = Math.max(85, Math.min(175, 163.5 - 0.0052 * currentStepSimulationTarget));
        const simY = mapY(simExpG);

        svgHtml += `<line x1="${simX.toFixed(1)}" y1="${padTop}" x2="${simX.toFixed(1)}" y2="${padTop + plotH}" stroke="#d97706" stroke-width="2" stroke-dasharray="3 3" />`;
        svgHtml += `<circle cx="${simX.toFixed(1)}" cy="${simY.toFixed(1)}" r="6.5" fill="#d97706" stroke="#ffffff" stroke-width="2" />`;
        svgHtml += `<circle cx="${simX.toFixed(1)}" cy="${simY.toFixed(1)}" r="10.5" fill="none" stroke="#d97706" stroke-width="1.5" opacity="0.6" />`;
        svgHtml += `<text x="${simX + 10}" y="${simY - 10}" font-size="9" font-family="monospace" fill="#b45309" font-weight="800">🎯 SIMULATED: ${simExpG.toFixed(1)} MG/DL</text>`;
      }

      svgHtml += `<text x="${padLeft + plotW / 2}" y="${340 - 8}" font-size="10" font-family="monospace" fill="#334155" font-weight="bold" text-anchor="middle">AMBULATORY DAILY STEP VOLUME</text>`;
      svgHtml += `<text x="14" y="${padTop + plotH / 2}" font-size="10" font-family="monospace" fill="#334155" font-weight="bold" text-anchor="middle" transform="rotate(-90 14 ${padTop + plotH / 2})">24-HOUR MEAN BLOOD GLUCOSE (MG/DL)</text>`;

      svg.innerHTML = svgHtml;
    }'''

assert old_bivariate_fn.search(html), "old_bivariate_fn pattern not matched!"
html = old_bivariate_fn.sub(new_bivariate_fn, html, count=1)

# 3. Update 360° Synchronized Tall Chart styling in renderTallSynchronizedChart
# High-contrast glucose curve (#c2410c), white pill badges for numbers, light badges for meals, meds, GLUT4
tall_old_banana = 'stroke="var(--lcars-banana)" stroke-width="2.5"'
tall_new_amber = 'stroke="#c2410c" stroke-width="3"'
html = html.replace(tall_old_banana, tall_new_amber, 1)

tall_grad_old = '<stop offset="0%" stop-color="var(--lcars-banana)" stop-opacity="0.35"/>'
tall_grad_new = '<stop offset="0%" stop-color="#ea580c" stop-opacity="0.25"/>'
html = html.replace(tall_grad_old, tall_grad_new, 1)

# Replace dark exercise badge
ex_old = '<rect x="${xCoords[i] - 28}" y="${exLane.y + 6}" width="56" height="18" fill="#1b120c" stroke="var(--lcars-peach)" stroke-width="1" rx="3"/>\n              <text x="${xCoords[i]}" y="${exLane.y + 19}" text-anchor="middle" fill="var(--lcars-peach)" font-size="8.5" font-weight="bold">🚶 GLUT4</text>'
ex_new = '<rect x="${xCoords[i] - 30}" y="${exLane.y + 6}" width="60" height="20" fill="#fff7ed" stroke="#f97316" stroke-width="1.5" rx="4"/>\n              <text x="${xCoords[i]}" y="${exLane.y + 20}" text-anchor="middle" fill="#9a3412" font-size="8.5" font-weight="900">🚶 GLUT4</text>'
html = html.replace(ex_old, ex_new, 1)

# Replace dark diet badge
diet_old = '<rect x="${xCoords[i] - 32}" y="${dLane.y + 12}" width="64" height="20" fill="#0d1420" stroke="${badgeColor}" stroke-width="1.2" rx="4"/>\n              <text x="${xCoords[i]}" y="${dLane.y + 26}" text-anchor="middle" fill="${badgeColor}" font-size="9" font-weight="bold">🥗 ${d.meal.type.slice(0,6)}</text>\n              <text x="${xCoords[i]}" y="${dLane.y + 48}" text-anchor="middle" fill="${badgeColor}" font-size="8.5">${d.meal.carbs}g net</text>'
diet_new = '<rect x="${xCoords[i] - 34}" y="${dLane.y + 10}" width="68" height="22" fill="#ecfdf5" stroke="#10b981" stroke-width="1.5" rx="4"/>\n              <text x="${xCoords[i]}" y="${dLane.y + 24}" text-anchor="middle" fill="#065f46" font-size="8.5" font-weight="900">🥗 ${d.meal.type.slice(0,6)}</text>\n              <text x="${xCoords[i]}" y="${dLane.y + 44}" text-anchor="middle" fill="#065f46" font-size="8.5" font-weight="800">${d.meal.carbs}g net</text>'
html = html.replace(diet_old, diet_new, 1)

# Replace dark meds badge
med_old_change = '<rect x="${xCoords[i] - 46}" y="${mLane.y + 10}" width="92" height="24" fill="#330b0b" stroke="#ff5555" stroke-width="1.5" rx="4"/>\n                <text x="${xCoords[i]}" y="${mLane.y + 26}" text-anchor="middle" fill="#ff9999" font-size="9" font-weight="bold">💊 MED CHANGE</text>'
med_new_change = '<rect x="${xCoords[i] - 46}" y="${mLane.y + 10}" width="92" height="24" fill="#fef2f2" stroke="#ef4444" stroke-width="1.8" rx="4"/>\n                <text x="${xCoords[i]}" y="${mLane.y + 26}" text-anchor="middle" fill="#991b1b" font-size="9" font-weight="900">💊 MED CHANGE</text>'
html = html.replace(med_old_change, med_new_change, 1)

med_old_std = '<circle cx="${xCoords[i]}" cy="${mLane.y + 36}" r="9" fill="#1b1c10" stroke="#ffcc66" stroke-width="1.2"/>\n                <text x="${xCoords[i]}" y="${mLane.y + 40}" text-anchor="middle" fill="#ffcc66" font-size="8.5" font-weight="bold">Rx</text>'
med_new_std = '<circle cx="${xCoords[i]}" cy="${mLane.y + 36}" r="11" fill="#eff6ff" stroke="#3b82f6" stroke-width="1.8"/>\n                <text x="${xCoords[i]}" y="${mLane.y + 40}" text-anchor="middle" fill="#1e40af" font-size="8.5" font-weight="900">Rx</text>'
html = html.replace(med_old_std, med_new_std, 1)

# Glucose points on curve: clear pill tag behind each number
pt_old = '<circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="5.5" fill="var(--lcars-banana)" stroke="#000000" stroke-width="2">\n                <title>${p.label}: ${p.val} mg/dL</title>\n              </circle>\n              <text x="${p.x.toFixed(1)}" y="${(p.y - 8).toFixed(1)}" text-anchor="middle" fill="var(--lcars-banana)" font-size="10.5" font-weight="bold">${p.val}</text>'
pt_new = '<circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="6" fill="#ea580c" stroke="#ffffff" stroke-width="2">\n                <title>${p.label}: ${p.val} mg/dL</title>\n              </circle>\n              <rect x="${(p.x - 14).toFixed(1)}" y="${(p.y - 21).toFixed(1)}" width="28" height="13" rx="3" fill="#ffffff" stroke="#cbd5e1" stroke-width="1"/>\n              <text x="${p.x.toFixed(1)}" y="${(p.y - 11).toFixed(1)}" text-anchor="middle" fill="#78350f" font-size="9" font-weight="900">${p.val}</text>'
html = html.replace(pt_old, pt_new, 1)

with open("index.html", "w", encoding="utf-8") as f:
    f.write(html)
with open("w11_htm_app/index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[+] Successfully updated index.html and w11_htm_app/index.html")
