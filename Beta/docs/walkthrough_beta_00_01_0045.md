# BUILD WALKTHROUGH: BETA.00.01.0045

## Objective
BFM Biometric Function Monitor Reports Sleep Hypnogram layout and live step/sleep accuracy fixes:
1. Override `.svg-timeline-container`'s fixed `225px !important` / `overflow: hidden` height on `#curveTimelineSleep` so the entire Sleep Hypnogram (stage badges, all 4 sleep stage bands, X-axis timestamps, and Sleep Quality Rating Summary below) renders completely visible without clipping.
2. Replace hardcoded stale sleep summary numbers and chart titles (`7.4 HOURS`, `2.2h`, `1.2h`, `3.7h`) and stale steps (`2,384 / 4,994`, `2772`) with live watch telemetry values.
3. Correct `CLINICAL GOAL: 2,000 STEPS (1.5 MILES)` to `CLINICAL GOAL: 2,000 STEPS (0.95 MILES)` matching `2000 * 0.000473`.

---

## Changes Made
- **`D:/veepoo/BFM.HTM`**:
  - Added `#curveTimelineSleep` (`height: auto !important; min-height: 440px; overflow: visible !important;`) and `#curveTimelineSleep svg` (`height: 240px !important;`) CSS rules.
  - Updated `renderNocturnalSleepHypnogram()` SVG height (`240px`) and injected live `deepH`, `deepPct`, `remH`, `remPct`, `lightH`, `lightPct` into the summary cards.
  - Updated `CLINICAL GOAL: 2,000 STEPS (0.95 MILES)` at line 4272 and replaced stale `repChartSleepTitle` / `repChartExerciseTitle` hardcoded values in `renderReportsView()`.
- **Triple-File Mirror Parity**:
  - Mirrored `BFM.HTM` bit-for-bit to `index.html` and `w11_htm_app/index.html`.

---

## Verification Matrix

| Check | Result |
|---|---|
| `strict_gate_v2.py` | 12/12 PASS |
| `audit_zero_defect.py` | 21/21 PASS |
| Tab 02 Meal Planner HTML Diff | 0 lines modified |
