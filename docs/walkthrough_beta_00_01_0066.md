# Build `beta.00.01.0066` Walkthrough — Meal Pantry Removal, Watch ECG Sync & QTc Chart Fix, Next-Build `/cell` Med Spec

The **BFM Biometric Function Monitor** release `beta.00.01.0066` delivers two live defect resolutions, updates affected screenshots and presentation slides, and logs the next-build mobile medication toggle requirement in the tracked backlog.

## Scope & Implemented Changes

1. **Remove Obsolete Meal Pantry Favorites (`TODO-UX-005` — Closed)**
   - **UI (`BFM.HTM`, `index.html`, `w11_htm_app/index.html`)**: Removed Pathway A (`#sectionMealPantry`, `#listboxPopularMeals`, template edit/delete controls) and removed Step 3 `Add to Pantry` buttons. Expanded Step 1 into an uncluttered full-width entry panel supporting text, voice order, plate photo, 4 focused discovery tokens, and beverage fluid ounces.
   - **Presentation (`tools/build_beta56_presentation.py`, Slides 19 & 23)**: Recaptured `bfm_t03_meal_pantry.png` (`1584x955`) and `bfm_t03_meal_bom_grocers.png` (`1584x955`), updated screenshot captions and slide bullets/narration for Slide 19 and Slide 23 with zero remaining Pantry references.

2. **Watch Lead-I ECG Sync & Bazett QTc Chart Parity (`TODO-BLE-006` — Closed)**
   - **Incremental Watch History Sync (`w11_htm_app/server.py`)**: Filtered already-stored `ecg_id` (`0x96`/`0x97`) and `bia_id` (`0x93`) records prior to history packet requests and replaced fixed `25.0s` sleeps with bounded `12.0s` / `2.2s` idle waits, eliminating stored watch scan starvation.
   - **Bazett QTc Telemetry & Rendering (`w11_htm_app/server.py` & `BFM.HTM`)**: Computed Bazett-corrected QTc (`QT / sqrt(60 / HR)`) in `/api/subreports/today` (`250 ms` raw QT at `145 BPM` -> `388.6 ms` QTc rounded to `389 ms`), plotted dashed `412 ms` normal baseline and `440 ms` clinical ceiling lines inside `renderSvgDualCurveChart()`, suppressed the incompatible `1.31` LF/HF ratio curve from the Y-axis (`skipSkinPlot: true`), and verified live rendering on both `http://127.0.0.1:8899/` and standalone `file:///D:/veepoo/BFM.HTM`.
   - **Presentation (`tools/build_beta56_presentation.py`, Slides 32 & 37)**: Recaptured `bfm_t04_06_sub_ecg_met.png` (`1584x955`) and `bfm_t04_met_bloodcomp_live.png` (`1600x3987`), and aligned Slide 32 and Slide 37 copy with live `388.6 ms` QTc, `412 ms / 440 ms` clinical bands, and `/api/subreports/today` counts (`102` MET, `115` Blood Comp, `229` Breath).

3. **Next-Build `/cell` 3-State Med Toggle Logged (`TODO-CELL-005` — Open for Next Build)**
   - Logged in `Beta/docs/BETA_BACKLOG_TODO.md` without modifying current runtime behavior: light blue initial state -> 1st press records morning med taken -> 2nd press turns green for dinner meds -> automatic `00:01` daily reset to light blue (mirroring the BFM desktop app).
