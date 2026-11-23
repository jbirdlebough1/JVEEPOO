import os

req_path = r"D:\veepoo\docs\bfm_diabetes_beta_requirements_specification.md"
content = open(req_path, "r", encoding="utf-8").read()

sec_target = """#### Visual Cleanliness & Zero Defect Quality Mandate

To guarantee enterprise elegance, maximum readability across all platforms, and zero documentation defects:
1. **Zero Broken Image Links & Zero SVG Fragility**: Absolutely zero fragile external `.svg` or unverified image embeds in markdown documents that render as broken image boxes (`file:///D:/`). All architectural diagrams, workflows, and pipelines must be authored using robust, universally portable, natively rendered Markdown Architecture Tables.
2. **Zero LaTeX / Raw Math Clutter**: Absolutely zero `$` or `$$` math delimiters, zero KaTeX / MathJax markers, zero LaTeX macros (`\frac`, `\sigma`, `\sqrt`, `\approx`, `\pm`, `\times`, `\ge`, `\le`, `\iff`, `\ne`). Math must use natural, clean plain-text typography (`(Glucose + 46.7) / 28.7`, `sigma / sqrt(N)`, `<= 45g`, `+/- 15%`).
3. **Zero Monospace ASCII Box-Art**: Absolutely zero box-drawing characters (`┌`, `┐`, `└`, `┘`, `│`, `─`, `├`, `┤`, `┬`, `┴`, `┼`). Replaced with clean, accessible Markdown tables.
4. **Zero Raw Text Flowchart Code Blocks**: Absolutely zero `flowchart TD`, `subgraph`, or raw text pseudo-code diagrams in user-facing documents.
5. **Zero Forbidden Legacy Terms**: Zero "5010", zero "patient", zero "clinical", zero "Star Trek" in-app.
6. **Mandatory Automated Pre-Commit Audit**: Every document, presentation, and display code file must pass the automated `audit_zero_defect.py` tool (`-Werror`) with zero defects before staging or committing.

---"""

sec_addition = """#### Visual Cleanliness & Zero Defect Quality Mandate

To guarantee enterprise elegance, maximum readability across all platforms, and zero documentation defects:
1. **Zero Broken Image Links & Zero SVG Fragility**: Absolutely zero fragile external `.svg` or unverified image embeds in markdown documents that render as broken image boxes (`file:///D:/`). All architectural diagrams, workflows, and pipelines must be authored using robust, universally portable, natively rendered Markdown Architecture Tables.
2. **Zero LaTeX / Raw Math Clutter**: Absolutely zero `$` or `$$` math delimiters, zero KaTeX / MathJax markers, zero LaTeX macros (`\frac`, `\sigma`, `\sqrt`, `\approx`, `\pm`, `\times`, `\ge`, `\le`, `\iff`, `\ne`). Math must use natural, clean plain-text typography (`(Glucose + 46.7) / 28.7`, `sigma / sqrt(N)`, `<= 45g`, `+/- 15%`).
3. **Zero Monospace ASCII Box-Art**: Absolutely zero box-drawing characters (`┌`, `┐`, `└`, `┘`, `│`, `─`, `├`, `┤`, `┬`, `┴`, `┼`). Replaced with clean, accessible Markdown tables.
4. **Zero Raw Text Flowchart Code Blocks**: Absolutely zero `flowchart TD`, `subgraph`, or raw text pseudo-code diagrams in user-facing documents.
5. **Zero Forbidden Legacy Terms**: Zero "5010", zero "patient", zero "clinical", zero "Star Trek" in-app.
6. **Mandatory Automated Pre-Commit Audit**: Every document, presentation, and display code file must pass the automated `audit_zero_defect.py` tool (`-Werror`) with zero defects before staging or committing.

#### 1.2 Prime Philosophy & Success Mandate: "We The People" Self-Help Advocacy

The most important philosophy and overarching goal of the **BFM Console (Powered By Gemini)** is to **ensure daily, measurable success for users and their attending doctors in managing diabetes, diet, and physical activity**.

* **Strategic Positioning for Google Gemini**: Sponsoring, hosting, and powering the BFM platform decisively establishes **Google Gemini as the global AI leader in diabetes self-help services**.
* **Huge PR & Brand Opportunity**: Positions Artificial Intelligence not as an opaque corporate tool, but as a compassionate, benevolent, and empowering **"We The People" self-help health advocate**. Everyday individuals are equipped with sovereign, air-gapped, zero-cloud-leak tools to master their metabolic health.
* **Core Design Principle**: Every architectural component, user interface layout, algorithm, and test plan must be designed and validated against this primary philosophy.

#### 1.3 Total Quality Management (TQM) & SEI-CMMI Level 5 Maturity Standard

To guarantee institutional software excellence, the project incorporates **Total Quality Management (TQM)** across all workflows and targets **SEI-CMMI Maturity Level 5 (Self-Optimizing Closed-Loop Engineering)**.

* **Elite Industry Standing**: Fewer than 20 software organizations worldwide achieve certified SEI-CMMI Level 5 maturity.
* **Closed-Loop Engineering & Test (CLE)**: To reach and sustain Six Sigma quality, the BFM platform must resolve all external behaviors that impact sigma. Real-world sensor telemetry and user interactions continuously feedback into algorithmic refinement.
* **Permanent Knowledge Base**: From Beta 1 forward, session trajectory files (.jsonl), build histories, and automated test logs create a permanent institutional repository of engineering best practices, enabling future teams to achieve Six Sigma with minimum effort and time.

#### 1.4 Canonical Build Naming Standard

All software binaries, UI displays, server APIs, backup manifests, and Git tags must strictly follow the canonical build naming convention:
* **Alpha Generation Format**: `alpha.xx.xx.xx.xxxx` (where xxxx is the monotonically increasing 4-digit build number; e.g. `alpha.01.00.00.0678`).
* **Beta 1 First Build**: **`beta.00.01.0001`** (the certified Six-Sigma baseline passing all automated and human quality gates).
* **Subsequent Beta Patches**: `beta.00.01.0002`, `beta.00.01.0003`, etc.

#### 1.5 Human-Centric Metric of Success: Patient Daily Engagement & Physician Utility

Both Six Sigma (<= 3.4 Defects Per Million Opportunities) and SEI-CMMI Level 5 ratings are vital engineering controls, but they fail to measure user success. A product can achieve zero technical defects and still fail if the user or physician does not use it.

**Authoritative Definition of Success**:
1. **User Success**: The user genuinely likes the BFM Console and voluntarily uses it daily to manage their blood glucose trends, evaluate nutritional intake before meals, and regulate physical activity.
2. **Doctor Success**: The user's physicians and endocrinologists trust the telemetry, review the generated reports and Epic FHIR R4 exports, and use the data to optimize medical guidance.
3. **Behavioral Analytics Roadmap**: Following the viable Beta 1 release, BFM will implement mechanisms to collect and analyze longitudinal usage behaviors to drive continuous feature evolution matching the largest set of common user and doctor requirements.

---"""

