with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("=== CSS FOR log-tx ===")
for m in re.finditer(r'([^\n]*log-tx[^\n]*)', text):
    print("  ", m.group(1).strip())

idx = text.find('function logLCARS')
print(text[idx:idx+1800])
