with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re
matches = re.findall(r'<button[^>]*navTab[^>]*>.*?</button>', html, re.DOTALL)
print(f'Found {len(matches)} nav tabs:')
for m in matches:
    print('  ', re.sub(r'\s+', ' ', m))
