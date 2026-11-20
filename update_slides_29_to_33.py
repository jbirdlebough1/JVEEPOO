import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

# Helper to find slide by num
def get_slide(num_str):
    for s in slides:
        if s['num'] == num_str:
            return s
    return None

# =============================================================================
# SLIDE 29: Tab 04 Single Tall Synchronized Chart & Med/Food Markers
# =============================================================================
s29 = get_slide('29')
if s29:
    s29['tab_screenshot'] = 'images/lcars_slide29_tall_chart_stack.png'
    s29['tab_title'] = '04-REPORTS CONSOLE • 360° SYNCHRONIZED COMPOSITE TIMELINE'
    s29['narration'] = (
        "Displayed on your screen is our 360-degree Synchronized Composite Timeline from Tab 04 Reports Console. "
        "Instead of fragmenting physiological data across disconnected mobile screens, LCARS stacks all eight biometric channels "
        "on one shared twenty-four-hour horizontal clock. "
        "As your view scrolls down the screen, observe how continuous glucose, blood pressure, heart rate, oxygenation, "
        "and temperature align seamlessly above kinetic exercise, nutrition, and medications. "
        "Critically, notice the pinned food and medication markers integrated directly onto the time axis. "
        "The green salad marker logs dietary carbohydrate intake, while prominent medication markers pinpoint exact doses. "
        "By pinning these behavioral and clinical events onto the timeline, patients and physicians can immediately "
        "track how specific meals and medication changes directly govern every subsequent physiological deflection."
    )
    s29['bullets'] = [
        [
            "Single Synchronized Timeline",
            "Stacks all 8 biometric telemetry channels on a single shared 24-hour horizontal time axis with zero temporal misalignment."
        ],
        [
            "Pinned '🥗 FOOD / MEAL' Markers",
            "Visual meal badges capture carbohydrate density, caloric load, and exact timing directly above glycemic response curves."
        ],
        [
            "Pinned '💊 MED CHANGE' Milestones",
            "Prominently flags prescription adjustments and dosages, establishing irrefutable before-and-after therapeutic causality."
        ],
        [
            "Continuous Multi-Variable Context",
            "Replaces isolated glucose numbers with full physiological context, tracking sleep, stress, and kinetic clearance in real time."
        ]
    ]
    s29['takeaway'] = (
        "LCARS stacks all 8 telemetry channels on one synchronized timeline. Pinned '🥗 FOOD' and '💊 MED CHANGE' markers directly connect "
        "daily habits and prescriptions to objective physiological deflections, eliminating guesswork."
    )
    s29['right_header'] = 'SYNCHRONIZED TIMELINE ARCHITECTURE'
    s29['right_badge'] = '8-CHANNEL SYNCHRONIZATION'
    s29['right_badge_col'] = '#eab308'
    s29['right_html'] = """
          <!-- 360 Synchronized Architecture & Event Markers Card -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">360° SYNCHRONIZED COMPOSITE TIMELINE</span>
              <span style="background: #eab308; color: #000; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">8 STACKED LANES</span>
            </div>

            <!-- Event Marker Callouts -->
            <div style="display: flex; flex-direction: column; gap: 6px; font-family: monospace; font-size: 0.67rem;">
              <div style="background: #f0fdf4; border: 1.5px solid #86efac; border-radius: 4px; padding: 6px;">
                <div style="display: flex; justify-content: space-between;">
                  <strong style="color: #15803d;">🥗 FOOD / MEAL MARKERS</strong>
                  <span style="color: #166534; font-weight: bold;">NUTRITIONAL TRACE</span>
                </div>
                <div style="color: #14532d; margin-top: 2px;">
                  Pins exact carbohydrate load (e.g. 18g lunch) directly to the timeline, illuminating postprandial glucose curves and GLUT4 recovery.
                </div>
              </div>

              <div style="background: #fef2f2; border: 1.5px solid #fecaca; border-radius: 4px; padding: 6px;">
                <div style="display: flex; justify-content: space-between;">
                  <strong style="color: #dc2626;">💊 MED CHANGE MILESTONES</strong>
                  <span style="color: #991b1b; font-weight: bold;">PRESCRIPTION TRACKING</span>
                </div>
                <div style="color: #7f1d1d; margin-top: 2px;">
                  Cuts vertically across all 8 channels whenever dosage changes occur, allowing 14-day pre/post therapeutic comparison.
                </div>
              </div>

              <div style="background: #eff6ff; border: 1.5px solid #bfdbfe; border-radius: 4px; padding: 6px;">
                <div style="display: flex; justify-content: space-between;">
                  <strong style="color: #0284c7;">↔ CONTINUOUS TIME COUPLING</strong>
                  <span style="color: #1e40af; font-weight: bold;">ZERO OVERLAP</span>
                </div>
                <div style="color: #1e3a8a; margin-top: 2px;">
                  All 8 biometrics (Glucose, BP, HR/HRV, SpO2, Temp, Steps, Diet, Meds) share one unified clock, eliminating disconnected data silos.
                </div>
              </div>
            </div>
          </div>
    """
    s29['step_focus'] = [
        {"bullet": 0, "focus_desc": "360° Synchronized Architecture: 8 Stacked Physiological Telemetry Lanes", "scroll_pct": 0.0},
        {"bullet": 1, "focus_desc": "Pinned '🥗 FOOD / MEAL' Markers: Connecting Carbs to Postprandial Excursions", "scroll_pct": 0.35},
        {"bullet": 2, "focus_desc": "Pinned '💊 MED CHANGE' Milestones: Auditing Prescription Efficacy", "scroll_pct": 0.70},
        {"bullet": 3, "focus_desc": "Continuous Multi-Variable Coupling: Eliminating Isolated Health Silos", "scroll_pct": 1.0}
    ]

