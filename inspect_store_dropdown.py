with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('LOCAL STORE SOURCING')
if pos == -1:
    pos = html.find('Local Store Sourcing')
print('LOCAL STORE SOURCING pos:', pos)
if pos != -1:
    print(html[pos-100:pos+1500])
