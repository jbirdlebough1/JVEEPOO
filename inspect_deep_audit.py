import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

print('=== 1. STEP IDS IN DOM ===')
step_ids = set(re.findall(r'id=["\']([^"\']*(?:step|pedometer)[^"\']*)["\']', text, re.I))
for sid in sorted(step_ids):
    print('  ID:', sid)

print('\n=== 2. SEARCH FOR valStep in JS ===')
for m in re.finditer(r'([^\n;]{1,80}valStep[^\n;]{1,80})', text):
    print('  Line:', m.group(0).strip())

print('\n=== 3. RENDER REPORTS VIEW FUNCTION ===')
idx = text.find('function renderReportsView')
if idx != -1:
    print(text[idx:idx+3500])

print('\n=== 4. SLEEP IN TELEMETRY DB / API ===')
idx_sleep = text.find('assembleAndParseSleepStream')
if idx_sleep != -1:
    print(text[idx_sleep:idx_sleep+2500])

print('\n=== 5. DOMContentLoaded / STARTUP ===')
idx_dom = text.find("addEventListener('DOMContentLoaded'")
if idx_dom != -1:
    print(text[idx_dom:idx_dom+2000])
