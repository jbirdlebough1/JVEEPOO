with open(r'D:\veepoo\run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    text = f.read()

for tc in ['TC-00', 'TC-03', 'TC-17']:
    idx = text.find(tc)
    print(f"=== {tc} ===")
    print(text[idx:idx+800])
