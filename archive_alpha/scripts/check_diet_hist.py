with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('async function renderDietHistory')
if pos == -1:
    pos = html.find('function renderDietHistory')
if pos != -1:
    print(html[pos:pos+3500])
