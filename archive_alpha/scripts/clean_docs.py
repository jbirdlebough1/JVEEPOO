import os
import re

# 1. Clean clinical_glucose_study_and_a1c_analysis.md
p1 = 'D:/veepoo/docs/clinical_glucose_study_and_a1c_analysis.md'
if os.path.exists(p1):
    with open(p1, 'r', encoding='utf-8') as f:
        text = f.read()

    # Clean LaTeX math
    text = re.sub(r'\$([^$\n]+)\$', r'\1', text)
    text = re.sub(r'\$\$([^$\n]+)\$\$', r'**\1**', text)
    text = text.replace('\\text{', '').replace('\\mathbf{', '').replace('\\equiv', '==')
    text = text.replace('\\rightleftharpoons', '<=>').replace('\\rightarrow', '->')
    text = text.replace('\\pm', '+/-').replace('\\approx', '~').replace('\\Delta', 'Delta')
    text = text.replace('\\times', '*').replace('\\beta', 'beta')
    text = text.replace('$$', '**').replace('$', '')

    # Replace mermaid blocks with markdown tables
    pattern_m1 = re.compile(r'```mermaid\s+flowchart TD\s+subgraph S1.*?```', re.DOTALL)
    table_s1 = '''| Hematological Factor | Clinical Parameter | Biological Value |
| :--- | :--- | :--- |
| **Prior Glycemic Window** | Historical 90-120 Days | Baseline HbA1c: 8.5% (3 Weeks Ago) |
| **Mathematical Average** | ADAG Formula | Estimated Average Glucose (eAG): 197.3 mg/dL |
| **Erythrocyte Kinetics** | 21 Days Elapsed | 17.5% Red Blood Cell Turnover |
| **Biological True State** | Current Hemoglobin State | True Physiological HbA1c: ~8.1% - 8.3% |'''
    text = pattern_m1.sub(table_s1, text)

    pattern_m2 = re.compile(r'```mermaid\s+flowchart TD\s+subgraph S2.*?```', re.DOTALL)
    table_s2 = '''| Stage | Firmware Mechanism | Physiological Impact |
| :---: | :--- | :--- |
| **1** | Factory Anchor Register Opcode 0x89 0x02 = 0x0184 (70.0 mg/dL) | Sensor internal regression curve assumes non-diabetic baseline. |
| **2** | High Ambient Glycemia (>150 mg/dL) | Readings exhibit -28 to -60 mg/dL systematic negative bias. |
| **3** | Dynamic Anchor Calibration (0x89 0x02 0x01 0x52 0x03) | Injects fasting fingerstick calibration anchor into watch MCU. |
| **4** | Curve Re-Centering | Optical curve aligns with patient true baseline (153.2 mg/dL). |'''
    text = pattern_m2.sub(table_s2, text)

    with open(p1, 'w', encoding='utf-8') as f:
        f.write(text)
    print(f'Cleaned {p1}')

# 2. Clean standalone_htm_cross_platform_architecture_proposal.md
p2 = 'D:/veepoo/docs/standalone_htm_cross_platform_architecture_proposal.md'
if os.path.exists(p2):
    with open(p2, 'r', encoding='utf-8') as f:
        text = f.read()

    # Replace ASCII box art with clean Markdown tables
    pattern_box = re.compile(r'```\s*┌.*?┘\s*```', re.DOTALL)
    table_box1 = '''| Workstation Layer | Platforms & Drivers | Key Responsibilities |
| :--- | :--- | :--- |
| **Client Platforms** | Windows PC, Apple Mac, Chromebook, Android, iPadOS | Zero-install single-file HTM execution via modern browser. |
| **Hardware Driver** | Web Bluetooth GATT (0xFEE7) | Direct continuous BLE telemetry streaming from wrist sensor. |
| **Deterministic Core** | Standalone JavaScript / WASM | IEEE 754 fixed-precision math, GLUT4 kinetics, eA1C modeling. |
| **Local Vault** | SQLite-WASM / IndexedDB | 365-day continuous epoch storage (<25MB) with eviction shield. |
| **Dual Export** | File System Access API | Automated daily export: Format 1 (.sqlite) + Format 2 (FHIR R4 .json). |'''
    text = pattern_box.sub(table_box1, text)

    # Clean any remaining box characters
    for c in ['┌', '┐', '└', '┘', '│', '─', '├', '┤', '┬', '┴', '┼']:
        text = text.replace(c, '')

    # Clean any math dollars
    text = re.sub(r'\$([^$\n]+)\$', r'\1', text)
    text = text.replace('$$', '**').replace('$', '')
    text = text.replace('\\text{', '').replace('\\frac', '').replace('\\right', '').replace('\\left', '')

    with open(p2, 'w', encoding='utf-8') as f:
        f.write(text)
    print(f'Cleaned {p2}')

# 3. Clean cmo_spec.html and generate_cmo_spec.py
p3 = 'D:/veepoo/docs/cmo_spec.html'
if os.path.exists(p3):
    with open(p3, 'r', encoding='utf-8') as f:
        text = f.read()
    text = text.replace('($7.4\\% \\to 5.6\\%$)', '(7.4% to 5.6%)')
    text = text.replace('Pearson $r$, $R^2$', 'Pearson r, R^2')
    text = text.replace('($RR_n$ vs $RR_{n+1}$)', '(RR[n] vs RR[n+1])')
    with open(p3, 'w', encoding='utf-8') as f:
        f.write(text)
    print(f'Cleaned {p3}')

p4 = 'D:/veepoo/docs/generate_cmo_spec.py'
if os.path.exists(p4):
    with open(p4, 'r', encoding='utf-8') as f:
        text = f.read()
    text = text.replace('($7.4\\% \\to 5.6\\%$)', '(7.4% to 5.6%)')
    text = text.replace('Pearson $r$, $R^2$', 'Pearson r, R^2')
    text = text.replace('($RR_n$ vs $RR_{n+1}$)', '(RR[n] vs RR[n+1])')
    with open(p4, 'w', encoding='utf-8') as f:
        f.write(text)
    print(f'Cleaned {p4}')

print('All legacy docs cleaned successfully!')
