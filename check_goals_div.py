with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_goals = html.find('id="pageGoals"')
pos_setup = html.find('id="pageSetup"')
goals_content = html[pos_goals:pos_setup]

import re
opens = [m.start() for m in re.finditer(r'<div\b', goals_content)]
closes = [m.start() for m in re.finditer(r'</div>', goals_content)]
print(f"Goals opens: {len(opens)}, closes: {len(closes)}")
# Find the mismatch
depth = 0
for m in re.finditer(r'</?div\b[^>]*>', goals_content):
    tag = m.group(0)
    if tag.startswith('</'):
        depth -= 1
    else:
        depth += 1
    if depth < 0:
        pos_in_file = pos_goals + m.start()
        print(f"Extra closing div at relative {m.start()}, file {pos_in_file}:")
        print(goals_content[max(0, m.start()-100):min(len(goals_content), m.end()+100)])
        break
