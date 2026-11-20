import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

# Find slide 25
s25 = None
for s in slides:
    if s['num'] == '25':
        s25 = s
        break

if not s25:
    print("Slide 25 not found!")
    exit(1)

# Update screenshot and tab title
s25['tab_screenshot'] = 'images/lcars_chart_glucose.png'
s25['tab_title'] = '03-METABOLIC CONSOLE • 24-HOUR FASTING & NOCTURNAL GLYCEMIC NADIR'

# Update narration to focus on screen displayed 24h glucose curve and explain NADIR
s25['narration'] = (
    "Displayed on your screen is our continuous 24-hour glucose curve from Tab 03 Metabolic Console, "
    "focusing on circadian fasting windows and the nocturnal nadir. "
    "In physiological telemetry, the nadir is defined as the lowest trough or baseline minimum reached by a biological parameter during a cycle. "
    "In diabetes management, the overnight glycemic nadir occurs between 3:00 and 4:30 AM, "
    "when digestion is complete and hepatic glucose production rests. "
    "Illustrated on the right panel is the vital clinical distinction between the Dawn Phenomenon and the Somogyi Effect. "
    "Many diabetics wake up with elevated fasting numbers. "
    "If continuous telemetry reveals a normal 4:00 AM nadir near 100 milligrams per deciliter followed by a 7:00 AM climb to 174, "
    "this is the true Dawn Phenomenon, driven by morning surges of cortisol and growth hormone. "
    "However, if the nadir plunges into a nocturnal hypoglycemic crash below 60, "
    "the morning spike is an emergency Somogyi rebound. "
    "Because an isolated morning fingerstick only captures the elevated waking number and is completely blind to the nadir, "
    "it risks dangerous medication overtreatment. "
    "LCARS gives your care team the continuous overnight trajectory needed for absolute clinical certainty."
)

s25['bullets'] = [
    [
        "What is the Nocturnal Nadir?",
        "The nadir is the lowest physiological trough reached during a 24-hour cycle (typically occurring between 3:00 AM and 4:30 AM), establishing the true baseline metabolic floor before waking."
    ],
    [
        "Dawn Phenomenon vs Somogyi Rebound",
        "A high waking number preceded by a normal nadir is hepatic Dawn Phenomenon (cortisol surge); preceded by a hypoglycemic crash (<60 mg/dL), it is Somogyi rebound requiring medication reduction."
    ],
    [
        "The Snapshot Blindness Hazard",
        "A single waking fingerstick cannot see the overnight nadir. Increasing evening medication for a presumed dawn surge can prove fatal if the true culprit was an unrecognized Somogyi crash."
    ],
    [
        "Targeted Clinical Countermeasures",
        "Continuous nadir telemetry empowers doctors to prescribe targeted bedtime protein-fat buffers or a brisk morning walk to immediately clear hepatic glucose via GLUT4 translocation."
    ]
]

s25['takeaway'] = (
    "The nadir is the lowest physiological trough during a continuous cycle. Continuous overnight telemetry captures the 4:00 AM nadir, "
    "enabling clinicians to definitively distinguish a benign Dawn Phenomenon from a dangerous Somogyi hypoglycemic crash—a vital clinical distinction "
    "that isolated morning fingersticks completely miss."
)

s25['right_header'] = 'CIRCADIAN FASTING & CLINICAL NADIR DYNAMICS'
s25['right_badge'] = 'PHYSIOLOGICAL TROUGH ANALYSIS'
s25['right_badge_col'] = '#d97706'

