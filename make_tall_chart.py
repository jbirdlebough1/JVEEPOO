def get_tall_chart_html():
    return '''          <!-- CRITICAL CLINICAL SAFETY WARNING: NOT FOR TYPE 1 DIABETICS (REPORTS) -->
          <div style="background: rgba(230, 57, 70, 0.15); border: 2px solid #e63946; border-left: 8px solid #e63946; border-radius: 6px; padding: 12px 16px; margin-bottom: 12px;">
            <div style="display: flex; align-items: center; gap: 10px;">
              <span style="font-size: 1.5rem;">⚠️</span>
              <div>
                <strong style="color: #ff6b6b; font-size: 0.98rem; letter-spacing: 0.5px; font-family: var(--lcars-mono);">
                  CLINICAL CONTRAINDICATION: NOT FOR TYPE 1 DIABETICS OR INSULIN DOSE CALCULATION
                </strong>
                <div style="color: #ffd0d0; font-size: 0.82rem; line-height: 1.4; margin-top: 2px;">
                  Optical PPG microvascular telemetry is engineered for <strong>Type 2 diabetes lifestyle management and behavioral feedback only</strong>. Never use these reports to calculate insulin dosages or manage acute diabetic emergencies.
                </div>
              </div>
            </div>
          </div>

          <!-- SINGLE TALL SYNCHRONIZED MULTI-MEASUREMENT TIMELINE CHART -->
          <div class="chart-box" id="boxSynchronizedTallChart" style="margin-bottom: 18px; border-left: 6px solid var(--lcars-banana);">
            <div class="chart-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;">
              <div>
                <span style="color: var(--lcars-banana); font-size: 1.15rem; font-weight: bold; letter-spacing: 0.05em;">
                  360° SYNCHRONIZED COMPOSITE TIMELINE (ALL MEASUREMENTS)
                </span>
                <span style="font-family: var(--lcars-mono); color: var(--lcars-green); display: block; font-size: 0.82rem; margin-top: 2px;">
                  STACKED MULTI-LANE TELEMETRY • DRAG VERTICAL SCRUBBER OR CLICK ANY POINT TO VIEW DIET &amp; EXERCISE INTERACTION
                </span>
              </div>
              <!-- Timeframe Selector Pills for Tall Chart -->
              <div style="display: flex; gap: 6px;">
                <button id="btnTallDaily" class="lcars-btn btn-banana" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('daily')">DAILY 24-HOUR</button>
                <button id="btnTallWeekly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('weekly')">WEEKLY 7-DAY</button>
                <button id="btnTallYearly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('yearly')">YEARLY 12-MONTH</button>
              </div>
            </div>

            <!-- Interactive Scrubber Control & Synchronized Real-Time HUD Readout -->
            <div style="background: #060b13; border: 1px solid #1a2a40; border-radius: 6px; padding: 10px 14px; margin-top: 10px;">
              <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px; flex-wrap: wrap; gap: 8px;">
                <div style="font-family: var(--lcars-mono); font-size: 0.82rem; color: var(--lcars-banana); font-weight: bold; display: flex; align-items: center; gap: 6px;">
                  <span>↔ VERTICAL TIMELINE SCRUBBER:</span>
                  <span style="color: var(--lcars-gray); font-weight: normal;">(Drag slider or move cursor across chart to inspect synchronized biometrics)</span>
                </div>
                <div style="font-family: var(--lcars-mono); font-size: 0.88rem; color: var(--lcars-banana); font-weight: bold;" id="tallScrubberTimeBadge">
                  TIMESTAMP: 13:30 (Lunch Peak)
                </div>
              </div>

              <!-- Top Scrubber Slider -->
              <div style="display: flex; align-items: center; gap: 10px;">
                <span style="font-family: var(--lcars-mono); font-size: 0.75rem; color: var(--lcars-gray);" id="tallScrubberStartLbl">00:00</span>
                <input type="range" id="tallScrubberSlider" min="0" max="100" value="50" oninput="onTallScrubberMoved(this.value)" style="flex: 1; accent-color: var(--lcars-banana); cursor: pointer; height: 8px;">
                <span style="font-family: var(--lcars-mono); font-size: 0.75rem; color: var(--lcars-gray);" id="tallScrubberEndLbl">24:00</span>
              </div>

              <!-- Synchronized Multi-Parameter HUD Ribbon -->
              <div id="tallHudRibbon" style="display: grid; grid-template-columns: repeat(auto-fit, minmax(130px, 1fr)); gap: 8px; margin-top: 10px; padding-top: 10px; border-top: 1px solid #142233; font-family: var(--lcars-mono); font-size: 0.78rem;">
                <div style="background: rgba(255,187,0,0.1); border-left: 3px solid var(--lcars-banana); padding: 5px 8px; border-radius: 3px;">
                  <span style="color: var(--lcars-gray); display: block; font-size: 0.7rem;">GLUCOSE</span>
                  <strong style="color: var(--lcars-banana); font-size: 0.95rem;" id="hudGlucose">188 mg/dL</strong>
                </div>
                <div style="background: rgba(255,102,0,0.1); border-left: 3px solid #ff6600; padding: 5px 8px; border-radius: 3px;">
                  <span style="color: var(--lcars-gray); display: block; font-size: 0.7rem;">BLOOD PRESSURE</span>
                  <strong style="color: #ff9944; font-size: 0.95rem;" id="hudBp">119/76 mmHg</strong>
                </div>
                <div style="background: rgba(255,85,102,0.1); border-left: 3px solid #ff5566; padding: 5px 8px; border-radius: 3px;">
                  <span style="color: var(--lcars-gray); display: block; font-size: 0.7rem;">PULSE / HRV</span>
                  <strong style="color: #ff5566; font-size: 0.95rem;" id="hudHrHrv">84 BPM / 56 ms</strong>
                </div>
                <div style="background: rgba(85,255,119,0.1); border-left: 3px solid var(--lcars-green); padding: 5px 8px; border-radius: 3px;">
                  <span style="color: var(--lcars-gray); display: block; font-size: 0.7rem;">OXYGEN (SpO2)</span>
                  <strong style="color: var(--lcars-green); font-size: 0.95rem;" id="hudSpo2">98%</strong>
                </div>
                <div style="background: rgba(56,182,255,0.1); border-left: 3px solid var(--lcars-blue); padding: 5px 8px; border-radius: 3px;">
                  <span style="color: var(--lcars-gray); display: block; font-size: 0.7rem;">TEMPERATURE</span>
                  <strong style="color: var(--lcars-blue); font-size: 0.95rem;" id="hudTemp">97.3°F</strong>
                </div>
                <div style="background: rgba(255,153,102,0.1); border-left: 3px solid var(--lcars-peach); padding: 5px 8px; border-radius: 3px; grid-column: span 2;">
                  <span style="color: var(--lcars-gray); display: block; font-size: 0.7rem;">EXERCISE &amp; GLUT4 IMPACT</span>
                  <strong style="color: var(--lcars-peach); font-size: 0.85rem;" id="hudExercise">780 steps • 20-min Post-Meal GLUT4 Walk (-34 mg/dL)</strong>
                </div>
                <div style="background: rgba(204,153,255,0.1); border-left: 3px solid var(--lcars-lilac); padding: 5px 8px; border-radius: 3px; grid-column: span 2;">
                  <span style="color: var(--lcars-gray); display: block; font-size: 0.7rem;">DIET &amp; NUTRITION</span>
                  <strong style="color: var(--lcars-lilac); font-size: 0.85rem;" id="hudDiet">Lunch: Grilled Chicken Salad (18g Carbs • 520 kcal)</strong>
                </div>
                <div style="background: rgba(255,204,102,0.1); border-left: 3px solid #ffcc66; padding: 5px 8px; border-radius: 3px; grid-column: span 2;">
                  <span style="color: var(--lcars-gray); display: block; font-size: 0.7rem;">MEDICATION REGIMEN</span>
                  <strong style="color: #ffcc66; font-size: 0.85rem;" id="hudMeds">Metformin 1,000mg Active • No acute changes</strong>
                </div>
              </div>
            </div>

            <!-- Tall Synchronized SVG Multi-Lane Chart Container -->
            <div id="tallChartSvgContainer" style="position: relative; width: 100%; overflow-x: auto; background: #05080f; border: 1px solid #142233; border-radius: 6px; margin-top: 10px; cursor: crosshair;" onmousemove="onTallChartMouseMove(event)" onclick="onTallChartClick(event)">
              <!-- Rendered via JavaScript -->
            </div>

            <!-- Interactive Click Point Pop-Up Card Modal -->
            <div id="timelinePointCardModal" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 9999; background: #080f1a; border: 2px solid var(--lcars-banana); border-radius: 8px; box-shadow: 0 0 35px rgba(0,0,0,0.85); width: 90%; max-width: 580px; padding: 18px; font-family: var(--lcars-font);">
              <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #1a2f4a; padding-bottom: 10px; margin-bottom: 12px;">
                <div style="display: flex; align-items: center; gap: 8px;">
                  <span style="font-size: 1.3rem;">📊</span>
                  <strong style="color: var(--lcars-banana); font-size: 1.05rem; font-family: var(--lcars-mono);" id="cardModalTitle">
                    POINT AUDIT: 13:30 (Lunch Peak)
                  </strong>
                </div>
                <button class="lcars-btn btn-orange" style="flex: none; padding: 3px 10px; font-size: 0.8rem;" onclick="closeTimelinePointCard()">✕ CLOSE</button>
              </div>

              <!-- Biometric Matrix at this point -->
              <div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 8px; margin-bottom: 12px;">
                <div style="background: rgba(255,187,0,0.1); border-left: 3px solid var(--lcars-banana); padding: 6px 10px; border-radius: 4px;">
                  <div style="font-size: 0.7rem; color: var(--lcars-gray);">GLUCOSE</div>
                  <div style="font-size: 1.15rem; font-weight: bold; color: var(--lcars-banana);" id="cardModalGlucose">188 mg/dL</div>
                  <div style="font-size: 0.7rem; color: var(--lcars-green);">TIR Safe (60–200)</div>
                </div>
                <div style="background: rgba(255,102,0,0.1); border-left: 3px solid #ff6600; padding: 6px 10px; border-radius: 4px;">
                  <div style="font-size: 0.7rem; color: var(--lcars-gray);">BLOOD PRESSURE</div>
                  <div style="font-size: 1.15rem; font-weight: bold; color: #ff9944;" id="cardModalBp">119/76 mmHg</div>
                  <div style="font-size: 0.7rem; color: var(--lcars-gray);">Normotensive</div>
                </div>
                <div style="background: rgba(85,255,119,0.1); border-left: 3px solid var(--lcars-green); padding: 6px 10px; border-radius: 4px;">
                  <div style="font-size: 0.7rem; color: var(--lcars-gray);">SPO2 / PULSE</div>
                  <div style="font-size: 1.15rem; font-weight: bold; color: var(--lcars-green);" id="cardModalSpo2Hr">98% / 84 BPM</div>
                  <div style="font-size: 0.7rem; color: var(--lcars-gray);">HRV: 56 ms</div>
                </div>
              </div>

              <!-- Diet & Exercise Cards at this point -->
              <div style="display: flex; flex-direction: column; gap: 8px; margin-bottom: 12px;">
                <!-- Diet Info -->
                <div style="background: #0d1726; border: 1px solid #1b304f; border-left: 4px solid var(--lcars-lilac); border-radius: 4px; padding: 10px;">
                  <div style="display: flex; justify-content: space-between; align-items: center;">
                    <strong style="color: var(--lcars-lilac); font-size: 0.9rem;">🥗 DIETARY INTAKE AT THIS POINT</strong>
                    <span class="badge" style="background: rgba(204,153,255,0.2); color: var(--lcars-lilac); font-size: 0.7rem;" id="cardModalDietBadge">LUNCH</span>
                  </div>
                  <div style="color: var(--lcars-text); font-size: 0.85rem; margin-top: 3px;" id="cardModalDietDesc">
                    Grilled chicken breast salad with avocado, olive oil dressing, leafy greens, walnuts.
                  </div>
                  <div style="margin-top: 4px; font-family: var(--lcars-mono); font-size: 0.78rem; color: var(--lcars-banana);" id="cardModalDietMacros">
                    18g Net Carbs • 38g Protein • 520 kcal • Low Glycemic Index
                  </div>
                </div>

                <!-- Exercise Info -->
                <div style="background: #0d1726; border: 1px solid #1b304f; border-left: 4px solid var(--lcars-peach); border-radius: 4px; padding: 10px;">
                  <div style="display: flex; justify-content: space-between; align-items: center;">
                    <strong style="color: var(--lcars-peach); font-size: 0.9rem;">🚶 KINETIC EXERCISE &amp; GLUT4 IMPACT</strong>
                    <span class="badge" style="background: rgba(255,153,102,0.2); color: var(--lcars-peach); font-size: 0.7rem;" id="cardModalExBadge">POST-MEAL WALK</span>
                  </div>
                  <div style="color: var(--lcars-text); font-size: 0.85rem; margin-top: 3px;" id="cardModalExDesc">
                    20-minute brisk walk commencing 15 minutes post-lunch.
                  </div>
                  <div style="margin-top: 4px; font-family: var(--lcars-mono); font-size: 0.78rem; color: var(--lcars-green);" id="cardModalExGlut4">
                    780 Steps • Zone 2 Moderate • GLUT4 Suppression: -34 mg/dL vs sedentary projection
                  </div>
                </div>

                <!-- Medication Status -->
                <div style="background: #0d1726; border: 1px solid #1b304f; border-left: 4px solid #ffcc66; border-radius: 4px; padding: 10px;">
                  <div style="display: flex; justify-content: space-between; align-items: center;">
                    <strong style="color: #ffcc66; font-size: 0.9rem;">💊 MEDICATION STATUS AT THIS POINT</strong>
                    <span class="badge" style="background: rgba(255,204,102,0.2); color: #ffcc66; font-size: 0.7rem;" id="cardModalMedBadge">ACTIVE REGIMEN</span>
                  </div>
                  <div style="color: var(--lcars-text); font-size: 0.85rem; margin-top: 3px;" id="cardModalMedDesc">
                    Metformin 1,000mg BID (Dose taken with breakfast at 08:15 AM). Empagliflozin 10mg taken at 08:15 AM.
                  </div>
                </div>
              </div>

              <!-- Doctor & Patient Clinical Insight -->
              <div style="background: rgba(85,255,119,0.08); border: 1px solid var(--lcars-green); border-radius: 4px; padding: 10px; font-size: 0.82rem; color: #c8ecd0; line-height: 1.45;">
                <strong style="color: var(--lcars-green);">DOCTOR &amp; USER CLINICAL CORRELATION:</strong>
                <span id="cardModalInsight">
                  Pairing an 18g low-carb meal with an immediate 20-minute postprandial walk successfully blunted the glycemic peak to 188 mg/dL, safely within the 60–200 mg/dL Time-In-Range window. Non-insulin GLUT4 skeletal muscle uptake cleared glucose without triggering acute pancreatic beta-cell hypersecretion.
                </span>
              </div>

              <div style="display: flex; justify-content: flex-end; margin-top: 12px;">
                <button class="lcars-btn btn-ice" onclick="closeTimelinePointCard()">DISMISS POPUP [ESC]</button>
              </div>
            </div>
          </div>
'''
