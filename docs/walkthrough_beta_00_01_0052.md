# Walkthrough — Build `beta.00.01.0052` (BFM Biometric Function Monitor)

BFM Biometric Function Monitor (`BFM.HTM`) and companion telemetry backend (`w11_htm_app/server.py`) were updated to enforce watch 12-hour AM/PM clock parity across all Reports charts, fix blank top-of-Reports KPI cards, replace simulated Lorenz and sub-report curves with genuine watch `0xDF` telemetry, and ingest Veepoo `0xDF` 5-minute history blocks for overnight/nocturnal bathroom-run steps, skin temperature, and R-R intervals.

## Root Causes Addressed

| Finding | Root Cause | Resolution |
| :--- | :--- | :--- |
| 24-Hour Axes on ECG, Blood Comp, BIA, Temp & BP Charts | `renderSvgDualCurveChart` hardcoded `${hr}:00` / `24:00` instead of honoring watch 12-hour setting (`0xB8` Page 0, `bytes[3] = 2`). | Added `isWatch12HourFormat()`, `formatAxisHourLabel()`, `formatClockMin()`, and `0xB8` RX sync; updated `renderSvgDualCurveChart`, `renderSvgCurveChart`, `renderNocturnalSleepHypnogram`, and Tall Scrubber labels. |
| Blank Reports KPI Cards (`--.- °F` Core/Skin Temp, `--%` Homeostasis) | `renderReportsView()` updated `repAvgGlucose` only inside tab-sync helpers and never populated `repAvgTemp`, `repRangeTemp`, `repAvgSkin`, or `repHomestasis`. | Computed all 5 KPI cards directly from live/timeline arrays inside `renderReportsView()`, backed by real `TEMPERATURE` `value2` (`skinTemp`) in `/api/telemetry/timeline`. |
| Simulated "Democrap" in Lorenz Scatter & Daily Sub-Reports | `renderLorenzScatterPlot` used PRNG `seed = 42` + `gauss()`, and `renderSubReportTrendCharts` synthesized values via modulo arithmetic; `nocturnalSpO2` / `circadianHRV` injected hardcoded fallbacks. | Removed synthetic generators, simulator buttons, and fallback arrays; added `GET /api/lorenz/today` and `GET /api/subreports/today` driven strictly by decoded watch data (or explicit zero-data notice when unsynced). |
| Missing Overnight Steps (e.g. Nighttime Bathroom Runs) | Live `0xA8`/`0x07` step polling only runs while the browser/daemon is actively awake; watch stores 5-minute overnight deltas in Opcode `0xDF` (`OriginV3`). | Added `_parse_df_origin_blocks()` + `ingest_df_origin_records()` in `server.py` (with `calc_veepoo_crc()` verification), `POST /api/origin/ingest`, Bleak auto-sync `0xDF` pull, and Web Bluetooth `0xDF` collector in `BFM.HTM`. |

## Verification Summary

| Check | Result | Details |
| :--- | :--- | :--- |
| Binary Mirror Parity | PASS | `BFM.HTM`, `index.html`, `w11_htm_app/index.html` identical SHA-256 `b4b0d080ea43bf07f27012856464ecc34d972f51682eaef8c6b86378b08d2aa7` |
| DOM & JS Syntax | PASS | `DIV open=1956 close=1956 diff=0`; `node --check` exit code `0` |
| Python Unit & Axis Check | PASS | `_parse_df_origin_blocks` decoded 01:15 AM 42-step + temp + 50 R-R block (`steps=42`, `core_f=98.2`, `skin_f=92.1`); `12 AM \| 4 AM \| 8 AM \| 12 PM \| 4 PM \| 8 PM \| 12 AM` |
| Six Sigma Gates | PASS | `strict_gate_v2.py` **12/12 PASS**; `audit_zero_defect.py` **21/21 PASS** (`68860 records preserved`) |
| Live HTTP Endpoints (`:8899`) | PASS | `/api/telemetry/latest` (`PEDOMETER: 2448.0`, `SLEEP: 9.17`), `/api/sleep/today` (`totalMin: 550`, `sessions: 3`), `/api/lorenz/today` (`n_epochs: 0`), `/api/subreports/today`, `/api/telemetry/timeline` (`has_skinTemp: True`) |
