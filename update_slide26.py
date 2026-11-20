import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

s26 = None
for s in slides:
    if s['num'] == '26':
        s26 = s
        break

if not s26:
    print("Slide 26 not found!")
    exit(1)

# Fix screenshot and tab title
s26['tab_screenshot'] = 'images/lcars_chart_sleep.png'
s26['tab_title'] = '04-REPORTS CONSOLE • SOMNO-03 SLEEP ARCHITECTURE & HYPNOGRAM'

# Update voice narration to focus on on-screen sleep chart
s26['narration'] = (
    "Displayed on your screen is our SOMNO-03 Sleep Architecture and Circadian Hypnogram chart. "
    "Sleep quality is one of the most powerful and overlooked determinants of diabetic glycemic control. "
    "As shown in the hypnogram, the sleep array segments rest into Deep Delta sleep, Light sleep, REM dreaming, and wake epochs. "
    "During Stage N3 deep delta sleep, the endocrine system suppresses cortisol secretion and releases growth hormone, "
    "which restores hepatic insulin sensitivity for the upcoming day. "
    "Conversely, if sleep is fragmented by nocturnal hypoxia, frequent arousals, or apnea dips, "
    "the sympathetic nervous system stays active, surging nocturnal cortisol and epinephrine. "
    "This unsuppressed cortisol directly triggers hepatic glucose output, causing stubborn morning fasting spikes. "
    "By continuously monitoring sleep efficiency and deep sleep percentage, LCARS empowers you and your care team "
    "to verify that nocturnal rest is actively protecting your glycemic resilience."
)

s26['bullets'] = [
    [
        "SOMNO-03 Hypnogram Segmentation",
        "Continuously segments nocturnal sleep into Deep Delta (N3), Light (N1/N2), REM, and micro-awakenings to quantify restorative rest."
    ],
    [
        "Endocrine Cortisol Suppression",
        "Deep sleep actively blunts nocturnal cortisol release. Achieving healthy deep sleep (1.5–2.0h) restores hepatic insulin sensitivity."
    ],
    [
        "Hypoxic Fragmentation & Morning Surges",
        "Sleep apnea and frequent arousals trigger nocturnal catecholamine surges, driving excessive morning glycogenolysis and fasting spikes."
    ],
    [
        "Actionable Lifestyle Intervention",
        "Guides evening sleep hygiene, CPAP compliance, and bedtime timing to maximize deep restorative sleep and stabilize next-day glucose."
    ]
]

s26['takeaway'] = (
    "Sleep architecture directly governs metabolic health. Deep delta sleep suppresses nocturnal cortisol and restores insulin sensitivity, "
    "whereas fragmented sleep triggers adrenaline surges that drive stubborn morning fasting spikes. LCARS's SOMNO-03 telemetry "
    "tracks deep sleep and nocturnal stability to optimize glycemic resilience."
)

s26['right_header'] = 'SLEEP ARCHITECTURE & GLYCEMIC REGULATION'
s26['right_badge'] = 'SOMNO-03 HYPNOGRAM'
s26['right_badge_col'] = '#0284c7'

