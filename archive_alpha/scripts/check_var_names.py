with open('D:/veepoo/run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    suite = f.read()

import re
matches = re.findall(r'(\w+)\s*=\s*(?:open|f\.read)', suite)
print("File content variables:", matches)
