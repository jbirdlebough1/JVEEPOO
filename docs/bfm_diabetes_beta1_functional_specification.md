# BFM Biometric Function Monitor: Functional Specification (FS)
## Beta 1 Release: Universal Personal Biofunction Platform & Zero-Regression Architecture

---

## 1. Executive Summary & Engineering Charter

* **Document Purpose**: Formal technical contract establishing the exact architecture, code migration plan, database schemas, and quality controls for the **Beta 1 Build** of the **BFM Biometric Function Monitor** (Console Powered By Gemini).
* **Core Paradigm Shift**: Transitioning from rapid ad-hoc prototyping to **formal pre-build functional specifications, failure mode analysis (FMEA), and Six Sigma zero-regression enforcement**.
* **The Author's 20-Year AI Provenance (Since 2007)**: Engineering AI and cloud systems for nearly two decades. After testing early Gemini, the author spent **18 grueling months fighting Claude's chronic development issues** (context drift, logic hallucinations, and regressive syntax breaks). Returning to **current-generation Google Gemini** produced **immediate, transformative success**: dramatically lower defects, rock-solid architectural compliance, and superior full-stack code quality.
* **Open Engineering Trajectory (`.jsonl`)**: In an unprecedented commitment to scientific rigor, the author has committed the **entire 14.3+ MB raw `.jsonl` trajectory (`session_logs/transcript.jsonl`) directly into the public build tree**. Google AI engineers, DeepMind researchers, and community developers can inspect real-world turn-by-turn performance, bug post-mortems, and iterative learning.
* **Rigorous Systems Engineering over Random Comments**: Eliminates cryptic commits and random comments. Every architectural decision on the **BFM Biometric Function Monitor (`beta.00.01.0057`)** is backed by formal Requirements, Functional Specifications (FS), and FMEA risk post-mortems.
* **Pioneer, Inventor & Philosophical Leader**: **The Author** (Creator, Chief Architect, and Leader of the BFM Philosophy)
* **Corporate & Technology Sponsor**: **Google** (Sponsoring, Hosting, and Powering via Google Gemini AI)
* **Workstation Console**: **BFM Biometric Function Monitor** (`BFM.HTM` & native Windows `BFM_Launcher.exe`, 14 Operational Tabs)
* **Auxiliary Units**: **BFM Mobile Companion** (`cell.html`, registered as **Cell 1, Cell 2, Cell 3...**)
* **Continuous Wearables**: **Linked Watch 1, Linked Watch 2** (Paired to PC, bound to UID and Cell ESN)
* **Primary Metric of Success**: **First-pass Six Sigma verification** with zero tag balance errors, zero cross-tenant data pollution, zero PHI cloud egress, and zero functional regressions from Alpha.
* **Visual Cleanliness & Zero Defect Quality Mandate**:
  1. **Zero Broken Image Links & Zero SVG Fragility**: Absolutely zero fragile external `.svg` or unverified image embeds in markdown documents that render as broken image boxes (`file:///D:/`). All architectural diagrams, workflows, and pipelines must be authored using robust, universally portable, natively rendered Markdown Architecture Tables.
  2. **Zero LaTeX / Raw Math Clutter**: Absolutely zero `$` or `$$` math delimiters, zero KaTeX / MathJax markers, zero LaTeX macros (`\frac`, `\sigma`, `\sqrt`, `\approx`, `\pm`, `\times`, `\ge`, `\le`, `\iff`, `\ne`). Math must use natural, clean plain-text typography (`(Glucose + 46.7) / 28.7`, `sigma / sqrt(N)`, `<= 45g`, `+/- 15%`).
  3. **Zero Monospace ASCII Box-Art**: Absolutely zero box-drawing characters (`┌`, `┐`, `└`, `┘`, `│`, `─`, `├`, `┤`, `┬`, `┴`, `┼`). Replaced with clean, accessible Markdown tables.
  4. **Zero Raw Text Flowchart Code Blocks**: Absolutely zero `flowchart TD`, `subgraph`, or raw text pseudo-code diagrams in user-facing documents.
  5. **Zero Forbidden Legacy Terms**: Zero "5010", zero "patient", zero "clinical", zero "Star Trek" in-app.
  6. **Mandatory Automated Pre-Commit Audit**: Every document, presentation, and display code file must pass the automated `audit_zero_defect.py` tool (`-Werror`) with zero defects before staging or committing.

---

## 2. Requirements Traceability Matrix (RTM)

