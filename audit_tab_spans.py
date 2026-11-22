import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

tab_names = ['pageFullScan', 'pageCardio', 'pageMetabolic', 'pageExport', 'pageLearning', 'pageReports', 'pageSetup', 'pageLog', 'pageOperations', 'pageHelp', 'pageDietTrades', 'pageWatchFaces', 'pageMedication', 'pageGoals']

for t in tab_names:
    # find where id="<t>" begins
    start_line = None
    for i, line in enumerate(lines):
        if f'id="{t}"' in line:
            start_line = i
            break
    if start_line is None:
        print(f"Tab {t} NOT FOUND!")
        continue
    
    # count div depth until this tab closes
    depth = 0
    closed_at = None
    for i in range(start_line, len(lines)):
        line = lines[i]
        open_count = len(re.findall(r'<div[\s>]', line))
        close_count = len(re.findall(r'</div>', line))
        depth += (open_count - close_count)
        if depth == 0:
            closed_at = i + 1
            break
        elif depth < 0:
            closed_at = f"UNDERFLOW at line {i+1} (depth {depth})"
            break
            
    print(f"Tab {t:16}: starts line {start_line+1:5}, ends line {str(closed_at):5}, total lines: {closed_at - (start_line+1) if isinstance(closed_at, int) else 'ERR'}")
