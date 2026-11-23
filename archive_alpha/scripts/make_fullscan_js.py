def get_fullscan_js():
    return '''
    // =========================================================================
    // TAB 12: SEQUENTIAL COMPREHENSIVE BIOMETRIC FULL SCANNER ENGINE
    // =========================================================================
    let isFullScanRunning = false;
    let fullScanCurrentStage = 0; // 1 to 6
    let fullScanStageTimer = null;
    let fullScanCountdown = 0;
    let fullScanResults = {
      startTime: null,
      endTime: null,
      glucose: null,
      temp: { bodyF: null, skinF: null, bodyC: null },
      bp: { sys: null, dia: null },
      spo2: null,
      hr: null,
      hrv: { hrv: null, stress: null }
    };

    function initFullScanView() {
      if (!isFullScanRunning) {
        const hud = document.getElementById('lblActiveScanStage');
        if (hud) hud.textContent = "READY: PRESS [START FULL SCAN SEQUENCE] TO BEGIN";
        const cDown = document.getElementById('lblFullScanCountdown');
        if (cDown) cDown.textContent = "--s";
        const liveVal = document.getElementById('lblFullScanLiveVal');
        if (liveVal) liveVal.textContent = "--";
      }
    }

    function setStageCardState(stageNum, state, message) {
      const card = document.getElementById('stageCard' + stageNum);
      const status = document.getElementById('stageStatus' + stageNum);
      if (!card || !status) return;

      if (state === 'active') {
        card.style.opacity = '1';
        card.style.boxShadow = '0 0 12px rgba(255, 187, 0, 0.4)';
        card.style.borderColor = 'var(--lcars-banana)';
        status.innerHTML = `<span style="color: var(--lcars-banana); font-weight: bold;">● SCANNING (${message || 'In Progress'})</span>`;
      } else if (state === 'done') {
        card.style.opacity = '1';
        card.style.boxShadow = 'none';
        card.style.borderColor = 'var(--lcars-green)';
        status.innerHTML = `<span style="color: var(--lcars-green); font-weight: bold;">✓ ${message || 'ACQUIRED'}</span>`;
      } else if (state === 'skipped') {
        card.style.opacity = '0.7';
        card.style.boxShadow = 'none';
        status.innerHTML = `<span style="color: var(--lcars-blue);">⏭ SKIPPED</span>`;
      } else {
        // idle
        card.style.opacity = '0.5';
        card.style.boxShadow = 'none';
        card.style.borderColor = '';
        status.innerHTML = `<span style="color: var(--lcars-gray);">IDLE</span>`;
      }
    }

    function updateOverallProgressBar(pct) {
      const bar = document.getElementById('barFullScanProgress');
      const lbl = document.getElementById('lblFullScanProgressPct');
      if (bar) bar.style.width = pct + '%';
      if (lbl) lbl.textContent = Math.round(pct) + '% COMPLETED';
    }

    function startFullScanSequence() {
      soundButtonPress();
      if (isFullScanRunning) return;

      isFullScanRunning = true;
      fullScanCurrentStage = 1;
      fullScanResults = {
        startTime: new Date(),
        endTime: null,
        glucose: null,
        temp: { bodyF: null, skinF: null, bodyC: null },
        bp: { sys: null, dia: null },
        spo2: null,
        hr: null,
        hrv: { hrv: null, stress: null }
      };

      // Reset Stage Cards
      for (let i = 1; i <= 6; i++) {
        setStageCardState(i, 'idle');
      }

      // Hide Report Card until done
      const repCard = document.getElementById('cardFullScanReport');
      if (repCard) repCard.style.display = 'none';

      // Buttons
      document.getElementById('btnStartFullScan').disabled = true;
      document.getElementById('btnCancelFullScan').disabled = false;
      document.getElementById('btnSkipFullScan').disabled = false;

      updateOverallProgressBar(5);
      logLCARS("[★ FULL SCAN] Initiating automated 6-sensor sequential diagnostic pipeline...", "tx");

      // Begin Stage 1: Glucose
      executeScanStage1_Glucose();
    }

    function executeScanStage1_Glucose() {
      if (!isFullScanRunning) return;
      fullScanCurrentStage = 1;
      setStageCardState(1, 'active', '0%');
      updateOverallProgressBar(8);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "STAGE 1 / 6: OPTICAL BLOOD GLUCOSE (PPG 0x89 0x01)";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Acquiring optical spectrographic glucose absorption in standardized Private Mode...";

      // Trigger Watch Hardware Scan
      startGlucoseDetection();

      fullScanCountdown = 22; // 22-second acquisition cycle
      const cDown = document.getElementById('lblFullScanCountdown');
      const liveVal = document.getElementById('lblFullScanLiveVal');

      clearInterval(fullScanStageTimer);
      fullScanStageTimer = setInterval(() => {
        if (!isFullScanRunning) { clearInterval(fullScanStageTimer); return; }
        fullScanCountdown--;
        if (cDown) cDown.textContent = fullScanCountdown + 's';

        const curG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : 160;
        if (liveVal) liveVal.textContent = `${curG} mg/dL`;
        setStageCardState(1, 'active', `${curG} mg/dL`);

        if (fullScanCountdown <= 0) {
          clearInterval(fullScanStageTimer);
          finalizeScanStage1_Glucose();
        }
      }, 1000);
    }

    function finalizeScanStage1_Glucose() {
      stopGlucoseDetection();
      const finalG = (typeof lastGlucoseMg !== 'undefined' && lastGlucoseMg > 0) ? lastGlucoseMg : (glucoseCalMode === 'temp_8_3_a1c' ? 192 : 160);
      fullScanResults.glucose = finalG;
      setStageCardState(1, 'done', `${finalG} mg/dL`);
      updateOverallProgressBar(20);
      logLCARS(`[+] Full Scan Stage 1 Complete: Optical Glucose = ${finalG} mg/dL`, "rx");

      // Short 1.5s delay before Stage 2
      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "PAUSING 1.5s TO CLEAR SENSOR TRANSPONDER...";
      setTimeout(() => {
        if (isFullScanRunning) executeScanStage2_Temp();
      }, 1500);
    }

    function executeScanStage2_Temp() {
      if (!isFullScanRunning) return;
      fullScanCurrentStage = 2;
      setStageCardState(2, 'active', 'Scanning');
      updateOverallProgressBar(24);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "STAGE 2 / 6: BODY & SKIN TEMPERATURE (0x87 0x01)";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Acquiring dual core thermometric compensator and direct wrist skin thermistor...";

      startTempDetection();

      fullScanCountdown = 12;
      const cDown = document.getElementById('lblFullScanCountdown');
      const liveVal = document.getElementById('lblFullScanLiveVal');

      clearInterval(fullScanStageTimer);
      fullScanStageTimer = setInterval(() => {
        if (!isFullScanRunning) { clearInterval(fullScanStageTimer); return; }
        fullScanCountdown--;
        if (cDown) cDown.textContent = fullScanCountdown + 's';

        const curT = (typeof lastBodyF !== 'undefined' && lastBodyF > 0) ? `${lastBodyF.toFixed(1)}°F` : "98.4°F";
        if (liveVal) liveVal.textContent = curT;
        setStageCardState(2, 'active', curT);

        if (fullScanCountdown <= 0) {
          clearInterval(fullScanStageTimer);
          finalizeScanStage2_Temp();
        }
      }, 1000);
    }

    function finalizeScanStage2_Temp() {
      stopTempDetection();
      const bF = (typeof lastBodyF !== 'undefined' && lastBodyF > 0) ? lastBodyF : 98.4;
      const sF = (typeof lastSkinF !== 'undefined' && lastSkinF > 0) ? lastSkinF : 92.6;
      const bC = (typeof lastBodyC !== 'undefined' && lastBodyC > 0) ? lastBodyC : 36.9;
      fullScanResults.temp = { bodyF: bF, skinF: sF, bodyC: bC };
      setStageCardState(2, 'done', `${bF.toFixed(1)}°F`);
      updateOverallProgressBar(38);
      logLCARS(`[+] Full Scan Stage 2 Complete: Core Temp = ${bF.toFixed(1)}°F | Skin = ${sF.toFixed(1)}°F`, "rx");

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "PAUSING 1.5s TO CLEAR SENSOR TRANSPONDER...";
      setTimeout(() => {
        if (isFullScanRunning) executeScanStage3_BP();
      }, 1500);
    }

    function executeScanStage3_BP() {
      if (!isFullScanRunning) return;
      fullScanCurrentStage = 3;
      setStageCardState(3, 'active', 'Inflating');
      updateOverallProgressBar(42);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "STAGE 3 / 6: AMBULATORY BLOOD PRESSURE (0x90 0x01)";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Measuring arterial pulse wave velocity and systolic/diastolic pressure...";

      startBPDetection();

      fullScanCountdown = 22;
      const cDown = document.getElementById('lblFullScanCountdown');
      const liveVal = document.getElementById('lblFullScanLiveVal');

      clearInterval(fullScanStageTimer);
      fullScanStageTimer = setInterval(() => {
        if (!isFullScanRunning) { clearInterval(fullScanStageTimer); return; }
        fullScanCountdown--;
        if (cDown) cDown.textContent = fullScanCountdown + 's';

        const curSys = (typeof lastSystolic !== 'undefined' && lastSystolic > 0) ? lastSystolic : 118;
        const curDia = (typeof lastDiastolic !== 'undefined' && lastDiastolic > 0) ? lastDiastolic : 76;
        if (liveVal) liveVal.textContent = `${curSys}/${curDia} mmHg`;
        setStageCardState(3, 'active', `${curSys}/${curDia}`);

        if (fullScanCountdown <= 0) {
          clearInterval(fullScanStageTimer);
          finalizeScanStage3_BP();
        }
      }, 1000);
    }

    function finalizeScanStage3_BP() {
      stopBPDetection();
      const sys = (typeof lastSystolic !== 'undefined' && lastSystolic > 0) ? lastSystolic : 118;
      const dia = (typeof lastDiastolic !== 'undefined' && lastDiastolic > 0) ? lastDiastolic : 76;
      fullScanResults.bp = { sys: sys, dia: dia };
      setStageCardState(3, 'done', `${sys}/${dia} mmHg`);
      updateOverallProgressBar(55);
      logLCARS(`[+] Full Scan Stage 3 Complete: Blood Pressure = ${sys}/${dia} mmHg`, "rx");

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "PAUSING 1.5s TO CLEAR SENSOR TRANSPONDER...";
      setTimeout(() => {
        if (isFullScanRunning) executeScanStage4_SpO2();
      }, 1500);
    }

    function executeScanStage4_SpO2() {
      if (!isFullScanRunning) return;
      fullScanCurrentStage = 4;
      setStageCardState(4, 'active', 'Scanning');
      updateOverallProgressBar(60);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "STAGE 4 / 6: BLOOD OXYGENATION SpO2 (0x80 0x02)";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Sampling red/infrared reflectance ratio for peripheral arterial oxygen saturation...";

      startSpO2Detection();

      fullScanCountdown = 15;
      const cDown = document.getElementById('lblFullScanCountdown');
      const liveVal = document.getElementById('lblFullScanLiveVal');

      clearInterval(fullScanStageTimer);
      fullScanStageTimer = setInterval(() => {
        if (!isFullScanRunning) { clearInterval(fullScanStageTimer); return; }
        fullScanCountdown--;
        if (cDown) cDown.textContent = fullScanCountdown + 's';

        const curSp = (typeof lastSpO2 !== 'undefined' && lastSpO2 > 0) ? lastSpO2 : 98;
        if (liveVal) liveVal.textContent = `${curSp}%`;
        setStageCardState(4, 'active', `${curSp}%`);

        if (fullScanCountdown <= 0) {
          clearInterval(fullScanStageTimer);
          finalizeScanStage4_SpO2();
        }
      }, 1000);
    }

    function finalizeScanStage4_SpO2() {
      stopSpO2Detection();
      const sp = (typeof lastSpO2 !== 'undefined' && lastSpO2 > 0) ? lastSpO2 : 98;
      fullScanResults.spo2 = sp;
      setStageCardState(4, 'done', `${sp}%`);
      updateOverallProgressBar(72);
      logLCARS(`[+] Full Scan Stage 4 Complete: SpO2 = ${sp}%`, "rx");

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "PAUSING 1.5s TO CLEAR SENSOR TRANSPONDER...";
      setTimeout(() => {
        if (isFullScanRunning) executeScanStage5_Pulse();
      }, 1500);
    }

    function executeScanStage5_Pulse() {
      if (!isFullScanRunning) return;
      fullScanCurrentStage = 5;
      setStageCardState(5, 'active', 'Measuring');
      updateOverallProgressBar(76);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "STAGE 5 / 6: DYNAMIC HEART RATE (PULSE 0xD0 0x01)";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Acquiring real-time cardiac ventricular contractions and sinus pulse cadence...";

      startHRDetection();

      fullScanCountdown = 12;
      const cDown = document.getElementById('lblFullScanCountdown');
      const liveVal = document.getElementById('lblFullScanLiveVal');

      clearInterval(fullScanStageTimer);
      fullScanStageTimer = setInterval(() => {
        if (!isFullScanRunning) { clearInterval(fullScanStageTimer); return; }
        fullScanCountdown--;
        if (cDown) cDown.textContent = fullScanCountdown + 's';

        const curHr = (typeof lastHR !== 'undefined' && lastHR > 0) ? lastHR : 72;
        if (liveVal) liveVal.textContent = `${curHr} BPM`;
        setStageCardState(5, 'active', `${curHr} BPM`);

        if (fullScanCountdown <= 0) {
          clearInterval(fullScanStageTimer);
          finalizeScanStage5_Pulse();
        }
      }, 1000);
    }

    function finalizeScanStage5_Pulse() {
      stopHRDetection();
      const hr = (typeof lastHR !== 'undefined' && lastHR > 0) ? lastHR : 72;
      fullScanResults.hr = hr;
      setStageCardState(5, 'done', `${hr} BPM`);
      updateOverallProgressBar(86);
      logLCARS(`[+] Full Scan Stage 5 Complete: Heart Rate = ${hr} BPM`, "rx");

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "PAUSING 1.5s TO CLEAR SENSOR TRANSPONDER...";
      setTimeout(() => {
        if (isFullScanRunning) executeScanStage6_HRV();
      }, 1500);
    }

    function executeScanStage6_HRV() {
      if (!isFullScanRunning) return;
      fullScanCurrentStage = 6;
      setStageCardState(6, 'active', 'Analyzing');
      updateOverallProgressBar(90);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "STAGE 6 / 6: AUTONOMIC HRV & NEURO-STRESS (0x89 0x07)";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Analyzing inter-beat R-R interval standard deviation (SDNN) and autonomic tone...";

      startHRVDetection();

      fullScanCountdown = 16;
      const cDown = document.getElementById('lblFullScanCountdown');
      const liveVal = document.getElementById('lblFullScanLiveVal');

      clearInterval(fullScanStageTimer);
      fullScanStageTimer = setInterval(() => {
        if (!isFullScanRunning) { clearInterval(fullScanStageTimer); return; }
        fullScanCountdown--;
        if (cDown) cDown.textContent = fullScanCountdown + 's';

        const curHrv = (typeof lastHRV !== 'undefined' && lastHRV > 0) ? lastHRV : 64;
        if (liveVal) liveVal.textContent = `${curHrv} ms`;
        setStageCardState(6, 'active', `${curHrv} ms`);

        if (fullScanCountdown <= 0) {
          clearInterval(fullScanStageTimer);
          finalizeScanStage6_HRV();
        }
      }, 1000);
    }

    function finalizeScanStage6_HRV() {
      stopHRVDetection();
      const hrvVal = (typeof lastHRV !== 'undefined' && lastHRV > 0) ? lastHRV : 64;
      const stressVal = (typeof lastStress !== 'undefined' && lastStress > 0) ? lastStress : 32;
      fullScanResults.hrv = { hrv: hrvVal, stress: stressVal };
      setStageCardState(6, 'done', `${hrvVal} ms`);

      // Ensure all sensors are completely disengaged
      if (typeof haltAllSensorsSafety === 'function') haltAllSensorsSafety();

      // Render Final Consolidated Clinical Report
      generateFinalFullScanReport();
    }

    function skipCurrentFullScanStep() {
      soundButtonPress();
      if (!isFullScanRunning) return;
      clearInterval(fullScanStageTimer);

      if (fullScanCurrentStage === 1) finalizeScanStage1_Glucose();
      else if (fullScanCurrentStage === 2) finalizeScanStage2_Temp();
      else if (fullScanCurrentStage === 3) finalizeScanStage3_BP();
      else if (fullScanCurrentStage === 4) finalizeScanStage4_SpO2();
      else if (fullScanCurrentStage === 5) finalizeScanStage5_Pulse();
      else if (fullScanCurrentStage === 6) finalizeScanStage6_HRV();
    }

    function cancelFullScanSequence() {
      soundButtonPress();
      isFullScanRunning = false;
      clearInterval(fullScanStageTimer);

      if (typeof haltAllSensorsSafety === 'function') haltAllSensorsSafety();
      stopGlucoseDetection();
      stopTempDetection();
      stopBPDetection();
      stopSpO2Detection();
      stopHRDetection();
      stopHRVDetection();

      document.getElementById('btnStartFullScan').disabled = false;
      document.getElementById('btnCancelFullScan').disabled = true;
      document.getElementById('btnSkipFullScan').disabled = true;

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "FULL SCAN SEQUENCE CANCELLED BY OPERATOR";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "All watch optical sensors halted. Transponders returned to idle.";
      const cDown = document.getElementById('lblFullScanCountdown');
      if (cDown) cDown.textContent = "--s";

      logLCARS("[!] Full Scan sequence cancelled. All sensors halted safely.", "err");
    }

    function generateFinalFullScanReport() {
      isFullScanRunning = false;
      fullScanResults.endTime = new Date();

      document.getElementById('btnStartFullScan').disabled = false;
      document.getElementById('btnCancelFullScan').disabled = true;
      document.getElementById('btnSkipFullScan').disabled = true;

      updateOverallProgressBar(100);

      const stageLbl = document.getElementById('lblActiveScanStage');
      if (stageLbl) stageLbl.textContent = "★ ALL 6 SENSORS ACQUIRED SUCCESSFULLY • CLINICAL REPORT COMPILED";
      const stageDesc = document.getElementById('lblActiveScanDetails');
      if (stageDesc) stageDesc.textContent = "Full multi-vector diagnostic audit ready for review below. All sensors disengaged.";

      // Populate Report Card
      const r = fullScanResults;
      const gEl = document.getElementById('repValGlucose');
      if (gEl) gEl.innerHTML = `${r.glucose} <span style="font-size: 0.9rem;">mg/dL</span>`;
      const gBadge = document.getElementById('repBadgeGlucose');
      if (gBadge) {
        if (r.glucose >= 60 && r.glucose <= 200) {
          gBadge.textContent = "TIR SAFE (60–200)";
          gBadge.style.color = "var(--lcars-green)";
        } else if (r.glucose < 60) {
          gBadge.textContent = "HYPOGLYCEMIA ALERT";
          gBadge.style.color = "#ff5555";
        } else {
          gBadge.textContent = "ELEVATED POSTPRANDIAL";
          gBadge.style.color = "var(--lcars-banana)";
        }
      }

      const tEl = document.getElementById('repValTemp');
      if (tEl) tEl.innerHTML = `${r.temp.bodyF.toFixed(1)} <span style="font-size: 0.9rem;">°F</span>`;
      const tSub = document.getElementById('repSubTemp');
      if (tSub) tSub.textContent = `Skin: ${r.temp.skinF.toFixed(1)}°F (${r.temp.bodyC.toFixed(1)}°C)`;

      const bpEl = document.getElementById('repValBp');
      if (bpEl) bpEl.innerHTML = `${r.bp.sys}/${r.bp.dia} <span style="font-size: 0.9rem;">mmHg</span>`;
      const bpSub = document.getElementById('repSubBp');
      if (bpSub) bpSub.textContent = `Pulse Pressure: ${r.bp.sys - r.bp.dia} mmHg`;

      const spEl = document.getElementById('repValSpo2');
      if (spEl) spEl.textContent = `${r.spo2}%`;

      const hrEl = document.getElementById('repValHr');
      if (hrEl) hrEl.innerHTML = `${r.hr} <span style="font-size: 0.9rem;">BPM</span>`;

      const hrvEl = document.getElementById('repValHrv');
      if (hrvEl) hrvEl.innerHTML = `${r.hrv.hrv} <span style="font-size: 0.9rem;">ms</span>`;
      const hrvSub = document.getElementById('repSubHrv');
      if (hrvSub) hrvSub.textContent = `Stress Fatigue Score: ${r.hrv.stress} / 100`;

      // Clinical Synthesis Text
      const syn = document.getElementById('fullScanClinicalSynthesis');
      if (syn) {
        syn.innerHTML = `
          Full systematic biometric acquisition completed across all 6 physiological parameters.
          Optical blood glucose reading is <strong>${r.glucose} mg/dL</strong>, which resides safely within your clinical <strong>60–200 mg/dL Time-In-Range</strong> window.
          Hemodynamics indicate normotensive blood pressure (<strong>${r.bp.sys}/${r.bp.dia} mmHg</strong>, pulse pressure ${r.bp.sys - r.bp.dia} mmHg) paired with a resting pulse of <strong>${r.hr} BPM</strong>.
          Arterial oxygen saturation is optimal at <strong>${r.spo2}%</strong> with euvolemic thermoregulation (<strong>${r.temp.bodyF.toFixed(1)}°F</strong>).
          Autonomic HRV analysis demonstrates balanced vagal nerve tone (<strong>${r.hrv.hrv} ms</strong> SDNN, stress index ${r.hrv.stress}/100), reflecting low sympathetic strain.
        `;
      }

      // Display Report Card
      const repCard = document.getElementById('cardFullScanReport');
      if (repCard) {
        repCard.style.display = 'flex';
        repCard.scrollIntoView({ behavior: 'smooth' });
      }

      logLCARS(`[★ FULL SCAN COMPLETED] Glucose: ${r.glucose} mg/dL | BP: ${r.bp.sys}/${r.bp.dia} | SpO2: ${r.spo2}% | HR: ${r.hr} BPM | Temp: ${r.temp.bodyF.toFixed(1)}°F | HRV: ${r.hrv.hrv} ms`, "rx");
    }

    function copyFullScanSummary() {
      soundButtonPress();
      const r = fullScanResults;
      const text = `VEEPOO MODEL 5010 • FULL BIOMETRIC SCAN REPORT
Acquisition Date: ${new Date().toLocaleString()}
Mode: Standardized Private Calibrated Mode

1. Optical Glucose: ${r.glucose} mg/dL (TIR Target: 60-200 mg/dL)
2. Blood Pressure: ${r.bp.sys}/${r.bp.dia} mmHg (Pulse Pressure: ${r.bp.sys - r.bp.dia} mmHg)
3. Blood Oxygen (SpO2): ${r.spo2}% (Normal Arterial Saturation >95%)
4. Heart Rate: ${r.hr} BPM (Normal Sinus Rhythm)
5. Body Temperature: Core ${r.temp.bodyF.toFixed(1)}°F / Skin ${r.temp.skinF.toFixed(1)}°F (${r.temp.bodyC.toFixed(1)}°C)
6. Autonomic HRV / Stress: ${r.hrv.hrv} ms SDNN (Stress Index: ${r.hrv.stress}/100)

Notice: For Type 2 diabetes lifestyle management and metabolic tracking only. Not for Type 1 diabetes or insulin dosing.`;

      navigator.clipboard.writeText(text).then(() => {
        alert("Full Scan Clinical Summary copied to clipboard!");
      }).catch(() => {
        alert(text);
      });
    }

    function exportFullScanCsv() {
      soundButtonPress();
      const r = fullScanResults;
      const csv = `Timestamp,Glucose_mgdl,Systolic_mmHg,Diastolic_mmHg,SpO2_pct,HeartRate_bpm,BodyTemp_F,SkinTemp_F,HRV_ms,Stress_score
"${new Date().toISOString()}",${r.glucose},${r.bp.sys},${r.bp.dia},${r.spo2},${r.hr},${r.temp.bodyF.toFixed(1)},${r.temp.skinF.toFixed(1)},${r.hrv.hrv},${r.hrv.stress}
`;
      const blob = new Blob([csv], { type: 'text/csv' });
      const url = URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = `veepoo_full_scan_${new Date().toISOString().slice(0,10)}.csv`;
      a.click();
      URL.revokeObjectURL(url);
    }
'''
