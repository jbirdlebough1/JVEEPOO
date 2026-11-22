import re

for test_file in ['D:/veepoo/run_full_audit_test_suite.py', 'D:/veepoo/test_accumulated_qa.py']:
    with open(test_file, 'r', encoding='utf-8') as f:
        text = f.read()
    print(f"--- {test_file} ---")
    for m in re.finditer(r'pageVitals|valGlucose|navTabVitals', text):
        s = max(0, m.start() - 60)
        e = min(len(text), m.end() + 60)
        print(text[s:e])
