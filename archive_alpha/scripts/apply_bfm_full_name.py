import os

# 1. GOOGLE_LEADERSHIP_PRIMER.md
p1 = r"D:\veepoo\docs\GOOGLE_LEADERSHIP_PRIMER.md"
c1 = open(p1, "r", encoding="utf-8").read()
c1 = c1.replace(
    'The foundational mission of the **BFM Console (Powered By Gemini)** is to',
    'The foundational mission of the **BFM Biometric Function Monitor** (Console Powered By Gemini) is to'
)
open(p1, "w", encoding="utf-8").write(c1)
print("Updated primer.")

# 2. bfm_diabetes_beta_requirements_specification.md
p2 = r"D:\veepoo\docs\bfm_diabetes_beta_requirements_specification.md"
c2 = open(p2, "r", encoding="utf-8").read()
c2 = c2.replace(
    '# BFM Console (Powered By Gemini): Requirements & Architectural Specification',
    '# BFM Biometric Function Monitor: Requirements & Architectural Specification'
)
c2 = c2.replace(
    '* **System Name**: **BFM Console (Powered By Gemini)**',
    '* **System Name**: **BFM Biometric Function Monitor** (Console Powered By Gemini)'
)
c2 = c2.replace(
    'The most important philosophy and overarching goal of the **BFM Console (Powered By Gemini)** is to',
    'The most important philosophy and overarching goal of the **BFM Biometric Function Monitor** (Console Powered By Gemini) is to'
)
open(p2, "w", encoding="utf-8").write(c2)
print("Updated requirements spec.")

# 3. bfm_diabetes_beta1_functional_specification.md
p3 = r"D:\veepoo\docs\bfm_diabetes_beta1_functional_specification.md"
c3 = open(p3, "r", encoding="utf-8").read()
c3 = c3.replace(
    '# BFM Console (Powered By Gemini): Functional Specification (FS)',
    '# BFM Biometric Function Monitor: Functional Specification (FS)'
)
c3 = c3.replace(
    '* **Document Purpose**: Formal technical contract establishing the exact architecture, code migration plan, database schemas, and quality controls for the **Beta 1 Build** of the BFM Console.',
    '* **Document Purpose**: Formal technical contract establishing the exact architecture, code migration plan, database schemas, and quality controls for the **Beta 1 Build** of the **BFM Biometric Function Monitor** (Console Powered By Gemini).'
)
c3 = c3.replace(
    'The ultimate mission of the **BFM Console (Powered By Gemini)** is to',
    'The ultimate mission of the **BFM Biometric Function Monitor** (Console Powered By Gemini) is to'
)
open(p3, "w", encoding="utf-8").write(c3)
print("Updated functional spec.")

# 4. bfm_beta1_master_test_plan.md
p4 = r"D:\veepoo\docs\bfm_beta1_master_test_plan.md"
c4 = open(p4, "r", encoding="utf-8").read()
c4 = c4.replace(
    '# BFM Console (Powered By Gemini): Master Test Plan',
    '# BFM Biometric Function Monitor: Master Test Plan'
)
c4 = c4.replace(
    'The primary goal of testing the **BFM Console (Powered By Gemini)** is to',
    'The primary goal of testing the **BFM Biometric Function Monitor** (Console Powered By Gemini) is to'
)
open(p4, "w", encoding="utf-8").write(c4)
print("Updated master test plan.")

# 5. standalone_htm_cross_platform_architecture_proposal.md
p5 = r"D:\veepoo\docs\standalone_htm_cross_platform_architecture_proposal.md"
c5 = open(p5, "r", encoding="utf-8").read()
c5 = c5.replace(
    'To achieve universal patient and clinical adoption, the **BFM 5010 Clinical Workstation** is architected',
    'To achieve universal patient and clinical adoption, the **BFM Biometric Function Monitor** is architected'
)
open(p5, "w", encoding="utf-8").write(c5)
print("Updated standalone proposal.")

# 6. clinical_glucose_study_and_a1c_analysis.md
p6 = r"D:\veepoo\docs\clinical_glucose_study_and_a1c_analysis.md"
c6 = open(p6, "r", encoding="utf-8").read()
c6 = c6.replace(
    'During live telemetry auditing, the optical wrist sensor reported values',
    'During live telemetry auditing of the **BFM Biometric Function Monitor**, the optical wrist sensor reported values'
)
open(p6, "w", encoding="utf-8").write(c6)
print("Updated clinical glucose study.")

print("[✔] All 6 documents successfully updated with 'BFM Biometric Function Monitor' in paragraph 1!")
