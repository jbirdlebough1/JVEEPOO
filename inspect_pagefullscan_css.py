with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

# find CSS for .lcars-workspace, .tab-page, .active-page, #pageFullScan
print('--- CSS Rules ---')
for m in re.finditer(r'(\.lcars-workspace|\.tab-page|\.active-page|#pageFullScan)[^\{]*\{[^}]*\}', text):
    print(m.group(0))

# inspect where pageFullScan starts and ends
start_pfs = text.find('id="pageFullScan"')
# find next tab-page
next_page = text.find('class="tab-page', start_pfs + 20)
print('\n--- pageFullScan length ---')
print('Length of pageFullScan content:', next_page - start_pfs)
print('Start of pageFullScan snippet:')
print(text[start_pfs:start_pfs+1000])
