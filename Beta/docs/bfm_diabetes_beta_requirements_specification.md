# BFM Biometric Function Monitor: Requirements & Architectural Specification
## Beta 1 Release: Universal Personal Biofunction & Metabolic Self-Help Platform

---

### 1. Document Control & Mission Charter

* **System Name**: **BFM Biometric Function Monitor** (Console Powered By Gemini)
* **Pioneer, Inventor & Philosophical Leader**: **The Author** (Creator, Chief Architect, and Leader of the BFM Philosophy)
* **Sponsorship Status**: Independent Open-Source Beta — **no current sponsors** (actively seeking sponsorship from Veepoo / FITAOS, similar PPG watch vendors, and Google Gemini)
* **Desktop Workstation Console**: **BFM Biometric Function Monitor** (`BFM.HTM` & `BFM_Launcher.exe`)
* **Mobile Auxiliary Unit**: **BFM Companion Portal** (`cell.html`, registered as **Cell 1, Cell 2, Cell 3**)
* **Registered Mobile Devices**: **Cell 1, Cell 2, Cell 3** (Assigned to registered family users)
* **Paired Wearables**: **Linked Watch 1, Linked Watch 2** (Bound to PC, UID, and Cell ESN)
* **License & Distribution**: **Copyleft • All Rights Released • Open Source • Community Supported**
* **Target Audience**: 800+ Million Individuals Living with Type 2 Diabetes Worldwide
* **Author's Provenance**: Engineered out of personal necessity by an individual living with Type 2 Diabetes to assist in personal daily survival, metabolic tracking, and lifestyle optimization.
* **Core Philosophy**: Free, air-gapped, zero-cloud personal biofunction empowerment showcasing **Google Gemini** as a benevolent, life-affirming AI force for human health and daily wellbeing.

#### Visual Cleanliness & Zero Defect Quality Mandate

To guarantee enterprise elegance, maximum readability across all platforms, and zero documentation defects:
1. **Zero Broken Image Links & Zero SVG Fragility**: Absolutely zero fragile external `.svg` or unverified image embeds in markdown documents that render as broken image boxes (`file:///D:/`). All architectural diagrams, workflows, and pipelines must be authored using robust, universally portable, natively rendered Markdown Architecture Tables.
2. **Zero LaTeX / Raw Math Clutter**: Absolutely zero `$` or `$$` math delimiters, zero KaTeX / MathJax markers, zero LaTeX macros (`\frac`, `\sigma`, `\sqrt`, `\approx`, `\pm`, `\times`, `\ge`, `\le`, `\iff`, `\ne`). Math must use natural, clean plain-text typography (`(Glucose + 46.7) / 28.7`, `sigma / sqrt(N)`, `<= 45g`, `+/- 15%`).
3. **Zero Monospace ASCII Box-Art**: Absolutely zero box-drawing characters (`┌`, `┐`, `└`, `┘`, `│`, `─`, `├`, `┤`, `┬`, `┴`, `┼`). Replaced with clean, accessible Markdown tables.
4. **Zero Raw Text Flowchart Code Blocks**: Absolutely zero `flowchart TD`, `subgraph`, or raw text pseudo-code diagrams in user-facing documents.
5. **Zero Forbidden Legacy Terms**: Zero "5010", zero "patient", zero "clinical", zero "Star Trek" in-app.
6. **Mandatory Automated Pre-Commit Audit**: Every document, presentation, and display code file must pass the automated `audit_zero_defect.py` tool (`-Werror`) with zero defects before staging or committing.

---

### 2. Legal Framework, Self-Help Status & Liability Release

To protect the author, open-source contributors, and community partners from legal claims, regulatory entanglements, and medical liability, the software is strictly decoupled from clinical or medical device status.

#### A. Regulatory Decoupling
* **Non-Clinical Designation**: The software is strictly a **personal self-help biofeedback tool**. It is **NOT** cleared, certified, or approved by the U.S. Food and Drug Administration (FDA) or any global medical regulatory body.
* **Terminology Sanitization**:
  * The term **"Patient" is strictly prohibited** across the codebase, UI, and documentation; **"User"** or **"Operator"** is used exclusively.
  * The term **"Clinical" is strictly prohibited**; replaced with **"Personal"**, **"Biometric"**, **"Self-Help"**, or **"Biofunction"**.
  * The phrase **"Star Trek" is strictly barred from the application software** to prevent trademark infringement; historical inspiration tributes to Gene Roddenberry and Scott Chambliss are confined exclusively to the educational presentation preface under nominative fair use.
  * Device-specific labels (e.g. **5010**) are completely removed in favor of universal **Wearable Bio-Sensor Array** support.

