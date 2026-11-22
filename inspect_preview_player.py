with open('presentation/preview.html', 'r', encoding='utf-8') as f:
    text = f.read()

print('Length of preview.html:', len(text))

import re
# check audio or scrolling or slide logic
scripts = re.findall(r'<script>(.*?)</script>', text, re.DOTALL)
print('Total scripts in preview.html:', len(scripts))
for i, s in enumerate(scripts):
    print(f'Script {i+1} length: {len(s)}')
    for line in s.splitlines()[:25]:
        print('  ', line)
