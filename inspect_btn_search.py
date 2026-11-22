with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('SEARCH LOCAL STORE PRICING')
print('pos:', pos)
if pos != -1:
    print(html[pos-100:pos+300])
