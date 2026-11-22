with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'_sleepPackets', text)]
print('Matches for _sleepPackets in index.html:', len(matches))
for m in matches:
    print(text[max(0, m-100):min(len(text), m+300)].replace('\n', ' '))
