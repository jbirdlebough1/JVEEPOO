# Standalone Single-File .HTM Clinical Workstation Proposal
## Universal Cross-Platform Architecture with 1-Year Storage, Automated USB/SD Backup & Zero-Variance Determinism

---

### Executive Summary

To achieve universal patient and clinical adoption, the **BFM Biometric Function Monitor** is architected as a **single, zero-install, double-click `.HTM` application** (modeled after the proven monolithic pattern of *JumpStart 3D*). 

This proposal establishes three non-negotiable architectural pillars:
1. **Long-Term Longitudinal Vault (&ge; 1 Year Continuous Storage)**: Preserves 365+ days of multi-resolution ambulatory telemetry locally within persistent browser storage.
2. **Automated Daily USB / SD Card External Backup**: Delivers automated, 1-click external backups to thumb drives and SD cards across all operating systems.
3. **Zero-Variance Clinical Determinism**: Ensures bit-for-bit identical physiological calculations, glycemic projections, and Epic FHIR R4 exports regardless of whether opened on Windows, macOS, Linux, ChromeOS, Android, or iOS/iPadOS.

| Workstation Layer | Platforms & Drivers | Key Responsibilities |
| :--- | :--- | :--- |
| **Workstation Client** | Windows 11 (`BFM.HTM` / `BFM_Launcher.exe`) | Monolithic `.HTM` workstation console; excludes Cell shortcuts & suppresses W11 Swift Pair toasts. |
| **Android Cell Companion** | Standalone Signed APK (`BFM-Cell.apk`, `com.bfm.cell`) | Offline-capable intake, HDYF feelings & 3-state daily Med app (zero BLE permissions; auto Wi-Fi sync). |
| **Hardware Driver** | Web Bluetooth GATT (0xFEE7) | Direct continuous BLE telemetry streaming from wrist sensor. |
| **Deterministic Core** | Standalone JavaScript / WASM | IEEE 754 fixed-precision math, GLUT4 kinetics, eA1C modeling. |
| **Local Vault** | SQLite-WASM / IndexedDB | 365-day continuous epoch storage (<25MB) with eviction shield. |
| **Dual Export** | File System Access API | Automated daily export: Format 1 (.sqlite) + Format 2 (FHIR R4 .json). |

---

### Pillar 1: Longitudinal Vault (&ge; 1 Year Storage Capacity)

#### 1. Mathematical Storage Footprint Analysis
A full year of continuous 24-hour ambulatory monitoring generates the following data footprint:

| Telemetry Stream | Frequency & Packet Size | Daily Footprint | 365-Day (1-Year) Total |
| :--- | :--- | :--- | :--- |
| **5-Min OriginV3 Full-Day Health (`0xDF` / 244B MTU)** | 288 epochs/day (`0xB1`–`0xC2` blocks: Glucose, HR, BP, SpO2, HRV/R-R) | ~14.4 KB / day | **~5.25 MB / year** |
| **Daily Core & Skin Temperature History (`0x88`)** | 144 slots/day (`0x00`–`0x8F` dual thermal curve, `00:00–23:59`) | 1.15 KB / day | **420 KB / year** |
| **Daily Kinetic Activity & Pedometer (`0xA8` / `0x07`)** | 48 half-hour step & GLUT4 slots + live pedometer sync (`2,000` goal) | 0.77 KB / day | **281 KB / year** |
| **Circadian Sleep Hypnograms & Naps (`0xE0`)** | Multi-session `0xA1 -> 0xA3+0xA5` TLV (nocturnal + daytime naps) | 0.24 KB / day | **88 KB / year** |
| **Meal Intake, Hydration & Nutritional BOM** | 4–6 logged meals/snacks + fluid oz (`fluid_oz`) | 2.50 KB / day | **912 KB / year** |
| **Pharmacological Doses & Regimen** | User/physician-entered Rx records (Zero synthetic fallback) | 0.40 KB / day | **146 KB / year** |
| **TOTAL ANNUAL TELEMETRY FOOTPRINT** | **All 13 channels synchronized (`w11_htm_app/telemetry.db`)** | **~19.5 KB / day** | **~7.1 MB / year** |

> [!NOTE]
> Even with SQLite indexing and relational metadata overhead, **1 full year of continuous ambulatory data occupies less than 15–25 MB**. 

