import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

def get_slide(num_str):
    for s in slides:
        if s['num'] == num_str:
            return s
    return None

# =============================================================================
# SLIDE 35: Estimated A1C (eA1C) Projection Curve - SHOW MATH IN RIGHT PANE
# =============================================================================
s35 = get_slide('35')
if s35:
    s35['right_header'] = 'ADAG MATHEMATICAL eA1C CONSENSUS MODEL'
    s35['right_badge'] = 'ADAG EQUATION (NATHAN ET AL.)'
    s35['right_badge_col'] = '#0284c7'
    s35['right_html'] = """
          <!-- ADAG Mathematical A1C Modeling Card -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a; font-family: monospace;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-size: 0.72rem; color: #0284c7; font-weight: 800;">ADAG CONSENSUS EQUATION (DIABETES CARE 2008)</span>
              <span style="background: #0284c7; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px;">eA1C MODEL</span>
            </div>

            <!-- Core Formulas -->
            <div style="background: #eff6ff; border: 1.5px solid #bfdbfe; border-radius: 5px; padding: 8px; margin-bottom: 8px;">
              <div style="font-size: 0.70rem; color: #1e40af; font-weight: 800; margin-bottom: 4px;">1. ROLLING ESTIMATED A1C FORMULA:</div>
              <div style="font-size: 0.95rem; font-weight: 900; color: #1e3a8a; text-align: center; padding: 4px; background: #ffffff; border-radius: 4px; border: 1px solid #93c5fd;">
                eA1C (%) = (Mean Glucose + 46.7) / 28.7
              </div>
              <div style="font-size: 0.68rem; color: #1e40af; font-weight: 800; margin-top: 6px; margin-bottom: 4px;">2. INVERSE MEAN GLUCOSE EQUIVALENT:</div>
              <div style="font-size: 0.90rem; font-weight: 900; color: #1e3a8a; text-align: center; padding: 4px; background: #ffffff; border-radius: 4px; border: 1px solid #93c5fd;">
                Mean Glucose (mg/dL) = (28.7 × eA1C) - 46.7
              </div>
            </div>

            <!-- Mathematical Sensitivity Derivative -->
            <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 4px; padding: 6px; margin-bottom: 8px; font-size: 0.67rem;">
              <div style="color: #166534; font-weight: bold;">SENSITIVITY DERIVATIVE:</div>
              <div style="color: #14532d; margin-top: 2px;">
                d(eA1C) / d(MeanG) = 1 / 28.7 ≈ <strong>0.0348% per mg/dL</strong>
              </div>
              <div style="color: #15803d; font-weight: bold; margin-top: 2px;">
                ➔ Every -28.7 mg/dL sustained drop lowers eA1C by exactly -1.0%.
              </div>
            </div>

            <!-- Conversion Table -->
            <table style="width: 100%; border-collapse: collapse; font-size: 0.65rem; background: #ffffff; border: 1px solid #e2e8f0; border-radius: 4px; overflow: hidden;">
              <thead>
                <tr style="background: #f1f5f9; color: #334155; border-bottom: 1px solid #cbd5e1;">
                  <th style="padding: 4px; text-align: left;">MEAN GLUCOSE</th>
                  <th style="padding: 4px; text-align: center;">eA1C (%)</th>
                  <th style="padding: 4px; text-align: right;">CLINICAL STATUS</th>
                </tr>
              </thead>
              <tbody>
                <tr style="border-bottom: 1px solid #f1f5f9;">
                  <td style="padding: 3px 4px; font-weight: bold; color: #15803d;">126 mg/dL</td>
                  <td style="padding: 3px 4px; text-align: center; font-weight: bold; color: #15803d;">6.0%</td>
                  <td style="padding: 3px 4px; text-align: right; color: #166534;">Optimal Non-Diabetic</td>
                </tr>
                <tr style="border-bottom: 1px solid #f1f5f9;">
                  <td style="padding: 3px 4px; font-weight: bold; color: #0284c7;">154 mg/dL</td>
                  <td style="padding: 3px 4px; text-align: center; font-weight: bold; color: #0284c7;">7.0%</td>
                  <td style="padding: 3px 4px; text-align: right; color: #0c4a6e;">ADA Recommended Target</td>
                </tr>
                <tr style="border-bottom: 1px solid #f1f5f9;">
                  <td style="padding: 3px 4px; font-weight: bold; color: #d97706;">183 mg/dL</td>
                  <td style="padding: 3px 4px; text-align: center; font-weight: bold; color: #d97706;">8.0%</td>
                  <td style="padding: 3px 4px; text-align: right; color: #92400e;">Sub-Optimal Control</td>
                </tr>
                <tr>
                  <td style="padding: 3px 4px; font-weight: bold; color: #dc2626;">212 mg/dL</td>
                  <td style="padding: 3px 4px; text-align: center; font-weight: bold; color: #dc2626;">9.0%</td>
                  <td style="padding: 3px 4px; text-align: right; color: #991b1b;">High Microvascular Risk</td>
                </tr>
              </tbody>
            </table>
          </div>
    """

