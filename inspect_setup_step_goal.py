with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('DAILY STEP GOAL')
line_no = text[:pos].count('\n') + 1
lines_arr = text.splitlines()
for j in range(line_no - 3, min(line_no + 15, len(lines_arr))):
    print(f'{j+1}: {lines_arr[j]}')
