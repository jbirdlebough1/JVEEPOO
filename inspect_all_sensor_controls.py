with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

target_fns = [
    'startGlucoseDetection', 'stopGlucoseDetection',
    'startTempDetection', 'stopTempDetection',
    'startBPDetection', 'stopBPDetection',
    'startSpO2Detection', 'stopSpO2Detection',
    'startHRDetection', 'stopHRDetection',
    'startHRVDetection', 'stopHRVDetection',
    'cancelFullScanSequence', 'skipCurrentFullScanStep'
]

for fn in target_fns:
    m = re.search(r'function\s+' + fn + r'\b', text)
    if not m:
        print(f'{fn}: NOT FOUND')
        continue
    start = m.start()
    next_m = re.search(r'\n    function\s+', text[start+20:])
    end = start + 20 + next_m.start() if next_m else start + 1000
    fn_code = text[start:end]
    print(f'=== {fn} ===')
    print(fn_code.strip())
