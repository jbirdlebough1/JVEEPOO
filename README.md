# JVEEPOO — FITAOS Model 5010 LCARS 360° Continuous Health Console & Clinical Telemetry Suite

### *Turnkey Windows 11 Physiological Workstation, Non-Invasive Optical Tonometry Glucose Profile, 8.3% HbA1c Calibration Engine & 8-Stream Biometric Synthesis*

[![Platform: Windows 11](https://img.shields.io/badge/Platform-Windows%2011%20%7C%20x64-blue.svg)](https://github.com/jbirdlebough1/JVEEPOO)
[![Hardware: Model 5010](https://img.shields.io/badge/Hardware-FITAOS%20%2F%20Veepoo%20Model%205010-orange.svg)](https://github.com/jbirdlebough1/JVEEPOO)
[![Architecture: LCARS / Starfleet Cybernetics](https://img.shields.io/badge/Architecture-LCARS%2024th%20Century-purple.svg)](https://github.com/jbirdlebough1/JVEEPOO)
[![Consensus: ADA / ADAG Algorithm](https://img.shields.io/badge/Clinical-ADA%20%2F%20ADAG%20eA1C-green.svg)](docs/clinical_glucose_study_and_a1c_analysis.md)
[![Ecosystem: HBandSDK](https://img.shields.io/badge/Ecosystem-HBandSDK%20%7C%20Veepoo-brightgreen.svg)](https://github.com/HBandSDK)
[![License: GPL v3 / Open Source](https://img.shields.io/badge/License-GPL%20v3%20%2F%20Open%20Source-green.svg)](LICENSE)

---

## 🎬 Interactive Technical Presentation (40-Slide Audio-Visual Walkthrough)

Before running or configuring the telemetry console, you can explore the complete audio-visual technical walkthrough directly in your browser using our auto-advancing blueprint player with synchronized voice narration:

👉 **[Launch Presentation: LCARS 5010 Clinical Telemetry Architecture (Interactive Web Player)](presentation/preview.html)**  
*(40-slide clinical architecture walkthrough: covers the clinical rationale, physician co-configuration, GLUT4 glycemic exercise kinetics, 8-stream biometric synthesis, medication titration tracking, and ADA goal management with Australian voice narration and live telemetry scrolling)*

* 📁 **Source Assets & Narration Script**: [`presentation/`](presentation/) | [`presentation/TRANSCRIPT.md`](presentation/TRANSCRIPT.md)
* 💻 **Standalone Windows 1-Click Launcher (Self-Contained)**: [`Preview_Presentation.exe`](Preview_Presentation.exe) *(Zero install, native Windows 11 launcher — double-click to launch the presentation instantly in your browser)*
* 🩺 **Physician's Master Clinical Specification**: [`docs/generate_cmo_spec.py`](docs/generate_cmo_spec.py) | [`docs/cmo_spec.html`](docs/cmo_spec.html)

---

## ⚡ Fast Operator Quickstart (Windows 11 Workstation)

Run the full LCARS 5010 clinical telemetry environment on any Windows 11 machine without complex dependencies:

### 1. Launch Multithreaded Local Server
```powershell
# From the repository root:
& 'tools\python\python.exe' w11_htm_app\server.py
```
*The server initializes on `http://localhost:8899/` with automatic no-cache headers for instant zero-latency UI reloads.*

### 2. Connect Continuous Watch Monitor
```powershell
# In a secondary terminal:
& 'tools\python\python.exe' opt3_ble_python\watch_monitor.py
```
*Discovers and pairs with FITAOS Model 5010 over Bluetooth Low Energy (`8B:DE:40:83:F6:16`), streaming continuous 24-hour optical tonometry, dual-lead ECG, blood pressure, core/skin temperature, and pulse oximetry directly into SQLite (`telemetry.db`).*

### 3. Open Operational Console
Open any modern browser to **`http://localhost:8899/`**.

---

## 🩺 Clinical Telemetry Features & Architecture

### 1. 360-Degree Real-Time Physiological Console
Correlates 8 simultaneous biometric streams into a unified Starfleet LCARS visual display:
1. **Continuous Glucose Tonometry (60 - 200 mg/dL target range)**
2. **Estimated A1C Projection Curve (ADAG Formula: $eA1C = \frac{\text{Glucose} + 46.7}{28.7}$)**
3. **Core vs. Skin Body Temperature Dynamics (°F)**
4. **Nocturnal Sleep Hypnogram & Staging (Deep Delta, Light, REM Dream)**
5. **Hourly Exercise Step Kinetics & GLUT4 Muscle Contraction Translocation**
6. **Cardiovascular Stress & 5-Zone Cardiac Load (BPM)**
7. **Ambulatory Blood Pressure Oscillometry (Systolic / Diastolic mmHg)**
8. **Nocturnal SpO2 Stability & Autonomic HRV SDNN Recovery (ms)**

### 2. 24-Hour Multi-Line Staggered Timeline Charts
- **Zero-Overlap X-Axis Architecture**: Separates time ticks (`00:00`, `04:00`, `07:00`, etc.) onto Line 1 while staggering event badges (`(Nadir)`, `(Fasting)`, `(Morn Walk)`, `(GLUT4 Clear)`, `(Lunch Peak)`, `(Dinner Peak)`) across alternating Lines 2 and 3 with full 128px horizontal clearance.
- **Reference Line & Data Halos**: 4px high-contrast white halos isolate data values and clinical reference lines (`8.3% eAG ANCHOR: 191.5`, `CLINICAL CEILING: 200 MG/DL`, `CLINICAL FLOOR: 60 MG/DL`) to eliminate visual collision.

### 3. Dual Morning & Evening Medication Intake Schedule
- **Intake 1 (Morning)**: Full scrollable 8-medication clinical suite (Metformin ER, Glipizide XL, Empagliflozin, Lisinopril, Atorvastatin, Omega-3, Vitamin D3, CoQ10).
- **Intake 2 (Evening)**: Full evening regimen with dosage, indication, and adherence tracking.
- **Titration Markers**: Pinning medication dose modifications automatically generates clinical timeline flags to track longitudinal response.

### 4. Senior Accessibility & Senior Vision Contrast Overhaul
- **Luminance Contrast Standard**: Overhauled from pastel backgrounds to solid white `#ffffff` cards with 21:1 deep navy `#091428` typography.
- **Saturated Readout Colors**: Bold crimson `#dc2626` (Heart Rate), cobalt electric blue `#0284c7` (Temperature), forest green `#15803d` (SpO2), and amber `#d97706` (Blood Pressure).
- **Tactical Bridge Night Vision**: Preserves dark adaptation and rhodopsin with red, green, and high-contrast monochrome themes.

---

## 📊 H-Band vs. LCARS Feature Comparison Matrix

| Clinical & Telemetry Capability | Stock H-Band Mobile App | LCARS 5010 Workstation Console |
| :--- | :---: | :---: |
| **Longitudinal Display Architecture** | Fragmented isolated tabs | **Unified 360° Single-Screen Telemetry Matrix** |
| **Glucose Calibration Mode** | Static linear offset | **Clinical 8.3% HbA1c Tonometry Anchor (191.5 mg/dL)** |
| **Mathematical eA1C Projection** | ❌ Not available | **ADAG Algorithm Real-Time Projection Curve** |
| **Exercise vs Glycemia Correlation** | Raw daily step counter | **Hour-by-hour GLUT4 glucose drop correlation** |
| **Dual Temperature Differential** | Single uncalibrated metric | **Simultaneous Core (°F) vs Skin (°F) Matrix** |
| **Medication Schedule & Titration** | ❌ None | **Morning (Intake 1) & Evening (Intake 2) Suite** |
| **Clinical Senior Accessibility** | Small gray low-contrast text | **21:1 Solid White Cards, Bold Crimson/Cobalt Numerals** |
| **Physician Consultation Export** | ❌ Screenshot only | **Standardized CSV & Print-Ready Clinical Summary** |
| **Offline Privacy & Security** | Cloud server synchronization | **100% Local SQLite & Bluetooth LE (Zero Cloud Dependency)** |

---

---

## 🌐 HBandSDK Ecosystem & Smartwatch Applications Repository

**JVEEPOO** serves as an open-source desktop application extending the official [HBandSDK GitHub Ecosystem](https://github.com/HBandSDK) to Windows 11, asynchronous Python telemetry daemons, and clinical visual workstations:

* 🏢 **Official HBandSDK GitHub Organization**: [https://github.com/HBandSDK](https://github.com/HBandSDK)
* 📱 **Official Android BLE SDK**: [`HBandSDK/Android_Ble_SDK`](https://github.com/HBandSDK/Android_Ble_SDK) — Native Android BLE protocol library (`com.veepoo.protocol`).
* 🍏 **Official iOS BLE SDK**: [`HBandSDK/iOS_Ble_SDK`](https://github.com/HBandSDK/iOS_Ble_SDK) — Native iOS Objective-C BLE protocol.
* 💬 **Official WeChat Mini Program SDK**: [`HBandSDK/WeChat_Mini_Program_Ble_SDK`](https://github.com/HBandSDK/WeChat_Mini_Program_Ble_SDK) — TypeScript BLE connectivity.
* 🌐 **Official HarmonyOS SDK**: [`HBandSDK/HarmonyOS_BLE_SDK`](https://github.com/HBandSDK/HarmonyOS_BLE_SDK) — HarmonyOS wearable integration.
* 🖥️ **Windows 11 / Python Clinical Workstation**: [`jbirdlebough1/JVEEPOO`](https://github.com/jbirdlebough1/JVEEPOO) — Production Windows 11 single-page application, 24-hour optical tonometry glucose engine, 8.3% HbA1c calibration anchor, and 8-stream biometric synthesis.

### Protocol Compatibility Matrix:
| Protocol Opcode | Description | HBandSDK Native Class | JVEEPOO Python BLE Driver |
| :---: | :--- | :--- | :--- |
| `0x89` | Optical Blood Glucose Tonometry | `VPOperateManager.startBloodGlucose()` | `watch_monitor.py: start_glucose_private()` |
| `0x80` | Nocturnal SpO2 & Apnea Detection | `VPOperateManager.startSpo2()` | `watch_monitor.py: start_spo2()` |
| `0xD0` | Continuous 24h Optical Heart Rate | `VPOperateManager.startHeartRate()` | `watch_monitor.py: start_heart_rate()` |
| `0xA0` | Battery & Charge Subsystem | `VPBatteryManager` | `watch_monitor.py: BATTERY` |
| `0xA8` | 32-Bit Step & Exercise Accelerometer | `VPStepManager` | `watch_monitor.py: PEDOMETER` |

## 🗃️ Repository Structure

```
D:\veepoo\
├── README.md                           # Master Project Documentation & Quickstart
├── Preview_Presentation.exe            # Windows 11 Native 1-Click Presentation Launcher
├── presentation\                       # 40-Slide Presentation Suite
│   ├── preview.html                    # Auto-advancing interactive presentation player
│   ├── TRANSCRIPT.md                   # Full 40-slide narration transcript
│   ├── SimpleLauncher.cs               # C# source for standalone native launcher
│   └── slides\                         # Individual HTML slide templates (01 to 40)
├── w11_htm_app\                        # Core LCARS Web Application
│   ├── index.html                      # LCARS 5010 Master Single-Page Application
│   ├── server.py                       # Multithreaded local server daemon (Port 8899)
│   ├── telemetry.db                    # SQLite biometric database
│   └── sounds\                         # Starfleet LCARS tactile audio chimes
├── opt3_ble_python\                    # Bluetooth Low Energy Device Drivers
│   ├── watch_monitor.py                # Live BLE monitor daemon for Model 5010
│   └── vpo_protocol.py                 # Reverse-engineered Veepoo/FITAOS binary protocol
├── docs\                               # Clinical Documentation & Specification
│   ├── cmo_spec.html                   # Chief Medical Officer clinical specification
│   ├── generate_cmo_spec.py            # CMO spec HTML & vector PDF generator
│   └── clinical_glucose_study...md     # Clinical analysis of HbA1c calibration
├── session_logs\                       # Full Engineering Trajectory & Transcripts
│   ├── transcript.jsonl                # Compact step-by-step engineering trajectory
│   └── transcript_full.jsonl           # Complete full-text engineering trajectory
└── tools\                              # Embedded runtimes and utilities
    └── python\                         # Standalone Python 3.11 environment
```

---

## 🐛 Bug Tracker Integration (`CLEBUGGER`)

Every issue and architectural evolution is formally logged in the local CLEBUGGER database (`D:\CLEBUGGER\bugs.db`):
- **`CLE-027`**: Dynamic Heart Rate Matrix & universal biometrics bay senior contrast overhaul (`RESOLVED`).
- **`CLE-028`**: Elimination of UI element overlaps, Cardio layout restructuring, and Morning med list expansion (`RESOLVED`).
- **`CLE-029`**: SVG Timeline multi-line X-axis separation (Line 1 Time, Lines 2 & 3 Events) and 4px white reference line halos (`RESOLVED`).
- **`CLE-030`**: Comprehensive terminology normalization: Complete removal of "Cockpit" in favor of "Console" and "Clinical Interface" (`RESOLVED`).

---

## 📜 Session Logs (`.jsonl`)

In accordance with project development protocols, complete conversation transcripts and tool invocation trajectories are committed directly in [`session_logs/`](session_logs/):
- [`transcript.jsonl`](session_logs/transcript.jsonl): Step-by-step trajectory of all commands and operations.
- [`transcript_full.jsonl`](session_logs/transcript_full.jsonl): Full un-truncated context logs.

---

> [!CAUTION]
> **RESEARCH & CLINICAL DISCLAIMER**  
> This software is experimental research and an assistive telemetry reference tool for diabetic maintenance. It does not replace professional medical diagnosis, continuous blood glucose monitoring (CGM) prescribed by a physician, or emergency medical treatment. Always consult with your attending endocrinologist or physician regarding medication titration and dietary adjustments.
