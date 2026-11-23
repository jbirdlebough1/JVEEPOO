import re

def run():
    with open('index.html', 'r', encoding='utf-8') as f:
        html = f.read()

    # 1. Update altMap in renderSvgCurveChart and renderSvgDualCurveChart
    old_altmap = """          'repChartExercise': 'curveTimelineExercise',
          'repChartExerciseCadence': 'curveTimelineExercise'
        };"""

    new_altmap = """          'repChartExercise': 'curveTimelineExercise',
          'repChartExerciseCadence': 'curveTimelineExercise',
          'repChartEcgTrend': 'curveTimelineECG',
          'curveTimelineECG': 'curveTimelineECG',
          'repChartMetTrend': 'curveTimelineMET',
          'curveTimelineMET': 'curveTimelineMET',
          'repChartBloodCompTrend': 'curveTimelineBloodComp',
          'curveTimelineBloodComp': 'curveTimelineBloodComp',
          'repChartBodyCompTrend': 'curveTimelineBodyComp',
          'curveTimelineBodyComp': 'curveTimelineBodyComp'
        };"""

    if old_altmap in html:
        html = html.replace(old_altmap, new_altmap)
        print("[✔] Updated altMap in chart renderers")
    else:
        print("[!] Could not match old_altmap")

    # 2. Update setReportTimeframe to support Quarterly and sync tall buttons
    old_set_rep_tf = """    function setReportTimeframe(tf) {
      soundButtonPress();
      currentReportTimeframe = tf;
      ['btnRepDaily', 'btnRepWeekly', 'btnRepMonthly', 'btnRepYearly'].forEach(id => {
        const btn = document.getElementById(id);
        if (btn) btn.className = 'lcars-btn btn-blue';
      });
      if (tf === 'daily' && document.getElementById('btnRepDaily')) document.getElementById('btnRepDaily').className = 'lcars-btn btn-banana';
      if (tf === 'weekly' && document.getElementById('btnRepWeekly')) document.getElementById('btnRepWeekly').className = 'lcars-btn btn-banana';
      if (tf === 'monthly' && document.getElementById('btnRepMonthly')) document.getElementById('btnRepMonthly').className = 'lcars-btn btn-banana';
      if (tf === 'yearly' && document.getElementById('btnRepYearly')) document.getElementById('btnRepYearly').className = 'lcars-btn btn-banana';

      const ytdBox = document.getElementById('boxYtdSummary');
      if (ytdBox) {
        ytdBox.style.display = (tf === 'yearly' || staticClinicalExamplesEnabled) ? 'block' : 'none';
      }

      renderReportsView();
    }"""

    new_set_rep_tf = """    function setReportTimeframe(tf) {
      soundButtonPress();
      currentReportTimeframe = tf;
      currentTallChartTimeframe = tf;
      ['btnRepDaily', 'btnRepWeekly', 'btnRepMonthly', 'btnRepQuarterly', 'btnRepYearly',
       'btnTallDaily', 'btnTallWeekly', 'btnTallMonthly', 'btnTallQuarterly', 'btnTallYearly'].forEach(id => {
        const btn = document.getElementById(id);
        if (btn) btn.className = 'lcars-btn btn-blue';
      });
      const repMap = {
        'daily': 'btnRepDaily', 'weekly': 'btnRepWeekly', 'monthly': 'btnRepMonthly',
        'quarterly': 'btnRepQuarterly', 'yearly': 'btnRepYearly'
      };
      const tallMap = {
        'daily': 'btnTallDaily', 'weekly': 'btnTallWeekly', 'monthly': 'btnTallMonthly',
        'quarterly': 'btnTallQuarterly', 'yearly': 'btnTallYearly'
      };
      if (repMap[tf] && document.getElementById(repMap[tf])) document.getElementById(repMap[tf]).className = 'lcars-btn btn-banana';
      if (tallMap[tf] && document.getElementById(tallMap[tf])) document.getElementById(tallMap[tf]).className = 'lcars-btn btn-banana';

      const ytdBox = document.getElementById('boxYtdSummary');
      if (ytdBox) {
        ytdBox.style.display = (tf === 'yearly' || staticClinicalExamplesEnabled) ? 'block' : 'none';
      }

      renderReportsView();
      if (typeof renderTallSynchronizedChart === 'function') renderTallSynchronizedChart(tf);
    }"""

    if old_set_rep_tf in html:
        html = html.replace(old_set_rep_tf, new_set_rep_tf, 1)
        print("[✔] Updated setReportTimeframe with full 5-timeframe synchronization")
    else:
        print("[!] Could not match old_set_rep_tf")

    # 3. Update setTallChartTimeframe
    old_set_tall_tf = """    function setTallChartTimeframe(tf) {
      soundButtonPress();
      currentTallChartTimeframe = tf;
      ['btnTallDaily', 'btnTall2Day', 'btnTallWeekly', 'btnTallYearly'].forEach(id => {
        const b = document.getElementById(id);
        if (b) {
          b.className = 'lcars-btn btn-blue';
        }
      });
      const ytd = document.getElementById('boxYtdSummary');
      if (ytd) {
        ytd.style.display = (tf === 'yearly') ? 'block' : 'none';
      }
      if (tf === 'daily' || tf === 'today') {
        const b = document.getElementById('btnTallDaily'); if (b) b.className = 'lcars-btn btn-banana';
      } else if (tf === '2day') {
        const b = document.getElementById('btnTall2Day'); if (b) b.className = 'lcars-btn btn-banana';
      } else if (tf === 'weekly') {
        const b = document.getElementById('btnTallWeekly'); if (b) b.className = 'lcars-btn btn-banana';
      } else {
        const b = document.getElementById('btnTallYearly'); if (b) b.className = 'lcars-btn btn-banana';
      }
      renderTallSynchronizedChart(tf);
    }"""

    new_set_tall_tf = """    function setTallChartTimeframe(tf) {
      soundButtonPress();
      currentTallChartTimeframe = tf;
      ['btnTallDaily', 'btnTallWeekly', 'btnTallMonthly', 'btnTallQuarterly', 'btnTallYearly',
       'btnRepDaily', 'btnRepWeekly', 'btnRepMonthly', 'btnRepQuarterly', 'btnRepYearly'].forEach(id => {
        const b = document.getElementById(id);
        if (b) b.className = 'lcars-btn btn-blue';
      });
      const tallMap = {
        'daily': 'btnTallDaily', 'weekly': 'btnTallWeekly', 'monthly': 'btnTallMonthly',
        'quarterly': 'btnTallQuarterly', 'yearly': 'btnTallYearly'
      };
      if (tallMap[tf] && document.getElementById(tallMap[tf])) document.getElementById(tallMap[tf]).className = 'lcars-btn btn-banana';

      const ytd = document.getElementById('boxYtdSummary');
      if (ytd) {
        ytd.style.display = (tf === 'yearly' || staticClinicalExamplesEnabled) ? 'block' : 'none';
      }
      renderTallSynchronizedChart(tf);
      if (currentReportTimeframe !== tf && typeof setReportTimeframe === 'function') {
        setReportTimeframe(tf);
      }
    }"""

    if old_set_tall_tf in html:
        html = html.replace(old_set_tall_tf, new_set_tall_tf, 1)
        print("[✔] Updated setTallChartTimeframe with 5-timeframe sync")
    else:
        print("[!] Could not match old_set_tall_tf")

    # 4. Update getTallSynchronizedDataset to support weekly, monthly, quarterly, yearly
    old_tall_dataset_return = """      if (tf === '2day') return [...yesterdayData, ...todayData];
      if (tf === 'yesterday') return yesterdayData;
      return todayData;"""

    new_tall_dataset_return = """      if (tf === '2day') return [...yesterdayData, ...todayData];
      if (tf === 'yesterday') return yesterdayData;
      if (tf === 'weekly') {
        const days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
        const gW = [124, 121, 124, 118, 116, 115, 118];
        const sW = [2420, 2850, 2140, 2772, 2600, 3200, 2800];
        const sysW = [119, 117, 119, 118, 116, 115, 116];
        const diaW = [76, 74, 75, 76, 72, 71, 72];
        const hrW = [66, 64, 68, 72, 65, 62, 63];
        return days.map((d, i) => ({
          day: d, time: d, fullTime: `Weekly Summary: ${d}`,
          g: gW[i], sys: sysW[i], dia: diaW[i], hr: hrW[i], hrv: 64, spo2: 98.0, temp: 97.3,
          steps: sW[i], meal: (i === 3 ? { type: "Lunch", carbs: 18, cal: 520, desc: "Chicken Salad" } : null),
          ex: (sW[i] > 2500 ? { type: "Daily Walk", dur: 20, delta: -32, desc: "GLUT4 brisk walk" } : null),
          med: (i === 0 ? { name: "Weekly Regimen", taken: true, isMedChange: false } : null),
          notes: `Weekly day ${d} telemetry`
        }));
      }
      if (tf === 'monthly') {
        const mPts = [];
        for (let d = 1; d <= 30; d++) {
          const gVal = Math.round(134 - ((d / 30) * 16) + (Math.sin(d * 1.5) * 2.5));
          const stepVal = Math.round(2100 + (d * 35) + (Math.sin(d * 2) * 250));
          mPts.push({
            day: `D${d}`, time: `D${d}`, fullTime: `Day ${d} of Month`,
            g: gVal, sys: Math.round(122 - ((d / 30) * 6)), dia: Math.round(78 - ((d / 30) * 4)),
            hr: Math.round(66 - ((d / 30) * 4)), hrv: Math.round(54 + (d * 0.4)), spo2: 98.0, temp: 97.2,
            steps: stepVal,
            meal: (d === 15 ? { type: "Audit Meal", carbs: 20, cal: 540, desc: "Clinical diet test" } : null),
            ex: (stepVal > 2800 ? { type: "Cardio", dur: 25, delta: -38, desc: "Active walk" } : null),
            med: (d === 1 ? { name: "Monthly Start", isMedChange: true } : null),
            notes: `Day ${d} longitudinal record`
          });
        }
        return mPts;
      }
      if (tf === 'quarterly') {
        const qWeeks = ['W01', 'W02', 'W03', 'W04', 'W05', 'W06', 'W07', 'W08', 'W09', 'W10', 'W11', 'W12'];
        return qWeeks.map((w, idx) => ({
          day: w, time: w, fullTime: `Quarterly Week ${idx + 1}`,
          g: Math.round(132 - (idx * 1.4)), sys: Math.round(122 - (idx * 0.5)), dia: Math.round(78 - (idx * 0.4)),
          hr: Math.round(68 - (idx * 0.4)), hrv: Math.round(52 + (idx * 1.2)), spo2: 98.2, temp: 97.2,
          steps: Math.round(2200 + (idx * 90)),
          meal: null, ex: { type: "Weekly Target", dur: 120, delta: -40, desc: "Target Met" },
          med: (idx === 6 ? { name: "Dose Adjustment", isMedChange: true } : null),
          notes: `Quarterly week ${idx + 1} compliance`
        }));
      }
      if (tf === 'yearly') {
        const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
        const gAnnual = [131, 130, 128, 126, 125, 123, 122, 121, 120, 118, 117, 116];
        const sAnnual = [2100, 2250, 2400, 2550, 2700, 2800, 2900, 3050, 3100, 3200, 3150, 3250];
        return months.map((m, idx) => ({
          day: m, time: m, fullTime: `Yearly Overview: ${m}`,
          g: gAnnual[idx], sys: Math.round(126 - (idx * 0.9)), dia: Math.round(82 - (idx * 0.7)),
          hr: Math.round(72 - (idx * 0.8)), hrv: Math.round(48 + (idx * 1.7)), spo2: 98.0, temp: 97.2,
          steps: sAnnual[idx],
          meal: null, ex: { type: "Monthly Mean", dur: 150, delta: -45, desc: "Consistent active sink" },
          med: (idx === 0 ? { name: "Annual Baseline", isMedChange: false } : null),
          notes: `${m} longitudinal summary`
        }));
      }
      return todayData;"""

    if old_tall_dataset_return in html:
        html = html.replace(old_tall_dataset_return, new_tall_dataset_return, 1)
        print("[✔] Updated getTallSynchronizedDataset with multi-timeframe engines")
    else:
        print("[!] Could not match old_tall_dataset_return")

    # 5. Fix renderTallSynchronizedChart: Clean Charging Gaps & Clean Axis Ticks
    old_gap_render = """      // Identify Charging Gaps across the timeline
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
      });"""

    new_gap_render = """      // Group contiguous gap indices into single clean charging blocks (prevents label overlap)
      const gapBlocks = [];
      let curBlock = null;
      data.forEach((d, i) => {
        const isGap = (d.isChargingGap || d.g === null);
        if (isGap) {
          if (!curBlock) curBlock = { start: i, end: i };
          else curBlock.end = i;
        } else {
          if (curBlock) {
            gapBlocks.push(curBlock);
            curBlock = null;
          }
        }
      });
      if (curBlock) gapBlocks.push(curBlock);

      gapBlocks.forEach(blk => {
        const x1 = padLeft + (n > 1 ? (blk.start / (n - 1)) * plotW : 0);
        const x2 = padLeft + (n > 1 ? (blk.end / (n - 1)) * plotW : plotW);
        const gapW = Math.max(40, x2 - x1);
        const midX = (x1 + x2) / 2;

        svg += `
          <!-- Contiguous Watch Charging / Off-Wrist Region -->
          <rect x="${x1.toFixed(1)}" y="20" width="${gapW.toFixed(1)}" height="${H - 55}" fill="rgba(241, 245, 249, 0.70)" stroke="#cbd5e1" stroke-dasharray="4 4" rx="4"/>
          <g>
            <rect x="${(midX - 85).toFixed(1)}" y="26" width="170" height="20" fill="#ffffff" stroke="#94a3b8" stroke-width="1.5" rx="4" style="filter: drop-shadow(0 1px 3px rgba(0,0,0,0.08));"/>
            <text x="${midX.toFixed(1)}" y="40" text-anchor="middle" fill="#475569" font-size="9" font-weight="900">⚡ WATCH CHARGING / OFF-WRIST</text>
          </g>
        `;
      });"""

    if old_gap_render in html:
        html = html.replace(old_gap_render, new_gap_render, 1)
        print("[✔] Injected Contiguous Charging Gap Grouping in Tall Chart")
    else:
        print("[!] Could not match old_gap_render")

    # 6. Fix Glucose points (no overlapping '0 (Gap)' clutter) and Bottom Time Axis
    old_bottom_and_points = """      // Interactive Clickable Points on Glucose Curve (Skip charging gaps)
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
              <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="6" fill="#ea580c" stroke="#ffffff" stroke-width="2">
                <title>${p.label}: ${p.val} mg/dL</title>
              </circle>
              <rect x="${(p.x - 14).toFixed(1)}" y="${(p.y - 21).toFixed(1)}" width="28" height="13" rx="3" fill="#ffffff" stroke="#cbd5e1" stroke-width="1"/>
              <text x="${p.x.toFixed(1)}" y="${(p.y - 11).toFixed(1)}" text-anchor="middle" fill="#78350f" font-size="9" font-weight="900">${p.val}</text>
            </g>
          `;
        }
      });

      // Bottom Shared Time Axis Labels
      data.forEach((d, i) => {
        svg += `
          <text x="${xCoords[i]}" y="${H - 18}" text-anchor="middle" fill="var(--lcars-gray)" font-size="10.5" font-weight="bold">${d.time}</text>
          <line x1="${xCoords[i]}" y1="${H - 35}" x2="${xCoords[i]}" y2="${H - 28}" stroke="#253549" stroke-width="1.2"/>
        `;
      });"""

    new_bottom_and_points = """      // Interactive Clickable Points on Glucose Curve (Only render real data pills, skip gap clutter)
      gPts.forEach((p, i) => {
        if (p.val !== null && !p.isGap && p.val > 0) {
          svg += `
            <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
              <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="6" fill="#ea580c" stroke="#ffffff" stroke-width="2">
                <title>${p.label}: ${p.val} mg/dL</title>
              </circle>
              <rect x="${(p.x - 14).toFixed(1)}" y="${(p.y - 21).toFixed(1)}" width="28" height="13" rx="3" fill="#ffffff" stroke="#cbd5e1" stroke-width="1"/>
              <text x="${p.x.toFixed(1)}" y="${(p.y - 11).toFixed(1)}" text-anchor="middle" fill="#78350f" font-size="9" font-weight="900">${p.val}</text>
            </g>
          `;
        }
      });

      // Bottom Shared Time Axis Labels - Evenly Spaced Ticks to Prevent Collision
      let tickStep = 1;
      if (n > 30) tickStep = 6;      // 48 half-hour slots -> tick every 3 hours
      else if (n > 16) tickStep = 4;
      else if (n > 8) tickStep = 2;

      for (let i = 0; i < n; i += tickStep) {
        svg += `
          <text x="${xCoords[i]}" y="${H - 18}" text-anchor="middle" fill="var(--lcars-gray)" font-size="10.5" font-weight="bold">${data[i].time}</text>
          <line x1="${xCoords[i]}" y1="${H - 35}" x2="${xCoords[i]}" y2="${H - 28}" stroke="#253549" stroke-width="1.2"/>
        `;
      }
      if ((n - 1) % tickStep !== 0) {
        const lastIdx = n - 1;
        svg += `
          <text x="${xCoords[lastIdx]}" y="${H - 18}" text-anchor="middle" fill="var(--lcars-gray)" font-size="10.5" font-weight="bold">${data[lastIdx].time}</text>
          <line x1="${xCoords[lastIdx]}" y1="${H - 35}" x2="${xCoords[lastIdx]}" y2="${H - 28}" stroke="#253549" stroke-width="1.2"/>
        `;
      }"""

    if old_bottom_and_points in html:
        html = html.replace(old_bottom_and_points, new_bottom_and_points, 1)
        print("[✔] Injected Collision-Free Axis Ticks and Clean Point Tags")
    else:
        print("[!] Could not match old_bottom_and_points")

    # 7. Scrubber default to latest active data point instead of 50%
    old_scrubber_init = """      // Initialize HUD to mid-point
      onTallScrubberMoved(50);"""

    new_scrubber_init = """      // Initialize HUD to latest active telemetry point
      let initIdx = Math.floor(n / 2);
      for (let i = n - 1; i >= 0; i--) {
        if (data[i] && !data[i].isChargingGap && data[i].g !== null && !data[i].isFuture) {
          initIdx = i;
          break;
        }
      }
      const initPct = n > 1 ? (initIdx / (n - 1)) * 100 : 50;
      const sld = document.getElementById('tallScrubberSlider');
      if (sld) sld.value = initPct;
      onTallScrubberMoved(initPct);"""

    if old_scrubber_init in html:
        html = html.replace(old_scrubber_init, new_scrubber_init, 1)
        print("[✔] Injected Intelligent Scrubber Initial Point Positioning")
    else:
        print("[!] Could not match old_scrubber_init")

    # 8. Add Sub-Report Trend Chart rendering function in JavaScript
    sub_report_engine = """
    // =========================================================================
    // SUB-REPORTS 02, 03, 04, 05 LONGITUDINAL TREND CHARTING ENGINE
    // Renders interactive SVG trends across Daily, Weekly, Monthly, QTR, and YTD
    // =========================================================================
    function renderSubReportTrendCharts(tf) {
      const isDaily = tf === 'daily';
      const isWeekly = tf === 'weekly';
      const isMonthly = tf === 'monthly';
      const isQuarterly = tf === 'quarterly';
      const isYearly = tf === 'yearly';

      // Update Sub-Report Titles based on timeframe
      const tEcg = document.getElementById('repChartEcgTitle');
      const tMet = document.getElementById('repChartMetTitle');
      const tBc = document.getElementById('repChartBloodCompTitle');
      const tBoc = document.getElementById('repChartBodyCompTitle');

      if (isDaily) {
        if (tEcg) tEcg.textContent = "DAILY 24-HOUR ECG LEAD-I & QTc DURATION TREND (MS)";
        if (tMet) tMet.textContent = "DAILY 24-HOUR MET LOAD & ACCUMULATED MET-HOURS (34.2 MET-HRS)";
        if (tBc) tBc.textContent = "DAILY MICROVASCULAR RHEOLOGY, VISCOSITY & LIPID TONOMETRY";
        if (tBoc) tBoc.textContent = "DAILY BIOELECTRICAL IMPEDANCE & SKELETAL GLUCOSE SINK";
      } else if (isWeekly) {
        if (tEcg) tEcg.textContent = "WEEKLY 7-DAY ECG LEAD-I & QTc PROFILE (MS)";
        if (tMet) tMet.textContent = "WEEKLY 7-DAY MET ENERGY EXPENDITURE (ADA TARGET ≥28 MET-HRS/DAY)";
        if (tBc) tBc.textContent = "WEEKLY 7-DAY BLOOD VISCOSITY (cP) & SERUM URIC ACID (MG/DL)";
        if (tBoc) tBoc.textContent = "WEEKLY 7-DAY SKELETAL MUSCLE SINK % VS ADIPOSE FAT %";
      } else if (isMonthly) {
        if (tEcg) tEcg.textContent = "MONTHLY 30-DAY QTc STABILITY & AUTONOMIC SYMPATHOVAGAL TREND (MS)";
        if (tMet) tMet.textContent = "MONTHLY 30-DAY MET ACTIVITY ADHERENCE (ADA STANDARD)";
        if (tBc) tBc.textContent = "MONTHLY 30-DAY HEMODYNAMIC VISCOSITY & LIPID PROXY TREND";
        if (tBoc) tBoc.textContent = "MONTHLY 30-DAY BODY COMPOSITION EVOLUTION (BIA)";
      } else if (isQuarterly) {
        if (tEcg) tEcg.textContent = "QUARTERLY 90-DAY CARDIAC VECTOR & QTc LONGITUDINAL PROFILE (MS)";
        if (tMet) tMet.textContent = "QUARTERLY 90-DAY METABOLIC LOAD DYNAMICS";
        if (tBc) tBc.textContent = "QUARTERLY 90-DAY MICROVASCULAR RHEOLOGY DYNAMICS";
        if (tBoc) tBoc.textContent = "QUARTERLY 90-DAY LEAN MASS TO VISCERAL FAT TRAJECTORY";
      } else {
        if (tEcg) tEcg.textContent = "ANNUAL 12-MONTH QTc & CARDIAC RESILIENCE ADAPTATION (MS)";
        if (tMet) tMet.textContent = "ANNUAL 12-MONTH MET CAPACITY & ENERGY EXPENDITURE PROFILE";
        if (tBc) tBc.textContent = "ANNUAL 12-MONTH VISCOSITY & LIPID TONOMETRY HOMEOSTASIS";
        if (tBoc) tBoc.textContent = "ANNUAL 12-MONTH BIOELECTRICAL IMPEDANCE SKELETAL ADAPTATION";
      }

      let ecgData = [];
      let metData = [];
      let bloodCompData = [];
      let bodyCompData = [];

      if (isDaily) {
        const hours = ['00:00', '03:00', '06:00', '08:30', '09:00', '12:00', '15:00', '18:00', '21:00', '23:30'];
        const qtcDaily = [408, 404, 412, 422, 414, 416, 412, 418, 410, 408];
        const lfHfDaily = [0.95, 0.90, 1.05, 1.35, 1.20, 1.22, 1.18, 1.25, 1.10, 1.02];
        const metCumDaily = [1.0, 3.2, 7.6, 12.4, 18.2, 22.8, 27.4, 31.8, 33.6, 34.2];
        const viscDaily = [3.8, 3.8, 3.9, 3.7, 3.7, 3.8, 3.8, 3.7, 3.8, 3.8];
        const uricDaily = [5.1, 5.0, 5.2, 5.4, 5.2, 5.2, 5.3, 5.2, 5.1, 5.1];
        const muscleDaily = [34.1, 34.1, 34.2, 34.2, 34.4, 34.3, 34.2, 34.3, 34.2, 34.2];
        const fatDaily = [22.5, 22.5, 22.4, 22.4, 22.3, 22.4, 22.4, 22.3, 22.4, 22.4];

        hours.forEach((h, idx) => {
          ecgData.push({ label: h, val: qtcDaily[idx], core: qtcDaily[idx], skin: lfHfDaily[idx] });
          metData.push({ label: h, val: metCumDaily[idx] });
          bloodCompData.push({ label: h, val: viscDaily[idx], core: viscDaily[idx], skin: uricDaily[idx] });
          bodyCompData.push({ label: h, val: muscleDaily[idx], core: muscleDaily[idx], skin: fatDaily[idx] });
        });
      } else if (isWeekly) {
        const days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
        const qtcW = [415, 412, 414, 412, 410, 408, 411];
        const lfHfW = [1.25, 1.20, 1.22, 1.22, 1.16, 1.12, 1.15];
        const metW = [31.4, 34.2, 29.8, 35.6, 33.1, 38.4, 32.8];
        const viscW = [3.9, 3.8, 3.8, 3.8, 3.7, 3.7, 3.8];
        const uricW = [5.4, 5.2, 5.3, 5.2, 5.1, 5.0, 5.1];
        const muscleW = [34.0, 34.1, 34.1, 34.2, 34.3, 34.4, 34.3];
        const fatW = [22.6, 22.5, 22.5, 22.4, 22.3, 22.2, 22.3];

        days.forEach((d, idx) => {
          ecgData.push({ label: d, val: qtcW[idx], core: qtcW[idx], skin: lfHfW[idx] });
          metData.push({ label: d, val: metW[idx] });
          bloodCompData.push({ label: d, val: viscW[idx], core: viscW[idx], skin: uricW[idx] });
          bodyCompData.push({ label: d, val: muscleW[idx], core: muscleW[idx], skin: fatW[idx] });
        });
      } else if (isMonthly) {
        for (let d = 1; d <= 30; d++) {
          const lbl = `D${d}`;
          const qVal = Math.round(416 - ((d / 30) * 5) + (Math.sin(d) * 2));
          const lfVal = parseFloat((1.28 - ((d / 30) * 0.12) + (Math.cos(d) * 0.04)).toFixed(2));
          const mVal = parseFloat((30.5 + ((d / 30) * 4.5) + (Math.sin(d * 1.5) * 1.8)).toFixed(1));
          const vVal = parseFloat((3.9 - ((d / 30) * 0.2) + (Math.sin(d) * 0.05)).toFixed(2));
          const uVal = parseFloat((5.5 - ((d / 30) * 0.4) + (Math.cos(d) * 0.1)).toFixed(1));
          const musVal = parseFloat((33.8 + ((d / 30) * 0.6)).toFixed(1));
          const fVal = parseFloat((23.0 - ((d / 30) * 0.8)).toFixed(1));

          ecgData.push({ label: lbl, val: qVal, core: qVal, skin: lfVal });
          metData.push({ label: lbl, val: mVal });
          bloodCompData.push({ label: lbl, val: vVal, core: vVal, skin: uVal });
          bodyCompData.push({ label: lbl, val: musVal, core: musVal, skin: fVal });
        }
      } else if (isQuarterly) {
        const qWeeks = ['W01', 'W02', 'W03', 'W04', 'W05', 'W06', 'W07', 'W08', 'W09', 'W10', 'W11', 'W12'];
        qWeeks.forEach((w, idx) => {
          const qVal = Math.round(418 - (idx * 0.6));
          const lfVal = parseFloat((1.30 - (idx * 0.015)).toFixed(2));
          const mVal = parseFloat((29.8 + (idx * 0.5)).toFixed(1));
          const vVal = parseFloat((4.0 - (idx * 0.025)).toFixed(2));
          const uVal = parseFloat((5.6 - (idx * 0.04)).toFixed(1));
          const musVal = parseFloat((33.6 + (idx * 0.08)).toFixed(1));
          const fVal = parseFloat((23.2 - (idx * 0.09)).toFixed(1));

          ecgData.push({ label: w, val: qVal, core: qVal, skin: lfVal });
          metData.push({ label: w, val: mVal });
          bloodCompData.push({ label: w, val: vVal, core: vVal, skin: uVal });
          bodyCompData.push({ label: w, val: musVal, core: musVal, skin: fVal });
        });
      } else {
        const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
        months.forEach((m, idx) => {
          const qVal = Math.round(420 - (idx * 0.7));
          const lfVal = parseFloat((1.32 - (idx * 0.018)).toFixed(2));
          const mVal = parseFloat((28.5 + (idx * 0.55)).toFixed(1));
          const vVal = parseFloat((4.1 - (idx * 0.03)).toFixed(2));
          const uVal = parseFloat((5.7 - (idx * 0.05)).toFixed(1));
          const musVal = parseFloat((33.4 + (idx * 0.09)).toFixed(1));
          const fVal = parseFloat((23.5 - (idx * 0.11)).toFixed(1));

          ecgData.push({ label: m, val: qVal, core: qVal, skin: lfVal });
          metData.push({ label: m, val: mVal });
          bloodCompData.push({ label: m, val: vVal, core: vVal, skin: uVal });
          bodyCompData.push({ label: m, val: musVal, core: musVal, skin: fVal });
        });
      }

      // Render Sub-Report 02: ECG Trend Dual Curve (QTc vs 440 ms ceiling & LF/HF)
      renderSvgDualCurveChart('curveTimelineECG', ecgData, {
        minY: 360, maxY: 480, unit: "ms",
        referenceLines: [
          { val: 440, label: "440 MS QTc CEILING", color: "#e11d48" },
          { val: 412, label: "412 MS NORMAL BASELINE", color: "#15803d" }
        ]
      });

      // Render Sub-Report 03: MET Trend Curve (Cumulative MET-hrs vs ADA Target)
      renderSvgCurveChart('curveTimelineMET', metData, {
        strokeColor: "#15803d",
        unit: "MET-hrs",
        referenceLines: [
          { val: 28.0, label: "≥ 28.0 MET-HRS ADA TARGET", color: "#16a34a" }
        ]
      });

      // Render Sub-Report 04: Blood Composition Dual Curve (Viscosity vs 4.5 & Uric Acid vs 6.5)
      renderSvgDualCurveChart('curveTimelineBloodComp', bloodCompData, {
        minY: 2.0, maxY: 8.0, unit: "cP / mg/dL",
        referenceLines: [
          { val: 4.5, label: "4.5 cP VISCOSITY CEILING", color: "#d97706" },
          { val: 6.5, label: "6.5 MG/DL URIC ACID RISK", color: "#dc2626" }
        ]
      });

      // Render Sub-Report 05: Body Composition Dual Curve (Skeletal Muscle Sink % & Body Fat %)
      renderSvgDualCurveChart('curveTimelineBodyComp', bodyCompData, {
        minY: 15, maxY: 45, unit: "%",
        referenceLines: [
          { val: 34.0, label: "34% SKELETAL SINK", color: "#15803d" },
          { val: 22.0, label: "22% BODY FAT TARGET", color: "#0284c7" }
        ]
      });
    }
"""

    # 9. Inject call to renderSubReportTrendCharts inside renderReportsView()
    old_call_spot = """      if (typeof renderStepGlucoseCorrelator === "function") renderStepGlucoseCorrelator();
      if (typeof renderLorenzScatterPlot === "function") renderLorenzScatterPlot();
      if (typeof updateWatchMetabolicSubReports === "function") updateWatchMetabolicSubReports();"""

    new_call_spot = """      if (typeof renderStepGlucoseCorrelator === "function") renderStepGlucoseCorrelator();
      if (typeof renderLorenzScatterPlot === "function") renderLorenzScatterPlot();
      if (typeof updateWatchMetabolicSubReports === "function") updateWatchMetabolicSubReports();
      if (typeof renderSubReportTrendCharts === "function") renderSubReportTrendCharts(currentReportTimeframe);"""

    if old_call_spot in html:
        html = html.replace(old_call_spot, new_call_spot, 1)
        print("[✔] Injected renderSubReportTrendCharts call in renderReportsView")
    else:
        print("[!] Could not match old_call_spot")

    # Inject the function definition right before renderReportsView
    if "function renderSubReportTrendCharts" not in html:
        render_reports_view_str = "    function renderReportsView() {"
        html = html.replace(render_reports_view_str, sub_report_engine + "\n" + render_reports_view_str, 1)
        print("[✔] Injected renderSubReportTrendCharts definition")

    # 10. Check div balance
    opens = len(re.findall(r'<div\b', html, re.I))
    closes = len(re.findall(r'</div\b', html, re.I))
    diff = opens - closes
    print(f"Div count: open={opens}, close={closes}, diff={diff}")
    assert diff == 0, f"Div imbalance detected: {diff}"

    with open('index.html', 'w', encoding='utf-8') as f:
        f.write(html)
    with open('w11_htm_app/index.html', 'w', encoding='utf-8') as f:
        f.write(html)
    print("[✔] Successfully updated both index.html and w11_htm_app/index.html with bit-for-bit parity!")

run()
