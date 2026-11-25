# BFM Biometric Function Monitor: Master Test Plan
## Build Under Test: `beta.00.01.0002` • Six Sigma & SEI-CMMI Level 5 Quality Assurance
**System Architecture:** Star Ship Gemini NCC-202X • Desktop Console & Home Wi-Fi Companion Portal  
**Document Classification:** Enterprise Quality Management & Human-Centric Testing Protocol  
**Pioneer, Inventor & Philosophical Leader:** The Author (Creator, Chief Architect, and Inventor)  
**Corporate & Technology Sponsor:** Google (Sponsoring, Hosting, and Powering via Google Gemini AI)  

---

### 1. Prime Philosophy & Human-Centric Mission Charter

The foundational goal of testing the **BFM Biometric Function Monitor** (Console Powered By Gemini) is to **ensure that the system creates demonstrable, daily success for users and their attending doctors in managing diabetes, diet, and physical activity**.

#### Core Testing Principles:
1. **Beyond Six Sigma & CMMI Level 5**: While mathematical Six Sigma (<= 3.4 Defects Per Million Opportunities) and SEI-CMMI Level 5 process rigor are strictly enforced, they are insufficient on their own. The true measure of quality is **Human-Centric Success**:
   * **User Success**: The user genuinely likes BFM, understands their circadian glycemic curves, relies on it daily, and achieves stable glucose control.
   * **Doctor Success**: The user's physicians trust the high-density ambulatory data, review the generated Epic FHIR R4 reports, and actively utilize BFM telemetry in clinical consultations.
2. **"We The People" Advocacy**: Every test procedure validates that Google Gemini functions as a compassionate, transparent, and sovereign health advocate for everyday people.
3. **Total Quality Management (TQM) & Closed-Loop Engineering**: Real-world field test findings feed directly into root cause analysis, permanently resolving defect patterns before subsequent releases.
4. **Pure Zero-Data Reality Principle**: In clinical medical software, mock data is hazardous. If data does not exist, the test asserts that charts and gauges display **0**, journals display empty notices, and patient identifiers remain blank until configured.

---

### 2. Two-Pass Testing Methodology

Testing is executed in two strictly decoupled phases:

| Testing Tier | Verification Method | Execution Environment | Tools & Harnesses | Pass / Fail Criteria |
| :--- | :--- | :--- | :--- | :--- |
| **Pass 1: Gemini Automated Validation** | Headless automated compilation, AST parsing, schema checks, and REST dispatch | Local Node CL3 (`127.0.0.1:8899`) | `audit_zero_defect.py`<br>`strict_gate_v2.py`<br>`test_beta1_e2e.py` | 100% Pass (0 errors, 0 warnings, 0 defects) |
| **Pass 2: Human-in-the-Loop Validation** | Human operator manual UI/UX execution starting by double-clicking `BFM.HTM` | Windows 11 Desktop + Chrome/Edge | Windows Explorer<br>`BFM.HTM`<br>CLEBugger (`:8999`) | All 14 tab bays nominal; 0 blocking defects; user likes UX |

---

### 3. Pass 1: Gemini Automated Pre-Flight Suite

Before any human testing begins, the automated suite must certify the build with zero defects:

1. **Check 1: Zero Defect Visual Cleanliness (`audit_zero_defect.py`)**:
   * Evaluates all documentation, presentations, and display files across 8 strict checks.
   * Asserts 0 broken image links, 0 fragile SVG embeds, 0 LaTeX math tokens, 0 ASCII box-art, and 0 raw flowcharts.
2. **Check 2: Ten-Point Six Sigma Pre-Commit Gate (`strict_gate_v2.py`)**:
   * Bit-for-bit SHA-256 parity between root `BFM.HTM`, `index.html`, and `w11_htm_app/index.html`.
   * HTML DOM tag balance (Opens == Closes == 1,995, Diff == 0).
   * JavaScript AST compilation via Node.js `--check`.
   * Python AST bytecode compilation (`server.py`).
   * SQLite deduplication & confectionery safety truth tables.
   * Zero monetary price residue in nutrition headers.
   * Multi-tenant database schema integrity.
   * Mobile companion `cell.html` DOM balance.
   * FHIR R4 JSON schema export template validation.
