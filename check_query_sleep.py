with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('function querySleepData')
if pos != -1:
    print(html[pos:pos+4000])
