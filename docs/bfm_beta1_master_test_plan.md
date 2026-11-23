# BFM Biometric Function Monitor: Master Test Plan
## Build Under Test: `beta.00.01.0001` • Six Sigma & SEI-CMMI Level 5 Quality Assurance
**System Architecture:** Star Ship Gemini NCC-202X • Shuttlecraft Foodoor (Cell 1)  
**Document Classification:** Enterprise Quality Management & Human-Centric Testing Protocol  

---

### 1. Prime Philosophy & Human-Centric Mission Charter

The primary goal of testing the **BFM Biometric Function Monitor** (Console Powered By Gemini) is to **ensure that the system creates demonstrable, daily success for users and their attending doctors in managing diabetes, diet, and physical activity**.

#### Core Testing Principles:
1. **Beyond Six Sigma & CMMI Level 5**: While mathematical Six Sigma (<= 3.4 Defects Per Million Opportunities) and SEI-CMMI Level 5 process rigor are strictly enforced, they are insufficient on their own. The true measure of quality is **Human-Centric Success**:
   * **User Success**: The user genuinely likes BFM, understands their circadian glycemic curves, relies on it daily, and achieves stable glucose control.
   * **Doctor Success**: The user's physicians trust the high-density ambulatory data, review the generated Epic FHIR R4 reports, and actively utilize BFM telemetry in clinical consultations.
2. **"We The People" Advocacy**: Every test procedure is designed to validate that Google Gemini functions as a compassionate, transparent, and sovereign health advocate for everyday people.
3. **Total Quality Management (TQM) & Closed-Loop Engineering**: Real-world feedback and test findings feed directly into root cause analysis, preventing defects before release.

---

### 2. Two-Pass Testing Methodology

Testing build `beta.00.01.0001` is executed in two strictly decoupled phases:

| Testing Tier | Verification Method | Execution Environment | Tools & Harnesses | Pass / Fail Criteria |
| :--- | :--- | :--- | :--- | :--- |
| **Pass 1: Gemini Automated Validation** | Headless automated compilation, AST parsing, schema checks, and REST dispatch | Local Node CL3 (`127.0.0.1:8899`) | `audit_zero_defect.py`<br>`strict_gate_v2.py`<br>`test_beta1_e2e.py` | 100% Pass (0 errors, 0 warnings, 0 defects) |
| **Pass 2: Human-in-the-Loop Validation** | Human operator manual UI/UX execution starting by double-clicking `BFM.HTM` | Windows 11 Desktop + Chrome/Edge | Windows Explorer<br>`BFM.HTM`<br>`cell.html`<br>CLEBugger (`:8999`) | All 14 tab bays nominal; 0 blocking defects; user likes UX |

---

### 3. Pass 1: Gemini Automated Pre-Flight Suite

Before any human testing begins, the automated suite must certify the build with zero defects:

1. **Check 1: Zero Defect Visual Cleanliness (`audit_zero_defect.py`)**:
   * Evaluates all documentation, presentations, and display files across 8 strict checks.
   * Asserts 0 broken image links, 0 fragile SVG embeds, 0 LaTeX math tokens, 0 ASCII box-art, and 0 raw flowcharts.
2. **Check 2: Ten-Point Six Sigma Pre-Commit Gate (`strict_gate_v2.py`)**:
   * Bit-for-bit SHA-256 parity between root and app.
   * HTML DOM tag balance (Opens == Closes == 1,995, Diff == 0).
   * JavaScript AST compilation via Node.js `--check`.
   * Python AST bytecode compilation (`server.py`).
   * SQLite deduplication & confectionery safety truth tables.
   * Zero monetary price residue in Tab 09 nutrition BOM.
   * Mobile client tag balance (Opens == Closes == 61, Diff == 0).
   * Multi-tenant 3-Way Triad schema and DDL constraints.
   * Epic FHIR R4 JSON export schema verification.
   * Canonical version string `beta.00.01.0001` verification.
3. **Check 3: Full Lifecycle Integration Suite (`test_beta1_e2e.py`)**:
   * Live HTTP handshake with port 8899.
   * Multi-tenant family roster retrieval (`/api/shuttle/roster`).
   * Mobile provisioning handshake (`/api/shuttle/provision`).
   * Mobile registration handshake (`/api/shuttle/register`).
   * Offline meal batch docking (`/api/shuttle/dock`).
   * Automated dual USB/SD backup export (`/api/backup/dual_export`).

---

### 4. Pass 2: Human Manual Test Protocol (Step-by-Step)

The human tester executes the following sequential protocol, beginning by locating the single-file entry point:

#### Step 1: Launch via `BFM.HTM`
1. Navigate to `D:\veepoo` (or `D:\jveepoo`) in Windows Explorer.
2. Locate the file **`BFM.HTM`** and double-click it.
3. Verify the browser launches and loads the console without error popups or broken layouts.

#### Step 2: Critical Clinical Notice & Terms Acceptance (Startup Modal)
1. Verify `#modalLegalStartup` appears on first boot, blocking workspace interaction.
2. Verify clinical notice: Non-invasive PPG trend monitor only; not for Type 1; contraindicated for rapid insulin dosing.
3. Verify Terms Version displays **`BFM-BETA1-2026`**.
4. Click **"✓ I ACCEPT AND UNDERSTAND ALL CLINICAL NOTICES"**.
5. Verify modal dismisses smoothly, sets `bfm_legal_accepted` in `localStorage`, and commits audit record to database.

#### Step 3: Header Branding & Canonical Version Verification
1. Verify top left transponder displays: **`NCC-202X`**.
2. Verify main header title displays: **`BFM CONSOLE • STAR SHIP GEMINI NCC-202X`**.
3. Verify subtitle displays: **`WEARABLE SENSOR ARRAY • MOBILE AUXILIARY SHUTTLECRAFT FOODOOR • POWERED BY GEMINI`**.
4. Verify canonical build string displays: **`beta.00.01.0001`**.
5. Click **"THEME: LIGHT"** pill: verify instant transition to Dark mode and back to Light mode with zero text distortion.

#### Step 4: Workspace Navigation (14 Tab Bays)
Click each tab in the left navigation sidebar in sequence and verify clean rendering:
* **01-FULL SCAN**: Real-time telemetry ribbon, 6-sensor status, and live transponder gauge.
* **02-MEAL PLANNER**: Pre-choice nutrition estimator, USDA pantry library, and glycemic load predictor.
* **03-METABOLIC**: 24-hour circadian timelines, ADAG eA1C engine, and GLUT4 post-walk recovery trajectories.
* **04-REPORTS**: Multi-day glycemic summary tables, time-in-range (TIR) percentages, and physician consultation export.
* **05-EXPORT**: 1-click Epic FHIR R4 JSON export, RFC-4180 CSV generation, and USB thumb drive backup.
* **06-LEARNING**: Metabolic science tutorials, Amadori rearrangement explanations, and carbohydrate absorption kinetics.
* **07-HELP & FAQ**: Hardware pairing guides, PPG metrology explanations, and optical cleaning instructions.
* **08-MEDICATION**: Pharmacological regimen schedules, oral hypoglycemic tracking, and meal split logging.
* **09-CLINICAL GOALS**: Target eA1C range sliders, glycemic velocity limits, and daily step thresholds.
* **10-CONFIG**: User biometric profile, residential zip code, physician EHR setup, and **Section 12 Family Cells Roster**.
* **11-OPERATIONS**: Engineering diagnostic registers, BLE packet sniffers, and system performance telemetry.
* **12-DATA LOG**: Raw telemetry record table with real-time filtering and timestamp search.
* **13-WATCH**: Wearable face gallery, display customization, and private mode baseline offset (`0x89 0x02`).

#### Step 5: Section 12 Registered Family Cells & Watch Roster (Tab 10 Bottom)
1. Navigate to **10-CONFIG** and scroll to bottom (Section 12).
2. Verify roster table lists active units:
   * **Cell 1**: Callsign "John", Registry "NCC-202X-1", Phone "Pixel 9 Pro (Shuttlecraft Foodoor)", Status "ACTIVE".
   * **Cell 2**: Callsign "Mary", Registry "NCC-202X-2", Phone "Samsung Galaxy S24 Ultra", Status "ACTIVE".
3. Click **"➕ PROVISION NEW FAMILY CELL"**:
   * Enter Cell ID: `Cell 3`.
   * Enter Callsign: `Sarah`.
   * Enter Registry: `NCC-202X-3`.
   * Enter Watch MAC: `D4:33:A2:11:00:88`.
   * Click **"⚡ PROVISION CELL NOW"**.
   * Verify provisioned token is issued and new card appears in the roster table.
4. Click **"⚡ TEST SHUTTLECRAFT DOCKING HANDSHAKE"**:
   * Verify success alert confirming simulated bulk meal transfer.

#### Step 6: Mobile Auxiliary Scout Testing (`cell.html`)
1. Open a new browser tab and navigate to `http://127.0.0.1:8899/cell`.
2. Verify header displays **"📱 SHUTTLECRAFT FOODOOR • AUXILIARY SCOUT"** and local ESN.
3. Enter meal: `Grilled Chicken Breast with Steamed Broccoli`.
4. Click meal occasion button: **"🥪 LUNCH"**.
5. Click **"🚀 SUBMIT TO PC NOW"**:
   * If connected to Wi-Fi, verify immediate commit to PC database.
   * If simulated offline, verify item queues in phone memory with badge indicator.
   * Click **"⚡ SYNC ALL STAGED MEALS TO PC"**: verify atomic bulk docking transfers all queued records.