# =============================================================================
# SLIDE 30: The 8 Stacked Measurement Lanes
# =============================================================================
s30 = get_slide('30')
if s30:
    s30['tab_screenshot'] = 'images/lcars_slide29_tall_chart_stack.png'
    s30['tab_title'] = '04-REPORTS CONSOLE • 8 STACKED PHYSIOLOGICAL TELEMETRY LANES'
    s30['narration'] = (
        "Displayed on your screen is our comprehensive eight-lane stacked clinical telemetry array. "
        "As the chart scrolls downward, we examine each physiological measurement in clinical sequence. "
        "Lane 1 displays continuous glucose, shaded with a green target band representing sixty to two hundred milligrams per deciliter. "
        "Lane 2 tracks arterial blood pressure, contrasting systolic and diastolic curves against normal thresholds. "
        "Lane 3 charts heart rate and heart rate variability, capturing autonomic sympathetic tone. "
        "Lane 4 monitors pulse oximetry, alerting to nocturnal desaturations below ninety-five percent. "
        "Lane 5 traces core and skin temperature, mapping the circadian metabolic nadir. "
        "Lane 6 details kinetic exercise steps and post-meal non-insulin GLUT4 clearance. "
        "Lane 7 captures dietary nutrition with pinned meal carb markers, and Lane 8 records active medications and titration milestones. "
        "This vertical stack gives clinicians an unprecedented panoramic view of whole-body metabolic regulation."
    )
    s30['bullets'] = [
        [
            "Lanes 1 & 2: Glycemic & Hemodynamic Core",
            "Lane 1 maps continuous glucose with 60–200 mg/dL TIR shading; Lane 2 tracks systolic and diastolic BP trends."
        ],
        [
            "Lanes 3, 4 & 5: Autonomic, Respiratory & Thermal",
            "Lane 3 tracks pulse & HRV stress response; Lane 4 watches SpO2 hypoxia thresholds (95%); Lane 5 traces circadian nadir."
        ],
        [
            "Lane 6: Kinetic Exercise & GLUT4 Clearance",
            "Bar graphs quantify step density and highlight active postprandial walks that accelerate glucose disposal."
        ],
        [
            "Lanes 7 & 8: Nutrition & Medication Regimen",
            "Lane 7 pins carb intake and meal events; Lane 8 logs active prescriptions and titration milestones."
        ]
    ]
    s30['takeaway'] = (
        "Eight stacked telemetry lanes provide a 360° panoramic audit of metabolic health, correlating glucose, hemodynamics, autonomic stress, "
        "and temperature directly against exercise, meals, and medication changes."
    )
    s30['right_header'] = '8-LANE TELEMETRY MATRIX'
    s30['right_badge'] = 'PHYSIOLOGICAL CHANNELS'
    s30['right_badge_col'] = '#0284c7'
    s30['right_html'] = """
          <!-- 8 Stacked Lanes Clinical Breakdown Table -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">8-CHANNEL STACKED ARCHITECTURE</span>
              <span style="background: #0284c7; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">CLINICAL LANES</span>
            </div>

            <div style="display: flex; flex-direction: column; gap: 4px; font-family: monospace; font-size: 0.65rem;">
              <div style="display: flex; justify-content: space-between; padding: 3px 6px; background: #fff; border: 1px solid #e2e8f0; border-radius: 3px;">
                <span style="color: #eab308; font-weight: bold;">LANE 1: GLUCOSE</span>
                <span style="color: #64748b;">60–200 mg/dL TIR Shading • Dawn Nadir</span>
              </div>
              <div style="display: flex; justify-content: space-between; padding: 3px 6px; background: #fff; border: 1px solid #e2e8f0; border-radius: 3px;">
                <span style="color: #ff9944; font-weight: bold;">LANE 2: BLOOD PRESSURE</span>
                <span style="color: #64748b;">Systolic/Diastolic • 120/80 Reference</span>
              </div>
              <div style="display: flex; justify-content: space-between; padding: 3px 6px; background: #fff; border: 1px solid #e2e8f0; border-radius: 3px;">
                <span style="color: #ff5566; font-weight: bold;">LANE 3: PULSE & HRV</span>
                <span style="color: #64748b;">BPM & RMSSD Autonomic Stress Response</span>
              </div>
              <div style="display: flex; justify-content: space-between; padding: 3px 6px; background: #fff; border: 1px solid #e2e8f0; border-radius: 3px;">
                <span style="color: #15803d; font-weight: bold;">LANE 4: OXYGEN SPO2</span>
                <span style="color: #64748b;">95% Nocturnal Hypoxia Alert Threshold</span>
              </div>
              <div style="display: flex; justify-content: space-between; padding: 3px 6px; background: #fff; border: 1px solid #e2e8f0; border-radius: 3px;">
                <span style="color: #0284c7; font-weight: bold;">LANE 5: TEMPERATURE</span>
                <span style="color: #64748b;">Dual Sensor Core/Skin Circadian Curve</span>
              </div>
              <div style="display: flex; justify-content: space-between; padding: 3px 6px; background: #fff; border: 1px solid #e2e8f0; border-radius: 3px;">
                <span style="color: #ea580c; font-weight: bold;">LANE 6: EXERCISE & STEPS</span>
                <span style="color: #64748b;">Hourly Step Density & Post-Meal GLUT4</span>
              </div>
              <div style="display: flex; justify-content: space-between; padding: 3px 6px; background: #fff; border: 1px solid #e2e8f0; border-radius: 3px;">
                <span style="color: #9333ea; font-weight: bold;">LANE 7: DIETARY INTAKE</span>
                <span style="color: #64748b;">Pinned Carbs, Calories & Meal Badges</span>
              </div>
              <div style="display: flex; justify-content: space-between; padding: 3px 6px; background: #fff; border: 1px solid #e2e8f0; border-radius: 3px;">
                <span style="color: #b45309; font-weight: bold;">LANE 8: MEDICATIONS</span>
                <span style="color: #64748b;">Prescription Doses & '💊 MED CHANGE' Tags</span>
              </div>
            </div>
          </div>
    """
    s30['step_focus'] = [
        {"bullet": 0, "focus_desc": "Lanes 1 & 2: Glycemic Target Shading (60-200 mg/dL) & Blood Pressure Hemodynamics", "scroll_pct": 0.0},
        {"bullet": 1, "focus_desc": "Lanes 3, 4 & 5: Autonomic HRV, Nocturnal SpO2 (95% Threshold) & Circadian Nadir", "scroll_pct": 0.35},
        {"bullet": 2, "focus_desc": "Lane 6: Kinetic Exercise Steps & Non-Insulin GLUT4 Postprandial Clearance", "scroll_pct": 0.70},
        {"bullet": 3, "focus_desc": "Lanes 7 & 8: Nutrition Carb Badges & Pinned Medication Milestones", "scroll_pct": 1.0}
    ]

