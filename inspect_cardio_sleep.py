with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

idx = text.find('id="pageCardio"')
end_idx = text.find('id="pageMetabolic"')
cardio_html = text[idx:end_idx]

print('=== Sleep and Night in pageCardio ===')
for m in re.finditer(r'.{0,40}(?:sleep|night|querySleep|dayOffset).{0,40}', cardio_html, re.IGNORECASE):
    print(m.group(0).strip())

print('\n=== Search across entire JS for sleep query & previous night ===')
for m in re.finditer(r'function\s+querySleep[A-Za-z0-9_]*[^{]*\{[^}]*\}', text):
    print(m.group(0)[:500])
