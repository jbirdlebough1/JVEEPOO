with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'RUN ANOTHER', text, re.IGNORECASE)]
for m in matches:
    start = max(0, text.rfind('\n', 0, m - 50))
    end = min(len(text), text.find('\n', m + 150))
    print("--- MATCH ---")
    print(text[start:end])
