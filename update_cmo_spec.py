import re
import subprocess
import os
import shutil

examples_7_to_10 = """
  <!-- GRAPH 7: SUB-REPORT 02 - LONGITUDINAL LEAD-I ECG & QTC INTERVAL -->
  <div class="chart-card">
    <div class="chart-header">
      <span class="chart-title">Example 7: Sub-Report 02 - Longitudinal Lead-I ECG &amp; QTc Autonomic Interval Tracking (ms)</span>
      <span class="chart-badge badge-red">CARDIOLOGY / DAN SCREENING</span>
    </div>

    <div style="background: #f8fafc; border-left: 4px solid #e53e3e; padding: 8px 12px; margin-bottom: 12px; font-size: 8.5pt;">
      <strong>CLINICAL MEASUREMENT PROTOCOL (USER-INITIATED ON WATCH):</strong>
      Patient selects ECG mode on watch and places thumb and index finger of opposite hand firmly on the <em>right side bezel electrode</em> for 30 uninterrupted seconds. This completes the Einthoven Lead-I thoracic circuit across the chest cavity. The workstation decodes the stored bipolar microvoltage, derives the QTc interval, and graphs autonomic LF/HF sympathovagal tone.
    </div>

    <!-- SVG Vector Graph 7 -->
    <svg viewBox="0 0 700 160" width="100%" height="160" xmlns="http://www.w3.org/2000/svg">
      <rect x="50" y="10" width="630" height="120" fill="#fafbfc" stroke="#e2e8f0" stroke-width="1"/>
      
      <!-- Normal QTc Range (<440 ms) -->
      <rect x="50" y="45" width="630" height="85" fill="#f0fff4" opacity="0.7"/>
      <line x1="50" y1="45" x2="680" y2="45" stroke="#e53e3e" stroke-width="1.5" stroke-dasharray="4,4"/>
      <text x="675" y="40" font-size="7.5" font-family="sans-serif" fill="#e53e3e" font-weight="bold" text-anchor="end">440 ms QTc Prolongation Threshold (DAN Risk)</text>

      <!-- Y-Axis Labels -->
      <text x="42" y="18" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">480 ms</text>
      <text x="42" y="48" font-size="7.5" font-family="sans-serif" fill="#e53e3e" font-weight="bold" text-anchor="end">440 ms</text>
      <text x="42" y="85" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">410 ms</text>
      <text x="42" y="125" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">370 ms</text>

      <!-- X-Axis Labels (Weekly Progression: W1 to W10) -->
      <text x="60" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W1</text>
      <text x="125" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W2</text>
      <text x="190" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W3</text>
      <text x="255" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W4</text>
      <text x="320" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W5</text>
      <text x="385" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W6</text>
      <text x="450" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W7</text>
      <text x="515" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W8</text>
      <text x="580" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W9</text>
      <text x="645" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">W10</text>

      <!-- QTc Polyline Trend -->
      <polyline fill="none" stroke="#e53e3e" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"
        points="60,82 125,78 190,85 255,80 320,74 385,76 450,71 515,68 580,69 645,65"/>
      
      <!-- LF/HF Sympathovagal Polyline Trend -->
      <polyline fill="none" stroke="#805ad5" stroke-width="2" stroke-dasharray="3,3" stroke-linecap="round" stroke-linejoin="round"
        points="60,110 125,108 190,105 255,102 320,98 385,95 450,92 515,90 580,88 645,86"/>

      <!-- Data Dots -->
      <circle cx="60" cy="82" r="3.5" fill="#e53e3e"/>
      <circle cx="190" cy="85" r="3.5" fill="#e53e3e"/>
      <circle cx="385" cy="76" r="3.5" fill="#e53e3e"/>
      <circle cx="645" cy="65" r="4" fill="#38a169"/>

      <text x="645" y="55" font-size="8" font-family="sans-serif" fill="#38a169" font-weight="bold" text-anchor="middle">412 ms (Stable)</text>
    </svg>
    <div style="font-size: 8pt; color: #718096; margin-top: 4px; display: flex; justify-content: space-between;">
      <span><strong>Red Solid Curve:</strong> Corrected QT Interval (QTc, ms - Normal &lt;440 ms)</span>
      <span><strong>Purple Dashed Curve:</strong> Autonomic LF/HF Sympathovagal Ratio (Target: 1.0 - 1.5)</span>
    </div>
  </div>

  <!-- GRAPH 8: SUB-REPORT 03 - MET ENERGY EXPENDITURE -->
  <div class="chart-card">
    <div class="chart-header">
      <span class="chart-title">Example 8: Sub-Report 03 - Daily MET Energy Expenditure vs. ADA Activity Standard</span>
      <span class="chart-badge badge-green">METABOLIC WORKLOAD / GLUT4</span>
    </div>

    <div style="background: #f8fafc; border-left: 4px solid #38a169; padding: 8px 12px; margin-bottom: 12px; font-size: 8.5pt;">
      <strong>CLINICAL MEASUREMENT PROTOCOL (100% AUTONOMOUS 24/7 BACKGROUND):</strong>
      Zero manual patient execution required. The watch firmware continuously samples triaxial kinetic accelerometry and high-frequency optical PPG heart rate. Telemetry is classified into Resting (1.0 MET), Sedentary (1.2-1.4 MET), Moderate (3.5 MET), and Vigorous (≥6.0 MET) and accumulated hourly. Synced into LCARS without patient burden.
    </div>

    <!-- SVG Vector Graph 8 -->
    <svg viewBox="0 0 700 160" width="100%" height="160" xmlns="http://www.w3.org/2000/svg">
      <rect x="50" y="10" width="630" height="120" fill="#fafbfc" stroke="#e2e8f0" stroke-width="1"/>
      
      <!-- Target Zone (>= 28.0 MET-hrs/day) -->
      <rect x="50" y="10" width="630" height="65" fill="#f0fff4" opacity="0.7"/>
      <line x1="50" y1="75" x2="680" y2="75" stroke="#38a169" stroke-width="1.5" stroke-dasharray="4,4"/>
      <text x="675" y="70" font-size="7.5" font-family="sans-serif" fill="#38a169" font-weight="bold" text-anchor="end">ADA Standard Baseline: ≥ 28.0 MET-hrs/day</text>

      <!-- Y-Axis Labels -->
      <text x="42" y="20" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">40 MET</text>
      <text x="42" y="50" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">34 MET</text>
      <text x="42" y="78" font-size="7.5" font-family="sans-serif" fill="#38a169" font-weight="bold" text-anchor="end">28 MET</text>
      <text x="42" y="115" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">20 MET</text>

      <!-- X-Axis Labels (Days of Week) -->
      <text x="75" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Mon</text>
      <text x="160" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Tue</text>
      <text x="245" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Wed</text>
      <text x="330" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Thu</text>
      <text x="415" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Fri</text>
      <text x="500" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Sat</text>
      <text x="585" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Sun</text>

      <!-- MET-hours Bars -->
      <rect x="62" y="58" width="26" height="72" fill="#48bb78" rx="3"/>
      <rect x="147" y="48" width="26" height="82" fill="#48bb78" rx="3"/>
      <rect x="232" y="62" width="26" height="68" fill="#48bb78" rx="3"/>
      <rect x="317" y="52" width="26" height="78" fill="#48bb78" rx="3"/>
      <rect x="402" y="44" width="26" height="86" fill="#38a169" rx="3"/>
      <rect x="487" y="36" width="26" height="94" fill="#38a169" rx="3"/>
      <rect x="572" y="48" width="26" height="82" fill="#48bb78" rx="3"/>

      <!-- Value Labels -->
      <text x="75" y="52" font-size="7.5" font-family="sans-serif" fill="#2f855a" font-weight="bold" text-anchor="middle">31.2</text>
      <text x="160" y="42" font-size="7.5" font-family="sans-serif" fill="#2f855a" font-weight="bold" text-anchor="middle">33.8</text>
      <text x="245" y="56" font-size="7.5" font-family="sans-serif" fill="#2f855a" font-weight="bold" text-anchor="middle">30.4</text>
      <text x="330" y="46" font-size="7.5" font-family="sans-serif" fill="#2f855a" font-weight="bold" text-anchor="middle">32.9</text>
      <text x="415" y="38" font-size="7.5" font-family="sans-serif" fill="#2f855a" font-weight="bold" text-anchor="middle">34.2</text>
      <text x="500" y="30" font-size="7.5" font-family="sans-serif" fill="#2f855a" font-weight="bold" text-anchor="middle">36.5</text>
      <text x="585" y="42" font-size="7.5" font-family="sans-serif" fill="#2f855a" font-weight="bold" text-anchor="middle">33.1</text>
    </svg>
    <div style="font-size: 8pt; color: #718096; margin-top: 4px;">
      <strong>Clinical Significance:</strong> Consistent compliance above 28.0 MET-hrs/day sustains non-insulin-mediated GLUT4 muscular glucose translocation, directly mitigating postprandial glucose volatility.
    </div>
  </div>

  <!-- GRAPH 9: SUB-REPORT 04 - BLOOD COMPOSITION & VISCOSITY -->
  <div class="chart-card">
    <div class="chart-header">
      <span class="chart-title">Example 9: Sub-Report 04 - Microvascular Blood Viscosity (cP) vs. Serum Uric Acid (mg/dL)</span>
      <span class="chart-badge badge-blue">HEMORHEOLOGY &amp; RENAL PROXY</span>
    </div>

    <div style="background: #f8fafc; border-left: 4px solid #3182ce; padding: 8px 12px; margin-bottom: 12px; font-size: 8.5pt;">
      <strong>CLINICAL MEASUREMENT PROTOCOL (ACTIVE FASTING OPTICAL SWEEP):</strong>
      Recommended upon morning waking prior to hydration or food intake. Patient rests wrist flat at heart level. Triggered either directly on watch menu or via LCARS Tab 12 Full Scan. The multi-wavelength optical sensor runs a 30-second sweep analyzing microvascular capillary attenuation, hemorheological scattering, and pulse wave contour.
    </div>

    <!-- SVG Vector Graph 9 -->
    <svg viewBox="0 0 700 160" width="100%" height="160" xmlns="http://www.w3.org/2000/svg">
      <rect x="50" y="10" width="630" height="120" fill="#fafbfc" stroke="#e2e8f0" stroke-width="1"/>
      
      <!-- Target Zone for Viscosity (3.5 - 4.5 cP) -->
      <rect x="50" y="55" width="630" height="45" fill="#ebf8ff" opacity="0.7"/>
      <line x1="50" y1="55" x2="680" y2="55" stroke="#dd6b20" stroke-width="1.5" stroke-dasharray="4,4"/>
      <text x="675" y="50" font-size="7.5" font-family="sans-serif" fill="#dd6b20" font-weight="bold" text-anchor="end">4.5 cP Upper Viscosity Safety Ceiling</text>

      <!-- Y-Axis Labels -->
      <text x="42" y="25" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">5.5 cP</text>
      <text x="42" y="58" font-size="7.5" font-family="sans-serif" fill="#dd6b20" font-weight="bold" text-anchor="end">4.5 cP</text>
      <text x="42" y="85" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">3.8 cP</text>
      <text x="42" y="115" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">3.0 cP</text>

      <!-- X-Axis Labels (Monthly Progression: M1 to M6) -->
      <text x="80" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 1</text>
      <text x="190" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 2</text>
      <text x="300" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 3</text>
      <text x="410" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 4</text>
      <text x="520" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 5</text>
      <text x="630" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 6</text>

      <!-- Viscosity Polyline (Solid Blue) -->
      <polyline fill="none" stroke="#3182ce" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"
        points="80,50 190,58 300,68 410,75 520,80 630,82"/>
      
      <!-- Uric Acid Polyline (Dashed Orange) -->
      <polyline fill="none" stroke="#ed8936" stroke-width="2" stroke-dasharray="3,3" stroke-linecap="round" stroke-linejoin="round"
        points="80,45 190,52 300,62 410,70 520,72 630,76"/>

      <!-- Data Dots -->
      <circle cx="80" cy="50" r="3.5" fill="#3182ce"/>
      <circle cx="300" cy="68" r="3.5" fill="#3182ce"/>
      <circle cx="630" cy="82" r="4" fill="#38a169"/>

      <text x="630" y="98" font-size="8" font-family="sans-serif" fill="#38a169" font-weight="bold" text-anchor="middle">3.8 cP (Normoviscous)</text>
    </svg>
    <div style="font-size: 8pt; color: #718096; margin-top: 4px; display: flex; justify-content: space-between;">
      <span><strong>Blue Curve:</strong> Estimated Microvascular Blood Viscosity (cP - Normal 3.5 - 4.5 cP)</span>
      <span><strong>Orange Dashed:</strong> Serum Uric Acid Proxy (mg/dL - Normal &lt;6.5 mg/dL)</span>
    </div>
  </div>

  <!-- GRAPH 10: SUB-REPORT 05 - BODY COMPOSITION BIA -->
  <div class="chart-card">
    <div class="chart-header">
      <span class="chart-title">Example 10: Sub-Report 05 - Dual-Frequency BIA Body Composition (Muscle vs. Fat %)</span>
      <span class="chart-badge badge-purple">METABOLIC SINK &amp; BODY COMPOSITION</span>
    </div>

    <div style="background: #f8fafc; border-left: 4px solid #805ad5; padding: 8px 12px; margin-bottom: 12px; font-size: 8.5pt;">
      <strong>CLINICAL MEASUREMENT PROTOCOL (USER-INITIATED MICRO-CURRENT):</strong>
      Patient places opposite hand fingers onto the dual bezel contact plates for 15 seconds. High-frequency micro-current derives bioelectrical impedance, separating lean skeletal muscle mass from adipose tissue.
    </div>

    <!-- SVG Vector Graph 10 -->
    <svg viewBox="0 0 700 160" width="100%" height="160" xmlns="http://www.w3.org/2000/svg">
      <rect x="50" y="10" width="630" height="120" fill="#fafbfc" stroke="#e2e8f0" stroke-width="1"/>
      
      <!-- Y-Axis Labels -->
      <text x="42" y="25" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">45 %</text>
      <text x="42" y="55" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">35 %</text>
      <text x="42" y="85" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">25 %</text>
      <text x="42" y="115" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="end">15 %</text>

      <!-- X-Axis Labels (Monthly Progression: M1 to M6) -->
      <text x="80" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 1</text>
      <text x="190" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 2</text>
      <text x="300" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 3</text>
      <text x="410" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 4</text>
      <text x="520" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 5</text>
      <text x="630" y="142" font-size="7.5" font-family="sans-serif" fill="#718096" text-anchor="middle">Month 6</text>

      <!-- Skeletal Muscle % Polyline (Solid Green - Rising) -->
      <polyline fill="none" stroke="#38a169" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"
        points="80,95 190,90 300,82 410,75 520,68 630,62"/>
      
      <!-- Body Fat % Polyline (Solid Purple - Dropping) -->
      <polyline fill="none" stroke="#805ad5" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"
        points="80,48 190,52 300,58 410,65 520,72 630,78"/>

      <!-- Data Dots -->
      <circle cx="80" cy="95" r="3.5" fill="#38a169"/>
      <circle cx="630" cy="62" r="4" fill="#38a169"/>
      <text x="630" y="52" font-size="8" font-family="sans-serif" fill="#38a169" font-weight="bold" text-anchor="middle">Muscle: +5.2%</text>

      <circle cx="80" cy="48" r="3.5" fill="#805ad5"/>
      <circle cx="630" cy="78" r="4" fill="#805ad5"/>
      <text x="630" y="92" font-size="8" font-family="sans-serif" fill="#805ad5" font-weight="bold" text-anchor="middle">Fat: -4.8%</text>
    </svg>
    <div style="font-size: 8pt; color: #718096; margin-top: 4px; display: flex; justify-content: space-between;">
      <span><strong>Green Curve:</strong> Skeletal Muscle Mass Percentage (Glucose Storage Sink)</span>
      <span><strong>Purple Curve:</strong> Body Fat Percentage (Visceral Adiposity / Insulin Resistance)</span>
    </div>
  </div>
"""

with open('docs/generate_cmo_spec.py', 'r', encoding='utf-8') as f:
    spec_code = f.read()

target = """    </table>
  </div>

  <!-- SECTION 5: CLINICAL INTERPRETATION GUIDE -->"""

if target in spec_code:
    spec_code = spec_code.replace(target, "    </table>\n  </div>\n\n" + examples_7_to_10 + "\n\n  <!-- SECTION 5: CLINICAL INTERPRETATION GUIDE -->")
    with open('docs/generate_cmo_spec.py', 'w', encoding='utf-8') as f:
        f.write(spec_code)
    print("Updated docs/generate_cmo_spec.py with Examples 7-10.")
else:
    print("Target not found in docs/generate_cmo_spec.py!")

