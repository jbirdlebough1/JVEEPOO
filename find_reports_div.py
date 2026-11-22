with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'<div[^>]+id=[\'"]pageReports[\'"]', text)]
print('Matches for div pageReports:', matches)
for m in matches:
    line_no = text[:m].count('\n') + 1
    print('Line:', line_no)
    lines_arr = text.splitlines()
    for j in range(line_no - 1, min(line_no + 80, len(lines_arr))):
        print(f'{j+1}: {lines_arr[j]}')
