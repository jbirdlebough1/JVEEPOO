with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'cached2DayTimeline', text)]
print('Matches in index.html:', len(matches))
for m in matches:
    line_no = text[:m].count('\n') + 1
    print('Line:', line_no)
    lines_arr = text.splitlines()
    for j in range(max(0, line_no - 3), min(line_no + 12, len(lines_arr))):
        print(f'{j+1}: {lines_arr[j]}')
