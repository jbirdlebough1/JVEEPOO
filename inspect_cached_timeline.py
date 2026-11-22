with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("=== cached2DayTimeline occurrences ===")
for m in re.finditer(r'([^\n]*cached2DayTimeline[^\n]*)', text):
    print("  ", m.group(1).strip())