3. **Check 3: Dual-Origin API Connectivity (`test_beta1_e2e.py`)**:
   * Validates dynamic endpoint resolution: `window.location.protocol === 'file:' ? 'http://127.0.0.1:8899' : ''`.
   * Asserts zero failed fetch calls when launched directly from the local file system.

---

### 4. Pass 2: Human Manual Test Protocol (Step-by-Step)

The human tester executes the following sequential protocol, beginning by locating the single-file entry point:

#### Step 1: Launch via `BFM.HTM` & Origin Verification
1. Navigate to `D:\veepoo` in Windows Explorer.
2. Locate the file **`BFM.HTM`** and double-click it.
3. Verify the browser launches under `file:///D:/veepoo/BFM.HTM` and loads without script errors or broken layouts.

#### Step 2: Critical Clinical Notice & Terms Acceptance (Startup Modal)
1. Verify `#modalLegalStartup` appears on first boot, blocking workspace interaction.
2. Verify clinical notice: Non-invasive PPG trend monitor only; personal self-help biofeedback tool.
3. Verify Terms Version displays **`BFM-BETA1-2026`**.
4. Click **"✓ I ACCEPT AND UNDERSTAND ALL CLINICAL NOTICES"**.
5. Verify modal dismisses smoothly, sets `bfm_legal_accepted` in `localStorage`, and commits audit record to database.

#### Step 3: Abrams Science Station Visual Aesthetic Verification
1. Verify application boots **by default into Light Theme** (Scott / Abrams Science Station Standard).
2. Canvas background is clean luminous slate (`#f8fafc`); cards are crisp white (`#ffffff`) with subtle 1px borders (`#cbd5e1`).
3. Primary telemetry accents render in Starfleet science ice-blue (`#0284c7`), cobalt (`#1d4ed8`), and cyan (`#06b6d4`).
4. Zero 1990s clunky pill bars; high-contrast medical cards with monospaced tabular numerals.

#### Step 4: Header Strip & Comm Link Status Check
1. Verify top header bar displays **single consolidated battery & Bluetooth monitor** in the status ribbon.
2. Verify smaller duplicate battery and BT icons have been completely removed from the top strip.
3. Verify Comm Link State ribbon displays **`Connected`** (in luminous emerald) when watch transponder is linked, never displaying `STANDBY`.

#### Step 5: Pure Zero-Data Reality Baseline Check
1. Before watch scan, verify all telemetry tiles (`tile360Glucose`, `tile360Bp`, `tile360Spo2`, `tile360Temp`, `tile360HrHrv`) display **`0`** or **`--`** with `(NO DATA)` notice.
2. Verify 24-hour diurnal charts plot **0** across all intervals, and all future hours beyond current wall-clock time are set strictly to 0 or null.
3. Verify Chronological Nutrition & Meal Journal displays: `[NO NUTRITION LOGGED — JOURNAL EMPTY]`.
4. Verify Medication Management table displays: `[NO ACTIVE MEDICATIONS RECORDED]`.
5. Verify Patient Chart Number / MRN field displays: `[UNASSIGNED]`. Zero synthetic MRN strings.

#### Step 6: Watch Connection, Full Scan & Real-Time Metabolic Ingestion
1. Click **`⚡ SYNC WATCH NOW`** on the status ribbon.
2. Watch transponder links via Web Bluetooth; full 24-hour telemetry dump initiates.
3. Verify that upon scan completion, **Bay 03 (Metabolic Console) immediately updates** with live downloaded glucose, blood pressure, temperature, and SpO2 readings.
4. Verify that the charts refresh with real physical watch data with zero staleness.

#### Step 7: HBand 2.0 Full Sensor Equivalence Verification
1. Verify native display of continuous optical glucose, blood pressure, resting heart rate, HRV/stress, and SpO2.
2. Verify dedicated presentation of **raw ECG rhythm strip canvas** (25 mm/sec sweep, PR/QT intervals).
3. Verify **MET energy expenditure gauge** (active vs. resting metabolic rates).
4. Verify **multi-stage sleep architecture hypnogram** (deep, light, REM, awake cycles).