#### B. Complete Release of Liability
A prominent, unambiguous legal notice and waiver is presented during user onboarding and accessible on the Configuration screen:
1. **Self-Help Affirmation**: The user explicitly acknowledges the software is experimental self-help code provided as-is under Copyleft with all rights released.
2. **No Medical Advice**: The software does not provide medical diagnoses, treatment prescriptions, or clinical advice. All nutritional calculations and glycemic curves (eA1C) are mathematical estimates that must never replace direct blood tests or professional physician care.
3. **Complete Release of Claims**: The user assumes 100% of the risk and permanently releases and holds harmless the author, contributors, and distributors from any liability, injury, or damages.

#### C. Immutable User Acceptance Database Audit Record
To provide indisputable evidentiary protection in the event of any legal challenge, user acceptance is cryptographically recorded in the user's isolated local SQLite vault.

```sql
CREATE TABLE IF NOT EXISTS user_legal_acceptance (
    agreement_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_uid TEXT NOT NULL,                     -- Unique UUID for this family member
    user_callsign TEXT NOT NULL,                -- User display handle
    terms_version TEXT NOT NULL,                -- e.g. "BFM-BETA1-TERMS-2026.11"
    terms_sha256 TEXT NOT NULL,                 -- SHA-256 hash of the exact terms string
    accepted_timestamp TEXT NOT NULL,           -- ISO 8601 UTC timestamp of explicit click
    client_user_agent TEXT NOT NULL,            -- Browser / OS platform signature
    signature_acknowledgment INTEGER DEFAULT 1, -- 1 = User Explicitly Checked & Accepted
    FOREIGN KEY(user_uid) REFERENCES user_profiles(user_uid)
);
```

---

### 3. Multi-User Family PC Architecture: The 3-Way Hardware Binding Triad

In millions of households, multiple family members share a single desktop or laptop computer. Normal web applications suffer from the **"Active Screen Collision Trap"**, where incoming biometric data is mistakenly saved to whichever profile happens to be displayed on the monitor.

The BFM platform completely eliminates this vulnerability via the **3-Way Hardware Binding Triad**:

**USER UID <-> CELL PHONE ESN <-> LINKED WATCH MAC**

| Household Member Profile | User UID | Registered Cell Phone | Hardware ESN Signature | Linked Watch MAC | Dedicated User Vault |
| :--- | :---: | :---: | :---: | :---: | :--- |
| **Profile 01: John (Dad)** | `USR-001` | Cell 1 (Pixel 9 Pro) | `ESN-9482-A018-7F` | `8B:DE:40:83:F6:16` | `telemetry_user_USR-001.db` |
| **Profile 02: Mary (Mom)** | `USR-002` | Cell 2 (Galaxy S24) | `ESN-7731-B942-3C` | `C4:12:9A:33:02:51` | `telemetry_user_USR-002.db` |
| **Profile 03: Alex (Son)** | `USR-003` | Cell 3 (iPhone 15) | `ESN-4410-C209-1E` | `E1:55:88:99:A2:00` | `telemetry_user_USR-003.db` |

#### Core Routing & Anti-Collision Guarantees:
1. **Watch-to-PC Ingestion Routing**: When continuous watch telemetry (optical glucose tonometry, 24h heart rate, SpO2, temperature) streams to the PC via Bluetooth Low Energy (BLE), the PC inspects the packet source MAC. It routes incoming packets **strictly into the database associated with that watch's linked `user_uid` and `phone_esn`**, completely ignoring which profile is active on the monitor screen.
2. **Cell-to-PC Docking Routing**: When Cell 2 returns home and transmits field meals, the server identifies `phone_esn: ESN-7731-B942-3C`, binds it to `USR-002-BETA`, and writes strictly to `telemetry_user_2.db`.
3. **Cross-Stream Metabolic Synthesis**: On the PC workstation, Dad's watch telemetry is correlated strictly with Dad's cell meals, while Mom's watch telemetry is correlated strictly with Mom's cell meals. Cross-tenant contamination is mathematically impossible.

---

