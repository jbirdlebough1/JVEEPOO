with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'<div[^>]+id=[\'"](page[a-zA-Z0-9_]+)[\'"]', text)
print('Pages with id="page...":', matches)
