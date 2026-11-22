with open('presentation/slides/slide_01.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
scripts = re.findall(r'<script>(.*?)</script>', text, re.DOTALL)
print('Scripts in slide_01.html:', len(scripts))
for s in scripts:
    print(s[:1000])
