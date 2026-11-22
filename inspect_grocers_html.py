with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('containerTop3Grocers')
print(html[pos:pos+2500])
