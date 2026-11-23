# check_nutrition_parser.py
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8', errors='ignore') as f:
    server = f.read()

import re

# Check if there is any OCR or regex parsing for nutrition facts in server.py
nutrition_regexes = re.findall(r'(carbs?|calories|protein|fat|fiber).*?=', server, re.I)
print("Server nutrition assignments:", len(nutrition_regexes))

# Check how payload in server.py handles client-supplied nutrition
for i, line in enumerate(server.splitlines(), 1):
    if '/api/meal/upload' in line or 'insert_meal_entry' in line:
        print(f"Server L{i:4}: {line.strip()[:100]}")