content = content.replace(sec_target, sec_addition)

# Update Section 12 with REQ-15, REQ-16, REQ-17
sec12_old = """* **Six Sigma Quality Standard**: All releases must pass `strict_gate.py`:
  1. Bit-for-bit dual-file SHA-256 mirror parity.
  2. Perfect HTML tag and DOM tree balance (`div` balance diff = 0).
  3. Static JavaScript syntax verification via Node.js AST compiler.
  4. Python server bytecode compilation (`server.py`).
  5. SQLite deduplication and confectionery safety verification.
  6. Zero monetary cost strings in nutritional headers.
  7. Multi-tenant database isolation & 3-way binding verification (UID <-> ESN <-> Watch MAC).
  8. Zero forbidden terms check (zero "5010", zero "patient", zero "clinical", zero "Star Trek" in-app)."""

sec12_new = """* **Six Sigma Quality Standard**: All releases must pass `strict_gate_v2.py`:
  1. Bit-for-bit dual-file SHA-256 mirror parity (`BFM.HTM` == `w11_htm_app/index.html`).
  2. Perfect HTML tag and DOM tree balance (`div` balance diff = 0).
  3. Static JavaScript syntax verification via Node.js AST compiler.
  4. Python server bytecode compilation (`server.py`).
  5. SQLite deduplication and confectionery safety verification.
  6. Zero monetary cost strings in nutritional headers.
  7. Multi-tenant database isolation & 3-way binding verification (UID <-> ESN <-> Watch MAC).
  8. Zero forbidden terms check (zero "5010", zero "patient", zero "clinical", zero "Star Trek" in-app).
  9. Canonical Build Naming Verification (`beta.00.01.0001` match across UI, API, and FHIR metadata).
  10. Two-Pass Master Test Plan execution (Gemini automated + human manual via `BFM.HTM`) with CLEBugger defect synchronization (`http://127.0.0.1:8999/`).
  11. Human-Centric Adoption Verification: Daily patient utilization tracking and physician FHIR R4 report validation."""

content = content.replace(sec12_old, sec12_new)

with open(req_path, "w", encoding="utf-8") as f:
    f.write(content)

print("[✔] Updated Requirements Specification with TQM, CMMI Level 5, Prime Philosophy, and Versioning.")
