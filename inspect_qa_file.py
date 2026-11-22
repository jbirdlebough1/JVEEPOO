with open('test_accumulated_qa.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()
print(f'Total lines: {len(lines)}')
for i in range(min(50, len(lines))):
    print(lines[i], end='')
