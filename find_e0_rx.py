with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

matches = [m.start() for m in re.finditer(r'0xE0', text)]
print(f'Total matches for 0xE0: {len(matches)}')
for idx in matches:
    # print context if around switch or if
    snippet = text[max(0, idx-50):min(len(text), idx+100)]
    if 'case' in snippet or 'if' in snippet or 'rx' in snippet or 'cmd' in snippet:
        print('--- Snippet ---')
        print(snippet.replace('\n', ' '))
