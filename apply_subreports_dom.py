import re
import hashlib

def run():
    with open('index.html', 'r', encoding='utf-8') as f:
        html = f.read()

    # 1. Update Sub-Report 02 DOM: Insert Walk-through Banner & Chart Container
    old_sub02_end = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CARDIAC RESILIENCE</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">88 / 100</div>
                <div style="font-size: 0.7rem; color: #15803d;">Healthy Vagal Tone (Comet)</div>
              </div>
            </div>
          </div>"""

    new_sub02_end = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CARDIAC RESILIENCE</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">88 / 100</div>
                <div style="font-size: 0.7rem; color: #15803d;">Healthy Vagal Tone (Comet)</div>
              </div>
            </div>
            <!-- Clinical Guidance & Watch Test Protocol -->
            <div style="margin-top: 12px; font-size: 0.78rem; color: #881337; line-height: 1.45; background: #fff1f2; padding: 9px 12px; border-radius: 5px; border: 1px solid #fecdd3;">
              <strong>Watch Lead-I ECG Protocol:</strong> To record a diagnostic Lead-I ECG strip on the Veepoo watch, navigate to the ECG menu, place opposite thumb and forefinger on the right-side bezel electrode, and keep still for 30 seconds. Upon completion vibration, click Vault Sync in LCARS to download the digitized QTc interval and cardiac vector. Background Lead-I monitoring can also be enabled in 05-SETUP (0xB8 Page 1).
            </div>
            <!-- Sub-Report 02 Longitudinal Trend Chart -->
            <div style="margin-top: 12px; background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 10px;">
              <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
                <span style="font-weight: 800; font-size: 0.95rem; color: #be123c;" id="repChartEcgTitle">LONGITUDINAL ECG LEAD-I &amp; QTc DURATION TREND (MS)</span>
                <span style="font-size: 0.75rem; color: #64748b; font-family: var(--lcars-mono);">440 MS NORMAL CEILING &bull; SYMPATHOVAGAL LF/HF RATIO</span>
              </div>
              <div id="curveTimelineECG" style="width: 100%; min-height: 225px; position: relative;"></div>
            </div>
          </div>"""

    if old_sub02_end in html:
        html = html.replace(old_sub02_end, new_sub02_end, 1)
        print("[✔] Injected Sub-Report 02 ECG Trend Chart & Protocol Banner")
    else:
        print("[!] Could not match old_sub02_end")

    # 2. Update Sub-Report 03 DOM: Insert Walk-through Banner & Chart Container
    old_sub03_end = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">VIGOROUS ACTIVATION (&ge;6 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">18 <span style="font-size: 0.8rem;">min</span></div>
                <div style="font-size: 0.7rem; color: #0284c7;">1.8 MET-hrs</div>
              </div>
            </div>
          </div>"""

    new_sub03_end = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">VIGOROUS ACTIVATION (&ge;6 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">18 <span style="font-size: 0.8rem;">min</span></div>
                <div style="font-size: 0.7rem; color: #0284c7;">1.8 MET-hrs</div>
              </div>
            </div>
            <!-- Clinical Guidance & Continuous Harvester Info -->
            <div style="margin-top: 12px; font-size: 0.78rem; color: #14532d; line-height: 1.45; background: #f0fdf4; padding: 9px 12px; border-radius: 5px; border: 1px solid #bbf7d0;">
              <strong>Automated 24/7 Energy Derivation:</strong> MET energy expenditure is continuously derived from the triaxial accelerometer and heart rate kinetics in watch firmware (0xB8 Page 1). No manual test is required. LCARS automatically aggregates cumulative daily MET-hours to track ADA physical activity compliance (&ge; 28 MET-hrs/day).
            </div>
            <!-- Sub-Report 03 Longitudinal Trend Chart -->
            <div style="margin-top: 12px; background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 10px;">
              <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
                <span style="font-weight: 800; font-size: 0.95rem; color: #15803d;" id="repChartMetTitle">MET ENERGY EXPENDITURE &amp; ADA PHYSICAL ACTIVITY COMPLIANCE</span>
                <span style="font-size: 0.75rem; color: #64748b; font-family: var(--lcars-mono);">&ge; 28.0 MET-HRS/DAY STANDARD &bull; CUMULATIVE MET LOAD</span>
              </div>
              <div id="curveTimelineMET" style="width: 100%; min-height: 225px; position: relative;"></div>
            </div>
          </div>"""

    if old_sub03_end in html:
        html = html.replace(old_sub03_end, new_sub03_end, 1)
        print("[✔] Injected Sub-Report 03 MET Trend Chart & Guidance Banner")
    else:
        print("[!] Could not match old_sub03_end")

    # 3. Update Sub-Report 04 DOM: Insert Walk-through Banner & Chart Container
    old_sub04_end = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">OXYGEN P50 AFFINITY</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">26.8 <span style="font-size: 0.8rem;">mmHg</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">Normal dissociation curve</div>
              </div>
            </div>
          </div>"""

    new_sub04_end = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">OXYGEN P50 AFFINITY</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">26.8 <span style="font-size: 0.8rem;">mmHg</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">Normal dissociation curve</div>
              </div>
            </div>
            <!-- Clinical Guidance & Optical Sweep Protocol -->
            <div style="margin-top: 12px; font-size: 0.78rem; color: #713f12; line-height: 1.45; background: #fefce8; padding: 9px 12px; border-radius: 5px; border: 1px solid #fef08a;">
              <strong>Watch Optical Blood Composition Protocol:</strong> For optical blood composition (viscosity, uric acid, and lipid proxy), test in a fasting morning state upon waking. Navigate to the Blood Composition screen on the watch and tap Start. Maintain still arm position for 30 seconds while multi-wavelength PPG sweeps capillary perfusion. Results sync to LCARS via 0x89 telemetry packet.
            </div>
            <!-- Sub-Report 04 Longitudinal Trend Chart -->
            <div style="margin-top: 12px; background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 10px;">
              <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
                <span style="font-weight: 800; font-size: 0.95rem; color: #b45309;" id="repChartBloodCompTitle">MICROVASCULAR RHEOLOGY, VISCOSITY &amp; LIPID TONOMETRY TREND</span>
                <span style="font-size: 0.75rem; color: #64748b; font-family: var(--lcars-mono);">VISCOSITY (3.5 - 4.5 cP) &bull; SERUM URIC ACID (&lt;6.5 MG/DL)</span>
              </div>
              <div id="curveTimelineBloodComp" style="width: 100%; min-height: 225px; position: relative;"></div>
            </div>
          </div>"""

    if old_sub04_end in html:
        html = html.replace(old_sub04_end, new_sub04_end, 1)
        print("[✔] Injected Sub-Report 04 Blood Composition Trend Chart & Guidance Banner")
    else:
        print("[!] Could not match old_sub04_end")

    # 4. Update Sub-Report 05 DOM: Insert Walk-through Banner & Chart Container
    old_sub05_end = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CELLULAR WATER RATIO</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">0.62</div>
                <div style="font-size: 0.7rem; color: #15803d;">No Peripheral Edema</div>
              </div>
            </div>
          </div>"""

    new_sub05_end = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CELLULAR WATER RATIO</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">0.62</div>
                <div style="font-size: 0.7rem; color: #15803d;">No Peripheral Edema</div>
              </div>
            </div>
            <!-- Clinical Guidance & Skeletal Muscle Glucose Sink -->
            <div style="margin-top: 12px; font-size: 0.78rem; color: #3b0764; line-height: 1.45; background: #faf5ff; padding: 9px 12px; border-radius: 5px; border: 1px solid #e9d5ff;">
              <strong>Clinical Significance:</strong> Skeletal muscle mass represents the primary glucose sink for non-insulin mediated GLUT4 clearance. Bioelectrical impedance analysis (BIA) tracks the ratio of lean muscle mass to visceral adipose tissue across dietary regimens and titration phases.
            </div>
            <!-- Sub-Report 05 Longitudinal Trend Chart -->
            <div style="margin-top: 12px; background: #ffffff; border: 1px solid #e2e8f0; border-radius: 6px; padding: 10px;">
              <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
                <span style="font-weight: 800; font-size: 0.95rem; color: #6d28d9;" id="repChartBodyCompTitle">BODY COMPOSITION &amp; MULTI-FREQUENCY BIOIMPEDANCE TREND</span>
                <span style="font-size: 0.75rem; color: #64748b; font-family: var(--lcars-mono);">SKELETAL MUSCLE % &bull; BODY FAT % &bull; VISCERAL ADIPOSE</span>
              </div>
              <div id="curveTimelineBodyComp" style="width: 100%; min-height: 225px; position: relative;"></div>
            </div>
          </div>"""

    if old_sub05_end in html:
        html = html.replace(old_sub05_end, new_sub05_end, 1)
        print("[✔] Injected Sub-Report 05 Body Composition Trend Chart & Guidance Banner")
    else:
        print("[!] Could not match old_sub05_end")

    # 5. Update Timeframe Selector Buttons at Top of Tall Chart
    old_tall_btns = """              <div style="display: flex; gap: 6px;">
                <button id="btnTallDaily" class="lcars-btn btn-banana" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('daily')">DAILY 24-HOUR</button>
                <button id="btnTallWeekly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('weekly')">WEEKLY 7-DAY</button>
                <button id="btnTallYearly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('yearly')">YEARLY 12-MONTH</button>
              </div>"""

    new_tall_btns = """              <div style="display: flex; gap: 6px; flex-wrap: wrap;">
                <button id="btnTallDaily" class="lcars-btn btn-banana" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('daily')">DAILY 24-HOUR</button>
                <button id="btnTallWeekly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('weekly')">WEEKLY 7-DAY</button>
                <button id="btnTallMonthly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('monthly')">MONTHLY 30-DAY</button>
                <button id="btnTallQuarterly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('quarterly')">QUARTERLY (QTR)</button>
                <button id="btnTallYearly" class="lcars-btn btn-blue" style="font-size: 0.8rem; padding: 4px 12px;" onclick="setTallChartTimeframe('yearly')">YEARLY 12-MONTH</button>
              </div>"""

    if old_tall_btns in html:
        html = html.replace(old_tall_btns, new_tall_btns, 1)
        print("[✔] Injected Clean 5-Timeframe Buttons in Tall Chart Header")
    else:
        print("[!] Could not match old_tall_btns")

    # 6. Check div balance
    opens = len(re.findall(r'<div\b', html, re.I))
    closes = len(re.findall(r'</div\b', html, re.I))
    diff = opens - closes
    print(f"Div count: open={opens}, close={closes}, diff={diff}")
    assert diff == 0, f"Div imbalance detected: {diff}"

    with open('index.html', 'w', encoding='utf-8') as f:
        f.write(html)
    with open('w11_htm_app/index.html', 'w', encoding='utf-8') as f:
        f.write(html)
    print("[✔] Successfully updated index.html and w11_htm_app/index.html with bit-for-bit parity!")

run()
