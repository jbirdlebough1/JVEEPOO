# ARCHIVE: BFM ALPHA BUILD & ARTIFACT PRESERVATION

## 1. Executive Summary & Archive Purpose
This directory (`archive_alpha/`) preserves the complete historical codebase, scratch prototypes, verification tooling, and full session conversation trajectories (`.jsonl`) from the Alpha phase of the BFM (Biometric Function Monitor) project.

In accordance with Total Quality Management (TQM) and SEI-CMMI Level 5 (Optimizing) requirements:
- **Baseline Version:** `alpha.01.00.00.0678`
- **Beta 1 Successor:** `beta.00.01.0001`
- **Zero-Error Recompile Guarantee:** Any Alpha utility or script preserved here can be re-executed or recompiled against the `alpha.01.00.00.0678` state with zero errors.

---

## 2. Archive Directory Structure

| Subdirectory / File | Content Description | Preservation Status |
| :--- | :--- | :--- |
| `archive_alpha/scripts/` | 685 legacy Alpha prototyping, analysis, inspection, and patch scripts (`add_*.py`, `analyze_*.py`, `apply_*.py`, `clean_*.py`, `inspect_*.py`, `update_*.py`). | Frozen & Archived |
| `archive_alpha/logs/` | Complete session transcripts (`transcript.jsonl`, `transcript_full.jsonl`) recording the end-to-end conversation, tool executions, and audit trials. | Bit-for-bit Persisted |
| `archive_alpha/README.md` | Formal archive manifest and reproduction instructions. | Master Document |

---

## 3. Zero-Error Reproduction & Execution Instructions

To re-run any Alpha script:
1. Ensure the embedded Python interpreter at `D:\veepoo\tools\python\python.exe` is available.
2. Target the desired script in `D:\veepoo\archive_alpha\scripts\`:
```powershell
& "D:\veepoo\tools\python\python.exe" "D:\veepoo\archive_alpha\scripts\<script_name>.py"
```
3. To inspect historical conversation trajectories and prompt engineering decisions, read `archive_alpha/logs/transcript.jsonl` or `archive_alpha/logs/transcript_full.jsonl`.

---

## 4. Transition to Beta 1 (`beta.00.01.0001`)
The root workspace (`D:\veepoo\`) has been swept clean of all Alpha scratch scripts. The active Beta 1 workspace contains strictly production artifacts and verification gates:
- `BFM.HTM`: Master standalone desktop application entry point.
- `index.html`: Triple-mirrored master production application.
- `w11_htm_app/`: Production application package, server daemon, and dual backup engine.
- `audit_zero_defect.py`: Pre-flight Six Sigma visual cleanliness and integrity gate.
- `strict_gate_v2.py`: Verification gate validating database schemas, UI DOM, and meal planners.
- `test_beta1_e2e.py`: Automated end-to-end integration test suite.
- `docs/`: Comprehensive engineering, clinical, requirements, and functional specifications.
