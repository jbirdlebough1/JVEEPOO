with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_goals = html.find('id="pageGoals"')
pos_main_close = html.find('</main>', pos_goals)
print(html[pos_main_close-300:pos_main_close+50])