#### 2. Storage Implementation: Inlined SQLite-WASM + Persistent IndexedDB
* **WASM SQLite Kernel**: Like JumpStart 3D’s inlined OpenCASCADE kernel, our single `.HTM` embeds the official `sqlite3-wasm` / `sql.js` engine directly inside an inlined `<script type="text/plain" id="sqlite-wasm-src">`.
* **Eviction Shield**: On launch, the `.HTM` calls:
  ```javascript
  if (navigator.storage && navigator.storage.persist) {
    const isPersisted = await navigator.storage.persist();
    console.log(`[VAULT] Browser persistent storage locked: {isPersisted}`);
  }
  ```
  This instructs Windows, macOS, Android, and iOS never to purge the database during routine disk cache cleanups.

---

### Pillar 2: Daily Automated External Backup to USB / SD Card

Physicians and risk managers require off-device redundancy so that device theft, hardware damage, or OS upgrades never result in lost health telemetry.

| Backup Execution Target | Mechanism & API | Automated Output Artifact | Verification Standard |
| :--- | :--- | :--- | :--- |
| **Desktop Chromium (`BFM.HTM` / `BFM_Launcher.exe`)** | File System Access API (`window.showDirectoryPicker()`) + `usb_backup_engine.py` | Deduplicated `<USB_DRIVE>:\BFM_VAULT\BFM_VAULT_YYYY-MM-DD.sqlite` + `BFM_BACKUP_LATEST.sqlite` | Persistent `IndexedDB` directory handle; zero duplicate overwrite clutter |
| **Android Field Companion (`BFM-Cell.apk`)** | Offline `localStorage` queue inside signed WebView + automatic home Wi-Fi sync (`checkHostConnection()`) | Meals, photos, voice memos, HDYF feelings & `/api/meds/daily_status` docked to PC SQLite | Confirmed SHA-256 & SQLite persistence on home Wi-Fi reconnect |

#### 1. Chromium Desktop & ChromeOS: The File System Access API
On Chrome, Edge, and Opera (Windows, macOS, Linux, ChromeOS):
* **One-Time Pairing**: The user clicks **"Bind USB / SD Backup Drive"** once. The browser displays `window.showDirectoryPicker()`. The user selects their thumb drive or SD card (e.g. `E:\BFM_VAULT`).
* **Persistent Handle**: The directory handle is serialized in `IndexedDB`.
* **Zero-Friction Daily Write**: Whenever a daily sync occurs, the application writes:
  `BFM_VAULT_YYYY-MM-DD.sqlite` and `BFM_BACKUP_LATEST.sqlite`
  directly to the external drive **without opening any download dialogs**.

#### 2. Mobile & Safari Fallback (Android, iPadOS, Safari)
* On browsers that restrict silent directory binding, the app provides a high-visibility ribbon badge:
  `💾 DAILY BACKUP DUE: CLICK TO WRITE TO USB/SD`
* A single tap triggers an instant HTML5 Blob download stream directly to the connected external SD card or USB-C drive via the OS native Files picker.
* **Auto-Check Verification**: Backups write an embedded SHA-256 integrity hash. On next insertion, the workstation validates the hash with zero data corruption.

---

### Pillar 3: Zero-Variance Determinism Across All Platforms

Physicians and endocrinologists require absolute consistency across views and platforms. Across the 13-channel 3D Multi-Channel viewer, the 13-Lane Synchronized Tall Chart, and exported Epic FHIR R4 JSON bundles, mean glucose, postprandial spikes, Time-in-Range (TIR), hydration (`fl oz`), and estimated A1c (eA1C / GMI) must agree to the last decimal place (`0.000%` variance).

| Execution Environment | Shared Dataset Source | Reported Convergence Metrics | Variance vs. Desktop |
| :--- | :--- | :--- | :--- |
| **Windows 11 Desktop (`BFM.HTM` / `BFM_Launcher.exe`)** | `getTallSynchronizedDataset()` & `w11_htm_app/telemetry.db` | Mean Glucose, TIR (70–180), Step Total (`2,000` goal), Hydration (`80.0 fl oz`), eA1C | **Baseline (`0.000%`)** |
| **Mobile Companion (`cell.html`) & Clinic Export (`13-EXPORT`)** | Identical REST `/api/telemetry/timeline` & FHIR R4 serializer | Identical glucose curve, meal BOM carbs, medication events, and LOINC codes | **`0.000%` (Bit-for-bit Concordance)** |

#### Deterministic Engineering Standards
1. **Fixed-Precision Math Core**:
   * All glycemic and kinetic formulas avoid floating-point drift by utilizing integer micro-units or strict rounding standards conforming to **CLSI EP05-A3** and **ISO 15197**:
     `Glucose_calibrated = round((Baseline + DeltaG_optical * K_cal) / 1000)`
