import os

req_path = r"D:\veepoo\docs\bfm_diabetes_beta_requirements_specification.md"
content = open(req_path, "r", encoding="utf-8").read()

hdyf_section = """#### 1.6 The 360° x 360° Spherical Telemetry: "How Do You Feel?" (HDYF) Behavioral Psychology

Inspired by the philosophical inquiry from *Star Trek IV: The Voyage Home*, where recovery cannot be measured by physical instruments alone, BFM incorporates **Subjective Experiential Telemetry ("How Do You Feel?")**:
1. **The Three Spherical Dimensions**:
   * **Intake**: Nutritional BOM (calories, net carbs, fat, protein, glycemic impact).
   * **Metabolic Response**: Continuous optical PPG glucose, circadian trajectories, and GLUT4 exercise clearance.
   * **Subjective Experiential State**: Daily and on-demand self-assessment of energy, mental clarity (brain fog), mood, and physical wellbeing.
2. **Clinical & Psychological Value**:
   * Bridges endocrinology and diabetic psychology. Diabetic distress, emotional burnout, and non-compliance are rooted in unexplained physical discomfort and lack of agency.
   * Correlating exact timestamps of subjective feelings with preceding meals and concurrent glucose curves enables psychologists, diabetes educators (CDCES), and physicians to provide targeted, empathetic behavioral interventions.
3. **Closing the Information Bleed**:
   * Diabetes is both genetic and behavior-driven. By giving users immediate, clear correlation between what they eat, how their body responds, and how they actually feel, BFM provides the psychological feedback necessary to foster lasting, positive behavioral change.
4. **Economic & Commercial Opportunity**:
   * Lowers long-term insurance and clinical healthcare costs through proactive behavioral self-regulation.
   * Establishes **Google Gemini as the global pioneer of Proactive Diabetic Self-Management**, creating opportunities for tailored consumer wellness, diabetic-safe food retail integration, and health services.

---"""

content = content.replace("#### 1.5 Human-Centric Metric of Success: Patient Daily Engagement & Physician Utility\n\nBoth Six Sigma (<= 3.4 Defects Per Million Opportunities)", 
                          hdyf_section + "\n\n#### 1.5 Human-Centric Metric of Success: Patient Daily Engagement & Physician Utility\n\nBoth Six Sigma (<= 3.4 Defects Per Million Opportunities)")

# Update Section 12 with REQ-18
sec12_old = """  11. Human-Centric Adoption Verification: Daily patient utilization tracking and physician FHIR R4 report validation."""
sec12_new = """  11. Human-Centric Adoption Verification: Daily patient utilization tracking and physician FHIR R4 report validation.
  12. Subjective Experiential Telemetry: "How Do You Feel?" (HDYF) logging, correlation with metabolic datasets, and FHIR export."""

content = content.replace(sec12_old, sec12_new)

with open(req_path, "w", encoding="utf-8") as f:
    f.write(content)

print("[✔] Updated Requirements Specification with HDYF Section 1.6 and REQ-18.")