# =============================================================================
# SLIDE 37: Blood Pressure Dual-Band - ADD DISCUSSION TEXT IN RIGHT PANE
# =============================================================================
s37 = get_slide('37')
if s37:
    s37['right_header'] = 'PWTT ARTERIAL TONOMETRY & NOCTURNAL DIPPING'
    s37['right_badge'] = 'PWTT HEMODYNAMICS'
    s37['right_badge_col'] = '#ea580c'
    s37['right_html'] = """
          <!-- PWTT & Nocturnal Dipping Detailed Clinical Discussion -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a; font-family: monospace; font-size: 0.67rem; line-height: 1.4;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-size: 0.72rem; color: #ea580c; font-weight: 800;">AMBULATORY PWTT TONOMETRY & DIPPING</span>
              <span style="background: #ea580c; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px;">CLINICAL AUDIT</span>
            </div>

            <!-- PWTT Biomechanics -->
            <div style="background: #fff7ed; border: 1px solid #fed7aa; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <strong style="color: #9a3412; font-size: 0.70rem;">1. OPTICAL PULSE WAVE TRANSIT TIME (PWTT):</strong>
              <div style="color: #7c2d12; margin-top: 2px;">
                Derived from the Bramwell-Hill relationship: as arterial pressure rises, vascular walls stiffen, accelerating pulse wave propagation. Measuring transit time from the cardiac R-peak to the distal PPG pulse foot yields continuous systolic and diastolic trends without disruptive cuff inflations.
              </div>
            </div>

            <!-- Outpatient Blood Pressure Targets -->
            <div style="background: #eff6ff; border: 1px solid #bfdbfe; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <strong style="color: #1e40af; font-size: 0.70rem;">2. CLINICAL TARGET STANDARDS (AHA / ACC):</strong>
              <div style="color: #1e3a8a; margin-top: 2px;">
                • <strong>Normotensive Target:</strong> &lt;120 mmHg Systolic and &lt;80 mmHg Diastolic.<br>
                • <strong>Hypertension Stage 1:</strong> 130–139 / 80–89 mmHg.<br>
                • Eliminates "White-Coat" anxiety spikes by evaluating 288 ambulatory measurements across routine daily activities.
              </div>
            </div>

            <!-- Nocturnal Dipping Stratification -->
            <div style="background: #f0fdf4; border: 1.5px solid #86efac; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <strong style="color: #15803d; font-size: 0.70rem;">3. NOCTURNAL DIPPING CLASSIFICATION:</strong>
              <div style="color: #14532d; margin-top: 2px;">
                • <strong>Normal Dippers (10%–20% Drop):</strong> Demonstrates healthy nocturnal parasympathetic dominance and vascular decompression.<br>
                • <strong>Non-Dippers (&lt;10% Drop):</strong> Signals impaired autonomic regulation; strongly correlated with diabetic nephropathy and microalbuminuria.<br>
                • <strong>Reverse Dippers (Nocturnal Rise):</strong> 3.8× elevated hazard ratio for major adverse cardiac events.
              </div>
            </div>

            <div style="background: #f1f5f9; border-left: 3px solid #0284c7; padding: 5px 8px; border-radius: 3px; font-size: 0.65rem; color: #334155;">
              <strong>DIABETIC CARDIOVASCULAR PROTECTION:</strong> Continuous nocturnal dipping monitoring empowers endocrinologists to schedule evening antihypertensive timing (e.g. ACE/ARB chrono-therapy) to restore protective dipping.
            </div>
          </div>
    """

# =============================================================================
# SLIDE 38: Nocturnal SpO2 & OSAHS - ADD DETAIL IN RIGHT SIDE PANE
# =============================================================================
s38 = get_slide('38')
if s38:
    s38['right_header'] = 'PULSE OXIMETRY & APNEA-CORTISOL GLYCEMIC AXIS'
    s38['right_badge'] = 'ODI-4% HYPOXIA MATRIX'
    s38['right_badge_col'] = '#dc2626'
    s38['right_html'] = """
          <!-- Dual-Wavelength SpO2 & Apnea-Cortisol Detail Card -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a; font-family: monospace; font-size: 0.67rem; line-height: 1.4;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-size: 0.72rem; color: #dc2626; font-weight: 800;">DUAL-WAVELENGTH OXIMETRY & APNEA</span>
              <span style="background: #dc2626; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px;">RATIO-OF-RATIOS</span>
            </div>

            <!-- Optical Physics Ratio-of-Ratios -->
            <div style="background: #eff6ff; border: 1px solid #bfdbfe; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <strong style="color: #1e40af; font-size: 0.70rem;">1. DUAL-WAVELENGTH OPTICAL RATIO (R):</strong>
              <div style="color: #1e3a8a; margin-top: 2px;">
                R = [AC(660nm) / DC(660nm)] / [AC(940nm) / DC(940nm)]. Deoxygenated hemoglobin preferentially absorbs 660nm red light, while oxygenated HbO2 absorbs 940nm infrared. LCARS samples arterial pulse absorption continuously at 1% saturation resolution.
              </div>
            </div>

            <!-- ODI Criteria -->
            <div style="background: #fffbeb; border: 1px solid #fde68a; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <strong style="color: #92400e; font-size: 0.70rem;">2. OXYGEN DESATURATION INDEX (ODI-4%):</strong>
              <div style="color: #78350f; margin-top: 2px;">
                Quantifies nocturnal dips ≥4% below baseline lasting ≥10 seconds.<br>
                • Normal: &lt;5 dips/hour • Mild OSA: 5–14 • Moderate: 15–29 • Severe: ≥30.<br>
                Sustained baseline below 95% indicates obstructive hypoventilation.
              </div>
            </div>

            <!-- Endocrine Cortisol Surge Mechanism -->
            <div style="background: #fef2f2; border: 1.5px solid #fecaca; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <strong style="color: #dc2626; font-size: 0.70rem;">3. THE APNEA-CORTISOL GLYCEMIC SURGE:</strong>
              <div style="color: #7f1d1d; margin-top: 2px;">
                Nocturnal airway collapse triggers carotid chemoreceptors, spiking sympathetic catecholamines and cortisol. Unsuppressed cortisol stimulates hepatic glycogenolysis, driving refractory morning fasting spikes (&gt;180 mg/dL) that resist standard evening medication.
              </div>
            </div>

            <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 3px; padding: 5px 8px; font-size: 0.65rem; color: #166534;">
              <strong>CLINICAL INTERVENTION:</strong> Telemetry screens for occult sleep apnea, justifying home sleep studies, CPAP compliance, and bedtime airway optimization to stabilize morning glycemia.
            </div>
          </div>
    """

