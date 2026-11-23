# BFM 5010 Diabetes Platform: Requirements & Architectural Specification
## Universal Global Edition: Serving 800M+ Type 2 Diabetics Worldwide

---

### 1. Document Control & Mission Charter
* **Project Name**: BFM 5010 Clinical Ambulatory Workstation & Mobile Intake Ecosystem
* **Mothership**: `USS Gemini (NCC-5010-G)` (Desktop / Shared Family Workstation)
* **Away-Mission Scout**: `Shuttlecraft 01: Foodoor` (Mobile Pre-Choice Intake Estimator)
* **Clinical Heritage**: Informed by **Physio-Control LIFEPAK (100–300 Series) & LifeLine** emergency telemetry principles—delivering continuous physiological data to paramedics and triage physicians to save lives.
* **Global Target**: 800+ Million Individuals Living with Type 2 Diabetes Worldwide
* **Guiding Principle**: Free, zero-egress, clinical-grade diabetic empowerment powered by Google Gemini, demonstrating AI as a benevolent force for human health and life extension.

---

### 2. Multi-User Shared PC Family Architecture

In millions of households worldwide, multiple family members share a single desktop or laptop computer. The platform provides absolute data isolation, customized clinical targets, and dedicated cell-to-user pairing.

```
                  ┌──────────────────────────────────────────────────┐
                  │           USS GEMINI FAMILY WORKSTATION          │
                  │              (Shared Desktop / PC)               │
                  └────────────────────────┬─────────────────────────┘
                                           │
                        [ User Profile Selection Ribbon ]
                                           │
                 ┌─────────────────────────┴─────────────────────────┐
                 ▼                                                   ▼
       [PROFILE 01: JOHN (DAD)]                            [PROFILE 02: MARY (MOM)]
       • Target A1c: < 6.5%                                • Target A1c: < 7.0%
       • Isolated Vault: telemetry_john.db                 • Isolated Vault: telemetry_mary.db
       • Paired BLE Watch: MAC 8B:DE:40:...                • Paired BLE Watch: MAC C4:12:9A:...
       • Paired Shuttlecraft: Cell-01 (John's Pixel)       • Paired Shuttlecraft: Cell-02 (Mary's Galaxy)
                 ▲                                                   ▲
                 │ (Docking Sync)                                    │ (Docking Sync)
                 │                                                   │
       [SHUTTLECRAFT 01: FOODOOR]                          [SHUTTLECRAFT 02: FOODOOR-B]
       Away-Mission Device: John's Phone                   Away-Mission Device: Mary's Phone
```

#### Key Technical Requirements:
1. **Isolated Multi-Tenant Databases**:
   * Each family member has a dedicated SQLite-WASM / IndexedDB vault namespace (`telemetry_user_<uuid>.db`).
   * No data leakage or cross-contamination between family members' glucose histories, medication schedules, or phlebotomy calibration registers.
2. **Deterministic Cell-to-User Pairing**:
   * Each mobile device running *Shuttlecraft Foodoor* carries a cryptographic User UUID.
   * When docking via local Wi-Fi, QR scan, or cloud sync, *USS Gemini* routes the incoming away-mission meals and sensor scans directly into the corresponding user's vault without requiring manual file routing.
3. **1-Click Profile Switching**:
   * The top BFM status bar features an immediate Profile Switcher (`[👤 John (Active)] [👤 Mary] [➕ Add Family Member]`).
   * Switching profiles instantly swaps timelines, meal pantries, prescription regimens, and daily goal compliance.

---

### 3. PC Workstation "Install Shuttlecraft Cell App" Deployment Hub

The **USS Gemini** workstation serves as the local distribution base station, allowing any family member to provision their mobile phone in seconds with zero app-store gatekeepers.

```
[USS Gemini PC Console: Tab 05 / Settings]
           │
  [ 📱 INSTALL SHUTTLECRAFT FOODOOR ON PHONE ]
           │
           ▼ Generates Dynamic Setup Card:
  ┌──────────────────────────────────────────────────────────┐
  │  1. Scan QR Code with Phone Camera                      │
  │     (Routes to http://<PC-LAN-IP>:8899/cell)             │
  │  2. Auto-Binds Active User: [ John Doe (MRN-948201) ]   │
  │  3. Phone prompts: "Add Foodoor to Home Screen" (PWA)    │
  └──────────────────────────────────────────────────────────┘
```

