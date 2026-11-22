with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('function saveSetup')
if pos == -1:
    pos = html.find('saveSetup')
print(html[pos-100:pos+1500])
