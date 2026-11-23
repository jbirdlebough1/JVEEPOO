with open('D:/veepoo/run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(360, min(len(lines), 380)):
    print(f"{i+1}: {lines[i]}", end='')
