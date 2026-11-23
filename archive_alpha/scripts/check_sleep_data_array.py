with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('const sleepData =')
if pos == -1:
    pos = html.find('let sleepData =')
if pos == -1:
    pos = html.find('sleepData =')
if pos != -1:
    print(html[pos-100:pos+1500])
