with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
m = re.search(r'async function pollLiveTelemetryChanges', text)
if m:
    start = m.start()
    end = text.find('async function fetchTelemetryLatest', start)
    print(text[start:end])
