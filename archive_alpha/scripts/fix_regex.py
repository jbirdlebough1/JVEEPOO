with open('D:/veepoo/run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    suite = f.read()

bad_line = '''stages_block_m = re.search(r'id=["\']gridFullScanStages["\'][\s\S]*?(?=<div\s+id=["\']fullScanActiveHud)', idx_content)'''
good_line = '''stages_block_m = re.search(r'gridFullScanStages[\\s\\S]*?fullScanActiveHud', idx_content)'''

suite = suite.replace(bad_line, good_line)

with open('D:/veepoo/run_full_audit_test_suite.py', 'w', encoding='utf-8') as f:
    f.write(suite)

print("Fixed syntax in run_full_audit_test_suite.py!")
