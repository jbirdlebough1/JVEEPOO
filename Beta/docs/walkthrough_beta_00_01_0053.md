# Walkthrough — Build `beta.00.01.0053` (BFM Biometric Function Monitor)

BFM Biometric Function Monitor (`BFM.HTM`) and companion telemetry backend (`w11_htm_app/server.py`) were updated to fix Veepoo `0xDF` 244-byte out-of-order 5-minute block decoding, backfill all 213 real watch blocks (`00:00`–`18:20`), restrict blue gap shading strictly to off-wrist charging/shower windows, apply watch 12-hour clock formatting and sleep waves to the 3D Multi-Channel Scene, and guarantee every-field every-page refresh on every tab switch and button click.

## Root Causes Addressed

| Finding | Root Cause | Resolution |
| :--- | :--- | :--- |
| Missing `00:00`–`08:30` & `12:00`–`18:20` Watch Telemetry | `_parse_df_origin_blocks` clamped `pkt[13] = 244` against the 20-byte header packet (`subIdx=0`) and dropped out-of-order `subIdx=1` packets arriving before `subIdx=0` over Windows Web Bluetooth (`Decoded 0 blocks`). | Grouped all `0xDF` packets by `block_id = (p[2]<<8)\|p[1]`, decoded `pkt_len = hdr[13]` (244 bytes) without clamping against header size, added `backfill_today_df_from_packet_log()` on startup -> decoded 213 watch blocks, **8,251 genuine Lorenz R-R epochs**, **79 MET**, **97 Blood Comp**, **193 Breath**, **2,530 steps**. |
| Excessive Blue Shaded Gap Boxes Across Worn Periods | `renderTallSynchronizedChart()` shaded every interval where `d.g === null`, and `get_two_day_timeline()` marked non-charging quiet slots as `is_gap = True`. | Restricted `isChargingGap` strictly to confirmed off-wrist charging/shower intervals (`08:00` only today) and updated `renderTallSynchronizedChart()` + HUD scrubber (`pt.isChargingGap`) to preserve real worn vitals even when glucose is `--`. |
| 3D Multi-Channel Clock & Sleep Ribbon | Floor X-axis labels displayed 24-hour clock strings and `CH 08: Sleep Arch` checked `d.notes.includes('sleep')` instead of `0xE0` sleep sessions. | Formatted 3D floor ticks and bottom axis labels with `formatTimeDisplay()` (`12:00 AM`–`11:30 PM`) and plotted real `window.lastDailySleepData.sessions` (`00:25–07:25` nocturnal + `13:40–15:50` nap) onto `CH 08`. |
| Stale Fields Across Pages on Tab/Button Clicks | `switchTab` passed `soundButtonPress(true)` (skipping refresh) and `runFullPipelineAndRefreshAllScreens` / `pollLiveTelemetryChanges` gated updates behind `isReportsActive` / `isDietActive` (and used wrong `#pageDiet` ID). | Enabled `scheduleUniversalAllPagesRefresh()` on `switchTab()`, `soundButtonPress()`, and a global delegated click listener across all buttons/tabs, running unconditional parallel fetches and DOM/chart updates for every page. |

## Verification Summary

| Check | Result | Details |
| :--- | :--- | :--- |
| Binary Mirror Parity | PASS | `BFM.HTM`, `index.html`, `w11_htm_app/index.html` identical SHA-256 `3f64430be9d74ed5...` |
| DOM & JS Syntax | PASS | `DIV Opens=1956, Closes=1956, Diff=0`; `node --check` exit code `0` |
| Live `00:00–18:30` Timeline & Gaps (`:8899`) | PASS | `38` elapsed 30-min slots populated continuously from `00:00`; only `08:00` marked `isChargingGap: True` |
| Live Lorenz & Sub-Reports (`:8899`) | PASS | `LORENZ -> n_epochs: 8251` (`meanRR=698.7ms`, `bpm=86`, `sdnn=93.9`, `rmssd=41.6`, `sd1=29.4`, `sd2=129.4`, `ratio=0.227`); `MET_5MIN: 79`, `BLOOD_COMP: 97`, `BREATH_RATE: 193`, `PEDOMETER: 2530.0`, `SLEEP: 9.17h (3 sessions)` |
| Six Sigma Gates | PASS | `strict_gate_v2.py` **12/12 PASS**; `audit_zero_defect.py` **21/21 PASS** (`68950 records preserved`) |
