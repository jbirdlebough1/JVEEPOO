with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# find exercise/workout functions
for m in re.finditer(r'function\s+[A-Za-z0-9_]*(?:Exercise|Workout)[A-Za-z0-9_]*[^{]*\{', text):
    print(m.group(0))

idx = text.find('BAY 4: Ambulatory Exercise & Workout Dynamics')
if idx != -1:
    print('\n=== Bay 4 Exercise HTML & JS ===')
    print(text[idx:idx+1500])
