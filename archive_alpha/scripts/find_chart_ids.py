with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

idx1 = text.find('id="pageReports"')
idx2 = text.find('id="pageSetup"', idx1)
reports_html = text[idx1:idx2]

# Find all chart containers
print("--- Chart IDs in pageReports HTML ---")
for m in re.finditer(r'id=[\'"](chart\w+|rep\w*Chart\w*|\w+Chart\w*)[\'"]', reports_html):
    print(m.group(1))

# Find all chart IDs called in renderReportsView
idx_fn = text.find('function renderReportsView()')
fn_text = text[idx_fn:idx_fn+25000]
print("\n--- IDs called in renderReportsView() JS ---")
for m in re.finditer(r'renderSvg(?:CurveChart|DualCurveChart)\([\'"]([^\'"]+)[\'"]', fn_text):
    print(m.group(1))
