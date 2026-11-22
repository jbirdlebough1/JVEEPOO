with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'id="pageFullScan"' in l:
        before = lines[:i]
        b_open = sum(x.count('<div') for x in before)
        b_close = sum(x.count('</div>') for x in before)
        print(f"Before pageFullScan: open={b_open}, close={b_close}, diff={b_open - b_close}")
    if 'id="pageGoals"' in l:
        p_goals = i
    if '</main>' in l:
        print(f"Line {i+1}: {l.strip()}")
        after_goals = lines[p_goals:i]
        ag_open = sum(x.count('<div') for x in after_goals)
        ag_close = sum(x.count('</div>') for x in after_goals)
        print(f"pageGoals to </main>: open={ag_open}, close={ag_close}, diff={ag_open - ag_close}")