# =============================================================================
# SLIDE 40: Autonomic HRV & Lorenz Dynamics - SHOW MATH IN RIGHT PANE
# =============================================================================
s40 = get_slide('40')
if s40:
    s40['right_header'] = 'AUTONOMIC HRV & LORENZ SCATTER MATHEMATICS'
    s40['right_badge'] = 'POINCARÉ GEOMETRY (SD1 / SD2)'
    s40['right_badge_col'] = '#7c3aed'
    s40['right_html'] = """
          <!-- Autonomic HRV & Poincaré Lorenz Geometry Card -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a; font-family: monospace; font-size: 0.67rem;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-size: 0.72rem; color: #7c3aed; font-weight: 800;">HRV TIME-DOMAIN & POINCARÉ GEOMETRY</span>
              <span style="background: #7c3aed; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px;">AUTONOMIC TONE</span>
            </div>

            <!-- Time-Domain Formulas -->
            <div style="background: #eff6ff; border: 1px solid #bfdbfe; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <div style="color: #1e40af; font-weight: bold;">1. TIME-DOMAIN STANDARD METRICS:</div>
              <div style="color: #1e3a8a; margin-top: 3px;">
                • <strong>Inter-Beat Interval:</strong> RR_i = t_i - t_{i-1} (ms)<br>
                • <strong>Total Autonomic Power:</strong> SDNN = √[ 1/(N-1) ∑ (RR_i - Mean_RR)² ]<br>
                • <strong>Parasympathetic Vagal Tone:</strong> RMSSD = √[ 1/(N-1) ∑ (RR_{i+1} - RR_i)² ]
              </div>
            </div>

            <!-- Poincaré Lorenz Scatter Geometry -->
            <div style="background: #f5f3ff; border: 1.5px solid #ddd6fe; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <div style="color: #5b21b6; font-weight: bold;">2. LORENZ 2D SCATTER ELLIPSE EQUATIONS (RR_i vs RR_{i+1}):</div>
              <div style="color: #4c1d95; margin-top: 3px;">
                • <strong>Transverse Axis (Instantaneous Vagal):</strong><br>
                &nbsp;&nbsp;SD1 = √[ 1/2 Var(RR_{i+1} - RR_i) ] = <strong>RMSSD / √2</strong><br>
                • <strong>Longitudinal Axis (Total / Sympathetic):</strong><br>
                &nbsp;&nbsp;SD2 = √[ 2·SDNN² - 1/2·RMSSD² ]<br>
                • <strong>Sympatho-Vagal Balance Ratio:</strong><br>
                &nbsp;&nbsp;Ratio = <strong>SD1 / SD2</strong> (Normal resting ratio: 0.45 – 0.55)
              </div>
            </div>

            <!-- Clinical Diagnostic Table -->
            <div style="background: #ffffff; border: 1px solid #e2e8f0; border-radius: 4px; padding: 6px; font-size: 0.65rem;">
              <div style="font-weight: bold; color: #334155; margin-bottom: 3px;">CLINICAL MORPHOLOGY DIAGNOSTIC:</div>
              <div style="color: #15803d; font-weight: bold;">• Comet Shape (Normal):</div>
              <div style="color: #14532d; margin-bottom: 3px;">Wide dispersion, robust parasympathetic vagal recovery (SD1 &gt; 25 ms).</div>
              <div style="color: #dc2626; font-weight: bold;">• Constricted Torpedo (Diabetic Neuropathy):</div>
              <div style="color: #7f1d1d;">Narrow scatter along identity line (SD1 &lt; 12 ms), indicating autonomic exhaustion and cardiovascular vulnerability.</div>
            </div>
          </div>
    """

# =============================================================================
# SLIDE 41: Nocturnal Sleep Architecture - ADD TEXT DETAIL IN RIGHT PANE
# =============================================================================
s41 = get_slide('41')
if s41:
    s41['right_header'] = 'POLYSOMNOGRAPHIC ARCHITECTURE & GLYCEMIA'
    s41['right_badge'] = 'SOMNO-03 HYPNOGRAM'
    s41['right_badge_col'] = '#0284c7'
    s41['right_html'] = """
          <!-- Polysomnographic Sleep Stages & Endocrine Feedback Card -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a; font-family: monospace; font-size: 0.67rem; line-height: 1.4;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-size: 0.72rem; color: #0284c7; font-weight: 800;">MULTISENSOR POLYSOMNOGRAPHY & STAGING</span>
              <span style="background: #0284c7; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px;">SLEEP MATRIX</span>
            </div>

            <!-- Multisensor Modeling -->
            <div style="background: #eff6ff; border: 1px solid #bfdbfe; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <strong style="color: #1e40af; font-size: 0.70rem;">1. MULTISENSOR SLEEP STAGE SYNTHESIS:</strong>
              <div style="color: #1e3a8a; margin-top: 2px;">
                Synthesizes tri-axial accelerometer micro-movement zero-crossings, optical PPG pulse rate deceleration, and wrist epidermal heat-flux nadirs to accurately classify nocturnal epochs without cumbersome clinical polysomnography leads.
              </div>
            </div>

            <!-- Four Hypnogram Stages -->
            <div style="background: #f0fdf4; border: 1.5px solid #86efac; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <strong style="color: #15803d; font-size: 0.70rem;">2. THE FOUR HYPNOGRAM STAGES & CLINICAL BENCHMARKS:</strong>
              <div style="color: #14532d; margin-top: 2px;">
                • <strong>Deep Delta Sleep (N3):</strong> Target 1.5–2.0h (&gt;20%). Slow-wave delta rhythms suppress pituitary ACTH, blunting overnight cortisol and re-sensitizing muscle GLUT4 insulin receptors.<br>
                • <strong>Light Sleep (N1/N2):</strong> 50%–60% of total rest; physiological baseline rest.<br>
                • <strong>REM Dreaming (R):</strong> 20%–25%; autonomic and neurovascular restoration.<br>
                • <strong>Awake Epochs:</strong> &lt;5%; minimal micro-arousals.
              </div>
            </div>

            <!-- Endocrine-Glycemic Axis -->
            <div style="background: #fef2f2; border: 1px solid #fecaca; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <strong style="color: #dc2626; font-size: 0.70rem;">3. THE SLEEP-GLYCEMIC CASCADE:</strong>
              <div style="color: #7f1d1d; margin-top: 2px;">
                Patients achieving &lt;15% deep sleep exhibit a <strong>23% reduction in whole-body insulin sensitivity</strong>. Unsuppressed nocturnal cortisol accelerates hepatic gluconeogenesis, producing stubborn morning fasting blood glucose spikes (&gt;170 mg/dL).
              </div>
            </div>

            <div style="background: #f1f5f9; border-left: 3px solid #0284c7; padding: 5px 8px; border-radius: 3px; font-size: 0.65rem; color: #334155;">
              <strong>ACTIONABLE SLEEP HYGIENE:</strong> Auditing hypnogram architecture verifies that evening meal timing, bedtime routine, and CPAP compliance actively protect glycemic resilience.
            </div>
          </div>
    """

