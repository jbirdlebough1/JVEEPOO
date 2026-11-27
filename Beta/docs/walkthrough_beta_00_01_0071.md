# Build `beta.00.01.0071` Walkthrough — Clean APK Purge, Direct Live Google Gemini Search & Honest Field Sync

The **BFM Biometric Function Monitor** release `beta.00.01.0071` purges every hardcoded fake food preset and synthetic nutrition fallback from the Android Cell application (`w11_htm_app/cell.html` / `BFM-Cell.apk` `v0.1.71`) and backend parser, putting real Google Gemini 2.5 Flash + Google Search grounding directly on the phone so pre-consumption meal analysis works anywhere on cellular data or Wi-Fi before staging locally for automatic BFM PC Server docking.

## Scope & Implemented Changes

1. **Zero Hardcoded Food/Restaurant Crap in APK (`w11_htm_app/cell.html`, `BFM-Cell.apk` `v0.1.71`)**
   - Removed the 8 hardcoded quick-preset buttons, `setPreset()`, the `cellPackagedSnackMatrix` candy table, default fake tea input text, and the ~340-line hardcoded `calculateClinicalNutrition()` dictionary.
   - Removed hardcoded Sultan Bakery and sandwich dictionary shortcuts from `w11_htm_app/server.py` and prioritized `call_live_gemini_nutrition()` first whenever a live key is configured.
   - Enforced strict Pure Zero-Data Reality: without a configured live Gemini key or live network connectivity, `cell.html` returns `0` macros with an honest note prompting for the Gemini key rather than fabricating fake numbers.

2. **Direct Live Gemini API + Google Search Grounding on Phone Cellular/Wi-Fi (`w11_htm_app/cell.html`, `server.py`)**
   - Added `callDirectGeminiApi()` calling `gemini-2.5-flash` with `"google_search"` grounding and optional plate photo attachment directly from the Android Cell app over cellular data or Wi-Fi before the user eats.
   - Added `#btnCellGeminiKey` on Cell plus `GET /api/config/gemini_key_sync` on the private Home LAN so a single Gemini API key saved in Desktop `10-CONFIG -> Section 11B` or on the phone automatically synchronizes across both devices.
   - Preserved complete local offline storage on the phone for both pre-checked meals and **How Do You Feel?** (HDYF) entries (`staged_meals_queue`, `bfm_staged_feelings`) for automatic Home Wi-Fi sync to BFM Server.
