# BFM Biometric Function Monitor — Tracked UI & UX Backlog (`TODO`)

**System:** BFM Biometric Function Monitor (`BFM.HTM` / `index.html` / `w11_htm_app/index.html`)  
**Logged In Build:** `beta.00.01.0060` (2026-11-26)

---

## Active Backlog Items

| ID | Area | Tab / Target | Requested Action & Rationale | Status |
|---|---|---|---|---|
| `TODO-UX-001` | Estimate Meal UI Declutter | `03-MEAL PLANNER` (`BFM.HTM` `L6727-L6756`, below `#txtMealBuilderInput`) | Remove the `RESTAURANT PRE-CHOICE` quick-prompt button tiles (`sectionNewMeal`). Over short daily usage these accumulated preset buttons become visually messy on the Estimate Meal view; preserve Pathway A reusable meal pantry (`#listboxPopularMeals`) and Pathway B freeform text/voice/photo input. | `OPEN (BACKLOG)` |
| `TODO-FEAT-002` | HDYF Voice Stress Analysis & Report | Mobile Companion / BFM Server (`04-REPORTS` & `server.py`) | Add acoustic voice stress analysis (pitch jitter, shimmer, vocal cadence, fatigue/stress telemetry) on recorded "How Do You Feel?" (HDYF) audio notes and build a dedicated Voice Stress clinical report in the BFM server. | `OPEN (BACKLOG)` |
| `TODO-BLE-003` | Stored Lead-I ECG (`0x99`/`0x9A`) & BIA Body Composition (`BODY_COMP`) BLE History Download | `BFM.HTM` & `w11_htm_app/server.py` | Implement dedicated Web Bluetooth history readback & packet parsers for on-watch manual Lead-I ECG (`0x99`/`0x9A`) and BIA Body Composition (`BODY_COMP`) so user-initiated watch scans ingest automatically into `vital_records` (`telemetry.db`) and populate Sub-Reports 02 (`ECG`) and 05 (`BIA`). Currently `BFM.HTM` syncs `0xE0`/`0xDF`/`0x88` + Full Scan, leaving `ECG_QTC` and `BODY_COMP` at `0` rows (`Pure Zero-Data` guarded). | `OPEN (BACKLOG)` |
