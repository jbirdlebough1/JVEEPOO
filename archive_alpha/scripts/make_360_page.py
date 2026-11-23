def get_360_health_html():
    return '''      <!-- =====================================================================
           TAB 3: 360-DEGREE PERSONAL HEALTH VIEW & DIABETES LEARNING ENGINE
           ===================================================================== -->
      <div id="pageMetabolic" class="tab-page">
        <div class="diet-container">

          <!-- CRITICAL CLINICAL SAFETY WARNING: NOT FOR TYPE 1 DIABETICS -->
          <div style="background: rgba(230, 57, 70, 0.15); border: 2px solid #e63946; border-left: 8px solid #e63946; border-radius: 6px; padding: 14px 18px; margin-bottom: 8px;">
            <div style="display: flex; align-items: flex-start; gap: 12px;">
              <span style="font-size: 1.8rem; line-height: 1; flex-shrink: 0;">⚠️</span>
              <div>
                <strong style="color: #ff6b6b; font-size: 1.05rem; letter-spacing: 1px; font-family: var(--lcars-mono);">
                  CRITICAL CLINICAL NOTICE: STRICTLY FOR TYPE 2 DIABETES & METABOLIC LIFESTYLE TRACKING • NOT FOR TYPE 1 DIABETICS
                </strong>
                <div style="color: #ffd0d0; font-size: 0.85rem; line-height: 1.45; margin-top: 4px;">
                  This optical PPG biometric system provides non-invasive hemodynamic and lifestyle trend estimation for <strong>Type 2 diabetes management, dietary learning, and metabolic fitness only</strong>. It is <strong>NOT an FDA-cleared continuous glucose monitor (CGM)</strong> and does not directly measure interstitial or venous blood glucose.
                  <br><br>
                  <strong style="color: #ff9999;">NEVER USE THIS SYSTEM TO CALCULATE INSULIN DOSAGES, TITRATE BOLUS INJECTIONS, OR MANAGE ACUTE HYPOGLYCEMIA / KETOACIDOSIS.</strong> Patients with Type 1 diabetes must rely exclusively on prescription capillary fingerstick glucometers, cleared CGMs (Dexcom/Freestyle Libre), and licensed endocrinology guidance.
                </div>
              </div>
            </div>
          </div>

          <!-- COMPONENT 1: 360° LIVE BIOMETRIC CONSOLE (MULTI-SENSOR TELEMETRY SYNTHESIS) -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-banana);">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: var(--lcars-banana);">01. 360° REAL-TIME PHYSIOLOGICAL CONSOLE</span>
                <span style="font-size: 0.8rem; color: var(--lcars-gray); display: block;">SYNCHRONIZED SENSOR TELEMETRY &amp; METABOLIC HOMEOSTASIS</span>
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-btn btn-banana" style="flex: none; padding: 4px 14px; font-size: 0.8rem;" onclick="switchTab('reports')">OPEN 360° SYNCHRONIZED TALL CHART (TAB 04)</button>
              </div>
            </div>

            <!-- 6 Synchronized Real-Time Biometric Tiles -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); gap: 10px; margin-top: 6px;">
              <!-- Tile 1: Blood Glucose -->
              <div class="stat-card" style="border-left-color: var(--lcars-banana);">
                <span class="stat-card-title">OPTICAL GLUCOSE</span>
                <span class="stat-card-val" id="tile360Glucose" style="color: var(--lcars-banana);">160 <span style="font-size: 0.9rem;">mg/dL</span></span>
                <span class="stat-card-sub" id="tile360GlucoseSub">Safe Band (60–200 TIR)</span>
              </div>
              <!-- Tile 2: Blood Pressure -->
              <div class="stat-card" style="border-left-color: var(--lcars-orange);">
                <span class="stat-card-title">BLOOD PRESSURE</span>
                <span class="stat-card-val" id="tile360Bp" style="color: var(--lcars-orange);">118/76 <span style="font-size: 0.9rem;">mmHg</span></span>
                <span class="stat-card-sub">Optimal Hemodynamics</span>
              </div>
              <!-- Tile 3: Oxygenation -->
              <div class="stat-card" style="border-left-color: var(--lcars-green);">
                <span class="stat-card-title">BLOOD OXYGEN (SpO2)</span>
                <span class="stat-card-val" id="tile360Spo2" style="color: var(--lcars-green);">98%</span>
                <span class="stat-card-sub">Nocturnal Stability >95%</span>
              </div>
              <!-- Tile 4: Heart Rate & HRV -->
              <div class="stat-card" style="border-left-color: var(--lcars-peach);">
                <span class="stat-card-title">PULSE &amp; HRV STRESS</span>
                <span class="stat-card-val" id="tile360HrHrv" style="color: var(--lcars-peach);">72 <span style="font-size: 0.9rem;">BPM</span> / 62 <span style="font-size: 0.9rem;">ms</span></span>
                <span class="stat-card-sub">Parasympathetic Tone</span>
              </div>
              <!-- Tile 5: Temperature -->
              <div class="stat-card" style="border-left-color: var(--lcars-blue);">
                <span class="stat-card-title">CORE &amp; SKIN TEMP</span>
                <span class="stat-card-val" id="tile360Temp" style="color: var(--lcars-blue);">97.3°F</span>
                <span class="stat-card-sub">Euvolemic Diurnal Curve</span>
              </div>
              <!-- Tile 6: Kinetic Energy -->
              <div class="stat-card" style="border-left-color: var(--lcars-lilac);">
                <span class="stat-card-title">KINETIC ACTIVITY</span>
                <span class="stat-card-val" id="tile360Motion" style="color: var(--lcars-lilac);">2,384 <span style="font-size: 0.9rem;">steps</span></span>
                <span class="stat-card-sub">Morning Ambulation Logged</span>
              </div>
            </div>
          </div>

          <!-- COMPONENT 2: EXERCISE & KINETIC ACTIVITY LOG ("GLUT4 TRANSLOCATION ENGINE") -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-peach);">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: var(--lcars-peach);">02. EXERCISE &amp; KINETIC ACTIVITY LOG (GLUT4 CLEARANCE)</span>
                <span style="font-size: 0.8rem; color: var(--lcars-green); display: block;">NON-INSULIN SKELETAL MUSCLE GLUCOSE TRANSPORTER 4 (GLUT4) ACTIVATION</span>
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-btn btn-banana" style="flex: none; padding: 4px 14px; font-size: 0.8rem;" onclick="syncStepData()">SYNC WATCH STEPS</button>
              </div>
            </div>

            <!-- Activity Summary Badges -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 12px; margin-top: 4px;">
              <div class="stat-card" style="border-left-color: var(--lcars-banana);">
                <span class="stat-card-title">ACTIVE STEPS TODAY</span>
                <span class="stat-card-val" id="valStepCount">-- <span style="font-size: 1rem;">steps</span></span>
                <span class="stat-card-sub" id="valStepGoal">Goal: 2,000 (Awaiting Sync)</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-blue);">
                <span class="stat-card-title">DISTANCE TRAVELED</span>
                <span class="stat-card-val" style="color: var(--lcars-blue);" id="valStepDist">-- <span style="font-size: 1rem;">miles</span></span>
                <span class="stat-card-sub">Basal &amp; Active</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-orange);">
                <span class="stat-card-title">KINETIC CALORIES</span>
                <span class="stat-card-val" style="color: var(--lcars-orange);" id="valStepKcal">-- <span style="font-size: 1rem;">kcal</span></span>
                <span class="stat-card-sub">Active Energy Expenditure</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-green);">
                <span class="stat-card-title">ESTIMATED GLUT4 CLEARANCE</span>
                <span class="stat-card-val" style="color: var(--lcars-green);" id="valGlut4Clearance">-34 <span style="font-size: 1rem;">mg/dL</span></span>
                <span class="stat-card-sub">Postprandial Spike Suppression</span>
              </div>
            </div>

            <!-- Targeted Workout Log Form -->
            <div style="background: #090e17; border: 1px solid #1a273b; border-radius: 6px; padding: 14px; margin-top: 10px;">
              <div style="font-size: 0.88rem; font-weight: bold; color: var(--lcars-peach); margin-bottom: 10px; font-family: var(--lcars-mono);">
                LOG TARGETED WORKOUT / AMBULATION SESSION
              </div>
              <div class="diet-form-grid">
                <div class="setup-field">
                  <label class="setup-label">DATE &amp; TIME</label>
                  <input type="datetime-local" id="exTimestamp" class="setup-input">
                </div>
                <div class="setup-field">
                  <label class="setup-label">WORKOUT / ACTIVITY TYPE</label>
                  <select id="exType" class="setup-select">
                    <option value="Post-Meal Walk">15–20 MIN POST-MEAL WALK (GLUT4 BLUNTING)</option>
                    <option value="Brisk Walk / Cardio">30 MIN BRISK WALKING (ZONE 2 AEROBIC)</option>
                    <option value="Resistance Training">RESISTANCE / STRENGTH TRAINING (MUSCLE GLUCOSE DEPOT)</option>
                    <option value="Cycling / Ergometer">STATIONARY CYCLING / ERGOMETER</option>
                    <option value="Post-Snack Movement">5–10 MIN LIGHT MOBILITY / STAIRS</option>
                  </select>
                </div>
                <div class="setup-field">
                  <label class="setup-label">DURATION (MINUTES)</label>
                  <input type="number" id="exDuration" class="setup-input" value="15" min="5" max="240">
                </div>
                <div class="setup-field">
                  <label class="setup-label">TIMING RELATIVE TO MEAL</label>
                  <select id="exTiming" class="setup-select">
                    <option value="15-min Post-Meal">15–30 MIN POST-MEAL (OPTIMAL BLUNTING)</option>
                    <option value="Immediate Post-Meal">IMMEDIATE POST-MEAL (<15 MIN)</option>
                    <option value="Fasted Morning">FASTED MORNING (WAKE-UP SENSITIVITY)</option>
                    <option value="Late Afternoon">AFTERNOON / PRE-DINNER</option>
                    <option value="Evening Stroll">EVENING BEDTIME STROLL</option>
                  </select>
                </div>
                <div class="setup-field">
                  <label class="setup-label">PRE-EXERCISE GLUCOSE (MG/DL)</label>
                  <input type="number" id="exPreGlucose" class="setup-input" value="168" min="50" max="350">
                </div>
                <div class="setup-field">
                  <label class="setup-label">POST-EXERCISE GLUCOSE (MG/DL)</label>
                  <input type="number" id="exPostGlucose" class="setup-input" value="132" min="50" max="350">
                </div>
                <div class="setup-field" style="grid-column: span 2;">
                  <label class="setup-label">PERSONAL NOTES &amp; PERCEIVED SENSATION</label>
                  <input type="text" id="exNotes" class="setup-input" placeholder="e.g. Brisk neighborhood walk right after lunch; felt energized and postprandial fatigue vanished.">
                </div>
              </div>
              <div style="display: flex; gap: 10px; margin-top: 10px;">
                <button class="lcars-btn btn-peach" style="flex: 2;" onclick="saveExerciseEntry()">RECORD WORKOUT &amp; CALCULATE GLUT4 CLEARANCE</button>
                <button class="lcars-btn btn-orange" style="flex: 1;" onclick="clearExerciseForm()">CLEAR FORM</button>
              </div>
            </div>

            <!-- Chronological Exercise Journal -->
            <div class="setup-section-title" style="margin-top: 12px;">
              <span>CHRONOLOGICAL EXERCISE &amp; GLUT4 JOURNAL</span>
              <button class="lcars-btn btn-orange" style="flex: none; padding: 4px 14px; font-size: 0.8rem;" onclick="clearAllExercises()">CLEAR EXERCISE LOG</button>
            </div>
            <div class="diet-history-list" id="exerciseHistoryList"></div>
          </div>

          <!-- COMPONENT 3: DAILY NUTRITION MACROS & MEAL JOURNAL -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-lilac);">
            <div class="setup-section-title">
              <span style="color: var(--lcars-lilac);">03. DIETARY INTAKE &amp; MEAL LOGGING</span>
              <span style="font-size: 0.85rem; color: var(--lcars-green);" id="lblLinkedGlucose">AUTO-LINKED TO RECENT GLUCOSE: -- MG/DL</span>
            </div>

            <!-- Macro Summary Ribbon -->
            <div class="diet-ribbon-summary" style="margin-top: 4px;">
              <div class="stat-card">
                <span class="stat-card-title">TOTAL CARBS</span>
                <span class="stat-card-val" id="sumCarbs">0 <span style="font-size: 1rem;">g</span></span>
                <span class="stat-card-sub">Daily Macro Intake</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-blue);">
                <span class="stat-card-title">TOTAL PROTEIN</span>
                <span class="stat-card-val" style="color: var(--lcars-blue);" id="sumProtein">0 <span style="font-size: 1rem;">g</span></span>
                <span class="stat-card-sub">Daily Macro Intake</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-orange);">
                <span class="stat-card-title">TOTAL CALORIES</span>
                <span class="stat-card-val" style="color: var(--lcars-orange);" id="sumCalories">0 <span style="font-size: 1rem;">kcal</span></span>
                <span class="stat-card-sub">Nutritional Intake</span>
              </div>
              <div class="stat-card" style="border-left-color: var(--lcars-green);">
                <span class="stat-card-title">POST-MEAL GLUCOSE AVG</span>
                <span class="stat-card-val" style="color: var(--lcars-green);" id="sumAvgPostGlucose">124 <span style="font-size: 1rem;">mg/dL</span></span>
                <span class="stat-card-sub">Postprandial Glycemic Index</span>
              </div>
            </div>

            <!-- Meal Log Entry Form -->
            <div class="diet-form-grid" style="margin-top: 10px;">
              <div class="setup-field">
                <label class="setup-label">DATE &amp; TIME</label>
                <input type="datetime-local" id="dietTimestamp" class="setup-input">
              </div>
              <div class="setup-field">
                <label class="setup-label">MEAL / EVENT TYPE</label>
                <select id="dietMealType" class="setup-select">
                  <option value="Breakfast">BREAKFAST</option>
                  <option value="Lunch">LUNCH</option>
                  <option value="Dinner">DINNER</option>
                  <option value="Snack">SNACK / REFRESHMENT</option>
                  <option value="Fasting">FASTING LOG</option>
                </select>
              </div>
              <div class="setup-field" style="grid-column: span 2;">
                <label class="setup-label">FOOD / BEVERAGE DESCRIPTION</label>
                <input type="text" id="dietDesc" class="setup-input" placeholder="e.g. Grilled salmon, roasted asparagus, riced cauliflower, olive oil, unsweetened tea">
              </div>
              <div class="setup-field">
                <label class="setup-label">ESTIMATED CARBS (G)</label>
                <input type="number" id="dietCarbs" class="setup-input" value="18" min="0" max="500">
              </div>
              <div class="setup-field">
                <label class="setup-label">PROTEIN (G)</label>
                <input type="number" id="dietProtein" class="setup-input" value="32" min="0" max="300">
              </div>
              <div class="setup-field">
                <label class="setup-label">CALORIES (KCAL)</label>
                <input type="number" id="dietCalories" class="setup-input" value="480" min="0" max="4000">
              </div>
              <div class="setup-field">
                <label class="setup-label">GLUCOSE CONTEXT</label>
                <select id="dietContext" class="setup-select">
                  <option value="Pre-Meal">PRE-MEAL (FASTING)</option>
                  <option value="1-Hr Post">1-HOUR POST-MEAL</option>
                  <option value="2-Hr Post">2-HOUR POST-MEAL</option>
                  <option value="Post-Meal Walk">PAIRED WITH POST-MEAL WALK</option>
                </select>
              </div>
            </div>
            <div style="display: flex; gap: 10px; margin-top: 10px;">
              <button class="lcars-btn btn-banana" style="flex: 2;" onclick="saveMealEntry()">RECORD MEAL &amp; CORRELATE SENSORS</button>
              <button class="lcars-btn btn-orange" style="flex: 1;" onclick="clearMealForm()">CLEAR FORM</button>
            </div>

            <!-- History of Logged Meals -->
            <div class="setup-section-title" style="margin-top: 12px;">
              <span>CHRONOLOGICAL NUTRITION &amp; MEAL JOURNAL</span>
              <button class="lcars-btn btn-orange" style="flex: none; padding: 4px 14px; font-size: 0.8rem;" onclick="clearAllMeals()">CLEAR JOURNAL</button>
            </div>
            <div class="diet-history-list" id="dietHistoryList"></div>
          </div>

          <!-- COMPONENT 4: DIET CHOICES & 1-TO-1 ADA TRADES COMPARISON TOOL -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-green);">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: var(--lcars-green);">04. DIET CHOICES &amp; 1-TO-1 ADA TRADES COMPARATOR</span>
                <span style="font-size: 0.8rem; color: #80c890; display: block;">PREVENTING GLYCEMIC SPIKES WITHOUT SACRIFICING PORTION SATIETY</span>
              </div>
              <div>
                <button class="lcars-btn btn-green" style="padding: 4px 14px; font-size: 0.8rem;" onclick="switchTab('diettrades')">OPEN FULL ADA TRADES ENGINE (TAB 09)</button>
              </div>
            </div>

            <div style="font-size: 0.88rem; color: #b8d0c0; line-height: 1.45; margin-bottom: 8px;">
              Compare common high-glycemic trigger foods with clinically validated 1-to-1 ADA trades. Selecting low-glycemic substitutes maintains steady insulin sensitivity and flatlines postprandial excursions:
            </div>

            <!-- Trade Comparison Grid -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 12px;">
              <!-- Trade 1 -->
              <div style="background: #06110a; border: 1px solid #1a3822; padding: 12px; border-radius: 6px;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <strong style="color: #ff8888; font-size: 0.95rem;">TRIGGER: WHITE RICE (1 CUP)</strong>
                  <span class="badge" style="background: rgba(255,85,85,0.2); color: #ff8888; font-size: 0.7rem;">+65 MG/DL SPIKE</span>
                </div>
                <div style="font-size: 0.82rem; color: #90a898; margin: 4px 0;">45g Net Carbs • Rapid pancreatic insulin surge • Post-meal fatigue</div>
                <div style="border-top: 1px dashed #1a3822; margin: 6px 0; padding-top: 6px;">
                  <strong style="color: var(--lcars-green); font-size: 0.95rem;">TRADE: RICED CAULIFLOWER</strong>
                  <span class="badge" style="background: rgba(85,255,119,0.2); color: var(--lcars-green); font-size: 0.7rem; float: right;">+4 MG/DL FLAT</span>
                  <div style="font-size: 0.82rem; color: #80c890; margin-top: 2px;">4g Net Carbs • High sulforaphane • Satiating fiber &amp; buttery flavor</div>
                </div>
              </div>

              <!-- Trade 2 -->
              <div style="background: #06110a; border: 1px solid #1a3822; padding: 12px; border-radius: 6px;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <strong style="color: #ff8888; font-size: 0.95rem;">TRIGGER: POTATO CHIPS / CRACKERS</strong>
                  <span class="badge" style="background: rgba(255,85,85,0.2); color: #ff8888; font-size: 0.7rem;">+48 MG/DL SPIKE</span>
                </div>
                <div style="font-size: 0.82rem; color: #90a898; margin: 4px 0;">28g Refined Starch • Fast absorption • Triggers snack cravings</div>
                <div style="border-top: 1px dashed #1a3822; margin: 6px 0; padding-top: 6px;">
                  <strong style="color: var(--lcars-green); font-size: 0.95rem;">TRADE: ROASTED ALMONDS / CELERY &amp; PB</strong>
                  <span class="badge" style="background: rgba(85,255,119,0.2); color: var(--lcars-green); font-size: 0.7rem; float: right;">+2 MG/DL FLAT</span>
                  <div style="font-size: 0.82rem; color: #80c890; margin-top: 2px;">3g Net Carbs • Healthy monounsaturated fats • Long-acting satiety</div>
                </div>
              </div>

              <!-- Trade 3 -->
              <div style="background: #06110a; border: 1px solid #1a3822; padding: 12px; border-radius: 6px;">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <strong style="color: #ff8888; font-size: 0.95rem;">TRIGGER: SODA / SWEET TEA</strong>
                  <span class="badge" style="background: rgba(255,85,85,0.2); color: #ff8888; font-size: 0.7rem;">+80 MG/DL SPIKE</span>
                </div>
                <div style="font-size: 0.82rem; color: #90a898; margin: 4px 0;">39g Free Fructose/Sucrose • Instant absorption • Hepatic steatosis risk</div>
                <div style="border-top: 1px dashed #1a3822; margin: 6px 0; padding-top: 6px;">
                  <strong style="color: var(--lcars-green); font-size: 0.95rem;">TRADE: SPARKLING WATER + FRESH MINT</strong>
                  <span class="badge" style="background: rgba(85,255,119,0.2); color: var(--lcars-green); font-size: 0.7rem; float: right;">0 MG/DL IMPACT</span>
                  <div style="font-size: 0.82rem; color: #80c890; margin-top: 2px;">0g Sugar • Crisp hydration • Zero vascular endothelial stress</div>
                </div>
              </div>
            </div>
          </div>

          <!-- COMPONENT 5: "WHAT WORKS FOR YOU" DIABETES LEARNING ENGINE & ADAPTIVE SCORECARD -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-ice);">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: var(--lcars-ice);">05. "WHAT WORKS FOR YOU" DIABETES LEARNING ENGINE</span>
                <span style="font-size: 0.8rem; color: var(--lcars-gray); display: block;">AUTOMATED CORRELATION OF SENSORS, MEALS, MOVEMENT &amp; MEDS</span>
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-btn btn-ice" style="padding: 4px 14px; font-size: 0.8rem;" onclick="generatePersonalHealthInsights()">REFRESH PERSONAL INSIGHTS</button>
              </div>
            </div>

            <!-- Dynamic Learning Insights Grid -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 12px; margin-top: 6px;">
              <!-- Insight 1: Exercise -->
              <div style="background: rgba(85,255,119,0.06); border: 1px solid var(--lcars-green); border-radius: 6px; padding: 14px;">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
                  <strong style="color: var(--lcars-green); font-size: 0.95rem;">🚶 MOVEMENT CORRELATION</strong>
                  <span style="font-family: var(--lcars-mono); font-size: 0.8rem; color: var(--lcars-green); font-weight: bold;">VERIFIED IMPACT</span>
                </div>
                <div style="font-size: 0.85rem; color: var(--lcars-text); line-height: 1.45;" id="insightMovement">
                  Taking a <strong>15–20 minute post-meal walk</strong> blunts your postprandial glucose spike by an average of <strong>-38 mg/dL</strong>. Your optimal timing is 15 minutes after finishing lunch or dinner.
                </div>
              </div>

              <!-- Insight 2: Carbs -->
              <div style="background: rgba(255,187,0,0.06); border: 1px solid var(--lcars-banana); border-radius: 6px; padding: 14px;">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
                  <strong style="color: var(--lcars-banana); font-size: 0.95rem;">🥗 CARB THRESHOLD DISCOVERY</strong>
                  <span style="font-family: var(--lcars-mono); font-size: 0.8rem; color: var(--lcars-banana); font-weight: bold;">TIME-IN-RANGE</span>
                </div>
                <div style="font-size: 0.85rem; color: var(--lcars-text); line-height: 1.45;" id="insightCarbs">
                  Meals containing <strong>&le;22g net carbs</strong> keep your blood sugar in 100% Time-in-Range (60–200 mg/dL). Meals above 40g carbs triggered excursions up to 185 mg/dL.
                </div>
              </div>

              <!-- Insight 3: Sequencing -->
              <div style="background: rgba(56,182,255,0.06); border: 1px solid var(--lcars-blue); border-radius: 6px; padding: 14px;">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
                  <strong style="color: var(--lcars-blue); font-size: 0.95rem;">🥩 MACRO SEQUENCING</strong>
                  <span style="font-family: var(--lcars-mono); font-size: 0.8rem; color: var(--lcars-blue); font-weight: bold;">GASTRIC PACING</span>
                </div>
                <div style="font-size: 0.85rem; color: var(--lcars-text); line-height: 1.45;" id="insightSequencing">
                  Pairing <strong>25g+ protein or salad fiber</strong> before carbohydrates slowed gastric absorption, cutting your peak glucose velocity from +2.2 mg/dL/min down to +0.8 mg/dL/min.
                </div>
              </div>

              <!-- Insight 4: Sleep & Meds -->
              <div style="background: rgba(204,153,255,0.06); border: 1px solid var(--lcars-lilac); border-radius: 6px; padding: 14px;">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
                  <strong style="color: var(--lcars-lilac); font-size: 0.95rem;">🌙 REST &amp; RECOVERY</strong>
                  <span style="font-family: var(--lcars-mono); font-size: 0.8rem; color: var(--lcars-lilac); font-weight: bold;">FASTING CONTROL</span>
                </div>
                <div style="font-size: 0.85rem; color: var(--lcars-text); line-height: 1.45;" id="insightSleepMeds">
                  Nights with <strong>SpO2 &gt;96% and 7+ hours of sleep</strong> correlate with <strong>14–18 mg/dL lower morning fasting glucose</strong>, suppressing sympathetic cortisol Dawn surges.
                </div>
              </div>
            </div>

            <!-- Personalized Actionable Summary Scorecard -->
            <div style="margin-top: 14px; background: #060b13; border: 1px solid #1a2a40; border-radius: 6px; padding: 14px; display: flex; flex-wrap: wrap; justify-content: space-between; align-items: center; gap: 12px;">
              <div>
                <strong style="color: var(--lcars-ice); font-size: 1rem; font-family: var(--lcars-mono);">YOUR PERSONALIZED DIABETES MANAGEMENT SCORECARD</strong>
                <div style="font-size: 0.84rem; color: var(--lcars-text); margin-top: 4px;">
                  Overall 360° Control Score: <span style="color: var(--lcars-green); font-weight: bold;">88 / 100 • EXCELLENT METABOLIC PROGRESS</span> | Time-in-Range: <span style="color: var(--lcars-green); font-weight: bold;">86.4%</span>
                </div>
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-btn btn-peach" onclick="switchTab('reports')">VIEW 360° SYNCHRONIZED TALL CHART</button>
                <button class="lcars-btn btn-orange" onclick="switchTab('medication')">CHECK MEDICATION PROGRESS</button>
              </div>
            </div>
          </div>

        </div>
      </div>
'''