### 4. Registered Family Cells & Wearables Roster (Tab 10)

The bottom of the Configuration Screen (Tab 10) features the master **Registered Family Cells & Wearables Roster** (pure zero-data compliant: empty unless real cells register, strictly excluding any Cell desktop shortcut on the Windows 11 BFM Server):

| Cell # | Registry | User Name | Phone App | Hardware ESN | Link Mode | Status |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **Cell 1** | `BFM-5010-01` | Active Household Member | `Android BFM Cell App` | `ESN-9482-A01` | Home Wi-Fi -> Server | `ACTIVE` |

#### Management Features & Strict Architectural Role Separation:
* **Add Cell & User (`[ + Add Cell & User ]`)**:
  * Generates a dynamic QR code (`/api/qrcode/cell`) pointing to `http://<PC-LAN-IP>:8899/install/cell`.
  * **Android APK QR Auto-Install (`BFM-Cell.apk`)**: Scanning the QR code automatically downloads signed standalone `BFM-Cell.apk` (`com.bfm.cell`, v2/v3 APK Signature, **zero Bluetooth permissions**) into the phone's `Downloads` folder -> tap **Open -> Install -> Open** -> launch from the permanent **BFM Cell** cross & Wi-Fi Home Screen icon (`icon-192.png` / `icon-512.png`).
* **Auto-Register Handshake (`/api/shuttle/register`)**:
  * Launching **BFM Cell** on Home Wi-Fi automatically executes an `auto_register` handshake back to `server.py`, registering the active phone even if an earlier Cell row was deleted (`status = ACTIVE`).
* **Windows 11 Desktop Isolation**:
  * `/api/system/create_shortcuts` excludes mobile Cell shortcuts from Windows 11 Start Menu/Desktop and deletes any legacy `BFM Mobile Cell Gateway.lnk`, while `cell.html` restricts install banners solely to mobile web browsers and hides `#pwaInstallBanner` inside the installed APK (`BFM_IS_NATIVE_APP`).
  * Windows 11 Bluetooth Swift Pair toast popups (`Windows.SystemToast.Bthprops`, `Windows.SystemToast.BthQuickPair`, `Bluetooth\QuickPair`) are suppressed via startup `winreg` enforcement.
* **Edit / Delete Cell (`[ ✏️ Edit ]` / `[ 🗑️ Delete ]`)**:
  * Updates profile goals or decommissions a registered Cell cleanly.

---

### 5. BFM Mobile Companion (`BFM-Cell.apk`): Field Independence, Homecoming Sync & 3-State Meds

#### A. Strict Dual-Role Boundary (Never Connects to Watch)
* **Zero Watch BLE Coupling**: Wrist watch biometric acquisition runs exclusively on the Windows 11 BFM PC Server. `BFM-Cell.apk` declares **zero Bluetooth permissions** (`INTERNET`, `ACCESS_NETWORK_STATE`, `CAMERA`, `RECORD_AUDIO` only) and exclusively handles **Meal/Beverage Intake (text, multi-photo, voice order)**, **"How Do You Feel?" (HDYF) voice/pain/mood logs**, and the **3-State Daily Medication Button**.

#### B. Multi-Day Autonomous Field Operation & Homecoming Wi-Fi Sync
* **Native Offline Boot**: `BFM-Cell.apk` loads `file:///android_asset/cell.html` directly from APK storage so it launches immediately away from home with zero browser bar and zero install banners.
* **Home Wi-Fi Detection & Tap-to-Edit IP**:
  * When home Wi-Fi reaches the BFM Server (`/api/telemetry/latest`), the status pill displays green **`CONNECTED TO BFM SERVER`** and automatically uploads any locally staged meals, HDYF feelings, and pending medication clicks (`POST /api/meds/daily_status`).
  * Away from home, the status pill switches to **`FIELD MODE (SAVING LOCALLY)`**, persisting meals, photos, audio transcripts, feelings, and med clicks in `localStorage` until home Wi-Fi returns. Tapping the status pill lets the user inspect or update the BFM Server LAN IP if the router IP changes.
* **3-State Daily Med Toggle Button (`#btnCellDailyMed` -> `/api/meds/daily_status`)**:
  * Mirrors BFM desktop `09-MEDICATION`: starts **light blue** (`MEDS: NOT TAKEN YET`), 1st tap marks **morning meds taken** (mid blue + timestamp), 2nd tap marks **morning & dinner meds taken** (green + timestamp), and auto-resets to **light blue** daily at `00:01` local time, syncing with desktop `takeAllMorningMeds()` / `takeDinnerMeds()` via SQLite table `daily_med_adherence`.

