with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

depth = 0
for i, l in enumerate(lines):
    opens = l.count('<div')
    closes = l.count('</div>')
    depth += (opens - closes)
    if depth < 0:
        print(f"Line {i+1} dropped below 0: {depth}")
print(f"Final depth: {depth}")

# Find which section changed depth
pages = ['pageFullScan', 'pageVitals', 'pageCardio', 'pageMetabolic', 'pageExport', 'pageLearning', 'pageReports', 'pageSetup', 'pageLog', 'pageOperations', 'pageHelp', 'pageDietTrades', 'pageWatchFaces', 'pageMedication', 'pageGoals']
for p in pages:
    idx = -1
    for i, l in enumerate(lines):
        if f'id="{p}"' in l or f"id='{p}'" in l:
            idx = i
            break
    if idx != -1:
        # count within page
        end_idx = len(lines)
        for j in range(idx+1, len(lines)):
            if any(f'id="{other}"' in lines[j] for other in pages if other != p):
                end_idx = j
                break
        page_lines = lines[idx:end_idx]
        p_open = sum(l.count('<div') for l in page_lines)
        p_close = sum(l.count('</div>') for l in page_lines)
        if p_open != p_close:
            print(f"Page {p} (lines {idx+1}-{end_idx}): open={p_open}, close={p_close}, diff={p_open - p_close}")
