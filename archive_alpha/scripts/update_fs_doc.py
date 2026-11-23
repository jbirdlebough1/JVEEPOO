import os

fs_path = r"D:\veepoo\docs\bfm_diabetes_beta1_functional_specification.md"
content = open(fs_path, "r", encoding="utf-8").read()

sec1_target = """* **Visual Cleanliness & Zero Defect Quality Mandate**:
  1. **Zero Broken Image Links & Zero SVG Fragility**: Absolutely zero fragile external `.svg` or unverified image embeds in markdown documents that render as broken image boxes (`file:///D:/`). All architectural diagrams, workflows, and pipelines must be authored using robust, universally portable, natively rendered Markdown Architecture Tables.
  2. **Zero LaTeX / Raw Math Clutter**: Absolutely zero `$` or `$$` math delimiters, zero KaTeX / MathJax markers, zero LaTeX macros (`\frac`, `\sigma`, `\sqrt`, `\approx`, `\pm`, `\times`, `\ge`, `\le`, `\iff`, `\ne`). Math must use natural, clean plain-text typography (`(Glucose + 46.7) / 28.7`, `sigma / sqrt(N)`, `<= 45g`, `+/- 15%`).
  3. **Zero Monospace ASCII Box-Art**: Absolutely zero box-drawing characters (`┌`, `┐`, `└`, `┘`, `│`, `─`, `├`, `┤`, `┬`, `┴`, `┼`). Replaced with clean, accessible Markdown tables.
  4. **Zero Raw Text Flowchart Code Blocks**: Absolutely zero `flowchart TD`, `subgraph`, or raw text pseudo-code diagrams in user-facing documents.
  5. **Zero Forbidden Legacy Terms**: Zero "5010", zero "patient", zero "clinical", zero "Star Trek" in-app.
  6. **Mandatory Automated Pre-Commit Audit**: Every document, presentation, and display code file must pass the automated `audit_zero_defect.py` tool (`-Werror`) with zero defects before staging or committing.

---"""

sec1_addition = """* **Visual Cleanliness & Zero Defect Quality Mandate**:
  1. **Zero Broken Image Links & Zero SVG Fragility**: Absolutely zero fragile external `.svg` or unverified image embeds in markdown documents that render as broken image boxes (`file:///D:/`). All architectural diagrams, workflows, and pipelines must be authored using robust, universally portable, natively rendered Markdown Architecture Tables.
  2. **Zero LaTeX / Raw Math Clutter**: Absolutely zero `$` or `$$` math delimiters, zero KaTeX / MathJax markers, zero LaTeX macros (`\frac`, `\sigma`, `\sqrt`, `\approx`, `\pm`, `\times`, `\ge`, `\le`, `\iff`, `\ne`). Math must use natural, clean plain-text typography (`(Glucose + 46.7) / 28.7`, `sigma / sqrt(N)`, `<= 45g`, `+/- 15%`).
  3. **Zero Monospace ASCII Box-Art**: Absolutely zero box-drawing characters (`┌`, `┐`, `└`, `┘`, `│`, `─`, `├`, `┤`, `┬`, `┴`, `┼`). Replaced with clean, accessible Markdown tables.
  4. **Zero Raw Text Flowchart Code Blocks**: Absolutely zero `flowchart TD`, `subgraph`, or raw text pseudo-code diagrams in user-facing documents.
  5. **Zero Forbidden Legacy Terms**: Zero "5010", zero "patient", zero "clinical", zero "Star Trek" in-app.
  6. **Mandatory Automated Pre-Commit Audit**: Every document, presentation, and display code file must pass the automated `audit_zero_defect.py` tool (`-Werror`) with zero defects before staging or committing.

### 1.2 Prime Philosophy & Success Mandate: "We The People" Self-Help Advocacy
The ultimate mission of the **BFM Console (Powered By Gemini)** is to **ensure daily, measurable success for users and their attending physicians in managing diabetes, diet, and physical activity**.
* **Global Strategic Positioning**: Sponsoring, hosting, and powering BFM solutions decisively establishes **Google Gemini as the global AI leader in diabetes self-help services**.
* **Extraordinary PR Opportunity**: Positions AI as a benevolent, compassionate, and life-affirming **"We The People" self-help health advocate**, equipping 800+ million individuals with air-gapped, sovereign biofunction tools.
* **Core Design Commitment**: Every module, algorithm, and test plan is designed and evaluated against this human-centric philosophy.

### 1.3 Total Quality Management (TQM) & SEI-CMMI Level 5 Closed-Loop Engineering
The project engages Total Quality Management (TQM) and targets **SEI-CMMI Maturity Level 5 (Self-Optimizing Closed-Loop Engineering)**:
* **Fewer than 20 organizations worldwide** have achieved verified SEI-CMMI Level 5 maturity.
* **Closed-Loop Engineering & Test (CLE)**: To reach and sustain Six Sigma, BFM actively resolves all external behaviors that lead to impacting sigma. Telemetry feedback drives continuous algorithmic calibration.
* **Permanent Institutional Repository**: From Beta 1 forward, .jsonl session trajectory files, build histories, and automated test logs create a permanent institutional repository of engineering best practices, enabling future teams to achieve Six Sigma with minimum effort and time.

### 1.4 Canonical Build Naming Standard
All software binaries, UI displays, server APIs, backup manifests, and Git tags must strictly follow the canonical build naming convention:
* **Alpha Generation Format**: `alpha.xx.xx.xx.xxxx` (where xxxx is the monotonically increasing 4-digit build number; e.g. `alpha.01.00.00.0678`).
* **Beta 1 First Build**: **`beta.00.01.0001`** (the certified Six-Sigma baseline passing all automated and human quality gates).
* **Subsequent Beta Patches**: `beta.00.01.0002`, `beta.00.01.0003`, etc.

### 1.5 Human-Centric Metric of Success: Patient Daily Engagement & Physician Utility
Both Six Sigma (<= 3.4 DPMO) and SEI-CMMI Level 5 fail to measure user success if the product is not used. 
* **User Success Definition**: The user genuinely likes the BFM Console, finds it life-affirming, and relies on it daily to regulate blood glucose, evaluate nutritional choices, and log exercise.
* **Doctor Success Definition**: Attending physicians trust the telemetry, review generated reports and Epic FHIR R4 exports, and use BFM data to optimize patient care.

---"""

