# BFM Biometric Function Monitor: Functional Specification (FS)
## Beta 1 Release: Universal Personal Biofunction Platform & Zero-Regression Architecture

---

## 1. Executive Summary & Engineering Charter

* **Document Purpose**: Formal technical contract establishing the exact architecture, code migration plan, database schemas, and quality controls for the **Beta 1 Build** of the **BFM Biometric Function Monitor** (Console Powered By Gemini).
* **Core Paradigm Shift**: Transitioning from rapid ad-hoc prototyping to **formal pre-build functional specifications, failure mode analysis (FMEA), and Six Sigma zero-regression enforcement**.
* **The Author's 20-Year AI Provenance (Since 2007)**: Engineering AI and cloud systems for nearly two decades. After testing early Gemini, the author spent **18 grueling months fighting Claude's chronic development issues** (context drift, logic hallucinations, and regressive syntax breaks). Returning to **current-generation Google Gemini** produced **immediate, transformative success**: dramatically lower defects, rock-solid architectural compliance, and superior full-stack code quality.
* **Open Engineering Trajectory (`.jsonl`)**: In an unprecedented commitment to scientific rigor, the author has committed the **entire 14.3+ MB raw `.jsonl` trajectory (`session_logs/transcript.jsonl`) directly into the public build tree**. Google AI engineers, DeepMind researchers, and community developers can inspect real-world turn-by-turn performance, bug post-mortems, and iterative learning.
* **Rigorous Systems Engineering over Random Comments**: Eliminates cryptic commits and random comments. Every architectural decision on Star Ship Gemini NCC-202X is backed by formal Requirements, Functional Specifications (FS), and FMEA risk post-mortems.
* **Author & Philosophical Leader**: **The Author** (Creator, Chief Architect, and Leader of the BFM Philosophy)
* **Corporate & Technology Sponsor**: **Google** (Sponsoring, Hosting, and Powering via Google Gemini AI)
* **Base Vessel**: **Star Ship Gemini (NCC-202X)** (Desktop Workstation Console)
* **Auxiliary Units**: **Shuttlecraft Foodoor** (Mobile PWA, registered as **Cell 1, Cell 2, Cell 3...**)
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
| **REQ-01** | BFM Console • Star Ship Gemini NCC-202X Branding | Title Bar & Main Header | `index.html` Line 6, 1629 | AST & DOM String Match |
| **REQ-02** | Complete removal of "5010" across UI and code | Universal Wearable Layer | `index.html`, `server.py` | Strict Gate Check 8 (Forbidden Terms) |
| **REQ-03** | Complete removal of "Patient" and "Clinical" | Liability & Regulatory Shield | All `.html`, `.py`, `.md` | Strict Gate Check 8 (Forbidden Terms) |
| **REQ-04** | Star Trek limited strictly to presentation intro | IP & Trademark Quarantine | `presentation/TRANSCRIPT.md` | Strict Gate Check 8 (Forbidden Terms) |
| **REQ-05** | Copyleft Disclaimer & Immutable User Acceptance | Legal Acceptance Modal | `index.html` (Tab 10), SQLite | Query `user_legal_acceptance` |
| **REQ-06** | 3-Way Triad Binding: UID <-> ESN <-> Watch MAC | Multi-Tenant Dispatcher | `server.py` & `watch_monitor.py` | Multi-Source Concurrent Ingest Test |
| **REQ-07** | Registered Family Cells & Watch Roster on Tab 10 | Fleet Manager UI | `index.html` (Tab 10 Bottom) | DOM Element Inspection & CRUD Tests |
| **REQ-08** | Auto-Scan QR Handshake (Phone Name + ESN) | Mobile Onboarding Engine | `cell.html` + `/api/shuttle/register` | Automated ESN Reflection Test |
| **REQ-09** | Shuttlecraft Foodoor Multi-Day Offline Autonomy | Mobile Client Vault | `cell.html` (IndexedDB) | Disconnected Mode Intake Test |
| **REQ-10** | Homecoming Data Transfer with Zero Data Loss | Bulk Ingestion Engine | `server.py` + `cell.html` | SHA-256 Receipt Verification |
| **REQ-11** | Automated Daily Dual USB/SD Backup | Removable Storage Sync | `index.html` (Tab 05) | File System Access API Export Test |
| **REQ-12** | Complete refactor from `lcars-` to `bfm-` | Modular CSS/DOM Standard | `index.html`, `cell.html` | Strict Gate Check 3 (DOM Hierarchy) |
| **REQ-13** | Developer Incentives & Unlimited Gemini Assets | Open Source Ecosystem | `docs/GOOGLE_LEADERSHIP_PRIMER.md` | Institutional Review Board Portal |
| **REQ-14** | Zero Defect Mandate & Visual Cleanliness Standard | Quality Assurance Subsystem | `audit_zero_defect.py` | Automated Zero Defect Pre-Commit Gate |
| **REQ-15** | Canonical Build Naming Standard (`beta.00.01.0001`) | Versioning Subsystem | All UI, API & Manifests | Regex & Checksum Parity Gate |
| **REQ-16** | Two-Pass Master Test Plan & CLEBugger Integration | Quality Assurance / TQM | `docs/bfm_beta1_master_test_plan.md` | Automated Pass 1 + Human Pass 2 via `BFM.HTM` |
| **REQ-17** | Human-Centric User & Physician Adoption Metric | Behavioral Feedback Engine | Longitudinal Vault & FHIR | Daily Active Use (>=90%) & Doctor FHIR Review |
| **REQ-18** | Subjective Experiential Telemetry ("How Do You Feel?") | Behavioral Psychology / HDYF | `cell.html`, `server.py`, DB | Mobile HDYF Prompt, Home Wi-Fi Sync & FHIR Export |

