import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

# -----------------------------------------------------------------------------
# SLIDE 14: Optical Glucose Absorption Spectroscopy Diagram
# -----------------------------------------------------------------------------
s14_diagram = """
          <!-- Physical Sensor Architecture & Optical Absorption Diagram -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 6px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">OPTICAL PPG SPECTROSCOPY & DERMAL PATH</span>
              <span style="background: #0284c7; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">BEER-LAMBERT ABSORPTION</span>
            </div>

            <!-- SVG Diagram: Wrist Tissue Layer & Optical Ray Tracing -->
            <svg viewBox="0 0 420 170" style="width: 100%; height: auto; display: block; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0;">
              <!-- Watch Case Body -->
              <rect x="30" y="10" width="360" height="28" rx="5" fill="#1e293b"/>
              <text x="210" y="24" fill="#94a3b8" font-size="9" font-family="monospace" text-anchor="middle" font-weight="bold">FITAOS 5150 BIOSENSOR UNDERSIDE CHASSIS</text>

              <!-- Optical Sensors on Watch Base -->
              <!-- LED Emitter 1 -->
              <rect x="90" y="38" width="34" height="12" fill="#16a34a" rx="2"/>
              <text x="107" y="47" fill="#ffffff" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">525nm</text>
              <!-- Photodiode Detector -->
              <rect x="180" y="38" width="60" height="12" fill="#0284c7" rx="2"/>
              <text x="210" y="47" fill="#ffffff" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">PHOTODIODE</text>
              <!-- LED Emitter 2 (NIR) -->
              <rect x="295" y="38" width="35" height="12" fill="#dc2626" rx="2"/>
              <text x="312" y="47" fill="#ffffff" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">940nm</text>

              <!-- Wrist Skin Layers -->
              <!-- Stratum Corneum / Epidermis -->
              <rect x="30" y="52" width="360" height="26" fill="#fed7aa" opacity="0.6"/>
              <text x="40" y="68" fill="#9a3412" font-size="8" font-family="monospace" font-weight="bold">EPIDERMIS & INTERSTITIAL FLUID (ISF)</text>

              <!-- Dermis & Capillary Bed -->
              <rect x="30" y="78" width="360" height="42" fill="#fecaca" opacity="0.6"/>
              <text x="40" y="94" fill="#991b1b" font-size="8" font-family="monospace" font-weight="bold">DERMIS & PULSATILE ARTERIAL CAPILLARY LOOPS</text>

              <!-- Subcutaneous Fat / Artery -->
              <rect x="30" y="120" width="360" height="40" fill="#fef08a" opacity="0.4"/>
              <text x="40" y="136" fill="#854d0e" font-size="8" font-family="monospace" font-weight="bold">DEEP ARTERIAL BED (RADIAL HEMODYNAMICS)</text>

              <!-- Light Ray Paths (Banana-shaped optical penetration) -->
              <path d="M 107 50 Q 150 115, 200 50" fill="none" stroke="#22c55e" stroke-width="2.5" stroke-dasharray="4,2"/>
              <path d="M 312 50 Q 260 135, 220 50" fill="none" stroke="#ef4444" stroke-width="2.5" stroke-dasharray="4,2"/>

              <!-- Glucose Molecules in ISF -->
              <circle cx="140" cy="88" r="4" fill="#0284c7" opacity="0.8"/>
              <text x="148" y="91" fill="#0369a1" font-size="7" font-family="monospace">Glucose</text>
              <circle cx="270" cy="98" r="4" fill="#0284c7" opacity="0.8"/>
              <circle cx="180" cy="105" r="4" fill="#0284c7" opacity="0.8"/>
              <circle cx="240" cy="120" r="4" fill="#0284c7" opacity="0.8"/>

              <!-- Pulse Wave Indicator -->
              <path d="M 320 145 Q 330 130, 335 145 T 345 145" fill="none" stroke="#dc2626" stroke-width="2"/>
              <text x="355" y="148" fill="#991b1b" font-size="7" font-family="monospace">Pulsatile Flow</text>
            </svg>

            <!-- Physical Equations & Principle Callout -->
            <div style="margin-top: 6px; display: grid; grid-template-columns: 1fr 1fr; gap: 6px; font-family: monospace; font-size: 0.67rem;">
              <div style="background: #eff6ff; border: 1px solid #bfdbfe; border-radius: 4px; padding: 5px;">
                <div style="color: #1e40af; font-weight: bold;">🔬 BEER-LAMBERT TRANSMITTANCE</div>
                <div style="color: #1e3a8a; margin-top: 2px;">I = I₀ · e^(-ε(λ)·c·d)<br>Differential attenuation reveals glucose concentration c in capillary bed.</div>
              </div>
              <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 4px; padding: 5px;">
                <div style="color: #166534; font-weight: bold;">📐 ADAG eA1C PROJECTION</div>
                <div style="color: #14532d; margin-top: 2px;">eA1C = (Average Glucose + 46.7) / 28.7<br>Continuous 288 epochs/day eliminate static quarterly error.</div>
              </div>
            </div>
          </div>
"""

