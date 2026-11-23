# build_presentation_clean.py - Module 1 (Slides 1 to 5)
curriculum = [
    # MODULE 1: INTRODUCTION & CLINICAL SCOPE (1-5)
    {
        "id": 1, "num": "01",
        "module": "Module 1: Introduction & Clinical Scope",
        "title": "Welcome to LCARS 5010 Clinical Ambulatory Telemetry",
        "desc": "Overview of the LCARS 5010 Clinical Telemetry Console for the Veepoo Model 5010 biosensing array.",
        "narration": "G'day mate! Welcome to the LCARS 5010 Clinical Telemetry Masterclass. Today, we're taking you inside the complete engineering and physiological suite behind the Veepoo Model 5010 biosensing array. We built this platform to give doctors and patients a continuous, reliable window into outpatient metabolic health, completely eliminating proprietary cloud silos and delivering clinical-grade telemetry right on your local PC.",
        "card_title": "SYSTEM OVERVIEW & TRANSPONDER STATUS",
        "bullets": [
            ("Continuous Ambulatory Telemetry", "Streams 8 simultaneous physiological signals over low-energy radio directly to your PC."),
            ("Zero Cloud Dependencies", "Processes every reading locally on your computer with total patient privacy."),
            ("Doctor-Patient Partnership", "Designed specifically as a shared clinical tool to review during consultations.")
        ],
        "takeaway": "Empowers doctors and patients with 24/7 objective data to safely achieve glycemic goals.",
        "right_header": "LCARS 5010 • SYSTEM GATEWAY",
        "right_badge": "● LINKED & ONLINE",
        "right_badge_col": "#15803d",
        "right_html": """
          <div style="display:grid; grid-template-columns:1fr 1fr; gap:8px; margin-top:8px;">
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">WATCH TRANSPONDER</div>
              <div style="font-size:1.1rem; font-weight:800; color:#0f172a; font-family:monospace;">MODEL 5010 ACTIVE</div>
            </div>
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">COMMUNICATION</div>
              <div style="font-size:1.1rem; font-weight:800; color:#0284c7; font-family:monospace;">BLUETOOTH 5.0 LE</div>
            </div>
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">CALIBRATION ANCHOR</div>
              <div style="font-size:1.1rem; font-weight:800; color:#d97706; font-family:monospace;">8.3% HbA1c (191.5)</div>
            </div>
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">LOCAL VAULT SYNC</div>
              <div style="font-size:1.1rem; font-weight:800; color:#15803d; font-family:monospace;">SYNC NOMINAL</div>
            </div>
          </div>
          <div style="margin-top:10px; background:#eff6ff; border:1.5px solid #bfdbfe; border-radius:6px; padding:10px;">
            <div style="font-weight:800; color:#1e40af; font-size:0.85rem;">PATIENT MONITORING PROFILE</div>
            <div style="font-size:0.8rem; color:#1e3a8a; margin-top:4px;">Age: 68 • Protocol: Outpatient T2D Maintenance • Port: 8899 Local Air-Gapped</div>
          </div>
        """
    },
    {
        "id": 2, "num": "02",
        "module": "Module 1: Introduction & Clinical Scope",
        "title": "The Snapshot Fallacy: Why Isolated Fingersticks Fail",
        "desc": "Examining why intermittent spot-checks fail to capture metabolic kinetics, excursions, and nocturnal hypoglycemia.",
        "narration": "Let's talk about the snapshot fallacy. For decades, diabetes management has relied on intermittent fingersticks and quarterly lab work. But a single fasting blood glucose reading at 7 AM tells you nothing about the post-meal glucose spike at noon, the rapid drop during an afternoon walk, or the dangerous hypoglycemic valley while you sleep. The Model 5010 changes that by recording up to 288 discrete data points every single day.",
        "card_title": "LIMITATIONS OF CONVENTIONAL SPOT-CHECKS",
        "bullets": [
            ("Missing Nocturnal Events", "Isolated fingersticks cannot detect overnight glucose valleys occurring between 2 AM and 5 AM."),
            ("Postprandial Excursions", "Spot checks miss the peak post-meal glucose excursions that drive diabetic complications."),
            ("Statistical Significance", "288 automated daily readings provide 100 times more clinical data than occasional fingersticks.")
        ],
        "takeaway": "Continuous monitoring captures critical glycemic dynamics that single spot-checks completely miss.",
        "right_header": "THE SNAPSHOT FALLACY AUDIT",
        "right_badge": "CONTINUOUS VS SPOT-CHECK",
        "right_badge_col": "#dc2626",
        "right_html": """
          <div style="display:grid; grid-template-columns:1fr 1fr; gap:8px; margin-top:8px;">
            <div style="background:#fff1f2; border:1.5px solid #fecdd3; border-radius:4px; padding:8px;">
              <div style="color:#e11d48; font-weight:800; font-size:0.75rem;">CONVENTIONAL FINGERSTICK</div>
              <div style="font-size:1.1rem; font-weight:800; color:#9f1239; margin-top:4px; font-family:monospace;">1 TO 4 READINGS / DAY</div>
              <div style="font-size:0.7rem; color:#881337; margin-top:4px;">Completely misses 95% of daytime spikes and nocturnal drops.</div>
            </div>
            <div style="background:#f0fdf4; border:1.5px solid #bbf7d0; border-radius:4px; padding:8px;">
              <div style="color:#16a34a; font-weight:800; font-size:0.75rem;">MODEL 5010 CONTINUOUS</div>
              <div style="font-size:1.1rem; font-weight:800; color:#14532d; margin-top:4px; font-family:monospace;">288 READINGS / 24 HRS</div>
              <div style="font-size:0.7rem; color:#14532d; margin-top:4px;">Captures continuous kinetics every 5 minutes around the clock.</div>
            </div>
          </div>
          <div style="margin-top:10px; background:#f8fafc; border:1px solid #e2e8f0; border-radius:4px; padding:8px; font-family:monospace; font-size:0.75rem; color:#475569;">
            Diagnostic Gain: +284 clinical observations per 24-hour cycle.
          </div>
        """
    },
    {
        "id": 3, "num": "03",
        "module": "Module 1: Introduction & Clinical Scope",
        "title": "The FITAOS 5150 / Veepoo Model 5010 Smartwatch & Biosensing Features",
        "desc": "Hardware architecture, physical design, and 5-sensor biosensing array of the FITAOS 5150 (Veepoo Model 5010) smartwatch.",
        "narration": "On screen is the physical FITAOS 5150 smartwatch, powered by the Veepoo Model 5010 architecture. On the left is the watch hardware itself. It features a crisp 1.39-inch high-definition touch display with a tactile rotary crown, multi-wavelength optical sensors for non-invasive glucose tonometry, dual-contact thermistors for core and skin temperature, and dual ECG electrodes for blood pressure and cardiac rhythm. With a week of continuous battery life and magnetic charging, it captures clinical data 24 hours a day without interruptions.",
        "card_title": "FITAOS 5150 / MODEL 5010 SMARTWATCH HARDWARE",
        "bullets": [
            ("1.39-inch HD Display & Alloy Chassis", "Vibrant, high-contrast touch screen with physical rotary crown for senior-friendly tactile navigation."),
            ("Optical Biosensing Array", "Green, Red, and Infrared photoplethysmography sensors calculate non-invasive continuous glucose, SpO2, and heart rate."),
            ("Dual Thermistors & ECG Electrodes", "Two contact thermistors distinguish core vs skin temperature; dual electrodes compute PWTT blood pressure."),
            ("Magnetic Rapid Charging & 7-Day Battery", "Efficient power management delivers a full week of continuous ambulatory telemetry on a single charge.")
        ],
        "takeaway": "An accessible, clinical-grade multisensor wearable capturing 24/7 continuous vitals right on your wrist.",
        "right_header": "FITAOS 5150 • HARDWARE & SENSOR MATRIX",
        "right_badge": "5 INTEGRATED SENSORS",
        "right_badge_col": "#0284c7",
        "right_html": """
          <div style="display:grid; grid-template-columns:1fr 1fr; gap:8px; margin-top:8px;">
            <div style="background:#f8fafc; border:1px solid #cbd5e1; border-radius:4px; padding:8px;">
              <div style="color:#0284c7; font-weight:800; font-size:0.78rem;">1. OPTICAL PPG ARRAY</div>
              <div style="font-size:0.72rem; color:#475569; margin-top:2px;">Triple-wavelength Non-invasive Glucose & Arterial SpO2</div>
            </div>
            <div style="background:#f8fafc; border:1px solid #cbd5e1; border-radius:4px; padding:8px;">
              <div style="color:#dc2626; font-weight:800; font-size:0.78rem;">2. DUAL THERMISTORS</div>
              <div style="font-size:0.72rem; color:#475569; margin-top:2px;">Core vs Cutaneous Skin Temp Differential (°F)</div>
            </div>
            <div style="background:#f8fafc; border:1px solid #cbd5e1; border-radius:4px; padding:8px;">
              <div style="color:#15803d; font-weight:800; font-size:0.78rem;">3. PWTT HEMODYNAMICS</div>
              <div style="font-size:0.72rem; color:#475569; margin-top:2px;">Pulse Wave Transit Time for Ambulatory Blood Pressure</div>
            </div>
            <div style="background:#f8fafc; border:1px solid #cbd5e1; border-radius:4px; padding:8px;">
              <div style="color:#7c3aed; font-weight:800; font-size:0.78rem;">4. DUAL ECG ELECTRODES</div>
              <div style="font-size:0.72rem; color:#475569; margin-top:2px;">Body-contact lead points capture cardiac rhythm & HRV</div>
            </div>
          </div>
          <div style="margin-top:10px; background:#eff6ff; border:1px solid #bfdbfe; border-radius:4px; padding:8px;">
            <div style="display:flex; justify-content:space-between; font-weight:800; font-size:0.78rem; color:#1e40af;">
              <span>FIRMWARE REVISION</span>
              <span>BLUETOOTH 5.0 LE</span>
            </div>
            <div style="font-size:0.72rem; color:#1e3a8a; margin-top:2px;">
              Firmware V0.16.16.0 • Circular Flash Storage • Low-power Nordic nRF52 series SoC • Magnetic 2-pin charging
            </div>
          </div>
        """
    },
    {
        "id": 4, "num": "04",
        "module": "Module 1: Introduction & Clinical Scope",
        "title": "Strict Zero-Egress Local Telemetry vs Cloud Silos",
        "desc": "Understanding why patient data sovereignty requires an air-gapped, local-first PC architecture.",
        "narration": "Data privacy is a non-negotiable medical right. Most consumer wearables force your health metrics through overseas cloud servers, where your data is commoditized or held hostage behind monthly subscriptions. LCARS 5010 is completely air-gapped. Your raw vitals travel directly from the watch into your local PC's browser sandbox and SQLite database. Zero bytes leave your machine without your explicit authorization.",
        "card_title": "LOCAL-FIRST AIR-GAPPED DATA ARCHITECTURE",
        "bullets": [
            ("Zero Cloud Egress", "100% of telemetry data remains strictly on your local PC with zero external transmissions."),
            ("No Subscription Paywalls", "Free and open platform with no monthly fees, account lock-ins, or cloud requirements."),
            ("Total Data Sovereignty", "You and your physician own and control all clinical data in standard open formats.")
        ],
        "takeaway": "Protects patient medical privacy through a completely local, air-gapped architecture.",
        "right_header": "ZERO-EGRESS SECURITY MATRIX",
        "right_badge": "AIR-GAPPED SYSTEM",
        "right_badge_col": "#15803d",
        "right_html": """
          <div style="display:grid; grid-template-columns:1fr 1fr; gap:8px; margin-top:8px;">
            <div style="background:#f0fdf4; border:1.5px solid #bbf7d0; border-radius:4px; padding:8px;">
              <div style="color:#16a34a; font-weight:800; font-size:0.75rem;">AIR-GAPPED LOCAL PC</div>
              <div style="font-size:1rem; font-weight:800; color:#14532d; font-family:monospace;">LOCALHOST:8899</div>
              <div style="font-size:0.7rem; color:#14532d; margin-top:4px;">SQLite + LocalStorage with zero cloud egress.</div>
            </div>
            <div style="background:#fff1f2; border:1.5px solid #fecdd3; border-radius:4px; padding:8px;">
              <div style="color:#e11d48; font-weight:800; font-size:0.75rem;">COMMERCIAL CLOUDS</div>
              <div style="font-size:1rem; font-weight:800; color:#9f1239; font-family:monospace;">BLOCKED & DISABLED</div>
              <div style="font-size:0.7rem; color:#881337; margin-top:4px;">Zero third-party trackers, subscriptions, or telemetry ads.</div>
            </div>
          </div>
          <div style="margin-top:10px; background:#f8fafc; border:1px solid #e2e8f0; border-radius:4px; padding:8px; font-family:monospace; font-size:0.75rem; color:#15803d; font-weight:bold;">
            Data Policy: 100% Patient Sovereignty • HIPAA-Compliant Air Gap
          </div>
        """
    },
    {
        "id": 5, "num": "05",
        "module": "Module 1: Introduction & Clinical Scope",
        "title": "Core Clinical Targets: Achieving HbA1c <6.8% and TIR >70%",
        "desc": "The primary clinical objectives of the LCARS 5010 continuous monitoring protocol.",
        "narration": "What gets measured gets managed. The clinical goal of this entire masterclass is simple: help Type 2 diabetic patients achieve an HbA1c below 6.8% and maintain Time-in-Range above 70%, all while strictly avoiding hypoglycemia below 60 mg/dL. LCARS 5010 provides the biofeedback engine to make that goal an everyday reality.",
        "card_title": "ADA CLINICAL TARGET BENCHMARKS",
        "bullets": [
            ("Target HbA1c < 6.8%", "Lowering glycosylated hemoglobin below 6.8% significantly reduces macrovascular complications."),
            ("Time-in-Range > 70%", "Keeping glucose between 60 and 200 mg/dL for more than 70% of the day provides metabolic stability."),
            ("Hypoglycemia Prevention", "Zero tolerance for readings below 60 mg/dL protects against neuroglycopenic events.")
        ],
        "takeaway": "Delivers real-time biofeedback to maintain Time-in-Range above 70% and lower HbA1c safely.",
        "right_header": "ADA CLINICAL TARGET BENCHMARKS",
        "right_badge": "OUTPATIENT OBJECTIVES",
        "right_badge_col": "#0284c7",
        "right_html": """
          <div style="display:grid; grid-template-columns:1fr 1fr; gap:8px; margin-top:8px;">
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">PRIMARY METRIC</div>
              <div style="font-size:1.1rem; font-weight:800; color:#15803d; font-family:monospace;">HbA1c &lt; 6.8%</div>
            </div>
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">TIME-IN-RANGE (TIR)</div>
              <div style="font-size:1.1rem; font-weight:800; color:#0284c7; font-family:monospace;">&gt; 70.0%</div>
            </div>
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">GLYCEMIC HARBOR</div>
              <div style="font-size:1.1rem; font-weight:800; color:#d97706; font-family:monospace;">60 - 200 MG/DL</div>
            </div>
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">HYPOGLYCEMIA LIMIT</div>
              <div style="font-size:1.1rem; font-weight:800; color:#dc2626; font-family:monospace;">&lt; 60 MG/DL (0%)</div>
            </div>
          </div>
          <div style="margin-top:10px; background:#eff6ff; border:1px solid #bfdbfe; border-radius:4px; padding:8px; font-size:0.75rem; color:#1e3a8a;">
            Consensus: ADA/EASD 2024 Standards of Care for Continuous Telemetry.
          </div>
        """
    }
]
