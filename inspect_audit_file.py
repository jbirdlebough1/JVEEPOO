with open('run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()
print(f'Total lines: {len(lines)}')
for i in range(min(40, len(lines))):
    print(lines[i], end='')
