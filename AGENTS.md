# ACCOUNT-WIDE PRIME DIRECTIVES & STRICT ZERO-DEFECT QUALITY RULES
# (Applies across all projects, sessions, and workspaces for this account — Updated: 2026-11-24)

## PRIME DIRECTIVE 1: STRICT SCOPE ONLY
* **Execute Only What Is Explicitly Requested**: Complete the exact, enumerated bullet points in the user's prompt. Never add, infer, or extrapolate unprompted features, architectural layers, or speculative requirements.
* **Exploratory Discussions Are Quarantined**: When the user introduces a conceptual idea, future roadmap topic, or asks to "discuss", treat it strictly as a conversational discussion note. **NEVER** promote discussion concepts into the active build requirements, functional specifications, code, or test plans without explicit, written instruction to do so.
* **Zero Speculative Inventions**: Never invent feature names, speculative branding, or unrequested modules. Keep executive presentations, specifications, and code clean, grounded, and focused on core deliverables.
* **Prohibition on `/boost`**: Strictly prohibited. Never recommend, suggest, or invoke `/boost`.

---

## PRIME DIRECTIVE 2: MINIMAL REPAIR MODE
* **Surgical, Targeted Modifications**: Fix defects with the smallest, cleanest, most direct drop-in modifications necessary.
* **No Unsolicited Rewrites**: Never rewrite entire files, redesign working architectures, or change visual themes beyond what was specifically requested.
* **Zero Side-Effects**: Every change must isolate its modifications and verify that neighboring components, data schemas, DOM structures, and UI styling remain 100% intact.

---

## PRIME DIRECTIVE 3: /plan & /grill-me MANDATORY GATING & ALIGNMENT
* **Plan Before Execution**: For any multi-step task, architectural change, or bug resolution, establish a clear, concise, step-by-step plan before touching code.
* **Explicit Target Boundaries**: Explicitly state which files, lines, and database schemas will be touched and what is deliberately left untouched.
* **Mandatory `/grill-me` Pre-Implementation Alignment**: When user intent, requirements, data flow boundaries, or design decisions carry ambiguity, run `/grill-me` directly with the Author to conduct a focused interview, resolve trade-offs, and lock down exact specifications prior to execution.
* **Stop and Align on Ambiguity**: If any scope boundary is uncertain, stop calling execution tools and ask for clarification rather than making assumptions.

---

## PRIME DIRECTIVE 4: /learn — PERMANENT DEFECT PREVENTION (NEVER REPEAT A FAILURE)
The assistant must maintain continuous, unbroken institutional memory of past failure modes and maintain strict Six Sigma zero-defect quality:
1. **The Pure Zero-Data Reality Principle (Zero Is Zero)**:
   * **Never inject synthetic placeholder data**: No fake meals ("Poached eggs"), no fake medications ("Metformin ER"), no fake exercises, and no projected sine waves on charts.
   * If the watch has not synced, the chart displays **0**.
   * If no meals have been logged today, the journal displays: `[NO NUTRITION LOGGED — JOURNAL EMPTY]`.
   * If no medications are prescribed, the table displays: `[NO MEDICATIONS RECORDED]`.
   * If no chart number is entered, the MRN field remains blank (`""`), displaying `[UNASSIGNED]`. Zero hardcoded test MRNs.
2. **Exact Runtime Parity & Multi-Target Integrity**:
   * Never assume an automated test harness running against `http://127.0.0.1` proves the application works for the human user.
   * Always verify the exact user execution vector: standalone desktop launch via `file:///` in Windows Explorer.
   * Enforce dynamic API host resolution (`window.location.protocol === 'file:' ? 'http://127.0.0.1:8899' : ''`).
   * **Triple-Target Hash Parity**: `BFM.HTM`, `index.html`, and `w11_htm_app/index.html` must remain 100% bitwise identical across all builds.
3. **Mandatory Screen Telemetry Propagation (Zero Refresh Latency)**:
   * Every watch telemetry sync, BLE stream packet, or scan completion must immediately propagate to all 14 screens/consoles via `propagateFullScanResultsToAllPages()`. No console or metabolic chart may lag behind harvested data.
4. **Medical Data Sanctity**:
   * Pre-populating medications or modifying prescribed regimens is an extreme medical hazard. The medication registry belongs strictly to the user and their physician.
5. **Nutrition Metrology Accuracy**:
   * Nutritional disclosures (e.g. Taco Time NW Fiesta Platter: 520 kcal, 33g net carbs) must reflect published facts with 100% precision. Never use arbitrary mock numbers.
   * Never display monetary price strings (`$`, `COST`) in nutritional BOM workflows; display metabolic impact.
6. **No Visual Box-Art**:
   * Absolutely zero character graphic box diagrams (`┌`, `┐`, `└`, `┘`, `│`, `─`, `▼`, etc.) in chat or documentation. Native Markdown tables only.

---

## PRIME DIRECTIVE 5: GOVERNANCE, NOMENCLATURE & TERMINOLOGY STANDARDS
* **The Author**: Sole Pioneer, Inventor, System Architect, and Philosophical Leader across all projects.
* **Sponsorship Status**: Currently independent with **no sponsors** (actively seeking sponsorship from Veepoo / FITAOS, PPG watch vendors, and Google Gemini). Never claim active corporate sponsorship.
* **Nomenclature**: In executive and specification documents, always introduce "BFM Biometric Function Monitor" in the first paragraph.
* **Terminology Standards**:
   * Purge "Workstation" across UI elements and copy; use "BFM Console" or "BFM Telemetry Suite".
   * Standard BFM icon: Clinical stethoscope standard icon for desktop and mobile assets.
   * Mobile companion standard: "Cell Data Collector".

---

## PRIME DIRECTIVE 6: /codereview, CONTINUOUS VERSION CONTROL & SESSION TRAJECTORY (.jsonl) PERSISTENCE
* **Mandatory Active `/codereview` on Every Build (Never Bypassed)**:
  * Before stamping or delivering any build, perform an active, exhaustive line-by-line `/codereview` of all modified files.
  * Audit for:
    1. Syntax errors, scope leaks, and unhandled promises.
    2. HTML tag balance and DIV container matching.
    3. Strict Zero-Data reality compliance (no placeholder leaks).
    4. DOM propagation check: Verify target DOM IDs physically receive refreshed values.
    5. Adherence to Prime Directives 1, 2, and 3.
* **Dual-Remote Git Check-in on Every Build (Local & Internet)**:
  * Every build must be staged and committed to Git with a canonical, semantic commit message and an explicit build tag (e.g. `beta.00.01.0013`).
  * Every commit must be pushed immediately to **BOTH** the local Git remote (e.g. `origin`) and the internet GitHub remote (e.g. `github`). Never leave commits local-only.
* **Account-Wide Session Trajectory (`.jsonl`) Preservation**:
  * For every build and session across all projects on this account, copy the active conversation logs (`transcript.jsonl` and `transcript_full.jsonl`) from the brain directory into the project's repository under `archive_alpha/logs/` or `session_logs/`.
  * Track and commit these `.jsonl` trajectory files directly into Git on both local and internet remotes to ensure complete, unbroken, reproducible institutional memory.
