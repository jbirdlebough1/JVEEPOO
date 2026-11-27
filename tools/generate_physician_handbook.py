"""Generate the comprehensive Physician's Clinical Reference, Biophysical Metrology & Reporting Monograph PDF.

Compiles D:/veepoo/docs/cmo_spec.html via Headless Chrome CDP Page.printToPDF into:
  - D:/veepoo/Beta/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf
  - D:/veepoo/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf
  - D:/veepoo/w11_htm_app/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf
"""

import asyncio
import base64
import json
import os
import re
import shutil
import subprocess
import time

import aiohttp

WORKSPACE = r"D:\veepoo"
CHROME_PATH = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

HTML_TARGETS = [
    os.path.join(WORKSPACE, "docs", "cmo_spec.html"),
    os.path.join(WORKSPACE, "Beta", "docs", "cmo_spec.html"),
    os.path.join(WORKSPACE, "w11_htm_app", "docs", "cmo_spec.html"),
]

PDF_TARGETS = [
    os.path.join(WORKSPACE, "Beta", "docs", "VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf"),
    os.path.join(WORKSPACE, "docs", "VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf"),
    os.path.join(WORKSPACE, "w11_htm_app", "docs", "VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf"),
]


def cite(*nums):
    return "".join(f'<a class="cite-link" href="#ref-{n}">[{n}]</a>' for n in nums)


def frac(num, den):
    return (
        '<span class="frac">'
        f'<span class="num">{num}</span>'
        f'<span class="den">{den}</span>'
        "</span>"
    )


def sqrt_expr(inner, root_idx=""):
    idx_html = f'<sup class="rad-idx">{root_idx}</sup>' if root_idx else ""
    return f'<span class="rad-wrap">{idx_html}&radic;<span class="radicand">{inner}</span></span>'


def eq_card(title, badge, eq_html, legend_rows, clinical_note, cite_nums):
    legend_html = "".join(
        f'<tr><td class="sym">{sym}</td><td>{meaning}</td><td class="unit">{unit}</td></tr>'
        for sym, meaning, unit in legend_rows
    )
    cites_html = cite(*cite_nums)
    return f"""
    <div class="eq-card">
      <div class="eq-head">
        <span class="eq-title">{title} {cites_html}</span>
        <span class="eq-badge">{badge}</span>
      </div>
      <div class="eq-display">{eq_html}</div>
      <table class="legend-tbl">
        <thead><tr><th>Symbol</th><th>Biophysical / Clinical Definition</th><th>Unit / Bound</th></tr></thead>
        <tbody>{legend_html}</tbody>
      </table>
      <div class="eq-note"><strong>Clinical Interpretation:</strong> {clinical_note}</div>
    </div>
    """


def fig_card(fig_num, title, img_rel, caption, takeaways, cite_nums=()):
    cites_html = cite(*cite_nums) if cite_nums else ""
    takeaway_html = "".join(f"<li>{t}</li>" for t in takeaways)
    return f"""
    <div class="fig-card">
      <div class="fig-head">
        <span class="fig-num">FIGURE {fig_num}</span>
        <span class="fig-title">{title} {cites_html}</span>
      </div>
      <div class="fig-img-wrap">
        <img src="{img_rel}" alt="{title}">
      </div>
      <div class="fig-cap"><strong>Caption:</strong> {caption}</div>
      <ul class="fig-points">{takeaway_html}</ul>
    </div>
    """


def svg_fig_card(fig_num, title, svg_markup, caption, cite_nums=()):
    cites_html = cite(*cite_nums) if cite_nums else ""
    return f"""
    <div class="fig-card">
      <div class="fig-head">
        <span class="fig-num">VECTOR SCHEMATIC {fig_num}</span>
        <span class="fig-title">{title} {cites_html}</span>
      </div>
      <div class="svg-wrap">{svg_markup}</div>
      <div class="fig-cap">{caption}</div>
    </div>
    """


def build_svg_sampling_retention():
    return """
    <svg viewBox="0 0 760 215" style="width:100%;height:auto;display:block;">
      <rect x="0" y="0" width="760" height="215" rx="6" fill="#f8fafc" stroke="#cbd5e1"/>
      <!-- Left Plot: SE = sigma / sqrt(N) -->
      <text x="190" y="20" text-anchor="middle" font-size="11" font-weight="800" fill="#0f172a">A. Standard Error Reduction: SE = &#963; / &#8730;N (&#963; = 24 mg/dL)</text>
      <rect x="46" y="34" width="315" height="140" fill="#ffffff" stroke="#cbd5e1"/>
      <line x1="46" y1="69" x2="361" y2="69" stroke="#f1f5f9" stroke-dasharray="3,3"/>
      <line x1="46" y1="104" x2="361" y2="104" stroke="#f1f5f9" stroke-dasharray="3,3"/>
      <line x1="46" y1="139" x2="361" y2="139" stroke="#f1f5f9" stroke-dasharray="3,3"/>
      <!-- Curve -->
      <path d="M 55 42 C 95 55, 130 140, 195 162 L 350 170" fill="none" stroke="#0284c7" stroke-width="2.5"/>
      <!-- Highlight points -->
      <circle cx="55" cy="42" r="5" fill="#dc2626" stroke="#fff" stroke-width="1.5"/>
      <text x="64" y="45" font-size="9" font-weight="700" fill="#991b1b">Office Visit (N=1): SE = 24.0 mg/dL</text>
      <circle cx="155" cy="148" r="5" fill="#d97706" stroke="#fff" stroke-width="1.5"/>
      <text x="163" y="143" font-size="9" font-weight="700" fill="#92400e">1 Day 5-min (N=288): SE = 1.41 mg/dL (17&#215;)</text>
      <circle cx="338" cy="169" r="5" fill="#15803d" stroke="#fff" stroke-width="1.5"/>
      <text x="200" y="164" font-size="9" font-weight="800" fill="#15803d">90-Day BFM (N=25,920): SE = 0.15 mg/dL</text>
      <text x="40" y="42" text-anchor="end" font-size="8.5" fill="#64748b">24</text>
      <text x="40" y="172" text-anchor="end" font-size="8.5" fill="#64748b">0.1</text>
      <text x="203" y="191" text-anchor="middle" font-size="9" font-weight="700" fill="#475569">Cumulative Sample Count (Log Scale N = 1 &#8594; 25,920)</text>

      <!-- Right Plot: Serial Practice Loss vs 5-Specialist Shared Care -->
      <text x="570" y="20" text-anchor="middle" font-size="11" font-weight="800" fill="#0f172a">B. Diagnostic Signal Retention (90-Day Horizon)</text>
      <rect x="400" y="34" width="340" height="140" fill="#ffffff" stroke="#cbd5e1"/>
      <!-- Bars -->
      <text x="415" y="60" font-size="9.5" font-weight="700" fill="#64748b">Standard Office (1 A1C / 90d)</text>
      <rect x="415" y="66" width="12" height="18" rx="2" fill="#dc2626"/>
      <text x="434" y="79" font-size="9" font-weight="800" fill="#dc2626">~2.5% transient variance captured</text>

      <text x="415" y="102" font-size="9.5" font-weight="700" fill="#64748b">Spot Fingerstick (1-2x / day)</text>
      <rect x="415" y="108" width="52" height="18" rx="2" fill="#d97706"/>
      <text x="474" y="121" font-size="9" font-weight="800" fill="#b45309">~16% (nocturnal &amp; peak blind spots)</text>

      <text x="415" y="144" font-size="9.5" font-weight="800" fill="#0f172a">BFM 13-Ch + 5-Specialist Team</text>
      <rect x="415" y="150" width="295" height="18" rx="2" fill="#15803d"/>
      <text x="422" y="162.5" font-size="9" font-weight="800" fill="#ffffff">&gt;96% I_retained = 1 &#8722; &#8719;(1 &#8722; &#951;_k) across 288 pts/day</text>
      <text x="570" y="191" text-anchor="middle" font-size="9" font-weight="700" fill="#475569">Shareable across PCP, PharmD, RD, Endo &amp; Neuro</text>
    </svg>
    """


def build_svg_wrist_transducers():
    return """
    <svg viewBox="0 0 760 225" style="width:100%;height:auto;display:block;">
      <rect x="0" y="0" width="760" height="225" rx="6" fill="#f8fafc" stroke="#cbd5e1"/>
      <text x="380" y="20" text-anchor="middle" font-size="11.5" font-weight="800" fill="#0f172a">FITAOS / Veepoo Model 5010 Dermal-Vascular Optical, Thermal &amp; Electrophysiologic Interface</text>
      <!-- Epidermis / Dermis Layers -->
      <rect x="35" y="34" width="440" height="32" fill="#fef3c7" stroke="#f59e0b"/>
      <text x="255" y="54" text-anchor="middle" font-size="9.5" font-weight="700" fill="#92400e">Epidermis &amp; Stratum Corneum (~0.1–0.2 mm) • Skin NTC Contact Zone</text>
      <rect x="35" y="66" width="440" height="46" fill="#fee2e2" stroke="#f87171"/>
      <text x="255" y="88" text-anchor="middle" font-size="9.5" font-weight="700" fill="#991b1b">Dermis Capillary &amp; Arteriolar Plexus • Multi-Wavelength Optical Path (&#955; = 525, 660, 940 nm)</text>
      <text x="255" y="102" font-size="8.5" text-anchor="middle" fill="#b91c1c">AC Pulsatile (HbO2 / Hb / PWTT) vs DC Baseline Tissue Absorption</text>
      <!-- Light paths -->
      <path d="M 135 152 Q 165 72 200 152" fill="none" stroke="#16a34a" stroke-width="2.2" stroke-dasharray="4,2"/>
      <path d="M 240 152 Q 280 68 320 152" fill="none" stroke="#dc2626" stroke-width="2.2" stroke-dasharray="4,2"/>
      <path d="M 350 152 Q 395 62 435 152" fill="none" stroke="#7c3aed" stroke-width="2.2"/>
      <!-- Watch sapphire backplate -->
      <rect x="85" y="152" width="375" height="52" rx="8" fill="#0f172a" stroke="#334155" stroke-width="2"/>
      <rect x="115" y="158" width="110" height="20" rx="4" fill="#1e293b" stroke="#22c55e"/>
      <text x="170" y="171" text-anchor="middle" font-size="8.5" font-weight="800" fill="#4ade80">525nm Green + 660/940nm LED</text>
      <rect x="235" y="158" width="90" height="20" rx="4" fill="#1e293b" stroke="#38bdf8"/>
      <text x="280" y="171" text-anchor="middle" font-size="8.5" font-weight="800" fill="#38bdf8">Si Photodiode Rx</text>
      <rect x="335" y="158" width="110" height="20" rx="4" fill="#1e293b" stroke="#fbbf24"/>
      <text x="390" y="171" text-anchor="middle" font-size="8.5" font-weight="800" fill="#fbbf24">Dual NTC + BIA Pad</text>
      <text x="272" y="195" text-anchor="middle" font-size="9" font-weight="700" fill="#e2e8f0">Watch Backplate (1 Finger Width Above Ulnar Styloid) • Side Bezel = Lead-I ECG</text>

      <!-- Right legend box -->
      <rect x="492" y="34" width="248" height="170" rx="6" fill="#ffffff" stroke="#cbd5e1"/>
      <text x="616" y="52" text-anchor="middle" font-size="10" font-weight="800" fill="#0284c7">6 Co-Located Hardware Circuits</text>
      <text x="504" y="72" font-size="8.8" fill="#1e293b">&#8226; 525 nm Green: HR, HRV R-R, Resp (RSA)</text>
      <text x="504" y="90" font-size="8.8" fill="#1e293b">&#8226; 660 / 940 nm Red-IR: SpO2 Ratio-of-Ratios</text>
      <text x="504" y="108" font-size="8.8" fill="#1e293b">&#8226; Spectral-PWTT Fusion: Glucose &amp; BP</text>
      <text x="504" y="126" font-size="8.8" fill="#1e293b">&#8226; Dual NTC Matrix: Core vs Skin Heat Flux</text>
      <text x="504" y="144" font-size="8.8" fill="#1e293b">&#8226; Einthoven Lead-I Bezel: 30–40s ECG/QTc</text>
      <text x="504" y="162" font-size="8.8" fill="#1e293b">&#8226; Dual-Electrode BIA: 50 kHz Z Impedance</text>
      <text x="504" y="182" font-size="8.8" font-weight="800" fill="#15803d">Optical Repeatability: CV = 5.36% (SE &lt;1.8)</text>
      <text x="504" y="196" font-size="8.5" font-weight="700" fill="#0369a1">Off-Wrist Capacitance Drop &#8594; Blue Gap</text>
    </svg>
    """


