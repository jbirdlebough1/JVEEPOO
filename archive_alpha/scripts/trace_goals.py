with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_goals = html.find('id="pageGoals"')
pos_main_close = html.find('</main>', pos_goals)
goals_snippet = html[pos_goals:pos_main_close]

depth = 0
for line_idx, line in enumerate(goals_snippet.split('\n')):
    opens = line.count('<div')
    closes = line.count('</div>')
    depth += opens - closes
    if depth < 0:
        print(f"Negative depth at line {line_idx+1}: {line}")
        print(f"depth is now {depth}")
print(f"Final depth at end of pageGoals: {depth}")
