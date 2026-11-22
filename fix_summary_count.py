with open('D:/veepoo/run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    suite = f.read()

suite = suite.replace('total_tests = len(results)', '# total_tests')
# Find where audit summary is printed:
old_print = 'print(f"AUDIT SUMMARY: {total_tests} TOTAL AUDIT CHECKS | {passed_tests} PASSED | {failed_tests} FAILED")'
new_print = '''total_tests = len(results)
passed_tests = sum(1 for r in results if r["passed"])
failed_tests = total_tests - passed_tests
print(f"AUDIT SUMMARY: {total_tests} TOTAL AUDIT CHECKS | {passed_tests} PASSED | {failed_tests} FAILED")'''

suite = suite.replace(old_print, new_print)

with open('D:/veepoo/run_full_audit_test_suite.py', 'w', encoding='utf-8') as f:
    f.write(suite)

print("Updated audit summary count!")
