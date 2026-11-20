def get_fullscan_html():
    return '''      <!-- =====================================================================
           TAB 12: SEQUENTIAL COMPREHENSIVE BIOMETRIC FULL SCAN
           ===================================================================== -->
      <div id="pageFullScan" class="tab-page">
        <div class="diet-container">

          <!-- CRITICAL CLINICAL SAFETY WARNING: NOT FOR TYPE 1 DIABETICS / EMERGENCY USE -->
          <div style="background: rgba(230, 57, 70, 0.15); border: 2px solid #e63946; border-left: 8px solid #e63946; border-radius: 6px; padding: 14px 18px; margin-bottom: 8px;">
            <div style="display: flex; align-items: flex-start; gap: 12px;">
              <span style="font-size: 1.8rem; line-height: 1; flex-shrink: 0;">⚠️</span>
              <div>
                <strong style="color: #ff6b6b; font-size: 1.05rem; letter-spacing: 1px; font-family: var(--lcars-mono);">
                  CRITICAL CLINICAL NOTICE: NOT FOR TYPE 1 DIABETES OR EMERGENCY DIAGNOSTIC TRIAGE
                </strong>
                <div style="color: #ffd0d0; font-size: 0.85rem; line-height: 1.45; margin-top: 4px;">
                  This automated sequential scan operates optical photoplethysmography (PPG), hemodynamic pulse analysis, and infrared thermometry across watch hardware.
                  <br><br>
                  Measurements are intended for <strong>Type 2 diabetes lifestyle management, routine outpatient monitoring, and metabolic tracking only</strong>. Never rely on non-invasive wrist scans for acute diabetic emergencies, ketoacidosis, or independent insulin dosing adjustments.
                </div>
              </div>
            </div>
          </div>

          <!-- COMPONENT 1: FULL SCANNER CONTROL & PIPELINE ORCHESTRATOR -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-green);">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: var(--lcars-green); font-size: 1.15rem; letter-spacing: 0.05em;">12. SEQUENTIAL COMPREHENSIVE BIOMETRIC FULL SCAN</span>
                <span style="font-size: 0.8rem; color: var(--lcars-gray); display: block; margin-top: 2px;">RUNS EACH SENSOR SEQUENTIALLY (GLUCOSE ➔ TEMP ➔ BP ➔ SPO2 ➔ PULSE ➔ HRV) THEN REPORTS COMPLETE CLINICAL AUDIT</span>
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-btn btn-peach" style="padding: 4px 14px; font-size: 0.8rem;" onclick="switchTab('reports')">VIEW 360° SYNCHRONIZED TALL CHART (TAB 04)</button>
              </div>
            </div>

            <!-- Sequence Action Bar -->
            <div style="display: flex; gap: 10px; margin-top: 6px; flex-wrap: wrap;">
              <button id="btnStartFullScan" class="lcars-btn btn-green" style="flex: 2; min-height: 44px; font-size: 1.05rem; font-weight: bold; letter-spacing: 0.05em;" onclick="startFullScanSequence()">
                ▶ START FULL SCAN SEQUENCE (ALL 6 SENSORS)
              </button>
              <button id="btnCancelFullScan" class="lcars-btn btn-orange" style="flex: 1; min-height: 44px; font-size: 0.95rem;" onclick="cancelFullScanSequence()" disabled>
                ✕ CANCEL SEQUENCE
              </button>
              <button id="btnSkipFullScan" class="lcars-btn btn-blue" style="flex: 1; min-height: 44px; font-size: 0.95rem;" onclick="skipCurrentFullScanStep()" disabled>
                ⏭ SKIP TO NEXT
              </button>
            </div>

            <!-- Overall Sequence Progress Bar -->
            <div style="margin-top: 14px;">
              <div style="display: flex; justify-content: space-between; align-items: center; font-family: var(--lcars-mono); font-size: 0.82rem; margin-bottom: 4px;">
                <span style="color: var(--lcars-gray);">OVERALL PIPELINE PROGRESS:</span>
                <span id="lblFullScanProgressPct" style="color: var(--lcars-banana); font-weight: bold;">0% COMPLETED</span>
              </div>
              <div style="width: 100%; height: 12px; background: #0c1420; border: 1px solid #1c2b3e; border-radius: 6px; overflow: hidden;">
                <div id="barFullScanProgress" style="width: 0%; height: 100%; background: linear-gradient(90deg, var(--lcars-banana), var(--lcars-green)); transition: width 0.3s ease;"></div>
              </div>
            </div>

            <!-- 6 Sequenced Stage Status Cards -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(150px, 1fr)); gap: 8px; margin-top: 12px;" id="gridFullScanStages">
              <!-- Stage 1 -->
              <div id="stageCard1" class="stat-card" style="border-left-color: var(--lcars-banana); padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 1 • 0x89 0x01</div>
                <strong style="color: var(--lcars-banana); font-size: 0.88rem; display: block; margin-top: 2px;">OPTICAL GLUCOSE</strong>
                <div id="stageStatus1" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 2 -->
              <div id="stageCard2" class="stat-card" style="border-left-color: var(--lcars-blue); padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 2 • 0x87 0x01</div>
                <strong style="color: var(--lcars-blue); font-size: 0.88rem; display: block; margin-top: 2px;">BODY TEMPERATURE</strong>
                <div id="stageStatus2" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 3 -->
              <div id="stageCard3" class="stat-card" style="border-left-color: #ff9944; padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 3 • 0x90 0x01</div>
                <strong style="color: #ff9944; font-size: 0.88rem; display: block; margin-top: 2px;">BLOOD PRESSURE</strong>
                <div id="stageStatus3" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 4 -->
              <div id="stageCard4" class="stat-card" style="border-left-color: var(--lcars-green); padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 4 • 0x80 0x02</div>
                <strong style="color: var(--lcars-green); font-size: 0.88rem; display: block; margin-top: 2px;">BLOOD OXYGEN (SpO2)</strong>
                <div id="stageStatus4" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 5 -->
              <div id="stageCard5" class="stat-card" style="border-left-color: #ff5566; padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 5 • 0xD0 0x01</div>
                <strong style="color: #ff5566; font-size: 0.88rem; display: block; margin-top: 2px;">HEART RATE (PULSE)</strong>
                <div id="stageStatus5" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
              <!-- Stage 6 -->
              <div id="stageCard6" class="stat-card" style="border-left-color: var(--lcars-peach); padding: 10px; opacity: 0.6;">
                <div style="font-size: 0.7rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">STEP 6 • 0x89 0x07</div>
                <strong style="color: var(--lcars-peach); font-size: 0.88rem; display: block; margin-top: 2px;">HRV &amp; NEURO-STRESS</strong>
                <div id="stageStatus6" style="font-size: 0.75rem; color: var(--lcars-gray); margin-top: 4px;">IDLE</div>
              </div>
            </div>

            <!-- Active Stage Telemetry & Countdown HUD -->
            <div id="fullScanActiveHud" style="margin-top: 12px; background: #060b13; border: 1px solid #1a2a40; border-radius: 6px; padding: 12px; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;">
              <div>
                <span id="lblActiveScanStage" style="font-family: var(--lcars-mono); font-size: 0.95rem; font-weight: bold; color: var(--lcars-banana);">
                  READY: PRESS [START FULL SCAN SEQUENCE] TO BEGIN
                </span>
                <div id="lblActiveScanDetails" style="font-size: 0.82rem; color: var(--lcars-gray); margin-top: 3px;">
                  All 6 biometric transponders on Model 5010 will be acquired in non-interfering sequential stages.
                </div>
              </div>
              <div style="display: flex; align-items: center; gap: 12px;">
                <div style="text-align: right; font-family: var(--lcars-mono);">
                  <div style="font-size: 0.7rem; color: var(--lcars-gray);">STAGE TIME REMAINING</div>
                  <div id="lblFullScanCountdown" style="font-size: 1.25rem; font-weight: bold; color: var(--lcars-green);">--s</div>
                </div>
                <div style="text-align: right; font-family: var(--lcars-mono); padding-left: 12px; border-left: 1px solid #1c2b3e;">
                  <div style="font-size: 0.7rem; color: var(--lcars-gray);">STREAMING VALUE</div>
                  <div id="lblFullScanLiveVal" style="font-size: 1.25rem; font-weight: bold; color: var(--lcars-banana);">--</div>
                </div>
              </div>
            </div>
          </div>

          <!-- COMPONENT 2: CONSOLIDATED COMPREHENSIVE BIOMETRIC SCAN REPORT CARD -->
          <div id="cardFullScanReport" class="diet-form-card" style="border-left-color: var(--lcars-banana); display: none;">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: var(--lcars-banana); font-size: 1.15rem; letter-spacing: 0.05em;">
                  ★ CONSOLIDATED BIOMETRIC AUDIT REPORT (ALL 6 SENSORS)
                </span>
                <span id="lblFullScanReportMeta" style="font-size: 0.8rem; color: var(--lcars-green); display: block; margin-top: 2px;">
                  ACQUIRED TODAY • PRIVATE CALIBRATED MODE • 100% SENSOR COMPLETION
                </span>
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-btn btn-ice" style="padding: 4px 12px; font-size: 0.8rem;" onclick="copyFullScanSummary()">📋 COPY SUMMARY</button>
                <button class="lcars-btn btn-green" style="padding: 4px 12px; font-size: 0.8rem;" onclick="window.print()">🖨️ PRINT / PDF</button>
                <button class="lcars-btn btn-banana" style="padding: 4px 12px; font-size: 0.8rem;" onclick="exportFullScanCsv()">💾 EXPORT CSV</button>
              </div>
            </div>

            <!-- 6 Biometric Report Tiles -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(210px, 1fr)); gap: 10px; margin-top: 6px;">
              <!-- 1. Glucose -->
              <div class="stat-card" style="border-left-color: var(--lcars-banana);">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span class="stat-card-title">OPTICAL GLUCOSE</span>
                  <span class="badge" style="background: rgba(85,255,119,0.15); color: var(--lcars-green); font-size: 0.7rem;" id="repBadgeGlucose">TIR SAFE</span>
                </div>
                <span class="stat-card-val" id="repValGlucose" style="color: var(--lcars-banana);">-- <span style="font-size: 0.9rem;">mg/dL</span></span>
                <span class="stat-card-sub" id="repSubGlucose">Target Range: 60–200 mg/dL</span>
              </div>

              <!-- 2. Temperature -->
              <div class="stat-card" style="border-left-color: var(--lcars-blue);">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span class="stat-card-title">BODY TEMPERATURE</span>
                  <span class="badge" style="background: rgba(56,182,255,0.15); color: var(--lcars-blue); font-size: 0.7rem;">EUVOLEMIC</span>
                </div>
                <span class="stat-card-val" id="repValTemp" style="color: var(--lcars-blue);">-- <span style="font-size: 0.9rem;">°F</span></span>
                <span class="stat-card-sub" id="repSubTemp">Core &amp; Skin Homologous</span>
              </div>

              <!-- 3. Blood Pressure -->
              <div class="stat-card" style="border-left-color: #ff9944;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span class="stat-card-title">BLOOD PRESSURE</span>
                  <span class="badge" style="background: rgba(255,153,68,0.15); color: #ff9944; font-size: 0.7rem;" id="repBadgeBp">NORMOTENSIVE</span>
                </div>
                <span class="stat-card-val" id="repValBp" style="color: #ff9944;">--/-- <span style="font-size: 0.9rem;">mmHg</span></span>
                <span class="stat-card-sub" id="repSubBp">Pulse Pressure: -- mmHg</span>
              </div>

              <!-- 4. SpO2 -->
              <div class="stat-card" style="border-left-color: var(--lcars-green);">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span class="stat-card-title">BLOOD OXYGEN (SpO2)</span>
                  <span class="badge" style="background: rgba(85,255,119,0.15); color: var(--lcars-green); font-size: 0.7rem;">OPTIMAL</span>
                </div>
                <span class="stat-card-val" id="repValSpo2" style="color: var(--lcars-green);">--%</span>
                <span class="stat-card-sub" id="repSubSpo2">Normal Arterial Saturation &gt;95%</span>
              </div>

              <!-- 5. Heart Rate -->
              <div class="stat-card" style="border-left-color: #ff5566;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span class="stat-card-title">DYNAMIC PULSE</span>
                  <span class="badge" style="background: rgba(255,85,102,0.15); color: #ff5566; font-size: 0.7rem;">EUCARDIC</span>
                </div>
                <span class="stat-card-val" id="repValHr" style="color: #ff5566;">-- <span style="font-size: 0.9rem;">BPM</span></span>
                <span class="stat-card-sub" id="repSubHr">Normal Sinus Rhythm</span>
              </div>

              <!-- 6. HRV & Stress -->
              <div class="stat-card" style="border-left-color: var(--lcars-peach);">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <span class="stat-card-title">AUTONOMIC HRV / STRESS</span>
                  <span class="badge" style="background: rgba(255,170,85,0.15); color: var(--lcars-peach); font-size: 0.7rem;">BALANCED</span>
                </div>
                <span class="stat-card-val" id="repValHrv" style="color: var(--lcars-peach);">-- <span style="font-size: 0.9rem;">ms</span></span>
                <span class="stat-card-sub" id="repSubHrv">Stress Score: -- / 100</span>
              </div>
            </div>

            <!-- Automated Physician & Patient Clinical Interpretation -->
            <div style="background: #06101c; border: 1px solid #1a2f4a; border-radius: 6px; padding: 14px; margin-top: 12px;">
              <strong style="color: var(--lcars-green); font-size: 0.95rem; font-family: var(--lcars-mono);">
                CLINICAL MULTI-VECTOR SYNTHESIS &amp; EVALUATION
              </strong>
              <div id="fullScanClinicalSynthesis" style="font-size: 0.86rem; color: #d0e4f2; line-height: 1.5; margin-top: 6px;">
                <!-- Populated by JavaScript -->
              </div>
            </div>

            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 12px; flex-wrap: wrap; gap: 8px;">
              <div style="font-size: 0.8rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">
                All 6 sensors disengaged successfully. Watch hardware returned to idle display.
              </div>
              <button class="lcars-btn btn-banana" style="flex: none; padding: 6px 16px; font-weight: bold;" onclick="startFullScanSequence()">
                ↻ RUN ANOTHER FULL SCAN
              </button>
            </div>
          </div>

        </div>
      </div>
'''
