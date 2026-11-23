with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'id=[\'"]pageConfig[\'"]|id=[\'"]pageClinicalGoals[\'"]', text)]
for m in matches:
    line_no = text[:m].count('\n') + 1
    print('Tab found at line:', line_no)
    lines_arr = text.splitlines()
    for j in range(line_no - 1, min(line_no + 30, len(lines_arr))):
        print(f'{j+1}: {lines_arr[j]}')