#### Step 8: Meal Planner, Exact Estimation & Immediate Journal Commitment
1. Navigate to **02-MEAL PLANNER**.
2. Enter: *"Taco Time Northwest: Fiesta Platter, seasoned beef, sour cream, and 32 oz diet soda"*.
3. Click **`ESTIMATE NUTRITION (GEMINI)`**.
4. Verify estimated nutritional return matches published disclosures: **520 kcal, 33g net carbs, 28g protein, 28g fat, 32 oz fluid**, and zero monetary price strings.
5. Click **`🚀 CONSUME NOW (LOG TO MEAL LOG)`**.
6. Navigate to Chronological Nutrition & Meal Journal: verify meal is immediately appended to today's log and today's total net carbs update in real time.

#### Step 9: "How Do You Feel?" (HDYF) Somatosensory Pain & Discomfort Logging
1. Open the companion portal on local Wi-Fi (`http://<PC_IP>:8899/cell.html`).
2. Respond to the daily prompt: *"How do you feel right now?"*.
3. Voice or type a somatosensory complaint: *"Burning pain in toes tonight, knees stiff after walking."*
4. Verify statement is committed to `patient_experiential_journal` with exact timestamp.
5. On Desktop BFM Console, verify complaint is parsed into categories: **Neuropathic Burning (Rank 7/10)** and **Articular Stiffness (Rank 5/10)**.
6. Verify marker appears anchored directly over the correlated glucose and HRV telemetry on the master timeline.

#### Step 10: Single Consolidated EHR Data Export (Bay 05)
1. Navigate to **05-EXPORT** (`05-EHR DATA EXPORT`).
2. Verify all export triggers (Epic FHIR R4 JSON, LOINC CSV, SQLite snapshot, dual backup) are centralized in Bay 05.
3. Verify that duplicate export buttons have been eliminated from Tabs 01, 04, and 12.
4. Enter patient chart number (or leave unassigned); click **`1-CLICK GENERATE EPIC FHIR R4 JSON`**.
5. Verify downloaded file validates against HL7 FHIR R4 schema and contains zero hardcoded synthetic MRN strings.

---

### 5. Defect Logging & CLEBugger Integration (Port 8999)

Every anomaly, UI defect, or functional failure discovered during Pass 2 manual testing must be logged directly into the CLEBugger daemon running on port 8999:

* **Endpoint**: `http://127.0.0.1:8999/api/bugs`
* **Defect Classification**:
  - **Sev 1 (Critical Safety / Data Loss)**: Corrupted medical data, fake mock prescriptions, failed hardware communication.
  - **Sev 2 (Functional / Visual Anomaly)**: Stale chart rendering, theme clipping, incorrect nutritional calculation.
  - **Sev 3 (Cosmetic / Usability)**: Button alignment, label wording, minor layout padding.
* **Closed-Loop Resolution**: A build cannot achieve Six Sigma certification until all logged Sev 1 and Sev 2 defects have been resolved, re-tested, and verified with zero regression.

---

### 6. Human-Centric Success Verification Form

| Human Success Criterion | Operational Verification | Result (Pass / Fail) |
| :--- | :--- | :--- |
| **User Joy & Usability** | Did the user find the Abrams Science Station UI elegant, intuitive, and easy to read? | Evaluated in Pass 2 |
| **Zero Data Panic** | Did the user experience complete trust in the data (zero fake meds, zero fake meals, zero fake MRN)? | Evaluated in Pass 2 |
| **Nutritional Confidence** | Did the Meal Planner return exact, reliable nutritional estimates without price clutter? | Evaluated in Pass 2 |
| **Physiological Insight** | Did the watch scan immediately update the Metabolic console with real-time biometric readings? | Evaluated in Pass 2 |
| **Holistic Expression** | Did the HDYF portal accurately capture subjective pain, discomfort, and feelings onto the timeline? | Evaluated in Pass 2 |
| **Physician Trust** | Can the attending doctor open Bay 05, download the Epic FHIR R4 bundle, and review clean LOINC telemetry? | Evaluated in Pass 2 |
