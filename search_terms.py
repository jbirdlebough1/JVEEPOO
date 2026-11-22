with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("Searching for mentoring...")
for m in re.finditer(r'mentor', text, re.IGNORECASE):
    print(repr(text[m.start()-50:m.end()+50]))

print("\nSearching for gapped...")
for m in re.finditer(r'gapped', text, re.IGNORECASE):
    print(repr(text[m.start()-50:m.end()+50]))

print("\nSearching for air...")
for m in re.finditer(r'air[- ]gap', text, re.IGNORECASE):
    print(repr(text[m.start()-50:m.end()+50]))

print("\nSearching for 06- inside pageDietTrades...")
idx_diet = text.find('id="pageDietTrades"')
idx_diet_end = text.find('id="pageMetabolic"', idx_diet)
diet_html = text[idx_diet:idx_diet_end]
for m in re.finditer(r'06|ADA|mentor|gap|quick nav', diet_html, re.IGNORECASE):
    print(f"Match in pageDietTrades: {repr(diet_html[m.start()-30:m.end()+30])}")
