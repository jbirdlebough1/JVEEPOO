with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# find body and layout container
idx_body = text.find('<body')
idx_main = text.find('<main')
print('--- HTML between <body and <main ---')
print(text[idx_body:idx_main+100])

# find CSS for layout
print('\n--- CSS for layout ---')
for cls in ['body', '.lcars-app', '.lcars-body', '.lcars-content', '.lcars-sidebar', '.lcars-workspace', '.diet-container']:
    pattern = re.escape(cls) + r'\s*\{[^}]*\}'
    for m in re.finditer(pattern, text):
        print(m.group(0))
