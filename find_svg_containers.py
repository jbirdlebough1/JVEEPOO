with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

idx1 = text.find('id="pageReports"')
idx2 = text.find('id="pageSetup"', idx1)
reports_html = text[idx1:idx2]

containers = re.findall(r'<div[^>]*class=[\'"][^\'"]*svg-timeline-container[^\'"]*[\'"][^>]*id=[\'"]([^\'"]+)[\'"]', reports_html)
print("svg-timeline-container IDs in HTML:")
for c in containers:
    print(" ", c)