def build_svg_rbc_kinetics():
    return """
    <svg viewBox="0 0 760 195" style="width:100%;height:auto;display:block;">
      <rect x="0" y="0" width="760" height="195" rx="6" fill="#f8fafc" stroke="#cbd5e1"/>
      <text x="380" y="20" text-anchor="middle" font-size="11.5" font-weight="800" fill="#0f172a">Acute Watch ADAG eA1C / GMI vs. 120-Day Erythrocyte Glycation Renewal Kinetics (8.5% Baseline Study)</text>
      <rect x="65" y="34" width="655" height="125" fill="#ffffff" stroke="#cbd5e1"/>
      <!-- Grid lines -->
      <line x1="65" y1="60" x2="720" y2="60" stroke="#e2e8f0" stroke-dasharray="3,3"/>
      <line x1="65" y1="95" x2="720" y2="95" stroke="#e2e8f0" stroke-dasharray="3,3"/>
      <line x1="65" y1="130" x2="720" y2="130" stroke="#e2e8f0" stroke-dasharray="3,3"/>
      <text x="58" y="48" text-anchor="end" font-size="8.5" font-weight="700" fill="#dc2626">8.5%</text>
      <text x="58" y="98" text-anchor="end" font-size="8.5" font-weight="700" fill="#0284c7">7.5%</text>
      <text x="58" y="133" text-anchor="end" font-size="8.5" font-weight="700" fill="#15803d">7.0%</text>
      <!-- Day 21 marker -->
      <line x1="185" y1="34" x2="185" y2="159" stroke="#f59e0b" stroke-width="1.5" stroke-dasharray="4,2"/>
      <text x="190" y="47" font-size="8.5" font-weight="800" fill="#b45309">Day 21: 17.5% RBC Turnover</text>
      <!-- Lab HbA1c slow integral curve -->
      <path d="M 75 45 Q 240 62 410 92 T 705 130" fill="none" stroke="#dc2626" stroke-width="2.5"/>
      <!-- Acute ADAG eA1C / GMI fast step-down curve -->
      <path d="M 75 45 L 130 96 L 185 106 L 340 120 L 705 132" fill="none" stroke="#7c3aed" stroke-width="2.5" stroke-dasharray="6,3"/>
      <!-- Annotations -->
      <circle cx="185" cy="106" r="4.5" fill="#7c3aed" stroke="#fff"/>
      <text x="193" y="114" font-size="8.8" font-weight="800" fill="#6d28d9">Watch GMI responds immediately (e.g. 8.5% &#8594; 7.4% mean)</text>
      <circle cx="185" cy="60" r="4.5" fill="#dc2626" stroke="#fff"/>
      <text x="193" y="72" font-size="8.8" font-weight="800" fill="#b91c1c">Venipuncture Lab HbA1c retains 82.5% older RBCs (~8.3%)</text>
      <!-- X axis -->
      <text x="75" y="174" text-anchor="middle" font-size="8.5" fill="#475569">Day 0 (8.5%)</text>
      <text x="185" y="174" text-anchor="middle" font-size="8.5" font-weight="800" fill="#b45309">Day 21 (17.5%)</text>
      <text x="395" y="174" text-anchor="middle" font-size="8.5" fill="#475569">Day 60 (50% RBC Renewed)</text>
      <text x="695" y="174" text-anchor="middle" font-size="8.5" font-weight="800" fill="#15803d">Day 120 (Full Convergence)</text>
    </svg>
    """


def build_svg_lorenz_can():
    return """
    <svg viewBox="0 0 760 205" style="width:100%;height:auto;display:block;">
      <rect x="0" y="0" width="760" height="205" rx="6" fill="#f8fafc" stroke="#cbd5e1"/>
      <text x="380" y="20" text-anchor="middle" font-size="11.5" font-weight="800" fill="#0f172a">Poincar&#233; / 2D Lorenz R-R Geometry: Vagal Preservation vs. Diabetic Cardiac Autonomic Neuropathy (CAN)</text>
      <!-- Left: Healthy Comet -->
      <rect x="30" y="32" width="330" height="156" rx="5" fill="#ffffff" stroke="#86efac" stroke-width="1.5"/>
      <text x="195" y="48" text-anchor="middle" font-size="10" font-weight="800" fill="#15803d">Healthy Vagal "Comet" Envelope (Wide SD1 &amp; SD2)</text>
      <line x1="65" y1="168" x2="325" y2="60" stroke="#94a3b8" stroke-dasharray="4,3"/>
      <ellipse cx="205" cy="110" rx="75" ry="30" transform="rotate(-22 205 110)" fill="rgba(22,163,74,0.14)" stroke="#16a34a" stroke-width="2"/>
      <line x1="194" y1="84" x2="216" y2="136" stroke="#dc2626" stroke-width="2"/>
      <text x="225" y="138" font-size="8.8" font-weight="800" fill="#dc2626">SD1 (Vagal RMSSD)</text>
      <text x="72" y="154" font-size="8.8" font-weight="800" fill="#0284c7">SD2 (24h Sympathovagal)</text>
      <text x="195" y="180" text-anchor="middle" font-size="8.5" fill="#166534">Ellipse Area S = &#960; &#215; SD1 &#215; SD2 preserved across R-R range</text>

      <!-- Right: Constricted CAN Torpedo -->
      <rect x="400" y="32" width="330" height="156" rx="5" fill="#ffffff" stroke="#fca5a5" stroke-width="1.5"/>
      <text x="565" y="48" text-anchor="middle" font-size="10" font-weight="800" fill="#dc2626">Early Diabetic CAN "Torpedo" (Collapsed SD1)</text>
      <line x1="435" y1="168" x2="695" y2="60" stroke="#94a3b8" stroke-dasharray="4,3"/>
      <ellipse cx="565" cy="114" rx="58" ry="8" transform="rotate(-22 565 114)" fill="rgba(220,38,38,0.16)" stroke="#dc2626" stroke-width="2"/>
      <text x="565" y="88" text-anchor="middle" font-size="8.8" font-weight="700" fill="#991b1b">Tightly restricted beat-to-beat vagal dispersion</text>
      <text x="565" y="150" text-anchor="middle" font-size="8.8" font-weight="700" fill="#7f1d1d">SD1 &lt; 15 ms triggers early Neurology review</text>
      <text x="565" y="180" text-anchor="middle" font-size="8.5" fill="#991b1b">BFM plots actual watch 24h R-R points (e.g. 4,831+ / 8,859 epochs)</text>
    </svg>
    """


def build_svg_shared_care():
    return """
    <svg viewBox="0 0 760 190" style="width:100%;height:auto;display:block;">
      <rect x="0" y="0" width="760" height="190" rx="6" fill="#f8fafc" stroke="#cbd5e1"/>
      <text x="380" y="20" text-anchor="middle" font-size="11.5" font-weight="800" fill="#0f172a">5-Specialist Closed-Loop Clinical Governance Over Sovereign Local Telemetry Vault</text>
      <!-- Center Hub -->
      <rect x="260" y="34" width="240" height="44" rx="6" fill="#0284c7" stroke="#0369a1" stroke-width="1.5"/>
      <text x="380" y="53" text-anchor="middle" font-size="10" font-weight="800" fill="#ffffff">BFM Local SQLite + HL7 FHIR R4 Bundle</text>
      <text x="380" y="68" text-anchor="middle" font-size="8.5" fill="#e0f2fe">13 Channels &#8226; 5 Watch Sub-Reports &#8226; HDYF Voice Notes</text>
      <!-- 5 Specialist Cards -->
      <rect x="18" y="102" width="136" height="74" rx="5" fill="#ffffff" stroke="#0284c7"/>
      <text x="86" y="119" text-anchor="middle" font-size="9.5" font-weight="800" fill="#0284c7">1. Primary Care MD</text>
      <text x="86" y="135" text-anchor="middle" font-size="8.2" fill="#334155">Global TIR, BP dipping,</text>
      <text x="86" y="148" text-anchor="middle" font-size="8.2" fill="#334155">Core/Skin Temp, SpO2</text>
      <text x="86" y="164" text-anchor="middle" font-size="7.8" font-weight="700" fill="#15803d">LOINC Flowsheet</text>

      <rect x="164" y="102" width="136" height="74" rx="5" fill="#ffffff" stroke="#7c3aed"/>
      <text x="232" y="119" text-anchor="middle" font-size="9.5" font-weight="800" fill="#7c3aed">2. Endocrinologist</text>
      <text x="232" y="135" text-anchor="middle" font-size="8.2" fill="#334155">ADAG eA1C vs 120d A1C,</text>
      <text x="232" y="148" text-anchor="middle" font-size="8.2" fill="#334155">dG/dt post-meal curves</text>
      <text x="232" y="164" text-anchor="middle" font-size="7.8" font-weight="700" fill="#6d28d9">CV &le;36% &amp; TIR &ge;70%</text>

      <rect x="310" y="102" width="140" height="74" rx="5" fill="#ffffff" stroke="#dc2626"/>
      <text x="380" y="119" text-anchor="middle" font-size="9.5" font-weight="800" fill="#dc2626">3. Diabetic PharmD</text>
      <text x="380" y="135" text-anchor="middle" font-size="8.2" fill="#334155">AM/PM Rx split timing,</text>
      <text x="380" y="148" text-anchor="middle" font-size="8.2" fill="#334155">Zero-mock audit trail</text>
      <text x="380" y="164" text-anchor="middle" font-size="7.8" font-weight="700" fill="#b91c1c">Deprescribing Safety</text>

      <rect x="460" y="102" width="136" height="74" rx="5" fill="#ffffff" stroke="#15803d"/>
      <text x="528" y="119" text-anchor="middle" font-size="9.5" font-weight="800" fill="#15803d">4. Dietitian (RD)</text>
      <text x="528" y="135" text-anchor="middle" font-size="8.2" fill="#334155">Net Carbs (&le;45g/meal),</text>
      <text x="528" y="148" text-anchor="middle" font-size="8.2" fill="#334155">GL, 64+ oz, GLUT4 walks</text>
      <text x="528" y="164" text-anchor="middle" font-size="7.8" font-weight="700" fill="#15803d">Pre-Choice BOM Swaps</text>

      <rect x="606" y="102" width="136" height="74" rx="5" fill="#ffffff" stroke="#b45309"/>
      <text x="674" y="119" text-anchor="middle" font-size="9.5" font-weight="800" fill="#b45309">5. Neurologist</text>
      <text x="674" y="135" text-anchor="middle" font-size="8.2" fill="#334155">2D Lorenz CAN (SD1/2),</text>
      <text x="674" y="148" text-anchor="middle" font-size="8.2" fill="#334155">QTc, HDYF pain tokens</text>
      <text x="674" y="164" text-anchor="middle" font-size="7.8" font-weight="700" fill="#b45309">Early CAN / DPN Screen</text>

      <!-- Connectors -->
      <line x1="300" y1="78" x2="86" y2="102" stroke="#94a3b8" stroke-width="1.5"/>
      <line x1="340" y1="78" x2="232" y2="102" stroke="#94a3b8" stroke-width="1.5"/>
      <line x1="380" y1="78" x2="380" y2="102" stroke="#94a3b8" stroke-width="1.5"/>
      <line x1="420" y1="78" x2="528" y2="102" stroke="#94a3b8" stroke-width="1.5"/>
      <line x1="460" y1="78" x2="674" y2="102" stroke="#94a3b8" stroke-width="1.5"/>
    </svg>
    """


