# BUILD WALKTHROUGH: BETA.00.01.0041

## Objective
BFM Biometric Function Monitor Tab 03 (`#pageMetabolic`) refinement and Tab 02 (`Meal Planner`) freeze:
1. Freeze Tab 02 (`Meal Planner`) as verified PASS with zero modifications.
2. Merge Tab 03 Section 01 (`#secMetabolic360`) and Section 02 (`#secMetabolicExercise`) into a single deduplicated subcard titled `01. EXERCISE AND ACTIVITY LOG`.
3. Remove the duplicate steps tile (`KINETIC ACTIVITY` / `#tile360Motion`), leaving the single `ACTIVE STEPS TODAY` (`#valStepCount`) badge in the unified 9-tile biometric and activity grid.
4. Leave Tab 03 Section 03 (`#secMetabolicDiet`) completely untouched.

---

## Changes Made
- **`D:/veepoo/BFM.HTM`**:
  - Merged `01. 360° REAL-TIME PHYSIOLOGICAL CONSOLE` and `02. EXERCISE & KINETIC ACTIVITY LOG` into `01. EXERCISE AND ACTIVITY LOG` inside `#secMetabolicExercise`.
  - Removed duplicate `#tile360Motion` / `#tile360MotionSub` tile while preserving the 5 physiological tiles (`#tile360Glucose`, `#tile360Bp`, `#tile360Spo2`, `#tile360HrHrv`, `#tile360Temp`), 4 activity/GLUT4 tiles (`#valStepCount`, `#valStepDist`, `#valStepKcal`, `#valGlut4Clearance`), workout log form, and chronological exercise journal.
- **Triple-File Mirror Parity**:
  - Mirrored `BFM.HTM` bit-for-bit to `index.html` and `w11_htm_app/index.html`.

---

## Verification Matrix

| Check | Result |
|---|---|
| `strict_gate_v2.py` | 12/12 PASS |
| `audit_zero_defect.py` | 21/21 PASS |
| Tab 02 Meal Planner Diff | 0 lines modified (Frozen) |
| Tab 03 Section 03 Diff | 0 lines modified |
