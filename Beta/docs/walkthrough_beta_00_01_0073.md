# BFM Biometric Function Monitor — Build `beta.00.01.0073` Walkthrough

The **BFM Biometric Function Monitor** (`BFM.HTM`, mirrored identically to `index.html` and `w11_htm_app/index.html`) together with **`BFM Remote`** (`w11_htm_app/BFM-Cell.apk` `v0.1.73`) now operates completely out of the box for casual users with **zero Google Gemini account or API key required**, zero manual IP/port setup, and hardened Android WebView & network security.

## Summary of Zero-Account & Hardening Deliverables

| Component | Target | Enhancement Delivered | Verification Result |
| :--- | :--- | :--- | :--- |
| Casual-User Live Nutrition (Phone) | `w11_htm_app/cell.html` | Removed `#btnCellGeminiKey` button from phone UI; added 3-tier waterfall (optional Gemini -> Home Server -> direct U.S. Gov USDA FoodData Central `DEMO_KEY` HTTPS API -> Pure Zero-Data Reality fallback). | Works directly over cellular Field Mode with zero accounts or API keys. |
| Server Zero-Account Lookup & Geo | `w11_htm_app/server.py` | Added `call_public_usda_nutrition()` + OpenStreetMap Nominatim geo-locator right before unknown-item zero fallback; restricted `/api/config/gemini_key_sync` strictly to private LAN/loopback IPs (`403` otherwise). | Verified real non-zero USDA macros & street address for local bakery queries; strict `0` for unrecognized text. |
| Automated Setup API | `w11_htm_app/server.py`, `BFM.HTM` | Added `GET /api/system/setup_status`, `POST /api/system/auto_setup`, and startup `runBfmAutoSetup()` to verify W11 BT toast suppression and LAN-matched signed APK automatically. | Idempotent startup readiness confirmed on both HTTP and `file:///`. |
| Android Security Hardening | `tools/build_cell_apk.py`, `BFM-Cell.apk` | Bumped `versionCode=73` (`0.1.73`), renamed launcher label to `BFM Remote`, set `allowBackup=false`, added `network_security_config.xml` (cleartext restricted to `localhost`/`127.0.0.1`/LAN IP), disabled file-URL cross-access & mixed content, served assets from `http://localhost:18899/cell.html`. | Signed & verified via Android `apksigner` v2 & v3 schemes (`40.9 KB`). |
| Streamlined QR Modal 1 | `BFM.HTM`, `/install/cell` | Updated Modal 1 (`📱 ADD PHONE • BFM REMOTE`), added `#provZeroAccountBadge`, collapsed advanced inputs into `<details>`, and kept `/download/BFM-Remote.apk` + `/download/BFM-Cell.apk` routes live. | Verified on both `http://127.0.0.1:8899/` and standalone `file:///D:/veepoo/BFM.HTM`. |
