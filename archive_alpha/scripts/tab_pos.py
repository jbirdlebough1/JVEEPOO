with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()
print('pos_goals:', html.find('id="pageGoals"'))
print('pos_setup:', html.find('id="pageSetup"'))
print('pos_watchfaces:', html.find('id="pageWatchFaces"'))
