with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'valSleep\b', text)]
print('Matches for valSleep:', len(matches))
for idx in matches:
    print('---')
    print(text[max(0, idx-60):min(len(text), idx+120)].replace('\n', ' '))
