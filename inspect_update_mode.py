with open(r'D:\veepoo\opt2_decompiled\vpprotocol\sources\com\veepoo\protocol\VPOperateManager.java', 'r', encoding='utf-8', errors='ignore') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'makeDeviceIntoUpdateMode', text)]
for idx in matches:
    print(text[max(0, idx-50):min(len(text), idx+300)])
    print("-" * 50)