---

## 3. Alpha Code Audit, Reuse & Migration Inventory

An exhaustive audit of the 23,000+ lines of Alpha codebase was conducted to categorize every existing asset into three distinct migration streams:

| Migration Stream | Source Alpha Asset | Scope & Target Module | Technical Rationale & Architectural Rule |
| :--- | :--- | :--- | :--- |
| **Stream 1: Verbatim Reuse**<br>(100% Preserved) | `watch_monitor.py`<br>`server.py` Sec 08<br>`index.html` Tab 04 | • 6-Sensor Optical BLE Pipeline (0x89, 0xD0, 0x80, 0xA0, 0xA8)<br>• ADAG eA1C Math: `(Glucose + 46.7) / 28.7`<br>• Circadian 8-Lane Timelines with high-contrast halos<br>• Locked Conagra / USDA Nutrition Truth Tables | Reverse-engineered sensor packet handlers and clinical math pass zero-variance audits. Preserved without modification to prevent regressions. |
| **Stream 2: Refactored Modules**<br>(Structural Hardening) | `index.html`<br>`cell.html`<br>`server.py`<br>`watch_monitor.py` | • CSS/DOM: 1,847 replacements (`lcars-` -> `bfm-`)<br>• Universal Sensor Array (strip legacy "5010")<br>• Multi-Tenant Storage: `telemetry_user_<uuid>.db`<br>• Dynamic Dispatcher: 3-Way Triad Routing | Eliminates CSS naming collisions, decodes multi-user hardware concurrent streams, and isolates family health vaults. |
| **Stream 3: Newly Engineered**<br>(Beta 1 Capabilities) | `index.html` Tab 10<br>`server.py`<br>`cell.html` PWA<br>`strict_gate_v2.py` | • Tab 10 Family Cells & Wearables Master Roster<br>• Auto-Scan Handshake (`/api/shuttle/register`)<br>• Offline Mobile Vault (`cell.html` IndexedDB)<br>• Automated Daily Dual USB/SD Backup (`.sqlite` + FHIR)<br>• Legal Startup Acceptance Gate Modal & DB Table | Delivers multi-user family PC routing, zero-data-loss mobile field autonomy, and automated hospital interoperability. |

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
  * Workstation title updated to `BFM Console • Star Ship Gemini NCC-202X (Powered By Gemini)`.
  * Mobile companion updated to `Shuttlecraft Foodoor • Cell 1 (or Cell 2, Cell 3)`.

#### 3. Newly Engineered Components:
* **Tab 10 Family Cells & Wearables Roster**: Interactive HTML/CSS table with CRUD buttons (`Add Cell & User`, `Edit`, `Delete`), listing Cell #, Registry, User Name, Phone Name, Hardware ESN, Linked Watch MAC, and Status.
* **Auto-Discovery Handshake (`/api/shuttle/register`)**: Receives mobile hardware parameters (`phone_name`, `phone_esn`) and establishes binding without user typing.
* **Hardware ESN Ingestion Router (`/api/shuttle/dock`)**: Validates device credentials and dispatches field intake batches directly into the owner's isolated database.
* **IndexedDB Offline Mobile Vault (`cell.html`)**: Stores food photos (BLOBs), audio transcripts, and Gemini breakdown JSON locally during multi-day away trips.
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
| **Dad's Shuttlecraft (Wi-Fi)** | `ESN-9482-A018-7F` | Matches `phone_esn` -> `USR-001` | `telemetry_user_USR-001.db` | Two-phase commit with SHA-256 receipts |
| **Mom's Wrist Watch (BLE)** | `MAC C4:12:9A:33:02:51` | Matches `linked_watch_mac` -> `USR-002` | `telemetry_user_USR-002.db` | Streamed into Mom's vault in background |
| **Mom's Shuttlecraft (Wi-Fi)** | `ESN-7731-B942-3C` | Matches `phone_esn` -> `USR-002` | `telemetry_user_USR-002.db` | Zero cross-talk with Dad's active tab |
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