# =============================================================================
# SLIDE 31: Continuous Vertical Scrubber Line & HUD Automation
# =============================================================================
s31 = get_slide('31')
if s31:
    s31['tab_screenshot'] = 'images/lcars_slide31_scrubber_hud.png'
    s31['tab_title'] = '04-REPORTS CONSOLE • SYNCHRONIZED VERTICAL SCRUBBER HUD'
    s31['narration'] = (
        "Displayed on your screen is our automated Vertical Scrubber Line gliding across all eight telemetry channels. "
        "As the dashed yellow vertical crosshair tracks across the timeline, it intersects every physiological variable "
        "at that exact instant in time. "
        "Notice at 13:30, the post-lunch peak, the scrubber aligns a glucose peak of 188 milligrams per deciliter "
        "with an 18-gram low-carb salad, an 84 beat per minute pulse, and an immediate post-meal brisk walk. "
        "Above the chart, the real-time HUD ribbon instantly updates with exact synchronized values for glucose, "
        "blood pressure, SpO2, and medication status. "
        "By automating this vertical crosshair, LCARS uncovers the hidden physiological cause behind every glycemic peak and valley, "
        "allowing users to click any point to open a granular clinical pop-up audit."
    )
    s31['bullets'] = [
        [
            "Cross-Cutting Vertical Guideline",
            "A single synchronized vertical crosshair cuts through all 8 channels simultaneously to align cause and effect."
        ],
        [
            "Automated Real-Time HUD Readout",
            "Top dashboard ribbon displays exact numeric metrics for glucose, BP, pulse, SpO2, and diet at the cursor timestamp."
        ],
        [
            "Instant Excursion Correlation",
            "Aligns the 13:30 lunch peak (188 mg/dL) directly with 18g salad intake and subsequent 20-min GLUT4 walk."
        ],
        [
            "Click-to-Inspect Interaction",
            "Clicking any point along the vertical line opens the comprehensive Clinical Point Card modal for deeper audit."
        ]
    ]
    s31['takeaway'] = (
        "The continuous vertical scrubber line cross-sects all 8 telemetry channels simultaneously. At 13:30, it instantly connects "
        "a 188 mg/dL peak with meal carbs and GLUT4 walking, updating the HUD ribbon in real time."
    )
    s31['right_header'] = 'VERTICAL SCRUBBER LINE & HUD'
    s31['right_badge'] = '13:30 LUNCH PEAK'
    s31['right_badge_col'] = '#ca8a04'
    s31['right_html'] = """
          <!-- Vertical Scrubber HUD Correlation Diagram -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #ca8a04; font-weight: 800;">CROSS-CUTTING TEMPORAL SCRUBBER (13:30)</span>
              <span style="background: #ca8a04; color: #000; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">REAL-TIME HUD</span>
            </div>

            <!-- Real-Time HUD Values Grid -->
            <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 6px; font-family: monospace; font-size: 0.67rem;">
              <div style="background: #fffbeb; border: 1px solid #fde68a; border-radius: 3px; padding: 5px;">
                <div style="color: #92400e; font-size: 0.60rem;">GLUCOSE (TIR SAFE)</div>
                <strong style="color: #b45309; font-size: 0.85rem;">188 mg/dL</strong>
              </div>
              <div style="background: #fff7ed; border: 1px solid #fed7aa; border-radius: 3px; padding: 5px;">
                <div style="color: #9a3412; font-size: 0.60rem;">BLOOD PRESSURE</div>
                <strong style="color: #ea580c; font-size: 0.85rem;">119/76 mmHg</strong>
              </div>
              <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 3px; padding: 5px;">
                <div style="color: #166534; font-size: 0.60rem;">OXYGEN SPO2 / PULSE</div>
                <strong style="color: #15803d; font-size: 0.85rem;">98% / 84 BPM</strong>
              </div>
              <div style="background: #f0f9ff; border: 1px solid #bae6fd; border-radius: 3px; padding: 5px;">
                <div style="color: #075985; font-size: 0.60rem;">TEMPERATURE / HRV</div>
                <strong style="color: #0284c7; font-size: 0.85rem;">97.3°F / 56 ms</strong>
              </div>
            </div>

            <!-- Causal Alignment Card -->
            <div style="margin-top: 6px; background: #eff6ff; border: 1px solid #bfdbfe; border-radius: 4px; padding: 6px; font-family: monospace; font-size: 0.66rem; line-height: 1.35; color: #1e3a8a;">
              <strong>CAUSAL CORRELATION:</strong> 18g grilled chicken lunch triggered an initial rise, but an immediate 20-min GLUT4 brisk walk blunted the peak to 188 mg/dL, safely below the 200 mg/dL target threshold.
            </div>
          </div>
    """
    s31['step_focus'] = [
        {"bullet": 0, "focus_desc": "Continuous Vertical Scrubber Line Cutting Across All 8 Physiological Lanes", "scroll_pct": 0.0},
        {"bullet": 1, "focus_desc": "Synchronized HUD Ribbon Readout: Real-Time Multi-Parameter Metrics", "scroll_pct": 0.40},
        {"bullet": 2, "focus_desc": "Cause-and-Effect Alignment: Linking Lunch Meal Carbs to GLUT4 Walk Disposal", "scroll_pct": 0.85}
    ]

