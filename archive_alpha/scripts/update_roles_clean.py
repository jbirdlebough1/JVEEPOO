import os

# Update Requirements Spec
req_path = r"D:\veepoo\docs\bfm_diabetes_beta_requirements_specification.md"
req_content = open(req_path, "r", encoding="utf-8").read()

req_old = """* **System Name**: **BFM Console (Powered By Gemini)**
* **Desktop Workstation Base**: **Star Ship Gemini (NCC-202X)** (Shared Family PC Workstation)"""

req_new = """* **System Name**: **BFM Console (Powered By Gemini)**
* **Author & Philosophical Leader**: **The Author** (Creator, Chief Architect, and Leader of the BFM Philosophy)
* **Corporate & Technology Sponsor**: **Google** (Sponsoring, Hosting, and Powering via Google Gemini AI)
* **Desktop Workstation Base**: **Star Ship Gemini (NCC-202X)** (Shared Family PC Workstation)"""

req_content = req_content.replace(req_old, req_new)
with open(req_path, "w", encoding="utf-8") as f:
    f.write(req_content)

# Update Functional Spec
fs_path = r"D:\veepoo\docs\bfm_diabetes_beta1_functional_specification.md"
fs_content = open(fs_path, "r", encoding="utf-8").read()

fs_old = """* **Rigorous Systems Engineering over Random Comments**: Eliminates cryptic commits and random comments. Every architectural decision on Star Ship Gemini NCC-202X is backed by formal Requirements, Functional Specifications (FS), and FMEA risk post-mortems.
* **Base Vessel**: **Star Ship Gemini (NCC-202X)** (Desktop Workstation Console)"""

fs_new = """* **Rigorous Systems Engineering over Random Comments**: Eliminates cryptic commits and random comments. Every architectural decision on Star Ship Gemini NCC-202X is backed by formal Requirements, Functional Specifications (FS), and FMEA risk post-mortems.
* **Author & Philosophical Leader**: **The Author** (Creator, Chief Architect, and Leader of the BFM Philosophy)
* **Corporate & Technology Sponsor**: **Google** (Sponsoring, Hosting, and Powering via Google Gemini AI)
* **Base Vessel**: **Star Ship Gemini (NCC-202X)** (Desktop Workstation Console)"""

fs_content = fs_content.replace(fs_old, fs_new)
with open(fs_path, "w", encoding="utf-8") as f:
    f.write(fs_content)

print("[✔] Successfully updated governance and sponsorship roles in both specifications.")