2. **Identical SQLite Engine & Unified Chart Source**:
   * All 13 channels (`CH 01` Glucose through `CH 13` Medications Rx) read from the single `getTallSynchronizedDataset()` pipeline so the 3D perspective engine and 2D 13-Lane Tall Chart never diverge (`CLE-107`, `beta.00.01.0055`).
3. **Standardized Terminology & Units**:
   * Glucose: `mg/dL` (primary) and `mmol/L` (dual-scale).
   * Energy & Carbs: `kcal`, `g net carbs` (total carbs minus fiber), and `fl oz` fluid hydration.
   * Time: Watch-locked **12-hour (`0xB8` Page-0 guarded) or 24-hour** display format applied uniformly across every chart axis.

---

### Comparison: Deployment Architecture Matrix

| Capability | Current Two-Tier (PC + Python) | Proposed Standalone Hybrid `.HTM` |
| :--- | :--- | :--- |
| **Startup Experience** | Requires running Python in terminal / background | **Double-click `.HTM` file in any browser** |
| **Supported OS** | Windows PC only | **Windows, macOS, Linux, ChromeOS, Android, iPadOS** |
| **Hardware BLE Link** | Chrome Web Bluetooth or Python Bleak daemon | **Universal Browser Web Bluetooth (`navigator.bluetooth`)** |
| **Local Retention** | Hard disk SQLite (`telemetry.db`) | **1-Year Inlined SQLite-WASM + IndexedDB (&ge; 365 Days)** |
| **External Redundancy** | Manual file copy of `telemetry.db` | **Automated Daily USB / SD Backup via File System API** |
| **Doctor Experience** | Patient must bring PC or print screenshots | **Opens instantly on clinic workstation or iPad via USB/email** |
| **Clinical Determinism** | Python vs JS divergence risk | **Single deterministic JS/WASM mathematical engine (0.00% variance)** |
| **Interoperability** | Epic FHIR R4 JSON download | **Epic FHIR R4 JSON + PDF export on all devices** |

---

### Current Beta Cross-Platform Compatibility (macOS & Linux)

`BFM.HTM` is OS-agnostic HTML5/CSS/JS with zero Windows dependencies, and `w11_htm_app/server.py` is cross-platform Python (`bleak` supports macOS CoreBluetooth and Linux BlueZ; Windows registry calls are guarded by `if sys.platform != "win32"`). **We are actively seeking developers with Type 2 Diabetes to test and validate BFM on macOS and Linux platforms.**

| Platform | `BFM.HTM` UI & Watch BLE | `w11_htm_app/server.py` (SQLite + Cell LAN + BLE) | Notes / Requirements |
| :--- | :--- | :--- | :--- |
| **macOS** | **Yes** (Chrome / Edge / Brave) | **Yes** (`python3 w11_htm_app/server.py`) | **Safari & Firefox lack Web Bluetooth** — use Chrome/Edge for direct watch link; macOS prompts once for Bluetooth permission. |
| **Linux** | **Yes** (Chrome / Chromium / Edge) | **Yes** (`python3 w11_htm_app/server.py`) | Requires `bluez` running; in Chrome/Chromium on Linux enable `chrome://flags/#enable-experimental-web-platform-features` for Web Bluetooth (not needed if using `server.py` `bleak` sync). |
| **Windows-Only Pieces** | None in `BFM.HTM` | Shortcut button route only (`server.py` PowerShell shortcut endpoint) | `BFM_Launcher.exe` and the Windows shortcut installer route are Windows-only and not needed on macOS/Linux. |

---

### Implementation Milestones

1. **Phase 1: Inlined SQLite-WASM & Deterministic Math Core**
   * Package `sqlite3-wasm` as an inlined script blob inside `index.html`.
   * Standardize all nutritional, kinetic, and hemodynamic formulas to fixed-precision integer math.
2. **Phase 2: Universal Web Bluetooth & 1-Year FIFO Storage**
   * Wire native `navigator.bluetooth` GATT handlers directly in the client thread.
   * Configure 365-day multi-resolution schema with automated circular rollover protection.
3. **Phase 3: Automated Daily USB / SD Card Backup Interface**
   * Add `window.showDirectoryPicker()` UI to bind external thumb drives / SD cards.
   * Add automated daily backup trigger upon synchronization or session close.
4. **Phase 4: Multi-Platform Validation Audit**
   * Execute cross-platform testing (Windows Edge, Mac Safari/Chrome, Android Chrome, iPad Safari) using identical test telemetry vectors to certify **0.00% variance**.
