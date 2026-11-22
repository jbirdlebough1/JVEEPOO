with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('function setReportTimeframe(')
print('setReportTimeframe found at char:', pos)
lines = text.splitlines()
line_no = text[:pos].count('\n') + 1
print('Line:', line_no)
for i in range(line_no - 1, min(line_no + 40, len(lines))):
    print(f'{i+1}: {lines[i]}')
