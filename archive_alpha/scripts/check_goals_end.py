with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_goals = html.find('id="pageGoals"')
pos_main_close = html.find('</main>', pos_goals)
goals_content = html[pos_goals:pos_main_close]

import re
opens = len(re.findall(r'<div\b', goals_content))
closes = len(re.findall(r'</div>', goals_content))
print(f"Inside main after pageGoals: opens: {opens}, closes: {closes}")
print(html[pos_main_close-100:pos_main_close+50])