# =============================================================================
# SLIDE 43: Tab 11 Architecture - SCROLL AS YOU TALK SCREEN
# =============================================================================
s43 = get_slide('43')
if s43:
    s43['tab_screenshot'] = 'images/lcars_slide43_medication_tall.png'
    s43['tab_title'] = '11-MEDICATION CONSOLE • ACTIVE PHARMACOLOGICAL REGIMEN'
    s43['narration'] = (
        "Displayed on your screen is Tab 11 Medication Management Console. "
        "Diabetes care relies on strict pharmacological adherence, and as this screen scrolls downward, "
        "notice how El-CARS organizes your prescribed medications into a clear, unified architecture. "
        "At the top, our critical clinical notice emphasizes that all drug titrations must be directed by your licensed physician. "
        "Directly below in Component 1, your active regimen is split into two discrete intake schedules: "
        "the Morning Breakfast Suite and the Evening Dinner Split. "
        "Further down in Component 2, you can log prescription changes or dosage titrations, "
        "which automatically pins a prominent MED CHANGE milestone marker across your timeline charts. "
        "This vertical layout ensures that every dose, titration, and adherence confirmation "
        "is immediately coupled with your physiological ambulatory telemetry."
    )
    s43['step_focus'] = [
        {"bullet": 0, "focus_desc": "Tab 11 Architecture: Clinical Safety Notice & Pharmacological Framework", "scroll_pct": 0.0},
        {"bullet": 1, "focus_desc": "Component 1: Morning Suite & Evening Split Regimen Intake Cards", "scroll_pct": 0.40},
        {"bullet": 2, "focus_desc": "Component 2: Log Medication Change, Titration Forms & Pinned Milestones", "scroll_pct": 0.85}
    ]

# =============================================================================
# SLIDE 44: Two Discrete Daily Schedules - SCROLL TO SPECIFIC TOPIC LEFT SIDE
# =============================================================================
s44 = get_slide('44')
if s44:
    s44['tab_screenshot'] = 'images/lcars_slide44_medication_schedules.png'
    s44['tab_title'] = '11-MEDICATION CONSOLE • DUAL INTAKE SCHEDULES'
    s44['narration'] = (
        "Displayed on your screen is Component 1 of Tab 11: our Dual Intake Medication Schedules. "
        "To maximize clinical compliance, El-CARS structures outpatient regimens into exactly two discrete daily events. "
        "As the screen scrolls to the Morning Intake card at eight AM, notice your breakfast medications: "
        "your first Metformin one thousand milligram dose, Jardiance ten milligrams, and cardioprotective agents. "
        "Scrolling down to the Evening Intake card at six-thirty PM, your second split Metformin dose is logged with dinner, "
        "ensuring sustained nocturnal hepatic gluconeogenesis suppression. "
        "With one tap of 'TAKE ALL', you verify complete adherence, immediately updating your daily compliance timeline."
    )
    s44['step_focus'] = [
        {"bullet": 0, "focus_desc": "Morning Suite (Intake 1 • 08:00 AM): Breakfast Metformin & SGLT2 Regimen", "scroll_pct": 0.0},
        {"bullet": 1, "focus_desc": "Evening Split (Intake 2 • 06:30 PM): Dinner Metformin to Curb Dawn Surges", "scroll_pct": 0.50},
        {"bullet": 2, "focus_desc": "One-Click 'TAKE ALL' Adherence Logging & Telemetry Coupling", "scroll_pct": 1.0}
    ]

# =============================================================================
# SLIDE 45: Granular Medication Editing & Titration - SHOW TITRATION SCREEN
# =============================================================================
s45 = get_slide('45')
if s45:
    s45['tab_screenshot'] = 'images/lcars_slide45_titration_screen.png'
    s45['tab_title'] = '11-MEDICATION CONSOLE • TITRATION & MED CHANGE LOGGER'
    s45['narration'] = (
        "Displayed on your screen is Component 2 of Tab 11: our Medication Titration and Change Logging engine. "
        "Whenever your endocrinologist adjusts a dosage, switches drug classes, or prescribes a new agent, "
        "you enter the titration details directly on this screen. "
        "As shown, you select the change classification—such as dosage increase or tapering down—input the previous and new dosages, "
        "record the prescribing physician, and log the clinical rationale. "
        "Once committed, El-CARS pins a permanent red dashed milestone line across all timeline charts on that exact date. "
        "This allows both you and your doctor to compare biometrics before and after, empirically validating whether the titration delivered genuine progress."
    )
    s45['step_focus'] = [
        {"bullet": 0, "focus_desc": "Component 2 Titration Console: Dosage Adjustments & Drug Class Shifts", "scroll_pct": 0.0},
        {"bullet": 1, "focus_desc": "Prescribing Physician & Clinical Rationale Documentation", "scroll_pct": 0.45},
        {"bullet": 2, "focus_desc": "Pinning Milestone Markers to Prove Before-and-After Efficacy", "scroll_pct": 1.0}
    ]

