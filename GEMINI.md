# Veepoo LCARS 5010 Clinical Telemetry — Engineering Rules & 5-Sigma Zero-Defect Standards

## 1. Project Context & Medical Rigor
This project handles clinical physiological telemetry, ambulatory records keeping, and automated diagnostic synthesis for patients and clinical physicians.
- **Maximum Deviation Standard:** 5-sigma reliability.
- **Zero Defects Tolerance:** Zero runtime exceptions, zero syntax errors, zero missing features, zero unclosed DOM tags.
- **Absolute Data Honesty:** Zero synthetic/mock telemetry masking real biometric state. Clear, honest standby/dormant states (`--`) when sensors are off-wrist or unacquired.

## 2. Mandatory Verification Pipeline on Every Build
Before presenting any build or code change as validated, the following automated pipeline MUST be executed:
1. **Dual-File Synchronization:** Maintain `D:\veepoo\index.html` and `D:\veepoo\w11_htm_app\index.html` byte-for-byte identical at all times.
2. **AST Syntax & DOM Integrity Audit:**
   - Node V8 AST syntax check across all JavaScript blocks (0 parse/declaration errors).
   - Strict DOM tag balancing check (0 unclosed or mismatched tags across all 15 console tabs).
3. **Accumulated QA Test Suites:**
   - `& "D:\veepoo\tools\python\python.exe" "D:\veepoo\run_full_audit_test_suite.py"` (100% pass required).
   - `& "D:\veepoo\tools\python\python.exe" "D:\veepoo\test_accumulated_qa.py"` (100% pass required).
4. **Live CDP Browser DOM Verification:**
   - Headless Chrome DevTools Protocol verification of live rendered DOM nodes, computed styles, and element visibility to prevent layout blanking or rendering traps.
5. **CLEBUGGER Traceability:**
   - Log all bug entries, fixes, affected files, and verification proof in `D:\CLEBUGGER\bugs.db`.
6. **Git Version Control:**
   - Check in every build to git with descriptive commit messages and push to both `origin main` and `github main`.

## 3. Mandatory Build Reporting Format
Every build response presented to the user must explicitly report:
- **Defects Addressed & Improvements:** Exact issues resolved, including root cause and code fixes.
- **Regression Audit Delta:** Verification results comparing prior build vs. new build (pass rate, tag balance, 0 regressions).
- **CLEBUGGER Record:** Bug code (e.g. `CLE-087`), build number, and resolution confirmation.
