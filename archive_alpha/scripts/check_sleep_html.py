with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

print("=== Sleep in index.html ===")
for m in re.finditer(r'querySleepData|parseSleepPacket|parseExtendedSleepStream', html):
    pos = m.start()
    print(f"At {pos}:")
    print(html[pos-50:pos+300])
    print("="*40)