content = content.replace(sec1_target, sec1_addition)

# Update RTM table with REQ-15, REQ-16, REQ-17
rtm_old = """| **REQ-14** | Zero Defect Mandate & Visual Cleanliness Standard | Quality Assurance Subsystem | `audit_zero_defect.py` | Automated Zero Defect Pre-Commit Gate |"""
rtm_new = """| **REQ-14** | Zero Defect Mandate & Visual Cleanliness Standard | Quality Assurance Subsystem | `audit_zero_defect.py` | Automated Zero Defect Pre-Commit Gate |
| **REQ-15** | Canonical Build Naming Standard (`beta.00.01.0001`) | Versioning Subsystem | All UI, API & Manifests | Regex & Checksum Parity Gate |
| **REQ-16** | Two-Pass Master Test Plan & CLEBugger Integration | Quality Assurance / TQM | `docs/bfm_beta1_master_test_plan.md` | Automated Pass 1 + Human Pass 2 via `BFM.HTM` |
| **REQ-17** | Human-Centric User & Physician Adoption Metric | Behavioral Feedback Engine | Longitudinal Vault & FHIR | Daily Active Use (>=90%) & Doctor FHIR Review |"""

content = content.replace(rtm_old, rtm_new)

# Append Section 10 and Section 11 at end of file
sec10_11 = """

---

## 10. Master Test Plan Architecture & Execution Protocol

The Beta 1 verification protocol enforces a strict **Two-Pass Quality Validation Gate**:

| Test Phase | Operator / Agent | Primary Target | Scope of Execution | Defect Destination |
| :--- | :--- | :--- | :--- | :--- |
| **Pass 1: Automated Pre-Flight** | Gemini Subsystem | Headless Server & Engine | `audit_zero_defect.py` (8/8), `strict_gate_v2.py` (10/10), `test_beta1_e2e.py` (10/10) | Automated JSON post to CLEBugger (`:8999`) |
| **Pass 2: Human-in-the-Loop** | Human Operator | Desktop `BFM.HTM` | Operator double-clicks `BFM.HTM` in Windows Explorer; tests all 14 tab bays and mobile dock | Manual ticket / Change Request logged in CLEBugger |

* **CLEBugger Integration (Port 8999)**: All defects, anomalies, and change requests (CRs) are logged in real-time to CLEBugger (`http://127.0.0.1:8999/`). Zero open defects required before milestone sign-off.
* **Step-by-Step Human Testing**: Formalized in [`bfm_beta1_master_test_plan.md`](file:///d:/veepoo/docs/bfm_beta1_master_test_plan.md).

---

## 11. Longitudinal Behavioral Telemetry & Post-Beta Clinical Adoption Engine

Following the viable Beta 1 release, BFM operationalizes SEI-CMMI Level 5 closed-loop engineering by collecting and analyzing real-world usage behaviors:
1. **Patient Adherence Metrics**: Tracks frequency of daily mobile meal staging via Shuttlecraft Foodoor (`cell.html`), post-meal physical activity compliance, and nocturnal watch wearing.
2. **Physician Data Utilization**: Analyzes doctor review of generated Epic FHIR R4 JSON bundles, identifying the most clinically valuable LOINC telemetry streams.
3. **Closed-Loop Feature Evolution**: Translates user and physician behavioral patterns into prioritized feature enhancements matching the largest set of common real-world requirements.
"""

content = content + sec10_11

with open(fs_path, "w", encoding="utf-8") as f:
    f.write(content)

print("[✔] Updated Functional Specification with TQM, CMMI Level 5, Prime Philosophy, Test Plan, and Versioning.")
