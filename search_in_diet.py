with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

diet_text = text[362470:439946]

for m in re.finditer(r'(clinical\s+mentoring|air[- ]*gapped|06[- ]*ADA|quick\s*nav)', diet_text, re.IGNORECASE):
    print("Found:", repr(diet_text[m.start()-40:m.end()+40]))

for m in re.finditer(r'<span[^>]*class="box-title"[^>]*>([\s\S]*?)</span>', diet_text):
    print("Box title:", m.group(1).strip().replace('\n', ' '))