s26['right_html'] = """
          <!-- Sleep Architecture & Endocrine Glycemic Regulation Diagram -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 8px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">SOMNO-03 SLEEP STAGES & ENDOCRINE FEEDBACK</span>
              <span style="background: #0284c7; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">DELTA SLEEP vs CORTISOL</span>
            </div>

            <!-- SVG Diagram: Hypnogram Stages & Cortisol Curve -->
            <svg viewBox="0 0 420 170" style="width: 100%; height: auto; display: block; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0;">
              <!-- Left: Sleep Stages & Hypnogram (x: 10 to 220) -->
              <rect x="15" y="10" width="205" height="145" rx="5" fill="#f8fafc" stroke="#cbd5e1"/>
              <text x="117" y="24" fill="#334155" font-size="8" font-family="monospace" text-anchor="middle" font-weight="bold">SOMNO-03 HYPNOGRAM PROFILE</text>

              <!-- Stage Grid Lines -->
              <text x="45" y="42" fill="#dc2626" font-size="6" font-family="monospace" text-anchor="end">WAKE</text>
              <line x1="50" y1="40" x2="210" y2="40" stroke="#f1f5f9" stroke-width="1"/>

              <text x="45" y="67" fill="#6366f1" font-size="6" font-family="monospace" text-anchor="end">REM</text>
              <line x1="50" y1="65" x2="210" y2="65" stroke="#f1f5f9" stroke-width="1"/>

              <text x="45" y="92" fill="#0284c7" font-size="6" font-family="monospace" text-anchor="end">LIGHT</text>
              <line x1="50" y1="90" x2="210" y2="90" stroke="#f1f5f9" stroke-width="1"/>

              <text x="45" y="122" fill="#15803d" font-size="6" font-family="monospace" text-anchor="end" font-weight="bold">DEEP</text>
              <line x1="50" y1="120" x2="210" y2="120" stroke="#bbf7d0" stroke-width="1.5"/>

              <!-- Hypnogram Stepped Curve -->
              <path d="M 50 40 L 60 40 L 65 90 L 80 90 L 85 120 L 120 120 L 125 90 L 135 65 L 145 65 L 150 120 L 175 120 L 180 90 L 195 65 L 205 40" fill="none" stroke="#15803d" stroke-width="2.5"/>

              <!-- Deep Sleep Highlight Blocks -->
              <rect x="85" y="105" width="35" height="15" fill="#dcfce7" opacity="0.8" rx="2"/>
              <rect x="150" y="105" width="25" height="15" fill="#dcfce7" opacity="0.8" rx="2"/>
              <text x="117" y="142" fill="#166534" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">DEEP SLEEP: 1.9h (25.7%)</text>

              <!-- Right: Endocrine Mechanism (Cortisol & Insulin Sensitivity) -->
              <rect x="230" y="10" width="175" height="145" rx="5" fill="#ffffff" stroke="#cbd5e1"/>
              <text x="317" y="24" fill="#0284c7" font-size="8" font-family="monospace" text-anchor="middle" font-weight="bold">METABOLIC PATHWAY IMPACT</text>

              <!-- Pathway A: Restorative Deep Sleep -->
              <rect x="238" y="34" width="158" height="48" rx="4" fill="#f0fdf4" stroke="#86efac"/>
              <text x="245" y="47" fill="#15803d" font-size="7" font-family="monospace" font-weight="bold">✅ HIGH DEEP SLEEP (&gt;20%)</text>
              <text x="245" y="60" fill="#166534" font-size="6.5" font-family="monospace">• Suppresses nocturnal cortisol</text>
              <text x="245" y="72" fill="#166534" font-size="6.5" font-family="monospace">• Restores GLUT4 insulin sensitivity</text>

              <!-- Pathway B: Fragmented Hypoxic Sleep -->
              <rect x="238" y="90" width="158" height="56" rx="4" fill="#fef2f2" stroke="#fecaca"/>
              <text x="245" y="103" fill="#dc2626" font-size="7" font-family="monospace" font-weight="bold">⚠️ FRAGMENTED / HYPOXIC</text>
              <text x="245" y="116" fill="#991b1b" font-size="6.5" font-family="monospace">• Surges nighttime cortisol & adrenaline</text>
              <text x="245" y="128" fill="#991b1b" font-size="6.5" font-family="monospace">• Drives excessive morning glycogenolysis</text>
              <text x="245" y="140" fill="#991b1b" font-size="6.5" font-family="monospace">• Spikes morning fasting sugar (170+)</text>
            </svg>

            <!-- Clinical Key Insight Callout -->
            <div style="margin-top: 6px; background: #eff6ff; border-left: 4px solid #0284c7; padding: 5px 8px; border-radius: 4px; font-size: 0.67rem; color: #1e3a8a; line-height: 1.35; font-family: monospace;">
              <strong>CLINICAL SLEEP-GLYCEMIA CONNECTION:</strong> Patients with under 15% deep sleep exhibit a 23% reduction in whole-body insulin sensitivity. Tracking SOMNO-03 hypnogram architecture ensures lifestyle interventions directly support glycemic resilience.
            </div>
          </div>
"""

with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2, ensure_ascii=False)

print("Slide 26 updated with lcars_chart_sleep.png and comprehensive sleep architecture diagram!")
