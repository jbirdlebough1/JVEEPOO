import re
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageOperations"')
for m in re.finditer(r'<div id="page[A-Za-z0-9_]+"', text[idx:]):
    print(m.group(0), 'at offset', m.start())
