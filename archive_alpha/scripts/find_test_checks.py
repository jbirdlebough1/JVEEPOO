with open('test_accumulated_qa.py', 'r', encoding='utf-8') as f:
    tqa = f.read()

import re
matches = re.findall(r'def\s+(test_[a-zA-Z0-9_]*(?:report|timeline|bivariate|correlator|tall)[a-zA-Z0-9_]*)', tqa, re.IGNORECASE)
print('QA tests matching keywords:', matches)

with open('run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    taudit = f.read()
matches2 = re.findall(r'def\s+(test_[a-zA-Z0-9_]*(?:report|timeline|bivariate|correlator|tall)[a-zA-Z0-9_]*)', taudit, re.IGNORECASE)
print('Audit tests matching keywords:', matches2)