# =============================================================================
# SLIDE 32: Click-to-Pop Clinical Point Cards
# =============================================================================
s32 = get_slide('32')
if s32:
    s32['tab_screenshot'] = 'images/lcars_slide32_point_card_modal.png'
    s32['tab_title'] = '04-REPORTS CONSOLE • CLICK-TO-POP POINT AUDIT CARD'
    s32['narration'] = (
        "Displayed on your screen is our Click-to-Pop Clinical Point Card modal, captured directly in Tab 04 Reports Console. "
        "Touching or clicking any data point on the continuous timeline instantly surfaces this detailed clinical audit window. "
        "As shown in the modal, every point captures the exact verified glucose reading of 188 milligrams per deciliter, "
        "accompanied by normotensive blood pressure of 119 over 76, 98 percent oxygenation, and an 84 beat per minute pulse. "
        "Directly below, the modal details the patient's nutritional intake: a grilled chicken salad with eighteen grams of net carbs, "
        "paired with a twenty-minute post-meal GLUT4 walk that suppressed glucose by 34 milligrams per deciliter. "
        "Finally, the clinical correlation box explains that non-insulin skeletal muscle uptake safely maintained time-in-range. "
        "This gives physicians an instantaneous micro-audit during clinical consultations without searching through paper logs."
    )
    s32['bullets'] = [
        [
            "Instant Click-to-Pop Inspection",
            "Clicking any timeline node instantly surfaces a comprehensive modal displaying all verified metrics at that timestamp."
        ],
        [
            "Granular Multi-System Matrix",
            "Displays exact numeric glucose (188 mg/dL), blood pressure (119/76), oxygenation (98%), pulse (84 BPM), and HRV (56 ms)."
        ],
        [
            "Nutritional & Kinetic Correlates",
            "Details meal carbohydrates (18g net carbs), caloric intake (520 kcal), and post-meal GLUT4 walk step suppression (-34 mg/dL)."
        ],
        [
            "Physician-Patient Consultation Proof",
            "Provides an authoritative clinical explanation, verifying that lifestyle compliance actively protected beta-cell reserve."
        ]
    ]
    s32['takeaway'] = (
        "Clicking any point on the clinical curve opens an instant micro-audit modal. At 13:30, it validates 188 mg/dL glucose, "
        "18g meal carbs, and a 20-min GLUT4 walk (-34 mg/dL), providing physicians with complete consultation proof."
    )
    s32['right_header'] = 'CLINICAL POINT MICRO-AUDIT'
    s32['right_badge'] = 'POP-UP MODAL'
    s32['right_badge_col'] = '#15803d'
    s32['right_html'] = """
          <!-- Clinical Point Card Educational Structure -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #15803d; font-weight: 800;">POINT CARD ARCHITECTURE (13:30)</span>
              <span style="background: #15803d; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">INSTANT AUDIT</span>
            </div>

            <!-- Modal Content Breakdown -->
            <div style="display: flex; flex-direction: column; gap: 5px; font-family: monospace; font-size: 0.66rem;">
              <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 3px; padding: 5px;">
                <strong style="color: #166534;">1. Biometric Triad:</strong>
                <span style="color: #14532d;"> 188 mg/dL Glucose • 119/76 mmHg BP • 98% SpO2 / 84 BPM.</span>
              </div>
              <div style="background: #eff6ff; border: 1px solid #bfdbfe; border-radius: 3px; padding: 5px;">
                <strong style="color: #0284c7;">2. Nutritional Macro Profile:</strong>
                <span style="color: #1e3a8a;"> Grilled chicken salad • 18g net carbs • 520 kcal • Low GI.</span>
              </div>
              <div style="background: #fff7ed; border: 1px solid #fed7aa; border-radius: 3px; padding: 5px;">
                <strong style="color: #ea580c;">3. GLUT4 Exercise Clearance:</strong>
                <span style="color: #9a3412;"> 780 steps • 20-min walk • -34 mg/dL postprandial drop.</span>
              </div>
              <div style="background: #f5f3ff; border: 1px solid #ddd6fe; border-radius: 3px; padding: 5px;">
                <strong style="color: #7c3aed;">4. Active Prescription Status:</strong>
                <span style="color: #5b21b6;"> Metformin 1,000mg BID + Empagliflozin 10mg taken with breakfast.</span>
              </div>
            </div>
          </div>
    """
    s32['step_focus'] = [
        {"bullet": 0, "focus_desc": "Click-to-Pop Clinical Point Card Modal Architecture & Trigger", "scroll_pct": 0.0},
        {"bullet": 1, "focus_desc": "Verified Multi-Parameter Matrix: Glucose, Blood Pressure, SpO2 & Heart Rate", "scroll_pct": 0.40},
        {"bullet": 2, "focus_desc": "Nutritional Intake & GLUT4 Kinetic Exercise Clearance Evaluation", "scroll_pct": 0.85}
    ]

