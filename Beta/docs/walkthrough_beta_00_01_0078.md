# Walkthrough `beta.00.01.0078` — Sev 0 Medication Persistence & Cross-Origin SQLite Sync

The **BFM Biometric Function Monitor** (`JVEEPOO`) build `beta.00.01.0078` resolves a Sev 0 regression where physician-prescribed medications disappeared after launching via `http://127.0.0.1:8899/` due to browser Same-Origin Policy isolation of `localStorage`.

## Root Cause Analysis

| Component | Behavior in `beta.00.01.0076–0077` | Resolution in `beta.00.01.0078` |
| :--- | :--- | :--- |
| [`tools/BFM_Launcher.cs`](../tools/BFM_Launcher.cs) | `ResolveLaunchUrl()` checked `http://127.0.0.1:8899/` before local `BFM.HTM`, switching Chrome out of the `file://` origin where the user's real `veepoo_medication_list` lived. | Prioritizes local `file:///.../BFM.HTM` (`new Uri(bfmPath).AbsoluteUri`) first while still starting `w11_htm_app/server.py` in the background on port `8899`. |
| [`w11_htm_app/server.py`](../w11_htm_app/server.py) | Had `/api/meds/daily_status` adherence checkoffs only; no SQLite backing table for `medicationList` or `medicationChangeLog`. | Added `user_medications_store` SQLite table + `GET /api/meds/regimen` & `POST /api/meds/regimen` with anti-wipe protection (empty payloads cannot overwrite existing DB meds without `explicit_clear: true`). |
| [`BFM.HTM`](../BFM.HTM) | Loaded `veepoo_medication_list` and `veepoo_medication_changes` strictly from `localStorage`. | Added `syncMedicationsToServer()` and `hydrateMedicationsFromServer()` hooked into load, `08-MEDICATION` tab open, and all 8 regimen/titration/dose actions so `file:///`, `http://127.0.0.1:8899/`, and `http://localhost:8899/` stay synchronized automatically. |

## Verification Summary

- `BFM_Launcher.exe` recompiled (`9216` bytes, zero hardcoded local paths) and verified `file:///.../BFM.HTM` launch priority.
- Live `GET /api/meds/regimen` and `POST /api/meds/regimen` verified including anti-wipe protection (`preserved_meds: true`), and DB left cleanly empty (`[]`) in compliance with **Pure Zero-Data Reality** and **Medical Data Sanctity**.
- Six Sigma gates (`audit_zero_defect.py` `21/21`, `strict_gate_v2.py` `13/13`, `test_beta1_e2e.py` `10/10`) and SHA-256 triple-file parity verified.
