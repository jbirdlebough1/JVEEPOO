with open(r'D:\veepoo\run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'fetch_helpers' in l or 'pop_meals_listbox' in l or 'TC-00' in l:
        print(f"L{i+1}: {l.strip()}")
        for j in range(max(0, i-3), min(len(lines), i+4)):
            print(f"   {j+1}: {lines[j].strip()}")
