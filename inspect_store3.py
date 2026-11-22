with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('<!-- Store 3 -->')
print(html[pos:pos+2000])
