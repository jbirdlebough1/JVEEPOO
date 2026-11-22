with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# Check pageFullScan
idx = text.find('id="pageFullScan"')
print('pageFullScan index:', idx)
if idx != -1:
    print(text[max(0, idx-100):min(len(text), idx+600)])

# Check main content wrapper and styles
idx_main = text.find('<main')
print('\n<main> element:')
if idx_main != -1:
    print(text[idx_main:idx_main+400])

# Check switchTab default call
print('\nswitchTab default calls:')
for m in re.finditer(r'switchTab\([^\)]*\)', text):
    print(m.group(0))

# Check DOMContentLoaded or window.onload
print('\nonload / DOMContentLoaded handlers:')
for m in re.finditer(r'addEventListener\([\'\"]DOMContentLoaded[\'\"][^\n]*', text):
    print(m.group(0))
for m in re.finditer(r'window\.onload\s*=[^\n]*', text):
    print(m.group(0))
