# check_label_parser.py
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8', errors='ignore') as f:
    server = f.read()

import re
matches = re.findall(r'(re\.search\([^)]+\)|re\.findall\([^)]+\))', server)
print(f"Regex searches in server.py: {len(matches)}")
for m in matches:
    if any(k in m.lower() for k in ['carb', 'calorie', 'fiber', 'fat', 'protein', 'label', 'gram', 'url', 'http']):
        print(" ", m[:100])
