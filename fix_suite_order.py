with open('D:/veepoo/run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    content = f.read()

# Find Group 15
pos_g15 = content.find('# GROUP 15: HEX CODE SCRUB')
pos_end_g15 = content.find('total_tests = len(results)', pos_g15)
if pos_end_g15 == -1:
    pos_end_g15 = content.find('print(f"AUDIT SUMMARY:', pos_g15)

g15_code = content[pos_g15:pos_end_g15].strip()

# Remove g15 from its current location
content_no_g15 = content[:pos_g15] + content[pos_end_g15:]

# Now find TC-39 and insert g15 right after TC-39
pos_tc39 = content_no_g15.find('run_test("TC-39"')
pos_after_tc39 = content_no_g15.find('\n\n', pos_tc39)

content_fixed = (
    content_no_g15[:pos_after_tc39] +
    "\n\n" + g15_code + "\n\n" +
    content_no_g15[pos_after_tc39:]
)

# In the summary section, make sure total_tests is computed
summary_block = '''# -----------------------------------------------------------------------------
# SUMMARY & VERDICT
# -----------------------------------------------------------------------------
total_tests = len(results)
passed_tests = sum(1 for r in results if r["passed"])
failed_tests = total_tests - passed_tests'''

import re
content_fixed = re.sub(
    r'# -----------------------------------------------------------------------------\s*# SUMMARY & VERDICT\s*# -----------------------------------------------------------------------------[\s\S]*?(?=print\("\\n===)',
    summary_block + '\n\n',
    content_fixed
)

with open('D:/veepoo/run_full_audit_test_suite.py', 'w', encoding='utf-8') as f:
    f.write(content_fixed)

print("Reordered Group 15 and summary section in run_full_audit_test_suite.py!")
