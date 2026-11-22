with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'save_vital_record|vital_records', text)]
for m in matches[:10]:
    line_no = text[:m].count('\n') + 1
    print('Line:', line_no)
    lines_arr = text.splitlines()
    for j in range(max(0, line_no - 2), min(line_no + 8, len(lines_arr))):
        print(f'{j+1}: {lines_arr[j]}')
