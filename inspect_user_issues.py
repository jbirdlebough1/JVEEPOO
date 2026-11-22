import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

print("--- Tab 06 sidebar & headers ---")
for i, line in enumerate(lines):
    if '06-' in line:
        print(f"L{i+1}: {line[:120]}")

print("\n--- Buttons Rep/Tall ---")
for i, line in enumerate(lines):
    if 'btnRep' in line or 'btnTall' in line:
        print(f"L{i+1}: {line[:120]}")

print("\n--- setReportTimeframe / setTallChartTimeframe ---")
for i, line in enumerate(lines):
    if 'function setReportTimeframe' in line or 'function setTallChartTimeframe' in line:
        print(f"Line {i+1}:")
        for j in range(i, min(i+40, len(lines))):
            print(f"  {lines[j]}")

print("\n--- safe candy/store options trigger in analyzeMealWithGemini ---")
for i, line in enumerate(lines):
    if 'function analyzeMealWithGemini' in line:
        print(f"analyzeMealWithGemini found at line {i+1}")
        for j in range(i, min(i+120, len(lines))):
            if any(k in lines[j] for k in ['safe_options', 'hot_candy', 'candy', 'sour_candy', 'recipe', 'store', 'recommendations_list']):
                print(f"  {j+1}: {lines[j]}")
