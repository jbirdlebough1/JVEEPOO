with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
for target_id in ['valBP', 'txtHRV', 'badgeBP', 'valGlucose', 'valHeartRate', 'valTemp', 'valSpO2']:
    matches = list(re.finditer(f'id=["\']{target_id}["\']', text))
    print(f"=== Element id='{target_id}' (found {len(matches)} times) ===")
    for m in matches:
        start = max(0, m.start() - 100)
        end = min(len(text), m.end() + 150)
        print(text[start:end].replace('\n', ' '))