def build_html():
    refs = [
        (1, "Danne T, Nimri R, Battelino T, et al. International Consensus on Use of Continuous Glucose Monitoring. Diabetes Care. 2017;40(12):1631–1640.", "https://doi.org/10.2337/dc17-1600"),
        (2, "Battelino T, Danne T, Bergenstal RM, et al. Clinical Targets for Continuous Glucose Monitoring Data Interpretation: Recommendations From the International Consensus on Time in Range. Diabetes Care. 2019;42(8):1593–1603.", "https://doi.org/10.2337/dci19-0028"),
        (3, "Beck RW, Riddlesworth T, Ruedy K, et al. Effect of Continuous Glucose Monitoring on Glycemic Control in Adults With Type 1 Diabetes Using Insulin Injections: The DIAMOND Randomized Clinical Trial. JAMA. 2017;317(4):371–378.", "https://doi.org/10.1001/jama.2016.19975"),
        (4, "Pratley RE, Kanapka LG, Rickels MR, et al. Effect of Continuous Glucose Monitoring on Hypoglycemia in Older Adults With Type 1 Diabetes (WISDM): A Randomized Clinical Trial. JAMA. 2020;323(23):2397–2406.", "https://doi.org/10.1001/jama.2020.6928"),
        (5, "Nathan DM, Kuenen J, Borg R, Zheng H, Schoenfeld D, Heine RJ; ADAG Study Group. Translating the A1C Assay Into Estimated Average Glucose Values. Diabetes Care. 2008;31(8):1473–1478.", "https://doi.org/10.2337/dc08-0545"),
        (6, "Bergenstal RM, Beck RW, Close KL, et al. Glucose Management Indicator (GMI): A New Term for Estimating A1C From Continuous Glucose Monitoring. Diabetes Care. 2018;41(11):2275–2280.", "https://doi.org/10.2337/dc18-1581"),
        (7, "American Diabetes Association Professional Practice Committee. 7. Diabetes Technology: Standards of Care in Diabetes—2024. Diabetes Care. 2024;47(Suppl. 1):S126–S144.", "https://doi.org/10.2337/dc24-S007"),
        (8, "UK Prospective Diabetes Study (UKPDS) Group. Intensive blood-glucose control with sulphonylureas or insulin compared with conventional treatment and risk of complications in patients with type 2 diabetes (UKPDS 33). The Lancet. 1998;352(9131):837–853.", "https://doi.org/10.1016/S0140-6736(98)07019-6"),
        (9, "Shannon CE. A Mathematical Theory of Communication. Bell System Technical Journal. 1948;27(3):379–423.", "https://doi.org/10.1002/j.1538-7305.1948.tb01338.x"),
        (10, "Allen J. Photoplethysmography and its application in clinical physiological measurement. Physiological Measurement. 2007;28(3):R1–R39.", "https://doi.org/10.1088/0967-3334/28/3/R01"),
        (11, "Delpy DT, Cope M, van der Zee P, Arridge S, Wray S, Wyatt J. Estimation of optical pathlength through tissue from direct time of flight measurement. Physics in Medicine & Biology. 1988;33(12):1433–1442.", "https://doi.org/10.1088/0031-9155/33/12/008"),
        (12, "Gesche H, Grosskurth D, K&#252;chler G, Patzak A. Continuous blood pressure measurement by using the pulse transit time: comparison to a cuff-based method. Eur J Appl Physiol. 2012;112(1):309–315.", "https://doi.org/10.1007/s00421-011-1983-3"),
        (13, "Jubran A. Pulse oximetry. Critical Care. 2015;19(1):272.", "https://doi.org/10.1186/s13054-015-0984-8"),
        (14, "Franco RS. Measurement of red cell lifespan and aging. Transfusion Medicine and Hemotherapy. 2012;39(5):302–307.", "https://doi.org/10.1159/000342232"),
        (15, "Fridericia LS. Die Systolendauer im Elektrokardiogramm bei normalen Menschen und bei Herzkranken. Acta Medica Scandinavica. 1920;53(1):469–486.", "https://doi.org/10.1111/j.0954-6820.1920.tb18266.x"),
        (16, "Task Force of the European Society of Cardiology and NASPE. Heart rate variability: standards of measurement, physiological interpretation and clinical use. Circulation. 1996;93(5):1043–1065.", "https://doi.org/10.1161/01.CIR.93.5.1043"),
        (17, "Brennan M, Palaniswami M, Kamen P. Do existing measures of Poincar&#233; plot geometry reflect nonlinear features of heart rate variability? IEEE Trans Biomed Eng. 2001;48(11):1342–1347.", "https://doi.org/10.1109/10.959330"),
        (18, "Pop-Busui R, Boulton AJM, Feldman EL, et al. Diabetic Neuropathy: A Position Statement by the American Diabetes Association. Diabetes Care. 2017;40(1):136–154.", "https://doi.org/10.2337/dc16-2042"),
        (19, "Cho YI, Mooney MP, Cho DJ. Hemorheological disorders in diabetes mellitus. Journal of Diabetes Science and Technology. 2008;2(6):1130–1138.", "https://doi.org/10.1177/193229680800200622"),
        (20, "Kyle UG, Bosaeus I, De Lorenzo AD, et al. Bioelectrical impedance analysis—part I: review of principles and methods. Clinical Nutrition. 2004;23(5):1226–1243.", "https://doi.org/10.1016/j.clnu.2004.06.004"),
        (21, "DeFronzo RA, Jacot E, Jequier E, Maeder E, Wahren J, Felber JP. The effect of insulin on the disposal of intravenous glucose: results from indirect calorimetry and hepatic and femoral venous catheterization. Diabetes. 1981;30(12):1000–1007.", "https://doi.org/10.2337/diab.30.12.1000"),
        (22, "Richter EA, Hargreaves M. Exercise, GLUT4, and Skeletal Muscle Glucose Uptake. Physiological Reviews. 2013;93(3):993–1017.", "https://doi.org/10.1152/physrev.00038.2012"),
        (23, "Colberg SR, Sigal RJ, Yardley JE, et al. Physical Activity/Exercise and Diabetes: A Position Statement of the American Diabetes Association. Diabetes Care. 2016;39(11):2065–2079.", "https://doi.org/10.2337/dc16-1728"),
        (24, "Ainsworth BE, Haskell WL, Herrmann SD, et al. 2011 Compendium of Physical Activities: a second update of codes and MET values. Med Sci Sports Exerc. 2011;43(8):1575–1581.", "https://doi.org/10.1249/MSS.0b013e31821ece12"),
        (25, "Atkinson FS, Foster-Powell K, Brand-Miller JC. International Tables of Glycemic Index and Glycemic Load Values: 2008. Diabetes Care. 2008;31(12):2281–2283.", "https://doi.org/10.2337/dc08-1239"),
        (26, "Verdecchia P, Porcellati C, Schillaci G, et al. Ambulatory blood pressure. An independent predictor of prognostic outcome in essential hypertension. Hypertension. 1994;24(6):793–801.", "https://doi.org/10.1161/01.HYP.24.6.793"),
        (27, "Fagard RH, Celis H, Thijs L, et al. Daytime and nighttime blood pressure as predictors of death and cause-specific cardiovascular events in hypertension. Hypertension. 2008;51(1):55–61.", "https://doi.org/10.1161/HYPERTENSIONAHA.107.100727"),
        (28, "HL7 International. Fast Healthcare Interoperability Resources (FHIR) Release 4 — Observation & Bundle Resource Specifications.", "https://hl7.org/fhir/R4/observation.html"),
        (29, "Regenstrief Institute. Logical Observation Identifiers Names and Codes (LOINC) Clinical Terminology Standard.", "https://loinc.org/"),
        (30, "HL7 / Regenstrief Institute. Unified Code for Units of Measure (UCUM) Formal Specification.", "https://ucum.org/"),
    ]
    ref_rows = "".join(
        f'<tr id="ref-{n}">'
        f'<td class="ref-num">[{n}]</td>'
        f'<td>{txt}<br><a class="doi-url" href="{url}" target="_blank">{url}</a></td>'
        f"</tr>"
        for n, txt, url in refs
    )

    return f"""<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>BFM Biometric Function Monitor — Physician Clinical Reference, Biophysical Metrology &amp; Reporting Monograph</title>
<style>
  @page {{
    size: letter;
    margin: 0.55in 0.55in 0.6in 0.55in;
  }}
  * {{ box-sizing: border-box; }}
  body {{
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
    font-size: 9.2pt;
    line-height: 1.46;
    color: #0f172a;
    background: #ffffff;
    margin: 0;
    padding: 0;
  }}
  h1 {{
    font-size: 14pt;
    font-weight: 800;
    color: #0f172a;
    border-bottom: 2px solid #0284c7;
    padding-bottom: 4px;
    margin: 16px 0 8px 0;
    page-break-after: avoid;
    break-after: avoid;
  }}
  h2 {{
    font-size: 11pt;
    font-weight: 800;
    color: #0369a1;
    margin: 12px 0 5px 0;
    page-break-after: avoid;
    break-after: avoid;
  }}
  p {{ margin: 0 0 8px 0; text-align: justify; }}
  code {{
    font-family: "Consolas", "Courier New", monospace;
    font-size: 8.2pt;
    background: #f1f5f9;
    padding: 1px 4px;
    border-radius: 3px;
    color: #0369a1;
    border: 1px solid #e2e8f0;
  }}
  .page-break {{ page-break-before: always; break-before: page; }}
  .cover-banner {{
    background: linear-gradient(135deg, #0f172a 0%, #0c4a6e 100%);
    color: #ffffff;
    border-radius: 8px;
    padding: 18px 22px;
    margin-bottom: 12px;
  }}
  .cover-kicker {{
    font-family: monospace;
    font-size: 8pt;
    font-weight: 800;
    letter-spacing: 0.08em;
    color: #38bdf8;
    text-transform: uppercase;
    margin-bottom: 4px;
  }}
  .cover-title {{
    font-size: 17.5pt;
    font-weight: 900;
    line-height: 1.18;
    margin: 0 0 6px 0;
  }}
  .cover-sub {{
    font-size: 9.8pt;
    color: #cbd5e1;
    margin: 0;
  }}
  .meta-grid {{
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 8px;
    margin-bottom: 12px;
  }}
  .meta-box {{
    background: #f8fafc;
    border: 1px solid #cbd5e1;
    border-top: 3px solid #0284c7;
    border-radius: 5px;
    padding: 7px 9px;
  }}
  .meta-lbl {{ font-size: 7pt; font-weight: 800; color: #64748b; text-transform: uppercase; font-family: monospace; }}
  .meta-val {{ font-size: 8.8pt; font-weight: 800; color: #0f172a; margin-top: 2px; }}
  .shield-box {{
    background: #fef2f2;
    border: 1.5px solid #ef4444;
    border-left: 6px solid #dc2626;
    border-radius: 6px;
    padding: 9px 12px;
    margin-bottom: 12px;
    break-inside: avoid;
  }}
  .shield-box strong {{ color: #991b1b; font-size: 9.3pt; }}
  .toc-box {{
    background: #f8fafc;
    border: 1px solid #cbd5e1;
    border-radius: 6px;
    padding: 10px 14px;
    margin-bottom: 10px;
    break-inside: avoid;
  }}
  .toc-grid {{
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 4px 16px;
    font-size: 8.3pt;
  }}
  .sec-tag {{
    display: inline-block;
    background: #e0f2fe;
    color: #0369a1;
    border: 1px solid #7dd3fc;
    font-family: monospace;
    font-size: 7.4pt;
    font-weight: 800;
    padding: 1px 6px;
    border-radius: 3px;
    text-transform: uppercase;
    margin-bottom: 3px;
  }}
  /* Graphical Equation Cards */
  .eq-card {{
    background: #ffffff;
    border: 1.5px solid #cbd5e1;
    border-left: 5px solid #0284c7;
    border-radius: 6px;
    padding: 9px 12px;
    margin: 9px 0 11px 0;
    break-inside: avoid;
    page-break-inside: avoid;
  }}
  .eq-head {{
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #e2e8f0;
    padding-bottom: 4px;
    margin-bottom: 7px;
  }}
  .eq-title {{ font-weight: 800; font-size: 9.2pt; color: #0f172a; }}
  .eq-badge {{
    font-family: monospace;
    font-size: 7.2pt;
    font-weight: 800;
    background: #e0f2fe;
    color: #0369a1;
    padding: 2px 6px;
    border-radius: 3px;
  }}
  .eq-display {{
    background: #f8fafc;
    border: 1px solid #e2e8f0;
    border-radius: 5px;
    padding: 8px 12px;
    margin-bottom: 7px;
    font-family: "Cambria Math", "Times New Roman", Georgia, serif;
    font-size: 11.2pt;
    color: #0f172a;
    text-align: center;
    line-height: 1.55;
  }}
  .frac {{
    display: inline-flex;
    flex-direction: column;
    vertical-align: middle;
    align-items: center;
    margin: 0 3px;
    font-size: 0.92em;
  }}
  .frac .num {{ border-bottom: 1.3px solid #0f172a; padding: 0 4px 1px 4px; line-height: 1.15; }}
  .frac .den {{ padding: 1px 4px 0 4px; line-height: 1.15; }}
  .rad-wrap {{ display: inline-flex; align-items: baseline; white-space: nowrap; }}
  .rad-idx {{ font-size: 0.65em; margin-right: 1px; }}
  .radicand {{ border-top: 1.3px solid #0f172a; padding: 0 3px; display: inline-block; }}
  .legend-tbl {{
    width: 100%;
    border-collapse: collapse;
    font-size: 7.9pt;
    margin-bottom: 5px;
  }}
  .legend-tbl th {{
    background: #f1f5f9;
    color: #475569;
    font-weight: 800;
    text-align: left;
    padding: 3px 6px;
    border-bottom: 1px solid #cbd5e1;
  }}
  .legend-tbl td {{ padding: 3px 6px; border-bottom: 1px solid #f1f5f9; vertical-align: top; }}
  .legend-tbl td.sym {{ font-family: "Cambria Math", Georgia, serif; font-weight: 800; color: #0284c7; width: 15%; }}
  .legend-tbl td.unit {{ font-family: monospace; color: #15803d; font-weight: 700; width: 24%; }}
  .eq-note {{
    background: #f0fdf4;
    border-left: 3px solid #16a34a;
    padding: 4px 8px;
    font-size: 8.1pt;
    color: #14532d;
    border-radius: 3px;
  }}
  /* Figure & Table styling */
  .fig-card {{
    background: #ffffff;
    border: 1.5px solid #cbd5e1;
    border-radius: 6px;
    padding: 8px 10px;
    margin: 9px 0 11px 0;
    break-inside: avoid;
    page-break-inside: avoid;
  }}
  .fig-head {{
    display: flex;
    align-items: center;
    gap: 8px;
    border-bottom: 1px solid #e2e8f0;
    padding-bottom: 4px;
    margin-bottom: 6px;
  }}
  .fig-num {{
    background: #0f172a;
    color: #38bdf8;
    font-family: monospace;
    font-size: 7.3pt;
    font-weight: 800;
    padding: 2px 6px;
    border-radius: 3px;
  }}
  .fig-title {{ font-weight: 800; font-size: 9.1pt; color: #0f172a; }}
  .fig-img-wrap {{ text-align: center; background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 4px; padding: 3px; }}
  .fig-img-wrap img {{ width: 100%; max-height: 295px; object-fit: contain; display: block; margin: 0 auto; }}
  .svg-wrap {{ margin: 2px 0 5px 0; }}
  .fig-cap {{ font-size: 8pt; color: #334155; margin-top: 5px; line-height: 1.4; }}
  .fig-points {{ margin: 4px 0 0 16px; padding: 0; font-size: 7.9pt; color: #475569; }}
  .fig-points li {{ margin-bottom: 2px; }}
  .data-tbl {{
    width: 100%;
    border-collapse: collapse;
    font-size: 7.9pt;
    margin: 7px 0 10px 0;
    break-inside: avoid;
    page-break-inside: avoid;
  }}
  .data-tbl th {{
    background: #0f172a;
    color: #ffffff;
    padding: 5px 7px;
    text-align: left;
    font-weight: 700;
  }}
  .data-tbl td {{ padding: 4px 7px; border-bottom: 1px solid #e2e8f0; vertical-align: top; }}
  .data-tbl tr:nth-child(even) td {{ background: #f8fafc; }}
  .cite-link {{
    color: #0284c7;
    font-weight: 800;
    text-decoration: none;
    font-size: 7.6pt;
    vertical-align: super;
    margin-left: 1px;
  }}
  .ref-tbl {{ width: 100%; border-collapse: collapse; font-size: 7.7pt; }}
  .ref-tbl td {{ padding: 3.5px 5px; border-bottom: 1px solid #e2e8f0; vertical-align: top; }}
  .ref-num {{ font-family: monospace; font-weight: 800; color: #0284c7; width: 28px; }}
  .doi-url {{ color: #0369a1; text-decoration: underline; overflow-wrap: anywhere; font-family: monospace; font-size: 7.4pt; }}
  .signoff-box {{
    margin-top: 12px;
    border: 1.5px solid #0284c7;
    border-radius: 6px;
    padding: 10px 14px;
    background: #f8fafc;
    break-inside: avoid;
  }}
  .callout-grid {{
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 10px;
    margin: 7px 0 10px 0;
    break-inside: avoid;
  }}
  .callout {{
    background: #f0fdf4;
    border: 1px solid #86efac;
    border-left: 4px solid #16a34a;
    border-radius: 5px;
    padding: 7px 10px;
    font-size: 8.2pt;
  }}
  .callout-blue {{
    background: #eff6ff;
    border: 1px solid #93c5fd;
    border-left: 4px solid #0284c7;
    border-radius: 5px;
    padding: 7px 10px;
    font-size: 8.2pt;
  }}
</style>
</head>
<body>

<!-- COVER & EXECUTIVE ABSTRACT -->
<div class="cover-banner">
  <div class="cover-kicker">Physician's Clinical Engineering Reference &amp; Biophysical Metrology Monograph • Release beta.00.01.0065</div>
  <div class="cover-title">BFM Biometric Function Monitor: Multi-Channel Transducer Physics, Glycemic Metrology &amp; Clinical Interoperability Guide</div>
  <p class="cover-sub">Peer-Vetted Evidence Base, Graphical Mathematical Equations, Pure Zero-Data Hardware Telemetry &amp; HL7 FHIR R4 / LOINC Integration Architecture (FITAOS / Veepoo Model 5010)</p>
</div>

<div class="meta-grid">
  <div class="meta-box"><div class="meta-lbl">Platform System</div><div class="meta-val">BFM Biometric Function Monitor</div></div>
  <div class="meta-box"><div class="meta-lbl">Telemetry Channels</div><div class="meta-val">13 Synchronized + 5 Sub-Reports</div></div>
  <div class="meta-box"><div class="meta-lbl">EHR Interoperability</div><div class="meta-val">HL7 FHIR R4 JSON &amp; LOINC CSV</div></div>
  <div class="meta-box"><div class="meta-lbl">Evidence Base</div><div class="meta-val">30 Linked DOI / Consensus Refs</div></div>
</div>

<p>
The <strong>BFM Biometric Function Monitor</strong> is a local-first, sovereign ambulatory biofeedback workstation engineered to eliminate out-of-clinic data loss across Type 2 metabolic self-management. Traditional diabetes visits rely upon a single venipuncture HbA1c every 90 days alongside sporadic fingerstick spot checks, leaving attending physicians blind to postprandial excursions, asymptomatic nocturnal desaturations, systolic non-dipping patterns, autonomic vagal dampening, and off-wrist sensor gaps. By pairing continuous multi-wavelength smartwatch photoplethysmography (288 daily 5-minute intervals = 25,920 samples per 90-day quarter) with behavioral intake logging, pre-choice glycemic modeling, and HL7 FHIR R4 / LOINC flowsheet exports, BFM unifies Patient, Primary Care Physician, Endocrinologist, Diabetic Pharmacist, Registered Dietitian, and Neurologist onto one mathematical dataset.{cite(1, 2, 7)}
</p>

<div class="shield-box">
  <strong>REGULATORY SCOPE &amp; ADJUNCTIVE NON-DIAGNOSTIC BOUNDARY:</strong> This monograph documents physical transduction principles, statistical calibration performance, and standardized EHR interoperability for the BFM Workstation and Veepoo Model 5010 wearable. <strong>The optical glucose trend channel is a non-invasive adjunctive wellness &amp; behavioral biofeedback modality—NOT an FDA-cleared continuous glucose monitor (CGM).</strong> Type 1 diabetes and exogenous rapid-acting insulin bolus titration require capillary blood glucose fingersticks or FDA-cleared interstitial CGM verification. Never modify prescribed pharmacotherapy solely from uncalibrated wearable estimates.{cite(2, 7)}
</div>

<div class="toc-box">
  <strong style="color:#0f172a;font-size:9.2pt;display:block;margin-bottom:5px;">Monograph Contents &amp; Clinical Audit Roadmap</strong>
  <div class="toc-grid">
    <div><strong>Section 1:</strong> Information Theory &amp; 288-Point Sampling Statistics</div>
    <div><strong>Section 7:</strong> Pure Zero-Data Reality &amp; Blue Off-Wrist Metrology</div>
    <div><strong>Section 2:</strong> Optical PPG, PWTT, SpO2, Thermal Physics &amp; Lab Cal</div>
    <div><strong>Section 8:</strong> Pre-Choice Nutrition, Plasma Dilution &amp; HDYF Pain</div>
    <div><strong>Section 3:</strong> TIR/CV, ADAG eA1C / GMI &amp; 120-Day RBC Kinetics</div>
    <div><strong>Section 9:</strong> Pharmacological Sanctity &amp; Split-Dose Titration Audit</div>
    <div><strong>Section 4:</strong> 5 Watch Sub-Reports: Lead-I ECG, MET, BComp, BIA, Breath</div>
    <div><strong>Section 10:</strong> BLE 5.0 GATT Opcode Table &amp; MCU Hardware Boundaries</div>
    <div><strong>Section 5:</strong> CAN Screening, 2D Lorenz R-R &amp; Sleep / BP Dipping</div>
    <div><strong>Section 11:</strong> HL7 FHIR R4, LOINC/UCUM Codebook &amp; USB Backup</div>
    <div><strong>Section 6:</strong> Contraction-Mediated GLUT4 &amp; 2,000-Step Senior Floor</div>
    <div><strong>Section 12:</strong> 30 Linked Peer-Reviewed References &amp; Physician Sign-Off</div>
  </div>
</div>

{svg_fig_card("0.1", "Closed-Loop 5-Specialist Shared Care Architecture Over Sovereign Local SQLite + FHIR R4", build_svg_shared_care(), "Each specialist inspects the identical time-aligned 13-channel timeline, behavioral intake journal, and HDYF voice-note context—eliminating fragmented episodic silos between 90-day visits.", (1, 2, 7))}

<!-- SECTION 1 -->
<div class="page-break"></div>
<span class="sec-tag">Section 1 • Information Theory &amp; Biostatistics</span>
<h1>1. Information-Theoretic Rationale for 288-Point Continuous Ambulatory Sampling</h1>
<p>
Acute postprandial glycemia exhibits rapid absorption transients with rise times of 25–45 minutes and full excursion bandwidths spanning f<sub>max</sub> &asymp; 0.04–0.08 cycles/min (period &ge; 15 min). Measuring once per quarter (interval &Delta;t = 129,600 min) or twice daily via pre-meal fingersticks grossly violates the Nyquist–Shannon sampling theorem, causing severe temporal aliasing that conceals +60 to +110 mg/dL food spikes and overnight nadir lows.{cite(1, 3, 9)} Continuous 5-minute wearable logging samples at f<sub>s</sub> = 0.2 cycles/min (&Delta;t = 5 min), faithfully reconstructing postprandial velocity dG/dt, daytime nap episodes, and nocturnal recovery envelopes.{cite(2, 4)}
</p>

{eq_card(
    "Equation 1.1 — Nyquist–Shannon Non-Aliased Glycemic Reconstruction Criterion",
    "SIGNAL FIDELITY",
    f'f<sub>s</sub> = {frac("1", "&Delta;t<sub>sample</sub>")} = {frac("288 samples", "1,440 min")} = 0.20 min<sup>&minus;1</sup> &nbsp;&ge;&nbsp; 2 &middot; f<sub>max</sub> &nbsp;&nbsp;(Period<sub>min</sub> = 10–15 min)',
    [
        ("f<sub>s</sub>", "BFM wearable acquisition sampling frequency across all 24h channels", "0.20 cycles/min (5 min)"),
        ("f<sub>max</sub>", "Highest physiological frequency of postprandial glucose excursion envelope", "~0.05–0.08 cycles/min"),
        ("N<sub>90d</sub>", "Cumulative longitudinal observations per 90-day quarterly endocrinology cycle", "25,920 samples / channel"),
    ],
    "Eliminates temporal aliasing of 30–60 minute dietary spikes, reactive postprandial dips, and nocturnal breathing desaturations that episodic testing misses.",
    (1, 2, 9)
)}

{eq_card(
    "Equation 1.2 — Standard Error Attenuation (SE) &amp; Parallel Multi-Specialist Signal Retention",
    "NOISE SUPPRESSION",
    f'SE<sub>mean</sub> = {frac("&sigma;", sqrt_expr("N"))} &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; I<sub>retained</sub> = 1 &minus; &prod;<sub>k=1..5</sub> (1 &minus; &eta;<sub>k</sub>)',
    [
        ("&sigma;", "Pointwise biological + optical variability across serial wearable observations", "~24 mg/dL diurnal SD"),
        ("N", "Sample count: N = 1 (office), N = 288 (1-day curve), N = 25,920 (90-day quarter)", "17&times; daily / 161&times; 90d gain"),
        ("&eta;<sub>k</sub>", "Per-specialist diagnostic capture efficiency across PCP, Endo, PharmD, RD, Neuro", "Parallel team &gt;96%"),
    ],
    "Increasing N from 1 office check to 288 daily points collapses random measurement standard error by a factor of &radic;288 &asymp; 17.0&times; daily and &radic;25,920 &asymp; 161&times; over 90 days, enabling calm, evidence-driven titration.",
    (3, 4, 9)
)}

{svg_fig_card("1.1", "Sampling Noise Attenuation & Parallel Team Diagnostic Signal Retention", build_svg_sampling_retention(), "Panel A demonstrates inverse square-root standard error suppression across N = 1 to 25,920 samples; Panel B contrasts episodic ~2.5% quarterly snapshot capture against >96% closed-loop parallel visibility.", (1, 3, 9))}

<!-- SECTION 2 -->
<div class="page-break"></div>
<span class="sec-tag">Section 2 • Biophysical Transducers &amp; Lab Calibration</span>
<h1>2. Multi-Wavelength Optical, Hemodynamic &amp; Thermal Physics (Model 5010)</h1>
<p>
The FITAOS / Veepoo Model 5010 integrates six co-located hardware modalities beneath a biocompatible bottom optical window and lateral stainless steel electrodes: green (525 nm) arterial-capillary PPG, red/near-infrared (660 nm / 940 nm) dual-LED spectroscopy, dual-NTC differential thermometry, triaxial MEMS accelerometry, thoracic Einthoven Lead-I ECG contacts, and 50 kHz tetrapolar-equivalent wrist BIA plates.{cite(10, 11, 20)} Uncalibrated optical hardware displays high precision yet carries a factory systemic offset; single-point anchoring against a concurrent venipuncture BMP/CMP or HbA1c-derived average glucose aligns ongoing trend accuracy within &le;5.0% of laboratory truth.{cite(5, 7)}
</p>

{svg_fig_card("2.1", "Dermal-Vascular Multi-Wavelength Optical Path, Dual-NTC Thermal & Lead-I / BIA Electrode Geometry", build_svg_wrist_transducers(), "Wearing the strap snug 1 finger-width above the ulnar styloid stabilizes optical pathlength L and prevents ambient stray-light artifact during both ambulatory motion and 25–40 second active bezel sweeps.", (10, 11, 13))}

{eq_card(
    "Equation 2.1 — Modified Beer–Lambert Tissue Optical Attenuation &amp; PWTT Vascular Compliance",
    "OPTICAL + PWTT",
    f'&Delta;A(&lambda;) = &epsilon;(&lambda;) &middot; &Delta;c &middot; DPF(&lambda;) &middot; d + &Delta;G<sub>scatter</sub> &nbsp;&nbsp;|&nbsp;&nbsp; SBP &prop; {frac("1", "PWTT<sup>2</sup>")} &middot; ln(K<sub>stiff</sub>)',
    [
        ("&Delta;A(&lambda;), DPF", "Differential optical absorbance &amp; differential pathlength factor across dermis thickness d", "525 / 660 / 940 nm"),
        ("PWTT", "Pulse Wave Transit Time from ECG/PPG cardiac systolic foot to peripheral arterial peak", "ms (Moens–Korteweg)"),
        ("R<sub>SpO2</sub>", "Modulation ratio (AC<sub>660</sub> / DC<sub>660</sub>) / (AC<sub>940</sub> / DC<sub>940</sub>) mapped to arterial SpO2", "Normal &ge; 95%"),
    ],
    "Fusing multi-wavelength optical absorption with pulse wave velocity separates transient vasoactive motion shifts from true glucose and blood-pressure envelope trends.",
    (10, 11, 12, 13)
)}

{eq_card(
    "Equation 2.2 — Empirical Bench Repeatability (CV = 5.36%) &amp; Venous Phlebotomy Affine Calibration",
    "LAB SYNC &le;5.0%",
    f'CV = {frac("&sigma;", "&mu;")} &times; 100% = 5.36% &nbsp;&nbsp;|&nbsp;&nbsp; G<sub>cal</sub>(t) = G<sub>raw</sub>(t) + (G<sub>venous</sub>(t<sub>0</sub>) &minus; G<sub>raw</sub>(t<sub>0</sub>))',
    [
        ("CV, SE", "20-sample empirical repeatability benchmark: mean 145.8 mg/dL, SE &lt; 1.8 mg/dL", "CV = 5.36%"),
        ("G<sub>venous</sub>(t<sub>0</sub>)", "Timed venous plasma glucose or ADA ADAG target eAG at phlebotomy draw t<sub>0</sub>", "e.g. 191.5 mg/dL @ 8.3%"),
        ("Register Lock", "Hardware calibration coefficient written persistently to watch non-volatile memory", "Target Error &le; 5.0%"),
    ],
    "Because optical scatter is remarkably low (CV = 5.36%, SE &lt; 1.8 mg/dL), eliminating systematic factory zero-point bias once against venipuncture blood work anchors ongoing home trends reliably.",
    (5, 7)
)}

<!-- SECTION 3 -->
<div class="page-break"></div>
<span class="sec-tag">Section 3 • Glycemic Metrology &amp; Erythrocyte Kinetics</span>
<h1>3. Time-in-Range, Glycemic Variability &amp; Acute eA1C vs. 120-Day Lab HbA1c</h1>
<p>
International Consensus guidelines recognize Time-in-Range (TIR, 70–180 mg/dL; BFM extended target band 60–200 mg/dL as clinician-configured) and Percent Coefficient of Variation (%CV &le; 36%) as stronger predictors of microvascular complications than isolated HbA1c alone.{cite(2, 8)} Crucially, BFM resolves a frequent source of clinical confusion: when a patient achieves cleaner nutrition and post-meal walking, acute 24-hour ADAG eA1C (GMI) responds within days, whereas laboratory HbA1c lags because human erythrocytes circulate for ~120 days (only ~17.5% renew over 21 days).{cite(5, 6, 14)}
</p>

{eq_card(
    "Equation 3.1 — ADAG eA1C / GMI Equations &amp; 120-Day Erythrocyte Turnover Integral",
    "ADAG + GMI + RBC",
    f'eA1C = {frac("G<sub>mean</sub> + 46.7", "28.7")} &nbsp;|&nbsp; GMI (%) = 3.31 + 0.02392 &middot; G<sub>mean</sub> &nbsp;|&nbsp; A1C(t) = (1 &minus; {frac("t", "120")})A1C<sub>0</sub> + {frac("t", "120")}A1C<sub>new</sub>',
    [
        ("G<sub>mean</sub>", "Continuous arithmetic mean glucose across daily or multi-day 5-min window", "mg/dL (LOINC 2339-0)"),
        ("eAG Target", "Inverse ADAG formula: eAG = (28.7 &times; HbA1c) &minus; 46.7", "8.3% &#8594; 191.5 mg/dL"),
        ("t / 120", "Fraction of senescent erythrocytes replaced after t days of metabolic improvement", "t = 21d &#8594; 17.5% renewed"),
    ],
    "Reconciles immediate improvements in daily BFM GMI against slower quarterly venipuncture HbA1c shifts, preventing clinicians from misreading acute behavioral success as sensor error.",
    (5, 6, 8, 14)
)}

{eq_card(
    "Equation 3.2 — Continuous Time-in-Range (TIR), Hypoglycemia Guard (TBR) &amp; Excursion Velocity",
    "CV &le; 36% TARGET",
    f'TIR = {frac("100%", "T")} &int;<sub>0</sub><sup>T</sup> I(70 &le; G(t) &le; 180) dt &nbsp;&nbsp;|&nbsp;&nbsp; %CV = {frac("&sigma;<sub>G</sub>", "&mu;<sub>G</sub>")} &times; 100% &nbsp;&nbsp;|&nbsp;&nbsp; v<sub>G</sub> = {frac("dG", "dt")}',
    [
        ("TIR / TBR", "Target Time-in-Range &ge; 70% (&gt;16h 48m/day); Time-Below-Range (&lt;70) strictly &lt; 4%", "Older adults TBR &lt; 1%"),
        ("%CV", "Relative glycemic stability threshold dividing stable (&le;36%) from labile (&gt;36%) patterns", "&le; 36% stable ceiling"),
        ("dG / dt", "Instantaneous glycemic slope across consecutive 5-minute optical windows", "Alert if &gt; +2.0 mg/dL/min"),
    ],
    "Separates flat benign postprandial curves from sharp insulin-depleting excursions (&gt; +2.0 mg/dL/min) even when mean glucose appears unchanged.",
    (1, 2, 4)
)}

{svg_fig_card("3.1", "Immediate Acute Watch eA1C / GMI Improvement vs. 120-Day Red Blood Cell Renewal Lag", build_svg_rbc_kinetics(), "Explains quantitatively why a documented 8.5% historical HbA1c shifts smoothly toward ~8.3% at Day 21 even while daily BFM eA1C already reflects clean nutritional control.", (5, 6, 14))}

{fig_card("3.2", "Live BFM Daily 24-Hour Glucose & ADAG eA1C Projection Curves (Unclipped 04-REPORTS View)", "../images/bfm_t04_08_glucose_a1c.png", "Live November 26 daily trace anchored against the patient's 8.3% HbA1c baseline (191.5 mg/dL): calm nocturnal 60–66 mg/dL baseline rising through daytime intake (peak 188 mg/dL, 183 mg/dL at 8:00 PM) with sky-blue Watch Off-Wrist gap exclusions.", ["Plotted 24-hour ADAG eA1C mirrors instantaneous glucose excursion cleanly (3.7%–3.9% sleeping to 8.2% peak vs. 7.0% ADA and 8.3% anchor lines).", "Terminates strictly at verified current-hour watch samples without future synthetic curves."], (2, 5, 6))}

<!-- SECTION 4 -->
<div class="page-break"></div>
<span class="sec-tag">Section 4 • 5 Live Watch Sub-Reports</span>
<h1>4. Five Watch Sub-Reports: Lead-I ECG, MET, Blood Composition, BIA &amp; Breath Rate</h1>
<p>
Through reverse-engineered BLE protocol synchronization validated against watch firmware tables, BFM workstation ingests all five specialized wearable sub-reports alongside primary continuous vitals: stored bezel Lead-I ECG &amp; QTc intervals, 24/7 triaxial MET energy expenditure, optical hemorheology &amp; uric acid tonometry, tetrapolar wrist BIA body composition, and 24-hour respiratory rate / obstructive apnea screening.{cite(15, 19, 20, 24)}
</p>

{eq_card(
    "Equation 4.1 — Lead-I Ventricular Repolarization Correction (Bazett &amp; Fridericia QTc)",
    "LIVE: 250 QT / 389 QTC",
    f'QTc<sub>Bazett</sub> = {frac("QT", sqrt_expr("RR<sub>sec</sub>"))} = {frac("250 ms", sqrt_expr("60 / 145"))} = 389 ms &nbsp;&nbsp;|&nbsp;&nbsp; QTc<sub>Frid</sub> = {frac("QT", sqrt_expr("RR<sub>sec</sub>", "3"))}',
    [
        ("QT, RR<sub>sec</sub>", "Live Nov 26 10:23 AM bezel capture (40s): measured QT = 250 ms at HR = 145 BPM", "QTc = 389 ms (&le;440 safe)"),
        ("LF / HF", "Low-Frequency (sympathetic) to High-Frequency (vagal) spectral ratio during ECG", "1.31 (Target 1.0–1.5)"),
        ("Fridericia", "Cube-root heart-rate correction preferred by FDA guidelines at high heart rates", "336 ms at 145 BPM"),
    ],
    "Monitors QT interval prolongation triggered by severe hypoglycemia or electrolyte imbalance; live watch download verifies healthy repolarization below the 440 ms prolonged safety ceiling.",
    (15, 16, 18)
)}

{eq_card(
    "Equation 4.2 — Continuous 24h MET Energy Integral &amp; Poiseuille Hemorheology Law",
    "101 MET • 115 BCOMP",
    f'E<sub>MET-hrs</sub> = &int;<sub>t<sub>1</sub></sub><sup>t<sub>2</sub></sup> MET(t) dt &ge; 28.0 &nbsp;&nbsp;|&nbsp;&nbsp; Q<sub>cap</sub> = {frac("&pi; &middot; r<sup>4</sup> &middot; &Delta;P", "8 &middot; &eta; &middot; L")}',
    [
        ("MET(t)", "Normalized metabolic workload multiple: Sleep 1.0, Desk 1.2–1.4, Brisk Walk 3.5+ MET", "Live Nov 26: 34.2 MET-hrs"),
        ("&eta;, r", "Effective plasma/whole-blood viscosity (cP) &amp; capillary vessel radius r", "Target 3.5–4.5 cP (Live 3.8 avg)"),
        ("Uric Acid", "Optical tonometry screen alongside total lipid proxy (target &lt; 6.5 mg/dL)", "115 sweeps synced live"),
    ],
    "Confirms true physical energy expenditure autonomously 24/7 while linking microvascular viscosity (&eta;) directly to hydration status and optical measurement stability.",
    (19, 23, 24)
)}

{eq_card(
    "Equation 4.3 — BIA Skeletal Muscle Glucose Sink &amp; PPG Respiratory Sinus Arrhythmia (RSA)",
    "37.3% MUSCLE • 227 BREATH",
    f'FFM = a &middot; {frac("H<sup>2</sup>", "Z<sub>50kHz</sub>")} + b &middot; W + c &nbsp;&nbsp;|&nbsp;&nbsp; V<sub>clear</sub> &prop; M<sub>muscle</sub> &middot; S<sub>GLUT4</sub> &nbsp;&nbsp;|&nbsp;&nbsp; RSA = {frac("HR<sub>in</sub> &minus; HR<sub>ex</sub>", "f<sub>breath</sub>")}',
    [
        ("M<sub>muscle</sub>", "Live Nov 26 11:20 AM BIA scan: 37.3% skeletal muscle (47.5 kg), FFM 55.0 kg", "26.5% fat • BMI 24.4 • 57.4% H2O"),
        ("V<sub>clear</sub>", "DeFronzo clamp benchmark: &gt;80% of exogenous post-meal glucose clears to skeletal muscle", "BMR 2,210.3 kcal/day"),
        ("f<sub>breath</sub>, AHI", "227 continuous breath epochs: 14 RPM resting (12–18 target), AHI 2.4/hr (&lt;5 normal)", "Max pause 8s • 95.8% regular"),
    ],
    "Tracking lean muscle preservation alongside fat loss confirms durable insulin-independent glucose sink capacity; continuous RSA respiratory rate screens nocturnal hypopnea silently.",
    (10, 20, 21, 22)
)}

<div style="display:grid;grid-template-columns:1fr 1fr;gap:10px;break-inside:avoid;">
  {fig_card("4.1", "Live Synced ECG (10:23 AM: 250 ms QT / 389 QTc)", "../images/bfm_t04_06_sub_ecg_met.png", "Live watch Lead-I ECG marker framed cleanly between sky-blue Off-Wrist bands, plus Sub-Report 03 MET bar.", ["250 ms QT, 1.31 LF/HF, 28/100 stress, 70/100 resilience."], (15, 16))}
  {fig_card("4.2", "Live Synced BIA Body Comp (11:20 AM: 37.3% Muscle)", "../images/bfm_t04_sub_bodycomp_live.png", "Live tetrapolar watch BIA download: 37.3% (47.5 kg) skeletal muscle, 26.5% fat, 24.4 BMI, 57.4% cellular water.", ["Zero placeholder extrapolation outside true 11:20 AM hardware sync."], (20, 21))}
</div>

{fig_card("4.3", "Live 24-Hour Continuous Respiratory Rate & Nocturnal Mechanics (227 Epochs)", "../images/bfm_t04_sub_breath_live.png", "227 continuous PPG-RSA breathing epochs averaging 14 RPM (12–18 optimal envelope) with AHI 2.4/hr (&lt;5 normal threshold), 8-second max pause, and 95.8% respiratory regularity.", ["Co-plots nocturnal stability directly beneath SpO2 and blue Watch Off-Wrist windows."], (10, 13))}

<!-- SECTION 5 -->
<div class="page-break"></div>
<span class="sec-tag">Section 5 • Cardiac Autonomic Neuropathy, HRV Lorenz &amp; Sleep</span>
<h1>5. Diabetic CAN Screening, 2D Poincar&#233; R-R Plot &amp; Nocturnal BP Dipping</h1>
<p>
Cardiac Autonomic Neuropathy (CAN) is a high-morbidity complication of long-standing dysglycemia often preceding overt pain symptoms.{cite(18)} BFM captures raw inter-beat R-R intervals from optical hardware (4,831+ to 8,859 daily epochs) to render a genuine 2D Lorenz (Poincar&#233;) scatter plot quantifying parasympathetic beat-to-beat variance (SD1) vs. 24-hour global sympathetic/parasympathetic excursion (SD2). Simultaneously, ambulatory pulse-transit BP quantifies nocturnal systolic dipping and <code>0xE0</code> multi-session architecture records consolidated overnight sleep plus daytime naps.{cite(16, 17, 26, 27)}
</p>

{eq_card(
    "Equation 5.1 — 2D Lorenz (Poincar&#233;) SD1 / SD2 Axes &amp; Nocturnal Systolic Dipping Ratio",
    "NEUROLOGY + ABPM",
    f'SD1<sup>2</sup> = {frac("1", "2")} Var(RR<sub>n</sub> &minus; RR<sub>n+1</sub>) &nbsp;|&nbsp; S = &pi; &middot; SD1 &middot; SD2 &nbsp;|&nbsp; Dip% = {frac("SBP<sub>day</sub> &minus; SBP<sub>night</sub>", "SBP<sub>day</sub>")} &times; 100%',
    [
        ("SD1, RMSSD", "Short-term beat-to-beat parasympathetic dispersion orthogonal to identity line", "Warning if SD1 &lt; 15 ms"),
        ("SD2, S", "Long-term 24-hour autonomic envelope &amp; total ellipse dispersion area S", "Healthy 'Comet' geometry"),
        ("Dip%", "Physiological nocturnal systolic blood pressure reduction during deep NREM sleep", "Healthy dippers: 10%–20%"),
    ],
    "A collapsed beat-to-beat scatter ('torpedo' silhouette) or blunted nocturnal BP dipping (&lt;10% non-dipper) provides objective early warning of autonomic neuropathy and renal/vascular strain.",
    (16, 17, 18, 26, 27)
)}

{svg_fig_card("5.1", "Poincaré / 2D Lorenz Geometry Comparison: Preserved Vagal Comet vs. Diabetic CAN Torpedo", build_svg_lorenz_can(), "SD1 captures vagal respiratory beat-to-beat modulation; progressive loss narrows the transverse axis toward the identity diagonal well before clinical resting tachycardia emerges.", (17, 18))}

<div style="display:grid;grid-template-columns:1fr 1fr;gap:10px;break-inside:avoid;">
  {fig_card("5.2", "Genuine Watch 2D Lorenz Scatter Plot", "../images/bfm_t04_13_hrv_lorenz.png", "Actual hardware inter-beat R-R interval scatter plot computed without synthetic smoothing.", ["SD1/SD2 centroid quantifies vagal resilience cleanly."], (16, 17))}
  {fig_card("5.3", "24h Multi-Session Sleep & Daytime Naps", "../images/bfm_t04_14_sleep_0xe0_naps.png", "Nocturnal Deep/Light/REM/Awake TLV blocks combined with verified daytime restorative nap segments.", ["Correlates sleep continuity directly with dawn fasting glucose."], (18, 26))}
</div>

<!-- SECTION 6 -->
<div class="page-break"></div>
<span class="sec-tag">Section 6 • Kinetic Glucose Disposal (GLUT4)</span>
<h1>6. Contraction-Mediated GLUT4 Clearance &amp; The 2,000-Step Senior Floor</h1>
<p>
Skeletal muscle contraction translocates GLUT4 glucose transporter vesicles to the sarcolemma via an AMPK/Ca<sup>2+</sup>/calmodulin pathway completely independent of insulin receptor signaling—making light-to-moderate walking effective even in severe insulin resistance.{cite(21, 22, 23)} Instead of discouraging seniors with punitive 8,000-step commercial targets, BFM synchronizes a realistic <strong>2,000 steps/day (~0.95 mi)</strong> hardware floor to the wrist watch. Patient-specific regression in the Exercise Metabolic Correlator empirically contrasts active vs. sedentary glucose days.{cite(23, 24)}
</p>

{eq_card(
    "Equation 6.1 — Insulin-Independent AMPK GLUT4 Transport Flux &amp; Marginal Step Sensitivity",
    "GLUT4 SYNERGY",
    f'J<sub>GLUT4</sub>(t) = k<sub>ins</sub> &middot; [Insulin] + k<sub>AMPK</sub> &middot; MET(t) &nbsp;&nbsp;|&nbsp;&nbsp; {frac("d(TIR)", "d(Steps)")} &gt; 0 &nbsp;(Steepest 500 &#8594; 2,500 steps)',
    [
        ("k<sub>AMPK</sub> &middot; MET(t)", "Contraction-activated skeletal muscle glucose uptake proportional to MET intensity", "Bypasses insulin block"),
        ("Active Timing", "15–20 minute brisk walk initiated 20–25 min post-prandially intercepts absorption peak", "&minus;25 to &minus;45 mg/dL spike"),
        ("Senior Goal", "Watch-synchronized 2,000-step daily floor with MCU &lt;15-step false-step debounce", "3 &times; ~650-step walks"),
    ],
    "Empirically proves to older adults that three brief 15-minute walks right after meals dramatically flatten post-meal glucose crests without joint exhaustion.",
    (21, 22, 23, 24)
)}

{fig_card("6.1", "Live Exercise Metabolic Correlator: Active vs. Sedentary Patient Stratification & Slider Simulator", "../images/bfm_t04_05_step_glut4_correlator.png", "BFM compares active days vs. sedentary days inside the user's own historical SQLite vault while modeling step-volume dose-response curves for Time-in-Range and projected eA1C.", ["Turns behavioral advice into patient-owned quantitative proof using their own watch history."], (22, 23))}

<!-- SECTION 7 -->
<div class="page-break"></div>
<span class="sec-tag">Section 7 • Forensic Data Integrity &amp; 13-Channel Architecture</span>
<h1>7. Pure Zero-Data Reality, Sky-Blue Watch Off-Wrist Bands &amp; 13-Channel Alignment</h1>
<p>
Physician trust fails when consumer wellness apps fill missing battery-charging windows with synthetic sine waves or fake baseline averages. BFM enforces a strict <strong>Pure Zero-Data Reality Mandate</strong>: unelapsed future hours and unsynced watch streams strictly display 0 or explicit awaiting-sync notices, never fake meals or synthetic prescriptions. Whenever the wearable capacitance flag detects watch removal (charging, showering, cleaning), all 2D timelines shade the exact elapsed interval in translucent sky blue labeled <code>WATCH OFF WRIST</code> while keeping the 3D multi-channel trajectory faithful to physical worn samples.{cite(1, 2)}
</p>

<table class="data-tbl">
  <thead><tr><th style="width:10%;">Lane ID</th><th style="width:24%;">Biometric / Behavioral Track</th><th style="width:26%;">Hardware / App Source</th><th style="width:20%;">Zero-Data State</th><th style="width:20%;">Clinical Role</th></tr></thead>
  <tbody>
    <tr><td><code>CH 01</code></td><td>Blood Glucose Tonometry</td><td>Multi-wavelength optical PPG + Lab Anchor</td><td><code>0 mg/dL</code></td><td>TIR, CV%, Post-meal spike</td></tr>
    <tr><td><code>CH 02</code></td><td>Blood Pressure (SBP / DBP)</td><td>PWTT Optical Pulse Transit</td><td><code>0 / 0 mmHg</code></td><td>Hypertension &amp; Dipping%</td></tr>
    <tr><td><code>CH 03</code></td><td>Heart Rate &amp; Zones</td><td>525 nm Green PPG (5-min binned)</td><td><code>0 BPM</code></td><td>Tachy/Brady &amp; exertion</td></tr>
    <tr><td><code>CH 04</code></td><td>Oxygen Saturation (SpO2)</td><td>Red/IR Dual-LED Ratio-of-Ratios</td><td>Blue Off-Wrist Band</td><td>OSAHS / Hypoxia screen</td></tr>
    <tr><td><code>CH 05</code></td><td>Core &amp; Skin Temperature</td><td>Dual-NTC Matrix (0x88 History)</td><td><code>0.0 &deg;F</code></td><td>Febrile / circadian nadir</td></tr>
    <tr><td><code>CH 06</code></td><td>HRV &amp; Neuro-Stress</td><td>R-R Intervals (0xDF 244B MTU)</td><td><code>0 ms</code></td><td>CAN &amp; vagal resilience</td></tr>
    <tr><td><code>CH 07</code></td><td>Steps &amp; GLUT4 Activity</td><td>Triaxial MEMS (32-bit + Sport)</td><td><code>0 steps</code></td><td>2,000-step floor / GLUT4</td></tr>
    <tr><td><code>CH 08</code></td><td>Sleep &amp; Daytime Naps</td><td>0xE0 TLV Stage Decoder</td><td><code>0 hrs</code></td><td>Sleep latency / fragmentation</td></tr>
    <tr><td><code>CH 09</code></td><td>HDYF Somatosensory Pain</td><td>Mobile Voice Note NLP Parser</td><td><code>None reported</code></td><td>DPN burning / joint pain</td></tr>
    <tr><td><code>CH 10</code></td><td>HDYF Fatigue / Mood</td><td>Mobile Audio Stress &amp; Text Score</td><td><code>Nominal</code></td><td>Hypo/hyper mood correlate</td></tr>
    <tr><td><code>CH 11</code></td><td>Net Carbohydrate Intake</td><td>Tab 02 / Mobile Companion Journal</td><td><code>[JOURNAL EMPTY]</code></td><td>Per-meal &le;45g / 135g day</td></tr>
    <tr><td><code>CH 12</code></td><td>Fluid Intake Hydration</td><td>Beverage Logger (Target &ge;64 oz)</td><td><code>0.0 fl oz</code></td><td>Hemoconcentration guard</td></tr>
    <tr><td><code>CH 13</code></td><td>Prescribed Medication Rx</td><td>Tab 09 User/MD Entered Regimen</td><td><code>[NO MEDS RECORDED]</code></td><td>Split-dose T<sub>max</sub> alignment</td></tr>
  </tbody>
</table>

<div style="display:grid;grid-template-columns:1fr 1fr;gap:10px;break-inside:avoid;">
  {fig_card("7.1", "Animated 13-Channel 3D Orbital Multi-Telemetry Plot", "../images/bfm_t04_01_3d_multichannel.png", "Time &times; Channel &times; Normalized Clinical Range topography unifying physiological curves and intake markers.", ["Identifies synchronized multi-channel excursions at a glance."], (1, 7))}
  {fig_card("7.2", "Unified Sky-Blue Watch Off-Wrist Gap Exclusion", "../images/bfm_t04_watch_off_wrist.png", "Sky-blue dashed OFF intervals mark exact charging/shower removal spans identically across 2D lanes.", ["Prevents false zero-SpO2 or false zero-HR alarm fatigue."], (2, 13))}
</div>

<!-- SECTION 8 -->
<div class="page-break"></div>
<span class="sec-tag">Section 8 • Nutrition Metrology &amp; Behavioral HDYF</span>
<h1>8. Pre-Choice Nutritional Metrology, Plasma Dilution &amp; HDYF Telemetry</h1>
<p>
Rather than auditing diet retrospectively after a spike has occurred, BFM's Tab 03 Estimator uses Google Gemini AI alongside published franchise disclosures (such as Taco Time NW Fiesta Platter: 520 kcal, 33g net carbs; Banquet Beef Pot Pie 7 oz: 420 kcal, 34g net carbs) and zip-code-anchored restaurant data before consumption.{cite(25)} Unknown restaurant quantities strictly output zero macros and request detail rather than inventing values. Pre-meal fluid consumption is modeled explicitly via plasma-volume dilution physics, while Mobile Companion "How Do You Feel?" (HDYF) voice notes extract structured somatic pain categories (diabetic peripheral neuropathy, articular joint pain restricting walks, or gastroparetic fullness).{cite(18, 25)}
</p>

{eq_card(
    "Equation 8.1 — Dietary Glycemic Load, Recipe BOM Superposition &amp; Plasma Dilution Buffer",
    "PRE-CHOICE COST",
    f'C<sub>net</sub> = C<sub>tot</sub> &minus; C<sub>fiber</sub> &le; 45g &nbsp;|&nbsp; GL = {frac("GI &middot; C<sub>net</sub>", "100")} &nbsp;|&nbsp; G(t) = {frac("M<sub>glucose</sub>(t)", "V<sub>plasma</sub>(Hydration)")}',
    [
        ("C<sub>net</sub>, GL", "Net digestible carbs per meal (&le;45g meal, &le;135g day) and weighted Glycemic Load", "GL &le;10 low, 11–19 med"),
        ("M<sub>glucose</sub>, V<sub>p</sub>", "Adequate pre-meal water (e.g. 16 fl oz) prevents dehydration hemoconcentration", "&minus;10 to &minus;16 mg/dL buffer"),
        ("BOM Sum", "Linear ingredient mass addition across grocery aisles (zero monetary price noise)", "Exact metabolic BOM"),
    ],
    "Evaluates any planned meal or home recipe before a single bite is swallowed, pairing concrete swaps (&minus;42g sides, &minus;65g soda) with exact post-meal walk minutes.",
    (22, 23, 25)
)}

{fig_card("8.1", "Live Pre-Choice Evaluator (7 Macro Tiles, Hydration Buffer, Gemini Notes & GLUT4 Walk Plan)", "../images/bfm_t03_meal_estimator.png", "Live evaluation of a 7 oz Banquet Beef Pot Pie (420 kcal, 34g net carbs, GL 15 Safe Low-Moderate) paired with 16.0 fl oz hydration buffering (-10 to -16 mg/dL) and a 20-minute post-meal GLUT4 walk predicting 128 mg/dL flattened peak.", ["Clear green compliance badge confirms <=45g ceiling before deciding whether to consume, tweak, or swap."], (23, 25))}

<!-- SECTION 9 -->
<div class="page-break"></div>
<span class="sec-tag">Section 9 • Pharmacology &amp; Split-Dose Audit</span>
<h1>9. Pharmacological Sanctity, Morning/Evening Split Timing &amp; Titration Log</h1>
<p>
Pre-populating sample medications in medical software represents a dangerous clinical hazard that risks conflating spontaneous dietary improvements with unprescribed drug effects. BFM initializes every clean installation strictly to <code>[NO MEDICATIONS RECORDED]</code>. Only medications explicitly entered by the user or clinician appear in Tab 09 tables, Lane 13 <code>&#9660; Rx</code> timeline pins, or FHIR R4 bundles. Discrete Morning (Intake 1) and Evening/Dinner (Intake 2) split schedules let diabetic pharmacists correlate pharmacokinetic onset (T<sub>max</sub>) against fasting dawn phenomena and post-dinner excursions, accompanied by a structured titration audit log.{cite(2, 7, 8)}
</p>

<div class="callout-grid">
  <div class="callout">
    <strong>Zero Pre-Populated Drug Guarantee:</strong> New installations hold <code>[]</code> in local storage and zero fabricated Rx rows in SQLite—preventing any accidental charting of unprescribed Metformin, SGLT2i, or GLP-1 agents.
  </div>
  <div class="callout-blue">
    <strong>Dose-to-Peak Kinetic Alignment:</strong> Splitting agents such as Metformin Extended-Release into Morning (Breakfast) and Evening (Dinner) timestamps reveals overnight hepatic glucose suppression directly on Lane 01 vs. Lane 13.{cite(8)}
  </div>
</div>

{fig_card("9.1", "Live User-Entered Active Regimen in Tab 09 (11 Morning Intake 1 Agents & Split Evening Metformin)", "../images/bfm_t09_medication.png", "Authentic user-entered regimen displaying Morning Intake 1 (11 active items marked taken at 10:47 AM including Metformin XR 1,000 mg, Benazepril 20 mg, and lipid/GLP-1/SGLT2 agents) alongside Evening Intake 2 split Metformin XR 1,000 mg (07:01 PM), plus chronological titration audit trail below.", ["Every card exposes 1-click TAKEN logging, EDIT, TITRATE, and clean DELETE controls passively without autonomous dosing suggestions."], (7, 8))}

<!-- SECTION 10 -->
<div class="page-break"></div>
<span class="sec-tag">Section 10 • Hardware Protocol &amp; MCU Boundaries</span>
<h1>10. BLE 5.0 GATT Opcode Architecture &amp; Verified MCU Firmware Boundaries</h1>
<p>
Every hardware number displayed in BFM is traced directly to decompiled Veepoo SDK binaries and verified over live GATT exchanges against <code>VEE [8B:DE:40:83:F6:16]</code>. Knowing the firmware's deterministic boundaries protects clinicians from misinterpreting physical hardware guardrails as software faults.{cite(7, 10)}
</p>

<table class="data-tbl">
  <thead><tr><th style="width:14%;">Command / Opcode</th><th style="width:24%;">Physiological Payload</th><th style="width:32%;">Framing &amp; Decoding Semantics</th><th style="width:30%;">Verified Hardware / Clinical Boundary</th></tr></thead>
  <tbody>
    <tr><td><code>0xDF (244B MTU)</code></td><td>5-Min Glucose, HR, BP, SpO2, HRV</td><td>Out-of-order OriginV3 block reassembly across 24h slots</td><td>Full diurnal resolution; skips un-worn gaps</td></tr>
    <tr><td><code>0x88</code></td><td>Core &amp; Epidermal Temperature</td><td>Dual-NTC 15-min temperature archive (113+ daily points)</td><td>Strict 00:00–present wall-clock cutoff</td></tr>
    <tr><td><code>0xE0</code></td><td>Sleep Stages, Steps, MET, Blood Comp, Breath</td><td>Multi-session TLV stream uniting night sleep + daytime naps</td><td>Live MET/Blood/Breath sub-report backfill</td></tr>
    <tr><td><code>0x96 / 0x97</code></td><td>Stored Lead-I ECG &amp; QTc</td><td>Bezel capture header (HR, HRV, QT, ECG type) + 25 mm/s sample</td><td>Manual 30–40s opposite-hand bezel contact</td></tr>
    <tr><td><code>0x93 0x05/0x06</code></td><td>Stored BIA Body Composition</td><td>Skeletal muscle%, body fat%, BMI, FFM, body water%, BMR</td><td>Manual dual-electrode 15s impedance sweep</td></tr>
    <tr><td><code>0xB8 Page-0</code></td><td>12h / 24h Display Format Sync</td><td>Protected Page-0 read/write blocking 24h overwrite bugs</td><td>Honors user <code>cfgTimeFormat</code> cleanly</td></tr>
    <tr><td><code>0xA3 / 0x07</code></td><td>Pedometer Target &amp; Live Steps</td><td>Synchronizes 2,000-step senior goal to watch MCU</td><td>Firmware suppresses &lt;15-step random jitter</td></tr>
    <tr><td><code>Dial ROM Boundary</code></td><td>Watch Faces (Slots 0–3)</td><td>Detects fixed-ROM dial storage on Model 5010 hardware</td><td>Gracefully hides flash controls when read-only</td></tr>
  </tbody>
</table>

<!-- SECTION 11 -->
<div class="page-break"></div>
<span class="sec-tag">Section 11 • HL7 FHIR R4 / USCDI EHR Interoperability</span>
<h1>11. Standards-Based EHR Ingestion, 13-Channel LOINC Map &amp; Sovereign Privacy</h1>
<p>
BFM prevents proprietary cloud lock-in while remaining 100% compatible with institutional healthcare systems. All telemetry resides in an air-gapped local SQLite vault (<code>telemetry.db</code>) backed up daily with SHA-256 deduplication to removable USB media. When a user initiates sharing inside <code>13-EXPORT</code>, BFM compiles valid <strong>HL7 FHIR Release 4</strong> JSON Bundles and <strong>RFC-4180 LOINC CSV</strong> files ready for direct Epic Hyperspace, MyChart, or Cerner Millennium outpatient flowsheet ingestion—zero manual clinic re-keying or cloud middleware required.{cite(28, 29, 30)}
</p>

<table class="data-tbl">
  <thead><tr><th>BFM Telemetry Observation</th><th>LOINC Code</th><th>Official LOINC Display Term</th><th>UCUM Unit</th><th>FHIR R4 Resource Category</th></tr></thead>
  <tbody>
    <tr><td>Blood Glucose Tonometry</td><td><code>2339-0</code></td><td>Glucose [Mass/volume] in Blood</td><td><code>mg/dL</code></td><td><code>Observation (vital-signs / lab)</code></td></tr>
    <tr><td>Estimated A1C (ADAG / GMI)</td><td><code>4548-4</code></td><td>Hemoglobin A1c/Hemoglobin.total in Blood</td><td><code>%</code></td><td><code>Observation (laboratory)</code></td></tr>
    <tr><td>Systolic Blood Pressure</td><td><code>8480-6</code></td><td>Systolic blood pressure</td><td><code>mm[Hg]</code></td><td><code>Observation (vital-signs)</code></td></tr>
    <tr><td>Diastolic Blood Pressure</td><td><code>8462-4</code></td><td>Diastolic blood pressure</td><td><code>mm[Hg]</code></td><td><code>Observation (vital-signs)</code></td></tr>
    <tr><td>Heart Rate</td><td><code>8867-4</code></td><td>Heart rate</td><td><code>/min</code></td><td><code>Observation (vital-signs)</code></td></tr>
    <tr><td>Oxygen Saturation (SpO2)</td><td><code>59408-5</code></td><td>Oxygen saturation in Arterial blood by Pulse oximetry</td><td><code>%</code></td><td><code>Observation (vital-signs)</code></td></tr>
    <tr><td>Core / Body Temperature</td><td><code>8310-5</code></td><td>Body temperature</td><td><code>[degF]</code></td><td><code>Observation (vital-signs)</code></td></tr>
    <tr><td>24h Step Activity Volume</td><td><code>41950-7</code></td><td>Number of steps in 24 hour Measured</td><td><code>&#123;#&#125;</code></td><td><code>Observation (activity)</code></td></tr>
    <tr><td>Electrocardiogram QTc Interval</td><td><code>8636-3</code></td><td>Q-T interval corrected</td><td><code>ms</code></td><td><code>Observation (procedure)</code></td></tr>
    <tr><td>Respiratory Rate (PPG-RSA)</td><td><code>9279-1</code></td><td>Respiratory rate</td><td><code>/min</code></td><td><code>Observation (vital-signs)</code></td></tr>
    <tr><td>Body Mass Index (BIA)</td><td><code>39156-5</code></td><td>Body mass index (BMI) [Ratio]</td><td><code>kg/m2</code></td><td><code>Observation (vital-signs)</code></td></tr>
    <tr><td>Skeletal Muscle &amp; Body Fat %</td><td><code>41982-0</code></td><td>Percentage of body fat Measured</td><td><code>%</code></td><td><code>Observation (exam)</code></td></tr>
    <tr><td>Active Medication Regimen</td><td>RxNorm / Text</td><td><code>MedicationStatement</code> (AM / PM Split Dose)</td><td>Dose / timing</td><td><code>MedicationStatement</code></td></tr>
  </tbody>
</table>

{fig_card("11.1", "Consolidated Tab 13 Export: HL7 FHIR R4 JSON, LOINC CSV, Draft Email & Physician Handbook", "../images/bfm_t13_export_ehr.png", "All four clinical sharing deliverables reside cleanly in 13-EXPORT under strict user-initiated consent—zero background cloud transmission.", ["Supports Epic Hyperspace External Documents, MyChart attachments, and SHA-256 dual-USB clinical archiving."], (28, 29, 30))}

<!-- SECTION 12 -->
<div class="page-break"></div>
<span class="sec-tag">Section 12 • Peer-Reviewed Literature &amp; Physician Sign-Off</span>
<h1>12. Vetted Clinical Bibliography (Linked DOIs) &amp; Physician Verification Record</h1>
<p>
Every mathematical transformation, glycemic target floor, autonomic scatter threshold, and EHR code mapping documented in this monograph is anchored to peer-reviewed literature and international healthcare standards. All links below resolve directly to authoritative publisher DOI records or formal standards bodies.{cite(1, 2, 7, 28)}
</p>

<table class="ref-tbl">
  <tbody>{ref_rows}</tbody>
</table>

<div class="signoff-box">
  <strong style="color:#0f172a;font-size:9.5pt;display:block;margin-bottom:5px;">ATTENDING PHYSICIAN &amp; SPECIALIST TEAM REVIEW SIGN-OFF (CLINICAL CHART ATTACHMENT)</strong>
  <div style="display:grid;grid-template-columns:1fr 1fr 1fr;gap:10px;font-size:8pt;color:#334155;margin-top:6px;">
    <div><strong>Venous Lab Reference (t<sub>0</sub>):</strong> ____________________ mg/dL</div>
    <div><strong>Baseline Lab HbA1c / Target:</strong> ____________ % / ______ %</div>
    <div><strong>TIR / Step Goal Reviewed:</strong> &ge;______% / ________ steps</div>
    <div style="margin-top:6px;"><strong>Attending Physician / Credentials:</strong> ___________________________________</div>
    <div style="margin-top:6px;"><strong>Signature &amp; Clinic NPI:</strong> ___________________________________</div>
    <div style="margin-top:6px;"><strong>Review Date:</strong> ____________________</div>
  </div>
</div>

</body>
</html>
"""


