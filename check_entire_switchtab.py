with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
m = re.search(r'function switchTab\(tabId\)\s*\{', text)
if m:
    idx = m.start()
    end_idx = text.find('function ', idx + 20)
    print(text[idx:end_idx])
