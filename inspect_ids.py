with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
for id_name in ['valGlucose', 'valBP', 'valTemp', 'valSpO2', 'btnSkipFullScan']:
    matches = list(re.finditer(rf'\b{id_name}\b', text))
    print(f"--- {id_name} ({len(matches)} occurrences) ---")
    for m in matches[:5]:
        s = max(0, m.start() - 40)
        e = min(len(text), m.end() + 40)
        print(text[s:e].replace('\n', ' '))