# -----------------------------------------------------------------------------
# SLIDE 15: Dual-Sensor Thermal Matrix & Circadian Nadir Diagram
# -----------------------------------------------------------------------------
s15_diagram = """
          <!-- Dual-Sensor Thermal Matrix & Heat-Flux Vector Diagram -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 6px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">DUAL-SENSOR HEAT-FLUX & CIRCADIAN NADIR</span>
              <span style="background: #0284c7; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">Tcore vs Tskin</span>
            </div>

            <!-- SVG Diagram: Dual Thermistor Heat-Flux & Circadian Curve -->
            <svg viewBox="0 0 420 170" style="width: 100%; height: auto; display: block; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0;">
              <!-- Left: Dual Thermistor Sensor Architecture (x: 10 to 200) -->
              <rect x="15" y="10" width="180" height="75" rx="5" fill="#f1f5f9" stroke="#cbd5e1"/>
              <text x="105" y="24" fill="#334155" font-size="8" font-family="monospace" text-anchor="middle" font-weight="bold">DUAL THERMISTOR ARCHITECTURE</text>
              
              <!-- Watch Internal Sensor 2 -->
              <rect x="30" y="32" width="150" height="18" rx="3" fill="#e0f2fe" stroke="#0284c7"/>
              <text x="105" y="44" fill="#0369a1" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">SENSOR 2: INTERNAL AMBIENT/REF (T_int)</text>
              
              <!-- Heat Flux Arrow -->
              <line x1="105" y1="52" x2="105" y2="60" stroke="#dc2626" stroke-width="2" marker-end="url(#arrow)"/>
              
              <!-- Epidermal Contact Sensor 1 -->
              <rect x="30" y="62" width="150" height="18" rx="3" fill="#fed7aa" stroke="#ea580c"/>
              <text x="105" y="74" fill="#9a3412" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">SENSOR 1: EPIDERMAL CONTACT (T_skin: 85.6°F)</text>
              
              <!-- Wrist Tissue -->
              <rect x="15" y="90" width="180" height="65" rx="4" fill="#fee2e2"/>
              <text x="105" y="105" fill="#991b1b" font-size="7.5" font-family="monospace" text-anchor="middle" font-weight="bold">DEEP ARTERIAL CORE PERFUSION</text>
              <text x="105" y="125" fill="#dc2626" font-size="13" font-family="monospace" text-anchor="middle" font-weight="900">T_core = 96.1°F</text>
              <text x="105" y="145" fill="#7f1d1d" font-size="6.5" font-family="monospace" text-anchor="middle">Heat-Flux Model: Tcore = Tskin + α(Tskin - Tint)</text>

              <!-- Right: 24-Hour Circadian Temperature Curve (x: 210 to 410) -->
              <rect x="210" y="10" width="195" height="145" rx="5" fill="#ffffff" stroke="#cbd5e1"/>
              <text x="307" y="24" fill="#0284c7" font-size="8" font-family="monospace" text-anchor="middle" font-weight="bold">24-HOUR CIRCADIAN THERMAL CYCLE</text>

              <!-- Grid Lines -->
              <line x1="230" y1="40" x2="395" y2="40" stroke="#f1f5f9" stroke-width="1"/>
              <line x1="230" y1="75" x2="395" y2="75" stroke="#f1f5f9" stroke-width="1"/>
              <line x1="230" y1="110" x2="395" y2="110" stroke="#f1f5f9" stroke-width="1"/>

              <!-- Y-Axis Labels -->
              <text x="225" y="43" fill="#64748b" font-size="6.5" font-family="monospace" text-anchor="end">98.6°</text>
              <text x="225" y="78" fill="#64748b" font-size="6.5" font-family="monospace" text-anchor="end">97.2°</text>
              <text x="225" y="113" fill="#64748b" font-size="6.5" font-family="monospace" text-anchor="end">95.8°</text>

              <!-- Circadian Curve (Dips deeply at 4:30 AM Nadir, rises afternoon) -->
              <path d="M 235 60 C 260 70, 275 118, 295 118 C 320 118, 345 45, 385 45" fill="none" stroke="#0284c7" stroke-width="2.5"/>

              <!-- Circadian Nadir Marker -->
              <circle cx="295" cy="118" r="4" fill="#dc2626"/>
              <text x="295" y="132" fill="#dc2626" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">4:30 AM NADIR: 96.0°F</text>

              <!-- Time Labels -->
              <text x="235" y="150" fill="#64748b" font-size="6" font-family="monospace">00:00</text>
              <text x="295" y="150" fill="#dc2626" font-size="6" font-family="monospace" font-weight="bold">04:30</text>
              <text x="350" y="150" fill="#64748b" font-size="6" font-family="monospace">12:00</text>
              <text x="390" y="150" fill="#64748b" font-size="6" font-family="monospace">20:00</text>
            </svg>

            <!-- Physiological Significance Callout -->
            <div style="margin-top: 6px; background: #eff6ff; border-left: 4px solid #0284c7; padding: 5px 8px; border-radius: 4px; font-size: 0.67rem; color: #1e3a8a; line-height: 1.35; font-family: monospace;">
              <strong>CIRCADIAN NADIR BIOMARKER:</strong> Deep nighttime cooling to ~96.0°F reflects healthy parasympathetic tone and metabolic rest. Blunted or absent nocturnal dipping signals chronic systemic inflammation and nocturnal stress.
            </div>
          </div>
"""

