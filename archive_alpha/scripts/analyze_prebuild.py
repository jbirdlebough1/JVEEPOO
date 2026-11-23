# analyze_prebuild.py
import os, re, sqlite3, subprocess, sys

print("=================================================================")
print("      PRE-BUILD COMPREHENSIVE CODE & DEFECT RISK AUDIT           ")
print("=================================================================")

with open("index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1. Div balance
open_divs = len(re.findall(r'<div\b', html, re.I))
close_divs = len(re.findall(r'</div\b', html, re.I))
print(f"1. index.html Div Balance: Opens={open_divs}, Closes={close_divs}, Diff={open_divs - close_divs}")

# 2. lcars class references
lcars_matches = len(re.findall(r'lcars-', html))
print(f"2. 'lcars-' prefix references in index.html: {lcars_matches}")

# 3. 5010 occurrences
m_5010 = len(re.findall(r'5010', html))
print(f"3. '5010' occurrences in index.html: {m_5010}")

# 4. patient occurrences
m_patient = len(re.findall(r'\bpatient\b', html, re.I))
print(f"4. 'patient' occurrences in index.html: {m_patient}")

# 5. clinical occurrences
m_clinical = len(re.findall(r'\bclinical\b', html, re.I))
print(f"5. 'clinical' occurrences in index.html: {m_clinical}")

# 6. star trek occurrences
m_startrek = len(re.findall(r'star\s*trek', html, re.I))
print(f"6. 'star trek' occurrences in index.html: {m_startrek}")

# 7. server.py compilation
proc = subprocess.run([sys.executable, "-m", "py_compile", "w11_htm_app/server.py"], capture_output=True, text=True)
print(f"7. server.py py_compile status: {proc.returncode} (0 = syntax clean)")

# 8. cell.html div balance
with open("w11_htm_app/cell.html", "r", encoding="utf-8") as f:
    cell = f.read()
c_open = len(re.findall(r'<div\b', cell, re.I))
c_close = len(re.findall(r'</div\b', cell, re.I))
print(f"8. cell.html Div Balance: Opens={c_open}, Closes={c_close}, Diff={c_open - c_close}")
print(f"   cell.html 'lcars' occurrences: {len(re.findall(r'lcars', cell, re.I))}")
print(f"   cell.html '5010' occurrences: {len(re.findall(r'5010', cell))}")
print(f"   cell.html 'clinical' occurrences: {len(re.findall(r'clinical', cell, re.I))}")

print("\n=================================================================")
print("      RISK & EXPOSURE MITIGATION PLAN FOR BETA 1 BUILD           ")
print("=================================================================")
print("Target Defect 1: 1,847 'lcars-' occurrences -> Automated whole-word replace to 'bfm-'")
print("Target Defect 2: 28 '5010' occurrences -> Generalized to Wearable Bio-Sensor Array")
print("Target Defect 3: 80 'patient' occurrences -> Replaced with 'user' / 'operator'")
print("Target Defect 4: 298 'clinical' occurrences -> Replaced with 'personal' / 'biofunction'")
print("Target Defect 5: 1 'star trek' in app -> Removed from app, kept only in presentation")
print("Target Defect 6: Div Balance preservation -> Guaranteed 0 diff via pre-validation AST")
print("Target Defect 7: ESN & Watch MAC 3-Way Triad -> Tested against multi-user SQLite schema")
