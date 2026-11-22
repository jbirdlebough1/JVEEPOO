import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

for tab_id, start_line, end_line in [
    ('pageWatchFaces', 6610, 7302),
    ('pageMedication', 7303, 7453),
    ('pageGoals', 7454, 7684)
]:
    print(f"\n--- Checking {tab_id} (L{start_line} to L{end_line}) ---")
    open_divs = []
    for i in range(start_line - 1, end_line):
        line = lines[i]
        clean_line = re.sub(r'<!--.*?-->', '', line)
        tokens = re.finditer(r'(<div\b[^>]*>|</div>)', clean_line)
        for tok in tokens:
            tag = tok.group(0)
            if tag.startswith('<div'):
                m_id = re.search(r'id=["\']([^"\']+)["\']', tag)
                desc = m_id.group(1) if m_id else tag[:35]
                open_divs.append((i+1, desc))
            else:
                if open_divs:
                    closed = open_divs.pop()
                else:
                    print(f"  L{i+1}: EXTRA CLOSE </div> (open_divs is empty!)")
    print(f"End of {tab_id}: unclosed divs count = {len(open_divs)}")
    for item in open_divs:
        print("  Unclosed:", item)
