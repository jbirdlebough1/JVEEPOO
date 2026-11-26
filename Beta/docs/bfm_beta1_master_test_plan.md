# BFM Biometric Function Monitor: Master Test Plan
## Build Under Test: `beta.00.01.0057` • Six Sigma & SEI-CMMI Level 5 Quality Assurance
**System Architecture:** BFM Biometric Function Monitor (`BFM.HTM` & `BFM_Launcher.exe`) • Desktop Console & Home Wi-Fi Companion Portal (`cell.html`)  
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
| **Pass 1: Gemini Automated Validation** | Headless automated compilation, AST parsing, schema checks, presentation audit, and REST dispatch | Local Server (`127.0.0.1:8899`) | `audit_zero_defect.py` (`21/21`)<br>`strict_gate_v2.py` (`13/13`)<br>`test_beta1_e2e.py` (`10/10`)<br>`tools/audit_presentation_full.py` (`84/84`) | 100% Pass (0 errors, 0 warnings, 0 defects, 0 OPEN CLEBUGGER items) |
| **Pass 2: Human-in-the-Loop Validation** | Human operator manual UI/UX execution via `BFM_Launcher.exe` or double-clicking `BFM.HTM` | Windows 11 Desktop + Chrome/Edge | Windows Explorer<br>`BFM.HTM` / `BFM_Launcher.exe`<br>`D:\CLEBUGGER\bugs.db` | All 14 tabs nominal; 0 blocking defects; user approves UX |

---

### 3. Pass 1: Gemini Automated Pre-Flight Suite

Before any human testing begins, the automated suite must certify the build with zero defects:

1. **Check 1: Zero Defect Visual Cleanliness & Runtime Parity (`audit_zero_defect.py` — 21 Checks)**:
   * Evaluates documentation, presentations, `BFM.HTM`, `cell.html`, and `server.py` across 21 automated checks.
   * Asserts 0 broken image links, 0 fragile SVG embeds, 0 LaTeX math tokens, 0 ASCII box-art, 0 raw flowcharts, `file:///` parity, and pure zero-data compliance.
2. **Check 2: Thirteen-Point Six Sigma Pre-Commit Gate (`strict_gate_v2.py` — 13 Checks)**:
   * Bit-for-bit SHA-256 parity between root `BFM.HTM`, `index.html`, and `w11_htm_app/index.html`.
   * HTML DOM tag balance (Opens == Closes == `1,957`, Diff == `0`) across all 14 direct-child `<main>` tabs.
   * JavaScript AST compilation via Node.js `--check` and Python AST bytecode compilation (`server.py`).
   * SQLite deduplication & confectionery safety truth tables, zero monetary cost residue, and multi-tenant 3-way triad schema integrity.
   * Fail-closed CLEBUGGER synchronization (`tools/sync_clebugger.py --check`, `0 OPEN` across all tagged Beta builds).
3. **Check 3: Dual-Origin & Telemetry E2E Suite (`test_beta1_e2e.py` — 10 Checks)**:
   * Validates dynamic endpoint resolution (`window.location.protocol === 'file:' ? 'http://127.0.0.1:8899' : ''`), Bleak arbitration (`/api/browser_active`), and zero cross-tenant pollution.
4. **Check 4: Full 84-Slide Presentation Audit (`tools/audit_presentation_full.py`)**:
   * Verifies every slide (`01`–`84`) across `presentation/`, `presentation/slides/`, and `slides/` in strict Tab 01 (`01-FULL SCAN`) -> Tab 02 (`02-METABOLIC`) -> Tab 03 (`03-MEAL PLANNER`) -> ... -> Tab 14 order, all live PNG screenshots (`> 15 KB`), custom Slide 02 90-day sampling SVG, Slide 04 collector-to-PC topology, all 36 animated 3D fly-through JPEGs, all MP3 narrations, zero programmer jargon, and clinical measurement truth.

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