#### Step 6B: Subjective Experiential Telemetry ("How Do You Feel?" HDYF)
1. On `cell.html` (or workstation console), locate the **"💭 HOW DO YOU FEEL?"** inquiry button.
2. Enter subjective response via voice or text (e.g., *"Energetic, satisfied after lunch, zero brain fog"*).
3. Select feeling category tags: `Energy: High`, `Clarity: Clear`, `Mood: Positive`.
4. Click **"Submit Assessment"**: verify timestamped entry is staged in mobile memory.
5. Perform Homecoming Dock: verify the subjective comment is synchronized to `server.py` and attached to the contemporaneous glucose reading in `telemetry.db`.
6. Inspect Tab 03 / Tab 04: verify subjective feeling marker appears on the 24-hour timeline.

#### Step 7: Dual External USB/SD Storage Backup Testing
1. On Tab 10 bottom or Tab 05, click **"💾 TRIGGER DUAL USB/SD EXPORT NOW"**.
2. Verify backup summary dialog confirms generation of:
   * `telemetry_export_YYYY-MM-DD.sqlite` (Database snapshot)
   * `EPIC_FHIR_R4_TELEMETRY_PATIENT-MRN-948201_YYYY-MM-DD.json` (US-Core FHIR collection)
   * `telemetry_export_YYYY-MM-DD.sha256` (Integrity manifest)
3. Inspect `D:\veepoo\w11_htm_app\backups\` (or USB target): verify both files and manifest exist with non-zero bytes.

---

### 5. Defect Logging & CLEBugger Integration (Port 8999)

Every defect, UI inconsistency, or feature enhancement identified during automated or manual testing must be formally logged in **CLEBugger**:

* **CLEBugger URL:** `http://127.0.0.1:8999/`
* **Defect Classification Standard**:
  * **Critical Defect (P1)**: Prevents workstation launch, causes unhandled script crash, leaks data across user UIDs, or corrupts database.
  * **Major Defect (P2)**: Feature malfunction in one of the 14 bays, docking sync failure, or FHIR schema discrepancy.
  * **Minor Defect (P3)**: Visual misalignment, layout artifact, or minor copy typo.
  * **Change Request (CR)**: Feature improvement suggested to enhance daily user or physician adoption.

#### Logging Procedure:
1. Open `http://127.0.0.1:8999/` in browser.
2. Click **"New Bug / Issue"**.
3. Set Project: `BFM_NCC202X`.
4. Set Build: `beta.00.01.0001`.
5. Enter Title, Reproduction Steps, Expected vs. Actual Behavior, and attach screenshot/log excerpt.
6. Submit ticket: CLEBugger assigns a tracking ID (`CLE-XXX`).

---

### 6. Six Sigma Quality & SEI-CMMI Level 5 Assessment

#### Six Sigma Metric Calculation:
```text
Total Opportunities for Defect (OFD) = 50,000
Observed Defects (D) = [Total Pass 1 + Pass 2 Defects]
Defects Per Million Opportunities (DPMO) = (D / OFD) * 1,000,000

Target: DPMO <= 3.4 (Six Sigma Standard)
```

#### CMMI Level 5 Closed-Loop Feedback:
For every defect logged:
1. Perform Root Cause Analysis (RCA) categorizing failure mode (DOM, AST, Schema, Timing, Protocol).
2. Engineer a self-optimizing automated regression gate in `strict_gate_v2.py`.
3. Commit resolution trajectory to session `.jsonl` history.
4. Verify defect is 100% eliminated before build promotion.

---

### 7. Human-Centric Success Verification Form

| Success Parameter | Evaluation Question | Verification Standard | Human Tester Sign-Off |
| :--- | :--- | :--- | :---: |
| **Patient Daily Liking** | Does the tester find the interface intuitive, elegant, and empowering? | Affirmative (Rating >= 9/10) | [ ] PASS |
| **Nutritional Clarity** | Is the pre-choice meal planner fast enough to evaluate food before eating? | Latency < 2.5 seconds | [ ] PASS |
| **Circadian Insight** | Does the 24-hour glycemic timeline clearly reveal post-meal glucose spikes? | Immediate visual clarity | [ ] PASS |
| **Doctor Utility** | Would an endocrinologist trust the generated FHIR R4 report and eA1C curve? | Conforms to ADA standards | [ ] PASS |
| **Zero Frustration** | Was the software free of confusing errors, broken links, or freezes? | 100% zero unhandled errors | [ ] PASS |
