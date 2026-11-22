with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# inspect switchTab definition
m = re.search(r'function switchTab\([^\)]*\)\s*\{', text)
if m:
    idx = m.start()
    print('--- switchTab definition ---')
    print(text[idx:idx+1500])

# inspect DOMContentLoaded handler
m = re.search(r'addEventListener\([\'\"]DOMContentLoaded[\'\"]', text)
if m:
    idx = m.start()
    print('\n--- DOMContentLoaded handler ---')
    print(text[idx:idx+1500])
