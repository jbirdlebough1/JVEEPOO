with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('pageReports')
line_no = text[:pos].count('\n') + 1
print('pageReports found at line:', line_no)

lines_arr = text.splitlines()
for i in range(line_no - 1, min(line_no + 140, len(lines_arr))):
    print(f'{i+1}: {lines_arr[i]}')
