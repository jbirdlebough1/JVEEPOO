# JVEEPOO — FITAOS Model 5010 BFM 360° Continuous Health Console & Clinical Telemetry Suite

### *Turnkey Physiological Workstation, Non-Invasive Optical Tonometry Glucose Profile, 8.3% HbA1c Calibration Engine & 8-Stream Biometric Synthesis*

[![Platform: Cross-Platform](https://img.shields.io/badge/Platform-Windows%2011%20%7C%20macOS%20%7C%20Android%20%7C%20iOS%20%7C%20ChromeOS-blue.svg)](https://github.com/jbirdlebough1/JVEEPOO)
[![Hardware: Model 5010](https://img.shields.io/badge/Hardware-FITAOS%205150%20%2F%20Veepoo-orange.svg)](https://fitaos.com)
[![Aesthetic: BFM / Starfleet Sickbay](https://img.shields.io/badge/Aesthetic-BFM%20Biofunction%20Monitor-purple.svg)](https://github.com/jbirdlebough1/JVEEPOO)
[![Consensus: ADA / ADAG Algorithm](https://img.shields.io/badge/Clinical-ADA%20%2F%20ADAG%20eA1C-green.svg)](docs/clinical_glucose_study_and_a1c_analysis.md)
[![Heritage: Physio-Control LIFEPAK](https://img.shields.io/badge/Heritage-Physio--Control%20LIFEPAK%20%26%20LifeLine-red.svg)](docs/bfm_diabetes_beta_requirements_specification.md)
[![License: GPL v3 / Open Source](https://img.shields.io/badge/License-GPL%20v3%20%2F%20Open%20Source-green.svg)](LICENSE)

---

## 🩺 Author's Dedication: Why This Toolset Was Built

> *"That Others May Survive Successfully Diabetes"*

The author is a **Type II diabetic** who needed an objective, personal, continuous telemetry tool to collaborate directly with their multidisciplinary care team:
* 💊 **Diabetic Pharmacist** — Managing medication kinetics, intake schedules, and titration adherence.
* 🩺 **Primary Care Physician (PCP)** — Longitudinal ambulatory reviews and permanent clinical record keeping.
* 🔬 **Endocrinologist** — Analyzing insulin sensitivity, glucose variability, and Dawn Phenomenon kinetics.
* 🥗 **Diabetic Diet Specialist** — Correlating meal macronutrients, ADA carbohydrate trades, and post-meal GLUT4 exercise recovery.

Offered as **experimental open-source software (GPL v3)** with the sincere hope that it empowers others in their daily struggle with diabetes. We invite doctors, clinical specialists, and software engineers to contribute to this mission. We also warmly welcome **Google** and community sponsors to partner with us in expanding this open-access tool to the 800M+ diabetics worldwide.

---

## 🛸 Starfleet Heritage: Gene Roddenberry & Scott Chambliss Dedication

Just as the Space Shuttle *Enterprise* (OV-101) inspired a generation of NASA engineers to reach for the stars, this project draws deep creative inspiration from **Gene Roddenberry** and production designer **Scott Chambliss**. 

In the modern Star Trek cinematic universe, Chambliss established the **Biofunction Monitor (BFM)** aesthetic in the Starfleet Sickbay: moving beyond 1980s flat CRT pastel blocks into high-contrast 21:1 clinical white panels, multi-layered physiological waveforms, and intuitive triage color bands. 

In JVEEPOO:
* **Mothership Workstation**: **USS Gemini (NCC-5010-G)** — The comprehensive PC workstation managing longitudinal storage, multi-user isolation, and automated daily backup.
* **Mobile Scout**: **Shuttlecraft 01: Foodoor** — The mobile companion enabling pre-order food decisions, multimodal photo/voice meal estimates, and zero-PHI cloud interaction.

---

## ⚡ Real-World Clinical Telemetry Heritage: Physio-Control LIFEPAK

This system incorporates direct medical device engineering heritage from the author's prior work at **Physio-Control** on the renowned **LIFEPAK (100–300 series) and LifeLine** emergency defibrillator telemetry systems. In those systems, real-time physiological data was relayed directly from the field to triage nurses and emergency physicians, saving countless lives.

Building on that pedigree, the BFM console introduces **Tiered Emergency Dispatch**:
1. **Tier 1 (Local Awakening)**: Continuous watch monitors detect acute nocturnal hypoglycemia nadir (<54 mg/dL) or severe cardiovascular distress, triggering escalating high-decibel audible alarms on nearby PC and mobile devices to awaken the patient.
2. **Tier 2 (Emergency Relay)**: If unacknowledged within 3 minutes, an automated SMS alert with GPS coordinates and acute vitals is dispatched to emergency medical services (911) and the primary care physician.
3. **Tier 3 (30-Day Triage Telemetry Download)**: Arriving EMTs and triage doctors can instantly extract the preceding 30 days of high-density biometric telemetry via BLE or QR code to immediately diagnose root cause (e.g., insulin stacking vs. Somogyi rebound vs. acute cardiac episode).

---

## 🎬 Interactive Technical Presentation (40-Slide Audio-Visual Walkthrough)

> [!NOTE]
> **GitHub File Viewing Notice**: Clicking `.html` files directly within GitHub's web file browser displays raw source code instead of the rendered presentation. To experience the interactive walkthrough with voiceover narration, use either **Option A (Download & Click to Open on PC)** or **Option B (Watch Live in Browser)**:

### 💻 Option A: Download to PC & Click to Open (Recommended for Windows)
1. **Download the 1-Click Launcher**: Click [**Download Preview_Presentation.exe**](https://raw.githubusercontent.com/jbirdlebough1/JVEEPOO/main/Preview_Presentation.exe) (or download the full repository as a [**ZIP Archive**](https://github.com/jbirdlebough1/JVEEPOO/archive/refs/heads/main.zip) and extract it).
2. **Launch & Enjoy**: Double-click **`Preview_Presentation.exe`** on your Windows PC. It opens the presentation instantly in your default web browser with synchronized voice narration and live telemetry scrolling!

---

### 🌐 Option B: Watch Live in Browser (Zero Download Required)
You can launch the rendered presentation directly inside any desktop or mobile browser via our GitHub Pages deployment:

👉 **[Launch Live Online Presentation (Interactive Web Player)](https://jbirdlebough1.github.io/JVEEPOO/presentation/preview.html)**  
*(40-slide clinical architecture walkthrough: covers the clinical rationale, physician co-configuration, GLUT4 glycemic exercise kinetics, 8-stream biometric synthesis, medication titration tracking, and ADA goal management with voice narration and live telemetry scrolling)*

* 📁 **Source Assets & Narration Script**: [`presentation/`](presentation/) | [`presentation/TRANSCRIPT.md`](presentation/TRANSCRIPT.md)
* 🩺 **Physician's Master Clinical Specification**: [`docs/generate_cmo_spec.py`](docs/generate_cmo_spec.py) | [`docs/cmo_spec.html`](docs/cmo_spec.html)
* 📋 **Beta Requirements Specification**: [`docs/bfm_diabetes_beta_requirements_specification.md`](docs/bfm_diabetes_beta_requirements_specification.md)
* 🏗️ **Cross-Platform Standalone .HTM Architecture Proposal**: [`docs/standalone_htm_cross_platform_architecture_proposal.md`](docs/standalone_htm_cross_platform_architecture_proposal.md)

---

## ⚡ Fast Operator Quickstart (Windows 11 Workstation)

Run the full BFM 5010 clinical telemetry environment on any Windows 11 machine without complex dependencies:

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
Correlates 8 simultaneous biometric streams into a unified Starfleet BFM visual display:
1. **Continuous Glucose Tonometry (60 - 200 mg/dL target range)**
2. **Estimated A1C Projection Curve (ADAG Formula: $eA1C = \frac{\text{Glucose} + 46.7}{28.7}$)**
3. **Core vs. Skin Body Temperature Dynamics (°F)**
4. **Nocturnal Sleep Hypnogram & Staging (Deep Delta, Light, REM Dream)**
5. **Hourly Exercise Step Kinetics & GLUT4 Muscle Contraction Translocation**
6. **Cardiovascular Stress & 5-Zone Cardiac Load (BPM)**
7. **Ambulatory Blood Pressure Oscillometry (Systolic / Diastolic mmHg)**
8. **Nocturnal SpO2 Stability & Autonomic HRV SDNN Recovery (ms)**

### 2. Standalone Cross-Platform Single-File .HTM Architecture (JumpStart 3D Model)
- **Universal Zero-Install Deployment**: The entire application compiles into a single, self-contained `.HTM` document with embedded CSS, JavaScript, and inlined SQLite-WASM.
- **Cross-Platform Parity**: Zero variance in deterministic clinical calculations between Windows, macOS, Android, iOS, and ChromeOS.
- **Web Bluetooth Standard**: Connects directly to BLE wearables from the browser without native drivers or middleware.

### 3. Multi-User Family Support & Air-Gapped Privacy
- **Isolated User Profiles**: Multiple family members can share a single PC workstation with isolated databases (`telemetry_user_<uuid>.db`).
- **QR Code Pairing**: Workstation provisions **Shuttlecraft 01: Foodoor** to mobile devices with cryptographic user pairing tokens.
- **Zero-PHI Cloud Guarantee**: Mobile food intake analysis and Google Gemini meal estimates operate solely on public commodity food items. Zero medical telemetry or patient health information ever touches the internet or mobile phone.

### 4. Longitudinal Vault & Daily Dual Backup
- **>= 1-Year Local Retention**: <25 MB annual database footprint stored in high-performance local SQLite using `navigator.storage.persist()`.
- **Automated External Backup**: Daily 1-click or scheduled export to USB drive or SD card in **both formats**:
  - Raw `.sqlite`: Immediate, byte-for-byte disaster recovery.
  - Epic FHIR `.json`: Interoperable clinical exchange formatted for hospital Electronic Health Records (EHR) with SHA-256 integrity manifests.

### 5. Senior Accessibility & Scott Chambliss Sickbay Aesthetics
- **Luminance Contrast Standard**: Solid white `#ffffff` cards with 21:1 deep navy `#091428` typography exceeding WCAG 2.1 AAA.
- **Saturated Clinical Readout Colors**: Crimson `#dc2626` (Heart Rate), cobalt `#0284c7` (Temperature), forest green `#15803d` (SpO2), and amber `#d97706` (Blood Pressure).
- **Tactical Bridge Night Vision**: Preserves dark adaptation and rhodopsin with red, green, and high-contrast monochrome themes.
- **Hands-Free Speech & Voice Recognition**: Full voice navigation and auditory telemetry readouts for visually impaired or motor-disabled patients.

---

## 📊 H-Band vs. BFM Feature Comparison Matrix

| Clinical & Telemetry Capability | Stock H-Band Mobile App | BFM 5010 Workstation Console |
| :--- | :---: | :---: |
| **Longitudinal Display Architecture** | Fragmented isolated tabs | **Unified 360° Single-Screen Telemetry Matrix** |
| **Glucose Calibration Mode** | Static linear offset | **Clinical 8.3% HbA1c Tonometry Anchor (191.5 mg/dL)** |
| **Mathematical eA1C Projection** | ❌ Not available | **ADAG Algorithm Real-Time Projection Curve** |
| **Exercise vs Glycemia Correlation** | Raw daily step counter | **Hour-by-hour GLUT4 glucose drop correlation** |
| **Dual Temperature Differential** | Single uncalibrated metric | **Simultaneous Core (°F) vs Skin (°F) Matrix** |
| **Medication Schedule & Titration** | ❌ None | **Morning (Intake 1) & Evening (Intake 2) Suite** |
| **Clinical Senior Accessibility** | Small gray low-contrast text | **21:1 Solid White Cards, Bold Crimson/Cobalt Numerals** |
| **Physician Consultation Export** | ❌ Screenshot only | **Standardized Epic FHIR JSON & Print Summary** |
| **Offline Privacy & Security** | Cloud server synchronization | **100% Local SQLite & Bluetooth LE (Zero Cloud Egress)** |
| **Multi-User Family PC Support** | Single user only | **Isolated Profile DBs & Mobile Shuttlecraft Pairing** |
| **Long-Term Data Storage** | Rolling 7-30 days | **>= 1-Year Local Storage with Daily USB/SD Dual Backup** |

---

## 🌐 Google Gemini Community Expansion & Market Opportunities

1. **Free to 800M+ Diabetics Worldwide**: Positioning Google Gemini as a compassionate global public health ally.
2. **Developer & Physician Rewards**: Open-source contributors, clinical researchers, and doctors contributing change requests receive complimentary Google Gemini Max subscriptions.
3. **Patient Recognition Spiffs**: Diabetic users sharing validated lifestyle interventions and metabolic recovery stories receive community badges and incentive rewards.
4. **Food Manufacturer & Retailer Portal**: Non-PHI aggregated usage data enables food manufacturers (Conagra, Tyson) and retailers (Costco, Sysco) to stock satisfying, crispy, <=45g net carb foods diabetics genuinely want.
5. **10-Mile Radius Local Sourcing**: Google Diabetes News integrated radar connects patients to local grocery stores and restaurants offering verified diabetic-safe selections.

---

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
├── w11_htm_app\                        # Core BFM Web Application
│   ├── index.html                      # BFM 5010 Master Single-Page Application (USS Gemini)
│   ├── cell.html                       # Mobile Companion (Shuttlecraft 01: Foodoor)
│   ├── server.py                       # Multithreaded local server daemon (Port 8899)
│   ├── telemetry.db                    # SQLite biometric database
│   └── sounds\                         # Tactile audio chimes
├── opt3_ble_python\                    # Bluetooth Low Energy Device Drivers
│   ├── watch_monitor.py                # Live BLE monitor daemon for Model 5010
│   └── vpo_protocol.py                 # Reverse-engineered Veepoo/FITAOS binary protocol
├── docs\                               # Clinical Documentation & Specification
│   ├── bfm_diabetes_beta_requirements_specification.md # Master Beta Requirements Spec
│   ├── standalone_htm_cross_platform_architecture_proposal.md # Cross-Platform Architecture
│   ├── GOOGLE_LEADERSHIP_PRIMER.md     # Google Executive Briefing & Partnership Model
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

## 📜 Session Logs (`.jsonl`)

In accordance with project development protocols, complete conversation transcripts and tool invocation trajectories are committed directly in [`session_logs/`](session_logs/):
- [`transcript.jsonl`](session_logs/transcript.jsonl): Step-by-step trajectory of all commands and operations.
- [`transcript_full.jsonl`](session_logs/transcript_full.jsonl): Full un-truncated context logs.

---

> [!CAUTION]
> **RESEARCH & CLINICAL DISCLAIMER**  
> This software is experimental research and an assistive telemetry reference tool for diabetic maintenance. It does not replace professional medical diagnosis, continuous blood glucose monitoring (CGM) prescribed by a physician, or emergency medical treatment. Always consult with your attending endocrinologist or physician regarding medication titration and dietary adjustments.
