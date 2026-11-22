with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'<div[^>]*id=["\']([^"\']*(?:meal|diet)[^"\']*)["\']', text, re.I)
print("DOM IDs with meal or diet:", set(matches))
