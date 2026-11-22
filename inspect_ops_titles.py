with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageOperations"')
idx_help = text.find('id="pageHelp"', idx)
ops_text = text[idx:idx_help]

import re
matches = re.findall(r'<span style="color:\s*var\(--lcars-peach\);">([^<]+)</span>', ops_text)
print('Section titles in pageOperations:')
for m in matches:
    print(' ', m.strip())