#### Provisioning Features:
* **Zero App Store Downloads**: Employs Progressive Web App (PWA) standard; opens immediately in mobile Safari or Chrome and installs a native home-screen app icon.
* **Auto-Provisioned User Token**: The QR code embeds the user's isolated profile token, automatically establishing the docking link back to the mothership.

---

### 4. Zero-PHI Architecture: Food Intake Public Commodity vs. Zero Medical Egress

A core architectural tenet is that **patient medical data NEVER touches the cell phone or internet**. 

```
┌────────────────────────────────────────────────────────────────────────────────────────┐
│                   THE 100% AIR-GAPPED MEDICAL PRIVACY ARCHITECTURE                     │
├───────────────────────────────────────────────────┬────────────────────────────────────┤
│           LOCAL CLIENT BROWSER SANDBOX            │        GOOGLE CLOUD / GEMINI       │
│                (100% PRIVATE)                     │           (ZERO PHI EGRESS)        │
├───────────────────────────────────────────────────┼────────────────────────────────────┤
│ • Continuous Glucose Readings (mg/dL)             │ ❌ ZERO ACCESS TO BIOMETRICS       │
│ • Blood Pressure, Heart Rate, SpO2 Telemetry      │ ❌ ZERO ACCESS TO MEDICAL HISTORY  │
│ • Patient Name, MRN, Age, Weight, Phlebotomy Labs │ ❌ ZERO ACCESS TO PATIENT IDENTITY │
│ • Prescribed Drug Regimen & Titrations            │ ❌ ZERO ACCESS TO MEDICATIONS      │
│ • 1-Year Local Database (SQLite-WASM / IndexedDB) │ ❌ NEVER TRANSMITTED TO SERVERS    │
├───────────────────────────────────────────────────┼────────────────────────────────────┤
│           OUTBOUND INTERNET CALLS ONLY:           │                OUTPUT:             │
│   "Banquet beef pot pie 7oz" (Food Query) ───────>│ Searches Nutrition Disclosures     │
│   (Pure public commodity food data)               │ Returns 420 kcal, 34g net carbs    │
└───────────────────────────────────────────────────┴────────────────────────────────────┘
```

* **Cell Phone Isolation**: The smartphone acts purely as a food scanner and intake scout. It contains zero health history, zero clinical numbers, and zero medical diagnoses.
* **Commodity Food Ingestion**: Food intake is public commodity data. Querying menu items or photographing groceries does not compromise patient privacy.

---

### 5. The 360° Diabetes Food Sphere: Demand Intelligence & 10-Mile Radius Sourcing

Aggregating non-PHI food queries across millions of diabetics creates a transformative economic and clinical flywheel:

```
                  ┌──────────────────────────────────────────────┐
                  │    800M+ DIABETIC FOOD SEARCH INTELLIGENCE   │
                  │   (Aggregated Anonymized Demand Insights)    │
                  └──────────────────────┬───────────────────────┘
                                         │
                 ┌───────────────────────┴───────────────────────┐
                 ▼                                               ▼
     [ FOOD INDUSTRY DEMAND ENGINE ]               [ 10-MILE RADIUS LOCAL RADAR ]
     • Retailers & Manufacturers                   • Google Diabetes News Feed
       (Costco, Conagra, Sysco)                      surfaces safe restaurants within
     • Reveals exactly what diabetics want:          10 miles of patient's location
       - Taste & satisfying mouthfeel              • Identifies local diners, grocers &
       - Crispy textures without starch              bakeries stocking compliant options
       - Verified ≤45g net carb items              • Eliminates urban & rural food deserts
```

1. **Food Manufacturer & Retailer Intelligence Portal**:
   * Manufacturers (e.g. Conagra, General Mills) and wholesale retailers (Costco, restaurant suppliers) see aggregated demand data on what diabetics actually want to purchase.
   * Promotes reformulation of products (e.g., cauliflower crusts, almond flour snacks, low-sugar marinades) that meet clinical glycemic goals while maintaining great taste and satisfying texture.
2. **Google Diabetes News: 10-Mile Local Sourcing**:
   * The platform's Diabetes News feed highlights safe food vendors, bakeries, diners, and markets within a **10-mile radius** of the user's community (e.g. Sultan 98294, Monroe, Seattle).
   * Teaches patients exactly where in their immediate neighborhood they can order compliant takeout or purchase low-carb staples.

---

### 6. Universal Accessibility: Hands-Free Voice In / Speech Out (All Platforms)

Diabetic retinopathy, peripheral neuropathy, and motor limitations require a platform that is 100% accessible hands-free across all devices.