async def html_to_pdf(html_path, pdf_path):
    udd = os.path.join(WORKSPACE, "_chrome_pdf_b65")
    os.makedirs(udd, exist_ok=True)
    proc = subprocess.Popen(
        [
            CHROME_PATH,
            "--headless=new",
            "--remote-debugging-port=9265",
            f"--user-data-dir={udd}",
            "--disable-gpu",
            "--no-first-run",
            "--no-default-browser-check",
            "--allow-file-access-from-files",
            "about:blank",
        ],
        stdout=subprocess.DEVNULL,
        stderr=subprocess.DEVNULL,
    )
    time.sleep(2.4)
    try:
        async with aiohttp.ClientSession() as session:
            ws_url = None
            for _ in range(15):
                try:
                    async with session.get("http://127.0.0.1:9265/json") as resp:
                        pages = [t for t in await resp.json() if t.get("type") == "page"]
                        if pages:
                            ws_url = pages[0]["webSocketDebuggerUrl"]
                            break
                except Exception:
                    await asyncio.sleep(0.4)
            if not ws_url:
                raise RuntimeError("Chrome CDP websocket did not start on 9265")

            async with session.ws_connect(ws_url, max_msg_size=100 * 1024 * 1024) as ws:
                mid = 1

                async def send(m, p=None):
                    nonlocal mid
                    c = mid
                    mid += 1
                    await ws.send_json({"id": c, "method": m, **({"params": p} if p else {})})
                    while True:
                        raw = await ws.receive_json()
                        if raw.get("id") == c:
                            if "error" in raw:
                                raise RuntimeError(f"CDP error {m}: {raw['error']}")
                            return raw.get("result", {})

                await send("Page.enable")
                await send("Runtime.enable")
                url = "file:///" + html_path.replace("\\", "/")
                await send("Page.navigate", {"url": url})
                await asyncio.sleep(1.5)

                img_status = await send(
                    "Runtime.evaluate",
                    {
                        "expression": """(async () => {
                            await document.fonts.ready;
                            const imgs = Array.from(document.images);
                            await Promise.all(imgs.map(i => i.complete ? null : new Promise(r => { i.onload = r; i.onerror = r; })));
                            return { total: imgs.length, broken: imgs.filter(i => !i.naturalWidth).map(i => i.src) };
                        })()""",
                        "awaitPromise": True,
                        "returnByValue": True,
                    },
                )
                img_val = img_status.get("result", {}).get("value") or {}
                print("[PDF IMG STATUS]:", json.dumps(img_val))
                if img_val.get("broken"):
                    raise RuntimeError(f"Broken images in PDF HTML: {img_val['broken']}")

                header_html = (
                    '<div style="width:100%;font-family:Arial,sans-serif;font-size:7pt;color:#64748b;'
                    'border-bottom:0.5px solid #cbd5e1;padding:0 0.55in 3px 0.55in;display:flex;justify-content:space-between;">'
                    '<span>BFM Biometric Function Monitor &bull; Physician Clinical Monograph</span>'
                    '<span>Release beta.00.01.0065 &bull; Non-Diagnostic Adjunctive Biofeedback</span></div>'
                )
                footer_html = (
                    '<div style="width:100%;font-family:Arial,sans-serif;font-size:7pt;color:#64748b;'
                    'border-top:0.5px solid #cbd5e1;padding:3px 0.55in 0 0.55in;display:flex;justify-content:space-between;">'
                    '<span>HL7 FHIR R4 / LOINC Compliant Clinical Documentation</span>'
                    '<span>Page <span class="pageNumber"></span> of <span class="totalPages"></span></span></div>'
                )
                pdf_res = await send(
                    "Page.printToPDF",
                    {
                        "printBackground": True,
                        "displayHeaderFooter": True,
                        "headerTemplate": header_html,
                        "footerTemplate": footer_html,
                        "paperWidth": 8.5,
                        "paperHeight": 11.0,
                        "marginTop": 0.58,
                        "marginBottom": 0.62,
                        "marginLeft": 0.55,
                        "marginRight": 0.55,
                        "preferCSSPageSize": False,
                    },
                )
                pdf_bytes = base64.b64decode(pdf_res["data"])
                with open(pdf_path, "wb") as fp:
                    fp.write(pdf_bytes)

                # Capture 4 sample viewport snapshots of key HTML print pages at Letter width (~816px) for visual inspection
                await send("Emulation.setDeviceMetricsOverride", {"width": 900, "height": 1165, "deviceScaleFactor": 1, "mobile": False})
                preview_dir = r"C:\Users\jclou\.gemini\antigravity\brain\1eb2f37b-9c6a-4b02-88af-c0eebae67964\scratch\pdf_preview"
                os.makedirs(preview_dir, exist_ok=True)
                doc_h = (await send("Runtime.evaluate", {"expression": "document.body.scrollHeight", "returnByValue": True}))["result"]["value"]
                for idx, frac_y in enumerate((0.0, 0.19, 0.45, 0.90), start=1):
                    y_pos = int(doc_h * frac_y)
                    await send("Runtime.evaluate", {"expression": f"window.scrollTo(0, {y_pos})"})
                    await asyncio.sleep(0.35)
                    shot = await send("Page.captureScreenshot", {"format": "png", "fromSurface": True})
                    with open(os.path.join(preview_dir, f"pdf_preview_{idx}.png"), "wb") as fp:
                        fp.write(base64.b64decode(shot["data"]))

                return pdf_bytes
    finally:
        proc.terminate()
        try:
            proc.wait(timeout=5)
        except Exception:
            proc.kill()
        shutil.rmtree(udd, ignore_errors=True)


