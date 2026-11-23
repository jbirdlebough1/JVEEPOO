with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('function setTallChartTimeframe')
lines = text.splitlines()
line_no = text[:pos].count('\n') + 1
print('setTallChartTimeframe at line:', line_no)
for i in range(line_no - 1, min(line_no + 30, len(lines))):
    print(f'{i+1}: {lines[i]}')