Every requirement specified in [`bfm_diabetes_beta_requirements_specification.md`](file:///d:/veepoo/docs/bfm_diabetes_beta_requirements_specification.md) is mapped directly to an architectural module, file, and database target:

| Req ID | Requirement Description | Functional Module Target | Source File / Endpoint | Verification Mechanism |
| :--- | :--- | :--- | :--- | :--- |
| **REQ-01** | BFM Biometric Function Monitor (`BFM.HTM`) Branding | Title Bar & Main Header | `BFM.HTM`, `index.html` | AST & DOM String Match |
| **REQ-02** | Model 5010 Dial Write Compatibility Boundary | Dial Tab (`11-WATCH`) | `BFM.HTM` `#boxDialOverwriteExp` | Conditional hide below screenshot on non-writable dials |
| **REQ-03** | Zero Synthetic Placeholder Leakage | Zero-Data Guardrails | All `.html`, `.py`, `.md` | `audit_zero_defect.py` Checks 09 & 16 |
| **REQ-04** | Star Trek limited strictly to historical intro tribute | IP & Trademark Quarantine | `presentation/TRANSCRIPT.md` | Compliance Audit |
| **REQ-05** | Copyleft Disclaimer & Immutable User Acceptance | Legal Acceptance Modal | `BFM.HTM` (Tab 10), SQLite | Query `user_legal_acceptance` |
| **REQ-06** | 3-Way Triad Binding: UID <-> ESN <-> Watch MAC | Multi-Tenant Dispatcher | `server.py` & `watch_monitor.py` | Multi-Source Concurrent Ingest Test |
| **REQ-07** | Registered Family Cells Roster & W11 Cell Isolation | Fleet Manager UI & W11 Host | `BFM.HTM` (`10-CONFIG`), `server.py` | DOM Roster Check & Zero W11 Cell Shortcut |
| **REQ-08** | QR Auto-Download Standalone Android `BFM-Cell.apk` & Auto-Register | Mobile Onboarding Engine | `/api/qrcode/cell`, `/install/cell`, `/download/BFM-Cell.apk`, `/api/shuttle/register` | Real-Device Android `Cell PASS` (`ESN-9482-A01`, `ACTIVE`) |
| **REQ-09** | `BFM-Cell.apk` Multi-Day Field Autonomy & Zero Watch BLE Coupling | Mobile Native APK (`com.bfm.cell`) | `w11_htm_app/BFM-Cell.apk`, `cell.html` (Zero BLE permissions) | Native `file:///android_asset/` launch + local field queue |
| **REQ-10** | Homecoming Wi-Fi Auto-Sync (Meals, Feelings & 3-State Med Toggle) | Bulk Ingestion & Med Sync Engine | `server.py`, `cell.html`, `daily_med_adherence` | `CONNECTED TO BFM SERVER`, `POST /api/meds/daily_status`, SHA-256 Receipt |
| **REQ-11** | Automated Daily Deduplicated USB Backup | Removable Storage Sync | `BFM.HTM` (`13-EXPORT`) & `usb_backup_engine.py` | File System Access API & USB Dedup Test |
| **REQ-12** | Complete refactor from `lcars-` to `bfm-` | Modular CSS/DOM Standard | `BFM.HTM`, `cell.html` | Strict Gate Check 2 (DOM Hierarchy) |
| **REQ-13** | Developer Incentives & Unlimited Gemini Assets | Open Source Ecosystem | `docs/GOOGLE_LEADERSHIP_PRIMER.md` | Institutional Review Board Portal |
| **REQ-14** | Zero Defect Mandate & Visual Cleanliness Standard | Quality Assurance Subsystem | `audit_zero_defect.py` (`21/21`) | Automated Zero Defect Pre-Commit Gate |
| **REQ-15** | Canonical Build Naming Standard (`beta.00.01.0057`) | Versioning Subsystem | All UI, API & Manifests | Regex & Checksum Parity Gate |
| **REQ-16** | Two-Pass Master Test Plan & CLEBUGGER Sync Gate | Quality Assurance / TQM | `tools/sync_clebugger.py`, `D:\CLEBUGGER\bugs.db` | `strict_gate_v2.py` Check 12 (`0 OPEN`) |
| **REQ-17** | Human-Centric User & Physician Adoption Metric | Behavioral Feedback Engine | Longitudinal Vault & FHIR | Daily Active Use (>=90%) & Doctor FHIR Review |
| **REQ-18** | Subjective Experiential Telemetry ("How Do You Feel?") | Behavioral Psychology / HDYF | `cell.html`, `server.py`, DB | Mobile HDYF Prompt, Home Wi-Fi Sync & FHIR Export |
| **REQ-19** | Somatosensory Pain & Physical Discomfort Telemetry | HDYF NLP Classification Engine | `cell.html`, `server.py`, DB | Pain & Discomfort Extraction and Timeline Anchor |
| **REQ-20** | HBand & HBand 2.0 Full Hardware Equivalence | Universal Wearable Subsystem | BLE GATT Stream & DB | Raw ECG, MET, Sleep Hypnogram, BIA Verification |
| **REQ-21** | Abrams-Era Bridge Science Station Design System | Luminous Clinical UI Framework | `BFM.HTM`, CSS System | Light Slate `#f8fafc`, Ice-Blue `#0284c7`, Zero Slabs |
| **REQ-22** | Pure Zero-Data Reality Principle (Zero Is Zero) | Telemetry & Database State Engine | All Charts, Gauges, Journals | Un-synced 0 Baseline, Empty Journals, Blank MRN |
| **REQ-23** | Consolidated Single-Bay EHR Data Export | Export Subsystem | `BFM.HTM` (Bay 05) | 1-Click Centralized Export, Zero Redundant Triggers |

---

## 3. Alpha Code Audit, Reuse & Migration Inventory

An exhaustive audit of the 23,000+ lines of Alpha codebase was conducted to categorize every existing asset into three distinct migration streams:

| Migration Stream | Source Alpha Asset | Scope & Target Module | Technical Rationale & Architectural Rule |
| :--- | :--- | :--- | :--- |
| **Stream 1: Verbatim Reuse**<br>(100% Preserved) | `watch_monitor.py`<br>`server.py`<br>`BFM.HTM` Tab 04 | • OriginV3 244B MTU & Optical BLE Pipeline (`0xDF`, `0x88`, `0xE0`, `0xB8`, `0x89`, `0xD0`, `0x80`, `0xA8`, `0x07`)<br>• ADAG eA1C Math: `(Glucose + 46.7) / 28.7`<br>• Unified 13-Lane & 3D Multi-Channel Timelines (`getTallSynchronizedDataset()`)<br>• Locked Conagra / USDA / Restaurant Nutrition Truth Tables (`15` `popular_meals`) | Verified protocol packet handlers and glycemic math pass zero-variance audits across all 48 daily 30-min intervals. |
| **Stream 2: Refactored Modules**<br>(Structural Hardening) | `BFM.HTM`<br>`cell.html`<br>`server.py`<br>`watch_monitor.py` | • CSS/DOM structural unification across 14 operational tabs (`1,957` balanced `<div>` tags)<br>• Dynamic API base wrapper (`file:/// -> http://127.0.0.1:8899`)<br>• `/api/browser_active` Bleak radio arbitration + serialized GATT TX queue<br>• Dynamic Dispatcher: 3-Way Triad Routing (`UID <-> ESN <-> MAC`) | Eliminates BLE contention, enforces `file:///` parity, prevents `0xB8` 12h clock overwrites (`CLE-110`), and removes all fake placeholders. |
| **Stream 3: Newly Engineered**<br>(Beta `00.01.0057` Capabilities) | `BFM.HTM`<br>`server.py`<br>`BFM_Launcher.exe`<br>`usb_backup_engine.py`<br>`strict_gate_v2.py` | • `0xDF` 244B MTU out-of-order block assembly + genuine `8,859`-epoch R-R Lorenz Poincaré<br>• `0x88` continuous Core/Skin temperature history (`00:00–23:59`, `113`+ rows)<br>• `0xE0` 24h Sleep + daytime naps + `2,000` step goal + Hydration (`fl oz`) overlays<br>• Native `BFM_Launcher.exe`, deduplicated USB backup, fail-closed CLEBUGGER Check 12/13 | Delivers full 24h multi-sensor capture, 13-channel 3D/2D parity, Windows 11 Taskbar launch, and zero open CLEBUGGER defects. |

### Detailed Component Inventory:

#### 1. Components Preserved Verbatim:
* **Optical Sensor Protocol Metrology (`watch_monitor.py` & Section 08 of `server.py`)**:
  * 0x89 (Glucose tonometry), 0xD0 (24h Heart Rate), 0x80 (Dual-wavelength SpO2), 0xA0 (Battery), 0xA8 (32-bit Pedometer).
  * *Rationale*: Reverse-engineered protocol packet handlers are 100% verified against hardware and function flawlessly.
* **ADAG eA1C Projection Mathematics (`index.html`)**:
  * eA1C = (Glucose + 46.7) / 28.7.
  * *Rationale*: Matches ADA clinical consensus guidelines; already passes zero-variance audits.
* **24-Hour Multi-Lane SVG Timelines (`index.html` Tab 04)**:
  * Staggered 3-line X-axis event badges, 4px high-contrast reference line halos, 8 stacked physiological lanes.
  * *Rationale*: Successfully resolved in CLE-029; senior contrast readability is optimal.
* **Verified Nutritional Package Database (`server.py` Section 0D)**:
  * Official Conagra package disclosures (Banquet Pot Pie 7oz: 420 kcal, 34g net carbs) and USDA tea pairing.
  * *Rationale*: Passes all confectionery safety checks.

#### 2. Components Refactored:
* **CSS Class Names and DOM Structure (`index.html` & `cell.html`)**:
  * Every `.lcars-*` class migrated to `.bfm-*` (`.bfm-workspace`, `.bfm-card`, `.bfm-btn`, `.bfm-header`, `.bfm-sidebar`, `.bfm-pill`).
  * All CSS theme variables migrated: `--lcars-peach` -> `--bfm-peach`, `--lcars-cyan` -> `--bfm-cyan`.
* **Database & BLE Ingestion Layer (`server.py` & `watch_monitor.py`)**:
  * Refactor from single static `telemetry.db` to dynamic connection router targeting `telemetry_user_<uuid>.db` based on:
    1. Incoming payload `phone_esn` (for field meals from cells).
    2. Incoming BLE packet `watch_mac` (for continuous biometrics from watches).
* **Branding and Header Badges**:
  * Workstation title updated to `BFM Biometric Function Monitor • Console (Powered By Gemini)`.
  * Mobile companion updated to `BFM Mobile Companion • Cell 1 (or Cell 2, Cell 3)`.

#### 3. Newly Engineered Components:
* **Tab 10 Family Cells Roster**: Interactive HTML/CSS table (`tblShuttleRoster`) with `+ ADD CELL` QR provisioning and Delete CRUD controls, showing live `Cell 1 | Android BFM Cell App | ESN-9482-A01 | ACTIVE`.
* **Standalone Android APK (`w11_htm_app/BFM-Cell.apk`, `tools/build_cell_apk.py`)**: Signed `com.bfm.cell` Android WebView package built with portable JDK 17 + Android SDK 34 (`apksigner` v2/v3 verified), declaring **zero Bluetooth permissions**, bundling `cell.html` offline with native photo picker, mic/camera grant, tap-to-edit server IP, and zero install banner (`BFM_IS_NATIVE_APP`).
* **QR Auto-Install & Auto-Register (`/api/qrcode/cell`, `/install/cell`, `/download/BFM-Cell.apk`, `/api/shuttle/register`)**: Scanning the Tab 10 QR downloads `BFM-Cell.apk` straight to Android Downloads; launching on home Wi-Fi executes `auto_register` automatically (`ACTIVE`).
* **3-State Daily Med Toggle & Midnight Reset (`#btnCellDailyMed`, `daily_med_adherence`, `/api/meds/daily_status`)**: Light blue (untaken) -> 1st press mid blue morning taken -> 2nd press green dinner taken -> auto-reset to light blue at `00:01` daily, synchronized bidirectionally with `BFM.HTM` Tab `09-MEDICATION`.
* **Hardware ESN Ingestion Router (`/api/shuttle/dock`)**: Validates device credentials and dispatches field intake meals & HDYF feelings cleanly into `w11_htm_app/telemetry.db` when home Wi-Fi reconnects.
* **Dual USB/SD Storage Backup Engine (`index.html` Tab 05)**: Browser File System Access API integration exporting raw `.sqlite` and Epic FHIR R4 `.json` bundles with SHA-256 manifests.

---

## 4. Multi-Tenant Anti-Collision: The 3-Way Hardware Binding Triad

In shared household computing, the active monitor screen cannot be trusted for routing:
1. **The Active Screen Trap**: If Dad is viewing Tab 04 on the PC, and Mom’s watch sends glucose packets or Mom's phone docks via Wi-Fi, naive software writes to Dad’s profile.
2. **The Triad Solution**: Star Ship Gemini maintains an immutable 3-way binding:
   **USER_UID <-> PHONE_ESN <-> LINKED_WATCH_MAC**
3. **Deterministic Hardware Routing Architecture**:

| Hardware Source Layer | Device Signature | Dispatcher Lookup (`telemetry_master.db`) | Target Local Vault | Anti-Collision Protection |
| :--- | :--- | :--- | :--- | :--- |
| **Dad's Wrist Watch (BLE)** | `MAC 8B:DE:40:83:F6:16` | Matches `linked_watch_mac` -> `USR-001` | `telemetry_user_USR-001.db` | Routes by MAC; ignores active screen |
| **Dad's Mobile Companion (Wi-Fi)** | `ESN-9482-A018-7F` | Matches `phone_esn` -> `USR-001` | `telemetry_user_USR-001.db` | Two-phase commit with SHA-256 receipts |
| **Mom's Wrist Watch (BLE)** | `MAC C4:12:9A:33:02:51` | Matches `linked_watch_mac` -> `USR-002` | `telemetry_user_USR-002.db` | Streamed into Mom's vault in background |
| **Mom's Mobile Companion (Wi-Fi)** | `ESN-7731-B942-3C` | Matches `phone_esn` -> `USR-002` | `telemetry_user_USR-002.db` | Zero cross-talk with Dad's active tab |
| **Son's Standby Roster** | `ESN-4410-C209-1E` | Matches `phone_esn` -> `USR-003` | `telemetry_user_USR-003.db` | Registered and isolated on Tab 10 |

* **Screen Independence**: The PC monitor remains undisturbed on Dad's charts while Mom's watch and cell sync quietly in the background with zero cross-tenant contamination.

---

## 5. Alpha Code Review & Lessons Learned (`/codereview` `/audit` `/learn`)

To fulfill the mandate of **stopping constant regression**, we conducted a thorough root-cause analysis of historical Alpha bugs:

| Incident | Root Cause in Alpha | Engineering Safeguard for Beta 1 |
| :--- | :--- | :--- |
| **DOM Tag Imbalance (Div Diff != 0)** | Using regex `re.sub()` to insert HTML chunks into `index.html`, inadvertently injecting or stripping trailing `</div>` tags. | **Zero Regex HTML Mutations**: All UI changes must be performed via full AST parsing or verified whole-template block generation with pre-commit div balance validation. |
| **Dual-File Mirror Drift** | Editing `index.html` at the project root while forgetting to update `w11_htm_app/index.html`, breaking runtime parity. | **Automated Parity Synchronization**: Pre-commit hook enforces bit-for-bit SHA-256 equality (`Check 1/10`); build script automatically mirrors changes bidirectionally. |
| **Python Server Syntax Errors** | Unmatched brackets left in `server.py` after manual edits to route tables. | **Pre-Commit Bytecode Compilation**: Mandatory execution of `py_compile.compile()` across all backend scripts prior to staging (`Check 5/10`). |
| **Nutritional Calculation Variance** | General LLM prompt drift returning variable carbohydrate values for branded items. | **Hard-Pinned Nutrition Truth Tables**: High-frequency items (e.g. Banquet Pot Pie, Junior Mints) are locked in deterministic local SQLite tables with strict net-carb math. |
| **UI Overlaps on SVG Charts** | Single-line X-axis timestamps colliding with clinical event badges during high-activity periods. | **Staggered Multi-Line Architecture**: Fixed in CLE-029; X-axis separated into Line 1 (Time), Line 2 (Pre-Meal/Nadir), and Line 3 (Post-Meal GLUT4 Peak). |

---

## 6. Six Sigma FMEA Risk Analysis & Mitigation Matrix

A formal **Failure Mode and Effects Analysis (FMEA)** was conducted for the Beta 1 architecture. Each potential failure mode is evaluated by **Severity (S)**, **Occurrence (O)**, and **Detection (D)** to calculate the **Risk Priority Number (RPN = S x O x D)** on a 1–10 scale:

| Failure Mode ID | Failure Mode Description | S | O | D | RPN | Mitigation Strategy & Safeguard | Post-Mitigation RPN |
| :--- | :--- | :---: | :---: | :---: | :---: | :--- | :---: |
| **FMEA-01** | **Multi-User Data Pollution**: Mom's watch or phone syncs data into Dad's health vault. | 9 | 4 | 3 | **108** | **3-Way Triad Binding**: Server routes payload strictly by `phone_esn` and `watch_mac` lookup in `registered_cells`. Active screen on PC is completely ignored. | 9 x 1 x 1 = **9** |
| **FMEA-02** | **Field Data Loss on Network Drop**: Wi-Fi drops mid-upload during homecoming sync. | 8 | 5 | 2 | **80** | **Two-Phase Commit with SHA-256 Receipts**: Records remain `PENDING_UPLOAD` in phone's IndexedDB until Star Ship Gemini returns verified batch hash. | 8 x 1 x 1 = **8** |
| **FMEA-03** | **Regulatory / Malpractice Exposure**: Lawsuit alleging clinical diagnostic malfunction. | 10 | 2 | 2 | **40** | **Strict Legal Decoupling**: Copyleft license, zero "patient"/zero "clinical" terminology, and mandatory startup acceptance gate stored in SQLite. | 10 x 1 x 1 = **10** |
| **FMEA-04** | **USB/SD Removable Media Permission Loss**: Browser forgets permission to write to external drive. | 7 | 4 | 2 | **56** | **Persistent Storage API + Automatic Fallback**: Prompts via File System Access API; if permission revoked, gracefully triggers browser file download. | 7 x 1 x 1 = **7** |
| **FMEA-05** | **DOM Tag Nesting Regression**: Editing code breaks layout hierarchy on secondary tabs. | 6 | 4 | 1 | **24** | **Automated Tag-Counting Gate**: CI/CD gate immediately rejects any commit where `div_open != div_close` or tab pages are not direct children of `<main>`. | 6 x 1 x 1 = **6** |

---

## 7. Target Database Schemas & API Contracts

### A. Star Ship Gemini Master Registry Database (`telemetry_master.db`)

```sql
-- 1. Family User Profiles
CREATE TABLE IF NOT EXISTS user_profiles (
    user_uid TEXT PRIMARY KEY,                  -- Unique UUID (e.g., "USR-9482-01")
    user_callsign TEXT NOT NULL,                -- Display Name (e.g., "John (Dad)")
    target_a1c REAL DEFAULT 6.5,                -- Personal Target HbA1c (%)
    target_tir_min REAL DEFAULT 70.0,           -- Target Time-in-Range Floor (mg/dL)
    target_tir_max REAL DEFAULT 140.0,          -- Target Time-in-Range Ceiling (mg/dL)
    assigned_cell_id TEXT UNIQUE,               -- Assigned Cell (e.g., "Cell 1")
    assigned_registry TEXT UNIQUE,              -- Starfleet Registry (e.g., "NCC-202X-1")
    database_filename TEXT NOT NULL,            -- Isolated DB (e.g., "telemetry_user_1.db")
    created_timestamp TEXT NOT NULL
);

-- 2. Registered Family Cells & 3-Way Hardware Bindings
CREATE TABLE IF NOT EXISTS registered_cells (
    cell_id TEXT PRIMARY KEY,                   -- e.g., "Cell 1", "Cell 2"
    registry_number TEXT NOT NULL,              -- e.g., "NCC-202X-1", "NCC-202X-2"
    user_uid TEXT NOT NULL,                     -- Foreign Key -> user_profiles(user_uid)
    phone_name TEXT NOT NULL,                   -- e.g., "Google Pixel 9 Pro"
    phone_esn TEXT UNIQUE NOT NULL,             -- Hardware ESN / Device UUID
    linked_watch_mac TEXT UNIQUE NOT NULL,      -- Hardware MAC (e.g., "8B:DE:40:83:F6:16")
    linked_watch_name TEXT DEFAULT 'Wearable',  -- e.g., "FITAOS 5150 / Model 5010"
    registered_timestamp TEXT NOT NULL,         -- Initial pairing timestamp
    last_docked_timestamp TEXT,                 -- Last successful bulk data sync
    status TEXT DEFAULT 'ACTIVE',               -- 'ACTIVE', 'REVOKED', 'OFFLINE'
    FOREIGN KEY(user_uid) REFERENCES user_profiles(user_uid)
);

-- 3. Immutable User Legal Acceptance Audit Log
CREATE TABLE IF NOT EXISTS user_legal_acceptance (
    agreement_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_uid TEXT NOT NULL,
    terms_version TEXT NOT NULL,                -- e.g., "BFM-BETA1-TERMS-2026.11"
    terms_sha256 TEXT NOT NULL,                 -- SHA-256 hash of accepted disclaimer text
    accepted_timestamp TEXT NOT NULL,           -- ISO 8601 UTC timestamp
    client_user_agent TEXT NOT NULL,            -- Browser / OS platform fingerprint
    signature_acknowledgment INTEGER DEFAULT 1, -- 1 = Explicitly Accepted
    FOREIGN KEY(user_uid) REFERENCES user_profiles(user_uid)
);
```

### B. Isolated User Telemetry Vault (`telemetry_user_<uuid>.db`)

```sql
-- 4. Ingested Continuous Watch Biometrics
CREATE TABLE IF NOT EXISTS watch_telemetry (
    record_id INTEGER PRIMARY KEY AUTOINCREMENT,
    timestamp TEXT NOT NULL,
    glucose_mgdl REAL,
    projected_ea1c REAL,
    heart_rate_bpm INTEGER,
    spo2_pct INTEGER,
    temperature_f REAL,
    step_count INTEGER,
    source_watch_mac TEXT NOT NULL,
    FOREIGN KEY(source_watch_mac) REFERENCES registered_cells(linked_watch_mac)
);

-- 5. Ingested Field Meals from Shuttlecraft Foodoor
CREATE TABLE IF NOT EXISTS field_meals (
    meal_id INTEGER PRIMARY KEY AUTOINCREMENT,
    intake_timestamp TEXT NOT NULL,
    inquiry_timestamp TEXT NOT NULL,
    meal_name TEXT NOT NULL,
    food_category TEXT DEFAULT 'MEAL',
    calories REAL NOT NULL,
    net_carbs REAL NOT NULL,
    protein REAL NOT NULL,
    fat REAL NOT NULL,
    fiber REAL NOT NULL,
    glycemic_impact_score TEXT,
    photo_blob BLOB,
    audio_transcript TEXT,
    source_cell_id TEXT NOT NULL,
    source_phone_esn TEXT NOT NULL,
    sync_timestamp TEXT NOT NULL
);
```

### C. Server Ingestion API Contracts (`server.py`)

| Step | Phase & Operation | Initiator & Endpoint | Payload / Protocol Data | Destination & System State |
| :---: | :--- | :--- | :--- | :--- |
| **01** | Dynamic QR Generation | PC Console (`index.html` Tab 10) | `GET /api/shuttle/provision?uid=USR-002` | Server generates cryptographically bound pairing token. |
| **02** | Device Interrogation | Shuttlecraft PWA (`cell.html`) | Scans Dynamic QR Code | Interrogates phone hardware name & crypto-generated ESN. |
| **03** | Registration Handshake | Shuttlecraft -> Server | `POST /api/shuttle/register` | Transmits pairing token, phone name, hardware ESN. |
| **04** | Master Registry Lock | Server (`server.py`) | `INSERT INTO registered_cells` | Locks ESN to `USR-002` & Linked Watch MAC; returns 200 OK. |
| **05** | Offline Field Autonomy | Shuttlecraft PWA | Mobile IndexedDB Local Vault | Encrypted local storage of meal photos (BLOBs) & audio. |
| **06** | Homecoming Bulk Dock | Shuttlecraft -> Server | `POST /api/shuttle/dock` | Transmits ESN + batch array of pending field meals. |
| **07** | Triad Signature Routing | Server Ingestion Engine | Query `registered_cells.phone_esn` | Resolves `USR-002`; writes batch to `telemetry_user_2.db`. |
| **08** | Two-Phase Commit Receipt| Server -> Shuttlecraft | Return verified SHA-256 batch hash | PWA clears `PENDING_UPLOAD` status; Tab 10 updates live. |

#### 1. Dynamic QR Provisioning (`GET /api/shuttle/provision?uid=<user_uid>`)
* **Response**:
  ```json
  {
    "status": "SUCCESS",
    "mothership": "Star Ship Gemini",
    "base_registry": "NCC-202X",
    "assigned_cell": "Cell 2",
    "assigned_registry": "NCC-202X-2",
    "user_uid": "USR-9482-02",
    "user_callsign": "Mary",
    "linked_watch_mac": "C4:12:9A:33:02:51",
    "pairing_token": "a8f7c9e1-4b2d-49f3-8d01-e246985a1b3c"
  }
  ```

#### 2. Auto-Scan Mobile Registration Handshake (`POST /api/shuttle/register`)
* **Payload**:
  ```json
  {
    "pairing_token": "a8f7c9e1-4b2d-49f3-8d01-e246985a1b3c",
    "cell_id": "Cell 2",
    "phone_name": "Samsung Galaxy S24 Ultra",
    "phone_esn": "ESN-7731-B942-3C",
    "client_version": "Beta 1.0"
  }
  ```

#### 3. Homecoming Bulk Data Docking (`POST /api/shuttle/dock`)
* **Payload**:
  ```json
  {
    "cell_id": "Cell 2",
    "phone_esn": "ESN-7731-B942-3C",
    "batch_records": [
      {
        "intake_timestamp": "2026-11-22T12:30:00Z",
        "inquiry_timestamp": "2026-11-22T12:28:15Z",
        "meal_name": "Banquet beef pot pie 7oz",
        "calories": 420,
        "net_carbs": 34,
        "protein": 8,
        "fat": 27,
        "fiber": 1,
        "glycemic_impact_score": "MODERATE",
        "photo_base64": "data:image/jpeg;base64,...",
        "audio_transcript": "Having beef pot pie for lunch"
      }
    ]
  }
  ```

---

## 8. Thirteen-Point Six Sigma Pre-Build Quality Gate (`strict_gate_v2.py`)

To prevent regressions, the pre-commit gate (`strict_gate_v2.py`) enforces an exhaustive **13-Point Six Sigma Gate** (`-Werror`):

| Check ID | Verification Target | Target File / Artifact | Standard of Zero Regression |
| :---: | :--- | :--- | :--- |
| **Check 01** | Triple-File SHA-256 Parity | `BFM.HTM` == `index.html` == `w11_htm_app/index.html` | Bit-for-bit identical cryptographic checksum. |
| **Check 02** | Strict Div Tag Balance | `BFM.HTM` (`1,957` opens == `1,957` closes) | Opens == Closes, Div balance difference == `0`. |
| **Check 03** | JavaScript AST Syntax | `BFM.HTM` | Node.js `--check` passes with zero errors. |
| **Check 04** | Python Server AST | `w11_htm_app/server.py` | Python bytecode compilation passes cleanly. |
| **Check 05** | SQLite Dedup & Safety | `w11_htm_app/telemetry.db` (`popular_meals`) | `15` unique meals; zero high-sugar confections flagged safe. |
| **Check 06** | Zero Monetary Cost Strings | `BFM.HTM` BOM Headers | Zero `$` price residue; strictly metabolic impact metrics. |
| **Check 07** | Mobile Client Tag Balance | `w11_htm_app/cell.html` | Opens == Closes, Div balance difference == `0`. |
| **Check 08** | SQLite 3-Way Triad DDL | `user_profiles`, `registered_cells` | Foreign keys and `UNIQUE` constraints verified on ESN & MAC. |
| **Check 09** | HL7 FHIR R4 Bundle Schema | FHIR R4 Export Template | Valid LOINC Observation collection with deterministic hash. |
| **Check 10** | Cell Registry & Delete API | `telemetry.db` + `server.py` | `Cell 1` active + `/api/shuttle/cell/delete` verified. |
| **Check 11** | Hardware Table & UI Segregation | `BFM.HTM` (`#tblWatchFunctions`, `#curveTimelineBreath`) | Forbidden header button removed; transducer map intact. |
| **Check 12** | Fail-Closed CLEBUGGER Sync | `tools/sync_clebugger.py --check` (`D:\CLEBUGGER\bugs.db`) | `0 OPEN` items across all tagged Beta builds & Alpha defects. |
| **Check 13** | Pre-Build Gate Readiness | Full Gate Suite (`13/13 PASS`) | Zero warnings, zero un-synced deltas. |

---

## 9. Completed & Active Implementation Phases (`beta.00.01.0001` -> `beta.00.01.0057`)

| Phase | Milestone Name | Key Engineering Deliverables | Verified Quality Gate |
| :---: | :--- | :--- | :--- |
| **Phase 1** | Database & Triad Engine | `user_profiles`, `registered_cells`, `/api/shuttle/dock`, `70,858+` vital epochs in `telemetry.db` | Schema constraints & `Cell 1` triad verified |
| **Phase 2** | Protocol Decoding & 13-Lane Parity | `0xDF` 244B MTU, `0x88` Temp, `0xE0` Sleep + naps, `0xB8` 12h clock lock, 13-Channel 3D & Tall Chart | Live watch verified (`CLE-110`, `CLE-111`, `0055`, `0056`) |
| **Phase 3** | Mobile Companion (`cell.html`) | Hardware interrogation, IndexedDB offline vault, HDYF pain/mood capture, Homecoming Dock | Div balance `0` & live dock verified |
| **Phase 4** | Native Launcher & USB Backup | `BFM_Launcher.exe` W11 Taskbar app, `usb_backup_engine.py`, consolidated `13-EXPORT` FHIR/CSV | `file:///` parity (`Check 08`) & USB dedup verified |
| **Phase 5** | Docs, 87-Slide Deck & Certification | Updated EM/Exec docs (`beta.00.01.0061`), 87-slide consolidated clinician & user deck (Tabs 01–14, 360° Ring, 90-Day SVG, Topology, FITAOS 5010 Hardware/Sensors, 5-Specialist Team, Info Loss Math, Animated 3D, Voice-Synced Scroll, Unified 2D Watch Off-Wrist Outage Bands, Live Synced MET/BloodComp/Breath, Mind the Gap Closing Slide) + audio, `4`-tool audit certification | `13/13` strict + `21/21` audit + `10/10` E2E + `87/87` slides |

1. **Phase 1: Database & Backend Engine**:
   * Implement `telemetry_master.db` with `user_profiles`, `registered_cells` (with `linked_watch_mac`), and `user_legal_acceptance`.
   * Implement endpoints `/api/shuttle/provision`, `/api/shuttle/register`, and `/api/shuttle/dock`.
   * Refactor BLE ingestion in `watch_monitor.py` / `server.py` to route packets by source `watch_mac`.
2. **Phase 2: Workstation UI Refactoring (`index.html`)**:
   * Refactor CSS/DOM from `lcars-` to `bfm-`.
   * Update title and header to **BFM Biometric Function Monitor (Powered By Gemini)**.
   * Add the **Registered Family Cells & Wearable Watch Roster** to Tab 10 bottom with Add/Edit/Delete modals.
   * Add the Startup Legal Acceptance Modal.
3. **Phase 3: Mobile Auxiliary Client (`cell.html`)**:
   * Implement auto-scan device interrogation on load (pulling `phone_name` and cryptographic ESN).
   * Implement IndexedDB offline vault for field meals.
   * Implement the one-click Homecoming Docking transfer button.
4. **Phase 4: Dual External USB/SD Storage Backup**:
   * Implement automated daily `.sqlite` + Epic FHIR R4 `.json` bundle generator with SHA-256 manifests (`usb_backup_engine.py`).
5. **Phase 5: Verification & Gate Certification**:
   * Execute 13-point Six Sigma gate (`strict_gate_v2.py`) and 21-point zero-defect suite (`audit_zero_defect.py`).
   * Perform end-to-end multi-device concurrent sync verification.


---

## 10. Master Test Plan Architecture & Execution Protocol

The Beta 1 verification protocol enforces a strict **Two-Pass Quality Validation Gate**:

| Test Phase | Operator / Agent | Primary Target | Scope of Execution | Defect Destination |
| :--- | :--- | :--- | :--- | :--- |
| **Pass 1: Automated Pre-Flight** | Gemini Subsystem | Headless Server & Engine | `audit_zero_defect.py` (8/8), `strict_gate_v2.py` (10/10), `test_beta1_e2e.py` (10/10) | Automated JSON post to CLEBugger (`:8999`) |
| **Pass 2: Human-in-the-Loop** | Human Operator | Desktop `BFM.HTM` | Operator double-clicks `BFM.HTM` in Windows Explorer; tests all 14 tab bays and mobile dock | Manual ticket / Change Request logged in CLEBugger |

* **CLEBugger Integration (Port 8999)**: All defects, anomalies, and change requests (CRs) are logged in real-time to CLEBugger (`http://127.0.0.1:8999/`). Zero open defects required before milestone sign-off.
* **Step-by-Step Human Testing**: Formalized in [`bfm_beta1_master_test_plan.md`](file:///d:/veepoo/docs/bfm_beta1_master_test_plan.md).

---

## 11. Longitudinal Behavioral Telemetry & Post-Beta Clinical Adoption Engine

Following the viable Beta 1 release, BFM operationalizes SEI-CMMI Level 5 closed-loop engineering by collecting and analyzing real-world usage behaviors:
1. **Patient Adherence Metrics**: Tracks frequency of daily mobile meal staging via Shuttlecraft Foodoor (`cell.html`), post-meal physical activity compliance, and nocturnal watch wearing.
2. **Physician Data Utilization**: Analyzes doctor review of generated Epic FHIR R4 JSON bundles, identifying the most clinically valuable LOINC telemetry streams.
3. **Closed-Loop Feature Evolution**: Translates user and physician behavioral patterns into prioritized feature enhancements matching the largest set of common real-world requirements.


---

## 12. HDYF Somatosensory Pain & Discomfort Classification Engine

The "How Do You Feel?" (HDYF) portal captures the patient's natural language state on home Wi-Fi and automatically parses, categorizes, and timestamps physical pain and somatic distress:

| Pain / Discomfort Category | Extracted Clinical Keywords | Mapped Etiology | Target Correlation Telemetry |
| :--- | :--- | :--- | :--- |
| **Diabetic Peripheral Neuropathy (DPN)** | Burning feet, tingling, pins and needles, electric zaps, numb toes. | Microvascular peripheral ischemia from chronic glucose variability. | Nocturnal glucose excursions (>180 mg/dL), sleep fragmentation, low HRV. |
| **Articular / Musculoskeletal** | Knee pain, joint stiffness, lumbar ache, walking discomfort. | Joint degeneration; physical limitation preventing post-meal walks. | Ambulatory step volume (<2,000 steps); identifies valid reasons for low kinetic activity. |
| **Neuro-Vascular Cephalalgia** | Throbbing headache, retro-orbital eye pressure, temple throbbing. | Hypertensive crisis or rapid glucose rate of rise (dG/dt > +2.5 mg/dL/min). | Systolic BP spikes (>140 mmHg) and acute postprandial glucose surges. |
| **Gastrointestinal / Visceral** | Bloating, acid reflux, stomach heaviness, delayed digestion, nausea. | Diabetic autonomic vagal neuropathy (gastroparesis) or drug intolerance. | Delayed glucose spikes occurring 3 to 5 hours post-consumption. |
| **Plantar Cutaneous Distress** | Foot tenderness, friction hotspot, blister pressure, heel fissure. | Biomechanical overload from sensory loss predisposing to ulceration. | Cumulative daily step peaks; prompts immediate physical foot check. |

---

## 13. HBand & HBand 2.0 Full Hardware Equivalence Model

BFM delivers complete functional equivalence with **HBand and HBand 2.0** across all Veepoo wearable telemetry streams:

| Wearable Stream | GATT Command / Protocol | HBand 2.0 Benchmark | BFM Workstation Implementation | Zero-Data Display Rule |
| :--- | :--- | :--- | :--- | :--- |
| **Optical Glucose** | `0xDF` / `0x87` / `0x89` Stream | Real-time glucose, TIR (70–180), daily average, eA1C/GMI. | Real-time tile, TIR breakdown, 48-slot 24-hr curve & `CH 01`. | Displays `0 mg/dL` until first physical scan. |
| **Hemodynamics (BP)** | `0xDF` / `0x02` Optical PPG | Systolic, Diastolic, MAP, morning surge staging. | Hemodynamic dual-line gauge & `CH 02: BLOOD PRESSURE`. | Displays `0 / 0 mmHg` until first physical scan. |
| **Resting & Peak HR** | `0xDF` / `0xD0` Continuous | Continuous HR timeline, RHR baseline, zones. | 24-Hour envelope (`00:00–23:59`) & `CH 03: HEART RATE`. | Displays `0 BPM` until first physical scan. |
| **HRV & Lorenz R-R** | `0xDF` / `0x08` R-R Scatter | Lorenz Poincaré plot (`8,859` watch R-R epochs), HRV (ms). | Real-time Poincaré scatter plot & `CH 06: HRV / STRESS`. | Displays `0 ms` until genuine R-R epochs sync. |
| **SpO2 & Nocturnal Apnea** | `0xDF` / `0x12` Red/IR Ratio | Nocturnal oxygenation, desaturation index, off-wrist holes. | Continuous oxygenation track & `CH 04: OXYGEN SpO2`. | Blue off-wrist band when un-worn; never false `0%`. |
| **Body Core & Skin Temp** | `0x88` Historical Thermal | Dual-channel Core & Skin curve (`00:00–23:59`, `113`+ rows). | Diurnal thermal curve & `CH 05: BODY TEMP (°F)`. | Displays `0.0 °F` until `0x88` sync. |
| **Steps & GLUT4 Walk** | `0xA8` / `0x07` / `0xA3` Sync | Cumulative steps matched to `2,000` watch goal (`0xA3`). | Kinetic timeline + `▲ GLUT4` walk badges (`CH 07`). | `<15`-step MCU debounce strictly respected. |
| **Sleep & Daytime Naps** | `0xE0` Multi-Session TLV | Total sleep, Deep, Light, REM, Awake (`00:01–23:59` + naps). | Full stacked hypnogram & `CH 08: SLEEP ARCH`. | Displays `0 hrs / 0 score` until nocturnal sync. |
| **Time Format & Config** | `0xB8` Page-0 Protected | Watch 12-hour vs 24-hour display format synchronized UI-wide. | Read-guard stops `b8 02 02 01` overwrite (`CLE-110`); re-renders charts live. | Respects `#cfgTimeFormat` (`timeFormatUserSet`). |
| **ECG, BIA & Hydration** | `0x96`/`0x97`, `0x93 0x05`/`0x06`, BOM (`fluid_oz`) | 25 mm/sec Lead-I ECG, BIA muscle %, `80.0 fl oz` hydration & Rx. | Live synced ECG QT/QTc + LF/HF, BIA skeletal muscle/fat/BMI/water & `CH 09`–`CH 13` (`Pain`, `Mood`, `Carbs`, `Fluid`, `Rx`). | Zero hardcoded fake meds (`Metformin` excised); `TODO-BLE-003` closed in `beta.00.01.0062`. |

---

## 14. Abrams Science Station Design System & Color Specifications

The BFM interface is authored to the **J.J. Abrams Enterprise bridge science station design system**:
* **Base Canvas**: Crisp luminous medical white and light slate (`#f8fafc` to `#ffffff`).
* **Card Containers**: Precision frosted glass cards with 1px subtle border (`#cbd5e1`) and clean drop shadows (`box-shadow: 0 1px 3px rgba(0,0,0,0.05)`).
* **Telemetry Accents**: Starfleet science ice-blue (`#0284c7`), cobalt (`#1d4ed8`), and cyan (`#06b6d4`).
* **Diagnostic Alerts**: Luminous clinical alert amber (`#f59e0b`), clinical nominal emerald (`#10b981`), and critical red (`#ef4444`).
* **Typography**: Modern clinical sans-serif paired with monospaced tabular numerals (`font-family: monospace; font-variant-numeric: tabular-nums`). Zero 1990s clunky pill bars.

---

## 15. Pure Zero-Data Reality Architecture & Empty-State Mandate

In strict accordance with clinical data sanctity:
1. **Un-synced Telemetry Zeroing**: All charts and gauges display zero (`0`) until valid physical watch transponder data is received. No synthetic sine curves or artificial baselines.
2. **Future Diurnal Truncation**: All 24-hour daily timeline curves are truncated at the current wall-clock hour (`new Date()`). All future hours (e.g. from 12:00 to 23:59) are strictly set to 0 or null.
3. **Empty Journal Default**: The Chronological Nutrition & Meal Journal and Exercise Journal initialize to completely empty arrays (`[]`). If no meals have been logged, the UI displays: `[NO NUTRITION LOGGED — JOURNAL EMPTY]`.
4. **Zero-Mock Prescription Table**: The medication table contains zero pre-populated drugs. Only medications explicitly entered by the user or clinician are displayed.
5. **Blank Patient Chart Number**: The MRN field is initialized to blank (`""`), displaying `[NOT ENTERED / UNASSIGNED]` until explicitly configured. Zero hardcoded synthetic MRN strings.
