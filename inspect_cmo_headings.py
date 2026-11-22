with open('docs/cmo_spec.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
headings = re.findall(r'<h[123][^>]*>(.*?)</h[123]>', text)
for h in headings[:35]:
    clean = re.sub(r'<[^>]+>', '', h).strip()
    print('  ', clean)
