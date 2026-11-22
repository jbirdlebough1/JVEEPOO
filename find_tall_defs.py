with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'function\s+([a-zA-Z0-9_]*Tall[a-zA-Z0-9_]*|setTall[a-zA-Z0-9_]*|onTall[a-zA-Z0-9_]*)', text)]
for m in matches:
    line_no = text[:m].count('\n') + 1
    print('Match at line:', line_no)
    lines_arr = text.splitlines()
    for j in range(line_no - 1, min(line_no + 15, len(lines_arr))):
        print(f'{j+1}: {lines_arr[j]}')

matches2 = [m.start() for m in re.finditer(r'sliderStepTarget', text)]
for m in matches2:
    line_no = text[:m].count('\n') + 1
    print('sliderStepTarget at line:', line_no)
    lines_arr = text.splitlines()
    for j in range(line_no - 1, min(line_no + 10, len(lines_arr))):
        print(f'{j+1}: {lines_arr[j]}')
