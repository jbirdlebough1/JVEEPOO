with open(r'D:\veepoo\opt2_decompiled\vpprotocol\sources\com\veepoo\protocol\util\UiUpdateUtil.java', 'r', encoding='utf-8', errors='ignore') as f:
    text = f.read()

import re
pos = text.find('startUpdateUi')
if pos == -1:
    pos = text.find('startUpdate')
if pos == -1:
    pos = text.find('updateUi')
print("Position:", pos)
if pos != -1:
    print(text[pos:pos+1500])
else:
    for m in re.finditer(r'public void [a-zA-Z0-9_]+\(', text):
        print(m.group(0))