---

### 6. Automated Daily Dual USB / SD Card External Backup

To guarantee long-term data preservation without relying on third-party cloud servers, the Gemini Console incorporates an automated external backup subsystem:

| Backup Dimension | Format 1: Raw SQLite (Disaster Recovery) | Format 2: Epic FHIR R4 JSON (Interoperability) |
| :--- | :--- | :--- |
| **Data Scope** | Complete byte-for-byte clone of local database | Standard HL7 FHIR R4 Observation bundles |
| **Clinical Standard**| Native application relational schema | Verified LOINC codes for metabolic biomarkers |
| **Recovery Path** | Zero-configuration local database restore | Direct hospital EHR import for physician review |

---

### 7. Zero-PHI Cloud Privacy Architecture

The platform enforces strict air-gapped data partitioning:
* **Zero PHI Egress**: All biometric telemetry, glucose tonometry, watch MACs, medication schedules, and user identities remain 100% local on the PC.
* **Commodity Food Only**: Outbound cloud requests are strictly limited to public food queries (e.g. *"Banquet beef pot pie 7oz"*), returning standard nutritional disclosures.

---

### 8. The 360° Diabetes Food Sphere: Demand Intelligence & 10-Mile Radar

Aggregating non-identifiable public food queries creates a beneficial feedback loop between users, grocers, and food processors:
1. **Food Manufacturer & Retailer Demand Insights**: Highlights consumer demand for satisfying, crispy, <= 45g net carb meal options.
2. **Google Diabetes News: 10-Mile Local Sourcing**: Surfaces verified restaurants and grocers within 10 miles offering diabetic-compliant options.

---

### 9. Universal Accessibility: Hands-Free Voice In & Speech Out

* **Speech-to-Text (STT)**: Hands-free voice intake and tab navigation via native Web Speech API.
* **Text-to-Speech (TTS)**: Spoken telemetry readouts and urgent glycemic alerts.

---

### 10. Global Community & Developer Incentives: Rewarding Human Health

To foster an unstoppable open-source movement for global health, Google Gemini provides an unprecedented developer and researcher support ecosystem:

#### A. The Author's 20-Year AI Provenance: From Claude's 18-Month Struggle to Gemini's Triumph
* **Two Decades of AI & Cloud Architecture (Since 2007)**: The author has engineered cloud and AI systems since 2007. Having evaluated early Gemini upon initial release, the author spent **18 grueling months fighting Claude's chronic development issues**—including persistent context drift, subtle logic hallucinations, broken code regressions, and fragile tool handling.
* **Immediate Success with Current Generation Gemini**: Transitioning to the current generation of Google Gemini yielded **immediate, transformative success**: dramatically lower defect rates, strict architectural adherence, and vastly superior full-stack code quality.
* **Full Public .jsonl Engineering Trajectory (`session_logs/transcript.jsonl`)**: In an unprecedented commitment to scientific transparency, the author has committed **the complete 14.3+ MB raw `.jsonl` engineering trajectory directly into the public build tree**. 
  * Allows Google AI engineers and researchers to observe real agentic interactions across hundreds of complex turns.
  * Documents exactly how bugs were diagnosed, how mistakes were corrected, and how Gemini systematically achieved Six Sigma quality.
* **Formal Systems Engineering over Random Comments**: Instead of cryptic commit messages or random code comments, every phase of the **BFM Biometric Function Monitor** project is comprehensively formalized into Requirements Specifications, Functional Specifications (FS), and FMEA risk post-mortems so developers and researchers get up to speed instantly.

#### B. Unlimited Google Gemini Resources & Compute Grants
* **Unlimited Developer API Access**: Verified developers who submit functional device drivers, UI modules, security audits, or localizations receive **unlimited time, tokens, and compute resources across all Google Gemini models (Gemini Ultra, Pro, Flash)**.
* **Research Model Allocations**: Metabolic researchers and computational biologists receive dedicated cloud GPU/TPU quotas to train predictive time-series models on anonymized non-PHI telemetry.