# -----------------------------------------------------------------------------
# SLIDE 16: Pulse Wave Transit Time (PWTT) & Blood Pressure Diagram
# -----------------------------------------------------------------------------
s16_diagram = """
          <!-- Pulse Wave Transit Time (PWTT) & Hemodynamic Arterial Waveform -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 6px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">PWTT DUAL-SIGNAL HEMODYNAMIC SYNCHRONIZATION</span>
              <span style="background: #d97706; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">ECG + PPG TIMING</span>
            </div>

            <!-- SVG Diagram: ECG R-Wave to Wrist PPG Transit Time & Waveform -->
            <svg viewBox="0 0 420 170" style="width: 100%; height: auto; display: block; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0;">
              <!-- Signal 1: Central Cardiac ECG (Ventricular Ejection Start) -->
              <text x="20" y="24" fill="#0284c7" font-size="8" font-family="monospace" font-weight="bold">1. CARDIAC ECG (AORTIC VALVE OPENS AT R-PEAK: t₀)</text>
              <path d="M 20 45 L 80 45 L 88 48 L 95 18 L 102 52 L 108 45 L 140 45 Q 155 35, 170 45 L 400 45" fill="none" stroke="#0284c7" stroke-width="2"/>
              <circle cx="95" cy="18" r="3" fill="#dc2626"/>
              <text x="95" y="12" fill="#dc2626" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">R-PEAK (t₀)</text>

              <!-- Vertical Timing Markers showing PWTT -->
              <line x1="95" y1="18" x2="95" y2="125" stroke="#dc2626" stroke-width="1.5" stroke-dasharray="3,2"/>
              <line x1="210" y1="75" x2="210" y2="125" stroke="#15803d" stroke-width="1.5" stroke-dasharray="3,2"/>

              <!-- PWTT Delta Time Arrow -->
              <line x1="95" y1="65" x2="210" y2="65" stroke="#d97706" stroke-width="2"/>
              <rect x="120" y="56" width="65" height="16" fill="#fef3c7" rx="3" stroke="#f59e0b"/>
              <text x="152" y="68" fill="#b45309" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">PWTT = 215 ms</text>

              <!-- Signal 2: Peripheral Wrist Optical PPG (Arterial Pulse Arrival) -->
              <text x="20" y="85" fill="#15803d" font-size="8" font-family="monospace" font-weight="bold">2. WRIST OPTICAL PPG (PULSE ARRIVAL & DICROTIC NOTCH: t₁)</text>
              <path d="M 20 120 L 170 120 C 185 120, 195 78, 210 78 C 225 78, 235 98, 245 94 C 255 90, 270 120, 310 120 L 400 120" fill="none" stroke="#15803d" stroke-width="2.5"/>
              <circle cx="210" cy="78" r="3" fill="#15803d"/>
              <text x="210" y="72" fill="#15803d" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">SYSTOLIC PEAK</text>
              <circle cx="245" cy="94" r="2.5" fill="#d97706"/>
              <text x="245" y="88" fill="#d97706" font-size="6.5" font-family="monospace" text-anchor="middle">DICROTIC NOTCH</text>

              <!-- PWV & BP Readout Card -->
              <rect x="290" y="130" width="120" height="32" rx="4" fill="#f8fafc" stroke="#cbd5e1"/>
              <text x="350" y="142" fill="#334155" font-size="7" font-family="monospace" text-anchor="middle">CALCULATED BP</text>
              <text x="350" y="157" fill="#dc2626" font-size="11" font-family="monospace" text-anchor="middle" font-weight="bold">126 / 84 MMHG</text>
            </svg>

            <!-- Physical Law Callout -->
            <div style="margin-top: 6px; display: grid; grid-template-columns: 1fr 1fr; gap: 6px; font-family: monospace; font-size: 0.67rem;">
              <div style="background: #fffbeb; border: 1px solid #fde68a; border-radius: 4px; padding: 5px;">
                <div style="color: #92400e; font-weight: bold;">📐 MOENS-KORTEWEG PHYSICAL LAW</div>
                <div style="color: #78350f; margin-top: 2px;">PWV = Distance / PWTT = √(E·h / 2rρ)<br>Stiffened hypertensive arteries accelerate pulse wave arrival.</div>
              </div>
              <div style="background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 4px; padding: 5px;">
                <div style="color: #166534; font-weight: bold;">🌙 NOCTURNAL DIPPING CRITERION</div>
                <div style="color: #14532d; margin-top: 2px;">Verifies healthy 10%–20% overnight blood pressure dip without disturbing sleep with an inflating cuff.</div>
              </div>
            </div>
          </div>
"""

