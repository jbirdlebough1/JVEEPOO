# BFM Biometric Function Monitor — Build `beta.00.01.0048` Walkthrough

## 1. UI Removals (Strict Scope)

| Tab / Section | Element Removed | Verification |
| :--- | :--- | :--- |
| `05-LEARNING` (`#pageLearning`) | Removed light-blue `Timeframe Summary Banner` (`lblLearningRangeTitle`, `lblLearningRangeDesc`, `txtLearningReanalyzeProgress`, `btnLearningReanalyze`, `exportLearningReport()`) | Verified null-guards intact; card header retained |
| `04-REPORTS` (`#pageReports`) | Removed blue `CONFIGURE CLINICAL GOALS (TAB 13)` button | Clinical Goal Adherence Audit header retained |
| `04-REPORTS` (`#pageReports`) | Removed orange `EXPORT CSV` button | Report Timeframe bar retained |

All 11 frozen tabs (`01-FULL SCAN`, `02-MEAL PLANNER`, `03-METABOLIC`, `06-OPERATIONS`, `07-FAQ`, `08-CLINICAL GOALS`, `09-MEDICATION`, `10-CONFIG`, `12-DATA LOG`, `13-EXPORT`, `14-TROUBLESHOOTING`) remain untouched.

---

## 2. Tab `11-WATCH` (`#pageWatchFaces`) Fixes & Real BLE `DOOG` Flasher

### 2.1 Watch Display Simulator & Catalog/Preview Fixes
- Fixed ID mismatch (`pageWatch` -> `pageWatchFaces`) in `tickChronoClock()` and `runFullPipelineAndRefreshAllScreens()` so the 240x280 chronometer watch display animates and updates whenever `11-WATCH` is active.
- Enforced Pure Zero-Data Reality in `tickChronoClock()` and `updateChronoDial()`: reads real synced values (`lastGlucoseMg`, `lastSteps`, `lastHeartRate`, `#valBattery`) or `0` when unsynced instead of synthetic fallbacks (`124`, `8420`, `72`, `85`).
- Added `/api/dial/catalog` and `/api/dial/relabel/<name>` endpoints in `w11_htm_app/server.py`.
- Fixed `file:///` and `http://127.0.0.1:8899` compatibility in `loadStockDialInventory()`, `onQuickDialSelect()`, `loadVeepooDialCatalog()`, and `renderVeepooDials()` so the 94 local verified Shape 4 PNG previews and `.bin` binaries load cleanly in both launch vectors.

### 2.2 Real Veepoo `DOOG` / `F0030001` Binary Upload & Relabel Upgrade Test
- Previous code in `flashCloudDialToWatch()` and `testOverwriteSlot3WithSlot2()` only queried `0x50 0x02` and switched slots via `0xC7 0x01`, without streaming the `.bin` payload.
- Bound the Veepoo UI Big Data GATT service `F0030001-0451-4000-b000-000000000000` (`f0030002` control notify/write, `f0030003` bulk writeWithoutResponse) in `setupGattChannels()`.
- Implemented `flashDialBinaryViaDoogProtocol()` following decompiled `UiUpdateUtil.java`, `vp_dc.java`, and `vp_a.java`:
  1. Verify `F0030001` service presence (explicitly reports `HARDWARE UNSUPPORTED` if absent).
  2. Query partition metadata (`0x50 0x02 <uiFromType>`) and enter update mode (`0x50 0x01 <uiFromType>`).
  3. Send `DOOG` Start (`[0x44,0x4F,0x4F,0x47, 0x05, 0x00]`) and Erase (`[0x44,0x4F,0x4F,0x47, 0x01, addrLE, lenLE]`).
  4. Stream 4-byte-aligned `0xFF`-padded `.bin` payload on `f0030003`.
  5. Send `DOOG` End (`0x02`) and Verify CRC-16 Modbus (`0x03`, poly `0xA001`), then engage Slot 3 via `0xC7`.
- Added `/api/dial/relabel/<name>` and button `RELABEL STOCK DIAL & FLASH` (`testRelabelAndFlashWatchFace()`), which stamps `0x06..0x15` (`0xFF` padding bytes after `VEEPOO` magic) with a unique ASCII test stamp and recomputes CRC-16 so the hardware cannot skip flashing due to matching CRC.

---

## 3. Veepoo 5010 Watch Face Design & Glucose `mg/dL` Hardware Determination

1. **Repository Source Clarification**: `opt1_sdk_official` and `opt2_decompiled` contain the Android BLE client SDK (`vpprotocol-2.3.96.15.aar`), NOT the embedded C/RTOS firmware running inside the watch MCU.
2. **Watch Flash Readback Limit**: Neither `vpprotocol` nor the watch GATT profile implements a BLE command to download/dump watch-face `.bin` files from watch flash (`0x50 0x02` returns partition metadata/CRC only; `0xC7 0x02` returns installed dial slot IDs). All 94 verified Shape 4 (240x280) stock binaries are locally cached under `stock_dials/shape4_240x280/`.
3. **Shape 4 `.bin` Anatomy**: `0x00..0x05` `VEEPOO` magic, `0x06..0x22` `0xFF` padding, `0x28+` Protobuf layout table & widget tags, RGB565 bitmap sprites, verified by CRC-16 Modbus (`0xA001`).
4. **Native Glucose Widget Determination (`EWatchUIElementType.java`)**: Decompiled firmware enum `EWatchUIElementType` enumerates all dynamic watch-face widgets recognized by the watch firmware: `0=NONE`, `1=DATE_STYLE_1`, `2=DATE_STYLE_2`, `3=STEP`, `4=SLEEP`, `5=HEART`, `6=KCAL`, `7=DISTANCE`, `8=BLUETOOTH_BATTERY`, `99=TIME`. **No Glucose (`mg/dL`) element ID exists in the closed Veepoo watch firmware protocol**, so an external `.bin` watch face cannot bind live Glucose numbers the way `STEP` does without custom MCU firmware compiled by Veepoo.

---

## 4. Verification Summary

| Check | Result |
| :--- | :--- |
| `/api/dial/catalog` | HTTP 200, 659 entries, 94 local Shape 4 prioritized |
| `/api/dial/inventory` | HTTP 200, 94 verified Shape 4 binaries |
| `/api/dial/preview/C164.png` | HTTP 200, 36,082 bytes PNG |
| `/api/dial/relabel/C164` | HTTP 200, 180,040 bytes, `mod 4 == 0`, `VEEPOO` magic, CRC `40241` -> `17551` |
| `node --check` (BFM.HTM JS) | Exit code 0 (zero syntax errors) |
| DOM `<div>` Balance | Opens 1956, Closes 1956, Diff 0 |
| Mirror SHA-256 Parity | `0e66faa6097c3132b921b088fdc70ded9ee5b026ddf995e7529c4a2250503914` across all 3 HTML files |
| `strict_gate_v2.py` | 12/12 PASS |
| `audit_zero_defect.py` | 21/21 PASS |
