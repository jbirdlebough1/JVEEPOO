with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("=== mealJournal occurrences ===")
for m in re.finditer(r'([^\n]*mealJournal[^\n]*)', text):
    print("  ", m.group(1).strip()[:100])

print("\n=== /api/meal occurrences ===")
for m in re.finditer(r'([^\n]*/api/meal[^\n]*)', text):
    print("  ", m.group(1).strip()[:100])