# -----------------------------------------------------------------------------
# SLIDE 17: Dual-Wavelength SpO2 & Apnea Screening Diagram
# -----------------------------------------------------------------------------
s17_diagram = """
          <!-- Dual-Wavelength Spectrophotometry & OSAHS Hypoxia Diagram -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 6px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">DUAL-WAVELENGTH SPECTROPHOTOMETRY & OSAHS</span>
              <span style="background: #15803d; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">660nm vs 940nm</span>
            </div>

            <!-- SVG Diagram: Absorption Curves & Overnight Apnea Desaturation Dip -->
            <svg viewBox="0 0 420 170" style="width: 100%; height: auto; display: block; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0;">
              <!-- Left: Spectrophotometric Absorption Spectra (x: 10 to 200) -->
              <rect x="15" y="10" width="185" height="145" rx="5" fill="#f8fafc" stroke="#cbd5e1"/>
              <text x="107" y="24" fill="#334155" font-size="8" font-family="monospace" text-anchor="middle" font-weight="bold">HEMOGLOBIN ABSORPTION SPECTRA</text>

              <!-- 660nm Red Line (Hb absorbs more) -->
              <line x1="60" y1="35" x2="60" y2="135" stroke="#ef4444" stroke-width="1.5" stroke-dasharray="2,2"/>
              <text x="60" y="145" fill="#dc2626" font-size="6.5" font-family="monospace" text-anchor="middle" font-weight="bold">660nm (Red)</text>

              <!-- 940nm NIR Line (HbO2 absorbs more) -->
              <line x1="150" y1="35" x2="150" y2="135" stroke="#0284c7" stroke-width="1.5" stroke-dasharray="2,2"/>
              <text x="150" y="145" fill="#0369a1" font-size="6.5" font-family="monospace" text-anchor="middle" font-weight="bold">940nm (IR)</text>

              <!-- Deoxyhemoglobin (Hb) Curve: High at 660, low at 940 -->
              <path d="M 30 45 Q 60 50, 100 90 T 180 120" fill="none" stroke="#dc2626" stroke-width="2"/>
              <text x="35" y="42" fill="#dc2626" font-size="7" font-family="monospace" font-weight="bold">Hb (Deoxygenated)</text>

              <!-- Oxyhemoglobin (HbO2) Curve: Low at 660, high at 940 -->
              <path d="M 30 115 Q 60 110, 100 85 T 180 50" fill="none" stroke="#0284c7" stroke-width="2"/>
              <text x="145" y="42" fill="#0369a1" font-size="7" font-family="monospace" font-weight="bold">HbO₂ (Oxygenated)</text>

              <!-- Isosbestic Point -->
              <circle cx="102" cy="87" r="3" fill="#15803d"/>
              <text x="102" y="100" fill="#15803d" font-size="6" font-family="monospace" text-anchor="middle">Isosbestic 805nm</text>

              <!-- Right: Overnight Sleep Apnea Hypoxia Dip (x: 215 to 410) -->
              <rect x="215" y="10" width="190" height="145" rx="5" fill="#ffffff" stroke="#cbd5e1"/>
              <text x="310" y="24" fill="#dc2626" font-size="8" font-family="monospace" text-anchor="middle" font-weight="bold">NOCTURNAL HYPOXEMIA DESATURATION</text>

              <!-- Baseline 98% Line -->
              <line x1="230" y1="45" x2="390" y2="45" stroke="#16a34a" stroke-width="1" stroke-dasharray="3,2"/>
              <text x="228" y="48" fill="#16a34a" font-size="6.5" font-family="monospace" text-anchor="end">98%</text>

              <!-- 90% Hypoxia Threshold -->
              <line x1="230" y1="90" x2="390" y2="90" stroke="#dc2626" stroke-width="1" stroke-dasharray="3,2"/>
              <text x="228" y="93" fill="#dc2626" font-size="6.5" font-family="monospace" text-anchor="end">90%</text>

              <!-- Apnea Desaturation Waveform -->
              <path d="M 235 45 L 270 45 C 280 45, 290 125, 305 125 C 315 125, 325 45, 340 45 L 390 45" fill="none" stroke="#dc2626" stroke-width="2.5"/>
              <circle cx="305" cy="125" r="3.5" fill="#dc2626"/>
              <text x="305" y="140" fill="#dc2626" font-size="7" font-family="monospace" text-anchor="middle" font-weight="bold">86% APNEA DESATURATION</text>

              <!-- Callout -->
              <text x="310" y="70" fill="#7f1d1d" font-size="6.5" font-family="monospace" text-anchor="middle">Airway Collapse Event</text>
            </svg>

            <!-- Clinical Impact Callout -->
            <div style="margin-top: 6px; background: #fef2f2; border-left: 4px solid #ef4444; padding: 5px 8px; border-radius: 4px; font-size: 0.67rem; color: #991b1b; line-height: 1.35; font-family: monospace;">
              <strong>DIABETES-APNEA CO-MORBIDITY:</strong> Nocturnal oxygen drops below 90% trigger catecholamine surges that spike morning fasting glucose and amplify insulin resistance. Continuous 940nm tracking detects silent apnea without sleep disruption.
            </div>
          </div>
"""

