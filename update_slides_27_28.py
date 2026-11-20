import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

# Update Slide 27
s27 = [s for s in slides if s['num'] == '27'][0]
s27['tab_screenshot'] = 'images/lcars_slide27_habit_correlation.png'
s27['tab_title'] = '04-REPORTS CONSOLE • MULTI-VARIABLE HABIT CORRELATION'
s27['narration'] = (
    "Displayed on your screen is our Exercise and Metabolic Clearance Correlation engine from Tab 04 Reports Console. "
    "Diabetes management succeeds through daily habits, and here LCARS correlates your physical movement and meals "
    "directly against objective glycemic outcomes. "
    "As shown on screen, active workout days with at least 7,000 steps achieve an optimal mean glucose of 118 milligrams per deciliter "
    "and a Time-in-Range of 86.4 percent, with post-meal walks blunting glucose spikes by an average of 42 milligrams per deciliter. "
    "Conversely, sedentary days reflect wider glycemic variability and depressed time in range. "
    "On the right panel, our multi-variable regression reveals a strong inverse correlation coefficient of r equals minus 0.79 "
    "between daily step volume and mean glucose, providing patients and physicians with undeniable proof of what works best for your body."
)
s27['bullets'] = [
    [
        "Active Days vs Sedentary Days",
        "Directly quantifies that active days (≥7,000 steps) achieve 86.4% Time-in-Range and a tight mean glucose of 118 mg/dL (SD ±18.2)."
    ],
    [
        "Post-Meal Spike Blunting (-42 mg/dL)",
        "Proves that post-prandial walking immediately blunts glycemic spikes by an average of 42 mg/dL via non-insulin GLUT4 clearance."
    ],
    [
        "Mathematical Correlation (r = -0.79)",
        "Strong inverse correlation proves that physical step density systematically lowers 24-hour mean glycemic load."
    ],
    [
        "Continuous Behavioral Reinforcement",
        "Replaces vague lifestyle advice with real-time empirical validation, empowering patients to sustain healthy daily routines."
    ]
]
s27['takeaway'] = (
    "Daily habits govern glycemic control. Continuous telemetry proves that active days achieve 86.4% Time-in-Range and that post-meal walks "
    "blunt spikes by 42 mg/dL (r = -0.79), replacing guesswork with empirical behavioral correlation."
)
s27['right_header'] = 'BEHAVIORAL HABIT CORRELATION & CLEARANCE'
s27['right_badge'] = 'r = -0.79 CORRELATION'
s27['right_badge_col'] = '#15803d'
s27['right_html'] = """
          <!-- Habit Correlation & Regression Diagram -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">STEP VOLUME VS 24-HOUR MEAN GLUCOSE</span>
              <span style="background: #15803d; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">r = -0.79 INVERSE</span>
            </div>

            <!-- SVG Diagram: Steps vs Glucose Correlation Scatter -->
            <svg viewBox="0 0 420 165" style="width: 100%; height: auto; display: block; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0;">
              <!-- Grid Lines -->
              <line x1="45" y1="20" x2="400" y2="20" stroke="#fee2e2" stroke-width="1"/>
              <text x="40" y="24" fill="#dc2626" font-size="6.5" font-family="monospace" text-anchor="end">180</text>

              <line x1="45" y1="65" x2="400" y2="65" stroke="#f1f5f9" stroke-width="1"/>
              <text x="40" y="69" fill="#64748b" font-size="6.5" font-family="monospace" text-anchor="end">140</text>

              <line x1="45" y1="110" x2="400" y2="110" stroke="#dcfce7" stroke-width="1"/>
              <text x="40" y="114" fill="#16a34a" font-size="6.5" font-family="monospace" text-anchor="end">100</text>

              <!-- Axes -->
              <line x1="45" y1="140" x2="400" y2="140" stroke="#94a3b8" stroke-width="1.5"/>
              <text x="70" y="153" fill="#64748b" font-size="6.5" font-family="monospace">2,000</text>
              <text x="160" y="153" fill="#64748b" font-size="6.5" font-family="monospace">5,000</text>
              <text x="260" y="153" fill="#15803d" font-size="7" font-family="monospace" font-weight="bold">8,000 STEPS</text>
              <text x="360" y="153" fill="#15803d" font-size="7" font-family="monospace" font-weight="bold">12,000+ STEPS</text>

              <!-- Negative Correlation Trendline (r = -0.79) -->
              <line x1="60" y1="35" x2="380" y2="125" stroke="#15803d" stroke-width="2.5" stroke-dasharray="4,2"/>

              <!-- Sedentary Cluster (High Glucose) -->
              <circle cx="70" cy="30" r="3.5" fill="#dc2626" opacity="0.8"/>
              <circle cx="85" cy="45" r="3.5" fill="#dc2626" opacity="0.8"/>
              <circle cx="100" cy="38" r="3.5" fill="#dc2626" opacity="0.8"/>
              <rect x="60" y="55" width="85" height="15" rx="3" fill="#fef2f2" stroke="#fecaca"/>
              <text x="102" y="65" fill="#991b1b" font-size="6.5" font-family="monospace" text-anchor="middle" font-weight="bold">SEDENTARY: 168 mg/dL</text>

              <!-- Moderate Active Cluster -->
              <circle cx="180" cy="75" r="3.5" fill="#d97706" opacity="0.8"/>
              <circle cx="210" cy="82" r="3.5" fill="#d97706" opacity="0.8"/>

              <!-- Highly Active Cluster (Low Glucose) -->
              <circle cx="290" cy="105" r="3.5" fill="#16a34a" opacity="0.8"/>
              <circle cx="330" cy="112" r="3.5" fill="#16a34a" opacity="0.8"/>
              <circle cx="360" cy="118" r="3.5" fill="#16a34a" opacity="0.8"/>
              <rect x="290" y="85" width="95" height="15" rx="3" fill="#f0fdf4" stroke="#86efac"/>
              <text x="337" y="95" fill="#166534" font-size="6.5" font-family="monospace" text-anchor="middle" font-weight="bold">ACTIVE: 118 mg/dL</text>
            </svg>

            <!-- Metrics Summary Cards -->
            <div style="margin-top: 6px; display: grid; grid-template-columns: 1fr 1fr; gap: 6px; font-family: monospace; font-size: 0.67rem;">
              <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 4px; padding: 6px;">
                <div style="color: #166534; font-weight: bold;">🚶 ACTIVE DAYS (≥7,000 STEPS)</div>
                <div style="color: #14532d; margin-top: 2px;">
                  • Mean: <strong>118 mg/dL</strong> (SD ±18.2)<br>
                  • Time-in-Range: <strong>86.4%</strong><br>
                  • Post-Meal Drop: <strong>-42 mg/dL</strong>
                </div>
              </div>
              <div style="background: #fef2f2; border: 1px solid #fecaca; border-radius: 4px; padding: 6px;">
                <div style="color: #991b1b; font-weight: bold;">🛋️ SEDENTARY DAYS (&lt;3,000 STEPS)</div>
                <div style="color: #7f1d1d; margin-top: 2px;">
                  • Mean: <strong>164 mg/dL</strong> (SD ±34.6)<br>
                  • Time-in-Range: <strong>62.1%</strong><br>
                  • Sustained Postprandial Spikes
                </div>
              </div>
            </div>
          </div>
"""

