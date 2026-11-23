with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

# Check all tab-page divs and their line numbers
import re

print("--- ALL TAB PAGES ---")
tab_pages = []
for i, line in enumerate(lines):
    m = re.search(r'<div\s+id=[\'"]([^\'"]+)[\'"]\s+class=[\'"][^\'"]*tab-page[^\'"]*[\'"]', line)
    if not m:
        m = re.search(r'<div\s+class=[\'"][^\'"]*tab-page[^\'"]*[\'"]\s+id=[\'"]([^\'"]+)[\'"]', line)
    if m:
        tab_pages.append((i+1, m.group(1), line.strip()))
        print(f"Line {i+1}: {m.group(1)}")

print(f"\nTotal tab pages found: {len(tab_pages)}")

# Check nesting of tab pages: count opening and closing divs from line 1699 (<main class="lcars-workspace">)
print("\n--- NESTING DEPTH AT EACH TAB PAGE ---")
depth = 0
main_start = False
for i, line in enumerate(lines):
    if '<main' in line:
        main_start = True
        print(f"Main starts at L{i+1}")
    if '</main>' in line:
        print(f"Main ends at L{i+1}")
        main_start = False
    
    # Check if this line starts a tab page
    for t_line, t_id, _ in tab_pages:
        if i + 1 == t_line:
            print(f"  Tab {t_id} at line {t_line} (depth relative to main: {depth})")
            
    # Count <div> and </div>
    open_divs = len(re.findall(r'<div[\s>]', line))
    close_divs = len(re.findall(r'</div>', line))
    depth += (open_divs - close_divs)
