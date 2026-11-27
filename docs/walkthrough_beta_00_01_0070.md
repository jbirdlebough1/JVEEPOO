# Build `beta.00.01.0070` Walkthrough — Meal Estimator Sandwich/Bakery Resolution & Live Gemini Search Grounding

The **BFM Biometric Function Monitor** release `beta.00.01.0070` resolves the meal estimator defect reported on `"Sultan Bakery Sultan WA 98294, Ham and cheese sandwich"`, adds live Google Gemini 2.5 Flash Search grounding support, displays detailed estimation notes inside the Android Cell app, and updates signed `BFM-Cell.apk` to `v0.1.70`.

## Root Cause & Implemented Fixes

1. **Regional Bakery / Deli Sandwich Fix & Zero-Regression Cheese Narrowing (`w11_htm_app/server.py`, `w11_htm_app/cell.html`)**
   - Previously, `server.py` had no bakery/sandwich handler or Sultan Bakery record, and matched `"cheese"` inside `"ham and cheese sandwich"` against the standalone `"snack/cheese"` shortcut (`110 kcal`, `1g` carb) — or fell back to zero when unmatched — while `cell.html` did not display `gemini_notes`.
   - Added dedicated **Sultan Bakery (`31407 US-2, Sultan, WA 98294`)** estimation handling Deli Ham & Cheese (`410 kcal`, `31.0g` net carbs), `Grilled 95` (`510 kcal`, `33.0g` net carbs), Breakfast Ham & Cheese (`480 kcal`, `33.0g` net carbs), and custom extras (`extra mayo`, `extra cheese`, `no bread`), along with a general Deli/Bakery Sandwich handler prior to standalone cheese snacks.
   - Added `<p id="resNotes">` in `cell.html` so users directly see the located restaurant address, `350–440 kcal` range context, and version clarification tips both online and offline.

2. **Live Google Gemini 2.5 Flash + Google Search Grounding (`w11_htm_app/server.py`, `BFM.HTM` Section `11B`)**
   - Added `call_live_gemini_nutrition()` wired to `gemini-2.5-flash` with `"google_search"` grounding whenever a Gemini API key is configured via `GEMINI_API_KEY` environment variable or `10-CONFIG -> Section 11B` (`/api/config/gemini_key` stored in local SQLite `sync_state`), with clean automatic fallback to the local engine if no key is set or offline.

3. **APK & Mirror Build (`w11_htm_app/BFM-Cell.apk` `v0.1.70`)**
   - Mirrored `BFM.HTM` bit-for-bit to `index.html` and `w11_htm_app/index.html`, rebuilt and signed `BFM-Cell.apk` (`versionCode 70`, `versionName 0.1.70`), and verified `audit_zero_defect.py`, `test_beta1_e2e.py`, `strict_gate_v2.py`, and `audit_presentation_full.py`.
