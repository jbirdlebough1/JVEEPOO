with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('onStepTargetSliderChanged')
if pos != -1:
    line_no = text[:pos].count('\n') + 1
    print('onStepTargetSliderChanged at line:', line_no)
    lines_arr = text.splitlines()
    for j in range(line_no - 1, min(line_no + 120, len(lines_arr))):
        print(f'{j+1}: {lines_arr[j]}')
else:
    print('NOT FOUND')
