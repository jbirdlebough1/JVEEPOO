# BFM Biometric Function Monitor — Tracked UI & UX Backlog (`TODO`)

**System:** BFM Biometric Function Monitor (`BFM.HTM` / `index.html` / `w11_htm_app/index.html`)  
**Logged In Build:** `beta.00.01.0062` (2026-11-26)

---

## Active Backlog Items

| ID | Area | Tab / Target | Requested Action & Rationale | Status |
|---|---|---|---|---|
| `TODO-UX-001` | Estimate Meal UI Declutter | `03-MEAL PLANNER` (`BFM.HTM` `L6727-L6756`, below `#txtMealBuilderInput`) | Remove the `RESTAURANT PRE-CHOICE` quick-prompt button tiles (`sectionNewMeal`). Over short daily usage these accumulated preset buttons become visually messy on the Estimate Meal view; preserve Pathway A reusable meal pantry (`#listboxPopularMeals`) and Pathway B freeform text/voice/photo input. | `OPEN (BACKLOG)` |
| `TODO-FEAT-002` | HDYF Voice Stress Analysis & Report | Mobile Companion / BFM Server (`04-REPORTS` & `server.py`) | Add acoustic voice stress analysis (pitch jitter, shimmer, vocal cadence, fatigue/stress telemetry) on recorded "How Do You Feel?" (HDYF) audio notes and build a dedicated Voice Stress clinical report in the BFM server. | `OPEN (BACKLOG)` |
| `TODO-BLE-003` | Stored Lead-I ECG (`0x96`/`0x97`) & BIA Body Composition (`0x93`) BLE History Download | `BFM.HTM` & `w11_htm_app/server.py` | Implemented stored Lead-I ECG (`0x96`/`0x97`, `vp_ah`/`vp_ag`/`vp_af`/`vp_r`) & BIA Body Composition (`0x93 0x05`/`0x06`, `vp_k`/`vp_l`) BLE history parsers + auto-sync in `w11_htm_app/server.py`, live UI KPIs & dynamic ECG floor clamping in `BFM.HTM`, syncing real watch records (`10:23` ECG `250 ms` QT / `389 ms` QTc, `11:20` BIA `37.3%` muscle / `26.5%` fat / `24.4` BMI) into `telemetry.db`. | `CLOSED (beta.00.01.0062)` |
| `TODO-PWA-004` | Mobile `/cell` Installable PWA App | `w11_htm_app/cell.html`, `w11_htm_app/server.py`, Web App Manifest / Service Worker | The mobile companion works reliably inside the cell phone web browser, but does not install cleanly as a standalone installable app on the cell phone home screen. Add proper HTTPS/localhost or PWA installability support, icons, and manifest prompt once presentation alignment wraps. | `OPEN (BACKLOG)` |
