# Walkthrough — Build `beta.00.01.0054` (BFM Biometric Function Monitor)

BFM Biometric Function Monitor (`BFM.HTM`) and companion telemetry backend (`w11_htm_app/server.py`) were updated across every chart in `04-REPORTS` to eliminate false `0%` SpO2 drops during worn periods, render uniform translucent blue off-wrist/charging holes with data lines extending cleanly to the exact edges of each hole, and convert `BLOOD_COMP` serum uric acid and `MET_5MIN` series into matching chart units.

## Root Causes Addressed

| Finding | Root Cause | Resolution |
| :--- | :--- | :--- |
| False `0%` Plunges on `CH04: SpO2` in 3D Multi-Channel & Tall 8-Lane SpO2 | Periodic watch SpO2 blocks only record at certain 30-minute intervals (`00:00`–`06:30`, `09:00`–`10:30`, `12:00`, `17:00`). Missing intervening `d.spo2` slots returned `0` in `render3DMultiChannelScene()` and `buildSegmentedPath()`, making worn daytime SpO2 drop to `0%` ("dead"). | Segmented worn trajectories strictly on off-wrist blue holes (`d.isChargingGap`), bridging consecutive measured SpO2 readings cleanly across worn intervals and extending to the exact left/right boundaries of each blue hole (`CH04` Y-axis scaled `80%–100%`). |
| Inconsistent Blue Holes & Lines Not Touching Hole Edges Across Reports Graphs | Individual `renderSvgCurveChart()`, `renderSvgDualCurveChart()`, and `renderNocturnalSleepHypnogram()` built gaps independently (or not at all) from per-series missing points rather than unified watch off-wrist intervals. | Added `getTodayOffWristIntervals()` and `partitionPointsByOffWristGaps()` shared by `renderSvgCurveChart()`, `renderSvgDualCurveChart()`, and `renderNocturnalSleepHypnogram()`, plus synchronized midpoint boundaries (`gapMeta` in Tall 8-Lane, `gapBlocks3D` in 3D Multi-Channel). |
| Lane 5 Tall Temperature & `BLOOD_COMP` / `MET_5MIN` Unit Mismatch | Tall Lane 5 Temperature floor (`95.5°F`) clipped `92.1°F–94.5°F` readings; `BLOOD_COMP` sent raw `umol/L` (`160–293`) on a `2.0–8.0 mg/dL` chart; `MET_5MIN` sent instantaneous MET instead of cumulative MET-hrs. | Lowered Tall Lane 5 floor to `90.0°F–100.5°F` with data point dots; converted `umol/L` Uric Acid to `mg/dL` (`v / 59.48`) and accumulated 5-min MET into cumulative MET-hours in `get_today_subreports()`. |

## Verification Summary

| Check | Result | Details |
| :--- | :--- | :--- |
| Binary Mirror Parity | PASS | `BFM.HTM`, `index.html`, `w11_htm_app/index.html` identical SHA-256 `45f0e658aef003a4...` |
| DOM & JS Syntax | PASS | `DIV Opens=1956, Closes=1956, Diff=0`; `node --check` exit code `0` |
| Edge-to-Gap Node Unit Test | PASS | `SEGS COUNT: 2 SEG0_END_X: 298.5 SEG1_START_X: 312.8` (exact left/right boundaries of `07:45–08:15` hole) |
| Live HTTP Endpoints (`:8899`) | PASS | `/api/telemetry/timeline` (`38` slots, single off-wrist gap `['08:00']`); `/api/subreports/today` (`BLOOD_COMP` Uric Acid `<= 15 mg/dL`, cumulative `MET_5MIN > 5.0 MET-hrs`) |
| Six Sigma Gates | PASS | `strict_gate_v2.py` **12/12 PASS**; `audit_zero_defect.py` **21/21 PASS** (`70381 records preserved`) |
