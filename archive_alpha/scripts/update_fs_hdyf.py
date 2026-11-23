import os

fs_path = r"D:\veepoo\docs\bfm_diabetes_beta1_functional_specification.md"
content = open(fs_path, "r", encoding="utf-8").read()

hdyf_fs_section = """### 1.6 The 360° x 360° Spherical Telemetry: "How Do You Feel?" (HDYF) Behavioral Architecture
Inspired by *Star Trek IV: The Voyage Home*, BFM operationalizes **Subjective Experiential Telemetry** to bridge physiological sensing with behavioral psychology:
* **The Spherical Triad**:
  1. **Intake Data**: Exact timestamps, meal photos, calories, net carbs, protein, fat, and glycemic load.
  2. **Metabolic Telemetry**: High-density 5-minute optical PPG glucose, 24-hour circadian timelines, and GLUT4 postprandial clearance.
  3. **Subjective Experiential State**: Natural voice or text responses to daily and on-demand prompts: *"How Do You Feel?"*
* **Psychological & Clinical Correlation**: Captures sentiment, somatic fatigue, brain fog, and satiety. Correlating feelings with contemporaneous glucose levels and preceding meal BOMs enables diabetic psychologists, lifestyle coaches, and physicians to evaluate emotional distress and guide behavioral adaptation.
* **Closing Information Bleed**: Eliminates the disconnect between physiological numbers and daily patient vitality, providing the self-affirming feedback necessary to sustain long-term lifestyle compliance.
* **Commercial Leadership**: Positions Google Gemini as the pioneer of **Proactive Self-Management for Type 2 Diabetics**, establishing partnerships with food retailers, supply chains, and health insurers."""

content = content.replace("### 1.5 Human-Centric Metric of Success: Patient Daily Engagement & Physician Utility",
                          hdyf_fs_section + "\n\n### 1.5 Human-Centric Metric of Success: Patient Daily Engagement & Physician Utility")

# Add REQ-18 to RTM table
rtm_old = """| **REQ-17** | Human-Centric User & Physician Adoption Metric | Behavioral Feedback Engine | Longitudinal Vault & FHIR | Daily Active Use (>=90%) & Doctor FHIR Review |"""
rtm_new = """| **REQ-17** | Human-Centric User & Physician Adoption Metric | Behavioral Feedback Engine | Longitudinal Vault & FHIR | Daily Active Use (>=90%) & Doctor FHIR Review |
| **REQ-18** | Subjective Experiential Telemetry ("How Do You Feel?") | Behavioral Psychology / HDYF | `cell.html`, `server.py`, DB | Mobile HDYF Prompt, Home Wi-Fi Sync & FHIR Export |"""

content = content.replace(rtm_old, rtm_new)

with open(fs_path, "w", encoding="utf-8") as f:
    f.write(content)

print("[✔] Updated Functional Specification with HDYF Section 1.6 and REQ-18.")
