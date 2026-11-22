with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("=== DOM ELEMENTS WITH 'STEP' OR 'MOTION' ===")
for m in re.finditer(r'<[^>]+id=["\']([^"\']*(?:step|motion|pedometer)[^"\']*)["\'][^>]*>', text, re.I):
    tag = m.group(0)[:120]
    id_name = m.group(1)
    print(f"  ID: {id_name:30} Tag: {tag}")

print("\n=== SEARCH FOR updateWorkoutWithSteps ===")
idx = text.find('function updateWorkoutWithSteps')
if idx != -1:
    print(text[idx:idx+800])
