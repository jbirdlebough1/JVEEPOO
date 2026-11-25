# BUILD WALKTHROUGH: BETA.00.01.0044

## Objective
BFM Biometric Function Monitor Sev 0 stale-data fix across Reports charts and all tab switches:
1. Remove the hardcoded `h == 11` charging gap override in `server.py` (`get_two_day_timeline`) and prioritize verified sensor telemetry over gap assumptions whenever real measurements exist in SQLite.
2. Eliminate the `_isPollingTelemetry` race condition caused by `switchTab()` calling `soundButtonPress()` before switching `.active-page`.
3. Refresh `cached2DayTimeline` via `fetchServer2DayTimeline()` inside both `pollLiveTelemetryChanges()` and `runFullPipelineAndRefreshAllScreens()`, and re-render all active-tab charts/panels on every tab switch, button press, and Full Scan completion.
4. Update `renderSubReportTrendCharts()` (`isDaily`) and `renderReportsView()` (`isDaily`) so charts plot live hourly points up to the current local minute `nowHM` (fixing the 09:00 cutoff while keeping unelapsed future intervals strictly at `0`).

---

## Changes Made
- **`D:/veepoo/w11_htm_app/server.py`**:
  - Removed `or (h == 11)` from `is_known_charging_window` in `get_two_day_timeline()` and reordered `elif has_any_sensor:` ahead of `elif is_am_charge_shower:`.
- **`D:/veepoo/BFM.HTM`**:
  - Updated `soundButtonPress(skipRefresh = false)` and `switchTab(tabId)` (`soundButtonPress(true)`) so switching tabs never triggers a premature background lock.
  - Added `_pendingForceTelemetryPoll` queueing and `await fetchServer2DayTimeline()` in `pollLiveTelemetryChanges()`.
  - Updated `runFullPipelineAndRefreshAllScreens()` to fetch `fetchServer2DayTimeline()` and refresh active views across Reports, Metabolic, Diet Trades, Full Scan, and Goals.
  - Updated `renderSubReportTrendCharts()` (`isDaily`) to render hourly slots + `nowHM` live point with `const isFuture` zero clipping.
  - Updated `renderReportsView()` (`isDaily`) to use real `p.hr` / `p.sys` / `p.dia` and append `nowHM` live points whenever live readings exceed the latest cached bucket.
  - Wrapped Full Scan `POST /api/telemetry` packets in `Promise.all(...)` triggering `runFullPipelineAndRefreshAllScreens()`.
- **Triple-File Mirror Parity**:
  - Mirrored `BFM.HTM` bit-for-bit to `index.html` and `w11_htm_app/index.html`.

---

## Verification Matrix

| Check | Result |
|---|---|
| `http://127.0.0.1:8899/api/telemetry/timeline` | Non-future `11:00`+ slots return verified telemetry (`gap=False`) |
| `strict_gate_v2.py` | 12/12 PASS |
| `audit_zero_defect.py` | 21/21 PASS |
| Tab 02 Meal Planner HTML Diff | 0 lines modified |