```
       ┌─────────────────────────────────────────────────────────────┐
       │             UNIVERSAL ACCESSIBILITY INTERFACE               │
       └─────────────────────────────┬───────────────────────────────┘
                                     │
           ┌─────────────────────────┴─────────────────────────┐
           ▼                                                   ▼
   [VOICE RECOGNITION (STT)]                           [SPEECH OUTPUT (TTS)]
   • Native Web Speech API                             • Native SpeechSynthesis API
   • Hands-free meal dictation                         • Natural BFM computer voice
   • "Computer, log lunch: beef pot pie"               • Speaks live glucose & spikes aloud
   • "Computer, start 6-sensor scan"                   • Audio medication dosage alerts
   • Voice navigation between tabs 01–14               • Step & GLUT4 exercise coach
```

---

### 7. Global Community Incentives: Rewarding Human Health

* **100% Free Core Software**: No subscriptions, no paywalls, zero ad monetization.
* **Developer Incentives**: Engineers contributing drivers, localizations, or clinical algorithms receive **Free Google Gemini Max / Ultra packages** and monthly API compute credits.
* **Clinical Exposure**: Contributing physicians, endocrinologists, and CDCES educators receive verified directory status and academic research attribution.
* **Patient Recognition & Spiffs**: Diabetics who maintain Time-in-Range (&gt;80%) or achieve verified eA1c milestones earn community recognition badges and healthy lifestyle partner spiffs.

---

### 8. Final Version Milestone: Emergency Critical Dispatch & 30-Day Triage (The Physio-Control Lifepak Heritage)

*Drawing directly from the clinical heritage of **Physio-Control LIFEPAK (100–300 Series) & LifeLine** emergency defibrillator telemetry.*

```
                 [ 24/7 AMBULATORY SMARTWATCH MONITORING ]
                                     │
          Nocturnal Hypoglycemia (<54 mg/dL) or Arrhythmia Detected
                                     │
          ┌──────────────────────────┴──────────────────────────┐
          ▼                                                     ▼
  [LEVEL 1: LOCAL AWAKENING]                            [LEVEL 2: ESCALATED DISPATCH]
  • Cell or PC in BLE Range                             • Patient Unresponsive (>3 min)
  • High-Decibel Auditory Alarm                         • Auto-SMS to 911 / EMS Dispatch
  • Pulsatile Wrist Haptic Buzz                           with GPS Coordinates & Vitals
  • Awakes patient to ingest rapid rescue carb          • Urgent notification to Attending PCP
                                                                │
                                                                ▼
                                                [LEVEL 3: 30-DAY TRIAGE INGESTION]
                                                • Paramedic / EMT on-scene download
                                                • ER triage doctor accesses last 30 days
                                                • Instantly identifies root cause:
                                                  (Insulin overdose vs. Somogyi rebound
                                                   vs. missed meal vs. cardiac ischemia)
```

#### Technical Specifications for Emergency Telemetry (Production Scope):
1. **Multi-Stage Tiered Awakening Protocol**:
   * When glucose drops into the severe hypoglycemia zone ($<54\text{ mg/dL}$) or heart rate exhibits acute tachycardia/bradycardia:
   * The watch alerts nearby paired devices (Cell `Foodoor` or PC `USS Gemini`) over BLE, triggering maximum-volume piercing acoustic alarms and screen flashing to awaken the sleeping patient before cognitive impairment occurs.
2. **Automated Emergency Dispatch (EMS & Primary Care Doctor)**:
   * If the alarm is not acknowledged within 3 minutes (confirming patient unresponsiveness / diabetic coma):
   * Dispatches automated priority SMS messages to local emergency services (911/EMS) and the patient's primary care physician, conveying:
     * Patient Medical ID & known Type 2 diabetic status.
     * Exact GPS coordinates of the incident.
     * Acute physiological vitals (e.g. *"Critical Glucose: 42 mg/dL, HR: 118 bpm, unresponsive for 180 seconds"*).
3. **The 30-Day Triage Telemetry Download (The Physio-Control Model)**:
   * Arriving paramedics and ER triage doctors can immediately link to the watch or workstation via secure local BLE / QR code to download the **preceding 30 days of high-resolution continuous telemetry**.
   * Gives attending triage physicians instant diagnostic certainty:
     * Differentiates an accidental insulin stacking overdose from an unbuffered alcohol crash or Somogyi rebound.
     * Eliminates diagnostic guesswork in the Golden Hour of emergency resuscitation.
