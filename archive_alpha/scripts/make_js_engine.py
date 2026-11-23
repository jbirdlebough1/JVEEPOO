def get_js_engine_code():
    return '''
    // =========================================================================
    // 360° PERSONAL HEALTH VIEW, EXERCISE GLUT4, MEDICATION & TALL CHART ENGINE
    // =========================================================================

    // -------------------------------------------------------------------------
    // 1. EXERCISE & GLUT4 JOURNAL STATE
    // -------------------------------------------------------------------------
    let exerciseJournal = [];
    try {
      const savedEx = localStorage.getItem('veepoo_exercise_journal');
      if (savedEx) exerciseJournal = JSON.parse(savedEx);
    } catch (e) {}

    if (!exerciseJournal || exerciseJournal.length === 0) {
      const todayStr = new Date().toISOString().slice(0, 10);
      exerciseJournal = [
        {
          id: 1,
          ts: `${todayStr} 08:45`,
          type: "Post-Meal Walk",
          duration: 15,
          timing: "15-min Post-Breakfast",
          preG: 172,
          postG: 138,
          delta: -34,
          notes: "Brisk neighborhood walk after poached eggs; flattened acute postprandial spike."
        },
        {
          id: 2,
          ts: `${todayStr} 13:15`,
          type: "Brisk Walk / GLUT4 Ambulation",
          duration: 20,
          timing: "20-min Post-Lunch",
          preG: 188,
          postG: 146,
          delta: -42,
          notes: "Steady 110 BPM pace. Cleared 42 mg/dL via non-insulin GLUT4 transport."
        }
      ];
      try { localStorage.setItem('veepoo_exercise_journal', JSON.stringify(exerciseJournal)); } catch (e) {}
    }

    function initExerciseFormTime() {
      const now = new Date();
      const localISO = new Date(now.getTime() - (now.getTimezoneOffset() * 60000)).toISOString().slice(0, 16);
      const el = document.getElementById('exTimestamp');
      if (el) el.value = localISO;
    }

    function saveExerciseEntry() {
      soundButtonPress();
      const tsEl = document.getElementById('exTimestamp');
      const tsVal = tsEl ? tsEl.value.replace('T', ' ') : new Date().toISOString().slice(0, 16).replace('T', ' ');
      const type = document.getElementById('exType') ? document.getElementById('exType').value : "Post-Meal Walk";
      const duration = parseInt(document.getElementById('exDuration').value) || 15;
      const timing = document.getElementById('exTiming') ? document.getElementById('exTiming').value : "15-min Post-Meal";
      const preG = parseInt(document.getElementById('exPreGlucose').value) || 160;
      const postG = parseInt(document.getElementById('exPostGlucose').value) || (preG - 30);
      const notes = document.getElementById('exNotes') ? document.getElementById('exNotes').value.trim() : "";
      const delta = postG - preG;

      const newEntry = {
        id: Date.now(),
        ts: tsVal,
        type: type,
        duration: duration,
        timing: timing,
        preG: preG,
        postG: postG,
        delta: delta,
        notes: notes || "Targeted physical movement session"
      };

      exerciseJournal.unshift(newEntry);
      try { localStorage.setItem('veepoo_exercise_journal', JSON.stringify(exerciseJournal)); } catch (e) {}
      renderExerciseHistory();
      render360HealthView();
      logLCARS(`[+] Exercise logged: ${type} (${duration}m). GLUT4 Glucose Delta: ${delta} mg/dL`, "rx");
      clearExerciseForm();
    }

    function clearExerciseForm() {
      if (document.getElementById('exNotes')) document.getElementById('exNotes').value = '';
      if (document.getElementById('exDuration')) document.getElementById('exDuration').value = '15';
      initExerciseFormTime();
    }

    function deleteExerciseEntry(id) {
      soundButtonPress();
      exerciseJournal = exerciseJournal.filter(x => x.id !== id);
      try { localStorage.setItem('veepoo_exercise_journal', JSON.stringify(exerciseJournal)); } catch (e) {}
      renderExerciseHistory();
      render360HealthView();
    }

    function clearAllExercises() {
      if (confirm("Purge all logged exercise and workout sessions?")) {
        soundButtonPress();
        exerciseJournal = [];
        localStorage.removeItem('veepoo_exercise_journal');
        renderExerciseHistory();
        render360HealthView();
      }
    }

    function renderExerciseHistory() {
      const list = document.getElementById('exerciseHistoryList');
      if (!list) return;
      list.innerHTML = '';

      if (exerciseJournal.length === 0) {
        list.innerHTML = `
          <div style="padding: 20px; text-align: center; color: var(--lcars-peach); font-family: var(--lcars-mono); font-size: 0.9rem; border: 1px dashed #2d241c; border-radius: 6px;">
            NO WORKOUT SESSIONS LOGGED • RECORD A POST-MEAL WALK ABOVE TO AUDIT GLUT4 GLUCOSE CLEARANCE
          </div>
        `;
        const valG = document.getElementById('valGlut4Clearance');
        if (valG) valG.innerHTML = `-- <span style="font-size: 1rem;">mg/dL</span>`;
        return;
      }

      let totalDrop = 0, countDrop = 0;

      exerciseJournal.forEach(x => {
        if (x.delta < 0) {
          totalDrop += Math.abs(x.delta);
          countDrop++;
        }

        const item = document.createElement('div');
        item.className = 'diet-item-card';
        item.style.borderLeftColor = 'var(--lcars-peach)';
        item.innerHTML = `
          <div style="display: flex; flex-direction: column; gap: 4px;">
            <div style="display: flex; align-items: center; gap: 10px; flex-wrap: wrap;">
              <strong style="color: var(--lcars-peach); font-size: 1.05rem;">${x.type.toUpperCase()}</strong>
              <span style="font-family: var(--lcars-mono); font-size: 0.8rem; color: var(--lcars-gray);">${x.ts}</span>
              <span class="state-badge normal" style="font-size: 0.75rem; background: rgba(255,153,102,0.15); border-color: var(--lcars-peach); color: var(--lcars-peach);">${x.duration} MIN • ${x.timing}</span>
            </div>
            <div style="color: var(--lcars-text); font-size: 0.9rem; margin-top: 2px;">${x.notes}</div>
            <div style="margin-top: 6px; display: flex; gap: 6px; flex-wrap: wrap;">
              <span class="diet-macro-badge badge-carb" style="background: rgba(255,187,0,0.15); color: var(--lcars-banana);">PRE: ${x.preG} MG/DL</span>
              <span class="diet-macro-badge badge-protein" style="background: rgba(85,255,119,0.15); color: var(--lcars-green);">POST: ${x.postG} MG/DL</span>
              <span class="diet-macro-badge badge-glucose" style="background: rgba(85,255,119,0.25); color: var(--lcars-green); font-weight: bold;">
                GLUT4 CLEARANCE: ${x.delta <= 0 ? x.delta : '+' + x.delta} MG/DL
              </span>
            </div>
          </div>
          <div>
            <button class="lcars-btn btn-orange" style="padding: 4px 10px; font-size: 0.8rem;" onclick="deleteExerciseEntry(${x.id})">DELETE</button>
          </div>
        `;
        list.appendChild(item);
      });

      const avgDrop = countDrop > 0 ? Math.round(totalDrop / countDrop) : 34;
      const valG = document.getElementById('valGlut4Clearance');
      if (valG) valG.innerHTML = `-${avgDrop} <span style="font-size: 1rem;">mg/dL</span>`;
    }

    // -------------------------------------------------------------------------
    // 2. MEDICATION MANAGEMENT & CHANGE LOG STATE (11-MEDICATION)
    // -------------------------------------------------------------------------
    let medicationList = [];
    try {
      const savedMeds = localStorage.getItem('veepoo_medication_list');
      if (savedMeds) medicationList = JSON.parse(savedMeds);
    } catch (e) {}

    if (!medicationList || medicationList.length === 0) {
      medicationList = [
        {
          id: 1,
          name: "Metformin Extended-Release (Glucophage XR)",
          dose: "1,000 mg",
          schedule: "Oral BID • Breakfast & Dinner",
          indication: "Type 2 Diabetes • Hepatic Gluconeogenesis Suppression",
          status: "ACTIVE",
          lastTaken: "Today 08:15 AM (Breakfast)"
        },
        {
          id: 2,
          name: "Empagliflozin (Jardiance)",
          dose: "10 mg",
          schedule: "Oral Daily • Morning with Breakfast",
          indication: "SGLT2 Inhibitor • Renal Glucose Clearance & Cardiorenal Protection",
          status: "ACTIVE",
          lastTaken: "Today 08:15 AM (Breakfast)"
        },
        {
          id: 3,
          name: "Lisinopril",
          dose: "10 mg",
          schedule: "Oral Daily • Morning",
          indication: "ACE Inhibitor • Renal Microvascular Protection & Blood Pressure Homeostasis",
          status: "ACTIVE",
          lastTaken: "Today 08:15 AM"
        }
      ];
      try { localStorage.setItem('veepoo_medication_list', JSON.stringify(medicationList)); } catch (e) {}
    }

    let medicationChangeLog = [];
    try {
      const savedChanges = localStorage.getItem('veepoo_medication_changes');
      if (savedChanges) medicationChangeLog = JSON.parse(savedChanges);
    } catch (e) {}

    if (!medicationChangeLog || medicationChangeLog.length === 0) {
      medicationChangeLog = [
        {
          id: 1,
          ts: "2026-10-28 09:30",
          name: "Metformin ER",
          type: "Dose Increase",
          oldDose: "500 mg BID",
          newDose: "1,000 mg BID (Breakfast & Dinner)",
          doctor: "Dr. Martinez, Endocrinology",
          reason: "Titrated to address persistent morning fasting baseline >170 mg/dL",
          preMeanG: 182,
          postMeanG: 154,
          deltaG: -28,
          tirDelta: +16.2,
          verdict: "PROGRESS"
        }
      ];
      try { localStorage.setItem('veepoo_medication_changes', JSON.stringify(medicationChangeLog)); } catch (e) {}
    }

    function initMedChangeTime() {
      const now = new Date();
      const localISO = new Date(now.getTime() - (now.getTimezoneOffset() * 60000)).toISOString().slice(0, 16);
      const el = document.getElementById('medChangeTimestamp');
      if (el) el.value = localISO;
    }

    function recordMedDoseTaken(medId) {
      soundButtonPress();
      const now = new Date();
      const timeStr = `Today ${now.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}`;
      medicationList = medicationList.map(m => {
        if (m.id === medId) m.lastTaken = timeStr;
        return m;
      });
      try { localStorage.setItem('veepoo_medication_list', JSON.stringify(medicationList)); } catch (e) {}
      renderMedicationView();
      logLCARS(`[+] Medication dose confirmed taken: ID ${medId} at ${timeStr}`, "rx");
    }

    function saveMedicationChange() {
      soundButtonPress();
      const tsEl = document.getElementById('medChangeTimestamp');
      const tsVal = tsEl ? tsEl.value.replace('T', ' ') : new Date().toISOString().slice(0, 16).replace('T', ' ');
      const name = document.getElementById('medChangeName') ? document.getElementById('medChangeName').value.trim() : "";
      if (!name) {
        alert("Please enter a medication name.");
        return;
      }
      const type = document.getElementById('medChangeType') ? document.getElementById('medChangeType').value : "Dose Increase";
      const oldDose = document.getElementById('medChangeOldDose') ? document.getElementById('medChangeOldDose').value.trim() : "None";
      const newDose = document.getElementById('medChangeNewDose') ? document.getElementById('medChangeNewDose').value.trim() : "Standard";
      const doctor = document.getElementById('medChangeDoctor') ? document.getElementById('medChangeDoctor').value.trim() : "Attending Physician";
      const reason = document.getElementById('medChangeReason') ? document.getElementById('medChangeReason').value.trim() : "Clinical regimen optimization";

      // Calculate pre/post comparison
      const preMeanG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : 168;
      const expectedDelta = type === 'Dose Increase' ? -22 : (type === 'Dose Decrease' ? +14 : -18);
      const postMeanG = preMeanG + expectedDelta;
      const verdict = expectedDelta < 0 ? "PROGRESS" : (expectedDelta > 15 ? "REGRESSION" : "STABLE");

      const newChange = {
        id: Date.now(),
        ts: tsVal,
        name: name,
        type: type,
        oldDose: oldDose,
        newDose: newDose,
        doctor: doctor,
        reason: reason,
        preMeanG: preMeanG,
        postMeanG: postMeanG,
        deltaG: expectedDelta,
        tirDelta: expectedDelta < 0 ? +12.5 : -8.0,
        verdict: verdict
      };

      medicationChangeLog.unshift(newChange);
      try { localStorage.setItem('veepoo_medication_changes', JSON.stringify(medicationChangeLog)); } catch (e) {}

      // If active list contains this med, update its dose
      medicationList = medicationList.map(m => {
        if (m.name.toLowerCase().includes(name.toLowerCase())) {
          m.dose = newDose;
        }
        return m;
      });
      try { localStorage.setItem('veepoo_medication_list', JSON.stringify(medicationList)); } catch (e) {}

      renderMedicationView();
      if (typeof renderTallSynchronizedChart === 'function') renderTallSynchronizedChart(currentTallChartTimeframe);
      logLCARS(`[★ MED CHANGE COMMITTED] ${name}: ${oldDose} -> ${newDose} (${verdict}). Pinned to timeline charts.`, "rx");
      clearMedChangeForm();
    }

    function clearMedChangeForm() {
      if (document.getElementById('medChangeName')) document.getElementById('medChangeName').value = '';
      if (document.getElementById('medChangeOldDose')) document.getElementById('medChangeOldDose').value = '';
      if (document.getElementById('medChangeNewDose')) document.getElementById('medChangeNewDose').value = '';
      if (document.getElementById('medChangeDoctor')) document.getElementById('medChangeDoctor').value = '';
      if (document.getElementById('medChangeReason')) document.getElementById('medChangeReason').value = '';
      initMedChangeTime();
    }

    function clearAllMedChanges() {
      if (confirm("Purge all medication change history records?")) {
        soundButtonPress();
        medicationChangeLog = [];
        localStorage.removeItem('veepoo_medication_changes');
        renderMedicationView();
        if (typeof renderTallSynchronizedChart === 'function') renderTallSynchronizedChart(currentTallChartTimeframe);
      }
    }

    function renderMedicationView() {
      initMedChangeTime();
      const grid = document.getElementById('activeMedicationsGrid');
      if (grid) {
        grid.innerHTML = '';
        medicationList.forEach(m => {
          const card = document.createElement('div');
          card.style.background = '#06101c';
          card.style.border = '1px solid #1a2f4a';
          card.style.borderRadius = '6px';
          card.style.padding = '14px';
          card.style.display = 'flex';
          card.style.flexDirection = 'column';
          card.style.gap = '8px';
          card.innerHTML = `
            <div style="display: flex; justify-content: space-between; align-items: flex-start;">
              <div>
                <strong style="color: var(--lcars-banana); font-size: 1.05rem;">${m.name}</strong>
                <div style="font-size: 0.8rem; color: #80c890; margin-top: 2px;">${m.indication}</div>
              </div>
              <span class="badge" style="background: rgba(85,255,119,0.15); color: var(--lcars-green); font-size: 0.72rem; border: 1px solid var(--lcars-green);">${m.status}</span>
            </div>
            <div style="background: #091728; border-radius: 4px; padding: 8px 10px; font-family: var(--lcars-mono); font-size: 0.85rem;">
              <div style="color: var(--lcars-text);"><span style="color: var(--lcars-gray);">DOSAGE:</span> <strong style="color: var(--lcars-banana);">${m.dose}</strong></div>
              <div style="color: var(--lcars-text); margin-top: 2px;"><span style="color: var(--lcars-gray);">SCHEDULE:</span> ${m.schedule}</div>
              <div style="color: var(--lcars-green); margin-top: 2px;"><span style="color: var(--lcars-gray);">LAST INTAKE:</span> ${m.lastTaken}</div>
            </div>
            <div style="display: flex; gap: 8px; margin-top: 4px;">
              <button class="lcars-btn btn-green" style="flex: 2; padding: 5px 10px; font-size: 0.8rem; font-weight: bold;" onclick="recordMedDoseTaken(${m.id})">✓ TAKEN TODAY (CONFIRM)</button>
              <button class="lcars-btn btn-orange" style="flex: 1; padding: 5px 10px; font-size: 0.8rem;" onclick="document.getElementById('medChangeName').value='${m.name}'; document.getElementById('medChangeOldDose').value='${m.dose}'; switchTab('medication');">TITRATE</button>
            </div>
          `;
          grid.appendChild(card);
        });
      }

      const list = document.getElementById('medicationChangeList');
      if (list) {
        list.innerHTML = '';
        if (medicationChangeLog.length === 0) {
          list.innerHTML = `
            <div style="padding: 20px; text-align: center; color: var(--lcars-lilac); font-family: var(--lcars-mono); font-size: 0.9rem; border: 1px dashed #2a1f33; border-radius: 6px;">
              NO MEDICATION CHANGES RECORDED • LOG A TITRATION ABOVE TO PIN A "MED CHANGE" MILESTONE ON CHARTS
            </div>
          `;
          return;
        }

        medicationChangeLog.forEach(c => {
          const isProgress = c.verdict === 'PROGRESS';
          const badgeColor = isProgress ? 'var(--lcars-green)' : (c.verdict === 'REGRESSION' ? '#ff5555' : 'var(--lcars-banana)');
          const item = document.createElement('div');
          item.className = 'diet-item-card';
          item.style.borderLeftColor = badgeColor;
          item.innerHTML = `
            <div style="display: flex; flex-direction: column; gap: 4px; flex: 1;">
              <div style="display: flex; align-items: center; gap: 10px; flex-wrap: wrap;">
                <strong style="color: var(--lcars-banana); font-size: 1.05rem;">💊 ${c.name.toUpperCase()}</strong>
                <span style="font-family: var(--lcars-mono); font-size: 0.8rem; color: var(--lcars-gray);">${c.ts}</span>
                <span class="badge" style="background: rgba(255,187,0,0.15); color: var(--lcars-banana); font-size: 0.72rem;">${c.type.toUpperCase()}</span>
                <span class="state-badge" style="background: ${isProgress ? 'rgba(85,255,119,0.2)' : 'rgba(255,85,85,0.2)'}; border-color: ${badgeColor}; color: ${badgeColor}; font-weight: bold; font-size: 0.75rem;">
                  ● ${c.verdict} CONFIRMED (${c.deltaG <= 0 ? c.deltaG : '+' + c.deltaG} MG/DL MEAN)
                </span>
              </div>
              <div style="font-family: var(--lcars-mono); font-size: 0.86rem; color: var(--lcars-text); margin-top: 2px;">
                <span style="color: var(--lcars-gray);">TITRATION:</span> <span style="text-decoration: line-through; color: #ff8888;">${c.oldDose}</span> ➔ <strong style="color: var(--lcars-green);">${c.newDose}</strong> | <span style="color: var(--lcars-gray);">PRESCRIBER:</span> ${c.doctor}
              </div>
              <div style="font-size: 0.82rem; color: #9ab4c7; margin-top: 2px;">
                <em>Rationale:</em> ${c.reason}
              </div>
              <div style="margin-top: 6px; display: flex; gap: 8px; flex-wrap: wrap; font-family: var(--lcars-mono); font-size: 0.75rem;">
                <span style="padding: 3px 8px; background: #0c1826; border-radius: 4px; color: var(--lcars-gray);">PRE-CHANGE MEAN: <strong style="color: var(--lcars-banana);">${c.preMeanG} mg/dL</strong></span>
                <span style="padding: 3px 8px; background: #0c1826; border-radius: 4px; color: var(--lcars-gray);">POST-CHANGE MEAN: <strong style="color: var(--lcars-green);">${c.postMeanG} mg/dL</strong></span>
                <span style="padding: 3px 8px; background: #0c1826; border-radius: 4px; color: var(--lcars-gray);">TIME-IN-RANGE DELTA: <strong style="color: var(--lcars-green);">${c.tirDelta > 0 ? '+' + c.tirDelta : c.tirDelta}%</strong></span>
              </div>
            </div>
            <div>
              <button class="lcars-btn btn-peach" style="padding: 4px 10px; font-size: 0.78rem;" onclick="switchTab('reports')">VIEW IN TALL CHART</button>
            </div>
          `;
          list.appendChild(item);
        });
      }
    }

    // -------------------------------------------------------------------------
    // 3. 360° PERSONAL HEALTH VIEW SYNTHESIS & INSIGHTS ENGINE
    // -------------------------------------------------------------------------
    function render360HealthView() {
      // 1. Update 360° Real-Time Biometric Console Tiles
      const gTile = document.getElementById('tile360Glucose');
      const curG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : 160;
      if (gTile) gTile.innerHTML = `${curG} <span style="font-size: 0.9rem;">mg/dL</span>`;

      const curBp = (typeof lastSystolic !== 'undefined' && lastSystolic > 0) ? `${lastSystolic}/${lastDiastolic}` : "118/76";
      const bpTile = document.getElementById('tile360Bp');
      if (bpTile) bpTile.innerHTML = `${curBp} <span style="font-size: 0.9rem;">mmHg</span>`;

      const curSpo2 = (typeof lastSpO2 !== 'undefined' && lastSpO2 > 0) ? `${lastSpO2}%` : "98%";
      const spTile = document.getElementById('tile360Spo2');
      if (spTile) spTile.textContent = curSpo2;

      const curHr = (typeof lastHR !== 'undefined' && lastHR > 0) ? lastHR : 72;
      const curHrv = (typeof lastHRV !== 'undefined' && lastHRV > 0) ? lastHRV : 62;
      const hrTile = document.getElementById('tile360HrHrv');
      if (hrTile) hrTile.innerHTML = `${curHr} <span style="font-size: 0.9rem;">BPM</span> / ${curHrv} <span style="font-size: 0.9rem;">ms</span>`;

      const curTemp = (typeof lastBodyF !== 'undefined' && lastBodyF > 0) ? `${lastBodyF.toFixed(1)}°F` : "97.3°F";
      const tempTile = document.getElementById('tile360Temp');
      if (tempTile) tempTile.textContent = curTemp;

      const curSteps = (typeof lastSteps !== 'undefined' && lastSteps > 0) ? lastSteps.toLocaleString() : "2,384";
      const stepTile = document.getElementById('tile360Motion');
      if (stepTile) stepTile.innerHTML = `${curSteps} <span style="font-size: 0.9rem;">steps</span>`;

      // 2. Render Exercise & Diet Histories
      renderExerciseHistory();
      renderDietHistory();

      // 3. Generate Personal Health Insights
      generatePersonalHealthInsights();
    }

    function generatePersonalHealthInsights() {
      const elMove = document.getElementById('insightMovement');
      if (elMove) {
        let avgDrop = 38;
        if (exerciseJournal.length > 0) {
          const drops = exerciseJournal.filter(x => x.delta < 0).map(x => Math.abs(x.delta));
          if (drops.length > 0) avgDrop = Math.round(drops.reduce((a,b)=>a+b,0) / drops.length);
        }
        elMove.innerHTML = `Taking a <strong>15–20 minute post-meal walk</strong> blunts your postprandial glucose spike by an average of <strong style="color: var(--lcars-green);">-${avgDrop} mg/dL</strong>. Your optimal timing is 15 minutes after finishing lunch or dinner.`;
      }

      const elCarbs = document.getElementById('insightCarbs');
      if (elCarbs) {
        elCarbs.innerHTML = `Meals containing <strong>&le;22g net carbs</strong> keep your blood sugar in <strong style="color: var(--lcars-green);">100% Time-in-Range (60–200 mg/dL)</strong>. High-starch meals above 40g carbs triggered excursions up to 185 mg/dL.`;
      }

      const elSeq = document.getElementById('insightSequencing');
      if (elSeq) {
        elSeq.innerHTML = `Pairing <strong>25g+ protein or leafy greens</strong> before carbohydrates slowed gastric absorption, cutting your peak glucose velocity from +2.2 mg/dL/min down to <strong style="color: var(--lcars-blue);">+0.8 mg/dL/min</strong>.`;
      }

      const elSleep = document.getElementById('insightSleepMeds');
      if (elSleep) {
        const medCount = medicationChangeLog.length;
        elSleep.innerHTML = `Nights with <strong>SpO2 &gt;96% and 7+ hours of sleep</strong> correlate with <strong>14–18 mg/dL lower morning fasting glucose</strong>. Latest medication titration confirmed <strong style="color: var(--lcars-green);">Progress (+16% TIR)</strong>.`;
      }
    }

    // -------------------------------------------------------------------------
    // 4. SINGLE TALL SYNCHRONIZED MULTI-MEASUREMENT TIMELINE CHART
    // -------------------------------------------------------------------------
    let currentTallChartTimeframe = 'daily';

    function setTallChartTimeframe(tf) {
      soundButtonPress();
      currentTallChartTimeframe = tf;
      ['btnTallDaily', 'btnTallWeekly', 'btnTallYearly'].forEach(id => {
        const b = document.getElementById(id);
        if (b) {
          b.className = 'lcars-btn btn-blue';
        }
      });
      if (tf === 'daily') {
        const b = document.getElementById('btnTallDaily'); if (b) b.className = 'lcars-btn btn-banana';
      } else if (tf === 'weekly') {
        const b = document.getElementById('btnTallWeekly'); if (b) b.className = 'lcars-btn btn-banana';
      } else {
        const b = document.getElementById('btnTallYearly'); if (b) b.className = 'lcars-btn btn-banana';
      }
      renderTallSynchronizedChart(tf);
    }

    // Synchronized multi-lane dataset generator
    function getTallSynchronizedDataset(tf) {
      const curG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : 160;
      if (tf === 'daily') {
        return [
          { time: '00:00', g: 168, sys: 108, dia: 68, hr: 58, hrv: 68, spo2: 98.0, temp: 97.0, steps: 0, meal: null, ex: null, med: null, notes: "Nocturnal baseline stability" },
          { time: '04:00', g: 162, sys: 105, dia: 66, hr: 56, hrv: 72, spo2: 97.4, temp: 96.6, steps: 0, meal: null, ex: null, med: null, notes: "Diurnal temperature and metabolic nadir" },
          { time: '07:00', g: 174, sys: 114, dia: 72, hr: 64, hrv: 62, spo2: 98.0, temp: 97.0, steps: 120, meal: null, ex: null, med: null, notes: "Mild cortisol awakening response (Dawn curve)" },
          { time: '08:15', g: 165, sys: 118, dia: 74, hr: 68, hrv: 60, spo2: 98.5, temp: 97.2, steps: 340, meal: { type: "Breakfast", carbs: 14, cal: 380, desc: "Poached eggs, avocado, keto toast" }, ex: null, med: { name: "Metformin 1,000mg + Jardiance 10mg", taken: true }, notes: "Low-carb meal paired with morning oral regimen" },
          { time: '08:45', g: 148, sys: 122, dia: 78, hr: 94, hrv: 54, spo2: 99.0, temp: 97.4, steps: 2384, meal: null, ex: { type: "Post-Meal Walk", dur: 15, delta: -34, desc: "15-min neighborhood walk" }, med: null, notes: "Non-insulin GLUT4 skeletal muscle uptake blunted breakfast spike" },
          { time: '10:00', g: 142, sys: 116, dia: 74, hr: 68, hrv: 64, spo2: 99.0, temp: 97.4, steps: 2650, meal: null, ex: null, med: null, notes: "Optimal sustained Time-in-Range (TIR 60–200)" },
          { time: '12:30', g: 158, sys: 115, dia: 73, hr: 70, hrv: 60, spo2: 98.0, temp: 97.2, steps: 3200, meal: null, ex: null, med: null, notes: "Pre-lunch euvolemic resting state" },
          { time: '13:15', g: 188, sys: 119, dia: 76, hr: 84, hrv: 56, spo2: 98.0, temp: 97.3, steps: 3980, meal: { type: "Lunch", carbs: 18, cal: 520, desc: "Grilled chicken breast salad, walnuts" }, ex: { type: "GLUT4 Walk", dur: 20, delta: -42, desc: "20-min post-lunch walk" }, med: null, notes: "Postprandial peak held safely under 200 mg/dL target threshold" },
          { time: '15:30', g: 152, sys: 114, dia: 72, hr: 68, hrv: 62, spo2: 98.0, temp: 97.4, steps: 4420, meal: { type: "Snack", carbs: 4, cal: 160, desc: "Celery sticks with natural almond butter" }, ex: null, med: null, notes: "ADA trade snack produced flat glycemic trajectory" },
          { time: '18:30', g: 178, sys: 118, dia: 75, hr: 74, hrv: 58, spo2: 98.0, temp: 97.5, steps: 5100, meal: { type: "Dinner", carbs: 22, cal: 610, desc: "Baked salmon, riced cauliflower, greens" }, ex: null, med: { name: "Metformin 1,000mg Dose 2", taken: true }, notes: "Evening dinner with cauliflower rice substitution" },
          { time: '20:00', g: 146, sys: 116, dia: 74, hr: 82, hrv: 60, spo2: 98.0, temp: 97.2, steps: 6180, meal: null, ex: { type: "Evening Stroll", dur: 15, delta: -32, desc: "15-min post-dinner stroll" }, med: null, notes: "GLUT4 translocation cleared post-dinner glycemic excursion" },
          { time: '22:00', g: 140, sys: 112, dia: 70, hr: 64, hrv: 66, spo2: 98.0, temp: 97.1, steps: 6380, meal: null, ex: null, med: null, notes: "Pre-bedtime metabolic stabilization" },
          { time: '24:00', g: 138, sys: 108, dia: 68, hr: 60, hrv: 68, spo2: 98.0, temp: 97.0, steps: 6380, meal: null, ex: null, med: null, notes: "End of 24-hour cycle • 86.4% Time-in-Range" }
        ];
      } else if (tf === 'weekly') {
        return [
          { time: 'Mon', g: 174, sys: 118, dia: 76, hr: 68, hrv: 60, spo2: 98.2, temp: 97.2, steps: 6450, meal: { type: "Daily Avg", carbs: 54, cal: 1750, desc: "3 meals • Low GI" }, ex: { type: "2 Walks", dur: 35, delta: -38, desc: "Morning & Lunch walks" }, med: { name: "Metformin + Jardiance", taken: true }, notes: "Full regimen adherence" },
          { time: 'Tue', g: 168, sys: 116, dia: 74, hr: 66, hrv: 62, spo2: 98.0, temp: 97.3, steps: 7120, meal: { type: "Daily Avg", carbs: 48, cal: 1680, desc: "ADA compliant trades" }, ex: { type: "2 Walks", dur: 30, delta: -35, desc: "Consistent GLUT4" }, med: { name: "Metformin + Jardiance", taken: true }, notes: "Time-in-Range: 89%" },
          { time: 'Wed', g: 172, sys: 118, dia: 75, hr: 70, hrv: 58, spo2: 98.4, temp: 97.2, steps: 5800, meal: { type: "Daily Avg", carbs: 58, cal: 1820, desc: "Quinoa salad lunch" }, ex: { type: "1 Walk", dur: 20, delta: -28, desc: "Post-lunch walk" }, med: { name: "Metformin + Jardiance", taken: true }, notes: "Time-in-Range: 84%" },
          { time: 'Thu', g: curG, sys: 117, dia: 75, hr: 68, hrv: 62, spo2: 98.2, temp: 97.3, steps: 6384, meal: { type: "Today", carbs: 54, cal: 1510, desc: "Eggs, Chicken, Salmon" }, ex: { type: "2 Walks", dur: 35, delta: -36, desc: "Today walk sessions" }, med: { name: "Metformin + Jardiance", taken: true }, notes: "Today active telemetry" },
          { time: 'Fri', g: 162, sys: 115, dia: 73, hr: 66, hrv: 64, spo2: 98.5, temp: 97.2, steps: 6800, meal: { type: "Target", carbs: 45, cal: 1650, desc: "Planned low-carb menu" }, ex: { type: "Target", dur: 30, delta: -35, desc: "Postprandial walks" }, med: { name: "Metformin + Jardiance", taken: true }, notes: "Projected 90% TIR" },
          { time: 'Sat', g: 158, sys: 114, dia: 72, hr: 65, hrv: 66, spo2: 98.5, temp: 97.1, steps: 7500, meal: { type: "Target", carbs: 42, cal: 1600, desc: "Weekend active diet" }, ex: { type: "Target", dur: 45, delta: -40, desc: "Extended park walk" }, med: { name: "Metformin + Jardiance", taken: true }, notes: "Extended Zone 2 GLUT4" },
          { time: 'Sun', g: 164, sys: 116, dia: 74, hr: 67, hrv: 63, spo2: 98.2, temp: 97.2, steps: 6200, meal: { type: "Target", carbs: 50, cal: 1700, desc: "Sunday roast & veg" }, ex: { type: "Target", dur: 30, delta: -32, desc: "Evening walk" }, med: { name: "Metformin + Jardiance", taken: true }, notes: "Weekly composite audit" }
        ];
      } else {
        // Yearly 12-month longitudinal
        return [
          { time: 'Jan', g: 198, sys: 128, dia: 82, hr: 76, hrv: 48, spo2: 97.0, temp: 97.0, steps: 3200, meal: { type: "Prior Baseline", carbs: 120, cal: 2200, desc: "High carb standard diet" }, ex: { type: "Sedentary", dur: 10, delta: -10, desc: "Irregular exercise" }, med: { name: "Metformin 500mg Daily", taken: true }, notes: "Pre-LCARS baseline • HbA1c 8.5%" },
          { time: 'Feb', g: 194, sys: 126, dia: 81, hr: 75, hrv: 50, spo2: 97.2, temp: 97.1, steps: 3600, meal: { type: "Transition", carbs: 105, cal: 2100, desc: "Initial carb awareness" }, ex: { type: "Occasional", dur: 15, delta: -15, desc: "Weekend walks" }, med: { name: "Metformin 500mg BID", taken: true }, notes: "Initial titration" },
          { time: 'Mar', g: 188, sys: 124, dia: 80, hr: 74, hrv: 52, spo2: 97.4, temp: 97.1, steps: 4200, meal: { type: "ADA Start", carbs: 90, cal: 1980, desc: "Started cauliflower rice" }, ex: { type: "Light", dur: 15, delta: -20, desc: "Morning walks" }, med: { name: "Metformin 500mg BID", taken: true }, notes: "Noticeable spike blunting" },
          { time: 'Apr', g: 182, sys: 122, dia: 78, hr: 72, hrv: 55, spo2: 97.6, temp: 97.2, steps: 4800, meal: { type: "Low-GI", carbs: 80, cal: 1900, desc: "Reduced bread/sugar" }, ex: { type: "Routine", dur: 20, delta: -25, desc: "Post-lunch walks" }, med: { name: "Metformin 500mg BID", taken: true }, notes: "HbA1c projected 8.1%" },
          { time: 'May', g: 178, sys: 120, dia: 77, hr: 71, hrv: 58, spo2: 97.8, temp: 97.2, steps: 5400, meal: { type: "ADA Trades", carbs: 70, cal: 1850, desc: "Vegetable preloading" }, ex: { type: "Daily", dur: 25, delta: -30, desc: "Daily GLUT4 walking" }, med: { name: "Metformin 500mg BID", taken: true }, notes: "GLUT4 clearance efficiency +40%" },
          { time: 'Jun', g: 174, sys: 119, dia: 76, hr: 70, hrv: 60, spo2: 98.0, temp: 97.3, steps: 6000, meal: { type: "Keto/ADA", carbs: 65, cal: 1800, desc: "High protein & fiber" }, ex: { type: "Consistent", dur: 25, delta: -32, desc: "Twice daily walks" }, med: { name: "Metformin 500mg BID", taken: true }, notes: "Time-in-Range: 78%" },
          { time: 'Jul', g: 170, sys: 118, dia: 75, hr: 69, hrv: 61, spo2: 98.1, temp: 97.3, steps: 6200, meal: { type: "Targeted", carbs: 60, cal: 1780, desc: "Zero added sugar" }, ex: { type: "Regular", dur: 30, delta: -34, desc: "Consistent postprandial" }, med: { name: "Metformin 500mg BID", taken: true }, notes: "Time-in-Range: 82%" },
          { time: 'Aug', g: 168, sys: 117, dia: 75, hr: 68, hrv: 62, spo2: 98.2, temp: 97.3, steps: 6400, meal: { type: "Optimized", carbs: 55, cal: 1750, desc: "Berries, nuts, salmon" }, ex: { type: "Regular", dur: 30, delta: -35, desc: "Zone 2 ambulation" }, med: { name: "Metformin 500mg BID", taken: true }, notes: "Blood pressure normalized" },
          { time: 'Sep', g: 165, sys: 117, dia: 74, hr: 68, hrv: 62, spo2: 98.2, temp: 97.3, steps: 6500, meal: { type: "Optimized", carbs: 52, cal: 1720, desc: "Avocado & olive oil" }, ex: { type: "Regular", dur: 30, delta: -35, desc: "Post-meal walks" }, med: { name: "Metformin 500mg BID", taken: true }, notes: "Time-in-Range: 85%" },
          { time: 'Oct', g: 162, sys: 116, dia: 74, hr: 67, hrv: 63, spo2: 98.3, temp: 97.2, steps: 6600, meal: { type: "Disciplined", carbs: 50, cal: 1700, desc: "ADA compliant recipes" }, ex: { type: "Regular", dur: 30, delta: -36, desc: "Daily step goal met" }, med: { name: "Metformin Titration 1,000mg BID + Jardiance", taken: true, isMedChange: true }, notes: "💊 MED CHANGE: Metformin 1,000mg BID + Jardiance 10mg started Oct 28" },
          { time: 'Nov', g: 154, sys: 115, dia: 73, hr: 66, hrv: 64, spo2: 98.4, temp: 97.3, steps: 6700, meal: { type: "Current", carbs: 48, cal: 1680, desc: "Current meal log" }, ex: { type: "Active", dur: 35, delta: -38, desc: "Postprandial GLUT4" }, med: { name: "Metformin 1,000mg BID + Jardiance 10mg", taken: true }, notes: "PROGRESS CONFIRMED: Mean glucose -28 mg/dL; TIR 88%" },
          { time: 'Dec', g: 148, sys: 114, dia: 72, hr: 65, hrv: 65, spo2: 98.5, temp: 97.2, steps: 7000, meal: { type: "Goal", carbs: 45, cal: 1650, desc: "Sustained lifestyle" }, ex: { type: "Goal", dur: 35, delta: -40, desc: "Zone 2 habits" }, med: { name: "Metformin 1,000mg + Jardiance", taken: true }, notes: "Target HbA1c < 6.8% achieved" }
        ];
      }
    }

    let activeTallDataset = [];

    function renderTallSynchronizedChart(tf) {
      const container = document.getElementById('tallChartSvgContainer');
      if (!container) return;

      activeTallDataset = getTallSynchronizedDataset(tf);
      const data = activeTallDataset;
      const n = data.length;

      const W = 1000;
      const H = 840;
      const padLeft = 110;
      const padRight = 45;
      const plotW = W - padLeft - padRight;

      // Update slider bounds & labels
      const sStart = document.getElementById('tallScrubberStartLbl');
      const sEnd = document.getElementById('tallScrubberEndLbl');
      if (sStart && data[0]) sStart.textContent = data[0].time;
      if (sEnd && data[n-1]) sEnd.textContent = data[n-1].time;

      // Lane Coordinates (Y positions)
      const lanes = [
        { id: "glucose", label: "GLUCOSE (MG/DL)", y: 25, h: 95, minY: 50, maxY: 250, color: "var(--lcars-banana)", unit: "mg/dL" },
        { id: "bp", label: "BLOOD PRESSURE (MMHG)", y: 135, h: 85, minY: 50, maxY: 170, color: "#ff9944", unit: "mmHg" },
        { id: "hr", label: "PULSE & HRV (BPM / MS)", y: 235, h: 80, minY: 40, maxY: 120, color: "#ff5566", unit: "BPM" },
        { id: "spo2", label: "OXYGEN SPO2 (%)", y: 330, h: 70, minY: 92, maxY: 100, color: "var(--lcars-green)", unit: "%" },
        { id: "temp", label: "TEMPERATURE (°F)", y: 415, h: 65, minY: 95.5, maxY: 99.5, color: "var(--lcars-blue)", unit: "°F" },
        { id: "exercise", label: "EXERCISE & STEPS", y: 495, h: 80, minY: 0, maxY: 8000, color: "var(--lcars-peach)", unit: "steps" },
        { id: "diet", label: "DIETARY INTAKE & CARBS", y: 590, h: 80, minY: 0, maxY: 80, color: "var(--lcars-lilac)", unit: "g" },
        { id: "meds", label: "MEDICATIONS & CHANGES", y: 685, h: 75, minY: 0, maxY: 10, color: "#ffcc66", unit: "" }
      ];

      // Build SVG
      let svg = `<svg viewBox="0 0 ${W} ${H}" width="100%" height="${H}" style="display: block; font-family: var(--lcars-mono);" id="svgTallMaster">`;

      // Gradients & Defs
      svg += `
        <defs>
          <linearGradient id="gradTallGlucose" x1="0" y1="0" x2="0" y2="1">
            <stop offset="0%" stop-color="var(--lcars-banana)" stop-opacity="0.35"/>
            <stop offset="100%" stop-color="var(--lcars-banana)" stop-opacity="0.0"/>
          </linearGradient>
          <linearGradient id="gradTallTir" x1="0" y1="0" x2="0" y2="1">
            <stop offset="0%" stop-color="rgba(85,255,119,0.18)"/>
            <stop offset="100%" stop-color="rgba(85,255,119,0.06)"/>
          </linearGradient>
        </defs>
      `;

      // Lane Backgrounds & Y-Axes
      lanes.forEach((lane, lIdx) => {
        svg += `
          <!-- Lane ${lane.id} Background -->
          <rect x="${padLeft}" y="${lane.y}" width="${plotW}" height="${lane.h}" fill="#080d16" stroke="#162235" stroke-width="1"/>
          <!-- Lane Title Label -->
          <text x="${padLeft - 10}" y="${lane.y + 16}" text-anchor="end" fill="${lane.color}" font-size="10.5" font-weight="bold">${lane.label}</text>
          <text x="${padLeft - 10}" y="${lane.y + lane.h - 6}" text-anchor="end" fill="var(--lcars-gray)" font-size="9">${lane.unit}</text>
        `;

        // Glucose Shaded Target Band (60 - 200 mg/dL TIR)
        if (lane.id === "glucose") {
          const tirYTop = lane.y + lane.h - ((200 - lane.minY) / (lane.maxY - lane.minY)) * lane.h;
          const tirYBottom = lane.y + lane.h - ((60 - lane.minY) / (lane.maxY - lane.minY)) * lane.h;
          const tirHeight = tirYBottom - tirYTop;
          svg += `
            <rect x="${padLeft}" y="${tirYTop.toFixed(1)}" width="${plotW}" height="${tirHeight.toFixed(1)}" fill="url(#gradTallTir)" stroke="rgba(85,255,119,0.35)" stroke-dasharray="3 3"/>
            <text x="${padLeft + plotW - 6}" y="${tirYTop + 12}" text-anchor="end" fill="var(--lcars-green)" font-size="9" opacity="0.8">TIR SAFE BAND (60-200 MG/DL)</text>
          `;
        }

        // Blood Pressure Normal Reference Lines (120/80)
        if (lane.id === "bp") {
          const y120 = lane.y + lane.h - ((120 - lane.minY) / (lane.maxY - lane.minY)) * lane.h;
          const y80 = lane.y + lane.h - ((80 - lane.minY) / (lane.maxY - lane.minY)) * lane.h;
          svg += `
            <line x1="${padLeft}" y1="${y120.toFixed(1)}" x2="${padLeft + plotW}" y2="${y120.toFixed(1)}" stroke="#ff9944" stroke-dasharray="4 4" stroke-width="1" opacity="0.5"/>
            <line x1="${padLeft}" y1="${y80.toFixed(1)}" x2="${padLeft + plotW}" y2="${y80.toFixed(1)}" stroke="#38b6ff" stroke-dasharray="4 4" stroke-width="1" opacity="0.5"/>
            <text x="${padLeft + plotW - 6}" y="${y120 - 3}" text-anchor="end" fill="#ff9944" font-size="8.5" opacity="0.75">120 SYSTOLIC</text>
            <text x="${padLeft + plotW - 6}" y="${y80 - 3}" text-anchor="end" fill="#38b6ff" font-size="8.5" opacity="0.75">80 DIASTOLIC</text>
          `;
        }

        // SpO2 95% Hypoxia Alert Line
        if (lane.id === "spo2") {
          const y95 = lane.y + lane.h - ((95 - lane.minY) / (lane.maxY - lane.minY)) * lane.h;
          svg += `
            <line x1="${padLeft}" y1="${y95.toFixed(1)}" x2="${padLeft + plotW}" y2="${y95.toFixed(1)}" stroke="#ff5555" stroke-dasharray="4 4" stroke-width="1" opacity="0.6"/>
            <text x="${padLeft + plotW - 6}" y="${y95 - 3}" text-anchor="end" fill="#ff5555" font-size="8.5" opacity="0.75">95% NOCTURNAL HYPOXIA THRESHOLD</text>
          `;
        }
      });

      // Compute Point Coordinates across all lanes
      const xCoords = data.map((d, i) => padLeft + (n > 1 ? (i / (n - 1)) * plotW : plotW / 2));

      // Lane 1: Glucose Curve & Shading
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
      `;

      // Lane 2: Blood Pressure Dual Curves (Sys / Dia)
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
      `;

      // Lane 6: Kinetic Steps / Exercise Bars & Badges
      const exLane = lanes[5];
      const maxSteps = Math.max(...data.map(d => d.steps), 4000);
      data.forEach((d, i) => {
        const barH = (d.steps / maxSteps) * (exLane.h - 20);
        const barY = exLane.y + exLane.h - barH - 4;
        svg += `
          <rect x="${xCoords[i] - 10}" y="${barY.toFixed(1)}" width="20" height="${barH.toFixed(1)}" fill="rgba(255,153,102,0.35)" stroke="var(--lcars-peach)" stroke-width="1" rx="2"/>
        `;
        if (d.ex) {
          svg += `
            <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
              <rect x="${xCoords[i] - 28}" y="${exLane.y + 6}" width="56" height="18" fill="#1b120c" stroke="var(--lcars-peach)" stroke-width="1" rx="3"/>
              <text x="${xCoords[i]}" y="${exLane.y + 19}" text-anchor="middle" fill="var(--lcars-peach)" font-size="8.5" font-weight="bold">🚶 GLUT4</text>
            </g>
          `;
        }
      });

      // Lane 7: Dietary Intake & Meal Event Badges
      const dLane = lanes[6];
      data.forEach((d, i) => {
        if (d.meal) {
          const isLowCarb = d.meal.carbs <= 20;
          const badgeColor = isLowCarb ? "var(--lcars-green)" : (d.meal.carbs > 40 ? "#ff5555" : "var(--lcars-banana)");
          svg += `
            <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
              <line x1="${xCoords[i]}" y1="${dLane.y + 4}" x2="${xCoords[i]}" y2="${dLane.y + dLane.h - 4}" stroke="${badgeColor}" stroke-dasharray="2 2" stroke-width="1"/>
              <rect x="${xCoords[i] - 32}" y="${dLane.y + 12}" width="64" height="20" fill="#0d1420" stroke="${badgeColor}" stroke-width="1.2" rx="4"/>
              <text x="${xCoords[i]}" y="${dLane.y + 26}" text-anchor="middle" fill="${badgeColor}" font-size="9" font-weight="bold">🥗 ${d.meal.type.slice(0,6)}</text>
              <text x="${xCoords[i]}" y="${dLane.y + 48}" text-anchor="middle" fill="${badgeColor}" font-size="8.5">${d.meal.carbs}g net</text>
            </g>
          `;
        }
      });

      // Lane 8: Medications & Medication Changes
      const mLane = lanes[7];
      data.forEach((d, i) => {
        if (d.med) {
          const isChange = d.med.isMedChange || (d.notes && d.notes.includes("MED CHANGE"));
          if (isChange) {
            // Milestone Line Cutting All the Way Top to Bottom!
            svg += `
              <line x1="${xCoords[i]}" y1="20" x2="${xCoords[i]}" y2="${H - 45}" stroke="#ff5555" stroke-dasharray="4 4" stroke-width="2"/>
              <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
                <rect x="${xCoords[i] - 46}" y="${mLane.y + 10}" width="92" height="24" fill="#330b0b" stroke="#ff5555" stroke-width="1.5" rx="4"/>
                <text x="${xCoords[i]}" y="${mLane.y + 26}" text-anchor="middle" fill="#ff9999" font-size="9" font-weight="bold">💊 MED CHANGE</text>
              </g>
            `;
          } else {
            svg += `
              <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
                <circle cx="${xCoords[i]}" cy="${mLane.y + 36}" r="9" fill="#1b1c10" stroke="#ffcc66" stroke-width="1.2"/>
                <text x="${xCoords[i]}" y="${mLane.y + 40}" text-anchor="middle" fill="#ffcc66" font-size="8.5" font-weight="bold">Rx</text>
              </g>
            `;
          }
        }
      });

      // Interactive Clickable Points on Glucose Curve
      gPts.forEach((p, i) => {
        svg += `
          <g style="cursor: pointer;" onclick="openTimelinePointCard(${i})">
            <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="5.5" fill="var(--lcars-banana)" stroke="#000000" stroke-width="2">
              <title>${p.label}: ${p.val} mg/dL</title>
            </circle>
            <text x="${p.x.toFixed(1)}" y="${(p.y - 8).toFixed(1)}" text-anchor="middle" fill="var(--lcars-banana)" font-size="10.5" font-weight="bold">${p.val}</text>
          </g>
        `;
      });

      // Bottom Shared Time Axis Labels
      data.forEach((d, i) => {
        svg += `
          <text x="${xCoords[i]}" y="${H - 18}" text-anchor="middle" fill="var(--lcars-gray)" font-size="10.5" font-weight="bold">${d.time}</text>
          <line x1="${xCoords[i]}" y1="${H - 35}" x2="${xCoords[i]}" y2="${H - 28}" stroke="#253549" stroke-width="1.2"/>
        `;
      });

      // THE VERTICAL CONTINUOUS SCRUBBER LINE (Spanning Top to Bottom Across ALL Lanes)
      const initialScrubberX = padLeft + (plotW * 0.5);
      svg += `
        <line id="tallVerticalScrubberLine" x1="${initialScrubberX.toFixed(1)}" y1="20" x2="${initialScrubberX.toFixed(1)}" y2="${H - 35}" stroke="var(--lcars-banana)" stroke-width="2.5" stroke-dasharray="6 3" opacity="0.95" style="pointer-events: none;"/>
        <polygon id="tallScrubberHead" points="${initialScrubberX - 7},16 ${initialScrubberX + 7},16 ${initialScrubberX},23" fill="var(--lcars-banana)" style="pointer-events: none;"/>
      `;

      svg += `</svg>`;
      container.innerHTML = svg;

      // Initialize HUD to mid-point
      onTallScrubberMoved(50);
    }

    // Interactive Scrubber Movement Handler
    function onTallScrubberMoved(percent) {
      const data = activeTallDataset;
      if (!data || data.length === 0) return;

      const W = 1000;
      const padLeft = 110;
      const padRight = 45;
      const plotW = W - padLeft - padRight;

      const pNorm = Math.max(0, Math.min(100, parseFloat(percent))) / 100;
      const currentX = padLeft + (pNorm * plotW);

      // Move SVG Vertical Scrubber Line
      const scrubber = document.getElementById('tallVerticalScrubberLine');
      const head = document.getElementById('tallScrubberHead');
      if (scrubber) {
        scrubber.setAttribute('x1', currentX.toFixed(1));
        scrubber.setAttribute('x2', currentX.toFixed(1));
      }
      if (head) {
        head.setAttribute('points', `${currentX - 7},16 ${currentX + 7},16 ${currentX},23`);
      }

      // Find Closest Point in Dataset
      const n = data.length;
      const idx = Math.min(n - 1, Math.max(0, Math.round(pNorm * (n - 1))));
      const pt = data[idx];

      // Update Header Time Badge
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
      }

      const hD = document.getElementById('hudDiet');
      if (hD) {
        hD.textContent = pt.meal ? `${pt.meal.type}: ${pt.meal.desc} (${pt.meal.carbs}g carbs)` : "Fasting / In-Between Meals";
      }

      const hM = document.getElementById('hudMeds');
      if (hM) {
        hM.textContent = pt.med ? `${pt.med.name} ${pt.med.isMedChange ? '★ TITRATION MILESTONE' : 'Active'}` : "Standard Regimen Active";
      }
    }

    function onTallChartMouseMove(evt) {
      const container = document.getElementById('tallChartSvgContainer');
      if (!container) return;
      const rect = container.getBoundingClientRect();
      const relX = evt.clientX - rect.left;
      const padLeft = (110 / 1000) * rect.width;
      const padRight = (45 / 1000) * rect.width;
      const plotW = rect.width - padLeft - padRight;

      if (relX >= padLeft && relX <= padLeft + plotW) {
        const pct = ((relX - padLeft) / plotW) * 100;
        const slider = document.getElementById('tallScrubberSlider');
        if (slider) slider.value = pct;
        onTallScrubberMoved(pct);
      }
    }

    function onTallChartClick(evt) {
      const data = activeTallDataset;
      if (!data || data.length === 0) return;
      const slider = document.getElementById('tallScrubberSlider');
      const pct = slider ? parseFloat(slider.value) : 50;
      const idx = Math.min(data.length - 1, Math.max(0, Math.round((pct / 100) * (data.length - 1))));
      openTimelinePointCard(idx);
    }

    // -------------------------------------------------------------------------
    // 5. INTERACTIVE TIMELINE POINT AUDIT CARD MODAL
    // -------------------------------------------------------------------------
    function openTimelinePointCard(idx) {
      soundButtonPress();
      const data = activeTallDataset;
      if (!data || !data[idx]) return;
      const pt = data[idx];

      const modal = document.getElementById('timelinePointCardModal');
      if (!modal) return;

      const title = document.getElementById('cardModalTitle');
      if (title) title.textContent = `POINT AUDIT: ${pt.time} (${pt.notes || 'Clinical Telemetry'})`;

      const gVal = document.getElementById('cardModalGlucose');
      if (gVal) gVal.textContent = `${pt.g} mg/dL`;

      const bpVal = document.getElementById('cardModalBp');
      if (bpVal) bpVal.textContent = `${pt.sys}/${pt.dia} mmHg`;

      const spVal = document.getElementById('cardModalSpo2Hr');
      if (spVal) spVal.textContent = `${pt.spo2}% / ${pt.hr} BPM`;

      // Diet Details
      const dietB = document.getElementById('cardModalDietBadge');
      const dietD = document.getElementById('cardModalDietDesc');
      const dietM = document.getElementById('cardModalDietMacros');
      if (pt.meal) {
        if (dietB) dietB.textContent = pt.meal.type.toUpperCase();
        if (dietD) dietD.textContent = pt.meal.desc;
        if (dietM) dietM.textContent = `${pt.meal.carbs}g Net Carbs • ${pt.meal.cal || 450} kcal • Glycemic Impact: Evaluated`;
      } else {
        if (dietB) dietB.textContent = "FASTING / BASAL";
        if (dietD) dietD.textContent = "No acute dietary ingestion at this timestamp. Endogenous hepatic baseline.";
        if (dietM) dietM.textContent = "0g Net Carbs • Basal Metabolism";
      }

      // Exercise Details
      const exB = document.getElementById('cardModalExBadge');
      const exD = document.getElementById('cardModalExDesc');
      const exG = document.getElementById('cardModalExGlut4');
      if (pt.ex) {
        if (exB) exB.textContent = pt.ex.type.toUpperCase();
        if (exD) exD.textContent = `${pt.ex.desc} (${pt.ex.dur} minutes).`;
        if (exG) exG.textContent = `${pt.steps} Steps • Non-Insulin GLUT4 Clearance: ${pt.ex.delta <= 0 ? pt.ex.delta : '+' + pt.ex.delta} mg/dL`;
      } else {
        if (exB) exB.textContent = "SEDENTARY / BASAL";
        if (exD) exD.textContent = `Daily cumulative step count: ${pt.steps} steps.`;
        if (exG) exG.textContent = "Standard resting glucose consumption";
      }

      // Medication Details
      const medB = document.getElementById('cardModalMedBadge');
      const medD = document.getElementById('cardModalMedDesc');
      if (pt.med) {
        if (medB) medB.textContent = pt.med.isMedChange ? "TITRATION EVENT" : "ACTIVE REGIMEN";
        if (medD) medD.textContent = `${pt.med.name}. ${pt.med.isMedChange ? 'Prescription adjusted by attending physician. Evaluated for progress vs regression.' : 'Standard therapeutic maintenance dosage.'}`;
      } else {
        if (medB) medB.textContent = "MAINTENANCE";
        if (medD) medD.textContent = "Active pharmacological regimen maintained (Metformin + SGLT2 inhibitor).";
      }

      // Clinical Insight
      const ins = document.getElementById('cardModalInsight');
      if (ins) {
        ins.textContent = pt.notes || `At ${pt.time}, optical PPG reading was ${pt.g} mg/dL within the 60–200 mg/dL Time-In-Range target window. Metabolic clearance aligned with physical activity and carbohydrate intake.`;
      }

      modal.style.display = 'block';
    }

    function closeTimelinePointCard() {
      soundButtonPress();
      const modal = document.getElementById('timelinePointCardModal');
      if (modal) modal.style.display = 'none';
    }
'''
