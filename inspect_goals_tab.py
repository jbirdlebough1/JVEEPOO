with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
idx = text.find('id="goals"')
if idx == -1:
    idx = text.find('id="tab-goals"')
if idx == -1:
    idx = text.find('CLINICAL GOALS')
print("Goals tab HTML:", text[idx:idx+2500])