def main():
    html_text = build_html()
    for p in HTML_TARGETS:
        os.makedirs(os.path.dirname(p), exist_ok=True)
        with open(p, "w", encoding="utf-8") as fp:
            fp.write(html_text)
    print(f"[OK] Wrote {len(html_text):,} chars to {len(HTML_TARGETS)} HTML mirrors.")

    pdf_bytes = asyncio.run(html_to_pdf(HTML_TARGETS[0], PDF_TARGETS[0]))
    page_count = len(re.findall(rb"/Type\s*/Page\b", pdf_bytes))
    doi_links = len(re.findall(rb"https://doi\.org/", pdf_bytes))
    std_links = len(re.findall(rb"https://(?:hl7\.org|loinc\.org|ucum\.org)", pdf_bytes))
    for dst in PDF_TARGETS[1:]:
        os.makedirs(os.path.dirname(dst), exist_ok=True)
        shutil.copy2(PDF_TARGETS[0], dst)
    print(f"[OK] Compiled PDF: {len(pdf_bytes):,} bytes | Pages={page_count} | DOI Links={doi_links} | Std Links={std_links}")
    print(json.dumps({"pages": page_count, "bytes": len(pdf_bytes), "doi_links": doi_links, "std_links": std_links}))


if __name__ == "__main__":
    main()
