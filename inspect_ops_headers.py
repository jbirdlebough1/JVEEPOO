with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageOperations"')
idx_help = text.find('id="pageHelp"', idx)
ops_text = text[idx:idx_help]

import re
headers = re.findall(r'<div class="setup-section[^>]*>[\s\S]*?<div class="setup-section-title">([\s\S]*?)<\/div>', ops_text)
print(f'Total setup sections: {len(headers)}')
for h in headers:
    clean = re.sub(r'<[^>]+>', ' ', h)
    print(' ', re.sub(r'\s+', ' ', clean).strip())
