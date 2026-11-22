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
    'executeScanStage1_Glucose', 'finalizeScanStage1_Glucose',
    'executeScanStage2_Temp', 'finalizeScanStage2_Temp',
    'executeScanStage3_BP', 'finalizeScanStage3_BP',
    'executeScanStage4_SpO2', 'finalizeScanStage4_SpO2',
    'executeScanStage5_HR', 'finalizeScanStage5_HR',
    'executeScanStage6_HRV', 'finalizeScanStage6_HRV',
    'finalizeFullScanReport'
]

print('=== AUDIT OF ELEMENT ACCESS IN SCAN FUNCTIONS ===')
for fn in target_fns:
    m = re.search(r'function\s+' + fn + r'\b[^{]*\{', text)
    if not m:
        print(f'Function {fn} NOT FOUND!')
        continue
    start_pos = m.start()
    # find end of function roughly
    next_fn = re.search(r'\n    function\s+', text[start_pos+20:])
    end_pos = (start_pos + 20 + next_fn.start()) if next_fn else start_pos + 2000
    fn_body = text[start_pos:end_pos]
    
    # find all getElementById
    ids = re.findall(r'document\.getElementById\([\'\"]([^\'\"]+)[\'\"]\)', fn_body)
    print(f'\n--- {fn} --- (referenced {len(ids)} element IDs)')
    for elem_id in ids:
        exists_in_dom = f'id="{elem_id}"' in text or f"id='{elem_id}'" in text
        status = "EXISTS" if exists_in_dom else "MISSING / NULL!"
        if not exists_in_dom:
            print(f'  [!] CRITICAL MISSING: {elem_id}')
        else:
            print(f'  OK: {elem_id}')
