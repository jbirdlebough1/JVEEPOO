# Walkthrough `beta.00.01.0079` — Watch Sync Pipeline, Live Reports, 1-Click GitHub Downloads & `BFM_Installer.exe`

The **BFM Biometric Function Monitor** (`JVEEPOO`) build `beta.00.01.0079` resolves multiple Sev 0 usability and data-pipeline blockers across standalone desktop launches, watch telemetry downloads, longitudinal reporting, and clean-PC installation.

## Root Causes & Implemented Fixes

| Area | Root Cause Prior to `beta.00.01.0079` | Permanent Fix in `beta.00.01.0079` |
| :--- | :--- | :--- |
| **Blank Watch Data on Clean PC / Standalone `BFM.HTM`** | `executeFullWatchConnectSync()` interrupted `0xDF` / `0x88` / `0xE0` BLE streams on fixed 2.5s timers (`splice(0)` mid-transfer failing CRC), lacked Day 1 sync, emitted colliding keep-alive `0x07`/`0xA0` pings mid-transfer, lacked `bfm_local_sleep_v1` storage, and shaped standalone `lastSubReportData` entries as scalars rather than chart series arrays. | Added event-driven stream completion (`0xDF FF FF` EOF or `1800ms` quiet tail), locked out colliding `0x07`/`0xA0` pings while `_isFullWatchSyncRunning` is active, synced Day 0 + Day 1 `0xDF` & `0x88`, persisted `bfm_local_sleep_v1`, and populated full standalone series arrays + `window._latestTelemetry`. |
| **Stale Server & Stale Reports** | `get_latest_telemetry_snapshot()` sorted by `id DESC` instead of `timestamp DESC`, hardcoded `battery_pct: 97.0`, and used stale in-memory counters; `triggerWatchSyncNow()` skipped auto-connect when unlinked; `renderRawTelemetryTable()`, `renderReportsView()`, `renderSubReportTrendCharts()`, and `updateWatchMetabolicSubReports()` contained static placeholder numbers (`2772`, `Eggs/Toast`, fake weekly/annual series). | Updated `server.py` to query `ORDER BY timestamp DESC, id DESC`, read live `MAX(id)` and real `BATTERY`, poll `0xDF`/`0x88` Day 0+1 in background sync, and added `GET /api/reports/history`; rewired `BFM.HTM` reports & raw tables exclusively to live synced DB/cache data with Pure Zero-Data Reality defaults (`0`), unconditional rendering across tabs, auto-connect on **SYNC WATCH NOW**, and 5-minute live `0xDF` polling. |
| **GitHub Opening `.HTM` Source Code** | Raw GitHub links rendered `.HTM` as plain text inside the browser. | Added same-origin [`download.html`](../download.html) auto-triggering `<a download>` straight to the user's `Downloads` folder (`?file=BFM.HTM`, `BFM_Installer.exe`, `BFM-Cell.apk`). |
| **Missing Windows Full Installer** | Only a small launcher executable existed; no installer downloaded BFM or added Desktop & Start Menu shortcuts. | Built [`tools/BFM_Installer.cs`](../tools/BFM_Installer.cs) -> [`BFM_Installer.exe`](../BFM_Installer.exe) with visible progress UI, GitHub asset downloads + offline fallback, Desktop + Start Menu shortcuts with [`bfm.ico`](../bfm.ico), and auto-launch via [`BFM_Launcher.exe`](../BFM_Launcher.exe). |

## Verification Summary

- [`BFM_Installer.exe`](../BFM_Installer.exe) verified end-to-end installing all 5 core files (`BFM.HTM`, `bfm.ico`, `BFM_Launcher.exe`, `w11_htm_app/server.py`, `w11_htm_app/cell.html`) and creating valid Desktop & Start Menu `.lnk` shortcuts.
- Live [`w11_htm_app/server.py`](../w11_htm_app/server.py) endpoints (`/api/telemetry/latest`, `/api/reports/history`) verified returning real live battery/telemetry and real DB-aggregated history.
- Standalone JavaScript `parseDfOriginBlocksJS` + `mergeStandaloneWatchRecordsJS` verified decoding real captured watch `0xDF` packets into timeline, sub-report, Lorenz, and latest vitals state with zero server.
- All Six Sigma gates passed: [`audit_zero_defect.py`](../audit_zero_defect.py) (`21/21 PASS`), [`strict_gate_v2.py`](../strict_gate_v2.py) (`13/13 PASS`), and [`test_beta1_e2e.py`](../test_beta1_e2e.py) (`10/10 PASS`).
