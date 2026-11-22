with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_goals = html.find('id="pageGoals"')
pos_main_close = html.find('</main>', pos_goals)
goals_lines = html[pos_goals:pos_main_close].split('\n')
for i in range(210, len(goals_lines)):
    print(f"{i+1}: {repr(goals_lines[i])}")