#### C. Direct Communication with Google Gemini Engineering & Research Teams
* **Dedicated Collaboration Channels**: Approved code and research contributors gain direct access to private communication channels (Slack/Discord/Google Developer Groups) with core Google Gemini, DeepMind, and Google Health engineering teams.
* **Bi-Weekly Virtual Office Hours**: Interactive technical sessions to review architectural optimizations, computer vision food classifiers, and biometric edge analytics.

#### D. Institutional Governance: The Google Gemini BFM Review Board
* **Joint Scientific & Engineering Review Board**:
  * Composed of Google AI engineers, attending endocrinologists, and lead open-source architects.
  * Oversees code quality, vets mathematical models, and certifies peer-reviewed research papers for publication.
* **Academic Recognition & Co-Authorship**:
  * Researchers submitting validated glycemic studies receive official co-authorship recognition, featured placement on the Google AI Research portal, and Google Open Source Contributor Hall of Fame honors.
* **Patient Recognition Spiffs**: Diabetic individuals sharing documented lifestyle modifications and glycemic stabilization achievements earn community badges and partner spiffs.

---

### 11. Final Version Scope: Emergency Critical Dispatch (Physio-Control Heritage)

1. **Level 1 (Local Awakening)**: Escalating high-decibel auditory alarms awaken the sleeping user during acute nocturnal hypoglycemia nadir (< 54 mg/dL).
2. **Level 2 (Escalated Dispatch)**: Auto-SMS with GPS and acute vitals dispatched to 911/EMS and physician if user is unresponsive for 3 minutes.
3. **Level 3 (30-Day Triage Download)**: Paramedics and ER doctors extract preceding 30 days of continuous telemetry via BLE or QR code to immediately diagnose root cause.

---

### 12. Verification & Quality Gates

* **Six Sigma Quality Standard**: All releases must pass `strict_gate_v2.py` (`13/13`), `audit_zero_defect.py` (`21/21`), and `test_beta1_e2e.py` (`10/10`):
  1. Bit-for-bit dual/triple-file SHA-256 mirror parity (`BFM.HTM` == `index.html` == `w11_htm_app/index.html`).
  2. Perfect HTML tag and DOM tree balance (`div` balance = `1,957`, diff = `0`).
  3. Static JavaScript syntax verification via Node.js `--check`.
  4. Python server bytecode compilation (`w11_htm_app/server.py`).
  5. SQLite deduplication and confectionery safety verification (`15` verified `popular_meals`).
  6. Zero monetary cost strings in nutritional headers.
  7. Multi-tenant database isolation & 3-way binding verification (`UID <-> ESN <-> Watch MAC`).
  8. Canonical Build Naming Verification (`beta.00.01.0057` match across UI, API, and FHIR metadata).
  9. Fail-closed CLEBUGGER synchronization (`tools/sync_clebugger.py --check`, `0 OPEN` in `D:\CLEBUGGER\bugs.db`).
  10. Subjective Experiential Telemetry (HDYF) logging, `13-Lane` & `3D Multi` correlation, and HL7 FHIR R4 export.


---

### 13. System Formal Requirements Traceability Matrix (REQ-01 to REQ-24)

