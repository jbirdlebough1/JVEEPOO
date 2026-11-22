with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('PATHWAY B')
print('PATHWAY B pos:', pos)
print(html[pos-100:pos+2000])
