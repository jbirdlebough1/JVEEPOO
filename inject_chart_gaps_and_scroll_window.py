import re

def apply_chart_gaps_and_scroll_window():
    path = r"D:\veepoo\w11_htm_app\index.html"
    with open(path, "r", encoding="utf-8") as f:
        html = f.read()

    print(f"Original size: {len(html)} bytes")

    # =========================================================================
    # 1. ENHANCE RAW SENSOR TELEMETRY LOG SECTION WITH BOUNDED SCROLL VIEWPORT
    #    AND WEEKLY, MONTHLY, YEARLY TIMEFRAME SELECTORS
    # =========================================================================
    old_raw_table_pattern = re.compile(
        r'<div class="chart-box">\s*<div class="chart-title">\s*<span>RAW SENSOR TELEMETRY LOG ENTRIES</span>.*?<tbody id="telemetryTableBody"></tbody>\s*</table>\s*</div>',
        re.DOTALL
    )

    new_raw_table_html = '''<div class="chart-box" id="boxRawTelemetryLog" style="background: #ffffff; border-left: 6px solid #7c3aed; margin-bottom: 20px;">
            <div class="chart-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: #6b21a8; font-weight: 900; font-size: 1.05rem;">📋 RAW SENSOR TELEMETRY AUDIT LOGS</span>
                <span style="font-size: 0.78rem; color: #64748b; display: block; font-family: var(--lcars-mono); margin-top: 2px;">
                  BOUNDED SCROLL VIEWPORT &bull; ZERO FOR UNRECORDED DATA &bull; CHARGING GAPS PRESERVED (1+ HR OFF-WRIST)
                </span>
              </div>
              <!-- Timeframe Selector to keep viewport size down -->
              <div style="display: flex; gap: 6px; align-items: center; flex-wrap: wrap;">
                <span style="font-size: 0.75rem; color: #475569; font-weight: 800; margin-right: 4px;">AUDIT RANGE:</span>
                <button id="btnRawDaily" class="lcars-btn btn-banana" style="padding: 4px 10px; font-size: 0.75rem; font-weight: bold;" onclick="setRawDataTimeframe('daily')">DAILY (24H)</button>
                <button id="btnRawWeekly" class="lcars-btn btn-blue" style="padding: 4px 10px; font-size: 0.75rem; font-weight: bold;" onclick="setRawDataTimeframe('weekly')">WEEKLY (7D)</button>
                <button id="btnRawMonthly" class="lcars-btn btn-blue" style="padding: 4px 10px; font-size: 0.75rem; font-weight: bold;" onclick="setRawDataTimeframe('monthly')">MONTHLY (30D)</button>
                <button id="btnRawYearly" class="lcars-btn btn-blue" style="padding: 4px 10px; font-size: 0.75rem; font-weight: bold;" onclick="setRawDataTimeframe('yearly')">YEARLY (12M)</button>
                <span id="repRecordCount" style="font-family: var(--lcars-mono); color: #7c3aed; font-weight: 800; font-size: 0.8rem; margin-left: 6px; background: #f3e8ff; padding: 3px 8px; border-radius: 4px; border: 1px solid #d8b4fe;">14 HOURLY SAMPLES AUDITED</span>
              </div>
            </div>

            <!-- Scrollable Window with Sticky Header to keep viewport size down -->
            <div id="rawTelemetryScrollContainer" style="max-height: 320px; overflow-y: auto; overflow-x: auto; border: 1.5px solid #cbd5e1; border-radius: 6px; background: #ffffff; box-shadow: inset 0 1px 3px rgba(0,0,0,0.04);">
              <table class="telemetry-table" style="width: 100%; border-collapse: collapse; margin: 0; font-family: var(--lcars-mono); font-size: 0.82rem;">
                <thead style="position: sticky; top: 0; z-index: 5; background: #f8fafc; border-bottom: 2px solid #cbd5e1;">
                  <tr>
                    <th style="padding: 8px 10px; text-align: left; background: #f1f5f9; color: #0f172a; font-weight: 800; border-bottom: 1.5px solid #cbd5e1;">TIMESTAMP / PERIOD</th>
                    <th style="padding: 8px 10px; text-align: left; background: #f1f5f9; color: #b45309; font-weight: 800; border-bottom: 1.5px solid #cbd5e1;">GLUCOSE (MG/DL)</th>
                    <th style="padding: 8px 10px; text-align: left; background: #f1f5f9; color: #1d4ed8; font-weight: 800; border-bottom: 1.5px solid #cbd5e1;">CORE / SKIN TEMP</th>
                    <th style="padding: 8px 10px; text-align: left; background: #f1f5f9; color: #c2410c; font-weight: 800; border-bottom: 1.5px solid #cbd5e1;">STEPS / ACTIVITY</th>
                    <th style="padding: 8px 10px; text-align: left; background: #f1f5f9; color: #0f766e; font-weight: 800; border-bottom: 1.5px solid #cbd5e1;">METABOLIC STATUS / CHARGING</th>
                    <th style="padding: 8px 10px; text-align: left; background: #f1f5f9; color: #7c3aed; font-weight: 800; border-bottom: 1.5px solid #cbd5e1;">CYCLE / EHR STATUS</th>
                  </tr>
                </thead>
                <tbody id="telemetryTableBody"></tbody>
              </table>
            </div>
            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 8px; font-size: 0.74rem; color: #64748b; font-family: var(--lcars-mono); flex-wrap: wrap; gap: 6px;">
              <span>⚡ Note: Gaps indicate off-wrist watch charging (~1.2h). Unrecorded intervals display 0.</span>
              <span style="font-weight: bold; color: #0369a1;">↕ SCROLLABLE VIEWPORT ACTIVE &bull; 320PX COMPACT BOUNDS</span>
            </div>
          </div>'''

    if old_raw_table_pattern.search(html):
        html = old_raw_table_pattern.sub(new_raw_table_html, html)
        print("[✔] Injected scroll window and timeframe buttons into Raw Telemetry section")
    else:
        print("[!] Could not match old_raw_table_pattern")

    # =========================================================================
    # 2. UPDATE getTallSynchronizedDataset('daily') TO INCLUDE CHARGING GAP
    # =========================================================================
    old_daily_dataset = """          { time: '10:00', g: 142, sys: 116, dia: 74, hr: 68, hrv: 64, spo2: 99.0, temp: 97.4, steps: 2650, meal: null, ex: null, med: null, notes: "Optimal sustained Time-in-Range (TIR 60–200)" },
          { time: '12:30', g: 158, sys: 115, dia: 73, hr: 70, hrv: 60, spo2: 98.0, temp: 97.2, steps: 3200, meal: null, ex: null, med: null, notes: "Pre-lunch euvolemic resting state" },"""

    new_daily_dataset = """          { time: '10:00', g: 142, sys: 116, dia: 74, hr: 68, hrv: 64, spo2: 99.0, temp: 97.4, steps: 2650, meal: null, ex: null, med: null, notes: "Optimal sustained Time-in-Range (TIR 60–200)" },
          { time: '11:00', g: null, sys: null, dia: null, hr: null, hrv: null, spo2: null, temp: null, steps: 0, isChargingGap: true, meal: null, ex: null, med: null, notes: "⚡ Watch Charging on Dock (Off-Wrist • 0 Telemetry • Gaps Expected)" },
          { time: '12:30', g: 158, sys: 115, dia: 73, hr: 70, hrv: 60, spo2: 98.0, temp: 97.2, steps: 3200, meal: null, ex: null, med: null, notes: "Pre-lunch euvolemic resting state (Watch donned post-charge)" },"""

    if old_daily_dataset in html:
        html = html.replace(old_daily_dataset, new_daily_dataset)
        print("[✔] Injected Watch Charging Gap into getTallSynchronizedDataset('daily')")
    else:
        print("[!] Could not find old_daily_dataset marker")

    # =========================================================================
    # 3. UPDATE renderTallSynchronizedChart TO BREAK CURVES ACROSS CHARGING GAP
    #    AND RENDER SPANNING CHARGING INDICATOR IN 8-LANE MASTER CHART
    # =========================================================================
    old_tall_render_curves = """      // Lane 1: Glucose Curve & Shading
      const gLane = lanes[0];
      const gPts = data.map((d, i) => {
        const y = gLane.y + gLane.h - ((d.g - gLane.minY) / (gLane.maxY - gLane.minY)) * gLane.h;
        return { x: xCoords[i], y: y, val: d.g, label: d.time };
      });
      const gPoly = gPts.map(p => `${p.x.toFixed(1)},${p.y.toFixed(1)}`).join(" ");
      const gArea = `${padLeft},${gLane.y + gLane.h} ` + gPoly + ` ${padLeft + plotW},${gLane.y + gLane.h}`;
      svg += `
        <polygon points="${gArea}" fill="url(#gradTallGlucose)"/>
        <polyline points="${gPoly}" fill="none" stroke="var(--lcars-banana)" stroke-width="2.5"/>
      `;"""

    new_tall_render_curves = """      // Identify Charging Gaps across the timeline
      const gapIndices = [];
      data.forEach((d, i) => {
        if (d.isChargingGap || d.g === null) gapIndices.push(i);
      });

      // Render Visual Charging Gap Column Spanning all 8 Lanes
      gapIndices.forEach(idx => {
        const prevX = idx > 0 ? xCoords[idx - 1] : padLeft;
        const nextX = idx < n - 1 ? xCoords[idx + 1] : padLeft + plotW;
        const gapXStart = (xCoords[idx] + prevX) / 2;
        const gapXEnd = (xCoords[idx] + nextX) / 2;
        const gapWidth = Math.max(30, gapXEnd - gapXStart);

        svg += `
          <!-- Full-Stack Watch Charging Gap Column -->
          <rect x="${gapXStart.toFixed(1)}" y="20" width="${gapWidth.toFixed(1)}" height="${H - 55}" fill="rgba(241, 245, 249, 0.75)" stroke="#94a3b8" stroke-dasharray="4 4" rx="4"/>
          <g>
            <rect x="${(xCoords[idx] - 75).toFixed(1)}" y="26" width="150" height="20" fill="#ffffff" stroke="#94a3b8" stroke-width="1.5" rx="4" style="filter: drop-shadow(0 1px 3px rgba(0,0,0,0.08));"/>
            <text x="${xCoords[idx].toFixed(1)}" y="40" text-anchor="middle" fill="#475569" font-size="9" font-weight="900">⚡ WATCH CHARGING (0 DATA)</text>
          </g>
        `;
      });

      // Helper to generate discontinuous SVG path segments respecting charging gaps
      function buildSegmentedPath(pts, laneObj) {
        let pathD = "";
        let areaD = "";
        let currentSeg = [];

        function flushSegment() {
          if (currentSeg.length > 0) {
            const first = currentSeg[0];
            const last = currentSeg[currentSeg.length - 1];
            pathD += ` M ${first.x.toFixed(1)} ${first.y.toFixed(1)}`;
            let segArea = ` M ${first.x.toFixed(1)} ${(laneObj.y + laneObj.h).toFixed(1)} L ${first.x.toFixed(1)} ${first.y.toFixed(1)}`;
            for (let k = 1; k < currentSeg.length; k++) {
              pathD += ` L ${currentSeg[k].x.toFixed(1)} ${currentSeg[k].y.toFixed(1)}`;
              segArea += ` L ${currentSeg[k].x.toFixed(1)} ${currentSeg[k].y.toFixed(1)}`;
            }
            segArea += ` L ${last.x.toFixed(1)} ${(laneObj.y + laneObj.h).toFixed(1)} Z`;
            areaD += segArea;
            currentSeg = [];
          }
        }

        pts.forEach(p => {
          if (p.val === null || p.val === undefined || p.isGap) {
            flushSegment();
          } else {
            currentSeg.push(p);
          }
        });
        flushSegment();
        return { pathD, areaD };
      }

      // Lane 1: Glucose Curve (Discontinuous across Charging Gap)
      const gLane = lanes[0];
      const gPts = data.map((d, i) => {
        const val = (d.isChargingGap || d.g === null) ? null : d.g;
        const y = val !== null ? gLane.y + gLane.h - ((val - gLane.minY) / (gLane.maxY - gLane.minY)) * gLane.h : (gLane.y + gLane.h);
        return { x: xCoords[i], y: y, val: val, label: d.time, isGap: d.isChargingGap };
      });
      const gSegments = buildSegmentedPath(gPts, gLane);
      svg += `
        <path d="${gSegments.areaD}" fill="url(#gradTallGlucose)"/>
        <path d="${gSegments.pathD}" fill="none" stroke="var(--lcars-banana)" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
      `;"""

    if old_tall_render_curves in html:
        html = html.replace(old_tall_render_curves, new_tall_render_curves)
        print("[✔] Injected discontinuous path and full-stack charging gap into Lane 1 (Glucose)")
    else:
        print("[!] Could not find old_tall_render_curves marker")

    # Update Lane 2, Lane 3, Lane 4, Lane 5 to use segmented paths across charging gaps
    old_lanes_2_to_5 = """      // Lane 2: Blood Pressure Dual Curves (Sys / Dia)
      const bpLane = lanes[1];
      const sysPts = data.map((d, i) => `${xCoords[i].toFixed(1)},${(bpLane.y + bpLane.h - ((d.sys - bpLane.minY) / (bpLane.maxY - bpLane.minY)) * bpLane.h).toFixed(1)}`).join(" ");
      const diaPts = data.map((d, i) => `${xCoords[i].toFixed(1)},${(bpLane.y + bpLane.h - ((d.dia - bpLane.minY) / (bpLane.maxY - bpLane.minY)) * bpLane.h).toFixed(1)}`).join(" ");
      svg += `
        <polyline points="${sysPts}" fill="none" stroke="#ff9944" stroke-width="2"/>
        <polyline points="${diaPts}" fill="none" stroke="#38b6ff" stroke-width="2"/>
      `;

      // Lane 3: Heart Rate & HRV
      const hrLane = lanes[2];
      const hrPts = data.map((d, i) => `${xCoords[i].toFixed(1)},${(hrLane.y + hrLane.h - ((d.hr - hrLane.minY) / (hrLane.maxY - hrLane.minY)) * hrLane.h).toFixed(1)}`).join(" ");
      const hrvPts = data.map((d, i) => `${xCoords[i].toFixed(1)},${(hrLane.y + hrLane.h - ((d.hrv - hrLane.minY) / (hrLane.maxY - hrLane.minY)) * hrLane.h).toFixed(1)}`).join(" ");
      svg += `
        <polyline points="${hrPts}" fill="none" stroke="#ff5566" stroke-width="2"/>
        <polyline points="${hrvPts}" fill="none" stroke="var(--lcars-lilac)" stroke-width="1.8" stroke-dasharray="3 2"/>
      `;

      // Lane 4: SpO2 Curve
      const spLane = lanes[3];
      const spPts = data.map((d, i) => `${xCoords[i].toFixed(1)},${(spLane.y + spLane.h - ((d.spo2 - spLane.minY) / (spLane.maxY - spLane.minY)) * spLane.h).toFixed(1)}`).join(" ");
      svg += `
        <polyline points="${spPts}" fill="none" stroke="var(--lcars-green)" stroke-width="2"/>
      `;

      // Lane 5: Temperature Curve
      const tLane = lanes[4];
      const tPts = data.map((d, i) => `${xCoords[i].toFixed(1)},${(tLane.y + tLane.h - ((d.temp - tLane.minY) / (tLane.maxY - tLane.minY)) * tLane.h).toFixed(1)}`).join(" ");
      svg += `
        <polyline points="${tPts}" fill="none" stroke="var(--lcars-blue)" stroke-width="2"/>
      `;"""

    new_lanes_2_to_5 = """      // Lane 2: Blood Pressure Dual Curves (Sys / Dia) Discontinuous across gaps
      const bpLane = lanes[1];
      const sysPtsList = data.map((d, i) => ({
        x: xCoords[i],
        y: d.sys !== null ? bpLane.y + bpLane.h - ((d.sys - bpLane.minY) / (bpLane.maxY - bpLane.minY)) * bpLane.h : bpLane.y + bpLane.h,
        val: d.sys, isGap: d.isChargingGap
      }));
      const diaPtsList = data.map((d, i) => ({
        x: xCoords[i],
        y: d.dia !== null ? bpLane.y + bpLane.h - ((d.dia - bpLane.minY) / (bpLane.maxY - bpLane.minY)) * bpLane.h : bpLane.y + bpLane.h,
        val: d.dia, isGap: d.isChargingGap
      }));
      const sysSeg = buildSegmentedPath(sysPtsList, bpLane);
      const diaSeg = buildSegmentedPath(diaPtsList, bpLane);
      svg += `
        <path d="${sysSeg.pathD}" fill="none" stroke="#ff9944" stroke-width="2" stroke-linecap="round"/>
        <path d="${diaSeg.pathD}" fill="none" stroke="#38b6ff" stroke-width="2" stroke-linecap="round"/>
      `;

      // Lane 3: Heart Rate & HRV Discontinuous across gaps
      const hrLane = lanes[2];
      const hrPtsList = data.map((d, i) => ({
        x: xCoords[i],
        y: d.hr !== null ? hrLane.y + hrLane.h - ((d.hr - hrLane.minY) / (hrLane.maxY - hrLane.minY)) * hrLane.h : hrLane.y + hrLane.h,
        val: d.hr, isGap: d.isChargingGap
      }));
      const hrvPtsList = data.map((d, i) => ({
        x: xCoords[i],
        y: d.hrv !== null ? hrLane.y + hrLane.h - ((d.hrv - hrLane.minY) / (hrLane.maxY - hrLane.minY)) * hrLane.h : hrLane.y + hrLane.h,
        val: d.hrv, isGap: d.isChargingGap
      }));
      const hrSeg = buildSegmentedPath(hrPtsList, hrLane);
      const hrvSeg = buildSegmentedPath(hrvPtsList, hrLane);
      svg += `
        <path d="${hrSeg.pathD}" fill="none" stroke="#ff5566" stroke-width="2" stroke-linecap="round"/>
        <path d="${hrvSeg.pathD}" fill="none" stroke="var(--lcars-lilac)" stroke-width="1.8" stroke-dasharray="3 2" stroke-linecap="round"/>
      `;

      // Lane 4: SpO2 Curve Discontinuous across gaps
      const spLane = lanes[3];
      const spPtsList = data.map((d, i) => ({
        x: xCoords[i],
        y: d.spo2 !== null ? spLane.y + spLane.h - ((d.spo2 - spLane.minY) / (spLane.maxY - spLane.minY)) * spLane.h : spLane.y + spLane.h,
        val: d.spo2, isGap: d.isChargingGap
      }));
      const spSeg = buildSegmentedPath(spPtsList, spLane);
      svg += `
        <path d="${spSeg.pathD}" fill="none" stroke="var(--lcars-green)" stroke-width="2" stroke-linecap="round"/>
      `;

      // Lane 5: Temperature Curve Discontinuous across gaps
      const tLane = lanes[4];
      const tPtsList = data.map((d, i) => ({
        x: xCoords[i],
        y: d.temp !== null ? tLane.y + tLane.h - ((d.temp - tLane.minY) / (tLane.maxY - tLane.minY)) * tLane.h : tLane.y + tLane.h,
        val: d.temp, isGap: d.isChargingGap
      }));
      const tSeg = buildSegmentedPath(tPtsList, tLane);
      svg += `
        <path d="${tSeg.pathD}" fill="none" stroke="var(--lcars-blue)" stroke-width="2" stroke-linecap="round"/>
      `;"""

    if old_lanes_2_to_5 in html:
        html = html.replace(old_lanes_2_to_5, new_lanes_2_to_5)
        print("[✔] Injected discontinuous paths into Lanes 2-5 (BP, HR, SpO2, Temp)")
    else:
        print("[!] Could not find old_lanes_2_to_5 marker")

    # In Interactive Glucose Points: don't plot circles on gap points, show 0 on scrubber
    old_g_pts_circles = """      // Interactive Clickable Points on Glucose Curve
      gPts.forEach((p, i) => {
        svg += `
          <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
            <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="5.5" fill="var(--lcars-banana)" stroke="#000000" stroke-width="2">
              <title>${p.label}: ${p.val} mg/dL</title>
            </circle>
            <text x="${p.x.toFixed(1)}" y="${(p.y - 8).toFixed(1)}" text-anchor="middle" fill="var(--lcars-banana)" font-size="10.5" font-weight="bold">${p.val}</text>
          </g>
        `;
      });"""

    new_g_pts_circles = """      // Interactive Clickable Points on Glucose Curve (Skip charging gaps)
      gPts.forEach((p, i) => {
        if (p.val === null || p.isGap) {
          svg += `
            <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
              <circle cx="${p.x.toFixed(1)}" cy="${(gLane.y + gLane.h - 10).toFixed(1)}" r="4" fill="#94a3b8" stroke="#ffffff" stroke-width="1.5">
                <title>${p.label}: ⚡ Watch Charging (0 mg/dL)</title>
              </circle>
              <text x="${p.x.toFixed(1)}" y="${(gLane.y + gLane.h - 18).toFixed(1)}" text-anchor="middle" fill="#94a3b8" font-size="9" font-weight="bold">0 (Gap)</text>
            </g>
          `;
        } else {
          svg += `
            <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
              <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="5.5" fill="var(--lcars-banana)" stroke="#000000" stroke-width="2">
                <title>${p.label}: ${p.val} mg/dL</title>
              </circle>
              <text x="${p.x.toFixed(1)}" y="${(p.y - 8).toFixed(1)}" text-anchor="middle" fill="var(--lcars-banana)" font-size="10.5" font-weight="bold">${p.val}</text>
            </g>
          `;
        }
      });"""

    if old_g_pts_circles in html:
        html = html.replace(old_g_pts_circles, new_g_pts_circles)
        print("[✔] Updated clickable glucose points to handle charging gaps with 0 (Gap)")
    else:
        print("[!] Could not find old_g_pts_circles marker")

    # Update onTallScrubberMoved to handle charging gap
    old_scrubber_handler = """      // Update Header Time Badge
      const timeBadge = document.getElementById('tallScrubberTimeBadge');
      if (timeBadge) timeBadge.textContent = `TIMESTAMP: ${pt.time} (${pt.meal ? pt.meal.type : (pt.ex ? pt.ex.type : 'Diurnal State')})`;

      // Update HUD Ribbon
      const hG = document.getElementById('hudGlucose');
      if (hG) hG.textContent = `${pt.g} mg/dL`;

      const hBp = document.getElementById('hudBp');
      if (hBp) hBp.textContent = `${pt.sys}/${pt.dia} mmHg`;

      const hHr = document.getElementById('hudHrHrv');
      if (hHr) hHr.textContent = `${pt.hr} BPM / ${pt.hrv} ms`;

      const hSp = document.getElementById('hudSpo2');
      if (hSp) hSp.textContent = `${pt.spo2}%`;

      const hT = document.getElementById('hudTemp');
      if (hT) hT.textContent = `${pt.temp.toFixed(1)}°F`;

      const hEx = document.getElementById('hudExercise');
      if (hEx) {
        hEx.textContent = pt.ex ? `${pt.steps} steps • ${pt.ex.type} (${pt.ex.delta <= 0 ? pt.ex.delta : '+' + pt.ex.delta} mg/dL)` : `${pt.steps} steps • Resting Homeostasis`;
      }"""

    new_scrubber_handler = """      // Handle Watch Charging Gap in Scrubber HUD
      const timeBadge = document.getElementById('tallScrubberTimeBadge');
      const hG = document.getElementById('hudGlucose');
      const hBp = document.getElementById('hudBp');
      const hHr = document.getElementById('hudHrHrv');
      const hSp = document.getElementById('hudSpo2');
      const hT = document.getElementById('hudTemp');
      const hEx = document.getElementById('hudExercise');

      if (pt.isChargingGap || pt.g === null) {
        if (timeBadge) timeBadge.textContent = `TIMESTAMP: ${pt.time} (⚡ WATCH CHARGING ON DOCK • 0 TELEMETRY)`;
        if (hG) hG.textContent = `0 mg/dL (Charging)`;
        if (hBp) hBp.textContent = `-- / -- (Off-Wrist)`;
        if (hHr) hHr.textContent = `0 BPM / 0 ms`;
        if (hSp) hSp.textContent = `-- %`;
        if (hT) hT.textContent = `--.- °F`;
        if (hEx) hEx.textContent = `0 steps • Watch on Charger (~1.2h)`;
      } else {
        if (timeBadge) timeBadge.textContent = `TIMESTAMP: ${pt.time} (${pt.meal ? pt.meal.type : (pt.ex ? pt.ex.type : 'Diurnal State')})`;
        if (hG) hG.textContent = `${pt.g} mg/dL`;
        if (hBp) hBp.textContent = `${pt.sys}/${pt.dia} mmHg`;
        if (hHr) hHr.textContent = `${pt.hr} BPM / ${pt.hrv} ms`;
        if (hSp) hSp.textContent = `${pt.spo2}%`;
        if (hT) hT.textContent = `${pt.temp ? pt.temp.toFixed(1) : '--.-'}°F`;
        if (hEx) {
          hEx.textContent = pt.ex ? `${pt.steps} steps • ${pt.ex.type} (${pt.ex.delta <= 0 ? pt.ex.delta : '+' + pt.ex.delta} mg/dL)` : `${pt.steps} steps • Resting Homeostasis`;
        }
      }"""

    if old_scrubber_handler in html:
        html = html.replace(old_scrubber_handler, new_scrubber_handler)
        print("[✔] Injected charging gap HUD telemetry into onTallScrubberMoved")
    else:
        print("[!] Could not find old_scrubber_handler marker")

    # =========================================================================
    # 4. INJECT setRawDataTimeframe JAVASCRIPT CONTROLLER FOR SCROLL WINDOW
    # =========================================================================
    raw_controller_code = '''
    // =========================================================================
    // RAW SENSOR TELEMETRY AUDIT LOG ENGINE (BOUNDED SCROLL WINDOW)
    // Supports Daily (24h), Weekly (7d), Monthly (30d), and Yearly (12m)
    // Keeps viewport size down with 320px scroll window; displays 0 for missing data
    // =========================================================================
    let currentRawDataTimeframe = 'daily';

    function setRawDataTimeframe(tf) {
      soundButtonPress();
      currentRawDataTimeframe = tf;

      ['btnRawDaily', 'btnRawWeekly', 'btnRawMonthly', 'btnRawYearly'].forEach(id => {
        const b = document.getElementById(id);
        if (b) b.className = 'lcars-btn btn-blue';
      });

      const activeBtnId = tf === 'daily' ? 'btnRawDaily' : (tf === 'weekly' ? 'btnRawWeekly' : (tf === 'monthly' ? 'btnRawMonthly' : 'btnRawYearly'));
      const activeBtn = document.getElementById(activeBtnId);
      if (activeBtn) activeBtn.className = 'lcars-btn btn-banana';

      renderRawTelemetryTable();
    }

    function renderRawTelemetryTable() {
      const tbody = document.getElementById('telemetryTableBody');
      const recCount = document.getElementById('repRecordCount');
      if (!tbody) return;

      tbody.innerHTML = "";
      const tf = currentRawDataTimeframe || 'daily';
      let rows = [];

      const todayStr = new Date().toISOString().slice(0, 10);

      if (tf === 'daily') {
        rows = [
          { period: `${todayStr} 00:00:15`, g: 168, core: 97.0, skin: 90.2, steps: 0, status: "Nocturnal Baseline", progress: "100% Synced" },
          { period: `${todayStr} 04:00:22`, g: 162, core: 96.6, skin: 88.6, steps: 0, status: "Metabolic Nadir", progress: "100% Synced" },
          { period: `${todayStr} 07:00:05`, g: 174, core: 97.0, skin: 91.2, steps: 120, status: "Dawn Awakening", progress: "100% Synced" },
          { period: `${todayStr} 08:15:00`, g: 165, core: 97.2, skin: 92.4, steps: 340, status: "Breakfast (Eggs/Toast)", progress: "100% Synced" },
          { period: `${todayStr} 08:45:30`, g: 148, core: 97.4, skin: 92.8, steps: 2384, status: "Post-Meal GLUT4 Walk", progress: "100% Synced" },
          { period: `${todayStr} 10:00:12`, g: 142, core: 97.4, skin: 93.2, steps: 2650, status: "Optimal TIR Sustained", progress: "100% Synced" },
          // Watch Charging Gap (10:30 - 12:00)
          { period: `${todayStr} 11:00:00`, g: 0, core: null, skin: null, steps: 0, status: "⚡ WATCH CHARGING (OFF-WRIST)", progress: "0% (Charging Gap)", isCharging: true },
          { period: `${todayStr} 12:30:18`, g: 158, core: 97.2, skin: 92.4, steps: 3200, status: "Watch Donned • Pre-Lunch", progress: "100% Synced" },
          { period: `${todayStr} 13:15:45`, g: 188, core: 97.3, skin: 92.6, steps: 3980, status: "Post-Lunch GLUT4 Walk", progress: "100% Synced" },
          { period: `${todayStr} 15:30:00`, g: 152, core: 97.4, skin: 93.0, steps: 4420, status: "ADA Trade Snack (Almonds)", progress: "100% Synced" },
          { period: `${todayStr} 18:30:10`, g: 178, core: 97.5, skin: 93.4, steps: 5100, status: "Dinner (Salmon / Greens)", progress: "100% Synced" },
          { period: `${todayStr} 20:00:30`, g: 146, core: 97.2, skin: 92.2, steps: 6180, status: "Evening GLUT4 Stroll", progress: "100% Synced" },
          { period: `${todayStr} 22:00:00`, g: 140, core: 97.1, skin: 91.4, steps: 6380, status: "Pre-Bedtime Normal", progress: "100% Synced" },
          { period: `${todayStr} 24:00:00`, g: 138, core: 97.0, skin: 90.6, steps: 6380, status: "24h Cycle Complete", progress: "100% Synced" }
        ];
        if (recCount) recCount.textContent = "14 HOURLY SAMPLES AUDITED";
      } else if (tf === 'weekly') {
        const days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday (Today)', 'Friday', 'Saturday', 'Sunday'];
        const dates = ['Nov 17', 'Nov 18', 'Nov 19', 'Nov 20', 'Nov 21', 'Nov 22', 'Nov 23'];
        const glucoses = [174, 168, 172, 160, 162, 158, 164];
        const stepVols = [6450, 7120, 5800, 6384, 6800, 7500, 6200];
        const chgNotes = ["Charged 1.1h @ 11:00", "Charged 1.3h @ 10:45", "Charged 1.0h @ 11:15", "Charged 1.2h @ 11:00", "Projected", "Projected", "Projected"];

        rows = days.map((d, i) => ({
          period: `${dates[i]} (${d})`,
          g: glucoses[i],
          core: 97.2,
          skin: 92.4,
          steps: stepVols[i],
          status: `TIR ${Math.round(82 + i * 1.2)}% • ${chgNotes[i]}`,
          progress: i <= 3 ? "100% Verified" : "Scheduled"
        }));
        if (recCount) recCount.textContent = "7 DAILY AUDITS (WEEKLY)";
      } else if (tf === 'monthly') {
        // 30 days of telemetry audits in a clean scrollable table
        rows = [];
        for (let day = 1; day <= 30; day++) {
          const isRecent = day <= 20;
          const gVal = isRecent ? Math.round(185 - (day * 1.2)) : 0; // 0 for unrecorded future days
          const stepVal = isRecent ? Math.round(5200 + (day * 65)) : 0;
          rows.push({
            period: `2026-11-${day.toString().padStart(2, '0')}`,
            g: gVal,
            core: isRecent ? 97.2 : null,
            skin: isRecent ? 92.3 : null,
            steps: stepVal,
            status: isRecent ? (day % 4 === 0 ? "⚡ Charged 1.2h • 88% TIR" : "Full Compliance • 86% TIR") : "0 Telemetry (Unrecorded)",
            progress: isRecent ? "Audited (EHR)" : "Pending (0)"
          });
        }
        if (recCount) recCount.textContent = "30 DAILY AUDITS (MONTHLY)";
      } else {
        // Yearly 12 months longitudinal
        const months = ['Jan 2026', 'Feb 2026', 'Mar 2026', 'Apr 2026', 'May 2026', 'Jun 2026', 'Jul 2026', 'Aug 2026', 'Sep 2026', 'Oct 2026', 'Nov 2026 (Cur)', 'Dec 2026 (Goal)'];
        const a1cVals = [198, 194, 188, 182, 178, 174, 170, 168, 165, 162, 154, 148];
        const monthSteps = [3200, 3600, 4200, 4800, 5400, 6000, 6200, 6400, 6500, 6600, 6700, 7000];
        const clinicalNotes = [
          "Pre-LCARS Baseline • HbA1c 8.5%",
          "Initial Metformin Titration",
          "Cauliflower Rice ADA Trades Initiated",
          "Post-Lunch GLUT4 Walking Habit",
          "Vegetable Preloading Regimen",
          "Time-in-Range Reached 78%",
          "Zero Added Sugar Compliance",
          "Blood Pressure Normalized (117/75)",
          "Daily 6,500 Steps Milestone",
          "💊 MED CHANGE: Metformin 1,000mg BID + Jardiance 10mg",
          "PROGRESS: Mean Glucose -28 mg/dL; TIR 88%",
          "Target HbA1c < 6.8% Projected"
        ];

        rows = months.map((m, idx) => ({
          period: m,
          g: a1cVals[idx],
          core: 97.2,
          skin: 92.4,
          steps: monthSteps[idx],
          status: clinicalNotes[idx],
          progress: idx <= 10 ? "Committed" : "Target"
        }));
        if (recCount) recCount.textContent = "12 MONTHLY AUDITS (YEARLY)";
      }

      rows.forEach((r, idx) => {
        const tr = document.createElement('tr');
        tr.style.borderBottom = "1px solid #e2e8f0";
        if (r.isCharging) {
          tr.style.background = "#fffbeb";
        } else if (idx % 2 === 1) {
          tr.style.background = "#f8fafc";
        } else {
          tr.style.background = "#ffffff";
        }

        const gDisplay = r.g > 0 ? `${r.g} mg/dL` : (r.isCharging ? `<span style="color: #ea580c; font-weight: bold;">0 (Charging)</span>` : `<span style="color: #94a3b8;">0</span>`);
        const tempDisplay = r.core ? `${r.core.toFixed(1)}° / ${r.skin.toFixed(1)}°` : (r.isCharging ? `<span style="color: #ea580c;">-- / --</span>` : `<span style="color: #94a3b8;">--</span>`);
        const stepsDisplay = r.steps > 0 ? `${r.steps.toLocaleString()}` : `<span style="color: #94a3b8;">0</span>`;
        const statusColor = r.isCharging ? "#c2410c" : (r.status.includes("MED CHANGE") ? "#dc2626" : "#0f766e");

        tr.innerHTML = `
          <td style="padding: 7px 10px; color: #334155; font-weight: 600;">${r.period}</td>
          <td style="padding: 7px 10px; color: #b45309; font-weight: 800;">${gDisplay}</td>
          <td style="padding: 7px 10px; color: #1d4ed8; font-weight: bold;">${tempDisplay}</td>
          <td style="padding: 7px 10px; color: #c2410c; font-weight: bold;">${stepsDisplay}</td>
          <td style="padding: 7px 10px; color: ${statusColor}; font-weight: bold;">${r.status}</td>
          <td style="padding: 7px 10px; color: #7c3aed; font-weight: 600;">${r.progress}</td>
        `;
        tbody.appendChild(tr);
      });
    }
'''

    # Inject renderRawTelemetryTable call into renderReportsView
    if 'renderRawTelemetryTable();' not in html:
        pos = html.find('// 10. Populate Raw Telemetry Table')
        if pos != -1:
            end_pos = html.find('}', html.find('tbody.appendChild(tr);', pos))
            if end_pos != -1:
                html = html[:pos] + '// 10. Populate Raw Telemetry Table with Bounded Scroll Window\n      renderRawTelemetryTable();\n    ' + html[end_pos+1:]
                print("[✔] Replaced old inline table loop with renderRawTelemetryTable() call")
        else:
            print("[!] Could not find '// 10. Populate Raw Telemetry Table'")

    # Inject the controller functions right before openEpicUploadModal
    if 'function setRawDataTimeframe' not in html:
        pos_epic = html.find('// EPIC EHR DIRECT INTEGRATION & FHIR TELEMETRY TRANSMISSION ENGINE')
        if pos_epic != -1:
            html = html[:pos_epic] + raw_controller_code + '\n    ' + html[pos_epic:]
            print("[✔] Injected setRawDataTimeframe and renderRawTelemetryTable functions")
        else:
            print("[!] Could not find openEpicUploadModal marker")

    # =========================================================================
    # 5. INJECT CHARGING GAP INTO 10 CLINICAL SVG CHARTS (DAILY GLUCOSE, HR, TEMP)
    #    When val === 0 or isGap === true, render discontinuous curve and gap badge
    # =========================================================================
    old_daily_glucose_data = """            { label: '10:00 (GLUT4 Clear)', val: 165 },
            { label: '12:30 (Pre-Lunch)', val: 188 },"""

    new_daily_glucose_data = """            { label: '10:00 (GLUT4 Clear)', val: 165 },
            { label: '11:00 (Watch Charging)', val: 0, isGap: true },
            { label: '12:30 (Pre-Lunch)', val: 188 },"""

    if old_daily_glucose_data in html:
        html = html.replace(old_daily_glucose_data, new_daily_glucose_data)
        print("[✔] Injected charging gap into 10-chart daily glucoseData")

    old_daily_hr_data = """          { label: '10:00', val: 68 }, { label: '12:30', val: 70 },"""
    new_daily_hr_data = """          { label: '10:00', val: 68 }, { label: '11:00 (Charging)', val: 0, isGap: true }, { label: '12:30', val: 70 },"""
    if old_daily_hr_data in html:
        html = html.replace(old_daily_hr_data, new_daily_hr_data)
        print("[✔] Injected charging gap into daily hrData")

    old_daily_temp_data = """          { label: '10:00', core: 97.4, skin: 93.2 }, { label: '12:30', core: 97.2, skin: 92.4 },"""
    new_daily_temp_data = """          { label: '10:00', core: 97.4, skin: 93.2 }, { label: '11:00', core: null, skin: null, isGap: true }, { label: '12:30', core: 97.2, skin: 92.4 },"""
    if old_daily_temp_data in html:
        html = html.replace(old_daily_temp_data, new_daily_temp_data)
        print("[✔] Injected charging gap into daily tempData")

    # Update renderSvgCurveChart to handle isGap and val === 0 with discontinuous curves
    old_curve_builder = """      const polyPoints = points.map(p => `${p.x.toFixed(1)},${p.y.toFixed(1)}`).join(" ");
      const lastX = points.length > 0 ? points[points.length - 1].x : padLeft;
      const areaPoints = `${padLeft},${padTop + plotH} ` + polyPoints + ` ${lastX.toFixed(1)},${padTop + plotH}`;"""

    new_curve_builder = """      // Segment builder: breaks curve across gaps (isGap === true or val === null or val === 0)
      let curvePaths = "";
      let areaPaths = "";
      let gapRectsHtml = "";
      let currentSegment = [];

      function flushCurveSegment() {
        if (currentSegment.length > 0) {
          const first = currentSegment[0];
          const last = currentSegment[currentSegment.length - 1];
          const segPoints = currentSegment.map(p => `${p.x.toFixed(1)},${p.y.toFixed(1)}`).join(" ");
          curvePaths += `<polyline points="${segPoints}" fill="none" stroke="${strokeColor}" stroke-width="2.8" stroke-linecap="round" stroke-linejoin="round" style="filter: drop-shadow(0 2px 4px rgba(0,0,0,0.15));"/>`;
          const segArea = `${first.x.toFixed(1)},${padTop + plotH} ` + segPoints + ` ${last.x.toFixed(1)},${padTop + plotH}`;
          areaPaths += `<polygon points="${segArea}" fill="url(#${gradId})"/>`;
          currentSegment = [];
        }
      }

      points.forEach((p, idx) => {
        if (p.isGap || p.val === null || (p.val === 0 && !options.allowZeroValue)) {
          flushCurveSegment();
          // Draw charging gap indicator
          const prevX = idx > 0 ? points[idx - 1].x : padLeft;
          const nextX = idx < points.length - 1 ? points[idx + 1].x : padLeft + plotW;
          const gStart = (p.x + prevX) / 2;
          const gEnd = (p.x + nextX) / 2;
          const gW = Math.max(30, gEnd - gStart);
          gapRectsHtml += `
            <rect x="${gStart.toFixed(1)}" y="${padTop + 4}" width="${gW.toFixed(1)}" height="${plotH - 8}" fill="rgba(241, 245, 249, 0.75)" stroke="#cbd5e1" stroke-dasharray="3 3" rx="4"/>
            <text x="${p.x.toFixed(1)}" y="${padTop + 16}" text-anchor="middle" fill="#64748b" font-family="var(--lcars-mono)" font-size="8.5" font-weight="900">⚡ CHARGING GAP (0 DATA)</text>
          `;
        } else {
          currentSegment.push(p);
        }
      });
      flushCurveSegment();"""

    if old_curve_builder in html:
        html = html.replace(old_curve_builder, new_curve_builder)
        # Also replace trend fill and polyline in template
        html = html.replace(
            """          <!-- Trend Fill -->\n          <polygon points="${areaPoints}" fill="url(#${gradId})"/>\n          <!-- Active Trend Curve -->\n          <polyline class="plot-curve" points="${polyPoints}" fill="none" stroke="${strokeColor}" \n                    stroke-width="2.8" stroke-linecap="round" stroke-linejoin="round"\n                    style="filter: drop-shadow(0 2px 4px rgba(0,0,0,0.15));"/>""",
            """          <!-- Gap Indicator Blocks -->\n          ${gapRectsHtml}\n          <!-- Trend Fills (Discontinuous) -->\n          ${areaPaths}\n          <!-- Active Trend Curves (Discontinuous across Charging Gaps) -->\n          ${curvePaths}"""
        )
        print("[✔] Updated renderSvgCurveChart to draw discontinuous curves across charging gaps")
    else:
        print("[!] Could not find old_curve_builder marker")

    # In renderSvgCurveChart points loop: display 0 or gap marker cleanly
    old_p_val_display = """            <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="4.5" 
                    fill="${strokeColor}" stroke="#ffffff" stroke-width="2">
              <title>${p.label}: ${p.val} ${unit}</title>
            </circle>
            <text x="${p.x.toFixed(1)}" y="${valY.toFixed(1)}" text-anchor="middle" 
                  fill="${strokeColor}" font-family="var(--lcars-mono)" font-size="11" font-weight="900"
                  style="paint-order: stroke fill; stroke: #ffffff; stroke-width: 3.5px; stroke-linejoin: round;">${p.val}${options.valSuffix || ''}</text>"""

    new_p_val_display = """            ${p.isGap || p.val === null ? `
              <circle cx="${p.x.toFixed(1)}" cy="${padTop + plotH - 6}" r="3.5" fill="#94a3b8" stroke="#ffffff" stroke-width="1.5">
                <title>${p.label}: ⚡ Watch Charging (0 ${unit})</title>
              </circle>
              <text x="${p.x.toFixed(1)}" y="${padTop + plotH - 12}" text-anchor="middle" fill="#94a3b8" font-family="var(--lcars-mono)" font-size="9" font-weight="bold">0 (Gap)</text>
            ` : `
              <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="4.5" 
                      fill="${strokeColor}" stroke="#ffffff" stroke-width="2">
                <title>${p.label}: ${p.val} ${unit}</title>
              </circle>
              <text x="${p.x.toFixed(1)}" y="${valY.toFixed(1)}" text-anchor="middle" 
                    fill="${strokeColor}" font-family="var(--lcars-mono)" font-size="11" font-weight="900"
                    style="paint-order: stroke fill; stroke: #ffffff; stroke-width: 3.5px; stroke-linejoin: round;">${p.val}${options.valSuffix || ''}</text>
            `}"""

    if old_p_val_display in html:
        html = html.replace(old_p_val_display, new_p_val_display)
        print("[✔] Updated renderSvgCurveChart point labels to display 0 (Gap) during charging")
    else:
        print("[!] Could not find old_p_val_display marker")

    # =========================================================================
    # 6. UPDATE renderHBand48Chart (BAY 4) TO SHOW 0 FOR EMPTY SLOTS AND
    #    CHARGING SYMBOL DURING CHARGING HOURS (SLOTS 21-23, 10:30-12:00)
    # =========================================================================
    old_hband48_init = """    function initHBand48Slots() {
      // Base activity with this morning's recorded walk (2,384 steps total)
      hbandDaily48Slots.fill(0);
      hbandDaily48Slots[16] = 320;   // 08:00 AM (Warm up)
      hbandDaily48Slots[17] = 1120;  // 08:30 AM (Brisk walk / GLUT4)
      hbandDaily48Slots[18] = 840;   // 09:00 AM (Aerobic recovery)
      hbandDaily48Slots[19] = 104;   // 09:30 AM (Cooldown)
      renderHBand48Chart();
    }"""

    new_hband48_init = """    function initHBand48Slots() {
      // Activity bins: only show data we have, slots with no data are explicitly 0.
      // Watch charging gap from 10:30 to 12:00 (slots 21, 22, 23) has 0 steps.
      hbandDaily48Slots.fill(0);
      hbandDaily48Slots[16] = 320;   // 08:00 AM (Warm up)
      hbandDaily48Slots[17] = 1120;  // 08:30 AM (Brisk walk / GLUT4)
      hbandDaily48Slots[18] = 840;   // 09:00 AM (Aerobic recovery)
      hbandDaily48Slots[19] = 104;   // 09:30 AM (Cooldown)
      // Slots 21-23 remain exactly 0 steps (Watch Charging)
      renderHBand48Chart();
    }"""

    if old_hband48_init in html:
        html = html.replace(old_hband48_init, new_hband48_init)
        print("[✔] Updated initHBand48Slots with explicit 0 slots and charging gaps")
    else:
        print("[!] Could not find old_hband48_init marker")

    old_hband48_bar_loop = """        // Color coding: Blue for normal activity, Green for GLUT4 exercise sessions (>300 steps/slot)
        const isExercise = val >= 300;
        const fillColor = isExercise ? 'var(--lcars-green)' : (val > 100 ? 'var(--lcars-blue)' : '#2a4055');

        barsSvg += `
          <rect x="${x}" y="${y}" width="${slotWidth - 2}" height="${barHeight}" fill="${fillColor}" rx="1" opacity="0.9">
            <title>Time: ${Math.floor(i/2).toString().padStart(2,'0')}:${(i%2===0?'00':'30')} • Steps: ${val}</title>
          </rect>
        `;"""

    new_hband48_bar_loop = """        // Slots 21-23 (10:30 - 12:00) represent the 1.5h Watch Charging Window (0 Steps)
        const isChargingSlot = (i >= 21 && i <= 23);
        const isExercise = val >= 300;
        const fillColor = isChargingSlot ? '#f59e0b' : (isExercise ? 'var(--lcars-green)' : (val > 100 ? 'var(--lcars-blue)' : '#cbd5e1'));

        if (isChargingSlot) {
          barsSvg += `
            <rect x="${x}" y="${baseY - 14}" width="${slotWidth - 2}" height="14" fill="rgba(245, 158, 11, 0.25)" stroke="#f59e0b" stroke-dasharray="2 2" rx="1">
              <title>Time: ${Math.floor(i/2).toString().padStart(2,'0')}:${(i%2===0?'00':'30')} • ⚡ Watch Charging on Dock (0 Steps)</title>
            </rect>
            <text x="${x + (slotWidth - 2)/2}" y="${baseY - 3}" text-anchor="middle" fill="#d97706" font-size="9" font-weight="bold">⚡</text>
          `;
        } else if (val > 0) {
          barsSvg += `
            <rect x="${x}" y="${y}" width="${slotWidth - 2}" height="${barHeight}" fill="${fillColor}" rx="1" opacity="0.9">
              <title>Time: ${Math.floor(i/2).toString().padStart(2,'0')}:${(i%2===0?'00':'30')} • Steps: ${val}</title>
            </rect>
          `;
        } else {
          // Zero data slot: small baseline tick
          barsSvg += `
            <rect x="${x}" y="${baseY - 2}" width="${slotWidth - 2}" height="2" fill="#e2e8f0" rx="1">
              <title>Time: ${Math.floor(i/2).toString().padStart(2,'0')}:${(i%2===0?'00':'30')} • 0 Steps (No Telemetry)</title>
            </rect>
          `;
        }"""

    if old_hband48_bar_loop in html:
        html = html.replace(old_hband48_bar_loop, new_hband48_bar_loop)
        print("[✔] Updated renderHBand48Chart to show 0 for unrecorded slots and ⚡ for charging window")
    else:
        print("[!] Could not find old_hband48_bar_loop marker")

    # Save to w11_htm_app/index.html
    with open(path, "w", encoding="utf-8") as f:
        f.write(html)
    print(f"Saved w11_htm_app/index.html ({len(html)} bytes)")

    # Synchronize to root index.html
    path_root = r"D:\veepoo\index.html"
    with open(path_root, "w", encoding="utf-8") as f:
        f.write(html)
    print(f"Synchronized to {path_root}")

if __name__ == "__main__":
    apply_chart_gaps_and_scroll_window()
