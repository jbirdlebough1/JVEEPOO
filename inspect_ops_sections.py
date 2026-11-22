with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageOperations"')
idx2 = text.find('id="pageLog"')
ops_html = text[idx:idx2]

import re
titles = re.findall(r'<div class="setup-section-title">[\s\S]*?<\/div>', ops_html)
print(f'Total sections in pageOperations: {len(titles)}')
for t in titles:
    clean = re.sub(r'<[^>]+>', ' ', t)
    print(' ', re.sub(r'\s+', ' ', clean).strip())
