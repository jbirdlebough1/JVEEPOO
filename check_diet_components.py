with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

idx_start = text.find('id="pageDietTrades"')
idx_end = text.find('id="pageMetabolic"')
content = text[idx_start:idx_end]

for m in re.finditer(r'COMPONENT\s*\d+:[^\n<]+', content, re.IGNORECASE):
    print(m.group())

for m in re.finditer(r'<!--\s*=========================================================================\s*([^\n]+)', content):
    print(m.group(1))

# Check for "clinical mentoring" or "air-gapped" in pageDietTrades
for m in re.finditer(r'(clinical\s+mentoring|air[- ]*gapped|06[- ]*ADA)', content, re.IGNORECASE):
    print("Found in diet trades:", repr(content[m.start()-40:m.end()+40]))
