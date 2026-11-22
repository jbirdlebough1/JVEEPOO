with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
# Find all step elements
step_ids = re.findall(r'id=[\"\']([A-Za-z0-9_]*step[A-Za-z0-9_]*)[\"\']', text, re.I)
print("Step IDs across index.html:", list(set(step_ids)))

# Find renderReportsView definition
m = text.find('function renderReportsView')
print("\nrenderReportsView at", m)
if m != -1:
    print(text[m:m+1500])
