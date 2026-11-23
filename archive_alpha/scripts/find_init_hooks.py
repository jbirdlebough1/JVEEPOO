with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Let's inspect where initializeLCARSDashboard or window.onload or DOMContentLoaded is
import re
matches = re.findall(r'(function\s+init[A-Za-z0-9_]*|window\.addEventListener\([\'\"]DOMContentLoaded[\'\"]|window\.onload)', text)
print("Initialization hooks found:", matches)

for m in ['initializeLCARSDashboard', 'DOMContentLoaded', 'initApp', 'initDashboard']:
    pos = text.find(m)
    if pos != -1:
        print(f"Hook '{m}' found at {pos}:")
        print(text[pos:pos+500])