# =============================================================================
# SLIDE 46: Tab 12 Full Scan - ANIMATE SCREENSHOT LEFT SIDE, FLOW CHART RIGHT PANE
# =============================================================================
s46 = get_slide('46')
if s46:
    s46['tab_screenshot'] = 'images/lcars_slide46_fullscan_active.png'
    s46['tab_title'] = '12-FULL SCAN CONSOLE • 6-STAGE DIAGNOSTIC PIPELINE'
    s46['narration'] = (
        "Displayed on your screen is Tab 12: our Sequential Comprehensive Biometric Full Scan in active execution. "
        "Observe the animated scanning sequence on the left, demonstrating how El-CARS coordinates all six onboard sensors. "
        "Rather than triggering multiple sensors simultaneously—which causes optical cross-talk and battery voltage dips—"
        "El-CARS executes a choreographed one-hundred-and-twenty-second diagnostic pipeline. "
        "On the right pane, our diagnostic flow chart outlines the exact sequence: "
        "Stage 1 measures optical glucose absorption, Stage 2 captures infrared temperature, Stage 3 evaluates blood pressure tonometry, "
        "Stage 4 assesses dual-wavelength SpO2, Stage 5 records pulse frequency, and Stage 6 conducts Poincaré HRV analysis. "
        "Once complete, the orchestrator compiles a comprehensive clinical audit report ready for permanent EHR upload."
    )
    s46['right_header'] = '6-STAGE FULL SCAN PIPELINE FLOW CHART'
    s46['right_badge'] = 'SEQUENTIAL 120s FLOW'
    s46['right_badge_col'] = '#0891b2'
    s46['right_html'] = """
          <!-- 6-Stage Full Scan Sequential Diagnostic Flow Chart -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a; font-family: monospace;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-size: 0.72rem; color: #0891b2; font-weight: 800;">TAB 12 SEQUENTIAL SCAN FLOW CHART</span>
              <span style="background: #0891b2; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px;">6 STAGES • 120s</span>
            </div>

            <!-- SVG Flow Chart: 6-Stage Pipeline -->
            <svg viewBox="0 0 420 220" style="width: 100%; height: auto; display: block; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0;">
              <defs>
                <marker id="arrow" viewBox="0 0 10 10" refX="6" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse">
                  <path d="M 0 1 L 10 5 L 0 9 z" fill="#0891b2"/>
                </marker>
              </defs>

              <!-- START TRIGGER -->
              <rect x="15" y="10" width="105" height="32" rx="4" fill="#ecfeff" stroke="#0891b2" stroke-width="1.5"/>
              <text x="67" y="24" fill="#155e75" font-size="7.5" font-weight="bold" text-anchor="middle">START SCAN (0s)</text>
              <text x="67" y="35" fill="#0891b2" font-size="6" text-anchor="middle">User or Scheduled</text>
              <line x1="120" y1="26" x2="145" y2="26" stroke="#0891b2" stroke-width="1.5" marker-end="url(#arrow)"/>

              <!-- STAGE 1: GLUCOSE -->
              <rect x="150" y="10" width="120" height="32" rx="4" fill="#fefce8" stroke="#ca8a04" stroke-width="1.5"/>
              <text x="210" y="24" fill="#854d0e" font-size="7.5" font-weight="bold" text-anchor="middle">STAGE 1: GLUCOSE (20s)</text>
              <text x="210" y="35" fill="#a16207" font-size="6" text-anchor="middle">Optical PPG Absorption</text>
              <line x1="270" y1="26" x2="295" y2="26" stroke="#0891b2" stroke-width="1.5" marker-end="url(#arrow)"/>

              <!-- STAGE 2: TEMPERATURE -->
              <rect x="300" y="10" width="110" height="32" rx="4" fill="#eff6ff" stroke="#0284c7" stroke-width="1.5"/>
              <text x="355" y="24" fill="#1e40af" font-size="7.5" font-weight="bold" text-anchor="middle">STAGE 2: TEMP (15s)</text>
              <text x="355" y="35" fill="#0369a1" font-size="6" text-anchor="middle">Dual Thermistor Nadir</text>

              <!-- Connector Line Down -->
              <path d="M 355 42 L 355 68 L 395 68" fill="none" stroke="#0891b2" stroke-width="1.5"/>
              <path d="M 395 68 L 395 85 L 350 85" fill="none" stroke="#0891b2" stroke-width="1.5" marker-end="url(#arrow)"/>

              <!-- STAGE 3: BLOOD PRESSURE -->
              <rect x="225" y="75" width="120" height="32" rx="4" fill="#fff7ed" stroke="#ea580c" stroke-width="1.5"/>
              <text x="285" y="89" fill="#9a3412" font-size="7.5" font-weight="bold" text-anchor="middle">STAGE 3: BP PWTT (25s)</text>
              <text x="285" y="100" fill="#c2410c" font-size="6" text-anchor="middle">Arterial Tonometry</text>
              <line x1="225" y1="91" x2="195" y2="91" stroke="#0891b2" stroke-width="1.5" marker-end="url(#arrow)"/>

              <!-- STAGE 4: SPO2 -->
              <rect x="70" y="75" width="120" height="32" rx="4" fill="#f0fdf4" stroke="#15803d" stroke-width="1.5"/>
              <text x="130" y="89" fill="#166534" font-size="7.5" font-weight="bold" text-anchor="middle">STAGE 4: SPO2 (20s)</text>
              <text x="130" y="100" fill="#15803d" font-size="6" text-anchor="middle">660/940nm Dual Spectra</text>

              <!-- Connector Line Down -->
              <path d="M 70 91 L 35 91 L 35 150 L 65 150" fill="none" stroke="#0891b2" stroke-width="1.5" marker-end="url(#arrow)"/>

              <!-- STAGE 5: PULSE -->
              <rect x="70" y="135" width="120" height="32" rx="4" fill="#fef2f2" stroke="#dc2626" stroke-width="1.5"/>
              <text x="130" y="149" fill="#991b1b" font-size="7.5" font-weight="bold" text-anchor="middle">STAGE 5: PULSE (15s)</text>
              <text x="130" y="160" fill="#dc2626" font-size="6" text-anchor="middle">Green Optical PPG Rate</text>
              <line x1="190" y1="151" x2="220" y2="151" stroke="#0891b2" stroke-width="1.5" marker-end="url(#arrow)"/>

              <!-- STAGE 6: HRV POINCARE -->
              <rect x="225" y="135" width="130" height="32" rx="4" fill="#f5f3ff" stroke="#7c3aed" stroke-width="1.5"/>
              <text x="290" y="149" fill="#5b21b6" font-size="7.5" font-weight="bold" text-anchor="middle">STAGE 6: HRV (25s)</text>
              <text x="290" y="160" fill="#7c3aed" font-size="6" text-anchor="middle">Inter-Beat SD1/SD2</text>
              <line x1="355" y1="151" x2="385" y2="151" stroke="#0891b2" stroke-width="1.5"/>
              <path d="M 385 151 L 385 195 L 305 195" fill="none" stroke="#0891b2" stroke-width="1.5" marker-end="url(#arrow)"/>

              <!-- FINAL STAGE: 360 REPORT -->
              <rect x="65" y="180" width="235" height="32" rx="4" fill="#ecfeff" stroke="#0284c7" stroke-width="2"/>
              <text x="182" y="195" fill="#0369a1" font-size="8" font-weight="bold" text-anchor="middle">✅ COMPLETE 360° CLINICAL AUDIT REPORT (120s)</text>
              <text x="182" y="206" fill="#0284c7" font-size="6.5" text-anchor="middle">Synchronized Multi-Parameter Packet Committed to Epic EHR</text>
            </svg>

            <div style="margin-top: 6px; background: #eff6ff; border: 1px solid #bfdbfe; border-radius: 4px; padding: 5px 8px; font-size: 0.65rem; color: #1e3a8a;">
              <strong>NON-INTERFERING PROTOCOL:</strong> Executing sensors sequentially eliminates optical crosstalk, thermal interference, and battery voltage sag, ensuring each measurement achieves laboratory precision.
            </div>
          </div>
    """

