# Build `beta.00.01.0068` Walkthrough — Standalone Android `BFM-Cell.apk`, QR Auto-Download & 3-State Med Toggle

The **BFM Biometric Function Monitor** release `beta.00.01.0068` delivers a standalone signed Android application (`BFM-Cell.apk`) installed directly via the BFM Server QR code, strict architectural separation from the watch (zero BLE permissions on the Cell), automatic home Wi-Fi sync with local field storage when away, and the 3-state daily medication button (`TODO-PWA-004` & `TODO-CELL-005`).

## Scope & Implemented Changes

1. **Standalone Signed Android APK (`w11_htm_app/BFM-Cell.apk`, `tools/build_cell_apk.py`)**
   - Built with portable JDK 17 + Android SDK 34 (`aapt2`, `d8`, `zipalign`, `apksigner` v2/v3 verified) as `com.bfm.cell`.
   - Declares only `INTERNET`, `ACCESS_NETWORK_STATE`, `CAMERA`, and `RECORD_AUDIO` permissions — **zero Bluetooth permissions** (Watch BLE sync remains exclusively on the BFM PC Server).
   - Renders crisp BFM Cell launcher icon (`icon-192.png`, `icon-512.png`, `mipmap-*/ic_launcher.png`) depicting the white clinical cross and cyan/emerald Wi-Fi sync arcs.
   - Bundles `assets/cell.html` offline inside the APK so opening **BFM Cell** from the phone Home Screen boots immediately even outside home Wi-Fi, with native photo picker (`onShowFileChooser`), microphone/camera permissions, and native Alert/Prompt dialogs for tap-to-edit server IP.

2. **QR Code Auto-Download Installer Flow (`w11_htm_app/server.py`, `BFM.HTM`)**
   - Updated `/api/qrcode/cell` and `/mobile_wifi_qr.svg` to encode `http://<LAN-IP>:8899/install/cell`.
   - Added `/install/cell` mobile landing page that displays the BFM Cell icon, automatically initiates `/download/BFM-Cell.apk` (`Content-Type: application/vnd.android.package-archive`), and shows clear 3-step Android install steps.
   - Updated `POST /api/shuttle/register` with `auto_register` support so a newly installed Cell re-registers seamlessly even after deleting an old Cell entry.
   - Updated `BFM.HTM` Modal 1 QR instructions and mirrored bit-for-bit to `index.html` and `w11_htm_app/index.html`.

3. **Cell Native Runtime & 3-State Daily Med Button (`w11_htm_app/cell.html`, `server.py`, `BFM.HTM`)**
   - Suppressed `#pwaInstallBanner` whenever running inside the installed Android app (`BFM_IS_NATIVE_APP`).
   - Updated status pill to display `CONNECTED TO BFM SERVER` on home Wi-Fi (with tap-to-edit Server IP dialog) or `FIELD MODE (SAVING LOCALLY)` away from home, auto-syncing staged meals, feelings, and daily med status upon reconnection.
   - Added `#btnCellDailyMed` (`light blue -> mid blue morning taken -> green dinner taken -> 00:01 daily auto-reset`) backed by SQLite `daily_med_adherence` (`GET/POST /api/meds/daily_status`) shared with desktop `takeAllMorningMeds()` and `takeDinnerMeds()`.
