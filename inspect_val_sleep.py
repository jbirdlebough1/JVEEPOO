with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'id=["\']valSleep["\']', text)]
print('Matches for valSleep in index.html:', len(matches))
for m in matches:
    print(text[max(0, m-200):min(len(text), m+400)].replace('\n', ' '))
