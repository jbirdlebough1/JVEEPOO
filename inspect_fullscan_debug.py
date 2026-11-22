import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('class="lcars-workspace"')
if m != -1:
    print("--- lcars-workspace context ---")
    print(text[m:m+1500])

# Also let's check pageFullScan element itself
m2 = text.find('id="pageFullScan"')
if m2 != -1:
    print("--- pageFullScan content (first 2000 chars) ---")
    print(text[m2:m2+2000])

# Check if there are any unclosed tags inside pageFullScan or before it
# Let's count tags from beginning to pageFullScan
sub = text[:m2]
div_open = sub.count('<div')
div_close = sub.count('</div>')
print(f"Before pageFullScan: <div count={div_open}, </div> count={div_close}, diff={div_open - div_close}")

# Let's check from pageFullScan to next tab
m_next = text.find('id="pageVitals"')
sub_pfs = text[m2:m_next]
pfs_open = sub_pfs.count('<div')
pfs_close = sub_pfs.count('</div>')
print(f"Inside pageFullScan: <div count={pfs_open}, </div> count={pfs_close}, diff={pfs_open - pfs_close}")
