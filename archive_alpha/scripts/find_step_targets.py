with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'[a-zA-Z0-9_]*[sS]tep[tT]arget[a-zA-Z0-9_]*', text)
print('Unique step target identifiers in index.html:', set(matches))

matches_cfg = re.findall(r'(\d+)\s*(?:steps|step\s*target)', text, re.IGNORECASE)
print('Step target numbers mentioned:', set(matches_cfg[:20]))

pos = text.find('stepTarget')
while pos != -1:
    line_no = text[:pos].count('\n') + 1
    print(f'stepTarget at line {line_no}: {text[pos-20:pos+50].strip()}')
    pos = text.find('stepTarget', pos + 10)