# -----------------------------------------------------------------------------
# SLIDE 18: Autonomic HRV & Sympatho-Vagal Stress (Poincaré Plot)
# -----------------------------------------------------------------------------
s18_diagram = """
          <!-- Autonomic HRV Poincaré Scatter & Sympatho-Vagal Balance -->
          <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 10px; margin-top: 4px; color: #0f172a;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 4px; margin-bottom: 6px;">
              <span style="font-family: monospace; font-size: 0.72rem; color: #0284c7; font-weight: 800;">AUTONOMIC HRV POINCARÉ SCATTER & VAGAL TONE</span>
              <span style="background: #4f46e5; color: #fff; font-size: 0.60rem; font-weight: bold; padding: 2px 6px; border-radius: 3px; font-family: monospace;">SD1 vs SD2 DYNAMICS</span>
            </div>

            <!-- SVG Diagram: R-R Tachogram & Poincaré Scatter Geometry -->
            <svg viewBox="0 0 420 170" style="width: 100%; height: auto; display: block; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0;">
              <!-- Left: R-R Interval Tachogram (x: 10 to 180) -->
              <rect x="15" y="10" width="175" height="145" rx="5" fill="#f8fafc" stroke="#cbd5e1"/>
              <text x="102" y="24" fill="#334155" font-size="8" font-family="monospace" text-anchor="middle" font-weight="bold">R-R INTERVAL VARIABILITY (IBI)</text>

              <!-- Heartbeat sequence showing millisecond variations -->
              <path d="M 25 90 L 40 70 L 55 95 L 70 65 L 85 88 L 100 68 L 115 92 L 130 72 L 145 85 L 160 65 L 175 88" fill="none" stroke="#4f46e5" stroke-width="1.8"/>
              <circle cx="40" cy="70" r="2.5" fill="#4f46e5"/>
              <circle cx="70" cy="65" r="2.5" fill="#4f46e5"/>
              <circle cx="100" cy="68" r="2.5" fill="#4f46e5"/>
              
              <text x="102" y="120" fill="#312e81" font-size="7" font-family="monospace" text-anchor="middle">RR₁=840ms • RR₂=790ms • RR₃=860ms</text>
              <text x="102" y="140" fill="#15803d" font-size="7.5" font-family="monospace" text-anchor="middle" font-weight="bold">HEALTHY HRV: SDNN = 64 MS</text>

              <!-- Right: Poincaré / Lorenz Scatter Plot (x: 205 to 410) -->
              <rect x="205" y="10" width="200" height="145" rx="5" fill="#ffffff" stroke="#cbd5e1"/>
              <text x="305" y="24" fill="#4f46e5" font-size="8" font-family="monospace" text-anchor="middle" font-weight="bold">POINCARÉ (LORENZ) SCATTER PLOT</text>

              <!-- Line of Identity (RR_n+1 = RR_n) -->
              <line x1="225" y1="135" x2="385" y2="35" stroke="#cbd5e1" stroke-width="1.5" stroke-dasharray="3,2"/>
              <text x="385" y="30" fill="#94a3b8" font-size="6" font-family="monospace">RR(n+1)=RR(n)</text>

              <!-- Healthy Comet Ellipse -->
              <ellipse cx="305" cy="85" rx="45" ry="22" transform="rotate(-45 305 85)" fill="#e0e7ff" opacity="0.6" stroke="#6366f1" stroke-width="1.5"/>

              <!-- Scatter Points -->
              <circle cx="300" cy="80" r="2" fill="#4338ca"/>
              <circle cx="310" cy="90" r="2" fill="#4338ca"/>
              <circle cx="285" cy="100" r="2" fill="#4338ca"/>
              <circle cx="325" cy="70" r="2" fill="#4338ca"/>
              <circle cx="295" cy="95" r="2" fill="#4338ca"/>
              <circle cx="320" cy="75" r="2" fill="#4338ca"/>
              <circle cx="340" cy="55" r="2" fill="#4338ca"/>
              <circle cx="270" cy="115" r="2" fill="#4338ca"/>

              <!-- SD1 & SD2 Vector Axes -->
              <line x1="290" y1="100" x2="320" y2="70" stroke="#16a34a" stroke-width="2"/>
              <text x="330" y="65" fill="#15803d" font-size="6.5" font-family="monospace" font-weight="bold">SD1 (Vagal Tone)</text>

              <line x1="275" y1="115" x2="335" y2="55" stroke="#dc2626" stroke-width="2"/>
              <text x="250" y="130" fill="#991b1b" font-size="6.5" font-family="monospace" font-weight="bold">SD2 (Sympathetic)</text>

              <text x="305" y="150" fill="#4338ca" font-size="6.5" font-family="monospace" text-anchor="middle" font-weight="bold">ELLIPSE RATIO (SD1/SD2) = AUTONOMIC BALANCE</text>
            </svg>

            <!-- Autonomic Health Significance -->
            <div style="margin-top: 6px; background: #eef2ff; border-left: 4px solid #4f46e5; padding: 5px 8px; border-radius: 4px; font-size: 0.67rem; color: #312e81; line-height: 1.35; font-family: monospace;">
              <strong>VAGAL BRAKE ON GLYCEMIA:</strong> Robust parasympathetic tone (wide SD1 ellipse) buffers against stress-induced hepatic glycogenolysis. Narrowing to a torpedo cluster warns of sympathetic overdrive, cortisol surges, and diabetic neuropathy.
            </div>
          </div>
"""

