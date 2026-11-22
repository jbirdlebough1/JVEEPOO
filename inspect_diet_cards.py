with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re
pos_diet = html.find('id="pageDietTrades"')
pos_wf = html.find('id="pageWatchFaces"')
diet_html = html[pos_diet:pos_wf]

print('Length of pageDietTrades:', len(diet_html))
headers = re.findall(r'<div[^>]*class="[^"]*section-title[^"]*"[^>]*>(.*?)</div>', diet_html, re.DOTALL)
print('Titles in pageDietTrades:')
for h in headers:
    print('  ', re.sub(r'\s+', ' ', h.strip())[:120])

cards = re.findall(r'<div[^>]*id="([^"]+)"[^>]*>', diet_html)
print('\nIDs in pageDietTrades:')
for cid in cards:
    print('  ', cid)
