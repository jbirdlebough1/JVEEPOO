with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# Find headers in pageDietTrades and pageLearning
for page_id in ['pageDietTrades', 'pageLearning']:
    idx = text.find(f'id="{page_id}"')
    if idx != -1:
        snippet = text[idx:idx+3500]
        print(f"=== {page_id} top ===")
        print(snippet[:1500])
