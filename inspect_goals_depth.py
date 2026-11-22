with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'id="pageGoals"' in l:
        pg_start = i
    if '</main>' in l:
        pg_end = i
        break

goals_lines = lines[pg_start:pg_end]
d = 0
for k, gl in enumerate(goals_lines):
    op = gl.count('<div')
    cl = gl.count('</div>')
    d += (op - cl)
    if op != cl:
        # print non-zero changes
        pass
print(f"Goals depth at end: {d}")
# Check if pageGoals closing div is present
for k in range(len(goals_lines)-5, len(goals_lines)):
    print(f"Goal end line {pg_start + k + 1}: {goals_lines[k].strip()}")