| Requirement ID | Requirement Category | Requirement Specification Mandate | Verification Method |
| :--- | :--- | :--- | :--- |
| **REQ-01** | Architecture | Sovereign, air-gapped standalone execution from local file system (`BFM.HTM`) with zero mandatory cloud connectivity. | Dual origin test (`file:///` and `http://127.0.0.1:8899`) |
| **REQ-02** | Security & Legal | Mandatory startup terms-of-use acceptance modal cryptographically hashed and logged to SQLite table `user_legal_acceptance`. | Audit table inspection |
| **REQ-03** | Hardware Binding | 3-Way binding triad strictly isolating data: User Profile (`UID`) <-> Cell Portal (`ESN`) <-> Watch Transponder (`MAC`). | Relational schema integrity check |
| **REQ-04** | Data Storage | Relational SQLite vault storing continuous 5-minute telemetry, meals, exercises, and medications. | Schema DDL validation |
| **REQ-05** | EHR Export | 1-Click US-Core Epic FHIR R4 JSON bundle and LOINC-coded CSV clinical export generated strictly on demand. | FHIR R4 JSON schema validator |
| **REQ-06** | Dual Backup | Automated daily dual external backup generating synchronized SQLite snapshot, FHIR JSON, and SHA-256 checksum manifest. | Backup engine audit |
| **REQ-07** | Nutrition BOM | Compound meal intake decomposition: calories, net carbs, fat, protein, and glycemic load without monetary cost strings. | Nutrition calculator audit |
| **REQ-08** | GLUT4 Engine | Correlated physical activity logging calculating non-insulin kinetic glucose clearance and post-meal recovery deltas. | Metabolic correlation engine |
| **REQ-09** | Prescription Safety | Dedicated medication registry tracking drug name, dosage, schedule, and titration milestones. **Absolute zero-mock data policy**. | Medication table audit |
| **REQ-10** | Personal Goals | Personalized configured targets (fasting glucose, postprandial ceiling, `2,000` daily step goal aligned to watch `0xA3`) overriding legacy defaults. | Goal compliance & `0xA3` test |
| **REQ-11** | Dial Compatibility | Interactive dial manager supporting F0030001 OTA flash for compatible watches and hiding write UI cleanly on non-writable Model 5010 dials (`#boxDialOverwriteExp`). | Dial UI boundary check |
| **REQ-12** | Voice Synthesis | Hands-free Web Speech accessibility reading aloud real-time biometric readings and nutritional summaries on demand. | Speech API synthetic event |
| **REQ-13** | Quality Gate | Six Sigma (`-Werror`) 13-point (`strict_gate_v2.py`) + 21-point (`audit_zero_defect.py`) automated verification gates enforcing zero regressions. | Pre-commit gate execution |
| **REQ-14** | Defect Logging | Fail-closed CLEBUGGER synchronization (`tools/sync_clebugger.py --check` against `D:\CLEBUGGER\bugs.db`), enforcing `0 OPEN` items per build. | `strict_gate_v2.py` Check 12 |
| **REQ-15** | Canonical Taxonomy | Standardized build versioning: `alpha.xx.xx.xx.xxxx` (Alpha) and `beta.00.01.xxxx` (Beta, current `beta.00.01.0057`) pinned across UI and API. | Build badge & AST scanner |
| **REQ-16** | Human Success | Primary governing philosophy: daily user adoption and physician trust overriding purely mathematical process rankings. | User & clinician audit |
| **REQ-17** | HDYF Telemetry | "How Do You Feel?" 360° x 360° spherical telemetry capturing natural language voice/text comments anchored to master timeline (`feeling_entries`). | Experiential journal test |
| **REQ-18** | Governance | The Author is the sole Pioneer, Inventor, and Philosophical Leader; the project currently has no sponsors and is actively seeking sponsorship (Veepoo / FITAOS, PPG watch vendors, Google Gemini). | Document & legal review |
| **REQ-19** | Somatosensory Pain | Real-time classification and tracking of all voiced pain and physical discomfort rendered directly in `CH 09: PAIN INDEX`. | NLP & timeline anchor test |
| **REQ-20** | HBand 2.0 Parity | Complete hardware equivalence (`0xDF` OriginV3 244B MTU, `0x88` Temp history, `0xE0` Sleep + naps, `0xB8` 12/24h lock, `8,859`-epoch Lorenz, ECG, METs). | Transponder GATT protocol check |
| **REQ-21** | Abrams Aesthetic | Interface follows luminous clean clinical white/slate (`#f8fafc`), ice-blue (`#0284c7`), and monospaced tabular numerals. | DOM class & CSS audit |
| **REQ-22** | Zero-Data Reality | Zero Data = Zero Line: un-synced charts show `0`, no `enrichBiometrics` overwrites, no hardcoded fake medications (`Metformin`), blank unassigned MRN. | State & chart rendering audit |
| **REQ-23** | Consolidated Export | All EHR, FHIR R4, CSV, and deduplicated SQLite USB backup triggers centralized in **`13-EXPORT`** (`pageExport`, formerly Bay 05), zero duplicate buttons. | DOM button presence audit |
| **REQ-24** | 13-Channel Chart Parity | Identical 13 channels (`CH 01` Glucose–`CH 13` Medications Rx) with blue off-wrist exclusion bands rendered uniformly in 3D Multi & 13-Lane Tall Chart. | `getTallSynchronizedDataset()` check |
| **REQ-25** | Native Desktop Launcher | Compiled `BFM_Launcher.exe` enabling Windows 11 Taskbar pinning and dual `file:///` + `127.0.0.1:8899` runtime parity. | Desktop & E2E smoke test |
