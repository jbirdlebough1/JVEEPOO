with open('D:/veepoo/run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    suite = f.read()

suite = suite.replace('html_root', 'idx_content').replace('server_code', 'server_content')

with open('D:/veepoo/run_full_audit_test_suite.py', 'w', encoding='utf-8') as f:
    f.write(suite)

print("Fixed variable names in run_full_audit_test_suite.py!")
