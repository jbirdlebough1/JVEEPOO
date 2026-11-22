with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# find all sound function definitions
sound_defs = set(re.findall(r'function\s+(sound[A-Za-z0-9_]*)\s*\(', text))
print('Defined sound functions:', sound_defs)

# find all calls to sound functions
for sound_fn in sound_defs:
    calls = list(re.finditer(re.escape(sound_fn) + r'\s*\(', text))
    print(f'\nSound fn: {sound_fn} (called {len(calls)} times)')
    for c in calls:
        idx = c.start()
        line = text[:idx].count('\n') + 1
        line_str = text[max(0, idx-40):min(len(text), idx+60)].replace('\n', ' ')
        print(f'  L{line}: {line_str}')