#### Step 6: Watch Connection, `0xDF`/`0x88`/`0xE0`/`0xB8` Scan & Real-Time Ingestion
1. Click **`⚡ SYNC WATCH NOW`** on the status ribbon (or run tab switch / button press active hardware harvest).
2. Watch transponder links via Web Bluetooth; `0xDF` OriginV3 244B MTU 5-min history, `0x88` Core/Skin temperature history (`00:00–23:59`), and `0xE0` 24h Sleep + daytime naps ingest automatically.
3. Verify `0xB8` Page-0 `READ` guard preserves user **12-hour vs 24-hour** clock format across all Reports charts and **2,000** step goal parity (`0xA3`).
4. Verify **13-Channel 3D Multi** and **13-Lane Tall Chart** display identical signals (`CH 01` Glucose through `CH 13` Medications Rx), blue off-wrist exclusion bands during charging/shower, and event overlays (`▲ GLUT4`, `▲ Carbs`, `+oz` Fluid, `▼ Rx`).

#### Step 7: HBand 2.0 Sensor Equivalence & Model 5010 Dial Boundary
1. Verify continuous optical glucose, blood pressure, resting heart rate, genuine **`8,859`-epoch R-R Lorenz Poincaré**, SpO2, Core/Skin Temperature, and METs.
2. Verify **raw ECG rhythm strip canvas** (25 mm/sec sweep, PR/QT intervals) and **multi-stage sleep architecture hypnogram** (`Deep`, `REM`, `Light`, `Awake` + daytime naps).
3. In `11-WATCH`, verify Model 5010 non-writable dial hardware correctly hides dial-overwrite UI below `#boxDialOverwriteExp`.

#### Step 8: Meal Planner, Exact Estimation & Hydration Commitment (`03-MEAL PLANNER`)
1. Navigate to **`03-MEAL PLANNER`** (`pageDietTrades`).
2. Enter: *"Taco Time Northwest: Fiesta Platter, seasoned beef, sour cream, and 32 oz diet soda"*.
3. Click **`ESTIMATE NUTRITION (GEMINI)`** and verify exact published facts: **520 kcal, 33g net carbs, 28g protein, 28g fat, 32 oz fluid**, with zero monetary price strings.
4. Click **`🚀 CONSUME NOW (LOG TO MEAL LOG)`** and verify total calories, net carbs, and daily hydration (`fl oz`, e.g., `80.0 fl oz`) update immediately on Charts (`CH 11` & `CH 12`).

#### Step 9: "How Do You Feel?" (HDYF) Somatosensory Pain & Discomfort Logging
1. Open the companion portal on local Wi-Fi (`http://<PC_IP>:8899/cell.html`).
2. Respond to *"How do you feel right now?"* and submit a somatosensory note (e.g., *"Burning pain in toes tonight, knees stiff after walking"*).
3. Verify record persists in `feeling_entries` (`w11_htm_app/telemetry.db`) and renders directly in `CH 09: PAIN INDEX` and `CH 10: MOOD SCALE`.

#### Step 10: Single Consolidated EHR Data Export & USB Backup (`13-EXPORT`)
1. Navigate to **`13-EXPORT`** (`pageExport`).
2. Verify all export triggers (Epic FHIR R4 JSON, LOINC CSV, SQLite snapshot, deduplicated USB backup) are centralized in `13-EXPORT` without scattered buttons across other tabs.
3. Click **`1-CLICK GENERATE EPIC FHIR R4 JSON`** and verify HL7 FHIR R4 validation with zero synthetic MRNs.

---

### 5. Defect Logging & CLEBUGGER Synchronization (`D:\CLEBUGGER\bugs.db`)

Every anomaly, UI defect, or change request discovered during testing is persisted in **`D:\CLEBUGGER\bugs.db`** (`JVEEPOO` project) and audited by **`tools/sync_clebugger.py`**:

* **Canonical CLI Gate**: `python tools/sync_clebugger.py --check` (enforced by `strict_gate_v2.py` Check 12/13).
* **Defect Classification**:
  - **Sev 0 / Sev 1 (Critical Safety / Data Loss)**: Fake mock data leaks, missing sensor bands (`0xDF`/`0x88`/`0xE0`), 12h clock overwrites, or failed GATT transport.
  - **Sev 2 (Functional / Visual Anomaly)**: Chart desync between 3D Multi and Tall Chart, theme clipping, incorrect nutrition calculation.
  - **Sev 3 (Cosmetic / Usability)**: Button alignment, label wording, layout padding.
* **Zero-Open Certification**: Build release requires **`0 OPEN`** unresolved defects in `D:\CLEBUGGER\bugs.db` (`CLE-001`–`CLE-112` + all Beta builds resolved).

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
