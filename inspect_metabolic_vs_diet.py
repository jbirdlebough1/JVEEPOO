with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

pos_met = html.find('id="pageMetabolic"')
pos_diet = html.find('id="pageDietTrades"')
pos_rep = html.find('id="pageReports"')

print('pageMetabolic slice:', pos_met, 'to', pos_diet)
print('pageDietTrades slice:', pos_diet, 'to', pos_rep)

met_chunk = html[pos_met:pos_diet]
met_headers = re.findall(r'<div[^>]*class="[^"]*section-title[^"]*"[^>]*>(.*?)</div>', met_chunk, re.DOTALL)
print('\n--- METABOLIC SECTIONS ---')
for h in met_headers:
    print('  ', re.sub(r'\s+', ' ', h.strip())[:120])

diet_chunk = html[pos_diet:pos_rep]
diet_headers = re.findall(r'<div[^>]*class="[^"]*section-title[^"]*"[^>]*>(.*?)</div>', diet_chunk, re.DOTALL)
print('\n--- MEAL PLANNER SECTIONS ---')
for h in diet_headers:
    print('  ', re.sub(r'\s+', ' ', h.strip())[:120])
