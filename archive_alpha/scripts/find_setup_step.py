with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('id="pageSetup"')
pos_end = text.find('class="tab-page"', pos + 50)
setup_html = text[pos:pos_end]

import re
matches = re.findall(r'<label[^>]*>(.*?)</label>', setup_html, re.DOTALL)
for m in matches:
    clean = re.sub(r'<[^>]+>', '', m).strip()
    if any(k in clean.lower() for k in ['step', 'target', 'goal', 'pedometer', 'walk']):
        print('Setup field matching step/target:', clean)
