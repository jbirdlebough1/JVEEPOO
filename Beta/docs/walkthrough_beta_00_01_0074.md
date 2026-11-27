# BFM Biometric Function Monitor — Build `beta.00.01.0074` (Sev 0) Walkthrough

The **BFM Biometric Function Monitor** (`BFM.HTM`, mirrored identically to `index.html` and `w11_htm_app/index.html`) together with **`BFM Remote`** (`w11_htm_app/BFM-Cell.apk` `v0.1.74`) resolves two Sev 0 defects affecting today's Lunch telemetry: purging legacy `Simulated Cell Foodoor Ingestion` injection from `testCellConnection()`, and automatically refreshing stale time-of-day meal occasion buttons (`Breakfast` -> `Lunch` -> `Dinner`) when the mobile app stays open across meal windows.

## Root Cause & Resolution Matrix

| Defect Investigated | Root Cause Confirmed | Fix Applied (`beta.00.01.0074`) | Verification |
| :--- | :--- | :--- | :--- |
| Phone sync vs. `640 KCAL` `"SIMULATED CELL FOODOOR INGESTION"` in `LUNCH` | Phone DID sync cleanly (`ID #119`, `12:24:37 PM`, `Bagel plain`, `277 kcal`, `53.3g net carbs`, USDA live). It was tagged `[Breakfast]` because the app stayed open from morning, so real `LUNCH` only showed two earlier `320 kcal / 18g` clicks of Config's `TEST CONNECTION` button (`Simulated Cell Foodoor Ingestion`). | Deleted both synthetic `Foodoor` rows (`id=110`, `117`), reclassified `id=119` to `[Lunch] Bagel plain`, replaced `testCellConnection()` in `BFM.HTM` with a zero-data `batch_records: []` ping, and added a `/api/shuttle/dock` guard in `server.py`. | Live `/api/meal/recent` confirms zero `Simulated` rows and shows `[Lunch] Bagel plain` (`277 kcal`, `53.3g net`). |
| Stale meal occasion badge on mobile resume | `initMealTypeByTime()` in `cell.html` ran only on initial page load / after staging, leaving `[Breakfast]` active if resumed at `12:24 PM`. | Added `lastManualMealTypeAt` + `refreshMealTypeIfStale()` on `visibilitychange`, `focus`, `analyzeMeal()`, and `submitMealDirect()`, and rebuilt signed `BFM-Cell.apk` (`v0.1.74`). | `apksigner verify` v2/v3 passed; zero empty `catch` blocks; `cell.html` `Diff=0`. |
