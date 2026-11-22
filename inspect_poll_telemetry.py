with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
m = re.search(r'async function pollLiveTelemetryChanges', text)
if m:
    start = m.start()
    print("--- pollLiveTelemetryChanges ---")
    print(text[start:start+2500])

m2 = re.search(r'async function fetchTelemetryLatest', text)
if m2:
    start = m2.start()
    print("--- fetchTelemetryLatest ---")
    print(text[start:start+2500])
