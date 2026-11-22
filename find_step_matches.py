with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.finditer(r'(?:goalStepTarget|stepTarget|step_target|target_step|dailySteps|watchStep)', text)
for m in matches:
    pos = m.start()
    line_no = text[:pos].count('\n') + 1
    print(f'Line {line_no}: {text[max(0, pos-40):pos+60].strip()}')