# Assign diagrams and refined voice narrations
diagram_map = {
    "14": (s14_diagram, (
        "In Module 3, we tour the core biosensing channels. In Tab 01 Vitals, our primary focus is non-invasive optical glucose tonometry. "
        "Displayed on the right is the physical sensor architecture and optical ray-tracing diagram. "
        "The FITAOS 5150 underside chassis emits multi-wavelength optical beams—including 525 nanometer green and 940 nanometer near-infrared—"
        "penetrating the epidermal and dermal interstitial fluid layers down to pulsatile capillary loops. "
        "By applying the Beer-Lambert transmittance equation shown below, the photodiode captures differential photon attenuation "
        "caused by circulating glucose molecules in real time. "
        "LCARS then applies the standard ADAG consensus algorithm across continuous daily epochs, eliminating quarterly snapshot error "
        "and providing actionable metabolic insight without painful fingersticks."
    )),
    "15": (s15_diagram, (
        "Still in Tab 01 Vitals, our second sensor channel monitors the dual-matrix body temperature array. "
        "Illustrated on the right is our dual-thermistor heat-flux vector architecture alongside the 24-hour circadian thermal cycle. "
        "Rather than taking an uncalibrated surface snapshot, the sensor array couples an epidermal contact plate measuring wrist skin temperature "
        "with an internal insulated reference thermistor. "
        "By computing the heat-flux vector between them, the core algorithm accurately tracks deep arterial core temperature at 96.1 degrees. "
        "On the 24-hour curve, observe the physiological 4:30 AM circadian nadir reaching 96 degrees flat. "
        "This nocturnal temperature drop is a vital clinical biomarker of parasympathetic rest and restorative metabolic tone."
    )),
    "16": (s16_diagram, (
        "Moving to Tab 02 Cardio, the first cardiovascular channel tracks blood pressure using Pulse Wave Transit Time, or PWTT. "
        "The diagram on your screen demonstrates the exact electro-optical timing synchronization. "
        "Signal 1 captures central cardiac ventricular ejection at the ECG R-peak, defining time zero. "
        "Signal 2 captures the arterial pulse wave arrival at the wrist optical PPG sensor. "
        "The measured transit delay—here 215 milliseconds—is governed by the Moens-Korteweg physical law, "
        "where pulse velocity directly reflects arterial stiffness and hemodynamic pressure. "
        "This enables continuous, cuffless ambulatory monitoring to verify healthy 10 to 20 percent nocturnal blood pressure dipping "
        "without waking the patient with an inflating arm cuff."
    )),
    "17": (s17_diagram, (
        "Continuing in Tab 02 Cardio, the next channel provides dual-wavelength spectrophotometric oxygen monitoring. "
        "The diagram on your right details the absorption spectra of oxygenated versus deoxygenated hemoglobin. "
        "At 660 nanometers, red light is heavily absorbed by deoxygenated hemoglobin, whereas 940 nanometer infrared light "
        "is predominantly absorbed by oxygenated hemoglobin. "
        "The sensor computes the ratio of these pulsatile AC to DC signals to derive continuous arterial saturation. "
        "On the right half of the diagram, observe the nocturnal sleep apnea desaturation curve dipping to 86 percent. "
        "Because obstructive sleep apnea triggers catecholamine surges that exacerbate insulin resistance and spike morning blood sugar, "
        "this nocturnal safeguard is vital for diabetic care."
    )),
    "18": (s18_diagram, (
        "In our third cardiovascular channel, LCARS assesses autonomic nervous system recovery via Heart Rate Variability. "
        "On screen, we illustrate the beat-to-beat R-R interval tachogram and the resulting Poincaré Lorenz scatter plot. "
        "Each heartbeat interval is mapped against the subsequent beat, forming an elliptical dispersion pattern. "
        "The minor axis, SD1, directly measures parasympathetic vagal tone and short-term recovery, "
        "while the major axis, SD2, reflects sympathetic autonomic drive. "
        "A healthy autonomic nervous system produces a wide, resilient comet-shaped scatter. "
        "Conversely, chronic diabetic distress or autonomic neuropathy collapses the ellipse into a narrow torpedo cluster, "
        "warning clinicians of sympathetic overdrive and stress-induced glucose spikes."
    ))
}

for s in slides:
    num = s['num']
    if num in diagram_map:
        d_html, narr = diagram_map[num]
        s['right_html'] = d_html
        s['narration'] = narr
        print(f"Slide {num}: Successfully embedded rich vector sensor diagram and synchronized voice narration!")

with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2, ensure_ascii=False)

print("all_slides_data.json updated with sensor diagrams for Slides 14-18!")
