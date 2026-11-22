# STRICT ZERO-DEFECT QUALITY POLICY (SIX SIGMA / C -Werror EQUIVALENT)

## MANDATORY PRE-COMMIT & PRE-EDIT GATING PROTOCOL
To completely eliminate code dropping, syntax regressions, and accidental DOM corruption in this codebase:

1. **NO BLIND STRING REPLACEMENT OR TRUNCATED EDITS:**
   - Never replace code based on partial snippets without verifying the full enclosing tag/function scope.
   - For `index.html` and `w11_htm_app/index.html`, any HTML modification MUST preserve exact tag balance (`open == close`) and maintain all 14 tab pages as direct children of `main.lcars-workspace`.

2. **DUAL-FILE PARITY RULE:**
   - `index.html` and `w11_htm_app/index.html` MUST remain 100% bit-for-bit identical (SHA-256 match). Never modify one without updating the other.

3. **MANDATORY EXECUTION OF `strict_gate.py`:**
   - Before completing any task or committing changes, run:
     `& "D:\veepoo\tools\python\python.exe" "D:\veepoo\strict_gate.py"`
   - If ANY check fails (DOM balance, AST syntax check, SHA256 parity, database duplicate check, or prohibited monetary cost strings), the code is DEFECTIVE and MUST NOT be committed or delivered.

4. **CLINICAL NUTRITION SAFETY RULE:**
   - High-sugar confections, candies, chocolates, and sweets must NEVER be classified as healthy or "Meets Goal".
   - When nutrition numbers are provided via cell upload, OCR label, or QR link, they must be parsed accurately and committed to the database without being overwritten by default fallbacks.
   - Do NOT display monetary costs (`~$...`, `EST. COST`, `$ basket`) in metabolic workflows. Display **METABOLIC IMPACT** (Net Carbs, Glycemic Load, Projected Spike).

5. **DATABASE DEDUPLICATION RULE:**
   - The Meal Pantry (`popular_meals`) must enforce unique normalized titles (`LOWER(TRIM(title))`). Duplicate rows are strictly prohibited.
