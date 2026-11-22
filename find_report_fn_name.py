with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('if (isDaily) {')
line_no = text[:pos].count('\n') + 1
print('Line:', line_no)

# Find function name
r_idx = text.rfind('function ', 0, pos)
print('Function at:', text[r_idx:r_idx+60])
