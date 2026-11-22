with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'function (?:flash|install|upload|load|render)[A-Za-z0-9_]*Dial[A-Za-z0-9_]*\(', text)
print("Dial functions:", matches)
for m in matches:
    fname = m.replace('function ', '').replace('(', '')
    idx = text.find(f'function {fname}')
    print(f"\n--- {fname} ---")
    print(text[idx:idx+500])