# =============================================================================
# SLIDE 49: H-Band vs LCARS Part 2 - SHOW MATH RIGHT PANE
# =============================================================================
s49 = get_slide('49')
if s49:
    s49['right_header'] = 'CLINICAL CALIBRATION & METABOLIC MATHEMATICS'
    s49['right_badge'] = 'LCARS MATHEMATICAL ENGINE'
    s49['right_badge_col'] = '#ea580c'
    s49['right_html'] = """
          <!-- LCARS vs H-Band Mathematical Analytics Card -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a; font-family: monospace; font-size: 0.67rem;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-size: 0.72rem; color: #ea580c; font-weight: 800;">CALIBRATION & METABOLIC FORMULATION</span>
              <span style="background: #ea580c; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px;">CLINICAL MATH</span>
            </div>

            <!-- 1. Lab Venous Blood Draw Calibration -->
            <div style="background: #eff6ff; border: 1px solid #bfdbfe; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <div style="color: #1e40af; font-weight: bold;">1. CERTIFIED IN-CLINIC LAB CALIBRATION OFFSET:</div>
              <div style="color: #1e3a8a; margin-top: 2px;">
                Δ_lab = G_venous_draw - G_raw_sensor(t_draw)<br>
                G_calibrated(t) = G_raw(t) + Δ_lab<br>
                <em>Example: In-clinic draw = 162 mg/dL, raw = 120.5 mg/dL ➔ Δ_lab = +41.5 mg/dL persistent calibration anchor.</em>
              </div>
            </div>

            <!-- 2. ADAG eA1C Exposure Transform -->
            <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <div style="color: #166534; font-weight: bold;">2. ROLLING ADAG GLYCEMIC EXPOSURE:</div>
              <div style="color: #14532d; margin-top: 2px;">
                eA1C (%) = (Mean Glucose + 46.7) / 28.7<br>
                <em>Unlike H-Band's generic step summary, El-CARS projects rolling eA1C daily across 288 samples.</em>
              </div>
            </div>

            <!-- 3. Non-Insulin GLUT4 Clearance Kinetics -->
            <div style="background: #fff7ed; border: 1.5px solid #fed7aa; border-radius: 4px; padding: 6px; margin-bottom: 6px;">
              <div style="color: #9a3412; font-weight: bold;">3. KINETIC GLUT4 GLUCOSE DISPOSAL:</div>
              <div style="color: #7c2d12; margin-top: 2px;">
                ΔG_GLUT4 = -k · ∫ HR_Zone2(τ) dτ ➔ Average -42 mg/dL drop<br>
                Empirical Pearson correlation: <strong>r = -0.79 (p &lt; 0.001)</strong> between daily step density and 24h mean glucose.
              </div>
            </div>

            <div style="background: #ffffff; border: 1px solid #cbd5e1; border-radius: 3px; padding: 4px 6px; font-size: 0.64rem; color: #475569;">
              <strong>THE ARCHITECTURAL DIFFERENCE:</strong> Consumer apps present uncalibrated raw counts. El-CARS executes rigorous physiological and statistical models verified by certified laboratory blood draws.
            </div>
          </div>
    """

# =============================================================================
# SLIDE 51: Outpatient Review - FIX SCROLL TO SEE DOCTOR'S VIEW & CONSOLIDATED SHARING
# =============================================================================
s51 = get_slide('51')
if s51:
    s51['tab_screenshot'] = 'images/lcars_slide51_doctor_consultation_view.png'
    s51['tab_title'] = '04-REPORTS CONSOLE • OUTPATIENT DOCTOR REVIEW & EPIC EHR'
    s51['narration'] = (
        "When you sit down with your doctor, El-CARS 5010 provides an objective, three-tier clinical consultation dashboard. "
        "As your view scrolls down the screen, observe how multi-scale telemetry is synthesized for your clinical team. "
        "Tier 1 presents your Year-to-Date macro record, showing long-term eA1C progress and cumulative titration success. "
        "Tier 2 examines your monthly thirty-day retrospect, analyzing glycemic variance, Time-in-Range, and blood pressure dipping. "
        "And Tier 3 inspects daily continuous twenty-four-hour curves, showing exact meal excursions and postprandial exercise clearance. "
        "Consolidated information sharing is a key tool for diabetic management. "
        "With one click of 'PUSH TO DOCTOR'S EPIC', your verified ambulatory record is transmitted straight into your hospital's EHR, "
        "empowering your entire multidisciplinary care team to act on certified empirical proof."
    )
    s51['step_focus'] = [
        {"bullet": 0, "focus_desc": "Outpatient Consultation Review: Filter Toolbar & Multi-Scale Data Selection", "scroll_pct": 0.0},
        {"bullet": 1, "focus_desc": "Tier 1 YTD Progress & Tier 2 Monthly Retrospect: Long-Term eA1C Trends", "scroll_pct": 0.40},
        {"bullet": 2, "focus_desc": "Tier 3 Daily Telemetry & 'PUSH TO DOCTOR\\'S EPIC' EHR Integration", "scroll_pct": 0.85}
    ]

