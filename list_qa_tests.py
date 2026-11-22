with open('test_accumulated_qa.py', 'r', encoding='utf-8') as f:
    tqa = f.read()

import re
matches = re.findall(r'def\s+(test_[a-zA-Z0-9_]+)', tqa)
print('QA tests:', matches)
