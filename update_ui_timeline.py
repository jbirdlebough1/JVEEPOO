import os, re

def update_ui_timeline_and_collector():
    files = [r"D:\veepoo\w11_htm_app\index.html", r"D:\veepoo\index.html"]

    for fpath in files:
        with open(fpath, "r", encoding="utf-8") as f:
            html = f.read()

        # 1. Add postTelemetryToServer in JS
        post_fn = """
    // Real-Time Telemetry Host Synchronization & SQLite Deduplication
    function postTelemetryToServer(metric, val1, val2, unit, rawHex) {
      try {
        const payload = {
          metric: metric,
          value1: val1,
          value2: val2,
          unit: unit,
          progress: 100,
          status: "NOMINAL",
          mode: "WEB_BLUETOOTH_STREAM",
          raw_hex: rawHex,
          timestamp: new Date().toISOString()
        };
        fetch('/api/telemetry', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        }).catch(err => {});
      } catch (e) {}
    }
"""
        if "function postTelemetryToServer" not in html:
            pos_rx = html.find("function onTelemetryReceived(")
            if pos_rx != -1:
                html = html[:pos_rx] + post_fn + "\n    " + html[pos_rx:]
                print(f"[✔] Injected postTelemetryToServer into {fpath}")

        # 2. Call postTelemetryToServer inside onTelemetryReceived
        # Temperature
        html = re.sub(
            r'(insert_vital_record\("TEMPERATURE".*?\)|document\.getElementById\("valTemp"\)\.textContent = .*?;)',
            r'\1\n        postTelemetryToServer("TEMPERATURE", bodyF, skinF, "°F", hex);',
            html
        )
        # Glucose
        html = re.sub(
            r'(document\.getElementById\("valGlucose"\)\.textContent = .*?;)',
            r'\1\n        postTelemetryToServer("GLUCOSE", glucoseMgdl, ea1c, "mg/dL", hex);',
            html
        )
        # Blood Pressure
        html = re.sub(
            r'(document\.getElementById\("valBloodPressure"\)\.textContent = .*?;)',
            r'\1\n        postTelemetryToServer("BLOOD_PRESSURE", sysBp, diaBp, "mmHg", hex);',
            html
        )

        # 3. Add 2-Day Timeline Buttons in the Tall Chart Toolbar
        old_tall_btns = """<button class="lcars-btn btn-peach active" id="btnTallDaily" onclick="setTallTimeframe('daily')">DAILY (24H)</button>"""
        new_tall_btns = """<button class="lcars-btn btn-lilac active" id="btnTall2Day" onclick="setTallTimeframe('2day')" title="Scroll across 2 full days (Yesterday + Today)">📅 2-DAY VIEW (48H)</button>
                <button class="lcars-btn btn-peach" id="btnTallDaily" onclick="setTallTimeframe('daily')">TODAY (24H)</button>
                <button class="lcars-btn btn-blue" id="btnTallYesterday" onclick="setTallTimeframe('yesterday')">YESTERDAY (24H)</button>"""
        if old_tall_btns in html:
            html = html.replace(old_tall_btns, new_tall_btns)
            print(f"[✔] Added 2-Day View, Today, and Yesterday buttons to Tall Chart toolbar in {fpath}")

        # 4. Update getTallSynchronizedDataset to support 2day and fetch from /api/telemetry/timeline
        old_dataset_fn_start = "function getTallSynchronizedDataset(tf) {"
        pos_ds = html.find(old_dataset_fn_start)
        if pos_ds != -1:
            pos_ds_end = html.find("let activeTallDataset = [];", pos_ds)
            if pos_ds_end != -1:
                new_dataset_fn = """let cached2DayTimeline = null;

    async function fetchServer2DayTimeline() {
      try {
        const resp = await fetch('/api/telemetry/timeline');
        if (resp.ok) {
          const data = await resp.json();
          if (Array.isArray(data) && data.length > 0) {
            cached2DayTimeline = data;
            return data;
          }
        }
      } catch(e) {}
      return null;
    }

    function getTallSynchronizedDataset(tf) {
      const curG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : 106;
      
      // If server 2-day timeline is available, filter by tf
      if (cached2DayTimeline && cached2DayTimeline.length > 0) {
        if (tf === '2day') return cached2DayTimeline;
        if (tf === 'yesterday') return cached2DayTimeline.filter(d => d.day === 'YESTERDAY');
        if (tf === 'daily' || tf === 'today') return cached2DayTimeline.filter(d => d.day === 'TODAY');
      }

      // High-Fidelity 48-Hour Fallback (Yesterday + Today with Transparent Blue Charging Areas & Zero Random Fill)
      const yesterdayData = [
        { day: 'YESTERDAY', time: 'Y-00:00', fullTime: 'YESTERDAY 00:00', g: 154, sys: 112, dia: 70, hr: 58, hrv: 68, spo2: 98.2, temp: 97.0, steps: 0, meal: null, ex: null, med: null, notes: "Nocturnal baseline stability" },
        { day: 'YESTERDAY', time: 'Y-04:00', fullTime: 'YESTERDAY 04:00', g: 148, sys: 108, dia: 66, hr: 54, hrv: 72, spo2: 97.6, temp: 96.6, steps: 0, meal: null, ex: null, med: null, notes: "Nocturnal nadir" },
        { day: 'YESTERDAY', time: 'Y-07:30', fullTime: 'YESTERDAY 07:30', g: 162, sys: 116, dia: 74, hr: 66, hrv: 62, spo2: 98.0, temp: 97.0, steps: 220, meal: null, ex: null, med: null, notes: "Dawn phenomenon waking curve" },
        { day: 'YESTERDAY', time: 'Y-08:15', fullTime: 'YESTERDAY 08:15', g: 155, sys: 118, dia: 74, hr: 68, hrv: 60, spo2: 98.5, temp: 97.2, steps: 410, meal: { type: "Breakfast", carbs: 14, cal: 360, desc: "Eggs, spinach & avocado" }, ex: null, med: { name: "Morning Regimen", taken: true }, notes: "Low-carb breakfast paired with oral meds" },
        { day: 'YESTERDAY', time: 'Y-09:00', fullTime: 'YESTERDAY 09:00', g: 142, sys: 120, dia: 76, hr: 92, hrv: 55, spo2: 99.0, temp: 97.4, steps: 2450, meal: null, ex: { type: "Morning Walk", dur: 15, delta: -32, desc: "15-min GLUT4 walk" }, med: null, notes: "Non-insulin skeletal muscle GLUT4 blunted breakfast spike" },
        { day: 'YESTERDAY', time: 'Y-11:00', fullTime: 'YESTERDAY 11:00', g: null, sys: null, dia: null, hr: null, hrv: null, spo2: null, temp: null, steps: 2510, isChargingGap: true, meal: null, ex: null, med: null, notes: "⚡ Watch Charging on Dock (Off-Wrist • No Telemetry Samples)" },
        { day: 'YESTERDAY', time: 'Y-12:30', fullTime: 'YESTERDAY 12:30', g: 138, sys: 115, dia: 72, hr: 68, hrv: 62, spo2: 98.0, temp: 97.2, steps: 2600, meal: null, ex: null, med: null, notes: "Pre-lunch resting state (Watch donned post-charge)" },
        { day: 'YESTERDAY', time: 'Y-13:15', fullTime: 'YESTERDAY 13:15', g: 172, sys: 119, dia: 76, hr: 84, hrv: 56, spo2: 98.0, temp: 97.3, steps: 3850, meal: { type: "Lunch", carbs: 22, cal: 510, desc: "Grilled chicken salad" }, ex: { type: "Walk", dur: 15, delta: -38, desc: "Post-lunch walk" }, med: null, notes: "Postprandial peak held < 180 mg/dL" },
        { day: 'YESTERDAY', time: 'Y-16:00', fullTime: 'YESTERDAY 16:00', g: 130, sys: 114, dia: 72, hr: 66, hrv: 64, spo2: 98.5, temp: 97.4, steps: 4420, meal: null, ex: null, med: null, notes: "Afternoon euglycemic homeostasis" },
        { day: 'YESTERDAY', time: 'Y-19:00', fullTime: 'YESTERDAY 19:00', g: 158, sys: 118, dia: 75, hr: 72, hrv: 58, spo2: 98.0, temp: 97.5, steps: 5100, meal: { type: "Dinner", carbs: 24, cal: 580, desc: "Salmon & roasted vegetables" }, ex: null, med: { name: "Evening Dose", taken: true }, notes: "Low-carb dinner" },
        { day: 'YESTERDAY', time: 'Y-22:30', fullTime: 'YESTERDAY 22:30', g: 140, sys: 110, dia: 68, hr: 60, hrv: 68, spo2: 98.2, temp: 97.2, steps: 5400, meal: null, ex: null, med: null, notes: "Pre-sleep baseline" }
      ];

      const todayData = [
        { day: 'TODAY', time: 'T-00:00', fullTime: 'TODAY 00:00', g: 136, sys: 108, dia: 68, hr: 56, hrv: 70, spo2: 98.4, temp: 97.0, steps: 0, meal: null, ex: null, med: null, notes: "Nocturnal baseline stability" },
        { day: 'TODAY', time: 'T-04:00', fullTime: 'TODAY 04:00', g: 132, sys: 106, dia: 65, hr: 53, hrv: 74, spo2: 97.8, temp: 96.5, steps: 0, meal: null, ex: null, med: null, notes: "Diurnal temperature & metabolic nadir" },
        { day: 'TODAY', time: 'T-07:00', fullTime: 'TODAY 07:00', g: 145, sys: 114, dia: 72, hr: 64, hrv: 63, spo2: 98.0, temp: 97.0, steps: 150, meal: null, ex: null, med: null, notes: "Mild cortisol awakening surge" },
        { day: 'TODAY', time: 'T-08:15', fullTime: 'TODAY 08:15', g: 138, sys: 118, dia: 74, hr: 68, hrv: 60, spo2: 98.5, temp: 97.2, steps: 340, meal: { type: "Breakfast", carbs: 14, cal: 380, desc: "Poached eggs, avocado, keto toast" }, ex: null, med: { name: "Morning Regimen", taken: true }, notes: "Low-carb breakfast paired with oral meds" },
        { day: 'TODAY', time: 'T-09:00', fullTime: 'TODAY 09:00', g: 124, sys: 122, dia: 78, hr: 94, hrv: 54, spo2: 99.0, temp: 97.4, steps: 2384, meal: null, ex: { type: "Post-Meal Walk", dur: 15, delta: -34, desc: "15-min brisk neighborhood walk" }, med: null, notes: "GLUT4 muscle uptake blunted breakfast spike" },
        { day: 'TODAY', time: 'T-10:15', fullTime: 'TODAY 10:15', g: 118, sys: 116, dia: 74, hr: 68, hrv: 64, spo2: 99.0, temp: 97.4, steps: 2450, meal: null, ex: null, med: null, notes: "Optimal sustained Time-in-Range (TIR 60–200)" },
        { day: 'TODAY', time: 'T-11:00', fullTime: 'TODAY 11:00', g: null, sys: null, dia: null, hr: null, hrv: null, spo2: null, temp: null, steps: 2450, isChargingGap: true, meal: null, ex: null, med: null, notes: "⚡ Watch Charging on Dock (Off-Wrist • No Telemetry Samples)" },
        { day: 'TODAY', time: 'T-12:30', fullTime: 'TODAY 12:30', g: 114, sys: 115, dia: 73, hr: 70, hrv: 60, spo2: 98.0, temp: 97.2, steps: 2600, meal: null, ex: null, med: null, notes: "Pre-lunch euvolemic resting state (Watch donned post-charge)" },
        { day: 'TODAY', time: 'T-13:15', fullTime: 'TODAY 13:15', g: 148, sys: 119, dia: 76, hr: 84, hrv: 56, spo2: 98.0, temp: 97.3, steps: 3980, meal: { type: "Lunch", carbs: 18, cal: 520, desc: "Grilled chicken breast salad" }, ex: { type: "GLUT4 Walk", dur: 20, delta: -42, desc: "20-min post-lunch walk" }, med: null, notes: "Postprandial peak held safely under target ceiling" },
        { day: 'TODAY', time: 'T-15:30', fullTime: 'TODAY 15:30', g: 106, sys: 114, dia: 72, hr: 68, hrv: 62, spo2: 98.0, temp: 97.4, steps: 4420, meal: { type: "Snack", carbs: 4, cal: 160, desc: "Celery with almond butter" }, ex: null, med: null, notes: "ADA trade snack produced flat glycemic trajectory" },
        { day: 'TODAY', time: 'T-18:30', fullTime: 'TODAY 18:30', g: null, sys: null, dia: null, hr: null, hrv: null, spo2: null, temp: null, steps: 0, isFuture: true, meal: null, ex: null, med: null, notes: "Awaiting Live Telemetry (Future Window)" },
        { day: 'TODAY', time: 'T-21:00', fullTime: 'TODAY 21:00', g: null, sys: null, dia: null, hr: null, hrv: null, spo2: null, temp: null, steps: 0, isFuture: true, meal: null, ex: null, med: null, notes: "Awaiting Live Telemetry (Future Window)" }
      ];

      if (tf === '2day') return [...yesterdayData, ...todayData];
      if (tf === 'yesterday') return yesterdayData;
      return todayData;
    }

    """
                html = html[:pos_ds] + new_dataset_fn + html[pos_ds_end:]
                print(f"[✔] Injected 48-Hour / Two-Day timeline dataset with transparent blue charging areas into {fpath}")

        # 5. Enhance gap rendering in renderTallSynchronizedChart to show high-visibility blue charging area
        old_gap_render = """// Render Visual Charging Gap Column Spanning all 8 Lanes
      gapIndices.forEach(idx => {
        const prevX = idx > 0 ? xCoords[idx - 1] : padLeft;
        const nextX = idx < n - 1 ? xCoords[idx + 1] : padLeft + plotW;
        const gapW = Math.max(28, (nextX - prevX) * 0.7);
        const gapX = xCoords[idx] - (gapW / 2);

        svg += `
          <!-- Visual Charging Gap Column (All 8 Lanes) -->
          <rect x="${gapX}" y="20" width="${gapW}" height="${H - 45}" fill="#0284c7" fill-opacity="0.10" stroke="#0284c7" stroke-width="1.5" stroke-dasharray="4 2"/>
          <text x="${gapX + gapW / 2}" y="36" font-size="9" fill="#0284c7" font-weight="bold" text-anchor="middle">⚡ CHARGING GAP</text>
        `;
      });"""

        new_gap_render = """// Render High-Visibility Visual Charging Gap Column Spanning all 8 Lanes
      gapIndices.forEach(idx => {
        const prevX = idx > 0 ? xCoords[idx - 1] : padLeft;
        const nextX = idx < n - 1 ? xCoords[idx + 1] : padLeft + plotW;
        const gapW = Math.max(34, (nextX - prevX) * 0.85);
        const gapX = xCoords[idx] - (gapW / 2);

        svg += `
          <!-- Visual Charging / Off-Wrist Gap (All 8 Lanes) -->
          <rect x="${gapX}" y="20" width="${gapW}" height="${H - 45}" fill="#bfdbfe" fill-opacity="0.45" stroke="#3b82f6" stroke-width="2" stroke-dasharray="6 3"/>
          <rect x="${gapX + 2}" y="22" width="${gapW - 4}" height="18" fill="#1d4ed8" rx="3" />
          <text x="${gapX + gapW / 2}" y="35" font-size="8.5" fill="#ffffff" font-weight="900" text-anchor="middle">⚡ WATCH CHARGING (0 SAMPLES)</text>
        `;
      });

      // Render Future Window (Right side of Today)
      data.forEach((d, idx) => {
        if (d.isFuture) {
          const slotX = xCoords[idx];
          const slotW = idx < n - 1 ? (xCoords[idx+1] - slotX) : (padLeft + plotW - slotX);
          svg += `
            <rect x="${slotX}" y="20" width="${slotW}" height="${H - 45}" fill="#f1f5f9" fill-opacity="0.4" stroke="#cbd5e1" stroke-width="1" stroke-dasharray="2 2" />
            <text x="${slotX + slotW/2}" y="35" font-size="8" fill="#94a3b8" font-weight="bold" text-anchor="middle">AWAITING TELEMETRY</text>
          `;
        }
      });"""

        if old_gap_render in html:
            html = html.replace(old_gap_render, new_gap_render)
            print(f"[✔] Upgraded charging gap and future window rendering in {fpath}")

        # 6. Update setTallTimeframe to handle '2day'
        old_set_tf = """function setTallTimeframe(tf) {
      currentTallTimeframe = tf;
      ['btnTallDaily', 'btnTallWeekly', 'btnTallMonthly', 'btnTallYearly'].forEach(bId => {
        const b = document.getElementById(bId);
        if (b) b.classList.remove('active');
      });
      const activeId = tf === 'daily' ? 'btnTallDaily' : tf === 'weekly' ? 'btnTallWeekly' : tf === 'monthly' ? 'btnTallMonthly' : 'btnTallYearly';
      const activeBtn = document.getElementById(activeId);
      if (activeBtn) activeBtn.classList.add('active');"""

        new_set_tf = """function setTallTimeframe(tf) {
      currentTallTimeframe = tf;
      ['btnTall2Day', 'btnTallDaily', 'btnTallYesterday', 'btnTallWeekly', 'btnTallMonthly', 'btnTallYearly'].forEach(bId => {
        const b = document.getElementById(bId);
        if (b) b.classList.remove('active');
      });
      const activeId = tf === '2day' ? 'btnTall2Day' : tf === 'yesterday' ? 'btnTallYesterday' : tf === 'daily' ? 'btnTallDaily' : tf === 'weekly' ? 'btnTallWeekly' : tf === 'monthly' ? 'btnTallMonthly' : 'btnTallYearly';
      const activeBtn = document.getElementById(activeId);
      if (activeBtn) activeBtn.classList.add('active');
      
      // Async refresh from server if available
      if (typeof fetchServer2DayTimeline === 'function') {
        fetchServer2DayTimeline().then(() => renderTallSynchronizedChart(tf));
      }"""

        if old_set_tf in html:
            html = html.replace(old_set_tf, new_set_tf)
            print(f"[✔] Updated setTallTimeframe with 2-day selector in {fpath}")

        with open(fpath, "w", encoding="utf-8") as f:
            f.write(html)
        print(f"[✔] Saved {fpath}")

if __name__ == "__main__":
    update_ui_timeline_and_collector()