# =============================================================================
# SLIDE 53: Collaboration Diagram Spoke/Wheel Below Other Items
# =============================================================================
s53 = get_slide('53')
if s53:
    s53['narration'] = (
        "In closing, El-CARS 5010 is completely free, open-source copyleft software released under the GNU General Public License version 3. "
        "As an author managing Type 2 Diabetes alongside my care team, this toolset is dedicated to everyone fighting diabetes, so that others may survive successfully. "
        "On the right panel below our open resources, our multidisciplinary collaboration diagram illustrates the patient at the very center of care, "
        "connected through the 5010 telemetry watch to four specialist physicians: the Endocrinologist, Primary Care Doctor, Cardiologist, and Nephrologist or Diabetes Educator. "
        "Consolidated information sharing across this entire circle is the definitive key to successful diabetes management, eliminating clinical silos. "
        "We invite doctors, engineers, and sponsors like Google to join us in advancing this open community project. Thank you for joining our clinical onboarding presentation!"
    )
    s53['right_html'] = """
          <div style="display:grid; grid-template-columns:1fr 1fr; gap:8px; margin-top:6px;">
            <a href="https://fitaos.com" target="_blank" style="text-decoration:none; color:inherit; background:#f8fafc; border:1.5px solid #cbd5e1; border-radius:6px; padding:8px; display:block; transition:all 0.2s;">
              <div style="display:flex; align-items:center; gap:6px;">
                <span style="font-size:1.0rem;">🛒</span>
                <span style="font-weight:800; color:#0284c7; font-size:0.75rem;">FITAOS OFFICIAL SALES SITE</span>
              </div>
              <div style="font-size:0.68rem; color:#475569; margin-top:3px; line-height:1.3;">
                Official store for FITAOS 5150. Compatible with many Veepoo models.
              </div>
              <div style="font-size:0.68rem; color:#0284c7; font-weight:bold; margin-top:4px; font-family:monospace;">fitaos.com ↗</div>
            </a>

            <a href="https://github.com/jbirdlebough1/JVEEPOO/tree/main/docs" target="_blank" style="text-decoration:none; color:inherit; background:#f8fafc; border:1.5px solid #cbd5e1; border-radius:6px; padding:8px; display:block; transition:all 0.2s;">
              <div style="display:flex; align-items:center; gap:6px;">
                <span style="font-size:1.0rem;">📚</span>
                <span style="font-weight:800; color:#15803d; font-size:0.75rem;">GITHUB DOCS DIRECTORY</span>
              </div>
              <div style="font-size:0.68rem; color:#475569; margin-top:3px; line-height:1.3;">
                Clinical glucose trials, A1C validation models & specifications.
              </div>
              <div style="font-size:0.68rem; color:#15803d; font-weight:bold; margin-top:4px; font-family:monospace;">github.com/.../docs ↗</div>
            </a>

            <a href="https://github.com/jbirdlebough1/JVEEPOO" target="_blank" style="text-decoration:none; color:inherit; background:#f8fafc; border:1.5px solid #cbd5e1; border-radius:6px; padding:8px; display:block; transition:all 0.2s;">
              <div style="display:flex; align-items:center; gap:6px;">
                <span style="font-size:1.0rem;">💻</span>
                <span style="font-weight:800; color:#7c3aed; font-size:0.75rem;">GITHUB REPOSITORY</span>
              </div>
              <div style="font-size:0.68rem; color:#475569; margin-top:3px; line-height:1.3;">
                Full GPL v3 copyleft source code, Python daemons & SPA console.
              </div>
              <div style="font-size:0.68rem; color:#7c3aed; font-weight:bold; margin-top:4px; font-family:monospace;">github.com/.../JVEEPOO ↗</div>
            </a>

            <a href="https://github.com/HBandSDK/Android_Ble_SDK/issues/46" target="_blank" style="text-decoration:none; color:inherit; background:#f8fafc; border:1.5px solid #cbd5e1; border-radius:6px; padding:8px; display:block; transition:all 0.2s;">
              <div style="display:flex; align-items:center; gap:6px;">
                <span style="font-size:1.0rem;">🌐</span>
                <span style="font-weight:800; color:#d97706; font-size:0.75rem;">HBANDSDK COMMUNITY</span>
              </div>
              <div style="font-size:0.68rem; color:#475569; margin-top:3px; line-height:1.3;">
                Official open-source ecosystem showcase Issue #46 on HBandSDK.
              </div>
              <div style="font-size:0.68rem; color:#d97706; font-weight:bold; margin-top:4px; font-family:monospace;">HBandSDK Issue #46 ↗</div>
            </a>
          </div>

          <!-- Powered by Google Gemini Pro -->
          <div style="margin-top:8px; background:#eff6ff; border:1.5px solid #bfdbfe; border-radius:6px; padding:8px; display:flex; align-items:center; gap:10px;">
            <div style="background:#2563eb; color:#ffffff; font-weight:900; font-size:0.85rem; border-radius:4px; padding:4px 8px; font-family:monospace; white-space:nowrap;">
              GEMINI PRO
            </div>
            <div style="font-size:0.72rem; color:#1e3a8a; line-height:1.35;">
              <strong>Powered by Google Gemini Pro:</strong> Developed using Google DeepMind's advanced AI reasoning agent based on open-source HBandSDK and Veepoo repositories.
            </div>
          </div>

          <!-- ============================================================== -->
          <!-- MULTIDISCIPLINARY SPOKE-AND-WHEEL COLLABORATION DIAGRAM        -->
          <!-- ============================================================== -->
          <div style="margin-top:10px; background:#ffffff; border:2px solid #0284c7; border-radius:6px; padding:10px; box-shadow:0 2px 8px rgba(0,0,0,0.06);">
            <div style="display:flex; justify-content:space-between; align-items:center; border-bottom:1.5px solid #e2e8f0; padding-bottom:4px; margin-bottom:8px;">
              <div style="font-family:monospace; font-weight:900; color:#0284c7; font-size:0.76rem;">
                MULTIDISCIPLINARY COLLABORATION: CONSOLIDATED CARE
              </div>
              <span style="background:#15803d; color:#ffffff; font-size:0.58rem; font-weight:bold; padding:2px 6px; border-radius:3px; font-family:monospace;">
                PATIENT-CENTERED SPOKE & WHEEL
              </span>
            </div>

            <!-- SVG Spoke-and-Wheel Diagram -->
            <svg viewBox="0 0 440 280" style="width:100%; height:auto; display:block; background:#f8fafc; border-radius:4px; border:1px solid #cbd5e1;">
              <!-- Outer Connecting Telemetry Ring -->
              <circle cx="220" cy="140" r="105" fill="none" stroke="#93c5fd" stroke-width="2.5" stroke-dasharray="6 4"/>
              
              <!-- Radial Spokes connecting Patient Center to Outer Nodes -->
              <!-- Spoke 1 (North: Endocrinologist) -->
              <line x1="220" y1="140" x2="220" y2="40" stroke="#0284c7" stroke-width="2.5"/>
              <!-- Spoke 2 (East: PCP) -->
              <line x1="220" y1="140" x2="350" y2="140" stroke="#0284c7" stroke-width="2.5"/>
              <!-- Spoke 3 (South: Cardiologist) -->
              <line x1="220" y1="140" x2="220" y2="240" stroke="#0284c7" stroke-width="2.5"/>
              <!-- Spoke 4 (West: Nephrologist / CDCES) -->
              <line x1="220" y1="140" x2="90" y2="140" stroke="#0284c7" stroke-width="2.5"/>

              <!-- CENTER HUB: THE PATIENT -->
              <circle cx="220" cy="140" r="42" fill="#eff6ff" stroke="#1d4ed8" stroke-width="3"/>
              <text x="220" y="132" fill="#1e3a8a" font-size="8.5" font-family="monospace" font-weight="900" text-anchor="middle">👤 PATIENT</text>
              <text x="220" y="143" fill="#1d4ed8" font-size="6.5" font-family="monospace" font-weight="bold" text-anchor="middle">CENTER OF CARE</text>
              <text x="220" y="153" fill="#64748b" font-size="5.8" font-family="monospace" text-anchor="middle">Daily Self-Care</text>

              <!-- SPOKE 1 (NORTH): ENDOCRINOLOGIST -->
              <g transform="translate(145, 12)">
                <rect width="150" height="34" rx="4" fill="#ffffff" stroke="#ca8a04" stroke-width="1.8"/>
                <text x="75" y="14" fill="#854d0e" font-size="7.5" font-family="monospace" font-weight="900" text-anchor="middle">🩺 ENDOCRINOLOGIST</text>
                <text x="75" y="24" fill="#a16207" font-size="6.0" font-family="monospace" text-anchor="middle">Med Titration • Fasting Nadir</text>
                <!-- Watch Badge at Spoke End -->
                <circle cx="75" cy="34" r="5" fill="#ca8a04" stroke="#ffffff" stroke-width="1.5"/>
              </g>

              <!-- SPOKE 2 (EAST): PRIMARY CARE PHYSICIAN (PCP) -->
              <g transform="translate(315, 122)">
                <rect width="120" height="36" rx="4" fill="#ffffff" stroke="#0284c7" stroke-width="1.8"/>
                <text x="60" y="14" fill="#0369a1" font-size="7.5" font-family="monospace" font-weight="900" text-anchor="middle">👨‍⚕️ PRIMARY CARE</text>
                <text x="60" y="24" fill="#0284c7" font-size="6.0" font-family="monospace" text-anchor="middle">Lab Venous Calibration</text>
                <text x="60" y="32" fill="#64748b" font-size="5.5" font-family="monospace" text-anchor="middle">Care Coordination</text>
                <!-- Watch Badge at Spoke End -->
                <circle cx="0" cy="18" r="5" fill="#0284c7" stroke="#ffffff" stroke-width="1.5"/>
              </g>

              <!-- SPOKE 3 (SOUTH): CARDIOLOGIST -->
              <g transform="translate(145, 234)">
                <rect width="150" height="34" rx="4" fill="#ffffff" stroke="#dc2626" stroke-width="1.8"/>
                <text x="75" y="14" fill="#991b1b" font-size="7.5" font-family="monospace" font-weight="900" text-anchor="middle">❤️ CARDIOLOGIST</text>
                <text x="75" y="24" fill="#dc2626" font-size="6.0" font-family="monospace" text-anchor="middle">PWTT BP • Dipping • HRV</text>
                <!-- Watch Badge at Spoke End -->
                <circle cx="75" cy="0" r="5" fill="#dc2626" stroke="#ffffff" stroke-width="1.5"/>
              </g>

              <!-- SPOKE 4 (WEST): NEPHROLOGIST & CDCES EDUCATOR -->
              <g transform="translate(5, 122)">
                <rect width="130" height="36" rx="4" fill="#ffffff" stroke="#15803d" stroke-width="1.8"/>
                <text x="65" y="14" fill="#14532d" font-size="7.2" font-family="monospace" font-weight="900" text-anchor="middle">🥗 NEPHROLOGIST/CDCES</text>
                <text x="65" y="24" fill="#15803d" font-size="6.0" font-family="monospace" text-anchor="middle">Renal eGFR • Low-GI Trades</text>
                <text x="65" y="32" fill="#64748b" font-size="5.5" font-family="monospace" text-anchor="middle">GLUT4 Walking Kinetics</text>
                <!-- Watch Badge at Spoke End -->
                <circle cx="130" cy="18" r="5" fill="#15803d" stroke="#ffffff" stroke-width="1.5"/>
              </g>

              <!-- Outer Watch Transponder Badges -->
              <g transform="translate(295, 60)">
                <rect width="80" height="18" rx="3" fill="#1e293b" stroke="#38b6ff" stroke-width="1"/>
                <text x="40" y="12" fill="#38b6ff" font-size="6.0" font-family="monospace" font-weight="bold" text-anchor="middle">⌚ 5010 WATCH</text>
              </g>
              <g transform="translate(65, 60)">
                <rect width="80" height="18" rx="3" fill="#1e293b" stroke="#38b6ff" stroke-width="1"/>
                <text x="40" y="12" fill="#38b6ff" font-size="6.0" font-family="monospace" font-weight="bold" text-anchor="middle">⌚ 5010 WATCH</text>
              </g>
              <g transform="translate(295, 202)">
                <rect width="80" height="18" rx="3" fill="#1e293b" stroke="#38b6ff" stroke-width="1"/>
                <text x="40" y="12" fill="#38b6ff" font-size="6.0" font-family="monospace" font-weight="bold" text-anchor="middle">⌚ 5010 WATCH</text>
              </g>
              <g transform="translate(65, 202)">
                <rect width="80" height="18" rx="3" fill="#1e293b" stroke="#38b6ff" stroke-width="1"/>
                <text x="40" y="12" fill="#38b6ff" font-size="6.0" font-family="monospace" font-weight="bold" text-anchor="middle">⌚ 5010 WATCH</text>
              </g>
            </svg>

            <!-- Consolidated Information Sharing Principle -->
            <div style="margin-top:6px; background:#eff6ff; border-left:4px solid #0284c7; padding:6px 10px; font-family:monospace; font-size:0.68rem; color:#1e3a8a; line-height:1.4;">
              <strong>CONSOLIDATED INFORMATION SHARING:</strong> Eliminates fragmented oral recall. One continuous ambulatory record synchronizes the patient, Endocrinologist, PCP, Cardiologist, and Nephrologist, delivering unified clinical decisions straight into Epic EHR.
            </div>
          </div>
    """

# Update LCARS pronunciation to El-CARS across all narrations in deck
for s in slides:
    narration = s.get('narration', '')
    if 'LCARS' in narration:
        s['narration'] = narration.replace('LCARS', 'El-CARS')

with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2, ensure_ascii=False)

print("all_slides_data.json updated successfully with all user requests!")