# Update Slide 28
s28 = [s for s in slides if s['num'] == '28'][0]
s28['tab_screenshot'] = 'images/lcars_slide28_epic_gateway.png'
s28['tab_title'] = '04-REPORTS CONSOLE • HL7 FHIR R4 & EPIC MYCHART GATEWAY'
s28['narration'] = (
    "Displayed on your screen is the Reports Toolbar in Tab 04, highlighting our prominent blue button: Push Data to Doctor's Epic. "
    "Epic Systems is the world's leading Electronic Health Record software, used by over three hundred and five million patients "
    "and more than seventy-eight percent of hospital beds and primary care clinics across the globe. "
    "Why is direct Epic integration so critical? "
    "Traditionally, a massive clinical telemetry gap exists between a patient's everyday life and their fifteen-minute doctor appointment. "
    "Patients struggle to remember blood sugar trends, and doctors cannot inspect consumer smartphone apps. "
    "With a single click of our Epic upload button, LCARS securely packages your entire ninety-day continuous dataset—"
    "including twenty-five thousand glucose points, nocturnal nadirs, and blood pressure dipping—"
    "into standardized HL7 FHIR R4 clinical observation bundles. "
    "This data flows directly into your physician's Epic Hyperspace flowsheet and InBasket. "
    "This closes the loop between patient and doctor, enabling immediate medication titration and qualifying your care team "
    "for remote patient monitoring reimbursement."
)
s28['bullets'] = [
    [
        "What is Epic Systems?",
        "Epic is the global benchmark Electronic Health Record (EHR) powering >78% of healthcare systems (Mayo, Hopkins, Kaiser) and 305M patient records."
    ],
    [
        "The '📤 PUSH TO DOCTOR'S EPIC' Button",
        "Directly visible on the top toolbar: bundles 90 days of continuous telemetry into standardized HL7 FHIR R4 clinical observations."
    ],
    [
        "Closing the 15-Minute Clinical Gap",
        "Replaces subjective patient recall with verified time-series data delivered straight to your physician's Epic Hyperspace flowsheet."
    ],
    [
        "Actionable Titration & RPM Reimbursement",
        "Enables instant medication adjustments (insulin, GLP-1s, metformin) and qualifies care teams for CPT 99453/99454/99457 remote telemetry billing."
    ]
]
s28['takeaway'] = (
    "Epic is the world's leading EHR, serving 305M patients. With one click of '📤 PUSH TO DOCTOR'S EPIC', LCARS transmits 90 days "
    "of verified HL7 FHIR telemetry straight into your doctor's official clinical flowsheet, closing the communication gap and transforming patient care."
)
s28['right_header'] = 'EPIC EHR INTEGRATION & FHIR R4 TELEMETRY'
s28['right_badge'] = 'SMART-ON-FHIR R4 • 305M PATIENTS'
s28['right_badge_col'] = '#0284c7'
s28['right_html'] = """
          <!-- What is Epic & Why Direct Integration is Critical -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">EPIC ELECTRONIC HEALTH RECORD (EHR) BRIDGE</span>
              <span style="background: #0284c7; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">SMART-ON-FHIR R4</span>
            </div>

            <!-- What is Epic Educational Card -->
            <div style="background: #eff6ff; border: 1.5px solid #bfdbfe; border-radius: 5px; padding: 8px; font-family: monospace; font-size: 0.68rem; line-height: 1.35;">
              <div style="color: #1e40af; font-weight: 900; font-size: 0.74rem;">
                🏥 WHAT IS EPIC SYSTEMS?
              </div>
              <div style="color: #1e3a8a; margin-top: 4px;">
                <strong>Global Healthcare Standard:</strong> Epic is the gold-standard hospital and outpatient Electronic Health Record (EHR) platform managing records for over <strong>305 million patients worldwide</strong> (&gt;78% of US hospital capacity, including Mayo Clinic, Johns Hopkins, Kaiser Permanente, and Cleveland Clinic).
              </div>
            </div>

            <!-- The Toolbar Upload Button Highlight -->
            <div style="margin-top: 6px; background: #ffffff; border: 1.5px solid #0284c7; border-radius: 5px; padding: 8px; display: flex; align-items: center; justify-content: space-between;">
              <div>
                <div style="font-size: 0.65rem; color: #64748b; font-family: monospace;">TOOLBAR ACTION TRIGGER (TAB 04)</div>
                <div style="font-size: 0.85rem; font-weight: 900; color: #0284c7; font-family: monospace;">📤 PUSH TO DOCTOR'S EPIC</div>
              </div>
              <div style="background: #0284c7; color: #ffffff; font-size: 0.60rem; font-weight: bold; padding: 3px 8px; border-radius: 4px; font-family: monospace;">
                1-CLICK COMMIT
              </div>
            </div>

            <!-- Why This Integration is Essential (3 Core Pillars) -->
            <div style="margin-top: 6px; display: flex; flex-direction: column; gap: 5px; font-family: monospace; font-size: 0.67rem;">
              <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 4px; padding: 6px;">
                <strong style="color: #166534;">1. Closes the 15-Minute Visit Gap:</strong>
                <span style="color: #14532d;"> Eliminates oral recall guesswork. Doctors receive pre-correlated 90-day time-series directly inside their official clinical encounter charting.</span>
              </div>
              <div style="background: #f0f9ff; border: 1px solid #bae6fd; border-radius: 4px; padding: 6px;">
                <strong style="color: #0284c7;">2. Objective Drug Titration:</strong>
                <span style="color: #0c4a6e;"> Pharmacists and endocrinologists immediately adjust basal insulin, metformin, or GLP-1s based on verified nocturnal nadirs and post-meal spikes.</span>
              </div>
              <div style="background: #fffbeb; border: 1px solid #fde68a; border-radius: 4px; padding: 6px;">
                <strong style="color: #d97706;">3. Remote Patient Monitoring (RPM):</strong>
                <span style="color: #92400e;"> Directly qualifies clinical practices for Medicare/private insurance RPM billing codes (CPT 99453, 99454, 99457).</span>
              </div>
            </div>
          </div>
"""

with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2, ensure_ascii=False)

print("Slides 27 and 28 successfully updated with matching screenshots and comprehensive explanations!")
