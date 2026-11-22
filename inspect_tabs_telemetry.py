import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

print("=== CHECKING TELEMETRY FETCH AND POPULATION ===")
for fn in ['fetchTelemetryLatest', 'fetchTelemetryTimeline', 'pollLiveTelemetryChanges', 'initializeVitalsAndCardioBaselines', 'populateLatestVitals']:
    idx = text.find(fn)
    if idx != -1:
        print(f"\n--- {fn} ---")
        print(text[idx:idx+1200])

print("\n=== CHECKING TAB 01 FULL SCAN, TAB 02 VITALS, TAB 04 METABOLIC 360, TAB 11 GOALS ===")
# Look for where metrics like glucose, bp, hr, spo2, temp, sleep, steps are updated or rendered
matches = re.findall(r'function (?:update|render|display|load)[A-Za-z0-9_]*\(', text)
print("Update/Render functions found:", matches[:30])
