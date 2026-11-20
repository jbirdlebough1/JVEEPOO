# Script to build complete clean curriculum without hex bytes and with matching left/right panels

part2_slides = [
    # MODULE 2: PHYSICIAN-PATIENT ALIGNMENT & CALIBRATION (6-10)
    {
        "id": 6, "num": "06",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "title": "Physician-Patient Collaborative Care Partnership",
        "desc": "Structuring the LCARS console as a shared clinical interface between the attending physician and patient.",
        "narration": "Module 2 focuses on collaboration. LCARS is not just a patient gadget; it is a shared clinical interface designed for you and your physician. Instead of guessing how your lifestyle affects your diabetes, your doctor can review hard ambulatory data, evaluate medication response, and collaboratively adjust your therapy during clinical visits.",
        "card_title": "PHYSICIAN & PATIENT COLLABORATIVE PARTNERSHIP",
        "bullets": [
            ("Shared Clinical Portal", "Both doctor and patient review the exact same synchronized longitudinal graphs."),
            ("Objective Consultation Reviews", "Replaces subjective recall with 24-hour continuous biometric data."),
            ("Evidence-Based Titration", "Every prescription adjustment is grounded in direct ambulatory biofeedback.")
        ],
        "takeaway": "Transforms outpatient consultations from guesswork into a data-driven clinical partnership.",
        "right_header": "PHYSICIAN COLLABORATIVE INTERFACE",
        "right_badge": "SHARED CLINICAL PORTAL",
        "right_badge_col": "#0284c7",
        "right_html": """
          <div style="margin-top:10px; background:#f0f9ff; border:1.5px solid #bae6fd; border-radius:6px; padding:12px;">
            <div style="font-size:1.15rem; font-weight:800; color:#0369a1; font-family:monospace;">COLLABORATIVE CARE ACTIVE (ATTENDING MD + PATIENT)</div>
            <div style="font-size:0.84rem; color:#075985; margin-top:6px; line-height:1.45;">Dual-view portal enabling shared review of 24-hour glycemic excursions, exercise kinetics, and medication response.</div>
          </div>
          <div style="margin-top:10px; display:grid; grid-template-columns:1fr 1fr; gap:6px; font-family:monospace; font-size:0.75rem;">
            <div style="background:#f8fafc; padding:6px; border-radius:3px; border:1px solid #e2e8f0;">PATIENT VIEW: ACTIVE</div>
            <div style="background:#f8fafc; padding:6px; border-radius:3px; border:1px solid #e2e8f0;">MD AUDIT: READY</div>
          </div>
        """
    },
    {
        "id": 7, "num": "07",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "title": "Clinical Glycemic Band: Standardizing on 60–200 mg/dL",
        "desc": "Why establishing a clinical floor of 60 mg/dL and ceiling of 200 mg/dL prevents false alarms and protects renal health.",
        "narration": "Slide 7 establishes our clinical safe harbor. We standardize on a target band of 60 to 200 milligrams per deciliter. The 60 floor protects against acute hypoglycemia, while the 200 ceiling aligns with the renal threshold where excess glucose spills into urine. This clinical band provides clear guardrails without triggering constant alarm fatigue.",
        "card_title": "SAFE HARBOR GLYCEMIC BAND: 60 - 200 MG/DL",
        "bullets": [
            ("60 mg/dL Clinical Floor", "Prevents severe neuroglycopenic hypoglycemia with prompt alert thresholds."),
            ("200 mg/dL Renal Ceiling", "Caps postprandial excursions before kidney glucose excretion occurs."),
            ("Homeostasis Target Band", "Optimizes Time-in-Range within a realistic, achievable ambulatory corridor.")
        ],
        "takeaway": "Clinically calibrated guardrails that protect against lows while preventing alarm fatigue.",
        "right_header": "CLINICAL GLYCEMIC HARBOR",
        "right_badge": "60 – 200 MG/DL SAFE BAND",
        "right_badge_col": "#059669",
        "right_html": """
          <div style="margin-top:10px; display:flex; flex-direction:column; gap:8px;">
            <div style="display:flex; justify-content:space-between; align-items:center; background:#fee2e2; border-left:4px solid #dc2626; padding:8px 12px; border-radius:4px;">
              <span style="font-size:0.82rem; font-weight:bold; color:#991b1b;">Clinical Floor (Hypoglycemia Guard):</span>
              <span style="font-family:monospace; font-size:0.9rem; color:#dc2626; font-weight:800;">60 MG/DL</span>
            </div>
            <div style="display:flex; justify-content:space-between; align-items:center; background:#f0fdf4; border-left:4px solid #16a34a; padding:8px 12px; border-radius:4px;">
              <span style="font-size:0.82rem; font-weight:bold; color:#166534;">Target Homeostasis Band:</span>
              <span style="font-family:monospace; font-size:0.9rem; color:#15803d; font-weight:800;">60 – 200 MG/DL</span>
            </div>
            <div style="display:flex; justify-content:space-between; align-items:center; background:#fef3c7; border-left:4px solid #d97706; padding:8px 12px; border-radius:4px;">
              <span style="font-size:0.82rem; font-weight:bold; color:#92400e;">Clinical Ceiling (Renal Threshold):</span>
              <span style="font-family:monospace; font-size:0.9rem; color:#d97706; font-weight:800;">200 MG/DL</span>
            </div>
          </div>
        """
    },
    {
        "id": 8, "num": "08",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "title": "In-Clinic Venous Blood Draw Calibration",
        "desc": "How physician laboratory blood draws anchor continuous optical tonometry to verified clinical chemistry.",
        "narration": "Slide 8 details in-clinic calibration. Non-invasive optical sensors must be anchored to clinical truth. Whenever you have a certified lab blood draw, your doctor enters that exact lab value into LCARS. The system calculates the optical offset and writes the calibration directly to the watch hardware, ensuring future readings remain grounded in clinical reality.",
        "card_title": "IN-CLINIC VENOUS BLOOD DRAW CALIBRATION",
        "bullets": [
            ("Certified Lab Calibration", "Directly links optical skin reflectance to certified venous laboratory chemistry."),
            ("Persistent Anchor Registry", "Stores clinical calibration values into watch non-volatile hardware memory."),
            ("8.3% HbA1c Calibration Baseline", "Establishes a firm 191.5 mg/dL baseline anchor for all subsequent measurements.")
        ],
        "takeaway": "Grounds non-invasive wearable sensors in certified laboratory clinical truth.",
        "right_header": "VENOUS CALIBRATION REGISTRY",
        "right_badge": "LAB CALIBRATED ANCHOR",
        "right_badge_col": "#d97706",
        "right_html": """
          <div style="margin-top:10px; background:#fffbeb; border:1.5px solid #fde68a; border-radius:6px; padding:12px;">
            <div style="font-size:1.15rem; font-weight:800; color:#92400e; font-family:monospace;">LAB BASELINE: 8.3% HbA1c (191.5 MG/DL)</div>
            <div style="font-size:0.84rem; color:#78350f; margin-top:6px; line-height:1.45;">Laboratory venous blood draw calibration locked into transponder memory, synchronizing optical reflectance to verified clinical chemistry.</div>
          </div>
          <div style="margin-top:10px; display:flex; justify-content:space-between; font-family:monospace; font-size:0.75rem; color:#475569; background:#f8fafc; padding:6px 10px; border-radius:4px;">
            <span>CALIBRATION STATUS:</span>
            <span style="color:#15803d; font-weight:bold;">VERIFIED & ACTIVE</span>
          </div>
        """
    },
    {
        "id": 9, "num": "09",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "title": "Standardizing Strictly on Private Mode Telemetry",
        "desc": "Why generic uncalibrated public mode fails and how private mode unlocks personalized physiological accuracy.",
        "narration": "In Slide 9, we enforce Private Mode. The watch firmware has two modes: a generic public mode that uses default population assumptions, and Private Mode, which applies your personalized calibration curve. For diabetic maintenance, public mode is strictly avoided. LCARS locks the watch into Private Mode so all glucose curves reflect your individual physiology.",
        "card_title": "STANDARDIZING ON PRIVATE CALIBRATED MODE",
        "bullets": [
            ("Personalized Vascular Profile", "Private Mode incorporates patient skin tone, tissue density, and microvasculature."),
            ("Elimination of Generic Offsets", "Disables generic factory approximations that introduce significant errors."),
            ("Enforced Firmware Lock", "LCARS automatically commands Private Mode during every synchronization cycle.")
        ],
        "takeaway": "Locks your watch into a personalized physiological profile for true diabetic accuracy.",
        "right_header": "TRANSPONDER OPERATING MODE",
        "right_badge": "PRIVATE MODE LOCKED",
        "right_badge_col": "#15803d",
        "right_html": """
          <div style="margin-top:8px; display:flex; flex-direction:column; gap:8px;">
            <div style="display:flex; justify-content:space-between; align-items:center; background:#dcfce7; border-left:4px solid #16a34a; padding:8px 12px; border-radius:4px;">
              <span style="font-size:0.82rem; font-weight:bold; color:#166534;">Private Calibrated Mode:</span>
              <span style="font-family:monospace; font-size:0.85rem; color:#15803d; font-weight:800;">ACTIVE (ENFORCED)</span>
            </div>
            <div style="display:flex; justify-content:space-between; align-items:center; background:#fee2e2; border-left:4px solid #dc2626; padding:8px 12px; border-radius:4px;">
              <span style="font-size:0.82rem; font-weight:bold; color:#991b1b;">Generic Public Mode:</span>
              <span style="font-family:monospace; font-size:0.85rem; color:#dc2626; font-weight:800;">DISABLED (BLOCKED)</span>
            </div>
          </div>
          <div style="margin-top:10px; font-size:0.78rem; color:#475569; background:#f8fafc; padding:8px; border-radius:4px;">
            ✓ Ensures all glucose readings utilize patient-specific optical calibration coefficients.
          </div>
        """
    },
    {
        "id": 10, "num": "10",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "title": "Continuous Ambulatory Background Acquisition",
        "desc": "How 288 daily epochs stream seamlessly in the background without requiring user intervention.",
        "narration": "Slide 10 details background acquisition. The Model 5010 samples vitals automatically every five minutes, yielding 288 epochs every 24 hours. When you're near your PC, it streams in real-time. When you're away or asleep, the watch stores readings in internal flash and uploads them automatically upon reconnection. No manual logging required.",
        "card_title": "24/7 AMBULATORY BACKGROUND ACQUISITION",
        "bullets": [
            ("5-Minute Sampling Frequency", "Automatically samples all 8 vitals every 5 minutes around the clock."),
            ("Onboard Storage Buffer", "Internal flash memory stores weeks of data when away from your PC."),
            ("Zero-Effort Background Sync", "Reconnecting automatically downloads overnight and away-from-home records.")
        ],
        "takeaway": "Effortless, continuous data capture that works quietly in the background 24/7.",
        "right_header": "AMBULATORY ACQUISITION ENGINE",
        "right_badge": "288 EPOCHS / 24H",
        "right_badge_col": "#0284c7",
        "right_html": """
          <div style="display:grid; grid-template-columns:1fr 1fr; gap:8px; margin-top:8px;">
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">SAMPLING FREQUENCY</div>
              <div style="font-size:1.15rem; font-weight:800; color:#0284c7; font-family:monospace;">5-MINUTE EPOCHS</div>
            </div>
            <div style="background:#f8fafc; padding:8px; border-radius:4px; border:1px solid #e2e8f0;">
              <div style="font-size:0.7rem; color:#64748b; font-weight:bold;">DAILY DATA POINTS</div>
              <div style="font-size:1.15rem; font-weight:800; color:#15803d; font-family:monospace;">288 PER SENSOR</div>
            </div>
          </div>
          <div style="margin-top:10px; background:#f1f5f9; padding:8px 10px; border-radius:4px; font-size:0.78rem; color:#334155; font-family:monospace;">
            Buffer: Circular SPI NOR Flash • Zero Packet Loss Architecture
          </div>
        """
    }
]

print("Defined Part 2 slides (6-10)...")