s25['right_html'] = """
          <!-- What is a Nadir & Dawn Phenomenon Architecture -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">CLINICAL METABOLIC NADIR (00:00 TO 08:00 TRAJECTORY)</span>
              <span style="background: #d97706; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">TROUGH VS ZENITH</span>
            </div>

            <!-- SVG Diagram: Nocturnal Nadir & Dawn Phenomenon vs Somogyi Effect -->
            <svg viewBox="0 0 420 175" style="width: 100%; height: auto; display: block; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0;">
              <!-- Grid Background & Y-Axis Reference Lines -->
              <line x1="45" y1="25" x2="400" y2="25" stroke="#fee2e2" stroke-width="1"/>
              <text x="40" y="28" fill="#dc2626" font-size="6.5" font-family="monospace" text-anchor="end">180</text>

              <line x1="45" y1="65" x2="400" y2="65" stroke="#f1f5f9" stroke-width="1"/>
              <text x="40" y="68" fill="#64748b" font-size="6.5" font-family="monospace" text-anchor="end">140</text>

              <line x1="45" y1="105" x2="400" y2="105" stroke="#dcfce7" stroke-width="1"/>
              <text x="40" y="108" fill="#16a34a" font-size="6.5" font-family="monospace" text-anchor="end">100</text>

              <line x1="45" y1="145" x2="400" y2="145" stroke="#fef2f2" stroke-width="1.5" stroke-dasharray="3,2"/>
              <text x="40" y="148" fill="#991b1b" font-size="6.5" font-family="monospace" text-anchor="end" font-weight="bold">60 (HYPO)</text>

              <!-- Nighttime Window Shading (00:00 to 06:00) -->
              <rect x="50" y="15" width="220" height="140" fill="#f8fafc" opacity="0.8"/>
              <text x="160" y="22" fill="#64748b" font-size="7" font-family="monospace" text-anchor="middle">OVERNIGHT SLEEP & FASTING (00:00 - 06:00)</text>

              <!-- Curve A: True Dawn Phenomenon (Green/Orange Line) -->
              <path d="M 50 65 C 100 70, 140 100, 180 102 C 220 102, 270 50, 360 30 L 395 30" fill="none" stroke="#d97706" stroke-width="2.5"/>
              
              <!-- Point 1: 04:00 True Nadir -->
              <circle cx="180" cy="102" r="4" fill="#15803d"/>
              <rect x="125" y="112" width="110" height="15" rx="3" fill="#f0fdf4" stroke="#86efac"/>
              <text x="180" y="122" fill="#166534" font-size="6.5" font-family="monospace" text-anchor="middle" font-weight="bold">04:00 TRUE NADIR: 102 mg/dL</text>

              <!-- Point 2: 07:00 Fasting Dawn Surge -->
              <circle cx="360" cy="30" r="4" fill="#dc2626"/>
              <rect x="305" y="40" width="100" height="15" rx="3" fill="#fef2f2" stroke="#fca5a5"/>
              <text x="355" y="50" fill="#991b1b" font-size="6.5" font-family="monospace" text-anchor="middle" font-weight="bold">07:00 WAKING: 174 mg/dL</text>

              <!-- Curve B: Somogyi Rebound Crash (Red Dashed Line) -->
              <path d="M 50 65 C 100 80, 140 152, 180 152 C 220 152, 270 45, 360 30" fill="none" stroke="#dc2626" stroke-width="2" stroke-dasharray="4,3"/>
              <circle cx="180" cy="152" r="3.5" fill="#dc2626"/>
              <text x="180" y="165" fill="#991b1b" font-size="6" font-family="monospace" text-anchor="middle" font-weight="bold">⚠️ SOMOGYI CRASH: 52 mg/dL</text>

              <!-- Time Labels on X-Axis -->
              <text x="50" y="168" fill="#64748b" font-size="6.5" font-family="monospace">00:00</text>
              <text x="115" y="168" fill="#64748b" font-size="6.5" font-family="monospace">02:00</text>
              <text x="180" y="168" fill="#0284c7" font-size="7" font-family="monospace" font-weight="bold">04:00 (NADIR)</text>
              <text x="270" y="168" fill="#64748b" font-size="6.5" font-family="monospace">06:00</text>
              <text x="360" y="168" fill="#dc2626" font-size="7" font-family="monospace" font-weight="bold">07:00 (WAKE)</text>
            </svg>

            <!-- Detailed Medical Callout: What is a Nadir? -->
            <div style="margin-top: 8px; background: #fffbeb; border: 1.5px solid #fde68a; border-radius: 5px; padding: 8px; font-family: monospace; font-size: 0.68rem; line-height: 1.35;">
              <div style="color: #92400e; font-weight: 900; font-size: 0.74rem; display: flex; align-items: center; gap: 6px;">
                <span>🔬 WHAT IS A PHYSIOLOGICAL NADIR?</span>
              </div>
              <div style="color: #78350f; margin-top: 4px;">
                <strong>Definition:</strong> In medical science, the <em>nadir</em> (derived from Arabic <em>naẓīr</em>, meaning counterpart to zenith) is the lowest concentration, trough, or minimum level reached by a physiological biomarker during a cycle.
              </div>
              <div style="color: #78350f; margin-top: 4px;">
                <strong>The Nocturnal Glycemic Nadir:</strong> Occurs between 03:00 and 04:30 AM when insulin sensitivity is elevated and food digestion is absent. It reveals the patient's true baseline metabolic floor.
              </div>
            </div>

            <!-- Clinical Diagnostic Matrix: Dawn vs Somogyi -->
            <div style="margin-top: 6px; display: grid; grid-template-columns: 1fr 1fr; gap: 6px; font-family: monospace; font-size: 0.67rem;">
              <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 4px; padding: 6px;">
                <div style="color: #166534; font-weight: bold;">🌅 1. TRUE DAWN PHENOMENON</div>
                <div style="color: #14532d; margin-top: 2px;">
                  • 04:00 Nadir: Normal (95–110 mg/dL)<br>
                  • Cause: Morning cortisol & glucagon surge<br>
                  • Clinical Action: Evening protein buffer or titrated basal insulin.
                </div>
              </div>
              <div style="background: #fef2f2; border: 1px solid #fecaca; border-radius: 4px; padding: 6px;">
                <div style="color: #991b1b; font-weight: bold;">⚠️ 2. SOMOGYI REBOUND EFFECT</div>
                <div style="color: #7f1d1d; margin-top: 2px;">
                  • 04:00 Nadir: Hypoglycemic (&lt;60 mg/dL)<br>
                  • Cause: Adrenaline rescue dump after crash<br>
                  • Clinical Action: REDUCE evening medication (increasing it is dangerous!).
                </div>
              </div>
            </div>
          </div>
"""

with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2, ensure_ascii=False)

print("Slide 25 successfully updated in all_slides_data.json with correct screenshot and comprehensive Nadir explanation!")
