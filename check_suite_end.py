with open('D:/veepoo/run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    suite = f.read()

import re

for m in re.finditer(r'AUDIT SUMMARY', suite):
    print(m.start(), suite[m.start()-50:m.start()+100])
