with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
m = re.search(r'async function pollLiveTelemetryChanges', text)
if m:
    start = m.start()
    fn_text = text[start:start+5000]
    print("Has SLEEP in pollLiveTelemetryChanges?", "SLEEP" in fn_text)
    print("Has lastGlucoseMg in pollLiveTelemetryChanges?", "lastGlucoseMg" in fn_text)
    print("Has valSleep in pollLiveTelemetryChanges?", "valSleep" in fn_text)
    print("Has repVal in pollLiveTelemetryChanges?", "repVal" in fn_text)
