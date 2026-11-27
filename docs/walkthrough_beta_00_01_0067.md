# Build `beta.00.01.0067` Walkthrough — Windows 11 Cell Exclusion & VEE Bluetooth Toast Suppression

The **BFM Biometric Function Monitor** release `beta.00.01.0067` ensures the mobile Cell companion app is strictly excluded from Windows 11 BFM Server installation and permanently suppresses the repeating Windows 11 Bluetooth Swift Pair toast for the VEE watch.

## Scope & Implemented Changes

1. **Prevent Cell App Installation on Windows 11 BFM Server (`TODO-W11-007` — Closed)**
   - **Shortcut Cleanup (`w11_htm_app/server.py`)**: Removed creation of `BFM Mobile Cell Gateway.lnk` from `/api/system/create_shortcuts` and added cleanup to delete any legacy `BFM Mobile Cell Gateway.lnk` under the Start Menu program group.
   - **Desktop Install Prompt Guard (`w11_htm_app/cell.html`)**: Removed the duplicate `deferredPrompt` / `triggerPwaInstall()` handler and restricted `beforeinstallprompt` handling so `#pwaInstallBanner` only displays on mobile user agents (`Android|iPhone|iPad|iPod`), never on Windows 11 desktop browsers.
   - **Host Cleanup**: Removed existing `BFM Mobile Cell Gateway.lnk` from Windows 11 Start Menu Programs.

2. **Suppress Windows 11 `Add a device / VEE` Bluetooth Pairing Popups (`TODO-W11-007` — Closed)**
   - **Startup Registry Enforcement (`w11_htm_app/server.py`)**: Added `suppress_w11_bt_pairing_toasts()` invoked on every `server.py` launch before BLE background polling starts. Uses `winreg` under `HKEY_CURRENT_USER` to set `Enabled = 0` and `ShowInActionCenter = 0` on `Windows.SystemToast.Bthprops` and `Windows.SystemToast.BthQuickPair`, and sets `Value = 0` on `Bluetooth\QuickPair`.

3. **Backlog & Verification**
   - Updated `Beta/docs/BETA_BACKLOG_TODO.md` and `Beta/docs/GOOGLE_LEADERSHIP_PRIMER.md` while keeping `TODO-PWA-004` and `TODO-CELL-005` open as scheduled backlog items.
   - Verified `server.py` compilation, restarted daemon on port `8899`, confirmed registry keys via `reg query`, confirmed Start Menu cleanup, and passed `audit_zero_defect.py`, `test_beta1_e2e.py`, and `strict_gate_v2.py` with CLEBUGGER `67/67` (`OPEN=0`).