## 8. Ten-Point Six Sigma Pre-Build Quality Gate

To prevent regressions, the pre-commit gate (`strict_gate_v2.py`) enforces an exhaustive **10-Point Six Sigma Gate**:

| Check ID | Verification Target | Target File / Artifact | Standard of Zero Regression |
| :---: | :--- | :--- | :--- |
| **Check 01** | Dual-File SHA-256 Parity | `index.html` == `w11_htm_app/index.html` | Bit-for-bit identical cryptographic checksum. |
| **Check 02** | Strict Div Tag Balance | `index.html` & `cell.html` | Opens == Closes, Div balance difference == 0. |
| **Check 03** | DOM Workspace Hierarchy | `index.html` | All tab pages are direct children of `<main.bfm-workspace>`. |
| **Check 04** | JavaScript AST Syntax | `index.html` | Node.js syntax verification passes with zero warnings. |
| **Check 05** | Python Server AST | `server.py` | Python bytecode compilation passes with zero syntax errors. |
| **Check 06** | SQLite 3-Way Triad DDL | `telemetry_master.db` | Foreign keys and UNIQUE constraints verified on ESN & MAC. |
| **Check 07** | Confectionery Safety Math | `popular_meals` table | Locked truth tables; zero high-sugar confections. |
| **Check 08** | Zero Monetary Cost Strings | Tab 09 BOM Headers | Zero monetary price residue; strictly metabolic impact metrics. |
| **Check 09** | Zero Forbidden Terms | Entire Workspace | 0 instances of "5010", "Patient", "Clinical", "Star Trek". |
| **Check 10** | Concurrent Ingest Test | Hardware Triad Dispatcher | Mock watch + cell concurrent sync with 0 cross-talk. |

---

## 9. Phased Implementation Roadmap for Beta 1

| Phase | Milestone Name | Key Engineering Deliverables | -Werror Quality Gate Check |
| :---: | :--- | :--- | :--- |
| **Phase 1** | Database & Backend Engine | `telemetry_master.db`, `registered_cells`, `/api/shuttle/dock`, dynamic BLE packet router | Schema constraint verification & SQLite connection routing test |
| **Phase 2** | Workstation UI Refactoring | CSS migration `lcars-` -> `bfm-`, Tab 10 Master Roster, Legal Acceptance Modal | Strict Div Balance (Opens == Closes == 1965, Diff == 0) |
| **Phase 3** | Mobile Auxiliary Client | `cell.html` device interrogation, IndexedDB offline vault, Homecoming Dock button | ESN reflection test & IndexedDB disconnected ingestion test |
| **Phase 4** | Dual External Storage Backup | Automated daily `.sqlite` + Epic FHIR R4 `.json` bundle generator with manifests | File System Access API export & schema validation |
| **Phase 5** | Verification & Certification | End-to-end multi-device concurrent sync verification & documentation freeze | 10/10 Six Sigma Pre-Build Gate Pass (`strict_gate_v2.py`) |

1. **Phase 1: Database & Backend Engine**:
   * Implement `telemetry_master.db` with `user_profiles`, `registered_cells` (with `linked_watch_mac`), and `user_legal_acceptance`.
   * Implement endpoints `/api/shuttle/provision`, `/api/shuttle/register`, and `/api/shuttle/dock`.
   * Refactor BLE ingestion in `watch_monitor.py` / `server.py` to route packets by source `watch_mac`.
2. **Phase 2: Workstation UI Refactoring (`index.html`)**:
   * Refactor CSS/DOM from `lcars-` to `bfm-`.
   * Update title and header to **BFM Console • Star Ship Gemini NCC-202X (Powered By Gemini)**.
   * Add the **Registered Family Cells & Wearable Watch Roster** to Tab 10 bottom with Add/Edit/Delete modals.
   * Add the Startup Legal Acceptance Modal.
3. **Phase 3: Mobile Auxiliary Client (`cell.html`)**:
   * Implement auto-scan device interrogation on load (pulling `phone_name` and cryptographic ESN).
   * Implement IndexedDB offline vault for field meals.
   * Implement the one-click Homecoming Docking transfer button.
4. **Phase 4: Dual External USB/SD Storage Backup**:
   * Implement automated daily `.sqlite` + Epic FHIR R4 `.json` bundle generator with SHA-256 manifests.
5. **Phase 5: Verification & Gate Certification**:
   * Execute 10-point Six Sigma gate (`strict_gate_v2.py`).
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
