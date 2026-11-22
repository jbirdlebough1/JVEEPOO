with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# 1. Bay 3 HTML
idx = text.find('id="btnReadSleepYest"')
print('=== Sleep Buttons HTML in pageCardio ===')
print(text[idx-400:idx+400])

# 2. querySleepData JS implementation
idx2 = text.find('function querySleepData(')
print('\n=== querySleepData JS ===')
print(text[idx2:idx2+1800])

# 3. 0xE0 opcode receiver in BLE packet parser
idx3 = text.find('case 0xE0:')
if idx3 == -1:
    idx3 = text.find('0xE0')
print('\n=== 0xE0 Opcode Handling in BLE ===')
print(text[idx3-50:idx3+2500])