# =============================================================================
# SLIDE 33: Pinning '💊 MED CHANGE' Milestones: Auditing Progress vs Regression
# =============================================================================
s33 = get_slide('33')
if s33:
    s33['tab_screenshot'] = 'images/lcars_slide33_med_change_milestone.png'
    s33['tab_title'] = '04-REPORTS CONSOLE • PINNED MED CHANGE CLINICAL MILESTONES'
    s33['narration'] = (
        "Displayed on your screen is our longitudinal timeline with a pinned 'MED CHANGE' milestone marker. "
        "When an endocrinologist titrates an outpatient prescription—such as increasing Metformin or initiating an SGLT-2 inhibitor—"
        "LCARS pins a vertical milestone line cutting across all eight physiological channels on that exact calendar date. "
        "As shown on screen, this divides the timeline into an empirical before-and-after therapeutic comparison. "
        "In the thirty days prior to titration, mean glucose averaged 178 milligrams per deciliter with wide glycemic volatility. "
        "Following the medication change, mean glucose systematically dropped by 24 milligrams per deciliter, "
        "Time-in-Range expanded to 88 percent, and blood pressure stabilized. "
        "By visually pinning medication changes to objective ambulatory telemetry, patients and physicians obtain "
        "irrefutable proof of therapeutic progress, eliminating guesswork from diabetes management."
    )
    s33['bullets'] = [
        [
            "Prominent '💊 MED CHANGE' Milestones",
            "Cuts vertically across all 8 physiological channels, permanently anchoring the date of prescription titration."
        ],
        [
            "Empirical Before-and-After Comparison",
            "Directly contrasts 30-day glycemic trajectories, Time-in-Range, and blood pressure before vs after dosage shifts."
        ],
        [
            "Definitive Therapeutic Validation",
            "Confirms mean glucose dropped by 24 mg/dL (178 -> 154 mg/dL) and TIR expanded to 88%, proving genuine progress."
        ],
        [
            "Objective Drug Titration Auditing",
            "Empowers care teams to confidently adjust or discontinue medications based on continuous physiological evidence."
        ]
    ]
    s33['takeaway'] = (
        "Pinning '💊 MED CHANGE' milestones provides definitive proof of drug efficacy. Comparing before vs after trajectories confirms "
        "a 24 mg/dL glucose drop and 88% Time-in-Range, replacing subjective recall with empirical pharmacological validation."
    )
    s33['right_header'] = 'MEDICATION TITRATION AUDIT'
    s33['right_badge'] = 'PROGRESS VS REGRESSION'
    s33['right_badge_col'] = '#dc2626'
    s33['right_html'] = """
          <!-- Med Change Milestone Before/After Audit Matrix -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #dc2626; font-weight: 800;">TITRATION MILESTONE: METFORMIN + SGLT2</span>
              <span style="background: #dc2626; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">OCT 28 ANCHOR</span>
            </div>

            <!-- Before vs After Comparison Grid -->
            <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 6px; font-family: monospace; font-size: 0.66rem;">
              <!-- Before Column -->
              <div style="background: #fef2f2; border: 1px solid #fecaca; border-radius: 4px; padding: 6px;">
                <div style="color: #991b1b; font-weight: bold; border-bottom: 1px solid #fecaca; padding-bottom: 2px; margin-bottom: 4px;">
                  ⏪ BEFORE TITRATION (30D)
                </div>
                <div style="color: #7f1d1d;">• Mean Glucose: <strong>178 mg/dL</strong></div>
                <div style="color: #7f1d1d;">• Time-in-Range: <strong>72.4%</strong></div>
                <div style="color: #7f1d1d;">• Fasting Nadir: <strong>162 mg/dL</strong></div>
                <div style="color: #7f1d1d;">• Glycemic SD: <strong>±34 mg/dL</strong></div>
              </div>

              <!-- After Column -->
              <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 4px; padding: 6px;">
                <div style="color: #166534; font-weight: bold; border-bottom: 1px solid #bbf7d0; padding-bottom: 2px; margin-bottom: 4px;">
                  ⏩ AFTER TITRATION (30D)
                </div>
                <div style="color: #14532d;">• Mean Glucose: <strong>154 mg/dL (-24)</strong></div>
                <div style="color: #14532d;">• Time-in-Range: <strong>88.1% (+15.7%)</strong></div>
                <div style="color: #14532d;">• Fasting Nadir: <strong>138 mg/dL</strong></div>
                <div style="color: #14532d;">• Glycemic SD: <strong>±19 mg/dL</strong></div>
              </div>
            </div>

            <!-- Clinical Verdict Banner -->
            <div style="margin-top: 6px; background: #ecfdf5; border: 1.5px solid #6ee7b7; border-radius: 4px; padding: 6px; font-family: monospace; font-size: 0.67rem; color: #065f46; display: flex; align-items: center; justify-content: space-between;">
              <span>✅ <strong>CLINICAL OUTCOME:</strong> THERAPEUTIC PROGRESS CONFIRMED</span>
              <span style="background: #059669; color: #fff; padding: 2px 6px; border-radius: 3px; font-weight: bold;">-24 mg/dL</span>
            </div>
          </div>
    """
    s33['step_focus'] = [
        {"bullet": 0, "focus_desc": "Pinning '💊 MED CHANGE' Vertical Milestone Markers Across Longitudinal Timeline", "scroll_pct": 0.0},
        {"bullet": 1, "focus_desc": "Empirical Before-and-After Analysis: 30-Day Pre vs Post Titration Comparison", "scroll_pct": 0.45},
        {"bullet": 2, "focus_desc": "Definitive Therapeutic Progress Proof: Sustained -24 mg/dL Glycemic Drop", "scroll_pct": 0.85}
    ]

with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2, ensure_ascii=False)

print("Slides 29, 30, 31, 32, and 33 successfully updated in all_slides_data.json!")
