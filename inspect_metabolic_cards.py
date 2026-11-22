with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re
pos_met = html.find('id="pageMetabolic"')
pos_exp = html.find('id="pageExport"')
met_html = html[pos_met:pos_exp]

print('Length of pageMetabolic:', len(met_html))
headers = re.findall(r'<div[^>]*class="[^"]*section-title[^"]*"[^>]*>(.*?)</div>', met_html, re.DOTALL)
print('Titles in pageMetabolic:')
for h in headers:
    print('  ', re.sub(r'\s+', ' ', h.strip())[:120])

cards = re.findall(r'<div[^>]*id="([^"]+)"[^>]*>', met_html)
print('\nIDs in pageMetabolic:')
for cid in cards:
    if any(k in cid.lower() for k in ['diet', 'meal', 'food', 'intake', 'history', 'journal', 'cardio']):
        print('  ', cid)
