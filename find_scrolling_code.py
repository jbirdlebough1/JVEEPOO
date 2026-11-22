with open('presentation/preview.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'scroll|Scroll|scrollTop|scrollIntoView', text)]
print('Matches in preview.html:', len(matches))
lines = text.splitlines()
for m in matches:
    line_no = text[:m].count('\n') + 1
    print(f'Line {line_no}: {lines[line_no-1].strip()[:100]}')
