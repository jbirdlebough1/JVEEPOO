import re

def update_reports_sub_reports():
    path = r"D:\veepoo\w11_htm_app\index.html"
    with open(path, "r", encoding="utf-8") as f:
        html = f.read()

    print(f"Loaded index.html, size = {len(html)}")

    # 1. Navigation Strip of Sub-Report Buttons
    nav_strip_html = '''
          <!-- =========================================================================
               CLINICAL BIOMETRIC WATCH ANALYSIS SUB-REPORTS NAVIGATION STRIP
               ========================================================================= -->
          <div style="background: #ffffff; border: 1.5px solid #cbd5e1; border-top: 4px solid #0284c7; border-radius: 8px; padding: 12px 16px; margin-bottom: 18px; box-shadow: 0 1px 4px rgba(0,0,0,0.05);">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px; flex-wrap: wrap; gap: 8px;">
              <div>
                <strong style="color: #0369a1; font-size: 0.95rem; font-family: var(--lcars-mono); letter-spacing: 0.05em;">
                  📋 CLINICAL WATCH BIOMETRIC ANALYSIS SUB-REPORTS (ONE-CLICK JUMP)
                </strong>
                <span style="font-size: 0.76rem; color: #64748b; display: block; margin-top: 1px;">
                  INSTANT NAVIGATION ACROSS ALL 6 ADVANCED SENSING MODALITIES &bull; 100% PURE WHITE BACKGROUND PARITY
                </span>
              </div>
              <div style="font-size: 0.75rem; color: #15803d; font-family: var(--lcars-mono); font-weight: bold;">
                ● 8 BIOMETRIC CHANNELS &bull; 6 CLINICAL MODULES ACTIVE
              </div>
            </div>

            <!-- Collection of Quick Sub-Report Jump Buttons -->
            <div style="display: flex; gap: 6px; flex-wrap: wrap;" id="subReportBtnStrip">
              <button class="lcars-btn btn-peach" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubMetabolism')">
                🔥 01. METABOLISM &amp; GLUT4
              </button>
              <button class="lcars-btn btn-orange" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubEcgStress')">
                💓 02. ECG STRESS &amp; HRV
              </button>
              <button class="lcars-btn btn-green" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubMet')">
                ⚡ 03. MET LOAD INDEX
              </button>
              <button class="lcars-btn btn-banana" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubBloodComp')">
                🩸 04. BLOOD COMPOSITION
              </button>
              <button class="lcars-btn btn-lilac" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubBodyComp')">
                ⚖️ 05. BODY COMP (BIA)
              </button>
              <button class="lcars-btn btn-blue" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSubBreath')">
                🫁 06. BREATH (14 RPM)
              </button>
              <button class="lcars-btn btn-ice" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxSynchronizedTallChart')">
                📊 07. 360° 8-LANE STACK
              </button>
              <button class="lcars-btn btn-green" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxGoalComplianceAudit')">
                🎯 08. % GOALS MET (85.4%)
              </button>
              <button class="lcars-btn btn-peach" style="font-size: 0.76rem; padding: 5px 12px; font-weight: bold;" onclick="jumpToSubReport('boxExerciseMetabolicCorrelation')">
                📈 09. STEP CORRELATOR
              </button>
            </div>
          </div>
'''
    if 'CLINICAL BIOMETRIC WATCH ANALYSIS SUB-REPORTS NAVIGATION STRIP' not in html:
        pos = html.find('<!-- SINGLE TALL SYNCHRONIZED MULTI-MEASUREMENT TIMELINE CHART -->')
        if pos != -1:
            html = html[:pos] + nav_strip_html + '\n          ' + html[pos:]
            print("[✔] Injected Sub-Reports Navigation Strip into Tab 04")
        else:
            print("[!] Could not find SINGLE TALL SYNCHRONIZED marker")
    else:
        print("[*] Sub-Reports Navigation Strip already present")

    # 2. Add the 6 Specific Sub-Report Cards right after boxExerciseMetabolicCorrelation
    sub_reports_cards_html = '''
          <!-- =========================================================================
               SUB-REPORT 01: METABOLISM, BMR & NON-INSULIN GLUT4 CLEARANCE
               ========================================================================= -->
          <div class="chart-box" id="boxSubMetabolism" style="margin-bottom: 16px; border-left: 6px solid #ea580c; background: #ffffff;">
            <div class="chart-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: #c2410c; font-size: 1.1rem; font-weight: 900; letter-spacing: 0.05em;">
                  🔥 01. METABOLISM &amp; GLUT4 MUSCULAR GLUCOSE CLEARANCE
                </span>
                <span style="font-family: var(--lcars-mono); color: #475569; display: block; font-size: 0.8rem; margin-top: 2px;">
                  CONTINUOUS 24-HOUR ENERGY METABOLISM &bull; BASAL RATE &bull; SUBSTRATE OXIDATION (RER 0.82)
                </span>
              </div>
              <span class="badge" style="background: #fff7ed; color: #c2410c; border: 1.5px solid #ea580c; font-weight: 800; font-size: 0.78rem;">
                GLUT4 EFFICIENCY: 86 / 100 (OPTIMAL)
              </span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">BASAL METABOLIC RATE (BMR)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">1,680 <span style="font-size: 0.8rem;">kcal/day</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">Resting cellular baseline</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">TOTAL ENERGY (TDEE)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #c2410c; font-family: var(--lcars-mono);">2,340 <span style="font-size: 0.8rem;">kcal/day</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">+660 Active Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SUBSTRATE RATIO (RER)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">0.82</div>
                <div style="font-size: 0.7rem; color: #0f172a;">60% Lipid / 40% Carb Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">GLUT4 CLEARANCE VELOCITY</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">+280%</div>
                <div style="font-size: 0.7rem; color: #15803d;">Post-meal walk acceleration</div>
              </div>
            </div>
            <div style="margin-top: 10px; font-size: 0.76rem; color: #334155; line-height: 1.45; background: #fff7ed; padding: 8px 12px; border-radius: 4px; border: 1px solid #fed7aa;">
              <strong>Clinical Significance:</strong> Muscular contraction during 15-minute post-meal GLUT4 walks translocates glucose transporters independently of insulin, accelerating systemic clearance from $0.012\text{ min}^{-1}$ (resting) to $0.042\text{ min}^{-1}$ (active), blunting peak excursions by up to 42 mg/dL.
            </div>
          </div>

          <!-- =========================================================================
               SUB-REPORT 02: ECG STRESS & AUTONOMIC SYMPATHOVAGAL BALANCE
               ========================================================================= -->
          <div class="chart-box" id="boxSubEcgStress" style="margin-bottom: 16px; border-left: 6px solid #e11d48; background: #ffffff;">
            <div class="chart-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: #be123c; font-size: 1.1rem; font-weight: 900; letter-spacing: 0.05em;">
                  💓 02. ECG STRESS, INTERVALS &amp; AUTONOMIC BALANCE
                </span>
                <span style="font-family: var(--lcars-mono); color: #475569; display: block; font-size: 0.8rem; margin-top: 2px;">
                  LEAD-I CARDIAC VECTOR &bull; QTc DURATION &bull; SYMPATHOVAGAL LF/HF RATIO
                </span>
              </div>
              <div style="display: flex; gap: 6px;">
                <button class="lcars-btn btn-orange" style="font-size: 0.75rem; padding: 3px 10px;" onclick="jumpToSubReport('boxChartHRV')">
                  📊 VIEW 2D LORENZ SCATTER (HBAND)
                </button>
              </div>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">QTc INTERVAL</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">412 <span style="font-size: 0.8rem;">ms</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">Normal (&lt;440 ms)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">PHYSICAL AUTONOMIC STRESS</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">28 / 100</div>
                <div style="font-size: 0.7rem; color: #15803d;">Low Physiological Strain</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SYMPATHOVAGAL (LF/HF)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">1.22</div>
                <div style="font-size: 0.7rem; color: #0f172a;">Target: 1.0 - 1.5</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CARDIAC RESILIENCE</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">88 / 100</div>
                <div style="font-size: 0.7rem; color: #15803d;">Healthy Vagal Tone (Comet)</div>
              </div>
            </div>
          </div>

          <!-- =========================================================================
               SUB-REPORT 03: MET (METABOLIC EQUIVALENT OF TASK) ACTIVITY INDEX
               ========================================================================= -->
          <div class="chart-box" id="boxSubMet" style="margin-bottom: 16px; border-left: 6px solid #16a34a; background: #ffffff;">
            <div class="chart-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: #15803d; font-size: 1.1rem; font-weight: 900; letter-spacing: 0.05em;">
                  ⚡ 03. MET (METABOLIC EQUIVALENT OF TASK) ENERGY EXPENDITURE
                </span>
                <span style="font-family: var(--lcars-mono); color: #475569; display: block; font-size: 0.8rem; margin-top: 2px;">
                  INTENSITY MAPPING &bull; DAILY CUMULATIVE MET-HOURS &bull; ADA PHYSICAL ACTIVITY STANDARD
                </span>
              </div>
              <span class="badge" style="background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; font-weight: 800; font-size: 0.78rem;">
                34.2 MET-HRS / DAY (EXCEEDS &ge; 28 GOAL)
              </span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING / SLEEP (1.0 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">7.6 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">7.6 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SEDENTARY (1.2 - 1.4 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #b45309; font-family: var(--lcars-mono);">9.2 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">11.9 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">MODERATE WALK (3.5 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">1.8 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">6.3 MET-hrs (GLUT4)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">VIGOROUS ACTIVATION (&ge;6 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">18 <span style="font-size: 0.8rem;">min</span></div>
                <div style="font-size: 0.7rem; color: #0284c7;">1.8 MET-hrs</div>
              </div>
            </div>
          </div>

          <!-- =========================================================================
               SUB-REPORT 04: BLOOD COMPOSITION, VISCOSITY & LIPIDS TONOMETRY
               ========================================================================= -->
          <div class="chart-box" id="boxSubBloodComp" style="margin-bottom: 16px; border-left: 6px solid #d97706; background: #ffffff;">
            <div class="chart-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: #b45309; font-size: 1.1rem; font-weight: 900; letter-spacing: 0.05em;">
                  🩸 04. BLOOD COMPOSITION, VISCOSITY &amp; LIPID TONOMETRY
                </span>
                <span style="font-family: var(--lcars-mono); color: #475569; display: block; font-size: 0.8rem; margin-top: 2px;">
                  MICROVASCULAR RHEOLOGY &bull; TOTAL LIPID ESTIMATE &bull; SERUM URIC ACID &bull; CAPILLARY PERFUSION
                </span>
              </div>
              <span class="badge" style="background: #fef3c7; color: #92400e; border: 1.5px solid #d97706; font-weight: 800; font-size: 0.78rem;">
                PERFUSION INDEX: 92 / 100 (HIGH)
              </span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">EST. BLOOD VISCOSITY</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">3.8 <span style="font-size: 0.8rem;">cP</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">Normal (3.5 - 4.5 cP)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">TOTAL LIPID PROXY</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">178 <span style="font-size: 0.8rem;">mg/dL</span></div>
                <div style="font-size: 0.7rem; color: #15803d;">Optimal (&lt;200)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SERUM URIC ACID</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">5.2 <span style="font-size: 0.8rem;">mg/dL</span></div>
                <div style="font-size: 0.7rem; color: #15803d;">Target &lt;6.5 (No Gout Risk)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">OXYGEN P50 AFFINITY</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">26.8 <span style="font-size: 0.8rem;">mmHg</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">Normal dissociation curve</div>
              </div>
            </div>
          </div>

          <!-- =========================================================================
               SUB-REPORT 05: BODY COMPOSITION & MULTI-FREQUENCY BIOIMPEDANCE (BIA)
               ========================================================================= -->
          <div class="chart-box" id="boxSubBodyComp" style="margin-bottom: 16px; border-left: 6px solid #7c3aed; background: #ffffff;">
            <div class="chart-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: #6d28d9; font-size: 1.1rem; font-weight: 900; letter-spacing: 0.05em;">
                  ⚖️ 05. BODY COMPOSITION &amp; BIOELECTRICAL IMPEDANCE (BIA)
                </span>
                <span style="font-family: var(--lcars-mono); color: #475569; display: block; font-size: 0.8rem; margin-top: 2px;">
                  SKELETAL MUSCLE SINK &bull; BODY FAT % &bull; VISCERAL ADIPOSE TISSUE &bull; CELLULAR WATER BALANCE
                </span>
              </div>
              <span class="badge" style="background: #f5f3ff; color: #6d28d9; border: 1.5px solid #7c3aed; font-weight: 800; font-size: 0.78rem;">
                VISCERAL FAT: LEVEL 7 (LOW RISK)
              </span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">BODY FAT PERCENTAGE</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">22.4%</div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">Healthy Adult Range</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SKELETAL MUSCLE MASS</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">34.2 <span style="font-size: 0.8rem;">kg</span></div>
                <div style="font-size: 0.7rem; color: #15803d;">Protective Glucose Sink</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">BODY MASS INDEX (BMI)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">24.8 <span style="font-size: 0.8rem;">kg/m²</span></div>
                <div style="font-size: 0.7rem; color: #15803d;">Normal Range (18.5 - 24.9)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CELLULAR WATER RATIO</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">0.62</div>
                <div style="font-size: 0.7rem; color: #15803d;">No Peripheral Edema</div>
              </div>
            </div>
          </div>

          <!-- =========================================================================
               SUB-REPORT 06: BREATH & RESPIRATORY MECHANICS (14 RPM)
               ========================================================================= -->
          <div class="chart-box" id="boxSubBreath" style="margin-bottom: 16px; border-left: 6px solid #0284c7; background: #ffffff;">
            <div class="chart-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: #0369a1; font-size: 1.1rem; font-weight: 900; letter-spacing: 0.05em;">
                  🫁 06. BREATH, RESPIRATION RATE &amp; NOCTURNAL MECHANICS
                </span>
                <span style="font-family: var(--lcars-mono); color: #475569; display: block; font-size: 0.8rem; margin-top: 2px;">
                  PPG RESPIRATORY SINUS ARRHYTHMIA &bull; RESTING RPM &bull; NOCTURNAL AHI APNEA SCREENING
                </span>
              </div>
              <span class="badge" style="background: #eff6ff; color: #0284c7; border: 1.5px solid #0284c7; font-weight: 800; font-size: 0.78rem;">
                AHI: 2.4 / HR (NORMAL &bull; NO OSAHS RISK)
              </span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING RESPIRATION</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">14 <span style="font-size: 0.8rem;">RPM</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">Optimal (12 - 18 RPM)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">NOCTURNAL AHI INDEX</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">2.4 <span style="font-size: 0.8rem;">/hr</span></div>
                <div style="font-size: 0.7rem; color: #15803d;">Normal (&lt;5 events/hr)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">LONGEST NOCTURNAL PAUSE</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">8 <span style="font-size: 0.8rem;">sec</span></div>
                <div style="font-size: 0.7rem; color: #15803d;">Safe (&lt;10s threshold)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">BREATH REGULARITY SCORE</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">95.8%</div>
                <div style="font-size: 0.7rem; color: #15803d;">High Stability</div>
              </div>
            </div>
          </div>
'''
    if 'id="boxSubMetabolism"' not in html:
        pos = html.find('id="boxExerciseMetabolicCorrelation"')
        if pos != -1:
            box_end = html.find('</div>\n          </div>', pos)
            if box_end == -1:
                box_end = html.find('</div>\r\n          </div>', pos)
            if box_end == -1:
                box_end = html.find('</div>', pos + 1000)
            html = html[:box_end+6] + '\n\n' + sub_reports_cards_html + '\n' + html[box_end+6:]
            print("[✔] Injected all 6 Sub-Reports cards into Tab 04")
        else:
            print("[!] Could not find boxExerciseMetabolicCorrelation")
    else:
        print("[*] Sub-Reports cards already present")

    # 3. Add jumpToSubReport JavaScript function
    jump_fn = '''
    function jumpToSubReport(elementId) {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      const el = document.getElementById(elementId);
      if (!el) return;
      el.scrollIntoView({ behavior: 'smooth', block: 'start' });
      el.style.transition = 'box-shadow 0.3s ease, border-color 0.3s ease';
      el.style.boxShadow = '0 0 20px rgba(2, 132, 199, 0.4)';
      el.style.borderColor = '#0284c7';
      setTimeout(() => {
        el.style.boxShadow = 'none';
        el.style.borderColor = '#cbd5e1';
      }, 1600);
    }
'''
    if 'function jumpToSubReport' not in html:
        pos = html.find('function saveClinicalGoals()')
        if pos != -1:
            html = html[:pos] + jump_fn + '\n    ' + html[pos:]
            print("[✔] Injected jumpToSubReport JavaScript function")

    with open(path, "w", encoding="utf-8") as f:
        f.write(html)

    # Sync to root index.html
    import shutil
    shutil.copyfile(path, "D:/veepoo/index.html")
    print("[✔] Synchronized to D:/veepoo/index.html")

if __name__ == '__main__':
    update_reports_sub_reports()
