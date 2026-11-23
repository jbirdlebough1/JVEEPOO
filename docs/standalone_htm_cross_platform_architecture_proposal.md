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
| **Client Platforms** | Windows PC, Apple Mac, Chromebook, Android, iPadOS | Zero-install single-file HTM execution via modern browser. |
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
| **5-Min Continuous Health (0xA7)** | 288 epochs/day &times; 8 bytes (Glucose, HR, BP, SpO2) | 2.30 KB / day | **840 KB / year** |
| **Daily Kinetic Activity (0x89)** | 48 half-hour step & burn slots &times; 16 bytes | 0.77 KB / day | **281 KB / year** |
| **Circadian Sleep Hypnograms (0xE0)** | 6 packets/night (Deep, Light, REM, Awakenings) | 0.12 KB / day | **44 KB / year** |
| **Meal Intake & Nutritional BOM** | 4–6 logged meals/snacks &times; 500 bytes JSON | 2.50 KB / day | **912 KB / year** |
| **Pharmacological Doses & Regimen** | Morning & Dinner split records &times; 200 bytes | 0.40 KB / day | **146 KB / year** |
| **Periodic 1 Hz Workout Sessions** | 30-min brisk walk @ 1 sample/sec (Pulse stream) | 3.60 KB / session | **1.31 MB / year** |
| **TOTAL ANNUAL TELEMETRY FOOTPRINT** | **All 8 channels synchronized** | **~9.7 KB / day** | **< 3.55 MB / year** |

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

Physicians and clinical risk managers require off-device redundancy so that device theft, hardware damage, or OS upgrades never result in lost health telemetry.

```
[Watch Sync Complete] > [Append to 1-Yr SQLite-WASM Vault]
                                    
                  Daily External Backup Triggered
                                    
         
         ▼                                                     ▼
[Chromium: Win, Mac, Linux, ChromeOS]              [Mobile / Safari Fallback]
  • File System Access API                           • Programmatic Blob Stream
  • One-Time USB/SD Folder Bind                      • 1-Click System Save Dialog
  • Silent background write to:                      • Direct export to external drive
    <USB_DRIVE>:\BFM_VAULT\daily_backup.sqlite       via native Files app
```

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

### Pillar 3: Zero-Variance Clinical Determinism Across All Platforms

Doctors and endocrinologists look for absolute consistency. If a patient displays a 14-day report on an iPad during a clinic visit, the mean glucose, postprandial spikes, Time-in-Range (TIR), and estimated A1c (eA1c) must match the numbers on their home Windows PC down to the last decimal place.

```
       [Raw Optical PPG Absorption & Accelerometry Telemetry]
                                 
     
     ▼                                                       ▼
[Patient Home PC: Windows 11]                   [Clinic Consultation: iPadOS]
  • Calculated Mean Glucose: 139.4 mg/dL          • Calculated Mean Glucose: 139.4 mg/dL
  • Time-in-Range (70–180): 88.2%                 • Time-in-Range (70–180): 88.2%
  • Projected Excursion: +54 mg/dL                • Projected Excursion: +54 mg/dL
  • eA1c: 6.48%                                   • eA1c: 6.48%
                                                            
     
                                 ▼
         [VARIANCE = 0.000% • BIT-FOR-BIT CLINICAL CONCORDANCE]
```

#### Deterministic Engineering Standards
1. **Fixed-Precision Math Core**:
   * All clinical formulas avoid nondeterministic floating-point drift by utilizing fixed-precision integer math or strict rounding standards conforming to **CLSI EP05-A3** and **ISO 15197**.
   * Example: Glucose calculations compute in integer micro-units before final display formatting:
     Glucose}_{calibrated}} = round}(Baseline} + {\Delta G_{optical}} \times K_{cal}}}{1000})
2. **Identical Inlined SQLite Engine**:
   * By compiling the exact same `sqlite3-wasm` binary into the `.HTM`, SQL queries (`ORDER BY`, `GROUP BY`, date formatting, aggregate means) execute with identical C-level bytecode semantics on ARM64 (Apple Silicon, Android) and x86_64 (Windows, Intel).
3. **Standardized Clinical Terminology & Units**:
   * Glucose: mg/dL} (primary) and mmol/L} (dual-scale).
   * Energy & Carbs: kcal}, g net carbs} (carbs minus fiber).
   * Time: ISO 8601 timestamps (`YYYY-MM-DDTHH:MM:SSZ`) anchored to device UTC offsets to prevent time-zone distortion when traveling.

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
