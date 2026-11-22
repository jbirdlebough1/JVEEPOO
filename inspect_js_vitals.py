with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("--- pageVitals matches ---")
for m in re.finditer(r'pageVitals|navTabVitals', text):
    s = max(0, m.start() - 60)
    e = min(len(text), m.end() + 60)
    print(text[s:e])
    print('---')
