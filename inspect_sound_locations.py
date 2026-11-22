with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# find all <script> tags
for m in re.finditer(r'<script[^>]*>', text):
    line_num = text[:m.start()].count('\n') + 1
    print(f'Script tag at line {line_num}: {m.group(0)}')

# where is soundSuccess defined?
m_sound = re.search(r'function soundSuccess\b', text)
if m_sound:
    line_num = text[:m_sound.start()].count('\n') + 1
    print(f'soundSuccess defined at line {line_num}')

# where are calls to soundSuccess?
for m in re.finditer(r'soundSuccess\(\)', text):
    line_num = text[:m.start()].count('\n') + 1
    print(f'  soundSuccess() called at line {line_num}')
