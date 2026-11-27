# BFM Biometric Function Monitor — Build `beta.00.01.0050` Walkthrough

## 1. Conditional Watch-Face UI Gating (`11-WATCH` / `#pageWatchFaces`)

- **Container Isolation**: Wrapped all sections from `#boxDialOverwriteExp` (`HARDWARE EXPERIMENT: OVERWRITE FACE 3 WITH FACE 2 (CLE-009)`) through `Section 3: Custom Dial Image Architecture` (to the bottom of `#pageWatchFaces`) inside `#watchFaceWritableSections` (default `display: none`).
- **Visible Scope for Non-Writable Watches (`VEE_5010`)**: Only `WATCH CAPABILITY & PROTOCOL ANALYZER` and `WATCH HARDWARE CONTROLLER & DISPLAY MANAGER` render when the connected/scanned watch does not support BLE watch-face uploads.
- **Dynamic Detection & Restoration (`probeWatchFaceWritableCapability()`, `syncWatchFaceWritableUI()`, `updateWatchFaceWritableVisibility()`)**:
  - Evaluates live BLE capabilities on GATT connect (`setupGattChannels()`), capability audit (`runWatchCapabilityAudit()`), `0x50 0x02`/`0x50 0x01` hardware responses, and `flashDialBinaryViaDoogProtocol()`.
  - A watch is treated as writable only when both `F0030001` (`f0030002`/`f0030003`) characteristics exist and `0x50 0x02` returns `isOk === true && flashAddr > 0 && maxLen > 0`.
  - If a newly scanned/connected watch supports watch-face writing, `#watchFaceWritableSections` automatically displays (`display: flex`) and initializes the dial catalog, previews, and chronometer simulator.

## 2. Verification Summary

| Check | Result |
| :--- | :--- |
| DOM `<div>` Balance (`BFM.HTM`, `index.html`, `w11_htm_app/index.html`) | Opens `1957`, Closes `1957`, Diff `0` |
| `node --check` (extracted JS) | Exit code `0` |
| `strict_gate_v2.py` | 12/12 PASS |
| `audit_zero_defect.py` | 21/21 PASS |
