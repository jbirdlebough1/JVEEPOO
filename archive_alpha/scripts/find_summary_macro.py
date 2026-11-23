with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'summaryMacroBreakfast', text)]
print('Matches for summaryMacroBreakfast in index.html:', len(matches))
for idx in matches:
    print('---')
    print(text[max(0, idx-100):min(len(text), idx+300)])
