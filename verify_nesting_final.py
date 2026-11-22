import sys
sys.path.insert(0, r'D:\veepoo')
import re
import verify_perfect_balance

sim = verify_perfect_balance.sim

lines = sim.splitlines()
tab_pages = []
for i, line in enumerate(lines):
    m = re.search(r'<div\s+id=[\'"]([^\'"]+)[\'"]\s+class=[\'"][^\'"]*tab-page[^\'"]*[\'"]', line)
    if not m:
        m = re.search(r'<div\s+class=[\'"][^\'"]*tab-page[^\'"]*[\'"]\s+id=[\'"]([^\'"]+)[\'"]', line)
    if m:
        tab_pages.append((i+1, m.group(1)))

depth = 0
for i, line in enumerate(lines):
    if '<main' in line:
        print(f"Main starts at L{i+1}")
    if '</main>' in line:
        print(f"Main ends at L{i+1} (depth: {depth})")
    
    for t_line, t_id in tab_pages:
        if i + 1 == t_line:
            print(f"  Tab {t_id:16} at line {t_line} (depth relative to main: {depth})")
            
    open_divs = len(re.findall(r'<div[\s>]', line))
    close_divs = len(re.findall(r'</div>', line))
    depth += (open_divs - close_divs)

print(f"Final depth at end of file: {depth}")
